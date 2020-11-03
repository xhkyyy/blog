# inner_hits

> https://www.elastic.co/guide/en/elasticsearch/reference/7.4/search-request-body.html#request-body-search-inner-hits

### 用途

> parent/child 和 nested 场景下，允许返回真正匹配关联关系的文档

通过在 nested、has_child、has_parent 查询和过滤器上定义 inner_hits 来返回不同范围内的数据，告诉你当前的查询匹配的是哪些字段，命中的是哪些文档。

也即是：

1. 对于 parent/child，当你根据 parent 查询 child 的时候，可以把 parent、child 的 doc 一并返回，反之亦然。
2. 对于 nested，将返回命中的是哪个 nested 字段（如果有多个的话，将很有用）以及哪个 doc。

**parent/child**

```json
DELETE my_index

GET my_index

PUT my_index
{
  "mappings": {
    "properties": {
      "my_join_field": { 
        "type": "join",
        "relations": {
          "question": "answer" 
        }
      }
    }
  }
}

PUT my_index/_doc/1?refresh
{
  "text": "This is a question",
  "my_join_field": {
    "name": "question" 
  }
}


PUT my_index/_doc/2?refresh
{
  "text": "This is another question",
  "my_join_field": {
    "name": "question"
  }
}

PUT my_index/_doc/3?routing=1&refresh
{
  "text": "This is an answer",
  "my_join_field": {
    "name": "answer", 
    "parent": "1" 
  }
}

PUT my_index/_doc/4?routing=1&refresh
{
  "text": "This is another answer",
  "my_join_field": {
    "name": "answer",
    "parent": "1"
  }
}


GET my_index/_search
{
  "query": {
    "parent_id": { 
      "type": "answer",
      "id": "1"
    }
  }
}


GET my_index/_search
{
  "query": {
    "has_parent": {
      "parent_type": "question",
      "query": {
        "terms": {
          "_id": [
            1
          ]
        }
      },
      "inner_hits": {}
    }
  }
}

```