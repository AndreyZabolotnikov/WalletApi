create sequence hibernate_sequence start with 1 increment by 1;

create table wallet
(
    uuid  bigint not null,
    balance float(53),
    primary key (uuid)
);