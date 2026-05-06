-- ============================================================
-- 📝 BLOQUE 9 — Funciones
-- ============================================================
-- Lee la teoría en: teoria/T09_funciones.md
-- Valida tus soluciones con: tests/T09_funciones.sql
-- ============================================================

SET SERVEROUTPUT ON;

-- ────────────────────────────────────────────────────────────
-- Ejercicio 9.1 — Función local que cuenta departamentos
-- ────────────────────────────────────────────────────────────
-- Crea una función local "f1" (sin parámetros) que devuelva
-- el COUNT(*) de sedes.
-- En BEGIN: asigna resultado := f1 e imprime.
-- ────────────────────────────────────────────────────────────
DECLARE
  resultado NUMBER;

  FUNCTION f1 RETURN NUMBER
  IS
    total NUMBER;
  BEGIN
    -- TODO: SELECT COUNT(*) INTO total FROM sedes
    -- TODO: RETURN total
    RETURN -1; -- stub
  END f1;

BEGIN
  -- TODO: resultado := f1
  -- TODO: Imprime 'Número de departamentos: ' || resultado
  NULL;
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 9.2 — Función almacenada con cursor
-- ────────────────────────────────────────────────────────────
-- Crea una función almacenada "f2_masterclass" que reciba
-- num_producto (NUMBER) y devuelva cuántos pedidos tiene.
-- Usa un cursor FOR para contar (no COUNT(*)).
-- Luego llámala desde un bloque anónimo.
-- ────────────────────────────────────────────────────────────

-- Paso 1: Crear la función
CREATE OR REPLACE FUNCTION f2_masterclass(num_producto NUMBER) RETURN NUMBER
IS
  CURSOR c IS SELECT pedidonu FROM ventas WHERE productonu = num_producto;
  contador NUMBER := 0;
BEGIN
  -- TODO: FOR reg IN c LOOP, incrementa contador, END LOOP
  -- TODO: RETURN contador
  RETURN -1; -- stub
END f2_masterclass;
/

-- Paso 2: Llamarla junto con p2_masterclass
DECLARE
  p_unidades  NUMBER;
  num_pedidos NUMBER;
BEGIN
  -- TODO: p2_masterclass(&num_pedido, p_unidades)
  -- TODO: Imprime 'Unidades para ese pedido: ' || p_unidades
  -- TODO: num_pedidos := f2_masterclass(&num_producto)
  -- TODO: Imprime 'Pedidos del producto: ' || num_pedidos
  NULL;
END;
/
