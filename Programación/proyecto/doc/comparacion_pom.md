# Comparación de pom.xml

## pom.xml del ejemplo vs. pom.xml del proyecto

| Aspecto | Ejemplo (`demo`) | Tu proyecto (`proyecto`) |
|---|---|---|
| `groupId` | `com.example` | `com.biblioteca` |
| `artifactId` | `demo` | `proyecto` |
| **Java versión** | `source/target 17` (separados) | `release 21` (unificado, más correcto) |
| `sourceEncoding` | No definido | `UTF-8` explícito |

---

## Dependencias

| Dependencia | Ejemplo | Tu proyecto |
|---|---|---|
| `sqlite-jdbc` | 3.45.1.0 | 3.45.1.0 ✓ |
| `slf4j-simple` | 2.0.12 | 2.0.12 ✓ |
| `junit-jupiter-api` | No tiene | 5.11.4 (scope test) |
| `junit-jupiter-engine` | No tiene | 5.11.4 (scope test) |

---

## Plugins de build

| Plugin | Ejemplo | Tu proyecto |
|---|---|---|
| `exec-maven-plugin` | 3.1.0 (para ejecutar `main`) | **No tiene** |
| `maven-enforcer-plugin` | No tiene | 3.4.1 (fuerza versión mínima de Maven) |
| `maven-checkstyle-plugin` | No tiene | 3.3.1 (análisis estático de estilo) |
| `maven-surefire-plugin` | No tiene | 3.2.5 (ejecución de tests) |
| `jacoco-maven-plugin` | No tiene | 0.8.12 (cobertura de tests) |
| `maven-javadoc-plugin` | No tiene | 3.6.3 (generación de Javadoc) |

---

## Resumen de diferencias clave

### Lo que tiene el ejemplo y tú no tienes
- **`exec-maven-plugin`**: permite lanzar una clase `main` directamente con `mvn exec:java`. Si quieres ejecutar tu programa desde Maven, necesitarías añadirlo configurando `<mainClass>` con tu clase principal.

### Lo que tienes tú y el ejemplo no tiene
- **JUnit 5**: batería completa de testing con `jupiter-api` y `jupiter-engine`.
- **JaCoCo**: mide la cobertura de los tests y puede fallar el build si no se alcanza un umbral mínimo.
- **Checkstyle**: valida que el código sigue las reglas de estilo definidas (`com/github/ngeor/checkstyle.xml`).
- **Surefire**: ejecuta los tests durante la fase `test` del ciclo de Maven.
- **Enforcer**: garantiza que se usa una versión de Maven >= 3.6.3.
- **Javadoc**: genera documentación HTML a partir de los comentarios `/** */`.

### Diferencia en la propiedad de versión Java
- El ejemplo usa `<maven.compiler.source>` y `<maven.compiler.target>` por separado, que es el estilo antiguo.
- Tu proyecto usa `<maven.compiler.release>`, que es la forma recomendada desde Java 9+: establece source, target y bootclasspath de golpe y evita inconsistencias.
