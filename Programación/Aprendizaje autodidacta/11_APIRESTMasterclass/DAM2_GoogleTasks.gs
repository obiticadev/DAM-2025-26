/****************************************************************************************
 *  DAM2 · Generador de checklist en Google Tasks (aparece en Google Calendar)
 *  ---------------------------------------------------------------------------------
 *  Crea una lista de tareas llamada "DAM2" y mete CADA ejercicio del PLAN_VERANO_2DAM
 *  como una TAREA con casilla de check, fecha (su semana del plan) y metadatos en las
 *  notas (módulo, RA, bloque, archivo, teoría, comando de test).
 *
 *  POR QUÉ Google Tasks y no eventos de Calendar:
 *  un evento de Calendar NO se puede "marcar como hecho"; una tarea de Google Tasks SÍ,
 *  y las tareas se ven dentro de Google Calendar (panel lateral + en la rejilla por fecha).
 *  La lista se llama "DAM2", como pediste.
 *
 *  CÓMO USARLO (5 minutos, una sola vez):
 *   1. Entra en https://script.google.com  (con tu cuenta de Google).
 *   2. "Nuevo proyecto". Borra el código de ejemplo y pega TODO este archivo.
 *   3. Menú izquierdo "Servicios" (icono +)  ->  añade "Tasks API"  ->  Añadir.
 *   4. Arriba, selecciona la función  crearChecklistDAM2  y pulsa "Ejecutar".
 *   5. Google te pedirá autorizar (es tu cuenta, tu consentimiento). Acepta.
 *   6. Abre Google Calendar o Google Tasks: verás la lista "DAM2" con todo marcable.
 *
 *  RE-EJECUTAR: si la lista "DAM2" ya existe, el script NO duplica (sale avisando).
 *  Para empezar de cero, borra la lista "DAM2" en Google Tasks y vuelve a ejecutar.
 *
 *  AJUSTES rápidos (constantes de abajo):
 *   - PONER_FECHAS: false -> todas las tareas sin fecha (solo checklist).
 *   - DIAS_ESTUDIO: en cuántos días de cada semana repartir los ejercicios.
 ****************************************************************************************/

// ----------------------------- AJUSTES -----------------------------
const NOMBRE_LISTA  = 'DAM2';
const PONER_FECHAS  = true;   // las tareas con fecha aparecen en la rejilla de Calendar
const DIAS_ESTUDIO  = 7;      // en cuántos días repartir cada semana (7 = todos, sin descanso)

// Emoji por prioridad (P0 núcleo obligatorio AD/PSP, P1 cobertura RA, P2 maestría)
const EMOJI = { P0: '🟥', P1: '🟦', P2: '🟩' };

/****************************************************************************************
 *  DATOS DEL PLAN  (9 semanas, jul-ago 2026). Cada item:
 *  ej(codigo, archivo, bloque, modulo, ra, prioridad, concepto)
 *  El comando de test se deduce del nombre del archivo (clase espejo + "Test").
 ****************************************************************************************/
function ej(codigo, archivo, bloque, modulo, ra, prioridad, concepto) {
  return { codigo, archivo, bloque, modulo, ra, prioridad, concepto };
}

function PLAN() {
  return [
    // ---------------- SEMANA 1 · 1-7 jul · Java moderno (base) ----------------
    { inicio: '2026-07-01', titulo: 'S1 · Java moderno (base)', teoria: 'teoria/01_Java_Moderno_para_APIs.md', items: [
      ej('011','b01_java/Ej011Records.java','b01_java','Java base','—','P0','record inmutable como DTO'),
      ej('012','b01_java/Ej012OptionalSafeAccess.java','b01_java','Java base','—','P0','Optional sin NullPointer'),
      ej('013','b01_java/Ej013StreamsBasics.java','b01_java','Java base','—','P0','map/filter/collect'),
      ej('014','b01_java/Ej014StreamsAdvanced.java','b01_java','Java base','—','P0','reduce, flatMap, Collectors'),
      ej('015','b01_java/Ej015GenericsRepository.java','b01_java','Java base','—','P0','genéricos y T acotados'),
      ej('017','b01_java/Ej017FunctionalInterfaces.java','b01_java','Java base','—','P0','Function/Predicate/Supplier'),
      ej('019','b01_java/Ej019ExceptionsAndTryWith.java','b01_java','Java base','—','P0','excepciones y try-with-resources'),
      ej('020','b01_java/Ej020DateTimeApi.java','b01_java','Java base','—','P0','java.time'),
      ej('022','b01_java/Ej022EqualsHashCodeContracts.java','b01_java','Java base','—','P0','contratos equals/hashCode'),
      ej('016','b01_java/Ej016WildcardsVariance.java','b01_java','Java base','—','P2','? extends / ? super'),
      ej('018','b01_java/Ej018SealedPatternMatching.java','b01_java','Java base','—','P2','sealed + switch patrones'),
      ej('021','b01_java/Ej021ConcurrencyBasics.java','b01_java','Java base','—','P2','aperitivo de PSP'),
    ]},
    // ---------------- SEMANA 2 · 8-14 jul · AD RA1 ficheros ----------------
    { inicio: '2026-07-08', titulo: 'S2 · AD RA1 ficheros', teoria: 'teoria/26_IO_Ficheros_NIO2.md', items: [
      ej('207','b26_io/Ej207ByteStreams.java','b26_io','AD (0486)','RA1','P0','InputStream/OutputStream, EOF'),
      ej('208','b26_io/Ej208CharStreams.java','b26_io','AD (0486)','RA1','P0','Reader/Writer, Charset'),
      ej('209','b26_io/Ej209RandomAccessFile.java','b26_io','AD (0486)','RA1','P0','acceso aleatorio, seek'),
      ej('210','b26_io/Ej210ObjectSerialization.java','b26_io','AD (0486)','RA1','P0','Serializable, transient'),
      ej('211','b26_io/Ej211Nio2PathFiles.java','b26_io','AD (0486)','RA1','P0','Path/Files: CRUD de ficheros'),
      ej('212','b26_io/Ej212Nio2ReadWriteWalk.java','b26_io','AD (0486)','RA1','P0','readAllLines, walk/find'),
      ej('214','b26_io/Ej214FormatConversion.java','b26_io','AD (0486)','RA1','P0','texto<->binario, Base64/hex'),
      ej('143','b16_xml/Ej143JaxbBinding.java','b16_xml','AD (0486)','RA1','P0','JAXB objeto<->XML'),
      ej('144','b16_xml/Ej144JacksonXml.java','b16_xml','AD (0486)','RA1','P0','Jackson XML'),
      ej('148','b16_xml/Ej148CsvImportExport.java','b16_xml','AD (0486)','RA1','P0','import/export CSV'),
      ej('213','b26_io/Ej213TempFilesAndChannels.java','b26_io','AD (0486)','RA1','P2','FileChannel/ByteBuffer'),
      ej('145','b16_xml/Ej145DomSaxParsing.java','b16_xml','AD (0486)','RA1','P2','DOM vs SAX'),
      ej('146','b16_xml/Ej146XmlEndpoint.java','b16_xml','AD (0486)','RA1','P2','endpoint que produce XML'),
      ej('147','b16_xml/Ej147FileBackedRepository.java','b16_xml','AD (0486)','RA1','P2','repo persistido en fichero'),
      ej('311','b40_media/Ej311ImageLoadSave.java','b40_media','PMDM (0489)','RA3','P1','[cobertura] formatos por magic number'),
      ej('255','b32_fxbase/Ej255AppLifecycle.java','b32_fxbase','DI (0488)','RA1','P1','[cobertura] ciclo de vida JavaFX'),
    ]},
    // ---------------- SEMANA 3 · 15-21 jul · AD RA2 JDBC ----------------
    { inicio: '2026-07-15', titulo: 'S3 · AD RA2 JDBC + procedimientos', teoria: 'teoria/11_JDBC_Profundo.md', items: [
      ej('093','b11_jdbc/Ej093ConnectionDriverManager.java','b11_jdbc','AD (0486)','RA2','P0','Connection, drivers'),
      ej('094','b11_jdbc/Ej094StatementVsPrepared.java','b11_jdbc','AD (0486)','RA2','P0','inyección SQL, PreparedStatement'),
      ej('095','b11_jdbc/Ej095ResultSetMapping.java','b11_jdbc','AD (0486)','RA2','P0','ResultSet -> objeto'),
      ej('096','b11_jdbc/Ej096CrudDao.java','b11_jdbc','AD (0486)','RA2','P0','DAO CRUD con JDBC puro'),
      ej('097','b11_jdbc/Ej097TransactionsCommitRollback.java','b11_jdbc','AD (0486)','RA2','P0','transacciones manuales'),
      ej('099','b11_jdbc/Ej099ConnectionPooling.java','b11_jdbc','AD (0486)','RA2','P0','pool (HikariCP)'),
      ej('100','b11_jdbc/Ej100JdbcTemplate.java','b11_jdbc','AD (0486)','RA2','P0','JdbcTemplate de Spring'),
      ej('249','b31_oodb/Ej249CallableStatement.java','b31_oodb','AD (0486)','RA2.k','P0','CallableStatement IN/OUT'),
      ej('250','b31_oodb/Ej250StoredFunctionResult.java','b31_oodb','AD (0486)','RA2.k','P0','funciones almacenadas'),
      ej('098','b11_jdbc/Ej098BatchOperations.java','b11_jdbc','AD (0486)','RA2','P2','inserción por lotes'),
      ej('101','b11_jdbc/Ej101RowMapperAndExtractor.java','b11_jdbc','AD (0486)','RA2','P2','RowMapper/ResultSetExtractor'),
      ej('102','b11_jdbc/Ej102NamedParameterJdbc.java','b11_jdbc','AD (0486)','RA2','P2','NamedParameterJdbcTemplate'),
      ej('251','b31_oodb/Ej251ObjectRelationalTypes.java','b31_oodb','AD (0486)','RA4','P2','tipos ARRAY (objeto-relacional)'),
      ej('252','b31_oodb/Ej252PersistObjectGraph.java','b31_oodb','AD (0486)','RA4','P2','persistir grafo de objetos'),
      ej('253','b31_oodb/Ej253OqlStyleQueries.java','b31_oodb','AD (0486)','RA4','P2','consultas estilo OQL'),
      ej('254','b31_oodb/Ej254TransactionsOnObjects.java','b31_oodb','AD (0486)','RA4','P2','transacciones sobre objetos'),
      ej('331','b43_erp/Ej331ErpConcepts.java','b43_erp','SGE (0491)','RA1','P1','[cobertura] modelo de datos del ERP'),
      ej('325','b42_mobile/Ej325MobileEnvOverview.java','b42_mobile','PMDM (0489)','RA1','P1','[cobertura] entorno de desarrollo móvil'),
    ]},
    // ---------------- SEMANA 4 · 22-28 jul · AD RA3 JPA core ----------------
    { inicio: '2026-07-22', titulo: 'S4 · AD RA3 JPA core + relaciones', teoria: 'teoria/12_Spring_Data_JPA.md', items: [
      ej('103','b12_jpa/Ej103EntityMapping.java','b12_jpa','AD (0486)','RA3','P0','@Entity/@Id/@Column'),
      ej('104','b12_jpa/Ej104IdGenerationStrategies.java','b12_jpa','AD (0486)','RA3','P0','@GeneratedValue'),
      ej('105','b12_jpa/Ej105JpaRepository.java','b12_jpa','AD (0486)','RA3','P0','JpaRepository CRUD'),
      ej('106','b12_jpa/Ej106DerivedQueryMethods.java','b12_jpa','AD (0486)','RA3','P0','queries por nombre de método'),
      ej('107','b12_jpa/Ej107JpqlQueries.java','b12_jpa','AD (0486)','RA3','P0','@Query JPQL'),
      ej('110','b12_jpa/Ej110EntityLifecycleCallbacks.java','b12_jpa','AD (0486)','RA3','P0','@PrePersist/@PreUpdate'),
      ej('111','b12_jpa/Ej111EnumAndEmbeddable.java','b12_jpa','AD (0486)','RA3','P0','@Enumerated/@Embeddable'),
      ej('115','b13_rel/Ej115OneToOne.java','b13_rel','AD (0486)','RA3','P0','@OneToOne'),
      ej('116','b13_rel/Ej116OneToManyManyToOne.java','b13_rel','AD (0486)','RA3','P0','@OneToMany/@ManyToOne'),
      ej('117','b13_rel/Ej117ManyToManyJoinTable.java','b13_rel','AD (0486)','RA3','P0','@ManyToMany'),
      ej('120','b13_rel/Ej120FetchLazyEager.java','b13_rel','AD (0486)','RA3','P0','LAZY vs EAGER'),
      ej('121','b13_rel/Ej121NPlusOneProblem.java','b13_rel','AD (0486)','RA3','P0','diagnosticar N+1'),
      ej('108','b12_jpa/Ej108NativeQueries.java','b12_jpa','AD (0486)','RA3','P2','SQL nativo'),
      ej('109','b12_jpa/Ej109ModifyingQueries.java','b12_jpa','AD (0486)','RA3','P2','@Modifying update/delete'),
      ej('112','b12_jpa/Ej112PersistenceContext.java','b12_jpa','AD (0486)','RA3','P2','contexto de persistencia, flush'),
      ej('113','b12_jpa/Ej113EqualsHashCodeEntities.java','b12_jpa','AD (0486)','RA3','P2','identidad de entidades'),
      ej('114','b12_jpa/Ej114DtoConstructorProjection.java','b12_jpa','AD (0486)','RA3','P2','proyección por constructor'),
      ej('118','b13_rel/Ej118BidirectionalSync.java','b13_rel','AD (0486)','RA3','P2','sincronizar lados de relación'),
      ej('119','b13_rel/Ej119CascadeTypes.java','b13_rel','AD (0486)','RA3','P2','cascade y orfandad'),
      ej('122','b13_rel/Ej122JoinFetchAndEntityGraph.java','b13_rel','AD (0486)','RA3','P2','JOIN FETCH, EntityGraph'),
      ej('312','b40_media/Ej312ImageFilters.java','b40_media','PMDM (0489)','RA3','P1','[cobertura] filtros por píxel'),
      ej('326','b42_mobile/Ej326ActivityLifecycle.java','b42_mobile','PMDM (0489)','RA2','P1','[cobertura] ciclo de vida Activity'),
    ]},
    // ---------------- SEMANA 5 · 29 jul-4 ago · AD RA3 JPA avanzado ----------------
    { inicio: '2026-07-29', titulo: 'S5 · AD RA3 JPA avanzado + consultas', teoria: 'teoria/14_JPA_Avanzado.md', items: [
      ej('123','b14_jpaadv/Ej123TransactionPropagation.java','b14_jpaadv','AD (0486)','RA3','P0','propagación de transacciones'),
      ej('124','b14_jpaadv/Ej124IsolationLevels.java','b14_jpaadv','AD (0486)','RA3','P0','niveles de aislamiento'),
      ej('125','b14_jpaadv/Ej125OptimisticLocking.java','b14_jpaadv','AD (0486)','RA3','P0','@Version'),
      ej('128','b14_jpaadv/Ej128Auditing.java','b14_jpaadv','AD (0486)','RA3','P0','@CreatedDate/@LastModifiedBy'),
      ej('129','b14_jpaadv/Ej129SoftDelete.java','b14_jpaadv','AD (0486)','RA3','P0','borrado lógico'),
      ej('133','b15_query/Ej133Pagination.java','b15_query','AD (0486)','RA3','P0','Pageable/Page'),
      ej('134','b15_query/Ej134Sorting.java','b15_query','AD (0486)','RA3','P0','Sort multinivel'),
      ej('136','b15_query/Ej136DynamicFiltering.java','b15_query','AD (0486)','RA3','P0','filtros por query params'),
      ej('137','b15_query/Ej137Specifications.java','b15_query','AD (0486)','RA3','P0','Specification (Criteria)'),
      ej('141','b15_query/Ej141AggregationsGroupBy.java','b15_query','AD (0486)','RA3','P0','agregaciones y GROUP BY'),
      ej('126','b14_jpaadv/Ej126PessimisticLocking.java','b14_jpaadv','AD (0486)','RA3','P2','LockModeType'),
      ej('127','b14_jpaadv/Ej127SecondLevelCache.java','b14_jpaadv','AD (0486)','RA3','P2','caché de 2º nivel'),
      ej('130','b14_jpaadv/Ej130InheritanceStrategies.java','b14_jpaadv','AD (0486)','RA3','P2','herencia de entidades'),
      ej('131','b14_jpaadv/Ej131FlushModesBatching.java','b14_jpaadv','AD (0486)','RA3','P2','flush, batch JPA'),
      ej('132','b14_jpaadv/Ej132FlywayMigrations.java','b14_jpaadv','AD (0486)','RA3','P2','migraciones de esquema'),
      ej('135','b15_query/Ej135SliceVsPage.java','b15_query','AD (0486)','RA3','P2','Slice vs Page'),
      ej('138','b15_query/Ej138CriteriaApi.java','b15_query','AD (0486)','RA3','P2','Criteria API tipada'),
      ej('139','b15_query/Ej139QueryByExample.java','b15_query','AD (0486)','RA3','P2','Query by Example'),
      ej('140','b15_query/Ej140InterfaceProjections.java','b15_query','AD (0486)','RA3','P2','proyecciones por interfaz'),
      ej('142','b15_query/Ej142KeysetPagination.java','b15_query','AD (0486)','RA3','P2','paginación por cursor'),
      ej('314','b40_media/Ej314AudioPlayback.java','b40_media','PMDM (0489)','RA3','P1','[cobertura] máquina de estados del reproductor'),
      ej('299','b38_fxreports/Ej299ReportDataModel.java','b38_fxreports','DI (0488)','RA5','P1','[cobertura] JasperReports: modelo datos'),
    ]},
    // ---------------- SEMANA 6 · 5-11 ago · AD RA5 Mongo + RA6 componentes ----------------
    { inicio: '2026-08-05', titulo: 'S6 · AD RA5 Mongo + RA6 componentes (AD 6/6)', teoria: 'teoria/17_NoSQL_MongoDB.md', items: [
      ej('149','b17_nosql/Ej149MongoDocumentMapping.java','b17_nosql','AD (0486)','RA5','P0','@Document/@Id'),
      ej('150','b17_nosql/Ej150MongoRepository.java','b17_nosql','AD (0486)','RA5','P0','MongoRepository CRUD'),
      ej('151','b17_nosql/Ej151MongoTemplateQueries.java','b17_nosql','AD (0486)','RA5','P0','MongoTemplate, Query'),
      ej('153','b17_nosql/Ej153AggregationPipeline.java','b17_nosql','AD (0486)','RA5','P0','pipeline de agregación'),
      ej('351','b46_datacomp/Ej351BeanProperties.java','b46_datacomp','AD (0486)','RA6','P0','JavaBean: propiedades por reflexión'),
      ej('352','b46_datacomp/Ej352PropertyChangeEvents.java','b46_datacomp','AD (0486)','RA6','P0','PropertyChangeSupport (eventos)'),
      ej('353','b46_datacomp/Ej353ComponentSerialization.java','b46_datacomp','AD (0486)','RA6','P0','serializar componente, transient'),
      ej('354','b46_datacomp/Ej354DataAccessComponent.java','b46_datacomp','AD (0486)','RA6','P0','componente DAO reutilizable (núcleo)'),
      ej('152','b17_nosql/Ej152EmbeddedVsReferences.java','b17_nosql','AD (0486)','RA5','P2','embebido vs referencias'),
      ej('154','b17_nosql/Ej154MongoRestEndpoint.java','b17_nosql','AD (0486)','RA5','P2','API REST sobre Mongo'),
      ej('355','b46_datacomp/Ej355ComponentPackaging.java','b46_datacomp','AD (0486)','RA6','P2','empaquetar como JAR (Manifest)'),
      ej('356','b46_datacomp/Ej356ComponentIntegration.java','b46_datacomp','AD (0486)','RA6','P2','integrar el componente'),
      ej('332','b43_erp/Ej332CsvXmlImportExport.java','b43_erp','SGE (0491)','RA5','P1','[cobertura] ETL import/export'),
      ej('329','b42_mobile/Ej329SensorsModel.java','b42_mobile','PMDM (0489)','RA2','P1','[cobertura] sensores en móvil'),
      ej('333','b43_erp/Ej333ErpApiClient.java','b43_erp','SGE (0491)','RA4','P1','[cobertura] cliente API ERP'),
    ]},
    // ---------------- SEMANA 7 · 12-18 ago · PSP RA2 concurrencia + RA1 procesos ----------------
    { inicio: '2026-08-12', titulo: 'S7 · PSP RA2 concurrencia + RA1 procesos', teoria: 'teoria/27_Concurrencia_Multihilo.md', items: [
      ej('215','b27_concur/Ej215ThreadRunnable.java','b27_concur','PSP (0490)','RA2','P0','Thread vs Runnable, start/join'),
      ej('216','b27_concur/Ej216ThreadStates.java','b27_concur','PSP (0490)','RA2','P0','estados del hilo, sleep, interrupción'),
      ej('217','b27_concur/Ej217RaceConditionSynchronized.java','b27_concur','PSP (0490)','RA2','P0','condición de carrera y synchronized'),
      ej('218','b27_concur/Ej218WaitNotify.java','b27_concur','PSP (0490)','RA2','P0','wait/notify: productor-consumidor'),
      ej('219','b27_concur/Ej219ExecutorService.java','b27_concur','PSP (0490)','RA2','P0','ExecutorService, pools, shutdown'),
      ej('220','b27_concur/Ej220CallableFuture.java','b27_concur','PSP (0490)','RA2','P0','Callable, Future, get/timeout'),
      ej('221','b27_concur/Ej221Locks.java','b27_concur','PSP (0490)','RA2','P0','ReentrantLock, ReadWriteLock'),
      ej('222','b27_concur/Ej222Semaphores.java','b27_concur','PSP (0490)','RA2','P0','Semaphore, Latch, Barrier'),
      ej('224','b27_concur/Ej224DeadlockLivelock.java','b27_concur','PSP (0490)','RA2','P0','deadlock: provocar, evitar, detectar'),
      ej('227','b28_proc/Ej227ProcessBuilderBasics.java','b28_proc','PSP (0490)','RA1','P0','ProcessBuilder, waitFor, exit code'),
      ej('229','b28_proc/Ej229ProcessPipesIPC.java','b28_proc','PSP (0490)','RA1','P0','IPC por pipes'),
      ej('231','b28_proc/Ej231ParallelProcesses.java','b28_proc','PSP (0490)','RA1','P0','procesos en paralelo'),
      ej('223','b27_concur/Ej223AtomicAndConcurrentCollections.java','b27_concur','PSP (0490)','RA2','P2','atómicos y colecciones concurrentes'),
      ej('225','b27_concur/Ej225CompletableFutureAdvanced.java','b27_concur','PSP (0490)','RA2','P2','CompletableFuture (composición async)'),
      ej('226','b27_concur/Ej226ThreadPriorityAndContext.java','b27_concur','PSP (0490)','RA2','P2','prioridades, ThreadLocal, daemon'),
      ej('228','b28_proc/Ej228ProcessIO.java','b28_proc','PSP (0490)','RA1','P2','redirección stdin/stdout/stderr'),
      ej('230','b28_proc/Ej230ProcessTimeoutAndDestroy.java','b28_proc','PSP (0490)','RA1','P2','waitFor(timeout), destroy'),
      ej('232','b28_proc/Ej232ProcessEnvAndDir.java','b28_proc','PSP (0490)','RA1','P2','environment(), dir de trabajo'),
      ej('320','b41_anim/Ej320AnimationTimerLoop.java','b41_anim','PMDM (0489)','RA4','P1','[cobertura] game loop'),
      ej('337','b44_nui/Ej337NuiOverview.java','b44_nui','DI (0488)','RA2','P1','[cobertura] pipeline NUI'),
      ej('293','b37_fxcustom/Ej293CustomControlCompose.java','b37_fxcustom','DI (0488)','RA3','P1','[cobertura] componente visual compuesto'),
    ]},
    // ---------------- SEMANA 8 · 19-25 ago · PSP RA3 sockets + RA5 cripto ----------------
    { inicio: '2026-08-19', titulo: 'S8 · PSP RA3 sockets + RA5 cripto', teoria: 'teoria/29_Sockets_Red.md', items: [
      ej('233','b29_sockets/Ej233TcpEchoServer.java','b29_sockets','PSP (0490)','RA3','P0','ServerSocket/Socket, eco TCP'),
      ej('234','b29_sockets/Ej234TcpClient.java','b29_sockets','PSP (0490)','RA3','P0','cliente TCP, setSoTimeout'),
      ej('235','b29_sockets/Ej235MultiClientThreadedServer.java','b29_sockets','PSP (0490)','RA3','P0','multicliente: un hilo por conexión'),
      ej('236','b29_sockets/Ej236ApplicationProtocol.java','b29_sockets','PSP (0490)','RA3','P0','protocolo de aplicación propio'),
      ej('237','b29_sockets/Ej237UdpDatagrams.java','b29_sockets','PSP (0490)','RA3','P0','UDP: DatagramSocket/Packet'),
      ej('241','b30_crypto/Ej241Hashing.java','b30_crypto','PSP (0490)','RA5','P0','MessageDigest (SHA-256)'),
      ej('242','b30_crypto/Ej242PasswordHashingSalt.java','b30_crypto','PSP (0490)','RA5','P0','salt + PBKDF2'),
      ej('243','b30_crypto/Ej243SymmetricAes.java','b30_crypto','PSP (0490)','RA5','P0','AES (GCM/CBC, IV)'),
      ej('244','b30_crypto/Ej244AsymmetricRsa.java','b30_crypto','PSP (0490)','RA5','P0','RSA pública/privada'),
      ej('245','b30_crypto/Ej245DigitalSignature.java','b30_crypto','PSP (0490)','RA5','P0','firma digital, no repudio'),
      ej('238','b29_sockets/Ej238ObjectOverSocket.java','b29_sockets','PSP (0490)','RA3','P2','objetos serializados por socket'),
      ej('239','b29_sockets/Ej239ServerWithThreadPool.java','b29_sockets','PSP (0490)','RA3','P2','servidor con ExecutorService'),
      ej('240','b29_sockets/Ej240GracefulShutdownAndTimeouts.java','b29_sockets','PSP (0490)','RA3','P2','cierre ordenado, timeouts'),
      ej('246','b30_crypto/Ej246HmacAndSecureRandom.java','b30_crypto','PSP (0490)','RA5','P2','HMAC, SecureRandom'),
      ej('247','b30_crypto/Ej247KeyStore.java','b30_crypto','PSP (0490)','RA5','P2','KeyStore PKCS12/JCEKS'),
      ej('248','b30_crypto/Ej248TlsSecureChannel.java','b30_crypto','PSP (0490)','RA5','P2','TLS (SSLSocket)'),
      ej('349','b45_juego3d/Ej349GameEngineArchitecture.java','b45_juego3d','PMDM (0489)','RA4','P1','[cobertura] mini-ECS, arquitectura de motor'),
      ej('338','b44_nui/Ej338VoiceCommandGrammar.java','b44_nui','DI (0488)','RA2','P1','[cobertura] gramática de voz'),
    ]},
    // ---------------- SEMANA 9 · 26-31 ago · cierre PSP RA4/RA5-Spring + repaso ----------------
    { inicio: '2026-08-26', titulo: 'S9 · cierre PSP (RA4/RA5) + repaso', teoria: 'teoria/05_Controllers_REST.md', items: [
      ej('001','b00_http/Ej001HttpRequestParser.java','b00_http','PSP (0490)','RA4','P0','anatomía de la request HTTP'),
      ej('004','b00_http/Ej004HttpMethodsSemantics.java','b00_http','PSP (0490)','RA4','P0','idempotencia y seguridad de verbos'),
      ej('045','b05_web/Ej045HelloController.java','b05_web','PSP (0490)','RA4','P0','@RestController/@GetMapping'),
      ej('048','b05_web/Ej048RequestBodyPost.java','b05_web','PSP (0490)','RA4','P0','@RequestBody, 201 Created'),
      ej('053','b05_web/Ej053CrudInMemory.java','b05_web','PSP (0490)','RA4','P0','CRUD REST completo (cierra RA4)'),
      ej('155','b18_sec/Ej155SecurityFilterChain.java','b18_sec','PSP (0490)','RA5','P0','config Spring Security'),
      ej('157','b18_sec/Ej157PasswordEncoder.java','b18_sec','PSP (0490)','RA5','P0','BCrypt'),
      ej('159','b18_sec/Ej159JwtIssue.java','b18_sec','PSP (0490)','RA5','P0','emisión de JWT'),
      ej('161','b18_sec/Ej161RoleBasedAccess.java','b18_sec','PSP (0490)','RA5','P0','roles, @PreAuthorize (cierra RA5)'),
      ej('290','b36_fxstyle/Ej290AccessibilityA11y.java','b36_fxstyle','DI (0488)','RA4','P1','[cobertura] accesibilidad/WCAG'),
      ej('357','b47_pruebas/Ej357TestStrategyPlan.java','b47_pruebas','DI (0488)','RA8','P1','[cobertura] plan/estrategia de pruebas'),
      ej('335','b43_erp/Ej335BiAggregations.java','b43_erp','SGE (0491)','RA3','P1','[cobertura] BI/KPIs con Streams'),
      ej('358','b47_pruebas/Ej358RegressionBaseline.java','b47_pruebas','DI (0488)','RA8','P1','[cobertura] pruebas de regresión'),
      ej('350','b45_juego3d/Ej350MiniGame3D.java','b45_juego3d','PMDM (0489)','RA5','P1','[cobertura] mini-juego 3D'),
    ]},
  ];
}

/****************************************************************************************
 *  MOTOR — crea la lista y las tareas. No tocar salvo que quieras cambiar el formato.
 ****************************************************************************************/
function crearChecklistDAM2() {
  const plan = PLAN();

  // 1) Buscar o crear la lista DAM2. Re-ejecutable: si ya existe, la reutiliza y reanuda.
  const listas = Tasks.Tasklists.list().items || [];
  let lista = listas.find(l => l.title === NOMBRE_LISTA);
  if (lista) {
    Logger.log('Lista "' + NOMBRE_LISTA + '" ya existe; se reanuda (no duplica).');
  } else {
    lista = Tasks.Tasklists.insert({ title: NOMBRE_LISTA });
    Logger.log('Lista creada: ' + lista.title + ' (' + lista.id + ')');
  }

  // 2) Títulos ya presentes, para no duplicar al reanudar tras un corte de cuota.
  const existentes = titulosExistentes(lista.id);

  // 3) Crear solo las tareas que falten. Orden inverso => panel de S1 a S9 de arriba abajo.
  let creadas = 0, saltadas = 0;
  const semanasRev = plan.slice().reverse();
  for (const semana of semanasRev) {
    const items = semana.items;
    for (let i = items.length - 1; i >= 0; i--) {
      const it = items[i];
      const titulo = tituloTarea(it);
      if (existentes[titulo]) { saltadas++; continue; }
      const tarea = { title: titulo, notes: notasTarea(it, semana) };
      if (PONER_FECHAS) {
        // reparto uniforme: el ej. i de n cae en el día floor(i*DIAS/n) de la semana,
        // así se usan TODOS los días (incluido el último) sin apelotonar al principio.
        const offset = Math.min(DIAS_ESTUDIO - 1,
                                Math.floor(i * DIAS_ESTUDIO / items.length));
        tarea.due = fechaRFC3339(semana.inicio, offset);
      }
      insertarConReintento(tarea, lista.id);
      creadas++;
      Utilities.sleep(500); // la Tasks API limita el ritmo de escritura
    }
  }
  Logger.log('Hecho. Creadas ahora: ' + creadas + ' · ya existían: ' + saltadas +
             ' · total en "' + NOMBRE_LISTA + '": ' + (creadas + saltadas));
  if (creadas > 0) {
    Logger.log('Si antes hubo un corte de cuota, vuelve a pulsar Ejecutar hasta que ' +
               '"Creadas ahora" sea 0 (entonces están todas).');
  } else {
    Logger.log('Todas las tareas ya estaban. Abre Google Tasks / Calendar para marcarlas.');
  }
}

// Devuelve un objeto {titulo: true} con todas las tareas ya existentes en la lista.
function titulosExistentes(listId) {
  const set = {};
  let pageToken = null;
  do {
    const resp = Tasks.Tasks.list(listId, {
      maxResults: 100, showCompleted: true, showHidden: true, pageToken: pageToken
    });
    (resp.items || []).forEach(t => { if (t.title) set[t.title] = true; });
    pageToken = resp.nextPageToken;
  } while (pageToken);
  return set;
}

// Inserta con reintentos y espera creciente si la API responde "Quota Exceeded".
function insertarConReintento(tarea, listId) {
  let espera = 2000;
  for (let intento = 1; intento <= 6; intento++) {
    try {
      Tasks.Tasks.insert(tarea, listId);
      return;
    } catch (e) {
      const msg = String(e);
      if (msg.indexOf('Quota') !== -1 || msg.indexOf('ateLimit') !== -1) {
        Logger.log('Cuota alcanzada; espero ' + (espera / 1000) + 's y reintento (' +
                   intento + '/6)...');
        Utilities.sleep(espera);
        espera = Math.min(espera * 2, 30000);
      } else {
        throw e; // otro error distinto: que se vea
      }
    }
  }
  throw new Error('No se pudo crear la tarea tras varios reintentos: ' + tarea.title);
}

function tituloTarea(it) {
  const emo = EMOJI[it.prioridad] || '';
  return emo + ' ' + it.bloque.split('_')[0] + '·Ej' + it.codigo + ' ' + it.concepto +
         ' [' + abreviaModulo(it.modulo) + (it.ra !== '—' ? '·' + it.ra : '') + ']';
}

function notasTarea(it, semana) {
  const testClass = it.archivo.split('/').pop().replace(/\.java$/, '') + 'Test';
  const prio = { P0: 'P0 · núcleo obligatorio (AD/PSP a fondo)',
                 P1: 'P1 · cobertura de RA (toca y entiende; remate en el curso)',
                 P2: 'P2 · maestría / si te sobra tiempo' }[it.prioridad] || it.prioridad;
  return [
    'Semana: ' + semana.titulo,
    'Módulo: ' + it.modulo + (it.ra !== '—' ? ' · ' + it.ra : ''),
    'Bloque: ' + it.bloque,
    'Archivo: ' + it.archivo,
    'Teoría: ' + semana.teoria,
    'Prioridad: ' + prio,
    '',
    'Flujo: python bloque.py ' + it.bloque + '  ->  implementar 10 TODOs  ->  test verde',
    'Test:  mvn test -Dtest=' + testClass,
    '',
    'Hecho = test en VERDE. Marca también el checkbox en SYLLABUS.md y PLAN_VERANO_2DAM.md.'
  ].join('\n');
}

function abreviaModulo(m) {
  if (m.indexOf('AD') === 0) return 'AD';
  if (m.indexOf('PSP') === 0) return 'PSP';
  if (m.indexOf('DI') === 0) return 'DI';
  if (m.indexOf('PMDM') === 0) return 'PMDM';
  if (m.indexOf('SGE') === 0) return 'SGE';
  return 'Java';
}

// Google Tasks usa fecha (la hora se ignora). Devuelve RFC3339 a medianoche UTC.
function fechaRFC3339(inicioISO, offsetDias) {
  const d = new Date(inicioISO + 'T00:00:00Z');
  d.setUTCDate(d.getUTCDate() + offsetDias);
  return d.toISOString().replace(/\.\d{3}Z$/, '.000Z');
}

/****************************************************************************************
 *  UTILIDAD: borrar la lista DAM2 (por si quieres regenerar de cero).
 *  Ejecuta  borrarListaDAM2  y confirma la autorización.
 ****************************************************************************************/
function borrarListaDAM2() {
  const listas = Tasks.Tasklists.list().items || [];
  const l = listas.find(x => x.title === NOMBRE_LISTA);
  if (!l) { Logger.log('No existe la lista "' + NOMBRE_LISTA + '".'); return; }
  Tasks.Tasklists.remove(l.id);
  Logger.log('Lista "' + NOMBRE_LISTA + '" borrada.');
}
