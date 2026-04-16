Aquí tienes una **Cheatsheet Profesional de SQL (DDL)** basada exclusivamente en el contenido de tu documento. Está diseñada para ser una referencia rápida, clara y efectiva para realizar ejercicios de Bases de Datos (enfocada en sintaxis Oracle/Estándar según el PDF).

---

# 📘 SQL Cheatsheet: Lenguaje de Definición de Datos (DDL)

## 1. Normas Básicas de Escritura
*   **Finalización:** Todas las instrucciones terminan con punto y coma (`;`).
*   **Case Insensitive:** No se distingue entre mayúsculas y minúsculas (salvo en literales entre comillas).
*   **Comentarios:**
    *   Bloque: `/* Comentario */`
    *   Línea (algunos SGBD): `-- Comentario`
*   **Nombres de objetos:** Deben empezar por letra, máx. 30 caracteres, sin espacios (usar `_`) y sin palabras reservadas.

---

## 2. Gestión de Bases de Datos
Aunque depende del SGBD, la sintaxis estándar es:

```sql
CREATE DATABASE nombre_base_datos;
```
> **Nota:** En Oracle, esto crea un conjunto de archivos físicos. Lógicamente, trabajamos sobre **Esquemas** (asociados a un usuario).

---

## 3. Creación de Tablas (`CREATE TABLE`)
Define la estructura donde se almacenan los datos.

**Sintaxis General:**
```sql
CREATE TABLE nombre_tabla (
    columna1 TIPO_DATO [Restricciones],
    columna2 TIPO_DATO [DEFAULT valor],
    ...
    [CONSTRAINT nombre_restriccion TIPO_RESTRICCION (columnas)]
);
```

### Tipos de Datos Comunes (Oracle Reference)
*   `VARCHAR2(n)`: Cadena de caracteres variable.
*   `NUMBER(p, s)`: Números (p=precisión, s=decimales).
*   `DATE`: Fechas.

---

## 4. Restricciones (Constraints)
Reglas que los datos deben cumplir obligatoriamente. Se recomienda nombrarlas siguiendo el patrón: `Tabla_Columna_Tipo` (ej. `USU_ID_PK`).

### A. Tipos de Restricciones

| Restricción | Descripción | Abreviatura Sugerida |
| :--- | :--- | :--- |
| **PRIMARY KEY** | Identificador único de la fila. (No nulo + Único). | `PK` |
| **FOREIGN KEY** | Relaciona una columna con la PK de otra tabla (Integridad Referencial). | `FK` |
| **NOT NULL** | Obliga a que la columna tenga valor. | `NN` |
| **UNIQUE** | Evita valores duplicados en la columna. | `UK` |
| **CHECK** | Valida que el dato cumpla una condición lógica. | `CK` |
| **DEFAULT** | Asigna un valor por defecto si no se especifica (ej. `SYSDATE`). | N/A |

### B. Formas de Definición

#### 1. Nivel de Columna (Inline)
Se define en la misma línea del campo. Ideal para `NOT NULL` o claves simples.
```sql
CREATE TABLE USUARIOS (
    Login VARCHAR2(15) CONSTRAINT usu_log_pk PRIMARY KEY,
    Password VARCHAR2(20) NOT NULL,
    Fecha_Ingreso DATE DEFAULT SYSDATE
);
```

#### 2. Nivel de Tabla (Out-of-line)
Se define al final, después de las columnas. Obligatorio para **claves compuestas** (más de una columna).

```sql
CREATE TABLE DETALLES (
    Nombre VARCHAR2(20),
    Apellido VARCHAR2(20),
    Edad NUMBER(2),
    -- Clave Primaria Compuesta
    CONSTRAINT det_nom_ape_pk PRIMARY KEY (Nombre, Apellido),
    -- Check de validación
    CONSTRAINT det_edad_ck CHECK (Edad BETWEEN 18 AND 65)
);
```

### C. Claves Ajenas (Foreign Keys)
Referencia a otra tabla padre.

```sql
CREATE TABLE PEDIDOS (
    ID_Pedido NUMBER(5) PRIMARY KEY,
    ID_Cliente VARCHAR2(5),
    -- Definición FK
    CONSTRAINT ped_cli_fk FOREIGN KEY (ID_Cliente) 
        REFERENCES CLIENTES(ID_Cliente)
        [ON DELETE CASCADE | ON DELETE SET NULL]
);
```
*   **ON DELETE CASCADE:** Si borras el padre, se borran los hijos.
*   **ON DELETE SET NULL:** Si borras el padre, el campo del hijo se pone a NULL.

---

## 5. Modificación de Tablas (`ALTER TABLE`)
Se usa cuando la tabla ya está creada y necesitamos cambiar su estructura.

### Columnas
*   **Añadir columna:**
    ```sql
    ALTER TABLE nombre_tabla ADD (nueva_columna TIPO_DATO);
    ```
*   **Modificar columna (Tipo de dato/Tamaño):**
    ```sql
    ALTER TABLE nombre_tabla MODIFY (columna TIPO_DATO [NOT NULL]);
    ```
*   **Renombrar columna:**
    ```sql
    ALTER TABLE nombre_tabla RENAME COLUMN nombre_viejo TO nombre_nuevo;
    ```
*   **Eliminar columna:**
    ```sql
    ALTER TABLE nombre_tabla DROP COLUMN (nombre_columna);
    ```

### Restricciones (Constraints)
*   **Añadir Primary Key:**
    ```sql
    ALTER TABLE tabla ADD CONSTRAINT nombre_pk PRIMARY KEY (columna);
    ```
*   **Añadir Foreign Key:**
    ```sql
    ALTER TABLE tabla_hija ADD CONSTRAINT nombre_fk FOREIGN KEY (columna) REFERENCES tabla_padre(columna);
    ```
*   **Añadir Check:**
    ```sql
    ALTER TABLE tabla ADD CONSTRAINT nombre_ck CHECK (condición);
    ```
*   **Eliminar Restricción:**
    ```sql
    ALTER TABLE tabla DROP CONSTRAINT nombre_restriccion;
    ```
*   **Desactivar/Activar Restricción:**
    ```sql
    ALTER TABLE tabla DISABLE CONSTRAINT nombre_restriccion [CASCADE];
    ALTER TABLE tabla ENABLE CONSTRAINT nombre_restriccion;
    ```
*   **Renombrar Restricción:**
    ```sql
    ALTER TABLE tabla RENAME CONSTRAINT nombre_viejo TO nombre_nuevo;
    ```

---

## 6. Eliminación de Objetos

### Eliminar Tabla (`DROP`)
Borra la estructura y los datos permanentemente.
```sql
DROP TABLE nombre_tabla [CASCADE CONSTRAINTS];
```
*   `CASCADE CONSTRAINTS`: Borra también las referencias de claves ajenas en otras tablas que apuntan a esta.

### Vaciar Tabla (`TRUNCATE`)
Borra todos los datos (filas) pero **mantiene** la estructura de la tabla. Es más rápido que un `DELETE` masivo.
```sql
TRUNCATE TABLE nombre_tabla;
```

---

## 7. Índices (Indices)
Estructuras para mejorar la velocidad de consulta. Se crean automáticamente con `PRIMARY KEY` y `UNIQUE`.

*   **Crear Índice:**
    ```sql
    CREATE INDEX nombre_indice ON nombre_tabla (columna);
    ```
*   **Eliminar Índice:**
    ```sql
    DROP INDEX nombre_indice;
    ```

---

## 8. Comandos Útiles de Información
*   **DESCRIBE:** Muestra la estructura de una tabla (columnas, tipos, nulabilidad).
    ```sql
    DESCRIBE nombre_tabla;
    -- o abreviado
    DESC nombre_tabla;
    ```