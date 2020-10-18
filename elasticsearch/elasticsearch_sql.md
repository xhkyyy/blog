# Elasticsearch SQL

> https://www.elastic.co/guide/en/elasticsearch/reference/7.4/xpack-sql.html

## 将 SQL 转化成 DSL

> https://www.elastic.co/guide/en/elasticsearch/reference/7.4/sql-translate.html

```shell
curl -XPOST "域名/_sql/translate" -H 'Content-Type: application/json' -d'{

	"query":	"select * from my_index"
 		
}'
```

## 使用 SQL 查询

```shell
curl -XPOST "域名/_sql?format=json" -H 'Content-Type: application/json' -d'{ 

	"query": "select * from my_index"

}'
```

其中，format 可以是 txt、json 等，见：[返回值类型](https://www.elastic.co/guide/en/elasticsearch/reference/7.4/sql-rest-format.html)

## SQL 使用限制

> https://www.elastic.co/guide/en/elasticsearch/reference/7.4/sql-limitations.html#large-parsing-trees

1. 只支持 `select`
2. 大的查询可能会抛出 `ParsingExpection`
3. 对于 Nested 类型的字段：
	1. 只能使用子字段，不能使用该 Nested  字段：`[nested_field_name].[sub_field_name]`
	2. 一个查询中不能包含多个 Nested 字段
	3. 不支持分页查询
4. `keyword` 字段不支持 `normalizer`
5. 不支持数组类型的字段（一个字段包含多个值的字段）
6. 聚合查询时：
	1. 只支持对前 512 个文档进行排序
	2. 聚合函数 MIN、MAX 不能用在子查询中
	3. 理论上只支持对聚合字段进行排序
7. 对于子查询：
	1. 仅支持有限的简单子查询
	2. 不支持子查询中包含：group by、having
8. 不支持在 having 时使用 FIRST、LAST 函数
9. SQL column 必须包含在 _source 中，否则查询不出来（这些类型除外 keyword、date、scaled_float、geo_point、geo_shape）



