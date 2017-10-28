CREATE TABLE filme
(
  id uuid NOT NULL,
  duracao bigint,
  fim_exibicao date,
  inicio_exibicao date,
  nome character varying(100) not null,
  sinopse text,
  CONSTRAINT filme_pkey PRIMARY KEY (id)
)