-- ============================================================
--  EJERCICIO 10.02 — mini.ai: text objects extendidos
--  Teoría:   teoria/10_Refactor_Mini_Conform_GrugFar.md (sección 3)
--  Verifica: bash scripts/verificar.sh 10 02
-- ============================================================
--
-- CHULETA
--   ia / aa  → ARGUMENT (entre comas dentro de paréntesis)
--   if / af  → FUNCTION
--   ic / ac  → CLASS
--   iI / aI  → INDENT (bloque con la misma indentación)
--
-- TODOS
--
--   TODO 1 (con pista): En la línea con f1, borra el ARGUMENTO 'segundo'
--     con daa (delete a argument) — incluye la coma. Resultado:
--     local x = f1(primero, tercero)
--
--   TODO 2 (con pista): En la línea con f2, cambia el ARGUMENTO 'a_borrar'
--     por 'mejor' con cia. Resultado:
--     local y = f2(mejor, otro)
--
--   TODO 3 (LIBRE): Borra el bloque INDENTADO marcado con BORRAR_INDENT
--     usando daI (delete around indent). Quedan tanto la línea anterior
--     como la posterior intactas.
--
--   TODO 4 (LIBRE): Guarda y sal.

local function f1(primero, segundo, tercero)
    return primero + segundo + tercero
end

local function f2(a_borrar, otro)
    return otro
end

local x = f1(primero, tercero)
local y = f2(mejor, otro)

if true then
end

print("se mantiene")
