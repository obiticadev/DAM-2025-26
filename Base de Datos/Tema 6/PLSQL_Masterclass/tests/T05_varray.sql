-- ============================================================
-- ✅ TEST BLOQUE 5 — VARRAY
-- ============================================================

SET SERVEROUTPUT ON;

-- Test 5.1 — Métodos VARRAY de cadenas
DECLARE
  TYPE tacad IS VARRAY(4) OF VARCHAR2(30);
  vtacad tacad := tacad('Hola', 'Mundo', 'PL/SQL');
BEGIN
  IF vtacad.FIRST <> 1 THEN
    RAISE_APPLICATION_ERROR(-20300, 'TEST 5.1 FALLIDO: FIRST debería ser 1');
  END IF;
  IF vtacad.COUNT <> 3 THEN
    RAISE_APPLICATION_ERROR(-20301, 'TEST 5.1 FALLIDO: COUNT debería ser 3');
  END IF;
  IF vtacad.LIMIT <> 4 THEN
    RAISE_APPLICATION_ERROR(-20302, 'TEST 5.1 FALLIDO: LIMIT debería ser 4');
  END IF;
  IF vtacad(3) <> 'PL/SQL' THEN
    RAISE_APPLICATION_ERROR(-20303, 'TEST 5.1 FALLIDO: posición 3 debería ser PL/SQL');
  END IF;
  DBMS_OUTPUT.PUT_LINE('✅ TEST 5.1 PASADO — Métodos VARRAY correctos');
END;
/

-- Test 5.3 — VARRAY numérico con bucle
DECLARE
  TYPE tavnum IS VARRAY(4) OF NUMBER;
  vtavnum tavnum := tavnum(0, 0, 0, 0);
BEGIN
  FOR i IN 1..4 LOOP
    vtavnum(i) := i * 3;
  END LOOP;
  IF vtavnum(1) <> 3 OR vtavnum(2) <> 6 OR vtavnum(3) <> 9 OR vtavnum(4) <> 12 THEN
    RAISE_APPLICATION_ERROR(-20310, 'TEST 5.3 FALLIDO: valores incorrectos');
  END IF;
  DBMS_OUTPUT.PUT_LINE('✅ TEST 5.3 PASADO — VARRAY numérico: 3,6,9,12');
END;
/
