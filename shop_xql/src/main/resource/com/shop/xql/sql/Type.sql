/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/12/8 9:20:28                            */
/*==============================================================*/


drop table if exists type;

/*==============================================================*/
/* Table: type                                                  */
/*==============================================================*/
create table Type
(
   type_id              varchar(36) not null,
   type_name            varchar(36),
   type_status          varchar(36),
   type_time            date,
   primary key (type_id)
);

