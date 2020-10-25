create table auction
(
    id              bigint not null auto_increment,
    title           varchar(255) not null,
    description     varchar(255) not null,
    primary_price   decimal not null,
    price           decimal not null,
    status          varchar(255) not null,
    user_id         bigint not null,
    foreign key (user_id) references user(id),
    primary key (id)
);