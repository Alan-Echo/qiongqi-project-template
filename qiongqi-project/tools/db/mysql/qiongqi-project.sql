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

 Date: 15/10/2021 01:34:48
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
-- Table structure for v_applet
-- ----------------------------
DROP TABLE IF EXISTS `v_applet`;
CREATE TABLE `v_applet`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '版本号',
  `appId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小程序appID',
  `appSecret` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小程序密钥',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '小程序配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for v_log_moneys
-- ----------------------------
DROP TABLE IF EXISTS `v_log_moneys`;
CREATE TABLE `v_log_moneys`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '版本号',
  `userId` bigint(20) NULL DEFAULT NULL COMMENT '用户id/商家id',
  `goodsId` bigint(20) NULL DEFAULT NULL COMMENT '商品id',
  `dataSrc` int(4) NULL DEFAULT NULL COMMENT '流水来源;1:交易订单 2:订单结算 3:提现申请 4.退款订单',
  `remark` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注/注释',
  `moneyType` int(4) NULL DEFAULT NULL COMMENT '流水标志;1:收入 0:支出',
  `money` decimal(11, 2) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '金额',
  `tradeNo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '外部流水号;交易流水号',
  `payType` int(4) NULL DEFAULT NULL COMMENT '支付类型;1:支付宝;2:微信',
  `dataFlag` int(4) NULL DEFAULT 1 COMMENT '有效状态;1:有效 -1:删除',
  `orderId` bigint(20) NULL DEFAULT NULL COMMENT '订单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资金流水表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for v_order
-- ----------------------------
DROP TABLE IF EXISTS `v_order`;
CREATE TABLE `v_order`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '版本号',
  `total` decimal(10, 2) NULL DEFAULT NULL COMMENT '订单金额',
  `userId` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `goodsId` bigint(20) NULL DEFAULT NULL COMMENT '商品id',
  `isPay` int(4) NULL DEFAULT NULL COMMENT '是否支付;1:是;0:否',
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `goodsName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名',
  `orderType` int(4) NULL DEFAULT NULL COMMENT '类型;1:周卡;2:月卡;3:年卡',
  `orderNum` int(10) NULL DEFAULT 1 COMMENT '订单数量',
  `payType` int(4) NULL DEFAULT NULL COMMENT '支付类型;1;支付宝;2;微信',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for v_payconfig
-- ----------------------------
DROP TABLE IF EXISTS `v_payconfig`;
CREATE TABLE `v_payconfig`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '版本号',
  `aliAppId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付宝appid',
  `aliMerchantPrivateKey` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '支付宝商户私钥',
  `aliPublicKey` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '支付宝公钥',
  `notifyUrl` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付通知回调域名',
  `payExTime` int(10) NULL DEFAULT 30 COMMENT '支付有效期:(分钟)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '支付相关配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for v_qiniu
-- ----------------------------
DROP TABLE IF EXISTS `v_qiniu`;
CREATE TABLE `v_qiniu`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '版本号',
  `zone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `bucketName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '空间名',
  `ak` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'AK密码',
  `sk` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'SK密钥',
  `doMain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '域名',
  `status` int(4) NULL DEFAULT NULL COMMENT '状态:0:禁用;1启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '七牛云' ROW_FORMAT = Dynamic;

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
  `openId` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微信用户openId',
  `nickName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for v_vip
-- ----------------------------
DROP TABLE IF EXISTS `v_vip`;
CREATE TABLE `v_vip`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '版本号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `total` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `type` int(4) NULL DEFAULT NULL COMMENT '类型;1:周卡;2:月卡;3:年卡',
  `remark` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '说明',
  `img` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `sales` int(10) NULL DEFAULT 0 COMMENT '销量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'vip会员' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
