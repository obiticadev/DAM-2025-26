# Cómo Empezar - Guía de Configuración Vanilla

Si es tu primera vez en un ordenador con Java puro o acabas de descargar este repositorio, aquí tienes la guía paso a paso para dejarlo 100% operativo sin tocar interfaces gráficas liosas.

Este proyecto sigue una arquitectura "Test-Driven Learning" (Koans). Tu objetivo será rellenar piezas de código vacías (`TODO`) en `src/main/` para que los tests en `src/test/` pasen a estar en **verde**.

## Requisitos Previos Generales
Deberás tener **Java JDK 25** o superior, y **Maven**.

Podemos comprobarlo abriendo una terminal y ejecutando:
```bash
java -version
mvn -version
```
Si ambos comandos te devuelven una versión (y la de java es 25+), vamos al Lío.

---

## 🛠️ PASO A PASO: Configurando y validando el Proyecto

### 1. Abre el terminal en este mismo directorio
Si usas Visual Studio Code, ve al menú superior: `Terminal` -> `Nuevo Terminal`.
Asegúrate de que estás dentro de la carpeta `GenericosCRUDMasterclass`.

### 2. Descargar e Instalar Dependencias (El Pom.xml)
En la terminal, ejecuta el comando mágico de compilación limpia de Maven. Esto descargará JUnit 5 (testing), SQLite (BBDD), y HikariCP de internet directamente a tu pc.

👉 **Copia y pega este comando y presiona ENTER:**
```bash
mvn clean install -DskipTests
```
*(Verás un montón de descargas. Sólo tienes que esperar al mensaje `BUILD SUCCESS`).*

### 3. Validar que el Entorno de Test Funciona
Vamos a comprobar que el entorno está reconociendo los ejercicios. Como aún no has resuelto el nivel 1, el test debería dar **ERROR** adrede mostrándote qué esperar.

👉 **Copia y pega este comando y presiona ENTER:**
```bash
mvn test -Dtest=Nivel1Test
```
*(Si ves un mensaje en rojito o build failure quejándose de un `TODO` no implementado, **¡Enhorabuena! Tienes el entorno de test automatizado de la masterclass montado con éxito.**)*

---

## 🚀 ¿CÓMO RESOLVER LA MASTERCLASS?

El workflow o flujo de trabajo del Backend es siempre el mismo:

1. **Abre la carpeta `teoria/`**. Ahí tendrás un `.md` numerado por nivel (ej. `01_Fundamentos_Genericos.md`). Lee la teoría base, tómate tu tiempo.
2. **Abre el código**. Ve a `src/main/java/com/masterclass/nivel1...` y abre el archivo `.java` de tu ejercicio actual (ej. `Ejercicio01...`). 
3. **Resuelve el misterio**. Busca comentarios que digan `// TODO:` y cambia ese bloque por tu código.
4. **Pasa la prueba**. Desde VS Code, vete al archivo hermano de testing en `src/test/java/com/masterclass/nivel1...` y dale al botoncito de `▶️ Run Test` sobre el método test... o ejecuta en la terminal su equivalente (ej. `mvn test -Dtest=TestEjercicio01`).
5. **Si el test falla**, lee la consola, te dará pistas clave. ¡Vuelve a tocar tu código!
6. **Si el test aprueba (VERDE)**, subes la moral y avanzas al siguiente ejercicio.

---

¡Eso es todo! Tienes por delante el campo de entrenamiento más bestial de tu vida. Muchísima suerte.
