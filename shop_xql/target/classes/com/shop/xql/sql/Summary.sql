create table Summary
(
   sum_id               varchar(64) not null,
   sum_money            numeric(10,2) not null,
   sum_status           varchar(24) not null,
   sum_userid           varchar(36) not null,
   sum_name             varchar(200),
   sum_addid            varchar(36) not null,
   sum_address          varchar(500),
   sum_creatdate        date not null,
   primary key (sum_id)
);