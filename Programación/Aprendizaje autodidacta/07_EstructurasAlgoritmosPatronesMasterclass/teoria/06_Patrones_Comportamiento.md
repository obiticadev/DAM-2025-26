# 📚 Módulo 6: Patrones de Diseño de Comportamiento (GoF)

> **Ejercicios cubiertos**: 76 – 100  
> **Código fuente**: `src/main/java/modulo6_patrones_comportamiento/`

---

## 6.1 Visión General

Los patrones de comportamiento se ocupan de la **comunicación** entre objetos y la **asignación de responsabilidades**. Definen cómo los objetos interactúan y distribuyen la lógica.

```mermaid
graph TD
    COMP["11 Patrones de Comportamiento"] --> OB["Observer"]
    COMP --> ST["Strategy"]
    COMP --> CMD["Command"]
    COMP --> IT["Iterator"]
    COMP --> STA["State"]
    COMP --> TM["Template Method"]
    COMP --> COR["Chain of Responsibility"]
    COMP --> MED["Mediator"]
    COMP --> MEM["Memento"]
    COMP --> VIS["Visitor"]
    COMP --> INT["Interpreter"]
    
    style COMP fill:#f97316,color:#fff
```

---

## 6.2 Observer — Suscripción a Eventos

Cuando un objeto cambia de estado, notifica automáticamente a todos sus dependientes (observadores).

```mermaid
classDiagram
    class Subject {
        -observers: Observer[]
        +registrar(Observer): void
        +eliminar(Observer): void
        +notificar(): void
    }
    class Observer {
        <<interface>>
        +actualizar(Subject): void
    }
    class ConcreteObserverA { +actualizar(Subject): void }
    class ConcreteObserverB { +actualizar(Subject): void }
    Subject o-- Observer
    Observer <|.. ConcreteObserverA
    Observer <|.. ConcreteObserverB
```

> **Analogía**: YouTube — te suscribes a un canal y recibes notificaciones.

---

## 6.3 Strategy — Intercambiar Algoritmos en Runtime

Encapsula una familia de algoritmos y los hace intercambiables. El cliente elige qué estrategia usar sin cambiar su código.

```mermaid
classDiagram
    class Context {
        -strategy: Strategy
        +setStrategy(Strategy): void
        +ejecutar(): void
    }
    class Strategy {
        <<interface>>
        +algoritmo(datos): Resultado
    }
    class StrategyA { +algoritmo(datos): Resultado }
    class StrategyB { +algoritmo(datos): Resultado }
    Context o-- Strategy
    Strategy <|.. StrategyA
    Strategy <|.. StrategyB
```

---

## 6.4 Command — Encapsular Acciones como Objetos

Convierte una solicitud en un objeto independiente que contiene toda la información sobre la solicitud. Permite deshacer, rehacer y encolar operaciones.

```mermaid
sequenceDiagram
    participant C as Cliente
    participant I as Invoker
    participant CMD as Command
    participant R as Receiver
    
    C->>CMD: new ConcreteCommand(receiver)
    C->>I: setCommand(cmd)
    I->>CMD: ejecutar()
    CMD->>R: accion()
    I->>CMD: deshacer()
    CMD->>R: accionInversa()
```

---

## 6.5 Iterator — Recorrer sin Exponer

Proporciona acceso secuencial a los elementos de una colección sin exponer su representación interna.

```mermaid
classDiagram
    class Iterator {
        <<interface>>
        +hasNext(): boolean
        +next(): Object
        +reset(): void
    }
    class Collection {
        <<interface>>
        +crearIterador(): Iterator
    }
    class ConcreteCollection {
        -elementos: Object[]
        +crearIterador(): Iterator
    }
    class ConcreteIterator {
        -posicion: int
        +hasNext(): boolean
        +next(): Object
    }
    Collection <|.. ConcreteCollection
    Iterator <|.. ConcreteIterator
    ConcreteCollection ..> ConcreteIterator
```

---

## 6.6 State — Comportamiento que Cambia con el Estado

Permite que un objeto altere su comportamiento cuando cambia su estado interno. Parece que el objeto cambia de clase.

```mermaid
stateDiagram-v2
    [*] --> Borrador
    Borrador --> EnRevision: enviarARevision()
    EnRevision --> Publicado: aprobar()
    EnRevision --> Borrador: rechazar()
    Publicado --> [*]
```

---

## 6.7 Template Method — Algoritmo con Pasos Customizables

Define el esqueleto de un algoritmo en la superclase, permitiendo que las subclases redefinan ciertos pasos sin cambiar la estructura.

```mermaid
classDiagram
    class AbstractClass {
        +templateMethod(): void
        #paso1(): void
        #paso2(): void*
        #paso3(): void*
        #hook(): void
    }
    class ConcreteClassA {
        #paso2(): void
        #paso3(): void
    }
    class ConcreteClassB {
        #paso2(): void
        #paso3(): void
        #hook(): void
    }
    AbstractClass <|-- ConcreteClassA
    AbstractClass <|-- ConcreteClassB
```

> Los **hooks** son pasos opcionales con implementación vacía por defecto.

---

## 6.8 Chain of Responsibility — Cadena de Manejadores

Pasa la solicitud a lo largo de una cadena de manejadores. Cada manejador decide si procesa la solicitud o la pasa al siguiente.

```mermaid
graph LR
    REQ["Solicitud"] --> H1["Handler 1"]
    H1 -->|"No puedo"| H2["Handler 2"]
    H2 -->|"No puedo"| H3["Handler 3"]
    H3 -->|"Yo me encargo"| RES["Procesado"]
    
    style RES fill:#22c55e,color:#fff
```

---

## 6.9 Mediator — Coordinador Central

Reduce las dependencias directas entre objetos forzando la comunicación a través de un mediador central.

```mermaid
graph TD
    subgraph SIN["Sin Mediator: N*(N-1)/2 conexiones"]
        A1["A"] --- B1["B"]
        A1 --- C1["C"]
        A1 --- D1["D"]
        B1 --- C1
        B1 --- D1
        C1 --- D1
    end
    
    subgraph CON["Con Mediator: N conexiones"]
        A2["A"] --- M["Mediator"]
        B2["B"] --- M
        C2["C"] --- M
        D2["D"] --- M
    end
    
    style M fill:#22c55e,color:#fff
```

---

## 6.10 Memento — Guardar y Restaurar Estado

Captura y almacena el estado interno de un objeto para poder restaurarlo posteriormente sin violar la encapsulación.

```mermaid
sequenceDiagram
    participant O as Originator
    participant M as Memento
    participant C as Caretaker
    
    O->>M: crearMemento()
    M-->>C: guardar(memento)
    Note over O: estado cambia...
    C->>O: restaurar(memento)
    Note over O: estado restaurado
```

---

## 6.11 Visitor — Operaciones sobre Estructuras

Permite añadir nuevas operaciones a una estructura de objetos sin modificar las clases de los elementos.

```mermaid
classDiagram
    class Visitor {
        <<interface>>
        +visitarCirculo(Circulo): void
        +visitarRectangulo(Rectangulo): void
    }
    class Elemento {
        <<interface>>
        +aceptar(Visitor): void
    }
    class Circulo {
        +aceptar(Visitor v): void
    }
    class AreaVisitor { +visitarCirculo(): void }
    Elemento <|.. Circulo
    Visitor <|.. AreaVisitor
```

> **Double Dispatch**: `elemento.aceptar(visitor)` → `visitor.visitarCirculo(this)`

---

## 6.12 Interpreter — Evaluar Gramáticas

Define una representación de gramática y un intérprete que la evalúa.

---

## 6.13 Mapa de Ejercicios del Módulo 6

| Ejercicio | Patrón | Concepto | Dificultad |
|-----------|--------|----------|------------|
| 76 | Observer | Sistema de eventos / newsletter | ⭐⭐⭐ |
| 77 | Observer | Estación meteorológica con displays | ⭐⭐⭐ |
| 78 | Strategy | Algoritmos de ordenamiento intercambiables | ⭐⭐⭐ |
| 79 | Strategy | Sistema de descuentos en tienda | ⭐⭐⭐ |
| 80 | Command | Editor de texto con Undo/Redo | ⭐⭐⭐⭐ |
| 81 | Command | Control domótico con macros | ⭐⭐⭐ |
| 82 | Iterator | Iterador sobre colección manual | ⭐⭐⭐ |
| 83 | Iterator | Iterador sobre árbol binario | ⭐⭐⭐⭐ |
| 84 | State | Máquina expendedora | ⭐⭐⭐⭐ |
| 85 | State | Flujo de publicación (borrador→revisión→publicado) | ⭐⭐⭐ |
| 86 | Template Method | Generador de reportes (PDF, HTML, CSV) | ⭐⭐⭐ |
| 87 | Template Method | Pipeline de procesamiento de datos | ⭐⭐⭐ |
| 88 | Chain of Resp. | Middleware HTTP (auth, logging, validation) | ⭐⭐⭐⭐ |
| 89 | Chain of Resp. | Soporte técnico multi-nivel | ⭐⭐⭐ |
| 90 | Mediator | Chat room con usuarios | ⭐⭐⭐ |
| 91 | Mediator | Torre de control de aeropuerto | ⭐⭐⭐ |
| 92 | Memento | Editor con historial y Ctrl+Z | ⭐⭐⭐ |
| 93 | Memento | Sistema de checkpoints en juego | ⭐⭐⭐ |
| 94 | Visitor | Calcular área/perímetro de formas | ⭐⭐⭐⭐ |
| 95 | Visitor | Exportar AST a distintos formatos | ⭐⭐⭐⭐ |
| 96 | Interpreter | Evaluador de expresiones matemáticas | ⭐⭐⭐⭐ |
| 97 | Combinado | MVC mini-framework (Observer+Strategy+Command) | ⭐⭐⭐⭐⭐ |
| 98 | Combinado | Juego RPG (State+Strategy+Observer+Command) | ⭐⭐⭐⭐⭐ |
| 99 | Combinado | Sistema de workflows (Chain+State+Command+Memento) | ⭐⭐⭐⭐⭐ |
| 100 | FINAL | Proyecto integrador: 23 patrones GoF | ⭐⭐⭐⭐⭐ |

---

> **🔗 Código fuente**: `src/main/java/modulo6_patrones_comportamiento/`  
> ¡Lee esta teoría antes de tocar una sola línea de código!
