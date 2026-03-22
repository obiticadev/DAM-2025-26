


Aquí tienes el contenido adaptado con la misma estructura profesional, clara y exhaustiva del manual que me enviaste como referencia. He conservado todos los datos, ejemplos, tablas y notas del HTML original, transformándolos al formato y estilo de tu "Manual Maestro".

--- START OF FILE Manual_Completo_JSON_UT5.31.md ---

# 📖 Manual Completo y Definitivo de Formato JSON (UT5.31)

Este manual recopila, íntegramente y sin omitir un solo detalle, todo el contenido referente al formato JSON. Toda la información, características, comparativas, casos de uso, ejemplos de código y estructura original se conservan intactos en este archivo maestro para consulta general.

---

## 📑 Índice General

- [📖 Manual Completo y Definitivo de Formato JSON (UT5.31)](#-manual-completo-y-definitivo-de-formato-json-ut531)
  - [📑 Índice General](#-índice-general)
- [PARTE I: Fundamentos y Estructura de JSON](#parte-i-fundamentos-y-estructura-de-json)
  - [1. Introducción a JSON](#1-introducción-a-json)
  - [2. Características Principales](#2-características-principales)
  - [3. Tipos de Datos en JSON](#3-tipos-de-datos-en-json)
  - [4. Sintaxis de JSON](#4-sintaxis-de-json)
    - [4.1. Reglas Estructurales](#41-reglas-estructurales)
    - [4.2. Ejemplo de Objeto JSON](#42-ejemplo-de-objeto-json)
    - [4.3. Ejemplo de Array JSON](#43-ejemplo-de-array-json)
    - [4.4. Estructuras Anidadas](#44-estructuras-anidadas)
- [PARTE II: Ecosistema, Comparativas y Casos de Uso](#parte-ii-ecosistema-comparativas-y-casos-de-uso)
  - [5. JSON vs XML](#5-json-vs-xml)
    - [Cuadro Comparativo](#cuadro-comparativo)
  - [6. Uso de JSON en JavaScript](#6-uso-de-json-en-javascript)
    - [A. Parsear JSON — `JSON.parse()`](#a-parsear-json--jsonparse)
    - [B. Serializar a JSON — `JSON.stringify()`](#b-serializar-a-json--jsonstringify)
  - [7. JSON en APIs REST](#7-json-en-apis-rest)
- [PARTE III: Calidad, Errores y Validación](#parte-iii-calidad-errores-y-validación)
  - [8. Errores Comunes en JSON](#8-errores-comunes-en-json)
  - [9. Buenas Prácticas](#9-buenas-prácticas)
  - [10. JSON Schema](#10-json-schema)
    - [Palabras clave de JSON Schema](#palabras-clave-de-json-schema)

---
---

<a id="parte-i-fundamentos-y-estructura"></a>
# PARTE I: Fundamentos y Estructura de JSON

<a id="1-introduccion-a-json"></a>
## 1. Introducción a JSON
**JSON** (JavaScript Object Notation) es un formato ligero de intercambio de datos, fácil de leer y escribir para humanos, y simple de analizar y generar para las máquinas. Aunque su nombre menciona JavaScript, JSON es un formato **independiente del lenguaje** de programación y es ampliamente utilizado en el desarrollo web moderno.

Fue propuesto por **Douglas Crockford** a principios de los años 2000 como una alternativa más sencilla al XML. Hoy en día es el estándar de facto para el intercambio de datos entre aplicaciones web, APIs REST y servicios en la nube.

> **NOTA TÉCNICA:**
> JSON está definido universalmente por el estándar **ECMA-404** y **RFC 8259**, lo que garantiza que sea completamente independiente de la plataforma y el lenguaje.

<a id="2-caracteristicas-principales"></a>
## 2. Características Principales
*   👁️ **Legibilidad:** Su sintaxis es clara e intuitiva para los humanos. Es fácil de leer y escribir sin necesidad de herramientas especiales.
*   ⚡ **Ligereza:** Ocupa menos espacio que formatos equivalentes (como XML) al eliminar etiquetas de apertura y cierre redundantes.
*   🌐 **Universalidad:** Es compatible con prácticamente todos los lenguajes de programación modernos (Python, Java, JavaScript, C#, PHP, etc.).
*   📋 **Estandarización:** Definido por el estándar ECMA-404 y RFC 8259, lo que garantiza total compatibilidad e interoperabilidad.
*   🔄 **Independencia:** Funciona en cualquier sistema operativo o entorno de ejecución, sin depender de librerías externas.

<a id="3-tipos-de-datos"></a>
## 3. Tipos de Datos en JSON
JSON soporta **seis tipos de datos fundamentales** que permiten representar cualquier estructura de información:

| Tipo          | Descripción                                             | Ejemplo              |
| :------------ | :------------------------------------------------------ | :------------------- |
| **`string`**  | Cadena de texto encerrada entre comillas dobles.        | `"Hola mundo"`       |
| **`number`**  | Número entero o decimal (sin comillas).                 | `42` / `3.14`        |
| **`boolean`** | Valor lógico verdadero o falso (sin comillas).          | `true` / `false`     |
| **`null`**    | Ausencia intencional de valor. Siempre en minúsculas.   | `null`               |
| **`object`**  | Colección de pares clave-valor encerrados entre llaves. | `{"clave": "valor"}` |
| **`array`**   | Lista ordenada de valores encerrada entre corchetes.    | `[1, 2, 3]`          |

<a id="4-sintaxis-de-json"></a>
## 4. Sintaxis de JSON
Un documento JSON puede ser un objeto o un array en su nivel raíz. Las reglas de sintaxis son **estrictas** y deben seguirse exactamente si no queremos que el *parser* devuelva un error fatal.

### 4.1. Reglas Estructurales
*   ✅ Las **claves** de los objetos siempre van entre **comillas dobles**.
*   ✅ Los pares clave-valor se separan con **dos puntos** (`:`).
*   ✅ Los elementos consecutivos se separan con **comas** (`,`).
*   ❌ No se permiten **comentarios** dentro del JSON (ni `//` ni `/* */`).
*   ❌ No se permite la **coma final** (*trailing comma*) después del último elemento de una lista u objeto.

### 4.2. Ejemplo de Objeto JSON
Un objeto es una colección **no ordenada** de pares clave-valor encerrados entre llaves `{ }`:
```json
{
  "nombre": "Ana García",
  "edad": 28,
  "activa": true,
  "saldo": 1250.75,
  "direccion": null
}
```

### 4.3. Ejemplo de Array JSON
Un array es una lista **ordenada** de valores encerrados entre corchetes `[ ]`:
```json[
  "manzana",
  "banana",
  "cereza",
  42,
  true
]
```

### 4.4. Estructuras Anidadas
JSON permite **anidar objetos y arrays** de manera ilimitada dentro de otros objetos y arrays, creando estructuras de datos complejas para representar escenarios reales:
```json
{
  "empresa": "TechCorp S.L.",
  "empleados":[
    {
      "id": 1,
      "nombre": "Carlos López",
      "departamento": "Ingeniería",
      "habilidades":["Python", "JavaScript", "SQL"],
      "contacto": {
        "email": "carlos@techcorp.com",
        "telefono": "+34 600 123 456"
      }
    },
    {
      "id": 2,
      "nombre": "María Torres",
      "departamento": "Diseño",
      "habilidades": ["Figma", "CSS", "UX"],
      "contacto": {
        "email": "maria@techcorp.com",
        "telefono": "+34 600 789 012"
      }
    }
  ],
  "activa": true,
  "fundacion": 2015
}
```

---
---

<a id="parte-ii-ecosistema-y-casos-de-uso"></a>
# PARTE II: Ecosistema, Comparativas y Casos de Uso

<a id="5-json-vs-xml"></a>
## 5. JSON vs XML
Antes de la llegada de JSON, **XML** era el formato dominante para el intercambio de datos en la web. A continuación, se compara la misma estructura de información en ambos formatos:

**JSON:**
```json
{
  "producto": {
    "nombre": "Laptop Pro",
    "precio": 1299.99,
    "disponible": true
  }
}
```

**XML equivalente:**
```xml
<producto>
  <nombre>Laptop Pro</nombre>
  <precio>1299.99</precio>
  <disponible>true</disponible>
</producto>
```

> **💡 VENTAJA DE JSON:**
> JSON es mucho más conciso y legible que XML al evitar repetir etiquetas enteras de apertura y cierre. Además, es directamente *parseable* por JavaScript sin necesidad de utilizar librerías complejas como *DOMParser*.

### Cuadro Comparativo
| Característica    | JSON                          | XML                             |
| :---------------- | :---------------------------- | :------------------------------ |
| **Legibilidad**   | Alta — sintaxis limpia        | Media — etiquetas verbosas      |
| **Peso**          | Ligero                        | Más pesado                      |
| **Comentarios**   | No soportados                 | Soportados (`<!-- -->`)         |
| **Tipos nativos** | 6 tipos (number, boolean…)    | Todo se trata como texto        |
| **Parsing en JS** | Nativo con `JSON.parse()`     | Requiere `DOMParser`            |
| **Uso actual**    | APIs REST, configuración, web | SOAP, documentos complejos, RSS |

<a id="6-json-en-javascript"></a>
## 6. Uso de JSON en JavaScript
Dado que JSON nació a partir de JavaScript, su integración es nativa y extremadamente sencilla a través del objeto global `JSON`.

### A. Parsear JSON — `JSON.parse()`
Convierte una **cadena de texto en formato JSON** en un objeto de JavaScript funcional. Se utiliza típicamente al recibir datos de una API.
```javascript
const texto = '{"nombre": "Luis", "edad": 30}';
const objeto = JSON.parse(texto);

console.log(objeto.nombre); // Salida: Luis
console.log(objeto.edad);   // Salida: 30
```

### B. Serializar a JSON — `JSON.stringify()`
Convierte un **objeto de JavaScript** en una cadena de texto JSON plana. Se utiliza típicamente para enviar información a un servidor.
```javascript
const usuario = {
  nombre: 'Elena',
  edad: 25,
  activa: true
};

const json = JSON.stringify(usuario);
console.log(json); 
// '{"nombre":"Elena","edad":25,"activa":true}'

// Para serializar con formato legible (indentación de 2 espacios):
const jsonBonito = JSON.stringify(usuario, null, 2);
```

<a id="7-json-en-apis-rest"></a>
## 7. JSON en APIs REST
Las **APIs REST** utilizan JSON como el formato estándar universal para enviar y recibir payloads (cargas de datos) entre el cliente y el servidor.

**Petición (Request HTTP) — Crear un usuario:**
```http
POST /api/usuarios HTTP/1.1
Content-Type: application/json

{
  "nombre": "Pedro Martínez",
  "email": "pedro@email.com",
  "edad": 35
}
```

**Respuesta (Response) del servidor:**
```http
HTTP/1.1 201 Created
Content-Type: application/json

{
  "id": 42,
  "nombre": "Pedro Martínez",
  "email": "pedro@email.com",
  "edad": 35,
  "creado_en": "2025-02-23T10:30:00Z"
}
```

> **🚨 IMPORTANTE:**
> Es obligatorio incluir siempre la cabecera `Content-Type: application/json` tanto en las peticiones enviadas como en las respuestas del servidor. Esto es lo que avisa al navegador o procesador de cómo debe interpretar el cuerpo del mensaje.

---
---

<a id="parte-iii-calidad-y-validacion"></a>
# PARTE III: Calidad, Errores y Validación

<a id="8-errores-comunes"></a>
## 8. Errores Comunes en JSON
Cometer cualquiera de estos errores hará que el documento JSON sea **inválido** y devuelva un error de parseo en la aplicación:
1.  **Usar comillas simples:** En JSON no existen comillas simples para strings, obligatoriamente se usan dobles.
    *   ❌ Mal: `{'nombre': 'Ana'}` 
    *   ✅ Bien: `{"nombre": "Ana"}`
2.  **Coma final (Trailing comma):** Dejar una coma suelta tras el último elemento.
    *   ❌ Mal: `{"nombre": "Ana",}` 
    *   ✅ Bien: `{"nombre": "Ana"}`
3.  **Añadir comentarios:** JSON no es un lenguaje de programación, es un modelo de datos. No soporta comentarios de ningún tipo.
4.  **Valores de lenguaje específico:** Intentar guardar elementos de JS como `undefined` o funciones matemáticas reales. JSON solo admite los 6 tipos básicos detallados en el Apartado 3.
5.  **Claves sin comillas:** En los lenguajes de programación (como JS) a veces no hacen falta, pero en JSON son obligatorias.
    *   ❌ Mal: `{nombre: "Ana"}` 
    *   ✅ Bien: `{"nombre": "Ana"}`

<a id="9-buenas-practicas"></a>
## 9. Buenas Prácticas
Para mantener documentos de calidad profesional:
*   🔤 **Nomenclatura consistente:** Utilizar nombres de clave en `camelCase` (ej. `nombreUsuario`) o `snake_case` (ej. `nombre_usuario`) de forma consistente en todo el proyecto.
*   📐 **Estructura plana:** Mantener una estructura plana siempre que sea posible. Evitar el anidamiento excesivamente profundo de objetos que complica la lectura.
*   📡 **Content-Type correcto:** Verificar que los servicios web siempre sirvan el archivo con la cabecera MIME correspondiente.
*   ✅ **Validación previa:** Validar el JSON entrante antes de procesarlo para evitar errores no controlados y caídas de servidor.
*   📄 **Uso de Schemas:** Documentar la estructura del JSON esperado mediante esquemas estandarizados para facilitar la integración a equipos de terceros.
*   🛠️ **Formateo y linting:** Utilizar linter de código o validadores online (ej. *jsonlint.com*) para mantener la consistencia en repositorios.

<a id="10-json-schema"></a>
## 10. JSON Schema
Del mismo modo que XML tiene DTD o XSD para validarse, **JSON Schema** es un vocabulario estandarizado que permite **validar, anotar y describir** la estructura matemática y obligatoria de un documento JSON.

**Ejemplo de un Esquema de Validación JSON:**
```json
{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "type": "object",
  "properties": {
    "nombre": {
      "type": "string",
      "minLength": 2,
      "maxLength": 100
    },
    "edad": {
      "type": "integer",
      "minimum": 0,
      "maximum": 150
    },
    "email": {
      "type": "string",
      "format": "email"
    }
  },
  "required": ["nombre", "email"]
}
```

### Palabras clave de JSON Schema
| Palabra clave                 | Descripción                                             | Ejemplo de restricción   |
| :---------------------------- | :------------------------------------------------------ | :----------------------- |
| **`type`**                    | Define el tipo de dato que debe recibir esa clave.      | `"type": "string"`       |
| **`required`**                | Lista rigurosa de claves cuya presencia es obligatoria. | `"required": ["nombre"]` |
| **`minLength` / `maxLength`** | Limita la longitud máxima y mínima de un string.        | `"minLength": 2`         |
| **`minimum` / `maximum`**     | Fija topes aritméticos numéricos (mayor o menor).       | `"minimum": 0`           |
| **`format`**                  | Imposición de formato semántico especial.               | `"format": "email"`      |
| **                            |