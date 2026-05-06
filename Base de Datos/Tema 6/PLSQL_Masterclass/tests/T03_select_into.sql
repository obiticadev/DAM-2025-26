-- ============================================================
-- ✅ TEST BLOQUE 3 — SELECT INTO, %TYPE, %ROWTYPE
-- ============================================================
-- Tests automáticos con RAISE_APPLICATION_ERROR + ROLLBACK.
-- ============================================================

SET SERVEROUTPUT ON;

-- Test 3.4 — COUNT de departamentos
DECLARE
  total NUMBER;
BEGIN
  SELECT COUNT(*) INTO total FROM sedes;
  IF total <> 4 THEN
    RAISE_APPLICATION_ERROR(-20100, 'TEST 3.4 FALLIDO: esperaba 4 departamentos, obtuvo ' || total);
  END IF;
  DBMS_OUTPUT.PUT_LINE('✅ TEST 3.4 PASADO — COUNT departamentos = 4');
END;
/

-- Test 3.5 — Suma unidades producto 20
DECLARE
  total NUMBER;
BEGIN
  SELECT SUM(unidades) INTO total FROM ventas WHERE productonu = 20;
  IF total <> 28 THEN
    RAISE_APPLICATION_ERROR(-20101, 'TEST 3.5 FALLIDO: esperaba 28, obtuvo ' || total);
  END IF;
  DBMS_OUTPUT.PUT_LINE('✅ TEST 3.5 PASADO — SUM unidades producto 20 = 28');
END;
/

-- Test 3.6 — SELECT INTO con %TYPE (depto 20)
DECLARE
  vnombre    sedes.nombre%TYPE;
  vlocalidad sedes.localidad%TYPE;
BEGIN
  SELECT nombre, localidad INTO vnombre, vlocalidad
  FROM sedes WHERE depnu = 20;
  IF vnombre <> 'INVESTIGACION' OR vlocalidad <> 'MADRID' THEN
    RAISE_APPLICATION_ERROR(-20102, 'TEST 3.6 FALLIDO: datos incorrectos');
  END IF;
  DBMS_OUTPUT.PUT_LINE('✅ TEST 3.6 PASADO — Depto 20 = INVESTIGACION, MADRID');
END;
/

-- Test 3.7 — SUM total de unidades
DECLARE
  total ventas.unidades%TYPE;
BEGIN
  SELECT SUM(unidades) INTO total FROM ventas;
  IF total <> 73 THEN
    RAISE_APPLICATION_ERROR(-20103, 'TEST 3.7 FALLIDO: esperaba 73, obtuvo ' || total);
  END IF;
  DBMS_OUTPUT.PUT_LINE('✅ TEST 3.7 PASADO — SUM total unidades = 73');
END;
/

-- Test 3.8 — %ROWTYPE (depto 40)
DECLARE
  fila_dep sedes%ROWTYPE;
BEGIN
  SELECT * INTO fila_dep FROM sedes WHERE depnu = 40;
  IF fila_dep.nombre <> 'PRODUCCION' OR fila_dep.localidad <> 'BILBAO' THEN
    RAISE_APPLICATION_ERROR(-20104, 'TEST 3.8 FALLIDO: datos incorrectos');
  END IF;
  DBMS_OUTPUT.PUT_LINE('✅ TEST 3.8 PASADO — Depto 40 = PRODUCCION, BILBAO');
END;
/
