-- ============================================================
-- ✅ TEST BLOQUE 8 — Procedimientos
-- ============================================================

SET SERVEROUTPUT ON;

-- Test 8.1 — Procedimiento anónimo p1 (producto 20, pedido 1001)
DECLARE
  resultado NUMBER;
  PROCEDURE p1(num_producto IN NUMBER, num_pedido IN NUMBER, cantidad OUT NUMBER)
  IS
  BEGIN
    SELECT unidades INTO cantidad
    FROM ventas
    WHERE productonu = num_producto AND pedidonu = num_pedido;
  END p1;
BEGIN
  p1(20, 1001, resultado);
  IF resultado <> 5 THEN
    RAISE_APPLICATION_ERROR(-20600, 'TEST 8.1 FALLIDO: esperaba 5, obtuvo ' || resultado);
  END IF;
  DBMS_OUTPUT.PUT_LINE('✅ TEST 8.1 PASADO — p1(20,1001) = 5 unidades');
END;
/

-- Test 8.2 — Procedimiento almacenado p2_masterclass (pedido 1002)
DECLARE
  resultado NUMBER;
BEGIN
  p2_masterclass(1002, resultado);
  IF resultado <> 10 THEN
    RAISE_APPLICATION_ERROR(-20610, 'TEST 8.2 FALLIDO: esperaba 10, obtuvo ' || resultado);
  END IF;
  DBMS_OUTPUT.PUT_LINE('✅ TEST 8.2 PASADO — p2_masterclass(1002) = 10 unidades');
EXCEPTION
  WHEN OTHERS THEN
    IF SQLCODE = -20610 THEN RAISE; END IF;
    DBMS_OUTPUT.PUT_LINE('❌ TEST 8.2 FALLIDO — ¿Has completado p2_masterclass en B08?');
    DBMS_OUTPUT.PUT_LINE('   Error: ' || SQLERRM);
END;
/
