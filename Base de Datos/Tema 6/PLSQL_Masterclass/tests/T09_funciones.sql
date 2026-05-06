-- ============================================================
-- ✅ TEST BLOQUE 9 — Funciones
-- ============================================================

SET SERVEROUTPUT ON;

-- Test 9.1 — Función local que cuenta departamentos
DECLARE
  resultado NUMBER;
  FUNCTION f1 RETURN NUMBER IS
    total NUMBER;
  BEGIN
    SELECT COUNT(*) INTO total FROM sedes;
    RETURN total;
  END f1;
BEGIN
  resultado := f1;
  IF resultado <> 4 THEN
    RAISE_APPLICATION_ERROR(-20700, 'TEST 9.1 FALLIDO: esperaba 4, obtuvo ' || resultado);
  END IF;
  DBMS_OUTPUT.PUT_LINE('✅ TEST 9.1 PASADO — f1() = 4 departamentos');
END;
/

-- Test 9.2 — Función almacenada f2_masterclass (producto 20 = 3 pedidos)
DECLARE
  resultado NUMBER;
BEGIN
  resultado := f2_masterclass(20);
  IF resultado <> 3 THEN
    RAISE_APPLICATION_ERROR(-20710, 'TEST 9.2 FALLIDO: esperaba 3 pedidos, obtuvo ' || resultado);
  END IF;
  DBMS_OUTPUT.PUT_LINE('✅ TEST 9.2 PASADO — f2_masterclass(20) = 3 pedidos');
EXCEPTION
  WHEN OTHERS THEN
    IF SQLCODE = -20710 THEN RAISE; END IF;
    DBMS_OUTPUT.PUT_LINE('❌ TEST 9.2 FALLIDO — ¿Has completado f2_masterclass en B09?');
    DBMS_OUTPUT.PUT_LINE('   Error: ' || SQLERRM);
END;
/
