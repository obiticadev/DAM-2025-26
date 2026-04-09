# 📚 Módulo 5: Patrones de Diseño Estructurales (GoF)

> **Ejercicios cubiertos**: 61 – 75  
> **Código fuente**: `src/main/java/modulo5_patrones_estructurales/`

---

## 5.1 Visión General de los Patrones Estructurales

Los patrones estructurales se encargan de la **composición** de clases y objetos para formar estructuras más grandes. Se centran en cómo las clases y objetos se combinan para crear nuevas funcionalidades.

```mermaid
graph TD
    EST["7 Patrones Estructurales"] --> AD["Adapter"]
    EST --> DE["Decorator"]
    EST --> FA["Facade"]
    EST --> PR["Proxy"]
    EST --> CO["Composite"]
    EST --> BR["Bridge"]
    EST --> FL["Flyweight"]
    
    AD -->|"Incompatibilidad"| AD1["Traduce interfaces"]
    DE -->|"Extensión"| DE1["Añade responsabilidades"]
    FA -->|"Simplificación"| FA1["Interfaz unificada"]
    PR -->|"Control"| PR1["Intermediario de acceso"]
    CO -->|"Jerarquía"| CO1["Árbol parte-todo"]
    BR -->|"Desacoplamiento"| BR1["Abstracción vs Implementación"]
    FL -->|"Memoria"| FL1["Comparte estado intrínseco"]
    
    style EST fill:#3b82f6,color:#fff
```

---

## 5.2 Adapter — Traducir Interfaces Incompatibles

### Intención
Convertir la interfaz de una clase en otra que el cliente espera. Permite que clases con interfaces incompatibles trabajen juntas.

```mermaid
classDiagram
    class Target {
        <<interface>>
        +request(): String
    }
    
    class Adaptee {
        +specificRequest(): String
    }
    
    class Adapter {
        -adaptee: Adaptee
        +request(): String
    }
    
    Target <|.. Adapter
    Adapter o-- Adaptee : delega
    
    note for Adapter "Traduce request()\na specificRequest()"
```

```mermaid
sequenceDiagram
    participant C as Cliente
    participant A as Adapter
    participant L as Legacy
    
    C->>A: request()
    A->>L: specificRequest()
    L-->>A: resultado legacy
    A-->>C: resultado adaptado
```

> **Analogía**: Un adaptador de enchufes europeo⟶americano.

---

## 5.3 Decorator — Añadir Responsabilidades Dinámicamente

### Intención
Añadir responsabilidades adicionales a un objeto de forma dinámica. Los decoradores proporcionan una alternativa flexible a la herencia para extender funcionalidad.

```mermaid
classDiagram
    class Component {
        <<interface>>
        +operacion(): String
    }
    
    class ConcreteComponent {
        +operacion(): String
    }
    
    class Decorator {
        <<abstract>>
        -wrapped: Component
        +operacion(): String
    }
    
    class ConcreteDecoratorA {
        +operacion(): String
        +extraBehavior(): void
    }
    
    class ConcreteDecoratorB {
        +operacion(): String
    }
    
    Component <|.. ConcreteComponent
    Component <|.. Decorator
    Decorator <|-- ConcreteDecoratorA
    Decorator <|-- ConcreteDecoratorB
    Decorator o-- Component : wraps
```

```mermaid
graph LR
    BASE["Café Base\n$2.00"] --> D1["+ Leche\n+$0.50"]
    D1 --> D2["+ Chocolate\n+$0.75"]
    D2 --> D3["+ Crema\n+$0.30"]
    D3 --> TOTAL["Total: $3.55"]
    
    style BASE fill:#6b4226,color:#fff
    style TOTAL fill:#22c55e,color:#fff
```

> **Clave**: Los decoradores se apilan como capas de cebolla.

---

## 5.4 Facade — Simplificar Sistemas Complejos

### Intención
Proporcionar una interfaz unificada y simplificada a un conjunto de interfaces de un subsistema.

```mermaid
graph TD
    CLIENT["Cliente"] --> FACADE["Facade"]
    
    FACADE --> S1["Subsistema 1"]
    FACADE --> S2["Subsistema 2"]
    FACADE --> S3["Subsistema 3"]
    FACADE --> S4["Subsistema 4"]
    
    style FACADE fill:#22c55e,color:#fff
    style CLIENT fill:#6366f1,color:#fff
```

> **Analogía**: Un camarero en un restaurante. Tú no hablas con el cocinero, el sommelier y el cajero por separado. Le dices al camarero lo que quieres y él coordina todo.

---

## 5.5 Proxy — Intermediario de Acceso

### Intención
Proporcionar un sustituto o representante de otro objeto para controlar el acceso a él.

```mermaid
classDiagram
    class Subject {
        <<interface>>
        +request(): void
    }
    
    class RealSubject {
        +request(): void
    }
    
    class Proxy {
        -realSubject: RealSubject
        +request(): void
    }
    
    Subject <|.. RealSubject
    Subject <|.. Proxy
    Proxy o-- RealSubject : controla acceso
```

### Tipos de Proxy

```mermaid
graph TD
    PROXY["Proxy"] --> VP["Virtual Proxy\nLazy Loading"]
    PROXY --> PP["Protection Proxy\nControl de acceso"]
    PROXY --> CP["Caching Proxy\nAlmacena resultados"]
    PROXY --> LP["Logging Proxy\nRegistra accesos"]
    
    VP -->|"Retrasa creación"| VP1["Crea el objeto real\nsolo cuando se necesita"]
    PP -->|"Verifica permisos"| PP1["Solo usuarios autorizados\nacceden al objeto real"]
    CP -->|"Evita recálculo"| CP1["Si ya se calculó,\nretorna el resultado guardado"]
    
    style PROXY fill:#f97316,color:#fff
```

---

## 5.6 Composite — Tratar Simples y Compuestos Igual

### Intención
Componer objetos en estructuras de árbol para representar jerarquías parte-todo. Permite tratar objetos individuales y composiciones de manera uniforme.

```mermaid
classDiagram
    class Component {
        <<interface>>
        +operacion(): void
        +agregar(Component): void
        +eliminar(Component): void
    }
    
    class Leaf {
        +operacion(): void
    }
    
    class Composite {
        -hijos: Component[]
        +operacion(): void
        +agregar(Component): void
        +eliminar(Component): void
    }
    
    Component <|.. Leaf
    Component <|.. Composite
    Composite o-- Component : contiene
```

```mermaid
graph TD
    ROOT["📁 raiz"] --> SRC["📁 src"]
    ROOT --> README["📄 README.md"]
    SRC --> MAIN["📁 main"]
    SRC --> TEST["📁 test"]
    MAIN --> APP["📄 App.java"]
    MAIN --> UTIL["📄 Utils.java"]
    TEST --> APPT["📄 AppTest.java"]
    
    style ROOT fill:#6366f1,color:#fff
    style SRC fill:#8b5cf6,color:#fff
    style MAIN fill:#a78bfa,color:#fff
```

---

## 5.7 Bridge — Separar Abstracción de Implementación

### Intención
Desacoplar una abstracción de su implementación para que ambas puedan variar independientemente.

```mermaid
classDiagram
    class Abstraction {
        -impl: Implementation
        +operation(): void
    }
    
    class RefinedAbstraction {
        +operation(): void
    }
    
    class Implementation {
        <<interface>>
        +operationImpl(): void
    }
    
    class ConcreteImplA {
        +operationImpl(): void
    }
    
    class ConcreteImplB {
        +operationImpl(): void
    }
    
    Abstraction <|-- RefinedAbstraction
    Abstraction o-- Implementation
    Implementation <|.. ConcreteImplA
    Implementation <|.. ConcreteImplB
```

> **Sin Bridge**: N abstracciones × M implementaciones = N×M clases.  
> **Con Bridge**: N abstracciones + M implementaciones = N+M clases.

---

## 5.8 Flyweight — Compartir Para Ahorrar Memoria

### Intención
Usar compartición para soportar eficientemente un gran número de objetos de grano fino.

```mermaid
graph TD
    subgraph POOL["Flyweight Pool"]
        FA["'A' - Times 12pt"]
        FB["'B' - Times 12pt"]
        FC["'C' - Arial 14pt"]
    end
    
    T1["Texto pos 1"] --> FA
    T2["Texto pos 5"] --> FA
    T3["Texto pos 3"] --> FB
    T4["Texto pos 7"] --> FA
    T5["Texto pos 9"] --> FC
    
    NOTE["3 objetos compartidos\nen vez de 5 independientes"]
    
    style POOL fill:#22c55e,color:#fff
    style NOTE fill:#6366f1,color:#fff
```

| Concepto | Descripción |
|----------|-------------|
| **Estado intrínseco** | Compartido, inmutable (carácter, fuente) |
| **Estado extrínseco** | Único por contexto (posición, color) |

---

## 5.9 Mapa de Ejercicios del Módulo 5

| Ejercicio | Patrón | Concepto | Dificultad |
|-----------|--------|----------|------------|
| 61 | Adapter | Adaptar API legacy de pagos | ⭐⭐⭐ |
| 62 | Adapter | Adapter bidireccional XML↔JSON | ⭐⭐⭐ |
| 63 | Decorator | Decoradores de bebidas (Starbucks) | ⭐⭐⭐ |
| 64 | Decorator | Decoradores de flujo de datos (cifrado, compresión) | ⭐⭐⭐⭐ |
| 65 | Facade | Facade de Home Theater | ⭐⭐⭐ |
| 66 | Facade | Facade de proceso de pedido e-commerce | ⭐⭐⭐ |
| 67 | Proxy | Proxy de Protección con roles | ⭐⭐⭐ |
| 68 | Proxy | Proxy de Caché con lazy loading | ⭐⭐⭐ |
| 69 | Composite | Sistema de archivos (árbol) | ⭐⭐⭐⭐ |
| 70 | Composite | Organigrama empresarial | ⭐⭐⭐ |
| 71 | Bridge | Dispositivos y controles remotos | ⭐⭐⭐ |
| 72 | Bridge | Formas y renderizadores (SVG, Canvas) | ⭐⭐⭐ |
| 73 | Flyweight | Editor de texto con caracteres compartidos | ⭐⭐⭐⭐ |
| 74 | Combinado | Adapter + Decorator para streams | ⭐⭐⭐⭐ |
| 75 | Integración | Mini-framework con múltiples patrones | ⭐⭐⭐⭐⭐ |

---

> **🔗 Código fuente**: `src/main/java/modulo5_patrones_estructurales/`  
> ¡Lee esta teoría antes de tocar una sola línea de código!
