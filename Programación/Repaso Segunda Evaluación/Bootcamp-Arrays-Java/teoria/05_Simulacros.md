# Bloque V — Simulacros de Examen

> Referencia para ejercicios `Ej25` a `Ej30` en `src/main/java/bloque5/`

## 1. Estrategia ante un examen de arrays + clases + DAO

```mermaid
flowchart TD
    A["Leer enunciado COMPLETO"] --> B["Identificar clases necesarias"]
    B --> C["Identificar atributos y tipos"]
    C --> D["Implementar constructores con validaciones"]
    D --> E["Implementar métodos de negocio"]
    E --> F["Implementar DAO si lo pide"]
    F --> G["Implementar menú si lo pide"]
    G --> H["Probar con main"]
```

**Regla de oro:** No empieces a codificar sin haber leido TODO el enunciado. Marca los requisitos.

## 2. Errores frecuentes en examenes

```mermaid
flowchart LR
    subgraph "TOP 5 ERRORES"
        E1["Arrays.fill con objetos<br>→ misma referencia"]
        E2["No validar rango<br>→ ArrayIndexOutOfBounds"]
        E3["(Integer) null a int<br>→ NullPointerException"]
        E4["Concatenar en bucle con +<br>→ Lento, usar StringBuilder"]
        E5["No hacer copia profunda<br>→ Referencias compartidas"]
    end
```

## 3. Checklist pre-entrega

- [ ] Todas las clases compilan sin errores
- [ ] Constructor valida TODOS los parametros
- [ ] Métodos con array validan rango SIEMPRE
- [ ] Copia profunda donde se necesita (constructor, getter)
- [ ] StringBuilder en vez de concatenacion con +
- [ ] DAO devuelve copias de la lista, no la original
- [ ] main funciona y demuestra las funcionalidades

## 4. Patrones que SIEMPRE caen

```mermaid
graph TD
    P1["Clase con array bidi"] --> P1a["Constructor + validacion"]
    P1 --> P1b["Reservar/Liberar"]
    P1 --> P1c["Mostrar con StringBuilder"]
    P2["DAO con ArrayList"] --> P2a["cargarDatos()"]
    P2 --> P2b["buscarPor(criterio)"]
    P2 --> P2c["agregar/eliminar"]
    P3["Menu con Scanner"] --> P3a["do-while + switch"]
    P3 --> P3b["Lectura robusta"]
    P3 --> P3c["Delegar en metodos"]
```

## 5. Estructura de un ejercicio tipo examen completo

```mermaid
classDiagram
    class EntidadBase {
        -atributos
        +constructor(validaciones)
        +metodoNegocio1()
        +metodoNegocio2()
        +mostrar() String
    }
    class DAO {
        -ArrayList lista
        +cargarDatos()
        +buscar()
        +agregar()
        +eliminar()
        +consultaAgregada()
    }
    class App {
        +main()
        -menu()
        -opcion1()
        -opcion2()
    }
    App --> DAO
    DAO --> EntidadBase
```

## 6. Tips de rendimiento en el examen

- **Empieza por la clase base** — es lo que mas puntua y lo mas facil
- **Luego el DAO** — es mecanico si ya tienes la clase
- **El menu al final** — es lo que menos puntua y depende de todo lo demas
- **Si te atascas, salta** — un metodo sin implementar no rompe el resto
- **Comenta tu intencion** — si no te da tiempo, al menos demuestra que sabias que hacer
