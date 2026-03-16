# 🚀 CHEATSHEET DEFINITIVO: TEMA 5 - DML, Transacciones y Concurrencia

Esta hoja de referencia rápida (Cheatsheet) condensa todo lo necesario para dominar el tratamiento de datos en Oracle SQL: sentencias DML básicas y avanzadas con subconsultas, gestión de integridad referencial, control de transacciones y resolución de problemas de concurrencia. **Directo al grano, para aprobar y trabajar.**

---

## 📑 Índice de Contenidos
1. [Manipulación de Datos (DML Básico)](#1-manipulación-de-datos-dml-básico)
   - [INSERT (Añadir)](#insert-añadir)
   - [UPDATE (Modificar)](#update-modificar)
   - [DELETE (Borrar)](#delete-borrar)
2. [Integridad Referencial (Relaciones)](#2-integridad-referencial-relaciones)
   - [Restricciones de Borrado](#restricciones-de-borrado)
   - [Supresión en Cascada](#supresión-en-cascada)
3. [DML Avanzado con Subconsultas](#3-dml-avanzado-con-subconsultas)
   - [INSERT + SELECT](#insert--select)
   - [UPDATE con Subconsultas](#update-con-subconsultas)
   - [DELETE con Subconsultas](#delete-con-subconsultas)
4. [Control de Transacciones (TCL)](#4-control-de-transacciones-tcl)
   - [Propiedades ACID](#propiedades-acid)
   - [Comandos Principales (COMMIT, ROLLBACK)](#comandos-principales)
5. [Concurrencia y Bloqueos (Locks)](#5-concurrencia-y-bloqueos-locks)
   - [Reglas de Oro del Bloqueo](#reglas-de-oro-del-bloqueo)
   - [Bloqueo Manual (FOR UPDATE)](#bloqueo-manual-for-update)

---

<a id="1-manipulación-de-datos-dml-básico"></a>
## 1. Manipulación de Datos (DML Básico)

Las operaciones DML (Data Manipulation Language) alteran los **datos**, pero no la estructura de la tabla.

### INSERT (Añadir)
Introduce nuevas filas. Si no se especifican las columnas, se deben pasar **todos** los valores en el **orden exacto** de creación de la tabla.

```sql
-- Inserción explícita segura (Recomendado):
INSERT INTO USUARIOS (Login, Password, Nombre) 
VALUES ('juan99', '12345', 'Juan Pérez');

-- Inserción implícita total (Peligroso si cambia la tabla):
INSERT INTO ROLES VALUES (1, 'Admin', 'Acceso Total');

-- Inserción usando funciones de Oracle:
INSERT INTO LOGS (Id, Fecha_Accion) VALUES (10, SYSDATE);

-- Consultar estructura (Obligatorio antes de tocar datos):
DESCRIBE nombre_tabla; -- O simplemente DESC
```
*💡 **Error típico:** `ORA-00913 (demasiados valores)` - Has puesto más valores en `VALUES` que columnas declaradas.*

### UPDATE (Modificar)
Actualiza datos existentes. **⚠️ PELIGRO: Si omites el `WHERE`, modificas la tabla entera.**

```sql
-- Modificar un único campo a múltiples usuarios:
UPDATE EMPLEADOS SET Credito = 300 WHERE Sexo = 'M';

-- Modificar múltiples campos a la vez:
UPDATE PRODUCTOS 
SET Stock = 50, Precio = Precio * 1.10 
WHERE Categoria = 'Informática';

-- Destrucción masiva (¡Cuidado, sin WHERE!):
UPDATE CONFIGURACION SET Activo = 0; 
```

### DELETE (Borrar)
Borra filas enteras. **⚠️ PELIGRO: Si omites el `WHERE`, vacías la tabla.**

```sql
-- Borrado selectivo mediante condición lógica:
DELETE FROM USUARIOS WHERE Estado = 'Baja';

-- Borrado total de la tabla (Sigue siendo recuperable con ROLLBACK antes del COMMIT):
DELETE FROM CARPETA_TEMPORAL;
```

---

<a id="2-integridad-referencial-relaciones"></a>
## 2. Integridad Referencial (Relaciones)

Mantiene la coherencia entre **Claves Foráneas (FK)** de una tabla "Hija" y las **Claves Primarias (PK)** de la tabla "Padre". La regla de oro: *Un hijo no puede existir sin su padre.*

### Restricciones de Borrado
Si intentas un `DELETE` en la tabla Padre de un registro que ya tiene hijos usándolo, Oracle te frenará en seco con el error:
👉 `ORA-02292: restricción de integridad violada - registro secundario encontrado`.

### Supresión en Cascada
Para evitar el error ORA-02292 automáticamente, se configura el comportamiento de la Foreign Key en el `CREATE TABLE`:

```sql
-- AL BORRAR AL PADRE, SE BORRAN SUS HIJOS (Cascada Destructiva):
CONSTRAINT FK_Juego FOREIGN KEY (Cod_Juego) REFERENCES JUEGOS (Codigo) 
ON DELETE CASCADE

-- AL BORRAR AL PADRE, LOS HIJOS QUEDAN HUÉRFANOS (Su campo FK pasa a ser nulo):
CONSTRAINT FK_Juego FOREIGN KEY (Cod_Juego) REFERENCES JUEGOS (Codigo) 
ON DELETE SET NULL

-- APLICAR EN TABLA YA EXISTENTE:
ALTER TABLE PARTIDAS ADD CONSTRAINT FK_Hija FOREIGN KEY (Cod) REFERENCES PADRE (ID) ON DELETE CASCADE;
```

---

<a id="3-dml-avanzado-con-subconsultas"></a>
## 3. DML Avanzado con Subconsultas

El culmen del DML: Usar una consulta dinámica para alimentar la inserción o actualización de datos. Desaparece la palabra `VALUES`.

### INSERT + SELECT
Clonar masivamente o mover datos filtrados de tabla a tabla.
```sql
-- Copiamos a la tabla históricos todos los usuarios borrados (Estado 0):
INSERT INTO USUARIOS_HISTORICO (Login, Nombre, Apellido)
SELECT Login, Nombre, Apellido 
FROM USUARIOS 
WHERE Estado = 0;
```

### UPDATE con Subconsultas
Actualizar campos usando matemáticas avanzadas sacadas de otras tablas.
```sql
-- Igualar el sueldo de todos los becarios al Sueldo Medio de la empresa:
UPDATE EMPLEADOS 
SET Salario = (SELECT AVG(Salario) FROM EMPLEADOS)
WHERE Categoria = 'Becario';
```

### DELETE con Subconsultas
Unir tablas lógicamente en la mente para saber a quién borrar.
```sql
-- Borrar a los usuarios cuyas partidas tengan estado 0:
DELETE FROM USUARIOS 
WHERE Login IN (SELECT Cod_Crea FROM PARTIDAS WHERE Estado = 0);
```

---

<a id="4-control-de-transacciones-tcl"></a>
## 4. Control de Transacciones (TCL)

Una transacción es un bloque de órdenes SQL DML que se ejecutan enteras (**todas**) o no se ejecuta ninguna (**nada**). Es el "guardar partida" de las Bases de Datos.

### Propiedades ACID
*   **Atomicidad:** Funciona al 100% o falla al 100%.
*   **Consistencia:** El paso de estado A a estado B siempre es legal.
*   **Aislamiento:** Nadie ve mis cambios mientras no los confirme (`COMMIT`).
*   **Durabilidad:** Si se guarda, sobrevive hasta a apagones de servidor.

### Comandos Principales
```sql
-- 1. COMMIT: Confirmar transacción y volcar a disco. (Público y Permanente).
COMMIT;

-- 2. ROLLBACK: Revertir y anular todos los DML ejecutados desde el último COMMIT.
ROLLBACK;

-- 3. SAVEPOINT: Crear un marcapáginas a mitad de un proceso gigante.
SAVEPOINT paso_1;
ROLLBACK TO SAVEPOINT paso_1; -- Vuelve al marcapáginas sin abortarlo todo.

-- 4. NIVEL DE AISLAMIENTO:
SET TRANSACTION ISOLATION LEVEL SERIALIZABLE; -- Máxima seguridad.
```
*🔥 **Aviso letal:** Si ejecutas CUALQUIER comando DDL (CREATE, ALTER, DROP...), Oracle dispara un `COMMIT` automático e implícito, guardando todos tus DML anteriores sin preguntarte.*

---

<a id="5-concurrencia-y-bloqueos-locks"></a>
## 5. Concurrencia y Bloqueos (Locks)

En sistemas multiusuario, Oracle usa bloqueos para evitar que dos personas editen al mismo tiempo el exacto mismo dato.

### Reglas de Oro del Bloqueo
1.  **Escritor vs Escritor:** Totalmente bloqueado. El 1º que hace el `UPDATE` bloquea la fila. El 2º se queda cargando (congelado) hasta que el 1º haga `COMMIT` o `ROLLBACK`.
2.  **Lector vs Escritor:** Sin retenciones. Si tú haces un `SELECT`, NO bloqueas a nadie. Si tú haces un `UPDATE` sin guardarlo, un Lector que haga `SELECT` verá una copia virtual de los **datos antiguos** (sistema *Undo* de Oracle).
3.  **Filosofías de Software:**
    *   **Pesimista:** Bloquea la puerta desde que entras a la página a mirar el producto.
    *   **Optimista:** Te deja rellenar el formulario en paz, y solo bloquea atómicamente la DB la fracción de segundo en la que pulsas "Guardar". (El estándar web actual).

### Tipos de Candados
*   **Compartido (S):** Otros pueden leer, pero nadie puede escribir.
*   **Exclusivo (X):** Nadie más puede leer con bloqueo ni escribir. (UPDATE/DELETE).

### Bloqueo Manual
1.  **A nivel de Fila:** `SELECT ... FOR UPDATE;`
2.  **A nivel de Tabla:** `LOCK TABLE nombre IN EXCLUSIVE MODE;`

### Bloqueo Manual (FOR UPDATE)
Si hacemos una app para comprar entradas de cine, queremos que cuando alguien *seleccione* la butaca 12A (con un `SELECT`), esa butaca se bloquee para el resto de España mientras la paga, **comportando el SELECT como un temible Escritor.**

```sql
-- BLOQUEO ESPECÍFICO DE FILAS PARA UNA RESERVA SEGURA (Bloqueo Pesimista Exclusivo):
SELECT Asiento, Estado 
FROM VUELOS 
WHERE ID_Avion = 401 AND Asiento = '12A'
FOR UPDATE; -- La magia ocurre aquí: La fila 12A queda candada 🔒

-- (...) Hacemos los cálculos que haya que hacer en la app (...)

-- Guardamos y liberamos el candado para los demás:
UPDATE VUELOS SET Estado = 'Vendido' WHERE Asiento = '12A';
COMMIT; 🔓
```
