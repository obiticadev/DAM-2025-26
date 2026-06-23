---
name: crear-bloque
description: Construye DESDE CERO un bloque completo de la masterclass 11_APIRESTMasterclass (clases de ejercicio + tests espejo + teoría canónica + pom + alta en SYLLABUS), con la calidad ya pulida de los bloques b01/b02 — sin necesidad de pasar luego por mejorar-bloque. Cada ejercicio cubre su tópico de lo más simple a lo más avanzado, repartido en 10 TODOs y 10 retos extra guiados paso a paso. Usar cuando el usuario pida "crea el bloque bNN", "construye el bloque NN", "genera el siguiente bloque" o similar. Recibe como argumento el identificador del bloque (ej. "b32" o "32").
---

# Skill · crear-bloque

Eres un agente que **construye un bloque entero** de la masterclass `11_APIRESTMasterclass`
de cero, dejándolo ya con la calidad final de `b01`/`b02` (no hace falta "mejorarlo" después).
Esta skill fusiona *crear* + *mejorar* en una sola pasada.

> **Diferencia con `mejorar-bloque`:** aquella reescribe un bloque que ya existe. Esta **lo crea
> de la nada** (carpeta, módulo Maven, N ejercicios, N tests, teoría) y aplica de salida todos
> los estándares de calidad. Si el bloque ya existe, usa `mejorar-bloque`.

---

## 0. Entrada y de dónde sacar la especificación

- **Argumento:** un identificador de bloque, p.ej. `b32` o `32`. Normalízalo a `bNN` (dos dígitos).
- **La especificación de QUÉ ejercicios lleva el bloque está en `ROADMAP_BUILD_MASTERCLASS.md`**
  (raíz del proyecto). Busca la sección `## BNN · bNN_nombre — …`. De ahí sacas:
  - nombre de carpeta (`bNN_nombre`), tema, módulo·RA, rango de ejercicios, dependencias `pom.xml`,
    nombre del archivo de teoría, **tabla de ejercicios** (`# | Archivo | Concepto clave`) y el
    bloque **"Detalle por ejercicio"** (métodos core y temática de retos).
- Si el bloque NO está en el roadmap, **pregunta al usuario** por la lista de ejercicios antes de
  inventar nada.
- Lee también `SYLLABUS.md` (índice) y, si el bloque es de UI/JavaFX, el **§1.6 (addendum JavaFX)**
  del roadmap.

> **Herramientas del repo:** `mvn` puede no estar en el PATH. Activa el módulo con
> `python bloque.py bNN` y luego compila con Maven, o localiza el binario. `bloque.py` autodetecta
> cualquier carpeta `^b\d{2}_` con `pom.xml` y reescribe los `<modules>` del `pom.xml` raíz; **no**
> necesitas declarar nada a mano: en cuanto creas la carpeta del bloque con su `pom.xml`, `bloque.py`
> ya lo reconoce.

---

## 1. Principio pedagógico (lo más importante de esta skill)

El alumno **aprende implementando**. Por eso cada ejercicio debe ser una **escalera del tópico
completo, de lo más simple y común a lo más avanzado**, sin huecos:

1. **Cubre el tópico entero, no una rebanada.** Si el ejercicio es "Streams básicos", entre sus
   métodos core + sus 10 retos deben aparecer TODOS los patrones que un programador usa de verdad
   con streams (filter, map, reduce, collect, distinct, sorted, skip/limit, anyMatch/allMatch,
   max/min→Optional…). Haz un **inventario del tópico** antes de escribir y repártelo.
2. **Orden creciente de dificultad.** Dentro de un ejercicio:
   - Los **10 TODOs** de un método core van en orden de ejecución y de dificultad: primero
     validar/casos límite, luego el paso común, al final el matiz (el retorno correcto, el tipo,
     el caso vacío). Numéralos `// TODO 1:` … `// TODO 10:` **exactamente 10** repartidos entre
     2–3 métodos core.
   - Los **10 retos extra** van de menor a mayor: reto 1 = la variante más simple del tópico;
     reto 10 = el caso avanzado o el que conecta con un concepto mayor (paginación, genéricos,
     round-trip, concurrencia…).
3. **Cada reto enseña algo nuevo**, no repite el core. Y al menos 2–3 retos **enlazan con otros
   bloques** (sección "CULTURA", ver §4) para que el alumno vea el mapa completo.
4. **El test es la especificación.** Diseña primero qué casos comprueba el test (incluido un caso
   límite por método) y escribe la GUÍA del reto avisando de la **trampa exacta** de ese test.

---

## 2. Anatomía OBLIGATORIA de cada clase de ejercicio

Replica exactamente este molde (verificado en `b01_java/.../Ej013StreamsBasics.java`):

- Paquete `com.masterclass.api.bNN_nombre`. Archivo `EjNNN<Nombre>.java`.
- Clase `public final`, **constructor privado**, **todos los métodos `static`**.
  - *Excepción JavaFX (b32–b42):* el Playground extiende `Application` y la lógica testeable vive
    en métodos `static` puros o en un `ViewModel`; ver §6.
- **Javadoc de clase** con la referencia a teoría: `Teoría: {@code teoria/NN_*.md} (sección X.Y)`.
- **2–3 métodos "core"** que reparten **EXACTAMENTE 10 TODOs** numerados y granulares. Cada core
  devuelve un **centinela** que hace fallar el test hasta implementarlo: `-1`, `null`, `""`,
  `List.of()`, `Optional.empty()`, `false`… (elige el que case con el tipo de retorno).
- **`main(String[] args)`** corto que demuestra los core (es el Playground; el alumno pulsa *Run*).
- **10 retos extra** `retoExtra…` con nombre **descriptivo en español** (`filtrarMayoresDeEdad`,
  no `retoExtra01`), cada uno con javadoc, un comentario **`// GUÍA:`** por capas (§4) y cuerpo:
  `throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para <metodo>");`

### Plantilla mínima (copia y adapta)

```java
package com.masterclass.api.bNN_nombre;

import java.util.List;

/**
 * Ejercicio NNN · <Título corto del tópico>.
 *
 * <p>Teoría: {@code teoria/NN_Nombre.md} (sección N.M).
 */
public final class EjNNNNombre {

    private EjNNNNombre() {
    }

    /**
     * <Qué hace, en una frase>.
     *
     * @param ... ...
     * @return ...  (describe el caso vacío/límite)
     */
    public static <Tipo> metodoCoreA(<args>) {
        // TODO 1: valida la entrada / caso límite.
        // TODO 2: <primer paso del algoritmo>.
        // TODO 3: <...>
        // TODO 4: <...>
        // TODO 5: devuelve el resultado.
        return <centinela>;   // -1 / null / List.of() / ""...
    }

    public static <Tipo> metodoCoreB(<args>) {
        // TODO 6: ...
        // TODO 7: ...
        // TODO 8: ...
        // TODO 9: ...
        // TODO 10: devuelve el resultado (ojo al caso vacío).
        return <centinela>;
    }

    public static void main(String[] args) {
        System.out.println(metodoCoreA(...));
        System.out.println(metodoCoreB(...));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: <título>.
     * <Qué pide, en una frase>.
     */
    public static <Tipo> nombreDescriptivoEnEspanol(<args>) {
        // GUÍA: <ver formato por capas en §4 de la skill>
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreDescriptivoEnEspanol");
    }

    // ... retos 2..10 ...
}
```

---

## 3. Test espejo OBLIGATORIO (`src/test/.../EjNNNNombreTest.java`)

- JUnit 5 (`org.junit.jupiter`), asserts estáticos (`import static …Assertions.*`).
- **Un `@Test` por método core** (con **al menos un caso límite real**: lista vacía, null, 0,
  frontera) + **10 `@Test` `retoExtraNN_<metodo>`** (uno por reto), en el mismo orden.
- Los tests **nacen en rojo** (por el centinela / la excepción) y se ponen verdes al implementar.
- Casos concretos y deterministas. Para asincronía/UI: `assertTimeout`, `@Timeout`,
  `CountDownLatch.await(timeout)`, `WaitForAsyncUtils` (TestFX). **Nunca** `Thread.sleep` a ciegas.

```java
package com.masterclass.api.bNN_nombre;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class EjNNNNombreTest {

    @Test
    void metodoCoreA() {
        assertEquals(<esperado>, EjNNNNombre.metodoCoreA(<entrada>));
        assertEquals(<esperado_limite>, EjNNNNombre.metodoCoreA(<entrada_vacia>)); // caso límite
    }

    @Test
    void retoExtra01_nombreDescriptivo() {
        assertEquals(<esperado>, EjNNNNombre.nombreDescriptivo(<entrada>));
        assertEquals(<esperado_limite>, EjNNNNombre.nombreDescriptivo(<entrada_limite>));
    }
    // ... uno por cada core y cada reto ...
}
```

---

## 4. Formato de la GUÍA de cada reto (capa por capa) — el sello de calidad b02

Cada `// GUÍA:` no es un TODO genérico: es una **guía paso a paso** que enseña sin dar el código
hecho del todo. Usa estas capas (todas las que apliquen), en este orden:

1. **Referencia a teoría:** `// GUÍA: teoría N.M (concepto).`
2. **Pasos numerados** del algoritmo (1., 2., 3.…), en prosa, citando la API exacta.
3. **`// PISTA:`** una línea casi-solución (la firma de la llamada clave), no el método entero.
4. **`// OJO:`** la **trampa concreta del test** (qué caso límite comprueba y por qué fallarías).
5. **`// CULTURA:`** (en 2–3 retos por ejercicio) conexión con el mundo real u **otro bloque**
   (`b12` Pageable, HTTP PATCH, colas/Kafka…), para tejer el mapa mental.

Ejemplo real (de `Ej013`, reto de paginación) — replica este nivel:

```java
public static List<String> limitarYDescartar(List<String> lista, int skip, int limit) {
    // GUÍA: lista.stream().skip(skip).limit(limit).toList();
    // ESTO ES LA PAGINACIÓN: ?page=2&size=10 de una API es skip(20).limit(10).
    // Cuando llegues a Pageable en Spring Data (b12) será esta misma idea.
    // skip/limit toleran pasarse del tamaño (segundo test: limit 5 sobre 2
    // elementos → devuelve los 2, sin excepción).
    throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para limitarYDescartar");
}
```

Ejemplo con todas las capas (de `Ej024`, reto de actualización parcial):

```java
public static Cliente actualizarConMap(String json, Cliente existente) {
    // GUÍA: teoría 2.2 (convertValue para pasar Cliente <-> Map y fusionar).
    // 1. Convierte 'existente' a un Map mutable de sus campos.
    // 2. Lee el patch JSON como Map.
    // 3. Vuelca el patch sobre la base (las claves del patch ganan).
    // 4. Reconstruye el Cliente con convertValue(base, Cliente.class).
    // PISTA: putAll DESPUÉS de tener la base, para que el campo del patch sobreescriba.
    // OJO: el patch del test solo trae "nombre"; el id debe conservarse del 'existente'.
    //   Cliente es un record INMUTABLE -> construyes uno nuevo (patrón "wither" de 1.1).
    // CULTURA: esto es exactamente un PATCH de HTTP (actualización parcial) -> bloque de controllers.
    throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para actualizarConMap");
}
```

---

## 5. Teoría canónica (`teoria/NN_Nombre.md`)

Un `.md` por bloque, en castellano, con esta estructura (modelos: `teoria/02_JSON_y_Jackson.md`,
`teoria/32_JavaFX_Base.md`). **No es un resumen: es el material de apuntes del alumno.** Debe poder
estudiarse sin abrir otra fuente y dejarle resolver casos que los ejercicios NO tocan.

> **EL ALUMNO VIENE DE 1º DAM.** Muchos conceptos son **nuevos** para él. Escribe sin ambigüedades:
> define cada término la primera vez, explica el *por qué* (no solo el *qué*), y nunca des por
> sabido nada de 2º. Antes que quedarte corto, **profundiza**.

### 5.1 Densidad y extensión obligatorias
- **Tamaño de referencia: ~450–680 líneas y ~2500–5500 palabras** (mídelo: los bloques de calidad
  rondan eso; `32_JavaFX_Base.md` ≈ 680 líneas). Una teoría de <300 líneas está **incompleta**.
- **Cada sección `## N.M` debe ser autosuficiente y detallada**, no un párrafo telegráfico. Como
  mínimo por sección: prosa explicativa + ≥1 diagrama Mermaid + 1–2 **tablas de referencia** +
  1–2 **snippets** + reglas/trampas + el recuadro "Lo practicas en…".
- **Cubre el tópico ENTERO, incluidas opciones que los ejercicios no usan.** Si el tema es "Stage",
  enumera TODOS sus métodos/estilos/modalidades aunque el ejercicio solo use dos: el alumno debe
  poder afrontar un caso nuevo por sí mismo. Las tablas marca explícitamente esas filas como
  "consulta — más de lo que usa el ejercicio" cuando aplique.

### 5.2 Estructura obligatoria (en este orden)
1. **Cabecera** `# Bloque <RomanoOArábigo o NN> · <Tema> (<Módulo·RA>)` + **cita motivadora** estilo
   "vienes de…, esto te falta…, por qué importa".
2. **"Cómo usar este documento"**: regla "lee UNA sección → haz SU ejercicio → vuelve", aviso "los
   tests son la especificación real", aviso de que la teoría **va más allá** de los ejercicios, y
   nota de testing si aplica (p.ej. headless/Monocle, puertos efímeros, proceso hijo…).
3. **(Si aplica) "Antes de empezar"**: trampas de *setup*/entorno que bloquean al alumno novato
   (p.ej. "JavaFX ya no viene en el JDK", drivers JDBC, dependencias nativas, módulos…).
4. **Tabla índice** `| Sección | Tema | Ejercicio |` (una fila por ejercicio) + un **recuadro con el
   modelo mental** del bloque + (recomendado) un `classDiagram`/`flowchart` general que lo resuma.
5. **Una sección `## N.M` por ejercicio** (ver 5.1 para el contenido mínimo). La prosa va de lo
   simple a lo avanzado (igual que los TODOs); los diagramas Mermaid se eligen según el tema:
   `flowchart`/`classDiagram` (estructura), `sequenceDiagram` (interacción/red/ciclo de petición),
   `stateDiagram-v2` (hilos, sockets, ciclos de vida). Cierra con
   `> **Lo practicas en \`EjNNN…\`**: <qué core y qué retos se practican>.`
6. **Cierre obligatorio del documento, con estas tres secciones finales** (sello de calidad b02):
   - `## Errores comunes del bloque`: **tabla numerada** `| # | Error | Antídoto |` (≥10 filas)
     recogiendo las trampas reales de los tests y del setup.
   - `## Chuleta final del bloque`: un bloque de código (```) con líneas `concepto = resumen
     telegráfico` que sirva de repaso rápido de TODO el bloque.
   - `## Autoevaluación (responde sin mirar; si fallas 2+, relee la sección)`: lista numerada de
     preguntas (≈1 por sección), cada una con la referencia `*(N.M)*`.
7. Separadores `---` entre secciones. Castellano y UTF-8 en todo.

### 5.3 Reglas de redacción
- **Define antes de usar.** La primera vez que aparece un término (DFS, attached property, z-order,
  modalidad, *bounds*…), explícalo en una frase.
- **Explica el *porqué* y los errores típicos**, no solo la API. Las "Reglas grabadas" y los avisos
  `> **Trampa…**` son obligatorios donde haya un error frecuente.
- **Enlaza con otros bloques** (de dónde viene el concepto, en qué bloque futuro se profundiza) para
  tejer el mapa mental, igual que la capa `CULTURA:` de los retos.
- La teoría debe **enseñar el tópico al completo** aunque el ejercicio solo pida una parte.

---

## 6. Reglas de determinismo y casos especiales

- **Centinela correcto por tipo:** numérico→`-1`/`0`; objeto→`null`; `String`→`""`; colección→
  `List.of()`/`Map.of()`; `Optional`→`Optional.empty()`; `boolean`→`false`. El test debe fallar
  con el centinela y pasar con la implementación.
- **Sockets/concurrencia (b27–b29):** core autocontenido — servidor en **puerto efímero**
  (`new ServerSocket(0)`) + cliente en el mismo método/test; `@Timeout` para no colgar la suite;
  cierra siempre (`try-with-resources`).
- **Procesos (b28):** para tests deterministas y portables lanza un **proceso Java hijo** conocido
  (clase de apoyo con `main`), no comandos del SO.
- **JavaFX/UI (b32–b42) — addendum §1.6 del roadmap:**
  - El **método core es lógica pura headless** (ViewModel, conversores, validadores, bindings
    calculados, modelo de datos del informe): se prueba con JUnit puro **sin abrir ventana**.
  - El **`main`/Playground** monta la UI real (`extends Application`, `launch(args)`).
  - Lo que toque nodos/propiedades JavaFX en test: inicializa el toolkit una vez
    (`new JFXPanel()` o `Platform.startup`) y usa **TestFX + Monocle headless**
    (`-Dtestfx.headless=true -Dglass.platform=Monocle -Dprism.order=sw`).
  - **Regla de oro:** si un `@Test` necesita pantalla real para pasar, empuja la lógica al
    ViewModel y deja la vista como cascarón.
- **Ejercicios "guion"** (Docker/CI, jpackage, Android): si el tema no es testeable en Maven/JUnit,
  el cuerpo son `pasoNN()` con guía en comentarios y el test comprueba un `ejecutar()`/metadato;
  **dilo claramente en la teoría** (es honesto, no un atajo).
- Recuento por defecto: **8 ejercicios** por bloque salvo que el roadmap diga otro número.
  **10 retos** por ejercicio (salvo que el roadmap fije otra cosa, p.ej. `b26` lleva 12).

---

## 7. Alta del módulo (Maven + SYLLABUS)

1. Crea la carpeta `bNN_nombre/` con:
   - `pom.xml` propio: copia el de un bloque hermano y cambia `artifactId`. Plantilla base:
     ```xml
     <project ...>
       <modelVersion>4.0.0</modelVersion>
       <parent>
         <groupId>com.masterclass</groupId>
         <artifactId>api-rest-masterclass</artifactId>
         <version>1.0.0</version>
         <relativePath>../pom.xml</relativePath>
       </parent>
       <artifactId>bNN_nombre</artifactId>
       <name>Module - bNN_nombre</name>
       <dependencies>
         <!-- añade aquí SOLO las del bloque (ver roadmap): openjfx, jasperreports, jackson... -->
       </dependencies>
     </project>
     ```
   - `src/main/java/com/masterclass/api/bNN_nombre/` con las clases de ejercicio.
   - `src/test/java/com/masterclass/api/bNN_nombre/` con los tests espejo.
   - `src/main/resources/...` si el bloque necesita FXML/`.jrxml`/`.properties`/datos.
2. **No** hay que declarar el módulo a mano: `bloque.py` lo autodetecta en cuanto existe la carpeta
   con su `pom.xml`. Para compilar TODO, el módulo debe estar en `<modules>` del `pom.xml` raíz: usa
   `python bloque.py todos` para regenerar esa lista (o `python bloque.py bNN` para activar solo este).
3. **Registra el bloque en `SYLLABUS.md`:** añade fila(s) en la tabla de rangos (§3), la tabla
   detallada de ejercicios (§4) y el checklist de progreso (§5).
4. Si procede, añade el bloque a la ruta de estudio `RUTA_ESTUDIO_2DAM.md`.

---

## 8. Verificación y Definición de Hecho (DoD)

Compila y comprueba que **los tests nacen en rojo limpio** (fallo por centinela/excepción, NO por
error de compilación):

```
python bloque.py bNN
mvn -pl bNN_nombre test    # o el binario de mvn si no está en PATH
```

El bloque está **terminado** cuando se cumple TODO esto:

- [ ] Carpeta + `pom.xml` + `src/{main,test}` espejo creados; `python bloque.py` lista el bloque.
- [ ] `mvn -pl bNN_nombre test-compile` **compila sin errores**.
- [ ] Cada ejercicio: clase `final` + ctor privado + métodos `static`, javadoc con ref a teoría.
- [ ] Cada ejercicio: **2–3 core con EXACTAMENTE 10 TODOs** + `main` + **10 retos** con nombre
      descriptivo, GUÍA por capas (teoría/pasos/PISTA/OJO/CULTURA) y `throw UnsupportedOperationException`.
- [ ] Los TODOs y los retos van **de lo simple a lo avanzado** y **cubren el tópico entero**.
- [ ] Test espejo: un `@Test` por core (con caso límite) + 10 `@Test` de retos; **todos en rojo**.
- [ ] Teoría `NN_Nombre.md` **profunda (~450–680 líneas)**: cabecera + "cómo usar" + ("antes de
      empezar" si hay trampa de setup) + tabla índice + recuadro de modelo mental + una sección por
      ejercicio (prosa + Mermaid + tablas de referencia que **cubren el tópico más allá del
      ejercicio** + snippets + trampas + recuadro "Lo practicas en…") + **cierres obligatorios**:
      "Errores comunes" (tabla ≥10), "Chuleta final" y "Autoevaluación". Sin ambigüedades, pensada
      para un alumno que viene de 1º DAM (define cada término nuevo).
- [ ] `SYLLABUS.md` actualizado (rangos + detalle + checklist).
- [ ] Castellano y UTF-8 en todo, comentarios incluidos. Nombres de método en español.

> Cuando termines, informa al usuario: qué ejercicios creaste, cuántos tests quedan en rojo, y
> recuérdale ejecutar `python bloque.py todos` antes de un `mvn test` global o un commit.
