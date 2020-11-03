# 包含多个子查询，可以控制每个子查询得分比重的例子

```json
{
  "_source": [
    "your_fields"
  ],
  "query": {
    "bool": {
      "should": [
        {
          "match_phrase": {
            "your_fields": {
              "query": "初二",
              "boost": 300,
              "slop": 0
            }
          }
        },
        {
          "match_phrase": {
            "your_fields": {
              "query": "二期",
              "boost": 10,
              "slop": 0
            }
          }
        },
        {
          "match": {
            "your_fields": {
              "query": "默默",
              "boost": 1
            }
          }
        }
      ]
    }
  }
}
```


![1](./images/boost_slop_query.png)

