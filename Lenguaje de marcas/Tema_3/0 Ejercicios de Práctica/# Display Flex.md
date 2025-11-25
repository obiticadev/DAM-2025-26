# 📦 Informe de Comandos CSS Flexbox

Flexbox es un módulo de diseño unidimensional que permite distribuir el espacio entre los ítems de una interfaz y alinearlos de manera eficiente. A continuación se detallan las propiedades explicadas en el tutorial.

---

## 1. Activación del Flexbox

Para comenzar a utilizar cualquiera de las propiedades siguientes, es necesario definir el contenedor.

| Propiedad | Uso |
| --- | --- |
| **`display: flex;`** | Convierte el elemento en un **contenedor flexible**. Sus hijos directos se convierten en *flex items*. Por defecto, los alinea en una fila (horizontal). |

---

## 2. Propiedades del Contenedor (Parent)

Estas propiedades se aplican al `div` o elemento que envuelve a los ítems y definen la estructura general y la alineación global.

### 📐 Dirección y Flujo

Controlan hacia dónde se dirigen los elementos y si deben saltar a una nueva línea.

- **`flex-direction`**: Define el eje principal del contenedor.
    - `row` *(default)*: Alinea los elementos horizontalmente (izquierda a derecha).
    - `column`: Alinea los elementos verticalmente (arriba a abajo).
    - `row-reverse`: Horizontal, pero invierte el orden (derecha a izquierda).
    - `column-reverse`: Vertical, pero invierte el orden (abajo a arriba).
- **`flex-wrap`**: Controla si los elementos deben mantenerse en una sola línea o pueden envolverse (saltar de línea) si no hay espacio.
    - `nowrap` *(default)*: Los elementos intentan encajar en una sola línea (pueden encogerse o desbordarse).
    - `wrap`: Si no hay espacio, los elementos saltan a la línea siguiente.
    - `wrap-reverse`: Igual que *wrap*, pero las nuevas líneas aparecen hacia arriba (orden inverso).
- **`flex-flow`**: **Shorthand** (atajo) para combinar las dos anteriores.
    - *Ejemplo:* `flex-flow: column wrap;` (Dirección columna y permite salto de línea).

### 🎯 Alineación y Distribución

Controlan cómo se utiliza el espacio sobrante y cómo se alinean los elementos entre sí.

- **`justify-content`**: Alinea los elementos a lo largo del **Eje Principal** (horizontal si es *row*, vertical si es *column*).
    - `center`: Centra los elementos.
    - `flex-start`: Alinea al inicio del contenedor.
    - `flex-end`: Alinea al final del contenedor.
    - `space-between`: Distribuye el espacio **entre** los elementos (el primero al inicio, el último al final, espacio en el medio).
    - `space-around`: Distribuye espacio **alrededor** de los elementos (incluyendo los bordes, aunque el espacio entre elementos se suma).
- **`align-items`**: Alinea los elementos a lo largo del **Eje Cruzado** (perpendicular al eje principal).
    - `center`: Centra los elementos verticalmente (en caso de fila).
    - `flex-start`: Alinea al borde superior.
    - `flex-end`: Alinea al borde inferior.
    - `stretch`: Estira los elementos para ocupar todo el alto del contenedor (si no tienen alto definido).
    - `baseline`: Alinea los elementos basándose en la línea base de su texto (útil cuando hay fuentes de diferentes tamaños).
- **`align-content`**: Similar a *justify-content*, pero funciona en el eje cruzado y **solo cuando hay múltiples líneas** (cuando se usa `flex-wrap`).
    - `center`, `flex-start`, `flex-end`: Alinea el bloque de líneas.
    - `space-between`, `space-around`: Distribuye el espacio entre las líneas.
    - `stretch`: Estira las líneas para ocupar el espacio vertical disponible.

---

## 3. Propiedades de los Elementos (Children)

Estas propiedades se aplican individualmente a los elementos que están *dentro* del contenedor flex para alterar su comportamiento específico.

### 🔢 Orden y Posicionamiento

- **`order`**: Permite cambiar el orden visual de los elementos sin modificar el HTML.
    - Acepta números enteros (ej. `1`, `2`, `4`). Los números más bajos van primero.
    - Por defecto es `0`.
- **`align-self`**: Permite sobrescribir la propiedad `align-items` del contenedor para **un solo elemento**.
    - *Ejemplo:* Si el contenedor tiene `align-items: center`, un hijo con `align-self: flex-end` se irá al final.

### 📏 Tamaño y Flexibilidad

Controlan cómo los elementos crecen o se encogen para llenar el espacio.

- **`flex-grow`**: Define la capacidad de un elemento para **crecer** si hay espacio disponible.
    - Valor numérico (ej. `1`, `3`).
    - Si todos tienen `1`, comparten el espacio igual. Si uno tiene `3`, intentará ocupar el triple de espacio libre que los demás.
- **`flex-shrink`**: Define la capacidad de un elemento para **encogerse** si falta espacio.
    - `1` *(default)*: El elemento se encogerá si es necesario.
    - `0`: El elemento **no** se encogerá, manteniendo su tamaño original aunque se desborde el contenedor.

---

### 💡 Nota Final

Se concluye mencionando que **Flexbox** es ideal para layouts de menor escala o unidimensionales. Para layouts a gran escala o bidimensionales (filas y columnas simultáneas complejas), se recomienda usar **CSS Grid**.