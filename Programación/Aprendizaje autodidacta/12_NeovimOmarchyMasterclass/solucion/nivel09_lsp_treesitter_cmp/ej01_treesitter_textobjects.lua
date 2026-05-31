-- ============================================================
--  EJERCICIO 09.01 — Text objects sintácticos con Treesitter
--  Teoría:   teoria/09_Treesitter_LSP_Mason_Completion.md (sección 2)
--  Verifica: bash scripts/verificar.sh 09 01
-- ============================================================
--
-- CHULETA
--   af / if  → AROUND / INNER function
--   ac / ic  → AROUND / INNER class (en lenguajes que las tengan)
--   al / il  → AROUND / INNER loop
--   ai / ii  → AROUND / INNER conditional (if)
--   aa / ia  → AROUND / INNER argument
--
-- Pre-requisito: :TSInstall lua  (y que tengas el módulo textobjects activo)
--
-- TODOS
--   TODO 1 (con pista): Borra la función ENTERA "funcion_a_borrar" con 'daf'
--     desde cualquier línea dentro de su cuerpo.
--
--   TODO 2 (con pista): En "funcion_renombrar", cambia el cuerpo (entre los
--     paréntesis) por 'return x + 1' usando 'cif' (inner function).
--
--   TODO 3 (con pista): En "funcion_argumentos(a, b, c)", borra el argumento
--     "b" usando 'daa' (delete a argument) — debe quedar 'funcion_argumentos(a, c)'.
--
--   TODO 4 (LIBRE): Borra la función ENTERA "funcion_final_a_eliminar".
--
--   TODO 5 (LIBRE): Guarda y sal.

local function funcion_renombrar(x)
    return x + 1
end

local function funcion_argumentos(a, c)
    return a + b + c
end

local function funcion_que_se_queda()
    return "intacta"
end
