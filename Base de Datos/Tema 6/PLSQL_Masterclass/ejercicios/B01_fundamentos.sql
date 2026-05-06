-- ============================================================
-- 📝 BLOQUE 1 — Fundamentos: Variables, Tipos y Condicionales
-- ============================================================
-- Lee la teoría en: teoria/T01_fundamentos.md
-- Valida tus soluciones con: tests/T01_fundamentos.sql
-- ============================================================

SET SERVEROUTPUT ON;

-- ────────────────────────────────────────────────────────────
-- Ejercicio 1.1 — Variables numéricas y suma básica
-- ────────────────────────────────────────────────────────────
-- Crea un bloque que tenga una variable inicializada a 5 y
-- otra leída por teclado. Imprime la suma de ambas.
-- Formato de salida: "La suma es: X"
-- ────────────────────────────────────────────────────────────
DECLARE
  num1 NUMBER := 5;
  num2 NUMBER := &num2;
BEGIN
  -- TODO: Imprime la suma de num1 y num2 usando DBMS_OUTPUT.PUT_LINE
  -- PISTA: usa paréntesis alrededor de la suma para que || no interfiera
  NULL;
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 1.2 — Variables con límites y formato de salida
-- ────────────────────────────────────────────────────────────
-- Crea un bloque con num1 NUMBER(1) y num2 NUMBER(2), ambas
-- leídas por teclado.
-- Formato de salida: "La suma X + Y = Z"
-- ────────────────────────────────────────────────────────────
DECLARE
  num1 NUMBER(1) := &num1;
  num2 NUMBER(2) := &num2;
BEGIN
  -- TODO: Imprime con formato "La suma X + Y = Z"
  -- PISTA: concatena num1, ' + ', num2, ' = ' y la suma
  NULL;
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 1.3 — Variables mixtas, condicional IF
-- ────────────────────────────────────────────────────────────
-- Declara: num (NUMBER, teclado), nota (NUMBER, teclado),
-- nombre (VARCHAR2, valor fijo 'Andres'), apellido (VARCHAR2, teclado).
-- Si la nota > 5, imprime: "El alumno Andres Garcia ha aprobado"
-- ────────────────────────────────────────────────────────────
DECLARE
  num      NUMBER       := &num;
  nota     NUMBER       := &nota;
  nombre   VARCHAR2(10) := 'Andres';
  apellido VARCHAR2(20) := '&apellido';
BEGIN
  -- TODO: Usa un IF para comprobar si nota > 5
  -- Si es así, imprime el mensaje con nombre y apellido concatenados
  NULL;
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 1.4 — Boolean e IF/ELSE
-- ────────────────────────────────────────────────────────────
-- Lee una cifra y un "combo" (1=TRUE, 0=FALSE) por teclado.
-- Si combo = 1, imprime "Te ha tocado X" (X = cifra al cuadrado).
-- Si combo = 0, imprime "Te ha tocado Y" (Y = cifra original).
-- ────────────────────────────────────────────────────────────
DECLARE
  cifra NUMBER := &cifra;
  combo NUMBER := &combo;
BEGIN
  -- TODO: Usa IF/ELSE para comprobar el valor de combo
  -- Si combo = 1, usa POWER(cifra, 2) para elevar al cuadrado
  -- Si no, imprime la cifra directamente
  NULL;
END;
/
