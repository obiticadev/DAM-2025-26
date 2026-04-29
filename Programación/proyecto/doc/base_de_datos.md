# Esquema de Base de Datos — Gestor de Biblioteca

## Tablas

---

### `libros`

Tabla base para todos los libros (papel y electrónicos).

| Columna              | Tipo           | Restricciones              |
|----------------------|----------------|----------------------------|
| `id`                 | INT            | PK, AUTO_INCREMENT         |
| `titulo`             | VARCHAR(255)   | NOT NULL                   |
| `autor`              | VARCHAR(255)   | NOT NULL                   |
| `genero`             | VARCHAR(100)   | NOT NULL                   |
| `isbn`               | VARCHAR(20)    | NOT NULL, UNIQUE           |
| `anio_publicacion`   | INT            | NOT NULL                   |
| `copias_totales`     | INT            | NOT NULL, DEFAULT 1        |
| `copias_disponibles` | INT            | NOT NULL                   |
| `tipo`               | ENUM('papel','electronico') | NOT NULL      |

> `tipo` actúa como discriminador para la herencia entre `libros_papel` y `libros_electronicos`.

---

### `libros_papel`

Extiende `libros` con atributos propios del libro físico.

| Columna      | Tipo         | Restricciones              |
|--------------|--------------|----------------------------|
| `id`         | INT          | PK, FK → `libros.id`       |
| `ubicacion`  | VARCHAR(100) |                            |

---

### `libros_electronicos`

Extiende `libros` con atributos propios del libro digital.

| Columna        | Tipo         | Restricciones              |
|----------------|--------------|----------------------------|
| `id`           | INT          | PK, FK → `libros.id`       |
| `formato`      | VARCHAR(20)  | (PDF, EPUB, MOBI…)         |
| `url_descarga` | VARCHAR(500) |                            |

---

### `usuarios`

Personas que pueden realizar préstamos.

| Columna           | Tipo         | Restricciones        |
|-------------------|--------------|----------------------|
| `id`              | INT          | PK, AUTO_INCREMENT   |
| `nombre`          | VARCHAR(100) | NOT NULL             |
| `apellidos`       | VARCHAR(150) | NOT NULL             |
| `email`           | VARCHAR(200) | UNIQUE               |
| `telefono`        | VARCHAR(20)  |                      |
| `fecha_registro`  | DATE         | NOT NULL             |

---

### `prestamos`

Registra cada préstamo y su devolución.

| Columna                    | Tipo         | Restricciones                             |
|----------------------------|--------------|-------------------------------------------|
| `id`                       | INT          | PK, AUTO_INCREMENT                        |
| `id_usuario`               | INT          | NOT NULL, FK → `usuarios.id`              |
| `id_libro`                 | INT          | NOT NULL, FK → `libros.id`                |
| `fecha_prestamo`           | DATE         | NOT NULL                                  |
| `fecha_devolucion_prevista`| DATE         | NOT NULL                                  |
| `fecha_devolucion_real`    | DATE         | NULL (si aún no se ha devuelto)           |
| `estado`                   | ENUM('activo','devuelto','retrasado') | NOT NULL, DEFAULT 'activo' |

---

## Relaciones

```
libros (1) ──── (0..1) libros_papel
libros (1) ──── (0..1) libros_electronicos

usuarios (1) ──< (0..N) prestamos
libros   (1) ──< (0..N) prestamos
```

---

## Mapeo clases → tablas

| Clase Java          | Tabla(s)                              |
|---------------------|---------------------------------------|
| `Libro`             | `libros`                              |
| `LibroEnPapel`      | `libros` + `libros_papel`             |
| `LibroElectronico`  | `libros` + `libros_electronicos`      |
| `Usuario`           | `usuarios`                            |
| `Prestamo`          | `prestamos`                           |

---

## Consultas relevantes previstas

```sql
-- Libros disponibles
SELECT * FROM libros WHERE copias_disponibles > 0;

-- Filtrar por autor
SELECT * FROM libros WHERE autor = ?;

-- Filtrar por género
SELECT * FROM libros WHERE genero = ?;

-- Préstamos activos de un usuario
SELECT l.titulo, p.fecha_prestamo, p.fecha_devolucion_prevista
FROM prestamos p
JOIN libros l ON p.id_libro = l.id
WHERE p.id_usuario = ? AND p.estado = 'activo';

-- Libro más prestado
SELECT id_libro, COUNT(*) AS total
FROM prestamos
GROUP BY id_libro
ORDER BY total DESC
LIMIT 1;

-- Género con más préstamos
SELECT l.genero, COUNT(*) AS total
FROM prestamos p
JOIN libros l ON p.id_libro = l.id
GROUP BY l.genero
ORDER BY total DESC;
```
