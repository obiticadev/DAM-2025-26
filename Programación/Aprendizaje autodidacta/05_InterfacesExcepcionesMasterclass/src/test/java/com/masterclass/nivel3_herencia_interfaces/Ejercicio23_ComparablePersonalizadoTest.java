package com.masterclass.nivel3_herencia_interfaces;

import com.masterclass.nivel3_herencia_interfaces.Ejercicio23_ComparablePersonalizado.Estudiante;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 3 - Ejercicio 23: Comparable Personalizado")
class Ejercicio23_ComparablePersonalizadoTest {

    @Test
    @DisplayName("23.1 - compareTo: mayor nota va primero")
    void mayorNotaPrimero() {
        var a = new Estudiante("Ana", 9.5);
        var b = new Estudiante("Luis", 7.0);
        assertThat(a.compareTo(b)).isNegative();
    }

    @Test
    @DisplayName("23.2 - compareTo: empate en nota desempata por nombre A-Z")
    void empateDesempataPorNombre() {
        var a = new Estudiante("Ana", 8.0);
        var b = new Estudiante("Zoe", 8.0);
        assertThat(a.compareTo(b)).isNegative();
    }

    @Test
    @DisplayName("23.3 - ordenarEstudiantes ordena correctamente")
    void ordenarEstudiantes() {
        var lista = List.of(
                new Estudiante("Carlos", 7.0),
                new Estudiante("Ana", 9.5),
                new Estudiante("Luis", 9.5)
        );
        var ordenados = Ejercicio23_ComparablePersonalizado.ordenarEstudiantes(lista);
        assertThat(ordenados).extracting(Estudiante::getNombre)
                .containsExactly("Ana", "Luis", "Carlos");
    }

    @Test
    @DisplayName("23.4 - mejorEstudiante devuelve el de mayor nota")
    void mejorEstudiante() {
        var lista = List.of(
                new Estudiante("Carlos", 7.0),
                new Estudiante("Ana", 9.5)
        );
        var mejor = Ejercicio23_ComparablePersonalizado.mejorEstudiante(lista);
        assertThat(mejor).isNotNull();
        assertThat(mejor.getNombre()).isEqualTo("Ana");
    }

    @Test
    @DisplayName("23.5 - mejorEstudiante con lista vacia devuelve null")
    void mejorEstudianteVacio() {
        assertThat(Ejercicio23_ComparablePersonalizado.mejorEstudiante(List.of())).isNull();
    }
}
