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
-- Crea función "fn_total_unidades_producto" que:
--   1. Reciba: num_producto (NUMBER)
--   2. Declare cursor: SELECT unidades FROM ventas WHERE productonu = num_producto
--   3. Declare variables: total := 0, contador := 0
--   4. Use FOR reg IN c LOOP:
--        - Incrementa contador
--        - Acumula reg.unidades en total
--      END LOOP
--   5. IF contador = 0 THEN
--        RAISE_APPLICATION_ERROR(-20001, 'No hay pedidos para ese producto')
--      END IF
--   6. RETURN total
-- ════════════════════════════════════════════════════════════
CREATE OR REPLACE FUNCTION fn_total_unidades_producto(num_producto NUMBER) RETURN NUMBER
IS
  CURSOR c IS SELECT unidades FROM ventas WHERE productonu = num_producto;
  total    NUMBER := 0;
  contador NUMBER := 0;
BEGIN
  -- Escribe tu código aquí
  RETURN total;
END fn_total_unidades_producto;
/

-- ════════════════════════════════════════════════════════════
-- PARTE B — Procedimiento con VARRAY de resultados
-- ════════════════════════════════════════════════════════════
-- Crea procedimiento "pr_resumen_productos" que:
--   1. Declare TYPE tresultado IS VARRAY(10) OF VARCHAR2(100)
--   2. Declare variable "resultados" inicializada: tresultado()
--   3. Declare cursor "c_prod": SELECT productonu, nombre FROM items
--   4. Declare variables: idx := 0, total NUMBER
--   5. FOR reg IN c_prod LOOP:
--        - idx := idx + 1
--        - resultados.EXTEND
--        - En un bloque BEGIN/EXCEPTION interno:
--            total := fn_total_unidades_producto(reg.productonu)
--            resultados(idx) := 'Producto: ' || reg.nombre || ' - Total unidades: ' || total
--          EXCEPTION WHEN OTHERS THEN
--            resultados(idx) := 'Producto: ' || reg.nombre || ' - Sin pedidos'
--        - END del bloque interno
--      END LOOP
--   6. Imprime cabecera: '=== RESUMEN DE PRODUCTOS ==='
--   7. FOR j IN 1..resultados.COUNT LOOP, imprime cada línea
-- ════════════════════════════════════════════════════════════
CREATE OR REPLACE PROCEDURE pr_resumen_productos
IS
  CURSOR c_prod IS SELECT productonu, nombre FROM items;
  TYPE tresultado IS VARRAY(10) OF VARCHAR2(100);
  resultados tresultado := tresultado();
  total      NUMBER;
  idx        NUMBER := 0;
BEGIN
  -- Escribe tu código aquí
END pr_resumen_productos;
/

-- ════════════════════════════════════════════════════════════
-- PARTE C — Paquete integrador
-- ════════════════════════════════════════════════════════════
-- Crea paquete "pkg_examen" con:
--   SPEC:
--     - FUNCTION total_unidades(num_producto NUMBER) RETURN NUMBER
--     - PROCEDURE resumen_completo
--
--   BODY:
--     - FUNCTION total_unidades: misma lógica que fn_total_unidades_producto
--     - PROCEDURE resumen_completo: misma lógica que pr_resumen_productos
--       pero usando pkg_examen.total_unidades
-- ════════════════════════════════════════════════════════════
-- SPEC:
CREATE OR REPLACE PACKAGE pkg_examen AS
  FUNCTION  total_unidades(num_producto NUMBER) RETURN NUMBER;
  PROCEDURE resumen_completo;
END pkg_examen;
/

-- BODY:
CREATE OR REPLACE PACKAGE BODY pkg_examen AS

  FUNCTION total_unidades(num_producto NUMBER) RETURN NUMBER
  IS
    CURSOR c IS SELECT unidades FROM ventas WHERE productonu = num_producto;
    total    NUMBER := 0;
    contador NUMBER := 0;
  BEGIN
    -- Escribe tu código aquí
    RETURN total;
  END total_unidades;

  PROCEDURE resumen_completo
  IS
    CURSOR c_prod IS SELECT productonu, nombre FROM items;
    TYPE tresultado IS VARRAY(10) OF VARCHAR2(100);
    resultados tresultado := tresultado();
    total_u    NUMBER;
    idx        NUMBER := 0;
  BEGIN
    -- Escribe tu código aquí
  END resumen_completo;

END pkg_examen;
/

-- ════════════════════════════════════════════════════════════
-- PARTE D — Ejecución final
-- ════════════════════════════════════════════════════════════
-- Llama a pkg_examen.resumen_completo
-- ════════════════════════════════════════════════════════════
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ════════════════════════════════════════════════════════════
-- EJERCICIOS ADICIONALES DE REPASO
-- ════════════════════════════════════════════════════════════

-- ────────────────────────────────────────────────────────────
-- Ejercicio adicional 1 — Tabla de multiplicar con procedimientos
-- ────────────────────────────────────────────────────────────
-- Crea procedimiento "p_tabla_multiplicar" que:
--   - Reciba: n (NUMBER)
--   - Use un bucle FOR de 1 a 10
--   - Imprima: n || ' x ' || i || ' = ' || (n * i)
-- ────────────────────────────────────────────────────────────
CREATE OR REPLACE PROCEDURE p_tabla_multiplicar(n NUMBER)
IS
BEGIN
  -- Escribe tu código aquí
END p_tabla_multiplicar;
/

BEGIN
  p_tabla_multiplicar(7);
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio adicional 2 — Función Fibonacci
-- ────────────────────────────────────────────────────────────
-- Crea función "f_fibonacci" que:
--   - Reciba: n (NUMBER)
--   - Use un bucle WHILE para calcular el n-ésimo número
--   - La serie: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34...
--   - Pista: c = a + b; luego a = b, b = c
-- ────────────────────────────────────────────────────────────
CREATE OR REPLACE FUNCTION f_fibonacci(n NUMBER) RETURN NUMBER
IS
  a NUMBER := 0;
  b NUMBER := 1;
  c NUMBER;
  i NUMBER := 1;
BEGIN
  -- Escribe tu código aquí
  RETURN a;
END f_fibonacci;
/

DECLARE
  v_resultado NUMBER;
BEGIN
  FOR i IN 0..10 LOOP
    v_resultado := f_fibonacci(i);
    DBMS_OUTPUT.PUT_LINE('Fibonacci(' || i || ') = ' || v_resultado);
  END LOOP;
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio adicional 3 — Cursor con COMMIT/ROLLBACK
-- ────────────────────────────────────────────────────────────
-- Crea procedimiento "p_insertar_multiple" que:
--   1. Declare TYPE tproducto IS RECORD con: codigo, nombre, precio, stock
--   2. Declare TYPE taproductos IS VARRAY(3) OF tproducto
--   3. Declare variable productos con 3 registros hardcodeados
--   4. Use un cursor FOR para insertar cada uno
--   5. COMMIT al final
--   6. Capture errores con ROLLBACK y muestre el error
-- ────────────────────────────────────────────────────────────
CREATE OR REPLACE PROCEDURE p_insertar_multiple
IS
  TYPE tproducto IS RECORD (
    codigo NUMBER,
    nombre VARCHAR2(40),
    precio NUMBER(6,2),
    stock  NUMBER
  );
  TYPE taproductos IS VARRAY(3) OF tproducto;
  productos taproductos;
BEGIN
  -- Escribe tu código aquí
  COMMIT;
  DBMS_OUTPUT.PUT_LINE('Inserciones confirmadas');
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    DBMS_OUTPUT.PUT_LINE('Error, se deshizo: ' || SQLERRM);
END p_insertar_multiple;
/

BEGIN
  p_insertar_multiple;
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio adicional 4 — Paquete con excepciones de negocio
-- ────────────────────────────────────────────────────────────
-- Crea paquete "pkg_stock" con:
--   - FUNCTION f_stock_actual(p_codigo NUMBER) RETURN NUMBER
--     → SELECT stock FROM items WHERE productonu = p_codigo
--   - PROCEDURE p_vender(p_codigo NUMBER, cantidad NUMBER)
--     → Resta cantidad del stock
--     → Si stock < cantidad, RAISE_APPLICATION_ERROR(-20001, 'Stock insuficiente')
-- ────────────────────────────────────────────────────────────
CREATE OR REPLACE PACKAGE pkg_stock AS
  PROCEDURE p_vender(p_codigo NUMBER, cantidad NUMBER);
  FUNCTION f_stock_actual(p_codigo NUMBER) RETURN NUMBER;
END pkg_stock;
/

CREATE OR REPLACE PACKAGE BODY pkg_stock AS

  FUNCTION f_stock_actual(p_codigo NUMBER) RETURN NUMBER
  IS
    v_stock items.stock%TYPE;
  BEGIN
    -- Escribe tu código aquí
    RETURN v_stock;
  END f_stock_actual;

  PROCEDURE p_vender(p_codigo NUMBER, cantidad NUMBER)
  IS
    v_stock items.stock%TYPE;
  BEGIN
    -- Escribe tu código aquí
  EXCEPTION
    WHEN OTHERS THEN
      RAISE;
  END p_vender;

END pkg_stock;
/

DECLARE
  v_stock NUMBER;
BEGIN
  v_stock := pkg_stock.f_stock_actual(10);
  DBMS_OUTPUT.PUT_LINE('Stock actual producto 10: ' || v_stock);
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio adicional 5 — Reporte con formato
-- ────────────────────────────────────────────────────────────
-- Crea procedimiento "p_reporte_ventas" que:
--   1. Declare cursor "c_ventas" con JOIN de ventas, clientes, items
--      (pedidonu, nombre_cliente, nombre_producto, unidades)
--   2. Declare contadores: v_total_unidades := 0, v_contador := 0
--   3. Imprima cabecera: "===== REPORTE DE VENTAS ====="
--   4. Recorra el cursor:
--        - Imprima: "Pedido X | Cliente Y | Producto Z | Unidades W"
--        - Acumule los contadores
--   5. Imprima resumen:
--        - "Total pedidos: X"
--        - "Total unidades vendidas: Y"
-- ────────────────────────────────────────────────────────────
CREATE OR REPLACE PROCEDURE p_reporte_ventas
IS
  CURSOR c_ventas IS
    SELECT v.pedidonu, c.nombre as cliente, i.nombre as producto, v.unidades
    FROM ventas v
    JOIN clientes c ON v.clientenu = c.clientenu
    JOIN items i ON v.productonu = i.productonu
    ORDER BY v.pedidonu;
  v_total_unidades NUMBER := 0;
  v_contador NUMBER := 0;
BEGIN
  -- Escribe tu código aquí
END p_reporte_ventas;
/

BEGIN
  p_reporte_ventas;
END;
/