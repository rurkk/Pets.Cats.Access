/*
    User table
*/
create table users
(
    id       uuid not null
        primary key,
    username text not null,
    password text not null
);

/*
    Role table
*/
create table roles
(
    id   serial not null
        primary key,
    name text   not null
);

/*
    User-Role many-to-many table
*/
create table users_roles
(
    user_id uuid    not null
        references users on delete cascade,
    role_id integer not null
        references roles on delete cascade
);

/*
 Permission table
 */
create table permissions
(
    id   serial not null
        primary key,
    name text   not null
);

/*
 Many-to-many roles-permissions table
 */
create table roles_permissions
(
    role_id       integer not null
        references roles on delete cascade,
    permission_id integer not null
        references permissions on delete cascade
);