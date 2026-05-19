# Bloque XV · Consultas avanzadas

> Una API que devuelve 1M de filas es una API rota. Paginación, filtrado,
> ordenación y proyección son obligatorios en producción.

---

## 15.1 Paginación

```mermaid
flowchart LR
    Q[SELECT ... ] --> O["OFFSET page*size"]
    O --> L["LIMIT size"]
    L --> R[página de resultados + total]
```

`Page` lleva contenido + metadatos (total, nº páginas). `Slice` solo sabe si
hay siguiente (más barato, sin COUNT).

## 15.2 Ordenación y filtrado dinámico

```mermaid
flowchart TD
    P[query params] --> S{¿sort?}
    S -->|sí| O[ORDER BY campo dir]
    P --> F{¿filtros?}
    F -->|sí| W[WHERE dinámico]
```

## 15.3 Specifications / Criteria

Construir el WHERE programáticamente y de forma tipada (CriteriaBuilder), en vez
de concatenar JPQL.

## 15.4 Keyset pagination

```mermaid
flowchart LR
    A["WHERE id > ultimoId"] --> B["ORDER BY id LIMIT n"]
    B --> C[estable y rápida con datasets enormes]
```

---

### Qué practicarás

Paginación, ordenación multinivel, Slice vs Page, filtrado dinámico,
Specifications, Criteria API, Query by Example, proyecciones por interfaz,
agregaciones GROUP BY y keyset pagination.
