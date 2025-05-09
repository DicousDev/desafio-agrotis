CREATE TABLE LABORATORIO(
    ID BIGSERIAL,
    NOME VARCHAR(255) NOT NULL,
    PRIMARY KEY(ID)
);

CREATE TABLE PROPRIEDADE(
    ID BIGSERIAL,
    NOME VARCHAR(255) NOT NULL,
    PRIMARY KEY(ID)
);

CREATE TABLE PESSOA(
    ID BIGSERIAL,
    NOME VARCHAR(255) NOT NULL,
    DATA_INICIAL TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    DATA_FINAL TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    OBSERVACOES VARCHAR(512),
    LABORATORIO_ID BIGINT,
    PROPRIEDADE_ID BIGINT,
    PRIMARY KEY(ID),
    FOREIGN KEY(LABORATORIO_ID) REFERENCES LABORATORIO(ID),
    FOREIGN KEY(PROPRIEDADE_ID) REFERENCES PROPRIEDADE(ID)
);