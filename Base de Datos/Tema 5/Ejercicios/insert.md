# Ejercicio: Sentencia INSERT INTO

## 1. Definición de la tabla inicial
```sql
CREATE TABLE antiguo (
    nombre VARCHAR2(30) PRIMARY KEY,
    edad NUMBER(3),
    ciudad VARCHAR2(40)
);
```

---

## 2. Tareas de inserción

1. **Inserción simplificada:** Insertar en la tabla `antiguo` sin poner los nombres de los campos al alumno **‘keko’**, de **31 años** y de **Aranjuez**.

2. **Inserción por campos:** Insertar en la tabla `antiguo` poniendo los campos en el orden **edad, ciudad y nombre** al alumno **‘michi’**, de edad **25** y de **Alcorcón**.

3. **Inserción múltiple:** Insertar varios alumnos de golpe en la misma sentencia:
    * ‘michel’, 45, paris
    * ‘julian’, 34, london
    * ‘ana’, 32, oviedo

---

## 3. Transferencia de datos

**Definición de la tabla de destino:**
```sql
CREATE TABLE alum (
    nombre VARCHAR2(30) PRIMARY KEY,
    edad NUMBER(3),
    ciudad VARCHAR2(40)
);
```

4. **Inserción condicional:** Insertar en la tabla `alum` aquellos alumnos de la tabla `antiguo` que tengan **más de 30 años**.