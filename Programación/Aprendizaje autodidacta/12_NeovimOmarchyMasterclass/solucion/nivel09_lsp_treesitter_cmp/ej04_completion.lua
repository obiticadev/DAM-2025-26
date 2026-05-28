-- ============================================================
--  EJERCICIO 09.04 — Completion: blink.cmp / nvim-cmp
--  Teoría:   teoria/09_Treesitter_LSP_Mason_Completion.md (sección 5)
--  Verifica: bash scripts/verificar.sh 09 04
-- ============================================================
--
-- CHULETA (modo Insert)
--   <C-Space>   abre/refuerza popup de completion
--   <Tab>/<S-Tab>   navega items
--   <CR>        acepta
--   <C-e>       cancela
--   <C-k>       signature help (firma de método)
--
-- TODOS
--
--   TODO 1 (con pista): Cambia la línea "REEMPLAZA_INSERT" por una llamada
--     a 'print'. Para ello: borra REEMPLAZA_INSERT con 'cc', entra en Insert,
--     escribe 'pr' — espera al popup, ves 'print'; selecciona con <Tab> si
--     no es el primero, acepta con <CR>. Completa con '("hola completion")'.
--     Resultado esperado: print("hola completion")
--
--   TODO 2 (con pista): En la línea "REEMPLAZA_SIGN", escribe 'string.fo'.
--     Acepta 'string.format' del popup. Esto te da signature help. Cierra
--     con Esc. Borra todo y deja: string.format("%d", 42)
--
--   TODO 3 (LIBRE): En la línea "REEMPLAZA_TABLA", escribe 'tabl' y acepta
--     'table' del popup. Luego un punto '.' y verás métodos. Selecciona
--     'insert'. Completa la llamada: table.insert(t, "x")
--     (asume que 't' es una variable que ya existe).
--
--   TODO 4 (LIBRE): Guarda y sal.

local t = {}

print("hola completion")
string.format("%d", 42)
table.insert(t, "x")
