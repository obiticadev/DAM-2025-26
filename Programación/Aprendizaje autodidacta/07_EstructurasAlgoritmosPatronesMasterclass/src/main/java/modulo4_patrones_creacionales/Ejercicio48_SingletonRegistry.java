package modulo4_patrones_creacionales;

/**
 * ============================================================================
 * EJERCICIO 48: Singleton — Registry y Problemas de Testing
 * ============================================================================
 * 📚 Teoría: teoria/04_Patrones_Creacionales.md - Sección 4.2
 *
 * CONTEXTO:
 * Un Singleton Registry permite almacenar y recuperar múltiples singletons
 * por nombre. Es un "contenedor" de instancias únicas. Pero los singletons
 * tienen un problema serio: dificultan el testing porque son estado global.
 *
 * Implementa:
 * 1. SingletonRegistry: almacena objetos por nombre (solo uno por nombre).
 * 2. ConnectionPool (Singleton): simula un pool de conexiones BD.
 * 3. Demuestra el problema del testing y una solución (inyección).
 *
 * RESTRICCIONES:
 * - SingletonRegistry usa dos arrays paralelos: String[] nombres, Object[] instancias.
 * - MAX 20 singletons registrados.
 * - ConnectionPool es Singleton que gestiona un array de "conexiones" booleanas.
 * - Sin java.util (ni Map, ni List).
 * ============================================================================
 */
public class Ejercicio48_SingletonRegistry {

    // ====================================================================
    //  SINGLETON REGISTRY
    // ====================================================================
    static class Registry {
        private static Registry instancia;
        private String[] nombres;
        private Object[] instancias;
        private int count;

        private Registry() {
            nombres = new String[20];
            instancias = new Object[20];
            count = 0;
        }

        public static Registry getInstance() {
            if (instancia == null) instancia = new Registry();
            return instancia;
        }

        public void registrar(String nombre, Object singleton) {
            // TODO 1: Verificar que no exista ya un singleton con ese nombre.
            //         Si existe, lanzar IllegalArgumentException con mensaje descriptivo.
            //         Si no, almacenar nombre e instancia en los arrays.
            //         Incrementar count.
        }

        public Object obtener(String nombre) {
            // TODO 2: Buscar el nombre en el array. Si lo encuentra, retornar la instancia.
            //         Si no, retornar null (o lanzar excepción).
            return null;
        }

        public void listar() {
            // TODO 3: Imprimir todos los singletons registrados:
            //         "  [Logger] → com.ejemplo.Logger@1a2b3c"
            //         Usar el nombre de la clase (getClass().getSimpleName()) y hashCode.
        }
    }

    // ====================================================================
    //  CONNECTION POOL (Singleton)
    // ====================================================================
    static class ConnectionPool {
        private static ConnectionPool instancia;
        private boolean[] conexiones;
        private int maxConexiones;

        private ConnectionPool(int max) {
            this.maxConexiones = max;
            this.conexiones = new boolean[max]; // false = disponible
        }

        public static ConnectionPool getInstance() {
            if (instancia == null) instancia = new ConnectionPool(5);
            return instancia;
        }

        public int obtenerConexion() {
            // TODO 4: Buscar la primera conexión disponible (false).
            //         Marcarla como en uso (true). Retornar su índice.
            //         Si no hay disponibles, retornar -1.
            return -1;
        }

        public void liberarConexion(int id) {
            // TODO 5: Marcar la conexión como disponible (false).
            //         Validar que el id esté en rango.
        }

        public int disponibles() {
            int count = 0;
            for (int i = 0; i < maxConexiones; i++) {
                if (!conexiones[i]) count++;
            }
            return count;
        }
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 48: Singleton Registry ===\n");

        // Registry
        Registry reg = Registry.getInstance();
        ConnectionPool pool = ConnectionPool.getInstance();

        reg.registrar("ConnectionPool", pool);
        System.out.println("Singletons registrados:");
        reg.listar();

        // Usar ConnectionPool
        System.out.println("\n--- Connection Pool ---");
        System.out.println("Disponibles: " + pool.disponibles());

        int conn1 = pool.obtenerConexion();
        int conn2 = pool.obtenerConexion();
        System.out.println("Obtenidas: " + conn1 + ", " + conn2);
        System.out.println("Disponibles: " + pool.disponibles());

        pool.liberarConexion(conn1);
        System.out.println("Liberada " + conn1 + ". Disponibles: " + pool.disponibles());

        // Verificar identidad
        ConnectionPool pool2 = ConnectionPool.getInstance();
        System.out.println("\nMisma instancia: " + (pool == pool2)); // true

        System.out.println("\n=== FIN EJERCICIO 48 ===");
    }
}
