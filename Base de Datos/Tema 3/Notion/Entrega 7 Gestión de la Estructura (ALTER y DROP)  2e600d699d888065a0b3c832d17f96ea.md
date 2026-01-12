# Entrega 7: Gestión de la Estructura (ALTER y DROP). (Añadir y borrar columnas. Atención especial a la sintaxis para modificar definiciones de columnas existentes: MODIFY / ALTER COLUMN, cambio de tipos de datos y restricciones).

# 🛠️ Mantenimiento de la Estructura: ALTER y DROP

Hasta ahora hemos visto cómo "construir la casa" desde cero (`CREATE TABLE`). Pero en el mundo real, los requisitos cambian: hay que añadir una nueva habitación, tirar un tabique o cambiar la instalación eléctrica. Para estas tareas de mantenimiento usamos los comandos DDL **`ALTER TABLE`** y **`DROP TABLE`**.

---

## 🗑️ 1. Eliminación Total: `DROP TABLE`

- **Concepto:** Borra la tabla completamente. Desaparece la estructura y **se pierden todos los datos** de forma irreversible.
- **Sintaxis:** `DROP TABLE NombreTabla;`

> ⚠️ Límite Crítico (Integridad Referencial): Si intentas borrar una tabla "padre" que está siendo referenciada por una tabla "hija" mediante una Foreign Key, el SGBD bloqueará el borrado y dará error para proteger la integridad de los datos.
> 

---

## 🔧 2. Modificación: `ALTER TABLE`

Es el comando "navaja suiza". Siempre empieza con la instrucción `ALTER TABLE NombreTabla` seguida de la acción específica:

### **A) Añadir Elementos (`ADD`)**

Permite agregar nuevas columnas (que nacerán con valores `NULL` para los registros existentes) o nuevas restricciones.

```sql
-- Añadir una columna nueva
ALTER TABLE Usuarios ADD telefono VARCHAR(20);

-- Añadir una constraint (usando sintaxis rigurosa con nombre)
ALTER TABLE Usuarios ADD CONSTRAINT uq_usuario_telefono UNIQUE (telefono);

```

### **B) Eliminar Elementos (`DROP`)**

Permite quitar columnas (se pierden sus datos) o reglas. Aquí es donde cobra importancia haber nombrado las restricciones con `CONSTRAINT`.

```sql
-- Eliminar una columna entera
ALTER TABLE Usuarios DROP COLUMN telefono;

-- Eliminar una restricción por su nombre
ALTER TABLE Usuarios DROP CONSTRAINT uq_usuario_email;

```

### **C) Modificar Columnas Existentes**

Se usa para cambiar el tipo de dato, la longitud o la obligatoriedad (`NULL`/`NOT NULL`).

> ❗ Límite Crítico (Compatibilidad): El cambio solo se permite si los datos existentes son compatibles.
> 
> - ✅ **Válido:** Aumentar `VARCHAR(50)` a `VARCHAR(100)`.
> - ❌ **Inválido:** Cambiar `VARCHAR` a `INTEGER` si hay texto almacenado.
> - ❌ **Inválido:** Poner `NOT NULL` si ya hay filas con valores vacíos.

### **Sintaxis según el SGBD:**

| Entorno | Sintaxis | Ejemplo |
| --- | --- | --- |
| **Oracle (Común en DAM)** | Usar **`MODIFY`** | `ALTER TABLE Usuarios MODIFY (email VARCHAR2(200));` |
| **Estándar / PostgreSQL** | Usar **`ALTER COLUMN`** | `ALTER TABLE Usuarios ALTER COLUMN email TYPE VARCHAR(200);` |
| **SQL Server** | Usar **`ALTER COLUMN`** | `ALTER TABLE Usuarios ALTER COLUMN email VARCHAR(200) NOT NULL;` |

---

## 📝 Ejemplo de Modificación en Oracle

Para cambiar el tipo y volverlo obligatorio simultáneamente:

```sql
-- Asumiendo que 'codigo' era numérico y permitía nulos:
ALTER TABLE Productos MODIFY (codigo CHAR(5) NOT NULL);

```

---

## 🖼️ Infografía Resumen: Gestión de Estructura

> Resumen de Operaciones ALTER:
> 
> 1. **ADD:** Incorpora columnas o reglas nuevas.
> 2. **DROP:** Elimina columnas o reglas (constraints) existentes.
> 3. **MODIFY / ALTER:** Cambia la definición de una columna (tipo, tamaño, nulidad).
> 4. **DROP TABLE:** Demolición total de la tabla y sus datos.

![image.png](image%206.png)