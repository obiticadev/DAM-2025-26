# EJERCICIO 08.03 — Navegación rápida entre buffers

> Teoría:   teoria/08_File_Explorer_Y_Buffers.md (sección 3)
> Verifica: bash scripts/verificar.sh 08 03

## Procedimiento

Abre 4 buffers de una vez para tener material con el que practicar:

```bash
cd "01_NeovimOmarchyMasterclass"
nvim ejercicios/nivel08_explorer_buffers/ej03_buffer_nav.md \
     ejercicios/nivel08_explorer_buffers/ej03_pista_alpha.txt \
     ejercicios/nivel08_explorer_buffers/ej03_pista_bravo.txt \
     ejercicios/nivel08_explorer_buffers/ej03_pista_charlie.txt
```

## TODOs

**TODO 1 (con pista):** Estás en el primer buffer (este `.md`). Pulsa `<S-l>` (Shift+L) para ir al siguiente buffer (`ej03_pista_alpha.txt`). Sustituye dentro de él `RELLENO_ALPHA` por `OK_ALPHA`. Guarda con `:w`.

**TODO 2 (con pista):** Pulsa `<S-l>` otra vez para `ej03_pista_bravo.txt`. Sustituye `RELLENO_BRAVO` por `OK_BRAVO`. Guarda.

**TODO 3 (con pista):** Pulsa `<S-l>` para `ej03_pista_charlie.txt`. Sustituye `RELLENO_CHARLIE` por `OK_CHARLIE`. Guarda.

**TODO 4 (LIBRE):** Vuelve a este `.md` con `<leader>,` (switcher visual con picker) o pulsando `<S-h>` tres veces. Sustituye `VOLVI_AL_MD` por `OK`.

**TODO 5 (LIBRE):** Cierra los 3 buffers de pistas (alpha/bravo/charlie) usando `<leader>bd` desde cada uno (o `<leader>bo` para cerrar todos menos este). Sustituye `BUFFERS_CERRADOS` por `OK`.

**TODO 6 (LIBRE):** Guarda y sal con `:wa` + `:q`.

---

BUFFER_NAV
volvi: OK
cerrados: OK
