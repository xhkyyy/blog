# 关于在 Elasticsearch "分页" 方案

> https://www.elastic.co/guide/en/elasticsearch/reference/7.6/search-request-body.html#request-body-search-scroll

## 方案1 - scroll

适合场景：批量导出满足查询条件的全量数据

**优点**：

+ 查询速度快
+ 支持大数据量导出

**缺点**：

+ 默认同时支持最大 500 个有效的 scroll 快照
+ 在整个 scroll 快照期间，数据不会被更新 （即对 doc 的增删改不会反应到已经生成的 scroll 快照中）
+ 磁盘和内存占用开销较大
+ scroll 只能翻下一页，不支持向前翻页
+ 初次创建 scroll context 开销较大，实时性差

改进：

使用 `sort _doc` 提升性能

```json
GET /_search?scroll=1m
{
  "sort": [
    "_doc"
  ]
}
```

## 方案2 - from + size

适合场景：实时展示、用户只关注前 N 页数据，不会导出全量数据

优点：

+ 灵活，可以上、下翻页
+ 实时性强

缺点：

+ 查询速度与查询复杂度成正比 
+ 无法支持大数据量下的深度翻页

改进：

+ 查询过滤条件尽量用 `filter` 关键字
+ 通过 `search_after` 提升性能 （尽量不要使用 `_id` 作为 `tiebreaker`，可以使用时间字段。同时注意，`search_after` 不受 `index.max_result_window` 限制)
