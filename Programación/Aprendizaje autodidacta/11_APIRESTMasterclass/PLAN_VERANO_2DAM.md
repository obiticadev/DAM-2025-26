# PLAN DE VERANO · Adelantar 2º DAM por ejercicio (julio–agosto 2026)

> **Qué es esto.** Un calendario **semana a semana y ejercicio a ejercicio** para usar la
> masterclass (`b00`–`b47`, 362 ejercicios) y llegar a 2º de DAM con **Acceso a Datos (AD) y
> Programación de Servicios y Procesos (PSP) medio ganados** —tu objetivo es el **doble 10**, como
> el 10 MH que sacaste en Programación— y con **todos los RA del resto de módulos al menos tocados**.
>
> **Fuentes que cruza este plan** (lectura punto por punto):
> - `SYLLABUS.md` → nombre y archivo exacto de cada ejercicio.
> - `ROADMAP_CIERRE_BOE2023.md` → mapeo **autoritativo RA→bloque** contra `BOE-A-2023-13221.pdf`
>   (Real Decreto 405/2023). Es la verdad oficial sobre qué RA cubre cada bloque.
> - `RUTA_ESTUDIO_2DAM.md` → el orden lógico por módulo (este plan lo aterriza a nivel de ejercicio).
>
> **Tus parámetros (elegidos por ti):** 10–12 h/semana constantes · AD+PSP a fondo, resto cobertura
> de RA · track REST/Spring incluido como pista secundaria de empleabilidad.

---

## 0. La cuenta honesta (léela antes de empezar)

| Concepto | Número |
|---|---|
| Semanas disponibles (1 jul – 31 ago) | **9** |
| Horas totales (a 11 h/sem) | **~100 h** |
| Ejercicios AD a fondo | **82** |
| Ejercicios PSP a fondo | **44** |
| Java base (b01) | **12** |
| **Núcleo del verano (Pista A)** | **138 ejercicios** |

A 30–40 min por ejercicio (leer Javadoc + 10 TODOs + test en verde + leer teoría), **AD + PSP a
fondo ocupan prácticamente las 9 semanas**. Eso **es el plan**, no un problema: son los dos módulos
duros donde más gente suspende y donde tú quieres el 10.

Por eso el verano se organiza en **dos pistas paralelas**:

- **Pista A · NÚCLEO (≈10 h/sem):** AD + PSP a fondo. **De aquí sale el doble 10.** No es negociable.
- **Pista B · COBERTURA (≈1–2 h/sem, opcional):** un **ejercicio bandera por cada RA** de
  Desarrollo de Interfaces, Multimedia/Móvil y Gestión Empresarial, más el track REST. Sirve para
  **tocar cada RA del BOE** y llegar a 2º sin que nada te suene a chino. Lo que no entre, está
  **planificado para septiembre** (§7). Así "cubres todos los RA" de forma honesta: tocados en
  verano, dominados en el curso.

> **Regla de oro** (de `RUTA_ESTUDIO_2DAM.md`): un ejercicio está hecho **solo cuando el test está
> en verde**. Si llevas 3 días leyendo sin escribir código, caíste en el bucle de tutoriales.

---

## 1. Leyenda de prioridades

| Marca | Significado | Qué hacer |
|---|---|---|
| 🟥 **P0** | Núcleo AD/PSP | Obligatorio. Los 10 TODOs core + test verde. |
| 🟦 **P1** | Cobertura de RA (DI/PMDM/SGE/REST) | Un ejercicio por RA. Toca y entiende; no hace falta dominar. |
| 🟩 **P2** | Maestría / "si te sobra tiempo" | Retos extra y ejercicios avanzados. Para nota de sobresaliente o 2ª pasada. |

Cada bloque trae además **10 retos extra** (`retoExtra01..10`) guiados. En verano, con los **10 TODOs
core** de cada ejercicio vas servido; los retos son P2.

---

## 2. Matriz de cobertura RA → bloque → ejercicios (los 5 módulos)

> Esta es la prueba de que **todos los RA del BOE 2023 quedan cubiertos**. Lo que se trabaja a fondo
> en verano (Pista A) frente a lo que se toca para cobertura (Pista B).

### 0486 · Acceso a Datos — **6/6 RA a fondo (Pista A)** 🟥

| RA | Tema | Bloque(s) | Ejercicios núcleo |
|---|---|---|---|
| RA1 | Ficheros | b26, b16 | 207–214, 143–148 |
| RA2 | BD relacionales (JDBC) + procedimientos | b11, b31 | 093–102, 249–250 |
| RA3 | ORM / JPA | b12, b13, b14, b15 | 103–142 |
| RA4 | BD objeto-relacionales/OO | b31 | 251–254 |
| RA5 | BD documentales (MongoDB) | b17 | 149–154 |
| RA6 | Componentes de acceso a datos (JavaBean) | b46 | 351–356 |

### 0490 · Programación de Servicios y Procesos — **5/5 RA a fondo (Pista A)** 🟥

| RA | Tema | Bloque(s) | Ejercicios núcleo |
|---|---|---|---|
| RA1 | Multiproceso / IPC | b28 | 227–232 |
| RA2 | Multihilo / concurrencia | b27 | 215–226 |
| RA3 | Sockets / red | b29 | 233–240 |
| RA4 | Servicios en red con protocolos estándar | b05, b06, b00 | 045–054, 001–010 *(track REST)* |
| RA5 | Programación segura (cripto + Security) | b30, b18 | 241–248, 155–164 |

### 0488 · Desarrollo de Interfaces — **8/8 RA tocados (Pista B + septiembre)** 🟦

| RA | Tema | Bloque(s) | Ejercicio bandera (cobertura) |
|---|---|---|---|
| RA1 | GUI con editores visuales (JavaFX/FXML) | b32, b33, b34 | 255, 271, 273 |
| RA2 | Interfaces **naturales** (voz/gestos/RA/ML) | b44 | 337, 338 |
| RA3 | Componentes visuales | b37 | 293 |
| RA4 | Usabilidad y accesibilidad | b36 | 290 |
| RA5 | Informes (JasperReports/PDF) | b38 | 299, 300 |
| RA6 | Documentación y ayuda | b39 | 306 |
| RA7 | Distribución e instaladores | b39 | 309, 310 |
| RA8 | Estrategia de pruebas | b47 | 357, 358 |

### 0489 · Programación Multimedia y Dispositivos Móviles — **5/5 RA tocados (Pista B + septiembre)** 🟦

| RA | Tema | Bloque(s) | Ejercicio bandera (cobertura) |
|---|---|---|---|
| RA1 | Entorno de desarrollo móvil | b42 | 325 |
| RA2 | Desarrollo de apps móviles | b42 | 326, 329 |
| RA3 | Contenidos multimedia (imagen/audio/vídeo) | b40 | 311, 312, 314 |
| RA4 | Motores de juego (2D y 3D) | b41, b45 | 320, 349 |
| RA5 | Juegos 2D y 3D sencillos | b41, b45 | 324, 345, 347, 350 |

### 0491 · Sistemas de Gestión Empresarial — **5/5 RA tocados (vertiente Java, Pista B)** 🟦

| RA | Tema | Bloque | Ejercicio bandera (cobertura) |
|---|---|---|---|
| RA1/RA2 | Identificar/implantar ERP-CRM *(guion + Odoo MCP)* | b43 | 331 |
| RA3 | Consulta y análisis (BI/KPIs) | b43 | 335 |
| RA4 | Adaptar ERP (integración) | b43 | 333, 336 |
| RA5 | Componentes para ERP (ETL) | b43 | 332, 334 |

> **Honestidad sobre SGE/PMDM/DI:** algunos CE son tarea de **herramienta** (parametrizar Odoo,
> desplegar en emulador Android real, entrenar un motor de voz). En la masterclass se modela en
> **Java puro** lo transferible y testeable, y el resto queda como **guion** documentado en la
> teoría. Para "ir con ventaja" a 2º es más que suficiente.

---

## 3. Cómo trabajar cada ejercicio (el ritual)

Flujo operativo (de `README_GUIA_TERMINAL.md` y `RUTA_ESTUDIO_2DAM.md`):

1. **Activa solo el bloque** (rendimiento del IDE):
   ```bash
   python bloque.py b27
   ```
2. **Lee la teoría primero**: `teoria/NN_*.md` (diagramas Mermaid, "Errores comunes", "Chuleta").
3. Abre el ejercicio, lee el **Javadoc** y los `// TODO 1..10:`.
4. Implementa. Corre el `main` (`*Playground`) para ver salida.
5. **Valida con el test** hasta verde:
   ```powershell
   mvn test -Dtest=Ej215ThreadRunnableTest
   ```
6. Marca el checkbox en `SYLLABUS.md` (§5 Progreso) **y** en este plan.
7. Antes de cualquier commit o `mvn test` global: `python bloque.py todos`.

**Ritmo diario sugerido (≈1.5 h/día, 5–6 días/semana):**

| Bloque de tiempo | Actividad |
|---|---|
| 15 min | Releer la sección de teoría del ejercicio de hoy + "Errores comunes" |
| 60 min | 2–3 ejercicios núcleo: TODOs → test verde |
| 15 min | Anotar dudas, marcar progreso, *si sobra:* 1 ejercicio de Pista B |

> Mejor **poco y constante** que atracones. Un día flojo: haz **1** ejercicio, pero hazlo.

---

## 4. CALENDARIO SEMANAL (9 semanas)

> Fechas 2026. **15-ago** (Asunción) cae en S7 → úsalo de colchón/repaso.
> En cada semana: **Pista A 🟥** es obligatoria; **"Si te sobra" 🟩** y **Pista B 🟦** son opcionales.

---

### 🗓️ SEMANA 1 · 1–7 jul · FASE 0 — Jav🟥 **Pista A — b01_java (núcleo):**
- [ ] 011 `Ej011Records` — `record` inmutable (tu DTO de toda la vida)
- [ ] 012 `Ej012OptionalSafeAccess` — `Optional` sin `NullPointerException`
- [ ] 013 `Ej013StreamsBasics` — `map`/`filter`/`collect`
- [ ] 014 `Ej014StreamsAdvanced` — `reduce`, `flatMap`, `Collectors`
- [ ] 015 `Ej015GenericsRepository` — genéricos y `T` acotados
- [ ] 017 `Ej017FunctionalInterfaces` — `Function`/`Predicate`/`Supplier`
- [ ] 019 `Ej019ExceptionsAndTryWith` — excepciones y try-with-resources
- [ ] 020 `Ej020DateTimeApi` — `java.time`
- [ ] 022 `Ej022EqualsHashCodeContracts` — contratos `equals`/`hashCode`

🟩 **Si te sobra:** 016 Wildcards · 018 Sealed + pattern matching · 021 ConcurrencyBasics
*(aperitivo de PSP; no lo exprimas, se profundiza en S7).*

✅ **Checkpoint:** sabes leer/escribir un `record`, encadenar `stream().filter().map().collect()` y
usar `Optional` sin NPE. **Entorno listo:** `bloque.py` y `mvn test` funcionando.

---

### 🗓️ SEMANA 2 · 8–14 jul · AD · RA1 — Ficheros
**Meta:** persistir y recuperar datos en ficheros (texto, binario, XML, CSV). Es el RA1 de Acceso a
Datos. · **Teoría:** `26_IO_Ficheros_NIO2.md`, `16_XML_y_Ficheros.md`

🟥 **Pista A — b26_io + b16_xml:**
- [ ] 207 `Ej207ByteStreams` — `InputStream`/`OutputStream`, copia con buffer, EOF
- [ ] 208 `Ej208CharStreams` — `Reader`/`Writer`, `Charset`, mojibake
- [ ] 209 `Ej209RandomAccessFile` — acceso aleatorio, `seek`, registros fijos
- [ ] 210 `Ej210ObjectSerialization` — `Serializable`, `transient`, grafos
- [ ] 211 `Ej211Nio2PathFiles` — `Path`/`Files`: crear, copiar, mover, borrar
- [ ] 212 `Ej212Nio2ReadWriteWalk` — `readAllLines`, `walk`/`find`, atributos
- [ ] 214 `Ej214FormatConversion` — texto↔binario, properties↔CSV, Base64/hex
- [ ] 143 `Ej143JaxbBinding` — JAXB objeto↔XML
- [ ] 144 `Ej144JacksonXml` — Jackson XML
- [ ] 148 `Ej148CsvImportExport` — import/export CSV

🟩 **Si te sobra:** 213 TempFiles/Channels · 145 DOM/SAX · 146 XmlEndpoint · 147 FileBackedRepository.
🟦 **Pista B (cobertura):** 311 `Ej311ImageLoadSave` (PMDM·RA3 — formatos por *magic number*) · 255 `Ej255AppLifecycle` (DI·RA1 — ciclo de vida JavaFX).

✅ **Checkpoint AD·RA1:** lees/escribes ficheros texto y binario, navegas con NIO.2, serializas
objetos, conviertes formatos y manejas XML/CSV.

---

### 🗓️ SEMANA 3 · 15–21 jul · AD · RA2 — JDBC + procedimientos
**Meta:** hablar con una base de datos relacional desde Java sin ORM: conexión, consultas seguras,
transacciones y procedimientos almacenados. · **Teoría:** `11_JDBC_Profundo.md`, `31_ObjetoRelacional_OO.md`

🟥 **Pista A — b11_jdbc + b31_oodb:**
- [ ] 093 `Ej093ConnectionDriverManager` — `Connection`, drivers
- [ ] 094 `Ej094StatementVsPrepared` — **inyección SQL**, `PreparedStatement`
- [ ] 095 `Ej095ResultSetMapping` — `ResultSet` → objeto
- [ ] 096 `Ej096CrudDao` — DAO CRUD con JDBC puro
- [ ] 097 `Ej097TransactionsCommitRollback` — transacciones manuales
- [ ] 099 `Ej099ConnectionPooling` — pool (HikariCP)
- [ ] 100 `Ej100JdbcTemplate` — `JdbcTemplate` de Spring
- [ ] 249 `Ej249CallableStatement` — `CallableStatement`, parámetros IN/OUT (RA2.k)
- [ ] 250 `Ej250StoredFunctionResult` — funciones almacenadas

🟩 **Si te sobra:** 098 Batch · 101 RowMapper · 102 NamedParameter · 251 ARRAY · 252 ObjectGraph ·
253 OQL · 254 TransactionsOnObjects *(estos 4 últimos = AD·RA4, ver S6)*.
🟦 **Pista B (cobertura):** 331 `Ej331ErpConcepts` (SGE·RA1 — modelo de datos de un ERP, glosario) · 325 `Ej325MobileEnvOverview` (PMDM·RA1 — entorno móvil).

✅ **Checkpoint AD·RA2:** CRUD con JDBC puro, `PreparedStatement` contra inyección, commit/rollback,
pool y procedimientos almacenados.

---

### 🗓️ SEMANA 4 · 22–28 jul · AD · RA3 — JPA core + relaciones
**Meta:** el corazón de Acceso a Datos. Mapear objetos a tablas con Hibernate/JPA y modelar
relaciones. · **Teoría:** `12_Spring_Data_JPA.md`, `13_Relaciones_JPA.md`

🟥 **Pista A — b12_jpa + b13_rel:**
- [ ] 103 `Ej103EntityMapping` — `@Entity`/`@Id`/`@Column`
- [ ] 104 `Ej104IdGenerationStrategies` — `@GeneratedValue`
- [ ] 105 `Ej105JpaRepository` — `JpaRepository` CRUD
- [ ] 106 `Ej106DerivedQueryMethods` — queries por nombre de método
- [ ] 107 `Ej107JpqlQueries` — `@Query` JPQL
- [ ] 110 `Ej110EntityLifecycleCallbacks` — `@PrePersist`/`@PreUpdate`
- [ ] 111 `Ej111EnumAndEmbeddable` — `@Enumerated`/`@Embeddable`
- [ ] 115 `Ej115OneToOne` — `@OneToOne`
- [ ] 116 `Ej116OneToManyManyToOne` — `@OneToMany`/`@ManyToOne`
- [ ] 117 `Ej117ManyToManyJoinTable` — `@ManyToMany`
- [ ] 120 `Ej120FetchLazyEager` — LAZY vs EAGER
- [ ] 121 `Ej121NPlusOneProblem` — diagnosticar/solucionar N+1

🟩 **Si te sobra:** 108 Native · 109 Modifying · 112 PersistenceContext · 113 equalsEntities ·
114 DtoProjection · 118 BidirectionalSync · 119 CascadeTypes · 122 JoinFetch/EntityGraph.
🟦 **Pista B (cobertura):** 312 `Ej312ImageFilters` (PMDM·RA3 — filtros por píxel) · 326 `Ej326ActivityLifecycle` (PMDM·RA2 — ciclo de vida Activity).

✅ **Checkpoint AD·RA3 (parte 1):** entidades, `JpaRepository`, JPQL, queries derivadas, relaciones
1:1/1:N/N:M, LAZY/EAGER y cómo cazar el N+1.

---

### 🗓️ SEMANA 5 · 29 jul–4 ago · AD · RA3 — JPA avanzado + consultas
**Meta:** transacciones serias, paginación y consultas dinámicas. Aquí marcas diferencia sobre el
resto de 2º. · **Teoría:** `14_JPA_Avanzado.md`, `15_Consultas_Avanzadas.md`

🟥 **Pista A — b14_jpaadv + b15_query:**
- [ ] 123 `Ej123TransactionPropagation` — propagación de transacciones
- [ ] 124 `Ej124IsolationLevels` — niveles de aislamiento
- [ ] 125 `Ej125OptimisticLocking` — `@Version`
- [ ] 128 `Ej128Auditing` — `@CreatedDate`/`@LastModifiedBy`
- [ ] 129 `Ej129SoftDelete` — borrado lógico
- [ ] 133 `Ej133Pagination` — `Pageable`/`Page`
- [ ] 134 `Ej134Sorting` — `Sort` multinivel
- [ ] 136 `Ej136DynamicFiltering` — filtros por query params
- [ ] 137 `Ej137Specifications` — `Specification` (Criteria)
- [ ] 141 `Ej141AggregationsGroupBy` — agregaciones y `GROUP BY`

🟩 **Si te sobra (maestría):** 126 Pessimistic · 127 SecondLevelCache · 130 Inheritance ·
131 FlushModes · 132 Flyway · 135 SliceVsPage · 138 CriteriaApi · 139 QueryByExample ·
140 InterfaceProjections · 142 KeysetPagination.
🟦 **Pista B (cobertura):** 314 `Ej314AudioPlayback` (PMDM·RA3 — máquina de estados del reproductor) · 299 `Ej299ReportDataModel` (DI·RA5 — JasperReports: modelo datos).

✅ **Checkpoint AD·RA3 (completo):** transacciones/aislamiento/locking, paginación, `Specification` y
agregaciones. Solo te quedan los dos RA cortos de AD.

---

### 🗓️ SEMANA 6 · 5–11 ago · AD · RA5 NoSQL + RA6 Componentes → **AD CERRADO 6/6** 🎯
**Meta:** rematar Acceso a Datos con MongoDB y el patrón de componente JavaBean. **Este es el módulo
de tu primer 10.** · **Teoría:** `17_NoSQL_MongoDB.md`, `46_Componentes_Datos.md`
**Docker:** `docker compose up -d mongo` (b17 lo necesita).

🟥 **Pista A — b17_nosql + b46_datacomp:**
- [ ] 149 `Ej149MongoDocumentMapping` — `@Document`/`@Id`
- [ ] 150 `Ej150MongoRepository` — `MongoRepository` CRUD
- [ ] 151 `Ej151MongoTemplateQueries` — `MongoTemplate`, `Query`
- [ ] 153 `Ej153AggregationPipeline` — pipeline de agregación
- [ ] 351 `Ej351BeanProperties` — JavaBean: propiedades por reflexión
- [ ] 352 `Ej352PropertyChangeEvents` — `PropertyChangeSupport` (eventos)
- [ ] 353 `Ej353ComponentSerialization` — serializar el componente, `transient`
- [ ] 354 `Ej354DataAccessComponent` — **componente DAO** reutilizable (núcleo del bloque)

🟩 **Si te sobra:** 152 EmbeddedVsReferences · 154 MongoRestEndpoint · 355 ComponentPackaging ·
356 ComponentIntegration · *recuperar pendientes de b31 (251–254) si los dejaste en S3*.
🟦 **Pista B (cobertura):** 332 `Ej332CsvXmlImportExport` (SGE·RA5 — ETL, import/export) · 329 `Ej329SensorsModel` (PMDM·RA2 — sensores en móvil) · 333 `Ej333ErpApiClient` (SGE·RA4 — cliente API ERP).

✅ **Checkpoint — ACCESO A DATOS GANADO (6/6 RA):** ficheros · JDBC+procedimientos · ORM/JPA ·
objeto-relacional · documental (Mongo) · componentes. **Módulo del primer 10 hecho.** 🟢

---

### 🗓️ SEMANA 7 · 12–18 ago · PSP · RA2 Concurrencia + RA1 Multiproceso
**Meta:** empezar el segundo módulo duro. Concurrencia es lo más abstracto de 2º y donde más gente
suspende: aquí está aterrizada en código que corre. *(15-ago festivo = colchón si te atrasas.)*
**Teoría:** `27_Concurrencia_Multihilo.md`, `28_Multiproceso_IPC.md`

🟥 **Pista A — b27_concur + b28_proc:**
- [ ] 215 `Ej215ThreadRunnable` — `Thread` vs `Runnable`, `start`/`join`
- [ ] 216 `Ej216ThreadStates` — estados del hilo, `sleep`, interrupción
- [ ] 217 `Ej217RaceConditionSynchronized` — **condición de carrera** y `synchronized`
- [ ] 218 `Ej218WaitNotify` — `wait`/`notify`: productor-consumidor
- [ ] 219 `Ej219ExecutorService` — `ExecutorService`, pools, `shutdown`
- [ ] 220 `Ej220CallableFuture` — `Callable`, `Future`, `get`/timeout
- [ ] 221 `Ej221Locks` — `ReentrantLock`, `ReadWriteLock`, `Condition`
- [ ] 222 `Ej222Semaphores` — `Semaphore`, `CountDownLatch`, `CyclicBarrier`
- [ ] 224 `Ej224DeadlockLivelock` — deadlock: provocar, evitar, detectar
- [ ] 227 `Ej227ProcessBuilderBasics` — `ProcessBuilder`, `waitFor`, exit code, PID
- [ ] 229 `Ej229ProcessPipesIPC` — IPC por pipes (stdin/stdout)
- [ ] 231 `Ej231ParallelProcesses` — procesos en paralelo, códigos de salida

🟩 **Si te sobra:** 223 Atomics/ConcurrentCollections · 225 CompletableFutureAdvanced ·
226 ThreadPriority/Context · 228 ProcessIO · 230 Timeout/Destroy · 232 ProcessEnvAndDir.
🟦 **Pista B (cobertura):** 320 `Ej320AnimationTimerLoop` (PMDM·RA4 — *game loop*, enlaza con tiempo/bucle) · 337 `Ej337NuiOverview` (DI·RA2 — pipeline NUI) · 293 `Ej293CustomControlCompose` (DI·RA3 — componente compuesto).

✅ **Checkpoint PSP·RA2+RA1:** entiendes una condición de carrera y sabes arreglarla; usas
`ExecutorService`/`Future`, locks y semáforos; provocas y evitas un deadlock; lanzas procesos y los
comunicas por pipes.

---

### 🗓️ SEMANA 8 · 19–25 ago · PSP · RA3 Sockets + RA5 Cripto
**Meta:** comunicación en red y programación segura. El servidor multicliente usa los hilos de la
semana pasada. · **Teoría:** `29_Sockets_Red.md`, `30_Criptografia_Seguridad.md`

🟥 **Pista A — b29_sockets + b30_crypto:**
- [ ] 233 `Ej233TcpEchoServer` — `ServerSocket`/`Socket`, eco TCP
- [ ] 234 `Ej234TcpClient` — cliente TCP, `setSoTimeout`, `ConnectException`
- [ ] 235 `Ej235MultiClientThreadedServer` — **multicliente: un hilo por conexión**
- [ ] 236 `Ej236ApplicationProtocol` — protocolo de aplicación propio (mini key-value)
- [ ] 237 `Ej237UdpDatagrams` — UDP: `DatagramSocket`/`DatagramPacket`
- [ ] 241 `Ej241Hashing` — `MessageDigest` (SHA-256), integridad
- [ ] 242 `Ej242PasswordHashingSalt` — salt + PBKDF2, contraste con BCrypt
- [ ] 243 `Ej243SymmetricAes` — cifrado simétrico AES (GCM/CBC, IV)
- [ ] 244 `Ej244AsymmetricRsa` — cifrado asimétrico RSA
- [ ] 245 `Ej245DigitalSignature` — firma digital, no repudio

🟩 **Si te sobra:** 238 ObjectOverSocket · 239 ServerWithThreadPool · 240 GracefulShutdown ·
246 HMAC/SecureRandom · 247 KeyStore · 248 TLS/SSLSocket.
🟦 **Pista B (cobertura):** 349 `Ej349GameEngineArchitecture` (PMDM·RA4 — mini-ECS) · 338 `Ej338VoiceCommandGrammar` (DI·RA2 — gramática de voz).

✅ **Checkpoint PSP·RA3+RA5(cripto):** levantas un servidor TCP que atiende a varios clientes a la
vez, defines un protocolo propio, usas UDP; ciframos, hasheamos y firmamos datos. **Solo te queda
cerrar RA4 (servicios en red) y RA5-Spring (Security), en S9.**

---

### 🗓️ SEMANA 9 · 26–31 ago · PSP RA4+RA5(Spring) → **PSP CERRADO 5/5** + repaso 🎯
**Meta:** cerrar PSP con servicios en red estándar (REST) y Spring Security, repasar lo de AD+PSP y
dejar todo verde. **Módulo de tu segundo 10.** · **Teoría:** `00_Fundamentos_HTTP_Web.md`,
`05_Controllers_REST.md`, `18_Seguridad_JWT.md`

🟥 **Pista A — cierre PSP (RA4 + RA5-Spring):**
- [ ] 001 `Ej001HttpRequestParser` — anatomía de la request (PSP·RA4)
- [ ] 004 `Ej004HttpMethodsSemantics` — idempotencia y seguridad de verbos
- [ ] 045 `Ej045HelloController` — `@RestController`/`@GetMapping`
- [ ] 048 `Ej048RequestBodyPost` — `@RequestBody, 201 Created`
- [ ] 053 `Ej053CrudInMemory` — CRUD REST completo (cierra **PSP·RA4**)
- [ ] 155 `Ej155SecurityFilterChain` — config Spring Security
- [ ] 157 `Ej157PasswordEncoder` — BCrypt
- [ ] 159 `Ej159JwtIssue` — emisión de JWT
- [ ] 161 `Ej161RoleBasedAccess` — roles, `@PreAuthorize` (cierra **PSP·RA5**)

🟥 **Repaso obligatorio (medio día):**
- [ ] `python bloque.py todos` && `mvn test` → **toda la suite AD+PSP en verde**
- [ ] Releer "Errores comunes" + "Autoevaluación" de las teorías 11, 12, 13, 27, 29, 30
- [ ] Rehacer de memoria **1 ejercicio bandera** por RA de AD y PSP (sin mirar la solución)

🟦 **Pista B (cobertura):** 290 `Ej290AccessibilityA11y` (DI·RA4) · 357 `Ej357TestStrategyPlan` (DI·RA8) · 335 `Ej335BiAggregations` (SGE·RA3) · 358 `Ej358RegressionBaseline` (DI·RA8 — regresión) · 350 `Ej350MiniGame3D` (PMDM·RA5 — mini-juego 3D).

✅ **Checkpoint — PSP GANADO (5/5 RA):** multiproceso · multihilo · sockets · servicios en red ·
seguridad. **Llegas a 2º con AD y PSP medio ganados y rumbo al doble 10.** 🟢🟢

---

## 5. Tablero de control del verano

| Semana | Fechas | Foco | RA que cierra | Núcleo (ej.) |
|---|---|---|---|---|
| 1 | 1–7 jul | Java base (b01) | — | 9 |
| 2 | 8–14 jul | AD ficheros (b26, b16) | AD·RA1 | 10 |
| 3 | 15–21 jul | AD JDBC (b11, b31) | AD·RA2 (+RA2.k) | 9 |
| 4 | 22–28 jul | AD JPA core (b12, b13) | AD·RA3 (½) | 12 |
| 5 | 29 jul–4 ago | AD JPA avanzado (b14, b15) | AD·RA3 (✓) | 10 |
| 6 | 5–11 ago | AD Mongo + componentes (b17, b46) | **AD·RA4/5/6 → 6/6** | 8 |
| 7 | 12–18 ago | PSP hilos + procesos (b27, b28) | PSP·RA1/RA2 | 12 |
| 8 | 19–25 ago | PSP sockets + cripto (b29, b30) | PSP·RA3/RA5 | 10 |
| 9 | 26–31 ago | PSP servicios+Security + repaso | **PSP·RA4/5 → 5/5** | 9 |
| | | | **TOTAL núcleo** | **~89** |

> El núcleo "obligatorio" son ~89 ejercicios (los más rentables de los 138). Los ~49 restantes de
> AD/PSP son 🟩 "si te sobra" — caen solos si vas con holgura. **Con el núcleo, AD y PSP están
> aprobados con buena nota; con el núcleo + "si te sobra", apuntas al 10.**

---

## 6. Track REST / Spring Boot (empleabilidad, secundario)

No está en el temario "puro" de ningún módulo, pero es **tu superpoder** para el Proyecto y la FCT, y
cubre de paso **PSP·RA4**. Ya tocas lo esencial en S9 (b00 + b05 + b18). Si quieres el track
completo, hazlo **después de cerrar AD+PSP** (septiembre) en orden numérico:

`b00 → b02 → b03 → b04 → b05 → b06 → b07 → b08 → b09 → b10` (API REST con validación, errores
RFC 7807, capas limpias) y luego `b19` (testing) · `b20` (OpenAPI/Actuator) · `b21` (caché/resiliencia)
· `b24` (**Boss Final**, integra todo).

---

## 7. Plan de continuación para septiembre (cobertura DI/PMDM/SGE)

Lo que la Pista B deja tocado pero no dominado, se remata durante el curso. Orden sugerido (cada uno
es ~1 semana ligera):

1. **DI · GUI (JavaFX):** b32 (255–262) → b33 (263–270) → b34 (271–278). *RA1.*
2. **DI · datos/estilo/componentes:** b35 (279–286) → b36 (287–292) → b37 (293–298). *RA2/3/4.*
3. **DI · informes/distribución/naturales/pruebas:** b38 (299–304) → b39 (305–310) → b44 (337–344) → b47 (357–362). *RA5/6/7/2/8.*
4. **PMDM · multimedia y juegos:** b40 (311–318) → b41 (319–324) → b45 (345–350) → b42 (325–330). *RA1–5.*
5. **SGE · ERP/CRM (Java + Odoo MCP):** b43 (331–336). *RA1–5.*

Al terminar esto, tienes los **5 módulos técnicos de 2º DAM cubiertos al 100 % del BOE 2023**.

---

## 8. Si vas justo (versión exprés irrenunciable)

Si una semana se tuerce, **nunca te saltes estos 8 bloques** (es el mínimo de `RUTA_ESTUDIO_2DAM.md`):

- **AD:** b26 (ficheros) · b11 (JDBC) · b12 + b13 (JPA y relaciones)
- **PSP:** b27 (concurrencia) · b29 (sockets) · b30 (cripto)
- **Java base:** b01

Con esos llegas a 2º habiendo tocado lo esencial de los dos módulos duros.

---

*Plan de estudio personal · generado el 2026-06-26 · cruza `SYLLABUS.md`, `ROADMAP_CIERRE_BOE2023.md`
y `RUTA_ESTUDIO_2DAM.md`. Marca tu progreso aquí y en `SYLLABUS.md §5`. Cuando lo tengas, dime cómo
quieres volcarlo al calendario y lo preparamos.*
