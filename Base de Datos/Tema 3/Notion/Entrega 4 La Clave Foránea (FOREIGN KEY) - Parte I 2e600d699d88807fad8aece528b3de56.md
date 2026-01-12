# Entrega 4: La Clave Foránea (FOREIGN KEY) - Parte I: Sintaxis Base. (Sintaxis rigurosa usando CONSTRAINT fk_nombre ... REFERENCES).

# 🔗 La Clave Foránea (FOREIGN KEY - FK)

Una vez que tenemos tablas con identificadores únicos (PK), es momento de conectarlas. La **Clave Foránea** es el "pegamento" del modelo relacional.

---

## 💡 Concepto Fundamental

Una **FK** es una columna (o conjunto de columnas) en una tabla **"hija"** que hace referencia a la Clave Primaria de una tabla **"padre"**.

- **Propósito:** Garantizar la **Integridad Referencial**. Asegura que las relaciones entre datos sean siempre válidas.
- **Ejemplo:** No puedes asignar un empleado al departamento `99` si el departamento `99` no existe en la tabla de departamentos.

> ❗ Regla de Oro de la Sintaxis:
La columna FK (hija) y la columna PK (padre) DEBEN tener exactamente el mismo tipo de dato y longitud. Si la PK es INTEGER, la FK debe ser INTEGER.
> 

---

## 🛠️ Sintaxis Rigurosa: `CONSTRAINT` ... `REFERENCES`

Al igual que con la PK, la forma profesional y recomendada es usar `CONSTRAINT` al final de la definición de la tabla hija.

### Estructura de la sentencia:

`CONSTRAINT nombre_regla FOREIGN KEY (columna_propia) REFERENCES TablaPadre(columna_padre)`

---

## 📝 Ejemplo Práctico: Departamentos y Empleados

Para que una FK funcione, la tabla **Padre** debe ser creada primero.

### 1. Tabla PADRE (Departamentos)

```sql
CREATE TABLE Departamentos (
    dept_id INTEGER NOT NULL,
    nombre VARCHAR(50) NOT NULL,

    -- PK definida rigurosamente
    CONSTRAINT pk_departamentos PRIMARY KEY (dept_id)
);

```

### 2. Tabla HIJA (Empleados)

```sql
CREATE TABLE Empleados (
    emp_id INTEGER NOT NULL,
    nombre VARCHAR(50) NOT NULL,

    -- Esta columna guardará el ID del departamento (FK)
    dept_fk INTEGER,

    -- PK del empleado
    CONSTRAINT pk_employees PRIMARY KEY (emp_id),

    -- DEFINICIÓN RIGUROSA DE LA FK
    -- Nombre de la regla | Tipo de restricción | Columna propia | Referencia al Padre
    CONSTRAINT fk_empleados_dept FOREIGN KEY (dept_fk) REFERENCES Departamentos(dept_id)
);

```

**Interpretación:** "Se crea una regla llamada `fk_empleados_dept` que obliga a que cualquier valor en la columna `dept_fk` de esta tabla exista previamente en la columna `dept_id` de la tabla `Departamentos`".

---

## 🛡️ Consecuencias de la Integridad Referencial

Al crear esta relación, la base de datos aplica automáticamente estas reglas de protección:

1. **Al Insertar en la tabla Hija:** Si intentas crear un empleado con un `dept_fk` que no existe en la tabla padre, la operación dará **error**.
2. **Al Borrar en la tabla Padre:** Si intentas borrar un departamento que todavía tiene empleados asociados, la base de datos **bloqueará el borrado** para evitar dejar "empleados huérfanos".

---

## 🖼️ Infografía Resumen: Conexión de Tablas

> Esquema de enlace:
> 
> - **Tabla Padre:** Contiene la **PRIMARY KEY**.
> - **Tabla Hija:** Contiene la **FOREIGN KEY**.
> - **Vínculo:** El valor de la FK debe "apuntar" a un valor real en la PK.
> - **Tipos:** Deben coincidir (ej. INT con INT).

![image.png](image%203.png)