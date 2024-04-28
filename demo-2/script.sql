CREATE TABLE AUTH_USER (
    id serial,
    username varchar(100),
    password varchar(255),
    created_date timestamp with time zone,
    updated_date timestamp with time zone,

    PRIMARY KEY (id)
);

CREATE TABLE ROLE (
    id serial,
    title varchar(100),

    PRIMARY KEY (id)
);

CREATE TABLE ROLE_USER (
    user_id bigint,
    role_id bigint,

    FOREIGN KEY (user_id) REFERENCES AUTH_USER(id),
    FOREIGN KEY (role_id) REFERENCES ROLE(id)
)