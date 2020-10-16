create table user
(
    id       bigint       not null auto_increment,
    active   bit          not null,
    email    varchar(255) not null unique ,
    name     varchar(255) not null unique,
    password varchar(255) not null,
    role     varchar(255) not null,
    primary key (id)
);
