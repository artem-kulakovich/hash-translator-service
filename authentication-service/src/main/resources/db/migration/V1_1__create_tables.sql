CREATE TABLE public."user"
(
    id        bigserial              NOT NULL,
    email     character varying(512) NOT NULL,
    password  character varying(512) NOT NULL,
    create_at timestamp with time zone,
    role_id   integer,
    PRIMARY KEY (id),
    UNIQUE (email)
);

CREATE TABLE public."role"
(
    id        bigserial              NOT NULL,
    name      character varying(100) NOT NULL,
    create_at timestamp with time zone,
    PRIMARY KEY (id),
    UNIQUE (name)
);

