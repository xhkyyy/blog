# 二分查找算法

## ⚠️注意点

一、

在计算 min 时，要小心溢出。

错误点演示❌ int mid = (low + high) / 2

正确点演示👍 int mid = low + (high - low) / 2
