# Entrega 5: La Clave Foránea (FOREIGN KEY) - Parte II: Integridad Referencial Avanzada. (Explicación profunda y sintaxis de ON DELETE/UPDATE CASCADE y SET NULL).

# 🔄 Integridad Referencial Avanzada: Acciones en Cascada

Por defecto, las bases de datos utilizan un comportamiento llamado `RESTRICT` o `NO ACTION`, que bloquea cualquier intento de borrar un "padre" si todavía tiene "hijos" asociados. Sin embargo, para dar flexibilidad al diseño, podemos definir acciones automáticas.

---

## 🛠️ Sintaxis Extendida

Las cláusulas de integridad se añaden justo después de la referencia a la tabla padre en la definición de la `FOREIGN KEY`.

```sql
CONSTRAINT nombre_fk FOREIGN KEY (columna_propia)
    REFERENCES TablaPadre(columna_padre)
    [ON DELETE acción]  -- Qué hacer si se borra el padre
    [ON UPDATE acción]  -- Qué hacer si cambia el ID del padre

```

---

## 🌊 1. La Acción `CASCADE` (Efecto Dominó)

**Concepto:** "Lo que le pase al padre, se replica automáticamente en los hijos".

- **ON DELETE CASCADE:** Si borras una Factura (padre), se borran automáticamente todas sus Líneas de Factura (hijos).
- **ON UPDATE CASCADE:** Si cambias el ID de un Departamento del `10` al `99`, todos los empleados que tenían el `10` se actualizan al `99` sin intervención manual.

### Ejemplo de uso:

```sql
CREATE TABLE LineasFactura (
    linea_id INT PRIMARY KEY,
    factura_id INT NOT NULL,
    -- ... otros campos ...
    CONSTRAINT fk_lineas_factura FOREIGN KEY (factura_id)
        REFERENCES Facturas(factura_id)
        ON DELETE CASCADE -- ¡Efecto dominó activado!
);

```

> ⚠️ Resultado: Al ejecutar DELETE FROM Facturas WHERE factura_id = 1;, la base de datos elimina esa factura y, acto seguido, elimina todas las líneas vinculadas a ella.
> 

---

## 👤 2. La Acción `SET NULL` (Huérfanos)

**Concepto:** "Si el padre desaparece, el hijo se queda sin asignación (su referencia se vuelve vacía)".

> ❗ Requisito Crítico: Para usar esta acción, la columna de la clave foránea en la tabla hija NO puede ser NOT NULL. Debe permitir valores nulos.
> 
- **Uso Típico:** Si borras un Departamento, no quieres despedir (borrar) a los empleados; solo quieres que conste que actualmente no pertenecen a ningún departamento.

### Ejemplo de uso:

```sql
CREATE TABLE Empleados (
    emp_id INT PRIMARY KEY,
    nombre VARCHAR(50),
    -- ¡IMPORTANTE! Sin 'NOT NULL' para permitir el SET NULL
    dept_fk INT,

    CONSTRAINT fk_empleados_dept FOREIGN KEY (dept_fk)
        REFERENCES Departamentos(dept_id)
        ON DELETE SET NULL -- ¡Convertir en huérfanos!
);

```

> ℹ️ Resultado: Al ejecutar DELETE FROM Departamentos WHERE dept_id = 10;, los empleados no se borran, pero su columna dept_fk pasará a valer NULL automáticamente.
> 

---

## 📊 Resumen de Comportamientos

| Acción | Efecto al borrar al Padre | Uso recomendado |
| --- | --- | --- |
| **NO ACTION** (Default) | Bloquea el borrado (da error). | Seguridad máxima. |
| **CASCADE** | Borra también a todos los hijos. | Relaciones fuertes (Factura -> Líneas). |
| **SET NULL** | Los hijos permanecen, pero con la FK vacía. | Relaciones débiles (Depto -> Empleado). |

---

### 🖼️ Infografía Resumen: Acciones Referenciales

> Comparativa Visual:
> 
> 1. **Restrict:** Una pared que impide el borrado.
> 2. **Cascade:** Una caída de fichas de dominó.
> 3. **Set Null:** El hijo se queda, pero su "cable" de conexión queda suelto (NULL).

![image.png](image%204.png)