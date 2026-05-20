# Presentación RA7 – Guion de diapositivas (para Slidev / slide.dev)

> Este fichero **no es código Slidev**. Es la **idea de cada diapositiva**: qué título lleva, qué contenido mostrar y qué decir al exponer/defender. Sirve para visualizar la estructura final antes de maquetarla. Aprox. 14 diapositivas para una defensa de 8–10 min entre los tres integrantes del grupo.

> Contexto del trabajo: el cluster Atlas (M0, AWS Frankfurt) ya está creado y compartido. Nos conectamos directamente con **MongoDB Compass** a `mongodb+srv://<usuario>:<password>@dam.pb0dxmb.mongodb.net/` (usuario `admin`, password `123`), y practicamos sobre las bases de muestra (`sample_mflix`) y una base propia (`practica_ra7`). No hay que enseñar montaje del cluster — eso ya viene hecho.

---

## Diapositiva 1 – Portada
- **Mostrar:** título "RA7 – Bases de datos no relacionales", módulo Bases de Datos 1º DAM, nombres de los tres autores, fecha.
- **Al hablar:** presentarnos y resumir en una frase de qué va el trabajo.

## Diapositiva 2 – Índice
- **Mostrar:** lista de los 5 bloques (A caracterización, B tipos, C elementos, D gestión, E práctica) + conclusiones.
- **Al hablar:** "vamos de la teoría a una demo real sobre MongoDB Atlas con datos de películas".

## Diapositiva 3 – ¿Qué es una BD no relacional?
- **Mostrar:** definición corta + 4 ideas clave (sin esquema, escalado horizontal, alto rendimiento, modelo cercano a la aplicación).
- **Al hablar:** por qué surgieron (volumen y variedad de datos modernos).

## Diapositiva 4 – Relacional vs No relacional
- **Mostrar:** tabla comparativa a dos columnas (estructura, esquema, lenguaje, escalado, transacciones, caso de uso).
- **Al hablar:** ninguna es mejor, depende del problema. Nombrar ACID vs BASE y el teorema CAP de forma sencilla.

## Diapositiva 5 – Los 4 tipos de NoSQL
- **Mostrar:** 4 tarjetas: Clave-valor (Redis), Documental (MongoDB), Columnar (Cassandra), Grafos (Neo4j), con un esquema visual de cada modelo.
- **Al hablar:** anticipar que usaremos la documental.

## Diapositiva 6 – ¿Por qué MongoDB?
- **Mostrar:** 3 razones: documental y versátil, capa gratuita en la nube (Atlas), cliente gráfico cómodo (Compass).
- **Al hablar:** justificar la elección para un trabajo de 1º DAM y mencionar que el cluster ya está montado y compartido entre los tres.

## Diapositiva 7 – Elementos de MongoDB
- **Mostrar:** tabla de equivalencias relacional ↔ MongoDB (tabla→colección, fila→documento, PK→`_id`...) y un documento JSON real sacado de `sample_mflix.movies`.
- **Al hablar:** explicar documento, colección, BSON, `_id`, índices, arrays y subdocumentos.

## Diapositiva 8 – Formas de gestión según el tipo
- **Mostrar:** tabla: cada familia y su lenguaje/API (comandos Redis, MQL Mongo, CQL Cassandra, Cypher Neo4j).
- **Al hablar:** en NoSQL cada gestor tiene su propio lenguaje (no hay un SQL estándar).

## Diapositiva 9 – Nuestra arquitectura de práctica
- **Mostrar:** diagrama: cluster Atlas M0 (AWS Frankfurt) en la nube ←→ MongoDB Compass en nuestro PC. Sobre el cluster, dos cajas: `sample_mflix` (datos para consultar) y `practica_ra7` (datos creados por nosotros).
- **Al hablar:** qué es el free tier, cadena de conexión `mongodb+srv://...`, replica set automático.

## Diapositiva 10 – Demo: conexión y bases de muestra
- **Mostrar:** capturas 1 y 2 de la memoria (Compass conectado y colección `sample_mflix.movies` cargada).
- **Al hablar:** "nos conectamos con la cadena y al instante tenemos miles de películas reales para probar consultas".

## Diapositiva 11 – Demo: consultas (READ)
- **Mostrar:** capturas 3, 4 y 5 (consultas con `$gt`, consulta combinada con rango de años, y consulta con `$regex` + sort + limit).
- **Al hablar:** enseñar la sintaxis de los filtros y los operadores. Comparar mentalmente con un `WHERE` de SQL.

## Diapositiva 12 – Demo: CRUD sobre nuestra colección
- **Mostrar:** capturas 6, 7, 8 y 9 (creación de `practica_ra7`, `insert` con varios documentos, `update` y `delete` desde Compass).
- **Al hablar:** Compass permite hacer el CRUD entero gráficamente, sin escribir código.

## Diapositiva 13 – Demo: índices, agregación e import/export
- **Mostrar:** capturas 10, 11, 12 y 13 (índice `nombre_1`, pipeline de agregación por género en `sample_mflix.movies`, exportación a JSON e importación a `productos_copia`).
- **Al hablar:** esto cubre lo más fuerte del apartado (e): consultas avanzadas, rendimiento y portabilidad de datos.

## Diapositiva 14 – Conclusiones + reparto + preguntas
- **Mostrar:** 3 conclusiones clave + tabla de quién ha hecho/defiende cada bloque + "¿Preguntas?".
- **Al hablar:** cerrar con el aprendizaje principal (flexibilidad de esquema y un lenguaje por gestor) y agradecer.

---

### Notas para maquetar después en Slidev
- Tema limpio (`seriph` o `default`), una idea por slide, poco texto, las capturas grandes y centradas.
- Reutilizar las tablas de la memoria casi tal cual (comparativa relacional/NoSQL, los 4 tipos, equivalencias, lenguajes por familia).
- Slides 10–13 son la parte de demostración: las capturas de la memoria son las mismas que aparecen aquí.
- Si hay tiempo, slide 11 o 12 se puede sustituir por demo en vivo desde Compass (las capturas valen como respaldo).
