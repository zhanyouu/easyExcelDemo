USE `easy_excel`;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `excel_info`
(
    `id`          INT          NOT NULL AUTO_INCREMENT COMMENT 'Id',
    `wx_id`       VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '微信id',
    `wx_nickname` VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '微信昵称',
    `roles`       VARCHAR(100) NOT NULL DEFAULT '' COMMENT '角色',
    `avatar_url`  VARCHAR(255) NOT NULL DEFAULT '' COMMENT '头像地址',
    `create_time` DATETIME     NOT NULL COMMENT '创建时间',
    `update_time` DATETIME     NOT NULL COMMENT '修改时间',
    `bonus`       INT          NOT NULL DEFAULT 300 COMMENT '积分',
    PRIMARY KEY (`id`)
)
    COMMENT = '分享';
