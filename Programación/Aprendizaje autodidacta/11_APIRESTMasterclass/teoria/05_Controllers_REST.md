# Bloque V · Controllers REST básicos

> Aquí Spring deja de ser teoría. Un `@RestController` mapea peticiones HTTP a
> métodos Java. Es el corazón de la API.

---

## 5.1 El flujo de una petición

```mermaid
sequenceDiagram
    participant C as Cliente
    participant DS as DispatcherServlet
    participant Ctrl as @RestController
    C->>DS: GET /api/saludo?nombre=Ana
    DS->>Ctrl: invoca saludo("Ana")
    Ctrl-->>DS: "Hola Ana"
    DS-->>C: 200 OK + body
```

---

## 5.2 Anotaciones de mapeo

| Anotación | HTTP |
|---|---|
| `@GetMapping` | GET |
| `@PostMapping` | POST |
| `@PutMapping` | PUT |
| `@PatchMapping` | PATCH |
| `@DeleteMapping` | DELETE |
| `@PathVariable` | parte de la ruta `/x/{id}` |
| `@RequestParam` | query `?clave=valor` |
| `@RequestBody` | cuerpo JSON |

---

## 5.3 Códigos correctos por verbo

```mermaid
flowchart LR
    G[GET] --> 200
    P[POST] --> 201[201 Created + Location]
    PU[PUT] --> 200o204
    D[DELETE] --> 204[204 No Content]
    NF[recurso ausente] --> 404
```

`ResponseEntity` da control total sobre status, headers y body.

---

### Qué practicarás

Controllers con todos los verbos, path/query params, `@RequestBody`,
`ResponseEntity`, CRUD en memoria y negociación. Los tests usan `MockMvc`
en modo standalone (sin levantar servidor).
