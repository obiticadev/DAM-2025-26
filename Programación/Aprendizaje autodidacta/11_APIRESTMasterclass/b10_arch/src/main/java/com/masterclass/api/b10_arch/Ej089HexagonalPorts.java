package com.masterclass.api.b10_arch;

/**
 * Ejercicio 089 · Arquitectura hexagonal (puertos y adaptadores).
 *
 * <p>Teoría: {@code teoria/10_Arquitectura_Patrones.md} (sección 10.3).
 *
 * <p>El dominio define el PUERTO (interfaz). Los adaptadores lo implementan.
 * El dominio NO depende de la infraestructura.
 */
public final class Ej089HexagonalPorts {

    /** PUERTO de salida: el dominio lo necesita, no sabe quién lo implementa. */
    public interface NotificacionPort {
        void enviar(String destino, String mensaje);
    }

    /** Adaptador concreto que registra en memoria (sustituible por email real). */
    public static class NotificacionEnMemoria implements NotificacionPort {
        public final java.util.List<String> enviados = new java.util.ArrayList<>();

        @Override
        public void enviar(String destino, String mensaje) {
            // TODO 1: valida destino y mensaje no null/blank.
            // TODO 2: registra "destino|mensaje" en la lista 'enviados'.
            // TODO 3: este adaptador es intercambiable sin tocar el dominio.
        }
    }

    /** Servicio de DOMINIO: depende del PUERTO, jamás del adaptador concreto. */
    public static class RegistroService {
        private final NotificacionPort notificacion;

        public RegistroService(NotificacionPort notificacion) {
            // TODO 4: guarda la dependencia del puerto (inyección por constructor).
            this.notificacion = notificacion;
        }

        /**
         * Registra un usuario y le notifica la bienvenida.
         *
         * @param email correo del usuario
         * @return mensaje de bienvenida enviado
         * @throws IllegalArgumentException si email es inválido (sin '@')
         */
        public String registrar(String email) {
            // TODO 5: valida que email contenga '@'.
            // TODO 6: construye el mensaje "Bienvenido " + email.
            // TODO 7: usa el PUERTO notificacion.enviar(email, mensaje).
            // TODO 8: el dominio no sabe si es email real o memoria (desacoplado).
            // TODO 9: devuelve el mensaje enviado.
            // TODO 10: si cambias el adaptador, este código NO cambia (esa es la idea).
            return "";
        }
    }

    private Ej089HexagonalPorts() {
    }

    public static void main(String[] args) {
        var adapter = new NotificacionEnMemoria();
        System.out.println(new RegistroService(adapter).registrar("a@b.com"));
    }

    /**
     * TODO extra 1: Valida que el destino y mensaje no sean nulos o vacíos.
     */
    public static void desafioValidarDestinoYMensaje(String destino, String mensaje) {
        // GUÍA: validación de entrada del adaptador. isBlank() (Java 11) cubre
        // null + "" + "   " en un gesto. CUIDADO: el test pasa (null,"Hola") y
        // también ("a@b.com"," ") — el espacio en blanco DEBE fallar, por eso
        // isBlank() y no isEmpty().
        if (destino == null || destino.isBlank() || mensaje == null || mensaje.isBlank()) {
            throw new IllegalArgumentException("Destino o mensaje inválidos");
        }
    }

    /**
     * TODO extra 2: Registra en una lista un mensaje en formato "destino|mensaje".
     */
    public static void desafioRegistrarEnviado(java.util.List<String> list, String destino, String mensaje) {
        // GUÍA: el adaptador "en memoria" guarda lo enviado como "destino|mensaje"
        // (reutiliza el reto 1 para validar). CUIDADO con el formato: el test
        // espera EXACTAMENTE "a@b.com|Hola" — la barra | es el separador.
        desafioValidarDestinoYMensaje(destino, mensaje);
        list.add(destino + "|" + mensaje);
    }

    /**
     * TODO extra 3: Comprueba si un puerto de notificación está activo e instanciado.
     */
    public static boolean desafioIntercambiarAdaptador(NotificacionPort port) {
        // GUÍA: la idea hexagonal — cualquier objeto que implemente el PUERTO sirve
        // como adaptador. El test pasa un NotificacionEnMemoria (true) y null
        // (false). Demuestra que el dominio solo exige "algo que cumpla el puerto".
        return port != null;
    }

    /**
     * TODO extra 4: Retorna el puerto inyectado si no es nulo.
     */
    public static NotificacionPort desafioAsignarPuerto(NotificacionPort port) {
        // GUÍA: inyección por constructor defensiva — rechaza un puerto nulo (el
        // servicio no puede funcionar sin él). El test pasa un puerto válido y
        // espera que lo devuelva tal cual.
        if (port == null) throw new IllegalArgumentException("Puerto nulo");
        return port;
    }

    /**
     * TODO extra 5: Valida que el correo contenga '@'.
     */
    public static boolean desafioValidarEmail(String email) {
        // GUÍA: validación mínima de email (solo presencia de '@'). El &&
        // cortocircuita: si email es null no llega a contains() y no hay NPE. El
        // test: "a@b.com" → true, "ab.com" → false. (En producción usarías una
        // validación @Email del bloque 8; aquí basta lo mínimo.)
        return email != null && email.contains("@");
    }

    /**
     * TODO extra 6: Construye un mensaje de bienvenida.
     */
    public static String desafioConstruirBienvenida(String email) {
        // GUÍA: lógica de dominio pura. CUIDADO con el formato: "Bienvenido " con
        // un espacio antes del email — el test compara con equals contra
        // "Bienvenido u@x.com". Un espacio de más o de menos rompe el assert.
        return "Bienvenido " + email;
    }

    /**
     * TODO extra 7: Envía una notificación usando el puerto.
     */
    public static void desafioEnviarPorPuerto(NotificacionPort port, String destino, String mensaje) {
        // GUÍA: el dominio llama al PUERTO a ciegas, sin saber qué adaptador hay
        // detrás. El test usa NotificacionEnMemoria y luego comprueba que
        // p.enviados.get(0) es "u@x.com|Msg" (lo registró el adaptador).
        port.enviar(destino, mensaje);
    }

    /**
     * TODO extra 8: Verifica que el dominio esté desacoplado del adaptador.
     */
    public static String desafioVerificarAislamiento() {
        // GUÍA: método "testigo" del concepto — el dominio está DESACOPLADO de la
        // infraestructura. El test solo comprueba que devuelve "desacoplado"
        // (literal exacto). Es didáctico: el valor real está en entenderlo.
        return "desacoplado";
    }

    /**
     * TODO extra 9: Retorna el mensaje enviado formateado.
     */
    public static String desafioObtenerMensajeRetorno(String email) {
        // GUÍA: mismo formato que desafioConstruirBienvenida (reto 6) — el
        // servicio DEVUELVE el mensaje que envió, para que el llamante sepa qué
        // se notificó. Test: "Bienvenido u@x.com".
        return "Bienvenido " + email;
    }

    /**
     * TODO extra 10: Simula todo el flujo hexagonal de registro y envío.
     */
    public static String desafioSimularFlujoHexagonal(NotificacionPort port, String email) {
        // GUÍA: el flujo completo de registrar() compuesto con los retos 5, 6 y 7:
        // valida → construye mensaje → envía por el puerto → devuelve el mensaje.
        // El test comprueba el retorno ("Bienvenido u@x.com") Y que el adaptador
        // registró "u@x.com|Bienvenido u@x.com". Reúsalo como guía del método base.
        if (!desafioValidarEmail(email)) throw new IllegalArgumentException("Email invalido");
        String msg = desafioConstruirBienvenida(email);
        desafioEnviarPorPuerto(port, email, msg);
        return msg;
    }

}
