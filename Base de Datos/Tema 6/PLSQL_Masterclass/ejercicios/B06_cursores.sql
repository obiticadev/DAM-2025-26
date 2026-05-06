-- ============================================================
-- 📝 BLOQUE 6 — Cursores
-- ============================================================
-- Lee la teoría en: teoria/T06_cursores.md
-- Valida tus soluciones con: tests/T06_cursores.sql
-- ============================================================

SET SERVEROUTPUT ON;

-- ────────────────────────────────────────────────────────────
-- Ejercicio 6.1 — Cursor explícito con FORMA 1 (WHILE)
-- ────────────────────────────────────────────────────────────
-- Declara un cursor que seleccione depnu de sedes.
-- Usa OPEN, FETCH, WHILE %FOUND, FETCH, CLOSE para recorrerlo.
-- Imprime cada número de departamento.
-- ────────────────────────────────────────────────────────────
DECLARE
  CURSOR c1 IS SELECT depnu FROM sedes;
  vdepnu sedes.depnu%TYPE;
BEGIN
  -- TODO: OPEN c1
  -- TODO: Primer FETCH c1 INTO vdepnu (para "cebar" el WHILE)
  -- TODO: WHILE c1%FOUND LOOP
  --         Imprime 'Departamento: ' || vdepnu
  --         FETCH c1 INTO vdepnu  (¡no olvides el segundo FETCH!)
  --       END LOOP
  -- TODO: CLOSE c1
  NULL;
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 6.2 — Cursor FOR combinado con VARRAY (FORMA 2)
-- ────────────────────────────────────────────────────────────
-- Usa FOR reg IN c1 LOOP para recorrer departamentos.
-- Almacena cada depnu en un VARRAY(20) usando EXTEND.
-- Al final, recorre el VARRAY e imprime su contenido.
-- ────────────────────────────────────────────────────────────
DECLARE
  CURSOR c1 IS SELECT depnu FROM sedes;
  TYPE tvarray IS VARRAY(20) OF NUMBER;
  varray tvarray := tvarray();
  idx    NUMBER  := 0;
BEGIN
  -- TODO: FOR reg IN c1 LOOP
  --         Incrementa idx, haz EXTEND, guarda reg.depnu en varray(idx)
  --       END LOOP
  -- TODO: Imprime '--- Contenido del VARRAY ---'
  -- TODO: FOR j IN 1..varray.COUNT LOOP, imprime cada elemento
  NULL;
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 6.3 — Cursor con parámetros
-- ────────────────────────────────────────────────────────────
-- Declara un cursor con parámetro (param NUMBER) que busque
-- el nombre de un producto por productonu.
-- Ábrelo con &param por teclado.
-- Recorre con WHILE %FOUND.
-- ────────────────────────────────────────────────────────────
DECLARE
  CURSOR c1(param NUMBER) IS
    SELECT nombre FROM items WHERE productonu = param;
  vnombre items.nombre%TYPE;
BEGIN
  -- TODO: OPEN c1(&param)
  -- TODO: FETCH, WHILE %FOUND, imprimir, FETCH, CLOSE
  NULL;
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 6.4 — REF CURSOR sobre productos (WHILE)
-- ────────────────────────────────────────────────────────────
-- Declara TYPE ref_cur IS REF CURSOR y una variable c2.
-- Primera apertura: todos los productos.
-- Segunda apertura: solo productos 20 y 40.
-- Recorre ambos con WHILE %FOUND.
-- ────────────────────────────────────────────────────────────
DECLARE
  TYPE ref_cur IS REF CURSOR;
  c2      ref_cur;
  vnombre items.nombre%TYPE;
BEGIN
  -- TODO: OPEN c2 FOR SELECT nombre FROM items
  -- TODO: FETCH, WHILE, imprimir 'Producto: ' || vnombre, FETCH, CLOSE

  -- TODO: OPEN c2 FOR SELECT nombre FROM items WHERE productonu IN (20, 40)
  -- TODO: FETCH, WHILE, imprimir 'Producto (filtrado): ' || vnombre, FETCH, CLOSE
  NULL;
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 6.5 — REF CURSOR sobre departamentos (LOOP)
-- ────────────────────────────────────────────────────────────
-- Igual que 6.4 pero con LOOP + EXIT WHEN %NOTFOUND.
-- Primera apertura: todos los departamentos.
-- Segunda apertura: departamentos 10, 20 y 40.
-- ────────────────────────────────────────────────────────────
DECLARE
  TYPE ref_cur IS REF CURSOR;
  c3      ref_cur;
  vnombre sedes.nombre%TYPE;
BEGIN
  -- TODO: OPEN c3 FOR SELECT nombre FROM sedes
  -- TODO: LOOP, FETCH, EXIT WHEN %NOTFOUND, imprimir, END LOOP, CLOSE

  -- TODO: OPEN c3 FOR SELECT nombre FROM sedes WHERE depnu IN (10,20,40)
  -- TODO: LOOP, FETCH, EXIT WHEN %NOTFOUND, imprimir filtrado, END LOOP, CLOSE
  NULL;
END;
/
