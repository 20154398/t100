SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`         varchar(32) not null COMMENT 'ID',
    `code` varchar(32) not null COMMENT '微信code',
    `name` varchar(32) DEFAULT null COMMENT '备注',
    `nickname` varchar(32) not null COMMENT '名称',
    `power` int(1) default 1 comment '权限',
    `group_id` varchar(32) DEFAULT '1' COMMENT '组id',
    `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `integral` int default 0 comment '积分',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT ='用户';

DROP TABLE IF EXISTS `grouping`;
CREATE TABLE `grouping`
(
    `id`   varchar(32) not null COMMENT 'ID',
    `name` varchar(32) DEFAULT null COMMENT '名称',
    PRIMARY KEY (`id`) USING BTREE
)COMMENT ='组';

insert into `grouping` value ('1','未分组的人');

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
) COMMENT ='任务';

DROP TABLE IF EXISTS `task_user`;
CREATE TABLE `task_user`
(
    `task_id`   varchar(32) not null COMMENT 'ID',
    `user_id` varchar(32) not null COMMENT '任务接受者id',
    `status` int DEFAULT 0 COMMENT '任务完成情况，0-未完成，1-完成未打分，2-完成已打分'
)  COMMENT ='任务用户关联表';

DROP TABLE IF EXISTS `task_integral`;
CREATE TABLE `task_integral`
(
    `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `user_id` varchar(32) not null COMMENT '任务完成者id',
    `task_id` varchar(32) not null COMMENT '任务id',
    `integral` int DEFAULT 0 COMMENT '积分流水'
) COMMENT ='任务积分流水表';

DROP TABLE IF EXISTS `commodity_integral`;
CREATE TABLE `commodity_integral`
(
    `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `user_id` varchar(32) not null COMMENT '商品兑换者id',
    `commodity_id` varchar(32) not null COMMENT '商品id',
    `integral` int DEFAULT 0 COMMENT '积分流水'
) COMMENT ='任务积分流水表';

DROP TABLE IF EXISTS `commodity_image`;
CREATE TABLE `commodity_image`
(
    `id` varchar(32) not null COMMENT 'ID',
    `image_url` varchar(500) DEFAULT null COMMENT '视频或文件的url',
    `commodity_id` varchar(32) DEFAULT null COMMENT '商品ID',
    PRIMARY KEY (`id`) USING BTREE
) COMMENT ='商品图片对应表';

DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity`
(
    `id` varchar(32) not null COMMENT 'ID',
    `integral` int not null COMMENT '积分',
    `name` varchar(32) DEFAULT null COMMENT '商品名称',
    `remain` int not null COMMENT '剩余数量',
    `publisher_id` varchar(32) not null COMMENT '商品上传者',
    `description` varchar(500) not null COMMENT '描述',
    `isSale` int(1) DEFAULT 1 COMMENT '描述',
    PRIMARY KEY (`id`) USING BTREE
) COMMENT ='商品表';

SET FOREIGN_KEY_CHECKS = 1;