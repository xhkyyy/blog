# MySQL 查询记录添加行号

```sql

SELECT
	(@row_number: = @row_number + 1) AS line_number,
	firstName,
	lastName
FROM
	employees,
	(SELECT @row_number: = 0) AS t
ORDER BY
	firstName,
	lastName

```
