/*
Navicat MySQL Data Transfer

Source Server         : 111.231.237.47
Source Server Version : 50722
Source Host           : 111.231.237.47:3306
Source Database       : o2o

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-10-14 13:58:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_area
-- ----------------------------
DROP TABLE IF EXISTS `tb_area`;
CREATE TABLE `tb_area` (
  `area_id` int(2) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(200) NOT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`area_id`),
  UNIQUE KEY `uk_area` (`area_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_area
-- ----------------------------
INSERT INTO `tb_area` VALUES ('1', '东丽A区', '0', '2018-07-07 19:10:38', null);
INSERT INTO `tb_area` VALUES ('2', '东丽B区', '0', '2018-07-07 19:11:14', null);
INSERT INTO `tb_area` VALUES ('3', '东丽C区', '0', '2018-07-07 19:11:23', null);
INSERT INTO `tb_area` VALUES ('4', '东丽D区', '0', '2018-07-07 19:11:33', null);
INSERT INTO `tb_area` VALUES ('5', '东区', '2', '2018-07-07 19:11:42', null);
INSERT INTO `tb_area` VALUES ('6', '西区', '0', '2018-07-07 19:11:51', null);

-- ----------------------------
-- Table structure for tb_head_line
-- ----------------------------
DROP TABLE IF EXISTS `tb_head_line`;
CREATE TABLE `tb_head_line` (
  `line_id` int(100) NOT NULL AUTO_INCREMENT,
  `line_name` varchar(1000) DEFAULT NULL,
  `line_link` varchar(2000) NOT NULL,
  `line_img` varchar(2000) NOT NULL,
  `priority` int(2) DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0' COMMENT '0:不可用，1:可显示',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`line_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_head_line
-- ----------------------------
INSERT INTO `tb_head_line` VALUES ('1', '头条1', 'test', '/upload/item/index/1.jpg', null, '1', null, null);
INSERT INTO `tb_head_line` VALUES ('2', '头条2', 'test', '/upload/item/index/2.jpg', null, '1', null, null);
INSERT INTO `tb_head_line` VALUES ('3', '头条3', 'test', '/upload/item/index/3.jpg', null, '1', null, null);

-- ----------------------------
-- Table structure for tb_local_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_local_auth`;
CREATE TABLE `tb_local_auth` (
  `local_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `username` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`local_auth_id`),
  KEY `uk_local_profile` (`username`),
  KEY `fk_localauth_profile` (`user_id`),
  CONSTRAINT `fk_localauth_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_local_auth
-- ----------------------------
INSERT INTO `tb_local_auth` VALUES ('1', '1', 'admin', '50565y5q2b92b9b2265q2b5s2b550ye6', '2018-10-09 19:36:38', null);
INSERT INTO `tb_local_auth` VALUES ('2', '2', '123456', 's05bse6q2qlb9qblls96s592y55y556s', '2018-10-09 21:04:22', null);

-- ----------------------------
-- Table structure for tb_person_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_person_info`;
CREATE TABLE `tb_person_info` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `profile_img` varchar(1024) DEFAULT NULL,
  `user_type` int(2) NOT NULL COMMENT '1.顾客，2.店家，3.超级管理员',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL COMMENT '0:禁止使用，1：允许使用本商城',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_person_info
-- ----------------------------
INSERT INTO `tb_person_info` VALUES ('1', '东东', 'ss', 'ss', 'ss', '2', '2018-10-09 17:36:41', null, '1');
INSERT INTO `tb_person_info` VALUES ('2', '西西', 'ss', 'ss', 'ss', '2', '2018-10-09 17:37:46', null, '1');

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product` (
  `product_id` int(10) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) NOT NULL,
  `product_desc` varchar(2000) DEFAULT NULL,
  `img_addr` varchar(2000) DEFAULT '',
  `normal_price` varchar(100) DEFAULT NULL,
  `promotion_price` varchar(100) DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0' COMMENT '0:下架，1:在前端展示系统显示',
  `product_category_id` int(11) DEFAULT NULL,
  `shop_id` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_id`),
  KEY `fk_product_procate` (`product_category_id`),
  KEY `fk_product_shop` (`shop_id`),
  CONSTRAINT `fk_product_procate` FOREIGN KEY (`product_category_id`) REFERENCES `tb_product_category` (`product_category_id`),
  CONSTRAINT `fk_product_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product
-- ----------------------------
INSERT INTO `tb_product` VALUES ('1', '脏脏奶茶', '网红脏脏奶茶，一杯15元！！！', '\\upload\\item\\shop\\1\\2018100920424153187.jpg', '20', '15', '10', '2018-10-09 20:42:41', '2018-10-09 20:42:41', '1', '1', '1');
INSERT INTO `tb_product` VALUES ('2', '原味奶茶', '原味奶茶！！', '/upload/item/shop/1/2018100921003657004.jpg', '15', '12', '5', '2018-10-09 21:00:36', '2018-10-09 21:00:36', '1', '2', '1');
INSERT INTO `tb_product` VALUES ('3', '抹茶奶茶', '抹茶奶茶，特价优惠', '/upload/item/shop/1/2018100921030846962.jpg', '15', '10', '5', '2018-10-09 21:03:08', '2018-10-09 21:03:08', '1', '1', '1');
INSERT INTO `tb_product` VALUES ('4', '优惠1', '两个汉堡+一包薯条+一杯可乐', '/upload/item/shop/3/2018100921091280782.jpg', '30', '20', '15', '2018-10-09 21:09:12', '2018-10-09 21:09:12', '1', '5', '3');
INSERT INTO `tb_product` VALUES ('5', '鸡腿堡', '鸡腿堡', '/upload/item/shop/3/2018100921101358300.jpg', '12', '8', '10', '2018-10-09 21:10:14', '2018-10-09 21:10:14', '1', '6', '3');
INSERT INTO `tb_product` VALUES ('6', '美式薯条', '美式薯条', '/upload/item/shop/3/2018100921105456151.jpg', '10', '7', '10', '2018-10-09 21:10:54', '2018-10-09 21:10:54', '1', '7', '3');
INSERT INTO `tb_product` VALUES ('7', '可乐', '可乐！', '/upload/item/shop/3/2018100921114010286.jpg', '8', '5', '5', '2018-10-09 21:11:40', '2018-10-09 21:11:40', '1', '8', '3');
INSERT INTO `tb_product` VALUES ('8', '芒果汁', '芒果汁', '/upload/item/shop/1/2018100921132865013.jpg', '15', '8', '5', '2018-10-09 21:13:28', '2018-10-09 21:13:28', '1', '4', '1');
INSERT INTO `tb_product` VALUES ('9', '黑咖啡', '咖啡', '/upload/item/shop/1/2018100921150373095.jpg', '20', '15', '5', '2018-10-09 21:15:03', '2018-10-09 21:15:03', '1', '9', '1');
INSERT INTO `tb_product` VALUES ('10', '黑森力蛋糕', '黑森林蛋糕', '/upload/item/shop/4/2018101014331295964.jpg', '20', '18', '10', '2018-10-10 14:33:12', '2018-10-10 14:33:12', '1', '10', '4');
INSERT INTO `tb_product` VALUES ('11', '雪媚娘', '雪媚娘', '/upload/item/shop/4/2018101014362675605.jpg', '15', '10', '8', '2018-10-10 14:36:27', '2018-10-10 14:36:27', '1', '14', '4');
INSERT INTO `tb_product` VALUES ('12', '甜甜圈', '甜甜圈', '/upload/item/shop/4/2018101014413830786.jpg', '8', '4', '8', '2018-10-10 14:36:30', '2018-10-10 14:41:38', '1', '14', '4');
INSERT INTO `tb_product` VALUES ('13', '原味烤奶', '益禾堂烤奶！', '/upload/item/shop/5/2018101014514953321.jpg', '12', '7', '10', '2018-10-10 14:51:50', '2018-10-10 14:51:50', '1', '15', '5');
INSERT INTO `tb_product` VALUES ('14', '柠檬冰', '柠檬冰！', '/upload/item/shop/7/2018101015040244159.jpg', '8', '5', '3', '2018-10-10 15:04:02', '2018-10-10 15:04:02', '1', '16', '7');
INSERT INTO `tb_product` VALUES ('15', '套餐A', '鸡翅+鸡蛋+青菜+白饭', '/upload/item/shop/8/2018101015113748080.jpg', '15', '10', '10', '2018-10-10 15:11:38', '2018-10-10 15:11:38', '1', '17', '8');
INSERT INTO `tb_product` VALUES ('16', '套餐B', '猪肉+鸡蛋+青菜+土豆+白饭', '/upload/item/shop/8/2018101015132271766.jpg', '16', '12.99', '10', '2018-10-10 15:13:23', '2018-10-10 15:13:23', '1', '17', '8');
INSERT INTO `tb_product` VALUES ('17', '紫菜牛肉丸汤饭', '紫菜牛肉丸汤饭', '/upload/item/shop/8/2018101015200972799.jpeg', '13', '9.9', '8', '2018-10-10 15:20:09', '2018-10-10 15:20:09', '1', '18', '8');
INSERT INTO `tb_product` VALUES ('18', '猪杂汤饭', '猪杂汤饭', '/upload/item/shop/8/2018101015215741314.jpeg', '13', '9.9', '8', '2018-10-10 15:21:57', '2018-10-10 15:21:57', '1', '18', '8');
INSERT INTO `tb_product` VALUES ('19', '皮蛋瘦肉粥', '皮蛋瘦肉粥', '/upload/item/shop/8/2018101015224023482.jpg', '13', '10', '8', '2018-10-10 15:22:40', '2018-10-10 15:22:40', '1', '19', '8');

-- ----------------------------
-- Table structure for tb_product_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_category`;
CREATE TABLE `tb_product_category` (
  `product_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_category_name` varchar(100) NOT NULL,
  `priority` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `shop_id` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_category_id`),
  KEY `fk_procate_shop` (`shop_id`),
  CONSTRAINT `fk_procate_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product_category
-- ----------------------------
INSERT INTO `tb_product_category` VALUES ('1', '特色产品', '10', '2018-10-09 20:40:54', '1');
INSERT INTO `tb_product_category` VALUES ('2', '奶茶', '5', '2018-10-09 20:40:54', '1');
INSERT INTO `tb_product_category` VALUES ('3', '奶盖', '5', '2018-10-09 20:40:54', '1');
INSERT INTO `tb_product_category` VALUES ('4', '果汁', '4', '2018-10-09 20:40:54', '1');
INSERT INTO `tb_product_category` VALUES ('5', '优惠套餐', '10', '2018-10-09 21:07:01', '3');
INSERT INTO `tb_product_category` VALUES ('6', '汉堡', '5', '2018-10-09 21:07:12', '3');
INSERT INTO `tb_product_category` VALUES ('7', '薯条', '5', '2018-10-09 21:07:19', '3');
INSERT INTO `tb_product_category` VALUES ('8', '饮料', '4', '2018-10-09 21:07:25', '3');
INSERT INTO `tb_product_category` VALUES ('9', '咖啡', '5', '2018-10-09 21:14:14', '1');
INSERT INTO `tb_product_category` VALUES ('10', '蛋糕', '5', '2018-10-10 14:28:54', '4');
INSERT INTO `tb_product_category` VALUES ('11', '普通面包', '3', '2018-10-10 14:29:44', '4');
INSERT INTO `tb_product_category` VALUES ('12', '酸奶', '3', '2018-10-10 14:30:15', '4');
INSERT INTO `tb_product_category` VALUES ('14', '糕点', '4', '2018-10-10 14:35:44', '4');
INSERT INTO `tb_product_category` VALUES ('15', '烤奶', '10', '2018-10-10 14:50:49', '5');
INSERT INTO `tb_product_category` VALUES ('16', '冰水', '3', '2018-10-10 15:01:08', '7');
INSERT INTO `tb_product_category` VALUES ('17', '优惠套餐', '10', '2018-10-10 15:10:10', '8');
INSERT INTO `tb_product_category` VALUES ('18', '汤饭', '8', '2018-10-10 15:10:10', '8');
INSERT INTO `tb_product_category` VALUES ('19', '粥', '8', '2018-10-10 15:13:36', '8');

-- ----------------------------
-- Table structure for tb_product_img
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_img`;
CREATE TABLE `tb_product_img` (
  `product_img_id` int(20) NOT NULL AUTO_INCREMENT,
  `img_address` varchar(2000) NOT NULL,
  `img_desc` varchar(2000) DEFAULT NULL,
  `priority` int(2) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `product_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`product_img_id`),
  KEY `fk_proimg_product` (`product_id`),
  CONSTRAINT `fk_proimg_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product_img
-- ----------------------------
INSERT INTO `tb_product_img` VALUES ('1', '\\upload\\item\\shop\\1\\2018100920424115354.jpg', null, null, '2018-10-09 20:42:42', '1');
INSERT INTO `tb_product_img` VALUES ('2', '\\upload\\item\\shop\\1\\2018100920424196729.jpg', null, null, '2018-10-09 20:42:42', '1');
INSERT INTO `tb_product_img` VALUES ('3', '/upload/item/shop/1/2018100921003686268.jpg', null, null, '2018-10-09 21:00:37', '2');
INSERT INTO `tb_product_img` VALUES ('4', '/upload/item/shop/1/2018100921030886433.jpg', null, null, '2018-10-09 21:03:09', '3');
INSERT INTO `tb_product_img` VALUES ('5', '/upload/item/shop/1/2018100921030888920.jpg', null, null, '2018-10-09 21:03:09', '3');
INSERT INTO `tb_product_img` VALUES ('6', '/upload/item/shop/3/2018100921091227906.jpg', null, null, '2018-10-09 21:09:13', '4');
INSERT INTO `tb_product_img` VALUES ('7', '/upload/item/shop/3/2018100921091274109.jpg', null, null, '2018-10-09 21:09:13', '4');
INSERT INTO `tb_product_img` VALUES ('8', '/upload/item/shop/3/2018100921101328106.jpg', null, null, '2018-10-09 21:10:14', '5');
INSERT INTO `tb_product_img` VALUES ('9', '/upload/item/shop/3/2018100921105426736.jpg', null, null, '2018-10-09 21:10:54', '6');
INSERT INTO `tb_product_img` VALUES ('10', '/upload/item/shop/3/2018100921114056164.jpg', null, null, '2018-10-09 21:11:40', '7');
INSERT INTO `tb_product_img` VALUES ('11', '/upload/item/shop/1/2018100921132883803.jpg', null, null, '2018-10-09 21:13:28', '8');
INSERT INTO `tb_product_img` VALUES ('12', '/upload/item/shop/1/2018100921132813976.jpg', null, null, '2018-10-09 21:13:28', '8');
INSERT INTO `tb_product_img` VALUES ('13', '/upload/item/shop/1/2018100921150345741.jpg', null, null, '2018-10-09 21:15:04', '9');
INSERT INTO `tb_product_img` VALUES ('14', '/upload/item/shop/4/2018101014331439026.jpg', null, null, '2018-10-10 14:33:15', '10');
INSERT INTO `tb_product_img` VALUES ('15', '/upload/item/shop/4/2018101014331414582.jpg', null, null, '2018-10-10 14:33:15', '10');
INSERT INTO `tb_product_img` VALUES ('18', '/upload/item/shop/4/2018101014363345914.jpg', null, null, '2018-10-10 14:36:34', '11');
INSERT INTO `tb_product_img` VALUES ('19', '/upload/item/shop/4/2018101014363476888.jpg', null, null, '2018-10-10 14:36:34', '11');
INSERT INTO `tb_product_img` VALUES ('20', '/upload/item/shop/4/2018101014413842543.jpg', null, null, '2018-10-10 14:41:39', '12');
INSERT INTO `tb_product_img` VALUES ('21', '/upload/item/shop/4/2018101014413942711.jpg', null, null, '2018-10-10 14:41:39', '12');
INSERT INTO `tb_product_img` VALUES ('22', '/upload/item/shop/5/2018101014515633114.jpg', null, null, '2018-10-10 14:51:56', '13');
INSERT INTO `tb_product_img` VALUES ('23', '/upload/item/shop/5/2018101014515694587.jpg', null, null, '2018-10-10 14:51:56', '13');
INSERT INTO `tb_product_img` VALUES ('24', '/upload/item/shop/7/2018101015040926999.jpg', null, null, '2018-10-10 15:04:10', '14');
INSERT INTO `tb_product_img` VALUES ('25', '/upload/item/shop/7/2018101015040936620.jpg', null, null, '2018-10-10 15:04:10', '14');
INSERT INTO `tb_product_img` VALUES ('26', '/upload/item/shop/8/2018101015113816568.jpg', null, null, '2018-10-10 15:11:38', '15');
INSERT INTO `tb_product_img` VALUES ('27', '/upload/item/shop/8/2018101015132322098.jpg', null, null, '2018-10-10 15:13:24', '16');
INSERT INTO `tb_product_img` VALUES ('28', '/upload/item/shop/8/2018101015201855874.jpeg', null, null, '2018-10-10 15:20:19', '17');
INSERT INTO `tb_product_img` VALUES ('29', '/upload/item/shop/8/2018101015220490838.jpeg', null, null, '2018-10-10 15:22:05', '18');
INSERT INTO `tb_product_img` VALUES ('30', '/upload/item/shop/8/2018101015224221805.jpg', null, null, '2018-10-10 15:22:43', '19');

-- ----------------------------
-- Table structure for tb_product_sell_daily
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_sell_daily`;
CREATE TABLE `tb_product_sell_daily` (
  `product_sell_daily_id` int(100) NOT NULL AUTO_INCREMENT,
  `product_id` int(100) DEFAULT NULL,
  `shop_id` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `total` int(10) DEFAULT '0',
  PRIMARY KEY (`product_sell_daily_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_product_sell_daily
-- ----------------------------

-- ----------------------------
-- Table structure for tb_shop
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop`;
CREATE TABLE `tb_shop` (
  `shop_id` int(10) NOT NULL AUTO_INCREMENT,
  `owner_id` int(10) NOT NULL COMMENT '店铺创建人',
  `area_id` int(5) DEFAULT NULL,
  `shop_category_id` int(11) DEFAULT NULL,
  `shop_name` varchar(256) NOT NULL,
  `shop_desc` varchar(1024) DEFAULT NULL,
  `shop_addr` varchar(200) DEFAULT NULL,
  `phone` varchar(128) DEFAULT NULL,
  `shop_img` varchar(1024) DEFAULT NULL,
  `priority` int(3) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0' COMMENT '0.审核中；-1.不可用；1.可用',
  `advice` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`shop_id`),
  KEY `fk_shop_area` (`area_id`),
  KEY `fk_shop_shopcate` (`shop_category_id`),
  KEY `fk_shop_profile` (`owner_id`),
  CONSTRAINT `fk_shop_area` FOREIGN KEY (`area_id`) REFERENCES `tb_area` (`area_id`),
  CONSTRAINT `fk_shop_profile` FOREIGN KEY (`owner_id`) REFERENCES `tb_person_info` (`user_id`),
  CONSTRAINT `fk_shop_shopcate` FOREIGN KEY (`shop_category_id`) REFERENCES `tb_shop_category` (`shop_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_shop
-- ----------------------------
INSERT INTO `tb_shop` VALUES ('1', '1', '1', '6', '一点点', '一点点奶茶点！！！你的奶茶首选店家！', '东丽A超市斜对面', '13242578375', '\\upload\\item\\shop\\1\\2018100920212653315.jpg', '2', '2018-10-09 20:21:26', '2018-10-09 20:21:26', '1', null);
INSERT INTO `tb_shop` VALUES ('2', '1', '6', '6', 'YouYou奶茶', 'YouYou奶茶，属于你的奶茶！', '西区门口左侧', '13242578375', '\\upload\\item\\shop\\2\\2018100920241391757.jpg', '1', '2018-10-09 20:24:13', '2018-10-09 20:24:13', '0', null);
INSERT INTO `tb_shop` VALUES ('3', '2', '5', '7', '汉堡王', '汉堡王！！！', '东区左侧100米', '13242578375', '/upload/item/shop/3/2018100921053936206.jpg', '1', '2018-10-09 21:05:39', '2018-10-09 21:05:39', '1', null);
INSERT INTO `tb_shop` VALUES ('4', '1', '1', '16', '蓝小轩', '蓝小轩面包，糕点店。', '东丽A超市后门十字路口', '13242578375', '/upload/item/shop/4/2018101014261219253.jpg', '2', '2018-10-10 14:26:11', '2018-10-10 14:26:11', '1', null);
INSERT INTO `tb_shop` VALUES ('5', '1', '1', '6', '益禾堂', '益禾堂，放心的店！', '东丽A区超市后门十字路口', '13242578375', '/upload/item/shop/5/2018101014482651758.jpg', '2', '2018-10-10 14:48:23', '2018-10-10 14:48:23', '1', null);
INSERT INTO `tb_shop` VALUES ('7', '1', '1', '6', '果缘站', '果缘站', '东丽A超市后门十字路口', '13242578375', '/upload/item/shop/7/2018101015000628585.jpg', '2', '2018-10-10 15:00:03', '2018-10-10 15:00:03', '1', null);
INSERT INTO `tb_shop` VALUES ('8', '2', '2', '9', '超鑫快餐店', '超鑫快餐店', '美食城', '13242578375', '/upload/item/shop/8/2018101015085825503.jpg', '1', '2018-10-10 15:08:58', '2018-10-10 15:08:58', '1', null);

-- ----------------------------
-- Table structure for tb_shop_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop_category`;
CREATE TABLE `tb_shop_category` (
  `shop_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_category_name` varchar(100) NOT NULL DEFAULT '',
  `shop_category_desc` varchar(1000) DEFAULT '',
  `shop_category_img` varchar(2000) DEFAULT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_edit_time` datetime DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL COMMENT '上级id',
  PRIMARY KEY (`shop_category_id`),
  KEY `fk_shop_category-self` (`parent_id`),
  CONSTRAINT `fk_shop_category-self` FOREIGN KEY (`parent_id`) REFERENCES `tb_shop_category` (`shop_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_shop_category
-- ----------------------------
INSERT INTO `tb_shop_category` VALUES ('1', '甜品饮品', '甜品饮品', '/upload/item/index/naicha.png', '4', null, null, null);
INSERT INTO `tb_shop_category` VALUES ('2', '美食外卖', '美食外卖', '/upload/item/index/meishi.png', '5', null, null, null);
INSERT INTO `tb_shop_category` VALUES ('3', '电影/演出', '电影/演出', '/upload/item/index/dianying.png', '3', null, null, null);
INSERT INTO `tb_shop_category` VALUES ('4', '休闲娱乐', '休闲娱乐', '/upload/item/index/yule.png', '3', null, null, null);
INSERT INTO `tb_shop_category` VALUES ('5', '住宿酒店', '住宿酒店', '/upload/item/index/zhusu.png', '3', null, null, null);
INSERT INTO `tb_shop_category` VALUES ('6', '奶茶店', '奶茶店', null, '0', null, null, '1');
INSERT INTO `tb_shop_category` VALUES ('7', '美式快餐', '美式快餐', null, '0', null, null, '2');
INSERT INTO `tb_shop_category` VALUES ('8', '电影院', '电影院', null, '0', null, null, '3');
INSERT INTO `tb_shop_category` VALUES ('9', '中式快餐', '中式快餐', null, '0', null, null, '2');
INSERT INTO `tb_shop_category` VALUES ('10', 'KTV', 'KTV', null, '0', null, null, '4');
INSERT INTO `tb_shop_category` VALUES ('11', '超市', '超市', null, '0', null, null, '2');
INSERT INTO `tb_shop_category` VALUES ('12', '游戏厅', '游戏厅', null, '0', null, null, '4');
INSERT INTO `tb_shop_category` VALUES ('13', '网咖', '网咖', null, '0', null, null, '4');
INSERT INTO `tb_shop_category` VALUES ('14', '青年旅舍', '青年旅舍', null, '0', null, null, '5');
INSERT INTO `tb_shop_category` VALUES ('15', '酒店', '酒店', null, '0', null, null, '5');
INSERT INTO `tb_shop_category` VALUES ('16', '面包店', '面包店', null, '0', null, null, '1');

-- ----------------------------
-- Table structure for tb_user_product_map
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_product_map`;
CREATE TABLE `tb_user_product_map` (
  `user_product_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL,
  `product_id` int(100) DEFAULT NULL,
  `shop_id` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_product_id`),
  KEY `fk_user_product_map_product` (`product_id`),
  KEY `fk_user_product_map_profile` (`user_id`),
  KEY `fk_user_product_shop` (`shop_id`),
  CONSTRAINT `fk_user_product_map_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`),
  CONSTRAINT `fk_user_product_map_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`),
  CONSTRAINT `fk_user_product_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_product_map
-- ----------------------------

-- ----------------------------
-- Table structure for tb_wechat_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_wechat_auth`;
CREATE TABLE `tb_wechat_auth` (
  `wechat_auth_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `open_id` varchar(1024) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`wechat_auth_id`),
  KEY `fk_wechatauth_profile` (`user_id`),
  CONSTRAINT `fk_wechatauth_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_person_info` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_wechat_auth
-- ----------------------------
