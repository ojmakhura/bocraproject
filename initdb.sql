CREATE DATABASE keycloak;

CREATE SEQUENCE IF NOT EXISTS public.access_point_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.access_point_seq
    OWNER TO postgres;

CREATE SEQUENCE IF NOT EXISTS public.access_point_type_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.access_point_type_seq
    OWNER TO postgres;


CREATE SEQUENCE IF NOT EXISTS public.authorisation_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.authorisation_seq
    OWNER TO postgres;

-- Table: public.access_point_type

-- DROP TABLE IF EXISTS public.access_point_type;

CREATE TABLE IF NOT EXISTS public.access_point_type
(
    id bigint NOT NULL,
    code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    description character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT access_point_type_pkey PRIMARY KEY (id),
    CONSTRAINT uk_r9fvqnr7i5q8x1d62978vugr5 UNIQUE (code),
    CONSTRAINT uk_sup08n3htnt1dqj7eu93wbiom UNIQUE (name)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.access_point_type
    OWNER to postgres;

-- Table: public.access_point

-- DROP TABLE IF EXISTS public.access_point;

CREATE TABLE IF NOT EXISTS public.access_point
(
    id bigint NOT NULL,
    created_by character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_date timestamp without time zone NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    updated_by character varying(255) COLLATE pg_catalog."default",
    updated_date timestamp without time zone,
    url character varying(255) COLLATE pg_catalog."default" NOT NULL,
    access_point_type_fk bigint NOT NULL,
    CONSTRAINT access_point_pkey PRIMARY KEY (id),
    CONSTRAINT uk_jb9t4beltchn2pq8212t4qcw5 UNIQUE (url),
    CONSTRAINT uk_l2rrepj61p2jnir0g03anq7mk UNIQUE (name),
    CONSTRAINT fkf3nracsfjln5ayag7o7e028pq FOREIGN KEY (access_point_type_fk)
        REFERENCES public.access_point_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.access_point
    OWNER to postgres;

-- Table: public.authorisation

-- DROP TABLE IF EXISTS public.authorisation;

CREATE TABLE IF NOT EXISTS public.authorisation
(
    id bigint NOT NULL,
    created_by character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_date timestamp without time zone NOT NULL,
    updated_by character varying(255) COLLATE pg_catalog."default",
    updated_date timestamp without time zone,
    access_point_fk bigint NOT NULL,
    CONSTRAINT authorisation_pkey PRIMARY KEY (id),
    CONSTRAINT uk_h9uuaoc214q4c0ucp9bl06mi6 UNIQUE (access_point_fk),
    CONSTRAINT fkd5pyeyc0ftn7ipyiwipmckthc FOREIGN KEY (access_point_fk)
        REFERENCES public.access_point (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.authorisation
    OWNER to postgres;

-- Table: public.authorisation_roles

-- DROP TABLE IF EXISTS public.authorisation_roles;

CREATE TABLE IF NOT EXISTS public.authorisation_roles
(
    authorisation_id bigint NOT NULL,
    roles character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT fk7dvqcuacmah8h1faq9xx847if FOREIGN KEY (authorisation_id)
        REFERENCES public.authorisation (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

-- create database keycloak;