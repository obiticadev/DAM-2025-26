# 🛠️ Requisitos y Compilación de Google Guava (Nivel 15)

Si quieres que este proyecto funcione en cualquier PC con una instalación básica de Java (JDK), sigue estos comandos en orden desde la terminal. Asegúrate de estar situado en la carpeta raíz del proyecto (`04_MapsMasterclass`).

## 📋 Pasos de Preparación

### 1. Crear carpeta de salida (Binarios)
Primero, necesitamos una carpeta donde Java guardará los archivos compilados (`.class`).

```powershell
mkdir bin
```

### 2. Compilación del Proyecto
Este comando compila todos los niveles del curso, incluyendo las dependencias de Google Guava situadas en la carpeta `lib/`. 

> [!NOTE]
> Usamos `-cp` para definir el Classpath y `-d bin` para enviar el resultado a la carpeta que acabas de crear.

```powershell
javac -cp ".;lib/*" -d bin src/App.java src/nivel*/*.java
```

*(Si el comando anterior te da error de "demasiados archivos", puedes usar este método universal):*

```powershell
dir /s /b src\*.java > sources.txt
javac -cp ".;lib/*" -d bin @sources.txt
del sources.txt
```

### 3. Ejecución de la Masterclass
Una vez compilado, puedes lanzar la aplicación principal. Recuerda que siempre debes incluir las librerías en el Classpath al ejecutar.

```powershell
java -cp "bin;lib/*" App
```

---

## 🔧 Solución de problemas comunes

### ¿Ves líneas rojas en el IDE (VS Code)?
Si el código funciona en la terminal pero VS Code muestra errores:
1. Asegúrate de que el archivo `.vscode/settings.json` tiene esta configuración:
   ```json
   {
       "java.project.referencedLibraries": ["lib/**/*.jar"]
   }
   ```
2. Si persiste, pulsa `Ctrl + Shift + P` y ejecuta: **Java: Clean Java Language Server Workspace**.

### ¿Faltan los archivos .jar?
Asegúrate de que dentro de `04_MapsMasterclass/lib/` se encuentran estos archivos esenciales:
* `guava-33.0.0-jre.jar`
* `failureaccess-1.0.2.jar`
* `jsr305-3.0.2.jar`
* `checker-qual-3.41.0.jar`
* `error_prone_annotations-2.23.0.jar`
* `j2objc-annotations-2.8.jar`

---

> [!IMPORTANT]
> **Arquitectura Vanilla:** Este proyecto no utiliza Maven ni Gradle para facilitar el aprendizaje de cómo Java gestiona las librerías de forma nativa. En un entorno profesional, estos comandos serían gestionados automáticamente por un archivo `pom.xml` o `build.gradle`.
