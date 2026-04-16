# 🐚 Cheatsheet: Introducción a Shellscript (Completo)

## 1. Conceptos Básicos
**Shell de Linux:** Interfaz que permite al usuario comunicarse con el sistema operativo mediante comandos de texto.

**Funciones principales:**
* 📂 Navegar y manipular el sistema de archivos.
* ⚙️ Administrar procesos.
* 🔧 Configurar el sistema.
* 🚀 Ejecutar programas y scripts.

**¿Qué es un Script?**
Es un fichero de texto que contiene órdenes y estructuras de control (condicionales, bucles) para **automatizar tareas** de administración del sistema.

---

## 2. La Extensión `.sh`
* **Convencionalismo:** Se suele usar `.sh` para identificar visualmente que es un script.
* **Realidad:** **No es necesario**. El sistema no requiere ninguna extensión específica para ejecutar el archivo.

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
````

-----

## 4\. El Shebang (`#!`)

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

-----

## 5\. Variables

En Shellscript las variables **no son tipadas** (no necesitas declarar si es int, string, etc.).

### Declaración y Uso

  * **Asignar:** No se deben poner espacios alrededor del igual `=`.
  * **Invocar:** Se utiliza el símbolo `$` delante del nombre.

<!-- end list -->

```bash
nombre="Juan"       # Correcto
edad=25             # Correcto
nombre = "Juan"     # ❌ Error: no dejar espacios
```

Para mostrar el valor:

```bash
echo "Hola, me llamo $nombre y tengo $edad años"
```

### Variables de Entorno (Sistema)

Son variables predefinidas por el sistema operativo que contienen información útil.

| Variable | Descripción |
| :--- | :--- |
| `$HOME` | Ruta del directorio personal del usuario actual (ej. `/home/alumno`). |
| `$PATH` | Lista de directorios donde el sistema busca los comandos ejecutables. |
| `$USER` | Nombre del usuario que ejecuta el script. |
| `$PWD` | Directorio actual de trabajo (*Print Working Directory*). |

-----

## 6\. Sustitución de Comandos y Fechas

Podemos guardar el resultado de un comando dentro de una variable o usarlo directamente en una cadena usando `$(comando)`.

### Sintaxis

```bash
# Forma moderna (recomendada)
variable=$(comando)

# Ejemplo con 'date'
hoy=$(date)
echo "Hoy es: $hoy"
```

### Formateo de Fecha y Hora

El comando `date` permite extraer partes específicas (hora, minutos, segundos) usando `+%formato`.

```bash
hora=$(date +%H)
minuto=$(date +%M)
segundo=$(date +%S)

echo "Son las $hora horas con $minuto minutos."
```

  * `%H`: Hora (00-23)
  * %M\`: Minuto (00-59)
  * `%S`: Segundo (00-59)
  * `%d-%m-%Y`: Día-Mes-Año

-----

## 7\. Interacción con el Usuario (`read`)

Permite pausar el script y pedir un dato al usuario.

### Método 1: `echo` + `read`

Primero imprimes el mensaje y luego lees la variable.

```bash
echo "Introduce tu nombre:"
read nombre
echo "Hola $nombre"
```

### Método 2: `read -p` (Prompt)

Más compacto. Imprime el mensaje y espera la entrada en la misma línea.

```bash
read -p "Introduce tu edad: " edad
echo "Tienes $edad años."
```

-----

## 8\. Estructuras de Control: `if`

Permite tomar decisiones basadas en si una condición es verdadera o falsa.

**Sintaxis básica:**

> ⚠️ **Importante:** Los espacios dentro de los corchetes `[ ]` son obligatorios.

```bash
if [ condición ]; then
    # Comandos si es verdad
else
    # Comandos si es falso
fi
```

### Comparadores Numéricos

Se utilizan letras en lugar de símbolos matemáticos clásicos dentro del `[]`.

| Comparador | Significado | Mnemotecnia (Inglés) |
| :--- | :--- | :--- |
| `-eq` | Igual a | **Eq**ual |
| `-ne` | No igual a (distinto) | **N**ot **E**qual |
| `-gt` | Mayor que | **G**reater **T**han |
| `-lt` | Menor que | **L**ess **T**han |
| `-ge` | Mayor o igual que | **G**reater or **E**qual |
| `-le` | Menor o igual que | **L**ess or **E**qual |

**Ejemplo:**

```bash
read -p "Dime un número: " num

if [ $num -gt 10 ]; then
    echo "El número es mayor que 10"
else
    echo "El número es 10 o menos"
fi
```

-----

## 9\. Ejemplos Prácticos Típicos

### A. Crear directorio para el usuario actual

Script que verifica si existe un directorio con tu nombre de usuario y si no, lo crea.

```bash
#!/bin/bash

# Usamos la variable de entorno $USER
directorio=$USER

if [ -d "$directorio" ]; then
    echo "El directorio $directorio ya existe."
else
    mkdir "$directorio"
    echo "Directorio $directorio creado exitosamente."
fi
```

### B. Script de saludo según la hora

Usa la sustitución de comandos para decidir cómo saludar.

```bash
#!/bin/bash

hora=$(date +%H)

if [ $hora -lt 12 ]; then
    echo "¡Buenos días!"
elif [ $hora -lt 20 ]; then
    echo "¡Buenas tardes!"
else
    echo "¡Buenas noches!"
fi
```