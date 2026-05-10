# Documentación ABP — Gestor de Biblioteca

## Datos del proyecto

| Campo | Valor |
|---|---|
| **Nombre del proyecto** | Gestor de Biblioteca |
| **Módulo** | Programación — DAM 1º |
| **Herramienta de colaboración** | Git + GitHub / VS Code |

---

## Fase 1 — Planificación

### Planning de trabajo

| Tarea | Responsable | Fecha estimada | Estado |
|---|---|---|---|
| Diseño del modelo de clases | Equipo | Semana 1 | ✅ Completado |
| Implementación jerarquía `Libro` | Equipo | Semana 1 | ✅ Completado |
| CRUD Usuarios en memoria | Equipo | Semana 2 | ✅ Completado |
| CRUD Libros en memoria | Equipo | Semana 2 | ✅ Completado |
| Gestión de Préstamos | Equipo | Semana 2 | ✅ Completado |
| Exportación a CSV (UT10) | Equipo | Semana 3 | ✅ Completado |
| Sistema de logs en fichero | Equipo | Semana 3 | ✅ Completado |
| Migración a SQLite (UT12) | Equipo | Semana 4 | ✅ Completado |
| Streams y estadísticas | Equipo | Semana 4 | ✅ Completado |
| Documentación y comentarios | Equipo | Semana 5 | ✅ Completado |
| Preparación presentación | Equipo | Semana 5 | 🔄 En curso |

---

## Fase 2 — Decisiones de diseño

### Modelo de datos

#### Jerarquía de clases

```
Libro (abstracta)
├── LibroEnPapel      → añade: ubicacion
└── LibroElectronico  → añade: formato (PDF/EPUB/MOBI), urlDescarga

Usuario              → id, nombre, apellido, email, telefono, fechaRegistro
Prestamo             → id, idUsuario, idLibro, fechaPrestamo,
                       fechaDevolucionPrevista, fechaDevolucionReal, estado
```

Los enums usados:

| Enum | Valores |
|---|---|
| `Genero` | FANTASIA, CIENCIA_FICCION, ROMANCE, MISTERIO, HISTORIA… |
| `Tipo` | PAPEL, ELECTRONICO |
| `Formato` | PDF, EPUB, MOBI |
| `Estado` | ACTIVO, DEVUELTO, RETRASADO |
| `Aviso` | INFO, AVISO, PELIGRO |

#### Atributos mínimos por libro

Título, autor, género, ISBN (único), año de publicación, copias totales, copias disponibles, tipo.

#### Relaciones entre clases

- Un `Usuario` puede tener 0..N `Prestamo`.
- Un `Libro` puede estar en 0..N `Prestamo`.
- Un `Prestamo` pertenece exactamente a 1 `Usuario` y 1 `Libro`.

El esquema completo de tablas está en [`base_de_datos.md`](base_de_datos.md).

---

### Gestión interna de colecciones

Se usa `List<T>` (interfaz) con implementación `ArrayList<T>` para todos los listados devueltos por los DAOs. La decisión se tomó porque:

- **ArrayList** ofrece acceso por índice en O(1) y es la estructura más directa para iterar.
- **No** se usa `HashMap` para el almacenamiento principal porque los datos residen en SQLite; las listas son solo el resultado de consultas.
- **No** se usa `Set` porque los duplicados ya se controlan a nivel de BD (restricción UNIQUE en ISBN y email).

Cuando se requieren estadísticas se usan **Streams** sobre la lista obtenida de la BD (ver sección Ampliación).

---

### Interfaz de usuario

- **Consola** con menús numerados (bucle `do-while` + `switch`).
- Navegación: menú principal → submenú → acción → retorno automático.
- Entrada validada en métodos `leerInt` / `leerString` / `leerGenero` / `leerFormato` / `leerTipo` que repiten la pregunta hasta recibir un valor correcto.

---

### Formato de almacenamiento (UT10)

Se eligió **CSV** para el backup de listados de libros y **texto plano** para los logs.

| Fichero | Formato | Motivo |
|---|---|---|
| `libros_*.csv` | CSV con cabecera | Legible en Excel/LibreOffice, fácil de importar, sin dependencias externas |
| `logs.txt` | Texto con timestamp | Formato simple, apendable con `FileWriter(true)`, lectura directa |

**Ventajas del CSV:**
- No requiere librería externa.
- Importable directamente en hojas de cálculo.
- Fácil de parsear si se necesita recuperar datos.

**Inconvenientes:**
- No soporta estructura anidada (p. ej. préstamo + datos de usuario).
- Los campos con comas o comillas requieren escapado (implementado en `Exportador.escaparCSV()`).

Los filtros disponibles para exportar son: todos, por autor, por género, disponibles, no disponibles.

---

### Control de errores

| Situación | Estrategia |
|---|---|
| Entrada no numérica | `try/catch NumberFormatException` en `leerInt()`, repite la pregunta |
| Enum inválido | `try/catch IllegalArgumentException` en `leerGenero/Formato/Tipo()` |
| Error SQL | `try/catch SQLException`, log con `Aviso.PELIGRO`, retorna `false` / lista vacía |
| Préstamo de libro sin copias | Validación previa en `insertarPrestamo()` |
| Eliminar usuario/libro con préstamos activos | Validación en `App` antes de llamar al DAO |
| Fecha en formato Unix (timestamps SQLite) | `parseFecha()` con fallback `Instant.ofEpochMilli()` |
| Fichero de log no disponible | Mensaje en consola, la app continúa sin interrumpirse |

---

### Escalabilidad

El código está organizado en capas:

```
App.java          ← capa de presentación (menús, entrada/salida)
dao/              ← capa de acceso a datos (DAOusuarios, DAOlibros, DAOprestamos)
Clases/           ← modelo de dominio (entidades puras, sin lógica de BD)
Enum/             ← tipos enumerados compartidos
```

Esta separación permite añadir funcionalidades futuras sin tocar el modelo:

- **Sanciones por retraso**: añadir campo `sancion` a `Prestamo` y método en `DAOprestamos`.
- **Reservas**: nueva entidad `Reserva` con su propio DAO.
- **Notificaciones**: nuevo servicio que consulta préstamos próximos a vencer.
- **Interfaz web**: los DAOs no cambian; solo se sustituye `App.java` por controladores REST.

---

### Migración a base de datos (UT12)

Se usa **SQLite** via JDBC (`jdbc:sqlite:biblioteca.db`). La decisión frente a otras opciones:

| BD | Motivo para/contra |
|---|---|
| SQLite | ✅ Sin servidor, fichero único, ideal para escritorio, ya disponible en Maven |
| MySQL | ❌ Requiere servidor externo, más complejo para una app de escritorio |
| H2 | ✅ Alternativa en memoria, pero menos estándar fuera de entornos de test |

**Patrón DAO:** cada entidad tiene su clase DAO con métodos CRUD y consultas propias. `Conexion.getConexion()` abre una conexión nueva por operación (SQLite lo soporta eficientemente).

**Transacciones:** `insertarPrestamo` y `devolverPrestamo` usan `setAutoCommit(false)` + `commit/rollback` para garantizar que el libro y el préstamo se actualizan juntos o ninguno.

El esquema completo y el mapeo clases→tablas está en [`base_de_datos.md`](base_de_datos.md).

---

### Rediseño de interfaz

Se mantiene la interfaz de **consola**. La arquitectura DAO facilita una migración futura a web (Spring Boot / Jakarta EE) sin cambiar la capa de datos.

---

## Propuesta de ampliación — Streams (UT9+)

Se usan `Stream` en los siguientes casos:

| Operación | Método | Stream usado |
|---|---|---|
| Filtrar libros disponibles | `DAOlibros.librosDisponibles()` | `.filter()` |
| Buscar por autor | `DAOlibros.buscarPorAutor()` | `.filter()` |
| Buscar por género | `DAOlibros.buscarPorGenero()` | `.filter()` |
| Buscar libro por ID | `DAOlibros.buscarLibroPorId()` | `.filter().findFirst()` |
| Buscar usuario por ID | `DAOusuarios.buscarUsuarioPorId()` | `.filter().findFirst()` |
| Préstamos activos de usuario | `DAOprestamos.prestamosActivosDeUnUsuario()` | `.filter().toList()` |
| Préstamos activos de libro | `DAOprestamos.prestamosActivosDeUnLibro()` | `.filter().toList()` |
| Libro más prestado | `DAOprestamos.libroMasPrestado()` | `.collect(groupingBy).entrySet().stream().max()` |
| Género con más préstamos | `DAOprestamos.generoConMasPrestamos()` | `.map().filter().collect(groupingBy).max()` |
| Libros no disponibles (CSV) | `App.menuExportarFichero()` | `.filter(copias == 0).toList()` |

---

## Dificultades encontradas

Ver [`dificultades detectadas.md`](dificultades%20detectadas.md) para el detalle completo.

Resumen:

- **Timestamps Unix en SQLite**: fechas guardadas como milisegundos (`long`) en lugar de `TEXT ISO`. Resuelto con método `parseFecha()` que intenta `LocalDate.parse()` y hace fallback a `Instant.ofEpochMilli()`.
- **Colecciones vs BD**: se descartó mantener listas en memoria sincronizadas con la BD; toda operación va directamente a SQLite para evitar desincronización.
- **Concurrencia con `Conexion`**: cada DAO abre su propia conexión por operación en lugar de compartir una instancia estática, eliminando problemas de estado compartido.

---

## Posibles mejoras

- Validación de ISBN (formato ISBN-13).
- Sistema de reservas cuando no hay copias disponibles.
- Exportación de préstamos a CSV además de libros.
- Interfaz gráfica (JavaFX) o web (Spring Boot).
- Notificaciones de devoluciones próximas a vencer.
- Sanciones automáticas por retraso.
- Autenticación de usuarios (rol bibliotecario vs. lector).
