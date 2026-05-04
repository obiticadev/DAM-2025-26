package com.masterclass.collections.nivel05_hashmap_avanzado;

import com.masterclass.collections.modelos.Empleado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 16 — compute* y HashMap Anidado")
class Ejercicio16_ComputeYHashMapAnidadoTest {

    // ── incrementarContador ──────────────────────────────────────────────────

    @Test
    @DisplayName("incrementarContador() inicializa en 1 la primera vez")
    void incrementarContador_primerVez() {
        HashMap<String, Integer> c = new HashMap<>();
        Ejercicio16_ComputeYHashMapAnidado.incrementarContador(c, "click");
        assertThat(c.get("click")).isEqualTo(1);
    }

    @Test
    @DisplayName("incrementarContador() incrementa el valor en cada llamada")
    void incrementarContador_multipleVeces() {
        HashMap<String, Integer> c = new HashMap<>();
        Ejercicio16_ComputeYHashMapAnidado.incrementarContador(c, "click");
        Ejercicio16_ComputeYHashMapAnidado.incrementarContador(c, "click");
        Ejercicio16_ComputeYHashMapAnidado.incrementarContador(c, "click");
        assertThat(c.get("click")).isEqualTo(3);
    }

    @Test
    @DisplayName("incrementarContador() maneja varias claves independientemente")
    void incrementarContador_variasClaves() {
        HashMap<String, Integer> c = new HashMap<>();
        Ejercicio16_ComputeYHashMapAnidado.incrementarContador(c, "A");
        Ejercicio16_ComputeYHashMapAnidado.incrementarContador(c, "B");
        Ejercicio16_ComputeYHashMapAnidado.incrementarContador(c, "A");
        assertThat(c.get("A")).isEqualTo(2);
        assertThat(c.get("B")).isEqualTo(1);
    }

    // ── agregarALista ────────────────────────────────────────────────────────

    @Test
    @DisplayName("agregarALista() crea la lista si la clave no existe")
    void agregarALista_creaListaNueva() {
        HashMap<String, ArrayList<String>> m = new HashMap<>();
        Ejercicio16_ComputeYHashMapAnidado.agregarALista(m, "frutas", "Manzana");
        assertThat(m).containsKey("frutas");
        assertThat(m.get("frutas")).containsExactly("Manzana");
    }

    @Test
    @DisplayName("agregarALista() añade a la lista existente sin crear una nueva")
    void agregarALista_anadoAExistente() {
        HashMap<String, ArrayList<String>> m = new HashMap<>();
        Ejercicio16_ComputeYHashMapAnidado.agregarALista(m, "frutas", "Manzana");
        Ejercicio16_ComputeYHashMapAnidado.agregarALista(m, "frutas", "Pera");
        assertThat(m.get("frutas")).containsExactly("Manzana", "Pera");
        assertThat(m).hasSize(1); // sigue siendo 1 clave
    }

    // ── actualizarSiPresente ─────────────────────────────────────────────────

    @Test
    @DisplayName("actualizarSiPresente() actualiza y retorna true si la clave existe")
    void actualizarSiPresente_existe() {
        HashMap<String, String> m = new HashMap<>();
        m.put("tarea", "Pendiente");
        boolean resultado = Ejercicio16_ComputeYHashMapAnidado.actualizarSiPresente(m, "tarea", " → Completada");
        assertThat(resultado).isTrue();
        assertThat(m.get("tarea")).isEqualTo("Pendiente → Completada");
    }

    @Test
    @DisplayName("actualizarSiPresente() no crea la clave y retorna false si no existe")
    void actualizarSiPresente_noExiste() {
        HashMap<String, String> m = new HashMap<>();
        boolean resultado = Ejercicio16_ComputeYHashMapAnidado.actualizarSiPresente(m, "nueva", " sufijo");
        assertThat(resultado).isFalse();
        assertThat(m).doesNotContainKey("nueva");
    }

    // ── construirIndiceAnidado ───────────────────────────────────────────────

    @Test
    @DisplayName("construirIndiceAnidado() crea un mapa por cada departamento")
    void construirIndiceAnidado_departamentos() {
        ArrayList<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado("E01", "Ana",  "IT",   45000, "admin"));
        empleados.add(new Empleado("E02", "Luis", "RRHH", 38000, "admin"));

        HashMap<String, HashMap<String, Double>> indice =
                Ejercicio16_ComputeYHashMapAnidado.construirIndiceAnidado(empleados);
        assertThat(indice).containsKeys("IT", "RRHH");
    }

    @Test
    @DisplayName("construirIndiceAnidado() asocia salarios correctos en el mapa interno")
    void construirIndiceAnidado_salariosCorrectos() {
        ArrayList<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado("E01", "Ana",  "IT", 45000, "admin"));
        empleados.add(new Empleado("E02", "Luis", "IT", 52000, "admin"));

        HashMap<String, HashMap<String, Double>> indice =
                Ejercicio16_ComputeYHashMapAnidado.construirIndiceAnidado(empleados);
        assertThat(indice.get("IT").get("Ana")).isEqualTo(45000.0);
        assertThat(indice.get("IT").get("Luis")).isEqualTo(52000.0);
    }

    // ── salarioEmpleado ──────────────────────────────────────────────────────

    @Test
    @DisplayName("salarioEmpleado() retorna el salario correcto para empleado existente")
    void salarioEmpleado_existe() {
        HashMap<String, HashMap<String, Double>> indice = new HashMap<>();
        HashMap<String, Double> it = new HashMap<>();
        it.put("Ana", 45000.0);
        indice.put("IT", it);
        assertThat(Ejercicio16_ComputeYHashMapAnidado.salarioEmpleado(indice, "IT", "Ana"))
                .isEqualTo(45000.0);
    }

    @Test
    @DisplayName("salarioEmpleado() retorna -1.0 si el departamento no existe")
    void salarioEmpleado_deptNoExiste() {
        assertThat(Ejercicio16_ComputeYHashMapAnidado.salarioEmpleado(new HashMap<>(), "IT", "Ana"))
                .isEqualTo(-1.0);
    }

    // ── decrementarYEliminarSiCero ───────────────────────────────────────────

    @Test
    @DisplayName("decrementarYEliminarSiCero() decrementa el valor si es > 1")
    void decrementar_reducen() {
        HashMap<String, Integer> c = new HashMap<>();
        c.put("item", 3);
        Ejercicio16_ComputeYHashMapAnidado.decrementarYEliminarSiCero(c, "item");
        assertThat(c.get("item")).isEqualTo(2);
    }

    @Test
    @DisplayName("decrementarYEliminarSiCero() elimina la entrada al llegar a 0")
    void decrementar_eliminaAlLlegarCero() {
        HashMap<String, Integer> c = new HashMap<>();
        c.put("item", 1);
        Ejercicio16_ComputeYHashMapAnidado.decrementarYEliminarSiCero(c, "item");
        assertThat(c).doesNotContainKey("item");
    }

    @Test
    @DisplayName("decrementarYEliminarSiCero() no lanza excepción si la clave no existe")
    void decrementar_claveInexistente() {
        HashMap<String, Integer> c = new HashMap<>();
        assertThatNoException().isThrownBy(() ->
                Ejercicio16_ComputeYHashMapAnidado.decrementarYEliminarSiCero(c, "noExiste"));
    }
}
