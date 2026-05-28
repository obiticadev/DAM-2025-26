-- ============================================================
--  EJERCICIO 09.03 — LSP básico: hover, definición, references
--  Teoría:   teoria/09_Treesitter_LSP_Mason_Completion.md (sección 3)
--  Verifica: bash scripts/verificar.sh 09 03
-- ============================================================
--
-- PRE-REQUISITO: tienes 'lua_ls' instalado vía Mason.
--
-- CHULETA LSP
--   K              hover (doc del símbolo)
--   gd             go to definition
--   gr             references (lista todas las llamadas)
--   gi             go to implementation
--   <leader>cr     rename símbolo
--   <leader>ca     code actions
--   <leader>cf     format
--   <leader>cd     diagnostic de la línea
--   ]d / [d        siguiente / anterior diagnostic
--   :LspInfo       info de los LSPs activos
--
-- TODOS
--
--   TODO 1 (con pista): Ponte sobre la palabra 'print' (línea de abajo) y
--     pulsa K. Aparece la doc en un popup flotante. Pulsa Esc o K otra vez
--     para cerrar. Después sustituye HOVER_PRINT por OK (más abajo).
--
--   TODO 2 (con pista): Ponte sobre 'multiplicar' en la línea que la INVOCA
--     (al final del archivo) y pulsa gd. Saltas a la línea donde está
--     DEFINIDA. Bien. Vuelve con Ctrl-o (jump list back).
--     Sustituye GD_OK por OK.
--
--   TODO 3 (con pista): Ponte sobre 'sumar' en su definición y pulsa gr.
--     Aparece una lista de referencias. Hay AL MENOS 2 (la definición y
--     una invocación). Pulsa Esc.
--     Sustituye GR_OK por OK.
--
--   TODO 4 (LIBRE): El siguiente código tiene un ERROR a propósito en una
--     línea. Encuéntralo navegando con ]d (siguiente diagnostic) o mira el
--     borde derecho buscando el ⚠️ rojo. Identifica el nombre de la
--     variable mal escrita y sustituye 'undefinedaVariable' por 'x'
--     (debe quedar 'return x * 2').
--
--   TODO 5 (LIBRE): Renombra la función 'sumar' a 'add' usando <leader>cr
--     desde la definición. Esto cambia la definición Y la invocación al
--     final. Guarda y sal.

local function add(a, b)
    return a + b
end

local function multiplicar(a, b)
    return a * b
end

local function con_error(x)
    return x * 2
end

-- Invocaciones
print("Hola mundo")
print(add(2, 3))
print(multiplicar(4, 5))
print(con_error(7))

-- Registros (no toques esta sección al editar)
-- HOVER_PRINT_marker: OK
-- GD_marker: OK
-- GR_marker: OK
