CREATE
    EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE instructors
(
    id    uuid DEFAULT uuid_generate_v4() primary key not null,
    name  varchar(256)                                not null,
    email varchar(256)
);