-- ============================================================
-- 🏆 SIMULACRO DE EXAMEN FINAL — PL/SQL
-- ============================================================
-- Este ejercicio integra: Cursores, VARRAY, Excepciones y Paquetes.
-- Es el nivel de un examen académico real.
-- ============================================================

SET SERVEROUTPUT ON;

-- ════════════════════════════════════════════════════════════
-- PARTE A — Función almacenada con cursor y excepción
-- ════════════════════════════════════════════════════════════
-- Crea una función "fn_total_unidades_producto" que:
--   1. Reciba un num_producto (NUMBER)
--   2. Use un cursor para recorrer todos los pedidos de ese producto
--   3. Acumule las unidades totales
--   4. Si no hay ningún pedido, lance una excepción de usuario
--      con RAISE_APPLICATION_ERROR(-20001, 'No hay pedidos para ese producto')
--   5. Devuelva el total de unidades
-- ════════════════════════════════════════════════════════════

CREATE OR REPLACE FUNCTION fn_total_unidades_producto(num_producto NUMBER) RETURN NUMBER
IS
  CURSOR c IS SELECT unidades FROM ventas WHERE productonu = num_producto;
  total    NUMBER := 0;
  contador NUMBER := 0;
BEGIN
  -- TODO: FOR reg IN c LOOP
  --         Incrementa contador
  --         Acumula reg.unidades en total
  --       END LOOP
  -- TODO: IF contador = 0 THEN
  --         RAISE_APPLICATION_ERROR(-20001, 'No hay pedidos para ese producto')
  --       END IF
  -- TODO: RETURN total
  RETURN -1; -- stub
END fn_total_unidades_producto;
/

-- ════════════════════════════════════════════════════════════
-- PARTE B — Procedimiento con VARRAY de resultados
-- ════════════════════════════════════════════════════════════
-- Crea un procedimiento "pr_resumen_productos" que:
--   1. Declare un VARRAY(10) OF VARCHAR2(100)
--   2. Use un cursor para recorrer TODOS los productos
--   3. Para cada producto, llame a fn_total_unidades_producto
--      (capturando la excepción si no tiene pedidos)
--   4. Almacene en el VARRAY una cadena con formato:
--      "Producto: NOMBRE — Total unidades: X" o
--      "Producto: NOMBRE — Sin pedidos"
--   5. Al final, recorra el VARRAY e imprima todo
-- ════════════════════════════════════════════════════════════

CREATE OR REPLACE PROCEDURE pr_resumen_productos
IS
  CURSOR c_prod IS SELECT productonu, nombre FROM items;
  TYPE tresultado IS VARRAY(10) OF VARCHAR2(100);
  resultados tresultado := tresultado();
  total      NUMBER;
  idx        NUMBER := 0;
BEGIN
  -- TODO: FOR reg IN c_prod LOOP
  --         idx := idx + 1
  --         resultados.EXTEND
  --         BEGIN (bloque anidado para capturar excepción)
  --           total := fn_total_unidades_producto(reg.productonu)
  --           resultados(idx) := 'Producto: ' || reg.nombre || ' — Total unidades: ' || total
  --         EXCEPTION
  --           WHEN OTHERS THEN
  --             resultados(idx) := 'Producto: ' || reg.nombre || ' — Sin pedidos'
  --         END
  --       END LOOP

  -- TODO: Imprime '=== RESUMEN DE PRODUCTOS ==='
  -- TODO: FOR j IN 1..resultados.COUNT LOOP, imprime cada línea
  NULL;
END pr_resumen_productos;
/

-- ════════════════════════════════════════════════════════════
-- PARTE C — Paquete integrador
-- ════════════════════════════════════════════════════════════
-- Crea un paquete "pkg_examen" que exponga:
--   - FUNCTION  total_unidades(num_producto NUMBER) RETURN NUMBER
--   - PROCEDURE resumen_completo
-- El paquete reutiliza la lógica de las partes A y B.
-- ════════════════════════════════════════════════════════════

-- Especificación
CREATE OR REPLACE PACKAGE pkg_examen AS
  FUNCTION  total_unidades(num_producto NUMBER) RETURN NUMBER;
  PROCEDURE resumen_completo;
END pkg_examen;
/

-- Cuerpo
CREATE OR REPLACE PACKAGE BODY pkg_examen AS

  FUNCTION total_unidades(num_producto NUMBER) RETURN NUMBER
  IS
    CURSOR c IS SELECT unidades FROM ventas WHERE productonu = num_producto;
    total    NUMBER := 0;
    contador NUMBER := 0;
  BEGIN
    -- TODO: Misma lógica que fn_total_unidades_producto (Parte A)
    RETURN -1; -- stub
  END total_unidades;

  PROCEDURE resumen_completo
  IS
    CURSOR c_prod IS SELECT productonu, nombre FROM items;
    TYPE tresultado IS VARRAY(10) OF VARCHAR2(100);
    resultados tresultado := tresultado();
    total_u    NUMBER;
    idx        NUMBER := 0;
  BEGIN
    -- TODO: Misma lógica que pr_resumen_productos (Parte B)
    --       pero usando pkg_examen.total_unidades en vez de
    --       fn_total_unidades_producto
    NULL;
  END resumen_completo;

END pkg_examen;
/

-- ════════════════════════════════════════════════════════════
-- PARTE D — Ejecución final
-- ════════════════════════════════════════════════════════════
BEGIN
  -- TODO: Llama a pkg_examen.resumen_completo para ver el resultado
  NULL;
END;
/
