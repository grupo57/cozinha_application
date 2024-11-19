CREATE DATABASE IF NOT EXISTS mydb;
USE mydb;

CREATE TABLE IF NOT EXISTS atendimento
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    id_pedido          BIGINT                NOT NULL,
    codigo             VARCHAR(100)          NOT NULL,
    data_criacao       datetime              NOT NULL,
    ultima_modificacao datetime              NOT NULL,
    situacao           VARCHAR(30)           NOT NULL,
    CONSTRAINT pk_atendimento PRIMARY KEY (id)
)  ENGINE=InnoDB;
