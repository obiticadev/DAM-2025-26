# Cuaderno de Ejercicios: SQL y Gestión de Tablas

## Ejercicio 0
**Crear tabla MESA**
*(Ejercicio corregido en clase)*.

> **Nota:** Si necesitas un código de ejemplo para probar, sería algo así:
```sql
CREATE TABLE MESA (
    ID_MESA    NUMBER CONSTRAINT PK_MESA PRIMARY KEY,
    UBICACION  VARCHAR2(20),
    CAPACIDAD  NUMBER
);
```

---

## Ejercicio 1
**Crear la tabla ESTUDIANTE**

Se requiere crear una tabla con las siguientes especificaciones:
*   **NOMBRE**: 20 caracteres. Debe ser la **Clave Primaria** (Constraint con nombre).
*   **APELLIDO**: 30 caracteres. No puede estar vacío (**NOT NULL**).
*   **DOMICILIO**: 30 caracteres. No se puede repetir (**UNIQUE**).
*   **TELEFONO**: 11 caracteres.

**Solución SQL:**
```sql
CREATE TABLE ESTUDIANTE (
    NOMBRE    VARCHAR2(20) CONSTRAINT ES_NO_PK PRIMARY KEY,
    APELLIDO  VARCHAR2(30) NOT NULL,
    DOMICILIO VARCHAR2(30) UNIQUE,
    TELEFONO  VARCHAR2(11)
);
```

---

## Ejercicio 2
**Consultar todas las tablas del sistema**

Para ver todas las tablas a las que tienes acceso (tuyas y del sistema), utilizamos la vista `ALL_TABLES`.

**Solución SQL:**
```sql
SELECT * FROM ALL_TABLES;
```

---

## Ejercicio 3
**Borrar una tabla**

El objetivo es eliminar la tabla llamada "TAL".

> *Nota: Para que este comando funcione sin errores, la tabla "TAL" debe existir previamente. Si necesitas crearla para probar, usa: `CREATE TABLE TAL (ID NUMBER);`*

**Solución SQL:**
```sql
DROP TABLE TAL;
```

---

## Ejercicio 4
**Verificar la eliminación de tablas**

Comprobar que la tabla "TAL" ha desaparecido utilizando dos métodos distintos:

1.  **Método Gráfico:**
    *   Ir al menú de la izquierda **TABLES**.
    *   Pulsar el botón azul de **Reseteo/Refrescar**.
    *   *Verificar visualmente que "TAL" ya no está.*

2.  **Método por Comandos:**
    *   Ejecutar nuevamente la consulta general.
    
    ```sql
    SELECT * FROM ALL_TABLES;
    ```
    *   *Buscar en los resultados para confirmar que la fila correspondiente a "TAL" ya no aparece.*

---

## Ejercicio 5
**Filtrar tablas por propietario**

Ver solo las tablas que pertenecen al usuario específico 'ALUMNO'.

**Solución SQL:**
```sql
SELECT * FROM ALL_TABLES WHERE OWNER = 'ALUMNO';
```
