# Entrega 10: Manipulación de Datos - Eliminación (DELETE).

# 🗑️ Eliminación de Datos: Comando `DELETE`

Llegamos a la última operación fundamental del DML: `DELETE`. Su función principal es eliminar **filas** específicas de una tabla.

---

## ⚖️ Diferencia Fundamental: DDL vs DML

Es vital no confundir estos dos comandos, ya que sus consecuencias son muy distintas:

- **`DROP TABLE` (DDL):** Borra la tabla entera (desaparece la estructura y los datos). La "casa" es demolida.
- **`DELETE FROM` (DML):** Borra los datos dentro de la tabla, pero la estructura permanece intacta. La "casa" se vacía, pero las paredes siguen ahí.

---

## 🏗️ Sintaxis Básica y el "Peligro Mortal" del `WHERE`

La estructura del comando es sencilla, pero requiere precaución extrema:

```sql
DELETE FROM NombreTabla
WHERE condición_filtro;

```

> 🚨 ¡PELIGRO!
Si omites la cláusula WHERE, la base de datos borrará TODAS las filas de la tabla de forma inmediata. Es una de las formas más rápidas de causar una pérdida de datos masiva en un entorno real.
> 

### Ejemplos de uso:

```sql
-- 1. Borrado controlado: Eliminar un usuario específico
DELETE FROM Usuarios
WHERE usuario_id = 55;

-- 2. Borrado masivo por condición: Limpieza de stock antiguo
DELETE FROM Productos
WHERE estado = 'Descatalogado' AND fecha_baja < '2023-01-01';

-- 3. Vaciado completo (accidental o intencionado)
DELETE FROM Historial_Logs;

```

---

## 🔗 Interacción con la Integridad Referencial (FK)

Aquí es donde la definición de tus claves foráneas (Entrega 5) determina el resultado. Si intentas borrar una fila **"padre"** que tiene **"hijos"** asociados, ocurrirá lo siguiente:

1. **Por defecto (RESTRICT / NO ACTION):** La base de datos detecta que hay registros vinculados. **Bloquea el borrado** y lanza un error para proteger a los hijos.
2. **Si usaste `ON DELETE CASCADE`:** La base de datos borra al padre y **automáticamente** borra a todos sus hijos (Efecto dominó).
3. **Si usaste `ON DELETE SET NULL`:** La base de datos borra al padre y pone a `NULL` la referencia en los hijos (Los deja huérfanos pero vivos).

---

## ⚡ Bonus DAM: `DELETE` vs `TRUNCATE`

Si necesitas vaciar una tabla completamente, existen dos caminos con diferencias técnicas importantes:

| Característica | `DELETE FROM Tabla;` | `TRUNCATE TABLE Tabla;` |
| --- | --- | --- |
| **Categoría** | DML (Manipulación) | DDL (Estructura) |
| **Velocidad** | Lenta (borra fila a fila) | Ultra rápida (reinicia la tabla) |
| **Registro (Logs)** | Registra cada fila borrada | Mínimo registro |
| **Deshacer** | Permite `ROLLBACK` | Generalmente no se puede deshacer |
| **Uso** | Borrados selectivos o pequeños | Vaciado total de tablas gigantes |

---

## 🖼️ Infografía Resumen: Eliminación e Integridad

> Puntos clave para recordar:
> 
> 1. **Filtro:** Siempre verifica el `WHERE` antes de ejecutar un `DELETE`.
> 2. **Bloqueo:** Si un borrado falla, revisa las claves foráneas (FK) que apuntan a esa fila.
> 3. **Eficiencia:** Usa `TRUNCATE` solo cuando estés 100% seguro de querer vaciar una tabla de forma definitiva y rápida.

![image.png](image%209.png)