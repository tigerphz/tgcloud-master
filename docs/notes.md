spring 注解验证

@Null   被注释的元素必须为 null  
@NotNull    被注释的元素必须不为 null  
@AssertTrue     被注释的元素必须为 true  
@AssertFalse    被注释的元素必须为 false  
@Min(value)     被注释的元素必须是一个数字，其值必须大于等于指定的最小值  
@Max(value)     被注释的元素必须是一个数字，其值必须小于等于指定的最大值  
@DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值  
@DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值  
@Size(max=, min=)   被注释的元素的大小必须在指定的范围内  
@Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内  
@Past   被注释的元素必须是一个过去的日期  
@Future     被注释的元素必须是一个将来的日期  
@Pattern(regex=,flag=)  被注释的元素必须符合指定的正则表达式  

Hibernate Validator 附加的 constraint  
@NotBlank(message =)   验证字符串非null，且长度必须大于0  
@Email  被注释的元素必须是电子邮箱地址  
@Length(min=,max=)  被注释的字符串的大小必须在指定的范围内  
@NotEmpty   被注释的字符串的必须非空  
@Range(min=,max=,message=)  被注释的元素必须在合适的范围内

mybatis tkMapper使用:
https://blog.csdn.net/potianliu/article/details/80390217

@Scope注解
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)这个是说在每次注入的时候回自动创建一个新的bean实例
@Scope(value=ConfigurableBeanFactory.SCOPE_SINGLETON)单例模式，在整个应用中只能创建一个实例
@Scope(value=WebApplicationContext.SCOPE_GLOBAL_SESSION)全局session中的一般不常用
@Scope(value=WebApplicationContext.SCOPE_APPLICATION)在一个web应用中只创建一个实例
@Scope(value=WebApplicationContext.SCOPE_REQUEST)在一个请求中创建一个实例
@Scope(value=WebApplicationContext.SCOPE_SESSION)每次创建一个会话中创建一个实例
里面还有个属性
proxyMode=ScopedProxyMode.INTERFACES创建一个JDK代理模式
proxyMode=ScopedProxyMode.TARGET_CLASS基于类的代理模式
proxyMode=ScopedProxyMode.NO（默认）不进行代理