# Entrega 2: Creación Básica de Tablas y Restricciones de Columna. (Enfoque en NOT NULL y el uso detallado de DEFAULT, incluyendo ejemplos con fechas y funciones como CURRENT_DATE).

---

# 🛠️ Estructura de `CREATE TABLE` y Restricciones Básicas

En esta sección nos enfocaremos en la estructura esencial y en dos restricciones que se definen **en la misma línea de la columna**: `NOT NULL` y `DEFAULT`.

---

## 🏗️ Sintaxis Básica de `CREATE TABLE`

La estructura es un "esqueleto" que rellenamos con definiciones de columnas separadas por comas.

```sql
CREATE TABLE NombreDeLaTabla (
    nombre_columna1 TIPO_DATO [Restricciones de Columna],
    nombre_columna2 TIPO_DATO [Restricciones de Columna],
    ...
    [Restricciones de Tabla (las veremos más adelante)]
);

```

> ⚠️ Nota de sintaxis: El punto y coma ; al final es obligatorio para indicar al SGBD que el comando ha terminado.
> 

---

## 📋 Restricciones de Columna (En línea)

Son reglas que se aplican a una columna específica. Se escriben justo **después del tipo de dato** y **antes de la coma**.

### 1. Restricción `NOT NULL` (Obligatoriedad)

- **Objetivo:** Impide que la columna acepte valores nulos (vacíos).
- **Concepto clave:** Un valor `NULL` no es un cero ni una cadena vacía; es la **ausencia de información**. Con `NOT NULL`, obligas a que siempre haya un dato válido.

**Ejemplo:**

```sql
dni VARCHAR(9) NOT NULL

```

### 2. Restricción `DEFAULT` (Valor por defecto)

- **Objetivo:** Si al insertar una fila **no** especificamos un valor, la base de datos usará el valor definido aquí automáticamente. Si sí lo especificamos, el `DEFAULT` se ignora.

### **Variantes de uso (Crítico en DAM):**

| Tipo de Valor | Sintaxis de Ejemplo | Descripción |
| --- | --- | --- |
| **Literal (Número)** | `saldo DECIMAL(10,2) DEFAULT 0.00` | Empieza en 0 si no se indica nada. |
| **Literal (Texto)** | `estado VARCHAR(20) DEFAULT 'Activo'` | Texto fijo entre comillas simples. |
| **Función (Fecha)** | `fecha_alta DATE DEFAULT CURRENT_DATE` | Usa la fecha actual del sistema. |

> 💡 Tip de Sintaxis: El orden recomendado es: NOMBRE → TIPO → DEFAULT → NOT NULL.
> 

---

## 🌍 Funciones de Sistema para Fechas

Muy útiles para registrar cuándo se creó una fila automáticamente.

- **Estándar SQL:**
    - `CURRENT_DATE` (solo fecha)
    - `CURRENT_TIMESTAMP` (fecha y hora exacta)
- **Oracle (Común en exámenes):**
    - `SYSDATE` (fecha y hora)

**Ejemplo comparativo:**

```sql
-- Versión estándar
fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL

-- Versión habitual en Oracle
fecha_registro DATE DEFAULT SYSDATE NOT NULL

```

---

## 🚀 Ejemplo Completo Combinado

Vamos a crear una tabla de `Usuarios` aplicando todo lo aprendido:

```sql
CREATE TABLE Usuarios (
    -- Columna simple obligatoria
    username VARCHAR(50) NOT NULL,

    -- Columna obligatoria con valor por defecto numérico
    creditos INTEGER DEFAULT 10 NOT NULL,

    -- Columna opcional (puede ser NULL porque no tiene NOT NULL)
    email_secundario VARCHAR(100),

    -- Columna obligatoria que usa una función de fecha como default
    fecha_alta TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

```

---

## 🖼️ Infografía Resumen

> Esquema visual:
> 
> 1. **Sentencia:** `CREATE TABLE` + Nombre.
> 2. **Columnas:** Nombre → Tipo → Default → Nulidad.
> 3. **Cierre:** Paréntesis final `);`.

![image.png](image%201.png)