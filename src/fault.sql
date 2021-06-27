/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : fault

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 27/06/2021 14:20:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for fault
-- ----------------------------
DROP TABLE IF EXISTS `fault`;
CREATE TABLE `fault`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `clazzid` int(11) NULL DEFAULT NULL,
  `workshopid` int(11) NULL DEFAULT NULL,
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of fault
-- ----------------------------
INSERT INTO `fault` VALUES (4, '2021-06-03 03:10:00', 1, 1, '塑料表面发乌,无光泽,并有细小裂纹');

-- ----------------------------
-- Table structure for fault_clazz
-- ----------------------------
DROP TABLE IF EXISTS `fault_clazz`;
CREATE TABLE `fault_clazz`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of fault_clazz
-- ----------------------------
INSERT INTO `fault_clazz` VALUES (1, '原料不良');
INSERT INTO `fault_clazz` VALUES (2, '补助动力公用设施');
INSERT INTO `fault_clazz` VALUES (3, '电气故障');
INSERT INTO `fault_clazz` VALUES (4, '机器故障');
INSERT INTO `fault_clazz` VALUES (5, '操作不当');
INSERT INTO `fault_clazz` VALUES (6, '其他');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', 'admin', '123456');

-- ----------------------------
-- Table structure for workshop
-- ----------------------------
DROP TABLE IF EXISTS `workshop`;
CREATE TABLE `workshop`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `leader` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of workshop
-- ----------------------------
INSERT INTO `workshop` VALUES (1, '1车间', '刘主任');
INSERT INTO `workshop` VALUES (2, '2车间', '李主任');
INSERT INTO `workshop` VALUES (3, '3车间', '王主任');
INSERT INTO `workshop` VALUES (4, '4车间', '吴主任');

SET FOREIGN_KEY_CHECKS = 1;
