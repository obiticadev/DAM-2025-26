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
-- En el DECLARE, crea una función "f1" sin parámetros que:
--   - Declare variable "total" NUMBER
--   - Haga: SELECT COUNT(*) INTO total FROM sedes
--   - Retorne: total
--
-- En el BEGIN:
--   - Declara variable "resultado" NUMBER
--   - Asigna: resultado := f1
--   - Imprime: 'Número de departamentos: ' || resultado
-- ────────────────────────────────────────────────────────────
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 9.2 — Función almacenada con cursor
-- ────────────────────────────────────────────────────────────
-- CREAR: Función "f2_masterclass" que:
--   - Reciba: num_producto (NUMBER)
--   - Declare cursor: SELECT pedidonu FROM ventas WHERE productonu = num_producto
--   - Declare variable "contador" inicializada a 0
--   - Use FOR reg IN c LOOP para incrementar contador
--   - Retorne contador
--
-- LLAMAR: En un bloque anónimo:
--   - Declara variable "num_pedidos" NUMBER
--   - Asigna: num_pedidos := f2_masterclass(20)
--   - Imprime: 'Pedidos del producto 20: ' || num_pedidos
-- ────────────────────────────────────────────────────────────
-- CREAR:
CREATE OR REPLACE FUNCTION f2_masterclass(num_producto NUMBER) RETURN NUMBER
IS
  CURSOR c IS SELECT pedidonu FROM ventas WHERE productonu = num_producto;
  contador NUMBER := 0;
BEGIN
  -- Escribe tu código aquí
  RETURN contador;
END f2_masterclass;
/

-- LLAMAR:
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 9.3 — Función con parámetros y valor por defecto
-- ────────────────────────────────────────────────────────────
-- Crea función "f_precio_con_iva" que:
--   - Reciba: precio (NUMBER)
--   - Reciba: iva (NUMBER, valor por defecto 21)
--   - Retorne: precio + (precio * iva / 100)
--
-- Pruébalo:
--   v_precio := f_precio_con_iva(100)
--   Imprime: 'Precio con IVA: ' || v_precio  (debe ser 121)
--
--   v_precio := f_precio_con_iva(100, 10)
--   Imprime: 'Precio con IVA reducido: ' || v_precio  (debe ser 110)
-- ────────────────────────────────────────────────────────────
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 9.4 — Función que devuelve texto según condición
-- ────────────────────────────────────────────────────────────
-- Crea función "f_nota_texto" que:
--   - Reciba: nota (NUMBER, 0-10)
--   - Retorne VARCHAR2
--   - Lógica: IF nota < 5 return 'Suspenso'
--             ELSIF nota < 7 return 'Aprobado'
--             ELSIF nota < 9 return 'Notable'
--             ELSE return 'Sobresaliente'
--
-- Pruébalo con notas 4, 7 y 9.
-- ────────────────────────────────────────────────────────────
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 9.5 — Función con manejo de excepciones
-- ────────────────────────────────────────────────────────────
-- Crea función "f_localidad_departamento" que:
--   - Reciba: p_depnu (NUMBER)
--   - Declare v_localidad de tipo sedes.localidad%TYPE
--   - Haga SELECT localidad INTO v_localidad FROM sedes WHERE depnu = p_depnu
--   - Capture NO_DATA_FOUND (WHEN OTHERS) y retorne 'No existe'
--   - Retorne v_localidad
--
-- Pruébalo con depnu 20 (existe) y 999 (no existe).
-- ────────────────────────────────────────────────────────────
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 9.6 — Función con %ROWTYPE
-- ────────────────────────────────────────────────────────────
-- Crea función "f_info_producto" que:
--   - Reciba: p_productonu (NUMBER)
--   - Declare variable "prod" de tipo items%ROWTYPE
--   - Cargue el producto con SELECT * INTO prod
--   - Retorne VARCHAR2 con formato: "X - Y (Z €)"
--     (prod.nombre || ' (' || prod.precio || ' €)')
--
-- Pruébalo con el producto 20.
-- ────────────────────────────────────────────────────────────
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 9.7 — Función para validar stock
-- ────────────────────────────────────────────────────────────
-- Crea función "f_validar_stock" que:
--   - Reciba: cantidad (NUMBER)
--   - Retorne BOOLEAN
--   - Retorne TRUE si cantidad está entre 0 y 100, FALSE si no
--
-- Pruébalo:
--   v_valido := f_validar_stock(50)
--   IF v_valido THEN DBMS_OUTPUT.PUT_LINE('Válido')
--   ELSE DBMS_OUTPUT.PUT_LINE('No válido')
--   END IF
-- ────────────────────────────────────────────────────────────
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 9.8 — Función con cursor y agregación
-- ────────────────────────────────────────────────────────────
-- Crea función "f_total_ventas_cliente" que:
--   - Reciba: p_clientenu (NUMBER)
--   - Declare cursor: SELECT unidades FROM ventas WHERE clientenu = p_clientenu
--   - Declare variable "total" inicializada a 0
--   - Use FOR para acumular las unidades
--   - Retorne total
--
-- Pruébalo con el cliente 101.
-- ────────────────────────────────────────────────────────────
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────