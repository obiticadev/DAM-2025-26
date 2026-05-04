# 06 — instanceof con Interfaces y Polimorfismo

> Referencia: [Ejercicios 26–36] — `nivel08_instanceof_basico/`, `nivel09_instanceof_avanzado/`, `nivel10_instanceof_collections/`

---

## 1. ¿Qué es instanceof?

El operador `instanceof` comprueba en **tiempo de ejecución** si un objeto es instancia de una clase, subclase o implementa una interfaz determinada. Retorna `boolean`.

```mermaid
flowchart TD
    A["Object obj = ???"] --> B{"obj instanceof Identificable?"}
    B -- true --> C["El objeto implementa Identificable"]
    B -- false --> D["El objeto NO implementa Identificable"]
    C --> E["Puedo hacer cast seguro:<br/>(Identificable) obj"]
```

---

## 2. Pattern Matching con instanceof (Java 16+)

Desde Java 16 existe el **pattern matching para instanceof**, que combina la comprobación y el cast en una sola expresión:

```mermaid
sequenceDiagram
    participant C as Código
    participant JVM as JVM
    
    C->>JVM: if (obj instanceof Empleado e)
    JVM->>JVM: ¿obj es instancia de Empleado?
    alt Sí
        JVM-->>C: true + variable 'e' ya casteada
        Note right of C: e.getSalario() — sin cast explícito
    else No
        JVM-->>C: false — variable 'e' NO accesible
    end
```

### Comparación: antes vs después

```java
// ANTES (Java < 16) — cast manual
if (obj instanceof Empleado) {
    Empleado e = (Empleado) obj;
    System.out.println(e.getSalario());
}

// DESPUÉS (Java 16+) — pattern matching
if (obj instanceof Empleado e) {
    System.out.println(e.getSalario());
}
```

---

## 3. instanceof con interfaces

El verdadero poder de `instanceof` brilla con las **interfaces**. Un objeto puede implementar múltiples interfaces, y `instanceof` permite descubrir qué contratos cumple:

```mermaid
classDiagram
    class Identificable {
        <<interface>>
        +getId() String
        +getNombre() String
    }
    class Auditable {
        <<interface>>
        +getFechaCreacion() LocalDateTime
        +getUltimaModificacion() LocalDateTime
        +getCreadoPor() String
    }
    class Clasificable {
        <<interface>>
        +getCategoria() String
        +getTipo() String
    }
    class Procesable {
        <<interface>>
        +procesar() void
        +getPrioridad() int
        +isProcesado() boolean
    }

    class Empleado {
        implements Identificable
        implements Auditable
    }
    class Producto {
        implements Identificable
        implements Clasificable
    }
    class Pedido {
        implements Identificable
        implements Procesable
    }
    class Evento {
        implements Procesable
        implements Auditable
    }

    Identificable <|.. Empleado
    Auditable <|.. Empleado
    Identificable <|.. Producto
    Clasificable <|.. Producto
    Identificable <|.. Pedido
    Procesable <|.. Pedido
    Procesable <|.. Evento
    Auditable <|.. Evento
```

### Tabla de implementación

| Clase | Identificable | Auditable | Clasificable | Procesable |
|---|---|---|---|---|
| `Empleado` | ✅ | ✅ | ❌ | ❌ |
| `Producto` | ✅ | ❌ | ✅ | ❌ |
| `Pedido` | ✅ | ❌ | ❌ | ✅ |
| `Evento` | ❌ | ✅ | ❌ | ✅ |

---

## 4. Flujo de decisión con instanceof

```mermaid
flowchart TD
    A["Object obj recibido"] --> B{"obj instanceof Identificable id?"}
    B -- Sí --> C["Extraer id.getId(), id.getNombre()"]
    B -- No --> D["No tiene identidad"]
    C --> E{"obj instanceof Auditable aud?"}
    E -- Sí --> F["Extraer aud.getFechaCreacion(), aud.getCreadoPor()"]
    E -- No --> G["No es auditable"]
    C --> H{"obj instanceof Procesable proc?"}
    H -- Sí --> I["Ejecutar proc.procesar(), leer proc.getPrioridad()"]
    H -- No --> J["No es procesable"]
    C --> K{"obj instanceof Clasificable clas?"}
    K -- Sí --> L["Extraer clas.getCategoria(), clas.getTipo()"]
    K -- No --> M["No es clasificable"]
```

> Un mismo objeto puede cumplir **múltiples** checks de instanceof simultáneamente. Por ejemplo, un `Empleado` pasa tanto `instanceof Identificable` como `instanceof Auditable`.

---

## 5. instanceof en colecciones heterogéneas

El caso de uso más potente: una colección `ArrayList<Object>` que contiene objetos de distintas clases, y necesitas procesarlos de forma polimórfica:

```mermaid
sequenceDiagram
    participant C as Código
    participant AL as ArrayList<Object>
    participant I as Identificable?
    participant A as Auditable?
    participant P as Procesable?

    C->>AL: for (Object obj : lista)
    loop Para cada objeto
        C->>I: obj instanceof Identificable id?
        alt Sí
            I-->>C: id.getId(), id.getNombre()
        end
        C->>A: obj instanceof Auditable aud?
        alt Sí
            A-->>C: aud.getCreadoPor()
        end
        C->>P: obj instanceof Procesable proc?
        alt Sí
            P-->>C: proc.procesar()
        end
    end
```

### Patrón de filtrado por interfaz

```java
// Filtrar todos los Identificables de una lista heterogénea
ArrayList<Identificable> filtrados = new ArrayList<>();
for (Object obj : listaMixta) {
    if (obj instanceof Identificable id) {
        filtrados.add(id);
    }
}
```

---

## 6. instanceof vs getClass()

```mermaid
flowchart LR
    subgraph "instanceof"
        A["Empleado e = new Empleado(...)"]
        A --> B["e instanceof Empleado → true"]
        A --> C["e instanceof Identificable → true"]
        A --> D["e instanceof Auditable → true"]
        A --> E["e instanceof Object → true"]
    end

    subgraph "getClass()"
        F["e.getClass() == Empleado.class → true"]
        G["e.getClass() == Object.class → false"]
    end
```

| | `instanceof` | `getClass()` |
|---|---|---|
| Comprueba | Clase + superclases + interfaces | Solo la clase exacta |
| Herencia | ✅ Sube por la jerarquía | ❌ Solo la clase concreta |
| Interfaces | ✅ | ❌ |
| null | `null instanceof X` → `false` | `null.getClass()` → NullPointerException |

---

## 7. Reglas de seguridad con instanceof

```mermaid
stateDiagram-v2
    [*] --> ReciboObjeto: Object obj
    ReciboObjeto --> CheckNull: obj == null?
    CheckNull --> SeguroFalse: null instanceof X = false (siempre)
    CheckNull --> CheckTipo: obj != null
    CheckTipo --> PatternMatch: if (obj instanceof Tipo t)
    PatternMatch --> UsarVariable: t está disponible y es del tipo correcto
    PatternMatch --> NoPasa: obj no es del tipo → t NO existe
```

- `null instanceof <cualquierTipo>` siempre retorna `false` — nunca lanza excepción.
- El pattern matching solo crea la variable si el check pasa.
- No se necesita cast explícito tras un pattern matching exitoso.

---

## Puntos clave para los ejercicios

- `instanceof` funciona con clases, subclases e interfaces.
- Java 16+ permite **pattern matching**: `if (obj instanceof Tipo t)`.
- Un objeto puede pasar múltiples `instanceof` si implementa múltiples interfaces.
- `null instanceof X` es siempre `false` (safe).
- En colecciones heterogéneas (`ArrayList<Object>`), `instanceof` es la herramienta de dispatch polimórfico.
- Prefiere `instanceof` sobre `getClass()` cuando quieres soportar herencia e interfaces.
