SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`         varchar(32) not null COMMENT 'ID',
    `code` varchar(32) not null COMMENT '微信code',
    `name` varchar(32) DEFAULT null COMMENT '名称',
    `power` int(1) default 0 comment '权限',
    `group_id` varchar(32) DEFAULT '1' COMMENT '组id',
    `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='用户';

DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`
(
    `id`   varchar(32) not null COMMENT 'ID',
    `name` varchar(32) DEFAULT null COMMENT '名称',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='组';

insert into `group` value ('1','未分组');

DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`
(
    `id`   varchar(32) not null COMMENT 'ID',
    `title` varchar(32) DEFAULT null COMMENT '标题',
    `context` varchar(2000) DEFAULT null COMMENT '正文',
    `audio` varchar(500) DEFAULT null COMMENT '视频或文件的url',
    `isVideo` int DEFAULT null COMMENT '区分视频和文件',
    `publisher_id` varchar(32) DEFAULT null COMMENT '任务发布者id',
    `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='任务';

DROP TABLE IF EXISTS `task_user`;
CREATE TABLE `task_user`
(
    `task_id`   varchar(32) not null COMMENT 'ID',
    `user_id` varchar(32) DEFAULT null COMMENT '任务接受者id',
    `status` int DEFAULT 0 COMMENT '任务完成情况，0-未完成，1-完成未打分，2-完成已打分'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='任务用户关联表';

SET FOREIGN_KEY_CHECKS = 1;