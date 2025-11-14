# Ejercicio 1: Normalización hasta la 3ª Forma Normal

Este ejercicio consiste en normalizar la tabla inicial `BIBLIOTECA` hasta la Tercera Forma Normal (3FN), eliminando las redundancias de datos y las anomalías de actualización.

---

## Tabla Inicial (En 1ª Forma Normal)

La tabla original es:
**BIBLIOTECA** (`numSocio`, `codiLibro`, `fechaPrestamo`, nombre, apellidos, titulo, editorial, país)

La tabla ya se encuentra en **Primera Forma Normal (1FN)** porque todos sus atributos son atómicos; es decir, no hay grupos de valores que se repitan en una sola celda.

*   **Clave Primaria**: La clave primaria es una clave compuesta por (`numSocio`, `codiLibro`, `fechaPrestamo`), ya que esta combinación identifica de forma única cada registro de préstamo.

---

## Paso a 2ª Forma Normal (2FN)

Una tabla está en 2FN si está en 1FN y todos los atributos que no forman parte de la clave primaria dependen de forma completa de la clave primaria. Debemos buscar y eliminar las dependencias parciales.

### Identificación de Dependencias Parciales

En la tabla `BIBLIOTECA`, se observan las siguientes dependencias parciales:

*   Los atributos `nombre` y `apellidos` dependen solo de una parte de la clave primaria: `numSocio`.
    *   `numSocio` → `nombre`, `apellidos`
*   Los atributos `titulo`, `editorial` y `país` dependen solo de `codiLibro`.
    *   `codiLibro` → `titulo`, `editorial`, `país`

### Creación de Tablas en 2FN

Para eliminar estas dependencias parciales, descomponemos la tabla original en las siguientes tablas:

**1. Tabla SOCIOS**
*   `numSocio` (Clave Primaria)
*   `nombre`
*   `apellidos`

**2. Tabla LIBROS**
*   `codiLibro` (Clave Primaria)
*   `titulo`
*   `editorial`
*   `país`

**3. Tabla PRESTAMOS**
*   `numSocio` (Clave Primaria, Clave Foránea a SOCIOS)
*   `codiLibro` (Clave Primaria, Clave Foránea a LIBROS)
*   `fechaPrestamo` (Clave Primaria)

---

## Paso a 3ª Forma Normal (3FN)

Una tabla está en 3FN si está en 2FN y no contiene dependencias transitivas. Una dependencia transitiva ocurre cuando un atributo que no es clave depende de otro atributo que tampoco es clave.

### Identificación de Dependencias Transitivas

Analizando las tablas resultantes del paso a 2FN:

*   Las tablas `SOCIOS` y `PRESTAMOS` ya están en 3FN.
*   En la tabla **LIBROS**, el atributo `país` depende de `editorial`, y `editorial` a su vez depende de la clave primaria `codiLibro`. Esto es una dependencia transitiva.
    *   `codiLibro` → `editorial` → `país`

### Creación de Tablas en 3FN

Para eliminar la dependencia transitiva, dividimos la tabla `LIBROS` en dos:

**1. Tabla LIBROS (actualizada)**
*   `codiLibro` (Clave Primaria)
*   `titulo`
*   `editorial` (Clave Foránea a EDITORIALES)

**2. Tabla EDITORIALES (nueva)**
*   `editorial` (Clave Primaria)
*   `país`

---

## Esquema Final en 3FN

El esquema de la base de datos normalizado en Tercera Forma Normal queda compuesto por las siguientes cuatro tablas:

**SOCIOS**
*   `numSocio` (PK)
*   `nombre`
*   `apellidos`

**EDITORIALES**
*   `editorial` (PK)
*   `país`

**LIBROS**
*   `codiLibro` (PK)
*   `titulo`
*   `editorial` (FK)

**PRESTAMOS**
*   `numSocio` (PK, FK)
*   `codiLibro` (PK, FK)
*   `fechaPrestamo` (PK)