# 📚 Módulo 4: Patrones de Diseño Creacionales (GoF)

> **Ejercicios cubiertos**: 46 – 60  
> **Código fuente**: `src/main/java/modulo4_patrones_creacionales/`

---

## 4.1 ¿Qué son los Patrones de Diseño?

Los **Patrones de Diseño** son soluciones reutilizables a problemas comunes en el diseño de software. Fueron catalogados por el "Gang of Four" (GoF) en 1994 en 23 patrones clasificados en tres categorías:

```mermaid
graph TD
    GOF["23 Patrones GoF"] --> CREAC["Creacionales (5)"]
    GOF --> ESTRUC["Estructurales (7)"]
    GOF --> COMPORT["De Comportamiento (11)"]
    
    CREAC --> S["Singleton"]
    CREAC --> FM["Factory Method"]
    CREAC --> AF["Abstract Factory"]
    CREAC --> BU["Builder"]
    CREAC --> PR["Prototype"]
    
    style GOF fill:#6366f1,color:#fff
    style CREAC fill:#22c55e,color:#fff
    style ESTRUC fill:#3b82f6,color:#fff
    style COMPORT fill:#f97316,color:#fff
```

Los patrones **Creacionales** se encargan de la creación de objetos, abstrayendo el proceso de instanciación para hacer el sistema independiente de cómo se crean, componen y representan sus objetos.

---

## 4.2 Singleton — Una Sola Instancia Global

### Intención
Garantizar que una clase tenga **exactamente una instancia** y proporcionar un punto de acceso global a ella.

### Diagrama UML

```mermaid
classDiagram
    class Singleton {
        -static instancia: Singleton
        -Singleton()
        +static getInstance(): Singleton
        +operacion(): void
    }
    
    note for Singleton "Constructor PRIVADO\nInstancia estática única\nAcceso mediante getInstance()"
```

### Variantes de Implementación

```mermaid
flowchart TD
    SINGLETON["Singleton"] --> EAGER["Eager Initialization"]
    SINGLETON --> LAZY["Lazy Initialization"]
    SINGLETON --> DCL["Double-Check Locking"]
    SINGLETON --> HOLDER["Bill Pugh (Holder)"]
    SINGLETON --> ENUM["Enum Singleton"]
    
    EAGER -->|"Se crea al cargar la clase"| SIMPLE["Simple pero no lazy"]
    LAZY -->|"Se crea al primer uso"| NOTS["No thread-safe"]
    DCL -->|"synchronized + volatile"| SAFE["Thread-safe y lazy"]
    HOLDER -->|"Inner static class"| BEST["Mejor opcion en Java"]
    ENUM -->|"enum con un valor"| EFCT["Effective Java recomienda"]
    
    style HOLDER fill:#22c55e,color:#fff
    style ENUM fill:#22c55e,color:#fff
    style NOTS fill:#dc2626,color:#fff
```

### Cuándo usar
- Acceso a recursos compartidos (conexión BD, configuración, logger).
- Cuando exactamente una instancia coordina acciones del sistema.

### Cuándo NO usar
- Si necesitas testabilidad (dificulta mocking/testing).
- Si necesitas múltiples instancias en el futuro.

---

## 4.3 Factory Method — Delegar la Creación a Subclases

### Intención
Definir una interfaz para crear un objeto, pero permitir que las **subclases** decidan qué clase instanciar.

### Diagrama UML

```mermaid
classDiagram
    class Creator {
        <<abstract>>
        +factoryMethod(): Product*
        +operacion(): void
    }
    
    class ConcreteCreatorA {
        +factoryMethod(): Product
    }
    
    class ConcreteCreatorB {
        +factoryMethod(): Product
    }
    
    class Product {
        <<interface>>
        +usar(): void
    }
    
    class ConcreteProductA {
        +usar(): void
    }
    
    class ConcreteProductB {
        +usar(): void
    }
    
    Creator <|-- ConcreteCreatorA
    Creator <|-- ConcreteCreatorB
    Product <|.. ConcreteProductA
    Product <|.. ConcreteProductB
    ConcreteCreatorA ..> ConcreteProductA : crea
    ConcreteCreatorB ..> ConcreteProductB : crea
```

### Flujo de Ejecución

```mermaid
sequenceDiagram
    participant Client as Cliente
    participant Factory as ConcreteFactory
    participant Product as Producto
    
    Client->>Factory: crearProducto()
    Factory->>Product: new ConcreteProduct()
    Factory-->>Client: retorna Product
    Client->>Product: usar()
```

### Cuándo usar
- No sabes de antemano los tipos exactos de objetos que necesitas.
- Quieres que las subclases especifiquen qué objetos crear.
- Necesitas centralizar la lógica de creación.

---

## 4.4 Abstract Factory — Familias de Objetos Relacionados

### Intención
Proporcionar una interfaz para crear **familias** de objetos relacionados sin especificar sus clases concretas.

### Diagrama UML

```mermaid
classDiagram
    class AbstractFactory {
        <<interface>>
        +crearBoton(): Boton
        +crearCheckbox(): Checkbox
    }
    
    class WindowsFactory {
        +crearBoton(): Boton
        +crearCheckbox(): Checkbox
    }
    
    class MacFactory {
        +crearBoton(): Boton
        +crearCheckbox(): Checkbox
    }
    
    class Boton {
        <<interface>>
        +pintar(): void
    }
    
    class Checkbox {
        <<interface>>
        +marcar(): void
    }
    
    class WindowsBoton {
        +pintar(): void
    }
    
    class MacBoton {
        +pintar(): void
    }
    
    class WindowsCheckbox {
        +marcar(): void
    }
    
    class MacCheckbox {
        +marcar(): void
    }
    
    AbstractFactory <|.. WindowsFactory
    AbstractFactory <|.. MacFactory
    Boton <|.. WindowsBoton
    Boton <|.. MacBoton
    Checkbox <|.. WindowsCheckbox
    Checkbox <|.. MacCheckbox
    WindowsFactory ..> WindowsBoton
    WindowsFactory ..> WindowsCheckbox
    MacFactory ..> MacBoton
    MacFactory ..> MacCheckbox
```

### Diferencia Factory Method vs Abstract Factory

```mermaid
graph LR
    subgraph FM["Factory Method"]
        FM1["Un método"] --> FM2["Un producto"]
    end
    
    subgraph AF["Abstract Factory"]
        AF1["Múltiples métodos"] --> AF2["Familia de productos"]
    end
    
    style FM fill:#3b82f6,color:#fff
    style AF fill:#8b5cf6,color:#fff
```

---

## 4.5 Builder — Construir Objetos Complejos Paso a Paso

### Intención
Separar la construcción de un objeto complejo de su representación, permitiendo construirlo **paso a paso** con diferentes configuraciones.

### Diagrama UML

```mermaid
classDiagram
    class Director {
        -builder: Builder
        +construir(): void
    }
    
    class Builder {
        <<interface>>
        +reset(): Builder
        +setPaso1(): Builder
        +setPaso2(): Builder
        +setPaso3(): Builder
        +build(): Producto
    }
    
    class ConcreteBuilder {
        -producto: Producto
        +reset(): Builder
        +setPaso1(): Builder
        +setPaso2(): Builder
        +setPaso3(): Builder
        +build(): Producto
    }
    
    class Producto {
        -campo1: String
        -campo2: int
        -campo3: boolean
    }
    
    Director o-- Builder
    Builder <|.. ConcreteBuilder
    ConcreteBuilder ..> Producto : construye
```

### Flujo con Fluent API (Method Chaining)

```mermaid
sequenceDiagram
    participant Client as Cliente
    participant Builder as PizzaBuilder
    participant Pizza as Pizza
    
    Client->>Builder: new PizzaBuilder()
    Client->>Builder: .tamano("Grande")
    Builder-->>Client: return this
    Client->>Builder: .masa("Fina")
    Builder-->>Client: return this
    Client->>Builder: .ingrediente("Jamon")
    Builder-->>Client: return this
    Client->>Builder: .ingrediente("Queso")
    Builder-->>Client: return this
    Client->>Builder: .build()
    Builder->>Pizza: new Pizza(config)
    Builder-->>Client: return Pizza
```

### Cuándo usar
- Objetos con muchos parámetros opcionales (evitar constructores telescópicos).
- Diferentes representaciones del mismo tipo de objeto.
- Construcción paso a paso con validación.

---

## 4.6 Prototype — Clonar Objetos Existentes

### Intención
Crear nuevos objetos **clonando** un prototipo existente, evitando el coste de creación desde cero.

### Diagrama UML

```mermaid
classDiagram
    class Prototype {
        <<interface>>
        +clonar(): Prototype
    }
    
    class ConcretePrototypeA {
        -campo1: String
        -campo2: int
        +clonar(): Prototype
    }
    
    class ConcretePrototypeB {
        -campo1: String
        -listaObjetos: List
        +clonar(): Prototype
    }
    
    Prototype <|.. ConcretePrototypeA
    Prototype <|.. ConcretePrototypeB
```

### Shallow Copy vs Deep Copy

```mermaid
graph TD
    subgraph SHALLOW["Shallow Copy - Copia Superficial"]
        OS["Original"] --> D1["datos primitivos COPIADOS"]
        OS --> R1["referencias COMPARTIDAS"]
        CS["Copia"] --> D2["datos primitivos COPIADOS"]
        CS --> R1
    end
    
    subgraph DEEP["Deep Copy - Copia Profunda"]
        OD["Original"] --> D3["datos COPIADOS"]
        OD --> R2["objetos internos ORIGINALES"]
        CD["Copia"] --> D4["datos COPIADOS"]
        CD --> R3["objetos internos CLONADOS"]
    end
    
    style SHALLOW fill:#f97316,color:#fff
    style DEEP fill:#22c55e,color:#fff
```

---

## 4.7 Mapa de Ejercicios del Módulo 4

| Ejercicio | Patrón | Concepto | Dificultad |
|-----------|--------|----------|------------|
| 46 | Singleton | Variantes Eager, Lazy, Double-Check | ⭐⭐ |
| 47 | Singleton | Singleton de Configuración con Properties | ⭐⭐⭐ |
| 48 | Singleton | Singleton Registry y problemas de testing | ⭐⭐⭐ |
| 49 | Factory Method | Fábrica de documentos (PDF, Word, Excel) | ⭐⭐⭐ |
| 50 | Factory Method | Fábrica de notificaciones con registro | ⭐⭐⭐ |
| 51 | Factory Method | Fábrica parametrizada con enums | ⭐⭐⭐ |
| 52 | Abstract Factory | Familias de componentes UI | ⭐⭐⭐⭐ |
| 53 | Abstract Factory | Temas Dark/Light para aplicación | ⭐⭐⭐⭐ |
| 54 | Abstract Factory | Sistema cross-platform (DB + Cache) | ⭐⭐⭐⭐ |
| 55 | Builder | Builder básico para objeto complejo | ⭐⭐⭐ |
| 56 | Builder | Builder con Director | ⭐⭐⭐ |
| 57 | Builder | Fluent API (Method Chaining) | ⭐⭐⭐ |
| 58 | Prototype | Shallow Copy manual | ⭐⭐ |
| 59 | Prototype | Deep Copy recursiva | ⭐⭐⭐ |
| 60 | Prototype | Prototype Registry (catálogo) | ⭐⭐⭐⭐ |

---

> **🔗 Código fuente**: `src/main/java/modulo4_patrones_creacionales/`  
> ¡Lee esta teoría antes de tocar una sola línea de código!
