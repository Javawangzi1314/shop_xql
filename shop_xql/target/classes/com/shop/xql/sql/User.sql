/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/12/10 10:43:35                          */
/*==============================================================*/


drop table if exists User;

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   user_id              varchar(36) not null,
   user_path            varchar(300),
   user_name            varchar(300),
   user_account         varchar(300),
   user_password        varchar(300),
   salt                 varchar(100),
   user_phone           varchar(11),
   user_time            date,
   user_status          varchar(100),
   primary key (user_id)
);

