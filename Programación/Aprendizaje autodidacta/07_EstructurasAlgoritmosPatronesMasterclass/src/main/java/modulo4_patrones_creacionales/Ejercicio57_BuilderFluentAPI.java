package modulo4_patrones_creacionales;

/**
 * ============================================================================
 * EJERCICIO 57: Builder — Fluent API (Consulta SQL)
 * ============================================================================
 * 📚 Teoría: teoria/04_Patrones_Creacionales.md - Sección 4.5
 *
 * CONTEXTO:
 * Fluent API (Method Chaining) es un estilo donde cada método retorna
 * 'this' para encadenar llamadas de forma legible. Es la forma más
 * popular del Builder en la industria moderna.
 *
 * Construirás un QueryBuilder que genera sentencias SQL de forma
 * fluida y type-safe, evitando concatenación manual de strings.
 *
 * Uso esperado:
 *   String sql = new QueryBuilder()
 *       .select("nombre", "edad", "email")
 *       .from("usuarios")
 *       .where("edad > 18")
 *       .and("pais = 'ES'")
 *       .orderBy("nombre", "ASC")
 *       .limit(10)
 *       .build();
 *
 * RESTRICCIONES:
 * - Almacenar las partes de la query en campos individuales.
 * - Campos de columnas y condiciones en arrays manuales (max 20).
 * - build() valida que al menos SELECT y FROM estén definidos.
 * - Sin java.util ni StringBuilder (puedes usar String concatenation).
 * ============================================================================
 */
public class Ejercicio57_BuilderFluentAPI {

    static class QueryBuilder {
        private String[] columnas;
        private int numColumnas;
        private String tabla;
        private String[] condiciones;
        private int numCondiciones;
        private String orderByColumn;
        private String orderByDirection;
        private int limitVal;
        private boolean hasLimit;

        public QueryBuilder() {
            this.columnas = new String[20];
            this.condiciones = new String[20];
            this.numColumnas = 0;
            this.numCondiciones = 0;
            this.hasLimit = false;
        }

        public QueryBuilder select(String... cols) {
            // TODO 1: Añadir las columnas al array.
            //         Si se pasa "*", guardar solo "*".
            //         Retornar this para chaining.
            return this;
        }

        public QueryBuilder from(String tabla) {
            // TODO 2: Establecer la tabla. Retornar this.
            return this;
        }

        public QueryBuilder where(String condicion) {
            // TODO 3: Añadir la condición al array de condiciones.
            //         Esta es la PRIMERA condición (con WHERE).
            //         Retornar this.
            return this;
        }

        public QueryBuilder and(String condicion) {
            // TODO 4: Añadir condición con conector AND.
            //         Almacenar como "AND <condicion>" en el array.
            //         Retornar this.
            return this;
        }

        public QueryBuilder or(String condicion) {
            // Almacenar como "OR <condicion>".
            return this;
        }

        public QueryBuilder orderBy(String columna, String direccion) {
            // TODO 5: Almacenar columna y dirección para ORDER BY.
            //         Retornar this.
            return this;
        }

        public QueryBuilder limit(int limite) {
            this.limitVal = limite;
            this.hasLimit = true;
            return this;
        }

        public String build() {
            // TODO 6: Validar que haya al menos una columna y una tabla.
            //         Si no, lanzar IllegalStateException.
            //         Construir la query SQL concatenando:
            //           "SELECT col1, col2, col3"
            //           " FROM tabla"
            //           " WHERE cond1 AND cond2 OR cond3"  (si hay condiciones)
            //           " ORDER BY col ASC"                 (si hay orderBy)
            //           " LIMIT 10"                         (si hay limit)
            //         Retornar la query como String.
            return "";
        }
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 57: Builder Fluent API ===\n");

        // Consulta compleja
        // String sql1 = new QueryBuilder()
        //     .select("nombre", "edad", "email")
        //     .from("usuarios")
        //     .where("edad > 18")
        //     .and("pais = 'ES'")
        //     .and("activo = true")
        //     .orderBy("nombre", "ASC")
        //     .limit(50)
        //     .build();
        // System.out.println("Query 1: " + sql1);

        // Consulta simple
        // String sql2 = new QueryBuilder()
        //     .select("*")
        //     .from("productos")
        //     .build();
        // System.out.println("Query 2: " + sql2);

        // Consulta con OR
        // String sql3 = new QueryBuilder()
        //     .select("titulo", "autor")
        //     .from("libros")
        //     .where("genero = 'Ciencia Ficción'")
        //     .or("genero = 'Fantasía'")
        //     .orderBy("titulo", "DESC")
        //     .limit(10)
        //     .build();
        // System.out.println("Query 3: " + sql3);

        // Validación: sin FROM debe fallar
        // try {
        //     new QueryBuilder().select("*").build();
        // } catch (IllegalStateException e) {
        //     System.out.println("\n✅ Validación: " + e.getMessage());
        // }

        System.out.println("(Implementa los TODOs para ver resultados)");

        System.out.println("\n=== FIN EJERCICIO 57 ===");
    }
}
