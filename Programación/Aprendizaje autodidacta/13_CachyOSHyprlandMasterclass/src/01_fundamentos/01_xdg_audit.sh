#!/usr/bin/env bash
# =============================================================================
# Ejercicio 01 — Auditoría XDG sobre tu máquina CachyOS
# -----------------------------------------------------------------------------
# Objetivo: que termines este script y, al ejecutarlo, te imprima un informe
# de tu jerarquía XDG real: qué variables están seteadas, qué hay en cada
# raíz, si ~/.config es un repo git limpio, y qué archivos "legacy" tienes
# directamente en $HOME que deberían estar bajo ~/.config.
#
# No introduce todavía nada de Hyprland — es el primer escalón: saber qué
# tienes y dónde antes de tocarlo.
#
# Referencia teórica: teoria/01_Fundamentos.md  §1
# =============================================================================

set -euo pipefail

print_header() {
    printf '\n=== %s ===\n' "$1"
}

# -----------------------------------------------------------------------------
# TODO 01: Imprime el valor (o "(default)" si no está exportada) de las
#          cuatro variables XDG base de usuario:
#              XDG_CONFIG_HOME, XDG_DATA_HOME, XDG_STATE_HOME, XDG_CACHE_HOME
#          Cuando no estén seteadas, muestra entre paréntesis su default
#          canónico (~/.config, ~/.local/share, ~/.local/state, ~/.cache).
#          Implementa una función `xdg_var <NOMBRE> <DEFAULT>` y úsala 4 veces.
#   Por qué: necesitas saber si tu sistema está en defaults o tienes overrides.
#   Verifica con: ejecutar este script y leer la salida.
# -----------------------------------------------------------------------------

print_header "Variables XDG"
# (implementa aquí xdg_var y las cuatro llamadas)


# -----------------------------------------------------------------------------
# TODO 02: Lista los directorios top-level de ~/.config ordenados
#          alfabéticamente, uno por línea, con un asterisco delante de los
#          que NO estén versionados (no aparecen en `git ls-files --directory`
#          de ese repo). Si ~/.config no es un repo git, marca todo con `?`.
#   Por qué: te da un mapa visual de qué módulos ya gestionas y cuáles vives
#            a ciegas.
# -----------------------------------------------------------------------------

print_header "Contenido de ~/.config (* = no versionado)"
# (implementa)


# -----------------------------------------------------------------------------
# TODO 03: Detecta si ~/.config tiene un repo git. Si lo tiene, imprime:
#            - rama actual
#            - SHA corto del HEAD
#            - número de archivos modificados sin commitear
#            - URL del remoto `origin` (o "(sin remoto)")
#          Si NO es repo, imprime un warning rojo "WARN: ~/.config no está
#          versionado — corrige con el ejercicio 05".
#   Por qué: confirmar que tu safety net está activa antes de tocar nada.
#   Verifica con: cd ~/.config && git status
# -----------------------------------------------------------------------------

print_header "Estado git de ~/.config"
# (implementa)


# -----------------------------------------------------------------------------
# TODO 04: Busca archivos "legacy" en $HOME (no en subdirectorios) que
#          deberían vivir bajo ~/.config. Escanea estos patrones y reporta
#          los que existan:
#              ~/.bashrc        (puede estar bien si tu shell es bash)
#              ~/.zshrc         (idem para zsh)
#              ~/.gitconfig     (algunos prefieren ~/.config/git/config)
#              ~/.vimrc         (mejor ~/.config/nvim/ o ~/.config/vim/)
#              ~/.tmux.conf     (mejor ~/.config/tmux/tmux.conf)
#              ~/.npmrc, ~/.gemrc, ~/.condarc, ~/.cargo (parcial)
#          Para cada uno que exista, imprime su ruta y un sugerimiento de
#          ruta XDG-friendly equivalente. NO lo muevas, solo informa.
#   Por qué: muchos legacy aún funcionan, pero conviene saber dónde estás
#            fuera del estándar.
# -----------------------------------------------------------------------------

print_header "Archivos legacy en \$HOME"
# (implementa)


# -----------------------------------------------------------------------------
# TODO 05: Imprime un resumen final con totales:
#            - nº de directorios en ~/.config
#            - nº de archivos versionados en ~/.config (git ls-files | wc -l)
#            - tamaño total de ~/.cache (du -sh) — solo informativo
#            - cuántos legacy hits encontró el TODO 04
#   Por qué: una sola línea de "pulso del sistema" para repetir mes a mes.
# -----------------------------------------------------------------------------

print_header "Resumen"
# (implementa)


# =============================================================================
# Zona de Ejecución Master
# -----------------------------------------------------------------------------
# Ejecuta con:
#     chmod +x ~/.config/scripts/xdg_audit.sh 2>/dev/null || true
#     bash 13_CachyOSHyprlandMasterclass/src/01_fundamentos/01_xdg_audit.sh
#
# Cuando el script te guste, plantéate copiarlo a:
#     ~/.config/scripts/xdg_audit.sh
# y añadirlo a `fish/config.fish` como alias `audit`, para ejecutarlo
# rápido cuando vuelvas a una máquina nueva.
# =============================================================================
