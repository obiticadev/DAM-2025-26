# 📖 Manual Completo de Consultas Oracle SQL (Tema 4)

Este documento unifica todo el temario relacionado con el **Tema 4 de Bases de Datos**, agrupando en un único manual estructurado, profesional y sin pérdida de información, los bloques de consultas básicas, el uso avanzado de funciones y agrupamientos, y por último, la manipulación relacional mediante combinaciones multi-tabla y subconsultas.

---

## 📑 Índice General

*   **[PARTE I. CONSULTAS BÁSICAS Y SELECCIÓN DE DATOS](#parte-i-consultas-básicas-y-selección-de-datos)**
    *   [1. La sentencia SELECT](#1-la-sentencia-select)
        *   [1.1. Detalles de la Cláusula SELECT](#11-detalles-de-la-cláusula-select)
        *   [1.2. Detalles de la Cláusula FROM](#12-detalles-de-la-cláusula-from)
        *   [1.3. Detalles de la Cláusula WHERE](#13-detalles-de-la-cláusula-where)
        *   [1.4. Paginación y Limitación: Cláusula FETCH (Row Limiting)](#14-paginación-y-limitación-cláusula-fetch)
        *   [1.5. Ordenación de registros: Cláusula ORDER BY](#15-ordenación-de-registros-cláusula-order-by)
    *   [2. Operadores](#2-operadores)
        *   [2.1. Operadores de comparación (Relacionales)](#21-operadores-de-comparación-relacionales)
        *   [2.2. Operadores aritméticos y de concatenación](#22-operadores-aritméticos-y-de-concatenación)
        *   [2.3. Operadores Lógicos](#23-operadores-lógicos)
        *   [2.4. Precedencia de Operadores](#24-precedencia-de-operadores)
    *   [3. Consultas Calculadas](#3-consultas-calculadas)
*   **[PARTE II. FUNCIONES E IDENTIFICACIÓN BÁSICA DE AGRUPAMIENTOS](#parte-ii-funciones-y-agrupamientos)**
    *   [4. Funciones Escalares](#4-funciones-escalares)
        *   [4.1. Funciones Numéricas](#41-funciones-numéricas)
        *   [4.2. Funciones de Cadenas de Caracteres](#42-funciones-de-cadenas-de-caracteres)
        *   [4.3. Funciones de Manejo de Fechas](#43-funciones-de-manejo-de-fechas)
        *   [4.4. Funciones de Conversión](#44-funciones-de-conversión)
        *   [4.5. Control de Nulos y Evaluaciones Condicionales](#45-control-de-nulos-y-evaluaciones-condicionales)
    *   [5. Consultas de Resumen (Funciones de Agregado)](#5-consultas-de-resumen-funciones-de-agregado)
        *   [5.1 Funciones Básicas: SUM y COUNT](#51-funciones-básicas-sum-y-count)
        *   [5.2 Funciones de Extremos: MIN y MAX](#52-funciones-de-extremos-min-y-max)
        *   [5.3 Funciones Estadísticas: AVG, VAR, STDEV](#53-funciones-estadísticas-avg-var-stdev)
    *   [6. Agrupamiento de Registros (GROUP BY y HAVING)](#6-agrupamiento-de-registros-group-by-y-having)
*   **[PARTE III. CONSULTAS MULTITABLA Y SUBCONSULTAS](#parte-iii-consultas-multitabla-y-subconsultas)**
    *   [7. Consultas Multitablas: Conceptos Básicos](#7-consultas-multitablas-conceptos-básicos)
        *   [7.1. Composiciones Internas (Sintaxis Clásica con WHERE)](#71-composiciones-internas-sintaxis-clásica-con-where)
        *   [7.2. Composiciones Externas (Sintaxis Tradicional de Oracle)](#72-composiciones-externas-sintaxis-tradicional-de-oracle)
        *   [7.3. Composiciones Modernas (Sintaxis SQL99)](#73-composiciones-modernas-sintaxis-sql99)
    *   [8. Operaciones de Conjuntos](#8-operaciones-de-conjuntos)
    *   [9. Subconsultas](#9-subconsultas)
        *   [9.1. Subconsultas de Fila Única](#91-subconsultas-de-fila-única)
        *   [9.2. Subconsultas de Múltiples Filas (IN, ANY, ALL)](#92-subconsultas-de-múltiples-filas-in-any-all)
        *   [9.3. Operadores de Existencia (EXISTS y NOT EXISTS)](#93-operadores-de-existencia-exists-y-not-exists)

---
---

<a id="parte-i-consultas-básicas-y-selección-de-datos"></a>
# PARTE I. CONSULTAS BÁSICAS Y SELECCIÓN DE DATOS

Esta primera sección recoge todos los conceptos, cláusulas, operadores y particularidades primordiales para la realización de consultas simples a una sola tabla de la base de datos.

<a id="1-la-sentencia-select"></a>
## 1. La sentencia SELECT

Para recuperar o seleccionar datos de una o varias tablas se utiliza la sentencia `SELECT`. Esta operación se basa en dos conceptos relacionales:
*   **Proyección:** Elegir qué columnas queremos ver.
*   **Selección:** Elegir qué filas (registros) queremos ver basándonos en criterios.

La sentencia consta de cuatro partes básicas, siendo dos obligatorias y dos opcionales:

1. **Cláusula SELECT** (Obligatoria): Indica qué nombres de columnas queremos que se muestren.
2. **Cláusula FROM** (Obligatoria): Indica de qué tabla(s) se extraerán los datos.
3. **Cláusula WHERE** (Opcional): Establece un criterio de selección o condición para filtrar los datos.
4. **Cláusula ORDER BY** (Opcional): Establece el criterio de ordenación.

### Sintaxis General
```sql
SELECT [ALL | DISTINCT] columna1, columna2, ...
FROM tabla1, tabla2, ...
WHERE condición1, condición2, ...
ORDER BY ordenación;
```

### Opciones ALL y DISTINCT
*   **`ALL`**: Es el valor por defecto. Selecciona todas las filas, aunque estén repetidas.
*   **`DISTINCT`**: Suprime aquellas filas del resultado que tengan igual valor que otras (elimina duplicados).

> **CASO DE USO PRÁCTICO (`DISTINCT`):** 
> *Problema:* Queremos saber en qué ciudades tenemos clientes, pero un cliente puede vivir en la misma ciudad que otro, lo que nos daría una lista llena de ciudades repetidas.
> *Solución:* Usar `DISTINCT` para un listado limpio.
> ```sql
> SELECT DISTINCT ciudad FROM CLIENTES;
> ```

---

<a id="11-detalles-de-la-cláusula-select"></a>
### 1.1. Detalles de la Cláusula SELECT

Esta cláusula define exactamente **qué** queremos visualizar en el resultado.

*   **Nombrar columnas con su origen**: Útil para evitar ambigüedades.
    ```sql
    SELECT USUARIOS.Nombre, USUARIOS.Apellidos FROM USUARIOS;
    ```
*   **Seleccionar todas las columnas**: Usamos asterisco (`*`).
    ```sql
    SELECT * FROM EMPLEADOS;
    ```
*   **Alias de columnas**: Cambia el nombre de la cabecera. Si el alias contiene espacios o caracteres especiales, debe ir entre **comillas dobles**.
    ```sql
    -- Alias simple sin comillas
    SELECT SALARIO AS Sueldo FROM EMPLEADOS;
    
    -- Alias complejo con espacios
    SELECT F_Nacimiento "Fecha de Nacimiento" FROM USUARIOS;
    ```
*   **Uso de constantes, expresiones y funciones**:
    ```sql
    -- Inyectar una constante en todas las filas resultados
    SELECT Nombre, 'Activo' AS "Estado de Cuenta" FROM USUARIOS;
    ```

---

<a id="12-detalles-de-la-cláusula-from"></a>
### 1.2. Detalles de la Cláusula FROM

Define de **dónde** se obtienen las columnas.

*   **Consultas combinadas (JOIN clásicos)**: Se separan por comas. (Se detalla en otro documento).
*   **Propietario de la tabla**: `ESQUEMA.TABLA`.
    ```sql
    SELECT * FROM HR.EMPLOYEES;
    ```
*   **Alias de tablas**: Abreviatura para simplificar consultas. Para asignarlo se deja un espacio (no `" "`).
    ```sql
    SELECT u.Nombre, u.Apellidos 
    FROM USUARIOS u; 
    ```

---

<a id="13-detalles-de-la-cláusula-where"></a>
### 1.3. Detalles de la Cláusula WHERE

Se utiliza para restringir la selección a un subconjunto de filas que cumplan una condición determinada. Va a continuación de `WHERE`.

> **CASO DE USO PRÁCTICO (`WHERE` con textos e ignorando mayúsculas):**
> *Problema:* Buscar empleados que se llamen "Juan", pero en la BD podría estar guardado como "JUAN", "juan" o "Juan".
> *Solución:* Convertir a mayúsculas al vuelo.
> ```sql
> SELECT nombre, apellidos FROM EMPLEADOS WHERE UPPER(nombre) = 'JUAN';
> ```

---

<a id="14-paginación-y-limitación-cláusula-fetch"></a>
### 1.4. Paginación y Limitación: Cláusula FETCH (Row Limiting)

Introducida en versiones modernas (Oracle 12c+), permite limitar el número de filas devueltas de forma limpia, ideal para "Top N" o paginación web.

**Sintaxis Básica:**
```sql
[ OFFSET n ROWS ]
FETCH { FIRST | NEXT } [ n | n PERCENT ] ROWS { ONLY | WITH TIES };
```

*   **FETCH FIRST n ROWS ONLY**: Devuelve exactamente las primeras n filas.
*   **OFFSET n ROWS**: Se salta las primeras n filas (útil para ver la "página 2").
*   **WITH TIES**: Si hay empate en el último registro (según el `ORDER BY`), los devuelve todos.
*   **PERCENT**: Permite extraer un porcentaje del total (ej. el 10% que más cobra).

> **EJEMPLOS DE LIMITACIÓN:**
> ```sql
> -- 1. Los 3 que más cobran:
> SELECT * FROM EMP ORDER BY SAL DESC FETCH FIRST 3 ROWS ONLY;
> 
> -- 2. Paginación (saltar 5 y ver los 5 siguientes):
> SELECT * FROM EMP ORDER BY ENAME OFFSET 5 ROWS FETCH NEXT 5 ROWS ONLY;
> ```

---

<a id="15-ordenación-de-registros-cláusula-order-by"></a>
### 1.5. Ordenación de registros: Cláusula ORDER BY

Permite especificar cómo se ordenará la respuesta de la consulta.

#### Sintaxis
```sql
ORDER BY columna1 [ASC | DESC], columna2 [ASC | DESC];
```

#### Reglas y Detalles
*   **Tipos de datos**: Se pueden ordenar caracteres, números o fechas.
*   **Dirección**: `ASC` (defecto) o `DESC`.
*   **Ordenación múltiple**:
    ```sql
    -- Ordena por Apellido de A a Z, y si hay dos iguales, ordena por Salario de mayor a menor.
    SELECT nombre, apellidos, salario 
    FROM EMPLEADOS 
    ORDER BY apellidos ASC, salario DESC;
    ```
*   **Ordenación por posición**: 
    ```sql
    SELECT nombre, apellidos, localidad 
    FROM usuarios 
    ORDER BY 3; -- Ordena por localidad
    ```

---

<a id="2-operadores"></a>
## 2. Operadores

<a id="21-operadores-de-comparación-relacionales"></a>
### 2.1. Operadores de comparación (Relacionales)

| Operador | Significado |
| :--- | :--- |
| `=`, `!=`, `< >`, `^=` | Igualdad / Desigualdad (Las 3 últimas equivalentes) |
| `<`, `>`, `<=`, `>=` | Menor, Mayor, etc. |
| `IN` / `NOT IN` | Pertenece / No pertenece a una lista |
| `BETWEEN` / `NOT BETWEEN` | Dentro / Fuera de un rango numérico o de fechas (inclusivo) |
| `LIKE` | Comparación de patrones (`%` conjunto, `_` un carácter) |
| `IS NULL` / `IS NOT NULL`| Comprobación de nulos |

*   **Valores `NULL`**: Nunca usar `=` con `NULL`. Siempre `IS NULL`. En `ORDER BY ASC`, los nulos van al final.

> **EJEMPLOS PRÁCTICOS DE OPERADORES RELACIONALES:**
> ```sql
> -- IN: Buscar usuarios de ciudades específicas
> SELECT * FROM USUARIOS WHERE Localidad IN ('Madrid', 'Barcelona', 'Valencia');
> 
> -- BETWEEN: Filtrar por rango de fechas (el between INCLUYE los límites)
> SELECT * FROM EMPLEADOS WHERE Fecha_Alta BETWEEN '01/01/2023' AND '31/12/2023';
> 
> -- LIKE: Correo termina en @gmail.com
> SELECT Correo FROM CLIENTES WHERE Correo LIKE '%@gmail.com';
> 
> -- LIKE: Nombre de 4 letras que empieza por 'P'
> SELECT Nombre FROM ALUMNOS WHERE Nombre LIKE 'P___';
> ```

---

<a id="22-operadores-aritméticos-y-de-concatenación"></a>
### 2.2. Operadores aritméticos y de concatenación

*   **Aritméticos**: `+`, `-`, `*`, `/`. Si se opera sobre `NULL`, el resultado es `NULL`.
*   **Concatenación (`||`)**: Une campos.

> **CASOS DE USO (Concatenación y Cálculos):**
> ```sql
> -- Mostrar una ficha legible en una sola columna:
> SELECT 'El empleado ' || Nombre || ' ' || Apellidos || ' tiene un salario de ' || Salario AS "Ficha" FROM EMPLEADOS;
> 
> -- Aumento salarial del 10%
> SELECT Salario, Salario * 1.10 AS "Salario Aumentado" FROM EMPLEADOS;
> ```

---

<a id="23-operadores-lógicos"></a>
### 2.3. Operadores Lógicos

Permiten evaluar múltiples condiciones conjuntas: `AND`, `OR`, `NOT`.

> **EJEMPLOS DE COMPLEJIDAD LÓGICA:**
> ```sql
> -- Empleados del departamento 10 con salario mayor a 2000
> SELECT * FROM EMPLEADOS WHERE Dept_no = 10 AND Salario > 2000;
> 
> -- Empleados directivos (dep 10) o del departamento informático (dep 20)
> SELECT * FROM EMPLEADOS WHERE Dept_no = 10 OR Dept_no = 20; 
> -- (Equivalente a: WHERE Dept_no IN (10, 20))
> 
> -- Empleados que NO son de Madrid
> SELECT * FROM EMPLEADOS WHERE NOT Localidad = 'Madrid';
> ```

---

<a id="24-precedencia-de-operadores"></a>
### 2.4. Precedencia de Operadores

Prioridad (de mayor a menor):
1.  Paréntesis (Uso indispensable para evitar errores lógicos).
2.  `*`, `/`
3.  `+`, `-`
4.  `||`
5.  Relacionales (`=`, `<`, `LIKE`, `IN`...)
6.  `NOT`
7.  `AND`
8.  `OR`

> **CASO DE USO (Precedencia):**
> *Problema:* Queremos administradores que ganen más de 2000 o usuarios de soporte que ganen más de 2000.
> *Mala consulta:* `WHERE Rol = 'Admin' OR Rol = 'Soporte' AND Salario > 2000` (El AND se ejecuta antes, daría soporte con >2000 y TODOS los Admins, sean del salario que sean).
> *Buena consulta:*
> ```sql
> SELECT * FROM EMPLEADOS WHERE (Rol = 'Admin' OR Rol = 'Soporte') AND Salario > 2000;
> ```

---

<a id="3-consultas-calculadas"></a>
## 3. Consultas Calculadas

Operaciones dinámicas cuyos campos no modifican las tablas reales, únicamente se procesan para la impresión.

> **CASOS DE USO (Consultas Calculadas Avanzadas):**
> ```sql
> -- Calcular el precio de productos aplicando IVA (21%) y nombrarlo
> SELECT Cod_Producto, Precio_Base, (Precio_Base * 1.21) AS Precio_Con_Iva 
> FROM PRODUCTOS;
> 
> -- Calcular beneficio anual a partir del beneficio mensual asumiendo valores constantes, descontando 50 fijos.
> SELECT Division, Beneficio_Mensual, ((Beneficio_Mensual * 12) - 50) AS Beneficio_Neto_Anual
> FROM SUCURSALES;
> ```

---
---

<a id="parte-ii-funciones-y-agrupamientos"></a>
# PARTE II. FUNCIONES E IDENTIFICACIÓN BÁSICA DE AGRUPAMIENTOS

En esta sección se explican las operaciones incorporadas de fábrica en el SGBD y cómo utilizarlas para derivar estadísticas (funciones de agrupación) de varios registros de golpe.
*   **Se pueden anidar** (unas dentro de otras).
*   Se pueden usar en las cláusulas `SELECT`, `WHERE` y `ORDER BY`.
*   **La Tabla DUAL**: Oracle proporciona una tabla especial llamada `DUAL` con una sola fila y una sola columna (`DUMMY`). Sirve exclusivamente para hacer pruebas o ejecutar cálculos.

<a id="4-funciones-escalares"></a>
## 4. Funciones Escalares

Las funciones escalares devuelven un único valor por cada fila procesada individualmente.

<a id="41-funciones-numéricas"></a>
### 4.1. Funciones Numéricas
Operan con valores numéricos y devuelven un resultado numérico.

| Función | Descripción | Ejemplo | Resultado |
| :--- | :--- | :--- | :--- |
| **`ABS(n)`** | Valor absoluto. | `SELECT ABS(-17) FROM DUAL;` | **17** |
| **`CEIL(n)`** | Entero inmediatamente superior (Techo). | `SELECT CEIL(17.4) FROM DUAL;` | **18** |
| **`FLOOR(n)`** | Entero inmediatamente inferior (Suelo). | `SELECT FLOOR(17.8) FROM DUAL;` | **17** |
| **`MOD(m, n)`** | Resto de la división. | `SELECT MOD(15, 2) FROM DUAL;` | **1** |
| **`POWER(v, e)`**| Exponenciación. | `SELECT POWER(4, 5) FROM DUAL;` | **1024** |
| **`ROUND(n, d)`**| Redondeo aritmético a _d_ decimales. | `SELECT ROUND(12.5874, 2) FROM DUAL;` | **12.59** |
| **`TRUNC(n, d)`**| Truncamiento (corte) sin redondear. | `SELECT TRUNC(12.5874, 2) FROM DUAL;` | **12.58** |
| **`SIGN(n)`** | Retorna signo (1, -1, 0). | `SELECT SIGN(-23) FROM DUAL;` | **-1** |

> **CASOS DE USO PRÁCTICOS:**
> ```sql
> -- Identificar años de servicio completos (ignorando meses extraños):
> SELECT Nombre, TRUNC((SYSDATE - Fecha_Contratacion)/365) AS "Años de Servicio" FROM EMPLEADOS;
> 
> -- Redondeo del salario a dos decimales tras un aumento (evitar problemas contables):
> SELECT Empleado, ROUND(Salario * 1.055, 2) AS Salario_Final FROM NOMINAS;
> ```

---

<a id="42-funciones-de-cadenas-de-caracteres"></a>
### 4.2. Funciones de Cadenas de Caracteres
Sirven para manipular texto, limpiar entrada de datos y estandarizar formatos.

| Función | Descripción | Ejemplo | Resultado |
| :--- | :--- | :--- | :--- |
| **`CONCAT(c1,c2)`**| Une dos cadenas (como `\|\|`). | `SELECT CONCAT('A', 'B') FROM DUAL;` | **AB** |
| **`LOWER(cad)`** | A minúsculas. | `SELECT LOWER('TXT') FROM DUAL;` | **txt** |
| **`UPPER(cad)`** | A mayúsculas. | `SELECT UPPER('txt') FROM DUAL;` | **TXT** |
| **`INITCAP(cad)`** | Primera letra mayúscula, resto min. | `SELECT INITCAP('holA') FROM DUAL;` | **Hola** |
| **`SUBSTR(c,m,n)`**| Extrae _n_ carácteres desde pos _m_. | `SELECT SUBSTR('12345', 2, 3) FROM DUAL;` | **234** |
| **`LENGTH(cad)`** | Longitud de cadena. | `SELECT LENGTH('hola') FROM DUAL;` | **4** |
| **`REPLACE(c,x,y)`**| Reemplaza _x_ por _y_ dentro de _c_. | `SELECT REPLACE('1-A', '-', '/') FROM DUAL;`| **1/A** |
| **`TRIM(cad)`** / `LTRIM` / `RTRIM` | Elimina espacios al principio/final. | `SELECT LTRIM('  TXT') FROM DUAL;` | **TXT** |
| **`INSTR(c, x)`**| Devuelve posición de un caracter _x_. | `SELECT INSTR('hola', 'l') FROM DUAL;` | **3** |

> **CASOS DE USO PRÁCTICOS:**
> ```sql
> -- 1. Encontrar personas cuyo nombre empiece con la misma letra que su apellido:
> SELECT Nombre, Apellidos FROM USUARIOS
> WHERE SUBSTR(Nombre, 1, 1) = SUBSTR(Apellidos, 1, 1);
> 
> -- 2. Generar el correo corporativo (primera inicial + apellido):
> SELECT LOWER(SUBSTR(Nombre, 1, 1) || Apellidos) || '@empresa.com' AS Correo FROM EMPLEADOS;
> ```

---

<a id="43-funciones-de-manejo-de-fechas"></a>
### 4.3. Funciones de Manejo de Fechas

*   **`DATE`**: Almacena fecha e incluye a veces la hora.
*   **`TIMESTAMP`**: Almacena tiempo exacto incluyendo fracciones de segundo.
*   Sumar o restar números a fechas implica días. (Ej. `SYSDATE + 7` = Dentro de una semana).

| Función | Descripción | Ejemplo |
| :--- | :--- | :--- |
| **`SYSDATE`** | Fecha y hora actuales. | `SYSDATE` |
| **`ADD_MONTHS(f, n)`** | Suma _n_ meses a la fecha _f_. | `ADD_MONTHS('12/05/2023', 2)` ➔ **12/07/2023** |
| **`MONTHS_BETWEEN(f1,f2)`**| Meses entre dos fechas. | `MONTHS_BETWEEN(f1, f2)` |
| **`LAST_DAY(f)`** | Último día del mes de esa fecha. | `LAST_DAY('15/02/2024')` ➔ **29/02/2024** |
| **`EXTRACT(campo FROM f)`**| Extrae el Año, Mes, o Día. | `EXTRACT(YEAR FROM SYSDATE)` ➔ **2024** |

> **CASOS DE USO PRÁCTICOS:**
> ```sql
> -- 1. Calcular cuándo se cumple el periodo de prueba de 3 meses:
> SELECT Nombre, ADD_MONTHS(Fecha_Ingreso, 3) AS Fin_Prueba FROM CONTRATOS;
> 
> -- 2. Extraer solo a los nacidos en 1990:
> SELECT * FROM USUARIOS WHERE EXTRACT(YEAR FROM F_Nacimiento) = 1990;
> ```

---

<a id="44-funciones-de-conversión"></a>
### 4.4. Funciones de Conversión

Las conversiones explícitas aseguran de que no haya fallos al interpretar una cadena como fecha o un número.

*   `TO_NUMBER(cad, formato)`: Texto a Número. (Formatos: `9` relleno blanco, `0` rellena con cero, `L` Moneda local...).
*   `TO_CHAR(dato, formato)`: Número o Fecha a Texto con formato específico.
*   `TO_DATE(cad, formato)`: Texto a Fecha real.
    *   *Año*: `YYYY` (2024), `YY` (24).
    *   *Mes*: `MM` (05), `MON` (MAY), `MONTH` (MAYO).
    *   *Día*: `DD` (12), `DAY` (LUNES).

> **CASOS DE USO PRÁCTICOS:**
> ```sql
> -- 1. Mostrar la fecha de forma agradable al usuario (TO_CHAR):
> SELECT TO_CHAR(SYSDATE, 'DD "de" MONTH "de" YYYY') FROM DUAL; 
> -- Resultado: 25 de OCTUBRE de 2024
> 
> -- 2. Insertar una fecha específica de forma segura (TO_DATE):
> SELECT * FROM EVENTOS WHERE Fecha = TO_DATE('2024-12-31', 'YYYY-MM-DD');
> ```

---

<a id="45-control-de-nulos-y-evaluaciones-condicionales"></a>
### 4.5. Control de Nulos y Evaluaciones Condicionales

*   **`NVL(valor, expr1)`**: Si `valor` es nulo, devuelve `expr1`.
*   **`NVL2(expr1, expr2, expr3)`**: Si `expr1` no es nulo devuelve `expr2`, si no `expr3`.
*   **`DECODE`**: Alternativa clásica (if-else if).
*   **`CASE`**: Sentencia condicional estándar, muy legible.

> **CASO DE USO (Calculando sin fallar por culpa de NULOS):**
> *Problema:* Las Comisiones pueden ser NULL. Sumar Salario + Comision = NULL. 
> ```sql
> -- 1. Suma correcta considerando 0 si es nulo.
> SELECT Nombre, Salario + NVL(Comision, 0) AS Sueldo_Total FROM VENDEDORES;
> 
> -- 2. Crear Categorías salariales
> SELECT Nombre, Salario,
>   CASE 
>     WHEN Salario > 3000 THEN 'Alto'
>     WHEN Salario BETWEEN 1500 AND 3000 THEN 'Medio'
>     ELSE 'Bajo'
>   END AS Rango
> FROM EMPLEADOS;
> ```

---

<a id="5-consultas-de-resumen-funciones-de-agregado"></a>
## 5. Consultas de Resumen (Funciones de Agregado)

Permiten realizar cálculos verticales sobre una columna entera (o grupo de datos), devolviendo un **único dato que resume al grupo**.
*   **Importante:** Se ignoran los valores `NULL` automáticamente (excepto con `COUNT(*)`).

<a id="51-funciones-básicas-sum-y-count"></a>
### 5.1 Funciones Básicas: SUM y COUNT
*   `SUM(campo)`: Suma de la columna.
*   `COUNT(campo)`: Cuenta elementos distintos de nulo en la columna.
*   `COUNT(*)`: Cuenta *todas las filas* (incluso nulas).

<a id="52-funciones-de-extremos-min-y-max"></a>
### 5.2 Funciones de Extremos: MIN y MAX
*   `MIN(campo)` y `MAX(campo)`: Valor mínimo/máximo. Válido también para Fechas y Textos (orden alfabético).

<a id="53-funciones-estadísticas-avg-var-stdev"></a>
### 5.3 Funciones Estadísticas: AVG, VAR, STDEV
*   `AVG(campo)` (Media), `VAR(campo)` (Varianza), `STDEV(campo)` (Desviación).

> **CASOS DE USO PRÁCTICOS:**
> ```sql
> -- Estadísticas de la tabla EMPLEADOS en una sola consulta
> SELECT 
>   COUNT(*) AS Total_Empleados,
>   SUM(Salario) AS Gasto_Nominas,
>   MAX(Salario) AS Sueldo_Mas_Alto,
>   MIN(Fecha_Alta) AS Empleado_Mas_Antiguo,
>   ROUND(AVG(Salario), 2) AS Salario_Promedio
> FROM EMPLEADOS;
> ```

---

<a id="6-agrupamiento-de-registros-group-by-y-having"></a>
## 6. Agrupamiento de Registros (GROUP BY y HAVING)

Cuando queremos sacar subtotales (ej. Gasto salarial *por departamento*), aplicamos `GROUP BY`.

#### Sintaxis Completa y Orden Lógico
```sql
SELECT Departamento, AVG(Salario)
FROM EMPLEADOS
WHERE Estado = 'Activo'       -- 1. Filtra las filas base (Solo los activos)
GROUP BY Departamento         -- 2. Crea las agrupaciones
HAVING AVG(Salario) > 2000    -- 3. Filtra la agrupación resultante
ORDER BY 2 DESC;              -- 4. Ordena el formato visual final
```

#### Reglas Esenciales
1.  Las columnas del `SELECT` que **no** formen parte de funciones de agregación (`SUM`, `MAX`, `AVG`...), **tienen que estar estrictamente obligadas** a aparecer en el `GROUP BY`.
2.  `WHERE` asocia condiciones basadas en filas. No admite `SUM()` ni parecidos.
3.  `HAVING` asocia condiciones basadas en las funciones de agregado calculadas en el agrupamiento.

> **CASOS DE USO PRÁCTICOS AVANZADOS:**
> ```sql
> -- 1. Gasto total del departamento 10 agrupado por el Puesto/Rol de trabajo:
> SELECT Rol, SUM(Salario) AS Gasto_Total
> FROM EMPLEADOS
> WHERE Cod_Dept = 10         -- Filtramos a la base
> GROUP BY Rol;               -- Rompemos las filas que pasaron en grupos según Rol
> 
> -- 2. Conocer qué departamentos tienen más de 5 empleados en total:
> SELECT Cod_Dept, COUNT(*) AS Total_Miembros
> FROM EMPLEADOS
> GROUP BY Cod_Dept
> HAVING COUNT(*) > 5;        -- El WHERE no puede filtrar COUNT! Se debe usar HAVING.
> 
> -- 3. Agrupaciones Múltiples (Por Departamento y luego por Género):
> SELECT Cod_Dept, Sexo, COUNT(*) AS Cuantos, ROUND(AVG(Salario), 2) AS Media_Salarial
> FROM EMPLEADOS
> GROUP BY Cod_Dept, Sexo
> ORDER BY Cod_Dept;
> ```

---
---

<a id="parte-iii-consultas-multitabla-y-subconsultas"></a>
# PARTE III. CONSULTAS MULTITABLA Y SUBCONSULTAS

En bases de datos relacionales, la información se distribuye en varias tablas. Para recuperar información conjunta, es necesario realizar consultas que involucren a más de una tabla simultáneamente.

Las composiciones y las subconsultas son el núcleo del SQL empresarial.

<a id="7-consultas-multitablas-conceptos-básicos"></a>
## 7. Consultas Multitablas: Conceptos Básicos

Si combinamos dos tablas en el `FROM` sin restricción (`SELECT * FROM TABLA1, TABLA2`), el resultado es el **Producto Cartesiano**. Una tabla de 10 filas con otra de 10 generará 100 filas, mezclando todo con todo. Para evitar esto, usamos el emparejamiento lógicamente validado (JOIN) de claves externas con primarias.

<a id="71-composiciones-internas-sintaxis-clásica-con-where"></a>
### 7.1. Composiciones Internas (Sintaxis Clásica con WHERE)

El enfoque tradicional consiste en poner las tablas separadas por comas en el `FROM` y establecer la igualdad limitadora obligatoria en el `WHERE`.

**Reglas de Oro:**
*   Hacen falta al menos **`N-1`** condiciones de emparejamiento (Si usas 3 tablas, al menos 2 igualdades).
*   Prefijar **siempre** los campos con el `NombreTabla.` o usar un *alias* (`T1.Campo`).

> **CASOS DE USO PRÁCTICOS (JOIN CLÁSICO):**
> ```sql
> -- 1. Mostrar de qué departamento es cada empleado (2 tablas):
> SELECT EMP.Nombre, DEP.Nombre_Dpto 
> FROM EMPLEADOS EMP, DEPARTAMENTOS DEP
> WHERE EMP.Cod_Dept = DEP.Cod_Dept;
> 
> -- 2. Incluyendo filtros extra (Dónde el puesto sea Administrativo):
> SELECT EMP.Nombre, EMP.Rol, DEP.Ubicacion
> FROM EMPLEADOS EMP, DEPARTAMENTOS DEP
> WHERE EMP.Cod_Dept = DEP.Cod_Dept  -- 1. Emparejamiento
>   AND EMP.Rol = 'Administrativo';  -- 2. Filtro restrictivo
> 
> -- 3. Unir 3 tablas (Alumnos, Matriculas y Asignaturas)
> SELECT ALU.Nombre, ASI.Nombre_Completo, MAT.Nota
> FROM ALUMNOS ALU, ASIGNATURAS ASI, MATRICULAS MAT
> WHERE ALU.Dni = MAT.Dni_Alumno
>   AND ASI.Cod_Asig = MAT.Cod_Asignatura;
> ```

---

<a id="72-composiciones-externas-sintaxis-tradicional-de-oracle"></a>
### 7.2. Composiciones Externas (Sintaxis Tradicional de Oracle)

Con las composiciones internas normales, si un registro de la TablaA no tiene equivalente en TablaB (Ej. Un departamento recién abierto sin trabajadores), este simplemente desaparece del listado final.
Si queremos que **todos** los elementos salgan aunque les falten datos relacionados (dejando nulos donde falten), usamos una *composición externa*.

En el método antiguo de Oracle, se usa el sufijo especial `(+)` colgado de la tabla a la que "**le faltan**" filas.

> **CASO DE USO PRÁCTICOS (OUTER JOIN ORACLE `(+)`):**
> *Problema:* Queremos la lista de todos los Departamentos con sus Empleados, pero garantizando que los Departamentos vacíos se muestren igual (con `NULL` en datos del empleado).
> ```sql
> -- El (+) va en la FK de la tabla Empleados, indicando "Si al Departamento le faltan empleados, créalos nulos para que el Departamento no se borre de la salida".
> SELECT DEP.Nombre_Dpto, EMP.Nombre
> FROM DEPARTAMENTOS DEP, EMPLEADOS EMP
> WHERE DEP.Cod_Dept = EMP.Cod_Dept(+);
> ```

---

<a id="73-composiciones-modernas-sintaxis-sql99"></a>
### 7.3. Composiciones Modernas (Sintaxis SQL99)

El modelo SQL99 modernizó las consultas separando la unificación en el `FROM`, dejando el `WHERE` puro y exclusivamente para filtros. Es la manera profesional de programar SQL hoy en día.

*   `CROSS JOIN`: Producto cartesiano real explícito.
*   `NATURAL JOIN`: Cruce automático basado en nombres idénticos (Peligroso si hay coincidencias no deseadas).
*   `JOIN ... USING (columna)`: Fija en qué pilar sostener la unión, simplificalo todo en un campo si se llaman igual.
*   `JOIN ... ON()`: Versátil. **La forma más universal y estricta generalizada**.
*   `OUTER JOIN`:
    *   `LEFT OUTER JOIN`: Todo lo de la izquierda se queda, cruzado o no.
    *   `RIGHT OUTER JOIN`: Todo lo de la derecha se queda.
    *   `FULL OUTER JOIN`: Ambas tablas mantienen y muestran sus huérfanos.

> **CASOS DE USO PRÁCTICOS (JOINs MODERNOS SQL99):**
> ```sql
> -- 1. INNER JOIN ESTÁNDAR (El recomendado siempre):
> SELECT EMP.Nombre, DEP.Nombre_Dpto
> FROM EMPLEADOS EMP
> JOIN DEPARTAMENTOS DEP ON (EMP.Cod_Dept = DEP.Cod_Dept)
> WHERE EMP.Salario > 2000;
> 
> -- 2. LEFT OUTER JOIN: (Todos los Departamentos y sus Empleados, incluso los vacíos)
> SELECT DEP.Nombre_Dpto, EMP.Nombre
> FROM DEPARTAMENTOS DEP 
> LEFT OUTER JOIN EMPLEADOS EMP ON (DEP.Cod_Dept = EMP.Cod_Dept);
> 
> -- 3. NATURAL JOIN: (Si estamos ciegamente seguros que PK-FK se llaman idéntico y nada más)
> SELECT * 
> FROM EMPLEADOS 
> NATURAL JOIN DEPARTAMENTOS;
> ```

---

<a id="8-operaciones-de-conjuntos"></a>
## 8. Operaciones de Conjuntos

Sirven para agrupar los resultados de varios `SELECT` independientes bloque a bloque (apilar consultas o restar consultas).

*   **REGLA DE ORO INTOCABLE:** Los SELECT deben tener las **mismas columnas, mismo tipo y mismo orden**.
*   `UNION`: Pegar filas y suprimir repetidas. (`UNION ALL` pega y NO suprime).
*   `INTERSECT`: Devuelve **solo las filas conjuntas** (presentes arriba y también presentes abajo).
*   `MINUS`: Extrae del bloque de arriba todas las filas que el de abajo le reclama. Resta. 

> **CASOS DE USO PRÁCTICOS (Operaciones de Conjunto):**
> ```sql
> -- UNION: Generar un listado de "Contactos Globales" 
> SELECT Nombre, Telefono, 'CLIENTE' AS Origen FROM CLIENTES
> UNION
> SELECT Nombre, Telefono, 'PROVEEDOR' AS Origen FROM PROVEEDORES;
> 
> -- INTERSECT: Alumnos que tienen inglés y a su vez estudian francés.
> SELECT Dni_alumno FROM MATRICULA_INGLES
> INTERSECT
> SELECT Dni_alumno FROM MATRICULA_FRANCES;
> 
> -- MINUS: Alumnos que estudian inglés pero ¡Qué NO estudian francés!.
> SELECT Dni_alumno FROM MATRICULA_INGLES
> MINUS
> SELECT Dni_alumno FROM MATRICULA_FRANCES;
> ```

---

<a id="9-subconsultas"></a>
## 9. Subconsultas

Utilizar el resultado analítico de una consulta pequeña como llave, filtro o dato en la principal.
**Reglas:** Siempre entre paréntesis `()`. En el WHERE van siempre al lado derecho del operador principal lógico.

<a id="91-subconsultas-de-fila-única"></a>
### 9.1. Subconsultas de Fila Única
Cuando el bloque interno escupe 1 solo registro numérico, de texto... Podemos usar los operadores matemáticos de siempre: `=`, `>`, `<`, `<=`, `!=`.

> **CASOS DE USO (Subconsulta Simple Escalar):**
> ```sql
> -- 1. Empleados que ganan más que la media de la empresa
> SELECT Nombre, Salario 
> FROM EMPLEADOS
> WHERE Salario > (SELECT AVG(Salario) FROM EMPLEADOS);
> 
> -- 2. Empleados que ingresaron en el mismo Departamento que el usuario '123A'
> SELECT Nombre 
> FROM EMPLEADOS 
> WHERE Depto = (SELECT Depto FROM EMPLEADOS WHERE Dni = '123A');
> ```

<a id="92-subconsultas-de-múltiples-filas-in-any-all"></a>
### 9.2. Subconsultas de Múltiples Filas (IN, ANY, ALL)
Si las tripas de la subconsulta exhalan 2 o más resultados, `=` fallará.

*   `IN` / `NOT IN`: El más popular, comprueba pertenencias rápidas al listado virtualizado devolviendo listas enteras.
*   `> ANY` / `< ANY`: Mayor/Menor que **al menos** uno del grupo.
*   `> ALL` / `< ALL`: Mayor/Menor que **todos y cada uno** por absoluto.

> **CASOS DE USO (Subconsultas de Salida Múltiple):**
> ```sql
> -- 1. IN: Listar empleados que operan en los Departamentos radicados en Madrid (2 saltos)
> SELECT Nombre, Apellidos, Salario 
> FROM EMPLEADOS
> WHERE Cod_Dept IN (
>     SELECT Cod_Dept 
>     FROM DEPARTAMENTOS 
>     WHERE Localidad = 'Madrid'
> );
> 
> -- 2. ALL: El super-top que cobra más salario que cualquier empleado base del Dep 10 (Gana más que el que más gana en Dep 10)
> SELECT Nombre, Salario
> FROM EMPLEADOS
> WHERE Salario > ALL (
>     SELECT Salario 
>     FROM EMPLEADOS 
>     WHERE Cod_Dept = 10
> );
> 
> -- 3. ANY/SOME: Cobran más que "algún" empleado del departamento 10
> SELECT Nombre, Salario
> FROM EMPLEADOS
> WHERE Salario > ANY (
>     SELECT Salario 
>     FROM EMPLEADOS 
> );
> ```

---

<a id="93-operadores-de-existencia-exists-y-not-exists"></a>
### 9.3. Operadores de Existencia (`EXISTS` y `NOT EXISTS`)

El operador `EXISTS` comprueba si una subconsulta retorna al menos un registro. Es muy eficiente para validaciones de presencia.

*   **`EXISTS`**: Cierto si la subconsulta devuelve filas.
*   **`NOT EXISTS`**: Cierto si la subconsulta no devuelve nada.

> **EJEMPLO CRUCIAL (`EXISTS`):**
> ```sql
> -- Mostrar departamentos que tengan al menos un empleado trabajando
> SELECT dname FROM dept d
> WHERE EXISTS (SELECT * FROM emp e WHERE e.deptno = d.deptno);
> ```
