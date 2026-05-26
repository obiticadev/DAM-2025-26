# EJERCICIO 06.02 — Instalar LazyVim (y opcionalmente la opinión Omarchy)

> Teoría:   teoria/06_Instalacion_Omarchy_LazyVim.md (sección 4)
> Verifica: bash scripts/verificar.sh 06 02

## Instrucciones según tu OS

Sigue UNO de los siguientes flujos según tu situación:

### Caso A — Ya tengo Omarchy en este equipo
```bash
nvim --headless "+Lazy! sync" +qa
```
Estás listo. No hace falta clonar nada.

### Caso B — Tengo Linux/Mac/WSL pero no Omarchy
```bash
# 1) Mueve tu config previa si existe
mv ~/.config/nvim ~/.config/nvim.backup 2>/dev/null

# 2) Clona el starter de LazyVim
git clone https://github.com/LazyVim/starter ~/.config/nvim
rm -rf ~/.config/nvim/.git

# 3) (Opcional) Encima, copia la opinión de Omarchy
git clone https://github.com/basecamp/omarchy /tmp/omarchy-src
cp -r /tmp/omarchy-src/default/neovim/* ~/.config/nvim/

# 4) Sincroniza plugins
nvim --headless "+Lazy! sync" +qa
```

### Caso C — Windows nativo (Git Bash o PowerShell)
```bash
# Git Bash:
mv "$LOCALAPPDATA/nvim" "$LOCALAPPDATA/nvim.backup" 2>/dev/null
git clone https://github.com/LazyVim/starter "$LOCALAPPDATA/nvim"
rm -rf "$LOCALAPPDATA/nvim/.git"
nvim --headless "+Lazy! sync" +qa
```

## TODOs

**TODO 1 (con pista):** Ejecuta el flujo correspondiente a tu caso. Cuando `nvim --headless "+Lazy! sync" +qa` termine SIN errores rojos, sustituye `LAZY_SYNC_STATE` por `OK`.

**TODO 2 (con pista):** Indica qué flujo seguiste — sustituye `FLUJO_ELEGIDO` por `A`, `B` o `C` según corresponda.

**TODO 3 (con pista):** Anota la ruta donde quedó tu config. Sustituye `CONFIG_PATH`:
- Si fue caso A o B → `~/.config/nvim`
- Si fue caso C → `%LOCALAPPDATA%/nvim`

**TODO 4 (LIBRE):** Abre `nvim` por PRIMERA vez de forma interactiva. Verás el dashboard de LazyVim. Cuando todo cargue (puede tardar 10-30s la primera vez), sal con `:q`. Si todo fue bien, sustituye `PRIMER_ARRANQUE` por `OK`.

**TODO 5 (LIBRE):** Guarda y sal.

> Si quieres pasar la verificación copiando los valores esperados, deja el bloque final exactamente como en la solución.

---

INSTALACION
lazy_sync: OK
flujo: A
config_en: ~/.config/nvim
primer_arranque: OK
