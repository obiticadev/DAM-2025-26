# Bloque XX · Observabilidad y documentacion

> Una API sin documentacion es un enigma; una API sin observabilidad es una
> caja negra. Documenta lo que ofreces y observa lo que ocurre.

---

## 20.1 OpenAPI / Swagger con springdoc

springdoc-openapi escanea los controladores y genera un documento OpenAPI 3
servido en `/v3/api-docs` y una UI en `/swagger-ui.html`.

```mermaid
flowchart LR
    C[Controllers] --> S[springdoc scan]
    S --> D["/v3/api-docs (JSON)"]
    D --> U[Swagger UI]
```

El documento es un arbol `info` + `paths -> path -> metodo -> operacion`.

## 20.2 Anotaciones de documentacion

`@Operation(summary=...)` y `@Schema(description=...)` enriquecen cada endpoint
y modelo. Reglas de precedencia: summary > description > valor por defecto.

## 20.3 Actuator: health agregado

```mermaid
flowchart TD
    H["/actuator/health"] --> DB[db]
    H --> DK[disk]
    H --> P[ping]
    DB --> A{¿todos UP?}
    DK --> A
    P --> A
    A -->|si| UP[status UP]
    A -->|no| DOWN[status DOWN]
```

El estado global es estrictamente AND: un solo componente DOWN tumba el todo.

## 20.4 Health y metricas propias

`HealthIndicator` custom + Micrometer. Tasa de error sobre contadores
acumulados decide el estado de salud frente a un umbral.

## 20.5 Logging estructurado con MDC

```mermaid
flowchart LR
    R[Request] --> M["MDC.put(traceId,...)"]
    M --> L[log line]
    L --> J["JSON: level + message + campos MDC"]
```

JSON es parseable por ELK/Loki; el MDC adjunta contexto a cada linea.

## 20.6 Trazas y correlacion

```mermaid
sequenceDiagram
    Cliente->>SvcA: (sin traceId)
    Note over SvcA: genera traceId, span :0
    SvcA->>SvcB: traceId en cabecera
    Note over SvcB: reutiliza traceId, span :1
```

El `traceId` se propaga intacto; el `spanId` es unico por salto.

---

### Qué practicarás

Generar un mini documento OpenAPI, resolver anotaciones `@Operation`/`@Schema`,
agregar el health de Actuator, calcular metricas y health propios, formatear
logs estructurados JSON con MDC y propagar traceId/spanId entre saltos.


## Teoría Extendida y Ejemplos de Código

### 1. Swagger / OpenAPI 3 (Documentación viva)
Añade `springdoc-openapi-starter-webmvc-ui`. En vez de hacer PDFs obsoletos, documentas en el código.
```java
@Operation(summary = "Crea un usuario", description = "Genera un usuario y lanza evento")
@ApiResponses({
    @ApiResponse(responseCode = "201", description = "Creado exitosamente"),
    @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
})
@PostMapping
public ResponseEntity<Usuario> crear(...) {}
```

### 2. Actuator (Observabilidad de la app)
Expone métricas críticas para que herramientas como Prometheus/Grafana sepan si la API está viva.
```yaml
management:
  endpoints:
    web:
      exposure:
        include: health, metrics, prometheus, info
  endpoint:
    health:
      show-details: always # Muestra si la DB está caída o sin espacio en disco
```

### 3. Trazabilidad de Logs (MDC)
Si entran 100 peticiones a la vez, ¿cómo sabes qué log pertenece a qué usuario? Con `MDC` en SLF4J inyectas un ID a todo el hilo de ejecución.
En tu logger (Logback) configuras el pattern: `[%X{traceId}] %m%n`.
```java
// Salida del log:
// [ab3d-19f8] Iniciando proceso de compra
// [ab3d-19f8] Lammando a pasarela de pago...
// [99fc-bbaa] Iniciando proceso de compra  <-- ¡Otra petición distinta a la vez!
```
