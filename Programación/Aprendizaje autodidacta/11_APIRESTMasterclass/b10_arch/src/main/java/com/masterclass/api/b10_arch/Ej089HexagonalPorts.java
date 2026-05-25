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
        if (destino == null || destino.isBlank() || mensaje == null || mensaje.isBlank()) {
            throw new IllegalArgumentException("Destino o mensaje inválidos");
        }
    }

    /**
     * TODO extra 2: Registra en una lista un mensaje en formato "destino|mensaje".
     */
    public static void desafioRegistrarEnviado(java.util.List<String> list, String destino, String mensaje) {
        desafioValidarDestinoYMensaje(destino, mensaje);
        list.add(destino + "|" + mensaje);
    }

    /**
     * TODO extra 3: Comprueba si un puerto de notificación está activo e instanciado.
     */
    public static boolean desafioIntercambiarAdaptador(NotificacionPort port) {
        return port != null;
    }

    /**
     * TODO extra 4: Retorna el puerto inyectado si no es nulo.
     */
    public static NotificacionPort desafioAsignarPuerto(NotificacionPort port) {
        if (port == null) throw new IllegalArgumentException("Puerto nulo");
        return port;
    }

    /**
     * TODO extra 5: Valida que el correo contenga '@'.
     */
    public static boolean desafioValidarEmail(String email) {
        return email != null && email.contains("@");
    }

    /**
     * TODO extra 6: Construye un mensaje de bienvenida.
     */
    public static String desafioConstruirBienvenida(String email) {
        return "Bienvenido " + email;
    }

    /**
     * TODO extra 7: Envía una notificación usando el puerto.
     */
    public static void desafioEnviarPorPuerto(NotificacionPort port, String destino, String mensaje) {
        port.enviar(destino, mensaje);
    }

    /**
     * TODO extra 8: Verifica que el dominio esté desacoplado del adaptador.
     */
    public static String desafioVerificarAislamiento() {
        return "desacoplado";
    }

    /**
     * TODO extra 9: Retorna el mensaje enviado formateado.
     */
    public static String desafioObtenerMensajeRetorno(String email) {
        return "Bienvenido " + email;
    }

    /**
     * TODO extra 10: Simula todo el flujo hexagonal de registro y envío.
     */
    public static String desafioSimularFlujoHexagonal(NotificacionPort port, String email) {
        if (!desafioValidarEmail(email)) throw new IllegalArgumentException("Email invalido");
        String msg = desafioConstruirBienvenida(email);
        desafioEnviarPorPuerto(port, email, msg);
        return msg;
    }

}
