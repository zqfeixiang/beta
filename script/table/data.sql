
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
     `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
     `operation_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '接口名称',
     `request` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '请求参数',
     `response` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '结果',
     `error` tinyint(4) DEFAULT NULL COMMENT '是否异常',
     `stack` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '异常堆栈',
     `taketime` bigint(20) DEFAULT NULL COMMENT '请求耗时，单位ms',
     `create_time` datetime DEFAULT NULL COMMENT '创建时间',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE `users` (
     `userId` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一自增的用户Id',
     `username` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户登录名',
     `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
     `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
     `jobNum` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工号',
     `department` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门',
     `team` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '团队',
     `role` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色（填写角色ID，如有多个，以逗号隔开）',
     `product` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
     `createTime` datetime DEFAULT NULL COMMENT '用户创建时间',
     `loginTime` datetime DEFAULT NULL COMMENT '最近一次登录时间',
     `note1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
     `note2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
     PRIMARY KEY (`userId`),
     KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


INSERT INTO `pomp_test`.`t_log` VALUES (1,'更新用户','Users(userid=null, username=test01, password=null, name=null, jobnum=null, department=111111111, team=test, role=null, product=null, createtime=null, logintime=null, note1=null, note2=null)','{\"code\":1,\"data\":{\"dataList\":[{\"createtime\":1526994276000,\"department\":\"111111111\",\"password\":\"111\",\"product\":\"3,4\",\"role\":\"30\",\"team\":\"test\",\"userid\":672,\"username\":\"test011\"}]},\"message\":\"success\"}',0,NULL,423,'2020-09-05 01:25:48');

INSERT INTO `pomp_test`.`users` VALUES (1,'zhangsan','111','张三','0001','工程管理部','技术管理团队','6','1,2,5,6','2018-02-08 14:22:12','2016-12-08 17:16:59','',''),
                           (2,'lisi','222','李四','0002','开发二部','后端团队','5','1,2,3','2018-02-08 14:22:12','2017-07-11 16:00:25','',''),
                           (3,'wangwu','333','王武','0003','开发二部','营销服务团队','5','1,2,3,4','2018-02-08 14:22:12','2017-01-03 16:54:42','',''),
                           (4,'songliu','444','宋刘','0004','资源保障部','财务团队','226',NULL,'2018-02-08 14:22:12',NULL,'','');