DROP TABLE IF EXISTS news_authors;
DROP TABLE IF EXISTS news_tags;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS news;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

DROP SEQUENCE IF EXISTS authors_id_seq;
DROP SEQUENCE IF EXISTS tags_id_seq;
DROP SEQUENCE IF EXISTS news_id_seq;
DROP SEQUENCE IF EXISTS users_id_seq;
DROP TRIGGER IF EXISTS news_update_modification_date_trigger ON news;
DROP FUNCTION IF EXISTS news_update_modification_date;

DROP TRIGGER IF EXISTS news_update_modification_date_trigger ON news_authors;
DROP FUNCTION IF EXISTS author_delete_related_news;


CREATE TABLE public.authors
(
    id serial NOT NULL,
    name character varying(30) COLLATE pg_catalog."default" NOT NULL,
    surname character varying(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT authors_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.authors
    OWNER to postgres;


CREATE TABLE public.news
(
    id serial NOT NULL,
    title character varying(30) COLLATE pg_catalog."default" NOT NULL,
    short_text character varying(100) COLLATE pg_catalog."default" NOT NULL,
    full_text character varying(2000) COLLATE pg_catalog."default" NOT NULL,
    creation_date timestamp without time zone NOT NULL DEFAULT now(),
    modification_date timestamp without time zone NOT NULL DEFAULT now(),
    CONSTRAINT news_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.news
    OWNER to postgres;


CREATE TABLE public.news_authors
(
    news_id bigint NOT NULL,
    author_id bigint NOT NULL,
    CONSTRAINT news_authors_pkey PRIMARY KEY (news_id),
    CONSTRAINT authors_id_foreign_key_constraint FOREIGN KEY (author_id)
        REFERENCES public.authors (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE ,
    CONSTRAINT news_id_foreign_key_constraint FOREIGN KEY (news_id)
        REFERENCES public.news (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.news_authors
    OWNER to postgres;



CREATE TABLE public.tags
(
    id serial NOT NULL,
    name character varying(30) COLLATE pg_catalog."default" NOT NULL UNIQUE,
    CONSTRAINT tags_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.tags
    OWNER to postgres;



CREATE TABLE public.news_tags
(
    news_id bigint NOT NULL,
    tag_id bigint NOT NULL,
    CONSTRAINT news_tags_pkey PRIMARY KEY (news_id, tag_id),
    CONSTRAINT news_id_foreign_key_constraint FOREIGN KEY (news_id)
        REFERENCES public.news (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE ,
    CONSTRAINT tag_id_foreign_key_constraint FOREIGN KEY (tag_id)
        REFERENCES public.tags (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.news_tags
    OWNER to postgres;


CREATE TABLE public.users
(
    id serial NOT NULL,
    name character varying(20) COLLATE pg_catalog."default" NOT NULL,
    surname character varying(20) COLLATE pg_catalog."default" NOT NULL,
    login character varying(30) COLLATE pg_catalog."default" NOT NULL,
    password character varying(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to postgres;



CREATE TABLE public.roles
(
    user_id bigint NOT NULL,
    role_name character varying(30) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (user_id),
    CONSTRAINT user_id_foreign_key_constraint FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.roles
    OWNER to postgres;


-- Triggers

CREATE FUNCTION news_update_modification_date () RETURNS trigger AS '
    BEGIN
        IF
                    OLD.title!=NEW.title OR OLD.short_text!=NEW.short_text OR OLD.full_text!=NEW.full_text THEN
            UPDATE news SET modification_date = now() WHERE id = NEW.id;
        END IF;
        return NEW;
    END;
' LANGUAGE plpgsql;

CREATE FUNCTION author_delete_related_news () RETURNS trigger AS '
    BEGIN
        DELETE FROM news WHERE news.id = OLD.news_id;
        return OLD;
    END;
' LANGUAGE plpgsql;


CREATE TRIGGER news_update_modification_date_trigger
	AFTER UPDATE ON news FOR EACH ROW
	EXECUTE PROCEDURE news_update_modification_date();

CREATE TRIGGER author_delete_related_news_trigger
    AFTER DELETE ON news_authors FOR EACH ROW
EXECUTE PROCEDURE author_delete_related_news();

INSERT INTO authors(name, surname) VALUES ('Dima', 'Ford'),
    ('Vasya', 'Pupkin'),
    ('Sova', 'Sovna'),
    ('Artem', 'Ford'),
    ('Vasya', 'Asin'),
    ('Nikita', 'Semenov'),
    ('Dima', 'Ford'),
    ('Dima', 'Jackson');

INSERT INTO tags(name) VALUES ('History'),
    ('Science'),
    ('Comedy'),
    ('Nature'),
    ('Art'),
    ('My'),
    ('Dogs'),
    ('Cats');

INSERT INTO news(title, short_text, full_text, creation_date) VALUES ('Robokop', 'Short text 1', 'Full text 1', '2020-01-27 02:50:39'),
    ('WoRk', 'Short text 2', 'Full text 2', '2020-01-04 00:50:39'),
    ('work', 'Short text 3', 'Full text 3', '2020-01-02 00:50:39'),
    ('News', 'Short text 4', 'Full text 4', '2020-01-10 00:50:39'),
    ('Boring', 'Short text 5', 'Full text 5', '2020-01-13 02:50:39'),
    ('Bomb shel', 'Short text 6', 'Full text 6', '2020-01-14 02:50:39'),
    ('UFO', 'Short text 7', 'Full text 7', '2020-01-17 02:50:39'),
    ('Warning', 'Short text 8', 'Full text 8', '2020-01-15 01:50:39'),
    ('JAVA core', 'Short text 9', 'Full text 9', '2020-01-21 02:50:39'),
    ('Spring', 'Short text 10', 'Full text 10', '2020-01-23 02:50:39'),
    ('Postgresql', 'Short text 11', 'Full text 11', '2020-01-01 00:49:39');

INSERT INTO news_tags(news_id, tag_id) VALUES
    (1, 2), (1, 3), (1, 6),
    (2, 3), (2, 4), (2, 8), (2, 2),
    (3, 3), (3, 5), (3, 8),
    (4, 1), (4, 3), (4, 8), (4, 2), (4, 7),
    (5, 1), (5, 3), (5, 5),
    (6, 2), (6, 6), (6, 7),
    (7, 1), (7, 2), (7, 3),
    (8, 5), (8, 6),
    (9, 2), (9, 5), (9, 6),
    (10, 2), (10, 5), (10, 6),
    (11, 1), (11, 2), (11, 3), (11, 7), (11, 8);

INSERT INTO news_authors(news_id, author_id) VALUES
    (1, 1),
    (2, 7),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 8),
    (7, 4),
    (8, 7),
    (9, 8),
    (10, 1),
    (11, 1);