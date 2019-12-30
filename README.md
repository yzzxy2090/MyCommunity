## 我的论坛
## 技术选型
    - SpringBoot
    - Mybatis
##参考
[【Spring Boot 实战】论坛项目【第一季】【修正版】](https://www.baidu.com/s?wd=markdown%E9%93%BE%E6%8E%A5&f=12&rsp=0&oq=markdown%E8%BF%9E%E6%8E%A5&ie=utf-8&rsv_pq=910aa098000a85d0&rsv_t=cbe8C%2bKsW1EOSqik%2bh9oDohPUTc8UFL89u9q9C1Mu9PXsi1Lu84B6ILXJtQ&rqlang=cn)

##数据库sql脚本
```sql
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `token` char(36) DEFAULT NULL,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1
```