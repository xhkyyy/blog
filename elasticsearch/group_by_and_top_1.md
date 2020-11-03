# 按照 field__1、field__2 分组，分组后按照 field__3 组内排序，并返回第一个文档


```json
DELETE my_index

PUT my_index

POST /my_index/_mapping
{
  "properties": {
    "name": {
      "type": "text"
    },
    "type_key": {
      "type": "keyword"
    },
    "cost": {
      "type": "float"
    },
    "type": {
      "type": "keyword"
    }
  }
}

POST /my_index/_bulk
{"index":{"_index":"my_index"}}
{"name":"汽车1","cost":1,"type":"汽车","type_key":1}
{"index":{"_index":"my_index"}}
{"name":"汽车2","cost":2,"type":"汽车","type_key":1}
{"index":{"_index":"my_index"}}
{"name":"火车100","cost":100,"type":"火车","type_key":100}
{"index":{"_index":"my_index"}}
{"name":"汽车3","cost":3,"type":"汽车","type_key":1}
{"index":{"_index":"my_index"}}
{"name":"火车80","cost":80,"type":"火车","type_key":100}
{"index":{"_index":"my_index"}}
{"name":"汽车3","cost":3,"type":"汽车","type_key":1}
{"index":{"_index":"my_index"}}
{"name":"汽车4","cost":4,"type":"汽车","type_key":1}
{"index":{"_index":"my_index"}}
{"name":"汽车5","cost":5,"type":"汽车","type_key":1}
{"index":{"_index":"my_index"}}
{"name":"自行车5","cost":5,"type":"自行车","type_key":1}
{"index":{"_index":"my_index"}}
{"name":"火车2","cost":2,"type":"火车","type_key":2}
{"index":{"_index":"my_index"}}
{"name":"摩托车2","cost":2,"type":"摩托车","type_key":1}
{"index":{"_index":"my_index"}}
{"name":"汽车0","cost":0,"type":"汽车","type_key":1}


POST my_index/_search
{
  "query": {
    "match": {
      "name": "车"
    }
  },
  "aggregations": {
    "groupby": {
      "composite": {
        "size": 1000,
        "sources": [
          {
            "typex": {
              "terms": {
                "field": "type"
               
              }
            }
          },
          {
            "type_keyx": {
              "terms": {
                "field": "type_key"
              }
            }
          }
        ]
      },
      "aggs": {
        "first_col": {
          "top_hits": {
            "sort": [
              {
                "cost": {
                  "order": "asc"
                }
              }
            ],
            "size": 1
          }
        }
      }
    }
  },
  "size": 0,
  "from": 0
}

```