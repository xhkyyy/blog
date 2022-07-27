# Dockerfile 中定制 debian/ubuntu 镜像源


### 使用清华源

```
RUN sed -i "s@http://@https://@g" /etc/apt/sources.list
RUN sed -i "s@deb.debian.org@mirrors.tuna.tsinghua.edu.cn@g" /etc/apt/sources.list
RUN sed -i "s@security.debian.org@mirrors.tuna.tsinghua.edu.cn@g" /etc/apt/sources.list
RUN apt-get update
```



执行下列命令，防止出现类似这样的错误：`buster-updates InRelease: At least one invalid signature was encountered.`

```
 docker image prune -f
 docker container prune -f
```



