# Parent-Child 搜索

## 关键点

### 1.如果查询字段是 string 类型，确保查询语句与是否分词 `not_analyzed` 关系正确
```json
field_name: {
    type: 'string',
    index : 'not_analyzed',
}
```

