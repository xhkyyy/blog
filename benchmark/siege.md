# siege

> web benchmark 工具

## 安装

+ github ：https://github.com/JoeDog/siege

+ 安装 yum install siege 或 make

## 示例：POST 请求

**urls.file**

```json
http://x.y.z.h.com/xxxxxx/_search POST { "query": { "term": { "username": "xyz" } } }
http://w.z.c.b.com/xxxxxx/_search POST { "query": { "term": { "username": "xyz" } } }
http://u.r.a.h.com/xxxxxx/_search POST { "query": { "term": { "username": "xyz" } } }
```

```shell
auth=$(echo -n '用户名:密码' | openssl base64)

# 请求总数：并发数 * 重复次数
siege   -c 并发数 -r 重复次数 -i --header="Authorization:Basic $auth" --content-type "application/json" -f urls.file
``` 