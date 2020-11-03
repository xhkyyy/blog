# 常用 REST API

### 1.查看 shards 信息，包括分片在各个节点的分布、那些正在 INITIALIZING 等

```
curl -XGET esurl.com:8000/_cat/shards?pretty
```

### 2.查看 allocation 等原因

```
curl -XGET esurl.com:8000/_cluster/allocation/explain?pretty

```