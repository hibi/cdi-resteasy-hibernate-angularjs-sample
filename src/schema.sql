CREATE TABLE airport (
  id serial primary key,
  code character varying NOT NULL DEFAULT '',
  name character varying NOT NULL DEFAULT '',
  country character varying DEFAULT NULL
);