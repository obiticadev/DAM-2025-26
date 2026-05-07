-- ============================================================
-- 📝 BLOQUE 10 — Paquetes (Integrador)
-- ============================================================
-- Lee la teoría en: teoria/T10_paquetes.md
-- Valida tus soluciones con: tests/T10_paquetes.sql
-- ============================================================

SET SERVEROUTPUT ON;

-- ────────────────────────────────────────────────────────────
-- Ejercicio 10.1 — Paquete básico
-- ────────────────────────────────────────────────────────────
-- Crea un paquete "paquete10_masterclass" con:
--   1. FUNCIÓN f1 sin parámetros que retorne COUNT(*) de sedes
--   2. PROCEDURE p2 con:
--        - parámetro IN: num_pedido (NUMBER)
--        - parámetro OUT: p_unidades (NUMBER)
--        - Selecciona unidades de ventas WHERE pedidonu = num_pedido
--
-- Pasos:
--   1. SPEC: Crear la especificación del paquete
--   2. BODY: Crear el cuerpo del paquete con la lógica
--   3. TEST: Bloque anónimo que llame a ambos
-- ────────────────────────────────────────────────────────────
-- SPEC:
CREATE OR REPLACE PACKAGE paquete10_masterclass AS
  FUNCTION  f1 RETURN NUMBER;
  PROCEDURE p2(num_pedido IN NUMBER, p_unidades OUT NUMBER);
END paquete10_masterclass;
/

-- BODY:
CREATE OR REPLACE PACKAGE BODY paquete10_masterclass AS

  FUNCTION f1 RETURN NUMBER
  IS
    total NUMBER;
  BEGIN
    -- Escribe tu código aquí
    RETURN total;
  END f1;

  PROCEDURE p2(num_pedido IN NUMBER, p_unidades OUT NUMBER)
  IS
  BEGIN
    -- Escribe tu código aquí
  END p2;

END paquete10_masterclass;
/

-- TEST:
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ════════════════════════════════════════════════════════════
-- EJERCICIOS ADICIONALES DE PAQUETES
-- ════════════════════════════════════════════════════════════

-- ────────────────────────────────────────────────────────────
-- Ejercicio 10.2 — Paquete para gestión de productos
-- ────────────────────────────────────────────────────────────
-- Crea paquete "pkg_productos" con:
--   - FUNCTION f_count RETURN NUMBER → COUNT(*) de items
--   - FUNCTION f_precio_medio RETURN NUMBER → AVG(precio) de items
--   - PROCEDURE p_listar → lista todos los productos
--   - PROCEDURE p_info(codigo IN NUMBER, p_nombre OUT, p_precio OUT, p_stock OUT)
--
-- Pruébalo en un bloque que muestre:
--   - Total de productos
--   - Precio medio
--   - Lista de productos
--   - Info del producto 20
-- ────────────────────────────────────────────────────────────
-- SPEC:
CREATE OR REPLACE PACKAGE pkg_productos AS
  FUNCTION  f_count RETURN NUMBER;
  FUNCTION  f_precio_medio RETURN NUMBER;
  PROCEDURE p_listar;
  PROCEDURE p_info(codigo IN NUMBER, p_nombre OUT VARCHAR2, p_precio OUT NUMBER, p_stock OUT NUMBER);
END pkg_productos;
/

-- BODY:
CREATE OR REPLACE PACKAGE BODY pkg_productos AS

  FUNCTION f_count RETURN NUMBER
  IS
    total NUMBER;
  BEGIN
    -- Escribe tu código aquí
    RETURN total;
  END f_count;

  FUNCTION f_precio_medio RETURN NUMBER
  IS
    promedio NUMBER;
  BEGIN
    -- Escribe tu código aquí
    RETURN promedio;
  END f_precio_medio;

  PROCEDURE p_listar
  IS
    CURSOR c IS SELECT productonu, nombre, precio FROM items;
  BEGIN
    -- Escribe tu código aquí
  END p_listar;

  PROCEDURE p_info(codigo IN NUMBER, p_nombre OUT VARCHAR2, p_precio OUT NUMBER, p_stock OUT NUMBER)
  IS
  BEGIN
    -- Escribe tu código aquí
  END p_info;

END pkg_productos;
/

-- TEST:
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 10.3 — Paquete para gestión de ventas
-- ────────────────────────────────────────────────────────────
-- Crea paquete "pkg_ventas" con:
--   - FUNCTION f_total_ventas(p_clientenu NUMBER) RETURN NUMBER
--     → SUM(unidades) WHERE clientenu = p_clientenu
--   - FUNCTION f_num_pedidos(p_clientenu NUMBER) RETURN NUMBER
--     → COUNT de pedidos (usa cursor, no COUNT(*))
--   - PROCEDURE p_pedidos_cliente(p_clientenu NUMBER)
--     → Lista los pedidos del cliente
--
-- Pruébalo con el cliente 101.
-- ────────────────────────────────────────────────────────────
-- SPEC:
CREATE OR REPLACE PACKAGE pkg_ventas AS
  FUNCTION f_total_ventas(p_clientenu NUMBER) RETURN NUMBER;
  FUNCTION f_num_pedidos(p_clientenu NUMBER) RETURN NUMBER;
  PROCEDURE p_pedidos_cliente(p_clientenu NUMBER);
END pkg_ventas;
/

-- BODY:
CREATE OR REPLACE PACKAGE BODY pkg_ventas AS

  FUNCTION f_total_ventas(p_clientenu NUMBER) RETURN NUMBER
  IS
    total NUMBER;
  BEGIN
    -- Escribe tu código aquí
    RETURN total;
  END f_total_ventas;

  FUNCTION f_num_pedidos(p_clientenu NUMBER) RETURN NUMBER
  IS
    CURSOR c IS SELECT pedidonu FROM ventas WHERE clientenu = p_clientenu;
    contador NUMBER := 0;
  BEGIN
    -- Escribe tu código aquí
    RETURN contador;
  END f_num_pedidos;

  PROCEDURE p_pedidos_cliente(p_clientenu NUMBER)
  IS
    CURSOR c IS SELECT pedidonu, productonu, unidades FROM ventas WHERE clientenu = p_clientenu;
  BEGIN
    -- Escribe tu código aquí
  END p_pedidos_cliente;

END pkg_ventas;
/

-- TEST:
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 10.4 — Paquete con variables de package
-- ────────────────────────────────────────────────────────────
-- Crea paquete "pkg_contador" con variable global:
--   g_contador NUMBER := 0
--
-- Contenido del paquete:
--   - PROCEDURE p_incrementar → incrementa g_contador
--   - PROCEDURE p_reset → pone g_contador a 0
--   - FUNCTION f_valor RETURN NUMBER → devuelve g_contador
--
-- IMPORTANTE: La variable global mantiene su valor entre llamadas.
-- Pruébalo ejecutando varias veces p_incrementar y mostrando f_valor.
-- ────────────────────────────────────────────────────────────
-- SPEC:
CREATE OR REPLACE PACKAGE pkg_contador AS
  PROCEDURE p_incrementar;
  PROCEDURE p_reset;
  FUNCTION f_valor RETURN NUMBER;
END pkg_contador;
/

-- BODY:
CREATE OR REPLACE PACKAGE BODY pkg_contador AS
  g_contador NUMBER := 0;

  PROCEDURE p_incrementar
  IS
  BEGIN
    -- Escribe tu código aquí
  END p_incrementar;

  PROCEDURE p_reset
  IS
  BEGIN
    -- Escribe tu código aquí
  END p_reset;

  FUNCTION f_valor RETURN NUMBER
  IS
  BEGIN
    -- Escribe tu código aquí
    RETURN g_contador;
  END f_valor;

END pkg_contador;
/

-- TEST:
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────