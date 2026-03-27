# Esquema Completo del Java Collections Framework (Mapas)

```mermaid
flowchart TD
    %% Definición de estilos profesionales
    classDef root fill:#311b92,stroke:#fff,stroke-width:2px,color:#fff,rx:10,ry:10
    classDef interface fill:#e3f2fd,stroke:#1565c0,stroke-width:2px,color:#0d47a1,stroke-dasharray: 4 4,rx:8,ry:8
    classDef classNode fill:#e8f5e9,stroke:#2e7d32,stroke-width:2px,color:#1b5e20,rx:4,ry:4
    classDef legacy fill:#ffebee,stroke:#c62828,stroke-width:2px,color:#b71c1c,rx:4,ry:4,stroke-dasharray: 2 2
    classDef concurrent fill:#fff3e0,stroke:#e65100,stroke-width:2px,color:#e65100,rx:4,ry:4

    %% Raíz
    Map(["🗺️ «Interface» Map<br/>(Clave → Valor)"]):::root

    %% Subgrupos de Interfaces
    subgraph Interfaces Ordenación
        SortedMap[["🔴 «Interface» SortedMap"]]:::interface
        NavigableMap[["🔴 «Interface» NavigableMap"]]:::interface
        SortedMap -->|extends| NavigableMap
    end

    subgraph Interfaces Concurrencia
        ConcurrentMap[["⚡ «Interface» ConcurrentMap"]]:::interface
    end

    Map ==>|extends| SortedMap
    Map ==>|extends| ConcurrentMap

    %% Implementaciones Generales
    HashMap["⚙️ HashMap<br/>(Rápido, sin orden)"]:::classNode
    LinkedHashMap["🔗 LinkedHashMap<br/>(Orden inserción/acceso)"]:::classNode
    Hashtable["⚠️ Hashtable<br/>(Legacy, Sincronizado)"]:::legacy
    
    Map -.->|implements| HashMap
    HashMap -->|extends| LinkedHashMap
    Map -.->|implements| Hashtable

    %% Implementaciones de Ordenación
    TreeMap["🌲 TreeMap<br/>(Ordenado natural por Clave)"]:::classNode
    NavigableMap -.->|implements| TreeMap

    %% Implementaciones Concurrentes
    ConcurrentHashMap["🚀 ConcurrentHashMap<br/>(Alto rendimiento Multi-hilo)"]:::concurrent
    ConcurrentMap -.->|implements| ConcurrentHashMap
```

---

## Leyenda y Guía de Referencia

### 1. Interfaces Principales

#### `Map<K, V>`
- **Definición**: Representa un mapeo entre "Clave/Llave" (Key) a un "Valor" (Value). Un Mapa no tolera llaves clonadas y cada llave puede identificar a máximo 1 valor (como un DNI al ciudadano). NOTA: Visualmente son colecciones, pero `Map` como tal **no hereda de Collection** internamente en Java por concepto purista.
- **Casos de Uso**: Representar directores indexados de BBDD que relacionan IDs frente a entidades gigantes, uso de Diccionarios y censos generalistas.
- **Métodos Principales**:
  - 🔸 `put(K key, V value)`: Agrega la asociación al mapa. Si había una misma llave lo pisa y reescribe al nuevo valor.
  - 🔸 `get(Object key)`: Retorna al instante el "Valor" a quién pertenece la llave indicada o `null`.
  - 🔸 `containsKey(Object key)` / `containsValue(Object value)`: Examina si dicho nombre de llavero o valor almacenado residen ahora en la matriz.
  - 🔸 `remove(Object key)`: Evapora y borra la llave dada (y lógicamente al mismo su valor asignado acompañante).
  - 🔸 `keySet()`: Extrae todas las claves (Keys) sin valores y las retorna de puro golpe modeladas como un tipo `Set`.
  - 🔸 `values()`: Extrae todos los "Values" desvinculados de su clave en formato `Collection`.
  - 🔸 `entrySet()`: Retorna su forma empaquetada real `Map.Entry`, clave y valor ligados útiles si deseas aplicar iteraciones y sentencias For completas por encima del mapa sin separar su unión.

#### `SortedMap<K, V>` y `NavigableMap<K, V>`
- **Definición**: Las interfaces especializadas de este campo imponen y supervisan orden estricto natural sobre cada Key introducida, `Navigable` aporta facilidades de investigación.
- **Métodos Principales**:
  - 🔹 `firstKey()` / `lastKey()`: Adjudica el extremo menor bajo o el altísimo.
  - 🔹 `lowerKey(K key)` / `higherKey(K key)`: Búsquedas aproximadas muy óptimas que consiguen llaves directamente vecinas.
  - 🔹 `subMap(K fromKey, K toKey)`: Construye un mini-mapa cortando o delimitando extractos al ser capaz de saber el orden interno exacto.

#### `ConcurrentMap<K, V>`
- **Definición**: Interfaz experta de multi-hilos segura (Thread-safe) optimizada.
- **Métodos Exclusivos Atómicos**:
  - 🔹 `putIfAbsent(K key, V value)`: Operación ininterrumpible. Introduce valor y llave solo bajo seguridad extrema si esa otra llave no yacía alojada antes.
  - 🔹 `replace(K key, V oldValue, V newValue)`: Reemplaza asegurándose que antes el valor anterior coincida de raíz de que nadie más se nos haya interpuesto temporalmente.

---

### 2. Implementaciones concretas (Clases)

#### `HashMap<K, V>`
- **Qué hace**: Su pilar central depende del cálculo Hash numérico de la "Key". Carece de garantías organizativas ni de iteración futura controlable. Es permitido guardar hasta un Key de valor puramente `null` e infinitos "Valores" nulos.
- **Cuándo usarla**: La cabecera central recomendada de cualquier software estándar. Opcioón más ligera de un mapa de coste $\mathcal{O}(1)$ generalizado. Funcional en la mayoría de escenarios corrientes y casuísticas diarias al buscar una Clave ID.

#### `LinkedHashMap<K, V>`
- **Qué hace**: Integra un enlazado constante detrás que cose y retiene memorizado un registro estático del acceso al momento de crear, salvaguardando lo indeseable organizativo que resulta el simple `HashMap` clásico.
- **Cuándo usarla**: Siempre que se disponga o interese de exportar todo o imprimir del Mapa su volumen total manteniendo el modelo formal donde los objetos introducidos primero aparezcan siempre primero. Se implementa además muy asiduamente para montarse mecánicas de *caché con purga por antigüedad (LRU)*.

#### `TreeMap<K, V>`
- **Qué hace**: Implementa `NavigableMap` haciendo sostén al Mapa completo recubriéndose en forma de estructura de "Red-Black tree" garantizando tiempo ordenado logarítmico sobre toda mutación o consulta básica.
- **Cuándo usarla**: Cuando precisemos indexarlo TODO al vuelo o exijamos hacerle llamadas continuas de aproximaciones (tipo: dame qué clientes tienen el ID de la cuenta justo mayor a 400).

#### `Hashtable<K, V>`
- **Qué hace**: La antítesis vieja de `HashMap`. Sincronizada y a prueba de asincronía (Multi-Threading Thread Safe) en exclusividad básica. Al revés que el HashMap no tolerará en la vida claves `null`.
- **Cuándo usarla**: Es una colección puramente identificada de **Legacy Class**. Su rendimiento de cuellos de botella forjó a dejar de usarse, habiendo alternativas que superaron la oferta con facilidad hoy. No implementar modernamente.

#### `ConcurrentHashMap<K, V>`
- **Qué hace**: Modernización suprema nacida para abarcar la multiturbo asincronía en aplicaciones y Servlets. Segmenta pedazos separados de su diccionario y deja leer en uno o editar por otro lado.
- **Cuándo usarla**: El "Must Have" obligatorio para variables o diccionarios Maps que deben estar hospedandos globalmente para una app donde Múltiples Procesos se pisan entre sí consultándole o alterándole el contador velozmente, a una seguridad impoluta sin atascar la memoria y recursos completos compartidos.
