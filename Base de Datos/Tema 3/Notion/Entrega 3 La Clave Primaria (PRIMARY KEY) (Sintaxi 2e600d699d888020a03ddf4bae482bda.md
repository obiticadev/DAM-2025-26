# Entrega 3: La Clave Primaria (PRIMARY KEY). (Sintaxis rigurosa usando CONSTRAINT pk_nombre).

# 🔑 La Clave Primaria (PRIMARY KEY - PK)

La **Clave Primaria** es la columna (o conjunto de columnas) que identifica de forma *única e inequívoca* a cada fila de la tabla. Es el identificador universal de cada registro (como un DNI o una matrícula).

---

## 📜 Reglas de Oro de la PK

1. **Unicidad:** No puede haber dos filas con el mismo valor en la PK.
2. **No Nulo:** Una PK nunca puede ser `NULL`. Al definirla, la base de datos aplica un `NOT NULL` implícito.
3. **Solo Una:** Una tabla solo puede tener **UNA** clave primaria (aunque esta puede estar compuesta por varias columnas).

---

## 🛠️ Sintaxis Rigurosa: Uso de `CONSTRAINT`

Aunque es posible definir una PK en la misma línea de la columna, la **forma profesional y recomendada** es usar la cláusula `CONSTRAINT` al final de la tabla.

### ¿Por qué usar `CONSTRAINT`?

- **Control de Errores:** Permite dar un nombre específico a la regla (ej. `pk_usuarios`). Si ocurre un error, el sistema te dirá exactamente qué restricción falló.
- **Flexibilidad:** Es la **única forma** de definir claves primarias compuestas.
- **Mantenimiento:** Facilita borrar o modificar la restricción en el futuro mediante `ALTER TABLE`.

> 🏷️ Convención de nombres: Un estándar muy usado en DAM es pk_NombreTabla.
> 

---

## 1️⃣ Clave Primaria Simple (Una sola columna)

Se define la columna normalmente y, al final de la lista, se añade la restricción.

```sql
CREATE TABLE Departamentos (
    dept_id INTEGER NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    ubicacion VARCHAR(100),

    -- Sintaxis: CONSTRAINT [nombre_regla] PRIMARY KEY (columna)
    CONSTRAINT pk_departamentos PRIMARY KEY (dept_id)
);

```

*Interpretación:* "Se crea una regla llamada `pk_departamentos` que establece que la columna `dept_id` es la clave primaria".

---

## 2️⃣ Clave Primaria Compuesta (Varias columnas)

Se usa cuando una sola columna no es suficiente para identificar un registro de forma única. Es común en tablas que relacionan otras entidades (como Notas de Alumnos).

> ⚠️ Importante: Esta sintaxis solo se puede realizar a nivel de tabla con CONSTRAINT.
> 

```sql
CREATE TABLE NotasAlumno (
    alumno_id INTEGER NOT NULL,
    asignatura_id VARCHAR(10) NOT NULL,
    nota DECIMAL(4, 2),
    fecha_examen DATE,

    -- La PK es la combinación única de (alumno + asignatura)
    CONSTRAINT pk_notas_alumno PRIMARY KEY (alumno_id, asignatura_id)
);

```

### ¿Cómo funciona la lógica compuesta?

- ✅ Se permite: `alumno 100` con `asignatura PROG`.
- ✅ Se permite: `alumno 100` con `asignatura BBDD`.
- ✅ Se permite: `alumno 200` con `asignatura PROG`.
- ❌ **Error:** No se puede repetir `alumno 100` con `asignatura PROG` de nuevo, ya que esa pareja ya existe.

---

## 🖼️ Infografía Resumen: PK y Constraints

> Resumen de concepto:
> 
> 1. **PK Simple:** 1 columna identifica la fila.
> 2. **PK Compuesta:** La combinación de N columnas identifica la fila.
> 3. **Constraint:** Es la mejor práctica para nombrar y gestionar estas reglas.

![image.png](image%202.png)