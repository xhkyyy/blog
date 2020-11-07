# Reindex API

### 1.注意

1. 原索引(source)必须要启用 _source
2. Reindex 不会同步 settings、mappings、shard counts、replicas，所以，需要先创建和配置目标索引
3. Reindex 获取的是索引的快照(scroll)

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






