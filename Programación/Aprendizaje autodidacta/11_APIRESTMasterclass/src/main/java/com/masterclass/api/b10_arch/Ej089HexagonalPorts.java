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
}
