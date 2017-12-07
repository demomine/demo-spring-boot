# demo-spring-boot
spring boot dao demo

# mybatis plus
##  id type
 AUTO->`0`("数据库ID自增")
 INPUT->`1`(用户输入ID")
 ID_WORKER->`2`("全局唯一ID")
 UUID->`3`("全局唯一ID")
## 异常信息处理
1.Cannot determine embedded database driver class for database type NONE
>   配置 spring.datasource.url 而不是 spring.datasource.druid.url
