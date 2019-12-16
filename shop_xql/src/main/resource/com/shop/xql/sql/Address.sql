create table Address
(
   add_id               varchar(36) not null,
   add_userid           varchar(36) not null,
   add_name             varchar(200),
   add_address          varchar(400) not null,
   add_Postalcode       varchar(10) not null,
   add_phone            varchar(11) not null,
   primary key (add_id)
);