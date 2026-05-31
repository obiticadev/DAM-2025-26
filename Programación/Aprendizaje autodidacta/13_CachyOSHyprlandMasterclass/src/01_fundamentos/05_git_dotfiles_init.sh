#!/usr/bin/env bash
# =============================================================================
# Ejercicio 05 — Git para dotfiles: init / audit / hardening de ~/.config
# -----------------------------------------------------------------------------
# Objetivo: tener un script idempotente que, ejecutado sobre cualquier
# máquina CachyOS, garantice que `~/.config` está bien versionado y
# conectado a tu remoto SSH `git@github.com:obiticadev/CachyOS.git`.
#
# Idempotente = puedes ejecutarlo 100 veces; si ya está todo bien, no
# hace nada y termina con exit 0. Si falta algo, lo configura y reporta.
#
# Referencia teórica: teoria/01_Fundamentos.md  §5
# =============================================================================

set -euo pipefail

CONFIG_DIR="${XDG_CONFIG_HOME:-$HOME/.config}"
REPO_URL_SSH="git@github.com:obiticadev/CachyOS.git"
EXPECTED_USER_NAME="obiticadev"
EXPECTED_USER_EMAIL="obitica.dev@gmail.com"
EXPECTED_DEFAULT_BRANCH="main"     # global default; tu repo actual está en master, no lo cambiamos

ok()    { printf '  \e[32m✓\e[0m %s\n' "$1"; }
warn()  { printf '  \e[33m!\e[0m %s\n' "$1"; }
err()   { printf '  \e[31m✗\e[0m %s\n' "$1" >&2; }


# -----------------------------------------------------------------------------
# TODO 01: Implementa `ensure_config_is_repo`
#   - Si "$CONFIG_DIR/.git" existe y es un repo válido (`git -C "$CONFIG_DIR"
#     rev-parse --is-inside-work-tree` da "true"), imprime con `ok` "ya es
#     repo" y devuelve 0.
#   - Si NO existe, imprime warning y NO inicializa automáticamente —
#     pide confirmación interactiva al usuario (read -r -p) antes de hacer
#     `git -C "$CONFIG_DIR" init -b main`. Esto es porque inicializar sobre
#     un ~/.config existente es semi-destructivo (un `git add -A` posterior
#     versionaría TODO sin filtrar). Si responde "no", aborta con err y
#     exit 1.
#   Por qué: este script lo correrás también en máquinas nuevas. No debe
#            jamás inicializar sin permiso explícito.
# -----------------------------------------------------------------------------

ensure_config_is_repo() {
    : # TODO 01 — implementa
}


# -----------------------------------------------------------------------------
# TODO 02: Implementa `ensure_git_identity`
#   - Lee `git config --global user.name` y `user.email`.
#   - Si ALGUNO está vacío o difiere del esperado, los setea (--global)
#     a $EXPECTED_USER_NAME y $EXPECTED_USER_EMAIL e imprime con `ok`.
#   - Si ya coinciden, imprime `ok "identidad git ya correcta"`.
#   - Adicionalmente, configura `init.defaultBranch` global a
#     $EXPECTED_DEFAULT_BRANCH si está vacío. NO cambies la rama del repo
#     actual ($CONFIG_DIR), solo el default global para FUTUROS repos.
#   Por qué: aliéniate con tu README — usuario y email deben ser estos.
# -----------------------------------------------------------------------------

ensure_git_identity() {
    : # TODO 02 — implementa
}


# -----------------------------------------------------------------------------
# TODO 03: Implementa `ensure_remote_origin`
#   - Si `git -C "$CONFIG_DIR" remote get-url origin` falla, añade el
#     remoto con `git -C "$CONFIG_DIR" remote add origin "$REPO_URL_SSH"`.
#   - Si existe pero apunta a una URL distinta (típico bug: clonaron por
#     https y luego no pueden hacer push), avisa con warn la URL actual
#     vs la esperada y NO la cambies automáticamente — pide confirmación
#     interactiva antes de hacer `set-url`.
#   - Si todo está bien, `ok`.
#   Por qué: una URL HTTPS sin token no permite push. Una SSH con tu key
#            ya cargada sí.
#   Verifica con: ssh -T git@github.com  (debe responder "Hi obiticadev!")
# -----------------------------------------------------------------------------

ensure_remote_origin() {
    : # TODO 03 — implementa
}


# -----------------------------------------------------------------------------
# TODO 04: Implementa `ensure_ssh_works`
#   - Ejecuta `ssh -T -o BatchMode=yes -o ConnectTimeout=5 git@github.com`.
#     GitHub responde código de salida 1 con mensaje "Hi <user>!" cuando
#     la autenticación va bien (sí, exit 1, no 0 — es así por diseño de
#     GitHub).
#   - Captura stderr. Si contiene "Hi $EXPECTED_USER_NAME!", reporta `ok`.
#   - Si NO, `err` con instrucción exacta para generar y subir la clave
#     (mismo texto que tu README, sección 2.3).
#   Por qué: detectar pronto que la clave SSH falta o caducó.
# -----------------------------------------------------------------------------

ensure_ssh_works() {
    : # TODO 04 — implementa
}


# -----------------------------------------------------------------------------
# TODO 05: Implementa `report_status`
#   Imprime un resumen final con:
#     - rama actual:         git -C "$CONFIG_DIR" symbolic-ref --short HEAD
#     - HEAD commit corto:   git -C "$CONFIG_DIR" rev-parse --short HEAD
#     - upstream tracking:   git -C "$CONFIG_DIR" rev-parse --abbrev-ref
#                            --symbolic-full-name '@{u}'  (o "(sin upstream)")
#     - commits ahead/behind respecto al upstream (si lo hay):
#         git -C "$CONFIG_DIR" rev-list --left-right --count HEAD...@{u}
#     - archivos modificados sin commitear: `git status --porcelain | wc -l`
#   Por qué: una sola línea de pulso para repetir cuando vuelvas tras
#            varios días.
# -----------------------------------------------------------------------------

report_status() {
    : # TODO 05 — implementa
}


# -----------------------------------------------------------------------------
# TODO 06: ORQUESTA EN main()
#   Llama, en este orden:
#       1. ensure_config_is_repo
#       2. ensure_git_identity
#       3. ensure_remote_origin
#       4. ensure_ssh_works
#       5. report_status
#   Si CUALQUIERA falla con exit != 0 (set -e ya está activo), el script
#   abortará. Eso está bien.
#   Por qué: cada paso depende del anterior; SSH no tiene sentido sin
#            remote configurado, etc.
# -----------------------------------------------------------------------------

main() {
    printf '\n== Git audit de %s ==\n\n' "$CONFIG_DIR"
    : # TODO 06 — llama las 5 funciones
}

main "$@"


# =============================================================================
# Zona de Ejecución Master
# -----------------------------------------------------------------------------
# Local (la mayoría de tests se skipean si no estás en CachyOS):
#     bats tests/01_fundamentos/05_git_dotfiles_init.bats
#
# En tu máquina real:
#     bash 13_CachyOSHyprlandMasterclass/src/01_fundamentos/05_git_dotfiles_init.sh
#
# Cuando todo pase verde, copia este script a:
#     ~/.config/scripts/dotfiles-audit.sh
# y añade un alias en fish:
#     alias dotaudit='bash ~/.config/scripts/dotfiles-audit.sh'
# =============================================================================
