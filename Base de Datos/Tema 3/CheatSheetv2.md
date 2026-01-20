# 1. Creación de Tablas (Nivel Avanzado)

### 1.1. Tablas Independientes (Maestras)
Son las que no tienen dependencias. Deben crearse primero.

```sql
CREATE TABLE CURSOS (
    cod_curso NUMBER(3) CONSTRAINT pk_cursos PRIMARY KEY,
    nombre_curso VARCHAR2(50) NOT NULL UNIQUE
);

CREATE TABLE ALUMNOS (
    dni_alumno VARCHAR2(9) CONSTRAINT pk_alumnos PRIMARY KEY,
    nombre VARCHAR2(50) NOT NULL
);
```

### 1.2. Clave Primaria compuesta por dos FK (Tabla de Relación)
Este es el caso donde la PK de la tabla está formada por las dos columnas que vienen de otras tablas. **Evita duplicados en la relación** (un alumno no puede matricularse dos veces en el mismo curso).

```sql
CREATE TABLE MATRICULAS (
    dni_alumno VARCHAR2(9), -- FK 1
    cod_curso NUMBER(3),    -- FK 2
    nota_final NUMBER(4,2),
    fecha_mat DATE DEFAULT SYSDATE,

    -- 1. Definimos la PRIMARY KEY COMPUESTA (nivel de tabla obligatorio)
    CONSTRAINT pk_matriculas PRIMARY KEY (dni_alumno, cod_curso),

    -- 2. Definimos las FOREIGN KEYS por separado
    CONSTRAINT fk_mat_alumno FOREIGN KEY (dni_alumno) 
        REFERENCES ALUMNOS(dni_alumno) ON DELETE CASCADE,
        
    CONSTRAINT fk_mat_curso FOREIGN KEY (cod_curso) 
        REFERENCES CURSOS(cod_curso) ON DELETE CASCADE,

    -- 3. Validación adicional
    CONSTRAINT ck_nota CHECK (nota_final BETWEEN 0 AND 10)
);
```
*   **Límite:** Una tabla solo puede tener **una** PRIMARY KEY. Si es compuesta, se listan las columnas entre paréntesis.

---

# 2. Modificación de Estructura (ALTER TABLE)

En el examen se valora mucho el uso de nombres específicos para no perder el control de la base de datos.

### 2.1. Renombrado (Tablas y Columnas)
```sql
-- Cambiar nombre a una tabla
RENAME MATRICULAS TO INSCRIPCIONES;

-- Cambiar nombre a una columna (Oracle 9i en adelante)
ALTER TABLE ALUMNOS RENAME COLUMN nombre TO nombre_completo;
```

### 2.2. Gestión de Columnas
```sql
-- Añadir varias columnas a la vez
ALTER TABLE ALUMNOS ADD (
    email VARCHAR2(100),
    telefono NUMBER(9)
);

-- Modificar columna (Cambiar tipo o permitir nulos)
ALTER TABLE ALUMNOS MODIFY (email VARCHAR2(150) NULL);

-- Borrar columna
ALTER TABLE ALUMNOS DROP COLUMN telefono;
```

### 2.3. Gestión de Restricciones (Añadir/Quitar/Cambiar)
```sql
-- Añadir una UNIQUE a una columna que ya existía
ALTER TABLE ALUMNOS ADD CONSTRAINT uk_alumno_email UNIQUE (email);

-- Eliminar una restricción sabiendo su nombre (Obligatorio en examen)
ALTER TABLE ALUMNOS DROP CONSTRAINT uk_alumno_email;

-- Desactivar temporalmente (Para cargar datos masivos sin errores de FK)
ALTER TABLE INSCRIPCIONES DISABLE CONSTRAINT fk_mat_curso;

-- Reactivar (Oracle validará que los datos actuales cumplan la regla)
ALTER TABLE INSCRIPCIONES ENABLE CONSTRAINT fk_mat_curso;
```

---

# 3. Borrado e Integridad (DROP / TRUNCATE)

### 3.1. Diferencias Críticas
*   **`DELETE`:** (DML) Borra filas una a una. Se puede deshacer con `ROLLBACK`.
*   **`TRUNCATE`:** (DDL) Vacía la tabla por completo. Es instantáneo y no se puede deshacer. Mantiene la estructura.
*   **`DROP`:** (DDL) Borra la tabla y su estructura de la base de datos.

### 3.2. Borrado con Dependencias
Si intentas borrar la tabla `CURSOS` teniendo datos en `MATRICULAS`, Oracle dará error. Tienes dos opciones:

```sql
-- Opción A: Borrar primero la tabla hija y luego la padre.
DROP TABLE MATRICULAS;
DROP TABLE CURSOS;

-- Opción B: Forzar el borrado (Elimina las restricciones de las hijas automáticamente)
DROP TABLE CURSOS CASCADE CONSTRAINTS;
```

---

# 4. Resumen de Cláusulas FK (Integridad Referencial)

Es vital entender qué pasa en la tabla **hija** cuando borras un registro en la tabla **padre**:

1.  **`ON DELETE CASCADE`**: Si borras el curso, se borran automáticamente todas sus matrículas. (Limpieza total).
2.  **`ON DELETE SET NULL`**: Si borras el curso, la matrícula sigue existiendo pero el campo `cod_curso` se pone a `NULL`. (Para mantener históricos).
3.  **Sin cláusula**: No te deja borrar el curso mientras existan alumnos matriculados en él. (Máxima seguridad).

---

# 💡 Tips Finales de Nivel Examen

*   **Tipos de Datos Coincidentes:** La columna que es FK en la hija debe tener el **mismo tipo y precisión** que la PK en la padre (ej: si una es `NUMBER(3)`, la otra no puede ser `NUMBER(5)`).
*   **Nombres de Constraint:** Usa siempre el formato `tipo_tabla_columna` (ej: `pk_alu_dni`, `fk_mat_alu`). Te ahorrará tiempo si el examen te pide borrar una restricción específica.
*   **Comas:** El error más común es poner una coma después de la última restricción o columna dentro del paréntesis. Revísalo siempre antes de entregar.
*   **Uso de `DESCRIBE`:** Si el examen es en ordenador, usa `DESC nombre_tabla;` para verificar que tus cambios se han aplicado correctamente.