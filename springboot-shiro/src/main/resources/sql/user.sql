DROP TABLE IF EXISTS `user`;

create table `user`
(
    `id`   int(11) not null AUTO_INCREMENT,
    `name` varchar(15) not null,
    `pwd`  varchar(15) not null,
    `perms` varchar (100),
    primary key (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
-- AUTO_INCREMENT=2 从2开始

insert into `user` (name, pwd)
values ('root', '123456');

insert into `user` (name, pwd)
values ('hello', '123456');