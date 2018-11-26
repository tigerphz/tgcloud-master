
## zipkin 安装
#### 资料说明
- [https://blog.csdn.net/xichenguan/article/details/80041119](https://blog.csdn.net/xichenguan/article/details/80041119)
- [https://github.com/openzipkin/zipkin/blob/master/zipkin-server/README.md#rabbitmq-collector](https://github.com/openzipkin/zipkin/blob/master/zipkin-server/README.md#rabbitmq-collector)
- [https://hub.docker.com/r/openzipkin/zipkin/](https://hub.docker.com/r/openzipkin/zipkin/)
#### docker命令
```shell
sudo docker run -it -p 9411:9411 -e STORAGE_TYPE=mysql -e MYSQL_DB=tgcloud_zipkin -e MYSQL_HOST=192.168.164.130 -e MYSQL_USER=root -e MYSQL_PASS=123456 -e RABBIT_ADDRESSES=192.168.164.130:5672 -e RABBIT_USER=admin -e RABBIT_PASSWORD=admin openzipkin/zipkin
```

## xxl-job 安装
#### docker命令
```shell
docker run -e PARAMS="--spring.datasource.url=jdbc:mysql://192.168.164.130:3306/xxl-job?Unicode=true&characterEncoding=UTF-8 --server.port=8011 --spring.datasource.username=root --spring.datasource.password=123456" -p 8011:8011 -v /home/hadoop/xxl-job-admin/applogs:/data/applogs --name xxl-job-admin -d xuxueli/xxl-job-admin:2.0.0
```

## RabbitMQ 安装
```shell
apt-get install rabbitmq-server  #安装成功自动启动
```
#### 查看 RabbitMq状
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
## servicecomb-saga 使用
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