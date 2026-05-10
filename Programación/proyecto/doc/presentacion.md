---
# Slidev frontmatter — ajusta según prefieras
# Temas recomendados: default, seriph, apple-basic, bricks, penguin
theme: default
title: Gestor de Biblioteca
info: ABP Programación · DAM 1º · 2025-26
# Transiciones: slide-left, fade, none
transition: slide-left
# Resaltado de código: shiki (soporta java, sql, bash)
highlighter: shiki
lineNumbers: true
# Activa el modo oscuro si quieres
colorSchema: auto
# Para mostrar número de slide en esquina
# slideNumber: true
---

<!-- ═══════════════════════════════════════════════
     SLIDE 1 — PORTADA
     Layout: cover  →  título grande centrado con fondo temático
     TODO: cambia el fondo con `background:` (imagen o color CSS)
     Ejemplo:  background: '#1a1a2e'  o  background: '/img/portada.jpg'
     ═══════════════════════════════════════════════ -->

# Gestor de Biblioteca

**Aplicación de gestión de préstamos y recursos**

ABP Programación · DAM 1º · 2025-26

---

<!-- ═══════════════════════════════════════════════
     SLIDE 2 — ÍNDICE
     Componente: <Toc> genera el índice automáticamente desde los títulos H1
     Si no usas <Toc>, deja este slide manual como está
     ═══════════════════════════════════════════════ -->

## Contenido

1. Contexto y objetivo
2. Arquitectura del proyecto
3. UT9 — Colecciones y jerarquía de clases
4. UT10 — Ficheros (CSV + logs)
5. UT12 — Base de datos SQLite
6. Decisiones técnicas clave
7. Dificultades y soluciones
8. Ampliación: Streams
9. Posibles mejoras

---

<!-- ═══════════════════════════════════════════════
     SLIDE 3 — CONTEXTO
     Layout: two-cols  →  problema a la izquierda, solución a la derecha
     Usa <v-clicks> para revelar los puntos uno a uno al hacer clic
     ═══════════════════════════════════════════════ -->

## Contexto y objetivo

**Problema:** la biblioteca gestiona préstamos en papel, sin control automatizado.

**Solución:** aplicación de consola en Java con:

<v-clicks>

- Control de libros en papel y electrónicos
- Registro y devolución de préstamos
- Exportación de listados filtrados a CSV
- Log de operaciones en fichero
- Persistencia en base de datos SQLite

</v-clicks>

---

<!-- ═══════════════════════════════════════════════
     SLIDE 4 — ARQUITECTURA
     TODO: inserta aquí un diagrama de capas o de paquetes
     Opción A: imagen PNG del diagrama  →  ![](./img/arquitectura.png)
     Opción B: diagrama Mermaid inline  (Slidev lo renderiza)
     ═══════════════════════════════════════════════ -->

## Arquitectura del proyecto

```
src/main/java/com/biblioteca/
├── App.java              ← menús y entrada/salida (capa de presentación)
├── Clases/               ← modelo de dominio
│   ├── Libro.java        (abstracta)
│   ├── LibroEnPapel.java
│   ├── LibroElectronico.java
│   ├── Usuario.java
│   └── Prestamo.java
├── Enum/                 ← Genero, Tipo, Formato, Estado, Aviso
└── dao/                  ← acceso a datos
    ├── Conexion.java
    ├── DAOlibros.java
    ├── DAOusuarios.java
    ├── DAOprestamos.java
    ├── Exportador.java
    └── Logs.java
```

---

<!-- ═══════════════════════════════════════════════
     SLIDE 5 — UT9: JERARQUÍA DE CLASES
     Layout: two-cols  →  código a la izquierda, explicación a la derecha
     TODO: añade un diagrama UML simple si tienes imagen
     Líneas resaltadas: {2,3} resalta líneas 2 y 3 del bloque
     ═══════════════════════════════════════════════ -->

## UT9 — Jerarquía de clases

```java {1|3,7|11}
// Clase abstracta base — campos comunes a todo libro
public abstract class Libro implements Serializable {
    protected int id, copiasTotales, copiasDisponibles;
    protected String titulo, autor, isbn;
    protected Genero genero;
    protected Tipo tipo;
}

// Subclase para libros físicos
public class LibroEnPapel extends Libro {
    private String ubicacion;
}

// Subclase para libros digitales
public class LibroElectronico extends Libro {
    private Formato formato;
    private String urlDescarga;
}
```

> `Libro` es abstracta: nunca se instancia directamente.
> El campo `tipo` actúa de discriminador en la tabla de BD.

---

<!-- ═══════════════════════════════════════════════
     SLIDE 6 — UT10: FICHEROS
     Usa dos bloques de código cortos uno debajo del otro
     o layout two-cols para CSV a la izquierda y logs a la derecha
     ═══════════════════════════════════════════════ -->

## UT10 — Ficheros: CSV y logs

**CSV** — exportación de listados filtrados con `Exportador.java`

```java
// Escribe cabecera + una línea por libro
pw.println("id,titulo,autor,genero,isbn,...");
for (Libro libro : libros) {
    pw.printf("%d,%s,%s,...%n", libro.getId(), escaparCSV(libro.getTitulo()), ...);
}
```

**Logs** — registro de operaciones con `Logs.java`

```java
// Escritura: añade al final del fichero (append = true)
new FileWriter("logs.txt", true)

// Lectura: devuelve los últimos N logs
leerUltimosLogs(5)  // lee todo y hace subList desde el final
```

Se eligió **CSV** frente a binario por legibilidad (Excel/LibreOffice) y **texto plano** para logs por su simplicidad y trazabilidad.

---

<!-- ═══════════════════════════════════════════════
     SLIDE 7 — UT12: BASE DE DATOS
     TODO: puedes mostrar el esquema de tablas como imagen
     o usar el bloque SQL directamente
     ═══════════════════════════════════════════════ -->

## UT12 — Persistencia en SQLite

**Tecnología:** SQLite via JDBC · patrón DAO · `PreparedStatement`

```sql
-- Tabla principal de libros (tabla única con discriminador `tipo`)
CREATE TABLE libros (
    id                 INTEGER PRIMARY KEY AUTOINCREMENT,
    titulo             TEXT NOT NULL,
    copias_disponibles INTEGER NOT NULL,
    tipo               TEXT NOT NULL,   -- 'PAPEL' o 'ELECTRONICO'
    formato            TEXT,            -- solo si ELECTRONICO
    ubicacion          TEXT             -- solo si PAPEL
);
```

**Transacciones** en préstamo y devolución:

```java
conn.setAutoCommit(false);
// INSERT prestamo + UPDATE copias_disponibles
conn.commit();   // ambos o ninguno
```

---

<!-- ═══════════════════════════════════════════════
     SLIDE 8 — DECISIONES TÉCNICAS
     Layout: two-cols o lista con v-clicks
     Cada punto es una decisión concreta tomada durante el desarrollo
     ═══════════════════════════════════════════════ -->

## Decisiones técnicas clave

<v-clicks>

- **Una conexión por operación** — `Conexion.getConexion()` abre y cierra en cada método DAO → evita estado compartido entre DAOs
- **Sin cache en memoria** — toda consulta va directamente a SQLite, eliminando el riesgo de desincronización entre lista y BD
- **Validación antes del DAO** — `App.java` comprueba préstamos activos antes de llamar a `eliminarUsuario` / `eliminarLibro`
- **`parseFecha()` con fallback** — maneja tanto fechas ISO como timestamps Unix que SQLite puede devolver
- **Streams solo sobre listas** — los streams operan sobre el resultado de `obtenerTodos()`, sin mezclar lógica de BD con operaciones de colecciones
- **Exportador y Logs como clases estáticas** — no necesitan estado de instancia; métodos estáticos simplifican el uso

</v-clicks>

---

<!-- ═══════════════════════════════════════════════
     SLIDE 9 — DIFICULTADES
     Cada dificultad: qué pasó → qué hicimos
     ═══════════════════════════════════════════════ -->

## Dificultades encontradas

| Problema | Solución |
|---|---|
| Fechas devueltas como timestamps Unix (`1778018400000`) | `parseFecha()`: intenta `LocalDate.parse()`, si falla usa `Instant.ofEpochMilli()` |
| Mantener colecciones sincronizadas con BD | Descartado: cada operación consulta directamente SQLite |
| Eliminar usuario/libro con préstamos activos | Validación previa en `App` con `prestamosActivosDeUnUsuario/Libro()` |
| Cálculo de días sin `ChronoUnit` | `fechaPrevista.toEpochDay() - LocalDate.now().toEpochDay()` |
| `UPDATE libros` en devolución sin variable `idLibro` | Subquery: `WHERE id = (SELECT id_libro FROM prestamos WHERE id = ?)` |

---

<!-- ═══════════════════════════════════════════════
     SLIDE 10 — AMPLIACIÓN: STREAMS
     Muestra un ejemplo concreto de stream complejo
     Resalta las partes importantes con {línea|línea}
     ═══════════════════════════════════════════════ -->

## Ampliación — Streams

```java {1-2|3-5|6-8}
// Género con más préstamos — combina dos listas con streams
List<Libro> libros = daoLibros.obtenerTodosLosLibros();
return obtenerTodosLosPrestamos().stream()
    .map(prestamo -> libros.stream()
        .filter(libro -> libro.getId() == prestamo.getIdLibro())
        .findFirst().orElse(null))
    .filter(Objects::nonNull)
    .collect(Collectors.groupingBy(Libro::getGenero, Collectors.counting()))
    .entrySet().stream()
    .max(Map.Entry.comparingByValue())
    .orElse(null);
```

También se usan streams en: `librosDisponibles`, `buscarPorAutor`, `buscarPorGenero`, `libroMasPrestado`, filtros del menú de exportación CSV.

---

<!-- ═══════════════════════════════════════════════
     SLIDE 11 — MEJORAS FUTURAS
     Layout: dos columnas o lista con v-clicks
     ═══════════════════════════════════════════════ -->

## Posibles mejoras

<v-clicks>

- **Reservas** cuando no hay copias disponibles
- **Sanciones automáticas** por devolución tardía
- **Exportación de préstamos** a CSV (actualmente solo libros)
- **Validación de ISBN-13** al insertar
- **Interfaz gráfica** — JavaFX para escritorio o Spring Boot para web
- **Notificaciones** de préstamos próximos a vencer
- **Roles** — bibliotecario (admin) vs. lector (solo consulta)
- **Búsqueda parcial** por título (actualmente solo autor exacto)

</v-clicks>

---

<!-- ═══════════════════════════════════════════════
     SLIDE 12 — CIERRE
     Layout: center o end
     ═══════════════════════════════════════════════ -->

# ¿Preguntas?

**Repositorio:** `DAM-2025-26 / Programación / proyecto`

**Tecnologías:** Java 17 · Maven · SQLite · JDBC

**Unidades cubiertas:** UT9 Colecciones · UT10 Ficheros · UT12 Base de Datos

---

<!--
═══════════════════════════════════════════════════
GUÍA RÁPIDA PARA EL AGENTE
═══════════════════════════════════════════════════

TEMAS DISPONIBLES (npm install antes de cambiar):
  default | seriph | apple-basic | bricks | penguin | purplin

LAYOUTS POR SLIDE:
  layout: cover        → portada con fondo grande
  layout: two-cols     → dos columnas  (<template #left> / <template #right>)
  layout: center       → contenido centrado
  layout: section      → separador de sección (número grande + subtítulo)
  layout: intro        → intro de presentador
  layout: full         → ocupa toda la pantalla (ideal para imágenes)
  layout: image-right  → imagen a la derecha, texto a la izquierda

COMPONENTES ÚTILES:
  <v-clicks>...</v-clicks>        → revela hijos uno por clic
  <v-click>...</v-click>          → revela un único elemento
  <Toc />                         → índice automático
  ```lang {línea|línea}```        → resalta líneas del bloque de código paso a paso

CÓDIGO CON RESALTADO PROGRESIVO:
  ```java {1|2-4|5,6}
  // El resaltado avanza con cada clic
  ```

NOTAS DEL PRESENTADOR (no visibles en la proyección):
  Añade al final de cada slide:
  <!-- notas aquí -->

COMANDOS DE SLIDEV:
  npx slidev presentacion.md          → modo dev con recarga en caliente
  npx slidev build presentacion.md    → genera HTML estático
  npx slidev export presentacion.md   → exporta a PDF

═══════════════════════════════════════════════════
-->
