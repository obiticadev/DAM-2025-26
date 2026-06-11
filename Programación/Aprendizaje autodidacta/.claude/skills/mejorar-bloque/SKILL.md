---
name: mejorar-bloque
description: Convierte un bloque del proyecto 11_APIRESTMasterclass en contenido guiado de aprendizaje. Reescribe la teoría del bloque como lección canónica y sustituye los TODOs genéricos de los retos extra por guías paso a paso. Usar cuando el usuario pida "mejorar el bloque bNN", "prepara el bloque NN", "haz el siguiente bloque" o similar. Recibe como argumento el identificador del bloque (ej. "b02" o "2").
---

# Mejorar un bloque del API REST Masterclass

Eres el preparador de contenido de un bootcamp autodidacta de APIs REST con
Java 21 + Spring Boot 3. El proyecto vive en `11_APIRESTMasterclass/` (búscalo
relativo al directorio de trabajo). El alumno es estudiante de DAM: domina Java
hasta interfaces, está aprendiendo el resto. Todo el contenido se escribe en
**español**.

El argumento es el bloque a preparar: `b02`, `b15`, etc. (si llega "2",
normalízalo a `b02`). Los bloques son módulos Maven `bNN_nombre/` (ej.
`b02_json`); cada uno tiene su teoría en `teoria/NN_*.md`.

**Trabaja UN solo bloque por invocación. No toques ningún otro bloque ni
ficheros fuera de los indicados aquí.**

Los bloques b00 y b01 ya están hechos: son LA REFERENCIA. Antes de empezar,
lee como modelo:
- `teoria/00_Fundamentos_HTTP_Web.md` y `teoria/01_Java_Moderno_para_APIs.md`
  (formato canónico de teoría).
- `b01_java/src/main/java/.../Ej012OptionalSafeAccess.java` (formato de las
  guías en los retos extra).

## Estructura de un bloque

```
bNN_nombre/
  pom.xml
  src/main/java/com/masterclass/api/bNN_nombre/EjXXX<Tema>.java   (ejercicios)
  src/test/java/com/masterclass/api/bNN_nombre/EjXXX<Tema>Test.java (tests)
teoria/NN_<Tema>.md                                                (teoría)
```

Cada ejercicio tiene: Javadoc de enunciado, métodos con `// TODO n:` numerados
(ejercicio base, normalmente bien guiados ya) y ~10 métodos "RETO EXTRA" cuyo
cuerpo lanza `UnsupportedOperationException` y cuyo comentario es un TODO
genérico repetido ("1. Validar exhaustivamente... 2. Diseñar e implementar...").
Ese boilerplate es lo que hay que sustituir.

## Proceso

### 1. Reconocimiento

Lee TODOS los ficheros del bloque: la teoría actual, cada ejercicio y cada
test. **Los tests son la especificación real**: cada pista que escribas debe
cuadrar con lo que los tests exigen (valores exactos, casos null, mensajes de
excepción literales).

### 2. Reescribir la teoría (`teoria/NN_*.md`)

Sustituye el fichero completo por una lección guiada con este formato canónico
(calca la estructura de `01_Java_Moderno_para_APIs.md`):

1. Título + cita motivadora breve.
2. Sección "Cómo usar este documento": leer UNA sección → hacer SU ejercicio →
   volver. Tabla de 3 columnas: Sección | Tema | Ejercicio.
3. Una sección numerada (N.1, N.2, …) por ejercicio del bloque, en su orden.
   Cada sección: explicación narrativa profunda pero al grano, ejemplos de
   código realistas, tablas de referencia cuando aporten, y los diagramas
   Mermaid del fichero original que sigan siendo válidos (consérvalos; añade
   nuevos solo si iluminan algo). Cierra SIEMPRE con el recuadro:
   `> **Lo practicas en \`EjXXXNombre\`**: <qué se practica>.`
4. Sección "Errores comunes del bloque": tabla `# | Error | Antídoto` con ~10
   errores reales que los tests del bloque castigan.
5. "Chuleta final del bloque": bloque de código con el resumen ultra-denso.
6. "Autoevaluación": 7-8 preguntas sin respuesta, cada una con referencia a su
   sección entre paréntesis.

Conserva del fichero original cualquier contenido bueno (diagramas, ejemplos);
elimina secciones duplicadas tipo "Teoría Extendida" integrándolas en las
secciones numeradas.

### 3. Mejorar los ejercicios (guías de los retos extra)

En cada `EjXXX*.java`, sustituye el bloque de comentarios boilerplate de CADA
reto extra por una guía real con este estilo:

```java
// GUÍA: teoría N.X (referencia a la sección que aplica).
// 1. Paso concreto (qué comprobar, qué devolver con null/vacío).
// 2. Paso concreto con la API exacta del JDK (método y clase reales).
// 3. ...
// PISTA: fragmento o expresión clave (ej. split(":", 2), Collectors.groupingBy).
// OJO/CUIDADO: la trampa concreta que ponen los tests (cítala con su valor).
// CULTURA: (opcional) por qué esto existe en el mundo real / conexión con
// bloques futuros (ej. "esto es lo que hace Spring Data en b12").
```

Reglas estrictas:
- **NO implementes las soluciones.** Solo comentarios. El `throw new
  UnsupportedOperationException(...)` y los cuerpos se quedan como están.
  Para retos de una línea evidente puedes dar la línea en el comentario
  (es pedagógico verla), pero sin escribirla como código activo.
- **Lee el test del reto antes de escribir su guía** y cita en la guía los
  casos límite que evalúa ("el test manda 'post' en minúsculas", "espera
  exactamente \"Valor requerido ausente\"").
- Fomenta la **reutilización**: si un reto puede apoyarse en otro método del
  mismo fichero, dilo ("reutiliza extraerCabeceraSegura del reto 7").
- Si un ejercicio ya tiene código resuelto por el alumno, **no lo toques**;
  si ese código tiene un bug que hará fallar los tests, añade en su guía una
  línea `// ⚠ CUIDADO: ...` explicando el fallo SIN corregirlo.
- Mantén el resto del fichero intacto (Javadoc, TODOs numerados del ejercicio
  base, main de demostración).

### 4. Arreglos de compilación (solo lo imprescindible)

Si el módulo no compila por errores PREEXISTENTES (imports que faltan en
tests, sintaxis ilegal en esqueletos), corrígelos de forma mínima. **Nunca
cambies las aserciones de un test** — solo imports o sintaxis rota.

### 5. Verificación

Compila el módulo directamente (no desde el pom raíz: su lista `<modules>`
puede estar filtrada por `bloque.ps1`/`bloque.sh` — no la modifiques):

- Linux (CachyOS/Fedora): `mvn -q -f bNN_nombre/pom.xml test-compile`
- Windows (mvn NO está en PATH):
  `& "C:\Users\obitica\maven\bin\mvn.cmd" -q -f bNN_nombre/pom.xml test-compile`

Debe terminar sin errores. Si falla por algo que introdujiste, arréglalo; si
falla por algo preexistente, aplica el punto 4.

### 6. Informe final

Termina con un resumen breve: qué secciones tiene la teoría nueva, cuántos
retos extra recibieron guía, qué arreglos de compilación hiciste (si hubo),
confirmación de que `test-compile` pasa, y qué bloque tocaría después.

## Ejemplo de transformación de un reto extra

Antes (boilerplate a eliminar):

```java
// TODO extra: Reto Extra 5: Lanzar excepción de negocio personalizada.
// 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
// 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
// 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
// 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
```

Después (guía real, alineada con su test):

```java
// GUÍA: una línea —
// return opt.orElseThrow(() -> new IllegalArgumentException("Valor requerido ausente"));
// El mensaje debe ser EXACTO (el test lo compara con equals).
// Este es el patrón "si no está → 404" de la teoría 1.2: en Spring la
// excepción será NotFoundException y un handler la convertirá en HTTP.
```

## Estado de los bloques

Hechos: b00_http, b01_java. Pendientes (en orden): b02_json, b03_core,
b04_boot, b05_web, b06_webadv, b07_dto, b08_valid, b09_err, b10_arch,
b11_jdbc, b12_jpa, b13_rel, b14_jpaadv, b15_query, b16_xml, b17_nosql,
b18_sec, b19_test, b20_obs, b21_perf, b22_deploy, b23_ci, b24_boss,
b25_thymeleaf.
