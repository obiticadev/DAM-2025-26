# 📚 2. Esquemas DTD (Document Type Definition)

El DTD fue la primera tecnología nacida en la Web para comprobar ("validar") que un XML inventor de etiquetas tenía sentido lógico (que un `<hijo>` estuviera dentro de un `<padre>`, y no que un `<padre>` estuviera dentro de un `<hijo>`). 

---

## 📑 Leyenda (Índice de Contenido)
1. [¿Qué es DTD?](#1-que-es-dtd)
2. [Referencia a un esquema DTD](#2-referencia-a-un-esquema-dtd)
3. [Declaraciones de Elementos (<!ELEMENT>)](#3-declaraciones-de-elementos)
4. [Anidamiento y Cardinalidad en Elementos](#4-declaraciones-de-descendientes-anidamiento-y-cardinalidad)
   - [A. Operadores de Secuencia y Selección](#a-operadores-de-secuencia-y-seleccion)
   - [B. Operadores de Repeticiones](#b-operadores-de-cardinalidad-repeticiones)
5. [Declaraciones de Entidades (<!ENTITY>)](#5-declaraciones-de-entidades)
6. [Declaraciones de Atributos (<!ATTLIST>)](#6-declaraciones-de-atributos)
   - [6.1. Tipos de Atributos Permitidos](#61-tipos-de-atributos-permitidos)
   - [6.2. Obligatoriedad (REQUIRED, IMPLIED)](#62-valores-iniciales-y-obligatoriedad)
7. [Limitaciones Famosas de DTD](#7-limitaciones-de-los-esquemas-dtd)

---

<a id="1-que-es-dtd"></a>
## 1. ¿Qué es DTD?
**DTD** (*Document Type Definition*) es un documento de texto plano (que **no** está escrito en sintaxis XML, no tiene etiquetas clásicas) que dicta las leyes y normas para validar un documento XML. 
*   Establece qué elementos o atributos pueden aparecer, en qué orden obligatorio y si son hijos de alguien más.
*   El XML se debe enfrentar al DTD para lograr el certificado de **Documento Válido**.
*   A día de hoy, DTD está siendo reemplazado activamente por XSD, pero su soporte es obligatorio.

---

<a id="2-referencia-a-un-esquema-dtd"></a>
## 2. Referencia a un esquema DTD (Conexión)
Existen dos formas de conectar un XML a un DTD de normas:
1.  **Interna:** Meter las normas DTD directamente en la cabecera de tu XML. (Poco práctico para sistemas grandes).
2.  **Externa (SYSTEM):** Llamar a un archivo que reside en tu disco duro (con la terminación `.dtd`). 
*   **Pública (`PUBLIC`):** Para llamar a DTDs famosos que residen en una URL de internet (como el antiguo del W3C de HTML o XHTML).

> **CASO DE USO (Llamada al DTD Externo en XML):**
> ```xml
> <?xml version="1.0" encoding="UTF-8"?>
> <!-- Debe especificar SIEMPRE quién es el "Elemento Raíz Mayor" (en este caso, agenda) -->
> <!DOCTYPE agenda SYSTEM "normativa_agenda.dtd">
> <agenda>
>     ...
> </agenda>
> ```

---

<a id="3-declaraciones-de-elementos"></a>
## 3. Declaraciones de Elementos (`<!ELEMENT>`)
Definen desde cero cómo se llama la etiqueta y qué relleno tiene autorizado.

**Sintaxis general:**
```xml
<!ELEMENT nombreElemento contenidoPermitido >
```

### Tipos de Contenido Base:
*   **`EMPTY`**: La etiqueta no está autorizada a contener absolutamente nada (salvo atributos vacíos).
    *   *DTD:* `<!ELEMENT saltoLinea EMPTY>`
    *   *XML Válido:* `<saltoLinea />`
*   **`(#PCDATA)`** *(Parsed Character Data)*: PCDATA es el equivalente a decir "**Texto Puro**". Este elemento SÓLO acepta texto rellenado por teclado, no puede tener otras etiquetas XML dentro de él.
    *   *DTD:* `<!ELEMENT nombre (#PCDATA)>`
    *   *XML Válido:* `<nombre>Francisco</nombre>`
*   **`ANY`**: Caos absoluto. La etiqueta puede contener lo que le dé la gana (textos libres, otras etiquetas...). Poco recomendable.
    *   *DTD:* `<!ELEMENT cajonDeSastre ANY>`

---

<a id="4-declaraciones-de-descendientes-anidamiento-y-cardinalidad"></a>
## 4. Anidamiento y Cardinalidad en Elementos
Si una etiqueta no es PCDATA o EMPTY, es porque es una etiqueta Madre que en su interior tiene a otras etiquetas (hijos). Estas obligaciones se escriben metiendo a los hijos entre paréntesis `(...)`.

<a id="a-operadores-de-secuencia-y-seleccion"></a>
### A. Operadores Lógicos (Orden o Exclusión)
*   **Coma ( `,` ) - Secuencia Estricta:** Indica el orden obligatorio y de forzosa aparición una tras otra.
    *   *DTD:* `<!ELEMENT jugador (nombre, apellidos, equipo)>` (Debe salir primero el nombre, luego apellidos, luego equipo. Ni uno más, ni alterar el orden).
*   **Pipe ( `|` ) - Alternativa OR Excluyente:** Puede aparecer el de la izquierda o el de la derecha, pero nunca jamás los dos a la vez.
    *   *DTD:* `<!ELEMENT contacto (email | telefono)>` (O me das su email, o me das su teléfono exclusivo).

<a id="b-operadores-de-cardinalidad-repeticiones"></a>
### B. Operadores Matemáticos (Multiplicadores)
Controlan la repetitividad de las etiquetas hijas. Si no tienen multiplicador, ocurre obligatoriamente 1 SOLA VEZ y nunca más de 1.
*   **Interrogante ( `?` ) - 0 o 1 vez:** Opcional clásico. "O me lo pones una vez, o no me lo pongas".
    *   *DTD:* `<!ELEMENT ficha (nombre, segundoNombre?)>`
*   **Asterisco ( `*` ) - 0 o Infinitas veces:** Lista múltiple pero opcional. "Puedes no poner ningún hijo, o poner 1000 sin parar".
    *   *DTD:* `<!ELEMENT pelicula (titulosAlternativos*)>`
*   **Suma ( `+` ) - 1 o Infinitas veces:** Lista múltiple pero obligada. "Mínimo tienes que poner 1 hijo, y después pon los que quieras".
    *   *DTD:* `<!ELEMENT listado_compra (producto+)>`

> **CASO DE USO (Anidamiento agrupado):**
> *Problema:* Queremos un cliente que siempre tenga `nombre`. Después, puede tener O `telefono` (obligatorio 1 o más) O `redes_sociales` (opcional infinitos).
> *Solución DTD:* Agrupamiento lógico con paréntesis internos.
> ```xml
> <!ELEMENT cliente (nombre, (telefono+ | red_social*))>
> ```

---

<a id="5-declaraciones-de-entidades"></a>
## 5. Declaraciones de Entidades (`<!ENTITY>`)
Un DTD puede regalarle atajos o constantes al XML. 
```xml
<!ENTITY autor_oficial "Servicio de Salud de Madrid S.A.">
```
*Si tú configuras esto en el DTD, el XML validado puede poner `<autor>&autor_oficial;</autor>`.*

---

<a id="6-declaraciones-de-atributos"></a>
## 6. Declaraciones de Atributos (`<!ATTLIST>`)
Añaden propiedades laterales a las etiquetas `<!ELEMENT>`. 

**Sintaxis general:**
```xml
<!ATTLIST nombreElementoASobreponer   nombreAtributo   tipoRestriccion  comportamientoCierre >
```

<a id="61-tipos-de-atributos-permitidos"></a>
### 6.1. Tipos de Atributos Permitidos
*   **`CDATA`**: El interior de las comillas ("") puede ser lo que te dé la gana con o sin espacios.
*   **`NMTOKEN`** *(Name Token)*: Muy restrictivo. Prohibe poner espacios entre palabras " ". (Ideal para NIFs o números de serie juntos).
*   **Enumeraciones `(op1 | op2)`**: Solo te dejo poner palabras cerradas.
    *   *DTD:* `<!ATTLIST semaforo color (verde | ambar | rojo) #REQUIRED>`
*   **`ID`**: Restringes este atributo para que sea una "Primary Key" del XML. El valor de este tributo si un elemento lo usa en `id="P1"`, ningún otro elemento de tu XML gigantesco podrá repetirlo. (El ID no puede empezar con números puros).
*   **`IDREF`**: La "Foreign Key". Te auto-obligas a que lo que pongas en estas comillas *necesite* existir antes como un identificador real (ID). Te obliga a vincular datos certeros.

<a id="62-valores-iniciales-y-obligatoriedad"></a>
### 6.2. Comportamientos Finales (Obligatoriedad)
*   **`#REQUIRED`**: Obligatorio. Si el programador tira la etiqueta y se olvida el atributo, dará Syntax Error el DTD.
*   **`#IMPLIED`**: Opcional inofensivo. Si lo pones lo cogemos, sino, se queda como Nulo de base de datos, ignorado.
*   **`"valor por defecto"`**: Automático predictivo. Si no lo pones, el DTD te enchufa mágicamente ese valor sin que tú lo escribas.
    *   *DTD:* `<!ATTLIST pago_empleado moneda CDATA "EUR">`
*   **`#FIXED "inamovible"`**: Constante dictatorial. Es opcional que lo escribas, **pero si lo escribes tiene que ser SIEMPRE ese texto**.
    *   *DTD:* `<!ATTLIST empresa cif CDATA #FIXED "A12345678">`

---

<a id="7-limitaciones-de-los-esquemas-dtd"></a>
## 7. Limitaciones Famosas de DTD (Por qué nació XSD)
Aunque fue el héroe original, se quedó corto en la tecnología actual:
1.  **Imposibilidad de Tipado de Datos Fuerte:** En DTD **todo es texto**. No puedo prohibirle a un `<edad>Cincuenta</edad>` que me meta letras en vez de los típicos `xs:integer` prohibitorios aritméticos.
2.  **Imposibilidad de limitar repeticiones exactas:** Tiene el `+` (infinitos) o `*`. Pero ¿Y si una quiniela debe tener exactamente 15 goles ni más ni menos? DTD está vendido, no sabe hacer "min=15 max=15".
3.  **No soporta Namespaces (Espacios de Nombres):** Choca en colisiones multi-herramientas.
4.  **No es XML nativo:** Obliga a los navegadores a incluir un "Lector Sub-Gramático especial de DTD" en vez de aprovechar las herramientas que ya traen para leer Simple XML.
