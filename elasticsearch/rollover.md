# Rollover index API

> https://www.elastic.co/guide/en/elasticsearch/reference/7.4/indices-rollover-index.html


#### 手动触发 Rollover

```
GET /_cat/indices/logs-*?v&s=index

GET /_cat/aliases/logs_write?v


DELETE logs-000001


GET /logs-000001


PUT logs-000001
{
  "aliases": {
    "logs_write": { "is_write_index": true } 
  }
}

# 这时索引不会被 rollover
# "acknowledged" : false

POST /logs_write/_rollover
{
  "conditions": {
    "max_age": "5m",
    "max_docs": 2
  },
  "settings": {
    "index.number_of_shards": 1
  }
}

POST logs_write/_search
{
  "query": {
    "match_all": {}
  }
}

# 连续请求写 2 + 个 doc

POST logs_write/_doc
{
  "message": "a dummy log"
}

POST logs_write/_refresh

# 这时索引会被 rollover
#  "acknowledged" : true

POST /logs_write/_rollover
{
  "conditions": {
    "max_age": "5m",
    "max_docs": 2
  },
  "settings": {
    "index.number_of_shards": 1
  }
}
```
