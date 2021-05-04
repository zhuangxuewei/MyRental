/*
 Navicat Premium Data Transfer

 Source Server         : tsmp
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : rental

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 03/05/2021 22:02:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cars_stock
-- ----------------------------
DROP TABLE IF EXISTS `car_stock`;
CREATE TABLE `car_stock` (
  `id` int(24) NOT NULL AUTO_INCREMENT COMMENT '租赁车辆表Id',
  `car_model` varchar(300) NOT NULL COMMENT '车辆model',
  `stock_num` int(20) NOT NULL COMMENT '库存量',
  `rental_price` decimal(18,2) DEFAULT '0.00' COMMENT '租赁价格',
  `date_created` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `date_updated` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `car_model_index` (`car_model`,`date_created`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' 租借车辆id';

-- ----------------------------
-- Table structure for rental_order
-- ----------------------------
DROP TABLE IF EXISTS `rental_order`;
CREATE TABLE `rental_order` (
  `id` int(24) NOT NULL AUTO_INCREMENT COMMENT '租赁订单表id',
  `order_id` varchar(24) NOT NULL COMMENT '租赁订单id',
  `rental_user_id` varchar(24) NOT NULL COMMENT '对应用户表id',
  `rental_days` int(20) DEFAULT NULL COMMENT '租赁时间',
  `total_amount` decimal(18,2) DEFAULT NULL COMMENT '租赁订单金额',
  `order_status` varchar(16) DEFAULT NULL COMMENT '订单状态',
  `date_created` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `date_updated` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `orderId_userId_created_index` (`order_id`,`rental_user_id`,`date_created`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rental_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `rental_order_detail`;
CREATE TABLE `rental_order_detail` (
  `id` int(24) NOT NULL AUTO_INCREMENT COMMENT '订单详情表id',
  `order_id` varchar(24) NOT NULL COMMENT '订单id',
  `car_stock_id` varchar(24) NOT NULL COMMENT '对应库存id',
  `rental_car_num` int(20) DEFAULT '0' COMMENT '租赁车辆数量',
  `date_created` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `date_updated` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `orderId_created_index` (`order_id`,`date_created`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rental_user
-- ----------------------------
DROP TABLE IF EXISTS `rental_user`;
CREATE TABLE `rental_user` (
  `id` int(24) NOT NULL AUTO_INCREMENT COMMENT '用户表id',
  `userId` varchar(50) NOT NULL COMMENT '用户账号',
  `career` varchar(200) DEFAULT NULL COMMENT '用户职业',
  `pay_card_no` varchar(32) DEFAULT NULL COMMENT '支付卡号',
  `date_created` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `date_updated` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId_card_no_index` (`userId`,`pay_card_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;

insert into car_stock( car_model,stock_num,rental_price,date_created,date_updated) values('Toyota Camry',2,100,now(),now())
