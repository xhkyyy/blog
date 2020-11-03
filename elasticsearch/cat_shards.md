# 统计每个分片在各个节点都分布

### 1.通过 Cat API 获取分片信息

> https://www.elastic.co/guide/en/elasticsearch/reference/current/cat-shards.html

```
GET _cat/shards
```

### 2.使用这个[脚本](https://github.com/xhkyyy/PyFor/blob/master/elasticsearch/es-count-shards.py)统计输出结果

> r ：副本
> p ：主分片

```
node name  r   p
node-1 1131 641
node-2 2026 185
node-3 1848 614
node-4 1675 1465
```

