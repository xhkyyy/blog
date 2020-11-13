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

### 通过 ILM 自动管理

```
GET /_cat/indices/logs-testilm-*?v&s=index


GET /_cat/aliases/logs_write?v


DELETE logs-testilm-00000*

DELETE logs_write*


DELETE /_template/template_j6javlxz804z3ixyrhmm


DELETE _ilm/policy/ilm_g6nknU978gyb390xSYsflgPEPm3B


PUT _ilm/policy/ilm_g6nknU978gyb390xSYsflgPEPm3B
{
  "policy": {
    "phases": {
      "hot": {
        "actions": {
          "rollover": {
            "max_age": "50m",
            "max_docs": 2
          },
          "set_priority": {
            "priority": 100
          }
        }
      }
    }
  }
}


PUT _template/template_j6javlxz804z3ixyrhmm
{
  "index_patterns": ["logs-testilm-*"], 
  "settings": {
    "number_of_shards": 1,
    "number_of_replicas": 1,
    "index.lifecycle.name": "ilm_g6nknU978gyb390xSYsflgPEPm3B", 
    "index.lifecycle.rollover_alias": "logs_write"
  }
}


GET /logs-testilm-000001

PUT _cluster/settings
{
  "transient": {
    "indices.lifecycle.poll_interval": "1m" 
  }
}



PUT logs-testilm-000001
{
  "aliases": {
    "logs_write": {
      "is_write_index": true
    }
  }
}


POST logs_write/_search
{
  "query": {
    "match_all": {}
  }
}


POST logs_write/_doc
{
  "message": "a dummy log"
}

# 记得要 refresh
POST logs_write/_refresh


```
