# 📘 Masterclass: Operaciones con Bases de Datos en JDBC (Tema 12)

Este documento ha sido generado con un diseño específico para ser ingerido por **NotebookLM**, optimizando la extracción de conceptos clave, lógica subyacente y trampas comunes para un examen tipo test sobre **JDBC (Java Database Connectivity)**.

---

## 🎯 1. Conceptos Fundamentales (Carne de Examen)

Para afrontar un test donde deberás leer fragmentos de código, es fundamental que domines las clases e interfaces principales de `java.sql.*` y las sutiles diferencias entre sus métodos.

### 🔑 El Flujo Básico de JDBC
Independientemente de la operación, el patrón siempre sigue una estructura similar:
1. **Conformar la consulta:** Guardar la sentencia SQL en un `String`.
2. **Preparar el Statement:** Crear un objeto `PreparedStatement` a partir de la conexión.
3. **Sustituir parámetros (si los hay):** Usar los métodos `setX()` para rellenar las interrogaciones `?`.
4. **Ejecutar:** Usar `executeQuery()` o `executeUpdate()`.
5. **Procesar (sólo en SELECT):** Recorrer el `ResultSet` con `next()` y extraer datos con `getX()`.

---

## 🛠️ 2. Análisis de Operaciones (Sintaxis y Lógica)

### A. Obteniendo Datos (`SELECT`)
**Método clave:** `executeQuery()`
**Retorna:** Un objeto `ResultSet`.

```java
String sql = "SELECT p.nombre, f.nombre, p.precio FROM productos p INNER JOIN fabricante f ON p.codigo_fabricante = f.codigo WHERE p.precio < ?";
PreparedStatement pstmt = conexion.prepareStatement(sql);
pstmt.setDouble(1, 100.0); // ⚠️ TRAMPA DE TEST: ¡Los índices de los ? empiezan en 1, no en 0!

ResultSet rs = pstmt.executeQuery(); // ⚠️ TRAMPA: Si pones executeUpdate() aquí, dará error.

while(rs.next()) { // ⚠️ TRAMPA: El cursor inicialmente apunta a "antes de la primera fila". Hay que llamar a next() para leer la primera.
    String nomProd = rs.getString(1); // Se puede obtener por índice (1-based)...
    String nomFab = rs.getString("nombre_fabricante"); // ...o por el nombre de la columna.
    double precio = rs.getDouble("precio");
    System.out.println(nomProd + " / " + nomFab + " / " + precio);
}
```

### B. Insertando Datos (`INSERT`)
**Método clave:** `executeUpdate()`
**Retorna:** Un `int` (el número de filas afectadas por la instrucción).

```java
String sql = "INSERT INTO fabricante (codigo, nombre) VALUES (?, ?)";
PreparedStatement pstmt = conexion.prepareStatement(sql);
pstmt.setInt(1, 10);
pstmt.setString(2, "Asus");

int filasAfectadas = pstmt.executeUpdate(); // ⚠️ TRAMPA: Devuelve un int.
if (filasAfectadas == 1) {
    System.out.println("Inserción correcta.");
}
```

### C. Actualizando (`UPDATE`) y Borrando (`DELETE`)
La mecánica es idéntica a la del `INSERT`.
- **UPDATE:** Cambia valores existentes.
- **DELETE:** Elimina filas completas.

**Lógica detrás de `executeUpdate()` para UPDATE/DELETE:**
Si intentas borrar un registro que no existe, `executeUpdate()` **NO lanzará una excepción**. Simplemente devolverá `0` (0 filas afectadas). Esto es una pregunta muy típica en tests.

---

## 🚀 3. Resolución de los Ejercicios (Aplicación Práctica)

A continuación, se desarrolla el código exacto que da respuesta a las directrices de los ejercicios, aplicando las buenas prácticas y patrones JDBC (como el uso del `Singleton` para la conexión que usáis en clase).

### Ejercicio 1: Descuentos en Tienda
*Objetivo:* Listar productos < 100€, rebajar un 20%, listar, devolver al valor original.

```java
public void ejercicio1() {
    Connection conn = Conexion.getConexion(); // Patrón Singleton asumido
    
    try {
        // 1. Mostrar < 100
        String sqlSelect = "SELECT p.nombre AS p_nom, f.nombre AS f_nom, p.precio " +
                           "FROM productos p JOIN fabricante f ON p.codigo_fabricante = f.codigo " +
                           "WHERE p.precio < 100";
        PreparedStatement psSelect = conn.prepareStatement(sqlSelect);
        ResultSet rs = psSelect.executeQuery();
        System.out.println("--- PRODUCTOS < 100€ ---");
        while(rs.next()){
            System.out.println(rs.getString("p_nom") + " / " + rs.getString("f_nom") + " / " + rs.getDouble("precio"));
        }
        
        // 2. Aplicar 20% descuento (precio = precio * 0.8)
        String sqlUpdate = "UPDATE productos SET precio = precio * 0.8";
        PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
        int filasRebajadas = psUpdate.executeUpdate();
        System.out.println("\nSe ha aplicado descuento a " + filasRebajadas + " productos.");
        
        // (Aquí se podría volver a lanzar el SELECT general para mostrar cómo han quedado)
        
        // 3. Devolver al valor original (precio = precio / 0.8)
        String sqlRestore = "UPDATE productos SET precio = precio / 0.8";
        PreparedStatement psRestore = conn.prepareStatement(sqlRestore);
        psRestore.executeUpdate();
        System.out.println("\nPrecios devueltos a su valor original.");
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
```

### Ejercicio 2: Traspaso de Empleados a Productos
*Objetivo:* Crear un fabricante nuevo. Leer empleados y convertirlos en productos (Nombre = producto, últimos 3 NIF = precio). Borrar todo después.

```java
public void ejercicio2() {
    Connection conn = Conexion.getConexion();
    
    try {
        // 1. Crear nuevo fabricante
        String sqlFab = "INSERT INTO fabricante (codigo, nombre) VALUES (?, ?)";
        PreparedStatement psFab = conn.prepareStatement(sqlFab);
        int idFabricanteNuevo = 999; 
        psFab.setInt(1, idFabricanteNuevo);
        psFab.setString(2, "Fabricante Empleados");
        psFab.executeUpdate();
        
        // 2. Leer Empleados
        String sqlEmp = "SELECT nombre, nif FROM empleados";
        PreparedStatement psEmp = conn.prepareStatement(sqlEmp);
        ResultSet rsEmp = psEmp.executeQuery();
        
        // Preparamos el Statement del INSERT fuera del bucle para optimizar (¡Importante!)
        String sqlInsertProd = "INSERT INTO productos (nombre, precio, codigo_fabricante) VALUES (?, ?, ?)";
        PreparedStatement psInsertProd = conn.prepareStatement(sqlInsertProd);
        
        // 3. Crear un producto por cada empleado
        while(rsEmp.next()) {
            String nombreEmp = rsEmp.getString("nombre");
            String nif = rsEmp.getString("nif");
            
            // Lógica para obtener los 3 últimos dígitos del NIF
            // Asumiendo formato de string como "12345678A"
            String substringNif = nif.replaceAll("[^0-9]", ""); // Quitar letras por si acaso
            String ultimos3 = substringNif.substring(substringNif.length() - 3);
            double precioCalculado = Double.parseDouble(ultimos3);
            
            // Ejecutamos el INSERT para este empleado
            psInsertProd.setString(1, nombreEmp);
            psInsertProd.setDouble(2, precioCalculado);
            psInsertProd.setInt(3, idFabricanteNuevo);
            psInsertProd.executeUpdate();
        }
        
        // 4. Mostrar toda la tabla (lógica de SELECT estándar que omitimos por brevedad)
        
        // 5. Borrar todo lo creado (Productos primero por la clave foránea, luego Fabricante)
        String sqlDelProd = "DELETE FROM productos WHERE codigo_fabricante = ?";
        PreparedStatement psDelProd = conn.prepareStatement(sqlDelProd);
        psDelProd.setInt(1, idFabricanteNuevo);
        psDelProd.executeUpdate();
        
        String sqlDelFab = "DELETE FROM fabricante WHERE codigo = ?";
        PreparedStatement psDelFab = conn.prepareStatement(sqlDelFab);
        psDelFab.setInt(1, idFabricanteNuevo);
        psDelFab.executeUpdate();
        
        System.out.println("Limpieza completada.");
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
```

---

## 🏗️ 4. Conceptos Avanzados de Arquitectura JDBC (del Proyecto)

A partir del código del proyecto principal de la asignatura (Gestor de Biblioteca), existen patrones de uso de JDBC más complejos que son **cruciales** y de alto valor para preguntas tipo test de nivel avanzado:

### A. Gestión de Transacciones (`setAutoCommit`)
Por defecto, cada instrucción SQL en JDBC hace un *Auto-Commit* (se guarda inmediatamente). En operaciones dependientes (ej: realizar un `INSERT` en `prestamos` y acto seguido un `UPDATE` restando una copia en `libros`), si la segunda falla, la primera debe deshacerse para no corromper la consistencia de datos.

**Sintaxis clave:**
```java
conn.setAutoCommit(false); // 1. Deshabilitamos el auto-guardado automático
try {
    pstmt1.executeUpdate(); // Ej: Insertar préstamo
    pstmt2.executeUpdate(); // Ej: Restar stock de libro
    conn.commit();          // 2. Si las dos van bien, confirmamos TODO a la vez.
} catch(SQLException e) {
    conn.rollback();        // 3. Si ALGO falla, deshacemos TODO y no se guarda nada.
}
```
* **Trampa de Test:** ¿Qué ocurre si haces un `INSERT`, da fallo el siguiente `UPDATE` pero olvidaste hacer `conn.rollback()`? Los cambios quedan bloqueados en una transacción colgada y no se reflejan para otros usuarios, provocando fugas de recursos en la base de datos.

### B. Recuperación de IDs Autoincrementales (`RETURN_GENERATED_KEYS`)
Cuando haces un `INSERT` en una tabla donde el ID es `AUTOINCREMENT`, no debes hacer un `SELECT MAX(id)` para saber qué id le ha tocado (daría problemas de concurrencia). JDBC lo recupera al vuelo así:

```java
// 1. Avisar al PreparedStatement mediante una constante
PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
pstmt.executeUpdate();

// 2. Extraer las claves generadas
ResultSet rsKeys = pstmt.getGeneratedKeys();
if(rsKeys.next()) {
    int nuevoIdGenerado = rsKeys.getInt(1); // Siempre en la columna 1
}
```

### C. Bloque `Try-With-Resources` (La sintaxis recomendada)
Se emplea masivamente en la arquitectura DAO. Dado que `Connection`, `PreparedStatement` y `ResultSet` implementan la interfaz `AutoCloseable`, si se instancian entre los paréntesis del `try`, se cierran solas.

```java
try (Connection conn = Conexion.getConexion();
     PreparedStatement pstmt = conn.prepareStatement(sql)) {
     // Operaciones JDBC...
} 
// ¡AQUÍ: Se ejecuta conn.close() y pstmt.close() de forma automática e invisible!
```
* **Trampa de Test:** Si ves un `try(...)` de este tipo en el test, recuerda que **no hace falta bloque `finally` para cerrar los recursos**, lo cual es distinto al viejo estilo del bloque `try-catch` de Java 6.

### D. Gestión de Valores Nulos (`setNull`)
Cuando necesitamos insertar explícitamente un valor nulo en una columna de la BBDD (por ejemplo, porque la ubicación de un e-book no existe), no se pasa `"null"`, sino que se invoca `setNull` referenciando a `java.sql.Types`.

```java
if (libro instanceof LibroElectronico) {
    pstmt.setString(1, "PDF");
} else {
    pstmt.setNull(1, Types.VARCHAR); // Inserta NULL en BBDD para el formato
}
```

---

## 🚨 5. TOP 5 Trampas de Examen Tipo Test (Memoriza esto)

1. **Índices en las Bases de Datos vs Arrays:** En Java, los arrays y las listas empiezan en el índice `0`. En JDBC, los `ResultSet` (`getString(1)`) y los `PreparedStatement` (`setString(1, "valor")`) empiezan en el índice **`1`**.
2. **`executeQuery()` vs `executeUpdate()`:** Si en una pregunta ves un código que hace `pstmt.executeQuery()` pasándole un `UPDATE` o un `INSERT`, la respuesta correcta es que **"lanzará una excepción"**.
3. **El cursor del `ResultSet`:** ¿A dónde apunta el cursor cuando haces `executeQuery()`? **No apunta a la primera fila**. Apunta a una posición *anterior* a la primera fila. Es estrictamente obligatorio hacer al menos un `rs.next()` antes de hacer el primer `get`.
4. **Cierre de recursos:** ¿Qué pasa si cierras el `Connection`? Se cierran automáticamente todos sus `Statement` y `ResultSet` asociados. Aún así, la buena práctica es cerrarlos individualmente o usar un bloque *try-with-resources*.
5. **Valores de retorno de `executeUpdate()`:** ¿Qué devuelve un `DELETE` si el registro no existía en la base de datos? La respuesta NO es que salte el `catch`. La respuesta es que devuelve el entero `0`.
