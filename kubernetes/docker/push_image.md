# 将 image 从一个 Registry 迁移到另一个 Registry

## 步骤

> ./migration.sh source-registry.com dest-registry.com  source-registry.com/imageA:v1 dest-registry.com/imageA:v1


```sh
source_registry=$1 # 源 Registry
dest_registry=$2 # 目标 Registry

source_image=$3 # 源 image
dest_image=$4 # 目标 image

docker login $source_registry

docker pull $source_image

docker tag $source_image $dest_image

docker logout $source_image

docker login $dest_registry

docker push $dest_image

docker logout $dest_image
```