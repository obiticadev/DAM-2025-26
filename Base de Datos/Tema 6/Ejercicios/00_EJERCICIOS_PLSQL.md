<a id="indice"></a>
# Ejercicios PL/SQL — Ruta de Aprendizaje Completa

> **Cómo usar este documento:**
> Sigue los bloques en orden. Cada bloque añade un concepto nuevo sobre el anterior.
> Escribe tu código en el hueco `-- Tu solución aquí` de cada ejercicio y anota el resultado en el campo correspondiente.

---

## Índice

| Bloque | Tema | Ejercicios |
|--------|------|------------|
| [Bloque 1](#bloque-1) | Fundamentos: Variables, Tipos y Condicionales | EJ1 · EJ2 · EJ3 · EJ4 |
| [Bloque 2](#bloque-2) | Estructuras de Control: Bucles | EJ0 · EJ1 · EJ2 · EJ3 · EJ4 |
| [Bloque 3](#bloque-3) | Interacción con la BD: SELECT INTO, %TYPE, %ROWTYPE | EJ1 · EJ2 · EJ3 · EJ4 · EJ5 · EJ6 · EJ7 · EJ8 |
| [Bloque 4](#bloque-4) | Tipos Compuestos: Registros (RECORD) | EJ1 · EJ2.1 · EJ2.2 |
| [Bloque 5](#bloque-5) | Tipos Compuestos: Arrays de Longitud Variable (VARRAY) | EJ1 · EJ2 · EJ3 |
| [Bloque 6](#bloque-6) | Cursores | EJ1 · EJ2 · EJ3 · EJ4 · EJ5 |
| [Bloque 7](#bloque-7) | Excepciones | EJ1 · EJ2 · EJ3 · EJ4 + Ejemplos PRAGMA |
| [Bloque 8](#bloque-8) | Procedimientos | Referencia + EJ1 · EJ2 |
| [Bloque 9](#bloque-9) | Funciones | Referencia + EJ1 · EJ2 |
| [Bloque 10](#bloque-10) | Paquetes — Integrador Final | Referencia + EJ Final |

---

<a id="bloque-1"></a>
## Bloque 1 — Fundamentos: Variables, Tipos y Condicionales

> **Conceptos clave:** `DECLARE / BEGIN / END`, tipos de dato (`NUMBER`, `VARCHAR2`, `BOOLEAN`),
> asignación (`:=`), `DBMS_OUTPUT.PUT_LINE`, sentencia `IF / ELSE / END IF`,
> comentarios de una línea (`--`) y de varias líneas (`/* ... */`),
> variables de sustitución por teclado (`&nombre`).

[↑ Volver al índice](#indice)

---

### Referencia rápida — Estructura IF

```sql
-- IF simple
BEGIN
  IF (condicion) THEN
    -- bloque si verdadero
  END IF;
END;

-- IF con ELSE
BEGIN
  IF (condicion) THEN
    -- bloque si verdadero
  ELSE
    -- bloque si falso
  END IF;
END;
```

---

<a id="b1-ej1"></a>
### Ejercicio 1.1 — Variables numéricas y suma básica

**Instrucciones:**
- Declarar dos variables numéricas: una que vale `5` y otra introducida por el usuario.
- Sumar ambos números e imprimirlos en **una sola línea**.

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b1-ej2"></a>
### Ejercicio 1.2 — Variables con límites, formato de salida y comentarios de línea

**Instrucciones:**
- Declarar `num1` con máximo 1 dígito (máx. valor 9), pedido por teclado.
- Declarar `num2` con máximo 2 dígitos (máx. valor 99), pedido por teclado.
- Comentar la declaración de `num2` con un comentario de **una línea** (`--`).
- Imprimir el resultado con el formato exacto: `La suma num1 + num2 = resultado`

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b1-ej3"></a>
### Ejercicio 1.3 — Variables mixtas, condicional IF y comentario multilínea

**Instrucciones:**
- Declarar variable numérica `num` pedida por teclado y una `nota` también numérica.
- Declarar `nombre VARCHAR2(10)` con valor inicial `'Andres'`.
- Declarar `apellido VARCHAR2(20)` pedida por teclado.
- Si `nota > 5`, imprimir: `El alumno <nombre> <apellido> ha aprobado`
- Añadir un **comentario de varias líneas** (`/* ... */`) al bloque.

```sql
/*
  Tu comentario multilínea aquí
*/
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b1-ej4"></a>
### Ejercicio 1.4 — Boolean e IF/ELSE

**Instrucciones:**
- Pedir una cifra numérica por teclado.
- Pedir un booleano por teclado en formato combo (`1` = TRUE, `0` = FALSE).
- Si es `TRUE`: imprimir `Te ha tocado <cifra elevada al cuadrado>`
- Si es `FALSE`: imprimir `Te ha tocado <cifra>`

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="bloque-2"></a>
## Bloque 2 — Estructuras de Control: Bucles

> **Conceptos clave:** `LOOP / EXIT WHEN`, `FOR i IN 1..n LOOP`, `WHILE condicion LOOP`,
> acumuladores, operador módulo (`MOD`), funciones matemáticas `SQRT()` y `POWER(base, exp)`.

[↑ Volver al índice](#indice)

---

<a id="b2-ej0"></a>
### Ejercicio 2.0 — Suma acumulativa con LOOP (parada condicional)

**Instrucciones:**
- Sumar los valores `1 + 2 + 3 + ...` de forma acumulativa.
- Cuando la suma supere `10`, parar e imprimir el valor acumulado.
- Usar un **bucle LOOP** con `EXIT WHEN`.

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b2-ej1"></a>
### Ejercicio 2.1 — Suma del 1 al 10 con FOR

**Instrucciones:**
- Sumar los números del 1 al 10 usando un **bucle FOR**.
- Imprimir el resultado final.

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b2-ej2"></a>
### Ejercicio 2.2 — Suma del 1 al 10 con WHILE

**Instrucciones:**
- Sumar los números del 1 al 10 usando un **bucle WHILE**.
- Imprimir el resultado final.

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b2-ej3"></a>
### Ejercicio 2.3 — Mayor de tres números

**Instrucciones:**
- Pedir 3 números por teclado.
- Determinar e imprimir el **mayor** de los tres.

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b2-ej4"></a>
### Ejercicio 2.4 — Distancia entre dos puntos

**Instrucciones:**
- Definir dos puntos: `P1(x1, y1)` y `P2(x2, y2)`.
- Calcular la distancia euclidiana entre ellos.
- Usar `SQRT()` (raíz cuadrada) y `POWER(num, 2)` (potencia al cuadrado).

> **Fórmula:** `d = SQRT( POWER(x2-x1, 2) + POWER(y2-y1, 2) )`

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="bloque-3"></a>
## Bloque 3 — Interacción con la BD: SELECT INTO, %TYPE, %ROWTYPE y DBMS_RANDOM

> **Conceptos clave:** `SELECT columna INTO variable FROM tabla WHERE ...`,
> anclar el tipo de una variable a una columna con `tabla.columna%TYPE`,
> anclar una variable a toda una fila con `tabla%ROWTYPE`,
> generación de números aleatorios con el paquete `DBMS_RANDOM`.

[↑ Volver al índice](#indice)

---

<a id="b3-ej1"></a>
### Ejercicio 3.1 — Suma aleatoria con DBMS_RANDOM

**Instrucciones:**
- Generar aleatoriamente un número `n` de iteraciones entre 1 y 10 usando `DBMS_RANDOM`.
- En cada iteración, generar un número aleatorio entre 10 y 100.
- Imprimir cada número generado y, al finalizar, la suma total acumulada.

> **Pista:** `TRUNC(DBMS_RANDOM.VALUE(min, max))` devuelve un entero en el rango `[min, max)`.

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b3-ej2"></a>
### Ejercicio 3.2 — Múltiplos de 3 entre 1 y 100

**Instrucciones:**
- Desarrollar un bloque que cuente y devuelva la cantidad de números múltiplos de 3 que existen entre 1 y 100.

> **Pista:** Un número es múltiplo de 3 si `MOD(numero, 3) = 0`.

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b3-ej3"></a>
### Ejercicio 3.3 — Día de la semana actual

**Instrucciones:**
- Desarrollar un bloque que devuelva el **nombre del día de la semana** en que estamos (Lunes, Martes, Miércoles…) y el **número de día** dentro de la semana (1, 2, 3…).

> **Pista:** `TO_CHAR(SYSDATE, 'DAY')` devuelve el nombre; `TO_CHAR(SYSDATE, 'D')` devuelve el número.

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b3-ej4"></a>
### Ejercicio 3.4 — COUNT de departamentos con SELECT INTO

**Instrucciones:**
- Con la sentencia `SELECT valor INTO variable`, calcular el **número total de departamentos** que existen en la tabla.
- Imprimir el resultado.

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b3-ej5"></a>
### Ejercicio 3.5 — Suma de unidades por producto

**Parte 5.1 — Producto fijo (nº 20):**
- Con `SELECT valor INTO variable`, calcular la **suma de unidades** para el producto número `20` e imprimirla.

**Parte 5.2 — Producto pedido por teclado:**
- Igual que la parte 5.1, pero el número de producto se introduce **por teclado**.

```sql
-- 5.1
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;

-- 5.2
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b3-ej6"></a>
### Ejercicio 3.6 — SELECT INTO con dos variables

**Instrucciones:**
- Cargar el **nombre** y la **localidad** del departamento `20` en dos variables separadas (`INTO var1, var2`).
- Imprimir ambos valores.

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b3-ej7"></a>
### Ejercicio 3.7 — Uso de %TYPE

**Instrucciones:**
- Cargar la **suma de todas las unidades** de todos los pedidos.
- La variable receptora debe declararse usando **`%TYPE`** del campo correspondiente en su tabla.
- Imprimir el resultado.

```sql
DECLARE
  total_unidades pedidose.unidades%TYPE;  -- ejemplo de ancla %TYPE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b3-ej8"></a>
### Ejercicio 3.8 — Uso de %ROWTYPE

**Instrucciones:**
- Cargar el nombre y la localidad del departamento `40` usando `SELECT ... INTO` sobre una única variable de tipo **`%ROWTYPE`** de la tabla correspondiente.
- Imprimir los dos valores accediendo a los campos de esa misma variable (ej. `variable.nombre`, `variable.localidad`).

```sql
DECLARE
  fila_dep departamentose%ROWTYPE;  -- ejemplo de ancla %ROWTYPE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="bloque-4"></a>
## Bloque 4 — Tipos Compuestos: Registros (RECORD)

> **Conceptos clave:** `TYPE nombre IS RECORD (campo tipo, ...)`,
> variable de tipo registro, acceso a campos con `variable.campo`,
> operaciones DML con la variable registro (`INSERT`, `DELETE`).

[↑ Volver al índice](#indice)

---

<a id="b4-ej1"></a>
### Ejercicio 4.1 — Tipo registro con INSERT en tabla

**Instrucciones:**
- Crear un **tipo registro** `TIPOPRO` con los siguientes campos:
  - `campo1 NUMBER`
  - `campo2 VARCHAR2(40)`
  - `campo3 NUMBER(6,2)`
  - `campo4 NUMBER`
- Crear una variable `vtipopro` de tipo `TIPOPRO` con los valores `100, 'tablon', 234.56, 34`.
- Imprimir todos los valores del registro.
- Insertar el registro en la tabla `productose`.

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b4-ej21"></a>
### Ejercicio 4.2.1 — SELECT INTO sobre variable registro

**Instrucciones:**
- Hacer un `SELECT ... INTO vtipopro` para cargar los datos del producto cuyo `productonu = 30`.
- Imprimir los valores cargados en el registro.

```sql
DECLARE
  -- Tu solución aquí (reutiliza el tipo TIPOPRO del ejercicio anterior)
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b4-ej22"></a>
### Ejercicio 4.2.2 — DELETE usando valor de registro pedido por teclado

**Instrucciones:**
- Pedir por teclado el valor de `productonu` para la variable `vtipopro`.
- Borrar de la tabla `productose` la fila cuyo `productonu` coincida con el valor introducido.

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="bloque-5"></a>
## Bloque 5 — Tipos Compuestos: Arrays de Longitud Variable (VARRAY)

> **Conceptos clave:** `TYPE nombre IS VARRAY(n) OF tipo`, inicialización con constructor `tipo(v1, v2, ...)`,
> métodos `FIRST`, `LAST`, `COUNT`, `LIMIT`, acceso por índice `array(i)`,
> VARRAY cuyos elementos son registros.

[↑ Volver al índice](#indice)

---

<a id="b5-ej1"></a>
### Ejercicio 5.1 — VARRAY de cadenas con métodos básicos

**Instrucciones:**
- Declarar un tipo `VARRAY` de **4 cadenas** llamado `TACAD`.
- Declarar una variable `VTACAD` de ese tipo.
- Rellenar **3 posiciones** con cualquier valor de texto.
- Imprimir el resultado de los métodos: `FIRST`, `COUNT`, `LAST` y `LIMIT`.
- Imprimir el contenido de la **posición 3**.

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b5-ej2"></a>
### Ejercicio 5.2 — VARRAY de registros TPERSONA

**Instrucciones:**
- Crear un tipo de registro `TPERSONA` con dos campos: `edad NUMBER` y `nombre VARCHAR2(30)`.
- Declarar un tipo `TAPERSONA` como VARRAY de elementos `TPERSONA`.
- Declarar la variable `VTAPERSONA` de tipo `TAPERSONA`.
- Almacenar:
  - Posición 1: persona `(1, 'ANA')`
  - Posición 2: persona `(2, 'ANDRES')`
  - Posición 3: persona con datos pedidos **por teclado**
- Imprimir los nombres de todas las personas usando un **bucle**.

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b5-ej3"></a>
### Ejercicio 5.3 — VARRAY numérico rellenado con bucle (triple de posición)

**Instrucciones:**
- Declarar un tipo VARRAY de números llamado `TAVNUM`.
- Declarar una variable `VTAVNUM` de ese tipo.
- Rellenar **4 posiciones** usando un **bucle FOR**, donde el valor de cada posición sea el **triple de su índice** (posición 1 = 3, posición 2 = 6, posición 3 = 9, posición 4 = 12).
- Imprimir el resultado de los métodos: `FIRST`, `COUNT`, `LAST`, `LIMIT`.
- Imprimir el valor de la **posición 3**.
- Imprimir **todo el contenido** del array recorriéndolo con un bucle.

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="bloque-6"></a>
## Bloque 6 — Cursores

> **Conceptos clave:** declaración de cursor (`CURSOR nombre IS SELECT ...`),
> atributos `%FOUND`, `%NOTFOUND`, `%ROWCOUNT`, `%ISOPEN`,
> manejo explícito (`OPEN / FETCH / CLOSE`), bucle `FOR` implícito,
> cursores con parámetros, `REF CURSOR` (cursor variable).

[↑ Volver al índice](#indice)

---

### Referencia rápida — Formas de manejo de cursores

```sql
-- FORMA 1: Manejo explícito
OPEN nombre_cursor;
FETCH nombre_cursor INTO variables;
WHILE nombre_cursor%FOUND LOOP
  -- operaciones con las variables
  FETCH nombre_cursor INTO variables;
END LOOP;
CLOSE nombre_cursor;

-- FORMA 2: Bucle FOR de cursor (implícito — abre, lee y cierra solo)
FOR i IN nombre_cursor LOOP
  -- usar i.nombre_columna directamente
END LOOP;
```

---

<a id="b6-ej1"></a>
### Ejercicio 6.1 — Cursor explícito con FORMA 1

**Instrucciones:**
- Crear un cursor `C1` que lea los **números de departamento** de la tabla.
- Implementar la solución con la **FORMA 1** (OPEN / FETCH / WHILE / CLOSE).
- En cada iteración del bucle, imprimir el número de departamento obtenido.

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b6-ej2"></a>
### Ejercicio 6.2 — Cursor FOR combinado con VARRAY (FORMA 2)

**Instrucciones:**
- Crear un cursor `C1` que lea los **números de departamento**.
- Usar la **FORMA 2** (bucle FOR de cursor).
- En cada iteración, guardar el número de departamento en un **VARRAY**.
- Al finalizar, recorrer e imprimir todo el contenido del array.

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b6-ej3"></a>
### Ejercicio 6.3 — Cursor con parámetros

**Instrucciones:**
- Crear un cursor `C1` que reciba un **parámetro numérico**.
- El cursor debe devolver el **nombre del producto** que coincida con el parámetro.
- El valor del parámetro se solicita **por teclado** (usando `&`).
- Ejecutar el cursor e imprimir los nombres resultantes.

```sql
DECLARE
  CURSOR C1(param NUMBER) IS
    -- Tu consulta aquí
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b6-ej4"></a>
### Ejercicio 6.4 — REF CURSOR sobre productos (bucle WHILE)

**Instrucciones:**
- Crear un **REF CURSOR** `C2` que devuelva filas de la tabla de productos.
- **Primera apertura:** devolver **todos** los productos e imprimir sus nombres.
- **Segunda apertura del mismo cursor:** devolver solo los **productos 20 y 40** e imprimirlos.
- Es obligatorio usar un bucle **WHILE** para los recorridos.

```sql
DECLARE
  TYPE ref_cur IS REF CURSOR;
  C2 ref_cur;
  -- Tu solución aquí
BEGIN
  -- Primera apertura
  OPEN C2 FOR SELECT ...;
  FETCH C2 INTO ...;
  WHILE C2%FOUND LOOP
    -- Tu solución aquí
    FETCH C2 INTO ...;
  END LOOP;
  CLOSE C2;

  -- Segunda apertura
  OPEN C2 FOR SELECT ... WHERE ...;
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b6-ej5"></a>
### Ejercicio 6.5 — REF CURSOR sobre departamentos (bucle LOOP)

**Instrucciones:**
- Crear un **REF CURSOR** `C3` que devuelva los **nombres de departamentos**.
- **Primera apertura:** recuperar e imprimir los nombres de **todos** los departamentos.
- **Segunda apertura del mismo cursor:** recuperar e imprimir los departamentos `10`, `20` y `40`.
- Es obligatorio usar un bucle **LOOP** básico con `EXIT WHEN` para los recorridos.

```sql
DECLARE
  TYPE ref_cur IS REF CURSOR;
  C3 ref_cur;
  -- Tu solución aquí
BEGIN
  -- Primera apertura
  OPEN C3 FOR SELECT ...;
  LOOP
    FETCH C3 INTO ...;
    EXIT WHEN C3%NOTFOUND;
    -- Tu solución aquí
  END LOOP;
  CLOSE C3;

  -- Segunda apertura
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="bloque-7"></a>
## Bloque 7 — Excepciones

> **Conceptos clave:** bloque `EXCEPTION`, excepciones predefinidas de Oracle (`NO_DATA_FOUND`, `TOO_MANY_ROWS`),
> cláusula comodín `WHEN OTHERS THEN`, variables de diagnóstico `SQLERRM` y `SQLCODE`,
> excepciones de usuario (`nombre EXCEPTION` + `RAISE nombre`),
> asociar código de error con `PRAGMA EXCEPTION_INIT`.

[↑ Volver al índice](#indice)

---

<a id="b7-ej1"></a>
### Ejercicio 7.1 — Consulta básica con excepción de usuario

**Parte 1.1 — Consulta y resultado:**
- Cargar el **número de cliente** para el pedido `1002` e imprimirlo.

**Parte 1.2 — Excepción de usuario:**
- Además, si el número de cliente **no es `103`**, lanzar una excepción llamada `errorcliente` e imprimir `'el cliente no es 101'`.

```sql
-- 1.1
DECLARE
  -- Tu solución aquí
BEGIN
  SELECT clientenu INTO ...
  FROM pedidose
  WHERE pedidonu = 1002;
  -- Tu solución aquí
END;

-- 1.2
DECLARE
  ncli    pedidose.clientenu%TYPE;
  errorcliente EXCEPTION;
BEGIN
  -- Tu solución aquí
EXCEPTION
  WHEN errorcliente THEN
    -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b7-ej2"></a>
### Ejercicio 7.2 — NO_DATA_FOUND sin OTHERS

**Instrucciones:**
- Hacer una consulta que devuelva la **localidad** del departamento `50`.
- Si no existe ninguna fila, capturar el error concreto **`NO_DATA_FOUND`** sin usar `OTHERS`.

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  SELECT localidad INTO ...
  FROM departamentose
  WHERE depnu = 50;
  -- Tu solución aquí
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b7-ej3"></a>
### Ejercicio 7.3 — Excepción capturada con OTHERS

**Instrucciones:**
- Igual que el ejercicio 7.2 (localidad del departamento `50`), pero capturar el error usando la cláusula **`WHEN OTHERS THEN`**.

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
EXCEPTION
  WHEN OTHERS THEN
    -- Tu solución aquí (usa SQLERRM y SQLCODE)
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b7-ej4"></a>
### Ejercicio 7.4 — TOO_MANY_ROWS

**Instrucciones:**
- Hacer una consulta que intente devolver los **números de pedido** para el producto `20` y almacenarlos en una variable escalar.
- Capturar el error **`TOO_MANY_ROWS`** que se producirá al haber más de una fila.

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  SELECT pedidonu INTO ...
  FROM pedidose
  WHERE productonu = 20;
EXCEPTION
  WHEN TOO_MANY_ROWS THEN
    -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

### Referencia — Excepciones de usuario con nombre y PRAGMA EXCEPTION_INIT

#### Ejemplo 1 — Excepción de usuario sin código Oracle asignado

```sql
DECLARE
    ncli     pedidose.clientenu%TYPE;
    errorcli EXCEPTION;
BEGIN
    SELECT clientenu INTO ncli
    FROM pedidose
    WHERE pedidonu = 1002;

    IF (ncli <> 103) THEN
        RAISE errorcli;
    END IF;

    DBMS_OUTPUT.PUT_LINE(ncli);
EXCEPTION
    WHEN errorcli THEN
        DBMS_OUTPUT.PUT_LINE('error valor ' || ' ERRM=' || SQLERRM || ' ECODE=' || SQLCODE);
END;
```

> **Nota:** Sin `PRAGMA`, `SQLCODE` devuelve `1` y `SQLERRM` devuelve `User-Defined Exception`.

---

#### Ejemplo 2 — Excepción con PRAGMA EXCEPTION_INIT (código Oracle -20015)

```sql
DECLARE
    ncli     pedidose.clientenu%TYPE;
    errorcli EXCEPTION;
    PRAGMA EXCEPTION_INIT(errorcli, -20015);
BEGIN
    SELECT clientenu INTO ncli
    FROM pedidose
    WHERE pedidonu = 1002;

    IF (ncli <> 103) THEN
        RAISE errorcli;
    END IF;

    DBMS_OUTPUT.PUT_LINE(ncli);
EXCEPTION
    WHEN errorcli THEN
        DBMS_OUTPUT.PUT_LINE('error valor ' || ' ERRM=' || SQLERRM || ' ECODE=' || SQLCODE);
END;
```

> **Nota:** Con `PRAGMA EXCEPTION_INIT`, `SQLCODE` devuelve `-20015` y el error queda vinculado al espacio de errores de usuario Oracle (`-20000` a `-20999`).

> **Resultado de los ejemplos:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="bloque-8"></a>
## Bloque 8 — Procedimientos

> **Conceptos clave:** `PROCEDURE nombre(param modo tipo)`,
> modos de parámetro: `IN` (entrada, por defecto), `OUT` (salida), `IN OUT` (ambos),
> procedimiento anónimo (declarado dentro de un bloque `DECLARE`),
> procedimiento almacenado (`CREATE OR REPLACE PROCEDURE`).

[↑ Volver al índice](#indice)

---

### Referencia — Estructura de procedimientos

#### Procedimiento anónimo (dentro de DECLARE)

```sql
DECLARE
  local VARCHAR2(30);

  -- Proc con parámetro IN: imprime dentro del proc
  PROCEDURE plocalidad1(numdep NUMBER)
  IS loc VARCHAR2(30);
  BEGIN
    SELECT localidad INTO loc FROM departamentose WHERE depnu = numdep;
    DBMS_OUTPUT.PUT_LINE(loc);
  END plocalidad1;

  -- Proc con parámetro OUT: devuelve valor al bloque llamador
  PROCEDURE plocalidad2(numdep NUMBER, loc OUT VARCHAR2) IS
  BEGIN
    SELECT localidad INTO loc FROM departamentose WHERE depnu = numdep;
  END plocalidad2;

BEGIN
  plocalidad1(20);           -- imprime en el propio proc
  plocalidad2(10, local);    -- devuelve en la variable local
  DBMS_OUTPUT.PUT_LINE(local);
END;
```

#### Procedimiento almacenado (CREATE OR REPLACE)

```sql
CREATE OR REPLACE PROCEDURE plocalidad3(numdep NUMBER)
IS loc VARCHAR2(30);
BEGIN
  SELECT localidad INTO loc FROM departamentose WHERE depnu = numdep;
  DBMS_OUTPUT.PUT_LINE(loc);
END plocalidad3;

-- Llamada desde un bloque aparte
BEGIN
  plocalidad3(40);
END;
```

---

<a id="b8-ej1"></a>
### Ejercicio 8.1 — Procedimiento anónimo con parámetros IN y OUT

**Instrucciones:**
- Crear un **procedimiento `P1`** dentro de un bloque `DECLARE` al que se le pase:
  - El número de **producto** (IN)
  - El número de **pedido** (IN)
- El procedimiento debe devolver la **cantidad pedida** para esa combinación en un parámetro `OUT`.
- Imprimir el resultado en el `BEGIN ... END` del `DECLARE`.

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b8-ej2"></a>
### Ejercicio 8.2 — Procedimiento almacenado (CREATE OR REPLACE)

**Instrucciones:**
- Crear un **procedimiento almacenado `P2`** con `CREATE OR REPLACE`.
- El número de pedido se pide **por teclado**.
- El procedimiento devuelve en un parámetro `OUT` el **número de unidades** para ese pedido.
- Llamarlo desde un `BEGIN ... END` aparte.

```sql
-- Crear procedimiento almacenado
CREATE OR REPLACE PROCEDURE p2(num_pedido IN NUMBER, unidades OUT NUMBER)
IS
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END p2;

-- Ejecutar desde bloque aparte
DECLARE
  resultado NUMBER;
BEGIN
  p2(&num_pedido, resultado);
  DBMS_OUTPUT.PUT_LINE('Unidades: ' || resultado);
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="bloque-9"></a>
## Bloque 9 — Funciones

> **Conceptos clave:** `FUNCTION nombre(param tipo) RETURN tipo_retorno`,
> sentencia `RETURN valor` para devolver el resultado,
> función local (declarada dentro de un `DECLARE`),
> función almacenada (`CREATE OR REPLACE FUNCTION`),
> uso de cursores dentro de funciones.

[↑ Volver al índice](#indice)

---

### Referencia — Estructura de funciones

#### Función local (dentro de DECLARE)

```sql
DECLARE
  local VARCHAR2(30);

  FUNCTION flocalidad(numdep NUMBER) RETURN VARCHAR2
  IS loc VARCHAR2(30);
  BEGIN  -- inicio función
    SELECT localidad INTO loc FROM departamentose WHERE depnu = numdep;
    RETURN loc;
  END flocalidad;

BEGIN
  local := flocalidad(30);
  DBMS_OUTPUT.PUT_LINE(local);
END;
```

---

<a id="b9-ej1"></a>
### Ejercicio 9.1 — Función local que cuenta departamentos

**Instrucciones:**
- Crear una **función `F1`** dentro de un bloque `DECLARE` que cuente y devuelva cuántos departamentos hay en la tabla.
- Imprimir el resultado llamando a `F1` desde el `BEGIN ... END` del `DECLARE`.

```sql
DECLARE
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="b9-ej2"></a>
### Ejercicio 9.2 — Función almacenada con cursor

**Instrucciones:**
- Crear una **función almacenada `F2`** con `CREATE OR REPLACE` que devuelva cuántos pedidos tiene un producto dado.
- El número de producto se pide **por teclado**.
- Usar un **cursor** dentro de la función para recorrer los pedidos.
- En un `BEGIN ... END` aparte, ejecutar tanto `P2` (del ejercicio 8.2) como `F2`.

```sql
-- Crear función almacenada
CREATE OR REPLACE FUNCTION f2(num_producto NUMBER) RETURN NUMBER
IS
  -- Declarar cursor aquí
  -- Tu solución aquí
BEGIN
  -- Tu solución aquí
  RETURN ...;
END f2;

-- Ejecutar P2 y F2 en el mismo bloque
DECLARE
  resultado_p2 NUMBER;
  resultado_f2 NUMBER;
BEGIN
  p2(&num_pedido, resultado_p2);
  DBMS_OUTPUT.PUT_LINE('Unidades pedido: ' || resultado_p2);

  resultado_f2 := f2(&num_producto);
  DBMS_OUTPUT.PUT_LINE('Pedidos del producto: ' || resultado_f2);
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

<a id="bloque-10"></a>
## Bloque 10 — Paquetes (Integrador Final)

> **Conceptos clave:** `CREATE OR REPLACE PACKAGE` (especificación — solo firmas públicas),
> `CREATE OR REPLACE PACKAGE BODY` (cuerpo — implementación completa),
> llamada a elementos del paquete con `nombre_paquete.nombre_elemento`,
> un paquete puede contener varios procedimientos y funciones.

[↑ Volver al índice](#indice)

---

### Referencia — Estructura de un paquete

```sql
-- 1. Especificación (cabecera pública)
CREATE OR REPLACE PACKAGE paq0 AS
  PROCEDURE p1(numero NUMBER);  -- solo la firma
END paq0;

-- 2. Cuerpo (implementación)
CREATE OR REPLACE PACKAGE BODY paq0 AS
  PROCEDURE p1(numero NUMBER)
  IS local VARCHAR2(30);
  BEGIN
    SELECT localidad INTO local FROM departamentose WHERE depnu = numero;
    DBMS_OUTPUT.PUT_LINE(local);
  END p1;
END paq0;

-- 3. Llamada desde un bloque aparte
BEGIN
  paq0.p1(20);
END;
```

---

<a id="b10-final"></a>
### Ejercicio Final — PAQUETE10

**Instrucciones:**
- Crear un paquete llamado **`PAQUETE10`** que incluya:
  - La **función `F1`** del ejercicio 9.1 (cuenta cuántos departamentos hay).
  - El **procedimiento `P2`** del ejercicio 8.2 (devuelve unidades para un número de pedido dado).
- Crear la **especificación** del paquete con las firmas de `F1` y `P2`.
- Crear el **cuerpo** del paquete con la implementación completa de ambos.
- En un `BEGIN ... END` aparte, llamar a `PAQUETE10.F1` y `PAQUETE10.P2` e imprimir los resultados.

```sql
-- Especificación del paquete
CREATE OR REPLACE PACKAGE paquete10 AS
  -- Tu solución aquí (firmas de F1 y P2)
END paquete10;

-- Cuerpo del paquete
CREATE OR REPLACE PACKAGE BODY paquete10 AS
  -- Tu solución aquí (implementación de F1 y P2)
END paquete10;

-- Ejecución desde bloque aparte
DECLARE
  num_dep   NUMBER;
  unidades  NUMBER;
BEGIN
  num_dep := paquete10.f1;
  DBMS_OUTPUT.PUT_LINE('Departamentos: ' || num_dep);

  paquete10.p2(&num_pedido, unidades);
  DBMS_OUTPUT.PUT_LINE('Unidades: ' || unidades);
END;
```

> **Resultado obtenido:**
> ```
> [Escribe aquí la salida de consola]
> ```

[↑ Volver al índice](#indice)

---

*Fin del documento — Ruta de Aprendizaje PL/SQL*
