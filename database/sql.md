# SQL


```sql

# insert where not exists
NSERT INTO table1 
            ( 
                        user_name, 
			  email,
                        x .... 
            ) 
SELECT ('user', '1@qq.com', '18666666666', ...) 
WHERE  NOT EXISTS 
       ( 
              SELECT id 
              FROM   table1 
              WHERE  x='18666666666');



```
