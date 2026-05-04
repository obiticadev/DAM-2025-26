# BOOTCAMP 10 — JDBC, DAO & SQLite Masterclass
## Sílabo y Planning Completo

---

## Variables del proyecto

| Variable | Valor |
|---|---|
| Lenguaje | Java 17 |
| Conceptos | JDBC · Singleton Conexión · PreparedStatement · ResultSet · try-with-resources · Entidad POJO · Patrón DAO · CRUD completo · SQLite |
| Ejercicios | 35 |
| Gestor | Maven |
| Ejecución | VS Code Run Button → `public static void main` |
| Testing | JUnit 5 |

---

## Contexto del examen

- Las **queries SQL te las dan** — no las tienes que memorizar.
- Lo que evalúan es la **lógica**: Singleton, PreparedStatement, ResultSet, DAO.
- Una vez aprendida la lógica, los ejercicios se resuelven en **sota, caballo y rey**.

---

## Estructura del proyecto

```
10_JDBCDAOMasterclass/
├── SYLLABUS.md                        ← este archivo
├── README_GUIA_TERMINAL.md            ← cómo levantar y testear
├── pom.xml
├── teoria/
│   ├── 01_Conexion_JDBC_Singleton.md
│   ├── 02_PreparedStatement_ResultSet.md
│   ├── 03_Patron_DAO_Completo.md
│   └── 04_Integracion_Escenarios.md
├── src/main/java/com/bootcamp/
│   ├── nivel1_conexion/
│   ├── nivel2_crud/
│   ├── nivel3_dao/
│   └── nivel4_integracion/
└── src/test/java/com/bootcamp/
    ├── nivel1/
    ├── nivel2/
    ├── nivel3/
    └── nivel4/
```

---

## BLOQUE I — Nivel 1: La Conexión (Ej01–Ej08)
> Teoría: `01_Conexion_JDBC_Singleton.md`

| Ej | Archivo | Concepto clave |
|---|---|---|
| 01 | `Ej01_PrimeraConexion` | `DriverManager.getConnection()` cruda, sin Singleton |
| 02 | `Ej02_SingletonConexion` | Constructor privado + `instance` estático |
| 03 | `Ej03_TryWithResources` | `try(Statement stmt = ...)` + cierre automático |
| 04 | `Ej04_CrearTabla` | `Statement.execute(sql)` + `CREATE TABLE IF NOT EXISTS` |
| 05 | `Ej05_CerrarConexion` | `instance.isClosed()` + cierre seguro |
| 06 | `Ej06_VerificarEstado` | Guard `null \|\| isClosed()` antes de reusar |
| 07 | `Ej07_MultiplesTablasCreacion` | Dos tablas distintas desde el mismo Singleton |
| 08 | `Ej08_EliminarYRecrear` | `DROP TABLE IF EXISTS` + recrear desde cero |

---

## BLOQUE II — Nivel 2: CRUD con PreparedStatement (Ej09–Ej18)
> Teoría: `02_PreparedStatement_ResultSet.md`

| Ej | Archivo | Concepto clave |
|---|---|---|
| 09 | `Ej09_InsertStringParams` | `pst.setString(pos, valor)` + `executeUpdate()` |
| 10 | `Ej10_InsertTiposMixtos` | `setInt`, `setString`, `setDouble` combinados |
| 11 | `Ej11_InsertRetornaBoolean` | `int actualizado = pst.executeUpdate(); return actualizado > 0` |
| 12 | `Ej12_SelectTodosALista` | `executeQuery()` → `while(rs.next())` → `List<Entidad>` |
| 13 | `Ej13_SelectPorId` | `WHERE id = ?` → `if(rs.next())` → `Entidad` o `null` |
| 14 | `Ej14_Update` | `UPDATE ... SET ... WHERE id = ?` → boolean |
| 15 | `Ej15_Delete` | `DELETE FROM ... WHERE id = ?` → boolean |
| 16 | `Ej16_NavegacionResultSet` | `rs.getString()`, `rs.getInt()`, `rs.getDouble()` |
| 17 | `Ej17_NulosEnResultSet` | `rs.wasNull()` + campos opcionales |
| 18 | `Ej18_MultiplesInserts` | Loop de inserts + conteo de éxitos |

---

## BLOQUE III — Nivel 3: Patrón DAO Completo (Ej19–Ej27)
> Teoría: `03_Patron_DAO_Completo.md`

| Ej | Archivo | Concepto clave |
|---|---|---|
| 19 | `Ej19_EntidadPOJO` | Clase con atributos privados, constructor, solo getters |
| 20 | `Ej20_InterfazDAO` | `interface` con firmas de todos los métodos CRUD |
| 21 | `Ej21_DAO_CrearTabla` | Implementar `crearTabla()` con `Statement` |
| 22 | `Ej22_DAO_Insertar` | Implementar `insertar(Entidad e)` → boolean |
| 23 | `Ej23_DAO_ObtenerTodos` | Implementar `obtenerTodos()` → `List<Entidad>` |
| 24 | `Ej24_DAO_ObtenerPorId` | Implementar `obtenerPorId(int id)` → `Entidad` |
| 25 | `Ej25_DAO_Actualizar` | Implementar `actualizar(Entidad e)` → boolean |
| 26 | `Ej26_DAO_Eliminar` | Implementar `eliminar(int id)` → boolean |
| 27 | `Ej27_DAOCompleto_SpeedRun` | DAO entero desde cero, entidad nueva, sin andamiaje |

---

## BLOQUE IV — Nivel 4: Integración y escenarios reales (Ej28–Ej33)
> Teoría: `04_Integracion_Escenarios.md`

| Ej | Archivo | Concepto clave |
|---|---|---|
| 28 | `Ej28_EntidadesRelacionadas` | Dos POJOs + dos DAOs compartiendo la misma `Conexion` |
| 29 | `Ej29_ConsultasFiltradas` | `WHERE campo LIKE ?` + parámetros opcionales |
| 30 | `Ej30_ConsultasOrdenadas` | `ORDER BY campo ASC/DESC` en la query del DAO |
| 31 | `Ej31_SimulacionTransaccion` | `setAutoCommit(false)` + `commit()` + `rollback()` |
| 32 | `Ej32_ManejoErrores` | `try/catch SQLException` en cada operación + mensajes útiles |
| 33 | `Ej33_SpeedRun_Examen` | Entidad + Singleton + DAO completo en un solo archivo simulando tiempo de examen |

---

## BOSS FINAL — Sistema de Gestión

**Escenario corporativo:** Una empresa necesita un sistema para gestionar su catálogo de **Productos** y **Categorías**. Sin plantilla. Sin guía. Solo la lógica.

| Archivo | Rol |
|---|---|
| `BossFinal_SistemaProductos` | Todo en un solo archivo: Categoria, Producto, Conexion, DAOCategoria, DAOProducto (5 TODOs) |
| `BossFinalTest` | Suite más exigente del bootcamp (24 tests) |

DAOProducto incluye además de CRUD completo:
- `obtenerPorCategoria(int idCategoria)` → lista filtrada
- `buscarPorNombre(String fragmento)` → búsqueda con LIKE

---

## Progreso

- [x] BLOQUE I — Nivel 1: La Conexión (Ej01–Ej08) ✓
- [x] BLOQUE II — Nivel 2: CRUD con PreparedStatement (Ej09–Ej18) ✓
- [x] BLOQUE III — Nivel 3: Patrón DAO Completo (Ej19–Ej27) ✓
- [x] BLOQUE IV — Nivel 4: Integración y escenarios reales (Ej28–Ej33) ✓
- [ ] BOSS FINAL — BossFinal_SistemaProductos
