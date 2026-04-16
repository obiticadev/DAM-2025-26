package bloque4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * EJERCICIO 21 — Crear Recursos AutoCloseable Personalizados
 * Teoria: teoria/04_Gestion_Segura_Recursos.md (seccion 5)
 *
 * Contexto: La clinica quiere un sistema de log que registre todo lo
 * que se hace durante una consulta. El log se abre al inicio de la
 * consulta y se cierra automaticamente al final, generando un resumen.
 * Implementaras AutoCloseable en tu propia clase.
 */
public class Ej21_AutoCloseableCustom {

    /**
     * Registrador de consulta que implementa AutoCloseable.
     * Acumula mensajes y al cerrar genera un resumen.
     */
    public static class RegistradorConsulta implements AutoCloseable {

        private final String nombreMascota;
        private final List<String> mensajes;
        private boolean cerrado;

        /**
         * Crea un nuevo registrador para la mascota indicada.
         *
         * @param nombreMascota nombre de la mascota
         */
        public RegistradorConsulta(String nombreMascota) {
            // TODO 1: Inicializar nombreMascota, crear ArrayList para mensajes,
            //         poner cerrado = false.
            //         Anadir "Inicio consulta: [nombreMascota]" como primer mensaje.
            this.nombreMascota = nombreMascota;
            this.mensajes = new ArrayList<>();
            this.cerrado = false;
        }

        /**
         * Registra un mensaje en el log.
         *
         * @param mensaje mensaje a registrar
         * @throws IllegalStateException si el registrador ya esta cerrado
         */
        public void registrar(String mensaje) {
            // TODO 2: Si cerrado es true, lanzar IllegalStateException.
            //         Si no, anadir el mensaje a la lista.
            throw new UnsupportedOperationException("TODO 2 no implementado");
        }

        /**
         * Devuelve el numero de mensajes registrados (incluyendo el de inicio).
         *
         * @return numero de mensajes
         */
        public int totalMensajes() {
            // TODO 3: Devolver el tamano de la lista de mensajes.
            return 0;
        }

        /**
         * Devuelve todos los mensajes como un String, uno por linea.
         *
         * @return todos los mensajes unidos por '\n'
         */
        public String obtenerLog() {
            // TODO 4: Unir mensajes con '\n' usando String.join.
            return "";
        }

        /**
         * Cierra el registrador. Anade "Fin consulta: [nombreMascota]" como
         * ultimo mensaje y marca el registrador como cerrado.
         * Si ya esta cerrado, no hace nada (idempotente).
         */
        @Override
        public void close() {
            // TODO 5: Si no esta cerrado:
            //         - Anadir "Fin consulta: [nombreMascota]" a mensajes.
            //         - Poner cerrado = true.
            //         Si ya esta cerrado, no hacer nada.
        }

        public boolean estaCerrado() { return cerrado; }
        public String getNombreMascota() { return nombreMascota; }
    }

    /**
     * Simula una consulta completa usando try-with-resources con RegistradorConsulta.
     * Registra los mensajes proporcionados y devuelve el log completo.
     *
     * @param mascota  nombre de la mascota
     * @param acciones array de acciones a registrar
     * @return log completo de la consulta
     */
    public static String simularConsulta(String mascota, String[] acciones) {
        // TODO 6: Crear RegistradorConsulta en try-with-resources.
        //         Registrar cada accion del array.
        //         Obtener el log ANTES de que se cierre.
        //         Despues del try, devolver el log + '\n' + "Fin consulta: [mascota]".
        //         (El close anade el mensaje de fin automaticamente).
        return "";
    }

    /**
     * Ejecuta dos consultas simultaneas (dos RegistradorConsulta en el mismo try)
     * y devuelve un informe combinado.
     *
     * @param mascota1   primera mascota
     * @param acciones1  acciones de la primera consulta
     * @param mascota2   segunda mascota
     * @param acciones2  acciones de la segunda consulta
     * @return informe con ambos logs separados por "==="
     */
    public static String consultaDoble(String mascota1, String[] acciones1,
                                        String mascota2, String[] acciones2) {
        // TODO 7: Usar try-with-resources con dos RegistradorConsulta.
        //         Registrar acciones de cada uno.
        //         Obtener ambos logs. Unir con '\n===\n'.
        return "";
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 21: AutoCloseable Custom ===\n");

        String log = simularConsulta("Luna",
                new String[]{"Peso: 4.5 kg", "Temperatura: 38.5C", "Vacuna antirabica"});
        System.out.println(log);

        System.out.println("\n=== Consulta Doble ===");
        String doble = consultaDoble("Luna", new String[]{"Revision"},
                "Max", new String[]{"Radiografia"});
        System.out.println(doble);
    }
}
