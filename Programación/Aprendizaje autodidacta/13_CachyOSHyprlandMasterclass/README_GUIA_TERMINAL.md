# Masterclass CachyOS + Hyprland — Guía Terminal

Bootcamp autodidacta para dominar la configuración de **CachyOS con Hyprland**, su
ecosistema (waybar, hyprlock, hypridle, hyprpaper, mako, wofi, kitty, fish,
starship…) y el versionado de tus dotfiles con git.

No es un copia-pega: cada ejercicio te pide aplicar una configuración **real**
sobre tu propia máquina y la valida con tests `bats-core`.

---

## 1. Filosofía de la masterclass

- **`teoria/`** — Referencia exhaustiva. Cada bloque cubre las directivas
  involucradas: todos los parámetros, sus tipos, defaults, rangos, ejemplos
  mínimos / realistas / extremos, interacciones con otras directivas y
  gotchas reales. Pensado para releer en seis meses.
- **`src/`** — Plantillas con `# TODO:` numerados. **No vienen resueltas**.
  Cada TODO te dice exactamente qué decisión tomar sobre tu setup real
  (tus monitores `DP-1` + `HDMI-A-1`, tu input method `fcitx5`, tu shell
  `fish`, etc.) y qué comando ejecutar para verificar. Al completar el
  archivo, lo copias / enlazas a `~/.config/...` para que tu sistema lo use.
- **`tests/`** — Suites `bats-core` que validan que tu archivo de ejercicio
  cumple las invariantes del enunciado. El ejercicio se considera **completo
  solo cuando todos los tests pasan a verde**.

---

## 2. Requisitos en tu máquina CachyOS

Esta masterclass se ejecuta **en la máquina real donde corre Hyprland**, no
en el equipo de desarrollo donde edites este repo. Algunos tests llaman a
`hyprctl`, `git`, `pacman`, etc.

```fish
# Paquetes base
sudo pacman -S --needed bats bats-assert bats-support bats-file \
    git jq shellcheck
```

> `bats-assert` y `bats-support` viven en repos AUR si pacman no los
> encuentra: `paru -S bats-assert bats-support bats-file`.

Verifica:

```fish
bats --version          # >= 1.10
git --version           # >= 2.40
jq --version
hyprctl version         # debes estar dentro de una sesión Hyprland
```

---

## 3. Estructura del proyecto

```
13_CachyOSHyprlandMasterclass/
├── README_GUIA_TERMINAL.md
├── teoria/
│   ├── 01_Fundamentos.md
│   ├── 02_Core_Hyprland.md
│   └── ...
├── src/
│   ├── 01_fundamentos/
│   │   ├── 01_xdg_audit.sh
│   │   ├── 02_hyprlang_sintaxis.conf
│   │   └── ...
│   └── ...
└── tests/
    ├── test_helper.bash
    └── 01_fundamentos/
        ├── 01_xdg_audit.bats
        └── ...
```

---

## 4. Flujo de un ejercicio (loop maestro)

1. **Lee** la sección de teoría del bloque (`teoria/0X_*.md`).
2. **Abre** el archivo del ejercicio en `src/0X_*/NN_*.{conf,sh,...}`.
3. **Resuelve cada `# TODO:`** editando el archivo. No te saltes ninguno
   — el orden está pensado para que cada paso construya sobre el anterior.
4. **Lanza los tests** del ejercicio:
   ```fish
   bats tests/01_fundamentos/01_xdg_audit.bats
   ```
5. **Aplica el resultado a tu sistema real** siguiendo la "Zona de Ejecución
   Master" al final del archivo (`hyprctl reload`, `source` desde
   `hyprland.conf`, copiar a `~/.config/...`, etc.).
6. **Commit** en `~/.config` con un mensaje semántico (lo aprenderás en el
   ejercicio 07).

---

## 5. Ejecutar la suite de tests

Desde la raíz del proyecto:

```fish
# Un ejercicio concreto
bats tests/01_fundamentos/02_hyprlang_sintaxis.bats

# Un bloque entero
bats tests/01_fundamentos/

# Todo el bootcamp (cuando esté completo)
bats -r tests/
```

Salida esperada de un test verde:

```
1..5
ok 1 archivo existe
ok 2 contiene directiva monitor para DP-1
ok 3 resolución y refresh válidos
ok 4 posición sin solapamientos
ok 5 hyprctl reload no genera errores
```

> Los tests que necesiten `hyprctl` (sesión Hyprland activa) se
> **skipean automáticamente** si no detectan `$HYPRLAND_INSTANCE_SIGNATURE`.
> Los tests "puramente de archivo" corren en cualquier sitio.

---

## 6. Convenciones de los TODOs

```conf
# TODO 01: <ACCIÓN OPERATIVA CONCRETA>
#   Por qué: <razón en una línea>
#   Verifica con: <comando exacto que demuestra que funciona>
```

Si un TODO menciona valores concretos de tu hardware (ej. `DP-1`,
`2560x1080@144`), es porque esos son los datos reales detectados en tu
repo `obiticadev/CachyOS`. Cámbialos solo si tu hardware cambia.

---

## 7. Cómo NO romperte el sistema

Antes de aplicar un cambio a `~/.config/hypr/hyprland.conf`:

```fish
cd ~/.config
git status                          # asegúrate de tener todo limpio
git commit -am "checkpoint pre-cambio"
```

Si el cambio rompe la sesión (pantalla negra, sesión que no entra):

1. Cambia a un TTY libre: `Ctrl+Alt+F3`.
2. Loguéate.
3. Restaura:
   ```fish
   cd ~/.config
   git reset --hard HEAD~1
   ```
4. Vuelve a `Ctrl+Alt+F1` (o donde esté tu display manager) y reintenta.

El **Bloque IX** profundiza en estrategias de recovery, ramas
experimentales y hooks pre-commit que validan la config antes de
commitear.

---

## 8. Cómo está numerado todo

- **Bloques**: `01_fundamentos`, `02_core_hyprland`, … `10_boss_final`.
- **Ejercicios**: `NN_nombre.ext` donde `NN` es global (01-70), no
  reinicia por bloque. Esto facilita localizar el ejercicio 47 sin
  recordar a qué bloque pertenece.
- **TODOs**: numerados dentro de cada archivo (`TODO 01`, `TODO 02`, …).

---

## 9. Boss Final (Ejercicio 70)

El último ejercicio reconstruye **toda tu configuración desde un `$HOME`
vacío** en una jaula de sandbox: monitores reales, fcitx5, waybar, wofi,
mako, hyprlock, hypridle, hyprpaper, kitty/fish/starship/fastfetch,
theming GTK, portales, autostart y git inicializado. Cuarenta y pico
invariantes auditadas con bats. Si pasa, sabes que podrías replicar tu
desktop en una máquina nueva sin tirar de IA.
