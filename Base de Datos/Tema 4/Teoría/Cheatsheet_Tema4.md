# 🚀 CHEATSHEET DEFINITIVO: TEMA 4 - Consultas SQL en Oracle

Este documento es una referencia profesional exhaustiva que unifica todo el conocimiento teórico y práctico sobre realización de consultas básicas y avanzadas, funciones escalares, agrupamientos, joins y subconsultas en Oracle SQL. 

---

## 📑 Índice de Contenidos Analítico

1. [Fundamentos y Selección de Datos](#1-fundamentos-y-seleccion-de-datos)
   - [Estructura del SELECT y Opciones](#estructura-del-select-y-opciones)
   - [Paginación y Limitación (FETCH, OFFSET)](#paginacion-y-limitacion-fetch-offset)
   - [Filtrado (WHERE) y Ordenación (ORDER BY)](#filtrado-where-y-ordenacion-order-by)
2. [Operadores en SQL](#2-operadores-en-sql)
   - [Relacionales y Aritméticos (Concatenación)](#relacionales-y-aritmeticos-concatenacion)
   - [Lógicos y Precedencia](#logicos-y-precedencia)
3. [Funciones Escalares Opcionales (Tabla DUAL)](#3-funciones-escalares-opcionales-tabla-dual)
   - [Funciones Numéricas y de Cadenas](#funciones-numericas-y-de-cadenas)
   - [Manejo de Fechas y Conversiones (TO\_CHAR, TO\_DATE)](#manejo-de-fechas-y-conversiones-to_char-to_date)
   - [Control de Nulos (NVL, NVL2, CASE)](#control-de-nulos-nvl-nvl2-case)
4. [Agrupamientos y Resúmenes](#4-agrupamientos-y-resumenes)
   - [Funciones de Agregado (SUM, COUNT, AVG)](#funciones-de-agregado-sum-count-avg)
   - [GROUP BY y HAVING (Reglas de Oro)](#group-by-y-having-reglas-de-oro)
5. [Consultas Multitabla (JOINs)](#5-consultas-multitabla-joins)
   - [Sintaxis Clásica Oracle (Interna y Externa `(+)`)](#sintaxis-clasica-oracle-interna-y-externa-)
   - [Sintaxis Moderna SQL99 (ON, USING, OUTER JOIN)](#sintaxis-moderna-sql99-on-using-outer-join)
6. [Operaciones de Conjuntos](#6-operaciones-de-conjuntos)
7. [Subconsultas](#7-subconsultas)
   - [Retorno Escalar vs Múltiples Filas (ANY, ALL, IN)](#retorno-escalar-vs-multiples-filas-any-all-in)
   - [Operadores de Existencia (EXISTS, NOT EXISTS)](#operadores-de-existencia-exists-not-exists)

---

<a id="1-fundamentos-y-seleccion-de-datos"></a>
## 1. Fundamentos y Selección de Datos

### Estructura del SELECT y Opciones
La sentencia `SELECT` extrae información basada en dos conceptos: **Proyección** (columnas) y **Selección** (filas).
*   **Obligatorio:** `SELECT` (qué mostrar) y `FROM` (de dónde).
*   **Comodín universal:** `*` devuelve todas las columnas.
*   **Alias:** Sirven para cambiar la cabecera visible. Se usa `AS` o un simple espacio. Si lleva huecos en blanco, requiere **comillas dobles**.
*   **Filtros de duplicidad:**
    *   `ALL`: Por defecto, saca todo aunque se repita.
    *   `DISTINCT`: Elimina del resultado visual las filas idénticamente duplicadas. Ejemplo: `SELECT DISTINCT Ciudad FROM Clientes;`

### Paginación y Limitación (FETCH, OFFSET)
Oracle 12c+ permite limitar resultados de forma limpia:
- **`FETCH FIRST n ROWS ONLY`**: Solo los _n_ primeros resultados.
- **`OFFSET n ROWS`**: Salta los _n_ primeros (para paginar).
- **`WITH TIES`**: Incluye empates al final del ranking.
- **`ROWNUM`**: Pseudocolumna antigua para limitar (`WHERE ROWNUM <= 10`). No requiere `ORDER BY`.

```sql
SELECT nombre FROM emp ORDER BY sal DESC OFFSET 5 ROWS FETCH NEXT 5 ROWS ONLY;
```

### Filtrado (WHERE) y Ordenación (ORDER BY)
*   **WHERE:** Aplica las matemáticas restrictivas. **No permite usar alias definidos arriba ni funciones de agregación (`SUM`)**.
*   **ORDER BY:** Va SIEMPRE al final de la consulta.
    *   `ASC`: Ascendente (Por defecto). Los nulos (`NULL`) van al final del listado.
    *   `DESC`: Descendente. Los nulos van al principio.
    *   *Ordenación por Posición:* Puedes evitar escribir la columna indicando su número: `ORDER BY 3;` (ordena por la tercera columna del select).

---

<a id="2-operadores-en-sql"></a>
## 2. Operadores en SQL

### Relacionales y Aritméticos (Concatenación)
| Tipo | Operadores Prácticos | Ejemplo / Naturaleza |
| :--- | :--- | :--- |
| **Relacional** | `=`, `!=`, `< >`, `<`, `>`, `<=`, `>=` | `WHERE Edad >= 18` |
| **Listas** | `IN (x,y,z)`, `NOT IN` | Igual a cualquiera en la fila. |
| **Rango** | `BETWEEN x AND y`, `NOT BETWEEN` | Incluye x e y. |
| **Patrones** | `LIKE`, `NOT LIKE` | `%` (conjunto) y `_` (1 carácter exacto). |
| **Nulos** | `IS NULL`, `IS NOT NULL` | ¡Jamás usar `= NULL`! |
| **Aritmética** | `+`, `-`, `*`, `/` | Si se suma un nulo, devuelve nulo. |
| **Concatenar** | `\|\|` | `Nombre \|\| ' ' \|\| Apellido` |

### Lógicos y Precedencia
*   **`AND`**: Ambas condiciones deben cumplirse.
*   **`OR`**: Al menos una condición debe cumplirse.
*   **`NOT`**: Invierte el operador (ej. `NOT EXISTS`).
*   **Jerarquía de Evaluación:** `( Paréntesis )` ➔ `*, /` ➔ `+, -` ➔ `||` ➔ `Relacionales (=, LIKE)` ➔ `NOT` ➔ `AND` ➔ `OR` *(evalúa lo último)*.
*   *Siempre pon paréntesis en mezclas de AND y OR para evitar cruces mortales.*

---

<a id="3-funciones-escalares-opcionales-tabla-dual"></a>
## 3. Funciones Escalares (Tabla DUAL)

Devuelven 1 valor procesado por cada fila que lo pide. La tabla `DUAL` en Oracle tiene 1 fila y 1 columna, y se usa para cálculos al vuelo: `SELECT SYSDATE FROM DUAL;`

### Funciones Numéricas y de Cadenas
- **Números:** `ABS(-5)` ➔ 5; `CEIL(3.2)` ➔ 4; `FLOOR(3.8)` ➔ 3; `MOD(10, 3)` ➔ 1; `POWER(2, 3)` ➔ 8; `ROUND(1.58, 1)` ➔ 1.6; `TRUNC(1.58, 1)` ➔ 1.5.
- **Texto / Cadenas:**
  - `LOWER('A')`, `UPPER('a')`, `INITCAP('hola mundo')` ➔ Hola Mundo.
  - `SUBSTR('Palabra', 2, 3)` ➔ 'ala' (Empieza en pos 2, coge 3 letras).
  - `LENGTH('Texto')` ➔ Longitud numérica.
  - `REPLACE('Coche', 'o', 'a')` ➔ 'Cache'.
  - `TRIM(' hola ')` ➔ 'hola' (Quita espacios de los bordes).

### Manejo de Fechas y Conversiones (TO_CHAR, TO_DATE)
*   **Aritmética temporal:** Fecha + Número = Sumas días.
*   **Útiles:** `SYSDATE` (Hoy), `ADD_MONTHS(f, 2)`, `MONTHS_BETWEEN(f1, f2)`, `LAST_DAY(f)`, `EXTRACT(YEAR FROM f)`.
*   **Conversiones de formato estrictas (Salvavidas SQL):**
    *   `TO_NUMBER('10')`: Para operar textos. (Formatos: `9` y `0`).
    *   `TO_CHAR(SYSDATE, 'DD/MM/YYYY')`: Para forzar fecha a texto imprimible o sacar los años con 4 dítigos `YYYY`.
    *   `TO_DATE('20-05-24', 'DD-MM-YY')`: Obligatorio al insertar fechas manuales.

### Control de Nulos (NVL, NVL2, CASE)
Un valor nulo destruye una suma. Se ataca con:
*   `NVL(campo, valor_si_es_nulo)`: `Salario + NVL(Comision, 0)`.
*   `NVL2(campo, valor_si_no_es_nulo, valor_si_es_nulo)`.
*   `CASE WHEN ... THEN ... ELSE ... END`: El condicional universal estándar.

---

<a id="4-agrupamientos-y-resumenes"></a>
## 4. Agrupamientos y Resúmenes

Convierten miles de filas en estadísticas verticales únicas. (Si la columna tiene un `NULL`, lo ignoran, salvo en el conteo total masivo).

### Funciones de Agregado (SUM, COUNT, AVG)
*   `COUNT(*)`: Cuenta el bloque de filas entero. Es inofensivo al desastre de nulos.
*   `COUNT(campo)`: Cuenta solo donde el campo tenga datos.
*   `SUM(x)`, `AVG(x)` (Media), `MIN(x)`, `MAX(x)`.

### GROUP BY y HAVING (Reglas de Oro)
Para sacar subtotales (ej. Gastos agrupados por Ciudad).
1.  **Regla Destructiva:** Si pones en el `SELECT` una provincia (`campo`), pero también metes un `SUM(Salario)`. **Estás matemáticamente OBLIGADO a poner la provincia en el `GROUP BY`**. (Si no, explota).
2.  **`WHERE` vs `HAVING`:**
    *   `WHERE` se ejecuta **antes** de hacer los grupos. Evalúa fila a fila. No puedes preguntarle `WHERE SUM(Saldo) > 10`.
    *   `HAVING` se ejecuta **después** y es el guardián de las funciones de agregado.

```sql
SELECT Depto, COUNT(*) AS "Plantilla", AVG(Salario) AS "MediaSalarial"
FROM EMPLEADOS
WHERE Estado = 'Fijo'          -- Filtro inicial
GROUP BY Depto                 -- Columna no agregada en SELECT va aquí
HAVING COUNT(*) > 10;          -- Filtro a la agregación
```

---

<a id="5-consultas-multitabla-joins"></a>
## 5. Consultas Multitabla (JOINs)

Evitan los masivos (y mortales) Productos Cartesianos uniendo eficientemente a las Claves Foráneas con sus Primarias. Necesitas atar **N-1** lados.

### Sintaxis Clásica Oracle (Interna y Externa `(+)`)
Todo va al `WHERE`. Se mezclan filtros lógicos con condiciones de ensamblaje.

*   **Composición Interna:** Si en la tabla A no hay equivalencia en la B, la fila se pierde de la visualización y se oculta.
    *   `SELECT E.Nombre, D.Nombre_Dpto FROM EMPLEADOS E, DEPARTAMENTOS D WHERE E.Id_Depto = D.Id_Depto;`
*   **Composición Externa `(+)`:** Obliga a mostrar todo lo de la otra tabla, aunque estemos huérfanos. **El (+) se pone en el campo DE LA TABLA POBRE DE LA IGUALDAD (a la que le pueden faltar las coincidencias)**.
    *   `WHERE D.Jefe_Dni = E.Dni(+);` ➔ "Sácame todos los Dep, y si a ese Departamento le falta jefe, rellena los datos del empleado del select con NULL sin borrarme nada."

### Sintaxis Moderna SQL99 (ON, USING, OUTER JOIN)
El nuevo estándar internacional. Todo el cruce estructural se hace en el `FROM`, dejando `WHERE` inmaculado para filtros lógicos puros de negocio.

*   `INNER JOIN / JOIN ... ON(x=y)`: Cruce interno seguro clásico. `... FROM EMP JOIN DEP ON (EMP.id_dept = DEP.id);`
*   `NATURAL JOIN`: Cruce por magia si las tablas tienen columnas idénticas. Útil pero inseguro en grandes sistemas.
*   `LEFT OUTER JOIN`: Se queda y muestra todo lo de la lista original de la Izquierda.
*   `RIGHT OUTER JOIN`: Se queda y muestra todo lo de la lista del JOIN (la Derecha).
*   `FULL OUTER JOIN`: Todos con todos, mostrando nulos huecos mútamente. 

---

<a id="6-operaciones-de-conjuntos"></a>
## 6. Operaciones de Conjuntos

Manejan bloques completos resultantes de dos sentencias `SELECT` de la misma anchura, mismo tipo y mismo orden milimétrico de columnas.

1.  **`UNION` / `UNION ALL`:** Suma dos consultas largas de abajo arriba. (Con ALL salen repeticiones, sin él suprime duplicados matemáticos idénticos).
2.  **`INTERSECT`:** Devuelve únicamente aquellos listados de registros que hayan cruzado vivos y existan simultáneamente en el Set de arriba y el Set de abajo a la vez.
3.  **`MINUS`:** A la tabla superior, entrégame el listado final tras **restarle/eliminarle** todas las presencias de las filas que retornó la tabla inferior.

---

<a id="7-subconsultas"></a>
## 7. Subconsultas

Poder puro. Inyectar `( SELECT ... )` en cláusulas WHERE, HAVING o FROM para compararlas.
**Regla:** A la **derecha** del operador SIEMPRE entre paréntesis obligatorios.

### Retorno Escalar (1 sola celda)
Usa los operadores igualitarios simples (`=, <, >`).
```sql
SELECT Nombre, Salario FROM EMPLEADOS 
WHERE Salario > (SELECT AVG(Salario) FROM EMPLEADOS);
```

### Múltiples Filas (ANY, ALL, IN)
Si la matriz devuelve múltiples líneas de datos, Oracle se quejará con un `error ORA-01427` si usas `=`.

*   **`IN`** / **`NOT IN`**: Determinar si un campo de la tabla superior pertenece a alguna de las salidas listadas del Sub-select inferior (Devolviéndolo como una mochila arrastrada).
    *   Ej: `WHERE ID_Juego IN (SELECT ID FROM MAS_VENDIDOS WHERE Mes = 4);`
*   **`> ANY`** / **`< ANY`**: Verdadero si le ganas o pierdes contra **CUALQUIERA** individual del conjunto.
*   **`> ALL`** / **`< ALL`**: Verdadero si sobrevive la comparación matemática frente a **TODOS Y ABSOLUTAMENTE CADA UNO** de los listados producidos por el Sub-select en la nube. (Literalmente superar al número máximo/mínimo absoluto extraído de golpe).

### Operadores de Existencia (EXISTS, NOT EXISTS)
Comprueban si la subconsulta devuelve **algún** dato. Devuelven True/False. No necesitan columnas específicas.
- **`EXISTS`**: Cierto si hay resultados.
- **`NOT EXISTS`**: Cierto si no hay resultados.

```sql
-- Empleados con facturas emitidas:
WHERE EXISTS (SELECT * FROM facturas f WHERE f.id_emp = emp.id);
```
