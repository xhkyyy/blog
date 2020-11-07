# Reindex API

> https://www.elastic.co/guide/en/elasticsearch/reference/7.4/docs-reindex.html

### 1.注意

1. 原索引(source)必须要启用 _source
2. Reindex 不会同步 settings、mappings、shard counts、replicas，所以，需要先创建和配置目标索引
3. Reindex 获取的是索引的快照(scroll)
4. 不同的集群之间也可以 Reindex

### 2.version_type

**internal 或 缺省**

> Omitting `version_type` or setting it to `internal` causes Elasticsearch to blindly dump documents into the target, overwriting any that happen to have the same ID.

直接覆盖所有已经存在相同 ID 的文档.

**external：**

> Setting `version_type` to `external` causes Elasticsearch to preserve the version from the source, create any documents that are missing, and update any documents that have an older version in the destination index than they do in the source index.

遇到相同 ID 的文档就更新，否则就插入.

```shell
curl -X POST "localhost:9200/_reindex?pretty" -H 'Content-Type: application/json' -d'
{
  "source": {
    ...
  },
  "dest": {
   	...
    "version_type": "external"
  }
}
'
```

### 3.op_type

Setting `op_type` to `create` causes _reindex to only create missing documents in the target index. All existing documents will cause a version conflict.

设置成 create 只会同步缺失的文档. 已经存在的文档将抛出版本冲突错误.

### 4.关于 version conflict

默认，发生 version conflict 时，Reindex 将终止。不过，设置 `conflicts`  为 `proceed` 时将忽略错误，继续 Reindex.

```json
curl -X POST "localhost:9200/_reindex?pretty" -H 'Content-Type: application/json' -d'
{
 "conflicts": "proceed",
  "source": {
  },
  "dest": {
  }
}
'
```

### 5.异步运行 Reindex

在 Query parameters 中设置`wait_for_completion=false`，将异步执行 Reindex.
返回值中将包含 task id，通过 [task API](https://www.elastic.co/guide/en/elasticsearch/reference/7.4/tasks.html) 可以查询任务执行状态.

```json
curl -X POST "localhost:9200/_reindex?pretty&wait_for_completion=false" -H 'Content-Type: application/json' -d'
{
 "conflicts": "proceed",
  "source": {
  },
  "dest": {
  }
}
'
```

### 6.Reindex 多个索引

```shell
for index in 'index1' 'index2' 'index3'; do
  curl -HContent-Type:application/json -XPOST localhost:9200/_reindex?pretty -d'{
    "source": {
      "index": "'$index'"
    },
    "dest": {
      "index": "'$index'-reindexed"
    }
  }'
done
```

### 7.Throttling

设置 `requests_per_second` 任何正数启用 Throttling.
禁用 Throttling，则设置 `requests_per_second=-1`.

`requests_per_second` 可以通过 `_rethrottle API` 在 Reindex 过程中调整它的大小.

如果增大`requests_per_second`的值(加速)，会立即生效，而如果减少它的值(减速)，则不会立即生效，需要下一个 batch 才会生效.

```shell
curl -X POST "localhost:9200/_reindex/${task ID}/_rethrottle?requests_per_second=-1&pretty"
```


### 8.Slicing

Reindex 支持 Sliced Scroll 以并行重建索引. 

当分配数量和 slices 的大小一致时，性能最好。

 

##### Manual slicing

```shell
curl -X POST "localhost:9200/_reindex?pretty" -H 'Content-Type: application/json' -d'
{
  "source": {
    ...
    "slice": {
      "id": 0,
      "max": 2
    }
  },
  "dest": {
    ...
  }
}
'
curl -X POST "localhost:9200/_reindex?pretty" -H 'Content-Type: application/json' -d'
{
  "source": {
    ...
    "slice": {
      "id": 1,
      "max": 2
    }
  },
  "dest": {
    ...
  }
}
'
```

##### Automatic slicing

通过指定 `slices` 参数实现 Automatic slicing.

slices 可选值为：
1. auto
2. 正整数，如 5

```shell
# slices=5

curl -X POST "localhost:9200/_reindex?slices=5&refresh&pretty" -H 'Content-Type: application/json' -d'
{
  "source": {
    ...
  },
  "dest": {
    ...
  }
}
'
```

```shell
# slices=auto

curl -X POST "localhost:9200/_reindex?slices=auto&refresh&pretty" -H 'Content-Type: application/json' -d'
{
  "source": {
    ...
  },
  "dest": {
    ...
  }
}
'
```

### Reindex routing

支持3 种 `routing`.

1. keep：默认支持，保持原文档中的 routing 不变.
2. discard：不保留 routing.
3. =<some text>：设置 routing 为 <some text>.

```shell
curl -X POST "localhost:9200/_reindex?pretty" -H 'Content-Type: application/json' -d'
{
  "source": {
    "index": "source",
    "query": {
      "match": {
        "company": "cat"
      }
    }
  },
  "dest": {
    "index": "dest",
    "routing": "=cat"
  }
}
'
```

### batch size

默认，scroll size 是 1000.

可以通过在 source 索引上指定 size 指定 batch size 大小.

```shell
curl -X POST "localhost:9200/_reindex?pretty" -H 'Content-Type: application/json' -d'
{
  "source": {
    "index": "source",
    "size": 10000
  },
  "dest": {
    "index": "dest",
    "routing": "=cat"
  }
}
'
```

### pipeline

Reindex 时，也可以使用 pipline.

```shell
curl -X POST "localhost:9200/_reindex?pretty" -H 'Content-Type: application/json' -d'
{
  "source": {
    "index": "source"
  },
  "dest": {
    "index": "dest",
    "pipeline": "some_ingest_pipeline"
  }
}
'
```

### 参数汇总

**Query parameters**

```
refresh
(Optional, enum) If true, Elasticsearch refreshes the affected shards to make this operation visible to search, if wait_for then wait for a refresh to make this operation visible to search, if false do nothing with refreshes. Valid values: true, false, wait_for. Default: false.

timeout
(Optional, time units) Specifies the period of time to wait for a response. If no response is received before the timeout expires, the request fails and returns an error. Defaults to 30s.

wait_for_active_shards
(Optional, string) The number of shard copies that must be active before proceeding with the operation. Set to all or any positive integer up to the total number of shards in the index (number_of_replicas+1). Default: 1, the primary shard.

See Active shards.

wait_for_completion
(Optional, boolean) If true, the request blocks until the operation is complete. Defaults to true.

requests_per_second
(Optional, integer) The throttle for this request in sub-requests per second. Defaults to -1 (no throttle).

scroll
(Optional, time units) Specifies how long a consistent view of the index should be maintained for scrolled search.

slices
(Optional, integer) The number of slices this task should be divided into. Defaults to 1 meaning the task isn’t sliced into subtasks.

max_docs
(Optional, integer) Maximum number of documents to process. Defaults to all documents.
```

**Request body**

```
conflicts
(Optional, enum) Set to proceed to continue reindexing even if there are conflicts. Defaults to abort.

source
    index
        (Required, string) The name of the index you are copying from. Also accepts a comma-separated list of indices to reindex from multiple sources.
    max_docs
        (Optional, integer) The maximum number of documents to reindex.
    query
        (Optional, query object) Specifies the documents to reindex using the Query DSL.
    remote
        host
            (Optional, string) The URL for the remote instance of Elasticsearch that you want to index from. Required when indexing from remote.
        username
            (Optional, string) The username to use for authentication with the remote host.
        password
            (Optional, string) The password to use for authentication with the remote host.
        socket_timeout
            (Optional, time units) The remote socket read timeout. Defaults to 30 seconds.
        connect_timeout
            (Optional, time units) The remote connection timeout. Defaults to 30 seconds.
    size
        {Optional, integer) The number of documents to index per batch. Use when indexing from remote to ensure that the batches fit within the on-heap buffer, which defaults to a maximum size of 100 MB.
    slice
        id
            (Optional, integer) Slice ID for manual slicing.
        max
            (Optional, integer) Total number of slices.
    sort
        (Optional, list) A comma-separated list of <field>:<direction> pairs to sort by before indexing. Use in conjunction with max_docs to control what documents are reindexed.
    _source
        (Optional, string) If true reindexes all source fields. Set to a list to reindex select fields. Defaults to true.
dest
    index
        (Required, string) The name of the index you are copying to.
    version_type
        (Optional, enum) The versioning to use for the indexing operation. Valid values: internal, external, external_gt, external_gte. See Version types for more information.
    op_type
        (Optional, enum) Set to create to only index documents that do not already exist (put if absent). Valid values: index, create. Defaults to index.
script
    source
        (Optional, string) The script to run to update the document source or metadata when reindexing.
    lang
        (Optional, enum) The script language: painless, expression, mustache, java. For more information, see Scripting.
```


