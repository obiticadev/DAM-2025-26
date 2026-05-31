# EJERCICIO 04.02 — Splits y navegación entre ventanas

> Teoría:   teoria/04_Buffers_Ventanas_Tabs.md (sección 3)
> Verifica: bash scripts/verificar.sh 04 02

## Cómo arrancar

```bash
nvim ejercicios/nivel04_buffers_ventanas/ej02_splits_horizontales_y_verticales.md
```

## Chuleta

```
:vsp ruta       split vertical con ruta
:sp  ruta       split horizontal
Ctrl-W h/j/k/l  navega entre ventanas (igual que hjkl)
Ctrl-W q        cierra la ventana actual
Ctrl-W o        deja SOLO la ventana actual (cierra las demás)
Ctrl-W =        iguala tamaños
Ctrl-W H/J/K/L  MUEVE la ventana entera
:wa             guarda todos los buffers modificados
```

## TODOs

**TODO 1 (con pista):** Abre este archivo en split VERTICAL junto a `ej02_panel_derecho.txt` (que ya existe).
Pista: ejecuta `:vsp ejercicios/nivel04_buffers_ventanas/ej02_panel_derecho.txt`. Para autocompletar rutas usa `<Tab>` después de escribir parte del nombre.

**TODO 2 (con pista):** Salta a la ventana derecha con `Ctrl-W l`. Estás ahora en `ej02_panel_derecho.txt`. Sustituye `RELLENO_DCHA` por `LISTO_DCHA`. Guarda con `:w`.

**TODO 3 (con pista):** Sin salir, abre OTRA ventana DEBAJO (split horizontal) con `ej02_panel_abajo.txt`.
Pista: `:sp ejercicios/nivel04_buffers_ventanas/ej02_panel_abajo.txt`.

**TODO 4 (LIBRE):** Ahora debes tener un layout 3-paneles: izda (este .md), arriba-dcha (ej02_panel_derecho), abajo-dcha (ej02_panel_abajo). En el panel inferior, sustituye `RELLENO_ABAJO` por `LISTO_ABAJO`. Guarda.

**TODO 5 (LIBRE):** Vuelve a la ventana de la izquierda (este archivo) navegando con `Ctrl-W h`. Sustituye `MARK_IZDA` (más abajo) por `OK_IZDA`. Guarda todos los buffers con `:wa`.

**TODO 6 (LIBRE):** Cierra todas las demás ventanas con `Ctrl-W o` (deja solo este). Sal con `:qa`.

---

MARK_IZDA
