package com.masterclass.nivel12_crud_create_read;

/**
 * EJERCICIO 45: Recuperando Almas (El Extractor Crudo ResultSet READ)
 * 
 * OBJETIVO: 
 * Desentrañar la magia bidireccional mediante el consumo iterado de un Cursor. 
 * Obligaremos a base de datos a entregarnos el total de filas escritas de forma persistida.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '12_CRUD_Create_Read.md' (Sección: "Absrobiendo Matrices ResultSet")
 */
public class Ejercicio45_ResultSetRead {

    // TODO 1: Asegura la persistencia de las rutas (URL constante).

    public void llistarTodoCrudo() {
        
        // TODO 2: Redacta en plano asíncrono crudo la orden universal transversal (SELECT * FROM...).
        
        // TODO 3: Utiliza inteligentemente tu 'try-with-resources' para envolver NO SOLO `Connection` sino también el `Statement` subyacente.
        // HINT: 'try (Connection c = ... ; Statement st = c.createStatement()) { ... }'
        
        // TODO 4: Emite un letal `.executeQuery(...)` en el body del test pasándole tu string en crudo.
        // Guárdalo puramente en la asignatura 'ResultSet'.

        // TODO 5: Instancia el bucle algorítmico obligatorio `while (rs.next()) { ... }`.
        
        // TODO 6: Al estar dentro del bucle, Java tiene garantizado que el "cursor" pisa datos.
        // Usa `rs.getInt("id")` y `rs.getString("nombre")` y emite un Sysout combinando la fila pura.
        
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- EL DETONADOR DE RECOPILACIÓN ---");
        
        // TODO 7 (PRUEBA): Analiza en consola cuantas réplicas del 'AdminSupremo' has insertado sin
        // querer en pruebas de niveles anteriores. La BDD no perdona. Visualízalas en consola purgada.
        
        // new Ejercicio45_ResultSetRead().llistarTodoCrudo();
    }
}
