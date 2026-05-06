-- ============================================================
-- ✅ TEST BLOQUE 4 — Registros (RECORD)
-- ============================================================
-- Tests automáticos con RAISE_APPLICATION_ERROR + ROLLBACK.
-- ============================================================

SET SERVEROUTPUT ON;

-- Test 4.1 — INSERT con registro (producto 100)
DECLARE
  v_count NUMBER;
BEGIN
  -- Primero limpia por si se ejecutó antes
  DELETE FROM ventas WHERE productonu = 100;
  DELETE FROM items WHERE productonu = 100;

  -- Simula lo que el alumno debió hacer
  INSERT INTO items VALUES (100, 'tablon', 234.56, 34);

  SELECT COUNT(*) INTO v_count FROM items WHERE productonu = 100;
  IF v_count <> 1 THEN
    RAISE_APPLICATION_ERROR(-20200, 'TEST 4.1 FALLIDO: producto 100 no insertado');
  END IF;
  DBMS_OUTPUT.PUT_LINE('✅ TEST 4.1 PASADO — Producto 100 insertado correctamente');
  ROLLBACK; -- Estado limpio
END;
/

-- Test 4.2.1 — SELECT INTO sobre producto 30
DECLARE
  TYPE tipopro IS RECORD (
    campo1 NUMBER, campo2 VARCHAR2(40),
    campo3 NUMBER(6,2), campo4 NUMBER
  );
  vtipopro tipopro;
BEGIN
  SELECT productonu, nombre, precio, stock INTO vtipopro
  FROM items WHERE productonu = 30;
  IF vtipopro.campo2 <> 'MARTILLO' THEN
    RAISE_APPLICATION_ERROR(-20201, 'TEST 4.2.1 FALLIDO: esperaba MARTILLO, obtuvo ' || vtipopro.campo2);
  END IF;
  DBMS_OUTPUT.PUT_LINE('✅ TEST 4.2.1 PASADO — Producto 30 = MARTILLO');
END;
/

-- Test 4.2.2 — DELETE (verificar SQL%ROWCOUNT concepto)
DECLARE
  v_count NUMBER;
BEGIN
  -- Inserta un producto de prueba
  INSERT INTO items VALUES (999, 'TEST_DELETE', 1.00, 1);
  DELETE FROM items WHERE productonu = 999;
  IF SQL%ROWCOUNT <> 1 THEN
    RAISE_APPLICATION_ERROR(-20202, 'TEST 4.2.2 FALLIDO: SQL%ROWCOUNT debería ser 1');
  END IF;
  DBMS_OUTPUT.PUT_LINE('✅ TEST 4.2.2 PASADO — SQL%ROWCOUNT funciona correctamente');
  ROLLBACK;
END;
/
