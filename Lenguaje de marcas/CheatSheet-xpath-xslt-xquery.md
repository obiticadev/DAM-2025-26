<a id="indice"></a>
# CheatSheet Práctica: XPath, XSLT, XQuery, DOM y SAX

Una síntesis directa, sencilla y estructurada para comprender, memorizar y aplicar mediante reglas mnemotécnicas y ejemplos al grano.

## Índice de Contenidos
- [1. XPath — El Bisturí de Navegación](#xpath)
- [2. XSLT — La Fábrica de Transformación](#xslt)
- [3. XQuery — El SQL de los XML](#xquery)
- [4. DOM vs SAX — Leer XML desde Código](#domsax)

---

<a id="xpath"></a>
## 1. XPath — El Bisturí de Navegación
[↑ Volver al índice](#indice)

XPath no busca texto, busca **Nodos** en un árbol.

> **Regla Mnemotécnica R-E-P:** Para navegar haz **R**uta, **E**je y **P**redicado.  
> *"Elige el camino (Ruta), mira alrededor (Eje), y pide DNI (Predicado)"*

### A. Funciones para ARRANCAR (Rutas y Agrupación)
Son las que usas al principio de la expresión para traer conjuntos de datos o resumirlos.

| Instrucción / Función | ¿Qué hace? | Ejemplo |
|---|---|---|
| `/` | **Arranca en la raíz** o baja 1 nivel exacto. | `/catalogo/libro` |
| `//` | **Búsqueda global:** Busca en cualquier nivel. | `//titulo` |
| `count(ruta)` | **Cuenta** cuántos nodos devuelve la ruta. | `count(//libro)` |
| `sum(ruta)` | **Suma** los valores numéricos. | `sum(//precio)` |

### B. Funciones para el PREDICADO `[ ]` (Las Aduanas)
Se meten entre corchetes para **filtrar** qué nodos pasan y cuáles no.

> **Mnemotecnia:** *Los corchetes `[ ]` son puertas de discoteca. Las funciones dentro son el portero comprobando requisitos.*

| Función en Predicado | ¿Qué filtra? | Ejemplo |
|---|---|---|
| `position()` y `last()` | Filtra por posición. | `//libro[last()]` (El último)<br>`//libro[position()=1]` (El primero) |
| `@atributo` | Tiene un atributo concreto o coincide. | `//libro[@id='001']` |
| `contains(A, B)` | `A` contiene el texto `B`. | `//libro[contains(autor, 'García')]` |
| `starts-with(A, B)` | `A` empieza por `B`. | `//libro[starts-with(@id, '00')]` |
| `> , < , = , and , or` | Comparadores lógicos y matemáticos. | `//libro[precio > 20 and @disponible='true']` |

**Ejemplo completo:**  
*"Dame los títulos de los libros del catálogo que cuesten más de 20€"*  
`//libro[precio > 20]/titulo`

---

<a id="xslt"></a>
## 2. XSLT — La Fábrica de Transformación
[↑ Volver al índice](#indice)

Convierte un XML en otra cosa (HTML, CSV, etc.) aplicando plantillas a medida que lo lee.

> **Regla Mnemotécnica S-O-T-A:** La estructura obligatoria de XSLT.  
> **S**tylesheet (Etiqueta raíz)  
> **O**utput (Formato de salida HTML/CSV)  
> **T**emplate (Plantilla raíz `match="/"`)  
> **A**pply-templates (Procesar al resto)

### A. Estructura Base (La S-O-T-A en código)
Para generar un **HTML**:
```xml
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="html" encoding="UTF-8"/> <!-- Salida HTML -->
  
  <xsl:template match="/"> <!-- Plantilla Raíz -->
    <html><body>
      <xsl:apply-templates/> <!-- Aplica plantillas a los hijos -->
    </body></html>
  </xsl:template>
  
  <!-- Aquí van el resto de templates -->
</xsl:stylesheet>
```
*Si quieres generar **CSV**, el output es `method="text"` y se imprime usando comas y saltos de línea `&#10;`.*

### B. Tipos de Plantillas: MATCH vs NAME
> **Mnemotecnia:** *`match` es una trampa automática para osos. `name` es un robot al que tienes que encender con un botón (`call`).*

- **Automáticas (`match`):** Se ejecutan solas cuando el motor encuentra ese nodo.
  ```xml
  <xsl:template match="libro"> <!-- Se activa al pisar un <libro> -->
    <p><xsl:value-of select="titulo"/></p> <!-- Imprime su título -->
  </xsl:template>
  ```
- **Manuales (`name`):** Tienes que invocarlas tú con `call-template`.
  ```xml
  <xsl:template name="cabecera">
    <h1>Mi Catálogo</h1>
  </xsl:template>
  <!-- Para llamarla: -->
  <xsl:call-template name="cabecera"/>
  ```

### C. Condicionales y Bucles
- **Bucle simple (`for-each`)**: Para iterar e imprimir (como en una tabla).
  ```xml
  <xsl:for-each select="catalogo/libro">
    <xsl:sort select="precio" data-type="number"/> <!-- Opcional: ordenar -->
    <li><xsl:value-of select="titulo"/></li>
  </xsl:for-each>
  ```
- **Condicional simple (`if`)**: Sin 'else'.
  ```xml
  <xsl:if test="precio > 20"> <b>¡Caro!</b> </xsl:if>
  ```
- **Condicional múltiple (`choose`)**: Es el *Switch* de XSLT.
  ```xml
  <xsl:choose>
    <xsl:when test="precio > 20"> Caro </xsl:when>
    <xsl:otherwise> Barato </xsl:otherwise> <!-- El 'else' -->
  </xsl:choose>
  ```

---

<a id="xquery"></a>
## 3. XQuery — El SQL de los XML
[↑ Volver al índice](#indice)

Se usa para interrogar Bases de Datos nativas de XML usando la estructura FLWOR.

> **Regla Mnemotécnica FLWOR:** "Flores Locas Vuelan Ordenadas Rápido"  
> **F**or (Bucle), **L**et (Variables), **W**here (Filtro), **O**rder by (Orden), **R**eturn (Salida).

### A. La estructura FLWOR
```xquery
for $libro in doc('biblioteca.xml')//libro   (: BUCLE: por cada libro... :)
let $precio := $libro/precio                 (: VARIABLE: me guardo su precio :)
where number($precio) > 10                   (: FILTRO: solo mayores de 10 :)
order by $libro/titulo ascending             (: ORDEN: de la A a la Z :)
return                                       (: SALIDA: Siempre obligatoria :)
  <resultado>
    <titulo>{ $libro/titulo/text() }</titulo>
    <precio>{ $precio/text() }</precio>
  </resultado>
```
> **Recuerda:** Todo lo dinámico de XML en el `return` debe ir entre llaves `{ }`. Si no, se imprime como texto plano.

### B. Modificar XML (XQUF)
Funciones para alterar la base de datos XML:
- **`insert node`** ... `into` / `before` / `after` -> Inserta un nuevo nodo.
- **`delete node`** -> Borra nodo. Ejemplo: `delete node //libro[@id='1']`
- **`replace value of`** ... `with` -> Cambia solo el contenido textual de un nodo.
- **`replace node`** ... `with` -> Sustituye un nodo completo por otro.

---

<a id="domsax"></a>
## 4. DOM vs SAX — Leer XML desde Código
[↑ Volver al índice](#indice)

Ambos son formas de que tu código (Java, Python, etc.) lea un XML. 

> **Regla Mnemotécnica Visual:**  
> - **DOM (Árbol Completo):** Como desplegar un mapa gigante sobre la mesa. Puedes ver todo a la vez, moverte en cualquier dirección y dibujar encima, pero ocupará casi toda la mesa (Memoria RAM).  
> - **SAX (Audiolibro):** Como escuchar un libro en cinta. Gasta cero espacio en la mesa (Memoria) y es rapidísimo, pero solo va hacia adelante de forma secuencial y no puedes editar la cinta en directo.

### A. Resumen Directo y Preciso
| Característica | DOM (Document Object Model) | SAX (Simple API for XML) |
|---|---|---|
| **Cómo carga** | **En memoria:** Construye un árbol jerárquico completo en la RAM. | **Por streaming:** Lee de corrido, línea a línea, reaccionando a "Eventos". |
| **Uso de Memoria** | **Alto:** Peligroso si el XML pesa varios Megabytes/Gigabytes. | **Mínimo:** Da igual el tamaño del archivo, lee pasito a pasito. |
| **Navegación** | **Aleatoria:** Puedes subir (padre), bajar (hijos), ir a los hermanos... | **Secuencial:** Solo avanza de arriba a abajo. No se puede retroceder. |
| **Modificación** | **Sí:** Permite editar, añadir y borrar nodos en caliente. | **No:** Es puramente de lectura (Read-Only). |

**¿Cuándo usar cuál?**
- ¿El fichero es manejable (<100MB) y necesitas manipularlo/modificarlo? → **Usa DOM**
- ¿El fichero es gigantesco (logs o volcados masivos) y solo te importa extraer/buscar datos rápido? → **Usa SAX**
