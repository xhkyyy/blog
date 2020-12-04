# Histogram Aggregation

> https://www.elastic.co/guide/en/elasticsearch/reference/7.4/search-aggregations-bucket-histogram-aggregation.html


```json

DELETE sales


PUT /sales
{
  "mappings": {
    "properties": {
      "product": {
        "type": "keyword"
      },
      "timestamp": {
        "type": "date"
      },
      "price": {
        "type": "long"
      },
      "shop": {
        "type": "keyword"
      }
    }
  }
}


POST /sales/_bulk?refresh
{"index":{"_id":0}}
{"product":"mad max","price":20,"timestamp":"2017-05-09T14:35"}
{"index":{"_id":1}}
{"product":"mad max","price":25,"timestamp":"2017-05-09T12:35"}
{"index":{"_id":2}}
{"product":"rocky","price":10,"timestamp":"2017-05-08T09:10"}
{"index":{"_id":3}}
{"product":"mad max","price":27,"timestamp":"2017-05-10T07:07"}
{"index":{"_id":4}}
{"product":"apocalypse now","price":10,"timestamp":"2017-05-11T08:35"}
{"index":{"_id":6}}
{"product":"apocalypse now","timestamp":"2017-05-11T08:35"}
#{"index":{"_id":5}}
#{"product":"apocalypse now","price":0,"timestamp":"2012-05-11T08:35"}



GET sales/_search

GET sales

POST /sales/_search?size=0
{
  "aggs": {
    "prices": {
      "histogram": {
        "field": "price",
        "interval": 10,
        "offset": 0,
        "missing": 0
      }
    }
  }
}

POST /sales/_search?size=0
{
  "aggs": {
    "prices": {
      "histogram": {
        "field": "price",
        "interval": 10,
        "min_doc_count" : 3,
        "offset": 0
      }
    }
  }
}


POST /sales/_search?size=0
{
  "aggs": {
    "prices": {
      "histogram": {
        "field": "price",
        "interval": 10,
        "offset": 0,
        "extended_bounds": {
          "min": 0,
          "max": 50
        }
      }
    }
  }
}
```
