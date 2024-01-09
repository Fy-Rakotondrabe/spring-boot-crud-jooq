CREATE TABLE user_role
(
  id        integer primary key,
  name      varchar(255)
);

INSERT INTO user_role(id, name) VALUES (1, 'admin');
INSERT INTO user_role(id, name) VALUES (2, 'user');

CREATE TABLE users
(
    id             integer primary key,
    name           varchar(255) not null,
    email          varchar(255) not null,
    role_id integer REFERENCES user_role (id),
    created_at     timestamp with time zone default CURRENT_TIMESTAMP,
    CONSTRAINT user_email_unique UNIQUE (email)
);