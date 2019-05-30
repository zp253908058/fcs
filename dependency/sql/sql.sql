# 用户id管理表，用于提供user_id以及记录该user_id是否可用
create table if not exists user_id
(
    id          INT           NOT NULL AUTO_INCREMENT comment '表id',
    user_id     bigint unique not null comment '用户id，全局唯一',
    create_date TIMESTAMP default CURRENT_TIMESTAMP comment '数据创建时间',
    id_state    int       default 0 comment '状态，标记是否可用',
    PRIMARY KEY (id) comment '将id设置为主键'
);

# 用户信息表
create table if not exists user_info
(
    id               INT           NOT NULL AUTO_INCREMENT comment '表id',
    user_id          bigint unique not null comment '用户id，user_id.user_id，该表中唯一',
    nick_name        varchar(16) default '' comment '昵称',
    avatar           varchar(16) default '' comment '头像',
    sex              int(1)      default 0 comment '性别，0未知1男2女',
    create_date      TIMESTAMP   default CURRENT_TIMESTAMP comment '数据创建时间',
    last_modify_date TIMESTAMP   default CURRENT_TIMESTAMP comment '数据修改时间',
    PRIMARY KEY (id) comment '将id设置为主键'
);

# 用户认证表
create table if not exists user_auth
(
    id             INT                 NOT NULL AUTO_INCREMENT comment '表id',
    user_id        bigint              not null comment '用户id，user_id.user_id',
    identity_type  varchar(32)         not null comment '登录类型 (手机号/邮箱/用户名) 或第三方应用名称 (微信 , 微博等)',
    identifier     varchar(32) unicode not null comment '标识 (手机号/邮箱/用户名或第三方应用的唯一标识)',
    credential     text not null comment '密码凭证 (站内的保存密码 , 站外的不保存或保存 token)',
    create_date    TIMESTAMP default CURRENT_TIMESTAMP comment '数据创建时间',
    last_auth_date TIMESTAMP default CURRENT_TIMESTAMP comment '随后一次认证时间',
    PRIMARY KEY (id) comment '表id'
);