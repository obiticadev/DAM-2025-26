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
-- Dentro del DECLARE, crea un procedimiento "p1" con:
--   - Parámetro IN: num_producto (NUMBER)
--   - Parámetro IN: num_pedido (NUMBER)
--   - Parámetro OUT: cantidad (NUMBER)
--
-- El cuerpo debe hacer:
--   SELECT unidades INTO cantidad FROM ventas
--   WHERE productonu = num_producto AND pedidonu = num_pedido
--
-- En el BEGIN principal:
--   - Declara variable "resultado" NUMBER
--   - Llama a p1(20, 1001, resultado)
--   - Imprime: 'Cantidad pedida: ' || resultado
-- ────────────────────────────────────────────────────────────
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 8.2 — Procedimiento almacenado básico
-- ────────────────────────────────────────────────────────────
-- CREAR: Crea procedimiento "p2_masterclass" con:
--   - Parámetro IN: num_pedido (NUMBER)
--   - Parámetro OUT: p_unidades (NUMBER)
-- El cuerpo debe hacer:
--   SELECT unidades INTO p_unidades FROM ventas
--   WHERE pedidonu = num_pedido
--
-- LLAMAR: Desde un bloque anónimo:
--   - Declara variable "resultado" NUMBER
--   - Llama a p2_masterclass(&num_pedido, resultado)
--   - Imprime: 'Unidades para ese pedido: ' || resultado
-- ────────────────────────────────────────────────────────────
-- CREAR:
CREATE OR REPLACE PROCEDURE p2_masterclass(num_pedido IN NUMBER, p_unidades OUT NUMBER)
IS
BEGIN
  -- Escribe tu código aquí
END p2_masterclass;
/

-- LLAMAR:
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 8.3 — Procedimiento para insertar producto
-- ────────────────────────────────────────────────────────────
-- Crea procedimiento "p_insertar_producto" con parámetros:
--   - p_codigo IN NUMBER
--   - p_nombre IN VARCHAR2
--   - p_precio IN NUMBER
--   - p_stock IN NUMBER
--
-- El cuerpo debe insertar en la tabla items.
-- Imprime: 'Producto insertado correctamente'
--
-- Pruébalo llamando: p_insertar_producto(500, 'Nuevo Producto', 99.99, 10)
-- ────────────────────────────────────────────────────────────
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 8.4 — Procedimiento para actualizar stock
-- ────────────────────────────────────────────────────────────
-- Crea procedimiento "p_actualizar_stock" con:
--   - p_codigo IN NUMBER
--   - p_nuevo_stock IN NUMBER
--
-- El cuerpo debe UPDATE el stock del producto indicado.
-- Imprime: 'Filas actualizadas: X' (SQL%ROWCOUNT)
--
-- Pruébalo con el producto 10 y stock 100.
-- ────────────────────────────────────────────────────────────
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 8.5 — Procedimiento para borrar producto
-- ────────────────────────────────────────────────────────────
-- Crea procedimiento "p_borrar_producto" con:
--   - p_codigo IN NUMBER
--
-- El cuerpo debe DELETE del producto indicado.
-- Imprime: 'Filas borradas: X'
--
-- Pruébalo borrando el producto 500.
-- ────────────────────────────────────────────────────────────
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 8.6 — Procedimiento con varios parámetros OUT
-- ────────────────────────────────────────────────────────────
-- Crea procedimiento "p_info_producto" con:
--   - p_codigo IN NUMBER
--   - p_nombre OUT VARCHAR2
--   - p_precio OUT NUMBER
--   - p_stock OUT NUMBER
--
-- Carga los datos del producto y devuélvelos por los OUT.
--
-- En el bloque de prueba:
--   - Declara variables para los 3 OUT
--   - Llama al procedimiento con el producto 20
--   - Imprime: 'Producto: X, Precio: Y, Stock: Z'
-- ────────────────────────────────────────────────────────────
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 8.7 — Procedimiento con valores por defecto
-- ────────────────────────────────────────────────────────────
-- Crea procedimiento "p_saludo" con:
--   - p_nombre IN VARCHAR2 := 'Mundo'
--
-- El cuerpo debe imprimir: 'Hola ' || p_nombre
--
-- En el bloque de prueba:
--   - Llama sin argumentos: p_saludo
--   - Llama con argumento: p_saludo('PL/SQL')
-- ────────────────────────────────────────────────────────────
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 8.8 — Procedimiento con cursor
-- ────────────────────────────────────────────────────────────
-- Crea procedimiento "p_listar_productos" sin parámetros.
--
-- Declara cursor "c_prod":
--   SELECT productonu, nombre, precio FROM items
--
-- El cuerpo recorre el cursor e imprime cada producto:
--   "X - Y (Z €)"  (código - nombre (precio))
-- ────────────────────────────────────────────────────────────
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────