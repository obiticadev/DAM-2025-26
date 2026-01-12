# Entrega 8: Manipulación de Datos - Inserción (INSERT).

# 📥 Inserción de Datos: Comando `INSERT`

El comando `INSERT INTO` es la instrucción fundamental de **DML** (Data Manipulation Language) para añadir nuevos registros o filas a nuestras tablas. Existen dos métodos principales para realizar esta operación.

---

## 🏗️ Métodos de Inserción

### 1. Inserción Implícita (Por Posición)

En este método **no** se especifican los nombres de las columnas. La base de datos asume que los valores se entregan en el **mismo orden exacto** en el que se definieron las columnas al crear la tabla.

```sql
INSERT INTO Empleados
VALUES (101, 'Ana García', 2500.50, 'Ventas');

```

> ⚠️ Riesgo: Si en el futuro se añade una columna nueva a la tabla con ALTER TABLE, todos los scripts que usen este método dejarán de funcionar porque el número de valores ya no coincidirá.
> 

### 2. Inserción Explícita (Por Nombre de Columna)

Es el **estándar profesional**. Se indican primero las columnas que se van a rellenar y luego los valores.

```sql
INSERT INTO Empleados (emp_id, nombre, departamento)
VALUES (102, 'Luis López', 'Marketing');

```

- **Ventaja:** No importa el orden físico de las columnas en la tabla.
- **Flexibilidad:** Puedes omitir columnas que tengan un valor `DEFAULT` o que permitan `NULL`.

---

## ⚖️ Comparativa de Métodos

| Característica | Método Implícita | Método Explícita |
| --- | --- | --- |
| **Sintaxis** | `INSERT INTO Tabla VALUES (...)` | `INSERT INTO Tabla (cols) VALUES (...)` |
| **Seguridad** | Baja (sensible a cambios) | Alta (robusta ante cambios) |
| **Columnas** | Debes poner todas obligatoriamente | Puedes omitir columnas opcionales |
| **Recomendación** | Solo para scripts rápidos | **Obligatorio en desarrollo profesional** |

---

## 📋 Gestión de valores NULL y DEFAULT

Al usar el método explícito, podemos gestionar cómo se rellenan los datos omitidos de dos formas:

1. **Omisión automática:** Si no mencionas una columna en la lista, la BBDD inserta el valor `DEFAULT` (si existe) o `NULL`.
2. **Palabras clave:** Puedes usar explícitamente las palabras `NULL` o `DEFAULT` en la sección de valores.

```sql
-- Forzando el uso de valores por defecto y nulos
INSERT INTO Productos (id, nombre, precio, stock)
VALUES (50, 'Teclado RGB', DEFAULT, NULL);

```

---

## 🖼️ Infografía Resumen: Inserción de Datos (INSERT)

> Mapeo de Datos:
> 
> - **Método Implícito:** Los valores deben "encajar" uno a uno con la estructura física de la tabla.
> - **Método Explícito:** Crea un "puente" visual entre el nombre de la columna y su valor, permitiendo ignorar aquellas columnas que la base de datos puede rellenar sola (como IDs autoincrementales, fechas por defecto o campos opcionales).

![image.png](image%207.png)