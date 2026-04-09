package com.masterclass.nivel9_proyecto_integrador;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 9 - Ejercicio 65: Sistema de Notificaciones")
class Ejercicio65_SistemaNotificacionesTest {
    @Test @DisplayName("65.1 - canal email ok") void email() throws Exception {
        var log = new ArrayList<String>();
        var canal = Ejercicio65_SistemaNotificaciones.crearCanalEmail(log);
        canal.enviar("user@mail.com","Hola");
        assertThat(log).containsExactly("EMAIL:user@mail.com:Hola");
    }
    @Test @DisplayName("65.2 - canal sms ok") void smsOk() throws Exception {
        var log = new ArrayList<String>();
        var canal = Ejercicio65_SistemaNotificaciones.crearCanalSMS(log);
        canal.enviar("+34612345678","Hola");
        assertThat(log).containsExactly("SMS:+34612345678:Hola");
    }
    @Test @DisplayName("65.3 - canal sms invalido") void smsFail() {
        var log = new ArrayList<String>();
        var canal = Ejercicio65_SistemaNotificaciones.crearCanalSMS(log);
        assertThatThrownBy(() -> canal.enviar("612345","Hola"))
            .isInstanceOf(Ejercicio65_SistemaNotificaciones.NotificacionException.class);
    }
    @Test @DisplayName("65.4 - enviar a todos con fallo parcial") void todos() {
        var log = new ArrayList<String>();
        var canales = List.of(
            Ejercicio65_SistemaNotificaciones.crearCanalEmail(log),
            Ejercicio65_SistemaNotificaciones.crearCanalSMS(log)
        );
        int ok = Ejercicio65_SistemaNotificaciones.enviarATodos(canales, "612345","Hola");
        assertThat(ok).isEqualTo(1);
    }
}
