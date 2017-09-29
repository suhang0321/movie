SET MODE MYSQL;
-- --------------------------------------------------------

CREATE TABLE `user` (
  `id`       BIGINT(11) UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增id',
  `username` VARCHAR(50)         NOT NULL
  COMMENT '用户名',
  `password` CHAR(64)            NOT NULL
  COMMENT '加密后的密码',
  `salt`     CHAR(32)            NOT NULL
  COMMENT '盐',
  `status`   TINYINT(4)          NOT NULL DEFAULT 0
  COMMENT '状态: 0:已生效 4:已删除',
  `ctime`    TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `mtime`    TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_username` (`username`)
)
  COMMENT = '用户表';

CREATE TABLE `movie` (
  `id`          BIGINT(11) UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '自增id',
  `name`        VARCHAR(50)         NOT NULL
  COMMENT '名称',
  `description` VARCHAR(255)
  COMMENT '描述',
  `status`      TINYINT(4)          NOT NULL DEFAULT 0
  COMMENT '状态: 0:已生效 4:已删除',
  `ctime`       TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `mtime`       TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '修改时间',
  PRIMARY KEY (`id`)
)
  COMMENT = '电影表';

CREATE TABLE `favorite` (
  `user_id`  BIGINT(11) UNSIGNED NOT NULL
  COMMENT '用户id',
  `movie_id` BIGINT(11) UNSIGNED NOT NULL
  COMMENT '电影id',
  `status`   TINYINT(4)          NOT NULL DEFAULT 0
  COMMENT '状态: 0:已生效 1:已删除',
  `ctime`    TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  `mtime`    TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '修改时间',
  PRIMARY KEY (`user_id`, `movie_id`),
  KEY `idx_favorite_user_id`(`user_id`),
  KEY `idx_favorite_movie_id`(`movie_id`)
)
  COMMENT = '收藏表';
