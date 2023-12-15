create table users
(
    id       uuid DEFAULT uuid_generate_v4() primary key not null,
    username varchar(128)                                not null,
    password varchar(128)                                not null
);

create table users_roles
(
    user_id uuid not null,
    role_id uuid not null,
    CONSTRAINT fk_user_userroles foreign key (user_id) references users (id),
    CONSTRAINT fk_role_userroles foreign key (role_id) references roles (id)
)