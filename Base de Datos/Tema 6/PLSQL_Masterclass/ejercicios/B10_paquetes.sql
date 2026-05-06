-- ============================================================
-- 📝 BLOQUE 10 — Paquetes (Integrador)
-- ============================================================
-- Lee la teoría en: teoria/T10_paquetes.md
-- Valida tus soluciones con: tests/T10_paquetes.sql
-- ============================================================

SET SERVEROUTPUT ON;

-- ────────────────────────────────────────────────────────────
-- Ejercicio Final — PAQUETE10
-- ────────────────────────────────────────────────────────────
-- Crea un paquete "paquete10_masterclass" que contenga:
--   - FUNCTION f1 RETURN NUMBER → devuelve COUNT(*) de sedes
--   - PROCEDURE p2(num_pedido IN NUMBER, p_unidades OUT NUMBER)
--     → devuelve las unidades de un pedido
-- ────────────────────────────────────────────────────────────

-- Paso 1: Especificación del paquete
CREATE OR REPLACE PACKAGE paquete10_masterclass AS
  FUNCTION  f1 RETURN NUMBER;
  PROCEDURE p2(num_pedido IN NUMBER, p_unidades OUT NUMBER);
END paquete10_masterclass;
/

-- Paso 2: Cuerpo del paquete
CREATE OR REPLACE PACKAGE BODY paquete10_masterclass AS

  FUNCTION f1 RETURN NUMBER
  IS
    total NUMBER;
  BEGIN
    -- TODO: SELECT COUNT(*) INTO total FROM sedes
    -- TODO: RETURN total
    RETURN -1; -- stub
  END f1;

  PROCEDURE p2(num_pedido IN NUMBER, p_unidades OUT NUMBER)
  IS
  BEGIN
    -- TODO: SELECT unidades INTO p_unidades
    --       FROM ventas WHERE pedidonu = num_pedido
    p_unidades := -1; -- stub
  END p2;

END paquete10_masterclass;
/

-- Paso 3: Ejecutar desde un bloque aparte
DECLARE
  num_dep  NUMBER;
  unidades NUMBER;
BEGIN
  -- TODO: num_dep := paquete10_masterclass.f1
  -- TODO: Imprime 'Número de departamentos: ' || num_dep
  -- TODO: paquete10_masterclass.p2(&num_pedido, unidades)
  -- TODO: Imprime 'Unidades para ese pedido: ' || unidades
  NULL;
END;
/
