## tgcloud
- 前后端完全分离，基于Spring Boot Finchley.RELEASE
- 前端使用vue + element ui
- 独立登陆入口
- Spring Security OAuth 权限定制
- 前端权限控制到按钮级别
- 后端权限还在考虑是集中到网关还是放到各个微服务自行管理(待完成)
- 完全开源，持续更新

## 相关项目
- [tgcloud-master 后端服务项目](https://github.com/tigerphz/tgcloud-master)
- [tgcloud-master-web 后台管理web项目](https://github.com/tigerphz/tgcloud-master-web)
- [tgcloud-login-web 登陆入口web项目](https://github.com/tigerphz/tgcloud-login-web)
- [tgcloud-config-repo config配置](https://github.com/tigerphz/tgcloud-config-repo)

## 功能
- 系统登陆 目前完成账号密码模式，可选短信验证码，社交模式后期会添加
- 独立登陆入口 
- 用户管理
- 部门管理
- 菜单权限管理
- 角色管理
- 微服务管理
- 动态路由 通过维护微服务管理数据进行zuul动态加载刷新路由
- 动态管理认证中心Client信息
- 错误日志 记录系统未捕获异常日志
- 服务限流：多种维度的流量控制（服务、IP、用户等）
- 集成xxl-job进行调度管理
- 聚合文档 基于zuul实现 swagger各个模块的实现
- 服务监控 Spring Boot Admin
- 断路器监控 集成RabbitMQ
- 消息中心
- zipkin链路追踪： 目前使用mysql保存数据

- 还有很多功能后期会慢慢添加,后期会通过个人博客介绍

## 架构介绍
```shell
./
├── docs  
├── logs                            
├── tgcloud-common
│   ├── tgcloud-common-base
│   ├── tgcloud-common-core
│   └── tgcloud-common-utils
├── tgcloud-config
├── tgcloud-control
├── tgcloud-eureka
├── tgcloud-gateway
├── tgcloud-gateway-zuul
├── tgcloud-modules
│   ├── tgcloud-microservice-dmc
│   └── tgcloud-microservice-uac
├── tgcloud-modules-api
│   ├── tgcloud-microservice-dmc-api
│   └── tgcloud-microservice-uac-api
└── tgcloud-security
    ├── tgcloud-security-app
    ├── tgcloud-security-authserver
    ├── tgcloud-security-core
    └── tgcloud-security-feign
```

## 账号
- 登陆密码都是123456
- 监控账号密码 admin admin

## 依赖插件
- 安装lombok插件
- 安装MapStruct support插件

## tgcloud数据库初始化
- 执行命令 tgcloud-solution\tgcloud-master\docs\sql\create-scripts\tgcloud.sql

## hosts配置
```shell
127.0.0.1 www.tgcloud.net
127.0.0.1 passport.tgcloud.net
127.0.0.1 admin.tgcloud.net
127.0.0.1 api.tgcloud.net
127.0.0.1 auth.tgcloud.net

127.0.0.1 discovery.tgcloud.net
127.0.0.1 eureka.tgcloud.net
127.0.0.1 gateway.tgcloud.net
127.0.0.1 monitor.tgcloud.net
192.168.164.130 zipkin.tgcloud.net
127.0.0.1 hystrix.tgcloud.net
192.168.164.130 jobadmin.tgcloud.net
192.168.164.130 mq.tgcloud.net
127.0.0.1 redis.tgcloud.net
127.0.0.1 mysql.tgcloud.net
```
- 192.168.164.130 是我虚拟机地址,docker在我虚拟机中

## mysql安装
- 百度

## redis安装
- [http://www.xixi624.com/2018/05/17/docker-redis-install/](http://www.xixi624.com/2018/05/17/docker-redis-install/)

## docker 
#### 资料说明
- [docker](http://www.xixi624.com/categories/docker/)

## zipkin 安装
#### 资料说明
- [https://blog.csdn.net/xichenguan/article/details/80041119](https://blog.csdn.net/xichenguan/article/details/80041119)
- [https://github.com/openzipkin/zipkin/blob/master/zipkin-server/README.md#rabbitmq-collector](https://github.com/openzipkin/zipkin/blob/master/zipkin-server/README.md#rabbitmq-collector)
- [https://hub.docker.com/r/openzipkin/zipkin/](https://hub.docker.com/r/openzipkin/zipkin/)
#### 创建数据库
- 执行脚本 tgcloud-solution\tgcloud-master\docs\zipkin.sql
#### docker命令
```shell
sudo docker run -it -p 9411:9411 -e STORAGE_TYPE=mysql -e MYSQL_DB=tgcloud_zipkin -e MYSQL_HOST=192.168.164.130 -e MYSQL_USER=root -e MYSQL_PASS=123456 -e RABBIT_ADDRESSES=192.168.164.130:5672 -e RABBIT_USER=admin -e RABBIT_PASSWORD=admin openzipkin/zipkin
```
- 请自行修改数据库地址，账号密码等
- 可以自行替换使用[elasticsearch](http://www.xixi624.com/2018/01/04/docker-elk-create/)

## xxl-job 安装
#### 资料说明
- [http://www.xuxueli.com/xxl-job/#/](http://www.xuxueli.com/xxl-job/#/)
#### 初始化数据库
- 执行脚本 tgcloud-solution\tgcloud-master\docs\tables_xxl_job.sql
#### docker命令
```shell
docker run -e PARAMS="--spring.datasource.url=jdbc:mysql://192.168.164.130:3306/xxl-job?Unicode=true&characterEncoding=UTF-8 --server.port=8011 --spring.datasource.username=root --spring.datasource.password=123456" -p 8011:8011 -v /home/hadoop/xxl-job-admin/applogs:/data/applogs --name xxl-job-admin -d xuxueli/xxl-job-admin:2.0.0
```
- 请自行修改数据库地址，账号密码等

## RabbitMQ 安装
#### 资料说明
- [RabbitMQ入门](http://www.xixi624.com/categories/RabbitMQ%E5%85%A5%E9%97%A8/)
```shell
apt-get install rabbitmq-server  #安装成功自动启动
```
#### 查看 RabbitMQ状态
```shell
systemctl status rabbitmq-server   #Active: active (running) 说明处于运行状态
```

#### 启动、停止、重启
```shell
rabbitmq-server start    # 启动
rabbitmq-server stop     # 停止
rabbitmq-server restart  # 重启

rabbitmq-plugins enable rabbitmq_management   # 启用插件
rabbitmq-server restart    # 重启
```
## servicecomb-saga 使用(暂时未接入，后期分布式事务会接入)
#### 资料说明
[https://jeremy-xu.oschina.io/2018/07/servicecomb-saga%E5%BC%80%E5%8F%91%E5%AE%9E%E6%88%98/](https://jeremy-xu.oschina.io/2018/07/servicecomb-saga%E5%BC%80%E5%8F%91%E5%AE%9E%E6%88%98/)
#### 获取源码：
```shell
$ git clone https://github.com/apache/incubator-servicecomb-saga.git
$ cd incubator-servicecomb-saga
```
#### 构建mysql的可执行文件：
```shell
$ mvn clean install -DskipTests -Pmysql
```
#### 创建数据库并给予用户访问该数据库的权限
```shell
$ mysql
mysql> create database saga default character set utf8;
mysql> GRANT ALL PRIVILEGES ON saga.* to 'saga'@'localhost' identified by '123456';
mysql> flush priveleges;
mysql> exit
```
## 项目预览
##### 登陆入口
![](http://lc-gjllsjil.cn-n1.lcfile.com/7fe7daa96745b407a21d.png)
##### 用户管理
![](http://lc-gjllsjil.cn-n1.lcfile.com/eb8fee3c87c2318d5ecb.png)
##### 角色管理
![](http://lc-gjllsjil.cn-n1.lcfile.com/147378ed4b8459e3ea21.png)
##### 权限菜单管理
![](http://lc-gjllsjil.cn-n1.lcfile.com/8b81559ca67aefc76da6.png)
##### 部门管理
![](http://lc-gjllsjil.cn-n1.lcfile.com/eb4101edaa8691c13399.png)
##### 调度管理
![](http://lc-gjllsjil.cn-n1.lcfile.com/c55f66332bde914d2409.png)
##### icons图标
![](http://lc-gjllsjil.cn-n1.lcfile.com/33b8aa67f450c32471fa.png)
##### 微服务管理
![](http://lc-gjllsjil.cn-n1.lcfile.com/3b6dd63abebea01db5b9.png)
##### 异常日志
![](http://lc-gjllsjil.cn-n1.lcfile.com/c629158fd1094c019aec.png)
##### 服务健康
![](http://lc-gjllsjil.cn-n1.lcfile.com/498d5a4bd4fa1a9a9750.png)
##### 服务注册
![](http://lc-gjllsjil.cn-n1.lcfile.com/7d56092d6f3920e74cc1.png)
##### 断路器监控
![](http://lc-gjllsjil.cn-n1.lcfile.com/7d98e22470dc93ad5b92.png)
##### 服务链路
![](http://lc-gjllsjil.cn-n1.lcfile.com/26e4fe7a04ad05d7cbf1.png)
##### RabbitMQ监控
![](http://lc-gjllsjil.cn-n1.lcfile.com/90ee01878b9cc8321cde.png)
##### API文档
![](http://lc-gjllsjil.cn-n1.lcfile.com/8856c8df10844c6e8f4e.png)


## 个人博客
- [http://www.xixi624.com/](http://www.xixi624.com/)

## 交流群
![spring cloud交流群二维码](http://lc-gjllsjil.cn-n1.lcfile.com/7f311d14bd83fb7e4dcc.png)

## 感谢
- [spring cloud 全家桶](http://spring.io/projects/spring-cloud)
- [vue-element-admin](https://github.com/PanJiaChen/vue-element-admin)
- [paascloud-master](https://gitee.com/paascloud/paascloud-master)
- [spring-cloud-zuul-ratelimit](https://github.com/marcosbarbero/spring-cloud-zuul-ratelimit)
- [Spring Security开发安全的REST服务](https://coding.imooc.com/class/chapter/134.html#Anchor)