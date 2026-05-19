# Bloque II · JSON y Jackson

> El cuerpo de casi toda API REST viaja en JSON. Spring usa **Jackson** por debajo
> para convertir objetos Java ↔ JSON. Entender esto antes de tocar controllers
> evita el 90 % del "por qué mi endpoint devuelve `{}`".

---

## 2.1 El modelo JSON

JSON solo tiene 6 tipos: objeto `{}`, array `[]`, string, número, booleano, `null`.

```mermaid
flowchart TD
    V[Valor JSON] --> O["objeto { clave: valor }"]
    V --> A["array [ v, v ]"]
    V --> S[string]
    V --> N[número]
    V --> B[booleano]
    V --> Z[null]
```

---

## 2.2 Serialización ↔ deserialización

```mermaid
sequenceDiagram
    participant J as Objeto Java
    participant M as ObjectMapper
    participant T as Texto JSON
    J->>M: writeValueAsString(obj)
    M-->>T: {"id":1,"nombre":"Ana"}
    T->>M: readValue(json, Clase.class)
    M-->>J: instancia poblada
```

- **Serializar**: Java → JSON (`writeValueAsString`).
- **Deserializar**: JSON → Java (`readValue`).

Jackson usa los *getters* para serializar y el constructor/*setters* (o el
constructor de un `record`) para deserializar.

---

## 2.3 Anotaciones clave

| Anotación | Efecto |
|---|---|
| `@JsonProperty("nom")` | Renombra el campo en el JSON |
| `@JsonIgnore` | Excluye el campo (p.ej. password) |
| `@JsonInclude(NON_NULL)` | Omite nulls |
| `@JsonFormat` | Formatea fechas |

---

## 2.4 Árbol JSON (lectura dinámica)

Cuando no conoces la forma del JSON, lo lees como árbol: `JsonNode`.

```mermaid
flowchart LR
    T["readTree(json)"] --> R[JsonNode raíz]
    R --> P1["get(\"datos\")"]
    P1 --> P2["get(0).get(\"id\").asInt()"]
```

---

### Qué practicarás

Modelar JSON, serializar/deserializar con `ObjectMapper`, renombrar e ignorar
campos, manejar objetos anidados y colecciones, escribir un serializer propio y
navegar un árbol `JsonNode`.


## Teoría Extendida y Ejemplos de Código

### 1. Anotaciones clave de Jackson
Para dominar el mapeo (binding) de Java a JSON.
```java
@JsonInclude(JsonInclude.Include.NON_NULL) // Oculta nulos
public class Producto {
    @JsonProperty("id_producto") // Cambia el nombre en el JSON
    private Long id;
    
    @JsonFormat(pattern = "yyyy-MM-dd") // Formatea fechas
    private LocalDate fechaCreacion;
    
    @JsonIgnore // Evita que se serialice la contraseña
    private String password;
}
```

### 2. ObjectMapper: Conversión Manual
Útil cuando lees de APIs de terceros o WebSockets.
```java
ObjectMapper mapper = new ObjectMapper()
    .registerModule(new JavaTimeModule()) // Vital para LocalDate
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

// String a Objeto
Usuario u = mapper.readValue("{\"nombre\":\"Juan\"}", Usuario.class);

// Objeto a String
String json = mapper.writeValueAsString(u);
```

### 3. JsonNode: Árbol Dinámico
Cuando el JSON entrante es muy variable y no quieres crear un DTO enorme.
```java
JsonNode root = mapper.readTree(jsonComplejo);
String ciudad = root.path("direccion").path("ciudad").asText("Desconocida");
```
