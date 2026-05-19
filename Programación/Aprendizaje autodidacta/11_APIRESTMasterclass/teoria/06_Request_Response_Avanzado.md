# Bloque VI · Request/Response avanzado

> El día a día de una API real: subir ficheros, CORS, cabeceras de caché,
> versionado, interceptores. Lo que separa un "hola mundo" de un servicio serio.

---

## 6.1 Negociación de contenido

```mermaid
flowchart LR
    R["Accept: application/xml"] --> S{Spring}
    S -->|hay HttpMessageConverter XML| X[respuesta XML]
    S -->|si no| J[respuesta JSON o 406]
```

## 6.2 CORS

El navegador bloquea peticiones cross-origin salvo que el servidor responda
con `Access-Control-Allow-Origin`.

```mermaid
sequenceDiagram
    participant N as Navegador (origin A)
    participant S as API (origin B)
    N->>S: OPTIONS /datos (preflight)
    S-->>N: 200 + Access-Control-Allow-Origin: A
    N->>S: GET /datos
    S-->>N: 200 + datos
```

## 6.3 Subida y descarga

`MultipartFile` para subir; `Resource`/bytes + `Content-Disposition` para bajar.

## 6.4 Versionado

```mermaid
flowchart TD
    V1["/api/v1/users"] --> R[Router]
    V2["/api/v2/users"] --> R
    H["Header: X-API-Version"] --> R
```

## 6.5 Filtros e interceptores

```mermaid
flowchart LR
    Req --> F[Filter] --> I[Interceptor preHandle] --> Ctrl[Controller] --> I2[postHandle] --> Resp
```

---

### Qué practicarás

Negociación JSON/XML, CORS, upload/download, lectura de headers, ETag/caché,
estrategia de versionado y un interceptor que mide tiempos.
