/*
Navicat MySQL Data Transfer

Source Server         : liyan
Source Server Version : 50554
Source Host           : 39.105.183.155:3306
Source Database       : yc_learning

Target Server Type    : MYSQL
Target Server Version : 50554
File Encoding         : 65001

Date: 2020-12-13 15:25:52
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `aname` varchar(100) COLLATE utf8_bin NOT NULL,
  `apwd` varchar(255) COLLATE utf8_bin NOT NULL,
  `status` int(1) DEFAULT NULL COMMENT '状态值: 1可用 2冻结',
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO admin VALUES ('1', 'HillCheung', '202cb962ac59075b964b07152d234b70', '1');

-- ----------------------------
-- Table structure for `chapter`
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter` (
  `chid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(100) COLLATE utf8_bin NOT NULL,
  `cid` int(11) DEFAULT NULL COMMENT '课程号id',
  `temp` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`chid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of chapter
-- ----------------------------
INSERT INTO chapter VALUES ('1', 'javaApi', '1', null);
INSERT INTO chapter VALUES ('2', 'oop', '1', null);
INSERT INTO chapter VALUES ('3', 'jsp', '2', null);
INSERT INTO chapter VALUES ('4', 'servlet', '2', null);

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `coursename` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  `descr` text COLLATE utf8_bin,
  `pic` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '状态值: 1可用 2冻结',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO course VALUES ('1', 'j2se', 0x6A617661E59FBAE7A180E79FA5E8AF86, null, null);
INSERT INTO course VALUES ('2', 'j2ee', 0x6A617661776562, null, null);

-- ----------------------------
-- Table structure for `exam`
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam` (
  `exid` int(11) NOT NULL AUTO_INCREMENT,
  `ename` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  `eids` text COLLATE utf8_bin COMMENT '对应题号: 1,2,3,4',
  `createtime` date DEFAULT NULL COMMENT '创建时间',
  `examtime` int(5) DEFAULT NULL COMMENT '考试时长',
  `classes` int(11) DEFAULT NULL COMMENT '班级号',
  `aname` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '作者',
  `status` int(1) DEFAULT NULL COMMENT '状态 未开考0   开考1  已评2',
  `temp` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`exid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO exam VALUES ('1', '234', null, '2020-12-20', null, null, null, null, null);
INSERT INTO exam VALUES ('2', '3424', null, '2020-12-26', null, null, null, null, null);
INSERT INTO exam VALUES ('3', '45435', null, '2020-12-19', null, null, null, null, null);
INSERT INTO exam VALUES ('4', '32423', null, '2020-12-26', null, null, null, null, null);
INSERT INTO exam VALUES ('5', '23112', null, '2020-12-31', null, null, null, null, null);

-- ----------------------------
-- Table structure for `exercises`
-- ----------------------------
DROP TABLE IF EXISTS `exercises`;
CREATE TABLE `exercises` (
  `eid` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) NOT NULL COMMENT '对应章节',
  `type` int(1) NOT NULL COMMENT '题型：1单选，2多选，3判断 4综合',
  `content` text COLLATE utf8_bin NOT NULL COMMENT '题目内容',
  `optionA` text COLLATE utf8_bin,
  `optionB` text COLLATE utf8_bin,
  `optionC` text COLLATE utf8_bin,
  `optionD` text COLLATE utf8_bin,
  `answer` text COLLATE utf8_bin,
  `analysis` text COLLATE utf8_bin COMMENT '题解',
  `inputtime` date DEFAULT NULL COMMENT '录入时间',
  `aname` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '作者',
  `temp` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`eid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of exercises
-- ----------------------------

-- ----------------------------
-- Table structure for `record`
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `exid` int(11) DEFAULT NULL COMMENT '对应考卷',
  `uid` int(11) DEFAULT NULL COMMENT '对应学生',
  `useranswer` text COLLATE utf8_bin COMMENT '考生答案',
  `grade` double(5,2) DEFAULT NULL,
  `temp` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of record
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(100) COLLATE utf8_bin NOT NULL,
  `upwd` varchar(255) COLLATE utf8_bin NOT NULL,
  `tel` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `qq` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `vx` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `classes` varchar(255) COLLATE utf8_bin NOT NULL,
  `registrytime` date DEFAULT NULL,
  `endtime` date DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '状态值: 1可用 2冻结',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO user VALUES ('1', 'wjj', 'a', '123', 'aaa', '123', '312', '81', '2020-12-17', '2020-12-25', '1');
INSERT INTO user VALUES ('2', 'ly', 'a', '21', '421', '412', '52', '81', '2020-12-17', '2020-12-31', '1');
INSERT INTO user VALUES ('3', 'tjs', 'a', '412', '412', '412', '254', '81', '2021-01-16', '2020-12-30', '1');
