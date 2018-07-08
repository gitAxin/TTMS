/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50640
Source Host           : localhost:3306
Source Database       : ttms

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2018-07-08 20:10:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_companies
-- ----------------------------
DROP TABLE IF EXISTS `sys_companies`;
CREATE TABLE `sys_companies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `category` varchar(50) DEFAULT NULL COMMENT '类型',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `address` varchar(100) DEFAULT NULL COMMENT '电话',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `valid` tinyint(1) DEFAULT '1' COMMENT '有效标志',
  `createdTime` datetime DEFAULT NULL COMMENT '新增时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '更新时间',
  `createdUser` varchar(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` varchar(20) DEFAULT NULL COMMENT '更新用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_companies
-- ----------------------------

-- ----------------------------
-- Table structure for sys_organizations
-- ----------------------------
DROP TABLE IF EXISTS `sys_organizations`;
CREATE TABLE `sys_organizations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '机构名称',
  `code` varchar(50) DEFAULT NULL COMMENT '机构编码',
  `parentId` int(11) DEFAULT NULL COMMENT '父机构id',
  `parentIds` int(11) DEFAULT NULL COMMENT '父机构ids 0/1/2/3',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否有效',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createdUser` varchar(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_organizations
-- ----------------------------

-- ----------------------------
-- Table structure for sys_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_resources`;
CREATE TABLE `sys_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `url` varchar(200) DEFAULT NULL COMMENT '资源URL',
  `type` int(11) DEFAULT NULL COMMENT '类型     1：菜单   2：按钮',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `note` varchar(100) DEFAULT NULL COMMENT '备注',
  `parentId` int(11) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `permission` varchar(500) DEFAULT NULL COMMENT '授权(如：user:create)',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createdUser` varchar(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源管理';

-- ----------------------------
-- Records of sys_resources
-- ----------------------------

-- ----------------------------
-- Table structure for sys_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles`;
CREATE TABLE `sys_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '角色',
  `note` varchar(500) DEFAULT NULL COMMENT '说明',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createdUser` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modifiedUser` varchar(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_roles
-- ----------------------------
INSERT INTO `sys_roles` VALUES ('1', '系统管理员', '系统管理员', '2017-08-27 14:59:32', null, 'admin', null);
INSERT INTO `sys_roles` VALUES ('2', '产品经理', '产品经理', '2017-08-27 15:00:01', null, 'admin', null);
INSERT INTO `sys_roles` VALUES ('3', '团负责人', '只能查看项目', '2017-08-27 15:00:38', null, 'admin', null);

-- ----------------------------
-- Table structure for sys_role_resources
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resources`;
CREATE TABLE `sys_role_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `resource_id` int(11) DEFAULT NULL COMMENT 'ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_resources
-- ----------------------------

-- ----------------------------
-- Table structure for sys_users
-- ----------------------------
DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE `sys_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐值',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机',
  `valid` tinyint(4) DEFAULT NULL COMMENT '状态  1：启用 0：禁用',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间 ',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createdUser` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modifiedUser` varchar(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_users
-- ----------------------------
INSERT INTO `sys_users` VALUES ('1', 'admin', '393b61ef45cfdc541280ecb4fa8b12dd', '287b48d2-d154-46c8-baf0-121141aab442', 'admin@163.com', '01234567890', '1', '2017-08-24 15:29:04', null, 'admin', null);
INSERT INTO `sys_users` VALUES ('7', 'Tom', '1035ac90e0c33632619d7b8d254457dc', '1d654add-5f12-4dc5-aead-9d2bd0bff880', 'Tom@qq.com', '13131933050', '1', '2017-08-29 22:45:48', '2017-08-29 22:51:06', null, null);
INSERT INTO `sys_users` VALUES ('8', 'Sally', '1d104b68c2b4be371f9e6edb7da56ba9', 'd1a24747-f076-427f-9418-83ee8b29c069', 'Sally@126.com', '15830533609', '1', '2017-08-29 22:46:33', '2017-08-29 22:50:55', null, null);

-- ----------------------------
-- Table structure for sys_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_roles`;
CREATE TABLE `sys_user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

-- ----------------------------
-- Records of sys_user_roles
-- ----------------------------
INSERT INTO `sys_user_roles` VALUES ('1', '1', '1');
INSERT INTO `sys_user_roles` VALUES ('2', '7', '2');
INSERT INTO `sys_user_roles` VALUES ('3', '8', '3');
INSERT INTO `sys_user_roles` VALUES ('14', '8', '3');
INSERT INTO `sys_user_roles` VALUES ('15', '7', '2');

-- ----------------------------
-- Table structure for tms_attachements
-- ----------------------------
DROP TABLE IF EXISTS `tms_attachements`;
CREATE TABLE `tms_attachements` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `fileName` varchar(200) DEFAULT NULL COMMENT '文件名称',
  `contentType` varchar(50) DEFAULT NULL COMMENT '文件类型',
  `filePath` varchar(200) DEFAULT NULL COMMENT '文件路径',
  `fileDisgest` varchar(200) DEFAULT NULL COMMENT '文件摘要',
  `athType` int(3) DEFAULT NULL COMMENT '所属分类id',
  `belongId` int(11) DEFAULT NULL COMMENT '所属团id',
  `createdUser` varchar(255) DEFAULT NULL COMMENT '创建人',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedUser` varchar(255) DEFAULT NULL COMMENT '修改人',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8 COMMENT='附件表';

-- ----------------------------
-- Records of tms_attachements
-- ----------------------------
INSERT INTO `tms_attachements` VALUES ('136', 'aaaa', 'abc.png', 'image/png', 'D:\\tts9\\workspace\\ttms2.0\\src\\main\\webapp\\uploads\\2017\\08\\22\\2e89c8dc-09c6-40a7-b6d6-2390a08168a6.png', '5f1d88136573370e5319259823d04ea6', '1', '1', 'admin', '2017-08-22 20:21:48', 'admin', '2017-08-22 20:21:48');
INSERT INTO `tms_attachements` VALUES ('137', '新', '新建文本文档.txt', 'text/plain', 'D:\\tts9\\workspace\\ttms2.0\\src\\main\\webapp\\uploads\\2017\\08\\22\\00b10e3e-7b10-4907-95fb-ab9fca615291.txt', '202cb962ac59075b964b07152d234b70', '1', '1', 'admin', '2017-08-22 20:30:28', 'admin', '2017-08-22 20:30:28');

-- ----------------------------
-- Table structure for tms_classes
-- ----------------------------
DROP TABLE IF EXISTS `tms_classes`;
CREATE TABLE `tms_classes` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(200) DEFAULT NULL COMMENT '分类名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序号',
  `parentId` int(11) DEFAULT NULL COMMENT '父类型id ',
  `note` varchar(500) DEFAULT NULL COMMENT '说明',
  `createdUser` varchar(255) DEFAULT NULL COMMENT '创建人',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedUser` varchar(255) DEFAULT NULL COMMENT '修改用户',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8 COMMENT='产品分类表';

-- ----------------------------
-- Records of tms_classes
-- ----------------------------
INSERT INTO `tms_classes` VALUES ('135', '出境游', '1', null, '', null, '2017-08-22 16:01:33', null, '2017-08-22 16:01:33');
INSERT INTO `tms_classes` VALUES ('136', '国内游', '2', null, '', null, '2017-08-22 16:01:44', null, '2017-08-22 16:01:44');
INSERT INTO `tms_classes` VALUES ('137', '海岛游', '3', null, '', null, '2017-08-22 16:02:08', null, '2017-08-22 16:02:08');
INSERT INTO `tms_classes` VALUES ('138', '欧美大国', '1', '135', '', null, '2017-08-22 16:02:29', null, '2017-08-22 16:02:29');
INSERT INTO `tms_classes` VALUES ('139', '澳非探秘', '1', '135', '', null, '2017-08-22 16:02:43', null, '2017-08-22 16:02:43');
INSERT INTO `tms_classes` VALUES ('140', '海滨', '1', '136', '', null, '2017-08-22 16:03:00', null, '2017-08-22 16:03:00');
INSERT INTO `tms_classes` VALUES ('141', '南方', '2', '136', '', null, '2017-08-22 16:03:15', null, '2017-08-22 16:03:15');
INSERT INTO `tms_classes` VALUES ('142', '经济岛屿', '1', '137', '', null, '2017-08-22 16:03:32', null, '2017-08-22 16:03:32');
INSERT INTO `tms_classes` VALUES ('143', '奢华岛屿', '2', '137', '', null, '2017-08-22 16:03:45', null, '2017-08-22 16:03:45');
INSERT INTO `tms_classes` VALUES ('144', '欧洲', '1', '138', '', null, '2017-08-22 16:04:08', null, '2017-08-22 16:04:08');
INSERT INTO `tms_classes` VALUES ('145', '澳洲', '2', '138', '', null, '2017-08-22 16:04:21', null, '2017-08-22 16:04:21');

-- ----------------------------
-- Table structure for tms_products
-- ----------------------------
DROP TABLE IF EXISTS `tms_products`;
CREATE TABLE `tms_products` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(50) NOT NULL COMMENT '产品编码',
  `name` varchar(200) DEFAULT '' COMMENT '产品名称',
  `teamId` int(11) DEFAULT NULL COMMENT '团Id',
  `exText` varchar(500) DEFAULT NULL COMMENT '特殊提示',
  `onlineDate` date DEFAULT NULL COMMENT '开始日期',
  `offlineDate` date DEFAULT NULL COMMENT '结束时期',
  `quantity` int(11) DEFAULT '0' COMMENT '数量',
  `minQty` int(11) DEFAULT '0' COMMENT '最低数量',
  `soldQty` int(11) DEFAULT '0' COMMENT '销售数量',
  `price` decimal(10,0) DEFAULT '0' COMMENT '单价',
  `classId` int(11) DEFAULT '0' COMMENT '所属分类id',
  `nights` int(11) DEFAULT '0' COMMENT '晚数',
  `state` int(11) DEFAULT '0' COMMENT '状态 0待售  1上架  2下架',
  `note` varchar(2000) DEFAULT NULL COMMENT '备注',
  `createdUser` varchar(255) DEFAULT NULL COMMENT '创建人',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedUser` varchar(255) DEFAULT NULL COMMENT '修改人',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='产品表';

-- ----------------------------
-- Records of tms_products
-- ----------------------------
INSERT INTO `tms_products` VALUES ('9', 'TLCN20170503SYCJ01001', '梦幻天堂·赏鲸之旅', '11', '注意安全', '2017-08-22', '2017-08-25', '0', '50', '0', '888', '143', '6', '2', '大溪地·波拉波拉岛圣瑞吉St Regis·与魔鬼鱼共舞+浮潜观鲸8天6晚 HOT', null, '2017-08-22 16:06:28', null, null);
INSERT INTO `tms_products` VALUES ('10', 'TLCN20170503HZCJ02001', '上天入地·奢豪之旅', '12', '注意安全', '2017-08-22', '2017-08-25', '0', '50', '0', '999', '143', '9', '2', '行程亮点\\r\\n【走进黄金帝国·畅游奇迹未来】\\r\\n★度假必选：走进柏拉图理想国，在棕榈岛亚特兰蒂斯水上王国嬉戏踏浪！\\r\\n★沙漠冲沙：专属四驱车冲沙，骑骆驼、绘汉娜、感受阿拉伯奢华，诠释贝都因风情。\\r\\n★VIP尊享： 直升机凌空俯览奇迹之国，法拉利主题乐园点燃激情，从陆地到天空的跨越！\\r\\n★乐畅购物：在特产手表黄金的国度，一站式购物中心免税血拼壕购！', null, '2017-08-22 16:07:52', null, null);
INSERT INTO `tms_products` VALUES ('11', 'TPCN-CHN-20170605-XA-11-001', '兵马俑制作+大明宫游览+拓片体验亲子文化游4晚5天', '23', '注意天气变化', '2017-08-22', null, '0', '50', '0', '1188', '141', '4', '1', '甄选悦椿温泉酒店，尽享泡汤之乐趣；精选凯悦湖景客房，揽南湖之美景；\\r\\n★兵马俑制作体验，寻找消失的军团；\\r\\n★模拟考古挖掘，用传统工艺拓出民族文化，从古文字和图案中品味秦唐风韵；\\r\\n★观皮影戏，感受民间戏曲艺术之乐趣；\\r\\n★实景历史剧《长恨歌》，骊山脚下再现大唐太平盛世；\\r\\n★全程专属车导，享无忧旅行；', null, '2017-08-22 16:09:47', null, null);
INSERT INTO `tms_products` VALUES ('12', 'TPCN-CHN-20171207-HRB-10-001', '哈尔滨冰灯豪华游', '24', '天气', null, null, '0', '25', '0', '788', '136', '2', '0', '哈尔滨冰灯豪华游', null, '2017-08-22 16:11:09', null, null);

-- ----------------------------
-- Table structure for tms_projects
-- ----------------------------
DROP TABLE IF EXISTS `tms_projects`;
CREATE TABLE `tms_projects` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(50) DEFAULT NULL COMMENT '项目编码',
  `name` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `beginDate` date DEFAULT NULL COMMENT '开始日期',
  `endDate` date DEFAULT NULL COMMENT '结束日期',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否启用：0否  1是',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间 ',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createdUser` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modifiedUser` varchar(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='项目表';

-- ----------------------------
-- Records of tms_projects
-- ----------------------------
INSERT INTO `tms_projects` VALUES ('4', 'TPCN-20170701-CHN-PEK-001', '2017 徒步北欧探梦布道石', '2017-07-21', '2017-07-25', '1', '2017 徒步北欧探梦布道石', '2017-07-05 14:19:13', '2017-08-22 15:55:46', null, null);
INSERT INTO `tms_projects` VALUES ('5', 'TP-20170710-USA-NY-001', '2017年澳大利亚乌鲁鲁马拉松', '2017-08-01', '2017-08-08', '1', '2017年澳大利亚乌鲁鲁马拉松', '2017-07-05 14:19:14', '2017-08-29 23:08:54', null, null);
INSERT INTO `tms_projects` VALUES ('6', 'TP-20170710-DEU-BER-001', '2017美国纽约马拉松', '2017-08-11', '2017-08-20', '1', '2017美国纽约马拉松', '2017-07-05 14:19:14', '2017-08-22 15:55:16', null, null);
INSERT INTO `tms_projects` VALUES ('7', 'TP-20170710-FIN-HEL-001', '2017德国柏林马拉松自由行', '2017-09-10', '2017-09-20', '1', '2017德国柏林马拉松自由行', '2017-07-05 14:19:14', '2017-08-22 15:55:27', null, null);

-- ----------------------------
-- Table structure for tms_teams
-- ----------------------------
DROP TABLE IF EXISTS `tms_teams`;
CREATE TABLE `tms_teams` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '团名称',
  `projectId` int(11) DEFAULT NULL COMMENT '项目id',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否启用： 0：禁用 1：启用',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createdUser` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modifiedUser` varchar(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='团表';

-- ----------------------------
-- Records of tms_teams
-- ----------------------------
INSERT INTO `tms_teams` VALUES ('11', '2017年澳大利亚7日团', '5', '1', '2017年澳大利亚7日团', '2017-07-06 10:34:23', '2017-08-22 15:59:03', null, null);
INSERT INTO `tms_teams` VALUES ('12', '2017年澳大利亚10日团', '5', '1', '2017年澳大利亚10日团', '2017-07-06 10:34:23', '2017-08-22 15:59:11', null, null);
INSERT INTO `tms_teams` VALUES ('23', '2017年澳大利亚5日团', '5', '1', '2017年澳大利亚5日团', '2017-07-06 14:46:51', '2017-08-22 15:58:52', null, null);
INSERT INTO `tms_teams` VALUES ('24', '2017年澳大利亚3日团', '5', '1', '2017年澳大利亚3日团', '2017-07-06 14:53:57', '2017-08-22 15:58:44', null, null);
INSERT INTO `tms_teams` VALUES ('25', '2017美国纽约马拉松5日团', '6', '0', '2017美国纽约马拉松5日团', '2017-07-06 16:00:37', '2017-08-22 15:56:44', null, null);
INSERT INTO `tms_teams` VALUES ('26', '2017美国纽约马拉松3日团', '6', '1', '2017美国纽约马拉松3日团', '2017-07-06 16:04:12', '2017-08-22 15:56:21', null, null);
