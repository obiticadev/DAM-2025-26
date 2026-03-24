¡Claro que sí! En Markdown se utiliza la etiqueta HTML `<details>` para crear desplegables. Es perfecto para practicar y luego comprobar si lo has hecho bien.

Aquí tienes el bloque de ejercicios preparado para tu examen:

# 📝 Ejercicios de Examen: SQL (Oracle)

### 1. Inserción Estándar
**Enunciado:** Inserta un nuevo empleado en la tabla `EMPLEADOSE`. El empleado se apellida 'PEÑA', su número es 8000, su oficio es 'ANALISTA', su director es el 7782, la fecha de alta es hoy (`'24/03/2026'`), tiene un salario de 3000, no tiene comisión y pertenece al departamento 20.

<details>
<summary>✅ Ver Solución</summary>

```sql
INSERT INTO EMPLEADOSE (EMPNU, APELLIDO, OFICIO, DIRECTOR, FECHALTA, SALARIO, COMISION, DEPNU)
VALUES (8000, 'PEÑA', 'ANALISTA', 7782, TO_DATE('24/03/2026', 'DD/MM/YYYY'), 3000, NULL, 20);
```
</details>

---

### 2. Inserción Múltiple con DUAL
**Enunciado:** Inserta de una sola vez (usando una única sentencia `INSERT`) dos nuevos departamentos en la tabla `DEPARTAMENTOSE`:
*   (60, 'IT', 'BILBAO')
*   (70, 'RRHH', 'MALAGA')

<details>
<summary>✅ Ver Solución</summary>

```sql
INSERT ALL
	INTO DEPARTAMENTOSE VALUES (60, 'IT', 'BILBAO')
	INTO DEPARTAMENTOSE VALUES (70, 'RRHH', 'MALAGA')
SELECT * FROM DUAL;
```
</details>

---

### 3. Transferencia de datos (Subconsulta)
**Enunciado:** Crea una tabla llamada `PEDIDOS_99` (con columnas `ID` y `FECHA`) e inserta en ella el `PEDIDONU` y la `FECHAPEDIDO` de todos los pedidos realizados en el año 1999.

<details>
<summary>✅ Ver Solución</summary>

```sql
-- Paso 1: Crear la tabla
CREATE TABLE PEDIDOS_99 (
    ID NUMBER(4),
    FECHA DATE
);

-- Paso 2: Transferir datos
INSERT INTO PEDIDOS_99 (ID, FECHA)
SELECT PEDIDONU, FECHAPEDIDO 
FROM PEDIDOSE 
WHERE FECHAPEDIDO BETWEEN TO_DATE('01/01/1999','DD/MM/YYYY') AND TO_DATE('31/12/1999','DD/MM/YYYY');
```
</details>

---

### 4. Update Complejo (Subconsultas en SET y WHERE)
**Enunciado:** Actualiza el salario de todos los empleados del departamento de 'INVESTIGACION'. El nuevo salario debe ser igual al salario mínimo que cobra un 'DIRECTOR' en toda la empresa.

<details>
<summary>✅ Ver Solución</summary>

```sql
UPDATE EMPLEADOSE
SET SALARIO = (SELECT MIN(SALARIO) FROM EMPLEADOSE WHERE OFICIO = 'DIRECTOR')
WHERE DEPNU = (SELECT DEPNU FROM DEPARTAMENTOSE WHERE DNOMBRE = 'INVESTIGACION');
```
</details>

---

### 5. Control de Transacciones (Rollback y Savepoint)
**Enunciado:** Realiza las siguientes acciones en orden:
1.  Actualiza el stock de todos los productos de la tabla `PRODUCTOSE` a 0.
2.  Crea un punto de control llamado `punto_seguro`.
3.  Borra a todos los clientes de la tabla `CLIENTESE`.
4.  Deshaz **únicamente** el borrado de los clientes (vuelve al punto de control).
5.  Confirma los cambios permanentemente.

<details>
<summary>✅ Ver Solución</summary>

```sql
-- 1. Actualizar stock
UPDATE PRODUCTOSE SET STOCKDISPONIBLE = 0;

-- 2. Punto de control
SAVEPOINT punto_seguro;

-- 3. Borrar clientes
DELETE FROM CLIENTESE;

-- 4. Deshacer solo el borrado
ROLLBACK TO SAVEPOINT punto_seguro;

-- 5. Confirmar
COMMIT;
```
</details>

---

### 💡 Tips para DBeaver durante el examen:
*   **Desactivar Auto-commit:** En la barra superior, busca el icono de la "A" con un check verde. Ponlo en **"Manual Commit"** (icono naranja/gris). Si no lo haces, no podrás usar `ROLLBACK` porque cada línea se guardará automáticamente.
*   **Log de ejecución:** Si algo falla, mira la pestaña "Output" abajo.
*   **Ver cambios:** Después de un `ROLLBACK`, haz un `SELECT` a la tabla para confirmar que los datos "han vuelto".