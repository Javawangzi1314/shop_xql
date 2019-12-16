/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/12/8 17:28:35                           */
/*==============================================================*/


drop table if exists Shop;

/*==============================================================*/
/* Table: Shop                                                  */
/*==============================================================*/
create table Shop
(
   shop_id              varchar(36) not null,
   shop_name            varchar(300),
   shop_price           double(10,2),
   shop_type            varchar(300),
   shop_count           int(9),
   shop_status          varchar(300),
   shop_dis             text,
   shop_path            varchar(300),
   primary key (shop_id)
);

