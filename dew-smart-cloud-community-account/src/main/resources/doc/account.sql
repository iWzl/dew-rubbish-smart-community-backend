SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account_login
-- ----------------------------
DROP TABLE IF EXISTS `account_login`;
CREATE TABLE `account_login`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '登录表主键',
  `login_key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录使用的关键字',
  `uin` bigint(11) NOT NULL COMMENT '关键字对应的用户uin',
  `status` enum('ENABLE','NO_VERIFY','DISABLE') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'ENABLE' COMMENT '账户的登录限制状态，可登录/不可登录',
  `id_type` enum('EMAIL','PHONE','WEIBO','WECHAT','QQ','UNKNOW') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'UNKNOW' COMMENT 'id类型',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `login_key`(`login_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 143 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for account_track
-- ----------------------------
DROP TABLE IF EXISTS `account_track`;
CREATE TABLE `account_track`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账户登录信息',
  `uin` bigint(11) NOT NULL COMMENT '账户uin',
  `imei` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机IMEI',
  `ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `app_version` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'APP版本号',
  `system_version` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '系统版本号',
  `device_model` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机型号',
  `device_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机设备名称',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1218 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for account_uin
-- ----------------------------
DROP TABLE IF EXISTS `account_uin`;
CREATE TABLE `account_uin`  (
  `uin` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '用户唯一标识',
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户注册使用的账户',
  `id_type` enum('EMAIL','PHONE','WEIBO','WECHAT','QQ','UNKNOW') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'UNKNOW' COMMENT '注册账号使用的账号类型',
  `secret` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密令',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户的密码',
  `status` enum('DELETED','DISABLED','ENABLE','DEACTIVATED') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'ENABLE' COMMENT '账户的状态',
  `type` enum('NORMAL','GRAY','AUDITOR') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号当前的状态',
  `platform` enum('Android','iOS','Web') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'Android' COMMENT '注册的平台',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_time` bigint(20) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`uin`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20140 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
