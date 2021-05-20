# Microservices Patterns

> https://book.douban.com/subject/33425123/



真的是一本可以给 5 星的书。

作者从一个单体应用为切入点，把将它发展为微服务的所有阶段都描述的非常清晰。

这其中涉及到了每一个阶段都包含要面临的问题以及大量的技术实践细节。



----------------

### 思考

##### P119

问：creating ticket 状态为什么没有经过 rejecting order 状态？

答：creating ticket 失败后，不涉及到事务补偿。

