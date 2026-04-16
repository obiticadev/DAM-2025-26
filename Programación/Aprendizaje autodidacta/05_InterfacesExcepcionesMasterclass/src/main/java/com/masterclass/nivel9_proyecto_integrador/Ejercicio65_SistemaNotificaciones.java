package com.masterclass.nivel9_proyecto_integrador;

import java.util.List;

/**
 * ============================================================================
 *  EJERCICIO 65 — SISTEMA DE NOTIFICACIONES (INTEGRADOR)
 * ============================================================================
 * Combina: interfaces, default methods, functional interfaces, excepciones custom.
 */
public class Ejercicio65_SistemaNotificaciones {

    public static class NotificacionException extends Exception {
        private final String canal;
        public NotificacionException(String canal, String msg) { super(msg); this.canal = canal; }
        public String getCanal() { return canal; }
    }

    @FunctionalInterface
    public interface CanalNotificacion {
        void enviar(String destinatario, String mensaje) throws NotificacionException;
    }

    /**
     * TODO: Implementa un CanalNotificacion "email" que siempre tiene exito.
     * Devuelve un canal que almacena los mensajes enviados en la lista proporcionada
     * como "EMAIL:{destinatario}:{mensaje}".
     */
    public static CanalNotificacion crearCanalEmail(List<String> log) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Implementa un CanalNotificacion "sms" que falla si destinatario no empieza con "+".
     * Si falla, lanza NotificacionException("SMS", "Numero invalido: {destinatario}").
     * Si OK, almacena "SMS:{destinatario}:{mensaje}" en log.
     */
    public static CanalNotificacion crearCanalSMS(List<String> log) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Envia el mensaje por todos los canales. Si un canal falla, captura la excepcion
     * y continua con el siguiente. Devuelve el numero de envios exitosos.
     */
    public static int enviarATodos(List<CanalNotificacion> canales, String destinatario, String mensaje) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
