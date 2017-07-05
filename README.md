# demo-spring-boot
##  Thymeleaf
>   Thymeleaf是服务端的模板引擎
>   提供优雅,高度可维护可扩展的方式创建模板
>   支持HTML5
>   可处理类型包括HTML,XML,TEXT,JAVASCRIPT,CSS,RAW

##  语法
### 标准表达式语法
1.  简单表达式语法
    1.1.    变量表达式: ${...}
    1.2.    变量选择表达式: *{...}
    1.3.    消息表达式: #{...}
    1.4.    链接表达式: @{...}
    1.5.    帧表达式: ~{...}
    
2.  直接量
    2.1.    文本直接量
    2.2.    数字直接量
    2.3.    布尔直接量
    2.4.    Null直接量
    2.5.    文字符号
    
3.  文本操作
    3.1.    字符串连接
    3.2.    字符串替换
    
4.  算数操作
    4.1.    加减乘除    +, -, *, /, %
    4.2.    正负  -
    
5.  比较操作
    5.1.    比较 >, <, >=, <= (gt, lt, ge, le)
    5.2.    等值比较 ==, != (eq, ne)    
    
6.  条件判断    
    6.1.    if - then  (if) ? (then)
    6.2.    if - then - else (if) ? (then) : (else)
    6.3.    Default (value) ?: (defaultvalue)
    
### 内置实体
1.  ctx: the context object.
2.  vars: the context variables.
3.  locale: the context locale.
4.  request: (only in Web Contexts) the HttpServletRequest object.
5.  response: (only in Web Contexts) the HttpServletResponse object.
6.  session: (only in Web Contexts) the HttpSession object.
7.  servletContext: (only in Web Contexts) the ServletContext object.