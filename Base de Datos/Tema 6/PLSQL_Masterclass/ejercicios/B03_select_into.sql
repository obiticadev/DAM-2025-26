-- ============================================================
-- 📝 BLOQUE 3 — Interacción con la BD: SELECT INTO, %TYPE, %ROWTYPE
-- ============================================================
-- Lee la teoría en: teoria/T03_select_into.md
-- Valida tus soluciones con: tests/T03_select_into.sql
-- ============================================================

SET SERVEROUTPUT ON;

-- ────────────────────────────────────────────────────────────
-- Ejercicio 3.1 — Suma aleatoria con DBMS_RANDOM
-- ────────────────────────────────────────────────────────────
-- Genera un número aleatorio n (1-10) que será el número de
-- iteraciones. En cada iteración genera un aleatorio (10-100)
-- y acumúlalo. Imprime cada número y la suma final.
-- ────────────────────────────────────────────────────────────
DECLARE
  n         NUMBER;
  aleatorio NUMBER;
  suma      NUMBER := 0;
BEGIN
  -- TODO: Genera n usando TRUNC(DBMS_RANDOM.VALUE(1, 11))
  -- TODO: Imprime "Iteraciones: " || n
  -- TODO: FOR de 1 a n, genera aleatorio con TRUNC(DBMS_RANDOM.VALUE(10, 101))
  -- TODO: En cada iteración imprime "Número i: X" y acumula en suma
  -- TODO: Imprime "Suma total: " || suma
  NULL;
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 3.2 — Múltiplos de 3 entre 1 y 100
-- ────────────────────────────────────────────────────────────
-- Recorre del 1 al 100 y cuenta cuántos son múltiplos de 3.
-- Usa MOD(i, 3) = 0 para comprobarlo.
-- Resultado esperado: "Múltiplos de 3 entre 1 y 100: 33"
-- ────────────────────────────────────────────────────────────
DECLARE
  contador NUMBER := 0;
BEGIN
  -- TODO: FOR de 1 a 100, si MOD(i, 3) = 0 incrementa contador
  NULL;
  DBMS_OUTPUT.PUT_LINE('Múltiplos de 3 entre 1 y 100: ' || contador);
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 3.3 — Día de la semana actual
-- ────────────────────────────────────────────────────────────
-- Obtén el nombre y número del día de la semana usando SYSDATE,
-- TO_CHAR y TRIM. Imprime ambos.
-- ────────────────────────────────────────────────────────────
DECLARE
  nombre_dia VARCHAR2(20);
  num_dia    NUMBER;
BEGIN
  -- TODO: Asigna nombre_dia con TO_CHAR(SYSDATE, 'DAY', 'NLS_DATE_LANGUAGE=SPANISH')
  -- TODO: Asigna num_dia con TO_NUMBER(TO_CHAR(SYSDATE, 'D'))
  -- TODO: Imprime "Día: NOMBRE, número: N" (usa TRIM en nombre_dia)
  NULL;
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 3.4 — COUNT de departamentos con SELECT INTO
-- ────────────────────────────────────────────────────────────
-- Usa SELECT COUNT(*) INTO para contar departamentos.
-- Resultado esperado: "Número de departamentos: 4"
-- ────────────────────────────────────────────────────────────
DECLARE
  total NUMBER;
BEGIN
  -- TODO: SELECT COUNT(*) INTO total FROM sedes
  -- TODO: Imprime el resultado
  total := 0; -- Quita esta línea cuando implementes
  DBMS_OUTPUT.PUT_LINE('Número de departamentos: ' || total);
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 3.5 — Suma de unidades por producto (fijo y teclado)
-- ────────────────────────────────────────────────────────────

-- Parte 3.5.1: Suma unidades del producto 20
DECLARE
  total NUMBER;
BEGIN
  -- TODO: SELECT SUM(unidades) INTO total FROM ventas WHERE productonu = 20
  total := 0;
  DBMS_OUTPUT.PUT_LINE('Total unidades producto 20: ' || total);
END;
/

-- Parte 3.5.2: Suma unidades de un producto leído por teclado
DECLARE
  total  NUMBER;
  numpro NUMBER := &numpro;
BEGIN
  -- TODO: SELECT SUM(unidades) INTO total FROM ventas WHERE productonu = numpro
  total := 0;
  DBMS_OUTPUT.PUT_LINE('Total unidades producto ' || numpro || ': ' || total);
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 3.6 — SELECT INTO con dos variables y %TYPE
-- ────────────────────────────────────────────────────────────
-- Obtén nombre y localidad del departamento 20 usando %TYPE.
-- Imprime: "Nombre: X, Localidad: Y"
-- ────────────────────────────────────────────────────────────
DECLARE
  vnombre    sedes.nombre%TYPE;
  vlocalidad sedes.localidad%TYPE;
BEGIN
  -- TODO: SELECT nombre, localidad INTO vnombre, vlocalidad
  --       FROM sedes WHERE depnu = 20
  -- TODO: Imprime el resultado
  NULL;
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 3.7 — Uso de %TYPE para suma de unidades
-- ────────────────────────────────────────────────────────────
-- Declara total_unidades con ventas.unidades%TYPE.
-- Suma TODAS las unidades de ventas.
-- ────────────────────────────────────────────────────────────
DECLARE
  total_unidades ventas.unidades%TYPE;
BEGIN
  -- TODO: SELECT SUM(unidades) INTO total_unidades FROM ventas
  -- TODO: Imprime el resultado
  NULL;
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 3.8 — Uso de %ROWTYPE
-- ────────────────────────────────────────────────────────────
-- Declara fila_dep con sedes%ROWTYPE.
-- Carga la fila completa del departamento 40 con SELECT * INTO.
-- Imprime nombre y localidad usando fila_dep.nombre, fila_dep.localidad.
-- ────────────────────────────────────────────────────────────
DECLARE
  fila_dep sedes%ROWTYPE;
BEGIN
  -- TODO: SELECT * INTO fila_dep FROM sedes WHERE depnu = 40
  -- TODO: Imprime fila_dep.nombre y fila_dep.localidad
  NULL;
END;
/
