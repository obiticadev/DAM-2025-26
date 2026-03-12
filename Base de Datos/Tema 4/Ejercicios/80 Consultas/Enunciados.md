


Aquí tienes el documento formateado con encabezados, estilos adecuados, tablas bien estructuradas y bloques de código para los datos y comandos. Se han corregido los pequeños errores del reconocimiento de texto (como las tildes y las eñes separadas) y se ha incluido el 100% del contenido original.

***

# Bases de Datos I — Práctica 1
## Ejercicios de SQL
**Curso 2008-2009**

## 1. Descripción del modelo de datos

El modelo de datos que se usará para las consultas está reflejado en el siguiente modelo E-R.

```text[ Diagrama E-R conceptual ]
DEPT (0,N) -------- (0,1) EMP
(Atributos DEPT: DEPTNO, DNAME, LOC)
(Atributos EMP: EMPNO, ENAME, JOB, HIREDATE, SAL, COMM, MGR)
(Relaciones: EMP trabaja en DEPT; EMP es subordinado de / es jefe de EMP)
```

Transformado a relacional, la base de datos almacena dos tablas: `DEPT` y `EMP`, cuyos campos se describen a continuación.

### DEPT
| Campo | Tipo | Descripción |
| :--- | :--- | :--- |
| **`DEPTNO`** | NUMBER(2) NOT NULL | Número o código del departamento.<br>Es la clave primaria de la tabla. |
| **`DNAME`** | VARCHAR2(14) | Nombre del departamento. |
| **`LOC`** | VARCHAR2(13) | Localidad (o ciudad) donde el departamento está ubicado. |

### EMP
| Campo | Tipo | Descripción |
| :--- | :--- | :--- |
| **`EMPNO`** | NUMBER(4) NOT NULL | Número o código del empleado.<br>Es la clave primaria de la tabla. |
| **`ENAME`** | VARCHAR2(10) | Nombre del empleado. |
| **`JOB`** | VARCHAR2(9) | Trabajo del empleado. |
| **`MGR`** | NUMBER(4) | Código del jefe del empleado.<br>Clave foránea que referencia (cíclicamente) la tabla `EMP`. |
| **`HIREDATE`** | DATE | Fecha de contratación. |
| **`SAL`** | NUMBER(7, 2) | Salario mensual del empleado. |
| **`COMM`** | NUMBER(7, 2) | Comisión. |
| **`DEPTNO`** | NUMBER(2) | Código del departamento al que el empleado está adscrito.<br>Clave foránea que referencia la tabla `DEPT`. |

### Notas
La base de datos usada es un ejemplo que Oracle incorpora en su instalación, por lo que tanto los nombres de los campos como sus valores están en inglés. Así, un vendedor tendrá en el atributo `JOB` el valor `SALESMAN`, o el departamento de investigación es `RESEARCH`. Además, todos los nombres (de empleado, departamento, trabajo) están almacenados en mayúsculas.

En cuanto a la definición de las tablas, aunque todos los campos excepto las claves primarias admiten valores nulos, consideraremos especialmente los siguientes casos:
* **`COMM`**: Si tiene un valor nulo, indica que el empleado no tiene comisión.
* **`MGR`**: Si tiene un nulo, indica que el empleado no tiene jefe.

---

## 2. Datos almacenados en las tablas

El contenido de la tabla `DEPT` es el siguiente:

```text
DEPTNO DNAME          LOC
------ -------------- -------------
    10 ACCOUNTING     NEW YORK
    20 RESEARCH       DALLAS
    30 SALES          CHICAGO
    40 OPERATIONS     BOSTON

4 filas seleccionadas.
```

Y el contenido de la tabla `EMP`, el siguiente:

```text
EMPNO ENAME      JOB          MGR HIREDATE        SAL     COMM   DEPTNO
----- ---------- --------- ------ --------- --------- -------- --------
 7369 SMITH      CLERK       7902 17-DEC-80       800   <Nulo>       20
 7499 ALLEN      SALESMAN    7698 20-FEB-81      1600      300       30
 7521 WARD       SALESMAN    7698 22-FEB-81      1250      500       30
 7566 JONES      MANAGER     7839 02-APR-81      2975   <Nulo>       20
 7654 MARTIN     SALESMAN    7698 28-SEP-81      1250     1400       30
 7698 BLAKE      MANAGER     7839 01-MAY-81      2850   <Nulo>       30
 7782 CLARK      MANAGER     7839 09-JUN-81      2450   <Nulo>       10
 7788 SCOTT      ANALYST     7566 09-DEC-82      3000   <Nulo>       20
 7839 KING       PRESIDENT <Nulo> 17-NOV-81      5000   <Nulo>       10
 7844 TURNER     SALESMAN    7698 08-SEP-81      1500        0       30
 7876 ADAMS      CLERK       7788 12-JAN-83      1100   <Nulo>       20
 7900 JAMES      CLERK       7698 03-DEC-81       950   <Nulo>       30
 7902 FORD       ANALYST     7566 03-DEC-81      3000   <Nulo>       20
 7934 MILLER     CLERK       7782 23-JAN-82      1300   <Nulo>       10

14 filas seleccionadas.
```

---

## 3. Consultas a desarrollar

La práctica 1 de la asignatura de Bases de Datos 1 consiste en el desarrollo de las sentencias `SELECT` que devuelvan el resultado enunciado en cada pregunta.

1. Obtener todos los datos de todos los empleados.
2. Obtener todos los datos de todos los departamentos.
3. Obtener todos los datos de los administrativos (su trabajo es, en inglés, 'CLERK').
4. Ídem, pero ordenado por el nombre.
5. Obtén el mismo resultado de la pregunta anterior, pero modificando la sentencia SQL.
6. Obtén el número (código), nombre y salario de los empleados.
7. Lista los nombres de todos los departamentos.
8. Ídem, pero ordenándolos por nombre.
9. Ídem, pero ordenándolo por la ciudad (no se debe seleccionar la ciudad en el resultado).
10. Ídem, pero el resultado debe mostrarse ordenado por la ciudad en orden inverso.
11. Obtener el nombre y empleo de todos los empleados, ordenado por salario.
12. Obtener el nombre y empleo de todos los empleados, ordenado primero por su trabajo y luego por su salario.
13. Ídem, pero ordenando inversamente por empleo y normalmente por salario.
14. Obtén los salarios y las comisiones de los empleados del departamento 30.
15. Ídem, pero ordenado por comisión.
16. (a) Obtén las comisiones de todos los empleados. (b) Obtén las comisiones de los empleados de forma que no se repitan.
17. Obtén el nombre de empleado y su comisión SIN FILAS repetidas.
18. Obtén los nombres de los empleados y sus salarios, de forma que no se repitan filas.
19. Obtén las comisiones de los empleados y sus números de departamento, de forma que no se repitan filas.
20. Obtén los nuevos salarios de los empleados del departamento 30, que resultarían de sumar a su salario una gratificación de 1000. Muestra también los nombres de los empleados.
21. Lo mismo que la anterior, pero mostrando también su salario original, y haz que la columna que almacena el nuevo salario se denomine `NUEVO_SALARIO`.
22. Halla los empleados que tienen una comisión superior a la mitad de su salario.
23. Halla los empleados que no tienen comisión, o que la tengan menor o igual que el 25 % de su salario.
24. Obtén una lista de nombres de empleados y sus salarios, de forma que en la salida aparezca en todas las filas “Nombre:” y “Salario:” antes del respectivo campo. Hazlo de forma que selecciones exactamente tres expresiones.
25. Hallar el código, salario y comisión de los empleados cuyo código sea mayor que 7500.
26. Obtén todos los datos de los empleados que estén (considerando una ordenación ASCII por nombre) a partir de la J, inclusive.
27. Obtén el salario, comisión y salario total (salario+comisión) de los empleados con comisión, ordenando el resultado por número de empleado.
28. Lista la misma información, pero para los empleados que no tienen comisión.
29. Muestra el nombre de los empleados que, teniendo un salario superior a 1000, tengan como jefe al empleado cuyo código es 7698.
30. Halla el conjunto complementario del resultado del ejercicio anterior.
31. Indica para cada empleado el porcentaje que supone su comisión sobre su salario, ordenando el resultado por el nombre del mismo.
32. Hallar los empleados del departamento 10 cuyo nombre no contiene la cadena LA.
33. Obtén los empleados que no son supervisados por ningún otro.
34. Obtén los nombres de los departamentos que no sean Ventas (`SALES`) ni investigación (`RESEARCH`). Ordena el resultado por la localidad del departamento.
35. Deseamos conocer el nombre de los empleados y el código del departamento de los administrativos (`CLERK`) que no trabajan en el departamento 10, y cuyo salario es superior a 800, ordenado por fecha de contratación.
36. Para los empleados que tengan comisión, obtén sus nombres y el cociente entre su salario y su comisión (excepto cuando la comisión sea cero), ordenando el resultado por nombre.
37. Lista toda la información sobre los empleados cuyo nombre completo tenga exactamente 5 caracteres.
38. Lo mismo, pero para los empleados cuyo nombre tenga al menos cinco letras.
39. Halla los datos de los empleados que, o bien su nombre empieza por A y su salario es superior a 1000, o bien reciben comisión y trabajan en el departamento 30.
40. Halla el nombre, el salario y el sueldo total de todos los empleados, ordenando el resultado primero por salario y luego por el sueldo total. En el caso de que no tenga comisión, el sueldo total debe reflejar sólo el salario.
41. Obtén el nombre, salario y la comisión de los empleados que perciben un salario que está entre la mitad de la comisión y la propia comisión.
42. Obtén el complementario del anterior.
43. Lista los nombres y empleos de aquellos empleados cuyo empleo acaba en MAN y cuyo nombre empieza por A.
44. Intenta resolver la pregunta anterior con un predicado simple, es decir, de forma que en la cláusula `WHERE` no haya conectores lógicos como `AND`, `OR`, etc. Si ayuda a resolver la pregunta, se puede suponer que el nombre del empleado tiene al menos cinco letras.
45. Halla los nombres de los empleados cuyo nombre tiene como máximo cinco caracteres.
46. Suponiendo que el año próximo la subida del sueldo total de cada empleado será del 6 %, y el siguiente del 7 %, halla los nombres y el salario total actual, del año próximo y del siguiente, de cada empleado. Indique además con SI o NO, si el empleado tiene comisión. Como en la pregunta 40, si no tiene comisión, el total se considera igual al salario. Se supone que no existen comisiones negativas.
47. Lista los nombres y fecha de contratación de aquellos empleados que no son vendedores (`SALESMAN`).
48. Obtén la información disponible de los empleados cuyo número es uno de los siguientes: 7844, 7900, 7521, 7521, 7782, 7934, 7678 y 7369, pero que no sea uno de los siguientes: 7902, 7839, 7499 ni 7878. La sentencia no debe complicarse innecesariamente, y debe dar el resultado correcto independientemente de los empleados almacenados en la base de datos.
49. Ordena los empleados por su código de departamento, y luego de manera descendente por su número de empleado.
50. Para los empleados que tengan como jefe a un empleado con código mayor que el suyo, obtén los que reciben de salario más de 1000 y menos de 2000, o que están en el departamento 30.
51. Obtén el salario más alto de la empresa, el total destinado a comisiones y el número de empleados.
52. Halla los datos de los empleados cuyo salario es mayor que el del empleado de código 7934, ordenando por el salario.
53. Obtén información en la que se reflejen los nombres, empleos y salarios tanto de los empleados que superan en salario a Allen como del propio Allen.
54. Halla el nombre el último empleado por orden alfabético.
55. Halla el salario más alto, el más bajo, y la diferencia entre ellos.
56. Sin conocer los resultados del ejercicio anterior, ¿quienes reciben el salario más alto y el más bajo, y a cuanto ascienden estos salarios?
57. Considerando empleados con salario menor de 5000, halla la media de los salarios de los departamentos cuyo salario mínimo supera a 900. Muestra también el código y el nombre de los departamentos.
58. ¿Qué empleados trabajan en ciudades de más de cinco letras? Ordena el resultado inversamente por ciudades y normalmente por los nombres de los empleados.
59. Halla los empleados cuyo salario supera o coincide con la media del salario de la empresa.
60. Obtén los empleados cuyo salario supera al de sus compañeros de departamento.
61. ¿Cuántos empleos diferentes, cuántos empleados, y cuántos salarios diferentes encontramos en el departamento 30, y a cuánto asciende la suma de salarios de dicho departamento?
62. ¿Cuántos empleados tienen comisión?
63. ¿Cuántos empleados tiene el departamento 20?
64. Halla los departamentos que tienen más de tres empleados, y el número de empleados de los mismos.
65. Obtén los empleados del departamento 10 que tienen el mismo empleo que alguien del departamento de Ventas. Desconocemos el código de dicho departamento.
66. Halla los empleados que tienen por lo menos un empleado a su mando, ordenados inversamente por nombre.
67. Obtén información sobre los empleados que tienen el mismo trabajo que que algún empleado que trabaje en Chicago.
68. ¿Qué empleos distintos encontramos en la empresa, y cuántos empleados desempeñan cada uno de ellos?
69. Halla la suma de salarios de cada departamento.
70. Obtén todos los departamentos sin empleados.
71. Halla los empleados que no tienen a otro empleado a sus órdenes.
72. ¿Cuántos empleados hay en cada departamento, y cuál es la media anual del salario de cada uno (el salario almacenado es mensual)? Indique el nombre del departamento para clarificar el resultado.
73. Halla los empleados del departamento 30, por orden descendente de comisión.
74. Obtén los empleados que trabajan en Dallas o New York.
75. Obtén un listado en el que se reflejen los empleados y los nombres de sus jefes. En el listado deben aparecer todos los empleados, aunque no tengan jefe, poniendo un nulo el nombre de éste.
76. Lista los empleados que tengan el mayor salario de su departamento, mostrando el nombre del empleado, su salario y el nombre del departamento.
77. Deseamos saber cuántos empleados supervisa cada jefe. Para ello, obtén un listado en el que se reflejen el código y el nombre de cada jefe, junto al número de empleados que supervisa directamente. Como puede haber empleados sin jefe, para estos se indicará sólo el número de ellos, y los valores restantes (código y nombre del jefe) se dejarán como nulos.
78. Hallar el departamento cuya suma de salarios sea la más alta, mostrando esta suma de salarios y el nombre del departamento.
79. Obtén los datos de los empleados que cobren los dos mayores salarios de la empresa. (Nota: Procure hacer la consulta de forma que sea fácil obtener los empleados de los *N* mayores salarios)
80. Obtén las localidades que no tienen departamentos sin empleados y en las que trabajen al menos cuatro empleados. Indica también el número de empleados que trabajan en esas localidades. (Nota: Por ejemplo, puede que en A Coruña existan dos departamentos, uno con más de cuatro empleados y otro sin empleados, en tal caso, A Coruña no debe aparecer en el resultado, puesto que tiene un departamento SIN EMPLEADOS, a pesar de tener otro con empleados Y tener más de cuatro empleados EN TOTAL. ATENCIÓN, la restricción de que tienen que ser cuatro empleados se refiere a la totalidad de los departamentos de la localidad.)

---

## 4. Normas de entrega

### 4.1. Grupos
Esta práctica debe realizarse en grupos formados por dos personas.

### Forma de entrega
El resultado que debe entregarse para superar esta práctica es un único fichero de texto, en el que se reflejen las 80 sentencias SQL (`SELECT`) de las consultas.
El fichero debe tener el siguiente formato, con una cabecera en la que se indican los autores de la práctica:

```text
========================================================================
PRACTICA 1 DE BASES DE DATOS 1
AUTORES:
      <Nombre 1> - <login de usuario> - <titulación> - Grupo de prácticas
      <Nombre 2> - <login de usuario> - <titulación> - Grupo de prácticas
========================================================================
-- Consulta 1: <opcionalmente, incluir el enunciado>
SELECT .....;

<resultado>

-- Consulta 2: <opcionalmente, incluir el enunciado>
SELECT ....;

<resultado>

....

-- Consulta 80: <opcionalmente, incluir el enunciado>
SELECT ...;

<resultado>
```

Para obtener de forma cómoda este fichero, se sugiere lo siguiente:

1. Crear un fichero en el que se irán escribiendo únicamente las sentencias `SELECT`, precedidas de un comentario en el que se indica el número de la consulta y, opcionalmente, el enunciado de la misma. Por ejemplo, usemos para ello un fichero llamado `consultas.sql`:
```sql
-- Consulta 1: <opcionalmente, incluir el enunciado>
SELECT .....;
-- Consulta 2: <opcionalmente, incluir el enunciado>
SELECT ....;
....
-- Consulta 80: <opcionalmente, incluir el enunciado>
SELECT ...;
```

2. Crear el fichero a entregar, que refleja las consultas y los resultados. La siguiente secuencia de comandos, en sqlplus, generará el fichero `practica1_bd.lst`.
```sql
SET PAGESIZE 100
SET LINESIZE 100
SET PAUSE OFF
SET ECHO ON
SPOOL practica1_bd
START consultas
SPOOL OFF
```

3. Abrir el fichero `practica1_bd.lst` con un editor de textos y añadirle la cabecera que se indica anteriormente.

---

## 5. Fecha entrega
La fecha límite para la entrega es el **viernes 15 de mayo**. La entrega será electrónica (copiar el fichero en directorio de entrega de prácticas).