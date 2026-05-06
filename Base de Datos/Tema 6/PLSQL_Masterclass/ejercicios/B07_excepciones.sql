-- ============================================================
-- 📝 BLOQUE 7 — Excepciones
-- ============================================================
-- Lee la teoría en: teoria/T07_excepciones.md
-- Valida tus soluciones con: tests/T07_excepciones.sql
-- ============================================================

SET SERVEROUTPUT ON;

-- ────────────────────────────────────────────────────────────
-- Ejercicio 7.1 — Consulta básica con excepción de usuario
-- ────────────────────────────────────────────────────────────
-- Parte 1.1: Obtén el clientenu del pedido 1002 e imprímelo.
-- ────────────────────────────────────────────────────────────
DECLARE
  ncli ventas.clientenu%TYPE;
BEGIN
  -- TODO: SELECT clientenu INTO ncli FROM ventas WHERE pedidonu = 1002
  -- TODO: Imprime 'Número de cliente: ' || ncli
  NULL;
END;
/

-- ────────────────────────────────────────────────────────────
-- Parte 1.2: Declara una excepción de usuario "errorcliente".
-- Después del SELECT, si ncli <> 103, lanza la excepción.
-- Captúrala e imprime un mensaje.
-- ────────────────────────────────────────────────────────────
DECLARE
  ncli         ventas.clientenu%TYPE;
  errorcliente EXCEPTION;
BEGIN
  -- TODO: SELECT clientenu INTO ncli FROM ventas WHERE pedidonu = 1002
  -- TODO: IF ncli <> 103 THEN RAISE errorcliente; END IF;
  -- TODO: Imprime 'Número de cliente: ' || ncli
  NULL;
EXCEPTION
  WHEN errorcliente THEN
    DBMS_OUTPUT.PUT_LINE('el cliente no es 101');
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 7.2 — NO_DATA_FOUND sin OTHERS
-- ────────────────────────────────────────────────────────────
-- Busca la localidad del departamento 50 (que no existe).
-- Captura NO_DATA_FOUND e imprime un mensaje.
-- ────────────────────────────────────────────────────────────
DECLARE
  vlocalidad sedes.localidad%TYPE;
BEGIN
  -- TODO: SELECT localidad INTO vlocalidad FROM sedes WHERE depnu = 50
  -- TODO: Imprime 'Localidad: ' || vlocalidad
  NULL;
EXCEPTION
  -- TODO: WHEN NO_DATA_FOUND THEN imprime 'No existe el departamento 50.'
  WHEN OTHERS THEN NULL; -- Reemplaza este WHEN por el correcto
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 7.3 — Excepción capturada con OTHERS
-- ────────────────────────────────────────────────────────────
-- Igual que 7.2 pero captura con WHEN OTHERS.
-- Imprime SQLCODE y SQLERRM.
-- ────────────────────────────────────────────────────────────
DECLARE
  vlocalidad sedes.localidad%TYPE;
BEGIN
  -- TODO: SELECT localidad INTO vlocalidad FROM sedes WHERE depnu = 50
  -- TODO: Imprime 'Localidad: ' || vlocalidad
  NULL;
EXCEPTION
  WHEN OTHERS THEN
    -- TODO: Imprime 'Error código: ' || SQLCODE
    -- TODO: Imprime 'Error mensaje: ' || SQLERRM
    NULL;
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 7.4 — TOO_MANY_ROWS
-- ────────────────────────────────────────────────────────────
-- Busca el pedidonu de los pedidos del producto 20.
-- Como hay varios, saltará TOO_MANY_ROWS. Captúralo.
-- ────────────────────────────────────────────────────────────
DECLARE
  vpedidonu ventas.pedidonu%TYPE;
BEGIN
  -- TODO: SELECT pedidonu INTO vpedidonu FROM ventas WHERE productonu = 20
  -- TODO: Imprime 'Pedido: ' || vpedidonu
  NULL;
EXCEPTION
  -- TODO: WHEN TOO_MANY_ROWS THEN imprime mensaje sobre usar cursor
  WHEN OTHERS THEN NULL;
END;
/
