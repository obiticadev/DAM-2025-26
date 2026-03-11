# 📖 Manual Completo de DML, Transacciones y Concurrencia (Tema 5)

Este documento unifica todo el temario relacionado con el **Tema 5 de Bases de Datos**, agrupando en un único manual estructurado, profesional y exhaustivo, los bloques de manipulación de datos (DML básico y avanzado con subconsultas), el mantenimiento de la integridad relacional, el control de transacciones ACID y la gestión concurrente en entornos multiusuario.

---

## 📑 Índice General

*   **[PARTE I. TRATAMIENTO DE DATOS (DML E INTEGRIDAD)](#parte-i-tratamiento-de-datos-dml-e-integridad)**
    *   [1. Edición de la información mediante Herramientas Gráficas](#1-edición-de-la-información-mediante-herramientas-gráficas-oracle-apex)
        *   [1.1. Gestión de Espacios de Trabajo (Workspaces)](#11-gestión-de-espacios-de-trabajo)
        *   [1.2. Operaciones DML Gráficas](#12-operaciones-dml-gráficas)
    *   [2. Edición de la información mediante Sentencias SQL](#2-edición-de-la-información-mediante-sentencias-sql)
        *   [2.1. Inserción de registros (INSERT)](#21-inserción-de-registros-insert)
        *   [2.2. Modificación de registros (UPDATE)](#22-modificación-de-registros-update)
        *   [2.3. Borrado de registros (DELETE)](#23-borrado-de-registros-delete)
    *   [3. Integridad Referencial](#3-integridad-referencial)
        *   [3.1. Conceptos y Terminología](#31-conceptos-y-terminología)
        *   [3.2. Implicaciones DML](#32-implicaciones-dml)
        *   [3.3. Configuración de la Supresión en Cascada](#33-configuración-de-la-supresión-en-cascada)
*   **[PARTE II. SUBCONSULTAS, TRANSACCIONES Y CONCURRENCIA](#parte-ii-subconsultas-transacciones-y-concurrencia)**
    *   [4. Subconsultas en órdenes de edición DML](#4-subconsultas-en-órdenes-de-edición-dml)
        *   [4.1. Inserción masiva (INSERT con SELECT)](#41-inserción-masiva-insert-con-select)
        *   [4.2. Modificación masiva (UPDATE con SELECT)](#42-modificación-masiva-update-con-select)
        *   [4.3. Supresión avanzada (DELETE condicional)](#43-supresión-avanzada-delete-condicional)
    *   [5. Transacciones](#5-transacciones)
        *   [5.1. Propiedades ACID](#51-propiedades-acid)
        *   [5.2. Sentencias de Control (COMMIT, ROLLBACK, SAVEPOINT)](#52-sentencias-de-control-de-transacciones)
        *   [5.3. Ejemplo Práctico (Transferencia Bancaria)](#53-ejemplo-práctico-transferencia-bancaria)
    *   [6. Problemas asociados al acceso simultáneo (Bloqueos)](#6-problemas-asociados-al-acceso-simultáneo)
        *   [6.1. Reglas de Comportamiento Automático en Oracle](#61-reglas-de-comportamiento-automático)
        *   [6.2. Diferencia entre bloqueos optimistas y pesimistas](#62-bloqueos-optimistas-y-pesimistas)
        *   [6.3. Forzar bloqueos especiales (FOR UPDATE)](#63-forzar-bloqueos-especiales-for-update)

---
---

<a id="parte-i-tratamiento-de-datos-dml-e-integridad"></a>
# PARTE I. TRATAMIENTO DE DATOS (DML E INTEGRIDAD)

El tratamiento de datos engloba todas las operaciones que permiten **añadir, modificar o suprimir** la información almacenada en una base de datos. A estas operaciones se les conoce como lenguaje de manipulación de datos (**DML** - *Data Manipulation Language*). Estas acciones se pueden realizar mediante entornos gráficos o mediante sentencias SQL.

---

<a id="1-edición-de-la-información-mediante-herramientas-gráficas-oracle-apex"></a>
## 1. Edición de la información mediante Herramientas Gráficas (Oracle APEX)

Oracle Database Express proporciona una herramienta gráfica llamada **Application Express (APEX)** accesible vía navegador web (por defecto en `http://equipo:8080/apex` o `http://localhost:8080/apex`). 

<a id="11-gestión-de-espacios-de-trabajo"></a>
### 1.1. Gestión de Espacios de Trabajo (Workspaces)
Para operar, se necesita un espacio de trabajo. Si no existe, se debe crear accediendo a la administración de APEX con un usuario con privilegios DBA (como `SYSTEM`).
*   **Crear Workspace:** Se especifica un *Database User* (nuevo o existente), un *Application Express Username* (ej. `jorge`) y un *Password*.
*   **Acceso:** Una vez creado, se hace login en APEX introduciendo el *Workspace*, el *Username* y el *Password*.

<a id="12-operaciones-dml-gráficas"></a>
### 1.2. Operaciones DML desde la Interfaz Gráfica
Desde el **Explorador de Objetos** de APEX, seleccionando una tabla y yendo a la pestaña **Datos**, se pueden realizar las operaciones visualmente:

*   **Inserción:** Clic en el botón **Insertar Fila**. Se rellenan los campos y se pulsa **Crear**. *(Si se introduce un texto en un campo numérico, saltará el `error ORA-00984: columna no permitida aquí`)*.
*   **Modificación:** Clic en el icono de **Editar** (símbolo de lápiz) junto al registro deseado. Se cambian los valores y se pulsa **Aplicar Cambios**.
*   **Borrado:** Clic en el icono de **Editar** del registro a eliminar y botón **Suprimir**. *(Nota: Si el registro está siendo referenciado por otra tabla, el borrado fallará por integridad referencial, mostrando un error como `ORA-02292`).*

---

<a id="2-edición-de-la-información-mediante-sentencias-sql"></a>
## 2. Edición de la información mediante Sentencias SQL

Las sentencias SQL otorgan mayor flexibilidad, detalle y rapidez, especialmente en procesamiento por lotes. 

<a id="21-inserción-de-registros-insert"></a>
### 2.1. Inserción de registros (`INSERT`)
Añade nuevas filas a una tabla existente.

#### Sintaxis Básica:
```sql
INSERT INTO nombre_tabla [(lista_campos)] VALUES (lista_valores);
```

> **CASOS DE USO PRÁCTICOS (`INSERT`):**
> ```sql
> -- 1. Inserción total (sin especificar columnas, asumiendo el orden exacto de creación):
> INSERT INTO ROLES VALUES (1, 'Administrador_Sistema', 'Acceso Total');
> 
> -- 2. Inserción parcial segura (Omitiendo el campo 'Correo', que quedará como NULL):
> INSERT INTO USUARIOS (Login, Password, Nombre, Apellidos) 
> VALUES ('natsan63', 'VBROMI', 'NATALIA', 'SANCHEZ GARCIA');
> 
> -- 3. Inserción con funciones al vuelo (Fecha actual del sistema para el alta):
> INSERT INTO CONTRATOS (Cod_Contrato, Empleado, Fecha_Alta)
> VALUES (1509, 'Miguel Rodríguez', SYSDATE);
> ```
*   *Error común:* Si metes 5 valores pero solo defines 4 columnas, dará `ORA-00913: demasiados valores`.

<a id="22-modificación-de-registros-update"></a>
### 2.2. Modificación de registros (`UPDATE`)
Modifica uno o varios campos de registros ya existentes. **Cuidado:** Si omites el `WHERE`, se actualizará la tabla entera.

#### Sintaxis Básica:
```sql
UPDATE nombre_tabla SET nombre_campo = valor [, nombre_campo = valor]... [ WHERE condición ];
```

> **CASOS DE USO PRÁCTICOS (`UPDATE`):**
> ```sql
> -- 1. Actualización masiva (Aviso: No tiene WHERE, afecta a TODA la tabla):
> UPDATE PRODUCTOS SET Stock = 0, Estado = 'Descatalogado';
> 
> -- 2. Actualización condicional simple:
> UPDATE EMPLEADOS SET Credito = 300 WHERE Sexo = 'M';
> 
> -- 3. Subida salarial del 5% pero SOLO para los currantes del departamento 20:
> UPDATE EMPLEADOS 
> SET Salario = Salario * 1.05 
> WHERE Depto = 20;
> ```

<a id="23-borrado-de-registros-delete"></a>
### 2.3. Borrado de registros (`DELETE`)
Elimina filas completas de una tabla. **Cuidado:** Si omites el `WHERE`, se vaciará la tabla entera.

#### Sintaxis Básica:
```sql
DELETE FROM nombre_tabla [ WHERE condición ];
```

> **CASOS DE USO PRÁCTICOS (`DELETE`):**
> ```sql
> -- 1. Peligro máximo: Vaciar una tabla entera (Sin WHERE)
> DELETE FROM LOGS_TEMPORALES;
> 
> -- 2. Borrado Selectivo: Eliminar usuarios dados de baja
> DELETE FROM USUARIOS WHERE Estado = 'Baja';
> ```

---

<a id="3-integridad-referencial"></a>
## 3. Integridad Referencial

Asegura que las relaciones (Foreign Keys -> Primary Keys) se mantengan coherentes. Cada valor de la clave ajena en la tabla hija **debe existir** previamente en la tabla padre.

<a id="31-conceptos-y-terminología"></a>
### 3.1. Conceptos y Terminología
*   **Tabla padre (Principal):** Contiene la Clave Primaria (PK). Sus datos son la fuente de verdad. Ej. `JUEGOS`.
*   **Tabla hija (Dependiente):** Contiene la Clave Ajena (FK). Sus datos dependen de que la PK exista en el padre. Ej. `PARTIDAS`.

<a id="32-implicaciones-dml"></a>
### 3.2. Implicaciones DML
1.  **Insertar (Hija):** No puedes insertar un `Cod_Juego` en `PARTIDAS` si ese juego no ha sido creado antes en `JUEGOS`.
2.  **Borrar (Padre):** *NO* puedes borrar un juego de `JUEGOS` si ya hay registros en `PARTIDAS` que dependen de él. Da el famoso error: `ORA-02292: restricción de integridad violada - registro secundario encontrado`.

<a id="33-configuración-de-la-supresión-en-cascada"></a>
### 3.3. Configuración de la Supresión en Cascada
Para solucionar el bloqueo de borrado del punto anterior, la base de datos permite automatizar respuestas al crear la Foreign Key:

1.  **Restrict (Opción por defecto):** Aborta y lanza error `ORA-02292`.
2.  **`ON DELETE CASCADE`:** Si borras al Padre (JUEGO), el sistema borrará automáticamente en cascada a todos sus hijos (PARTIDAS).
3.  **`ON DELETE SET NULL`:** Si borras al Padre, los hijos no se borran, simplemente su FK pasará a `NULL` (quedan huérfanos permitidos).

> **CASO DE USO (Aplicar Cascada en creación de tablas):**
> ```sql
> CREATE TABLE PARTIDAS (
>     ID_Partida INT PRIMARY KEY,
>     Cod_Juego INT,
>     -- Vinculación con cascada activa:
>     CONSTRAINT FK_Juego_Cascade 
>     FOREIGN KEY (Cod_Juego) REFERENCES JUEGOS (Codigo) 
>     ON DELETE CASCADE
> );
> ```

---
---

<a id="parte-ii-subconsultas-transacciones-y-concurrencia"></a>
# PARTE II. SUBCONSULTAS, TRANSACCIONES Y CONCURRENCIA

En bases de datos relacionales, las operaciones de edición (DML) pueden escalarse usando el resultado de otras consultas complejas. Además, trabajar en entornos multiusuario requiere obligatoriamente gestionar **transacciones** y **bloqueos** para asegurar la integridad total de la información (evitar el caos colectivo).

---

<a id="4-subconsultas-en-órdenes-de-edición-dml"></a>
## 4. Subconsultas en órdenes de edición DML

SQL permite utilizar los resultados obtenidos por una consulta (`SELECT`) para alimentar directamente operaciones de inserción (`INSERT`), modificación (`UPDATE`) o supresión (`DELETE`), ahorrando miles de líneas de programación manual.

<a id="41-inserción-masiva-insert-con-select"></a>
### 4.1. Inserción masiva (INSERT con SELECT)
Volcar datos masivos de una tabla "staging" a otra de producción quitando el `VALUES` y conectando el `SELECT`.

> **CASOS DE USO PRÁCTICOS (`INSERT INTO ... SELECT`):**
> ```sql
> -- 1. Clonar usuarios nuevos a la tabla principal
> INSERT INTO USUARIOS (LOGIN, PASSWORD, NOMBRE, CORREO)
> SELECT LOGIN, PASSWORD, NOMBRE, CORREO 
> FROM USUARIOS_TEMPORALES 
> WHERE Validado = 'SI';
> 
> -- 2. Inserción sobre vista en línea (SQL Avanzado)
> INSERT INTO (SELECT LOGIN, PASSWORD, CORREO FROM USUARIOS)
> VALUES ('natsan63', 'VBROMI', 'natsan@mail.com');
> ```

<a id="42-modificación-masiva-update-con-select"></a>
### 4.2. Modificación masiva (UPDATE con SELECT)
Actualizaciones potentes referenciadas a datos vivos de otras tablas.

> **CASO DE USO PRÁCTICO (`UPDATE` anidado):**
> *Problema:* Queremos actualizar el crédito de todos los creadores de partidas activas (Estado=1), dándoles el crédito máximo absoluto del servidor.
> ```sql
> UPDATE USUARIOS 
> SET Credito = (SELECT MAX(Credito) FROM USUARIOS) -- Qué valor poner
> WHERE Login IN (SELECT Cod_Crea FROM PARTIDAS WHERE Estado=1); -- A quién
> ```

<a id="43-supresión-avanzada-delete-condicional"></a>
### 4.3. Supresión avanzada (DELETE condicional)
Permite borrar datos cuya llave lógica dependa de una evaluación cruzada entre tablas.

> **CASO DE USO PRÁCTICO (`DELETE` anidado):**
> *Problema:* Borrar a todos los usuarios que hayan creado alguna partida que ya esté cancelada (Estado=0).
> ```sql
> DELETE FROM USUARIOS 
> WHERE Login IN (SELECT Cod_Crea FROM PARTIDAS WHERE Estado = 0);
> ```

---

<a id="5-transacciones"></a>
## 5. Transacciones

Una **transacción** es el concepto más importante en DB transaccionales. Es una **unidad atómica de trabajo** (indivisible) que agrupa una o más sentencias DML. Se ejecutan "todas juntas a la vez" o "no se aplica ninguna en absoluto". 

<a id="51-propiedades-acid"></a>
### 5.1. Propiedades ACID
1.  **Atomicidad:** O pasa al 100%, o al 0%. No existen medias tintas ni "me quedé a medias".
2.  **Consistencia:** La DB pasa de un estado válido e íntegro A a un estado íntegro B.
3.  **Aislamiento:** Nadie más puede ver mis updates al vuelo mientras no confirme mi transacción.
4.  **Durabilidad:** Una vez "confirmada", aunque se corte la luz al segundo siguiente, los datos residirán en el disco duro.

<a id="52-sentencias-de-control-de-transacciones"></a>
### 5.2. Sentencias de Control (COMMIT, ROLLBACK, SAVEPOINT)
*   `COMMIT`: Hace **permanentes y públicas** las modificaciones en el disco.
    *   *Nota*: Un DDL (`CREATE`, `ALTER`, `DROP`) hace un `COMMIT` implícito automático y te auto-guarda las transacciones en memoria que tuvieras pendientes sin avisar.
*   `ROLLBACK`: **Deshace, borra y anula** las ediciones DML hechas en tu sesión actuales que no estuvieran confirmadas, y restaura la tabla. (Ocurre automático si hay una desconexión crítica).
*   `SAVEPOINT nombre`: Crea un marcapáginas/checkpoint en la mitad de la transacción.
*   `ROLLBACK TO SAVEPOINT nombre`: En vez de abortar todo, anula solo tus últimas acciones hasta el checkpoint marcado.

<a id="53-ejemplo-práctico-transferencia-bancaria"></a>
### 5.3. Ejemplo Práctico (Transferencia Bancaria)
Si a alguien le restamos dinero, tenemos que obligatoriamente sumárselo a otro. Si a mitad de proceso hay un fallo de conexión, y no hubiera sistema de transacciones, se perdería el dinero para siempre.
```sql
BEGIN
  -- 1. Restamos 100€ (La DB bloquea esta cuenta temporalmente)
  UPDATE cuentas SET saldo = saldo - 100 WHERE cuenta = 1234;
  
  -- 2. Sumamos 100€ (La DB bloquea la cuenta destino temporalmente)
  UPDATE cuentas SET saldo = saldo + 100 WHERE cuenta = 5678;
  
  -- 3. Histórico de seguimiento
  INSERT INTO registro_operaciones VALUES ('TRANSFERENCIA', 1234, 5678, 100);
  
  -- Si todas las sentencias de arriba salieron bien sin crashear, confirmamos la transacción (Atomicidad):
  COMMIT; 
EXCEPTION
  WHEN OTHERS THEN
    -- Si ALGO sale mal en CUALQUIER momento de arriba, deshacemos Todo de golpe y el dinero nunca salió:
    ROLLBACK; 
END;
```

---

<a id="6-problemas-asociados-al-acceso-simultáneo"></a>
## 6. Problemas asociados al acceso simultáneo a los datos (Bloqueos)

Si dos desarrolladores o clientes entran al mismo tiempo (Concurrencia), Oracle usa **Locks** (Bloqueos) transparentes para proteger la integridad.

<a id="61-reglas-de-comportamiento-automático"></a>
### 6.1. Reglas de Comportamiento Automático en Oracle (Aislamiento)
1.  **Escritor vs Escritor:** Totalmente bloqueado. Si Juan y Ana hacen un `UPDATE` a la misma vez en la fila "ID=5", Juan llega primero (y bloquea la fila). Ana se quedará congelada con el icono "Cargando..." esperando horas hasta que Juan tire un `COMMIT` o `ROLLBACK`.
2.  **Escritor vs Lector:** Sin bloqueos. Nadie bloquea la lectura. Si Juan edita la fila 5 sin guardar, y entra Ana y hace un `SELECT *`, ella no ve el trabajo a medias de Juan, ella simplemente **ve una copia fantasmal de "Undo" con los datos originales** de antes de empezar.
3.  **Los bloqueos terminan y desaparecen SIEMPRE de dos únicas formas:** Usando `COMMIT` (para bien) o `ROLLBACK` (para mal / por caídas).

<a id="62-bloqueos-optimistas-y-pesimistas"></a>
### 6.2. Diferencias entre tipos de bloqueos por diseño
*   **Bloqueos pesimistas / Exclusivos:** Bloquean un registro desde que el usuario lo abre para mirarlo antes de editar. Evitan fallos pero revientan la experiencia de usuario general (Nadie puede apenas usar el software si crecen los usuarios).
*   **Bloqueos optimistas:** No bloquean nada mientras el usuario rellena el formulario. Solo tiran un min-bloqueo atómico a la base de datos el milisegundo final cuando pulsan al botón "Guardar". (El más usado universalmente de forma moderna por rendimiento web).

<a id="63-forzar-bloqueos-especiales-for-update"></a>
### 6.3. Forzar bloqueos especiales (`FOR UPDATE`)
A veces no puedes fiarte del sistema automático. Puedes transmutar un vulgar `SELECT` (que es inofensivo) para que secuestre las filas y las bloquee en exclusiva como si fuera un duro escritor `UPDATE`. **Útil para reservas de stock.**

> **CASO DE USO (Reserva pesimista de Billetes / Stock garantizado):**
> *Problema*: Dos personas han visto el billete al mismo tiempo en la app, al seguir al carrito, queremos que la fila del billete quede bloqueada para que nadie más la toque mientras pagamos.
> ```sql
> -- Esta orden es un SELECT normal, pero anexa un candado exclusivo intocable por culpa del FOR UPDATE.
> SELECT Asiento, Estado 
> FROM VUELOS 
> WHERE ID_Avion = 401 AND Asiento = '12A'
> FOR UPDATE;
> 
> -- (Aquí la app procede con el pago en pasarela) -> El resto del mundo se queda bloqueado esperando.
> 
> -- Confirmamos cambio al final del script para liberar el candado:
> UPDATE VUELOS SET Estado = 'Vendido' WHERE Asiento = '12A';
> COMMIT; -- Candado suelto.
> ```
