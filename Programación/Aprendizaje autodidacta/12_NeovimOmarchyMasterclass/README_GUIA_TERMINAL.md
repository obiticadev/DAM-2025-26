# 🧪 Neovim + Omarchy Masterclass — Guía Terminal
**Bootcamp Autodidacta · 70 Ejercicios · Editor Neovim · Ecosistema LazyVim/Omarchy · Proyecto final en Java + JUnit**

> Pasarás de no saber salir de `vim` a desarrollar, depurar y commitear un proyecto Maven multinivel
> usando **únicamente el teclado**, con el ecosistema de plugins que monta Omarchy de DHH.

---

## 📋 Requisitos Previos

| Herramienta | Versión mínima | Verificar |
|---|---|---|
| Neovim | ≥ 0.10 | `nvim --version` |
| git | cualquiera reciente | `git --version` |
| ripgrep (`rg`) | ≥ 13 | `rg --version` |
| fd (`fd` o `fdfind`) | ≥ 8 | `fd --version` |
| nodejs | ≥ 18 (para algunos LSPs) | `node --version` |
| JDK (a partir del Nivel 13) | 21 o 25 | `java -version` |
| Maven (a partir del Nivel 13) | ≥ 3.9 | `mvn -version` |
| bash o PowerShell (verificador) | — | `bash --version` o `pwsh --version` |

📖 **Las instrucciones de instalación detalladas por sistema operativo están en
[`INSTALL_BY_OS.md`](INSTALL_BY_OS.md)** — cubre Omarchy real, Arch puro, Fedora, Ubuntu/Debian, WSL2 y Git Bash en Windows.

---

## 🏗️ Estructura del Proyecto

```
01_NeovimOmarchyMasterclass/
│
├── README_GUIA_TERMINAL.md       ← estás aquí
├── INSTALL_BY_OS.md              ← receta por sistema operativo
├── implementation_plan.md        ← sílabo: 14 niveles × ~5 ejercicios = 70
│
├── teoria/                       ← 15 archivos .md (00–14)
│   └── NN_Tema.md                ← teoría narrativa + Mermaid + comandos bash de instalación
│
├── ejercicios/                   ← archivos abribles con `nvim`
│   └── nivelNN_tema/
│       └── ejMM_titulo.{txt,md,java}
│
├── solucion/                     ← espejo de ejercicios con estado final esperado
│   └── nivelNN_tema/
│       └── ejMM_titulo.{txt,md,java}
│
└── scripts/
    ├── verificar.sh              ← bash (Linux/WSL/Git Bash)
    ├── verificar.ps1             ← PowerShell (Windows nativo)
    └── reset_ej.sh               ← restaura un ejercicio a su estado original
```

---

## 🎯 Flujo de Trabajo Recomendado

Para **cada ejercicio**, repite este ciclo:

1. **Lee la teoría del nivel** antes de empezar:
   ```bash
   # Por ejemplo, antes del Nivel 01:
   nvim teoria/01_Modos_Y_Navegacion.md
   # o abre el .md con tu visor preferido
   ```

2. **Abre el ejercicio en Neovim** (el editor que estás aprendiendo):
   ```bash
   nvim ejercicios/nivel01_movimiento/ej01_navegacion.txt
   ```

3. **Lee la cabecera del ejercicio**: contiene una **chuleta de comandos** sin spoilers para que no tengas que alternar ventanas.

4. **Resuelve los TODOs en orden**:
   - Los primeros llevan una **pista** corta (qué comando usar).
   - Los últimos son **zona libre**: solo especificación, sin pista.

5. **Verifica con el script**:
   ```bash
   # En Linux/WSL/Git Bash:
   bash scripts/verificar.sh 01 01
   # En Windows PowerShell:
   pwsh scripts/verificar.ps1 01 01
   ```
   - ✅ verde → ejercicio completado, pasa al siguiente.
   - ❌ rojo → revisa el `diff` que aparece y vuelve a intentarlo.

6. **Si rompes el ejercicio sin remedio**, restaura el archivo original:
   ```bash
   bash scripts/reset_ej.sh 01 01
   ```

7. **Para el Nivel 14 (Boss Final, proyecto Maven)** la verificación cambia a:
   ```bash
   cd ejercicios/nivel14_boss_final
   mvn clean test
   ```
   Todos los tests en verde = bootcamp completado.

---

## ▶️ Ejecutar el bootcamp

### Primera vez (cualquier OS)
```bash
# 1. Verifica que todo está instalado (ver INSTALL_BY_OS.md)
nvim --version && rg --version && fd --version

# 2. Abre el archivo de introducción
nvim teoria/00_Por_Que_Vim_Y_Omarchy.md

# 3. Cuando termines de leerlo, lanza el primer ejercicio
nvim ejercicios/nivel00_introduccion/ej01_abrir_y_salir.txt
```

### Día normal de trabajo
```bash
# Reanuda donde lo dejaste (los ejercicios están numerados)
nvim ejercicios/nivelNN_*/ejMM_*.{txt,md,java}

# Cuando creas que está, verifica
bash scripts/verificar.sh NN MM
```

---

## ⚠️ Reglas del Bootcamp

> [!CAUTION]
> **PROHIBIDO usar el ratón.** Toda navegación, edición y manejo de plugins debe hacerse con teclado.
>
> **PROHIBIDO copiar/pegar la solución desde `solucion/`.** Está ahí para que el verificador haga `diff`, no para que la mires antes de intentar el ejercicio.
>
> **A partir del Nivel 06, PROHIBIDO modificar la config de LazyVim/Omarchy** salvo donde el ejercicio lo pida explícitamente. El objetivo de este bootcamp es **dominar la opinión por defecto**, no inventar la tuya.

> [!IMPORTANT]
> Trabaja los ejercicios en orden. Cada bloque construye sobre el anterior y los ejercicios "libres" del final
> de cada nivel asumen que dominas los anteriores.

---

## 🔍 Diagnóstico Rápido

| Problema | Solución |
|---|---|
| `nvim` abre pero no encuentra archivos | Ejecuta `:checkhealth` dentro de nvim para ver qué falta |
| Plugins no se cargan en el Nivel 06+ | `:Lazy sync` y luego reinicia |
| LSP de Java no arranca (Nivel 13+) | `:Mason` → instala `jdtls`, `java-debug-adapter`, `java-test`; comprueba `JAVA_HOME` |
| `verificar.sh` dice "command not found" en Windows | Usa `pwsh scripts/verificar.ps1` o instala Git Bash |
| Atascado en un ejercicio | Lee la teoría del bloque, mira en `:help <comando>` dentro de nvim |
| He destrozado un ejercicio | `bash scripts/reset_ej.sh NN MM` (te lo deja como al principio) |
| ¿Cómo salgo de nvim? | `:q` (sin guardar), `:wq` (guarda y sale), `:q!` (fuerza sin guardar), `ZZ`, `ZQ` — todo esto se aprende en el Nivel 00 |

---

## 📚 Referencias

- [LazyVim docs](https://www.lazyvim.org) — config base
- [Omarchy manual](https://learn.omacom.io) — distribución de DHH
- [Neovim docs](https://neovim.io/doc/) — `:help` dentro del editor
- [vimtutor](https://vimhelp.org/usr_01.txt.html) — tutorial oficial de Vim (`vimtutor` en terminal)

---

## 🚦 Próximo paso

Si acabas de clonar/abrir este bootcamp por primera vez:

```bash
# Lee la guía de instalación de TU sistema operativo
nvim INSTALL_BY_OS.md

# Luego abre el sílabo completo para tener el mapa mental
nvim implementation_plan.md

# Y finalmente arranca con la introducción
nvim teoria/00_Por_Que_Vim_Y_Omarchy.md
```

¡Bienvenido al teclado!
