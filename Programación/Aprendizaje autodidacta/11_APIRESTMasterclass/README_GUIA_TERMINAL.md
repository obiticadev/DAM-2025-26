# README · Guía de terminal — Masterclass API REST

Bootcamp de **200 ejercicios** de APIs REST con Java 21 + Spring Boot 3. Tú escribes
el código de los `// TODO:`; los tests dicen cuándo está bien.

## 0. Requisitos

- JDK 21 (`java -version` → 21.x)
- Maven 3.9+ (`mvn -version`)
- Docker (solo para bloques de Postgres/Mongo/despliegue; el resto usa H2 en memoria)

## 1. Rendimiento en VS Code: trabaja con UN bloque activo

El proyecto tiene 26 módulos Maven con Spring Boot. Si VS Code los importa todos,
el autocompletado de Java se arrastra. Solución: activa solo el bloque que estés
estudiando con el script `bloque.ps1`:

```powershell
.\bloque.ps1 b00        # activa solo b00_http (el bloque actual)
.\bloque.ps1 b04 b05    # puedes activar varios
.\bloque.ps1            # muestra qué bloques están activos
.\bloque.ps1 todos      # restaura los 26 (hazlo SIEMPRE antes de commit o mvn test global)
```

Tras ejecutarlo: `Ctrl+Shift+P` → **Java: Reload Projects**. Si las sugerencias
siguen raras, usa **Java: Clean Java Language Server Workspace** (reinicia el índice).

El resto de ajustes de rendimiento (memoria del language server, exclusión de
`target/`, autocompletado perezoso) ya están aplicados en `.vscode/settings.json`.

## 2. Compilar todo el ecosistema

```bash
mvn compile
```

Debe decir `BUILD SUCCESS`. Si falla, es que un esqueleto no compila: avísame.

## 3. Ejecutar un ejercicio concreto (comprobación visual)

Cada ejercicio tiene una clase `*Playground` con un `main`. Pulsa **Run** en el IDE
sobre ese `main`, o por terminal:

```bash
mvn -q exec:java -Dexec.mainClass="com.masterclass.api.b00_http.Ej001HttpRequestParser"
```

Para los ejercicios que levantan la API completa:

```bash
mvn spring-boot:run
```

## 4. Ejecutar la suite de tests (LA validación real)

Toda la suite:

```bash
mvn test
```

Solo un ejercicio:

```bash
mvn test -Dtest=Ej001HttpRequestParserTest
```

Solo un bloque (comodín):

```bash
mvn test -Dtest="Ej0*Test"
```

Un ejercicio está **completado** únicamente cuando su test pasa a verde.

## 5. Levantar Postgres / Mongo (bloques de persistencia y deploy)

```bash
docker compose up -d postgres mongo
docker compose down          # parar
```

## 6. Flujo de estudio recomendado

1. Lee `teoria/NN_*.md` del bloque (tiene diagramas Mermaid).
2. Abre el ejercicio en `src/main/java/.../bNN_*/`.
3. Lee el Javadoc y los `// TODO:`.
4. Implementa. Pulsa Run en el `main` para ver salida.
5. `mvn test -Dtest=ExEjTest` hasta verde.
6. Marca el checkbox en `SYLLABUS.md`.

> Regla de oro: si llevas 3 días sin escribir código, te has caído en el bucle de
> tutoriales. Aquí se construye, no se mira.
