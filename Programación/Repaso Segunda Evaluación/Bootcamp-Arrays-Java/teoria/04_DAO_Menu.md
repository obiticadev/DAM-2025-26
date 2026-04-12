# Bloque IV — DAO y Menús

> Referencia para ejercicios `Ej19` a `Ej24` en `src/main/java/bloque4/`

## 1. Patrón DAO (Data Access Object)

El DAO centraliza el acceso a los datos. El resto del programa NUNCA manipula la lista directamente.

```mermaid
classDiagram
    class App {
        +main()
        -mostrarMenu()
    }
    class DAO {
        -ArrayList~Entidad~ lista
        +cargarDatos() void
        +buscarPor(criterio) Entidad
        +agregar(Entidad) void
        +eliminar(criterio) boolean
        +listar() ArrayList
    }
    class Entidad {
        -atributos
        +metodos()
    }
    App --> DAO : usa
    DAO --> Entidad : gestiona
```

**Reglas del DAO:**
- `ArrayList` solo dentro del DAO
- Métodos de búsqueda, inserción, eliminación y listado
- Carga datos iniciales en un método `cargarDatos()`
- La lógica de negocio NO va en el DAO

## 2. Menú con Scanner

```mermaid
flowchart TD
    A["Mostrar opciones"] --> B["Leer opción"]
    B --> C{"switch/if"}
    C --> D1["Opción 1: acción"]
    C --> D2["Opción 2: acción"]
    C --> D3["Opción 0: salir"]
    D1 --> A
    D2 --> A
    D3 --> E["Fin"]
    C --> F["Opción no válida: mensaje error"]
    F --> A
```

**Patrón estándar:**
```java
int opcion;
do {
    mostrarMenu();
    opcion = pedirEntero(sc);
    switch (opcion) {
        case 1: metodo1(); break;
        case 2: metodo2(); break;
        case 0: System.out.println("Saliendo..."); break;
        default: System.out.println("Opcion no valida");
    }
} while (opcion != 0);
```

## 3. Lectura robusta de entrada

```mermaid
flowchart TD
    A["Mostrar prompt"] --> B["sc.nextLine()"]
    B --> C{"Es numero?"}
    C -- sí --> D["Convertir y devolver"]
    C -- no --> E["Mensaje error"]
    E --> A
```

**Clave:** Usar `sc.nextLine()` + `Integer.parseInt()` con try-catch para evitar `InputMismatchException`.

## 4. Integración Clase + DAO + Menú

```mermaid
sequenceDiagram
    participant U as Usuario
    participant M as Menu (App)
    participant D as DAO
    participant E as Entidad

    U->>M: Elige opcion
    M->>D: buscarPor(criterio)
    D-->>M: Entidad encontrada
    M->>E: entidad.operacion()
    E-->>M: resultado
    M-->>U: Muestra resultado
```

## 5. Estructura de archivos típica

```
bloque4/
├── Ej19_DAO.java          (DAO simple con ArrayList)
├── Ej20_Menu.java          (Menú con Scanner)
├── Ej21_Entrada.java       (Lectura robusta)
├── Ej22_DAOAvanzado.java   (DAO con búsquedas complejas)
├── Ej23_Integracion.java   (Clase + DAO + Menú)
└── Ej24_Completo.java      (Ejercicio completo tipo examen)
```
