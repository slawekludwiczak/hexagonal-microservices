create table product (
    id uuid primary key,
    name varchar(200),
    description varchar(1000),
    added_by uuid not null
);
