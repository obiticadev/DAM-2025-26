# 🧰 Instalación por sistema operativo

Este bootcamp está diseñado para funcionar en **5 entornos**. Localiza el tuyo y ejecuta SU sección.

> [!NOTE]
> Hasta el **Nivel 05** solo necesitas `neovim` instalado — todo es Vim puro.
> A partir del **Nivel 06** necesitas además: `git`, `ripgrep`, `fd`, y la **config de LazyVim/Omarchy**.
> A partir del **Nivel 13** necesitas además: `jdk-21` (o 25), `maven`, y un `jdtls` instalable vía `:Mason`.

---

## Índice

- [1. Omarchy real (Arch + Hyprland + LazyVim)](#1-omarchy-real)
- [2. Arch Linux puro (sin Hyprland)](#2-arch-linux-puro)
- [3. Fedora](#3-fedora)
- [4. Ubuntu / Debian](#4-ubuntu--debian)
- [5. WSL2 en Windows](#5-wsl2-en-windows)
- [6. Git Bash en Windows nativo](#6-git-bash-en-windows-nativo)
- [Apéndice — verificar la instalación](#apéndice--verificar-la-instalación)

---

## 1. Omarchy real

Si ya estás en Omarchy, lo tienes prácticamente todo. Solo verifica:

```bash
# Verificación básica
nvim --version | head -1            # debería decir NVIM v0.10+
rg --version | head -1
fd --version

# Lanza nvim y deja que Lazy sincronice todo
nvim --headless "+Lazy! sync" +qa

# Para los niveles de Java (13-14)
sudo pacman -S --needed jdk-openjdk maven
java -version
mvn -version
```

> **Atajos Omarchy útiles**:
> - `Super + Shift + N` abre nvim en el directorio actual desde Hyprland
> - `n` en una terminal abre nvim en el cwd
> - Para edición con sudo: `sudoedit /ruta/protegida`

---

## 2. Arch Linux puro

```bash
sudo pacman -S --needed neovim git ripgrep fd nodejs npm unzip

# Clona el starter de LazyVim
git clone https://github.com/LazyVim/starter ~/.config/nvim
rm -rf ~/.config/nvim/.git

# (Opcional) si quieres la config opinada de Omarchy encima:
# git clone https://github.com/basecamp/omarchy ~/.omarchy-source
# cp -r ~/.omarchy-source/default/neovim/* ~/.config/nvim/

# Sincroniza plugins
nvim --headless "+Lazy! sync" +qa

# Para Java (Niveles 13-14)
sudo pacman -S --needed jdk-openjdk maven
```

---

## 3. Fedora

```bash
# Neovim 0.10+ está en repos oficiales de Fedora 40+
sudo dnf install -y neovim git ripgrep fd-find nodejs npm unzip

# Clona el starter de LazyVim
git clone https://github.com/LazyVim/starter ~/.config/nvim
rm -rf ~/.config/nvim/.git

# Sincroniza plugins
nvim --headless "+Lazy! sync" +qa

# Para Java (Niveles 13-14)
sudo dnf install -y java-21-openjdk-devel maven
java -version
mvn -version

# 'fd' en Fedora se instala como 'fd-find' pero el binario sigue siendo 'fd'.
# Si tu PATH no lo encuentra:
which fd || sudo ln -s "$(command -v fdfind)" /usr/local/bin/fd
```

---

## 4. Ubuntu / Debian

> [!WARNING]
> La versión de `neovim` en `apt` de Ubuntu suele ser **antigua**. Necesitas ≥ 0.10.
> Usa el PPA oficial o el AppImage.

### Opción A — AppImage (recomendado, sin sudo)

```bash
mkdir -p ~/.local/bin
curl -L https://github.com/neovim/neovim/releases/latest/download/nvim-linux-x86_64.appimage \
     -o ~/.local/bin/nvim
chmod u+x ~/.local/bin/nvim
echo 'export PATH="$HOME/.local/bin:$PATH"' >> ~/.bashrc
source ~/.bashrc

# Dependencias del bootcamp
sudo apt update
sudo apt install -y git ripgrep fd-find nodejs npm unzip

# 'fd-find' en Ubuntu instala el binario como 'fdfind'. Crea alias:
mkdir -p ~/.local/bin && ln -sf "$(command -v fdfind)" ~/.local/bin/fd
```

### Opción B — PPA Unstable (con sudo)

```bash
sudo add-apt-repository -y ppa:neovim-ppa/unstable
sudo apt update && sudo apt install -y neovim git ripgrep fd-find nodejs npm unzip
```

### Resto común

```bash
git clone https://github.com/LazyVim/starter ~/.config/nvim
rm -rf ~/.config/nvim/.git
nvim --headless "+Lazy! sync" +qa

# Para Java (Niveles 13-14)
sudo apt install -y openjdk-21-jdk maven
```

---

## 5. WSL2 en Windows

WSL2 corre Ubuntu (u otra distro) dentro de Windows. Sigue la receta de **tu distro WSL** (Ubuntu en la mayoría de casos → ver §4), y añade lo siguiente para integrar bien el clipboard:

```bash
# Permite que yank/paste de Neovim use el portapapeles de Windows
sudo apt install -y wl-clipboard xclip 2>/dev/null || true

# Instala win32yank.exe para integración del clipboard W↔WSL
curl -sLo /tmp/win32yank.zip https://github.com/equalsraf/win32yank/releases/latest/download/win32yank-x64.zip
unzip -p /tmp/win32yank.zip win32yank.exe > ~/.local/bin/win32yank.exe
chmod +x ~/.local/bin/win32yank.exe
```

Si tu portapapeles no funciona dentro de nvim, dentro del editor ejecuta `:checkhealth provider` para diagnosticar.

> [!TIP]
> Trabaja siempre dentro del filesystem de WSL (`/home/usuario/...`) y NO en `/mnt/c/...`,
> el rendimiento de Neovim cae drásticamente sobre el filesystem montado.

---

## 6. Git Bash en Windows nativo

```bash
# Desde una terminal PowerShell elevada (admin):
winget install --id Neovim.Neovim
winget install --id BurntSushi.ripgrep.MSVC
winget install --id sharkdp.fd
winget install --id Git.Git
winget install --id OpenJS.NodeJS.LTS
# Para Java (niveles 13-14)
winget install --id EclipseAdoptium.Temurin.21.JDK
winget install --id Apache.Maven

# Reinicia Git Bash y verifica
nvim --version
rg --version
fd --version
java -version
mvn -version
```

```bash
# Dentro de Git Bash, clona el starter de LazyVim
git clone https://github.com/LazyVim/starter "$LOCALAPPDATA/nvim"
rm -rf "$LOCALAPPDATA/nvim/.git"

# Sincroniza plugins
nvim --headless "+Lazy! sync" +qa
```

> [!WARNING]
> En Windows nativo algunos plugins esperan binarios POSIX (`make`, `gcc`).
> Si en el Nivel 09 (Treesitter) ves errores, considera instalar [`zig`](https://ziglang.org/) o
> ejecutar el bootcamp dentro de WSL2 a partir de ese nivel.

---

## Apéndice — verificar la instalación

Ejecuta esto en cualquier OS y deberías ver todo en verde:

```bash
echo "=== Versiones ==="
nvim --version | head -1
git --version
rg --version | head -1
fd --version 2>/dev/null || fdfind --version
node --version 2>/dev/null || echo "node: no instalado (necesario desde nivel 09)"
java -version 2>&1 | head -1 || echo "java: no instalado (necesario desde nivel 13)"
mvn -version 2>/dev/null | head -1 || echo "mvn: no instalado (necesario desde nivel 13)"

echo ""
echo "=== Plugins LazyVim ==="
nvim --headless "+checkhealth lazy" +qa 2>&1 | tail -20

echo ""
echo "=== Listo para Niveles 00-05 si nvim funciona ==="
echo "=== Listo para Niveles 06-12 si Lazy reporta plugins instalados ==="
echo "=== Listo para Niveles 13-14 si java y mvn están instalados ==="
```

Guarda esto como `scripts/check_install.sh` si quieres re-ejecutarlo cuando cambies de equipo.

---

## Cambios entre equipos

Como trabajas en **varios sistemas** (Omarchy, Fedora, Ubuntu, WSL2, Windows), guarda este patrón mental:

```
¿Donde está la config de Neovim?
├─ Linux/Mac/WSL  → ~/.config/nvim/
└─ Windows nativo → %LOCALAPPDATA%\nvim\

¿Dónde están los plugins instalados (lazy)?
├─ Linux/Mac/WSL  → ~/.local/share/nvim/lazy/
└─ Windows nativo → %LOCALAPPDATA%\nvim-data\lazy\

¿Dónde están los binarios de Mason (LSPs, formatters)?
├─ Linux/Mac/WSL  → ~/.local/share/nvim/mason/
└─ Windows nativo → %LOCALAPPDATA%\nvim-data\mason\
```

Si quieres sincronizar la config entre máquinas, considera ponerla en un repo git aparte y clonarla.
