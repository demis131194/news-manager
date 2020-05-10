CREATE OR REPLACE FUNCTION func(integer) RETURNS TEXT AS $$
DECLARE str varchar;
BEGIN
    SELECT tag_name FROM full_news_view WHERE author_id=$1 GROUP BY tag_name ORDER BY COUNT(tag_name) DESC LIMIT 1;
    RETURN str;
END;$$
    LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION concat_tags_of_news(news_id integer, separator character) RETURNS TEXT AS $$
DECLARE str varchar;
    BEGIN
        SELECT string_agg(ta.name, separator) FROM news_tags nt JOIN tags ta ON nt.tag_id=ta.id WHERE nt.news_id=concat_tags_of_news.news_id INTO str;
        RETURN str;
    END;$$
LANGUAGE plpgsql;

CREATE OR REPLACE VIEW author_news_info_view AS
SELECT full_news_view.author_id,
       full_news_view.author_name,
       full_news_view.author_surname,
       count(DISTINCT full_news_view.news_id) AS "number of news",
       func(full_news_view.author_id) AS "most popular tag"
FROM full_news_view
GROUP BY full_news_view.author_id, full_news_view.author_name, full_news_view.author_surname;

CREATE OR REPLACE VIEW news_authors_view AS
SELECT n.id AS news_id,
       n.title,
       n.short_text,
       n.full_text,
       n.creation_date,
       n.modification_date,
       au.id AS author_id,
       au.name AS author_name,
       au.surname AS author_surname
FROM news n
         JOIN news_authors na ON n.id = na.news_id
         JOIN authors au ON na.author_id = au.id;

CREATE OR REPLACE VIEW full_news_view AS
SELECT n.id AS news_id,
       n.title,
       n.short_text,
       n.full_text,
       n.creation_date,
       n.modification_date,
       au.id AS author_id,
       au.name AS author_name,
       au.surname AS author_surname,
       ta.id AS tag_id,
       ta.name AS tag_name
FROM news n
         JOIN news_tags nt ON n.id = nt.news_id
         JOIN tags ta ON ta.id = nt.tag_id
         JOIN news_authors na ON n.id = na.news_id
         JOIN authors au ON na.author_id = au.id;

CREATE OR REPLACE VIEW authors_characters_more AS
SELECT news_authors_view.author_name,
       news_authors_view.author_surname,
       sum(length(news_authors_view.full_text::text)) AS sum,
       avg(length(news_authors_view.full_text::text)) AS avg
FROM news_authors_view
GROUP BY news_authors_view.author_name, news_authors_view.author_surname
HAVING sum(length(news_authors_view.full_text::text));

-- 5
select id, name,
       case
           when lag(id) over (order by id,1) is null then lead(id, cast((select count(*) - 1 from authors) as int)) over (order by id)
           when lag(id) over (order by id,1) is not null then lag(id) over (order by id,1)
           end
from authors order by random();


create table LOGGING (
    id serial NOT NULL,
    insert_date timestamp without time zone NOT NULL,
    tabl_name text NOT NULL,
    description text NOT NULL,
    CONSTRAINT logg_pkey PRIMARY KEY (id)
);

CREATE EXTENSION hstore;

create or replace function logging() returns trigger as
$$
DECLARE
    res text;
    k text;
    v text;
BEGIN
    FOR k,v IN select key,value from each(hstore(NEW)) LOOP
            res := CONCAT(res,k,'=',v,';');
        END LOOP;
    INSERT INTO logging (insert_date, tabl_name, description) values (now(), TG_TABLE_NAME, res);
    RETURN null;
END;
$$
    LANGUAGE PLPGSQL;

CREATE TRIGGER tags_insert_logging_trigger
    AFTER INSERT ON tags FOR EACH ROW
EXECUTE PROCEDURE logging();

CREATE TRIGGER authors_insert_logging_trigger
    AFTER INSERT ON authors FOR EACH ROW
EXECUTE PROCEDURE logging();

CREATE TRIGGER news_insert_logging_trigger
    AFTER INSERT ON news FOR EACH ROW
EXECUTE PROCEDURE logging();

CREATE TRIGGER news_tags_insert_logging_trigger
    AFTER INSERT ON news_tags FOR EACH ROW
EXECUTE PROCEDURE logging();

CREATE TRIGGER news_authors_insert_logging_trigger
    AFTER INSERT ON news_authors FOR EACH ROW
EXECUTE PROCEDURE logging();

CREATE TRIGGER users_insert_logging_trigger
    AFTER INSERT ON users FOR EACH ROW
EXECUTE PROCEDURE logging();



