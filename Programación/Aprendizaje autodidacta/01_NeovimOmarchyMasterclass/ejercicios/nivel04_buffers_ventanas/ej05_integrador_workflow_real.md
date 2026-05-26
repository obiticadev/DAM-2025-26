# EJERCICIO 04.05 — Integrador: workflow real con layout 2×2

> Teoría:   teoria/04_Buffers_Ventanas_Tabs.md (TODA)
> Verifica: bash scripts/verificar.sh 04 05

## Cómo arrancar

```bash
nvim ejercicios/nivel04_buffers_ventanas/ej05_integrador_workflow_real.md
```

## Objetivo

Recrear un layout 2×2 típico de trabajo:
```
+-----------+-----------+
| este .md  | docs.txt  |
+-----------+-----------+
| main.txt  | test.txt  |
+-----------+-----------+
```
Y editar cada panel.

## Chuleta combinada

```
:vsp ruta       split vertical con ruta
:sp  ruta       split horizontal
Ctrl-W h/j/k/l  navega
Ctrl-W =        iguala tamaños
Ctrl-W o        cierra todas menos esta
:wa             guarda todo
:qa             sale (avisa si hay sin guardar)
```

## TODOs

**TODO 1 (con pista):** Desde el layout inicial (una sola ventana con este archivo), construye el layout 2×2:
- Paso A: `:vsp ejercicios/nivel04_buffers_ventanas/ej05_docs.txt` (split vertical → ahora tienes izda y dcha).
- Paso B: vuelve a la izquierda con `Ctrl-W h`, y haz `:sp ejercicios/nivel04_buffers_ventanas/ej05_main.txt` (split horizontal → izda partida en dos).
- Paso C: salta a la dcha-arriba con `Ctrl-W l Ctrl-W k`, y haz `:sp ejercicios/nivel04_buffers_ventanas/ej05_test.txt` (split horizontal → dcha partida en dos).
- Al final tienes 4 paneles.

**TODO 2 (con pista):** Iguala tamaños con `Ctrl-W =`. Visualmente las 4 ventanas deben ser parecidas.

**TODO 3 (con pista):** En el panel `ej05_main.txt` (izda-abajo), sustituye `MAIN_RELLENO` por `MAIN_OK`. Guarda con `:w`.

**TODO 4 (con pista):** En `ej05_test.txt` (dcha-abajo) navega con Ctrl-W y sustituye `TEST_RELLENO` por `TEST_OK`. Guarda.

**TODO 5 (LIBRE):** En `ej05_docs.txt` (dcha-arriba) sustituye `DOCS_RELLENO` por `DOCS_OK`. Guarda.

**TODO 6 (LIBRE):** Vuelve a este `.md` (izda-arriba) y sustituye `INTEGRADOR_OK` (más abajo) por `BOOTCAMP_VIM_PURO_COMPLETADO`. Guarda TODOS los buffers con `:wa`.

**TODO 7 (LIBRE):** Cierra todo con `:qa`.

---

INTEGRADOR_OK
