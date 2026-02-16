/* =============================================================================
   GUÍA RÁPIDA DE SINTAXIS (SQL / ORACLE)
   - CREATE TABLE: Define la estructura inicial.
   - CONSTRAINT: Regla que asegura la integridad de los datos (PK, FK, CHECK).
   - ALTER TABLE: Modifica una tabla ya existente (ADD, MODIFY, RENAME, DROP).
   - ON DELETE CASCADE: Si se borra el padre, se borran automáticamente sus hijos.
   - VIEW: Consulta guardada que se comporta como una tabla virtual.
   - INDEX: Estructura que acelera la recuperación de datos (búsquedas rápidas).
============================================================================= */

-- =============================================================================
-- 1. BLOQUE DE CREACIÓN (CREATE) Y RESTRICCIONES BÁSICAS
-- =============================================================================

-- Definición de tipos de datos, PKs y validaciones CHECK
CREATE TABLE CONDUCTORES (
    nif VARCHAR2(9) PRIMARY KEY,                    -- Clave primaria (No nula y única)
    nombre VARCHAR2(25) NOT NULL,                   -- Obligatorio
    apellidos VARCHAR2(40) NOT NULL,
    foto BLOB NOT NULL,                             -- Tipo binario para archivos
    edad NUMBER(2) CHECK (edad BETWEEN 18 AND 99),  -- Validación de rango numérico
    tipo_carnet VARCHAR2(2) CHECK (tipo_carnet IN ('A1','A2','A','B1','B2')), -- Lista de valores permitidos
    fecha_carnet DATE DEFAULT SYSDATE,              -- Valor automático: fecha actual del sistema
    intentos NUMBER(1) DEFAULT 0 CHECK (intentos < 10) -- Valor por defecto y límite máximo
);

-- =============================================================================
-- 2. RELACIONES ENTRE TABLAS Y BORRADO EN CASCADA (REFERENCES)
-- =============================================================================

CREATE TABLE VEHICULOS (
    matricula VARCHAR2(10) PRIMARY KEY,
    antiguedad NUMBER(2) CONSTRAINT ck_anti CHECK (antiguedad <= 20)
);

-- Ejemplo de relación 1:N con integridad referencial
CREATE TABLE CLIENTES (
    nif_cliente VARCHAR2(9) PRIMARY KEY,
    nombre VARCHAR2(30),
    matricula_fk VARCHAR2(10),
    -- Relación vinculada: Si se elimina el vehículo, desaparecen sus clientes asociados
    CONSTRAINT fk_veh_cli FOREIGN KEY (matricula_fk) 
        REFERENCES VEHICULOS(matricula) ON DELETE CASCADE
);

-- =============================================================================
-- 3. MANIPULACIÓN DE DATOS (INSERT / SELECT / ALIAS)
-- =============================================================================

-- Inserción estándar de registros
INSERT INTO VEHICULOS (matricula, antiguedad) VALUES ('1234-ABC', 5);

-- INSERCIÓN CON FECHAS (Oracle): 
-- Uso de TO_DATE para asegurar que el formato sea independiente de la configuración del PC
INSERT INTO CONDUCTORES (nif, nombre, apellidos, foto, edad, tipo_carnet, fecha_carnet) 
VALUES ('12345678X', 'Juan', 'Pérez', EMPTY_BLOB(), 30, 'B1', TO_DATE('25/12/2023', 'DD/MM/YYYY'));

-- Inserción usando la fecha actual del servidor (SYSDATE)
INSERT INTO CONDUCTORES (nif, nombre, apellidos, foto, edad, tipo_carnet, fecha_carnet) 
VALUES ('87654321Z', 'Ana', 'García', EMPTY_BLOB(), 25, 'A', SYSDATE);

-- Consulta con ALIAS (Renombrado temporal de campos para el reporte)
SELECT matricula AS "Placa", antiguedad AS "Años de Uso" 
FROM VEHICULOS 
WHERE antiguedad > 2;

-- =============================================================================
-- 4. MODIFICACIÓN ESTRUCTURAL (ALTER / MODIFY / RENAME)
-- =============================================================================

-- Ejemplo de tabla base para aplicar modificaciones
CREATE TABLE EMPLEADOS_TEMP (
    id NUMBER,
    nombre VARCHAR2(10)
);

-- ALTER ADD: Agregar nuevas columnas o restricciones a una tabla viva
ALTER TABLE EMPLEADOS_TEMP ADD (domicilio VARCHAR2(50), sueldo NUMBER(6,2));

-- ALTER MODIFY: Cambiar el comportamiento de una columna (tipo o valor por defecto)
ALTER TABLE EMPLEADOS_TEMP MODIFY (domicilio DEFAULT 'Alcala');

-- ALTER RENAME: Cambiar el nombre de una columna sin perder los datos
ALTER TABLE EMPLEADOS_TEMP RENAME COLUMN nombre TO nombre_completo;

-- ALTER ADD CONSTRAINT: Añadir PK o FK después de haber creado la tabla
ALTER TABLE EMPLEADOS_TEMP ADD CONSTRAINT pk_emp_temp PRIMARY KEY (id);

-- =============================================================================
-- 5. OBJETOS VIRTUALES Y METADATOS (VIEWS / DICTIONARY)
-- =============================================================================

-- VISTAS: Para ocultar columnas sensibles (como 'sueldo') o simplificar consultas
CREATE VIEW VISTA_EMPLEADOS AS 
    SELECT id, nombre_completo, domicilio 
    FROM EMPLEADOS_TEMP;

-- CONSULTA AL DICCIONARIO: Ver todas las tablas propiedad del usuario 'ALUMNO'
SELECT table_name FROM ALL_TABLES WHERE OWNER = 'ALUMNO';

-- =============================================================================
-- 6. OPTIMIZACIÓN DE CONSULTAS (INDEX)
-- =============================================================================

-- INDEX: Mejora el rendimiento en columnas que se usan mucho en el WHERE
CREATE INDEX idx_conductor_apellidos ON CONDUCTORES(apellidos);

-- UNIQUE INDEX: Asegura que los valores no se repitan (además de crear el índice)
CREATE UNIQUE INDEX idx_veh_matricula ON VEHICULOS(matricula);

-- INDEX COMPUESTO: Para búsquedas frecuentes que filtran por varios campos a la vez
CREATE INDEX idx_nom_ape ON CONDUCTORES(nombre, apellidos);

-- CONSULTA AL DICCIONARIO: Ver índices creados por el usuario actual
SELECT index_name, table_name FROM USER_INDEXES;

-- =============================================================================
-- 7. LIMPIEZA DE ENTORNO (DROP)
-- =============================================================================

-- Eliminar tabla de forma definitiva
-- DROP TABLE EMPLEADOS_TEMP; 

-- Eliminar vista
-- DROP VIEW VISTA_EMPLEADOS;

-- Eliminar índice
-- DROP INDEX idx_conductor_apellidos;