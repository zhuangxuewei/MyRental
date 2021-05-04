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

 Date: 04/05/2021 23:23:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for car_stock
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
  UNIQUE KEY `car_model` (`car_model`),
  KEY `car_model_index` (`car_model`,`date_created`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT=' 租借车辆id';

-- ----------------------------
-- Records of car_stock
-- ----------------------------
BEGIN;
INSERT INTO `car_stock` VALUES (1, 'Toyota Camry', 2, 100.00, '2021-05-04 17:41:12', '2021-05-04 17:41:12');
INSERT INTO `car_stock` VALUES (2, 'BMW 650', 2, 200.00, '2021-05-04 21:57:23', '2021-05-04 21:57:29');
COMMIT;

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
  UNIQUE KEY `order_id` (`order_id`),
  KEY `orderId_userId_created_index` (`order_id`,`rental_user_id`,`date_created`) USING BTREE
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
  KEY `orderId_created_index` (`order_id`,`date_created`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

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
  UNIQUE KEY `userId` (`userId`),
  UNIQUE KEY `pay_card_no` (`pay_card_no`),
  UNIQUE KEY `userId_card_no_index` (`userId`,`pay_card_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rental_user
-- ----------------------------
BEGIN;
INSERT INTO `rental_user` VALUES (1, 'zhuangxuewei', 'programmer', '1234567891234567', '2021-05-04 21:50:29', '2021-05-04 21:50:35');
INSERT INTO `rental_user` VALUES (2, 'user1', 'worker', '2324242324232', '2021-05-04 21:51:46', '2021-05-04 21:51:51');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
