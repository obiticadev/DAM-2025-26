# 📜 JS DOM CHEAT SHEET (Nivel Inicial)

## 1. SELECCIÓN DE ELEMENTOS (Búsqueda)
*¿Cómo encuentro elementos en el HTML?*

| Comando | Sintaxis Completa | ¿Qué devuelve? | Descripción |
| :--- | :--- | :--- | :--- |
| **getElementById** | `document.getElementById("id")` | **Elemento** (Objeto) | Busca un elemento único por su ID. Si no existe, devuelve `null`. |
| **querySelector** | `document.querySelector(".clase")` | **Elemento** (Objeto) | Devuelve el **primer** elemento que coincida con el selector CSS (etiqueta, `.clase`, `#id`). |
| **querySelectorAll** | `document.querySelectorAll("p")` | **NodeList** (Lista) | Devuelve una **colección** (parecida a un Array) con **todos** los elementos que coincidan. |
| **body** | `document.body` | **Elemento** | Acceso directo a la etiqueta `<body>` de la página. |
| **this** | `onclick="funcion(this)"` | **Elemento** | Devuelve el elemento exacto que disparó el evento (el botón pulsado). |

---

## 2. LECTURA Y ESCRITURA DE CONTENIDO
*¿Cómo leo o cambio lo que hay dentro?*

| Propiedad | Sintaxis de Uso | Tipo de Dato | Descripción |
| :--- | :--- | :--- | :--- |
| **innerText** | `elemento.innerText = "Hola"` | **String** (Texto) | Lee o reemplaza el texto visible dentro de una etiqueta. |
| **innerHTML** | `elemento.innerHTML = "<b>X</b>"` | **String** (HTML) | Lee o reemplaza el contenido, interpretando etiquetas HTML dentro. |
| **value** | `input.value` | **String** | **Vital:** Obtiene el texto escrito por el usuario en un `<input>`. |
| **length** | `texto.length` / `lista.length` | **Number** | Cuenta caracteres (si es texto) o elementos (si es una NodeList). |

---

## 3. MANIPULACIÓN DE ATRIBUTOS
*Configuración de etiquetas HTML*

| Atributo | Sintaxis de Uso | Tipo | Descripción |
| :--- | :--- | :--- | :--- |
| **src** | `imagen.src = "ruta.jpg"` | **String** | Cambia la fuente de una `<img>`. |
| **href** | `enlace.href = "url"` | **String** | Cambia el destino de un `<a>`. |
| **type** | `input.type = "password"` | **String** | Cambia el tipo de input (ej. de texto a password). |
| **disabled** | `boton.disabled = true` | **Boolean** | `true` desactiva el elemento, `false` lo activa. |
| **id** | `elemento.id = "nuevoID"` | **String** | Asigna o cambia el ID del elemento. |

---

## 4. ESTILOS Y CLASES (CSS en JS)
*Cambiar la apariencia visual*

| Propiedad / Método | Sintaxis de Uso | Devuelve | Descripción |
| :--- | :--- | :--- | :--- |
| **.style.[propiedad]** | `elemento.style.color = "red"` | **String** | Cambia un estilo en línea. Usa **camelCase** (`backgroundColor`). |
| **classList.add** | `elem.classList.add("clase")` | **Void** (Nada) | Añade una clase CSS al elemento (sin borrar las anteriores). |
| **classList.remove** | `elem.classList.remove("clase")` | **Void** | Quita una clase específica. |
| **classList.toggle** | `elem.classList.toggle("clase")` | **Boolean** | Interruptor: Si la tiene, la quita. Si no, la pone. |

---

## 5. CREACIÓN E INSERCIÓN (El "Limbo")
*Cómo fabricar HTML nuevo y ponerlo en la página*

| Método | Sintaxis Completa | Devuelve | Descripción |
| :--- | :--- | :--- | :--- |
| **createElement** | `document.createElement("div")` | **Elemento** | Crea una etiqueta en memoria (RAM). **Aún no es visible** hasta que hagas append. |
| **appendChild** | `padre.appendChild(hijo)` | **Elemento** | Inserta el elemento `hijo` al **final** del elemento `padre`. |
| **insertBefore** | `padre.insertBefore(nuevo, ref)` | **Elemento** | Inserta `nuevo` justo **antes** del elemento `ref` (hermano). |

---

## 6. ELIMINACIÓN Y NAVEGACIÓN (Traversing)
*Moverse por el árbol genealógico y borrar*

| Propiedad / Método | Sintaxis de Uso | Devuelve | Descripción |
| :--- | :--- | :--- | :--- |
| **remove()** | `elemento.remove()` | **Void** | Elimina el elemento del HTML (suicidio). |
| **parentElement** | `elemento.parentElement` | **Elemento** | Devuelve al padre directo (el contenedor). Ideal para borrar filas `<tr>`. |
| **nextElementSibling** | `elemento.nextElementSibling` | **Elemento** | Devuelve al siguiente hermano en el mismo nivel (el vecino de abajo). |
| **Vaciar contenedor** | `contenedor.innerHTML = ""` | **Void** | Truco rápido para borrar **todos** los hijos de un golpe. |

---

## 7. VISIBILIDAD (Mostrar/Ocultar)
*Control de `display`*

| Estado | Código CSS/JS | Efecto Visual | ¿Ocupa espacio? |
| :--- | :--- | :--- | :--- |
| **Oculto** | `elem.style.display = "none"` | Desaparece completamente. | **NO** (El resto se recoloca). |
| **Visible** | `elem.style.display = "block"` | Aparece como bloque. | **SÍ**. |
| **Visible (Linea)** | `elem.style.display = "inline"` | Aparece en la misma línea. | **SÍ**. |

---

## 8. LÓGICA Y BUCLES (Estructuras de Control)
*Patrones comunes en los ejercicios*

### Bucle para recorrer `querySelectorAll`
Se usa cuando tienes una **NodeList** (lista de elementos).
```javascript
let lista = document.querySelectorAll("p"); // Devuelve NodeList

for (let i = 0; i < lista.length; i++) {
    // lista[i] es el elemento individual en este turno
    lista[i].style.color = "blue";
}
```

### Validaciones Comunes
| Código | Explicación |
| :--- | :--- |
| `if (input.value == "")` | ¿Está vacío el campo? |
| `if (texto.includes("@"))` | ¿Contiene el símbolo @? |
| `if (numero % 2 == 0)` | ¿Es par? (El resto de dividir entre 2 es 0). |
| `confirm("¿Seguro?")` | Lanza popup. Devuelve `true` (Aceptar) o `false` (Cancelar). |

---

## 9. EVENTOS (Disparadores)
*Cuándo ocurre el código*

| Evento HTML | Se dispara cuando... | Uso típico |
| :--- | :--- | :--- |
| `onclick="..."` | Se hace clic con el ratón. | Botones, Enlaces, Imágenes. |
| `onmouseover="..."` | El ratón entra en la zona. | Hover de imágenes, menús. |
| `onmouseout="..."` | El ratón sale de la zona. | Restaurar estado original. |
| `onkeyup="..."` | Se suelta una tecla al escribir. | Validación en tiempo real (inputs). |
| `onsubmit="..."` | Se intenta enviar un formulario. | Validar antes de enviar. |

---

### 💡 CONSEJO DE ORO: EL ORDEN DE CREACIÓN
Para crear elementos dinámicos complejos (Ejercicio 8), sigue siempre este orden mental:

1.  **CREAR**: `let x = document.createElement("tag")`
2.  **CONFIGURAR**: `x.innerText = "Hola"`, `x.style...`, `x.className...`
3.  **INSERTAR**: `padre.appendChild(x)` (Siempre al final del todo).