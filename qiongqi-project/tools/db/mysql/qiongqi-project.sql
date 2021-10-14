/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3306
 Source Schema         : qiongqi-project

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 02/10/2021 18:54:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for v_admin
-- ----------------------------
DROP TABLE IF EXISTS `v_admin`;
CREATE TABLE `v_admin`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员登陆名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `lastLoginTime` datetime NULL DEFAULT NULL COMMENT '上次登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of v_admin
-- ----------------------------
INSERT INTO `v_admin` VALUES (1302205018571415553, '2020-09-09 00:00:00', '2021-01-20 16:07:47', '2513669400@qq.com', '$2a$10$A2yj6JW0bhUMP5VCw6Xjk.T9bMUKWsHBVaBI/An31cEBntK9xtMf.', '2021-01-20 16:07:47');

-- ----------------------------
-- Table structure for v_role
-- ----------------------------
DROP TABLE IF EXISTS `v_role`;
CREATE TABLE `v_role`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色标识符',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for v_user
-- ----------------------------
DROP TABLE IF EXISTS `v_user`;
CREATE TABLE `v_user`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `wxId` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微信id',
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `score` int(255) NULL DEFAULT 0 COMMENT '积分',
  `avatar` varchar(2083) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `robotId` bigint(20) NULL DEFAULT NULL COMMENT '机器人表的Id',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '上级id',
  `groupName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属群的群昵称',
  `groupWxId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属群wxID',
  `openId` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微信用户openId',
  `nickName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `gender` int(255) NULL DEFAULT NULL COMMENT '性别 0-未知 1-男,2-女',
  `country` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国家',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省',
  `sessionKey` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会话密钥',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
