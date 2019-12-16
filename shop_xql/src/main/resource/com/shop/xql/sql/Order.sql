create table shop_Order
(
   order_id             varchar(36) not null,
   user_id              varchar(36),
   sum_sumid            varchar(64),
   sum_soopid           varchar(36) not null,
   sum_shopname         varchar(36) not null,
   sum_price          numeric(8,2) not null,
   sum_num              numeric(10,0) not null,
   sum_address          varchar(500),
   sum_status           varchar(100),
   primary key (order_id)
);