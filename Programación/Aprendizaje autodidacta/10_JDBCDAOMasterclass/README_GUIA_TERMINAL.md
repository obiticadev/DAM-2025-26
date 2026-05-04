# Guía de Terminal — Bootcamp 10: JDBC, DAO & SQLite

---

## Requisitos previos

- Java 17+
- Maven instalado (`mvn -v` para verificar)
- VS Code con extensión "Extension Pack for Java"

---

## Levantar el proyecto por primera vez

```bash
# Desde la raíz del proyecto (donde está pom.xml)
mvn install -DskipTests
```

Esto descarga el driver SQLite y JUnit 5. Solo hace falta hacerlo una vez.

---

## Ejecutar un ejercicio

1. Abre el archivo `.java` del ejercicio en VS Code.
2. Pulsa el botón **Run** que aparece encima del `public static void main`.
3. La base de datos SQLite se crea automáticamente en el directorio donde ejecutas (`bootcamp.db`).

---

## Ejecutar TODOS los tests

```bash
mvn test
```

---

## Ejecutar tests de un bloque concreto

```bash
# Solo Nivel 1
mvn test -Dtest="com.bootcamp.nivel1.*"

# Solo Nivel 2
mvn test -Dtest="com.bootcamp.nivel2.*"

# Solo Nivel 3
mvn test -Dtest="com.bootcamp.nivel3.*"

# Solo Nivel 4
mvn test -Dtest="com.bootcamp.nivel4.*"

# Boss Final
mvn test -Dtest="com.bootcamp.nivel4.BossFinalTest"
```

---

## Ejecutar un test concreto

```bash
mvn test -Dtest="Ej01Test"
```

---

## Limpiar bases de datos generadas durante los tests

Los tests usan bases de datos en memoria (`jdbc:sqlite::memory:`) — no dejan ficheros `.db` en disco. Si tus ejercicios del `main` crean un `bootcamp.db`, puedes borrarlo sin problema.

---

## Estructura de carpetas clave

```
src/main/java/com/bootcamp/
    nivel1_conexion/     ← Ej01 – Ej08
    nivel2_crud/         ← Ej09 – Ej18
    nivel3_dao/          ← Ej19 – Ej27
    nivel4_integracion/  ← Ej28 – Ej33 + BossFinal

src/test/java/com/bootcamp/
    nivel1/   nivel2/   nivel3/   nivel4/
```

---

## Flujo de trabajo recomendado

1. Lee el `.md` de teoría del bloque antes de empezar.
2. Abre el ejercicio, lee los `// TODO:` de arriba a abajo.
3. Implementa hasta que el `main` te dé la salida esperada.
4. Ejecuta `mvn test -Dtest="EjXXTest"` — verde = completado.
5. Pasa al siguiente.
