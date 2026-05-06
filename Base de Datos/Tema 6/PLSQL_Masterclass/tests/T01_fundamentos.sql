-- ============================================================
-- ✅ TEST BLOQUE 1 — Fundamentos: Variables, Tipos y Condicionales
-- ============================================================
-- Ejecuta este script DESPUÉS de haber completado B01_fundamentos.sql
-- Los tests de este bloque son manuales (salida visual) porque
-- los ejercicios usan variables de sustitución (&).
-- Verifica la salida de cada bloque contra los resultados esperados.
-- ============================================================

SET SERVEROUTPUT ON;

-- ────────────────────────────────────────────────────────────
-- TEST 1.1 — Verificación manual
-- ────────────────────────────────────────────────────────────
-- Ejecuta B01 Ejercicio 1.1 con num2 = 3
-- Resultado esperado: "La suma es: 8"

-- ────────────────────────────────────────────────────────────
-- TEST 1.2 — Verificación manual
-- ────────────────────────────────────────────────────────────
-- Ejecuta B01 Ejercicio 1.2 con num1=7, num2=25
-- Resultado esperado: "La suma 7 + 25 = 32"

-- ────────────────────────────────────────────────────────────
-- TEST 1.3 — Verificación manual
-- ────────────────────────────────────────────────────────────
-- Ejecuta B01 Ejercicio 1.3 con nota=7, apellido=Garcia
-- Resultado esperado: "El alumno Andres Garcia ha aprobado"

-- ────────────────────────────────────────────────────────────
-- TEST 1.4 — Verificación manual
-- ────────────────────────────────────────────────────────────
-- Ejecuta B01 Ejercicio 1.4 con cifra=4, combo=1
-- Resultado esperado: "Te ha tocado 16"
-- Ejecuta otra vez con cifra=4, combo=0
-- Resultado esperado: "Te ha tocado 4"

BEGIN
  DBMS_OUTPUT.PUT_LINE('============================================');
  DBMS_OUTPUT.PUT_LINE('TEST BLOQUE 1: Verificación manual');
  DBMS_OUTPUT.PUT_LINE('============================================');
  DBMS_OUTPUT.PUT_LINE('Los ejercicios de este bloque usan &variable');
  DBMS_OUTPUT.PUT_LINE('por lo que debes verificar la salida tú mismo.');
  DBMS_OUTPUT.PUT_LINE('');
  DBMS_OUTPUT.PUT_LINE('Ej 1.1 (num2=3)         → "La suma es: 8"');
  DBMS_OUTPUT.PUT_LINE('Ej 1.2 (n1=7,n2=25)     → "La suma 7 + 25 = 32"');
  DBMS_OUTPUT.PUT_LINE('Ej 1.3 (nota=7,ap=Ga..) → "El alumno Andres Garcia ha aprobado"');
  DBMS_OUTPUT.PUT_LINE('Ej 1.4 (cifra=4,combo=1)→ "Te ha tocado 16"');
  DBMS_OUTPUT.PUT_LINE('Ej 1.4 (cifra=4,combo=0)→ "Te ha tocado 4"');
  DBMS_OUTPUT.PUT_LINE('============================================');
END;
/
