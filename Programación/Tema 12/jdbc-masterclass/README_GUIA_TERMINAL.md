# 🚀 GUÍA TERMINAL: JDBC Masterclass

Bienvenido al Bootcamp Autodidacta de JDBC. Este proyecto te llevará desde el nivel más básico hasta crear una arquitectura robusta orientada a transacciones y protección de memoria.

## 🛠️ Requisitos Previos
- Tener instalado Java JDK 21 (o superior).
- Maven instalado en tu sistema.
- Extensión "Test Runner for Java" en VS Code o similar si deseas ejecutar los test visualmente.

## 📖 Cómo funciona esta Masterclass

1. **Lee la Teoría:** Dirígete a la carpeta `teoria/` y lee detenidamente el documento correspondiente a tu nivel actual. Entiende bien los diagramas de Mermaid.
2. **Resuelve el Reto:** Ve a `src/main/java/jdbc_masterclass/` y abre el nivel correspondiente (Ej. `Nivel01_ConexionSegura.java`). **Tú eres el arquitecto**. Reemplaza los comentarios `// TODO:` con tu código. No uses IAs para que te resuelvan el código.
3. **Playground:** Al final del ejercicio encontrarás un bloque `main()`. Puedes ejecutarlo directamente desde el IDE para hacer las pruebas que desees y ver lo que escupe la consola.
4. **Valida tu nivel:** Una vez estés seguro de tu respuesta, debes pasar la suite de Tests de ese nivel. 

## 💻 Comandos de Maven

Desde el directorio raíz `jdbc-masterclass`, abre tu terminal y ejecuta:

**Para compilar tu código:**
```bash
mvn clean compile
```

**Para evaluar TODOS los tests:**
```bash
mvn test
```

**Para evaluar SOLO los tests de un nivel concreto (¡Recomendado!):**
```bash
mvn test -Dtest=Nivel01_ConexionSeguraTest
```

Cuando el comando devuelva un hermoso **BUILD SUCCESS**, significará que has superado el reto y estás listo para avisar al instructor de que quieres pasar al siguiente bloque.
