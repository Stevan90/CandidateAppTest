DROP TABLE IF EXISTS public.candidates;

CREATE TABLE IF NOT EXISTS public.candidates
(
    id serial PRIMARY KEY,
    first_name varchar(64) NOT NULL,
    last_name varchar(64) NOT NULL,
    jmbg varchar(13) UNIQUE NOT NULL,
    birth_year smallint,
    email varchar(128) UNIQUE NOT NULL,
    phone varchar(16) UNIQUE,
    note varchar(1024),
	employed boolean,
    modification_date date NOT NULL DEFAULT CURRENT_DATE
);