-- Tabela USUARIO
CREATE TABLE T_CN_USUARIO (
                              ID_USUARIO INT IDENTITY(1,1) PRIMARY KEY,
                              NOME NVARCHAR(255) NOT NULL,
                              EMAIL NVARCHAR(255) NOT NULL UNIQUE,
                              SENHA NVARCHAR(50) NOT NULL,
                              ROLE NVARCHAR(50) NOT NULL DEFAULT 'USER'
);

-- Tabela TRANSPORTE
CREATE TABLE T_CN_TRANSPORTE (
                                 ID_TRANSPORTE INT IDENTITY(1,1) PRIMARY KEY,
                                 ID_USUARIO INT NOT NULL,
                                 TIPO_TRANSPORTE NVARCHAR(255) NOT NULL,
                                 DISTANCIA_KM DECIMAL(10,2) NOT NULL,
                                 DT_USO DATE NOT NULL,
                                 EMISSAO_CALCULADA DECIMAL(10,4) NOT NULL,
                                 EMISSAO_PERMITIDA_ISO DECIMAL(10,4) NOT NULL DEFAULT 2.5,
                                 CONFORME_ISO CHAR(1),
                                 CONSTRAINT FK_TRANSPORTE_ID_USUARIO FOREIGN KEY (ID_USUARIO) REFERENCES T_CN_USUARIO(ID_USUARIO)
);

-- Tabela ITEM ELETRICO
CREATE TABLE T_CN_ITEM_ELETRICO (
                                    ID_ITEM INT IDENTITY(1,1) PRIMARY KEY,
                                    ID_USUARIO INT NOT NULL,
                                    NOME_ITEM NVARCHAR(255) NOT NULL,
                                    CONSUMO_KWH DECIMAL(10,2) NOT NULL,
                                    DT_USO DATE NOT NULL,
                                    EMISSAO_CALCULADA DECIMAL(10,4) NOT NULL,
                                    CONSTRAINT FK_ITEM_ID_USUARIO FOREIGN KEY (ID_USUARIO) REFERENCES T_CN_USUARIO(ID_USUARIO)
);
