CREATE TABLE tbl_cliente(
    ID_CLIENTE int(11)  primary key NOT NULL AUTO_INCREMENT,
    RAZON_SOCIAL VARCHAR(220) NOT NULL UNIQUE,
    RUC CHAR(11) NOT NULL UNIQUE,
    TELEFONO VARCHAR(60) NULL,
    ESTADO CHAR(1) DEFAULT '1'
);

INSERT INTO tbl_cliente(RAZON_SOCIAL,RUC,TELEFONO)
VALUES('TELEFONICA DEL PERU SAA','20100017491','(01) 315 - 0730');


INSERT INTO tbl_cliente(RAZON_SOCIAL,RUC,TELEFONO)
VALUES('América Móvil Perú SAC','20467534026','(01) 315 - 0733');

INSERT INTO tbl_cliente(RAZON_SOCIAL,RUC,TELEFONO)
VALUES('Entel Móvil Perú SAC','20467534030','(01) 315 - 0737');




CREATE TABLE tbl_producto
   (ID_PRODUCTO int(11) primary key NOT NULL AUTO_INCREMENT,
	NOMBRE VARCHAR(120),
	PRECIO decimal(6,2),
	STOCK decimal(4,0),
	ESTADO CHAR(1) DEFAULT '1'
);


Insert into tbl_producto (NOMBRE,PRECIO,STOCK,ESTADO) values ('Producto 1','2500','90','0');
Insert into tbl_producto (NOMBRE,PRECIO,STOCK,ESTADO) values ('Producto 2','2500','16','0');
Insert into tbl_producto (NOMBRE,PRECIO,STOCK,ESTADO) values ('Producto 3','500','14','0');
Insert into tbl_producto (NOMBRE,PRECIO,STOCK,ESTADO) values ('Producto 4','2500','5','1');
Insert into tbl_producto (NOMBRE,PRECIO,STOCK,ESTADO) values ('Producto 5','2500','16','1');
Insert into tbl_producto (NOMBRE,PRECIO,STOCK,ESTADO) values ('Producto 6','2500','19','1');
Insert into tbl_producto (NOMBRE,PRECIO,STOCK,ESTADO) values ('Producto 7','2500','19','1');
Insert into tbl_producto (NOMBRE,PRECIO,STOCK,ESTADO) values ('Producto 8','2500','-5','1');
Insert into tbl_producto (NOMBRE,PRECIO,STOCK,ESTADO) values ('Producto 9','2500','19','1');
Insert into tbl_producto (NOMBRE,PRECIO,STOCK,ESTADO) values ('Producto 10','2500','19','1');


   CREATE TABLE tbl_pedido
   (ID_PEDIDO int(11) PRIMARY KEY AUTO_INCREMENT,
	ID_CLIENTE int(11) REFERENCES tbl_cliente(ID_CLIENTE),
	GLOSA VARCHAR(240),
	FECHA_REGISTRO timestamp DEFAULT CURRENT_TIMESTAMP,
	TOTAL decimal(9,2),
	ESTADO CHAR(1) DEFAULT '1'
   );

    CREATE TABLE tbl_pedido_detalle
   (ID_PEDIDO_DETALLE int(11),
	ID_PEDIDO int(11) REFERENCES tbl_pedido(ID_PEDIDO),
	ID_PRODUCTO int(11)REFERENCES tbl_producto(ID_PRODUCTO),
	CANTIDAD int(11),
	PRECIO decimal(6,2),
	SUB_TOTAL decimal(9,2),
	ESTADO CHAR(1) DEFAULT '1'
   );


  INSERT INTO tbl_pedido(ID_CLIENTE,GLOSA,TOTAL)
  VALUES(1,'Orden demo',6090);