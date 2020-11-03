# Mapping parameters

> https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping-params.html

+ index: 默认 true，设置字段是否被索引。

+ store：默认 false，默认不会存储字段的“原始值”，store 不会影响搜索，只是不存储原始值。字段的原始值是 _source 值的一部分。

+ enable：默认 true，该属性可以作用在整个 mapping 或字段上，如果某个字段 enable 被设置为 false，说明该字段将不能被检索，但是可以被获取到原始值。

+ norms: 默认 true，是索引的评分因子，如果某个字段仅用于过滤和聚合分析，那么可以将该字段 norms 设置为 flase，以节省空间。

+ doc_values: 默认 true，doc_values 和 _source 存储的是相同的内容，doc_values 在构建索引时被创建，但它是 column-oriented 存储的，在聚合和排序的场景下，比_source 高效。text 和 annotated_text 无法开启 doc_values。如果一个字段既不会被排序、聚合，也不会通过 script 访问该字段的值，那么，可以关闭 doc_value，以节省空间。关闭 doc_values 的字段仍然可以被用于检索。(由于 doc_values 不支持 text，所以，在 text 类型下有 fielddata 方案，但是 fielddata 是不推荐的，更好的方式是同时建立 keywork 和 text 2 钟类型的字段：https://www.elastic.co/guide/en/elasticsearch/reference/current/fielddata.html )


