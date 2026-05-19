# Presentación RA7 – Guion de diapositivas (para Slidev / slide.dev)

> Este fichero **no es código Slidev**. Es la **idea de cada diapositiva**: qué título lleva, qué contenido mostrar y qué decir al exponer/defender. Sirve para visualizar la estructura final antes de maquetarla. Aprox. 15 diapositivas para una defensa de 8–10 min entre los dos.

---

## Diapositiva 1 – Portada
- **Mostrar:** título "RA7 – Bases de datos no relacionales", módulo Bases de Datos 1º DAM, nombres de los dos autores, fecha.
- **Al hablar:** presentarnos y decir en una frase de qué va el trabajo.

## Diapositiva 2 – Índice / agenda
- **Mostrar:** lista de los 5 bloques (A caracterización, B tipos, C elementos, D gestión, E práctica) + conclusiones.
- **Al hablar:** "vamos a ir de la teoría a una demo real en MongoDB Atlas".

## Diapositiva 3 – ¿Qué es una BD no relacional?
- **Mostrar:** definición corta + 3 ó 4 iconos de las características clave (sin esquema, escalado horizontal, alto rendimiento, flexible).
- **Al hablar:** por qué surgieron (volumen y variedad de datos modernos).

## Diapositiva 4 – Relacional vs No relacional
- **Mostrar:** tabla comparativa a dos columnas (estructura, esquema, lenguaje, escalado, transacciones, caso de uso).
- **Al hablar:** la idea clave: no es mejor una u otra, depende del problema. Mencionar ACID vs BASE / teorema CAP de forma sencilla.

## Diapositiva 5 – Los 4 tipos de NoSQL (visión general)
- **Mostrar:** 4 tarjetas: Clave-valor, Documental, Columnar, Grafos, con un ejemplo de producto en cada una.
- **Al hablar:** anticipar que nosotros usaremos la documental (MongoDB).

## Diapositiva 6 – Clave-valor y Documental
- **Mostrar:** esquema visual de Redis (clave→valor) y de MongoDB (documento JSON).
- **Al hablar:** puntos fuertes/débiles y casos de uso (caché vs catálogo/API).

## Diapositiva 7 – Columnar y Grafos
- **Mostrar:** esquema de Cassandra (familias de columnas) y de Neo4j (nodos y aristas).
- **Al hablar:** big data/analítica vs redes sociales/recomendaciones.

## Diapositiva 8 – ¿Por qué MongoDB?
- **Mostrar:** 3 razones: documental y versátil, capa gratuita en la nube (Atlas), herramientas fáciles (Compass).
- **Al hablar:** justificar la elección para un trabajo de 1º DAM.

## Diapositiva 9 – Elementos de MongoDB
- **Mostrar:** tabla de equivalencias relacional ↔ MongoDB (tabla→colección, fila→documento, PK→`_id`...) y un documento JSON de ejemplo con array y subdocumento.
- **Al hablar:** explicar documento, colección, BSON, `_id`, índices.

## Diapositiva 10 – Formas de gestión según el tipo
- **Mostrar:** tabla: cada familia y su lenguaje/API (comandos Redis, MQL Mongo, CQL Cassandra, Cypher Neo4j).
- **Al hablar:** en NoSQL cada gestor tiene su propio lenguaje (no hay SQL estándar).

## Diapositiva 11 – Nuestra arquitectura de práctica
- **Mostrar:** diagrama: Atlas (cluster M0 en la nube) ←→ Compass / mongosh en nuestro PC.
- **Al hablar:** qué es el free tier, replica set automático, conexión por *connection string*.

## Diapositiva 12 – Demo: montaje y conexión
- **Mostrar:** capturas reales: cluster Active, usuario/IP, Compass conectado.
- **Al hablar:** resumir los pasos 0–3 del Bloque E. (Aquí se puede enseñar Compass en vivo si hay tiempo.)

## Diapositiva 13 – Demo: CRUD y consultas
- **Mostrar:** captura de `insertMany`, un `find` con filtro `$gt`, y un `updateOne`.
- **Al hablar:** enseñar el CRUD completo y operadores de consulta.

## Diapositiva 14 – Demo: índices, agregación e import/export
- **Mostrar:** captura del índice creado, del *aggregation pipeline* (media por etiqueta) y de la exportación a JSON.
- **Al hablar:** esto cubre el apartado (e): usar de verdad las herramientas del gestor.

## Diapositiva 15 – Conclusiones + reparto de trabajo
- **Mostrar:** 3 conclusiones clave + tabla de quién ha hecho/defiende cada bloque + "¿Preguntas?".
- **Al hablar:** cerrar con el aprendizaje principal (flexibilidad de esquema y un lenguaje por gestor) y agradecer.

---

### Notas para maquetar después en Slidev
- Usar tema limpio (`seriph` o `default`), una idea por slide, poco texto y apoyarse en las capturas del Bloque E de la memoria.
- Slides 12–14 son la parte de demostración: poner capturas grandes; si se hace demo en vivo, dejarlas como respaldo.
- Reutilizar las tablas de la memoria casi tal cual (comparativa, equivalencias, tipos).
