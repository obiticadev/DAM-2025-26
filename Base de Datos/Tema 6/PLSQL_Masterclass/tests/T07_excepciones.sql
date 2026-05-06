-- ============================================================
-- ✅ TEST BLOQUE 7 — Excepciones
-- ============================================================

SET SERVEROUTPUT ON;

-- Test 7.1 — clientenu del pedido 1002
DECLARE
  ncli ventas.clientenu%TYPE;
BEGIN
  SELECT clientenu INTO ncli FROM ventas WHERE pedidonu = 1002;
  IF ncli <> 103 THEN
    RAISE_APPLICATION_ERROR(-20500, 'TEST 7.1 FALLIDO: esperaba 103, obtuvo ' || ncli);
  END IF;
  DBMS_OUTPUT.PUT_LINE('✅ TEST 7.1 PASADO — Cliente pedido 1002 = 103');
END;
/

-- Test 7.2 — NO_DATA_FOUND para departamento 50
DECLARE
  vlocalidad sedes.localidad%TYPE;
  v_capturado BOOLEAN := FALSE;
BEGIN
  BEGIN
    SELECT localidad INTO vlocalidad FROM sedes WHERE depnu = 50;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      v_capturado := TRUE;
  END;
  IF NOT v_capturado THEN
    RAISE_APPLICATION_ERROR(-20510, 'TEST 7.2 FALLIDO: NO_DATA_FOUND no se lanzó');
  END IF;
  DBMS_OUTPUT.PUT_LINE('✅ TEST 7.2 PASADO — NO_DATA_FOUND capturado para depto 50');
END;
/

-- Test 7.4 — TOO_MANY_ROWS para producto 20
DECLARE
  vpedidonu ventas.pedidonu%TYPE;
  v_capturado BOOLEAN := FALSE;
BEGIN
  BEGIN
    SELECT pedidonu INTO vpedidonu FROM ventas WHERE productonu = 20;
  EXCEPTION
    WHEN TOO_MANY_ROWS THEN
      v_capturado := TRUE;
  END;
  IF NOT v_capturado THEN
    RAISE_APPLICATION_ERROR(-20520, 'TEST 7.4 FALLIDO: TOO_MANY_ROWS no se lanzó');
  END IF;
  DBMS_OUTPUT.PUT_LINE('✅ TEST 7.4 PASADO — TOO_MANY_ROWS capturado para producto 20');
END;
/
