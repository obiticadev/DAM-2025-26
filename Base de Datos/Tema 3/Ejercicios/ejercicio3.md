# Ejercicio 3: Relaciones entre Tablas (Vehículos y Clientes)

Pretendemos crear dos tablas relacionadas entre sí.

> **⚠️ Importante:** Debido a que la tabla `CLIENTES` depende de `VEHÍCULOS` (por la matrícula), primero debemos crear la tabla `VEHÍCULOS`.

---

## 3.1 Tabla VEHÍCULOS
Se requiere almacenar la información del parque móvil.

**Especificaciones:**
*   **MATRÍCULA**: Cadena de texto. Actúa como **Clave Única** (Primary Key).
*   **ANTIGÜEDAD**: Numérico. Debe tener una restricción para aceptar solo valores **entre 1 y 20**.

**Solución SQL:**

```sql
CREATE TABLE VEHICULOS (
    MATRICULA   VARCHAR2(10) CONSTRAINT PK_VEHICULOS PRIMARY KEY,
    ANTIGUEDAD  NUMBER CHECK (ANTIGUEDAD BETWEEN 1 AND 20)
);
```

---

## 3.2 Tabla CLIENTES
Se requiere almacenar la información de los propietarios.

**Especificaciones:**
*   **NIF**: Cadena de texto. **Clave Única** (Primary Key).
*   **NOMBRE**: Cadena, longitud máxima **25 caracteres**.
*   **APELLIDOS**: Cadena, longitud máxima **40 caracteres**.
*   **FECHA_NACIMIENTO**: Fecha. Campo **Obligatorio** (Not Null).
*   **SALARIO**: Numérico. Valores restringidos: **Máximo 1800** y **Mínimo 700**.
*   **COTIZ**: Numérico **con decimales**.
*   **HIJOS**: Verdadero o Falso.
    *   *Nota: Oracle no tiene tipo "Boolean", se usa `CHAR(1)` con restricción 'V'/'F' o `NUMBER(1)` con 1/0.*
*   **MATRÍCULA**: Cadena. **Clave Ajena** (Foreign Key) que referencia a la tabla `VEHÍCULOS`.

**Solución SQL:**

```sql
CREATE TABLE CLIENTES (
    NIF              VARCHAR2(10) CONSTRAINT PK_CLIENTES PRIMARY KEY,
    NOMBRE           VARCHAR2(25),
    APELLIDOS        VARCHAR2(40),
    FECHA_NACIMIENTO DATE NOT NULL,
    SALARIO          NUMBER CHECK (SALARIO BETWEEN 700 AND 1800),
    COTIZ            NUMBER(10, 2),
    HIJOS            CHAR(1) CHECK (HIJOS IN ('V', 'F')), 
    MATRICULA        VARCHAR2(10),
    CONSTRAINT FK_CLI_VEH FOREIGN KEY (MATRICULA) REFERENCES VEHICULOS(MATRICULA)
);
```