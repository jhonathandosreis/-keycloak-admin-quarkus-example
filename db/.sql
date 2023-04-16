CREATE TABLE USERS (
                       id bigserial not null primary key,
                       nome varchar(100) not null,
                       sobre_nome varchar(100) not null,
                       idade integer not null
);