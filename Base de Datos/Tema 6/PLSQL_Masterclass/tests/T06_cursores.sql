-- ============================================================
-- ✅ TEST BLOQUE 6 — Cursores
-- ============================================================

SET SERVEROUTPUT ON;

-- Test 6.1 — Cursor explícito sobre departamentos
DECLARE
  CURSOR c1 IS SELECT depnu FROM sedes ORDER BY depnu;
  vdepnu sedes.depnu%TYPE;
  idx    NUMBER := 0;
  TYPE tvarray IS VARRAY(10) OF NUMBER;
  esperados tvarray := tvarray(10, 20, 30, 40);
  obtenidos tvarray := tvarray();
BEGIN
  OPEN c1;
  FETCH c1 INTO vdepnu;
  WHILE c1%FOUND LOOP
    idx := idx + 1;
    obtenidos.EXTEND;
    obtenidos(idx) := vdepnu;
    FETCH c1 INTO vdepnu;
  END LOOP;
  CLOSE c1;

  IF idx <> 4 THEN
    RAISE_APPLICATION_ERROR(-20400, 'TEST 6.1 FALLIDO: esperaba 4 departamentos, obtuvo ' || idx);
  END IF;
  FOR i IN 1..4 LOOP
    IF obtenidos(i) <> esperados(i) THEN
      RAISE_APPLICATION_ERROR(-20401, 'TEST 6.1 FALLIDO: posición ' || i || ' incorrecta');
    END IF;
  END LOOP;
  DBMS_OUTPUT.PUT_LINE('✅ TEST 6.1 PASADO — Cursor explícito: 10,20,30,40');
END;
/

-- Test 6.2 — Cursor FOR con VARRAY
DECLARE
  CURSOR c1 IS SELECT depnu FROM sedes ORDER BY depnu;
  TYPE tvarray IS VARRAY(20) OF NUMBER;
  varray tvarray := tvarray();
  idx NUMBER := 0;
BEGIN
  FOR reg IN c1 LOOP
    idx := idx + 1;
    varray.EXTEND;
    varray(idx) := reg.depnu;
  END LOOP;
  IF varray.COUNT <> 4 THEN
    RAISE_APPLICATION_ERROR(-20410, 'TEST 6.2 FALLIDO: VARRAY debería tener 4 elementos');
  END IF;
  DBMS_OUTPUT.PUT_LINE('✅ TEST 6.2 PASADO — Cursor FOR + VARRAY con 4 elementos');
END;
/

-- Test 6.3 — Cursor con parámetro (producto 30 = MARTILLO)
DECLARE
  CURSOR c1(param NUMBER) IS
    SELECT nombre FROM items WHERE productonu = param;
  vnombre items.nombre%TYPE;
BEGIN
  OPEN c1(30);
  FETCH c1 INTO vnombre;
  IF c1%NOTFOUND THEN
    RAISE_APPLICATION_ERROR(-20420, 'TEST 6.3 FALLIDO: no encontró producto 30');
  END IF;
  IF vnombre <> 'MARTILLO' THEN
    RAISE_APPLICATION_ERROR(-20421, 'TEST 6.3 FALLIDO: esperaba MARTILLO, obtuvo ' || vnombre);
  END IF;
  CLOSE c1;
  DBMS_OUTPUT.PUT_LINE('✅ TEST 6.3 PASADO — Cursor con parámetro: MARTILLO');
END;
/
