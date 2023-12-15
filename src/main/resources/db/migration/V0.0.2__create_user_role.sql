create table roles
(
    id   uuid DEFAULT uuid_generate_v4() primary key not null,
    name varchar(128)                                not null
);