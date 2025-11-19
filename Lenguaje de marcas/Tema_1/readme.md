# Guía de Lenguajes de Marcas y Sistemas Web

Este documento proporciona una introducción a los conceptos fundamentales de la World Wide Web, la arquitectura de las aplicaciones web y los lenguajes de marcas utilizados para la gestión de la información.

### 1. World Wide Web (WWW)

La World Wide Web es un universo de información accesible globalmente a través de la red Internet. Está formada por un conjunto de recursos interconectados que conforman el conocimiento humano actual.

Su funcionamiento es posible gracias a la coexistencia de componentes de software y hardware:

*   **Componentes físicos:** `hubs`, `repetidores`, `puentes`, `pasarelas`, `encaminadores`, etc.
*   **Protocolos de comunicaciones:** `IP`, `TCP`, `DNS`, `HTTP`, `FTP`, `SMTP`, etc.

### 2. Arquitectura Web

Las aplicaciones web se basan en una arquitectura **cliente/servidor**, compuesta por:

*   **El cliente:** Es el navegador o visualizador web que solicita los recursos.
*   **El servidor:** Aloja la aplicación web, procesa las peticiones y ofrece el servicio `HTTP`.
*   **La conexión de red:** El canal de comunicación entre el cliente y el servidor.

El servidor distribuye páginas de información formateada (páginas web) a los clientes que las solicitan utilizando el protocolo `HTTP`.

### 3. Página Web

Una **página web** es un documento electrónico adaptado para la WWW, que forma parte de un sitio o aplicación web.

Se compone de información de texto, contenido multimedia e hiperenlaces, y se estructura utilizando el lenguaje de marcas `HTML`. Puede contener también datos de estilo (`CSS`) y aplicaciones embebidas (`JavaScript`) para hacerla interactiva.

El contenido de las páginas puede ser:

*   **Estático:** El contenido está predeterminado y no cambia.
*   **Dinámico:** El contenido se construye en el servidor en el momento en que es solicitado por el cliente.

### 4. Navegador Web

El **navegador web** es una aplicación que permite acceder a un recurso publicado por un servidor web a través de una dirección **URL** (*Universal Resource Locator*).

Cada navegador interpreta las páginas web de forma propia, dependiendo de su configuración y propósito (rapidez, fidelidad al contenido, seguridad, etc.). La comunicación se inicia cuando el usuario introduce una URL y termina cuando el recurso es visualizado, momento en el que la conexión se cierra hasta una nueva petición.

### 5. Lenguajes de Programación Web

Se clasifican en dos tipos según dónde se ejecuten:

*   **Entorno Cliente:** Se ejecutan en el navegador web.
    *   Ejemplos: `HTML`, `CSS`, `JavaScript`.
*   **Entorno Servidor:** Se ejecutan en el servidor web.
    *   Ejemplos: `PHP`, `ASP`, `JSP`, `Python`.

El código del entorno servidor se intercala con el `HTML` y es interpretado por el servidor para construir la página final que se enviará al navegador.

### 6. Definición de Lenguaje de Marcas

Los **lenguajes de marcas** (o de marcado) complementan la información de un documento con marcas o anotaciones que modifican su estructura o la forma de representarlo.

*   Definen qué etiquetas son posibles, su significado y dónde deben colocarse.
*   Las etiquetas no son visibles para el usuario final; solo el contenido y la apariencia resultante lo son.

### 7. Tipos y Ejemplos de Lenguajes de Marcas

#### Clasificación
*   **Orientados a la presentación:** Las etiquetas contienen indicaciones de formato. Ejemplo: `HTML`, procesadores de texto.
*   **Orientados a la descripción:** Las etiquetas dan un significado al texto, pero no indican cómo presentarlo. Ejemplo: `XML`.
*   **Orientados a procedimientos:** Las etiquetas se interpretan como órdenes o instrucciones. Ejemplo: `LaTeX`, `Postscript`.

#### Ejemplos Comunes
*   `HTML` (Hypertext Markup Language): Para crear documentos transportables en Internet con enlaces.
*   `XML` (eXtended Markup Language): Subconjunto de `SGML` que permite definir lenguajes con sintaxis estricta y extensible.
*   `SGML`: Estándar y base sobre la que se sostiene `HTML` y `XML`.
*   `LaTeX`: Para documentos científicos con fórmulas matemáticas.
*   `RTF` (Rich Text Format): Para documentos de texto con formato.

### 8. Componentes de un Documento de Marcas

| Componente | Definición | Ejemplo |
| :--- | :--- | :--- |
| **Etiqueta (Tag)** | Texto delimitado por `<` y `>`. La etiqueta de fin incluye una barra `/`. | `<nombre>María</nombre>` |
| **Atributo** | Par `nombre-valor` dentro de la etiqueta de inicio que define propiedades. | `<ciudad provincia="Madrid">` |
| **Elemento** | Estructura completa: etiqueta de inicio, contenido y etiqueta de fin. | `<ciudad provincia="Madrid">Alcobendas</ciudad>`|

### 9. Gramáticas (DTD y XSD)

Todos los documentos de un mismo lenguaje de marcas siguen una **gramática** que define las combinaciones de símbolos sintácticamente correctas.

*   **DTD (Document Type Definition):** Contiene las reglas de los elementos: nombre, significado, dónde se pueden usar y qué pueden contener.
*   **XMLSchema (XSD):** Es la evolución de DTD, descrita por el W3C. Utiliza sintaxis `XML`, es más potente y detallada, pero consume más recursos.

### 10. Organizaciones Desarrolladoras

*   **ISO (International Organization for Standardization):** Promueve el desarrollo de normas internacionales de fabricación, comercio y comunicación.
*   **W3C (World Wide Web Consortium):** Creado en 1994 por el MIT, se encarga de tutelar el crecimiento y la organización de la web. Normalizó `HTML` y desarrolló el estándar `XML` (publicado en 1998).