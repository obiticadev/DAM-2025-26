# ROADMAP · Bloques nuevos para cubrir 2º DAM (AD + PSP)

> Hoja de ruta para completar la masterclass `11_APIRESTMasterclass` de cara a 2º de DAM.
> Pensado para entregarse a un agente: cada bloque trae módulo·RA del BOE, tabla de
> ejercicios, anatomía exacta (10 TODOs + 10 retos extra + test espejo) y esquema de teoría,
> con el detalle necesario para construirlos con **la misma profundidad** que `b00`/`b01`.

## Contexto

La masterclass (206 ejercicios, bloques `b00`–`b25`) es un bootcamp de **API REST con
Java + Spring Boot**. Contrastada ejercicio por ejercicio contra el BOE (RD 405/2023,
`BOE-A-2023-13221.pdf`), cubre con sobresaliente la parte "web/datos" de 2º DAM pero deja
**agujeros reales** en los Resultados de Aprendizaje que no son de Spring:

| Módulo BOE | RA | Estado en la masterclass |
|---|---|---|
| **0486 Acceso a Datos** | RA2 JDBC, RA3 ORM/JPA, RA5 Mongo | ✅ Sobrado (b11–b17) |
| 0486 Acceso a Datos | RA1 Ficheros (I/O bajo nivel) | ⚠️ Solo binding XML/JSON (b02,b16); falta `java.io`, `RandomAccessFile`, serialización nativa, NIO.2 |
| 0486 Acceso a Datos | RA4 BD objeto-relacionales/OO (OQL) | ❌ No cubierto |
| 0486 Acceso a Datos | RA2 procedimientos almacenados | ⚠️ Falta `CallableStatement` |
| **0490 PSP** | RA4 Servicios en red | ✅ Sobrado (toda la masterclass) |
| 0490 PSP | RA1 Multiproceso (procesos, IPC) | ❌ No cubierto |
| 0490 PSP | RA2 Multihilo (concurrencia a fondo) | ❌ 1 ejercicio superficial (b01·Ej021) |
| 0490 PSP | RA3 Sockets (TCP/UDP) | ❌ No cubierto |
| 0490 PSP | RA5 Programación segura + criptografía | ⚠️ Solo JWT/BCrypt (b18); falta cripto real |

**Fuera de alcance a propósito** (otros módulos de 2º, no son de esta masterclass):
0488 Desarrollo de Interfaces (GUI escritorio), 0489 Programación Multimedia y Móviles,
0491 Sistemas de Gestión Empresarial. No se planifican aquí.

**Objetivo:** especificar **6 bloques nuevos** (`b26`–`b31`) que cierran los agujeros de AD
y PSP. Tras construirlos, AD y PSP quedan cubiertos al 100% del BOE.

---

## Convenciones obligatorias (heredadas de b00/b01) — válidas para TODOS los bloques

Un agente que cree un bloque DEBE replicar exactamente esta anatomía, verificada en
`b01_java/.../Ej013StreamsBasics.java` y su test espejo.

### 1. Anatomía de cada clase de ejercicio
- Paquete `com.masterclass.api.bNN_nombre`. Clase `public final`, **constructor privado**,
  todos los métodos `static`.
- Javadoc de clase con referencia a la sección de teoría: `Teoría: {@code teoria/NN_*.md} (sección X.Y)`.
- **2–3 métodos "core"** que reparten **exactamente 10 TODOs numerados** (`// TODO 1:` … `// TODO 10:`),
  granulares (validación, casos límite, pasos del algoritmo, retorno). Devuelven un
  **valor centinela** que hace fallar el test hasta implementarlo (`-1`, `List.of()`, `null`).
- Un `main(String[] args)` corto que demuestra los métodos core (sirve de Playground).
- **10 "Retos Extra"** (`retoExtra01`…`retoExtra10`): cada uno es un método con javadoc,
  un comentario `// GUÍA:` extenso (pista conceptual + snippet + trampa del test, estilo
  pedagógico de `Ej013`) y cuerpo `throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para <metodo>");`.

### 2. Test espejo (`src/test/.../EjNNNxxxTest.java`)
- Un `@Test` por método core (con casos límite reales) + 10 `@Test` `retoExtraNN_<metodo>`.
- JUnit 5 (`org.junit.jupiter`), asserts estáticos. Los tests nacen **en rojo** (centinela /
  excepción) y se ponen verdes al implementar. Para asincronía usar `assertTimeout`,
  `CountDownLatch.await(timeout)`, `Awaitility` o latches; nunca `Thread.sleep` a ciegas.

### 3. Teoría (`teoria/NN_Nombre.md`)
- Cabecera con cita motivadora ("vienes de…, esto te falta…").
- Sección "Cómo usar este documento" + tabla `| Sección | Tema | Ejercicio |`.
- Una sección `## N.M` por ejercicio, con prosa, bloques de código, **diagramas Mermaid**
  (`classDiagram`, `sequenceDiagram`, `stateDiagram-v2` para hilos/sockets) y recuadro final
  **"Lo practicas en `EjNNN…`"**.

### 4. Alta como módulo Maven
- Carpeta `bNN_nombre/` con su `pom.xml` propio (copiar el de un bloque hermano, p.ej.
  `b01_java/pom.xml`; cambiar `artifactId`). `src/main` y `src/test` espejo.
- **No** hace falta tocar `bloque.ps1`: detecta solo cualquier carpeta `^b\d{2}_` con `pom.xml`.
- Añadir el módulo a `<modules>` del `pom.xml` raíz solo al compilar todo (`.\bloque.ps1 todos`).
- Registrar el bloque en `SYLLABUS.md` (tabla de rangos + tabla detallada + checklist de progreso).
- Dependencias extra por bloque se declaran en el `pom.xml` del módulo (ver cada bloque).

### 5. Estilo
- Todo en **castellano**, comentarios incluidos. Codificación UTF-8. Nombres de método en
  español (como `nombresCarosEnMayus`, `filtrarMayoresDeEdad`).

---

## Bloques a crear (resumen y prioridad)

| Nuevo | Carpeta | Tema | Módulo·RA BOE | Ejercicios | Prioridad |
|---|---|---|---|---|---|
| B26 | `b26_io` | I/O de ficheros de bajo nivel y NIO.2 | AD·RA1 | 207–214 (8) | Alta |
| B27 | `b27_concur` | Concurrencia y multihilo a fondo | PSP·RA2 | 215–226 (12) | **Máxima** |
| B28 | `b28_proc` | Multiproceso e IPC | PSP·RA1 | 227–232 (6) | Media |
| B29 | `b29_sockets` | Sockets y programación en red | PSP·RA3 | 233–240 (8) | **Máxima** |
| B30 | `b30_crypto` | Criptografía y programación segura | PSP·RA5 / AD | 241–248 (8) | Alta |
| B31 | `b31_oodb` | BD objeto-relacionales/OO + procedimientos | AD·RA4 + RA2 | 249–254 (6) | **Opcional (baja)** |

Orden de construcción recomendado: **B27 → B29 → B30 → B26 → B28 → (B31)**
(primero lo que más pesa en PSP y más se suspende; B31 es cierre formal del BOE, opcional).

---

## B26 · `b26_io` — I/O de ficheros de bajo nivel y NIO.2  (AD·RA1)

**Cubre criterios de evaluación AD RA1:** a) clases de gestión de ficheros/directorios,
b) formas de acceso (secuencial vs aleatorio), c) recuperar info, d) almacenar info,
e) conversiones de formato, f) excepciones. (El binding XML/JSON ya está en b02/b16; aquí
va la base de `java.io`/`java.nio` que el examen de AD pide y la masterclass se salta.)

**pom.xml:** sin dependencias extra (JDK puro). **Teoría:** `teoria/26_IO_Ficheros_NIO2.md`.

| # | Archivo | Concepto clave |
|---|---|---|
| 207 | `Ej207ByteStreams.java` | `InputStream`/`OutputStream`, copia binaria con buffer |
| 208 | `Ej208CharStreams.java` | `Reader`/`Writer`, `BufferedReader`, encodings (`Charset`) |
| 209 | `Ej209RandomAccessFile.java` | Acceso aleatorio: `seek`, registros de tamaño fijo |
| 210 | `Ej210ObjectSerialization.java` | `Serializable`, `ObjectOutputStream`, `transient`, `serialVersionUID` |
| 211 | `Ej211Nio2PathFiles.java` | `Path`/`Files`: crear, copiar, mover, borrar, recorrer |
| 212 | `Ej212Nio2ReadWriteWalk.java` | `Files.readAllLines`, `write`, `walk`, `find`, `BasicFileAttributes` |
| 213 | `Ej213TempFilesAndChannels.java` | Ficheros temporales, `try-with-resources`, `FileChannel`/`ByteBuffer` |
| 214 | `Ej214FormatConversion.java` | Conversión entre formatos (texto↔binario, properties↔csv) |

**Detalle por ejercicio** (métodos core = 10 TODOs repartidos; 10 retos extra temáticos):
- **207:** core `copiarBinario(in,out)`, `leerTodosLosBytes(path)`. Retos: copia con buffer de
  N bytes, contar bytes, comparar dos ficheros, append vs overwrite, `try-with-resources`
  multinivel, detección de fin de stream (`-1`), `ByteArrayOutputStream` a memoria, etc.
- **208:** core `leerLineas(path,charset)`, `escribirTexto(path,texto,charset)`. Retos: UTF-8 vs
  ISO-8859-1 (mojibake), contar palabras, `BufferedReader.lines()`, BOM, etc.
- **209:** core `escribirRegistro(file,pos,reg)`, `leerRegistro(file,pos)`. Retos: índice por
  offset, actualizar en sitio, `length()`/`setLength`, lectura inversa, etc.
- **210:** core `serializar(obj,path)`, `deserializar(path)`. Retos: `transient` (campo no
  persistido), incompatibilidad de `serialVersionUID`, grafo de objetos, `Externalizable`, etc.
- **211–213:** NIO.2 — operaciones de `Files`, recorrido recursivo, atributos, temporales,
  canales/buffers. **214:** conversores texto↔binario y properties↔csv (enlaza con AD RA1.e).

**Teoría — secciones:** flujos de bytes vs caracteres (diagrama de jerarquía `java.io`),
secuencial vs aleatorio, serialización (estados de un objeto serializado), `java.io` vs
`java.nio.file`, encodings. Mermaid `classDiagram` para la jerarquía de streams.

---

## B27 · `b27_concur` — Concurrencia y multihilo a fondo  (PSP·RA2)  · PRIORIDAD MÁXIMA

**Cubre criterios PSP RA2:** a–k completos (crear/iniciar/finalizar hilos, estados,
compartir info entre hilos, sincronización, prioridades, librerías `java.util.concurrent`,
problemas de compartición). Es el RA donde más se suspende PSP y el que más diferencia.

**pom.xml:** JDK puro; en test añadir `org.awaitility:awaitility` (asíncrono determinista) y,
opcional, `org.junit.jupiter` `@Execution`. **Teoría:** `teoria/27_Concurrencia_Multihilo.md`.

| # | Archivo | Concepto clave |
|---|---|---|
| 215 | `Ej215ThreadRunnable.java` | `Thread` vs `Runnable`, `start` vs `run`, `join` |
| 216 | `Ej216ThreadStates.java` | Estados (`NEW`/`RUNNABLE`/`BLOCKED`/`WAITING`/`TIMED_WAITING`/`TERMINATED`), `sleep`, interrupción |
| 217 | `Ej217RaceConditionSynchronized.java` | Condición de carrera y `synchronized` (método/bloque, monitor) |
| 218 | `Ej218WaitNotify.java` | `wait`/`notify`/`notifyAll`: productor-consumidor clásico |
| 219 | `Ej219ExecutorService.java` | `ExecutorService`, pools, `submit`, `shutdown`/`awaitTermination` |
| 220 | `Ej220CallableFuture.java` | `Callable`, `Future`, `invokeAll`, recoger resultados |
| 221 | `Ej221Locks.java` | `ReentrantLock`, `tryLock`, `ReadWriteLock`, `Condition` |
| 222 | `Ej222Semaphores.java` | `Semaphore`, `CountDownLatch`, `CyclicBarrier` |
| 223 | `Ej223AtomicAndConcurrentCollections.java` | `AtomicInteger`/`LongAdder`, `ConcurrentHashMap`, `BlockingQueue` |
| 224 | `Ej224DeadlockLivelock.java` | Provocar y resolver deadlock (orden de locks), detección |
| 225 | `Ej225CompletableFutureAdvanced.java` | `thenApply`/`thenCompose`/`allOf`, composición async, timeouts |
| 226 | `Ej226ThreadPriorityAndContext.java` | Prioridades, `ThreadLocal`, `Daemon`, contexto de ejecución |

**Detalle representativo:**
- **217:** core `contadorSinSync(hilos,iter)` (demuestra el bug, resultado < esperado) y
  `contadorConSync(...)` (correcto). Retos: `synchronized` de bloque vs método, lock sobre
  `this` vs objeto dedicado, `volatile` vs `synchronized`, `AtomicInteger` como alternativa, etc.
- **218:** core productor-consumidor con buffer acotado usando `wait/notify`. Retos: `notifyAll`
  vs `notify`, spurious wakeups (bucle `while`), buffer lleno/vacío, etc.
- **219/220:** pools fijos/cacheados, `Future.get` con timeout, `invokeAll`/`invokeAny`,
  manejo de `ExecutionException`. **221:** `tryLock` para evitar bloqueo, `ReadWriteLock`
  (muchos lectores / un escritor), `Condition` en vez de `wait/notify`.
- **224:** core `provocarDeadlock()` (con timeout en test que confirma el bloqueo) y
  `evitarDeadlock()` (ordenando la adquisición de locks). Muy didáctico.

**Teoría:** `stateDiagram-v2` con los estados de un hilo; `sequenceDiagram` de
productor-consumidor; tabla "sincronización: `synchronized` vs `Lock` vs atómicos vs
colecciones concurrentes"; sección "los 3 pecados" (race, deadlock, visibilidad/`volatile`).
Enlazar con `b01·Ej021` (eleva aquel ejercicio mínimo) y con `b21·Ej184` (`@Async` Spring).

---

## B28 · `b28_proc` — Multiproceso e IPC  (PSP·RA1)

**Cubre criterios PSP RA1:** c) procesos y su ejecución por el SO, e) crear subprocesos,
f) compartir información con subprocesos, g) sincronizar y obtener el valor devuelto,
h) varias tareas en paralelo. (Procesos del SO, no hilos: `ProcessBuilder`/`Process`.)

**pom.xml:** JDK puro. **Teoría:** `teoria/28_Multiproceso_IPC.md`.
Nota de portabilidad: usar comandos disponibles en Windows y *nix, o un proceso Java hijo
(`java -cp ... Clase`) para que los tests sean deterministas en el homelab del alumno.

| # | Archivo | Concepto clave |
|---|---|---|
| 227 | `Ej227ProcessBuilderBasics.java` | Lanzar proceso, `start()`, `waitFor()`, exit code |
| 228 | `Ej228ProcessIO.java` | Redirección de `stdin`/`stdout`/`stderr`, leer salida |
| 229 | `Ej229ProcessPipesIPC.java` | Comunicación por pipes/streams entre procesos |
| 230 | `Ej230ProcessTimeoutAndDestroy.java` | `waitFor(timeout)`, `destroy`/`destroyForcibly`, `isAlive` |
| 231 | `Ej231ParallelProcesses.java` | Varios procesos en paralelo, recoger códigos de salida |
| 232 | `Ej232ProcessEnvAndDir.java` | `environment()`, directorio de trabajo, `inheritIO` |

**Detalle:** core típico `ejecutarYObtenerSalida(comando)` → captura stdout y exit code;
`ejecutarConTimeout(cmd,ms)`. Retos: separar stdout/stderr, exit code != 0 como error,
pasar variables de entorno, lanzar un `.jar`/clase Java hija que devuelva un valor por
stdout (canal de retorno), matar proceso colgado, lanzar N procesos y esperar a todos, etc.

**Teoría:** proceso vs hilo (tabla y `classDiagram`), estados y planificación de procesos,
`sequenceDiagram` padre↔hijo por pipes, advertencia de portabilidad/seguridad (no inyectar
comandos del usuario). Enlazar con B27 (hilos) para contrastar.

---

## B29 · `b29_sockets` — Sockets y programación en red  (PSP·RA3)  · PRIORIDAD MÁXIMA

**Cubre criterios PSP RA3:** d) concepto de socket/tipos, e) cliente que se comunica con
servidor, f) servidor en red, g) intercambio de información, i) hilos para varios clientes
simultáneos, j) modelos de comunicación. Cero en la masterclass actual.

**pom.xml:** JDK puro (`java.net`). **Teoría:** `teoria/29_Sockets_Red.md`.
Tests: levantar servidor en `localhost` con puerto efímero (`new ServerSocket(0)`), conectar
cliente en el mismo test, cerrar siempre; usar timeouts para no colgar la suite.

| # | Archivo | Concepto clave |
|---|---|---|
| 233 | `Ej233TcpEchoServer.java` | `ServerSocket`/`Socket`, eco TCP, streams de socket |
| 234 | `Ej234TcpClient.java` | Cliente TCP, enviar/recibir, `setSoTimeout` |
| 235 | `Ej235MultiClientThreadedServer.java` | Servidor multicliente con un hilo por conexión |
| 236 | `Ej236ApplicationProtocol.java` | Protocolo de aplicación propio (comandos línea a línea) |
| 237 | `Ej237UdpDatagrams.java` | `DatagramSocket`/`DatagramPacket`, UDP cliente/servidor |
| 238 | `Ej238ObjectOverSocket.java` | Enviar objetos serializados por socket (`ObjectStream`) |
| 239 | `Ej239ServerWithThreadPool.java` | Servidor con `ExecutorService` (límite de conexiones) |
| 240 | `Ej240GracefulShutdownAndTimeouts.java` | Cierre ordenado, timeouts, manejo de desconexión |

**Detalle:**
- **233/234:** core `arrancarEco(puerto)` + `enviarYRecibir(host,puerto,msg)`. Retos: eco en
  mayúsculas, longitud del mensaje, varios mensajes en la misma conexión, cierre por parte
  del cliente, `BufferedReader`/`PrintWriter` sobre el socket, etc.
- **235:** un hilo por cliente (`new Thread(() -> atender(socket))`), demostrar 2 clientes a la vez.
- **236:** protocolo tipo `PUT clave valor` / `GET clave` / `QUIT` sobre un mapa en memoria
  (mini key-value server) — integra B27 (mapa concurrente) y prepara el salto mental a REST.
- **237:** UDP (sin conexión, sin garantía de orden), contrastar con TCP. **239:** pool en vez
  de hilo-por-cliente (enlaza B27). **240:** `SO_TIMEOUT`, `try-with-resources` sobre sockets.

**Teoría:** modelo cliente-servidor, pila TCP/IP mínima, `sequenceDiagram` del handshake
lógico petición/respuesta, TCP vs UDP (tabla), `stateDiagram` del ciclo de vida del servidor.
**Puente conceptual explícito:** "un endpoint REST es esto + HTTP + Spring por encima" — conecta
con `b00` (HTTP) y `b05` (controllers).

---

## B30 · `b30_crypto` — Criptografía y programación segura  (PSP·RA5 / refuerzo AD)  · PRIORIDAD ALTA

**Cubre criterios PSP RA5:** a) programación segura, b) técnicas criptográficas, e) algoritmos
criptográficos para proteger info almacenada, f) asegurar info transmitida, g) comunicaciones
seguras. (b18 cubre *autenticación* JWT/roles; aquí va la *criptografía* que falta.)

**pom.xml:** JDK puro (`java.security`, `javax.crypto`). **Teoría:** `teoria/30_Criptografia_Seguridad.md`.

| # | Archivo | Concepto clave |
|---|---|---|
| 241 | `Ej241Hashing.java` | `MessageDigest` (SHA-256), integridad, por qué no para contraseñas |
| 242 | `Ej242PasswordHashingSalt.java` | Salt + hashing lento (PBKDF2), contraste con BCrypt de b18 |
| 243 | `Ej243SymmetricAes.java` | Cifrado simétrico AES (`Cipher`, modos, IV) |
| 244 | `Ej244AsymmetricRsa.java` | Cifrado asimétrico RSA, `KeyPairGenerator`, pública/privada |
| 245 | `Ej245DigitalSignature.java` | Firma digital (`Signature`), autenticidad + no repudio |
| 246 | `Ej246HmacAndSecureRandom.java` | HMAC (integridad con clave), `SecureRandom`, tokens |
| 247 | `Ej247KeyStore.java` | `KeyStore`: guardar claves/certificados de forma segura |
| 248 | `Ej248TlsSecureChannel.java` | Canal seguro TLS (`SSLSocket`) / verificación de certificado |

**Detalle:**
- **241:** core `sha256Hex(texto)`, `verificarIntegridad(datos,hashEsperado)`. Retos: comparar
  archivos por hash, efecto avalancha, MD5 vs SHA (por qué MD5 está roto), etc.
- **242:** salt aleatorio + PBKDF2; reto: misma contraseña → hashes distintos por el salt; enlaza
  con `b18·Ej157` (BCrypt) explicando la diferencia.
- **243:** AES con clave + IV; cifrar/descifrar texto; reto: mismo texto+IV distinto → cifrado
  distinto, modo ECB inseguro vs CBC/GCM. **244:** cifrar con pública, descifrar con privada.
- **245:** firmar con privada, verificar con pública. **248:** `SSLSocket` cliente contra un
  endpoint TLS, o servidor TLS local con keystore autofirmado.

**Teoría:** simétrico vs asimétrico (`sequenceDiagram` del intercambio), hash vs cifrado vs
firma (tabla), por qué TLS combina ambos, "los pecados de la cripto casera". Enlazar con
`b18` (seguridad Spring) y B29 (sockets → sockets seguros).

---

## B31 · `b31_oodb` — BD objeto-relacionales/OO y procedimientos almacenados  (AD·RA4 + RA2)  · OPCIONAL (PRIORIDAD BAJA)

> **Opcional.** Cierra el último criterio del BOE pero cubre tecnología marginal en la
> industria. Constrúyelo solo si quieres cobertura del 100% formal; no es prioritario.

**Cubre criterios AD RA4** (a–h: BD que almacenan objetos, persistencia de objetos
simples/estructurados, consultas, transacciones) **y AD RA2.k** (procedimientos almacenados).
RA arcaico pero presente en el BOE; se aborda de forma didáctica y honesta (decir al alumno
que en la industria pesa poco, pero entra en examen).

**pom.xml:** H2 (ya usado en tests del proyecto) para tipos objeto-relacionales y procedimientos;
opcional una OODB embebida/didáctica. **Teoría:** `teoria/31_ObjetoRelacional_OO.md`.

| # | Archivo | Concepto clave |
|---|---|---|
| 249 | `Ej249CallableStatement.java` | Procedimientos/funciones almacenados con `CallableStatement`, parámetros IN/OUT |
| 250 | `Ej250StoredFunctionResult.java` | Funciones que devuelven valor/result set |
| 251 | `Ej251ObjectRelationalTypes.java` | Tipos estructurados/`STRUCT`/`ARRAY` objeto-relacionales |
| 252 | `Ej252PersistObjectGraph.java` | Persistencia de objetos simples y estructurados |
| 253 | `Ej253OqlStyleQueries.java` | Consultas estilo OQL sobre objetos persistidos (didáctico) |
| 254 | `Ej254TransactionsOnObjects.java` | Transacciones sobre objetos, commit/rollback |

**Detalle:** core en 249 = declarar/llamar un `PROCEDURE` H2 con parámetro de salida y
recoger el resultado; retos: parámetro IN/OUT/INOUT, función con `RETURN`, procedimiento que
devuelve cursor, manejo de `SQLException`, transacción alrededor de la llamada, etc. 251–253
modelan persistencia de objetos y consultas tipo OQL de forma comparada con JPA (ya conocido),
para que el alumno vea el contraste. Enlaza con `b11` (JDBC) y `b12` (JPA).

**Nota honesta para la teoría:** dejar escrito que RA4 (BD OO puras / OQL) es marginal en la
industria actual; el objetivo es aprobar el criterio del BOE y entender el "desfase
objeto-relacional", no dominar una tecnología muerta.

---

## Resultado de cobertura tras los bloques

| Módulo | RA | Antes | Después |
|---|---|---|---|
| AD 0486 | RA1 ficheros | ⚠️ | ✅ (b02/b16 + **b26**) |
| AD 0486 | RA2 JDBC + procedimientos | ⚠️ | ✅ (b11 + **b31**) |
| AD 0486 | RA3 ORM | ✅ | ✅ |
| AD 0486 | RA4 BD OO/OQL | ❌ | ✅ (**b31** opcional) |
| AD 0486 | RA5 documentales | ✅ | ✅ |
| PSP 0490 | RA1 multiproceso | ❌ | ✅ (**b28**) |
| PSP 0490 | RA2 multihilo | ❌ | ✅ (**b27**) |
| PSP 0490 | RA3 sockets | ❌ | ✅ (**b29**) |
| PSP 0490 | RA4 servicios red | ✅ | ✅ |
| PSP 0490 | RA5 seguridad/cripto | ⚠️ | ✅ (b18 + **b30**) |

Con B26–B30, PSP queda al 100% y AD a falta solo del RA4 arcaico (que cierra B31, opcional).
DI, PMDM y SGE siguen fuera de alcance por diseño.

---

## Verificación al construir cada bloque

1. `.\bloque.ps1 bNN` → `mvn -pl bNN_nombre test` compila y deja los tests **en rojo**
   (centinela / `UnsupportedOperationException`); tras implementar, en **verde**.
2. `bloque.ps1` (sin args) lista el nuevo bloque como detectado automáticamente.
3. Cada bloque cumple la anatomía: 2–3 core con 10 TODOs + 10 retos extra + test espejo + teoría con Mermaid.

## Notas de ejecución

- La construcción de cada bloque se hará idealmente con la skill `mejorar-bloque` /
  `Prompt_Generador_Masterclass`, pasándole la sección correspondiente de este roadmap.
- Tras crear un bloque, actualizar `SYLLABUS.md` (rangos + tabla detallada + checklist).
