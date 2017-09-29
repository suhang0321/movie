SET MODE MYSQL;
INSERT INTO `user` (`username`, `password`, `salt`)
VALUES ('hang.su', 'hashed password', 'private salt');
INSERT INTO `user` (`username`, `password`, `salt`)
VALUES ('tomhon', 'hashed password', 'private salt');
INSERT INTO `movie` (`name`, `description`)
VALUES ('A Chinese Odyssey Part One: Pandoras Box', '大话西游之月光宝盒');
INSERT INTO `movie` (`name`, `description`)
VALUES ('A Chinese Odyssey Part Two: Cinderella', '大话西游之大圣娶亲');
INSERT INTO `movie` (`name`, `description`)
VALUES ('A Chain of Short Stories about Their Distance', '秒速五厘米');
INSERT INTO `favorite` (`user_id`, `movie_id`) VALUES (1, 1);
INSERT INTO `favorite` (`user_id`, `movie_id`) VALUES (1, 2);
INSERT INTO `favorite` (`user_id`, `movie_id`) VALUES (2, 1);
INSERT INTO `favorite` (`user_id`, `movie_id`) VALUES (2, 2);
