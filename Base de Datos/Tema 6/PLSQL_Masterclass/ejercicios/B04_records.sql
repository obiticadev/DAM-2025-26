-- ============================================================
-- 📝 BLOQUE 4 — Tipos Compuestos: Registros (RECORD)
-- ============================================================
-- Lee la teoría en: teoria/T04_records.md
-- Valida tus soluciones con: tests/T04_records.sql
-- ============================================================

SET SERVEROUTPUT ON;

-- ────────────────────────────────────────────────────────────
-- Ejercicio 4.1 — Tipo registro con INSERT en tabla
-- ────────────────────────────────────────────────────────────
-- Define un TYPE tipopro IS RECORD con 4 campos que coincidan
-- con items (NUMBER, VARCHAR2(40), NUMBER(6,2), NUMBER).
-- Asigna valores: 100, 'tablon', 234.56, 34.
-- Imprímelos separados por " | " y luego insértalos en items.
-- ────────────────────────────────────────────────────────────
DECLARE
  TYPE tipopro IS RECORD (
    campo1 NUMBER,
    campo2 VARCHAR2(40),
    campo3 NUMBER(6,2),
    campo4 NUMBER
  );
  vtipopro tipopro;
BEGIN
  -- TODO: Asigna los 4 valores al registro campo a campo
  -- TODO: Imprime los 4 campos separados por ' | '
  -- TODO: INSERT INTO items (productonu, nombre, precio, stock)
  --       VALUES (vtipopro.campo1, vtipopro.campo2, vtipopro.campo3, vtipopro.campo4)
  -- TODO: Imprime 'Fila insertada correctamente.'
  NULL;
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 4.2.1 — SELECT INTO sobre variable registro
-- ────────────────────────────────────────────────────────────
-- Usando el mismo TYPE tipopro, carga el producto 30 con
-- SELECT INTO y luego imprime los 4 campos.
-- ────────────────────────────────────────────────────────────
DECLARE
  TYPE tipopro IS RECORD (
    campo1 NUMBER,
    campo2 VARCHAR2(40),
    campo3 NUMBER(6,2),
    campo4 NUMBER
  );
  vtipopro tipopro;
BEGIN
  -- TODO: SELECT productonu, nombre, precio, stock INTO vtipopro
  --       FROM items WHERE productonu = 30
  -- TODO: Imprime "Producto: campo1 | campo2 | campo3 | campo4"
  NULL;
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 4.2.2 — DELETE usando registro pedido por teclado
-- ────────────────────────────────────────────────────────────
-- Pide el productonu por teclado, guárdalo en campo1 del registro,
-- y haz un DELETE usando ese valor. Imprime SQL%ROWCOUNT.
-- ────────────────────────────────────────────────────────────
DECLARE
  TYPE tipopro IS RECORD (
    campo1 NUMBER,
    campo2 VARCHAR2(40),
    campo3 NUMBER(6,2),
    campo4 NUMBER
  );
  vtipopro tipopro;
BEGIN
  vtipopro.campo1 := &productonu;
  -- TODO: DELETE FROM items WHERE productonu = vtipopro.campo1
  -- TODO: Imprime 'Filas borradas: ' || SQL%ROWCOUNT
  NULL;
END;
/
