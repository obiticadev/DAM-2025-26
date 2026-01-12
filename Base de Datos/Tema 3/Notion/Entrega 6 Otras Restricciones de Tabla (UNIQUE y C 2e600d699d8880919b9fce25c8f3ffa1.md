# Entrega 6: Otras Restricciones de Tabla (UNIQUE y CHECK Avanzado). (Sintaxis rigurosa. El apartado CHECK incluirá explícitamente cómo usar rangos con BETWEEN, listas de valores con IN (...) y comparaciones complejas).

# 🛡️ Restricciones de Negocio: UNIQUE y CHECK

Para que un modelo de datos sea robusto, no basta con definir claves y relaciones; necesitamos reglas que aseguren que la información tiene sentido lógico. Las restricciones `UNIQUE` y `CHECK` actúan como la "aduana" de los datos.

---

## 1. Restricción `UNIQUE` (Unicidad no primaria)

Garantiza que los valores de una columna (o conjunto de ellas) no se repitan, pero con matices distintos a la Clave Primaria.

| Característica | PRIMARY KEY | UNIQUE |
| --- | --- | --- |
| **Cantidad** | Solo **UNA** por tabla. | Puedes tener **MÚLTIPLES**. |
| **Nulos** | **No** los permite. | **Sí** los permite (un `NULL` no es igual a otro). |
| **Uso común** | ID técnico, DNI. | Email, Teléfono, Número de SS. |

### Sintaxis Rigurosa:

```sql
CREATE TABLE Usuarios (
    usuario_id INT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100),

    -- Restricción UNIQUE con nombre específico
    CONSTRAINT uq_usuario_email UNIQUE (email),

    -- Ejemplo de UNIQUE compuesto (la combinación de ambos debe ser única)
    CONSTRAINT uq_username_email UNIQUE (username, email)
);

```

---

## 2. Restricción `CHECK` (Validación Avanzada)

Es la herramienta más potente para aplicar reglas de negocio directamente en el motor de la base de datos. Define una condición lógica que cada fila debe cumplir; de lo contrario, la inserción o actualización es rechazada.

### A) Rangos y Límites Numéricos

Asegura que los valores estén dentro de límites físicos o lógicos coherentes.

```sql
CREATE TABLE Productos (
    prod_id INT PRIMARY KEY,
    nombre VARCHAR(100),
    precio DECIMAL(10, 2),
    stock INT,

    -- El precio no puede ser negativo
    CONSTRAINT chk_precio_positivo CHECK (precio >= 0),

    -- El stock debe estar en un rango específico
    CONSTRAINT chk_stock_rango CHECK (stock BETWEEN 0 AND 10000)
);

```

### B) Listas de Valores Permitidos (`IN`)

Restringe una columna de texto a un conjunto cerrado de opciones predefinidas (similar a un enumerado).

```sql
CREATE TABLE Tareas (
    tarea_id INT PRIMARY KEY,
    estado VARCHAR(20),
    prioridad CHAR(1),

    -- Solo se permiten estos tres estados exactos
    CONSTRAINT chk_tarea_estado
        CHECK (estado IN ('Pendiente', 'En Progreso', 'Completada')),

    -- Solo prioridades A, B o C
    CONSTRAINT chk_tarea_prioridad
        CHECK (prioridad IN ('A', 'B', 'C'))
);

```

### C) Comparación entre Columnas (Misma fila)

Valida la coherencia lógica comparando dos campos distintos del mismo registro.

```sql
CREATE TABLE Proyectos (
    proy_id INT PRIMARY KEY,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE,

    -- La fecha de fin debe ser posterior o igual a la de inicio
    CONSTRAINT chk_fechas_proyecto CHECK (fecha_fin >= fecha_inicio),

    -- Ejemplo adicional: sueldo_max >= sueldo_min
    CONSTRAINT chk_rango_salarial CHECK (salario_max >= salario_min)
);

```

---

## 🖼️ Infografía Resumen: UNIQUE y CHECK

> Puntos Clave para Exámenes:
> 
> 1. **UNIQUE:** Permite nulos (a diferencia de la PK). Evita duplicados en datos secundarios.
> 2. **CHECK + BETWEEN:** Para rangos numéricos y de fechas.
> 3. **CHECK + IN:** Para listas de estados o categorías fijas.
> 4. **CHECK Lógico:** Compara dos columnas (ej. inicio vs fin) para evitar errores de coherencia temporal o lógica.

![image.png](image%205.png)