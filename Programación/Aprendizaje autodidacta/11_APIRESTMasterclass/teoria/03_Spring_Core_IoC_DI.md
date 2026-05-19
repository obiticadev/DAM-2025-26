# Bloque III · Spring Core: IoC y DI

> Spring no es magia: es un **contenedor** que crea objetos por ti y se los pasa a
> quien los necesita. Entender IoC/DI es entender el 70 % de Spring.

---

## 3.1 Inversión de Control (IoC)

Sin IoC, tú haces `new ServicioPedidos(new RepoPedidos())`. Con IoC, le dices al
contenedor "necesito un ServicioPedidos" y él arma el árbol de dependencias.

```mermaid
flowchart TD
    C[Contenedor Spring] -->|crea| R[RepoPedidos]
    C -->|crea e inyecta R| S[ServicioPedidos]
    C -->|crea e inyecta S| Ctrl[PedidoController]
```

---

## 3.2 Inyección de dependencias por constructor

```java
@Service
class ServicioPedidos {
    private final RepoPedidos repo;
    ServicioPedidos(RepoPedidos repo) { this.repo = repo; } // Spring inyecta
}
```

Por constructor (no por campo) → inmutable, testeable, dependencias explícitas.

---

## 3.3 Ciclo de vida de un bean

```mermaid
stateDiagram-v2
    [*] --> Instanciado
    Instanciado --> Inyectado: dependencias resueltas
    Inyectado --> PostConstruct: @PostConstruct
    PostConstruct --> EnUso
    EnUso --> PreDestroy: cierre del contexto
    PreDestroy --> [*]
```

---

## 3.4 Scopes

| Scope | Instancias |
|---|---|
| `singleton` (def.) | Una por contenedor |
| `prototype` | Una nueva por cada petición de bean |

---

## 3.5 Configuración por Java

`@Configuration` + `@Bean` define beans programáticamente. `@Profile` y
`@Conditional` deciden si un bean existe según el entorno.

```mermaid
flowchart LR
    Cfg["@Configuration"] --> B1["@Bean dataSource()"]
    Cfg --> B2["@Bean clock()"]
    P["@Profile('dev')"] -.activa.-> B1
```

---

### Qué practicarás

Un mini-contenedor IoC propio, inyección por constructor, `@Qualifier`/`@Primary`,
scopes, ciclo de vida, `@Configuration`/`@Bean`, `@Conditional`, eventos y un
aspecto AOP. Los tests usan `AnnotationConfigApplicationContext` cuando hace falta
Spring real.


## Teoría Extendida y Ejemplos de Código

### 1. Inversión de Control (IoC) y Dependency Injection (DI)
El framework es el dueño del ciclo de vida de los objetos (Beans), no tú usando `new`.

```java
@Service // Declara el Bean
public class FacturacionService {
    
    private final NotificacionService notificador; // Inmutable
    
    // Inyección por Constructor (Recomendada). Evita @Autowired en propiedades.
    public FacturacionService(NotificacionService notificador) {
        this.notificador = notificador;
    }
}
```

### 2. Scopes y Perfiles
- **Singleton**: Una única instancia compartida (Por defecto).
- **Prototype**: Una nueva instancia cada vez que se inyecta.

```java
@Configuration
public class AppConfig {
    
    @Bean
    @Scope("prototype")
    @Profile("!test") // No se cargará en tests
    public ClienteHttp clienteExterno() {
        return new ClienteHttp();
    }
}
```
