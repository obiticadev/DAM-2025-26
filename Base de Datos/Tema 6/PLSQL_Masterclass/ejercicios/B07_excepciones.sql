-- ============================================================
-- 📝 BLOQUE 7 — Excepciones
-- ============================================================
-- Lee la teoría en: teoria/T07_excepciones.md
-- Valida tus soluciones con: tests/T07_excepciones.sql
-- ============================================================

SET SERVEROUTPUT ON;

-- ────────────────────────────────────────────────────────────
-- Ejercicio 7.1 — Consulta básica con SELECT INTO
-- ────────────────────────────────────────────────────────────
-- Declara una variable "ncli" de tipo: ventas.clientenu%TYPE
-- Obtén el clientenu del pedido 1002 con SELECT INTO.
-- Imprime: "Número de cliente: X"
-- ────────────────────────────────────────────────────────────
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 7.2 — Excepción de usuario
-- ────────────────────────────────────────────────────────────
-- Declara una variable "ncli" de tipo: ventas.clientenu%TYPE
-- Declara una excepción "errorcliente" EXCEPTION
-- Obtén el clientenu del pedido 1002 con SELECT INTO.
--
-- Después del SELECT, comprueba:
--   IF ncli <> 103 THEN RAISE errorcliente; END IF;
--
-- En el bloque EXCEPTION:
--   WHEN errorcliente THEN imprime 'el cliente no es 101'
-- ────────────────────────────────────────────────────────────
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 7.3 — NO_DATA_FOUND
-- ────────────────────────────────────────────────────────────
-- Declara variable "vlocalidad" de tipo: sedes.localidad%TYPE
-- Intenta obtener la localidad del departamento 50 (no existe).
-- Captura la excepción NO_DATA_FOUND.
-- Imprime: "No existe el departamento 50."
-- ────────────────────────────────────────────────────────────
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 7.4 — OTHERS con SQLCODE y SQLERRM
-- ────────────────────────────────────────────────────────────
-- Declara variable "vlocalidad" de tipo: sedes.localidad%TYPE
-- Intenta la misma consulta del ejercicio anterior.
-- Captura con WHEN OTHERS e imprime:
--   - 'Error código: ' || SQLCODE
--   - 'Error mensaje: ' || SQLERRM
-- ────────────────────────────────────────────────────────────
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 7.5 — TOO_MANY_ROWS
-- ────────────────────────────────────────────────────────────
-- Declara variable "vpedidonu" de tipo: ventas.pedidonu%TYPE
-- Intenta obtener el pedidonu de los pedidos del producto 20
-- (hay varios, por lo que saltará TOO_MANY_ROWS).
-- Captura la excepción e imprime: "Hay más de un pedido para ese producto"
-- ────────────────────────────────────────────────────────────
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 7.6 — ZERO_DIVIDE
-- ────────────────────────────────────────────────────────────
-- Declara variable "divisor" leída por teclado.
-- Declara variable "resultado".
-- Intenta calcular: resultado := 10 / divisor
-- Captura ZERO_DIVIDE e imprime: "No se puede dividir entre cero"
-- ────────────────────────────────────────────────────────────
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 7.7 — RAISE_APPLICATION_ERROR
-- ────────────────────────────────────────────────────────────
-- Declara variable "nota" leída por teclado (0-10).
-- Si la nota está fuera de rango (< 0 o > 10), usa:
--   RAISE_APPLICATION_ERROR(-20001, 'Nota fuera de rango')
-- En EXCEPTION, captura OTHERS e imprime el mensaje.
-- ────────────────────────────────────────────────────────────
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 7.8 — Excepciones con insert
-- ────────────────────────────────────────────────────────────
-- Declara variables para productonu, nombre, precio, stock.
-- Intenta insertar en la tabla items un registro que viole
-- alguna restricción (por ejemplo, un código duplicado).
-- Captura la excepción y muéstrala.
-- ────────────────────────────────────────────────────────────
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 7.9 — %TYPE con excepción
-- ────────────────────────────────────────────────────────────
-- Declara variable "pnu" leída por teclado.
-- Declara variable "precio" de tipo: items.precio%TYPE
-- Intenta obtener el precio del producto indicado.
-- Captura NO_DATA_FOUND e imprime: "Producto no encontrado"
-- ────────────────────────────────────────────────────────────
-- Escribe tu código aquí
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- Ejercicio 7.10 — Excepciones en funciones
-- ────────────────────────────────────────────────────────────
-- Crea una función local "fn_localidad" que:
--   - Reciba un parámetro "p_depnu" (NUMBER)
--   - Declare variable "v_localidad" de tipo sedes.localidad%TYPE
--   - Intente obtener la localidad del departamento
--   - Capture cualquier excepción internamente y devuelva NULL
--   - Retorne v_localidad
--
-- En el bloque principal, llama a la función con un valor por teclado.
-- Imprime el resultado.
-- ────────────────────────────────────────────────────────────
DECLARE
  resultado VARCHAR2(30);
  FUNCTION fn_localidad(p_depnu NUMBER) RETURN VARCHAR2
  IS
    v_localidad sedes.localidad%TYPE;
  BEGIN
    -- Escribe aquí tu código
    RETURN v_localidad;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END fn_localidad;
BEGIN
  resultado := fn_localidad(&p_depnu);
  DBMS_OUTPUT.PUT_LINE('Localidad: ' || resultado);
END;
/