# 📘 Resumen Ultrarrápido — DOM vs SAX

> **Tiempo de lectura:** 3 minutos  
> **Objetivo:** saber cuándo usar cada uno en el examen

---

## Dos Formas de Procesar XML desde Código

```mermaid
flowchart LR
    XML["📄 XML"]
    
    subgraph "DOM"
        D1["Carga TODO en RAM"]
        D2["Árbol de objetos"]
        D3["Acceso aleatorio"]
        D4["Puede modificar"]
    end
    
    subgraph "SAX"
        S1["Lee secuencialmente"]
        S2["Eventos en streaming"]
        S3["Solo hacia adelante"]
        S4["Solo lectura"]
    end
    
    XML --> D1
    XML --> S1

    style D1 fill:#1565c0,color:#fff
    style D2 fill:#1565c0,color:#fff
    style D3 fill:#1565c0,color:#fff
    style D4 fill:#1565c0,color:#fff
    style S1 fill:#e65100,color:#fff
    style S2 fill:#e65100,color:#fff
    style S3 fill:#e65100,color:#fff
    style S4 fill:#e65100,color:#fff
```

---

## Tabla Comparativa (la que cae en examen)

| Criterio | DOM | SAX |
|----------|-----|-----|
| **Modelo** | Árbol completo en memoria | Eventos en streaming |
| **Memoria** | Alta (carga todo) | Muy baja |
| **Velocidad** | Más lenta al cargar | Muy rápida |
| **Acceso** | Aleatorio (cualquier nodo) | Secuencial (solo adelante) |
| **Modificación** | ✅ Sí, fácil | ❌ No (solo lectura) |
| **Facilidad** | Alta (API intuitiva) | Más compleja |
| **Tamaño recomendado** | Pequeño/mediano (<100 MB) | Cualquier tamaño |
| **Relectura** | Sí (el árbol persiste) | No (un único paso) |
| **Caso típico** | Edición, transformación, XSLT | Indexación, extracción masiva |

---

## Regla de Oro para el Examen

```mermaid
flowchart TD
    Q1{"¿Necesitas MODIFICAR el XML?"}
    Q1 -->|Sí| DOM["Usa DOM"]
    Q1 -->|No| Q2{"¿El fichero es MUY grande?"}
    Q2 -->|Sí| SAX["Usa SAX"]
    Q2 -->|No| Q3{"¿Necesitas navegar libremente?"}
    Q3 -->|Sí| DOM2["Usa DOM"]
    Q3 -->|No| SAX2["Usa SAX"]

    style DOM fill:#1565c0,color:#fff
    style DOM2 fill:#1565c0,color:#fff
    style SAX fill:#e65100,color:#fff
    style SAX2 fill:#e65100,color:#fff
```

**Analogías:**
- **DOM** = cargar un libro entero en RAM y saltar a cualquier página.
- **SAX** = leer el libro página a página, reaccionando sobre la marcha.

---

*Resumen basado en Bloque 14 de la guía teórica · Lenguaje de Marcas T6*
