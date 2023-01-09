-------ORACLE ------
CREATE TABLE TBL_CLIENTE(
    ID_CLIENTE NUMERIC(6) NOT NULL PRIMARY KEY,
    RAZON_SOCIAL VARCHAR2(220) NOT NULL UNIQUE,
    RUC CHAR(11) NOT NULL UNIQUE,
    TELEFONO VARCHAR(60) NULL
);

CREATE SEQUENCE SEQ_CLIENTE;

INSERT INTO TBL_CLIENTE(ID_CLIENTE,RAZON_SOCIAL,RUC,TELEFONO) 
VALUES(SEQ_CLIENTE.NEXTVAL,'TELEFONICA DEL PERU SAA','20100017491','(01) 315 - 0730');
INSERT INTO TBL_CLIENTE(ID_CLIENTE,RAZON_SOCIAL,RUC,TELEFONO) 
VALUES(SEQ_CLIENTE.NEXTVAL,'América Móvil Perú SAC','20467534026','(01) 315 - 0733');

INSERT INTO TBL_CLIENTE(ID_CLIENTE,RAZON_SOCIAL,RUC,TELEFONO) 
VALUES(SEQ_CLIENTE.NEXTVAL,'Entel Móvil Perú SAC','20467534030','(01) 315 - 0737');

COMMIT;
≈

ALTER TABLE TBL_CLIENTE
ADD ESTADO CHAR(1) DEFAULT '1';

---------- MYSQL ------------ NO VALID
CREATE TABLE TBL_CLIENTE(
    ID_CLIENTE int(11)  primary key NOT NULL AUTO_INCREMENT,
    RAZON_SOCIAL VARCHAR(220) NOT NULL UNIQUE,
    RUC CHAR(11) NOT NULL UNIQUE,
    TELEFONO VARCHAR(60) NULL
);


INSERT INTO TBL_CLIENTE(RAZON_SOCIAL,RUC,TELEFONO)
VALUES('TELEFONICA DEL PERU SAA','20100017491','(01) 315 - 0730');
INSERT INTO TBL_CLIENTE(RAZON_SOCIAL,RUC,TELEFONO)
VALUES('América Móvil Perú SAC','20467534026','(01) 315 - 0733');

INSERT INTO TBL_CLIENTE(RAZON_SOCIAL,RUC,TELEFONO)
VALUES('Entel Móvil Perú SAC','20467534030','(01) 315 - 0737');


ALTER TABLE TBL_CLIENTE
ADD ESTADO CHAR(1) DEFAULT '1';

