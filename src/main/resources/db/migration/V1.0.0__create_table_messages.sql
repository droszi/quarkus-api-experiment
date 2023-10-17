CREATE TABLE messages (
    id serial,
    user_id integer NOT NULL,
    message text,
    title character varying(255)
);
