/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : qiongqi-project

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 20/01/2021 16:12:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for v_admin
-- ----------------------------
DROP TABLE IF EXISTS `v_admin`;
CREATE TABLE `v_admin`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员登陆名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `lastLoginTime` datetime(0) NULL DEFAULT NULL COMMENT '上次登录时间',
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
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
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
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `wxId` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微信id',
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `score` int(255) NULL DEFAULT 0 COMMENT '积分',
  `avatar` varchar(2083) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `robotId` bigint(20) NULL DEFAULT NULL COMMENT '机器人表的Id',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '上级id',
  `groupName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属群的群昵称',
  `groupWxId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属群wxID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of v_user
-- ----------------------------
INSERT INTO `v_user` VALUES (1302205018571415553, '2020-09-09 00:00:00', '2021-01-16 21:57:17', '111111111', '用户1', 12, NULL, 1351121701864103938, NULL, '测试群', '24855880487@chatroom');
INSERT INTO `v_user` VALUES (1302205018571415554, '2020-09-20 00:00:00', '2021-01-16 21:56:27', '222222222', '用户2', 0, NULL, 1351121701864103938, NULL, '测试群', '24855880487@chatroom');
INSERT INTO `v_user` VALUES (1302205018571415555, '2020-02-09 00:00:00', '2021-01-16 21:56:27', '222222222', '用户3', 0, NULL, 1351121701864103938, NULL, '测试群', '24855880487@chatroom');
INSERT INTO `v_user` VALUES (1302205018571415556, '2020-03-09 00:00:00', '2021-01-16 21:56:27', '222222222', '用户4', 0, NULL, 1351121701864103938, NULL, '测试群', '24855880487@chatroom');
INSERT INTO `v_user` VALUES (1302205018571415557, '2020-04-09 00:00:00', '2021-01-16 21:56:27', '222222222', '用户3', 0, NULL, 1351121701864103938, NULL, '测试群', '24855880487@chatroom');
INSERT INTO `v_user` VALUES (1351200408238723075, '2021-01-19 00:10:56', '2021-01-19 22:04:04', 'wxid_p67e9a43lu7v22', '王富贵', 48, NULL, 1351121701864103938, NULL, '测试群', '24855880487@chatroom');
INSERT INTO `v_user` VALUES (1351207612404666369, '2021-01-19 00:39:34', '2021-01-19 00:39:34', 'wxid_zuv0x3du2vpj22', '怀揣着梦想的咸鱼', 10, NULL, 1351121701864103938, 1351200408238723075, '测试群', '24855880487@chatroom');
INSERT INTO `v_user` VALUES (1351207612454998018, '2021-01-19 00:39:34', '2021-01-19 22:07:41', 'wxid_30wk2jz8hdrv22', '夜知冷暖', 16, NULL, 1351121701864103938, 1351200408238723075, '测试群', '24855880487@chatroom');

SET FOREIGN_KEY_CHECKS = 1;
