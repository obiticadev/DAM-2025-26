-- ============================================================
-- ✅ TEST SIMULACRO DE EXAMEN FINAL
-- ============================================================

SET SERVEROUTPUT ON;

-- Test Parte A — fn_total_unidades_producto
-- Producto 20 tiene pedidos: 1001(5), 1004(15), 1007(8) = 28 unidades
DECLARE
  resultado NUMBER;
BEGIN
  resultado := fn_total_unidades_producto(20);
  IF resultado <> 28 THEN
    RAISE_APPLICATION_ERROR(-20900, 'TEST SIM-A FALLIDO: esperaba 28, obtuvo ' || resultado);
  END IF;
  DBMS_OUTPUT.PUT_LINE('✅ TEST SIM-A.1 PASADO — fn_total_unidades_producto(20) = 28');
EXCEPTION
  WHEN OTHERS THEN
    IF SQLCODE = -20900 THEN RAISE; END IF;
    DBMS_OUTPUT.PUT_LINE('❌ TEST SIM-A.1 FALLIDO — Error: ' || SQLERRM);
END;
/

-- Test Parte A — Producto sin pedidos (ej. producto inexistente 99)
DECLARE
  resultado NUMBER;
  v_error   BOOLEAN := FALSE;
BEGIN
  BEGIN
    resultado := fn_total_unidades_producto(99);
  EXCEPTION
    WHEN OTHERS THEN
      IF SQLCODE = -20001 THEN
        v_error := TRUE;
      END IF;
  END;
  IF NOT v_error THEN
    RAISE_APPLICATION_ERROR(-20901, 'TEST SIM-A.2 FALLIDO: debería lanzar error -20001');
  END IF;
  DBMS_OUTPUT.PUT_LINE('✅ TEST SIM-A.2 PASADO — Excepción -20001 lanzada para producto sin pedidos');
EXCEPTION
  WHEN OTHERS THEN
    IF SQLCODE = -20901 THEN RAISE; END IF;
    DBMS_OUTPUT.PUT_LINE('❌ TEST SIM-A.2 FALLIDO — Error: ' || SQLERRM);
END;
/

-- Test Parte C — pkg_examen.total_unidades
DECLARE
  resultado NUMBER;
BEGIN
  resultado := pkg_examen.total_unidades(30);
  IF resultado <> 10 THEN
    RAISE_APPLICATION_ERROR(-20910, 'TEST SIM-C FALLIDO: esperaba 10, obtuvo ' || resultado);
  END IF;
  DBMS_OUTPUT.PUT_LINE('✅ TEST SIM-C PASADO — pkg_examen.total_unidades(30) = 10');
EXCEPTION
  WHEN OTHERS THEN
    IF SQLCODE = -20910 THEN RAISE; END IF;
    DBMS_OUTPUT.PUT_LINE('❌ TEST SIM-C FALLIDO — Error: ' || SQLERRM);
END;
/

-- Test Parte D — Ejecución completa del resumen
BEGIN
  DBMS_OUTPUT.PUT_LINE('');
  DBMS_OUTPUT.PUT_LINE('--- Ejecutando pkg_examen.resumen_completo ---');
  pkg_examen.resumen_completo;
  DBMS_OUTPUT.PUT_LINE('✅ TEST SIM-D PASADO — resumen_completo ejecutado sin errores');
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('❌ TEST SIM-D FALLIDO — Error: ' || SQLERRM);
END;
/
