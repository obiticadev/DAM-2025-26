# Bloque VIII · Bean Validation

> Validar en el borde de la API evita basura en la BD. Jakarta Validation declara
> las reglas con anotaciones; Spring las aplica con `@Valid`.

---

## 8.1 Flujo de validación

```mermaid
sequenceDiagram
    participant C as Cliente
    participant V as Validador
    participant Ctrl as Controller
    C->>Ctrl: POST /users { email: "malo" }
    Ctrl->>V: @Valid sobre el DTO
    V-->>Ctrl: ConstraintViolation(email)
    Ctrl-->>C: 400 + errores
```

## 8.2 Constraints habituales

| Anotación | Regla |
|---|---|
| `@NotNull` / `@NotBlank` | obligatorio |
| `@Size(min,max)` | longitud |
| `@Min` / `@Max` | rango numérico |
| `@Email` | formato email |
| `@Pattern(regexp)` | regex |
| `@Valid` (anidado) | valida el objeto hijo |

## 8.3 Grupos

```mermaid
flowchart LR
    Crear -->|grupo Create| R1[id null, nombre obligatorio]
    Actualizar -->|grupo Update| R2[id obligatorio]
```

---

### Qué practicarás

Constraints básicas y numéricas, validación anidada, grupos create/update,
constraint personalizada, validación entre campos, de parámetros y programática
con `Validator`.
