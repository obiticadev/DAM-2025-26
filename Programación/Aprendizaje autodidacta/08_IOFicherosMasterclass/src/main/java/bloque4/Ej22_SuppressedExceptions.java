package bloque4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * EJERCICIO 22 — Suppressed Exceptions
 * Teoria: teoria/04_Gestion_Segura_Recursos.md (seccion 4)
 *
 * Contexto: Al cerrar recursos, a veces el cierre tambien falla.
 * Java 7 introdujo las suppressed exceptions para no perder informacion.
 * Exploras como detectarlas y manejarlas.
 */
public class Ej22_SuppressedExceptions {

    /**
     * Recurso que puede lanzar excepcion al cerrarse.
     */
    public static class RecursoFragil implements AutoCloseable {
        private final String nombre;
        private final boolean fallaAlCerrar;
        private boolean cerrado = false;

        public RecursoFragil(String nombre, boolean fallaAlCerrar) {
            this.nombre = nombre;
            this.fallaAlCerrar = fallaAlCerrar;
        }

        public void trabajar() throws IOException {
            if (cerrado) throw new IllegalStateException("Recurso ya cerrado");
        }

        @Override
        public void close() throws IOException {
            cerrado = true;
            if (fallaAlCerrar) {
                throw new IOException("Error cerrando " + nombre);
            }
        }

        public String getNombre() { return nombre; }
        public boolean estaCerrado() { return cerrado; }
    }

    /**
     * Intenta usar un RecursoFragil. Si falla al cerrar, captura la excepcion
     * y devuelve el mensaje del error. Si no falla, devuelve "OK".
     *
     * @param fallaAlCerrar si el recurso debe fallar al cerrarse
     * @return "OK" o el mensaje de la excepcion
     */
    public static String probarCierre(boolean fallaAlCerrar) {
        // TODO 1: Usar try-with-resources con RecursoFragil("Test", fallaAlCerrar).
        //         Llamar a trabajar(). Capturar IOException.
        //         Si se captura, devolver e.getMessage(). Si no, devolver "OK".
        return "";
    }

    /**
     * Provoca una excepcion en try Y otra en close(), y devuelve
     * el numero de suppressed exceptions.
     *
     * @return numero de suppressed exceptions (esperado: 1)
     */
    public static int contarSuppressed() {
        // TODO 2: Crear try-with-resources con RecursoFragil("X", true).
        //         Dentro del try, lanzar una IOException("Error principal").
        //         En el catch, obtener e.getSuppressed() y devolver su length.
        return 0;
    }

    /**
     * Devuelve un informe con la excepcion principal y las suppressed.
     * Formato:
     * "Principal: [mensaje]
     * Suppressed 1: [mensaje]
     * Suppressed 2: [mensaje]
     * ..."
     *
     * @param excepcion excepcion a analizar
     * @return informe formateado
     */
    public static String informeSuppressed(Exception excepcion) {
        // TODO 3: Obtener mensaje principal. Recorrer getSuppressed().
        //         Formatear con StringBuilder.
        return "";
    }

    /**
     * Crea N RecursosFragiles, los usa en secuencia (no en try-with-resources),
     * y cierra todos manualmente. Si alguno falla al cerrar, anade la excepcion
     * como suppressed de la primera que fallo.
     *
     * @param n             numero de recursos
     * @param fallaIndice   indice del recurso que debe fallar al cerrar (-1 = ninguno)
     * @return excepcion resultante o null si no hubo error
     */
    public static Exception cerrarConSuppressed(int n, int fallaIndice) {
        // TODO 4: Crear una lista de RecursoFragil. El que tenga indice fallaIndice
        //         se crea con fallaAlCerrar=true, el resto con false.
        //         Cerrar todos en un bucle. Capturar IOExceptions.
        //         La primera se guarda como principal, las siguientes como suppressed.
        //         Devolver la excepcion principal o null.
        return null;
    }

    /**
     * Verifica si una excepcion tiene alguna suppressed exception
     * cuyo mensaje contenga la palabra dada.
     *
     * @param e       excepcion a analizar
     * @param palabra palabra a buscar
     * @return true si alguna suppressed contiene la palabra
     */
    public static boolean tieneSuppressedConPalabra(Exception e, String palabra) {
        // TODO 5: Si e es null, devolver false.
        //         Recorrer e.getSuppressed(). Si alguna getMessage() contiene la palabra
        //         (case insensitive), devolver true. Si no, devolver false.
        return false;
    }

    /**
     * Ejecuta una accion con un recurso y devuelve el estado final:
     * "EXITO" si todo fue bien, "ERROR_ACCION" si la accion fallo,
     * "ERROR_CIERRE" si el cierre fallo, "ERROR_AMBOS" si ambos fallaron.
     *
     * @param fallaAccion si la accion debe fallar
     * @param fallaCierre si el cierre debe fallar
     * @return estado final
     */
    public static String diagnosticarEstado(boolean fallaAccion, boolean fallaCierre) {
        // TODO 6: Crear RecursoFragil("Diag", fallaCierre) en try-with-resources.
        //         Si fallaAccion, lanzar IOException("accion") dentro del try.
        //         En catch, comprobar si hay suppressed.
        //         Devolver el estado correspondiente.
        return "";
    }

    /**
     * Cuenta cuantas excepciones totales (principal + suppressed) tiene una excepcion.
     *
     * @param e excepcion a analizar
     * @return 0 si e es null, 1 + suppressed.length si no
     */
    public static int totalExcepciones(Exception e) {
        // TODO 7: Si e es null, devolver 0.
        //         Si no, devolver 1 + e.getSuppressed().length.
        return 0;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 22: Suppressed Exceptions ===\n");

        System.out.println("Cierre OK: " + probarCierre(false));
        System.out.println("Cierre falla: " + probarCierre(true));
        System.out.println("Suppressed count: " + contarSuppressed());

        System.out.println("Diagnostico (F,F): " + diagnosticarEstado(false, false));
        System.out.println("Diagnostico (T,F): " + diagnosticarEstado(true, false));
        System.out.println("Diagnostico (F,T): " + diagnosticarEstado(false, true));
        System.out.println("Diagnostico (T,T): " + diagnosticarEstado(true, true));
    }
}
