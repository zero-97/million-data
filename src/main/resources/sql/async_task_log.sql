/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2022-10-22 21:06:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for async_task_log
-- ----------------------------
DROP TABLE IF EXISTS `async_task_log`;
CREATE TABLE `async_task_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createtime` date NOT NULL,
  `status` int(11) NOT NULL,
  `url` varchar(2083) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
