package com.masterclass.nivel1_interfaces_basicas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 1 - Ejercicio 06: Marker Interface")
class Ejercicio06_MarkerInterfaceTest {

    @Test
    @DisplayName("06.1 - tarea normal NO es Urgente")
    void tareaNormalNoEsUrgente() {
        var tarea = Ejercicio06_MarkerInterface.crearTareaNormal("Comprar leche");
        assertThat(tarea).isNotInstanceOf(Ejercicio06_MarkerInterface.Urgente.class);
    }

    @Test
    @DisplayName("06.2 - tarea urgente SI es Urgente")
    void tareaUrgenteEsUrgente() {
        var tarea = Ejercicio06_MarkerInterface.crearTareaUrgente("Apagar incendio");
        assertThat(tarea).isInstanceOf(Ejercicio06_MarkerInterface.Urgente.class);
    }

    @Test
    @DisplayName("06.3 - ambas tareas devuelven su descripcion correctamente")
    void ambasDevuelvenDescripcion() {
        assertThat(Ejercicio06_MarkerInterface.crearTareaNormal("A").getDescripcion()).isEqualTo("A");
        assertThat(Ejercicio06_MarkerInterface.crearTareaUrgente("B").getDescripcion()).isEqualTo("B");
    }

    @Test
    @DisplayName("06.4 - filtrarUrgentes devuelve solo las urgentes")
    void debeFiltrartareas() {
        List<Ejercicio06_MarkerInterface.Tarea> tareas = List.of(
                Ejercicio06_MarkerInterface.crearTareaNormal("Normal 1"),
                Ejercicio06_MarkerInterface.crearTareaUrgente("Urgente 1"),
                Ejercicio06_MarkerInterface.crearTareaNormal("Normal 2"),
                Ejercicio06_MarkerInterface.crearTareaUrgente("Urgente 2")
        );
        var urgentes = Ejercicio06_MarkerInterface.filtrarUrgentes(tareas);
        assertThat(urgentes).hasSize(2);
        assertThat(urgentes).allSatisfy(t ->
                assertThat(t).isInstanceOf(Ejercicio06_MarkerInterface.Urgente.class));
    }

    @Test
    @DisplayName("06.5 - contarNoUrgentes cuenta las no urgentes")
    void debeContarNoUrgentes() {
        List<Ejercicio06_MarkerInterface.Tarea> tareas = List.of(
                Ejercicio06_MarkerInterface.crearTareaNormal("N1"),
                Ejercicio06_MarkerInterface.crearTareaUrgente("U1"),
                Ejercicio06_MarkerInterface.crearTareaNormal("N2"),
                Ejercicio06_MarkerInterface.crearTareaNormal("N3")
        );
        assertThat(Ejercicio06_MarkerInterface.contarNoUrgentes(tareas)).isEqualTo(3);
    }
}
