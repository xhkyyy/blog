# 大文本存储

## ES 2 版本

#### 场景1

> 大小无限制

```json
"large_text": {
    "type": "string",
    "index": "no"
}
```

#### 场景2

> 最大支持 32766 个字节

```json
"large_text": {
    "type": "string",
    "index": "not_analyzed"
}
```


## ES 7 版本

#### 场景1

> 大小无限制

```json
"large_text": {
    "type": "text",
    "index": false
}
```

#### 场景2

> 最大支持 32766 个字节

定义成 keyword 而非 text 类型，存储文本大小均受到最大 32766 个字节限制。

```json
"large_text": {
    "type": "keyword",
    "index": false
}
```