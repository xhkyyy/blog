# inner_hits

> https://www.elastic.co/guide/en/elasticsearch/reference/7.4/search-request-body.html#request-body-search-inner-hits

### 用途

> 父子和嵌套场景下，允许返回不同范围内的匹配的文档

通过在嵌套、has_child、has_parent 查询和过滤器上定义 inner_hits 来返回不同范围内的数据。


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
    "match_all": {}
  },
  "sort": ["_id"]
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
    "terms": {
      "_id":[1]
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

GET my_index/_msearch
{}
{ "query": { "parent_id": { "type": "answer", "id": "1" } } }
{}
{ "query": { "terms": { "_id":[1] } } }


GET my_index/_search
{
  "query": {
    "parent_id": { 
      "type": "answer",
      "id": "1"
    }
  },
  "aggs": {
    "parents": {
      "terms": {
        "field": "my_join_field#question", 
        "size": 10
      }
    }
  },
  "script_fields": {
    "parent": {
      "script": {
         "source": "doc['text','my_join_field#question']" 
      }
    }
  }
}


POST /_sql/translate
{
  "query": "SELECT text FROM my_index where my_"
}

GET _search
{
  "query": {
    "match_all": {}
  }
}
```