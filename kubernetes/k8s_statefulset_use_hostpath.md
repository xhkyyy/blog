# statefulset use hostpath

```yaml
apiVersion: apps/v1
kind: StatefulSet
metadata:
  ...
spec:
  ...
  template:
    spec:
      containers:
        image: xxxxxxx
        name: xxxxx
        ...
        volumeMounts:
        - mountPath: /xxxxx/
          name: xxxxxxx
      initContainers:
        name: xxxxxxx
        ...
      volumes:
      - hostPath:
          path: xxxxxxx
          type: DirectoryOrCreate
        name: xxxxxxx
```