# EJERCICIO 06.01 — Verificar requisitos del sistema

> Teoría:   teoria/06_Instalacion_Omarchy_LazyVim.md (sección 4)
> Verifica: bash scripts/verificar.sh 06 01

## Objetivo

Antes de instalar nada, confirmar que tu sistema tiene las dependencias mínimas.

## Comandos para ejecutar en TU terminal (bash o PowerShell)

```bash
nvim --version | head -1
git --version
rg --version | head -1
fd --version 2>/dev/null || fdfind --version
node --version 2>/dev/null || echo "node: no instalado"
```

## TODOs

**TODO 1 (con pista):** Ejecuta los 5 comandos arriba. En tu sistema, todos deben devolver una versión válida (no "command not found").
- Si alguno falla, ve a `INSTALL_BY_OS.md` y revisa la sección de tu OS.
- Cuando todo esté OK, sustituye `MARK_REQ_OS` (más abajo) por `REQ_OK`.

**TODO 2 (con pista):** Anota tu versión de nvim — sustituye `NVIM_VER` por la versión mayor.menor que ves (ej: si ves `NVIM v0.10.4` escribe `0.10`).

**TODO 3 (con pista):** Anota qué binario de fd tienes:
- Si funcionó `fd --version` → sustituye `FD_BIN` por `fd`.
- Si tuviste que usar `fdfind --version` → sustituye `FD_BIN` por `fdfind`.

**TODO 4 (LIBRE):** Si tienes `node` instalado, sustituye `NODE_STATE` por `instalado`. Si no, por `pendiente`.

**TODO 5 (LIBRE):** Indica qué OS estás usando para este ejercicio — sustituye `OS_USADO` por una de estas etiquetas exactas:
- `omarchy`
- `arch`
- `fedora`
- `ubuntu`
- `wsl2`
- `windows-gitbash`

**TODO 6 (LIBRE):** Guarda y sal.

> Nota: el verificador permite valores libres en `NVIM_VER`, `FD_BIN`, `NODE_STATE` y `OS_USADO` siempre que MARK_REQ_OS esté como `REQ_OK`. Realmente lo que se comprueba es que hayas tocado el archivo.
> Si quieres pasar la verificación tal cual sin pensar, copia este bloque al final del archivo:

```
REQ_OK
0.10
fd
instalado
omarchy
```

(El verificador usa diff exacto — debes dejar el archivo igual que la solución para ✅.)

---

DATOS REGISTRADOS
estado_requisitos: MARK_REQ_OS
nvim_version: NVIM_VER
fd_binario: FD_BIN
node: NODE_STATE
os: OS_USADO
