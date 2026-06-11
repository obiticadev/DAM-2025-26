#!/usr/bin/env bash
# Activa solo los bloques (módulos Maven) en los que estás trabajando, para que
# el language server de Java en VS Code no cargue los 26 módulos a la vez.
# Es la versión Linux/macOS de bloque.ps1 (misma lógica, mismo resultado).
#
# Uso:
#   ./bloque.sh b00          # trabajar solo en b00_http
#   ./bloque.sh b04 b05      # varios bloques a la vez
#   ./bloque.sh todos        # restaurar los 26 (build completo / commit)
#   ./bloque.sh              # ver qué bloques están activos ahora
#
# Después de ejecutarlo, en VS Code: Ctrl+Shift+P -> "Java: Reload Projects".

set -euo pipefail
cd "$(dirname "$0")"

# Todos los módulos disponibles = carpetas bNN_* con pom.xml propio
todos=$(for d in b[0-9][0-9]_*/; do
    [ -f "${d}pom.xml" ] && printf '%s\n' "${d%/}"
done | sort)

if [ $# -eq 0 ]; then
    echo "Bloques activos:"
    grep -o '<module>[^<]*</module>' pom.xml | sed 's/<[^>]*>//g; s/^/  - /'
    echo
    echo "Uso: ./bloque.sh b00 [b05 ...]   |   ./bloque.sh todos"
    exit 0
fi

if [ "$1" = "todos" ]; then
    seleccion="$todos"
else
    seleccion=""
    for b in "$@"; do
        match=$(printf '%s\n' "$todos" | grep "^${b}" || true)
        if [ -z "$match" ]; then
            echo "ERROR: no existe ningún bloque que empiece por '$b'." >&2
            echo "Disponibles:" >&2
            printf '%s\n' "$todos" | sed 's/^/  /' >&2
            exit 1
        fi
        seleccion="$seleccion$match"$'\n'
    done
    seleccion=$(printf '%s' "$seleccion" | sort -u)
fi

# Conserva el estilo de fin de línea del pom (CRLF si vino de Windows).
# La detección se hace con awk en modo binario: el grep de Git Bash
# recorta los \r al leer y daría siempre falso negativo.
cr=''
if [ "$(awk -v BINMODE=3 'NR==1 { print ($0 ~ /\r$/) ? "si" : "no"; exit }' pom.xml)" = "si" ]; then
    cr=$'\r'
fi

# Construye la nueva sección <modules> (indentación de 4/8 espacios como el pom)
nueva="    <modules>$cr"$'\n'
while IFS= read -r m; do
    [ -n "$m" ] && nueva="$nueva        <module>$m</module>$cr"$'\n'
done <<< "$seleccion"
nueva="$nueva    </modules>$cr"

# Sustituye el bloque <modules>...</modules> del pom.xml
awk -v BINMODE=3 -v repl="$nueva" '
    /<modules>/  { dentro = 1; print repl; next }
    /<\/modules>/ { dentro = 0; next }
    !dentro { print }
' pom.xml > pom.xml.tmp && mv pom.xml.tmp pom.xml

n=$(printf '%s\n' "$seleccion" | grep -c .)
echo "pom.xml actualizado. Bloques activos ($n):"
printf '%s\n' "$seleccion" | sed 's/^/  - /'
echo
echo "Ahora en VS Code: Ctrl+Shift+P -> 'Java: Reload Projects'"
if [ "$1" != "todos" ]; then
    echo "Antes de hacer commit o 'mvn test' global: ./bloque.sh todos"
fi
