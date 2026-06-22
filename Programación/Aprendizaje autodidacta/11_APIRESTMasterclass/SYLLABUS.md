# SYLLABUS · Masterclass API REST con Java + Spring Boot (200 ejercicios)

> Bootcamp autodidacta de maestría absoluta en construcción de APIs REST profesionales.
> Continuación del plan de verano (Bloque 2 + Bloque 4) y preparación directa para
> **Acceso a Datos (AD)**, **PSP** y **Desarrollo de Interfaces** de DAM 2º curso.
> Filosofía: fundamentos pétreos primero, framework después, producción al final.

---

## 1. Tabla de variables del proyecto

| Variable | Valor |
|---|---|
| `{LENGUAJE_TECNOLOGIA}` | **Java 21 + Spring Boot 3.x** |
| `{CONCEPTOS_A_TRATAR}` | HTTP/Web a fondo · Java moderno (records, streams, generics, Optional, sealed, concurrencia) · JSON/Jackson · Spring IoC/DI · Boot config/profiles · Controllers REST · Negociación de contenido, CORS, ficheros · DTOs y mapeo · Bean Validation · Manejo de excepciones (RFC 7807) · Arquitectura por capas, DAO/Repository/Hexagonal · JDBC profundo · Spring Data JPA / Hibernate · Relaciones JPA · Transacciones, fetch, caché, locking, auditoría · Paginación/filtrado/ordenación/specifications/proyecciones · XML y ficheros (AD RA1) · NoSQL / MongoDB (AD RA4) · Spring Security + JWT · Testing (JUnit5, Mockito, MockMvc, @DataJpaTest, @SpringBootTest, Testcontainers) · OpenAPI/Swagger · Actuator/observabilidad · Caché, async, resiliencia, rate limiting · Docker + Compose + PostgreSQL · CI/CD |
| `{CANTIDAD_EJERCICIOS}` | **200** (densidad máxima) |
| `{SISTEMA_GESTOR_PAQUETES}` | **Maven** (`mvn`) |
| `{PUNTO_DE_EJECUCION_IDE}` | Botón *Run* sobre `main` (clase `*Playground`) o `mvn spring-boot:run` |
| `{FRAMEWORK_TESTING}` | **JUnit 5** + **Mockito** + **Spring Boot Test** (`MockMvc`, `@DataJpaTest`, `@WebMvcTest`, `@SpringBootTest`) + **Testcontainers / H2** |

Punto de partida: DAM1 terminado. Java hasta interfaces; nociones básicas de
colecciones, ficheros y JDBC. Spring Boot, JPA, seguridad y testing = **desde cero**.

> **Estándar de práctica:** cada ejercicio contiene **exactamente 10 TODOs**
> granulares y fundamentados (validaciones, casos límite, pasos del algoritmo y
> retorno). La práctica hace al maestro.

---

## 2. Estructura de carpetas

```
11_APIRESTMasterclass/
├── README_GUIA_TERMINAL.md      # cómo levantar y testear todo (Regla 7)
├── SYLLABUS.md                  # este archivo (Regla 13)
├── pom.xml
├── docker-compose.yml
├── teoria/                      # un .md por bloque, con diagramas Mermaid
│   ├── 00_Fundamentos_HTTP_Web.md
│   ├── 01_Java_Moderno_para_APIs.md
│   ├── ... (hasta 24_Boss_Final.md)
└── src/{main,test}/java/com/masterclass/api/
    ├── b00_http/ ... b24_boss/   # un paquete por bloque, test espejo
```

Cada paquete de `src/` referencia su `.md` homónimo en `teoria/`.

---

## 3. Mapa de bloques (rangos)

| Bloque | Tema | Ejercicios | Nº |
|---|---|---|---|
| 0 | Fundamentos HTTP y Web | 001–010 | 10 |
| I | Java moderno para APIs | 011–022 | 12 |
| II | JSON y Jackson (cimiento) | 023–028 | 6 |
| III | Spring Core: IoC / DI | 029–038 | 10 |
| IV | Spring Boot: config y profiles | 039–044 | 6 |
| V | Controllers REST básicos | 045–054 | 10 |
| VI | Request/Response avanzado | 055–062 | 8 |
| VII | DTOs y mapeo | 063–068 | 6 |
| VIII | Bean Validation | 069–076 | 8 |
| IX | Manejo de errores (RFC 7807) | 077–084 | 8 |
| X | Arquitectura y patrones | 085–092 | 8 |
| XI | JDBC profundo (refresco) | 093–102 | 10 |
| XII | Spring Data JPA core | 103–114 | 12 |
| XIII | Relaciones JPA | 115–122 | 8 |
| XIV | JPA avanzado | 123–132 | 10 |
| XV | Consultas avanzadas | 133–142 | 10 |
| XVI | XML y ficheros (AD RA1) | 143–148 | 6 |
| XVII | NoSQL / MongoDB (AD RA4) | 149–154 | 6 |
| XVIII | Seguridad: Security + JWT | 155–164 | 10 |
| XIX | Testing de APIs | 165–176 | 12 |
| XX | Observabilidad y documentación | 177–182 | 6 |
| XXI | Rendimiento y resiliencia | 183–188 | 6 |
| XXII | Docker y despliegue | 189–194 | 6 |
| XXIII | CI/CD y calidad | 195–198 | 4 |
| XXIV | Boss Final (multi-parte) | 199–200 | 2 |
| XXV | Plantillas y PDF (Thymeleaf) | 201–206 | 6 |
| XXVI | I/O de ficheros y NIO.2 (AD RA1) | 207–214 | 8 |
| XXVII | Concurrencia y multihilo (PSP RA2) | 215–226 | 12 |
| XXVIII | Multiproceso e IPC (PSP RA1) | 227–232 | 6 |
| XXIX | Sockets y programación en red (PSP RA3) | 233–240 | 8 |
| XXX | Criptografía y programación segura (PSP RA5) | 241–248 | 8 |
| XXXI | BD objeto-relacionales/OO y procedimientos (AD RA4 + RA2) | 249–254 | 6 |
| XXXII | JavaFX: aplicación, escena y layouts (DI RA1) | 255–262 | 8 |
| XXXIII | Controles, Properties y Binding observable (DI RA1/RA2) | 263–270 | 8 |
| XXXIV | FXML, Scene Builder, MVC/MVVM y eventos (DI RA1) | 271–278 | 8 |
| XXXV | Datos observables, tablas y asincronía (DI RA2/RA4) | 279–286 | 8 |
| XXXVI | CSS, usabilidad, accesibilidad e i18n (DI RA3) | 287–292 | 6 |
| XXXVII | Componentes personalizados, Canvas y gráficos (DI RA2) | 293–298 | 6 |
| XXXVIII | Informes, PDF e impresión (DI RA4) | 299–304 | 6 |
| XXXIX | Documentación, ayuda y distribución (DI RA5/RA6) | 305–310 | 6 |
| | **TOTAL** | | **310** |

> **Bloques de ampliación 2º DAM (ver `ROADMAP_BUILD_MASTERCLASS.md`):** cierran los huecos del BOE
> en Acceso a Datos y PSP que la ruta REST no toca. Construidos: `b26_io` (207–214),
> `b27_concur` (215–226), `b28_proc` (227–232), `b29_sockets` (233–240), `b30_crypto`
> (241–248) y `b31_oodb` (249–254, cierra AD·RA4 + RA2.k). En marcha el módulo de
> **Desarrollo de Interfaces (DI)** con JavaFX: `b32_fxbase` (255–262, DI·RA1),
> `b33_fxcontrols` (263–270, DI·RA1/RA2: controles + Properties + binding observable) y
> `b34_fxfxml` (271–278, DI·RA1: FXML/Scene Builder, MVC/MVVM y eventos) y
> `b35_fxdata` (279–286, DI·RA2/RA4: colecciones observables, `TableView`/`ListView`,
> `Task`/`Service` y consumo de la API REST de b05 sin congelar la UI) y
> `b36_fxstyle` (287–292, DI·RA3: CSS y selectores, pseudo-clases y temas claro/oscuro,
> accesibilidad WCAG e internacionalización con `ResourceBundle`/`Locale`) y
> `b37_fxcustom` (293–298, DI·RA2: componentes propios por composición y por `Control`+`Skin`,
> dibujo en `Canvas`/`GraphicsContext`, *hit testing* del ratón, gráficos `LineChart`/`BarChart`/
> `PieChart` y formas/colores/efectos) y
> `b38_fxreports` (299–304, DI·RA4: informes con JasperReports —modelo de datos, compilar/rellenar/
> exportar a PDF, parámetros y agrupaciones con subtotales, subinformes y gráficos embebidos—,
> impresión de un nodo con `PrinterJob` y exportación a PDF/XLSX/CSV con detección por *magic number*) y
> `b39_fxdeploy` (305–310, DI·RA5/RA6: documentación —Javadoc y metadatos del `MANIFEST.MF`—, ayuda
> integrada —"Acerca de", manual, `Hyperlink`—, persistencia de ajustes con `Preferences`,
> modularización + `jlink` y empaquetado nativo con `jpackage`, y versionado semántico con
> comprobación de actualizaciones). **Con b39 el módulo DI (0487) queda cubierto al completo.**
> Nota: `b26_io` lleva 12 retos extra por ejercicio (el resto, 10); `b36_fxstyle`, `b37_fxcustom`,
> `b38_fxreports` y `b39_fxdeploy` llevan 6 ejercicios cada uno.

---

## 4. Tabla detallada de ejercicios

### Bloque 0 · Fundamentos HTTP y Web (001–010)
| # | Archivo | Concepto clave |
|---|---|---|
| 001 | `b00_http/Ej001_HttpRequestParser.java` | Anatomía de la request HTTP |
| 002 | `b00_http/Ej002_HttpResponseBuilder.java` | Línea de estado, headers, body |
| 003 | `b00_http/Ej003_StatusCodeResolver.java` | Familias 1xx–5xx y semántica |
| 004 | `b00_http/Ej004_HttpMethodsSemantics.java` | Idempotencia y seguridad de verbos |
| 005 | `b00_http/Ej005_HeadersToolkit.java` | Headers comunes y custom |
| 006 | `b00_http/Ej006_ContentTypeNegotiation.java` | MIME types, `Accept` |
| 007 | `b00_http/Ej007_UrlAndQueryParser.java` | URI, path, query, fragment |
| 008 | `b00_http/Ej008_RestResourceModeler.java` | Recursos, sustantivos, jerarquía |
| 009 | `b00_http/Ej009_RestMaturityRichardson.java` | Modelo de madurez de Richardson |
| 010 | `b00_http/Ej010_StatelessAndCache.java` | Statelessness, caché, ETag |

### Bloque I · Java moderno para APIs (011–022)
| # | Archivo | Concepto clave |
|---|---|---|
| 011 | `b01_java/Ej011_Records.java` | `record` inmutable como DTO |
| 012 | `b01_java/Ej012_OptionalSafeAccess.java` | `Optional` sin `NullPointer` |
| 013 | `b01_java/Ej013_StreamsBasics.java` | map/filter/collect |
| 014 | `b01_java/Ej014_StreamsAdvanced.java` | reduce, flatMap, `Collectors` |
| 015 | `b01_java/Ej015_GenericsRepository.java` | Genéricos y `T` acotados |
| 016 | `b01_java/Ej016_WildcardsVariance.java` | `? extends` / `? super` |
| 017 | `b01_java/Ej017_FunctionalInterfaces.java` | `Function`/`Predicate`/`Supplier` |
| 018 | `b01_java/Ej018_SealedPatternMatching.java` | `sealed` + `switch` patrones |
| 019 | `b01_java/Ej019_ExceptionsAndTryWith.java` | Excepciones y try-with-resources |
| 020 | `b01_java/Ej020_DateTimeApi.java` | `java.time` para APIs |
| 021 | `b01_java/Ej021_ConcurrencyBasics.java` | `CompletableFuture`, threads |
| 022 | `b01_java/Ej022_EqualsHashCodeContracts.java` | Contratos `equals`/`hashCode` |

### Bloque II · JSON y Jackson (023–028)
| # | Archivo | Concepto clave |
|---|---|---|
| 023 | `b02_json/Ej023_JsonModel.java` | Sintaxis y tipos JSON |
| 024 | `b02_json/Ej024_ObjectMapperBasics.java` | Serializar/deserializar |
| 025 | `b02_json/Ej025_JsonAnnotations.java` | `@JsonProperty`/`@JsonIgnore` |
| 026 | `b02_json/Ej026_NestedAndCollections.java` | Objetos anidados y listas |
| 027 | `b02_json/Ej027_CustomSerializer.java` | Serializer/Deserializer propio |
| 028 | `b02_json/Ej028_JsonTreeModel.java` | `JsonNode`, lectura dinámica |

### Bloque III · Spring Core: IoC / DI (029–038)
| # | Archivo | Concepto clave |
|---|---|---|
| 029 | `b03_core/Ej029_ManualIoCContainer.java` | Contenedor IoC didáctico |
| 030 | `b03_core/Ej030_ComponentScan.java` | `@Component`/`@Service` |
| 031 | `b03_core/Ej031_ConstructorInjection.java` | Inyección por constructor |
| 032 | `b03_core/Ej032_QualifierAndPrimary.java` | `@Qualifier`/`@Primary` |
| 033 | `b03_core/Ej033_BeanScopes.java` | Singleton vs prototype |
| 034 | `b03_core/Ej034_BeanLifecycle.java` | `@PostConstruct`/`@PreDestroy` |
| 035 | `b03_core/Ej035_JavaConfigBeans.java` | `@Configuration`/`@Bean` |
| 036 | `b03_core/Ej036_ConditionalBeans.java` | `@Conditional`/`@Profile` |
| 037 | `b03_core/Ej037_ApplicationEvents.java` | Eventos y listeners |
| 038 | `b03_core/Ej038_AopCrossCutting.java` | AOP: aspectos transversales |

### Bloque IV · Spring Boot: config y profiles (039–044)
| # | Archivo | Concepto clave |
|---|---|---|
| 039 | `b04_boot/Ej039_ApplicationProperties.java` | `application.yml`, `@Value` |
| 040 | `b04_boot/Ej040_ConfigurationProperties.java` | `@ConfigurationProperties` |
| 041 | `b04_boot/Ej041_Profiles.java` | Perfiles dev/test/prod |
| 042 | `b04_boot/Ej042_ExternalizedConfig.java` | Env vars, 12-factor |
| 043 | `b04_boot/Ej043_AutoConfigurationInsight.java` | Cómo funciona la autoconfig |
| 044 | `b04_boot/Ej044_CommandLineRunner.java` | Arranque y bootstrap |

### Bloque V · Controllers REST básicos (045–054)
| # | Archivo | Concepto clave |
|---|---|---|
| 045 | `b05_web/Ej045_HelloController.java` | `@RestController`/`@GetMapping` |
| 046 | `b05_web/Ej046_PathVariables.java` | `@PathVariable` |
| 047 | `b05_web/Ej047_QueryParams.java` | `@RequestParam`, defaults |
| 048 | `b05_web/Ej048_RequestBodyPost.java` | `@RequestBody`, `201 Created` |
| 049 | `b05_web/Ej049_ResponseEntity.java` | Control de status y headers |
| 050 | `b05_web/Ej050_PutFullUpdate.java` | PUT idempotente |
| 051 | `b05_web/Ej051_PatchPartialUpdate.java` | PATCH parcial |
| 052 | `b05_web/Ej052_DeleteResource.java` | DELETE y `204 No Content` |
| 053 | `b05_web/Ej053_CrudInMemory.java` | CRUD completo en memoria |
| 054 | `b05_web/Ej054_RestControllerAdviceIntro.java` | Manejo central de errores (intro) |

### Bloque VI · Request/Response avanzado (055–062)
| # | Archivo | Concepto clave |
|---|---|---|
| 055 | `b06_webadv/Ej055_ContentNegotiation.java` | JSON/XML por `Accept` |
| 056 | `b06_webadv/Ej056_CorsConfiguration.java` | CORS |
| 057 | `b06_webadv/Ej057_FileUpload.java` | `MultipartFile` |
| 058 | `b06_webadv/Ej058_FileDownload.java` | Streaming de descarga |
| 059 | `b06_webadv/Ej059_RequestResponseHeaders.java` | Lectura/escritura de headers |
| 060 | `b06_webadv/Ej060_HttpCacheEtag.java` | `Cache-Control`, ETag |
| 061 | `b06_webadv/Ej061_VersioningStrategies.java` | Versionado de API |
| 062 | `b06_webadv/Ej062_FilterAndInterceptor.java` | Filtros e interceptores |

### Bloque VII · DTOs y mapeo (063–068)
| # | Archivo | Concepto clave |
|---|---|---|
| 063 | `b07_dto/Ej063_RequestResponseDto.java` | Separar entidad de DTO |
| 064 | `b07_dto/Ej064_ManualMapper.java` | Mapper a mano |
| 065 | `b07_dto/Ej065_MapStructIntro.java` | Mapper declarativo |
| 066 | `b07_dto/Ej066_AssemblerPattern.java` | Patrón Assembler |
| 067 | `b07_dto/Ej067_PartialUpdateDto.java` | DTO para PATCH parcial |
| 068 | `b07_dto/Ej068_NestedDtoGraphs.java` | DTOs anidados/agregados |

### Bloque VIII · Bean Validation (069–076)
| # | Archivo | Concepto clave |
|---|---|---|
| 069 | `b08_valid/Ej069_BasicConstraints.java` | `@NotNull`/`@Size`/`@Email` |
| 070 | `b08_valid/Ej070_NumericAndPattern.java` | `@Min`/`@Max`/`@Pattern` |
| 071 | `b08_valid/Ej071_NestedValidation.java` | `@Valid` en objetos anidados |
| 072 | `b08_valid/Ej072_GroupValidation.java` | Grupos create/update |
| 073 | `b08_valid/Ej073_CustomConstraint.java` | Constraint personalizada |
| 074 | `b08_valid/Ej074_CrossFieldValidation.java` | Validación entre campos |
| 075 | `b08_valid/Ej075_ValidatePathAndParams.java` | Validar `@PathVariable`/params |
| 076 | `b08_valid/Ej076_ProgrammaticValidation.java` | `Validator` programático |

### Bloque IX · Manejo de errores RFC 7807 (077–084)
| # | Archivo | Concepto clave |
|---|---|---|
| 077 | `b09_err/Ej077_GlobalExceptionHandler.java` | `@RestControllerAdvice` |
| 078 | `b09_err/Ej078_ProblemDetail.java` | `ProblemDetail` RFC 7807 |
| 079 | `b09_err/Ej079_DomainExceptionHierarchy.java` | Excepciones de dominio |
| 080 | `b09_err/Ej080_ValidationErrorPayload.java` | Errores de validación agrupados |
| 081 | `b09_err/Ej081_NotFoundAndConflict.java` | 404/409 semánticos |
| 082 | `b09_err/Ej082_ErrorTraceAndCorrelation.java` | `traceId`, correlación |
| 083 | `b09_err/Ej083_ExceptionTranslation.java` | Traducir excepciones de infra |
| 084 | `b09_err/Ej084_GracefulFallbacks.java` | Degradación controlada |

### Bloque X · Arquitectura y patrones (085–092)
| # | Archivo | Concepto clave |
|---|---|---|
| 085 | `b10_arch/Ej085_LayeredArchitecture.java` | Controller→Service→Repo |
| 086 | `b10_arch/Ej086_RepositoryPattern.java` | Patrón Repository |
| 087 | `b10_arch/Ej087_DaoPattern.java` | Patrón DAO clásico (AD) |
| 088 | `b10_arch/Ej088_ServiceTransactional.java` | `@Transactional`, rollback |
| 089 | `b10_arch/Ej089_HexagonalPorts.java` | Puertos y adaptadores |
| 090 | `b10_arch/Ej090_DomainModelVsAnemic.java` | Modelo rico vs anémico |
| 091 | `b10_arch/Ej091_FactoryAndBuilder.java` | Factory/Builder |
| 092 | `b10_arch/Ej092_StrategyAndPolicy.java` | Strategy en la capa de negocio |

### Bloque XI · JDBC profundo (093–102)
| # | Archivo | Concepto clave |
|---|---|---|
| 093 | `b11_jdbc/Ej093_ConnectionDriverManager.java` | `Connection`, drivers |
| 094 | `b11_jdbc/Ej094_StatementVsPrepared.java` | SQL injection, `PreparedStatement` |
| 095 | `b11_jdbc/Ej095_ResultSetMapping.java` | `ResultSet` → objeto |
| 096 | `b11_jdbc/Ej096_CrudDao.java` | DAO CRUD con JDBC puro |
| 097 | `b11_jdbc/Ej097_TransactionsCommitRollback.java` | Transacciones manuales |
| 098 | `b11_jdbc/Ej098_BatchOperations.java` | Inserción por lotes |
| 099 | `b11_jdbc/Ej099_ConnectionPooling.java` | Pool de conexiones (HikariCP) |
| 100 | `b11_jdbc/Ej100_JdbcTemplate.java` | `JdbcTemplate` de Spring |
| 101 | `b11_jdbc/Ej101_RowMapperAndExtractor.java` | `RowMapper`/`ResultSetExtractor` |
| 102 | `b11_jdbc/Ej102_NamedParameterJdbc.java` | `NamedParameterJdbcTemplate` |

### Bloque XII · Spring Data JPA core (103–114)
| # | Archivo | Concepto clave |
|---|---|---|
| 103 | `b12_jpa/Ej103_EntityMapping.java` | `@Entity`/`@Id`/`@Column` |
| 104 | `b12_jpa/Ej104_IdGenerationStrategies.java` | `@GeneratedValue` |
| 105 | `b12_jpa/Ej105_JpaRepository.java` | `JpaRepository` CRUD |
| 106 | `b12_jpa/Ej106_DerivedQueryMethods.java` | Queries por nombre |
| 107 | `b12_jpa/Ej107_JpqlQueries.java` | `@Query` JPQL |
| 108 | `b12_jpa/Ej108_NativeQueries.java` | SQL nativo |
| 109 | `b12_jpa/Ej109_ModifyingQueries.java` | `@Modifying` update/delete |
| 110 | `b12_jpa/Ej110_EntityLifecycleCallbacks.java` | `@PrePersist`/`@PreUpdate` |
| 111 | `b12_jpa/Ej111_EnumAndEmbeddable.java` | `@Enumerated`/`@Embeddable` |
| 112 | `b12_jpa/Ej112_PersistenceContext.java` | Contexto de persistencia, flush |
| 113 | `b12_jpa/Ej113_EqualsHashCodeEntities.java` | Identidad de entidades |
| 114 | `b12_jpa/Ej114_DtoConstructorProjection.java` | Proyección por constructor JPQL |

### Bloque XIII · Relaciones JPA (115–122)
| # | Archivo | Concepto clave |
|---|---|---|
| 115 | `b13_rel/Ej115_OneToOne.java` | `@OneToOne` |
| 116 | `b13_rel/Ej116_OneToManyManyToOne.java` | `@OneToMany`/`@ManyToOne` |
| 117 | `b13_rel/Ej117_ManyToManyJoinTable.java` | `@ManyToMany` |
| 118 | `b13_rel/Ej118_BidirectionalSync.java` | Sincronizar lados de relación |
| 119 | `b13_rel/Ej119_CascadeTypes.java` | `cascade` y orfandad |
| 120 | `b13_rel/Ej120_FetchLazyEager.java` | LAZY vs EAGER |
| 121 | `b13_rel/Ej121_NPlusOneProblem.java` | Diagnóstico/solución N+1 |
| 122 | `b13_rel/Ej122_JoinFetchAndEntityGraph.java` | `JOIN FETCH`, EntityGraph |

### Bloque XIV · JPA avanzado (123–132)
| # | Archivo | Concepto clave |
|---|---|---|
| 123 | `b14_jpaadv/Ej123_TransactionPropagation.java` | Propagación de transacciones |
| 124 | `b14_jpaadv/Ej124_IsolationLevels.java` | Niveles de aislamiento |
| 125 | `b14_jpaadv/Ej125_OptimisticLocking.java` | `@Version` |
| 126 | `b14_jpaadv/Ej126_PessimisticLocking.java` | `LockModeType` |
| 127 | `b14_jpaadv/Ej127_SecondLevelCache.java` | Caché de segundo nivel |
| 128 | `b14_jpaadv/Ej128_Auditing.java` | `@CreatedDate`/`@LastModifiedBy` |
| 129 | `b14_jpaadv/Ej129_SoftDelete.java` | Borrado lógico |
| 130 | `b14_jpaadv/Ej130_InheritanceStrategies.java` | Herencia de entidades |
| 131 | `b14_jpaadv/Ej131_FlushModesBatching.java` | Flush, batch JPA |
| 132 | `b14_jpaadv/Ej132_FlywayMigrations.java` | Migraciones de esquema |

### Bloque XV · Consultas avanzadas (133–142)
| # | Archivo | Concepto clave |
|---|---|---|
| 133 | `b15_query/Ej133_Pagination.java` | `Pageable`/`Page` |
| 134 | `b15_query/Ej134_Sorting.java` | `Sort` multinivel |
| 135 | `b15_query/Ej135_SliceVsPage.java` | `Slice` vs `Page` |
| 136 | `b15_query/Ej136_DynamicFiltering.java` | Filtros por query params |
| 137 | `b15_query/Ej137_Specifications.java` | `Specification` (Criteria) |
| 138 | `b15_query/Ej138_CriteriaApi.java` | Criteria API tipada |
| 139 | `b15_query/Ej139_QueryByExample.java` | Query by Example |
| 140 | `b15_query/Ej140_InterfaceProjections.java` | Proyecciones por interfaz |
| 141 | `b15_query/Ej141_AggregationsGroupBy.java` | Agregaciones y `GROUP BY` |
| 142 | `b15_query/Ej142_KeysetPagination.java` | Paginación por cursor |

### Bloque XVI · XML y ficheros — AD RA1 (143–148)
| # | Archivo | Concepto clave |
|---|---|---|
| 143 | `b16_xml/Ej143_JaxbBinding.java` | JAXB objeto↔XML |
| 144 | `b16_xml/Ej144_JacksonXml.java` | Jackson XML |
| 145 | `b16_xml/Ej145_DomSaxParsing.java` | DOM vs SAX |
| 146 | `b16_xml/Ej146_XmlEndpoint.java` | Endpoint que produce XML |
| 147 | `b16_xml/Ej147_FileBackedRepository.java` | Repo persistido en fichero |
| 148 | `b16_xml/Ej148_CsvImportExport.java` | Import/export CSV |

### Bloque XVII · NoSQL / MongoDB — AD RA4 (149–154)
| # | Archivo | Concepto clave |
|---|---|---|
| 149 | `b17_nosql/Ej149_MongoDocumentMapping.java` | `@Document`/`@Id` |
| 150 | `b17_nosql/Ej150_MongoRepository.java` | `MongoRepository` CRUD |
| 151 | `b17_nosql/Ej151_MongoTemplateQueries.java` | `MongoTemplate`, `Query` |
| 152 | `b17_nosql/Ej152_EmbeddedVsReferences.java` | Embebido vs referencias |
| 153 | `b17_nosql/Ej153_AggregationPipeline.java` | Pipeline de agregación |
| 154 | `b17_nosql/Ej154_MongoRestEndpoint.java` | API REST sobre Mongo |

### Bloque XVIII · Seguridad: Security + JWT (155–164)
| # | Archivo | Concepto clave |
|---|---|---|
| 155 | `b18_sec/Ej155_SecurityFilterChain.java` | Config Spring Security |
| 156 | `b18_sec/Ej156_InMemoryUsers.java` | Usuarios en memoria |
| 157 | `b18_sec/Ej157_PasswordEncoder.java` | BCrypt, hashing |
| 158 | `b18_sec/Ej158_UserDetailsService.java` | Usuarios desde BD |
| 159 | `b18_sec/Ej159_JwtIssue.java` | Emisión de JWT |
| 160 | `b18_sec/Ej160_JwtValidationFilter.java` | Filtro de validación JWT |
| 161 | `b18_sec/Ej161_RoleBasedAccess.java` | Roles, `@PreAuthorize` |
| 162 | `b18_sec/Ej162_MethodSecurity.java` | Seguridad a nivel de método |
| 163 | `b18_sec/Ej163_RefreshTokens.java` | Refresh tokens |
| 164 | `b18_sec/Ej164_CsrfAndCorsHardening.java` | CSRF/CORS, endurecido |

### Bloque XIX · Testing de APIs (165–176)
| # | Archivo | Concepto clave |
|---|---|---|
| 165 | `b19_test/Ej165_UnitTestJUnit5.java` | JUnit 5 puro |
| 166 | `b19_test/Ej166_MockitoMocks.java` | Mockito, stubbing |
| 167 | `b19_test/Ej167_ServiceUnitTest.java` | Test unitario de servicio |
| 168 | `b19_test/Ej168_WebMvcTest.java` | `@WebMvcTest`+`MockMvc` |
| 169 | `b19_test/Ej169_JsonAssertions.java` | Asserts sobre JSON |
| 170 | `b19_test/Ej170_DataJpaTest.java` | `@DataJpaTest` (H2) |
| 171 | `b19_test/Ej171_SpringBootIntegration.java` | `@SpringBootTest` e2e |
| 172 | `b19_test/Ej172_TestcontainersPostgres.java` | Postgres real |
| 173 | `b19_test/Ej173_SecurityTesting.java` | Endpoints protegidos |
| 174 | `b19_test/Ej174_ParameterizedTests.java` | Tests parametrizados |
| 175 | `b19_test/Ej175_TestSlicesAndProfiles.java` | Slices y perfil `test` |
| 176 | `b19_test/Ej176_CoverageAndQualityGate.java` | Cobertura, umbral mínimo |

### Bloque XX · Observabilidad y documentación (177–182)
| # | Archivo | Concepto clave |
|---|---|---|
| 177 | `b20_obs/Ej177_OpenApiSwagger.java` | springdoc-openapi |
| 178 | `b20_obs/Ej178_ApiDocAnnotations.java` | `@Operation`/`@Schema` |
| 179 | `b20_obs/Ej179_ActuatorEndpoints.java` | Actuator, health |
| 180 | `b20_obs/Ej180_CustomHealthMetrics.java` | Health/metrics propios |
| 181 | `b20_obs/Ej181_StructuredLoggingMdc.java` | Logging estructurado, MDC |
| 182 | `b20_obs/Ej182_RequestTracing.java` | Trazas y correlación |

### Bloque XXI · Rendimiento y resiliencia (183–188)
| # | Archivo | Concepto clave |
|---|---|---|
| 183 | `b21_perf/Ej183_SpringCacheAbstraction.java` | `@Cacheable`/`@CacheEvict` |
| 184 | `b21_perf/Ej184_AsyncEndpoints.java` | `@Async`, `CompletableFuture` |
| 185 | `b21_perf/Ej185_RateLimiting.java` | Rate limiting |
| 186 | `b21_perf/Ej186_RetryAndCircuitBreaker.java` | Reintentos, circuit breaker |
| 187 | `b21_perf/Ej187_TimeoutsAndBulkhead.java` | Timeouts, aislamiento |
| 188 | `b21_perf/Ej188_NPlusOneAndQueryTuning.java` | Diagnóstico de rendimiento |

### Bloque XXII · Docker y despliegue (189–194)
| # | Archivo | Concepto clave |
|---|---|---|
| 189 | `b22_deploy/Ej189_Dockerfile.java` | Multi-stage Dockerfile (guion) |
| 190 | `b22_deploy/Ej190_DockerComposeStack.java` | API + Postgres en Compose |
| 191 | `b22_deploy/Ej191_HealthcheckAndDepends.java` | Healthchecks, `depends_on` |
| 192 | `b22_deploy/Ej192_ConfigByEnvironment.java` | Config 12-factor |
| 193 | `b22_deploy/Ej193_GracefulShutdown.java` | Apagado ordenado |
| 194 | `b22_deploy/Ej194_ReverseProxyTraefik.java` | Reverse proxy (guion) |

### Bloque XXIII · CI/CD y calidad (195–198)
| # | Archivo | Concepto clave |
|---|---|---|
| 195 | `b23_ci/Ej195_GithubActionsPipeline.java` | Workflow build+test (guion) |
| 196 | `b23_ci/Ej196_DockerBuildPush.java` | Build & push de imagen (guion) |
| 197 | `b23_ci/Ej197_StaticAnalysisGate.java` | Análisis estático/quality gate |
| 198 | `b23_ci/Ej198_DeployWebhookHomelab.java` | Deploy vía webhook (guion) |

### Bloque XXIV · Boss Final — multi-parte (199–200)
| # | Archivo | Concepto clave |
|---|---|---|
| 199 | `b24_boss/Ej199_TaskTrackerCoreApi.java` | **API corporativa**: dominio + JPA + relaciones + validación + errores RFC7807 + paginación/filtrado |
| 200 | `b24_boss/Ej200_TaskTrackerSecuredObservable.java` | **El Boss Final**: + JWT/roles + caché + Actuator + dockerizable, con la suite de tests más rigurosa del bootcamp (integra los 24 bloques) |

### Bloque XXV · Plantillas Dinámicas y PDF — Thymeleaf (201–206)
| # | Archivo | Concepto clave |
|---|---|---|
| 201 | `b25_thymeleaf/Ej201_ThymeleafStandalone.java` | Configuración motor Standalone aislado |
| 202 | `b25_thymeleaf/Ej202_VariablesSimplesFactura.java` | Inyección simple, formateo numérico/fechas |
| 203 | `b25_thymeleaf/Ej203_IteracionesCondicionales.java` | Bucles para líneas de albarán, condicionales |
| 204 | `b25_thymeleaf/Ej204_FragmentosReutilizables.java` | `th:replace` para cabeceras fiscales |
| 205 | `b25_thymeleaf/Ej205_Internacionalizacion.java` | I18n de facturas multi-idioma |
| 206 | `b25_thymeleaf/Ej206_HtmlToPdfGenerator.java` | Transición HTML renderizado a binario PDF |

### Bloque XXVI · I/O de ficheros y NIO.2 — AD RA1 (207–214)
| # | Archivo | Concepto clave |
|---|---|---|
| 207 | `b26_io/Ej207ByteStreams.java` | `InputStream`/`OutputStream`, copia con buffer, EOF |
| 208 | `b26_io/Ej208CharStreams.java` | `Reader`/`Writer`, encodings (`Charset`), mojibake |
| 209 | `b26_io/Ej209RandomAccessFile.java` | Acceso aleatorio: `seek`, registros de tamaño fijo |
| 210 | `b26_io/Ej210ObjectSerialization.java` | `Serializable`, `transient`, grafos de objetos |
| 211 | `b26_io/Ej211Nio2PathFiles.java` | `Path`/`Files`: crear, copiar, mover, borrar |
| 212 | `b26_io/Ej212Nio2ReadWriteWalk.java` | `readAllLines`, `walk`/`find`/`list`, atributos |
| 213 | `b26_io/Ej213TempFilesAndChannels.java` | Temporales, `FileChannel`/`ByteBuffer`, `flip` |
| 214 | `b26_io/Ej214FormatConversion.java` | Conversión texto↔binario, properties↔CSV, Base64/hex |

### Bloque XXVII · Concurrencia y multihilo — PSP RA2 (215–226)
| # | Archivo | Concepto clave |
|---|---|---|
| 215 | `b27_concur/Ej215ThreadRunnable.java` | `Thread` vs `Runnable`, `start`/`run`, `join` |
| 216 | `b27_concur/Ej216ThreadStates.java` | Estados del hilo, `sleep`, interrupción |
| 217 | `b27_concur/Ej217RaceConditionSynchronized.java` | Condición de carrera y `synchronized` |
| 218 | `b27_concur/Ej218WaitNotify.java` | `wait`/`notify`: productor-consumidor |
| 219 | `b27_concur/Ej219ExecutorService.java` | `ExecutorService`, pools, `shutdown` |
| 220 | `b27_concur/Ej220CallableFuture.java` | `Callable`, `Future`, `get`/`cancel`/timeout |
| 221 | `b27_concur/Ej221Locks.java` | `ReentrantLock`, `tryLock`, `ReadWriteLock`, `Condition` |
| 222 | `b27_concur/Ej222Semaphores.java` | `Semaphore`, `CountDownLatch`, `CyclicBarrier` |
| 223 | `b27_concur/Ej223AtomicAndConcurrentCollections.java` | Atómicos y colecciones concurrentes |
| 224 | `b27_concur/Ej224DeadlockLivelock.java` | Deadlock: provocar, evitar, detectar |
| 225 | `b27_concur/Ej225CompletableFutureAdvanced.java` | `CompletableFuture` (composición async) |
| 226 | `b27_concur/Ej226ThreadPriorityAndContext.java` | Prioridades, `ThreadLocal`, daemon, contexto |

### Bloque XXVIII · Multiproceso e IPC — PSP RA1 (227–232)
| # | Archivo | Concepto clave |
|---|---|---|
| 227 | `b28_proc/Ej227ProcessBuilderBasics.java` | `ProcessBuilder`, `start`/`waitFor`, exit code, PID |
| 228 | `b28_proc/Ej228ProcessIO.java` | Redirección de stdin/stdout/stderr, leer salida |
| 229 | `b28_proc/Ej229ProcessPipesIPC.java` | IPC por pipes: escribir al stdin, leer stdout |
| 230 | `b28_proc/Ej230ProcessTimeoutAndDestroy.java` | `waitFor(timeout)`, `destroy`/`destroyForcibly` |
| 231 | `b28_proc/Ej231ParallelProcesses.java` | Procesos en paralelo, recoger códigos de salida |
| 232 | `b28_proc/Ej232ProcessEnvAndDir.java` | `environment()`, directorio de trabajo, `inheritIO` |

(Apoyo: `b28_proc/ProcesoHijo.java` — proceso Java hijo determinista usado por los tests.)

### Bloque XXIX · Sockets y programación en red — PSP RA3 (233–240)
| # | Archivo | Concepto clave |
|---|---|---|
| 233 | `b29_sockets/Ej233TcpEchoServer.java` | `ServerSocket`/`Socket`, eco TCP, streams del socket |
| 234 | `b29_sockets/Ej234TcpClient.java` | Cliente TCP, `setSoTimeout`, `ConnectException` |
| 235 | `b29_sockets/Ej235MultiClientThreadedServer.java` | Servidor multicliente: un hilo por conexión |
| 236 | `b29_sockets/Ej236ApplicationProtocol.java` | Protocolo de aplicación propio (mini key-value) |
| 237 | `b29_sockets/Ej237UdpDatagrams.java` | UDP: `DatagramSocket`/`DatagramPacket`, sin conexión |
| 238 | `b29_sockets/Ej238ObjectOverSocket.java` | Objetos serializados por socket (`ObjectStream`) |
| 239 | `b29_sockets/Ej239ServerWithThreadPool.java` | Servidor con `ExecutorService` (límite de conexiones) |
| 240 | `b29_sockets/Ej240GracefulShutdownAndTimeouts.java` | Cierre ordenado, timeouts, desconexiones |

### Bloque XXX · Criptografía y programación segura — PSP RA5 (241–248)
| # | Archivo | Concepto clave |
|---|---|---|
| 241 | `b30_crypto/Ej241Hashing.java` | `MessageDigest` (SHA-256), integridad, efecto avalancha |
| 242 | `b30_crypto/Ej242PasswordHashingSalt.java` | Salt + PBKDF2 (hashing lento), contraste con BCrypt |
| 243 | `b30_crypto/Ej243SymmetricAes.java` | Cifrado simétrico AES (GCM/CBC/ECB, IV) |
| 244 | `b30_crypto/Ej244AsymmetricRsa.java` | Cifrado asimétrico RSA, pública/privada |
| 245 | `b30_crypto/Ej245DigitalSignature.java` | Firma digital (`Signature`), no repudio, ECDSA |
| 246 | `b30_crypto/Ej246HmacAndSecureRandom.java` | HMAC (integridad con clave), `SecureRandom`, tokens |
| 247 | `b30_crypto/Ej247KeyStore.java` | `KeyStore` PKCS12/JCEKS: custodia de claves y certificados |
| 248 | `b30_crypto/Ej248TlsSecureChannel.java` | Canal seguro TLS (`SSLSocket`), cifrado híbrido |

### Bloque XXXI · BD objeto-relacionales/OO y procedimientos — AD RA4 + RA2.k (249–254)
| # | Archivo | Concepto clave |
|---|---|---|
| 249 | `b31_oodb/Ej249CallableStatement.java` | `CallableStatement`, escape JDBC, parámetros IN/OUT |
| 250 | `b31_oodb/Ej250StoredFunctionResult.java` | Funciones almacenadas: escalar y de tabla (`ResultSet`) |
| 251 | `b31_oodb/Ej251ObjectRelationalTypes.java` | Tipos objeto-relacionales `ARRAY` (`java.sql.Array`) |
| 252 | `b31_oodb/Ej252PersistObjectGraph.java` | Persistir/rehidratar un grafo de objetos (desfase O-R) |
| 253 | `b31_oodb/Ej253OqlStyleQueries.java` | Consultas estilo OQL (navegar objetos ≙ JOIN) |
| 254 | `b31_oodb/Ej254TransactionsOnObjects.java` | Transacciones sobre objetos: commit/rollback, savepoints |

### Bloque XXXII · JavaFX: aplicación, escena y layouts — DI RA1 (255–262)
| # | Archivo | Concepto clave |
|---|---|---|
| 255 | `b32_fxbase/Ej255AppLifecycle.java` | `Application`, `init`/`start`/`stop`, `Stage`, `launch`, construir la raíz |
| 256 | `b32_fxbase/Ej256SceneGraph.java` | Árbol de nodos: `Parent`/`Node`, recorrer/buscar por id, `visible`/`managed`, z-order |
| 257 | `b32_fxbase/Ej257StageWindow.java` | `Stage`: título, tamaño, posición, modalidad, ventanas en cascada |
| 258 | `b32_fxbase/Ej258LayoutBoxes.java` | `VBox`/`HBox`: spacing, padding, `setMargin`, `Priority.ALWAYS`, alignment |
| 259 | `b32_fxbase/Ej259BorderGridPane.java` | `BorderPane` (5 zonas) y `GridPane` (filas/columnas, colspan) |
| 260 | `b32_fxbase/Ej260StackAnchorFlow.java` | `StackPane`, `AnchorPane`, `FlowPane`, `TilePane` |
| 261 | `b32_fxbase/Ej261SizingAndBounds.java` | min/pref/max size, `Insets`, bounds, resizable |
| 262 | `b32_fxbase/Ej262SceneSwitching.java` | Cambiar de escena/vista, raíz dinámica, mini-router de navegación |

(Apoyo: `b32_fxbase` (test) `IniciadorFx.java` — arranca el toolkit JavaFX headless (Monocle) una vez.)

### Bloque XXXIII · Controles, Properties y Binding observable — DI RA1/RA2 (263–270)
| # | Archivo | Concepto clave |
|---|---|---|
| 263 | `b33_fxcontrols/Ej263BasicControls.java` | `Label`, `Button`, `TextField`, `PasswordField`, `CheckBox`, `RadioButton`/`ToggleGroup`, `TextInputControl` |
| 264 | `b33_fxcontrols/Ej264ChoiceComboPicker.java` | `ChoiceBox`, `ComboBox` (editable), `DatePicker`, `Spinner`, `Slider` |
| 265 | `b33_fxcontrols/Ej265PropertiesBasics.java` | `StringProperty`/`IntegerProperty`, `get`/`set`, `ChangeListener` vs `InvalidationListener`, patrón JavaFX bean |
| 266 | `b33_fxcontrols/Ej266UnidirectionalBinding.java` | `bind()`, operadores fluent, recálculo automático, property enlazada = solo lectura |
| 267 | `b33_fxcontrols/Ej267BidirectionalBinding.java` | `bindBidirectional`, sincronizar dos controles, enlace con `StringConverter` |
| 268 | `b33_fxcontrols/Ej268BindingsExpressions.java` | `Bindings.when/concat/createStringBinding/format/size/valueAt` (fluent) |
| 269 | `b33_fxcontrols/Ej269Converters.java` | `StringConverter`, conversores de fábrica, enchufar a control, parseo seguro |
| 270 | `b33_fxcontrols/Ej270FormValidationLive.java` | Validación reactiva: `botonHabilitado` bindeado al estado del form (ViewModel, 100% testeable sin UI) |

(Apoyo: `b33_fxcontrols` (test) `IniciadorFx.java` — arranca el toolkit JavaFX headless (Monocle) una vez.)

### Bloque XXXIV · FXML, Scene Builder, MVC/MVVM y eventos — DI RA1 (271–278)
| # | Archivo | Concepto clave |
|---|---|---|
| 271 | `b34_fxfxml/Ej271FxmlLoaderBasics.java` | `FXMLLoader`, cargar `.fxml` del classpath, `fx:controller`, `fx:root`, `controllerFactory` |
| 272 | `b34_fxfxml/Ej272ControllerInjection.java` | `@FXML` campos/métodos, `fx:id`, `initialize()`, `lookup("#id")`, inyección del controlador |
| 273 | `b34_fxfxml/Ej273EventHandlers.java` | `onAction`, `EventHandler`, `ActionEvent`, lambdas, `fire()`, `setOnAction` vs `addEventHandler` |
| 274 | `b34_fxfxml/Ej274MouseKeyboardEvents.java` | `MouseEvent`/`KeyEvent`, filtros vs handlers, captura/objetivo/burbuja, `consume()`, atajos |
| 275 | `b34_fxfxml/Ej275MvcSeparation.java` | MVC: dónde vive cada cosa (Vista/Controlador/Modelo), alta de cliente, record inmutable |
| 276 | `b34_fxfxml/Ej276MvvmViewModel.java` | MVVM: ViewModel `bindeado` (estado + comandos + validez), vista pasiva (100% testeable sin UI) |
| 277 | `b34_fxfxml/Ej277MultiViewNavigation.java` | Router multi-vista FXML, historial (pila), contexto compartido, maestro-detalle |
| 278 | `b34_fxfxml/Ej278DialogsAndAlerts.java` | `Alert`/`Dialog`/`ChoiceDialog`, `showAndWait`, `ButtonType`, decisión abstraída tras interfaz |

(Apoyo: `b34_fxfxml` (main) `Controlador271`/`Controlador272` y FXML en `resources/.../b34_fxfxml/`; (test) `IniciadorFx.java` — arranca el toolkit headless (Monocle) y reenvía fallos del FX thread.)

### Bloque XXXV · Datos observables, tablas y asincronía — DI RA2/RA4 (279–286)

| # | Archivo | Conceptos |
|---|---------|-----------|
| 279 | `b35_fxdata/Ej279ObservableCollections.java` | `ObservableList`, `FXCollections`, `ListChangeListener` (`wasAdded`/`wasRemoved`/`wasUpdated`), `unmodifiableObservableList`, `sort`/`reverse`, `FilteredList`/`SortedList`, extractor |
| 280 | `b35_fxdata/Ej280ListViewCellFactory.java` | `ListView`, `cellFactory`, `ListCell.updateItem(item, empty)`, render/clase por estado, `SelectionMode.MULTIPLE`, placeholder, items vivos |
| 281 | `b35_fxdata/Ej281TableViewColumns.java` | `TableView`, `TableColumn`, `cellValueFactory` (`PropertyValueFactory` vs lambda), `getCellObservableValue`, columna calculada, `CONSTRAINED_RESIZE_POLICY`, `sortOrder` |
| 282 | `b35_fxdata/Ej282TableEditSortFilter.java` | Cadena `ObservableList`→`FilteredList`→`SortedList`, `setPredicate`, `comparatorProperty().bind`, edición *in-cell* (`TextFieldTableCell`, `setOnEditCommit`), extractor + filtro vivo |
| 283 | `b35_fxdata/Ej283TaskBackground.java` | `Task<V>`: `call()`, `updateProgress`/`updateMessage`, `Task` es `Future`+`Runnable` (`get()` determinista), progreso monótono, `cancel()`, reintentos |
| 284 | `b35_fxdata/Ej284ServiceAndBindProgress.java` | `Service<V>`: `createTask()`, `start`/`restart`/`reset`, `Worker.State`, `ProgressBar.progressProperty().bind(...)`, indeterminado, `setOnFailed` |
| 285 | `b35_fxdata/Ej285PlatformRunLater.java` | Regla del FX App Thread, `Platform.isFxApplicationThread()`, `Platform.runLater` (FIFO), crear `Stage` fuera del hilo FX → `IllegalStateException`, patrón fondo→UI |
| 286 | `b35_fxdata/Ej286ConsumeRestApi.java` | Cliente REST: `HttpClient` (JDK) GET a la API (b05), parseo Jackson (`TypeReference`), `Task<List<ClienteDto>>`, éxito/timeout/paginación/manejo de error HTTP, poblar `TableView` |

(Apoyo: `b35_fxdata` (main) `ClienteFx` (bean observable) y `ClienteDto` (record JSON); (test) `IniciadorFx.java` — toolkit headless (Monocle) y los tests de red levantan un servidor HTTP local en puerto efímero.)

### Bloque XXXVI · CSS, usabilidad, accesibilidad e i18n — DI RA3 (287–292)

| # | Archivo | Conceptos |
|---|---------|-----------|
| 287 | `b36_fxstyle/Ej287CssStylesheets.java` | Hoja `.css`, selectores por tipo/clase/id, `getStyleClass`, descendiente vs multiclase, estilo inline (`setStyle`), añadir/quitar/alternar clase, especificidad (id 100 > clase 10 > tipo 1), estado→clase |
| 288 | `b36_fxstyle/Ej288PseudoClassesStates.java` | Pseudo-clases (`:hover`/`:focused`/`:pressed`/`:disabled`), `selectorConPseudo`, `PseudoClass` propia (`getPseudoClass`+`pseudoClassStateChanged`), `:not`, encadenado, precedencia de estados, máquina de estados |
| 289 | `b36_fxstyle/Ej289ThemingAndVariables.java` | Temas claro/oscuro, `alternarTema`, hoja por tema, *looked-up colors*, `derive`/`linear-gradient`, ciclo de 3 temas, clase del `.root`, orden de hojas en la cascada, paleta como diccionario |
| 290 | `b36_fxstyle/Ej290AccessibilityA11y.java` | Orden de foco (fila/columna), mnemónicos (`_Guardar`→`Alt+G`, `__` literal), `accessibleText`/rol, atajo `Shortcut+`, contraste WCAG (AA 4.5 / AAA 7), Tab salta deshabilitados |
| 291 | `b36_fxstyle/Ej291UsabilityFeedback.java` | Mensajes de error orientados al usuario, estado de botón (cargando/deshabilitado/activo), `disableProperty().bind`, tooltips, plural, truncado, resumen de errores, mensaje accionable (RFC 7807 / b09) |
| 292 | `b36_fxstyle/Ej292Internationalization.java` | `ResourceBundle`/`Locale.forLanguageTag`, `getString`/`containsKey`+fallback, `MessageFormat` (`{0}`), `NumberFormat`/`Currency` por región, nombre de mes, toggle de idioma, traducir pantalla; conecta con i18n de b25 |

(Apoyo: `b36_fxstyle` (main) `PlaygroundEstilo` (`Application` que aplica CSS y alterna tema/idioma en caliente); recursos `css/app.css`, `css/tema-claro.css`, `css/tema-oscuro.css` e `i18n/mensajes{,_es,_en}.properties`. Los tests son lógica pura: NO necesitan toolkit JavaFX.)

---

### Bloque XXXVII · Componentes personalizados, Canvas y gráficos — DI RA2 (293–298)

| # | Archivo | Conceptos |
|---|---------|-----------|
| 293 | `b37_fxcustom/Ej293CustomControlCompose.java` | Componente compuesto (`extends VBox`) con API propia: etiqueta con asterisco de obligatorio, estado del campo (vacío/corto/válido), contador, recorte, clase→estado, normalizar, mensaje de error, id accesible, progreso y resumen de errores de un formulario |
| 294 | `b37_fxcustom/Ej294SkinnableControl.java` | `Control`+`Skin` (MVC del control): valor acotado a `[min,max]`, estrellas llenas, fracción del rango, ángulo de aguja (270°), color por zona, marcas equidistantes, pseudo-estado (b36), serializar estado (b02) |
| 295 | `b37_fxcustom/Ej295CanvasDrawing.java` | `Canvas`/`GraphicsContext`: vértices de polígono regular, rotación de un punto, centro/medio/distancia (`Math.hypot`), punto en circunferencia, bounding box, traslación, área (shoelace), estrella de dos radios |
| 296 | `b37_fxcustom/Ej296CanvasInteractive.java` | *Hit testing*: dentro de rectángulo/círculo (dist²≤r²), rango 1D, línea con tolerancia, snap a rejilla, clamp de arrastre, colisión AABB (b41), ray casting de polígono, z-order, escena→canvas, selección por lazo |
| 297 | `b37_fxcustom/Ej297ChartsBuiltIn.java` | `LineChart`/`BarChart`/`PieChart`: serie X/Y (X desde 1), porcentajes de tarta, máximo/media, acumulado, normalizar 0..100, agrupar por categoría (`merge`), rango del eje, tendencia, top N, apilado (b38) |
| 298 | `b37_fxcustom/Ej298ShapesAndEffects.java` | `Shape`/`Color`/`Paint`/`Effect`: mezclar colores, a/desde hex (`%02X`), aclarar/oscurecer acotado, luminancia (0.299/0.587/0.114), texto legible (b36), rgba, interpolar degradado, radio de sombra, paleta derivada |

(Apoyo: `b37_fxcustom` (main) `PlaygroundComponentes` (`Application` que dibuja en un `Canvas`, monta un `BarChart` y muestra el control compuesto). Los cores son lógica pura headless (geometría, estado, datos, color): NO necesitan toolkit JavaFX.)

---

### Bloque XXXVIII · Informes, PDF e impresión — DI RA4 (299–304)

| # | Archivo | Conceptos |
|---|---------|-----------|
| 299 | `b38_fxreports/Ej299ReportDataModel.java` | Modelo de datos del informe (`JRBeanCollectionDataSource`): nº de registros, total con IVA, campos `$F{}`, cliente, gran total, filtrar/proyectar, factura mayor (`Optional`), total por cliente (`merge`/GROUP BY), base imponible, validar, fila detalle, ordenar por total |
| 300 | `b38_fxreports/Ej300JasperFillExport.java` | Pipeline `.jrxml`→compile→`.jasper`→fill→export: cabecera `%PDF-` (magic number), `esPdf`, nombre compilado, extensión, contenido no vacío, versión del PDF, tamaño KB, ruta en classpath, `empiezaPor` genérico, round-trip de bytes, resumen de export |
| 301 | `b38_fxreports/Ej301ReportParamsAndGroups.java` | Parámetros `$P{}` con valor por defecto y agrupaciones: subtotal por grupo (`merge`), gran total, título, nº de grupos, líneas/subtotal de un grupo, recuento y media por grupo, grupo mayor (`Optional`), % sobre total, parámetro entero, línea de resumen |
| 302 | `b38_fxreports/Ej302SubreportsAndCharts.java` | Subinformes (maestro-detalle) y gráfico embebido: líneas de un pedido, total por pedido, ventas por mes, nº de pedidos, total/nº de líneas, mes pico (`Optional`), pedido mayor, media por pedido, % por mes, ranking de meses, productos distintos, acumulado (b37) |
| 303 | `b38_fxreports/Ej303JavaFxPrinting.java` | `PrinterJob` de JavaFX: escala para caber (sin agrandar), páginas necesarias (`ceil`), área imprimible, cabe sin escalar, orientación, % de escala, centrar, mm↔puntos (72/25.4), dimensión escalada, margen deducido, paginar tabla, rango de página (= Pageable b12) |
| 304 | `b38_fxreports/Ej304ExportFormats.java` | Exportar PDF/XLSX/CSV y detectar por *magic number*: firmas (`%PDF-`, `PK..`), detectar formato, construir CSV, extensión/MIME, binario vs texto, escapar celda (RFC 4180), fila/cabecera CSV, contar columnas, formatos disponibles, nombre de salida, validar firma vs formato (b18) |

(Apoyo: `b38_fxreports` (main) `PlaygroundInformes` (`Application` con una `TableView` de facturas y un botón que usa `PrinterJob`). Los cores son lógica pura headless (modelo de datos, totales, bytes/magic numbers, geometría de impresión, CSV): NO necesitan toolkit JavaFX ni el motor JasperReports —el código real de Jasper se enseña en `teoria/38_Informes_PDF.md` y se activa descomentando la dependencia del `pom.xml`.)

---

### Bloque XXXIX · Documentación, ayuda y distribución — DI RA5/RA6 (305–310)

| # | Archivo | Conceptos |
|---|---------|-----------|
| 305 | `b39_fxdeploy/Ej305JavadocAndManifest.java` | Javadoc y metadatos del `MANIFEST.MF`: parsear pares `Clave: Valor`, leer atributo, `Implementation-Version` con respaldo, título/vendor/`Main-Class`, ¿existe atributo?, nº de atributos, construir línea, `Class-Path` (split por espacios), cobertura de Javadoc (% entero), nombre del jar, validar SemVer (b39 Ej310) |
| 306 | `b39_fxdeploy/Ej306IntegratedHelp.java` | Ayuda integrada: texto "Acerca de" (record `AcercaDe`), URL válida (http/https), línea de copyright, título de ventana, URL de docs por versión (barra final), email simple, secciones del manual, búsqueda sin mayúsculas, atajo, índice numerado (desde 1), ancla/slug, copyright desactualizado, "Acerca de" completo |
| 307 | `b39_fxdeploy/Ej307UserPreferences.java` | `Preferences` API (nodo temporal en test): guardar/leer/borrar texto, int/boolean/double, clave válida (`MAX_KEY_LENGTH` 80), existe clave, listar claves ordenadas, limpiar nodo (`clear`/`BackingStoreException`), ruta absoluta del nodo (registro/`.userPrefs`) |
| 308 | `b39_fxdeploy/Ej308ModularJlink.java` | **Guion**: parsear `module-info.java` (nombre, `requires`, `exports`), nº de requires/exports, ¿requiere módulo?, `requires transitive`, nombre de módulo válido (sin guiones), `--add-modules` (comas), comando `jlink`, módulo automático (`-`/`_`→`.`), módulo JDK (`java.`/`jdk.`), módulos de terceros (b22) |
| 309 | `b39_fxdeploy/Ej309JpackageInstaller.java` | **Guion**: tipo de instalador por SO (msi/deb/dmg/app-image), extensión (app-image = carpeta), comando `jpackage`, icono por SO (`.ico`/`.png`/`.icns`), nombre de app válido, `--app-version` (reutiliza Ej305), `--vendor` con comillas, nombre del paquete, herramienta externa (WiX), `--input`, nº de tipos por SO, tipo válido en SO, comando completo |
| 310 | `b39_fxdeploy/Ej310VersioningAndUpdate.java` | Versionado semántico: comparar versiones (cifra a cifra, NO texto), `hayActualizacion`, validar SemVer, parsear a `int[]`, MAJOR, incrementar patch/minor/major (con reinicios), formato `v`/quitar `v`, última de una lista, ¿pre-release? (`-beta`), tipo de cambio (major/minor/patch/ninguno) |

(Apoyo: `b39_fxdeploy` (main) `PlaygroundDistribucion` (`Application` con diálogo "Acerca de", `Hyperlink` a la doc, `CheckBox` de modo oscuro persistido con `Preferences` y aviso de actualización SemVer) y `README.md` con los comandos reales de `jlink`/`jpackage`. Los cores son lógica pura headless (parsear manifest/module-info, construir comandos como cadenas, comparar SemVer, `Preferences` en nodo temporal): NO necesitan toolkit JavaFX. `Ej308`/`Ej309` son **"guion"**: construyen y validan comandos de terminal; ejecutar `jlink`/`jpackage` de verdad se documenta en `teoria/39_Distribucion_Instaladores.md` y el README. **Con b39 el módulo DI (0487) queda cubierto al completo.**)

---

## 5. Progreso

- [x] B0 · Fundamentos HTTP/Web (001–010) ✅ compila, tests en rojo (a implementar)
- [x] B1 · Java moderno (011–022) ✅ compila
- [x] B2 · JSON y Jackson (023–028) ✅ compila
- [x] B3 · Spring Core IoC/DI (029–038) ✅ compila
- [x] B4 · Boot config/profiles (039–044) ✅ compila
- [x] B5 · Controllers REST básicos (045–054) ✅ compila
- [x] B6 · Request/Response avanzado (055–062) ✅ compila
- [x] B7 · DTOs y mapeo (063–068) ✅ compila
- [x] B8 · Bean Validation (069–076) ✅ compila
- [x] B9 · Manejo de errores (077–084) ✅ compila
- [x] B10 · Arquitectura y patrones (085–092) ✅ compila
- [x] B11 · JDBC profundo (093–102) ✅ compila
- [x] B12 · Spring Data JPA core (103–114) ✅ compila
- [x] B13 · Relaciones JPA (115–122) ✅ compila
- [x] B14 · JPA avanzado (123–132) ✅ compila
- [x] B15 · Consultas avanzadas (133–142) ✅ compila
- [x] B16 · XML y ficheros (143–148) ✅ compila
- [x] B17 · NoSQL / MongoDB (149–154) ✅ compila
- [x] B18 · Seguridad + JWT (155–164) ✅ compila
- [x] B19 · Testing (165–176) ✅ compila
- [x] B20 · Observabilidad y docs (177–182) ✅ compila
- [x] B21 · Rendimiento y resiliencia (183–188) ✅ compila
- [x] B22 · Docker y despliegue (189–194) ✅ compila
- [x] B23 · CI/CD y calidad (195–198) ✅ compila
- [x] B24 · Boss Final (199–200) ✅ compila
- [x] B25 · Plantillas y PDF Thymeleaf (201–206) ✅ compila
- [x] B26 · I/O de ficheros y NIO.2 · AD RA1 (207–214) ✅ compila, tests en rojo (a implementar) · 12 retos/ejercicio
- [x] B27 · Concurrencia y multihilo · PSP RA2 (215–226) ✅ compila, tests en rojo (a implementar)
- [x] B28 · Multiproceso e IPC · PSP RA1 (227–232) ✅ compila, tests en rojo (a implementar)
- [x] B29 · Sockets y programación en red · PSP RA3 (233–240) ✅ compila, tests en rojo (a implementar)
- [x] B30 · Criptografía y programación segura · PSP RA5 (241–248) ✅ compila, tests en rojo (a implementar)
- [x] B31 · BD objeto-relacionales/OO y procedimientos · AD RA4+RA2.k (249–254) ✅ compila, tests en rojo (a implementar)
- [x] B32 · JavaFX: aplicación, escena y layouts · DI RA1 (255–262) ✅ compila, tests en rojo (a implementar) · 96 tests headless (Monocle)
- [x] B33 · Controles, Properties y Binding observable · DI RA1/RA2 (263–270) ✅ compila, tests en rojo (a implementar) · 96 tests headless (Monocle)
- [x] B34 · FXML, Scene Builder, MVC/MVVM y eventos · DI RA1 (271–278) ✅ compila, tests en rojo (a implementar) · 96 tests headless (Monocle)
- [x] B35 · Datos observables, tablas y asincronía · DI RA2/RA4 (279–286) ✅ compila, tests en rojo (a implementar) · 96 tests headless (Monocle) + servidor HTTP local en los tests de red
- [x] B36 · CSS, usabilidad, accesibilidad e i18n · DI RA3 (287–292) ✅ compila, tests en rojo (a implementar) · 72 tests de lógica pura (sin toolkit) + recursos CSS/i18n
- [x] B37 · Componentes personalizados, Canvas y gráficos · DI RA2 (293–298) ✅ compila, tests en rojo (a implementar) · 72 tests de lógica pura (geometría/estado/datos/color, sin toolkit) + Playground con Canvas y BarChart
- [x] B38 · Informes, PDF e impresión · DI RA4 (299–304) ✅ compila, tests en rojo (a implementar) · 78 tests de lógica pura (modelo de datos, totales, magic numbers, geometría de impresión, CSV; sin toolkit ni motor Jasper) + Playground con TableView y PrinterJob
- [x] B39 · Documentación, ayuda y distribución · DI RA5/RA6 (305–310) ✅ compila, tests en rojo (a implementar) · 78 tests de lógica pura (parsear manifest/module-info, `Preferences` en nodo temporal, comandos jlink/jpackage como cadenas, SemVer; sin toolkit) + Playground con "Acerca de"/Hyperlink/preferencias. **Cierra el módulo DI (0487).** `Ej308`/`Ej309` son "guion" (comandos de terminal en el README)
