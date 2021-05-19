/*
 Navicat Premium Data Transfer

 Source Server         : 123
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : graduation

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 19/05/2021 15:09:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `face` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '政治面貌',
  `education` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (1, 'admin', 'admin', 'admin', '男', 0, '群众', '本科');
INSERT INTO `t_admin` VALUES (2, 'abc', '123', 'jacky', '男', 21, '团员', '硕士');
INSERT INTO `t_admin` VALUES (6, 'abcd', '123', 'mike', '男', 21, '团员', '硕士');

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科室名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department` VALUES (1, '内科');
INSERT INTO `t_department` VALUES (2, '外科');
INSERT INTO `t_department` VALUES (3, '男科');
INSERT INTO `t_department` VALUES (4, '妇产科');
INSERT INTO `t_department` VALUES (5, '儿科');
INSERT INTO `t_department` VALUES (6, '精神科');
INSERT INTO `t_department` VALUES (7, '中医科');
INSERT INTO `t_department` VALUES (8, '营养科');
INSERT INTO `t_department` VALUES (9, '皮肤科');
INSERT INTO `t_department` VALUES (10, '五官科');

-- ----------------------------
-- Table structure for t_doctor
-- ----------------------------
DROP TABLE IF EXISTS `t_doctor`;
CREATE TABLE `t_doctor`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `face` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '政治面貌',
  `education` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历',
  `salary` int(11) NULL DEFAULT NULL COMMENT '薪酬',
  `departmentId` int(11) NULL DEFAULT NULL COMMENT '科室ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `departmentId`(`departmentId`) USING BTREE,
  CONSTRAINT `t_doctor_ibfk_1` FOREIGN KEY (`departmentId`) REFERENCES `t_department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_doctor
-- ----------------------------
INSERT INTO `t_doctor` VALUES (1, 'abc', '123', 'mikey', '男', 18, '团员', '硕士', 2900, 1);
INSERT INTO `t_doctor` VALUES (3, '123', '123', '张三', '男', 15, '群众', '硕士', 1000, 1);
INSERT INTO `t_doctor` VALUES (7, '1234', '123', 'ma', '男', 15, '团员', '本科', 1000, 1);
INSERT INTO `t_doctor` VALUES (8, '12', '123', 'mw', '男', 15, '群众', '本科', 1000, 1);
INSERT INTO `t_doctor` VALUES (10, '12345', '123', 'aa', '男', 15, '群众', '本科', 1000, 1);
INSERT INTO `t_doctor` VALUES (11, '6', '123', 'ab', '男', 15, '群众', '本科', 1000, 1);
INSERT INTO `t_doctor` VALUES (12, '4', '123', 'ac', '男', 15, '群众', '本科', 1000, 1);
INSERT INTO `t_doctor` VALUES (13, '3', '123', 'aq', '男', 15, '群众', '本科', 1000, 1);
INSERT INTO `t_doctor` VALUES (15, '1235', '123', 'abt', '男', 15, '群众', '本科', 1000, 1);

-- ----------------------------
-- Table structure for t_extra
-- ----------------------------
DROP TABLE IF EXISTS `t_extra`;
CREATE TABLE `t_extra`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `money` int(11) NULL DEFAULT NULL COMMENT '奖惩资金',
  `sign` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '奖金或者罚款',
  `reason` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '奖惩原因',
  `doctorId` int(11) NULL DEFAULT NULL COMMENT '奖惩人员id',
  `adminId` int(11) NULL DEFAULT NULL COMMENT '创建人id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_extra_ibfk_1`(`doctorId`) USING BTREE,
  INDEX `t_extra_ibfk_2`(`adminId`) USING BTREE,
  CONSTRAINT `t_extra_ibfk_1` FOREIGN KEY (`doctorId`) REFERENCES `t_doctor` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_extra_ibfk_2` FOREIGN KEY (`adminId`) REFERENCES `t_admin` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_extra
-- ----------------------------
INSERT INTO `t_extra` VALUES (23, 10, '奖金', 'a', 3, 1);
INSERT INTO `t_extra` VALUES (24, 10, '罚款', 'aa', 3, 1);
INSERT INTO `t_extra` VALUES (25, 1, '罚款', 'adad', 1, 1);
INSERT INTO `t_extra` VALUES (26, 2000, '罚款', '缺勤', 3, 1);
INSERT INTO `t_extra` VALUES (28, 2000, '罚款', '缺勤', 3, 1);
INSERT INTO `t_extra` VALUES (29, 2001, '奖金', '缺勤', 7, 1);
INSERT INTO `t_extra` VALUES (30, 2000, '罚款', '缺勤', 7, 1);
INSERT INTO `t_extra` VALUES (31, 2001, '罚款', '缺勤', 8, 1);
INSERT INTO `t_extra` VALUES (32, 2000, '奖金', '缺勤', 12, 1);
INSERT INTO `t_extra` VALUES (33, 2001, '罚款', '缺勤', 15, 1);
INSERT INTO `t_extra` VALUES (34, 2000, '罚款', '缺勤', 13, 1);
INSERT INTO `t_extra` VALUES (35, 2001, '罚款', '缺勤', 3, 1);
INSERT INTO `t_extra` VALUES (36, 2000, '奖金', '缺勤', 3, 1);
INSERT INTO `t_extra` VALUES (37, 2001, '罚款', '缺勤', 3, 1);
INSERT INTO `t_extra` VALUES (40, 200, '罚款', '缺勤', 3, 1);
INSERT INTO `t_extra` VALUES (41, 2001, '罚款', '缺勤', 3, 1);
INSERT INTO `t_extra` VALUES (42, 2000, '罚款', '缺勤', 12, 1);
INSERT INTO `t_extra` VALUES (43, 2001, '罚款', '缺勤', 1, 1);

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oldName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资料上传的名字',
  `newName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资料真实存储的名字',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资料路径',
  `date` timestamp(0) NULL DEFAULT NULL COMMENT '上传时间',
  `adminId` int(11) NULL DEFAULT NULL COMMENT '上传管理员ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_file_ibfk_1`(`adminId`) USING BTREE,
  CONSTRAINT `t_file_ibfk_1` FOREIGN KEY (`adminId`) REFERENCES `t_admin` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_file
-- ----------------------------
INSERT INTO `t_file` VALUES (1, '冰川.jpg', '1621404912771-冰川.jpg', '/F:/Github/GraduationProject/target/classes/static/files/2021-05-19', '2021-05-19 14:15:13', 1);
INSERT INTO `t_file` VALUES (2, '黄昏.jpg', '1621404918004-黄昏.jpg', '/F:/Github/GraduationProject/target/classes/static/files/2021-05-19', '2021-05-19 14:15:18', 1);
INSERT INTO `t_file` VALUES (3, '科比.jpg', '1621404923903-科比.jpg', '/F:/Github/GraduationProject/target/classes/static/files/2021-05-19', '2021-05-19 14:15:24', 1);
INSERT INTO `t_file` VALUES (4, '天空.jpg', '1621404945518-天空.jpg', '/F:/Github/GraduationProject/target/classes/static/files/2021-05-19', '2021-05-19 14:15:46', 2);

-- ----------------------------
-- Table structure for t_medical
-- ----------------------------
DROP TABLE IF EXISTS `t_medical`;
CREATE TABLE `t_medical`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '患者姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '患者年龄',
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '患者性别',
  `result` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '诊断结果',
  `advice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '医嘱',
  `date` timestamp(0) NULL DEFAULT NULL COMMENT '就诊时间',
  `doctorId` int(11) NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `doctorId`(`doctorId`) USING BTREE,
  CONSTRAINT `t_medical_ibfk_1` FOREIGN KEY (`doctorId`) REFERENCES `t_doctor` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_medical
-- ----------------------------
INSERT INTO `t_medical` VALUES (1, 'jack', 18, '男', '感冒', '多喝热水', '2021-05-10 11:16:58', 1);
INSERT INTO `t_medical` VALUES (2, '患者1', 18, '男', '症状1', '医嘱1', '2021-05-10 11:16:58', 3);
INSERT INTO `t_medical` VALUES (3, '患者2', 18, '男', '症状2', '医嘱2', '2021-05-10 11:16:58', 7);
INSERT INTO `t_medical` VALUES (4, '患者3', 18, '男', '症状3', '医嘱3', '2021-05-10 11:16:58', 3);
INSERT INTO `t_medical` VALUES (5, '患者4', 18, '男', '症状4', '医嘱4', '2021-05-10 11:16:58', 1);
INSERT INTO `t_medical` VALUES (6, '患者5', 18, '男', '症状5', '医嘱5', '2021-05-10 11:16:58', 1);
INSERT INTO `t_medical` VALUES (7, '患者6', 18, '男', '症状6', '医嘱6', '2021-05-10 11:16:58', 1);
INSERT INTO `t_medical` VALUES (8, '患者7', 18, '男', '症状7', '医嘱7', '2021-05-10 11:16:58', 1);
INSERT INTO `t_medical` VALUES (9, '患者8', 18, '男', '症状8', '医嘱8', '2021-05-10 11:16:58', 1);
INSERT INTO `t_medical` VALUES (10, '患者9', 18, '男', '症状9', '医嘱9', '2021-05-10 11:16:58', 1);
INSERT INTO `t_medical` VALUES (11, '患者10', 18, '男', '症状10', '医嘱10', '2021-05-10 11:16:58', 1);
INSERT INTO `t_medical` VALUES (12, '患者11', 18, '男', '症状11', '医嘱11', '2021-05-10 11:16:58', 1);
INSERT INTO `t_medical` VALUES (13, '患者12', 18, '男', '症状12', '医嘱12', '2021-05-10 11:16:58', 1);
INSERT INTO `t_medical` VALUES (14, '患者13', 18, '男', '症状13', '医嘱13', '2021-05-10 11:16:58', 1);
INSERT INTO `t_medical` VALUES (15, '患者14', 18, '男', '症状14', '医嘱14', '2021-05-10 11:16:58', 1);
INSERT INTO `t_medical` VALUES (17, '患者15', 20, '男', '症状15', '医嘱15', '2021-05-12 13:45:42', 1);
INSERT INTO `t_medical` VALUES (18, '患者16', 22, '女', '症状16', '医嘱16', '2021-05-12 13:46:45', 1);

-- ----------------------------
-- Table structure for t_project
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目描述',
  `state` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请状态',
  `date` timestamp(0) NULL DEFAULT NULL COMMENT '申请时间',
  `money` int(11) NULL DEFAULT NULL COMMENT '需要的资金',
  `doctorId` int(11) NULL DEFAULT NULL COMMENT '申请人',
  `adminId` int(11) NULL DEFAULT NULL COMMENT '审批人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_project_ibfk_1`(`doctorId`) USING BTREE,
  INDEX `t_project_ibfk_2`(`adminId`) USING BTREE,
  CONSTRAINT `t_project_ibfk_1` FOREIGN KEY (`doctorId`) REFERENCES `t_doctor` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_project_ibfk_2` FOREIGN KEY (`adminId`) REFERENCES `t_admin` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_project
-- ----------------------------
INSERT INTO `t_project` VALUES (2, '第二个项目', '审核通过', '2021-04-13 23:01:18', 1000, 1, 1);
INSERT INTO `t_project` VALUES (3, '第三个项目', '审核通过', '2021-04-13 23:02:22', 100, 1, 1);
INSERT INTO `t_project` VALUES (5, 'a', '审核通过', '2021-04-22 10:12:39', 100, 1, 1);
INSERT INTO `t_project` VALUES (6, 'a', '审核通过', '2021-04-22 10:12:46', 1000, 1, 1);
INSERT INTO `t_project` VALUES (7, 'a', '审核通过', '2021-04-22 10:12:52', 2000, 1, 2);
INSERT INTO `t_project` VALUES (8, 'a', '未审核', '2021-04-22 10:12:56', 300, 1, NULL);
INSERT INTO `t_project` VALUES (9, 'b', '未审核', '2021-04-22 10:13:01', 4000, 1, NULL);
INSERT INTO `t_project` VALUES (11, 'abc', '未审核', '2021-04-22 11:44:48', 100, 1, NULL);
INSERT INTO `t_project` VALUES (12, 'abd', '未审核', '2021-04-22 11:44:48', 100, 1, NULL);
INSERT INTO `t_project` VALUES (13, 'abdc', '未审核', '2021-04-22 11:44:48', 100, 1, NULL);
INSERT INTO `t_project` VALUES (14, 'abdd', '未审核', '2021-04-22 11:44:48', 1002, 1, NULL);
INSERT INTO `t_project` VALUES (15, 'abddd', '未审核', '2021-04-22 11:44:48', 100, 1, NULL);
INSERT INTO `t_project` VALUES (19, 'aaa', '未审核', '2021-05-06 15:27:32', 1000, 1, NULL);

-- ----------------------------
-- Table structure for t_record
-- ----------------------------
DROP TABLE IF EXISTS `t_record`;
CREATE TABLE `t_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp(0) NULL DEFAULT NULL COMMENT '考勤时间',
  `state` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '考勤状态',
  `doctorId` int(11) NULL DEFAULT NULL COMMENT '医务人员ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_record_ibfk_1`(`doctorId`) USING BTREE,
  CONSTRAINT `t_record_ibfk_1` FOREIGN KEY (`doctorId`) REFERENCES `t_doctor` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_record
-- ----------------------------
INSERT INTO `t_record` VALUES (1, '2021-04-08 17:41:40', '迟到', 3);
INSERT INTO `t_record` VALUES (2, '2021-04-08 17:41:43', '早退', 3);
INSERT INTO `t_record` VALUES (3, '2021-04-13 23:03:47', '正常', 1);
INSERT INTO `t_record` VALUES (4, '2021-04-22 09:53:58', '迟到', 3);
INSERT INTO `t_record` VALUES (5, '2021-04-22 09:53:59', '迟到', 3);
INSERT INTO `t_record` VALUES (6, '2021-04-22 09:54:02', '迟到', 3);
INSERT INTO `t_record` VALUES (7, '2021-04-22 09:54:05', '迟到', 3);
INSERT INTO `t_record` VALUES (8, '2021-04-22 09:54:06', '早退', 8);
INSERT INTO `t_record` VALUES (9, '2021-04-22 09:54:07', '早退', 10);
INSERT INTO `t_record` VALUES (10, '2021-04-22 09:54:08', '早退', 3);
INSERT INTO `t_record` VALUES (11, '2021-04-22 09:54:10', '早退', 3);
INSERT INTO `t_record` VALUES (12, '2021-04-22 09:54:12', '早退', 3);
INSERT INTO `t_record` VALUES (13, '2021-04-22 09:54:14', '早退', 3);
INSERT INTO `t_record` VALUES (14, '2021-04-22 09:54:14', '迟到', 3);
INSERT INTO `t_record` VALUES (15, '2021-04-22 09:54:17', '早退', 3);
INSERT INTO `t_record` VALUES (16, '2021-04-22 09:54:21', '早退', 7);
INSERT INTO `t_record` VALUES (17, '2021-04-22 09:54:23', '迟到', 11);
INSERT INTO `t_record` VALUES (18, '2021-04-22 09:54:24', '迟到', 3);
INSERT INTO `t_record` VALUES (19, '2021-04-22 09:54:25', '迟到', 11);
INSERT INTO `t_record` VALUES (21, '2021-05-06 15:26:36', '早退', 8);
INSERT INTO `t_record` VALUES (22, '2021-05-06 18:17:47', '正常', 1);

-- ----------------------------
-- Triggers structure for table t_admin
-- ----------------------------
DROP TRIGGER IF EXISTS `tri_admin_del`;
delimiter ;;
CREATE TRIGGER `tri_admin_del` BEFORE DELETE ON `t_admin` FOR EACH ROW BEGIN
    UPDATE t_extra set adminId=1 where adminId=OLD.id;
		UPDATE t_file set adminId=1 where adminId=OLD.id;
		UPDATE t_project set adminId=1 where adminId=OLD.id;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table t_doctor
-- ----------------------------
DROP TRIGGER IF EXISTS `tri_doctor_del`;
delimiter ;;
CREATE TRIGGER `tri_doctor_del` BEFORE DELETE ON `t_doctor` FOR EACH ROW BEGIN
    DELETE FROM t_extra WHERE doctorId=OLD.id;
		DELETE FROM t_project WHERE doctorId=OLD.id;
		DELETE FROM t_record WHERE doctorId=OLD.id;
		DELETE FROM t_medical WHERE doctorId=OLD.id;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
