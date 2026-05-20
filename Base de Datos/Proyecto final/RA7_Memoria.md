# RA7 – Gestión de información en bases de datos no relacionales

**Módulo:** Bases de Datos – 1º DAM
**Curso:** 2025/26
**Resultado de aprendizaje:** RA7 – Gestiona la información almacenada en bases de datos no relacionales, evaluando y utilizando las posibilidades que proporciona el sistema gestor.

**Autores:**
- Nombre1 Apellido1
- Nombre2 Apellido2
- Nombre3 Apellido3

> Documento principal del trabajo. Se entrega como PDF dentro del zip `RA7_nombre1_nombre2_nombre3.zip`.

---

## Índice

1. Introducción
2. Bloque A – Caracterización de las bases de datos no relacionales
3. Bloque B – Tipos principales de bases de datos no relacionales
4. Bloque C – Elementos utilizados en estas bases de datos
5. Bloque D – Formas de gestión según el tipo
6. Bloque E – Práctica con MongoDB Atlas + Compass
7. Conclusiones
8. Reparto de tareas
9. Bibliografía

> A lo largo del Bloque E aparecen bloques destacados con `**[PASO — BORRAR AL TERMINAR]**`. Son instrucciones para nosotros: al completar el paso se sustituyen por la captura correspondiente. En la versión final que se entrega como PDF no debe quedar ninguno.

---

## 1. Introducción

Hasta ahora hemos trabajado con bases de datos relacionales (MySQL): tablas, filas, claves y SQL. En este trabajo nos centramos en las bases de datos **no relacionales** (NoSQL): qué son, qué tipos hay, qué elementos las componen y cómo se gestiona la información en ellas.

Para la parte práctica usamos **MongoDB**, una base de datos documental, desplegada en la nube con **MongoDB Atlas** en su capa gratuita (cluster M0 en AWS Frankfurt). Nos conectamos con **MongoDB Compass** (cliente gráfico de escritorio) usando la cadena de conexión:

```
mongodb+srv://<usuario>:<password>@dam.pb0dxmb.mongodb.net/
```

- **Usuario:** `admin`
- **Password:** `123`

El cluster ya viene con varias **bases de datos de muestra** que proporciona MongoDB (`sample_mflix`, `sample_airbnb`, `sample_analytics`, etc.), lo que nos permite practicar consultas y agregaciones sobre datos reales sin tener que cargar nada nosotros.

---

## 2. Bloque A – Caracterización de las bases de datos no relacionales

### 2.1 Qué es una base de datos no relacional

Una base de datos no relacional **no guarda la información en tablas** ni usa obligatoriamente SQL. En su lugar utiliza estructuras flexibles (documentos, pares clave-valor, grafos, familias de columnas).

Surgieron porque las aplicaciones modernas generan datos en gran volumen, muy variados y en constante cambio, escenarios en los que el modelo relacional clásico se queda corto.

### 2.2 Características principales

- **Sin esquema fijo:** documentos del mismo tipo pueden tener campos distintos.
- **Escalabilidad horizontal:** se reparten los datos entre varios servidores (*sharding*) en lugar de necesitar uno más potente.
- **Alto rendimiento** con grandes volúmenes de lectura/escritura.
- **Modelo cercano a la aplicación:** un documento JSON se parece a un objeto del programa.
- **Replicación y alta disponibilidad** integradas.
- **Consistencia eventual (BASE)** en lugar de ACID estricto. Aplican el teorema **CAP**.

### 2.3 Comparativa relacional vs no relacional

| Aspecto | Relacional (SQL) | No relacional (NoSQL) |
|---|---|---|
| Estructura | Tablas, filas, columnas | Documentos, clave-valor, grafos, columnas |
| Esquema | Fijo y definido antes | Flexible / sin esquema |
| Lenguaje | SQL estándar | API propia por producto |
| Escalado | Vertical | Horizontal |
| Relaciones | Claves ajenas, JOIN | Documentos anidados o referencias |
| Transacciones | ACID fuerte | BASE / consistencia eventual |
| Caso típico | Banca, ERP | Big data, tiempo real, datos variables |

### 2.4 Ventajas e inconvenientes

**Ventajas:** flexibilidad de esquema, escalado horizontal, alto rendimiento, encajan con desarrollo ágil.

**Inconvenientes:** no hay un lenguaje estándar, menos garantías de consistencia inmediata, las relaciones complejas son más incómodas y hay menos madurez de herramientas.

> No hay una opción mejor que otra: se elige según el problema. Datos muy estructurados y transacciones críticas → relacional. Datos variables, escala enorme → no relacional.

---

## 3. Bloque B – Tipos principales de bases de datos no relacionales

Se clasifican en cuatro grandes familias.

### 3.1 Clave-valor (Key-Value)
- **Cómo funciona:** pares `clave → valor`, como un diccionario.
- **Fuerte / débil:** muy rápida / no se consulta por contenido.
- **Ejemplos:** Redis, Amazon DynamoDB.
- **Uso típico:** cachés, sesiones, carritos.

### 3.2 Documental
- **Cómo funciona:** documentos JSON/BSON agrupados en colecciones.
- **Fuerte / débil:** flexible y consultable por cualquier campo / puede duplicar datos.
- **Ejemplos:** **MongoDB** (la que usamos), CouchDB, Firestore.
- **Uso típico:** catálogos, APIs, CMS.

### 3.3 Columnar / familia de columnas
- **Cómo funciona:** datos guardados por columnas, agrupados en familias.
- **Fuerte / débil:** analítica masiva muy rápida / modelado complejo.
- **Ejemplos:** Cassandra, HBase, Bigtable.
- **Uso típico:** big data, series temporales, logs.

### 3.4 Grafos
- **Cómo funciona:** nodos y relaciones (aristas) entre ellos.
- **Fuerte / débil:** ideal para relaciones / no ideal para volúmenes planos.
- **Ejemplos:** Neo4j, Amazon Neptune.
- **Uso típico:** redes sociales, recomendaciones, detección de fraude.

### 3.5 Tabla resumen

| Tipo | Modelo | Ejemplo | Mejor para |
|---|---|---|---|
| Clave-valor | clave → valor | Redis | Caché, sesiones |
| Documental | documentos JSON | MongoDB | Datos variables, APIs |
| Columnar | familias de columnas | Cassandra | Big data, analítica |
| Grafos | nodos y aristas | Neo4j | Relaciones, redes |

> **Por qué MongoDB:** documental (la familia más versátil), capa gratuita en Atlas, herramientas gráficas accesibles y mucha documentación.

---

## 4. Bloque C – Elementos utilizados en estas bases de datos

### 4.1 Equivalencias con el modelo relacional

| Relacional | MongoDB (documental) |
|---|---|
| Base de datos | Base de datos |
| Tabla | Colección |
| Fila / registro | Documento |
| Columna / campo | Campo |
| Clave primaria | Campo `_id` |
| Índice | Índice |
| JOIN | Documentos anidados o `$lookup` |

### 4.2 Elementos clave

- **Documento:** unidad básica, objeto tipo JSON con pares `campo: valor`.
- **Colección:** conjunto de documentos (equivale a una tabla sin esquema).
- **Base de datos:** conjunto de colecciones.
- **BSON:** formato binario en el que MongoDB guarda los documentos (JSON con más tipos: fechas, `ObjectId`, binarios…).
- **Campo `_id`:** identificador único por documento. Si no se indica, MongoDB genera un `ObjectId`. Hace de clave primaria.
- **Tipos de datos:** texto, número, booleano, fecha, array, documento anidado, `null`, `ObjectId`…
- **Documentos anidados y arrays:** permiten meter subobjetos o listas dentro de un documento, evitando muchos JOINs.
- **Índices:** estructuras para acelerar búsquedas (el de `_id` se crea solo).
- **Réplica (*Replica Set*):** conjunto de copias para tolerancia a fallos (Atlas lo monta automáticamente).
- **Sharding:** reparto de una colección entre varios servidores para escalar (no se usa en el free tier).

### 4.3 Ejemplo de documento (sacado de `sample_mflix.movies`)

```json
{
  "_id": ObjectId("..."),
  "title": "The Matrix",
  "year": 1999,
  "genres": ["Action", "Sci-Fi"],
  "runtime": 136,
  "cast": ["Keanu Reeves", "Laurence Fishburne", "Carrie-Anne Moss"],
  "imdb": { "rating": 8.7, "votes": 1500000 }
}
```

Se ven los elementos típicos: `_id`, campos simples, arrays (`genres`, `cast`) y subdocumento (`imdb`).

---

## 5. Bloque D – Formas de gestión de la información según el tipo

Cada familia NoSQL tiene su propia forma de insertar y consultar.

| Tipo | Cómo se gestionan los datos | Lenguaje / API |
|---|---|---|
| Clave-valor (Redis) | Comandos `SET clave valor` / `GET clave` | Comandos propios |
| Documental (MongoDB) | `insertOne`, `find`, `updateOne`, agregaciones | MQL (MongoDB Query Language) |
| Columnar (Cassandra) | Sentencias parecidas a SQL sobre familias de columnas | CQL |
| Grafos (Neo4j) | Patrones sobre nodos y aristas | Cypher |

**En MongoDB concretamente** se gestiona mediante:

- **CRUD:** `insertOne`/`insertMany`, `find`, `updateOne`/`updateMany`, `deleteOne`/`deleteMany`.
- **Operadores de consulta:** `$gt`, `$lt`, `$in`, `$and`, `$or`, `$regex`…
- **Framework de agregación:** etapas encadenadas (`$match`, `$group`, `$sort`, `$project`, `$unwind`) para consultas avanzadas, equivalente al `GROUP BY` de SQL.
- **Índices:** simples, compuestos, de texto, geoespaciales.
- **Importación/exportación:** JSON y CSV.
- **Seguridad:** usuarios, roles y autenticación.

En NoSQL **cada gestor tiene su propio lenguaje**, no existe un SQL común. Eso es precisamente lo que valora el apartado (e): saber usar las herramientas concretas del sistema gestor elegido.

---

## 6. Bloque E – Práctica con MongoDB Atlas + Compass

> Esta es la demostración práctica (apartado **e** del enunciado). El cluster ya está creado y compartido (free tier M0 en AWS Frankfurt) y trae las bases de datos de muestra. Trabajaremos sobre `sample_mflix` (consulta) y sobre una base nuestra `practica_ra7` (escritura), para no contaminar las bases compartidas.

### 6.1 Conexión al cluster con Compass

Abrimos MongoDB Compass y pegamos la *connection string* del cluster compartido (sustituyendo `<usuario>` por `admin` y `<password>` por `123`):

```
mongodb+srv://<usuario>:<password>@dam.pb0dxmb.mongodb.net/
```

Al pulsar **Connect**, Compass se conecta al cluster y muestra todas las bases de datos disponibles en el panel izquierdo (las `sample_*` y las que hayamos creado).

> **[PASO 1 — BORRAR AL TERMINAR]**
> 1. Abrir **MongoDB Compass**.
> 2. En la pantalla inicial, pegar la cadena de conexión de arriba en el campo **URI**.
> 3. Pulsar **Connect**.
> 4. **Captura 1:** Compass conectado, con el panel izquierdo mostrando las bases `sample_*`. Conviene que se vea la barra de tareas con la fecha/hora del sistema.
> _Sustituir este bloque por la captura cuando esté hecho._

`![Captura 1](capturas/captura-01.png)`

### 6.2 Exploración de las bases de datos de muestra

`sample_mflix` contiene varias colecciones (`movies`, `comments`, `users`, `theaters`…). La que vamos a usar es **`movies`**, con miles de películas reales y campos ricos (título, año, géneros, reparto, valoración IMDb…).

> **[PASO 2 — BORRAR AL TERMINAR]**
> 1. En el panel izquierdo, expandir **`sample_mflix`** y pulsar la colección **`movies`**.
> 2. Esperar a que Compass cargue documentos. Pulsar uno cualquiera para ver su estructura.
> 3. **Captura 2:** vista de `sample_mflix.movies` con varios documentos visibles, mostrando campos como `title`, `year`, `genres`, `imdb`.
> _Sustituir este bloque por la captura._

`![Captura 2](capturas/captura-02.png)`

### 6.3 Consultas (READ) con filtros en Compass

Todas estas consultas se ejecutan en la **barra de filtros** de la pestaña **Documents** de la colección `movies`. Se introduce el filtro en formato JSON y se pulsa **Find**.

| Nº | Filtro | Qué pide |
|---|---|---|
| C1 | `{ year: 1999 }` | Películas estrenadas en 1999. |
| C2 | `{ "imdb.rating": { $gt: 8.5 } }` | Películas con valoración IMDb > 8.5. |
| C3 | `{ genres: "Comedy" }` | Películas de género Comedia (filtra dentro de un array). |
| C4 | `{ genres: { $in: ["Action", "Adventure"] } }` | Acción **o** Aventura. |
| C5 | `{ year: { $gte: 2000, $lte: 2005 }, "imdb.rating": { $gt: 8 } }` | Buenas películas entre 2000 y 2005. |
| C6 | `{ title: { $regex: "matrix", $options: "i" } }` | Títulos que contienen "matrix" (insensible a mayúsculas). |

Además podemos usar el **Sort** (`{ "imdb.rating": -1 }`) y **Limit** (`10`) para ordenar y limitar resultados (equivalente a `ORDER BY` y `LIMIT` en SQL).

> **[PASO 3 — BORRAR AL TERMINAR]**
> 1. En la pestaña **Documents** de `sample_mflix.movies`, ejecutar la consulta **C2** (`{ "imdb.rating": { $gt: 8.5 } }`).
> 2. **Captura 3:** filtro pegado y resultados visibles abajo.
> 3. Ejecutar **C5** (consulta con varios criterios).
> 4. **Captura 4:** filtro de C5 con resultados.
> 5. Ejecutar **C6** (con `$regex`) ordenando por `{ "imdb.rating": -1 }` y `Limit: 10`.
> 6. **Captura 5:** filtro + sort + limit aplicados con resultados.
> _Sustituir este bloque por las tres capturas._

`![Captura 3](capturas/captura-03.png)`
`![Captura 4](capturas/captura-04.png)`
`![Captura 5](capturas/captura-05.png)`

### 6.4 Creación de una base de datos propia para escritura

Para no modificar las bases de muestra (son compartidas) creamos la nuestra: **`practica_ra7`** con la colección **`productos`**.

> **[PASO 4 — BORRAR AL TERMINAR]**
> 1. En el panel izquierdo de Compass, pulsar **+** junto a *Databases* → **Create Database**.
> 2. Database name: `practica_ra7`. Collection name: `productos`. **Create Database**.
> 3. **Captura 6:** base `practica_ra7` con la colección `productos` recién creada.
> _Sustituir este bloque por la captura._

`![Captura 6](capturas/captura-06.png)`

### 6.5 Inserción de documentos (CREATE)

Dentro de `practica_ra7.productos`, **Add Data → Insert Document** y pegamos en la ventana de inserción:

```json
[
  { "nombre": "Teclado mecánico", "precio": 49.90, "stock": 12, "etiquetas": ["gaming","USB"] },
  { "nombre": "Ratón óptico",     "precio": 12.50, "stock": 40, "etiquetas": ["USB"] },
  { "nombre": "Monitor 24\"",     "precio": 129.99,"stock": 5,  "etiquetas": ["oficina"] },
  { "nombre": "Webcam HD",        "precio": 25.00, "stock": 0,  "etiquetas": ["videollamada"] },
  { "nombre": "Auriculares BT",   "precio": 39.95, "stock": 18, "etiquetas": ["audio","bluetooth"] }
]
```

(Compass acepta directamente un array de documentos en la inserción.)

> **[PASO 5 — BORRAR AL TERMINAR]**
> 1. Entrar en `practica_ra7.productos` → **Add Data → Insert Document**.
> 2. Borrar la plantilla, pegar el array JSON de arriba y pulsar **Insert**.
> 3. **Captura 7:** los 5 documentos insertados visibles en la colección, con su `_id` autogenerado.
> _Sustituir este bloque por la captura._

`![Captura 7](capturas/captura-07.png)`

### 6.6 Actualización (UPDATE) y borrado (DELETE) desde Compass

Compass permite hacerlo gráficamente con los iconos **lápiz** (editar) y **papelera** (borrar) que aparecen al pasar el ratón sobre un documento.

- **Update:** localizar el documento `Webcam HD`, pulsar el lápiz, cambiar `stock` de 0 a 20 y **Update**.
- **Delete:** localizar `Ratón óptico`, pulsar la papelera y confirmar.

> **[PASO 6 — BORRAR AL TERMINAR]**
> 1. Pasar el ratón sobre el documento `Webcam HD` → **lápiz** → cambiar `stock` a `20` → **Update**.
> 2. **Captura 8:** el documento `Webcam HD` con `stock: 20` ya actualizado.
> 3. Pasar el ratón sobre `Ratón óptico` → **papelera** → **Delete**.
> 4. **Captura 9:** la colección sin el documento `Ratón óptico` (4 documentos restantes).
> _Sustituir este bloque por las dos capturas._

`![Captura 8](capturas/captura-08.png)`
`![Captura 9](capturas/captura-09.png)`

### 6.7 Índices

Cada colección lleva un índice automático sobre `_id`. Podemos crear otros para acelerar consultas sobre campos concretos. Lo hacemos sobre `practica_ra7.productos`, en la pestaña **Indexes → Create Index**:

- **Campo:** `nombre`
- **Tipo:** `1` (ascendente)

> **[PASO 7 — BORRAR AL TERMINAR]**
> 1. En `practica_ra7.productos` → pestaña **Indexes** → **Create Index**.
> 2. Field name: `nombre`. Type: `1` (Asc). **Create Index**.
> 3. **Captura 10:** pestaña **Indexes** mostrando los dos índices: `_id_` y `nombre_1`.
> _Sustituir este bloque por la captura._

`![Captura 10](capturas/captura-10.png)`

### 6.8 Framework de agregación (consulta avanzada)

Volvemos a `sample_mflix.movies` y entramos en la pestaña **Aggregations** de Compass. Vamos a calcular el **número de películas y la valoración media por género** para películas a partir del año 2000:

| Etapa | Operador | Contenido |
|---|---|---|
| 1 | `$match` | `{ year: { $gte: 2000 }, "imdb.rating": { $exists: true } }` |
| 2 | `$unwind` | `"$genres"` |
| 3 | `$group` | `{ _id: "$genres", peliculas: { $sum: 1 }, ratingMedio: { $avg: "$imdb.rating" } }` |
| 4 | `$sort` | `{ ratingMedio: -1 }` |
| 5 | `$limit` | `10` |

Compass tiene una interfaz visual: vas añadiendo etapas con el botón **Add Stage**, eliges el operador en el desplegable y pegas el contenido. A la derecha muestra una vista previa de los resultados parciales por etapa.

> **[PASO 8 — BORRAR AL TERMINAR]**
> 1. Entrar en `sample_mflix.movies` → pestaña **Aggregations**.
> 2. Añadir las 5 etapas indicadas en la tabla, en el mismo orden.
> 3. **Captura 11:** vista del pipeline completo con las 5 etapas y los resultados a la derecha (géneros ordenados por nota media).
> _Sustituir este bloque por la captura._

`![Captura 11](capturas/captura-11.png)`

### 6.9 Exportación e importación de datos

Compass permite exportar e importar colecciones en JSON o CSV (gestión de información de entrada/salida del sistema gestor).

**Exportar** `practica_ra7.productos`:

> **[PASO 9 — BORRAR AL TERMINAR]**
> 1. En `practica_ra7.productos`, menú **... → Export Collection** (o botón **Export Data**).
> 2. Formato **JSON**, exportar todos los documentos, guardar como `productos.json` en la carpeta del trabajo.
> 3. **Captura 12:** ventana de exportación con el formato JSON elegido y la confirmación de éxito.
> _Sustituir este bloque por la captura._

`![Captura 12](capturas/captura-12.png)`

**Importar** el mismo fichero a una colección nueva para demostrar la importación:

> **[PASO 10 — BORRAR AL TERMINAR]**
> 1. En `practica_ra7` crear una colección nueva llamada `productos_copia`.
> 2. Dentro de ella, **Add Data → Import JSON or CSV file** → seleccionar el `productos.json` exportado en el paso anterior.
> 3. Pulsar **Import**.
> 4. **Captura 13:** la colección `productos_copia` con los documentos importados.
> _Sustituir este bloque por la captura._

`![Captura 13](capturas/captura-13.png)`

### 6.10 Validación de esquema (opcional, nota alta)

Aunque MongoDB no obliga a tener esquema, permite definir reglas de validación con `$jsonSchema`. En Compass: colección `practica_ra7.productos` → pestaña **Validation → Add Rule**, pegar:

```json
{
  "$jsonSchema": {
    "bsonType": "object",
    "required": ["nombre", "precio"],
    "properties": {
      "nombre": { "bsonType": "string" },
      "precio": { "bsonType": "double" },
      "stock":  { "bsonType": "int" }
    }
  }
}
```

Acción al fallar: **Error**. Tras guardar, intentamos insertar un documento sin `precio`: MongoDB rechaza la inserción.

> **[PASO 11 (OPCIONAL) — BORRAR AL TERMINAR]**
> 1. En `practica_ra7.productos` → pestaña **Validation** → **Add Rule** → pegar el JSON de arriba → **Update**.
> 2. Intentar insertar `{ "nombre": "Producto sin precio" }` (falta el campo `precio`).
> 3. **Captura 14:** mensaje de error de validación que muestra Compass al rechazar la inserción.
> _Sustituir este bloque por la captura._

`![Captura 14](capturas/captura-14.png)`

---

## 7. Conclusiones

- Las bases de datos no relacionales **no sustituyen** a las relacionales: resuelven problemas distintos (escala, flexibilidad, datos variables).
- Existen **cuatro familias** (clave-valor, documental, columnar, grafos) y cada una gestiona la información de forma distinta, con su propio lenguaje o API.
- **MongoDB**, al ser documental, es muy intuitiva viniendo del modelo relacional, y **Atlas + Compass** permite practicar sin instalar nada en local.
- En la práctica hemos cubierto un **CRUD completo, consultas con operadores, índices, agregaciones, import/export y validación de esquema**, que es exactamente lo que pide el apartado (e): usar las herramientas del sistema gestor para gestionar la información.
- Lo más distinto respecto a SQL: la **flexibilidad de esquema** y que **cada gestor tiene su propio lenguaje** (no hay un equivalente universal a SQL).

---

## 8. Reparto de tareas

> Rellenar según el caso real del grupo. Cada alumno debe poder defender los puntos que ha hecho.

| Apartado | Responsable | Defendido por |
|---|---|---|
| A – Caracterización | Nombre1 | Nombre1 |
| B – Tipos de NoSQL | Nombre2 | Nombre2 |
| C – Elementos | Nombre3 | Nombre3 |
| D – Formas de gestión | Nombre1 | Nombre1 |
| E – Práctica (Atlas/Compass) | Nombre2 y Nombre3 | Ambos |
| Presentación | Los tres | Los tres |

---

## 9. Bibliografía

- Documentación oficial de MongoDB – https://www.mongodb.com/docs/
- MongoDB Atlas – https://www.mongodb.com/atlas
- MongoDB Compass – https://www.mongodb.com/products/tools/compass
- Bases de datos de muestra de Atlas – https://www.mongodb.com/docs/atlas/sample-data/
- Apuntes del módulo de Bases de Datos (campus virtual), Unidad UT7.

---

> **Entrega:** comprimir en `RA7_nombre1_nombre2_nombre3.zip` este documento exportado a **PDF** + carpeta `capturas/` + (opcional) el `productos.json` exportado. El documento principal es el PDF.
