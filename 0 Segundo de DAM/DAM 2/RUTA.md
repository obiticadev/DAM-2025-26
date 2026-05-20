Te la doy organizada por bloques en el orden lógico de aprendizaje, porque hay dependencias (no puedes hacer Spring sin entender HTTP, ni Docker sin saber qué es un proceso Linux). Cada bloque construye sobre el anterior. Marco con ⭐ lo crítico-crítico, y con ◯ lo que es "bonus si te da tiempo".

## Bloque 0 — Refuerzo de Java moderno ⭐
- Records, `var`, switch expressions, text blocks (Java 17/21)
- Streams API y expresiones lambda
- `Optional` y por qué existe
- Excepciones checked vs unchecked
- Genéricos básicos (`List<T>`, `Map<K,V>`)
- Diferencia entre `ArrayList`, `LinkedList`, `HashMap`, `HashSet`
- Inmutabilidad y `final`

## Bloque 1 — HTTP y la web ⭐
- Modelo cliente-servidor y stateless
- Verbos HTTP: GET, POST, PUT, PATCH, DELETE
- Códigos de estado: 2xx (éxito), 3xx (redirect), 4xx (cliente), 5xx (servidor) — los 10-15 más comunes
- Headers, request body, response body
- Content-Type y `application/json`
- HTTPS y certificados (concepto)
- CORS (qué es, por qué te va a dar dolores de cabeza)

## Bloque 2 — APIs REST ⭐
- Qué significa REST (recursos, no acciones)
- Diseño de endpoints (`/api/v1/users/{id}/orders`)
- Idempotencia (qué verbos lo son y por qué)
- JSON: estructura, parseo, serialización
- DTOs vs entidades (separar lo que expones de lo que persistes)
- Versionado de API
- Herramientas para probar APIs: Postman, Bruno (recomiendo este, es open source) o Insomnia

## Bloque 3 — Maven y estructura de proyecto Java ⭐
- `pom.xml`: groupId, artifactId, version, dependencies
- Ciclo de vida: compile, test, package, install
- Repositorio local `~/.m2`
- Spring Initializr (start.spring.io)
- Estructura estándar Maven (`src/main/java`, `src/test/java`, `src/main/resources`)

## Bloque 4 — Spring Boot fundamentos ⭐
- Inversión de Control (IoC) e Inyección de Dependencias (DI)
- Beans y el ApplicationContext
- Estereotipos: `@Component`, `@Service`, `@Repository`, `@Controller`, `@RestController`
- Inyección por constructor (no uses `@Autowired` en campo)
- `@Configuration` + `@Bean`
- `application.properties` vs `application.yml`
- Profiles (`dev`, `prod`, `test`) y `@Profile`
- `@Value` y `@ConfigurationProperties`
- Logging con SLF4J + Logback (niveles INFO, DEBUG, ERROR, WARN)

## Bloque 5 — Spring Web (la capa REST) ⭐
- `@RestController`, `@RequestMapping`
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@PatchMapping`, `@DeleteMapping`
- `@PathVariable`, `@RequestParam`, `@RequestBody`
- `ResponseEntity<T>` y devolver status codes correctos
- Validación con Bean Validation: `@Valid`, `@NotNull`, `@NotBlank`, `@Size`, `@Email`, `@Pattern`
- Manejo global de errores: `@ControllerAdvice` + `@ExceptionHandler`
- Mapeo DTO ↔ Entity (a mano al principio, MapStruct cuando aprietes)

## Bloque 6 — SQL y bases de datos ⭐
- DDL (CREATE, ALTER, DROP) y DML (SELECT, INSERT, UPDATE, DELETE)
- JOINs: INNER, LEFT, RIGHT (entiende bien los dos primeros)
- Claves primarias y foráneas
- Índices: cuándo crearlos
- Transacciones, ACID (concepto)
- GROUP BY, HAVING, agregaciones
- Subqueries
- Postgres instalación local + cliente DBeaver

## Bloque 7 — JPA, Hibernate y Spring Data ⭐
- Qué es un ORM y por qué existe
- `@Entity`, `@Table`, `@Id`, `@GeneratedValue`
- `@Column`, `@Enumerated`, `@Temporal`
- Relaciones: `@OneToMany`, `@ManyToOne`, `@OneToOne`, `@ManyToMany`
- Fetch types: LAZY vs EAGER (y el problema N+1)
- `JpaRepository` y métodos heredados
- Derived query methods (`findByEmail`, `findByNameContainingIgnoreCase`)
- JPQL y `@Query`
- Native queries (cuándo sí)
- `@Transactional` y su propagación
- Flyway para migraciones de esquema ◯

## Bloque 8 — Testing ⭐
- JUnit 5: `@Test`, `@BeforeEach`, `@AfterEach`, assertions (`assertEquals`, `assertThrows`)
- Mockito: `@Mock`, `@InjectMocks`, `when().thenReturn()`, `verify()`
- AAA pattern (Arrange, Act, Assert)
- `@SpringBootTest` (tests de integración)
- `@WebMvcTest` + MockMvc (tests de controlador)
- `@DataJpaTest` (tests de repositorio)
- Testcontainers ◯ (Postgres real en Docker para tests, diferencial brutal)

## Bloque 9 — Spring Security ⭐
- Autenticación vs autorización
- Filter chain de Spring Security (concepto general)
- BCrypt para hash de contraseñas (nunca guardes passwords en plano)
- `SecurityFilterChain` (estilo moderno, ignora tutoriales viejos con `WebSecurityConfigurerAdapter`)
- HTTP Basic (entender, no para producción)
- JWT: estructura (header.payload.signature), librería jjwt o nimbus
- Login endpoint → genera token → cliente lo envía en header `Authorization: Bearer <token>`
- `UserDetailsService` custom
- Roles y `@PreAuthorize("hasRole('ADMIN')")`
- Cuándo desactivar CSRF (APIs stateless con JWT, sí)

## Bloque 10 — Docker y contenedores ⭐
- Concepto: imagen vs contenedor vs volumen vs red
- Comandos: `docker build`, `run`, `ps`, `exec`, `logs`, `stop`, `rm`, `images`, `pull`, `push`
- Dockerfile: `FROM`, `WORKDIR`, `COPY`, `RUN`, `CMD`, `ENTRYPOINT`, `EXPOSE`, `ENV`
- Multi-stage builds (build con Maven, runtime con JRE — esto es CLAVE para Java)
- Imágenes base ligeras: `eclipse-temurin:21-jre-alpine`, distroless
- `.dockerignore`
- Variables de entorno y `--env-file`
- Volúmenes: named volumes vs bind mounts
- docker-compose: `docker-compose.yml` con varios servicios (app + Postgres + pgAdmin)
- Redes en Docker (bridge por defecto, custom)
- Docker Hub: subir tu imagen

## Bloque 11 — Git y GitHub serios ⭐
- Branching: `main`, `feature/*`, `fix/*`
- `rebase` vs `merge` (cuándo cada uno)
- Resolución de conflictos
- Pull requests y code review
- Conventional commits (`feat:`, `fix:`, `chore:`, `docs:`, `refactor:`)
- `.gitignore` para proyectos Java/Maven (`target/`, `.idea/`, `*.iml`)
- Tags y releases
- README.md decente: instalación, uso, stack, badges
- Recuperación de cagadas: `git reflog`, `git reset --hard`, `git revert`

## Bloque 12 — GitHub Actions (CI básico) ⭐
- Estructura de un workflow YAML en `.github/workflows/`
- Triggers: `on: push`, `on: pull_request`
- Jobs y steps
- Acciones esenciales: `actions/checkout`, `actions/setup-java`
- Cachear dependencias de Maven (`actions/cache`)
- Secrets y variables de entorno del repo
- Status checks que bloquean el merge
- ◯ Workflow que construye imagen Docker y la sube a Docker Hub

## Bloque 13 — Linux y línea de comandos ⭐
- Filesystem: `/`, `/etc`, `/var`, `/home`, `/opt`, `/tmp`
- Permisos: `chmod`, `chown`, lectura del `ls -la`
- Procesos: `ps`, `top` o `htop`, `kill`, señales (SIGTERM, SIGKILL)
- Redes: `curl`, `wget`, `ping`, `ss` (sustituto moderno de `netstat`), `dig`
- Texto: `grep`, `sed`, `awk` (básico), `cat`, `less`, `head`, `tail -f`
- `find` (búsqueda por nombre, fecha, tipo)
- Pipes y redirecciones: `|`, `>`, `>>`, `<`, `2>&1`
- `systemctl`: start, stop, status, enable, journalctl
- Bash scripting básico: variables, `if`, `for`, `while`, funciones, `$?`, `set -e`
- SSH: claves con `ssh-keygen`, `~/.ssh/config`, `scp`
- Editor: vim mínimo (`i`, `Esc`, `:wq`, `:q!`) o nano

## Bloque 14 — AWS aplicado (después de Cloud Practitioner) ⭐
- IAM: users, roles, policies, MFA, principio de mínimo privilegio
- EC2: tipos de instancia, AMIs, security groups, key pairs, Elastic IPs, user data
- VPC: subnets públicas/privadas, route tables, Internet Gateway, NAT Gateway
- S3: buckets, objects, versioning, lifecycle policies, presigned URLs
- RDS: motores, snapshots, multi-AZ (concepto), parameter groups
- ECR: subir imágenes Docker
- ECS Fargate: clusters, task definitions, services (mejor que EC2 para tu caso)
- CloudWatch: logs (CloudWatch Logs), métricas, alarmas
- ALB: target groups, listeners, health checks
- Route 53 (concepto, no profundices) ◯
- AWS CLI: `aws configure`, `aws s3 cp`, `aws ecs ...`

## Bloque 15 — Conceptos transversales que se notan cuando faltan ⭐
- Arquitectura por capas: controller → service → repository
- SOLID (sobre todo S, O y D al principio)
- Clean code: nombres descriptivos, funciones cortas, evitar comentarios obvios
- Manejo de errores consistente (excepciones custom, response uniforme)
- Logging útil (nunca `System.out.println` en producción)
- Variables de entorno para secretos (no commits con passwords)
- 12-Factor App (léete los 12 puntos, son 20 minutos)

## Bloque 16 — Frontend mínimo (solo si te sobra tiempo) ◯
Esto puedes dejarlo para 2º año tranquilamente. Si te pica:
- HTML5 + CSS3 lo básico (que ya habrás visto)
- JavaScript moderno (ES6+: `let/const`, arrow functions, `async/await`, destructuring)
- Fetch API (consumir tu propia API REST)
- Un proyecto frontend mínimo en HTML+JS vanilla que pegue contra tu backend
- React + TypeScript: déjalo para 2º o un proyecto otoñal

---

## Cómo priorizar si el verano se queda corto

Si tuviera que recortar (asumiendo que aprobar Cloud Practitioner ya está en marcha en paralelo), el orden de sacrificio sería el inverso: corta primero el Bloque 16, luego el 14 (puedes dejarlo para arrancar la SAA después), luego Testcontainers, luego Flyway. **Lo que NO puedes saltarte** para tener algo decente en el CV de cara a prácticas: bloques 0-12. Eso es el mínimo viable de "junior backend que sabe lo que hace".

Cuando lo tengas todo y mires hacia atrás, te darás cuenta de que el bloque 7 (JPA) y el 9 (Security) son los que más te van a costar. Es normal. No te frustres si JPA te lleva 3 semanas; le ha pasado a todo el mundo.

¿Quieres que te monte un calendario semana a semana sobre estos bloques cuadrando con la fecha del examen AWS, o prefieres tirar libre y volver a por el calendario más adelante?