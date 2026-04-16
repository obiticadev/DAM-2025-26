# Guía Rápida de Comandos Git

Esta es una chuleta con los comandos esenciales de Git para el día a día.

## ⚙️ Configuración Inicial
*Se configuran una sola vez en tu sistema.*

**Define tu nombre de usuario:**
Sirve para identificar quién realiza cada cambio.
```bash
git config --global user.name "Tu Nombre"
```

**Define tu email:**
Asociado a tus commits, visible en plataformas como GitHub.
```bash
git config --global user.email "tu@email.com"
```

## 🚀 Iniciar un Repositorio

**Crear un nuevo repositorio local:**
Convierte la carpeta actual en un repositorio de Git.
```bash
git init
```

**Clonar un repositorio remoto:**
Descarga una copia completa de un repositorio existente desde una URL.
```bash
git clone https://github.com/usuario/repositorio.git
```

## 🔄 Flujo de Trabajo Básico (El día a día)
*Estos son los comandos que usarás constantemente.*

**1. Ver el estado de tus archivos (`status`):**
Muestra qué archivos han sido modificados, cuáles están en el área de preparación (staging) y cuáles no están siendo rastreados. Es el comando más importante para saber dónde estás.
```bash
git status
```

**2. Añadir cambios al área de preparación (`add`):**
Prepara los archivos que quieres guardar en el próximo "commit".
```bash
# Añadir un archivo específico
git add nombre_del_archivo.js

# Añadir una carpeta entera
git add nombre_de_la_carpeta/

# Añadir TODOS los archivos nuevos o modificados en la carpeta actual y subcarpetas
git add .
```

**3. Guardar los cambios en el historial local (`commit`):**
Crea una "fotografía" permanente de los archivos que preparaste con `git add`. Cada commit tiene un mensaje descriptivo.
```bash
git commit -m "Un mensaje claro que describe los cambios realizados"
```

**4. Subir tus cambios al repositorio remoto (`push`):**
Envía tus commits locales a un repositorio remoto (como GitHub) para compartirlos.
```bash
git push origin main
```
*(Reemplaza `main` por el nombre de tu rama si es diferente).*

**5. Bajar y fusionar cambios del remoto (`pull`):**
Actualiza tu repositorio local con los cambios más recientes del repositorio remoto.
```bash
git pull origin main
```

## 🌿 Trabajar con Ramas (Branches)
*Las ramas te permiten trabajar en nuevas funcionalidades de forma aislada sin afectar la versión principal del código.*

**Listar todas las ramas:**
Muestra todas las ramas locales. La rama activa se marca con un asterisco.
```bash
git branch
```

**Crear una nueva rama:**
```bash
git branch nombre-de-la-nueva-rama
```

**Cambiar a otra rama:**
Te mueve a la rama especificada para poder trabajar en ella.
```bash
git checkout nombre-de-la-rama
```

**Crear una rama y cambiar a ella en un solo paso:**
Es la forma más común de empezar una nueva tarea.
```bash
git checkout -b nombre-de-la-nueva-rama
```

**Fusionar los cambios de otra rama en tu rama actual (`merge`):**
Incorpora los cambios de una rama (por ejemplo, `nueva-funcionalidad`) en tu rama actual (por ejemplo, `main`).
```bash
# Primero, asegúrate de estar en la rama que recibirá los cambios
git checkout main

# Después, ejecuta el merge
git merge nombre-de-la-rama-a-fusionar
```

**Borrar una rama local:**
Se usa cuando los cambios de esa rama ya han sido fusionados y no se necesita más.
```bash
git branch -d nombre-de-la-rama
```

## 📂 Gestionar Archivos

**Borrar un archivo del repositorio:**
Elimina el archivo de tu proyecto y prepara la eliminación para el próximo commit.
```bash
git rm nombre_del_archivo.txt
```

**Mover o renombrar un archivo:**
Cambia el nombre o la ubicación de un archivo. Git lo entiende como un renombramiento en lugar de borrar uno y crear otro.
```bash
git mv nombre_antiguo.txt nuevo_nombre.txt
```

## 📜 Historial y Deshacer Cambios

**Ver el historial de commits (`log`):**
Muestra un registro de todos los commits realizados en la rama actual.
```bash
# Vista detallada
git log

# Vista compacta y más legible
git log --oneline --graph --all
```

**Descartar cambios en un archivo:**
Revierte un archivo al estado exacto en el que estaba en el último commit. **¡Cuidado, los cambios no guardados se pierden!**
```bash
git restore nombre_del_archivo.txt
```

**Sacar un archivo del área de preparación (`restore --staged`):**
Deshace un `git add`. El archivo seguirá modificado en tu disco, pero no se incluirá en el próximo commit.
```bash
git restore --staged nombre_del_archivo.txt
```