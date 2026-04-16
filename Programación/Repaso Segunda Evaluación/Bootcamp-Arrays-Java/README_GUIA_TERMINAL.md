# Guía Terminal - Bootcamp Arrays Java

## Requisitos previos

Solo necesitas **Java 17+** instalado. Si no lo tienes:

1. Ve a https://adoptium.net/
2. Descarga **Temurin JDK 17** (o superior) para Windows
3. Durante la instalación, marca **"Add to PATH"** y **"Set JAVA_HOME"**
4. Reinicia la terminal

## Instalación de Maven

### Opción A: Instalar Maven manualmente (recomendado)

1. Ve a https://maven.apache.org/download.cgi
2. Descarga el archivo **Binary zip archive** (`apache-maven-3.9.x-bin.zip`)
3. Descomprime en `C:\apache-maven`
4. Añade `C:\apache-maven\bin` al **PATH** del sistema:
   - Busca "Variables de entorno" en el menú Inicio
   - En "Variables del sistema", edita **Path**
   - Añade una nueva entrada: `C:\apache-maven\bin`
5. Abre una terminal nueva y comprueba: `mvn -version`

### Opción B: Ejecutar el script automático

Haz doble clic en `setup.bat` en la raíz del proyecto. Te guiará paso a paso.

## Comandos esenciales

Todos los comandos se ejecutan desde la raíz del proyecto (`Bootcamp-Arrays-Java/`).

### Compilar el proyecto

```bash
mvn compile
```

### Ejecutar TODOS los tests

```bash
mvn test
```

### Ejecutar tests de UN ejercicio concreto

```bash
# Ejemplo: solo los tests del ejercicio 1
mvn test -Dtest=bloque1.Ej01_CrearYPintarTest

# Ejemplo: todos los tests del bloque 1
mvn test -Dtest="bloque1.*"

# Ejemplo: todos los tests del bloque 5
mvn test -Dtest="bloque5.*"
```

### Ejecutar un ejercicio (su main) desde terminal

```bash
# Compilar primero
mvn compile

# Ejecutar el main de un ejercicio
mvn exec:java -Dexec.mainClass="bloque1.Ej01_CrearYPintar"
```

### Ejecutar un ejercicio desde VS Code

1. Abre el archivo `.java` del ejercicio
2. Busca el método `public static void main`
3. Pulsa el botón **▶ Run** que aparece encima del método
4. La salida aparece en la terminal integrada

## Flujo de trabajo para cada ejercicio

```
1. Lee la teoría correspondiente en teoria/
2. Abre el ejercicio en src/main/java/bloqueX/
3. Busca los comentarios // TODO: y resuélvelos
4. Pulsa Run en el main para comprobar tu salida visual
5. Ejecuta mvn test -Dtest=bloqueX.EjXX_NombreTest
6. Si todos los tests pasan → ejercicio completado ✅
```

## Solución de problemas

| Problema | Solución |
|---|---|
| `mvn` no se reconoce como comando | Instala Maven y añádelo al PATH (ver arriba) |
| `java` no se reconoce como comando | Instala Java 17+ y marca "Add to PATH" |
| Tests fallan con `ClassNotFoundException` | Ejecuta `mvn compile` antes de `mvn test` |
| Error de codificación (tildes raras) | El proyecto ya usa UTF-8, asegúrate de que tu terminal también |
