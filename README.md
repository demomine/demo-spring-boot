# demo-spring-boot
spring boot demo
##   测试原则
>   test behavior not implementation

##  mockito术语解释
1.  Dummy

2.  Fake

3.  Stub
>   按照预定的行为进行(执行了特定的方法)

4.  Mock
>   创建Mock是为了测试方法

5.  Spy

##  mockito常用方法
1.  when
2.  then
3.  verify
4.  answer

##  书写顺序
1.  given
2.  when
3.  then
4.  verify

##  spring boot test
1.  annotation
1.1.    @DataJpaTest
1.2.    @DataLdapTest
1.3.    @DataMongoTest
1.4.    @DataNeo4jTest
1.5.    @DataRedisTest
1.6.    @JdbcTest
1.7.    @JooqTest
1.8.    @JsonTest
1.9.    @RestClientTest
1.10.   @WebFluxTest
1.11.   @WebMvcTest
2.  支持的框架
JUnit — The de-facto standard for unit testing Java applications.
Spring Test & Spring Boot Test — Utilities and integration test support for Spring Boot applications.
AssertJ — A fluent assertion library.
Hamcrest — A library of matcher objects (also known as constraints or predicates).
Mockito — A Java mocking framework.
JSONassert — An assertion library for JSON.
JsonPath — XPath for JSON.
##  异常
1.  Cannot determine embedded database driver class for database type NONE
>   配置`@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})`