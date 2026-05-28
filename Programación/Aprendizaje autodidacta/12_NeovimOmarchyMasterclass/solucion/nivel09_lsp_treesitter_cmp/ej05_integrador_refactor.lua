-- ============================================================
--  EJERCICIO 09.05 — Integrador: rename + code action + format
--  Teoría:   teoria/09_Treesitter_LSP_Mason_Completion.md (TODA)
--  Verifica: bash scripts/verificar.sh 09 05
-- ============================================================
--
-- TODOS
--
--   TODO 1 (con pista): Renombra la función 'calcularPrecio' a 'calcular'
--     usando <leader>cr desde la definición. Esto cambia la definición Y las
--     2 invocaciones a la vez.
--
--   TODO 2 (con pista): Renombra la variable local 'descuento_var' a 'desc'
--     usando <leader>cr.
--
--   TODO 3 (con pista): Formatea TODO el archivo con <leader>cf (necesita
--     'stylua' instalado vía Mason). Verás que la indentación se ajusta a
--     un estilo consistente.
--
--   TODO 4 (LIBRE): En la función 'mensaje_a_completar', completa el TODO
--     que aparece como string: cambia "TODO: rellenar" por "Operación
--     completada". (Sin LSP, esto es edición normal — el LSP no tiene magia
--     para los strings.)
--
--   TODO 5 (LIBRE): Guarda y sal.

local function calcular(base, desc)
    return base - (base * desc)
end

local function mensaje_a_completar()
    return "Operación completada"
end

-- Invocaciones
local p1 = calcular(100, 0.1)
local p2 = calcular(50, 0.2)
print(p1)
print(p2)
print(mensaje_a_completar())
