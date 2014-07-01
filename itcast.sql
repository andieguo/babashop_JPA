/*
Navicat MySQL Data Transfer

Source Server         : conn
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : itcast

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2013-05-06 09:28:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `brand`
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `code` varchar(36) COLLATE utf8_bin NOT NULL,
  `logopath` varchar(80) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(40) COLLATE utf8_bin NOT NULL,
  `visible` bit(1) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES ('330751a8-9d1d-409a-916e-925a780bff38', '/images/brand/2013/03/06/19/1501f245-1195-4261-a0bb-e8ad25e58d0b.jpg', '黎明瑜伽', '');
INSERT INTO `brand` VALUES ('386e0859-508a-4472-adb1-69894bef2977', '/images/brand/2013/03/06/19/022a3006-ee9f-4dff-b164-cd0080ac43ae.jpg', '远洋瑜伽', '');
INSERT INTO `brand` VALUES ('57f0570f-e150-4c61-9449-7d0862e87f0d', '/images/brand/2013/03/06/19/0e7f6b2b-9095-435f-bbfb-c5db6759dd67.jpg', '美洲狮', '');
INSERT INTO `brand` VALUES ('6560c672-1742-4c43-9e10-52bc8e3384e2', '/images/brand/2013/03/06/19/3ca478df-f751-4d3a-b328-b743d1442974.jpg', '时尚沐浴', '');
INSERT INTO `brand` VALUES ('9b0a0589-1b31-46b7-bd4c-e56f676380ab', '/images/brand/2013/03/24/10/ce5b6208-3cd6-4cfb-8111-7773f965c0e7.jpg', '时尚瑜伽', '');
INSERT INTO `brand` VALUES ('ffcf8806-8990-4f00-abcf-d7f75e243f9b', '/images/brand/2013/03/06/19/e635fd5d-76f7-4a20-9039-e82e5d31a072.jpg', '攀岩之家', '');

-- ----------------------------
-- Table structure for `productinfo`
-- ----------------------------
DROP TABLE IF EXISTS `productinfo`;
CREATE TABLE `productinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `baseprice` float NOT NULL,
  `buyexplain` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `clickcount` int(11) NOT NULL,
  `code` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `commend` bit(1) NOT NULL,
  `createdate` date DEFAULT NULL,
  `description` longtext COLLATE utf8_bin NOT NULL,
  `marketprice` float NOT NULL,
  `model` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  `sellcount` int(11) NOT NULL,
  `sellprice` float NOT NULL,
  `sexrequest` varchar(5) COLLATE utf8_bin NOT NULL,
  `visible` bit(1) NOT NULL,
  `weight` int(11) DEFAULT NULL,
  `brandid` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `typeid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA7C3E1DD2558859` (`brandid`),
  KEY `FKA7C3E1D95A1EC8E` (`typeid`),
  CONSTRAINT `FKA7C3E1D95A1EC8E` FOREIGN KEY (`typeid`) REFERENCES `producttype` (`typeid`),
  CONSTRAINT `FKA7C3E1DD2558859` FOREIGN KEY (`brandid`) REFERENCES `brand` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of productinfo
-- ----------------------------
INSERT INTO `productinfo` VALUES ('32', '110', '用于健身和锻炼', '1', 'YJ', '', '2013-03-25', 0x3C666F6E742073697A653D2233223EE794A8E4BA8EE581A5E8BAABE5928CE994BBE782BC3C2F666F6E743E, '130', 'SL410', '远洋瑜伽球', '0', '140', 'WOMEN', '', '200', '386e0859-508a-4472-adb1-69894bef2977', '28');
INSERT INTO `productinfo` VALUES ('33', '200', '用于健身', '3', 'YJZ110', '', '2013-01-27', 0xE794A8E4BA8EE581A5E8BAAB, '250', 'T90', '远洋瑜伽砖', '2', '230', 'NONE', '', '300', '386e0859-508a-4472-adb1-69894bef2977', '29');
INSERT INTO `productinfo` VALUES ('34', '300', '用于健身', '4', 'YJF', '', '2013-01-27', 0xE794A8E4BA8EE581A5E8BAAB, '350', 'T90', '远洋瑜伽服', '3', '320', 'WOMEN', '', '300', '386e0859-508a-4472-adb1-69894bef2977', '30');
INSERT INTO `productinfo` VALUES ('35', '200', '用于健身', '5', 'YJZ111', '', '2013-01-27', 0xE794A8E4BA8EE581A5E8BAAB, '250', 'T87', '黎明瑜伽砖', '4', '230', 'MAN', '', '300', '330751a8-9d1d-409a-916e-925a780bff38', '29');
INSERT INTO `productinfo` VALUES ('36', '120', '健身', '6', 'XS', '', '2013-01-27', 0xE581A5E8BAAB, '130', 'T90', '极地风暴攀岩线索', '5', '125', 'MAN', '', '20', 'ffcf8806-8990-4f00-abcf-d7f75e243f9b', '36');
INSERT INTO `productinfo` VALUES ('37', '159', '健身', '7', 'YJZ1', '', '2013-01-27', 0xE581A5E8BAAB, '170', 'YJ', '黎明瑜伽球', '6', '165', 'MAN', '', '200', '330751a8-9d1d-409a-916e-925a780bff38', '28');
INSERT INTO `productinfo` VALUES ('38', '150', '美洲狮溜冰鞋', '1', 'A28', '', '2013-01-28', 0xE7BE8EE6B4B2E78BAEE6BA9CE586B0E99E8B, '200', 'A28d090', '美洲狮MS077半软可调溜冰鞋', '0', '180', 'MAN', '', '200', 'ffcf8806-8990-4f00-abcf-d7f75e243f9b', '35');

-- ----------------------------
-- Table structure for `productstyle`
-- ----------------------------
DROP TABLE IF EXISTS `productstyle`;
CREATE TABLE `productstyle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imagename` varchar(100) COLLATE utf8_bin NOT NULL,
  `name` varchar(100) COLLATE utf8_bin NOT NULL,
  `visible` bit(1) NOT NULL,
  `productid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK459B72228A1B6677` (`productid`),
  CONSTRAINT `FK459B72228A1B6677` FOREIGN KEY (`productid`) REFERENCES `productinfo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of productstyle
-- ----------------------------
INSERT INTO `productstyle` VALUES ('53', '27f935f9-08f2-443d-b70a-533d4bf281ff.jpg', '黑色', '', '32');
INSERT INTO `productstyle` VALUES ('54', '2f0a9fcf-3624-4b99-bf2b-98520a5cb11e.jpg', '红色瑜伽砖', '', '33');
INSERT INTO `productstyle` VALUES ('55', '79fcaddd-029e-4a80-8335-a026308b6e1c.jpg', '蓝色瑜伽服', '', '34');
INSERT INTO `productstyle` VALUES ('56', '7bf97e21-70ba-4113-a88c-863d57a53355.jpg', '红色瑜伽砖', '', '35');
INSERT INTO `productstyle` VALUES ('57', 'c1dc203e-fc41-483d-8c8a-b2a9ec8b02e0.jpg', '灰色的', '', '36');
INSERT INTO `productstyle` VALUES ('58', '6d6513b6-85b1-493b-bcfc-ec131636d5ec.jpg', '红色的', '', '37');
INSERT INTO `productstyle` VALUES ('59', 'd61f0fda-a69c-4a2c-9431-69a41a23711c.jpg', '红白色', '', '38');
INSERT INTO `productstyle` VALUES ('60', '60ede801-b0b2-46d7-9b33-bbfc27a4941d.jpg', '红色', '', '32');

-- ----------------------------
-- Table structure for `producttype`
-- ----------------------------
DROP TABLE IF EXISTS `producttype`;
CREATE TABLE `producttype` (
  `typeid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(36) COLLATE utf8_bin NOT NULL,
  `note` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `visible` bit(1) DEFAULT NULL,
  `parantid` int(11) DEFAULT NULL,
  PRIMARY KEY (`typeid`),
  KEY `FKA8168A9EA56BFA` (`parantid`),
  CONSTRAINT `FKA8168A9EA56BFA` FOREIGN KEY (`parantid`) REFERENCES `producttype` (`typeid`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of producttype
-- ----------------------------
INSERT INTO `producttype` VALUES ('1', '瑜伽用品', '远洋瑜伽是一款很好的瑜伽产品，好', '', null);
INSERT INTO `producttype` VALUES ('2', '户外用品', '户外用品', '', null);
INSERT INTO `producttype` VALUES ('3', '自行车', '骑自行车环游世界', '', '2');
INSERT INTO `producttype` VALUES ('24', '鞋类', '敢问路在何方,路在脚下', '', '2');
INSERT INTO `producttype` VALUES ('25', '工具/仪器/眼镜', '工具必备，只有你想不到，没有你找不到', '', '2');
INSERT INTO `producttype` VALUES ('26', '攀岩装备', '攀岩必备', '', '2');
INSERT INTO `producttype` VALUES ('27', '户外服装', '服装我们的选择', '', '2');
INSERT INTO `producttype` VALUES ('28', '瑜伽球', '瑜伽球 好', '', '1');
INSERT INTO `producttype` VALUES ('29', '瑜伽砖', '瑜伽砖', '', '1');
INSERT INTO `producttype` VALUES ('30', '瑜伽服', '瑜伽服', '', '1');
INSERT INTO `producttype` VALUES ('32', '户外装备', '想要旅游吗，选择好的户外装备', '', '2');
INSERT INTO `producttype` VALUES ('33', '赛车', '赛车', '', '3');
INSERT INTO `producttype` VALUES ('34', '山地自行车', '山地自行车', '', '3');
INSERT INTO `producttype` VALUES ('35', '溜冰鞋', '溜冰鞋就是好', '', '24');
INSERT INTO `producttype` VALUES ('36', '攀岩绳索', '攀岩绳索', '', '26');
INSERT INTO `producttype` VALUES ('37', '健身器材', '健身器材', '', null);
INSERT INTO `producttype` VALUES ('38', '运动鞋', '运动鞋', '', null);
INSERT INTO `producttype` VALUES ('39', '哑铃', '哑铃', '', '37');
INSERT INTO `producttype` VALUES ('40', '休闲运动鞋', '休闲运动鞋', '', '38');
INSERT INTO `producttype` VALUES ('42', '跑步运动鞋', '跑步运动鞋', '', '38');
INSERT INTO `producttype` VALUES ('43', '跑步机', '跑步机', '', '37');
INSERT INTO `producttype` VALUES ('44', '呼啦圈', '呼啦圈', '', '37');
INSERT INTO `producttype` VALUES ('45', '越野车', '越野车', '', '3');
INSERT INTO `producttype` VALUES ('46', '太阳镜', '太阳镜 适用的太阳眼镜 保护眼镜哦', '', '25');
INSERT INTO `producttype` VALUES ('47', '匕首', '放生匕首', '', '25');

-- ----------------------------
-- Table structure for `uploadfile`
-- ----------------------------
DROP TABLE IF EXISTS `uploadfile`;
CREATE TABLE `uploadfile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `filepath` varchar(80) COLLATE utf8_bin NOT NULL,
  `uploadtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of uploadfile
-- ----------------------------
INSERT INTO `uploadfile` VALUES ('12', '/images/uploadfile/2013/01/20/20/4ca1dacd-1683-4de2-901a-698c62848b2b.jpg', '2013-01-20 20:50:19');
INSERT INTO `uploadfile` VALUES ('13', '/images/uploadfile/2013/01/21/09/4706cad6-63e6-4b96-b128-03cc33988c26.jpg', '2013-01-21 09:44:34');
INSERT INTO `uploadfile` VALUES ('14', '/images/uploadfile/2013/01/23/18/9972f38f-3316-4937-86f4-215cee45eeb6.jpg', '2013-01-23 18:32:32');
INSERT INTO `uploadfile` VALUES ('15', '/images/product/2013/01/24/10/215dbf54-65c0-444e-83f9-434a08e04960.jpg', '2013-01-24 10:18:08');
INSERT INTO `uploadfile` VALUES ('16', '/images/product/2013/01/24/10/a9ddbc4d-2ebf-45ac-a5aa-31fd6c64e4b5.jpg', '2013-01-24 10:22:21');
INSERT INTO `uploadfile` VALUES ('17', '/images/product/2013/01/24/10/2c2db6ed-da5c-4750-85fd-cef0dc2023a7.jpg', '2013-01-24 10:23:15');
INSERT INTO `uploadfile` VALUES ('18', '/images/product/2013/01/24/10/11e8a059-6a96-4c7f-82d9-9cc3b8a0129e.jpg', '2013-01-24 10:28:24');
INSERT INTO `uploadfile` VALUES ('19', '/images/product/2013/01/24/10/dfb0fc1b-70ab-4f53-aed2-d0ac12121cee.jpg', '2013-01-24 10:29:28');
INSERT INTO `uploadfile` VALUES ('20', '/images/product/2013/01/24/10/62abe665-6479-49ba-be07-ca7eb6fa9d0c.jpg', '2013-01-24 10:34:50');
INSERT INTO `uploadfile` VALUES ('21', '/images/product/2/29/prototype/381a2a41-96b3-4b75-8880-72bcf5bbbc87.jpg', '2013-01-24 10:52:07');
INSERT INTO `uploadfile` VALUES ('22', '/images/product/4/30/prototype/6d9cb495-0221-4a19-b7dc-17aeebd5faab.jpg', '2013-01-25 14:32:39');
INSERT INTO `uploadfile` VALUES ('23', '/images/product/4/31/prototype/7ca67a96-3dc5-4d63-bb3f-a52f3eaa0a53.jpg', '2013-01-25 14:36:26');
INSERT INTO `uploadfile` VALUES ('24', '/images/product/28/32/prototype/381221f2-3799-410a-889a-bb63f4b52cf2.jpg', '2013-01-27 22:00:49');
INSERT INTO `uploadfile` VALUES ('25', '/images/product/29/33/prototype/5652df7b-843c-4b8d-ab8c-19dd718dafa9.jpg', '2013-01-27 22:02:35');
INSERT INTO `uploadfile` VALUES ('26', '/images/product/30/34/prototype/112c63f3-ca78-425c-9014-f6ff993653a1.jpg', '2013-01-27 22:03:57');
INSERT INTO `uploadfile` VALUES ('27', '/images/product/29/35/prototype/61e37bd1-6e88-4188-8816-7f88f9a64df2.jpg', '2013-01-27 22:06:56');
INSERT INTO `uploadfile` VALUES ('28', '/images/product/29/37/prototype/3eaf7c6c-c393-425b-bc31-dfc2ccf124bd.jpg', '2013-01-27 23:07:58');
INSERT INTO `uploadfile` VALUES ('29', '/images/product/35/38/prototype/a2fe7c9f-2173-4f72-bda1-6c30a0e0cda9.jpg', '2013-01-28 18:58:09');
