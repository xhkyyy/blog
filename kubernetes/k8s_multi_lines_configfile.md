# kubernetes 支持多行的配置文件

```yaml
initContainers:
- name: init
  image: busybox:1.35.0
  command:
    - sh
    - -exc
    - |
      cat <<EOT > /tmp/1.txt
      line 1
      EOT
      cat <<EOT > /tmp/2.txt
      line 2
      EOT
  volumeMounts:
  - name: ...
    mountPath: ...
```