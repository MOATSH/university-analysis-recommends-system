create database UARS;
use UARS;
create table test(
                     id int primary key auto_increment,
                     username varchar(20) not null unique,
                     password varchar(32) not null
);
insert into test(username, password) values('moatsh', '123456');
insert into test(username, password) values('mortsh', '123456');


create table univ_info(
                           univ_id int primary key,
                           univ_name varchar(50) not null unique,
                           univ_rank varchar(10) not null,
                           prov_name varchar(30) not null,
                           univ_info varchar(2000) not null,
                           sex_ratio double,
                           MDP_amount int,
                           key_disp_amount int,
                           key_lab_amount int
);
create table univ_enrollment(
                                univ_id int references univ_info,
                                prov_name varchar(30) not null,
                                year int not null,
                                major_name varchar(1000) not null,
                                lowest_rank int,
                                arts_science varchar(10),
                                lowest_score int,
                                enroll_amount int
);
create table grad_quality(
                              univ_id int references univ_info,
                              univ_name varchar(50) not null,
                              year int not null,
                              grad_amount int,
                              prog_rate double,
                              abroad_rate double,
                              employ_rate double,
                              avarage_salary double
);
create table user(
                     id int primary key auto_increment,
                     username varchar(10) not null unique,
                     password varchar(15) not null,
                     province varchar(30)
);
create table user_favor(
    user_id int references user,
    favor_univ_id int references univ_info,
    primary key (user_id, favor_univ_id)
);