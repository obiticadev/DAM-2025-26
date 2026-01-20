# 1. La Sentencia SELECT: Estructura Básica

El orden de las cláusulas es inamovible. Si se altera, el motor de la base de datos dará error.

```sql
SELECT [ALL | DISTINCT] columna1, columna2... -- ¿Qué queremos ver?
FROM tabla1, tabla2...                         -- ¿De dónde viene el dato?
WHERE condicion                                -- ¿Qué filas queremos filtrar?
ORDER BY columna [ASC | DESC];                 -- ¿Cómo lo ordenamos?
```

### 1.1. Modificadores de Selección
*   **`ALL`**: (Por defecto) Muestra todas las filas que cumplen la condición.
*   **`DISTINCT`**: Elimina filas duplicadas del resultado.
*   **Asterisco (`*`)**: Selecciona todas las columnas de la tabla.

```sql
-- Obtener los nombres de los departamentos sin repetir
SELECT DISTINCT nombre_dep FROM DEPARTAMENTOS;
```

---

# 2. Cláusulas de Identificación (SELECT y FROM)

### 2.1. Alias y Calificadores
*   **Calificar**: Usar `Tabla.Columna` para evitar ambigüedad si dos tablas tienen el mismo nombre de columna.
*   **Alias de Columna**: Renombra la cabecera del resultado (uso de comillas dobles si hay espacios).
*   **Alias de Tabla**: Abreviar el nombre de la tabla en el `FROM`.

```sql
-- Uso de calificadores y alias
SELECT E.nombre AS "Nombre Empleado", E.salario * 1.05 AS "Nuevo Salario"
FROM EMPLEADOS E;
```

---

# 3. Filtrado de Datos (Cláusula WHERE)

Permite extraer solo las filas que cumplen una condición lógica.

### 3.1. Operadores de Comparación Estándar
*   `=`: Igualdad.
*   `!=`, `<>`, `^=`: Desigualdad (distinto).
*   `>`, `<`, `>=`, `<=`: Comparaciones de magnitud.

### 3.2. Operadores Especiales (Nivel Examen)
| Operador | Significado | Ejemplo |
| :--- | :--- | :--- |
| **`BETWEEN x AND y`** | Valor dentro de un rango (incluye extremos). | `WHERE edad BETWEEN 18 AND 25` |
| **`IN (a, b, c)`** | El valor coincide con cualquiera de la lista. | `WHERE ciudad IN ('Madrid', 'Soria')` |
| **`LIKE`** | Búsqueda de patrones en texto. | `WHERE nombre LIKE 'A%'` |
| **`IS NULL`** | Comprueba si el valor es nulo (desconocido). | `WHERE comision IS NULL` |

**Uso de comodines en `LIKE`:**
*   `%`: Sustituye a cualquier cadena de caracteres (0 o muchos).
*   `_`: Sustituye exactamente a **un** carácter.

---

# 4. Consultas Calculadas y Concatenación

Podemos transformar los datos antes de mostrarlos sin modificar la tabla original.

### 4.1. Operadores Aritméticos
Se pueden usar `+`, `-`, `*`, `/`.
```sql
-- Calcular el precio con IVA (21%)
SELECT nombre_prod, precio, precio * 1.21 AS precio_iva
FROM PRODUCTOS;
```

### 4.2. Concatenación (Oracle)
En Oracle se usa el operador doble tubería `||` para unir cadenas o columnas. Para añadir texto literal, se usan **comillas simples**.

```sql
-- Unir Nombre y Apellidos con un espacio en medio
SELECT nombre || ' ' || apellido1 || ' ' || apellido2 AS "Nombre Completo"
FROM EMPLEADOS;
```

---

# 5. Ordenación de Registros (ORDER BY)

Es la última cláusula de la consulta.
*   **`ASC`**: (Por defecto) De menor a mayor.
*   **`DESC`**: De mayor a menor.

**Posibilidades:**
1.  **Por nombre de columna:** `ORDER BY salario DESC`.
2.  **Por posición (índice):** `ORDER BY 2` (Ordena por la segunda columna del `SELECT`).
3.  **Múltiple:** `ORDER BY dep ASC, salario DESC` (Ordena por departamento y, en caso de empate, por salario).

---

# 6. Operadores Lógicos y Precedencia

Permiten combinar múltiples condiciones en el `WHERE`.
1.  **`NOT`**: Invierte el resultado lógico.
2.  **`AND`**: Ambas condiciones deben ser ciertas.
3.  **`OR`**: Al menos una condición debe ser cierta.

### Orden de Precedencia (Qué se evalúa primero):
1.  Paréntesis `( )`.
2.  Operadores aritméticos (`*`, `/` antes que `+`, `-`).
3.  Operador de concatenación `||`.
4.  Comparaciones (`=`, `>`, etc.).
5.  Operadores `IS NULL`, `LIKE`, `IN`, `BETWEEN`.
6.  `NOT`.
7.  `AND`.
8.  `OR`.

---

# 💡 Tips de Oro para el Tema 4

1.  **Tratamiento de NULL:** Recuerda que `salario = NULL` siempre es FALSO. Debes usar obligatoriamente `IS NULL`.
2.  **Literales:** En SQL, los textos y fechas siempre van entre **comillas simples** (`'Texto'`). Las comillas dobles solo se usan para **Alias** de columnas que tengan espacios o quieran respetar mayúsculas.
3.  **Cuidado con el OR:** Cuando mezcles `AND` y `OR`, usa siempre paréntesis para evitar errores de precedencia.
    *   *Mal:* `WHERE dep=10 OR dep=20 AND salario > 2000` (El AND se ejecuta antes).
    *   *Bien:* `WHERE (dep=10 OR dep=20) AND salario > 2000`.