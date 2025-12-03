# 📦 Cheatsheet: Gestión de Software (APT y DPKG)

## 1. Conceptos Clave
*   **APT (Advanced Packaging Tool):** Herramienta avanzada para gestionar paquetes. Resuelve dependencias automáticamente.
*   **DPKG:** El gestor de paquetes base de Debian. Instala archivos `.deb` pero **no** descarga dependencias.
*   **Repositorios:** Almacenes remotos desde donde se descargan los paquetes, definidos en `/etc/apt/sources.list`.

---

## 2. Comandos Básicos con `apt`
El comando moderno recomendado para el usuario final.

### 🔄 Actualización del Sistema
| Acción | Comando | Descripción |
| :--- | :--- | :--- |
| **Actualizar lista** | `sudo apt update` | Descarga la lista más reciente de paquetes disponibles desde los repositorios. |
| **Actualizar todo** | `sudo apt upgrade` | Actualiza todos los paquetes instalados y sus dependencias necesarias. |
| **Actualizar uno** | `sudo apt upgrade paquete` | Actualiza únicamente un paquete específico. |

### 📥 Instalación y Eliminación
| Acción | Comando | Descripción |
| :--- | :--- | :--- |
| **Instalar** | `sudo apt install paquete` | Descarga e instala el paquete y sus dependencias. |
| **Solo descargar** | `sudo apt-get install -d paq` | Descarga el paquete en `/var/cache/apt/archives/` sin instalarlo. |
| **Eliminar** | `sudo apt remove paquete` | Desinstala el programa, pero **mantiene** los archivos de configuración. |
| **Purgar** | `sudo apt purge paquete` | Desinstala el programa y **borra** también la configuración. |

### 🔍 Búsqueda y Consultas
| Acción | Comando | Descripción |
| :--- | :--- | :--- |
| **Buscar** | `sudo apt search término` | Busca paquetes relacionados con el nombre o término. |
| **Buscar (desc)**| `sudo apt-cache search "txt"`| Busca en las descripciones de los paquetes. |
| **Listar** | `sudo apt list` | Lista todos los paquetes (instalados y disponibles). |
| **Listar inst.** | `sudo apt list --installed` | Muestra solo los paquetes instalados. |
| **Versiones** | `sudo apt policy paquete` | Muestra la versión instalada y la candidata a instalar. |

---

## 3. Gestión de Repositorios
Añadir o quitar fuentes de software externas (PPAs o repositorios oficiales).

**Añadir repositorio estándar:**
```bash
sudo add-apt-repository "deb http://archive.canonical.com/ precise partner"
```

**Añadir repositorio PPA (Personal Package Archive):**
```bash
sudo add-apt-repository ppa:Nombre_Del_Repositorio
```

**Eliminar repositorio:**
Se usa el flag `-r`.
```bash
sudo add-apt-repository -r "deb http://..."
```

---

## 4. El comando `dpkg` (Paquetes locales .deb)
Se utiliza para instalar archivos que ya has descargado manualmente (extensión `.deb`).
⚠️ **Nota:** `dpkg` no descarga dependencias. Si faltan, dará error.

| Acción | Comando | Detalles |
| :--- | :--- | :--- |
| **Instalar** | `sudo dpkg -i paquete.deb` | Instala un archivo `.deb` local. |
| **Desinstalar**| `sudo dpkg -r paquete` | Elimina el paquete instalado. |
| **Purgar** | `sudo dpkg -P paquete` | Elimina el paquete y su configuración. |
| **Verificar** | `dpkg -l | grep nombre` | Comprueba si un paquete está instalado. |

---

## 5. `apt` vs `apt-get`
Aunque son similares, existen diferencias sutiles en la gestión de actualizaciones:

*   **`apt-get upgrade`**: Actualiza los paquetes instalados, pero **no** instala dependencias nuevas si estas han cambiado.
*   **`apt upgrade`**: Actualiza los paquetes e **instala** las dependencias nuevas si son necesarias para la actualización.

---

## 📂 Directorios Importantes
*   `/etc/apt/sources.list`: Fichero principal donde se listan los repositorios.
*   `/var/cache/apt/archives/`: Directorio donde se guardan los archivos `.deb` descargados por apt antes de instalarse.