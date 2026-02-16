CREATE TABLE CONDUCTORES (
    nif VARCHAR2(9) PRIMARY KEY,
    nombre VARCHAR2(25) NOT NULL,
    apellidos VARCHAR2(40) NOT NULL,
    foto BLOB NOT NULL,
    edad NUMBER(2) CHECK (edad BETWEEN 18 AND 99),
    tipo_carnet VARCHAR2(2) CHECK (tipo_carnet IN ('A1','A2','A','B1','B2')),
    fecha_carnet DATE DEFAULT SYSDATE,
    intentos NUMBER(1) DEFAULT 0 CHECK (intentos < 10)
);

CREATE TABLE VEHICULOS (
    matricula VARCHAR2(10) PRIMARY KEY,
    antiguedad NUMBER(2) CONSTRAINT ck_anti CHECK (antiguedad <= 20)
);

CREATE TABLE CLIENTES (
    nif_cliente VARCHAR2(9) PRIMARY KEY,
    nombre VARCHAR2(30),
    matricula_fk VARCHAR2(10),
    CONSTRAINT fk_veh_cli FOREIGN KEY (matricula_fk) 
        REFERENCES VEHICULOS(matricula) ON DELETE CASCADE
);

INSERT INTO VEHICULOS (matricula, antiguedad) VALUES ('1234-ABC', 5);

INSERT INTO CONDUCTORES (nif, nombre, apellidos, foto, edad, tipo_carnet, fecha_carnet) 
VALUES ('12345678X', 'Juan', 'Pérez', EMPTY_BLOB(), 30, 'B1', TO_DATE('25/12/2023', 'DD/MM/YYYY'));

INSERT INTO CONDUCTORES (nif, nombre, apellidos, foto, edad, tipo_carnet, fecha_carnet) 
VALUES ('87654321Z', 'Ana', 'García', EMPTY_BLOB(), 25, 'A', SYSDATE);

SELECT matricula AS "Placa", antiguedad AS "Años de Uso" 
FROM VEHICULOS 
WHERE antiguedad > 2;

CREATE TABLE EMPLEADOS_TEMP (
    id NUMBER,
    nombre VARCHAR2(10)
);

ALTER TABLE EMPLEADOS_TEMP ADD (domicilio VARCHAR2(50), sueldo NUMBER(6,2));
ALTER TABLE EMPLEADOS_TEMP MODIFY (domicilio DEFAULT 'Alcala');
ALTER TABLE EMPLEADOS_TEMP RENAME COLUMN nombre TO nombre_completo;
ALTER TABLE EMPLEADOS_TEMP ADD CONSTRAINT pk_emp_temp PRIMARY KEY (id);

CREATE VIEW VISTA_EMPLEADOS AS 
    SELECT id, nombre_completo, domicilio 
    FROM EMPLEADOS_TEMP;

SELECT table_name FROM ALL_TABLES WHERE OWNER = 'ALUMNO';

CREATE INDEX idx_conductor_apellidos ON CONDUCTORES(apellidos);
CREATE UNIQUE INDEX idx_veh_matricula ON VEHICULOS(matricula);
CREATE INDEX idx_nom_ape ON CONDUCTORES(nombre, apellidos);

SELECT index_name, table_name FROM USER_INDEXES;

DROP TABLE EMPLEADOS_TEMP; 
DROP VIEW VISTA_EMPLEADOS;
DROP INDEX idx_conductor_apellidos;