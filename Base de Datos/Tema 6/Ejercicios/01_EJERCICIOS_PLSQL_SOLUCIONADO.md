<a id="indice"></a>
# Ejercicios PL/SQL — Guía de Estudio con Soluciones

> **Cómo usar este documento:**
> Estudia primero la teoría de cada bloque, luego lee la solución y el razonamiento.
> El objetivo no es memorizar código sino entender *por qué* se hace así y no de otra manera.
> Las tablas usadas son: `departamentose (depnu, nombre, localidad)`,
> `pedidose (pedidonu, clientenu, productonu, unidades)` y
> `productose (productonu, nombre, precio, stock)`.

---

## Índice

| Bloque | Tema |
|--------|------|
| [Bloque 1](#bloque-1) | Fundamentos: Variables, Tipos y Condicionales |
| [Bloque 2](#bloque-2) | Estructuras de Control: Bucles |
| [Bloque 3](#bloque-3) | Interacción con la BD: SELECT INTO, %TYPE, %ROWTYPE |
| [Bloque 4](#bloque-4) | Tipos Compuestos: Registros (RECORD) |
| [Bloque 5](#bloque-5) | Tipos Compuestos: Arrays de Longitud Variable (VARRAY) |
| [Bloque 6](#bloque-6) | Cursores |
| [Bloque 7](#bloque-7) | Excepciones |
| [Bloque 8](#bloque-8) | Procedimientos |
| [Bloque 9](#bloque-9) | Funciones |
| [Bloque 10](#bloque-10) | Paquetes — Integrador Final |
| [Notas de Interés](#notas) | Errores típicos y estrategias de resolución |

---

<a id="bloque-1"></a>
## Bloque 1 — Fundamentos: Variables, Tipos y Condicionales

[↑ Volver al índice](#indice)

### Teoría

PL/SQL (Procedural Language / SQL) es la extensión procedimental de Oracle que añade variables, estructuras de control y manejo de errores al SQL estándar. Todo bloque PL/SQL tiene esta anatomía:

```
DECLARE   -- opcional: aquí se declaran variables, tipos, cursores
BEGIN     -- obligatorio: aquí va la lógica
EXCEPTION -- opcional: aquí se capturan errores
END;      -- cierra el bloque
```

**Variables y tipos básicos:**

| Tipo | Uso | Ejemplo |
|------|-----|---------|
| `NUMBER` | Cualquier número | `NUMBER`, `NUMBER(5)`, `NUMBER(6,2)` |
| `VARCHAR2(n)` | Texto de hasta n caracteres | `VARCHAR2(30)` |
| `BOOLEAN` | Verdadero/falso | Solo en PL/SQL, no en SQL |
| `DATE` | Fecha y hora | Se opera con `SYSDATE` |

- El operador de **asignación** es `:=` (no `=`, que en PL/SQL es comparación).
- Para pedir valores al usuario se usa `&nombre_variable` (variable de sustitución de SQL*Plus/SQL Developer). Para texto, se rodea de comillas: `'&apellido'`.
- **`BOOLEAN` no se puede leer con `&`** porque las variables de sustitución siempre se tratan como texto o número. El truco es leer un `NUMBER` (1 = TRUE, 0 = FALSE) y evaluarlo con IF.

**Condicional IF:**
```sql
IF (condicion) THEN
  -- se ejecuta si es verdad
ELSIF (otra_condicion) THEN  -- nota: ELSIF, no ELSEIF
  -- segunda condición
ELSE
  -- si ninguna anterior se cumple
END IF;
```

**Comentarios:**
- Una línea: `-- esto es un comentario`
- Varias líneas: `/* esto es un bloque de comentario */`

**Lógica de resolución para este bloque:**
1. Identifica qué variables necesitas y de qué tipo son.
2. Declara todas en el `DECLARE`.
3. En el `BEGIN`, asigna valores (ya sea fijos con `:=` o desde teclado con `&`).
4. Aplica la lógica condicional con `IF`.
5. Imprime con `DBMS_OUTPUT.PUT_LINE(...)`. Para concatenar usa `||`.

---

### Ejercicio 1.1 — Variables numéricas y suma básica

**Razonamiento:** Necesito dos variables `NUMBER`. Una se inicializa directamente a `5` en el `DECLARE` (con `:= 5`). La otra se pide por teclado con `&`. Luego concateno el resultado en una cadena para imprimirlo en una línea.

```sql
DECLARE
  num1 NUMBER := 5;
  num2 NUMBER := &num2;
BEGIN
  DBMS_OUTPUT.PUT_LINE('La suma es: ' || (num1 + num2));
END;
```

> **Por qué paréntesis en `(num1 + num2)`:** el operador `||` tiene mayor precedencia que `+`, así que sin paréntesis Oracle intentaría concatenar `num1` con la cadena `' + num2'` antes de sumar. Los paréntesis fuerzan que se calcule la suma primero.

> **Resultado esperado** (si el usuario introduce 3):
> ```
> La suma es: 8
> ```

[↑ Volver al índice](#indice)

---

### Ejercicio 1.2 — Variables con límites, formato de salida y comentarios de línea

**Razonamiento:** `NUMBER(1)` acepta solo 1 dígito (0-9). `NUMBER(2)` acepta hasta 2 (0-99). Si el usuario introduce un valor fuera de rango, Oracle lanzará un error de conversión. El formato exacto pedido es `La suma X + Y = Z`, que consigo concatenando las variables con `||`.

```sql
DECLARE
  num1 NUMBER(1) := &num1;
  num2 NUMBER(2) := &num2; -- segundo operando, máximo 2 dígitos
BEGIN
  DBMS_OUTPUT.PUT_LINE('La suma ' || num1 || ' + ' || num2 || ' = ' || (num1 + num2));
END;
```

> **Por qué `NUMBER(1)` y no solo `NUMBER`:** la precisión limita los valores aceptados. `NUMBER` sin precisión acepta hasta 38 dígitos. Usar `NUMBER(1)` hace que Oracle rechace valores mayores a 9 con un error de precisión.

> **Resultado esperado** (num1=7, num2=25):
> ```
> La suma 7 + 25 = 32
> ```

[↑ Volver al índice](#indice)

---

### Ejercicio 1.3 — Variables mixtas, condicional IF y comentario multilínea

**Razonamiento:** Tengo variables de distintos tipos. `nombre` se inicializa directamente en la declaración. `apellido` viene del teclado: como es texto, necesito `'&apellido'` con comillas simples para que Oracle lo trate como cadena. El IF es simple: si se cumple la condición, imprime; si no, no hace nada (no hay ELSE).

```sql
/*
  Ejercicio 3: Condicional con variables de tipo texto y numérico.
  Comprueba si el alumno ha aprobado según la nota introducida.
*/
DECLARE
  num      NUMBER    := &num;
  nota     NUMBER    := &nota;
  nombre   VARCHAR2(10) := 'Andres';
  apellido VARCHAR2(20) := '&apellido';
BEGIN
  IF (nota > 5) THEN
    DBMS_OUTPUT.PUT_LINE('El alumno ' || nombre || ' ' || apellido || ' ha aprobado');
  END IF;
END;
```

> **Por qué comillas en `'&apellido'`:** las variables de sustitución con `&` devuelven texto sin delimitadores. Si escribes `apellido VARCHAR2(20) := &apellido;`, Oracle lo interpretará como un identificador, no como una cadena. Las comillas simples lo convierten en literal.

> **Resultado esperado** (nota=7, apellido=Garcia):
> ```
> El alumno Andres Garcia ha aprobado
> ```

[↑ Volver al índice](#indice)

---

### Ejercicio 1.4 — Boolean e IF/ELSE

**Razonamiento:** `BOOLEAN` no se puede leer con `&` porque las variables de sustitución son texto o número, no booleanas. La solución estándar es leer un `NUMBER` (1 = true, 0 = false) y evaluar con IF. Para elevar al cuadrado uso `POWER(cifra, 2)`.

```sql
DECLARE
  cifra NUMBER := &cifra;
  combo NUMBER := &combo;  -- 1 representa TRUE, 0 representa FALSE
BEGIN
  IF (combo = 1) THEN
    DBMS_OUTPUT.PUT_LINE('Te ha tocado ' || POWER(cifra, 2));
  ELSE
    DBMS_OUTPUT.PUT_LINE('Te ha tocado ' || cifra);
  END IF;
END;
```

> **Por qué no `IF combo = TRUE`:** en PL/SQL, `TRUE` y `FALSE` son literales del tipo `BOOLEAN`, que es incompatible con `NUMBER`. Comparar un `NUMBER` con un literal booleano daría error de tipos.

> **Resultado esperado** (cifra=4, combo=1):
> ```
> Te ha tocado 16
> ```

[↑ Volver al índice](#indice)

---

<a id="bloque-2"></a>
## Bloque 2 — Estructuras de Control: Bucles

[↑ Volver al índice](#indice)

### Teoría

PL/SQL tiene tres estructuras de bucle. La clave está en elegir la correcta según lo que sabes antes de entrar al bucle:

| Bucle | Cuándo usarlo | Estructura |
|-------|--------------|------------|
| `LOOP` | No sabes cuántas iteraciones. La condición de parada está **dentro**. | `LOOP ... EXIT WHEN cond; ... END LOOP;` |
| `FOR` | Sabes exactamente el número de iteraciones o el rango. | `FOR i IN inicio..fin LOOP ... END LOOP;` |
| `WHILE` | La condición de parada se evalúa **antes** de cada iteración. | `WHILE cond LOOP ... END LOOP;` |

**Patrón acumulador:** la forma más habitual de sumar en un bucle es:
```sql
suma NUMBER := 0;  -- inicializa a 0
...
LOOP
  suma := suma + nuevo_valor;  -- acumula
END LOOP;
```

**Funciones matemáticas útiles:**
- `MOD(n, m)` → resto de dividir n entre m (para detectar múltiplos: `MOD(n, 3) = 0`)
- `SQRT(n)` → raíz cuadrada
- `POWER(base, exp)` → potencia

**Lógica de resolución:**
- Si el enunciado dice "mientras que..." → WHILE.
- Si dice "n veces" o "del 1 al n" → FOR.
- Si dice "hasta que..." o la condición de salida es el resultado de la propia iteración → LOOP.

---

### Ejercicio 2.0 — Suma acumulativa con LOOP (parada condicional)

**Razonamiento:** No sé cuántas iteraciones necesito; depende de cuándo la suma supere 10. Por eso uso LOOP. El contador `i` empieza en 1 y crece. Sumo primero y luego evalúo: así, cuando la suma supera 10, el bucle ya registró ese valor y es el que imprimo.

```sql
DECLARE
  i    NUMBER := 1;
  suma NUMBER := 0;
BEGIN
  LOOP
    suma := suma + i;
    EXIT WHEN suma > 10;
    i := i + 1;
  END LOOP;
  DBMS_OUTPUT.PUT_LINE('La suma es: ' || suma);
END;
```

> **Traza de ejecución:**
> - i=1: suma=1 → no sale
> - i=2: suma=3 → no sale
> - i=3: suma=6 → no sale
> - i=4: suma=10 → no sale (10 no es mayor que 10)
> - i=5: suma=15 → EXIT (15 > 10)
>
> **Resultado:** `La suma es: 15`

> **Por qué `EXIT WHEN` después de sumar:** si pusiera `EXIT WHEN` antes, el valor que supera 10 nunca se añadiría a la suma. El orden importa.

[↑ Volver al índice](#indice)

---

### Ejercicio 2.1 — Suma del 1 al 10 con FOR

**Razonamiento:** Sé exactamente el rango (1 a 10), así que FOR es la elección natural. La variable `i` del FOR es implícita, no hace falta declararla. Solo necesito declarar el acumulador.

```sql
DECLARE
  suma NUMBER := 0;
BEGIN
  FOR i IN 1..10 LOOP
    suma := suma + i;
  END LOOP;
  DBMS_OUTPUT.PUT_LINE('Suma del 1 al 10: ' || suma);
END;
```

> **Por qué no declaro `i` en el DECLARE:** el FOR crea su propia variable de iteración automáticamente. Si la declaras también en DECLARE, habrá conflicto de nombres. PL/SQL da precedencia a la variable local del FOR.

> **Resultado:** `Suma del 1 al 10: 55`

[↑ Volver al índice](#indice)

---

### Ejercicio 2.2 — Suma del 1 al 10 con WHILE

**Razonamiento:** El WHILE evalúa la condición antes de entrar. Por eso necesito inicializar `i := 1` antes del bucle. Dentro, sumo y luego incremento. Si incrementara antes de sumar, empezaría desde 2.

```sql
DECLARE
  i    NUMBER := 1;
  suma NUMBER := 0;
BEGIN
  WHILE i <= 10 LOOP
    suma := suma + i;
    i := i + 1;
  END LOOP;
  DBMS_OUTPUT.PUT_LINE('Suma del 1 al 10: ' || suma);
END;
```

> **Diferencia con LOOP:** en WHILE la condición está fuera del cuerpo y se evalúa al principio. En LOOP la condición está dentro y se evalúa cuando llega a `EXIT WHEN`. Si `i` empezara en 11, WHILE no entraría al bucle; LOOP entraría una vez.

> **Resultado:** `Suma del 1 al 10: 55`

[↑ Volver al índice](#indice)

---

### Ejercicio 2.3 — Mayor de tres números

**Razonamiento:** Necesito comparar tres valores. Uso `ELSIF` (no `ELSEIF`) para encadenar condiciones. La condición de cada rama debe garantizar que ese valor es el mayor de los tres, no solo que supera a uno de ellos. Por eso uso `AND`.

```sql
DECLARE
  n1    NUMBER := &n1;
  n2    NUMBER := &n2;
  n3    NUMBER := &n3;
  mayor NUMBER;
BEGIN
  IF (n1 >= n2 AND n1 >= n3) THEN
    mayor := n1;
  ELSIF (n2 >= n1 AND n2 >= n3) THEN
    mayor := n2;
  ELSE
    mayor := n3;
  END IF;
  DBMS_OUTPUT.PUT_LINE('El mayor es: ' || mayor);
END;
```

> **Por qué `>=` y no `>`:** con `>` estricto, si dos números son iguales y ambos son el mayor, el primero siempre ganaría pero el `ELSIF` nunca se evaluaría para el segundo. Con `>=` cualquier empate queda correctamente resuelto.

> **Resultado esperado** (n1=5, n2=12, n3=8):
> ```
> El mayor es: 12
> ```

[↑ Volver al índice](#indice)

---

### Ejercicio 2.4 — Distancia entre dos puntos

**Razonamiento:** La fórmula euclidiana es `d = √((x2-x1)² + (y2-y1)²)`. Oracle no tiene operador `^` para elevar; se usa `POWER(base, exponente)`. La raíz cuadrada es `SQRT()`. Declaro cuatro variables para las coordenadas y una para el resultado.

```sql
DECLARE
  x1        NUMBER := &x1;
  y1        NUMBER := &y1;
  x2        NUMBER := &x2;
  y2        NUMBER := &y2;
  distancia NUMBER;
BEGIN
  distancia := SQRT(POWER(x2 - x1, 2) + POWER(y2 - y1, 2));
  DBMS_OUTPUT.PUT_LINE('Distancia entre los puntos: ' || distancia);
END;
```

> **Resultado esperado** (x1=0, y1=0, x2=3, y2=4):
> ```
> Distancia entre los puntos: 5
> ```
> (El triángulo 3-4-5 es el más clásico para comprobar.)

[↑ Volver al índice](#indice)

---

<a id="bloque-3"></a>
## Bloque 3 — Interacción con la BD: SELECT INTO, %TYPE, %ROWTYPE

[↑ Volver al índice](#indice)

### Teoría

**El problema de SELECT en PL/SQL:**
En SQL puro, un `SELECT` devuelve un conjunto de filas que va al cliente. En PL/SQL, no podemos hacer eso directamente; necesitamos almacenar el resultado en variables. Para eso existe `SELECT ... INTO`.

```sql
SELECT columna INTO variable FROM tabla WHERE condicion;
```

**Regla crítica:** `SELECT INTO` debe devolver **exactamente una fila**.
- 0 filas → excepción `NO_DATA_FOUND`
- 2 o más filas → excepción `TOO_MANY_ROWS`

Esta restricción es lo que nos obligará a usar cursores (Bloque 6) cuando esperemos múltiples filas.

**Anclar tipos con %TYPE y %ROWTYPE:**

`variable tabla.columna%TYPE` declara la variable con el mismo tipo que esa columna. Si el DBA cambia `NUMBER(6)` a `NUMBER(10)`, tu variable se adapta sola sin tocar el código.

`variable tabla%ROWTYPE` declara una variable que tiene un campo por cada columna de la tabla. Se accede con `variable.nombre_columna`.

**¿Cuándo usar cada uno?**
- `%TYPE`: cuando solo necesitas una columna concreta.
- `%ROWTYPE`: cuando vas a cargar una fila completa de la tabla.
- Tipo manual: cuando la variable no corresponde directamente a ninguna columna.

**DBMS_RANDOM:**
El paquete built-in para generar números aleatorios.
- `DBMS_RANDOM.VALUE(a, b)` → número real en el intervalo `[a, b)` (b no incluido).
- `TRUNC(DBMS_RANDOM.VALUE(1, 11))` → entero entre 1 y 10.

---

### Ejercicio 3.1 — Suma aleatoria con DBMS_RANDOM

**Razonamiento:** Primero genero el número de iteraciones `n` (1-10). Luego entro en un bucle FOR de 1 a n, generando en cada iteración un número entre 10 y 100. El límite superior de VALUE es exclusivo: para obtener hasta 100, paso 101.

```sql
DECLARE
  n         NUMBER;
  aleatorio NUMBER;
  suma      NUMBER := 0;
BEGIN
  n := TRUNC(DBMS_RANDOM.VALUE(1, 11));  -- entero entre 1 y 10
  DBMS_OUTPUT.PUT_LINE('Iteraciones: ' || n);

  FOR i IN 1..n LOOP
    aleatorio := TRUNC(DBMS_RANDOM.VALUE(10, 101));  -- entero entre 10 y 100
    DBMS_OUTPUT.PUT_LINE('Número ' || i || ': ' || aleatorio);
    suma := suma + aleatorio;
  END LOOP;

  DBMS_OUTPUT.PUT_LINE('Suma total: ' || suma);
END;
```

> **Por qué TRUNC y no ROUND:** `VALUE(10, 101)` puede devolver 10.0, 10.7, 100.9... Con `TRUNC` truncamos hacia abajo, garantizando enteros en [10, 100]. Con `ROUND` podríamos obtener 101 (si el valor es 100.5 o más).

[↑ Volver al índice](#indice)

---

### Ejercicio 3.2 — Múltiplos de 3 entre 1 y 100

**Razonamiento:** Un número es múltiplo de 3 si su resto al dividir entre 3 es 0. `MOD(i, 3) = 0` comprueba eso. Recorro todos los números del 1 al 100 con FOR y cuento los que cumplen la condición.

```sql
DECLARE
  contador NUMBER := 0;
BEGIN
  FOR i IN 1..100 LOOP
    IF MOD(i, 3) = 0 THEN
      contador := contador + 1;
    END IF;
  END LOOP;
  DBMS_OUTPUT.PUT_LINE('Múltiplos de 3 entre 1 y 100: ' || contador);
END;
```

> **Resultado:** `Múltiplos de 3 entre 1 y 100: 33`
> (3, 6, 9, ..., 99 → son 33 múltiplos)

[↑ Volver al índice](#indice)

---

### Ejercicio 3.3 — Día de la semana actual

**Razonamiento:** `SYSDATE` devuelve la fecha y hora actuales del servidor Oracle. `TO_CHAR` formatea una fecha como texto. El formato `'DAY'` da el nombre completo del día; `'D'` da el número (1=Domingo en configuración anglosajona, pero varía con `NLS_TERRITORY`).

```sql
DECLARE
  nombre_dia VARCHAR2(20);
  num_dia    NUMBER;
BEGIN
  nombre_dia := TO_CHAR(SYSDATE, 'DAY', 'NLS_DATE_LANGUAGE=SPANISH');
  num_dia    := TO_NUMBER(TO_CHAR(SYSDATE, 'D'));
  DBMS_OUTPUT.PUT_LINE('Día: ' || TRIM(nombre_dia) || ', número: ' || num_dia);
END;
```

> **Por qué TRIM:** el formato `'DAY'` rellena el nombre con espacios hasta la longitud del día más largo ("MIÉRCOLES"). `TRIM` elimina esos espacios sobrantes.

> **Resultado** (si hoy es jueves):
> ```
> Día: JUEVES, número: 5
> ```

[↑ Volver al índice](#indice)

---

### Ejercicio 3.4 — COUNT de departamentos con SELECT INTO

**Razonamiento:** `COUNT(*)` siempre devuelve exactamente una fila (aunque la tabla esté vacía devuelve 0). Por eso es seguro usarlo con `SELECT INTO` sin riesgo de excepción.

```sql
DECLARE
  total NUMBER;
BEGIN
  SELECT COUNT(*) INTO total FROM departamentose;
  DBMS_OUTPUT.PUT_LINE('Número de departamentos: ' || total);
END;
```

> **Resultado esperado:**
> ```
> Número de departamentos: 4
> ```

[↑ Volver al índice](#indice)

---

### Ejercicio 3.5 — Suma de unidades por producto

**Razonamiento:** `SUM()` también devuelve siempre una fila (NULL si no hay datos), así que es seguro con `SELECT INTO`. En la parte 5.2 sustituyo el literal `20` por una variable que el usuario introduce.

```sql
-- 5.1 — Producto fijo
DECLARE
  total NUMBER;
BEGIN
  SELECT SUM(unidades) INTO total
  FROM pedidose
  WHERE productonu = 20;
  DBMS_OUTPUT.PUT_LINE('Total unidades producto 20: ' || total);
END;

-- 5.2 — Producto por teclado
DECLARE
  total  NUMBER;
  numpro NUMBER := &numpro;
BEGIN
  SELECT SUM(unidades) INTO total
  FROM pedidose
  WHERE productonu = numpro;
  DBMS_OUTPUT.PUT_LINE('Total unidades producto ' || numpro || ': ' || total);
END;
```

> **Por qué declarar `numpro` como variable y no usar `&numpro` directamente en el WHERE:** se puede hacer directamente (`WHERE productonu = &numpro`), pero declararlo como variable es más limpio: la sustitución ocurre una sola vez en el DECLARE, no cada vez que aparezca en el código.

[↑ Volver al índice](#indice)

---

### Ejercicio 3.6 — SELECT INTO con dos variables

**Razonamiento:** Cuando necesito más de una columna, separo las variables en el `INTO` con comas. El orden debe coincidir exactamente con el orden del `SELECT`.

```sql
DECLARE
  vnombre    departamentose.nombre%TYPE;
  vlocalidad departamentose.localidad%TYPE;
BEGIN
  SELECT nombre, localidad
  INTO   vnombre, vlocalidad
  FROM   departamentose
  WHERE  depnu = 20;

  DBMS_OUTPUT.PUT_LINE('Nombre: ' || vnombre || ', Localidad: ' || vlocalidad);
END;
```

> **Por qué uso `%TYPE`:** si el DBA cambia `nombre` de `VARCHAR2(30)` a `VARCHAR2(50)`, mi código sigue funcionando sin modificación. Es la forma "robusta" de declarar variables ligadas a columnas de tabla.

[↑ Volver al índice](#indice)

---

### Ejercicio 3.7 — Uso de %TYPE para suma de unidades

**Razonamiento:** Aunque la suma puede superar el valor máximo de una fila individual, declarar con `%TYPE` de la columna `unidades` es la forma correcta de acoplar el tipo. Si `unidades` es `NUMBER(6)`, `SUM(unidades)` podría desbordarlo, pero el ejercicio pide explícitamente usar `%TYPE`.

```sql
DECLARE
  total_unidades pedidose.unidades%TYPE;
BEGIN
  SELECT SUM(unidades) INTO total_unidades
  FROM pedidose;
  DBMS_OUTPUT.PUT_LINE('Total unidades en todos los pedidos: ' || total_unidades);
END;
```

[↑ Volver al índice](#indice)

---

### Ejercicio 3.8 — Uso de %ROWTYPE

**Razonamiento:** `%ROWTYPE` crea una variable con un campo por cada columna de la tabla. Cargar una fila completa con `SELECT * INTO fila_dep` es más limpio que tener múltiples variables. Accedo a cada campo con la notación `variable.nombre_columna`.

```sql
DECLARE
  fila_dep departamentose%ROWTYPE;
BEGIN
  SELECT * INTO fila_dep
  FROM departamentose
  WHERE depnu = 40;

  DBMS_OUTPUT.PUT_LINE('Nombre: '    || fila_dep.nombre);
  DBMS_OUTPUT.PUT_LINE('Localidad: ' || fila_dep.localidad);
END;
```

> **Diferencia entre `%TYPE` y `%ROWTYPE`:**
> - `%TYPE` → tipo de una sola columna.
> - `%ROWTYPE` → estructura de toda la tabla (o cursor). Ideal cuando vas a usar muchas columnas de la misma fila.

[↑ Volver al índice](#indice)

---

<a id="bloque-4"></a>
## Bloque 4 — Tipos Compuestos: Registros (RECORD)

[↑ Volver al índice](#indice)

### Teoría

Un `RECORD` es un tipo de dato compuesto que agrupa varios campos de distintos tipos bajo un mismo nombre. Es equivalente a un `struct` en C o una clase de datos en Java.

```sql
TYPE nombre_tipo IS RECORD (
  campo1 tipo1,
  campo2 tipo2,
  ...
);
variable nombre_tipo;
```

**¿Por qué usar RECORD en lugar de múltiples variables?**
- Agrupa datos relacionados lógicamente.
- Permite pasar un "grupo" de valores a un procedimiento como un solo argumento.
- Con `SELECT ... INTO variable_record` puedes cargar varias columnas de golpe.

**Diferencia con `%ROWTYPE`:**
- `%ROWTYPE` está **acoplado** a una tabla: tiene exactamente los mismos campos con los mismos tipos.
- `RECORD` es **independiente**: defines tú los campos. Más flexible, pero hay que mantenerlo manualmente.

**DML con RECORD:**
```sql
-- INSERT: tienes que especificar campo a campo
INSERT INTO tabla VALUES (r.campo1, r.campo2, r.campo3, r.campo4);

-- SELECT INTO un RECORD: Oracle asigna por posición
SELECT col1, col2 INTO r FROM tabla WHERE ...;
-- Ahora r.campo1 tiene col1, r.campo2 tiene col2, etc.
```

---

### Ejercicio 4.1 — Tipo registro con INSERT en tabla

**Razonamiento:** Defino el tipo `TIPOPRO` con los cuatro campos que coinciden con la estructura de `productose`. Creo la variable, asigno los valores campo a campo con `:=`, los imprimo y luego los inserto en la tabla.

```sql
DECLARE
  TYPE tipopro IS RECORD (
    campo1 NUMBER,
    campo2 VARCHAR2(40),
    campo3 NUMBER(6,2),
    campo4 NUMBER
  );
  vtipopro tipopro;
BEGIN
  vtipopro.campo1 := 100;
  vtipopro.campo2 := 'tablon';
  vtipopro.campo3 := 234.56;
  vtipopro.campo4 := 34;

  DBMS_OUTPUT.PUT_LINE(vtipopro.campo1 || ' | ' || vtipopro.campo2 ||
                       ' | ' || vtipopro.campo3 || ' | ' || vtipopro.campo4);

  INSERT INTO productose (productonu, nombre, precio, stock)
  VALUES (vtipopro.campo1, vtipopro.campo2, vtipopro.campo3, vtipopro.campo4);

  DBMS_OUTPUT.PUT_LINE('Fila insertada correctamente.');
END;
```

> **Por qué no `INSERT INTO productose VALUES vtipopro` directamente:** Oracle no soporta insertar un RECORD completo como valor directo en un INSERT estándar (sí lo permite en ciertos contextos de Oracle 21c+, pero no es universal). El enfoque seguro es listar los campos explícitamente.

[↑ Volver al índice](#indice)

---

### Ejercicio 4.2.1 — SELECT INTO sobre variable registro

**Razonamiento:** `SELECT INTO` puede cargar directamente en una variable RECORD si los tipos son compatibles. Oracle asigna por posición: la primera columna del SELECT va al primer campo del RECORD, etc. El orden del SELECT debe coincidir con el orden de los campos del RECORD.

```sql
DECLARE
  TYPE tipopro IS RECORD (
    campo1 NUMBER,
    campo2 VARCHAR2(40),
    campo3 NUMBER(6,2),
    campo4 NUMBER
  );
  vtipopro tipopro;
BEGIN
  SELECT productonu, nombre, precio, stock
  INTO   vtipopro
  FROM   productose
  WHERE  productonu = 30;

  DBMS_OUTPUT.PUT_LINE('Producto: ' || vtipopro.campo1 || ' | ' || vtipopro.campo2 ||
                       ' | ' || vtipopro.campo3 || ' | ' || vtipopro.campo4);
END;
```

> **Resultado esperado:**
> ```
> Producto: 30 | MARTILLO | 15.5 | 200
> ```

[↑ Volver al índice](#indice)

---

### Ejercicio 4.2.2 — DELETE usando registro pedido por teclado

**Razonamiento:** Pido el `productonu` al usuario, lo cargo en el campo `campo1` del registro, y uso ese valor para el DELETE. Uso `SQL%ROWCOUNT` para saber cuántas filas se borraron (útil para confirmar que la operación tuvo efecto).

```sql
DECLARE
  TYPE tipopro IS RECORD (
    campo1 NUMBER,
    campo2 VARCHAR2(40),
    campo3 NUMBER(6,2),
    campo4 NUMBER
  );
  vtipopro tipopro;
BEGIN
  vtipopro.campo1 := &productonu;

  DELETE FROM productose
  WHERE productonu = vtipopro.campo1;

  DBMS_OUTPUT.PUT_LINE('Filas borradas: ' || SQL%ROWCOUNT);
END;
```

> **`SQL%ROWCOUNT`:** atributo implícito de cursor que devuelve el número de filas afectadas por la última sentencia DML (INSERT, UPDATE o DELETE). Si es 0, el producto no existía.

[↑ Volver al índice](#indice)

---

<a id="bloque-5"></a>
## Bloque 5 — Tipos Compuestos: Arrays de Longitud Variable (VARRAY)

[↑ Volver al índice](#indice)

### Teoría

Un `VARRAY` (Variable-size ARRAy) es una colección **ordenada** y **acotada**: se define con un tamaño máximo y los elementos se acceden por índice numérico (empezando en 1, no en 0).

```sql
TYPE nombre_tipo IS VARRAY(max) OF tipo_elemento;
variable nombre_tipo;
```

**Inicialización obligatoria:** un VARRAY es `NULL` por defecto. Para poder usarlo hay que inicializarlo con el constructor del tipo (que tiene el mismo nombre que el tipo):
```sql
vtacad tacad('val1', 'val2', 'val3');  -- constructor con valores
vtacad tacad();                         -- constructor vacío (0 elementos)
```

Si está vacío y quieres añadir elementos, usa `EXTEND`:
```sql
vtacad.EXTEND;      -- añade 1 elemento NULL
vtacad.EXTEND(3);   -- añade 3 elementos NULL
```

**Métodos del VARRAY:**

| Método | Devuelve |
|--------|----------|
| `v.FIRST` | Índice del primer elemento (siempre 1 si no está vacío) |
| `v.LAST` | Índice del último elemento (= COUNT) |
| `v.COUNT` | Número de elementos actuales |
| `v.LIMIT` | Tamaño máximo definido en el TYPE |

**¿Cuándo VARRAY y no variables sueltas?**
Cuando tienes una colección de elementos del mismo tipo que quieres recorrer con un bucle. Un VARRAY de 10 elementos es mejor que 10 variables `n1, n2, ..., n10`.

---

### Ejercicio 5.1 — VARRAY de cadenas con métodos básicos

**Razonamiento:** Declaro el tipo con máximo 4 elementos y lo inicializo con 3 valores directamente en el constructor. El cuarto slot queda sin usar. `LIMIT` devuelve 4 (el máximo), `COUNT` devuelve 3 (los usados actualmente).

```sql
DECLARE
  TYPE tacad IS VARRAY(4) OF VARCHAR2(30);
  vtacad tacad := tacad('Hola', 'Mundo', 'PL/SQL');
BEGIN
  DBMS_OUTPUT.PUT_LINE('FIRST: ' || vtacad.FIRST);
  DBMS_OUTPUT.PUT_LINE('COUNT: ' || vtacad.COUNT);
  DBMS_OUTPUT.PUT_LINE('LAST:  ' || vtacad.LAST);
  DBMS_OUTPUT.PUT_LINE('LIMIT: ' || vtacad.LIMIT);
  DBMS_OUTPUT.PUT_LINE('Posición 3: ' || vtacad(3));
END;
```

> **Resultado:**
> ```
> FIRST: 1
> COUNT: 3
> LAST:  3
> LIMIT: 4
> Posición 3: PL/SQL
> ```

> **Diferencia COUNT vs LIMIT:** COUNT es cuántos elementos hay ahora; LIMIT es cuántos puede haber como máximo. Siempre COUNT ≤ LIMIT.

[↑ Volver al índice](#indice)

---

### Ejercicio 5.2 — VARRAY de registros TPERSONA

**Razonamiento:** Primero defino el tipo RECORD `TPERSONA`, luego el tipo VARRAY que contiene elementos de ese tipo. Como los elementos son RECORDs, no puedo usar el constructor directamente con valores; necesito inicializar vacío y extender, luego asignar campo a campo.

```sql
DECLARE
  TYPE tpersona IS RECORD (edad NUMBER, nombre VARCHAR2(30));
  TYPE tapersona IS VARRAY(3) OF tpersona;
  vtapersona tapersona := tapersona();
BEGIN
  vtapersona.EXTEND(3);  -- reservo 3 posiciones (con campos en NULL)

  vtapersona(1).edad   := 1;
  vtapersona(1).nombre := 'ANA';

  vtapersona(2).edad   := 2;
  vtapersona(2).nombre := 'ANDRES';

  vtapersona(3).edad   := &edad;
  vtapersona(3).nombre := '&nombre';

  FOR i IN vtapersona.FIRST..vtapersona.LAST LOOP
    DBMS_OUTPUT.PUT_LINE('Nombre: ' || vtapersona(i).nombre);
  END LOOP;
END;
```

> **Por qué `FIRST..LAST` en el FOR y no `1..3`:** usar `FIRST` y `LAST` hace el código más genérico. Si el array tuviera 10 elementos, el bucle funcionaría igual sin cambiar nada.

> **Resultado** (si el usuario introduce edad=25, nombre=JUAN):
> ```
> Nombre: ANA
> Nombre: ANDRES
> Nombre: JUAN
> ```

[↑ Volver al índice](#indice)

---

### Ejercicio 5.3 — VARRAY numérico rellenado con bucle

**Razonamiento:** Inicializo el VARRAY con 4 ceros (para que tenga 4 posiciones ya creadas). Luego el FOR los sobreescribe con el triple del índice. Al final recorro con otro FOR para imprimir todo.

```sql
DECLARE
  TYPE tavnum IS VARRAY(4) OF NUMBER;
  vtavnum tavnum := tavnum(0, 0, 0, 0);  -- 4 posiciones inicializadas a 0
BEGIN
  FOR i IN 1..4 LOOP
    vtavnum(i) := i * 3;
  END LOOP;

  DBMS_OUTPUT.PUT_LINE('FIRST: ' || vtavnum.FIRST);
  DBMS_OUTPUT.PUT_LINE('COUNT: ' || vtavnum.COUNT);
  DBMS_OUTPUT.PUT_LINE('LAST:  ' || vtavnum.LAST);
  DBMS_OUTPUT.PUT_LINE('LIMIT: ' || vtavnum.LIMIT);
  DBMS_OUTPUT.PUT_LINE('Posición 3: ' || vtavnum(3));

  DBMS_OUTPUT.PUT_LINE('--- Contenido completo ---');
  FOR i IN vtavnum.FIRST..vtavnum.LAST LOOP
    DBMS_OUTPUT.PUT_LINE('Posición ' || i || ': ' || vtavnum(i));
  END LOOP;
END;
```

> **Resultado:**
> ```
> FIRST: 1
> COUNT: 4
> LAST:  4
> LIMIT: 4
> Posición 3: 9
> --- Contenido completo ---
> Posición 1: 3
> Posición 2: 6
> Posición 3: 9
> Posición 4: 12
> ```

[↑ Volver al índice](#indice)

---

<a id="bloque-6"></a>
## Bloque 6 — Cursores

[↑ Volver al índice](#indice)

### Teoría

**El problema:** `SELECT INTO` falla si hay más de una fila. Pero en la base de datos real, casi siempre hay múltiples filas. Los **cursores** son la solución: un cursor es un puntero a un conjunto de resultados que puedes recorrer fila a fila.

**Ciclo de vida de un cursor explícito:**
```
DECLARE (define la consulta) → OPEN (ejecuta la consulta) → FETCH (lee una fila) → CLOSE (libera recursos)
```

**Atributos de cursor:**

| Atributo | Significado |
|----------|-------------|
| `%FOUND` | TRUE si el último FETCH obtuvo una fila |
| `%NOTFOUND` | TRUE si el último FETCH no obtuvo fila (fin de datos) |
| `%ROWCOUNT` | Número de filas FETCHeadas hasta ahora |
| `%ISOPEN` | TRUE si el cursor está abierto |

**Patrón FORMA 1 (explícito):**
```sql
OPEN cursor;
FETCH cursor INTO var;
WHILE cursor%FOUND LOOP
  -- procesar var
  FETCH cursor INTO var;  -- el segundo FETCH es crucial
END LOOP;
CLOSE cursor;
```
El truco: hacer el primer FETCH **antes** del WHILE para comprobar si hay datos. Dentro del bucle, el FETCH va **al final** para avanzar al siguiente registro.

**Patrón FORMA 2 (FOR implícito):**
Oracle gestiona todo: abre el cursor, hace FETCH automático y lo cierra. Más limpio cuando no necesitas controlar el cursor manualmente.

**REF CURSOR (cursor variable):**
Un REF CURSOR no apunta a una consulta fija: puede abrirse con distintas consultas en distintos momentos. Es el único tipo de cursor que se puede reutilizar o pasar como parámetro a un procedimiento.

---

### Ejercicio 6.1 — Cursor explícito con FORMA 1

**Razonamiento:** Defino el cursor con la consulta en DECLARE. En BEGIN: abro, hago el primer FETCH para "cebar" el bucle, entro en WHILE (que comprueba si ese FETCH encontró algo), proceso, y al final de cada iteración hago otro FETCH para avanzar.

```sql
DECLARE
  CURSOR c1 IS SELECT depnu FROM departamentose;
  vdepnu departamentose.depnu%TYPE;
BEGIN
  OPEN c1;
  FETCH c1 INTO vdepnu;
  WHILE c1%FOUND LOOP
    DBMS_OUTPUT.PUT_LINE('Departamento: ' || vdepnu);
    FETCH c1 INTO vdepnu;
  END LOOP;
  CLOSE c1;
END;
```

> **Error clásico:** olvidar el segundo `FETCH` dentro del WHILE. Si lo omites, el bucle se repite infinitamente con el mismo valor (el primero que se leyó).

[↑ Volver al índice](#indice)

---

### Ejercicio 6.2 — Cursor FOR combinado con VARRAY (FORMA 2)

**Razonamiento:** El FOR de cursor es más limpio. `reg` toma automáticamente los valores de cada fila. Para guardar en VARRAY uso EXTEND para ir añadiendo posiciones dinámicamente (no sé cuántos departamentos hay de antemano).

```sql
DECLARE
  CURSOR c1 IS SELECT depnu FROM departamentose;
  TYPE tvarray IS VARRAY(20) OF NUMBER;  -- máximo 20, suficiente margen
  varray tvarray := tvarray();
  idx    NUMBER  := 0;
BEGIN
  FOR reg IN c1 LOOP
    idx := idx + 1;
    varray.EXTEND;
    varray(idx) := reg.depnu;
  END LOOP;

  DBMS_OUTPUT.PUT_LINE('--- Contenido del VARRAY ---');
  FOR j IN 1..varray.COUNT LOOP
    DBMS_OUTPUT.PUT_LINE('Depto: ' || varray(j));
  END LOOP;
END;
```

> **Por qué EXTEND dentro del FOR:** en el FOR no sé cuántos elementos tiene el cursor. Inicio con VARRAY vacío y extiendo uno a uno conforme voy leyendo filas. Alternativa: saber de antemano el COUNT y pre-extender.

> **Por qué `varray.COUNT` en el segundo FOR y no `idx`:** ambos funcionan igual en este contexto. `COUNT` es más idiomático para colecciones.

[↑ Volver al índice](#indice)

---

### Ejercicio 6.3 — Cursor con parámetros

**Razonamiento:** El parámetro del cursor actúa como un "slot" que se rellena al hacer OPEN. Así evito tener que declarar el cursor con variables externas. El valor del parámetro se pide por teclado con `&` directamente en el OPEN.

```sql
DECLARE
  CURSOR c1(param NUMBER) IS
    SELECT nombre FROM productose WHERE productonu = param;
  vnombre productose.nombre%TYPE;
BEGIN
  OPEN c1(&param);
  FETCH c1 INTO vnombre;
  WHILE c1%FOUND LOOP
    DBMS_OUTPUT.PUT_LINE('Producto: ' || vnombre);
    FETCH c1 INTO vnombre;
  END LOOP;
  CLOSE c1;
END;
```

> **¿Por qué cursor con parámetro y no simplemente un cursor con una variable en el WHERE?** Si usas una variable del DECLARE en el WHERE del cursor, el cursor se "fija" al valor de esa variable cuando se abre. Con un parámetro, la intención queda explícita y el cursor es reutilizable con diferentes valores.

[↑ Volver al índice](#indice)

---

### Ejercicio 6.4 — REF CURSOR sobre productos (bucle WHILE)

**Razonamiento:** Un REF CURSOR se declara como tipo primero. La variable de ese tipo puede abrirse con cualquier consulta usando `OPEN c FOR SELECT ...`. Al volver a abrir (segunda apertura), Oracle cierra automáticamente el cursor anterior si estaba abierto, aunque es buena práctica cerrarlo explícitamente antes.

```sql
DECLARE
  TYPE ref_cur IS REF CURSOR;
  c2      ref_cur;
  vnombre productose.nombre%TYPE;
BEGIN
  -- Primera apertura: todos los productos
  OPEN c2 FOR SELECT nombre FROM productose;
  FETCH c2 INTO vnombre;
  WHILE c2%FOUND LOOP
    DBMS_OUTPUT.PUT_LINE('Producto: ' || vnombre);
    FETCH c2 INTO vnombre;
  END LOOP;
  CLOSE c2;

  -- Segunda apertura: solo productos 20 y 40
  OPEN c2 FOR SELECT nombre FROM productose WHERE productonu IN (20, 40);
  FETCH c2 INTO vnombre;
  WHILE c2%FOUND LOOP
    DBMS_OUTPUT.PUT_LINE('Producto (filtrado): ' || vnombre);
    FETCH c2 INTO vnombre;
  END LOOP;
  CLOSE c2;
END;
```

> **Ventaja del REF CURSOR sobre cursor estático:** la consulta se decide en tiempo de ejecución, no en tiempo de compilación. Esto permite construir consultas dinámicas o reutilizar el mismo cursor con distintos filtros.

[↑ Volver al índice](#indice)

---

### Ejercicio 6.5 — REF CURSOR sobre departamentos (bucle LOOP)

**Razonamiento:** Igual que el anterior pero usando LOOP con `EXIT WHEN c3%NOTFOUND`. En este patrón, el FETCH va dentro del LOOP y el EXIT se pone inmediatamente después del FETCH (antes de procesar el dato), para evitar procesar la última lectura fallida.

```sql
DECLARE
  TYPE ref_cur IS REF CURSOR;
  c3      ref_cur;
  vnombre departamentose.nombre%TYPE;
BEGIN
  -- Primera apertura: todos los departamentos
  OPEN c3 FOR SELECT nombre FROM departamentose;
  LOOP
    FETCH c3 INTO vnombre;
    EXIT WHEN c3%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE('Departamento: ' || vnombre);
  END LOOP;
  CLOSE c3;

  -- Segunda apertura: departamentos 10, 20 y 40
  OPEN c3 FOR SELECT nombre FROM departamentose WHERE depnu IN (10, 20, 40);
  LOOP
    FETCH c3 INTO vnombre;
    EXIT WHEN c3%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE('Departamento (filtrado): ' || vnombre);
  END LOOP;
  CLOSE c3;
END;
```

> **Diferencia entre LOOP+EXIT WHEN y WHILE con %FOUND:**
> - LOOP+EXIT: el FETCH está dentro, EXIT inmediatamente después. No hace falta "cebar" el bucle con un FETCH previo.
> - WHILE+%FOUND: necesita un FETCH antes del WHILE y otro al final de cada iteración.
> Ambos son correctos; LOOP+EXIT es más compacto para cursores.

[↑ Volver al índice](#indice)

---

<a id="bloque-7"></a>
## Bloque 7 — Excepciones

[↑ Volver al índice](#indice)

### Teoría

Las excepciones en PL/SQL son eventos de error que interrumpen el flujo normal del programa. Sin el bloque `EXCEPTION`, cualquier error aborta la ejecución. Con él, puedes interceptar el error y reaccionar.

**Estructura:**
```sql
BEGIN
  -- código que puede fallar
EXCEPTION
  WHEN excepcion1 THEN ...
  WHEN excepcion2 THEN ...
  WHEN OTHERS THEN ...  -- captura todo lo que no capturaron los anteriores
END;
```

**Excepciones predefinidas más importantes:**

| Nombre | Código Oracle | Cuándo ocurre |
|--------|--------------|---------------|
| `NO_DATA_FOUND` | ORA-01403 | SELECT INTO no devuelve filas |
| `TOO_MANY_ROWS` | ORA-01422 | SELECT INTO devuelve más de una fila |
| `ZERO_DIVIDE` | ORA-01476 | División entre cero |
| `VALUE_ERROR` | ORA-06502 | Error de conversión o truncamiento |
| `DUP_VAL_ON_INDEX` | ORA-00001 | Violación de restricción UNIQUE |

**Variables de diagnóstico (disponibles en el bloque EXCEPTION):**
- `SQLCODE`: código numérico del error (negativo para errores Oracle, 1 para excepciones de usuario).
- `SQLERRM`: mensaje de texto del error.

**Excepción de usuario:**
```sql
DECLARE
  mi_error EXCEPTION;
BEGIN
  IF condicion THEN RAISE mi_error; END IF;
EXCEPTION
  WHEN mi_error THEN ...
END;
```

**PRAGMA EXCEPTION_INIT:**
Liga una excepción de usuario a un código de error Oracle del rango `-20000` a `-20999` (reservado para errores de usuario):
```sql
mi_error EXCEPTION;
PRAGMA EXCEPTION_INIT(mi_error, -20015);
```
Esto permite que `SQLERRM` y `SQLCODE` devuelvan valores significativos.

---

### Ejercicio 7.1 — Consulta básica con excepción de usuario

**Parte 1.1:**

```sql
DECLARE
  ncli pedidose.clientenu%TYPE;
BEGIN
  SELECT clientenu INTO ncli
  FROM pedidose
  WHERE pedidonu = 1002;
  DBMS_OUTPUT.PUT_LINE('Número de cliente: ' || ncli);
END;
```

**Parte 1.2 — Con excepción de usuario:**

**Razonamiento:** Declaro la excepción en DECLARE. En el BEGIN, después de cargar el valor, compruebo si cumple la condición; si no cumple, lanzo la excepción con `RAISE`. El bloque EXCEPTION la captura y muestra el mensaje.

```sql
DECLARE
  ncli         pedidose.clientenu%TYPE;
  errorcliente EXCEPTION;
BEGIN
  SELECT clientenu INTO ncli
  FROM pedidose
  WHERE pedidonu = 1002;

  IF ncli <> 103 THEN
    RAISE errorcliente;
  END IF;

  DBMS_OUTPUT.PUT_LINE('Número de cliente: ' || ncli);
EXCEPTION
  WHEN errorcliente THEN
    DBMS_OUTPUT.PUT_LINE('el cliente no es 101');
END;
```

> **Flujo de ejecución:** si `ncli` no es 103, el `RAISE` interrumpe el flujo del BEGIN y lo transfiere directamente al bloque EXCEPTION. El `DBMS_OUTPUT.PUT_LINE` del BEGIN no se ejecuta.

[↑ Volver al índice](#indice)

---

### Ejercicio 7.2 — NO_DATA_FOUND sin OTHERS

**Razonamiento:** El departamento 50 no existe → SELECT INTO devuelve 0 filas → Oracle lanza automáticamente `NO_DATA_FOUND`. La capturo con su nombre exacto, sin usar OTHERS.

```sql
DECLARE
  vlocalidad departamentose.localidad%TYPE;
BEGIN
  SELECT localidad INTO vlocalidad
  FROM departamentose
  WHERE depnu = 50;
  DBMS_OUTPUT.PUT_LINE('Localidad: ' || vlocalidad);
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    DBMS_OUTPUT.PUT_LINE('No existe el departamento 50.');
END;
```

> **¿Por qué capturar por nombre y no con OTHERS?** Ser específico en la captura es mejor práctica: si hay otro error diferente (un problema de red, un error de tipo...), no quedará silenciado por el OTHERS. Solo capturo exactamente lo que espero que pueda fallar.

[↑ Volver al índice](#indice)

---

### Ejercicio 7.3 — Excepción capturada con OTHERS

**Razonamiento:** `OTHERS` captura cualquier excepción no capturada antes. Dentro, uso `SQLERRM` y `SQLCODE` para mostrar qué error ocurrió exactamente, lo que es útil para depurar.

```sql
DECLARE
  vlocalidad departamentose.localidad%TYPE;
BEGIN
  SELECT localidad INTO vlocalidad
  FROM departamentose
  WHERE depnu = 50;
  DBMS_OUTPUT.PUT_LINE('Localidad: ' || vlocalidad);
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('Error código: ' || SQLCODE);
    DBMS_OUTPUT.PUT_LINE('Error mensaje: ' || SQLERRM);
END;
```

> **Resultado:**
> ```
> Error código: 100
> Error mensaje: ORA-01403: no data found
> ```
> (En Oracle, `NO_DATA_FOUND` tiene `SQLCODE = 100`, no un número negativo.)

[↑ Volver al índice](#indice)

---

### Ejercicio 7.4 — TOO_MANY_ROWS

**Razonamiento:** Hay múltiples pedidos para el producto 20. `SELECT INTO` solo puede manejar una fila → Oracle lanza `TOO_MANY_ROWS`. La capturo para mostrar un mensaje controlado en lugar de un error abrupto.

```sql
DECLARE
  vpedidonu pedidose.pedidonu%TYPE;
BEGIN
  SELECT pedidonu INTO vpedidonu
  FROM pedidose
  WHERE productonu = 20;
  DBMS_OUTPUT.PUT_LINE('Pedido: ' || vpedidonu);
EXCEPTION
  WHEN TOO_MANY_ROWS THEN
    DBMS_OUTPUT.PUT_LINE('La consulta devuelve más de una fila. Usa un cursor.');
END;
```

> **La solución real a TOO_MANY_ROWS es usar un cursor.** Esta excepción es una señal de que el diseño del bloque necesita revisarse: si esperas múltiples filas, necesitas un cursor (Bloque 6), no un SELECT INTO.

[↑ Volver al índice](#indice)

---

<a id="bloque-8"></a>
## Bloque 8 — Procedimientos

[↑ Volver al índice](#indice)

### Teoría

Un **procedimiento** es un bloque PL/SQL con nombre que puede recibir parámetros y ejecutar lógica. No devuelve un valor directamente (eso lo hacen las funciones), pero puede devolver valores a través de parámetros `OUT`.

**Modos de parámetro:**

| Modo | Dirección | Puede leerse dentro | Puede escribirse dentro | Se pasa como |
|------|-----------|---------------------|------------------------|--------------|
| `IN` | Entrada | Sí | No | Valor o expresión |
| `OUT` | Salida | No* | Sí | Variable obligatoria |
| `IN OUT` | Ambos | Sí | Sí | Variable obligatoria |

*En Oracle 11g+, `OUT` con `NOCOPY` sí permite lectura, pero no es el caso general.

**Procedimiento anónimo vs almacenado:**
- **Anónimo** (en DECLARE): solo existe en ese bloque. No es persistente ni reutilizable desde otros programas.
- **Almacenado** (CREATE OR REPLACE): se guarda en el esquema de la base de datos. Cualquier usuario con privilegios puede llamarlo.

```sql
-- Anónimo (local al bloque)
DECLARE
  PROCEDURE mi_proc(x IN NUMBER) IS
  BEGIN ... END mi_proc;
BEGIN
  mi_proc(5);  -- solo se puede llamar aquí
END;

-- Almacenado (persistente en BD)
CREATE OR REPLACE PROCEDURE mi_proc(x IN NUMBER) IS
BEGIN ... END mi_proc;

BEGIN
  mi_proc(5);  -- llamable desde cualquier sesión
END;
```

---

### Referencia — Procedimientos anónimos y almacenados

```sql
-- PROCEDIMIENTO ANÓNIMO (dentro de DECLARE)
DECLARE
  local VARCHAR2(30);

  PROCEDURE plocalidad1(numdep NUMBER)
  IS loc VARCHAR2(30);
  BEGIN
    SELECT localidad INTO loc FROM departamentose WHERE depnu = numdep;
    DBMS_OUTPUT.PUT_LINE(loc);
  END plocalidad1;

  PROCEDURE plocalidad2(numdep NUMBER, loc OUT VARCHAR2) IS
  BEGIN
    SELECT localidad INTO loc FROM departamentose WHERE depnu = numdep;
  END plocalidad2;

BEGIN
  plocalidad1(20);          -- imprime dentro del proc
  plocalidad2(10, local);   -- devuelve en la variable OUT
  DBMS_OUTPUT.PUT_LINE(local);
END;

-- PROCEDIMIENTO ALMACENADO (persistente)
CREATE OR REPLACE PROCEDURE plocalidad3(numdep NUMBER)
IS loc VARCHAR2(30);
BEGIN
  SELECT localidad INTO loc FROM departamentose WHERE depnu = numdep;
  DBMS_OUTPUT.PUT_LINE(loc);
END plocalidad3;

BEGIN
  plocalidad3(40);
END;
```

---

### Ejercicio 8.1 — Procedimiento anónimo con IN y OUT

**Razonamiento:** Necesito devolver un valor desde el procedimiento. `IN` para los parámetros de entrada (producto y pedido) y `OUT` para el valor devuelto (cantidad). En el BEGIN del DECLARE llamo al procedimiento y paso la variable donde quiero recibir el resultado.

```sql
DECLARE
  resultado NUMBER;

  PROCEDURE p1(num_producto IN NUMBER, num_pedido IN NUMBER, cantidad OUT NUMBER)
  IS
  BEGIN
    SELECT unidades INTO cantidad
    FROM pedidose
    WHERE productonu = num_producto AND pedidonu = num_pedido;
  END p1;

BEGIN
  p1(20, 1001, resultado);
  DBMS_OUTPUT.PUT_LINE('Cantidad pedida: ' || resultado);
END;
```

> **Importante:** los parámetros OUT deben pasarse como **variables**, nunca como literales. `p1(20, 1001, 0)` daría error porque `0` no es una variable a la que el procedimiento pueda asignar.

[↑ Volver al índice](#indice)

---

### Ejercicio 8.2 — Procedimiento almacenado (CREATE OR REPLACE)

**Razonamiento:** El procedimiento almacenado se crea primero (ejecuto el CREATE OR REPLACE) y luego se llama desde un bloque anónimo aparte. Uso un nombre de parámetro diferente al de la columna (`p_unidades` vs `unidades`) para evitar ambigüedad en el SELECT.

```sql
-- Paso 1: Crear el procedimiento (se ejecuta una vez)
CREATE OR REPLACE PROCEDURE p2(num_pedido IN NUMBER, p_unidades OUT NUMBER)
IS
BEGIN
  SELECT unidades INTO p_unidades
  FROM pedidose
  WHERE pedidonu = num_pedido;
END p2;

-- Paso 2: Llamarlo desde un bloque anónimo
DECLARE
  resultado NUMBER;
BEGIN
  p2(&num_pedido, resultado);
  DBMS_OUTPUT.PUT_LINE('Unidades para ese pedido: ' || resultado);
END;
```

> **Por qué `p_unidades` y no `unidades`:** si el parámetro se llamara igual que la columna, Oracle podría confundirlos en el WHERE. El prefijo `p_` (de "parámetro") es una convención habitual para evitar este problema.

[↑ Volver al índice](#indice)

---

<a id="bloque-9"></a>
## Bloque 9 — Funciones

[↑ Volver al índice](#indice)

### Teoría

Una **función** es como un procedimiento, pero **siempre devuelve un valor** con la sentencia `RETURN`. Este valor de retorno hace que las funciones puedan usarse en expresiones (asignaciones, condiciones, e incluso en consultas SQL si son almacenadas).

```sql
FUNCTION nombre(parametros) RETURN tipo_retorno IS
  -- variables locales
BEGIN
  -- lógica
  RETURN valor;
END nombre;
```

**Función vs Procedimiento:**

| | Procedimiento | Función |
|--|---------------|---------|
| Devuelve valor | No (solo por OUT) | Sí, con RETURN |
| Uso en SQL | No | Sí (si almacenada y cumple restricciones) |
| Puede tener OUT | Sí | Técnicamente sí, pero no se recomienda |
| Se llama con | `proc(args)` | `var := func(args)` |

**Regla:** si necesitas devolver un único valor escalar, usa función. Si necesitas devolver varios valores o solo ejecutar lógica, usa procedimiento.

---

### Referencia — Función local en DECLARE

```sql
DECLARE
  local VARCHAR2(30);

  FUNCTION flocalidad(numdep NUMBER) RETURN VARCHAR2
  IS loc VARCHAR2(30);
  BEGIN
    SELECT localidad INTO loc FROM departamentose WHERE depnu = numdep;
    RETURN loc;
  END flocalidad;

BEGIN
  local := flocalidad(30);
  DBMS_OUTPUT.PUT_LINE(local);
END;
```

---

### Ejercicio 9.1 — Función local que cuenta departamentos

**Razonamiento:** La función no tiene parámetros (simplemente cuenta todos los departamentos). Devuelve `NUMBER`. La llamo en el BEGIN asignando su resultado a una variable, luego imprimo esa variable.

```sql
DECLARE
  resultado NUMBER;

  FUNCTION f1 RETURN NUMBER
  IS total NUMBER;
  BEGIN
    SELECT COUNT(*) INTO total FROM departamentose;
    RETURN total;
  END f1;

BEGIN
  resultado := f1;
  DBMS_OUTPUT.PUT_LINE('Número de departamentos: ' || resultado);
END;
```

> **También se puede llamar directamente en el PUT_LINE:**
> ```sql
> DBMS_OUTPUT.PUT_LINE('Departamentos: ' || f1);
> ```
> Ambas formas son válidas. La primera es más legible cuando el resultado se usa varias veces.

[↑ Volver al índice](#indice)

---

### Ejercicio 9.2 — Función almacenada con cursor

**Razonamiento:** La función almacenada `F2` usa un cursor para recorrer todos los pedidos de un producto y contarlos. Un contador es el mecanismo más sencillo. El valor del contador se devuelve con RETURN. Después ejecuto P2 y F2 en el mismo bloque para mostrar que ambos funcionan.

```sql
-- Crear la función almacenada
CREATE OR REPLACE FUNCTION f2(num_producto NUMBER) RETURN NUMBER
IS
  CURSOR c IS SELECT pedidonu FROM pedidose WHERE productonu = num_producto;
  contador NUMBER := 0;
BEGIN
  FOR reg IN c LOOP
    contador := contador + 1;
  END LOOP;
  RETURN contador;
END f2;

-- Ejecutar P2 y F2 en el mismo bloque
DECLARE
  p_unidades  NUMBER;
  num_pedidos NUMBER;
BEGIN
  p2(&num_pedido, p_unidades);
  DBMS_OUTPUT.PUT_LINE('Unidades para ese pedido: ' || p_unidades);

  num_pedidos := f2(&num_producto);
  DBMS_OUTPUT.PUT_LINE('Pedidos del producto: ' || num_pedidos);
END;
```

> **¿Por qué usar un cursor en lugar de `SELECT COUNT(*) INTO`?** El enunciado lo pide explícitamente. Sin embargo, `SELECT COUNT(*) INTO contador FROM pedidose WHERE productonu = num_producto` sería más eficiente. El cursor recorre fila a fila; COUNT lo hace internamente en SQL. El cursor es educativamente correcto para practicar su uso dentro de funciones.

[↑ Volver al índice](#indice)

---

<a id="bloque-10"></a>
## Bloque 10 — Paquetes (Integrador Final)

[↑ Volver al índice](#indice)

### Teoría

Un **paquete** agrupa procedimientos, funciones, tipos y variables bajo un espacio de nombres común. Es el equivalente a una clase (sin instancias) o a un módulo/librería en otros lenguajes.

**Estructura en dos partes:**

1. **Especificación** (cabecera pública): declara qué ofrece el paquete al mundo exterior. Solo contiene firmas (nombres y parámetros), no implementaciones.
2. **Cuerpo** (implementación): contiene el código de todo lo declarado en la especificación.

```sql
-- Especificación
CREATE OR REPLACE PACKAGE mi_paquete AS
  PROCEDURE proc1(x NUMBER);
  FUNCTION func1 RETURN NUMBER;
END mi_paquete;

-- Cuerpo
CREATE OR REPLACE PACKAGE BODY mi_paquete AS
  PROCEDURE proc1(x NUMBER) IS
  BEGIN ... END proc1;

  FUNCTION func1 RETURN NUMBER IS
  BEGIN ... END func1;
END mi_paquete;
```

**Ventajas de los paquetes:**
- **Encapsulamiento:** puedes tener elementos privados en el cuerpo que no aparecen en la especificación.
- **Rendimiento:** Oracle carga el paquete completo en memoria la primera vez que se usa; las llamadas posteriores son más rápidas.
- **Organización:** agrupa lógica relacionada (ej. `paquete_ventas`, `paquete_rrhh`).
- **Variables de estado:** variables declaradas en la especificación persisten durante la sesión.

**Llamada:** `nombre_paquete.nombre_elemento(argumentos)`

---

### Referencia — Paquete básico

```sql
CREATE OR REPLACE PACKAGE paq0 AS
  PROCEDURE p1(numero NUMBER);
END paq0;

CREATE OR REPLACE PACKAGE BODY paq0 AS
  PROCEDURE p1(numero NUMBER)
  IS local VARCHAR2(30);
  BEGIN
    SELECT localidad INTO local FROM departamentose WHERE depnu = numero;
    DBMS_OUTPUT.PUT_LINE(local);
  END p1;
END paq0;

BEGIN
  paq0.p1(20);
END;
```

---

### Ejercicio Final — PAQUETE10

**Razonamiento:** El paquete es simplemente un contenedor. Pongo dentro la función `F1` (del ejercicio 9.1) y el procedimiento `P2` (del ejercicio 8.2) sin modificar su lógica interna. En la especificación solo pongo las firmas; en el cuerpo, el código completo. Luego los llamo con la notación `paquete10.f1` y `paquete10.p2`.

```sql
-- Paso 1: Especificación del paquete
CREATE OR REPLACE PACKAGE paquete10 AS
  FUNCTION  f1 RETURN NUMBER;
  PROCEDURE p2(num_pedido IN NUMBER, p_unidades OUT NUMBER);
END paquete10;

-- Paso 2: Cuerpo del paquete
CREATE OR REPLACE PACKAGE BODY paquete10 AS

  FUNCTION f1 RETURN NUMBER
  IS total NUMBER;
  BEGIN
    SELECT COUNT(*) INTO total FROM departamentose;
    RETURN total;
  END f1;

  PROCEDURE p2(num_pedido IN NUMBER, p_unidades OUT NUMBER)
  IS
  BEGIN
    SELECT unidades INTO p_unidades
    FROM pedidose
    WHERE pedidonu = num_pedido;
  END p2;

END paquete10;

-- Paso 3: Ejecutar desde un bloque aparte
DECLARE
  num_dep  NUMBER;
  unidades NUMBER;
BEGIN
  num_dep := paquete10.f1;
  DBMS_OUTPUT.PUT_LINE('Número de departamentos: ' || num_dep);

  paquete10.p2(&num_pedido, unidades);
  DBMS_OUTPUT.PUT_LINE('Unidades para ese pedido: ' || unidades);
END;
```

> **Orden de compilación:** primero se compila la especificación, luego el cuerpo. Si el cuerpo no coincide exactamente con las firmas de la especificación, Oracle da error de compilación. El nombre, los tipos y los modos de los parámetros deben ser idénticos en ambas partes.

[↑ Volver al índice](#indice)

---

<a id="notas"></a>
## Notas de Interés — Errores Típicos y Estrategias

[↑ Volver al índice](#indice)

### 1. Olvidar el segundo FETCH en WHILE con cursor

```sql
-- MAL: bucle infinito
OPEN c1;
FETCH c1 INTO vdep;
WHILE c1%FOUND LOOP
  DBMS_OUTPUT.PUT_LINE(vdep);
  -- falta FETCH c1 INTO vdep; aquí
END LOOP;

-- BIEN
OPEN c1;
FETCH c1 INTO vdep;
WHILE c1%FOUND LOOP
  DBMS_OUTPUT.PUT_LINE(vdep);
  FETCH c1 INTO vdep;  -- avanza al siguiente
END LOOP;
CLOSE c1;
```

---

### 2. SELECT INTO sin exactamente una fila

```sql
-- ORA-01403: no data found  → la condición WHERE no coincide con ninguna fila
-- ORA-01422: exact fetch returns more than requested number of rows → WHERE muy permisivo
```
**Estrategia:** si no estás seguro de cuántas filas devuelve una consulta, usa siempre un cursor. Reserva `SELECT INTO` para funciones de agregado (`COUNT`, `SUM`, `MAX`) o consultas por clave primaria.

---

### 3. BOOLEAN con variables de sustitución &

```sql
-- ESTO NO FUNCIONA:
mi_bool BOOLEAN := &valor;  -- Error: no hay conversión de texto a BOOLEAN

-- ESTO SÍ FUNCIONA:
combo NUMBER := &combo;  -- 1=TRUE, 0=FALSE
IF combo = 1 THEN ...
```

---

### 4. Confundir := (asignación) con = (comparación)

```sql
-- = en PL/SQL es SOLO comparación:
IF x = 5 THEN ...       -- correcto
IF x = 5 AND y = 3 THEN -- correcto

-- := es asignación:
x := 5;                  -- correcto
IF x := 5 THEN ...       -- ERROR de compilación
```

---

### 5. Usar VARRAY sin inicializar

```sql
-- ESTO DA ERROR en tiempo de ejecución:
DECLARE
  TYPE tvarray IS VARRAY(5) OF NUMBER;
  v tvarray;  -- NULL por defecto
BEGIN
  v(1) := 10;  -- ORA-06531: reference to uninitialized collection

-- BIEN: inicializa con el constructor
  v := tvarray(0, 0, 0, 0, 0);
  v(1) := 10;  -- ahora funciona
END;
```

---

### 6. Parámetro OUT llamado con literal en lugar de variable

```sql
-- ESTO DA ERROR:
p2(1001, 0);  -- 0 es un literal, no una variable. Oracle no puede escribir en él.

-- BIEN:
DECLARE resultado NUMBER;
BEGIN
  p2(1001, resultado);  -- resultado es una variable en la que p2 puede escribir
END;
```

---

### 7. Cuerpo de paquete sin compilar la especificación antes

Oracle compila primero la especificación y luego el cuerpo. Si modificas una firma en la especificación sin actualizar el cuerpo, el cuerpo quedará "inválido" aunque antes funcionara. Siempre ejecuta ambos en orden: especificación primero, cuerpo después.

---

### 8. ELSIF vs ELSEIF

```sql
-- PL/SQL usa ELSIF (sin E al final del ELSE):
IF x = 1 THEN ...
ELSIF x = 2 THEN ...  -- CORRECTO
ELSEIF x = 2 THEN ... -- ERROR de compilación
```

---

### 9. Estrategia general para resolver ejercicios PL/SQL

1. **Lee el enunciado y clasifica qué necesitas:** ¿variables simples? ¿consulta a BD? ¿múltiples filas? ¿reutilización?
2. **Elige la herramienta correcta:**
   - Una fila esperada → `SELECT INTO`
   - Múltiples filas → cursor
   - Lógica reutilizable que devuelve valor → función
   - Lógica reutilizable que ejecuta acciones → procedimiento
   - Agrupación de lo anterior → paquete
3. **Dibuja el esqueleto vacío** (`DECLARE / BEGIN / END`) y rellena por partes.
4. **Empieza por los tipos y variables** en DECLARE. Asegúrate de que los tipos son compatibles con los de la tabla (usa `%TYPE` cuando puedas).
5. **Escribe la lógica en BEGIN** de lo más simple a lo más complejo.
6. **Añade el bloque EXCEPTION** solo si el enunciado lo pide o si usas `SELECT INTO` con riesgo de 0 o múltiples filas.

---

### 10. ¿Por qué el código no muestra salida en SQL Developer?

```sql
-- Antes de ejecutar cualquier bloque PL/SQL, activa la salida:
SET SERVEROUTPUT ON;

-- O desde SQL Developer: menú View → DBMS Output → + (añadir conexión)
```
Sin `SET SERVEROUTPUT ON`, `DBMS_OUTPUT.PUT_LINE` produce texto pero nadie lo recibe. El programa "funciona" pero no ves nada.

[↑ Volver al índice](#indice)

---

*Fin del documento — Guía de Estudio con Soluciones PL/SQL*
