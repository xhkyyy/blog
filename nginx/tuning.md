
# Nginx 性能调优

优化方向：

+ Linux

  + net.core.somaxconn ***排队最大连接数***

  + net.core.netdev_max_backlog ***网卡相关***

  + **file descriptors**

    + sys.fs.file-max ***系统级的最大文件打开数***

    + nofile（/etc/security/limits.conf） ***进程级的最大文件打开数***

+ Nginx 参数

  + **Worker Processes**

    + worker_processes

    + worker_connections

  + **Keepalive**

    + **Keepalive Connections**

      + keepalive_requests

      + keepalive_timeout

    + **Upstream keepalives**

      + keepalive

  + **Access Logging**

    + access_log [buffer/flush](https://nginx.org/en/docs/http/ngx_http_log_module.html#access_log)

  + **Sendfile**

    + [sendfile](https://nginx.org/en/docs/http/ngx_http_core_module.html#sendfile) (zero‑copy)

  + **Limits**

    + limit_conn/limit_conn_zone

    + limit_rate

    + limit_req/limit_req_zone

    + max_conns

    + queue\Caching\Compression (NGINX Plus)

```nginx
# 启用 keepalive 也需要包含下面的配置指令
proxy_http_version 1.1;
proxy_set_header Connection "";
```