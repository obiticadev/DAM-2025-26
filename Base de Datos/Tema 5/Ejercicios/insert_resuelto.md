**1. Inserción simplificada:**
Al no especificar los campos, debemos seguir estrictamente el orden en que se definieron las columnas en el `CREATE TABLE` (nombre, edad, ciudad).
```sql
INSERT INTO antiguo VALUES ('keko', 31, 'Aranjuez');
```

**2. Inserción por campos:**
Aquí especificamos el orden de las columnas entre paréntesis antes de la cláusula `VALUES`.
```sql
INSERT INTO antiguo (edad, ciudad, nombre) 
VALUES (25, 'Alcorcón', 'michi');
```

**3. Inserción múltiple:**
En Oracle, la forma más eficiente de insertar varias filas en una sola sentencia es usar `INSERT ALL` o una combinación de `SELECT` con `UNION ALL`.

*Opción A (Usando INSERT ALL):*
```sql
INSERT ALL 
  INTO antiguo (nombre, edad, ciudad) VALUES ('michel', 45, 'paris')
  INTO antiguo (nombre, edad, ciudad) VALUES ('julian', 34, 'london')
  INTO antiguo (nombre, edad, ciudad) VALUES ('ana', 32, 'oviedo')
SELECT * FROM DUAL;
```

*Opción B (Usando SELECT UNION ALL):*
```sql
INSERT INTO antiguo (nombre, edad, ciudad)
SELECT 'michel', 45, 'paris' FROM DUAL UNION ALL
SELECT 'julian', 34, 'london' FROM DUAL UNION ALL
SELECT 'ana', 32, 'oviedo' FROM DUAL;
```

---

### 3. Transferencia de datos

**4. Inserción condicional:**
Para pasar datos de una tabla a otra usamos `INSERT INTO ... SELECT`. Tal como indica tu documento **5.B (pág. 5)**, en este caso no se usa la palabra `VALUES`.

```sql
INSERT INTO alum (nombre, edad, ciudad)
SELECT nombre, edad, ciudad 
FROM antiguo 
WHERE edad > 30;
```

*(Nota: Como ambas tablas tienen exactamente la misma estructura, también podrías usar `INSERT INTO alum SELECT * FROM antiguo WHERE edad > 30;`)*

---

### Resumen de lo aplicado (según tus apuntes):
*   **Punto 1 y 2:** Aplicamos la sintaxis básica de `INSERT INTO`.
*   **Punto 3:** Aplicamos el concepto de "insertar a partir de una consulta" usando la tabla virtual `DUAL`.
*   **Punto 4:** Aplicamos la inserción basada en el resultado de una subconsulta con filtro `WHERE`.