# 🐚 Cheatsheet: Introducción a Shellscript

## 1. Conceptos Básicos
**Shell de Linux:** Interfaz que permite al usuario comunicarse con el sistema operativo mediante comandos de texto.

**Funciones principales:**
*   📂 Navegar y manipular el sistema de archivos.
*   ⚙️ Administrar procesos.
*   🔧 Configurar el sistema.
*   🚀 Ejecutar programas y scripts.

**¿Qué es un Script?**
Es un fichero de texto que contiene órdenes y estructuras de control (condicionales, bucles) para **automatizar tareas** de administración del sistema.

---

## 2. La Extensión `.sh`
*   **Convencionalismo:** Se suele usar `.sh` para identificar visualmente que es un script.
*   **Realidad:** **No es necesario**. El sistema no requiere ninguna extensión específica para ejecutar el archivo.

---

## 3. Ejecución de Scripts
Existen dos métodos principales para ejecutar un script:

### A. Ejecución directa (con permisos)
Requiere asignar permisos de ejecución previamente.

1.  **Dar permisos:**
    ```bash
    $ chmod a+x holamundo.sh
    ```
2.  **Ejecutar:**
    ```bash
    $ ./holamundo.sh
    ```

### B. Invocar mediante intérprete (sin permisos)
Si el script no tiene permiso de ejecución, se pasa como argumento al comando de la shell.

```bash
$ bash un_script
# o bien
$ sh un_script
```

---

## 4. El Shebang (`#!`)
Es la secuencia de caracteres que aparece en la **primera línea** del script. Indica la ruta absoluta del intérprete que debe usar el sistema.

**Sintaxis:**
```bash
#!/bin/bash   <-- Para usar bash
#!/bin/sh     <-- Para usar sh
```

### ¿Qué pasa si NO uso Shebang?
El comportamiento depende de cómo se invoque:

| Contexto | Comportamiento |
| :--- | :--- |
| **Ejecución directa en shell abierta** | El sistema usará la **shell actual** del usuario (ej. si estás en `bash`, usa `bash`). |
| **Ejecución desde el entorno** | El sistema puede usar la **shell predeterminada** (usualmente `bash`, pero en sistemas antiguos podría ser `sh` u otra). |

> ✅ **Mejor Práctica:** Siempre incluir el shebang (`#!/bin/bash`) en la primera línea para evitar ambigüedades y asegurar la compatibilidad.

---

## 5. Comandos de utilidad

**Verificar shells instaladas**
Para saber qué intérpretes tienes disponibles en tu sistema:

```bash
$ cat /etc/shells
```
*(Ejemplo de salida: `/bin/sh`, `/bin/bash`, `/bin/dash`, etc.)*