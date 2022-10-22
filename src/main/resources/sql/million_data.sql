/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2022-10-22 21:06:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for million_data
-- ----------------------------
DROP TABLE IF EXISTS `million_data`;
CREATE TABLE `million_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `key1` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `key2` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `key3` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `million_data_uuid_uindex` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=3000001 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
