# RA7 – Gestión de información en bases de datos no relacionales

**Módulo:** Bases de Datos – 1º DAM
**Curso:** 2025/26
**Resultado de aprendizaje:** RA7 – Gestiona la información almacenada en bases de datos no relacionales, evaluando y utilizando las posibilidades que proporciona el sistema gestor.

**Autores:**
- Nombre1 Apellido1
- Nombre2 Apellido2

> Documento principal del trabajo. Se entrega como PDF dentro del zip `RA7_nombre1_nombre2.zip`.

---

## Índice

1. Introducción y objetivos
2. Bloque A – Caracterización de las bases de datos no relacionales
3. Bloque B – Evaluación de los principales tipos de bases de datos no relacionales
4. Bloque C – Elementos utilizados en estas bases de datos
5. Bloque D – Formas de gestión de la información según el tipo
6. Bloque E – Práctica con el sistema gestor (MongoDB Atlas + Compass + mongosh)
7. Conclusiones
8. Reparto de tareas entre los miembros del grupo
9. Bibliografía y recursos

> **Nota sobre las capturas:** a lo largo del Bloque E aparecen marcas del tipo `[CAPTURA n]`. En esas posiciones hay que pegar la captura de pantalla real hecha por nosotros (se ve la fecha/hora del sistema o el nombre de usuario del cluster) para demostrar que el trabajo lo hemos realizado nosotros y no otra persona.

---

## 1. Introducción y objetivos

Durante todo el curso hemos trabajado con bases de datos relacionales (MySQL): tablas, filas, claves primarias y ajenas, y el lenguaje SQL. En este trabajo damos un paso distinto y nos metemos en el mundo de las bases de datos **no relacionales** o **NoSQL** (*Not Only SQL*).

El objetivo es entender **qué son**, **qué tipos hay**, **qué elementos las forman** y, sobre todo, **practicar de verdad** con una de ellas. Para la parte práctica hemos elegido **MongoDB**, que es una base de datos documental, y la hemos montado en la nube con **MongoDB Atlas** usando la capa gratuita (*free tier* M0). Para trabajar contra ella usamos dos herramientas:

- **MongoDB Compass:** programa de escritorio con interfaz gráfica. Es el más cómodo para empezar porque se ve todo (colecciones, documentos, índices) sin escribir comandos.
- **mongosh:** la consola/terminal de MongoDB. La usamos para demostrar que también sabemos gestionar los datos por comandos, que es lo que se valora en el apartado (e).
- **Atlas Data Explorer:** el visor que trae la propia web de Atlas en el navegador, como alternativa si no quisiéramos instalar nada.

> Hemos comprobado que **Compass + mongosh** es la combinación más cómoda: Compass para ver y mongosh para gestionar. No hace falta otra herramienta.

---

## 2. Bloque A – Caracterización de las bases de datos no relacionales

### 2.1 ¿Qué es una base de datos no relacional?

Una base de datos no relacional es aquella que **no guarda la información en tablas con filas y columnas** ni usa obligatoriamente SQL como lenguaje. En lugar de tablas, usa estructuras más flexibles (documentos, pares clave-valor, grafos…).

Surgieron porque las aplicaciones modernas (redes sociales, IoT, big data, apps móviles) generan **muchísimos datos, muy variados y que cambian de forma constante**, y el modelo relacional clásico se queda corto en algunos de esos escenarios.

### 2.2 Características principales

- **Sin esquema fijo (*schema-less* / esquema flexible):** dos registros de la misma colección pueden tener campos distintos. No hay que definir la estructura por adelantado como en `CREATE TABLE`.
- **Escalabilidad horizontal:** en vez de comprar un servidor más potente (escalado vertical), se reparten los datos entre varios servidores baratos (*sharding*). Crecer es añadir máquinas.
- **Alto rendimiento en grandes volúmenes:** están pensadas para leer y escribir mucho y rápido.
- **Modelo de datos cercano a la aplicación:** un documento JSON se parece mucho a un objeto del programa, así que se reduce el desfase objeto-relacional.
- **Replicación y alta disponibilidad:** suelen venir preparadas para tener copias (réplicas) y seguir funcionando si una máquina cae.
- **Consistencia normalmente eventual:** muchas siguen el teorema **CAP** y priorizan disponibilidad y tolerancia a particiones frente a consistencia inmediata (modelo **BASE** en vez de **ACID** estricto).

### 2.3 Comparativa rápida relacional vs no relacional

| Aspecto | Relacional (SQL) | No relacional (NoSQL) |
|---|---|---|
| Estructura | Tablas, filas, columnas | Documentos, clave-valor, grafos, columnas |
| Esquema | Fijo y definido antes | Flexible / sin esquema |
| Lenguaje | SQL estándar | API propia por producto |
| Escalado | Sobre todo vertical | Sobre todo horizontal |
| Relaciones | Claves ajenas, JOIN | Documentos anidados o referencias |
| Transacciones | ACID fuerte | Normalmente BASE / consistencia eventual |
| Caso típico | Banca, ERP, datos muy estructurados | Big data, tiempo real, datos variables |

### 2.4 Ventajas e inconvenientes

**Ventajas:** flexibilidad de esquema, escalan muy bien, buen rendimiento con datos enormes, encajan con desarrollo ágil.

**Inconvenientes:** no hay un estándar como SQL (cada producto es un mundo), menos garantías de consistencia inmediata, las relaciones complejas (muchos JOIN) son más incómodas, y hay menos madurez de herramientas que en el mundo relacional.

> Conclusión del bloque: ni una es mejor que otra; **se elige según el problema**. Datos muy estructurados y transacciones críticas → relacional. Datos variables, volumen enorme y necesidad de escalar → no relacional.

---

## 3. Bloque B – Evaluación de los principales tipos de bases de datos no relacionales

No todas las NoSQL son iguales. Se clasifican en **cuatro grandes familias**.

### 3.1 Clave-valor (Key-Value)

- **Cómo funciona:** una tabla gigante de pares `clave → valor`, como un diccionario. Se accede por la clave y se obtiene el valor.
- **Puntos fuertes:** simplísima y rapidísima.
- **Punto débil:** no se puede consultar por el contenido del valor, solo por la clave.
- **Ejemplos:** **Redis**, Amazon DynamoDB, Riak.
- **Uso típico:** cachés, sesiones de usuario, carritos de la compra, rankings.

### 3.2 Documental (Document)

- **Cómo funciona:** guarda **documentos** (normalmente JSON/BSON) agrupados en **colecciones**. Cada documento puede tener su propia estructura.
- **Puntos fuertes:** muy flexible, el documento se parece a un objeto del programa, permite consultas por cualquier campo.
- **Punto débil:** puede haber datos duplicados si se anida mucho.
- **Ejemplos:** **MongoDB** (la que usamos), CouchDB, Firebase Firestore.
- **Uso típico:** catálogos de productos, perfiles de usuario, CMS, APIs.

### 3.3 Columnar / familia de columnas (Wide-Column)

- **Cómo funciona:** guarda los datos por columnas en vez de por filas, agrupados en familias de columnas.
- **Puntos fuertes:** consultas analíticas sobre cantidades enormes de datos muy rápidas.
- **Punto débil:** más compleja de modelar y administrar.
- **Ejemplos:** **Apache Cassandra**, HBase, Google Bigtable.
- **Uso típico:** big data, series temporales, logs, analítica.

### 3.4 Grafos (Graph)

- **Cómo funciona:** guarda **nodos** y **relaciones (aristas)** entre ellos. Las relaciones son ciudadanos de primera clase.
- **Puntos fuertes:** ideal cuando lo importante son las conexiones (recorrer relaciones es muy eficiente).
- **Punto débil:** no es la mejor para almacenar grandes volúmenes de datos planos.
- **Ejemplos:** **Neo4j**, Amazon Neptune, ArangoDB.
- **Uso típico:** redes sociales (amigos de amigos), recomendaciones, detección de fraude, rutas.

### 3.5 Tabla de evaluación

| Tipo | Modelo | Ejemplo | Mejor para | Peor para |
|---|---|---|---|---|
| Clave-valor | clave → valor | Redis | Caché, sesiones | Consultas por contenido |
| Documental | documentos JSON | MongoDB | Datos variables, APIs | Relaciones muy complejas |
| Columnar | familias de columnas | Cassandra | Big data, analítica | Modelado sencillo |
| Grafos | nodos y aristas | Neo4j | Relaciones, redes | Volúmenes planos enormes |

> **Por qué elegimos MongoDB:** es documental (la familia más versátil para empezar), tiene capa gratuita en la nube (Atlas), herramientas gráficas muy fáciles (Compass) y mucha documentación. Es la más adecuada para un trabajo de 1º DAM.

---

## 4. Bloque C – Elementos utilizados en estas bases de datos

Aquí identificamos las "piezas" con las que se trabaja. Lo explicamos con MongoDB y comparándolo con lo que ya conocemos del modelo relacional.

### 4.1 Equivalencias con el modelo relacional

| Relacional | MongoDB (documental) |
|---|---|
| Base de datos | Base de datos |
| Tabla | Colección |
| Fila / registro | Documento |
| Columna / campo | Campo (clave del documento) |
| Clave primaria | Campo `_id` |
| Índice | Índice |
| JOIN | Documentos anidados o `$lookup` |

### 4.2 Elementos clave

- **Documento:** la unidad básica. Es un objeto tipo JSON con pares campo:valor. Ejemplo:
  ```json
  {
    "_id": ObjectId("..."),
    "nombre": "Teclado mecánico",
    "precio": 49.90,
    "stock": 12,
    "etiquetas": ["gaming", "USB"],
    "fabricante": { "nombre": "Logix", "pais": "España" }
  }
  ```
- **Colección:** conjunto de documentos (equivale a una tabla, pero sin esquema fijo).
- **BSON:** formato binario en el que MongoDB guarda los documentos internamente (es JSON con más tipos de datos: fechas, ObjectId, binarios…).
- **Campo `_id`:** identificador único de cada documento. Si no lo ponemos, MongoDB crea un `ObjectId` automáticamente. Hace de clave primaria.
- **Tipos de datos:** texto, número, booleano, fecha, array, documento anidado, null, ObjectId…
- **Documentos anidados y arrays:** permiten meter "subobjetos" o listas dentro de un documento, evitando muchas tablas y JOINs.
- **Índices:** estructuras para que las búsquedas sean rápidas (igual que en SQL). El de `_id` se crea solo.
- **Réplica (Replica Set):** conjunto de copias de la base de datos para tener tolerancia a fallos. Atlas ya nos lo monta.
- **Sharding:** reparto de una colección entre varios servidores para escalar (no se usa en el free tier, pero conviene nombrarlo).

---

## 5. Bloque D – Formas de gestión de la información según el tipo

Cada familia NoSQL gestiona y consulta la información de una forma distinta.

| Tipo | Cómo se insertan / consultan los datos | Lenguaje o API |
|---|---|---|
| Clave-valor (Redis) | Comandos `SET clave valor` / `GET clave` | Comandos propios |
| Documental (MongoDB) | `insertOne`, `find`, `updateOne`, agregaciones | MQL (lenguaje de consultas de MongoDB) |
| Columnar (Cassandra) | Sentencias parecidas a SQL sobre familias de columnas | CQL |
| Grafos (Neo4j) | Se recorren nodos y relaciones con patrones | Cypher |

**En MongoDB concretamente**, la información se gestiona mediante:

- **Operaciones CRUD:** `insertOne`/`insertMany`, `find`, `updateOne`/`updateMany`, `deleteOne`/`deleteMany`.
- **Operadores de consulta:** `$gt`, `$lt`, `$in`, `$and`, `$or`, `$regex`…
- **Framework de agregación (*aggregation pipeline*):** etapas encadenadas (`$match`, `$group`, `$sort`, `$project`) para consultas avanzadas, parecido a `GROUP BY` de SQL.
- **Índices:** para acelerar consultas.
- **Importación/exportación:** cargar y descargar datos en JSON/CSV.
- **Seguridad:** usuarios, roles y contraseñas para controlar quién accede.

En el modelo relacional todo esto se haría con un único lenguaje (SQL); en NoSQL **cada gestor tiene su propia forma**, y eso es justo lo que se evalúa: saber gestionar la información con las herramientas que ofrece el sistema gestor.

---

## 6. Bloque E – Práctica con el sistema gestor (MongoDB Atlas + Compass + mongosh)

> Este es el bloque de demostración práctica (apartado **e** del enunciado). Cada paso lleva su captura de pantalla **hecha por nosotros**. Recomendación: que en cada captura se vea la barra de tareas con la fecha/hora o el nombre del cluster, para demostrar autoría.

### 6.1 Actividad 0 – Crear la cuenta y el cluster en Atlas

**Qué hicimos:** entramos en [mongodb.com/atlas](https://www.mongodb.com/atlas), creamos una cuenta y desplegamos un cluster gratuito **M0** en una región europea.

`[CAPTURA 1: panel de Atlas mostrando el cluster M0 creado y en estado "Active"]`

### 6.2 Actividad 1 – Crear usuario de base de datos y permitir el acceso por IP

**Qué hicimos:** en *Database Access* creamos un usuario con contraseña, y en *Network Access* añadimos nuestra IP (o `0.0.0.0/0` solo para pruebas).

`[CAPTURA 2: pantalla "Database Access" con el usuario creado]`
`[CAPTURA 3: pantalla "Network Access" con la IP permitida]`

### 6.3 Actividad 2 – Obtener la cadena de conexión y conectar con Compass

**Qué hicimos:** botón *Connect → Compass*, copiamos la *connection string* (`mongodb+srv://usuario:<password>@cluster...`) y la pegamos en MongoDB Compass.

`[CAPTURA 4: Compass conectado, mostrando el cluster a la izquierda]`

### 6.4 Actividad 3 – Crear la base de datos y una colección

**Qué hicimos:** en Compass, *Create Database*. Nombre de BD: `tienda`. Colección: `productos`.

`[CAPTURA 5: Compass con la base de datos "tienda" y la colección "productos" creadas]`

### 6.5 Actividad 4 – Insertar documentos (CREATE)

**Por Compass:** *Add Data → Insert Document* y pegamos un documento JSON.

**Por mongosh** (botón *>_MONGOSH* abajo en Compass):
```js
use tienda
db.productos.insertOne({ nombre: "Teclado mecánico", precio: 49.90, stock: 12, etiquetas: ["gaming","USB"] })
db.productos.insertMany([
  { nombre: "Ratón óptico", precio: 12.50, stock: 40, etiquetas: ["USB"] },
  { nombre: "Monitor 24\"", precio: 129.99, stock: 5, etiquetas: ["oficina"] },
  { nombre: "Webcam HD", precio: 25.00, stock: 0, etiquetas: ["videollamada"] }
])
```
`[CAPTURA 6: documentos insertados visibles en Compass]`
`[CAPTURA 7: comando insertMany ejecutado en mongosh con el resultado "acknowledged: true"]`

### 6.6 Actividad 5 – Consultar documentos (READ)

```js
db.productos.find()                                  // todos
db.productos.find({ stock: { $gt: 10 } })            // stock mayor que 10
db.productos.find({ etiquetas: "USB" })              // que tengan la etiqueta USB
db.productos.find({ precio: { $lt: 30 } }).sort({ precio: 1 })  // baratos ordenados
db.productos.countDocuments({ stock: 0 })            // cuántos sin stock
```
`[CAPTURA 8: resultado de una consulta find con filtro en mongosh o en Compass (pestaña Documents con un filtro aplicado)]`

### 6.7 Actividad 6 – Actualizar documentos (UPDATE)

```js
db.productos.updateOne({ nombre: "Webcam HD" }, { $set: { stock: 20 } })
db.productos.updateMany({ precio: { $lt: 30 } }, { $inc: { precio: 2 } })
```
`[CAPTURA 9: documento actualizado mostrando el nuevo valor]`

### 6.8 Actividad 7 – Borrar documentos (DELETE)

```js
db.productos.deleteOne({ nombre: "Ratón óptico" })
```
`[CAPTURA 10: confirmación del borrado (deletedCount: 1) y la colección sin ese documento]`

### 6.9 Actividad 8 – Crear un índice

```js
db.productos.createIndex({ nombre: 1 })
db.productos.getIndexes()
```
También se puede hacer desde Compass en la pestaña **Indexes → Create Index**.

`[CAPTURA 11: pestaña "Indexes" de Compass mostrando el índice creado]`

### 6.10 Actividad 9 – Consulta avanzada con el framework de agregación

Precio medio y stock total por etiqueta:
```js
db.productos.aggregate([
  { $unwind: "$etiquetas" },
  { $group: { _id: "$etiquetas", precioMedio: { $avg: "$precio" }, stockTotal: { $sum: "$stock" } } },
  { $sort: { precioMedio: -1 } }
])
```
`[CAPTURA 12: resultado de la agregación]`

### 6.11 Actividad 10 – Exportar e importar datos

En Compass, en la colección: **Export Data** (a JSON o CSV) e **Import Data**. Esto demuestra la gestión de información de entrada/salida del sistema gestor.

`[CAPTURA 13: ventana de exportación de la colección a JSON]`
`[CAPTURA 14: importación de un fichero JSON correctamente terminada]`

### 6.12 Actividad 11 – Validación de esquema (opcional, nota alta)

Aunque NoSQL no obliga esquema, MongoDB permite poner reglas de validación:
```js
db.createCollection("clientes", {
  validator: { $jsonSchema: {
    bsonType: "object",
    required: ["nombre", "email"],
    properties: {
      nombre: { bsonType: "string" },
      email: { bsonType: "string" }
    }
  }}
})
```
`[CAPTURA 15: error al intentar insertar un cliente sin email, demostrando que la validación funciona]`

---

## 7. Conclusiones

- Las bases de datos no relacionales **no sustituyen** a las relacionales: resuelven problemas distintos (escala, flexibilidad, datos variables).
- Existen **cuatro familias** (clave-valor, documental, columnar, grafos) y cada una gestiona la información de forma diferente.
- MongoDB, al ser documental, es muy intuitiva para quien viene del modelo relacional, y **Atlas + Compass + mongosh** permite practicar sin instalar un servidor propio.
- Hemos hecho un **CRUD completo, índices, agregaciones, importación/exportación y validación**, que es exactamente lo que pide el apartado (e): usar las herramientas del sistema gestor para gestionar la información.
- Aprendizaje personal: lo más distinto respecto a SQL es la **flexibilidad de esquema** y que **cada gestor tiene su propio lenguaje**.

---

## 8. Reparto de tareas entre los miembros del grupo

> El enunciado pide especificar qué puntos ha hecho cada alumno y que cada uno lo pueda defender. Rellenar según vuestro caso real:

| Apartado | Responsable | Defendido por |
|---|---|---|
| A – Caracterización | Nombre1 | Nombre1 |
| B – Tipos de NoSQL | Nombre2 | Nombre2 |
| C – Elementos | Nombre1 | Nombre1 |
| D – Formas de gestión | Nombre2 | Nombre2 |
| E – Práctica (Atlas/Compass/mongosh) | Nombre1 y Nombre2 | Ambos |
| Presentación | Nombre1 y Nombre2 | Ambos |

---

## 9. Bibliografía y recursos

- Documentación oficial de MongoDB – https://www.mongodb.com/docs/
- MongoDB Atlas – https://www.mongodb.com/atlas
- MongoDB Compass – https://www.mongodb.com/products/tools/compass
- MongoDB University (cursos gratuitos) – https://learn.mongodb.com/
- Apuntes del módulo de Bases de Datos (campus virtual), Unidad UT7.
- (Añadir cualquier otra fuente realmente consultada.)

---

> **Recordatorio de entrega:** comprimir en `RA7_nombre1_nombre2.zip` este documento exportado a **PDF** + carpeta de capturas + (opcional) los ficheros JSON exportados. El documento principal debe ser el PDF.
