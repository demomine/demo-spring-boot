# demo-spring-boot
spring boot demo


##  异常
1.  Cannot determine embedded database driver class for database type NONE
> 配置`@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})`