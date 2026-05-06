-- ============================================================
-- ✅ TEST BLOQUE 10 — Paquetes
-- ============================================================

SET SERVEROUTPUT ON;

-- Test 10 — paquete10_masterclass.f1
DECLARE
  resultado NUMBER;
BEGIN
  resultado := paquete10_masterclass.f1;
  IF resultado <> 4 THEN
    RAISE_APPLICATION_ERROR(-20800, 'TEST 10.f1 FALLIDO: esperaba 4, obtuvo ' || resultado);
  END IF;
  DBMS_OUTPUT.PUT_LINE('✅ TEST 10.f1 PASADO — paquete10_masterclass.f1 = 4');
EXCEPTION
  WHEN OTHERS THEN
    IF SQLCODE = -20800 THEN RAISE; END IF;
    DBMS_OUTPUT.PUT_LINE('❌ TEST 10.f1 FALLIDO — ¿Has completado paquete10_masterclass en B10?');
    DBMS_OUTPUT.PUT_LINE('   Error: ' || SQLERRM);
END;
/

-- Test 10 — paquete10_masterclass.p2 (pedido 1003)
DECLARE
  resultado NUMBER;
BEGIN
  paquete10_masterclass.p2(1003, resultado);
  IF resultado <> 25 THEN
    RAISE_APPLICATION_ERROR(-20810, 'TEST 10.p2 FALLIDO: esperaba 25, obtuvo ' || resultado);
  END IF;
  DBMS_OUTPUT.PUT_LINE('✅ TEST 10.p2 PASADO — paquete10_masterclass.p2(1003) = 25');
EXCEPTION
  WHEN OTHERS THEN
    IF SQLCODE = -20810 THEN RAISE; END IF;
    DBMS_OUTPUT.PUT_LINE('❌ TEST 10.p2 FALLIDO — ¿Has completado paquete10_masterclass en B10?');
    DBMS_OUTPUT.PUT_LINE('   Error: ' || SQLERRM);
END;
/
