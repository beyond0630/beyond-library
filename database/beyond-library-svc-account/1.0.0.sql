CREATE DATABASE `beyond-library-account` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';

USE `beyond-library-account`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bla_permission
-- ----------------------------
DROP TABLE IF EXISTS `bla_permission`;
CREATE TABLE `bla_permission`
(
    `id`          int                                                           NOT NULL AUTO_INCREMENT COMMENT 'id',
    `code`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '权限编码',
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '权限名称',
    `url`         varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '访问地址',
    `is_disabled` bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否禁用',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_bla_permission_code` (`code`) USING BTREE,
    UNIQUE INDEX `uk_bla_permission_url` (`url`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '权限表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bla_permission
-- ----------------------------
INSERT INTO `bla_permission`
VALUES (1, 'ADD_ROLE', '添加角色', 'POST-/api/roles', b'0');
INSERT INTO `bla_permission`
VALUES (2, 'EDIT_ROLE', '编辑角色', 'PUT-/api/roles', b'0');
INSERT INTO `bla_permission`
VALUES (3, 'DELETE_ROLE', '删除角色', 'DELETE-/api/roles', b'0');
INSERT INTO `bla_permission`
VALUES (4, 'ADD_PERMISSION', '添加权限', 'POST-/api/permissions', b'0');
INSERT INTO `bla_permission`
VALUES (5, 'EDIT_PERMISSION', '编辑权限', 'PUT-/api/permissions', b'0');
INSERT INTO `bla_permission`
VALUES (6, 'DELETE_PERMISSION', '删除权限', 'DELETE-/api/permissions', b'0');
INSERT INTO `bla_permission`
VALUES (7, 'ADD_USER_ROLE', '添加用户角色', 'POST-/api/users/{userId}/roles', b'0');
INSERT INTO `bla_permission`
VALUES (8, 'DELETE_USER_ROLE', '删除用户角色', 'DELETE-/api/users/{userId}/roles', b'0');
INSERT INTO `bla_permission`
VALUES (9, 'ADD_ROLE_PERMISSION', '添加角色权限', 'POST-/api/roles/{code}/permissions', b'0');
INSERT INTO `bla_permission`
VALUES (10, 'DELETE_ROLE_PERMISSION', '删除角色权限', 'DELETE-/api/roles/{code}/permissions', b'0');

-- ----------------------------
-- Table structure for bla_role
-- ----------------------------
DROP TABLE IF EXISTS `bla_role`;
CREATE TABLE `bla_role`
(
    `id`          int                                                          NOT NULL AUTO_INCREMENT COMMENT 'id',
    `code`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '角色名称',
    `is_disabled` bit(1)                                                       NOT NULL DEFAULT b'0' COMMENT '是否禁用',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_bla_role_code` (`code`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bla_role
-- ----------------------------
INSERT INTO `bla_role`
VALUES (1, 'ADMIN', '管理员', b'0');

-- ----------------------------
-- Table structure for bla_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `bla_role_permission`;
CREATE TABLE `bla_role_permission`
(
    `id`              int                                                          NOT NULL AUTO_INCREMENT,
    `role_code`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
    `permission_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限编码',
    `is_disabled`     bit(1)                                                       NOT NULL DEFAULT b'0' COMMENT '是否禁用',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `fk_bla_role_permission_bla_role_1` (`role_code`) USING BTREE,
    INDEX `fk_bla_role_permission_bla_permission_1` (`permission_code`) USING BTREE,
    CONSTRAINT `fk_bla_role_permission_bla_permission_1` FOREIGN KEY (`permission_code`) REFERENCES `bla_permission` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `fk_bla_role_permission_bla_role_1` FOREIGN KEY (`role_code`) REFERENCES `bla_role` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色权限表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bla_role_permission
-- ----------------------------
INSERT INTO `bla_role_permission`
VALUES (1, 'ADMIN', 'ADD_ROLE', b'0');
INSERT INTO `bla_role_permission`
VALUES (2, 'ADMIN', 'EDIT_ROLE', b'0');
INSERT INTO `bla_role_permission`
VALUES (3, 'ADMIN', 'DELETE_ROLE', b'0');
INSERT INTO `bla_role_permission`
VALUES (4, 'ADMIN', 'ADD_PERMISSION', b'0');
INSERT INTO `bla_role_permission`
VALUES (5, 'ADMIN', 'EDIT_PERMISSION', b'0');
INSERT INTO `bla_role_permission`
VALUES (6, 'ADMIN', 'DELETE_PERMISSION', b'0');
INSERT INTO `bla_role_permission`
VALUES (7, 'ADMIN', 'ADD_USER_ROLE', b'0');
INSERT INTO `bla_role_permission`
VALUES (8, 'ADMIN', 'DELETE_USER_ROLE', b'0');
INSERT INTO `bla_role_permission`
VALUES (9, 'ADMIN', 'ADD_ROLE_PERMISSION', b'0');
INSERT INTO `bla_role_permission`
VALUES (10, 'ADMIN', 'DELETE_ROLE_PERMISSION', b'0');

-- ----------------------------
-- Table structure for bla_user
-- ----------------------------
DROP TABLE IF EXISTS `bla_user`;
CREATE TABLE `bla_user`
(
    `id`            bigint                                                        NOT NULL COMMENT 'id',
    `username`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户名',
    `password`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
    `email`         varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '邮箱',
    `register_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
    `is_disabled`   bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否禁用',
    `is_deleted`    bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `created_by`    bigint                                                        NOT NULL COMMENT '创建人',
    `created_at`    datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modified_by`   bigint                                                        NOT NULL COMMENT '更新人',
    `modified_at`   datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_bla_user_username` (`username`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bla_user
-- ----------------------------
INSERT INTO `bla_user`
VALUES (1, 'admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'aaaa@beyond.com', '2021-12-09 17:15:45', b'0', b'0', 1,
        '2021-12-09 17:15:45', 1, '2021-12-09 17:15:56');
INSERT INTO `bla_user`
VALUES (918892588088426496, 'beyond', '754159999dd84d5f3ecfd8c45b8c6608476fe944', 'beyond@beyond.com',
        '2021-12-10 15:51:05', b'0', b'0', 918892588088426496, '2021-12-10 15:51:05', 918892588088426496,
        '2021-12-10 15:51:05');

-- ----------------------------
-- Table structure for bla_user_role
-- ----------------------------
DROP TABLE IF EXISTS `bla_user_role`;
CREATE TABLE `bla_user_role`
(
    `id`          int                                                          NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id`     bigint                                                       NOT NULL COMMENT '用户 id',
    `role_code`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
    `is_disabled` bit(1)                                                       NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `fk_bla_user_role_bla_user_1` (`user_id`) USING BTREE,
    INDEX `fk_bla_user_role_bla_role_1` (`role_code`) USING BTREE,
    CONSTRAINT `fk_bla_user_role_bla_role_1` FOREIGN KEY (`role_code`) REFERENCES `bla_role` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `fk_bla_user_role_bla_user_1` FOREIGN KEY (`user_id`) REFERENCES `bla_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户角色表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bla_user_role
-- ----------------------------
INSERT INTO `bla_user_role`
VALUES (1, 1, 'ADMIN', b'0');
INSERT INTO `bla_user_role`
VALUES (2, 918892588088426496, 'ADMIN', b'0');

SET FOREIGN_KEY_CHECKS = 1;
