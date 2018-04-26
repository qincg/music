create database music;
use music;
create table page(
  id int auto_increment primary key ,
  url varchar(200) not null,
  title varchar(50),
  status int unsigned default 0,
  type int unsigned default 0
);
create table song(
  id int auto_increment primary key ,
  song_url varchar(200) not null ,
  song_name varchar(20),
  songer varchar(20),
  comment_count bigint
);

create table ip(
  id int auto_increment primary key ,
  ip varchar(20),
  port int
);