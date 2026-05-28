-- ============================================================
--  EJERCICIO 10.03 — conform.nvim: formateo
--  Teoría:   teoria/10_Refactor_Mini_Conform_GrugFar.md (sección 5)
--  Verifica: bash scripts/verificar.sh 10 03
-- ============================================================
--
-- PRE-REQUISITO: tienes 'stylua' instalado vía Mason (Nivel 09).
--
-- CHULETA
--   <leader>cf   → formatea el buffer actual con conform
--   <leader>uf   → toggle format-on-save (LazyVim)
--   :ConformInfo → muestra qué formatters tienes para este buffer
--
-- TODOS
--
--   TODO 1 (con pista): Ejecuta :ConformInfo. Deberías ver 'stylua' listado
--     como formatter para Lua. Si no, vuelve al Nivel 09 e instálalo.
--
--   TODO 2 (con pista): Pulsa <leader>cf (format). Las líneas mal indentadas
--     y los espacios sobrantes desaparecen. La solución asume el output
--     ESTÁNDAR de stylua con indent de 4 espacios.
--
--   TODO 3 (LIBRE): Guarda y sal con :wq.

local function ChAOTic(   a,b ,   c)
return     a +b +c
end

local x={1,2 ,3,    4}

local function   otra(  )
   print("mal indentado")
        print( "espacios raros" )
return  nil
end

ChAOTic( 1 ,2,3)
