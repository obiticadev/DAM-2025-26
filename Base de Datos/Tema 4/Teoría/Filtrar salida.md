


La cláusula **`FETCH FIRST n ROWS ONLY`** (conocida formalmente como *Row Limiting Clause*) fue introducida en Oracle 12c (en el año 2013) para adaptarse al estándar ANSI SQL:2008. 

Antes de esto, hacer un simple "Top N" o paginar resultados en Oracle era un dolor de cabeza que obligaba a usar subconsultas con `ROWNUM` (como vimos en tu duda anterior). Esta nueva sintaxis viene a solucionar precisamente eso: **primero ordena todo el resultado y luego extrae las filas que le pidas**, en ese orden estricto.

Pero lo mejor es que no sirve solo para sacar "la primera fila". Esta sintaxis tiene varias variantes y utilidades muy potentes. Aquí tienes los usos más comunes con ejemplos aplicados a tu tabla `EMP`:

### 1. El uso básico: Top N (Los primeros X registros)
Si quieres los 3 empleados que más cobran:

```sql
SELECT ENAME, SAL
FROM EMP
ORDER BY SAL DESC
FETCH FIRST 3 ROWS ONLY;
```
*Nota: Puedes usar `FIRST` o `NEXT`, a nivel de código hacen exactamente lo mismo, es solo para que la frase en inglés tenga sentido.*

### 2. Paginación: Saltar filas con `OFFSET`
Esta es la función "estrella" para el desarrollo web. Si tienes una web que muestra 5 empleados por página, y quieres mostrar la **página 2** (es decir, saltarte los 5 primeros y coger los 5 siguientes), usas `OFFSET`:

```sql
SELECT ENAME, SAL
FROM EMP
ORDER BY SAL DESC
OFFSET 5 ROWS FETCH NEXT 5 ROWS ONLY;
```
* **`OFFSET 5 ROWS`**: Ignora los 5 primeros resultados ya ordenados.
* **`FETCH NEXT 5 ROWS ONLY`**: Coge los 5 siguientes.

### 3. Gestionar los "empates" con `WITH TIES`
Imagina que quieres los 2 empleados que menos cobran. Haces esto:

```sql
SELECT ENAME, SAL
FROM EMP
ORDER BY SAL ASC
FETCH FIRST 2 ROWS ONLY;
```
Si miras los datos de tu tabla `EMP`, el empleado que menos cobra es SMITH (800) y el segundo es JAMES (950). Te devolvería esos dos.
Pero, ¿qué pasaría si hubiera **tres** empleados cobrando 800? Con `ONLY`, Oracle cortaría por lo sano y te devolvería a dos de ellos al azar. 

Para evitar esto y decir "tráeme a los 2 primeros, **y si el tercero empata con el segundo, tráemelo también**", se cambia `ONLY` por `WITH TIES`:

```sql
SELECT ENAME, SAL
FROM EMP
ORDER BY SAL ASC
FETCH FIRST 2 ROWS WITH TIES;
```
*(Ojo: Para que `WITH TIES` funcione, la consulta **debe** tener un `ORDER BY`, de lo contrario da error).*

### 4. Extraer porcentajes con `PERCENT`
A veces no sabes cuántos empleados hay en total, pero necesitas "el 20% de los empleados que más cobran". Puedes pedir porcentajes en lugar de un número fijo de filas:

```sql
SELECT ENAME, SAL
FROM EMP
ORDER BY SAL DESC
FETCH FIRST 20 PERCENT ROWS ONLY;
```
Como en tu tabla hay 14 empleados, el 20% son 2,8. Oracle redondeará hacia arriba y te devolverá a los 3 empleados con mayor salario (KING, SCOTT y FORD).

---

### Resumen de la sintaxis completa:

Para que veas todas las combinaciones, la "plantilla" que usa Oracle es esta:

```sql
[ OFFSET n ROWS ]
FETCH { FIRST | NEXT } [ n | n PERCENT ] ROWS { ONLY | WITH TIES }
```

**Por qué esto es mejor que el antiguo `ROWNUM`:**
1. Es el estándar de SQL (funciona igual en PostgreSQL, SQL Server, etc.).
2. El código es mucho más limpio y fácil de leer (te ahorras hacer subconsultas `SELECT * FROM (SELECT...) WHERE...`).
3. El motor de base de datos sabe exactamente cuál es tu intención ("paginar" o hacer un "Top-N") y lo optimiza mejor internamente.