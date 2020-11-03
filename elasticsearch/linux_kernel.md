# Centos 7 Linux 内核升级

### 升级步骤

```
1.启用 elrepo：
rpm --import https://www.elrepo.org/RPM-GPG-KEY-elrepo.org
rpm -Uvh https://www.elrepo.org/elrepo-release-7.0-3.el7.elrepo.noarch.rpm  （可以去 www.elrepo.org 找到最新的 rpm）

2.列出可用的内核版本：
yum --disablerepo="*" --enablerepo="elrepo-kernel" list available

3.选择安装 lt 版本 （lt 表示长期支持稳定版本，ml 表示主线稳定版本）
yum --enablerepo=elrepo-kernel install  kernel-lt-devel kernel-lt

4.查看内核启动顺序：
awk -F\' '$1=="menuentry " {print i++ " : " $2}' /etc/grub2.cfg

5. grub2-set-default 0    （一般安装完成后，排在第一个到就是新安装的内核版本，选择默认启动它）
```
