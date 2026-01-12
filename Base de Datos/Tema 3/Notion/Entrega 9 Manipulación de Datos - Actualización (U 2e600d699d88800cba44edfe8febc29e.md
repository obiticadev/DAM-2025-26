# Entrega 9: Manipulación de Datos - Actualización (UPDATE).

# 🖋️ Modificación de Datos: Comando `UPDATE`

Una vez que los datos están almacenados, es común que necesiten actualizarse: los precios cambian, los usuarios actualizan sus correos o el stock varía. Para modificar datos que **ya existen**, utilizamos el comando `UPDATE`.

---

## 💡 Concepto Clave: Integridad Activa

Al igual que el `INSERT`, el comando `UPDATE` está vigilado permanentemente por todas las restricciones de la tabla (`NOT NULL`, `UNIQUE`, `CHECK`, `FOREIGN KEY`).

> [!IMPORTANT]
Si el nuevo valor que intentas asignar viola alguna regla de integridad en cualquiera de las filas afectadas, la operación completa falla y no se guarda ningún cambio.
> 

---

## 🏗️ Sintaxis Básica y la Cláusula `SET`

La estructura se divide en la tabla a modificar y los cambios a aplicar:

```sql
UPDATE NombreTabla
SET columna1 = nuevo_valor1,
    columna2 = nuevo_valor2,
    ...
WHERE condición_filtro;

```

- **Cláusula `SET`:** Es donde defines las asignaciones.
- **Valores Literales:** Puedes poner un dato fijo (ej. `precio = 10.50`).
- **Expresiones:** Puedes realizar cálculos basados en el valor actual (ej. `stock = stock - 1` o `salario = salario * 1.05`).

---

## 🚨 El Peligro Crítico del `WHERE`

Si ejecutas un `UPDATE` y olvidas la cláusula `WHERE`, la base de datos aplicará el cambio a **TODAS LAS FILAS** de la tabla.

> [!CAUTION]
Omitir el WHERE es un error catastrófico común. Podrías, por ejemplo, poner el precio de todos los productos del catálogo a 0€ por accidente. Salvo que busques un cambio masivo, siempre usa un filtro.
> 

---

## 📝 Ejemplos Prácticos

### 1. Actualización de una fila específica (por PK)

Ideal para cambios puntuales donde conocemos el identificador único.

```sql
-- Cambiar precio y stock del producto con ID 105
UPDATE Productos
SET precio = 199.99,
    stock = 50
WHERE prod_id = 105;

```

### 2. Actualización masiva controlada (por Condición)

Útil para aplicar reglas de negocio a un grupo de registros.

```sql
-- Subir un 10% el salario a todos los empleados de Ventas (Depto 10)
UPDATE Empleados
SET salario = salario * 1.10
WHERE dept_fk = 10;

```

### 3. Ejemplo de fallo por Restricción (`CHECK`)

Supongamos que tenemos la regla `CONSTRAINT chk_precio_positivo CHECK (precio >= 0)`.

```sql
-- Intentamos poner un precio negativo por error
UPDATE Productos
SET precio = -50.00
WHERE prod_id = 200;

-- RESULTADO: La base de datos arroja un ERROR y bloquea el cambio.

```

---

## 🖼️ Infografía Resumen: El comando UPDATE

> Esquema Visual de Ejecución:
> 
> 1. **Identificar:** ¿Qué tabla quiero tocar? (`UPDATE`).
> 2. **Definir:** ¿Qué columnas cambian y a qué valor? (`SET`).
> 3. **Filtrar:** ¿A quiénes afecta exactamente? (`WHERE`).
> - *Si falta el paso 3, afectas a todo el mundo.*

![image.png](image%208.png)