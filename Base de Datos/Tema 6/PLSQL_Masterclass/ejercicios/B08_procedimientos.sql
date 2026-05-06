-- ============================================================
-- 📝 BLOQUE 8 — Procedimientos
-- ============================================================
-- Lee la teoría en: teoria/T08_procedimientos.md
-- Valida tus soluciones con: tests/T08_procedimientos.sql
-- ============================================================

SET SERVEROUTPUT ON;

-- ────────────────────────────────────────────────────────────
-- Ejercicio 8.1 — Procedimiento anónimo con IN y OUT
-- ────────────────────────────────────────────────────────────
-- Crea un procedimiento anónimo "p1" en DECLARE con:
--   - num_producto IN NUMBER
--   - num_pedido   IN NUMBER
--   - cantidad     OUT NUMBER
-- Dentro: SELECT unidades INTO cantidad FROM ventas
--         WHERE productonu = num_producto AND pedidonu = num_pedido
-- En BEGIN: llama a p1(20, 1001, resultado) e imprime resultado.
-- ────────────────────────────────────────────────────────────
DECLARE
  resultado NUMBER;

  PROCEDURE p1(num_producto IN NUMBER, num_pedido IN NUMBER, cantidad OUT NUMBER)
  IS
  BEGIN
    -- TODO: SELECT unidades INTO cantidad
    --       FROM ventas
    --       WHERE productonu = num_producto AND pedidonu = num_pedido
    cantidad := -1; -- stub: quita cuando implementes
  END p1;

BEGIN
  -- TODO: Llama a p1(20, 1001, resultado)
  -- TODO: Imprime 'Cantidad pedida: ' || resultado
  NULL;
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 8.2 — Procedimiento almacenado (CREATE OR REPLACE)
-- ────────────────────────────────────────────────────────────
-- Crea un procedimiento almacenado "p2_masterclass" con:
--   - num_pedido  IN NUMBER
--   - p_unidades  OUT NUMBER
-- Dentro: SELECT unidades INTO p_unidades FROM ventas
--         WHERE pedidonu = num_pedido
-- Luego llámalo desde un bloque anónimo con &num_pedido.
-- ────────────────────────────────────────────────────────────

-- Paso 1: Crear el procedimiento
CREATE OR REPLACE PROCEDURE p2_masterclass(num_pedido IN NUMBER, p_unidades OUT NUMBER)
IS
BEGIN
  -- TODO: SELECT unidades INTO p_unidades
  --       FROM ventas WHERE pedidonu = num_pedido
  p_unidades := -1; -- stub: quita cuando implementes
END p2_masterclass;
/

-- Paso 2: Llamarlo
DECLARE
  resultado NUMBER;
BEGIN
  -- TODO: p2_masterclass(&num_pedido, resultado)
  -- TODO: Imprime 'Unidades para ese pedido: ' || resultado
  NULL;
END;
/
