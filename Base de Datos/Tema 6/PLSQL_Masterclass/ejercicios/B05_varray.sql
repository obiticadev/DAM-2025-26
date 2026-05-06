-- ============================================================
-- 📝 BLOQUE 5 — Tipos Compuestos: Arrays (VARRAY)
-- ============================================================
-- Lee la teoría en: teoria/T05_varray.md
-- Valida tus soluciones con: tests/T05_varray.sql
-- ============================================================

SET SERVEROUTPUT ON;

-- ────────────────────────────────────────────────────────────
-- Ejercicio 5.1 — VARRAY de cadenas con métodos básicos
-- ────────────────────────────────────────────────────────────
-- Crea un TYPE VARRAY(4) OF VARCHAR2(30).
-- Inicialízalo con 3 valores: 'Hola', 'Mundo', 'PL/SQL'.
-- Imprime FIRST, COUNT, LAST, LIMIT y el elemento en posición 3.
-- ────────────────────────────────────────────────────────────
DECLARE
  TYPE tacad IS VARRAY(4) OF VARCHAR2(30);
  vtacad tacad := tacad('Hola', 'Mundo', 'PL/SQL');
BEGIN
  -- TODO: Imprime vtacad.FIRST, vtacad.COUNT, vtacad.LAST, vtacad.LIMIT
  -- TODO: Imprime 'Posición 3: ' || vtacad(3)
  NULL;
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 5.2 — VARRAY de registros TPERSONA
-- ────────────────────────────────────────────────────────────
-- Crea TYPE tpersona IS RECORD (edad NUMBER, nombre VARCHAR2(30)).
-- Crea TYPE tapersona IS VARRAY(3) OF tpersona.
-- Inicializa vacío, extiende 3 posiciones.
-- Asigna: (1,'ANA'), (2,'ANDRES'), (edad y nombre por teclado).
-- Recorre con FOR e imprime todos los nombres.
-- ────────────────────────────────────────────────────────────
DECLARE
  TYPE tpersona IS RECORD (edad NUMBER, nombre VARCHAR2(30));
  TYPE tapersona IS VARRAY(3) OF tpersona;
  vtapersona tapersona := tapersona();
BEGIN
  -- TODO: EXTEND(3) para reservar posiciones
  -- TODO: Asigna edad y nombre para las posiciones 1, 2 y 3
  -- TODO: Recorre con FOR desde FIRST hasta LAST e imprime cada nombre
  NULL;
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 5.3 — VARRAY numérico rellenado con bucle
-- ────────────────────────────────────────────────────────────
-- Crea TYPE VARRAY(4) OF NUMBER, inicializa con 4 ceros.
-- Usa un FOR para asignar i*3 a cada posición.
-- Imprime FIRST, COUNT, LAST, LIMIT, posición 3.
-- Luego imprime todo el contenido con un segundo FOR.
-- ────────────────────────────────────────────────────────────
DECLARE
  TYPE tavnum IS VARRAY(4) OF NUMBER;
  vtavnum tavnum := tavnum(0, 0, 0, 0);
BEGIN
  -- TODO: FOR de 1 a 4, asigna vtavnum(i) := i * 3
  -- TODO: Imprime los métodos FIRST, COUNT, LAST, LIMIT
  -- TODO: Imprime 'Posición 3: ' || vtavnum(3)
  -- TODO: Imprime '--- Contenido completo ---'
  -- TODO: FOR de FIRST a LAST, imprime cada posición y su valor
  NULL;
END;
/
