# fielddata 和 doc_values

倒排索引用于快速的全文搜索，它存储的是词和包含这些词的文档的映射关系。

```
Term      Doc_1   Doc_2   Doc_3
------------------------------------
brown   |   X   |   X   |
dog     |   X   |       |   X
dogs    |       |   X   |   X
fox     |   X   |       |   X
foxes   |       |   X   |
in      |       |   X   |
jumped  |   X   |       |   X
```

由于倒排索引这种特殊的索引结构，导致它在查询包含了某些词的文档时非常高效，但是，反方向的操作就不太高效了，比如，某文档包含了哪些词。

再比如：聚合和排序，这类场景倒排索引都无法很好都胜任，因此，出现了 `doc_values` 和 `fielddata`。 



### doc_values

doc_values 用于加快聚合和排序都速度，它的结构如下：

```
Doc      Terms
-----------------------------------------------------------------
Doc_1 | brown, dog, fox, jumped, lazy, over, quick, the
Doc_2 | brown, dogs, foxes, in, lazy, leap, over, quick, summer
Doc_3 | dog, dogs, fox, jumped, over, quick, the
```

### fielddata

fielddata 的出现，是由于 text 类型不支持 doc_values。

当字段首次用于聚合和排序时，Elasticsearch 会从倒排索引中反向查询出该字段的所有 term，并加载到内存中。

fielddata 预加载做到在索引刷新的时候载入 fielddata, 而不是查询时，从而提高搜索体验。不过，导致占用大量堆内存的代价是不变的。
