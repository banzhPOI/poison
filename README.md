# poison
先把parent install到本地就能运行了，推荐java17

# 建表语句
USE poison;
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
id          VARCHAR(64)  NOT NULL COMMENT '主键ID',
username    VARCHAR(16)  NOT NULL DEFAULT '' COMMENT '用户名',
email       VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '邮箱',
mobile      VARCHAR(32)  NOT NULL DEFAULT '' COMMENT '手机号',
`status`    VARCHAR(32)  NOT NULL DEFAULT 'NORMAL' COMMENT '状态',
`password`  VARCHAR(256) NOT NULL COMMENT '密码',
create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY (`id`)
) COMMENT ='用户表';

