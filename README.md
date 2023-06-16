# poison
先把parent install到本地就能运行了，推荐java17

# 建表语句
USE poison;
DROP TABLE IF EXISTS user;
CREATE TABLE `user`
(
`id`          varchar(64)  NOT NULL COMMENT '主键ID',
`nickname`    varchar(16)  NOT NULL DEFAULT '' COMMENT '昵称',
`email`       varchar(64)  NOT NULL DEFAULT '' COMMENT '邮箱',
`mobile`      varchar(32)  NOT NULL DEFAULT '' COMMENT '手机号',
`status`      varchar(32)  NOT NULL DEFAULT 'NORMAL' COMMENT '状态',
`password`    varchar(256) NOT NULL COMMENT '密码',
`create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY (`id`),
UNIQUE KEY `uni_email` (`email`),
UNIQUE KEY `uni_mobile` (`mobile`)
) COMMENT ='用户表';