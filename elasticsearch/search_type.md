# Elasticsearch： Search Type

搜索是一个将查询请求发送到分片（replia 或 primary），各个分片经过本地查询后，将结果返回到 coordinating 节点的过程。

一次查询的过程大致是：

当一个查询要求返回前 10 个查询结果，假如我们有 10 个分片，这时需要每个分片分别返回各自的 10 个结果集（共 100 个），然后 coordinating 节点经过相关度排名、筛选之后，返回相关性最大的前 10 个查询结果到客户端。
之所以每个分片都返回 10 个结果集，是因为每个分片都是独立的，当在一个分片执行查询时，它是不考虑词频或者其它分片的相关信息，比如文档数等。

Elasticsearch 通过在 query string 上配置一个 search_type 参数的方式，提供了一个可以控制查询行为的特性。

默认的 search_type 是：query_then_fetch。

- - - -

[参考文章1](https://www.datadoghq.com/blog/monitor-elasticsearch-performance-metrics/#toc-host-level-network-and-system-metrics)

[参考文章2](http://www.cnblogs.com/zlslch/p/6438352.html)






