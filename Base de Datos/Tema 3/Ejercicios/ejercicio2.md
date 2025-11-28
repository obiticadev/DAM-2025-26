# Ejercicio 2: Creación Avanzada de Tablas

## 2.1 Creación de la tabla TEMPLEADO
Se solicita crear una tabla llamada `TEMPLEADO` utilizando el comando `CREATE TABLE` con las siguientes especificaciones y restricciones:

*   **ID**: Numérico de 4 dígitos. Debe ser **Clave Primaria** con nombre asignado.
*   **NOMBRE**: Alfanumérico de 10 caracteres. **No se puede repetir** (Unique).
*   **FECHA**: Tipo fecha.
*   **EDAD**: Numérico de 3 dígitos. **No puede estar vacío** (Not Null).
*   **SALARIO**: Numérico de 4 dígitos. Debe tener una restricción **CHECK** para asegurar que el valor esté **entre 1000 y 3000**.
*   **DEPARTAMENTO**: Numérico de 2 dígitos. Si no se especifica, tendrá un valor **por defecto de 10**.

**Solución SQL:**

```sql
CREATE TABLE TEMPLEADO (
    ID            NUMBER(4) CONSTRAINT PK_TEMPLEADO PRIMARY KEY,
    NOMBRE        VARCHAR2(10) UNIQUE,
    FECHA         DATE,
    EDAD          NUMBER(3) NOT NULL,
    SALARIO       NUMBER(4) CHECK (SALARIO BETWEEN 1000 AND 3000),
    DEPARTAMENTO  NUMBER(2) DEFAULT 10
);
```

---

## 2.2 Verificación de Tablas
Consultar las tablas pertenecientes al usuario 'ALUMNO' para verificar la creación.

**Solución SQL:**

```sql
SELECT * FROM ALL_TABLES WHERE OWNER = 'ALUMNO';
```