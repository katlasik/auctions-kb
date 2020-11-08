create table auction_log
(
    id                          bigint not null auto_increment,
    auction_id                  bigint not null,
    user_id                     bigint not null,
    oldPrice                    decimal(10,2)not null,
    newPrice                    decimal(10,2) not null,
    updated_at                  datetime default current_timestamp,
    foreign key (user_id)       references user(id),
    foreign key (auction_id)    references auction(id),
    primary key (id)
);