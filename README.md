## 百万数据级别的数据导出
### 这是一个简单的 Demo
### Mysql 数据库的表结构在 sql 文件夹
### 批量生成数据
#### 创建存储过程，实现批量插入记录
```
delimiter $$ #声明存储过程的结束符号为$$
create procedure auto_insert()
BEGIN
    declare i int default 1;
    while(i<=3000000)do
        insert into million_data values(
                                        i
                                        ,REPLACE(UUID(),"-","")
                                        ,concat(MOD(i, 24), MOD(i, 24))
                                        ,concat(CHAR(MOD(i, 24)+64), CHAR(MOD(i, 24)+64))
                                        ,concat('测试',CHAR(MOD(i, 24)+64), CHAR(MOD(i+1, 24)+64))
                                        ,now()
                                        );
        set i=i+1;
    end while;
END$$ #$$结束
delimiter ; #重新声明分号为结束符号
```

#### 调用存储过程
```
call auto_insert();
```
