-- ============================================================
-- 📝 BLOQUE 2 — Estructuras de Control: Bucles
-- ============================================================
-- Lee la teoría en: teoria/T02_bucles.md
-- Valida tus soluciones con: tests/T02_bucles.sql
-- ============================================================

SET SERVEROUTPUT ON;

-- ────────────────────────────────────────────────────────────
-- Ejercicio 2.0 — Suma acumulativa con LOOP (parada condicional)
-- ────────────────────────────────────────────────────────────
-- Suma los números 1, 2, 3, 4... usando LOOP.
-- El bucle termina cuando la suma supere 10.
-- Imprime la suma final.
-- Resultado esperado: "La suma es: 15"
-- ────────────────────────────────────────────────────────────
DECLARE
  i    NUMBER := 1;
  suma NUMBER := 0;
BEGIN
  LOOP
    -- TODO: Acumula i en suma
    -- TODO: Sal del bucle cuando suma > 10 (EXIT WHEN)
    -- TODO: Incrementa i
    NULL;
    EXIT WHEN TRUE; -- Quita esta línea cuando implementes la lógica
  END LOOP;
  DBMS_OUTPUT.PUT_LINE('La suma es: ' || suma);
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 2.1 — Suma del 1 al 10 con FOR
-- ────────────────────────────────────────────────────────────
-- Usa un FOR para sumar todos los números del 1 al 10.
-- Resultado esperado: "Suma del 1 al 10: 55"
-- ────────────────────────────────────────────────────────────
DECLARE
  suma NUMBER := 0;
BEGIN
  -- TODO: Bucle FOR de 1 a 10, acumula i en suma
  NULL;
  DBMS_OUTPUT.PUT_LINE('Suma del 1 al 10: ' || suma);
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 2.2 — Suma del 1 al 10 con WHILE
-- ────────────────────────────────────────────────────────────
-- Haz lo mismo que el 2.1 pero con WHILE.
-- Resultado esperado: "Suma del 1 al 10: 55"
-- ────────────────────────────────────────────────────────────
DECLARE
  i    NUMBER := 1;
  suma NUMBER := 0;
BEGIN
  -- TODO: Bucle WHILE (i <= 10), acumula i en suma, incrementa i
  NULL;
  DBMS_OUTPUT.PUT_LINE('Suma del 1 al 10: ' || suma);
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 2.3 — Mayor de tres números
-- ────────────────────────────────────────────────────────────
-- Lee tres números por teclado y determina cuál es el mayor.
-- Usa IF / ELSIF / ELSE con condiciones AND.
-- Formato: "El mayor es: X"
-- ────────────────────────────────────────────────────────────
DECLARE
  n1    NUMBER := &n1;
  n2    NUMBER := &n2;
  n3    NUMBER := &n3;
  mayor NUMBER;
BEGIN
  -- TODO: Usa IF/ELSIF/ELSE para determinar el mayor de n1, n2, n3
  -- PISTA: cada rama debe comprobar que el candidato es >= los otros dos
  mayor := 0; -- Quita esta línea cuando implementes
  DBMS_OUTPUT.PUT_LINE('El mayor es: ' || mayor);
END;
/

-- ────────────────────────────────────────────────────────────
-- Ejercicio 2.4 — Distancia entre dos puntos
-- ────────────────────────────────────────────────────────────
-- Lee x1, y1, x2, y2 por teclado.
-- Calcula la distancia euclidiana: d = SQRT((x2-x1)^2 + (y2-y1)^2)
-- Usa POWER y SQRT.
-- Resultado esperado (0,0,3,4): "Distancia entre los puntos: 5"
-- ────────────────────────────────────────────────────────────
DECLARE
  x1        NUMBER := &x1;
  y1        NUMBER := &y1;
  x2        NUMBER := &x2;
  y2        NUMBER := &y2;
  distancia NUMBER;
BEGIN
  -- TODO: Calcula la distancia usando SQRT y POWER
  distancia := 0; -- Quita esta línea cuando implementes
  DBMS_OUTPUT.PUT_LINE('Distancia entre los puntos: ' || distancia);
END;
/
