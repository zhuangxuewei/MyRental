# MyRental
rental工程设计说明
1.设计4张表分别是存储表car_stock，订单表rental_order，订单详情表rental_order_detail，用户表rental_user。为简单起见，用户表没设计对应api，使用初始用户user1.建表sql文件rental.sql放在db目录下
数据库名为rental

2.设计八个API，遵循restful风格。API文档用postman生成并发布。地址如下：
https://documenter.getpostman.com/view/15652381/TzRNGAqk
服务部署在azure云平台上。应用服务域名为：https://prudentialrental.azurewebsites.net
如果无法访问，可以访问另一个域名：http://47.106.237.70/
或者在本地部署访问

3.接口使用springboot开发，使用注解和xml混合方式。xml用于做mapper映射，存放sql。

4.API接口的service层用junit做单元测试。controller层用postman测试过。
单元测试做成套件(Suite)，整合在类 AllTests

