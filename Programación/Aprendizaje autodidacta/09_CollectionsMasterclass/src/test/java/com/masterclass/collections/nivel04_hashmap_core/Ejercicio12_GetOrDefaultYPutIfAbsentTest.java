package com.masterclass.collections.nivel04_hashmap_core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 12 — API Condicional de Map")
class Ejercicio12_GetOrDefaultYPutIfAbsentTest {

    private HashMap<String, String> config;

    @BeforeEach
    void setUp() {
        config = new HashMap<>();
        config.put("host", "localhost");
        config.put("puerto", "8080");
    }

    // ── obtenerODefecto ──────────────────────────────────────────────────────

    @Test
    @DisplayName("obtenerODefecto() retorna el valor si la clave existe")
    void obtenerODefecto_claveExiste() {
        assertThat(Ejercicio12_GetOrDefaultYPutIfAbsent.obtenerODefecto(config, "host", "127.0.0.1"))
                .isEqualTo("localhost");
    }

    @Test
    @DisplayName("obtenerODefecto() retorna el defecto si la clave no existe")
    void obtenerODefecto_claveInexistente() {
        assertThat(Ejercicio12_GetOrDefaultYPutIfAbsent.obtenerODefecto(config, "timeout", "30s"))
                .isEqualTo("30s");
    }

    @Test
    @DisplayName("obtenerODefecto() no inserta la clave en el mapa cuando usa el defecto")
    void obtenerODefecto_noInsertaClave() {
        Ejercicio12_GetOrDefaultYPutIfAbsent.obtenerODefecto(config, "timeout", "30s");
        assertThat(config).doesNotContainKey("timeout");
    }

    // ── insertarSiNoExiste ───────────────────────────────────────────────────

    @Test
    @DisplayName("insertarSiNoExiste() retorna true y la inserta cuando la clave es nueva")
    void insertarSiNoExiste_claveNueva() {
        boolean insertado = Ejercicio12_GetOrDefaultYPutIfAbsent.insertarSiNoExiste(config, "timeout", "30s");
        assertThat(insertado).isTrue();
        assertThat(config.get("timeout")).isEqualTo("30s");
    }

    @Test
    @DisplayName("insertarSiNoExiste() retorna false y NO sobreescribe cuando la clave existe")
    void insertarSiNoExiste_claveExistente() {
        boolean insertado = Ejercicio12_GetOrDefaultYPutIfAbsent.insertarSiNoExiste(config, "host", "remoto");
        assertThat(insertado).isFalse();
        assertThat(config.get("host")).isEqualTo("localhost"); // valor original intacto
    }

    // ── reemplazarSiExiste ───────────────────────────────────────────────────

    @Test
    @DisplayName("reemplazarSiExiste() retorna true y actualiza cuando la clave existe")
    void reemplazarSiExiste_claveExistente() {
        boolean actualizado = Ejercicio12_GetOrDefaultYPutIfAbsent.reemplazarSiExiste(config, "puerto", "9090");
        assertThat(actualizado).isTrue();
        assertThat(config.get("puerto")).isEqualTo("9090");
    }

    @Test
    @DisplayName("reemplazarSiExiste() retorna false y NO inserta cuando la clave no existe")
    void reemplazarSiExiste_claveInexistente() {
        boolean actualizado = Ejercicio12_GetOrDefaultYPutIfAbsent.reemplazarSiExiste(config, "noExiste", "valor");
        assertThat(actualizado).isFalse();
        assertThat(config).doesNotContainKey("noExiste");
    }

    // ── doblarTodosLosValores ────────────────────────────────────────────────

    @Test
    @DisplayName("doblarTodosLosValores() multiplica todos los valores por 2")
    void doblarTodosLosValores_multiplicaPorDos() {
        HashMap<String, Integer> stats = new HashMap<>();
        stats.put("clicks", 10);
        stats.put("vistas", 25);
        Ejercicio12_GetOrDefaultYPutIfAbsent.doblarTodosLosValores(stats);
        assertThat(stats.get("clicks")).isEqualTo(20);
        assertThat(stats.get("vistas")).isEqualTo(50);
    }

    @Test
    @DisplayName("doblarTodosLosValores() no cambia el número de entradas")
    void doblarTodosLosValores_mismasCantidadEntradas() {
        HashMap<String, Integer> stats = new HashMap<>();
        stats.put("a", 1); stats.put("b", 2); stats.put("c", 3);
        int tamanoBefore = stats.size();
        Ejercicio12_GetOrDefaultYPutIfAbsent.doblarTodosLosValores(stats);
        assertThat(stats).hasSize(tamanoBefore);
    }

    // ── normalizarClaves ─────────────────────────────────────────────────────

    @Test
    @DisplayName("normalizarClaves() convierte todas las claves a mayúsculas")
    void normalizarClaves_clavesEnMayusculas() {
        HashMap<String, Integer> mixto = new HashMap<>();
        mixto.put("ciudad", 1);
        mixto.put("pais", 2);
        HashMap<String, Integer> resultado =
                Ejercicio12_GetOrDefaultYPutIfAbsent.normalizarClaves(mixto);
        assertThat(resultado).containsKey("CIUDAD").containsKey("PAIS");
        assertThat(resultado).doesNotContainKey("ciudad").doesNotContainKey("pais");
    }

    @Test
    @DisplayName("normalizarClaves() no modifica el mapa original")
    void normalizarClaves_noModificaOriginal() {
        HashMap<String, Integer> original = new HashMap<>();
        original.put("nombre", 42);
        Ejercicio12_GetOrDefaultYPutIfAbsent.normalizarClaves(original);
        assertThat(original).containsKey("nombre").doesNotContainKey("NOMBRE");
    }

    // ── reemplazarSoloSiValorCorrecto ────────────────────────────────────────

    @Test
    @DisplayName("reemplazarSoloSiValorCorrecto() actualiza cuando el valor actual coincide")
    void reemplazar3args_coincide() {
        HashMap<String, String> estado = new HashMap<>();
        estado.put("semaforo", "rojo");
        boolean resultado = Ejercicio12_GetOrDefaultYPutIfAbsent
                .reemplazarSoloSiValorCorrecto(estado, "semaforo", "rojo", "verde");
        assertThat(resultado).isTrue();
        assertThat(estado.get("semaforo")).isEqualTo("verde");
    }

    @Test
    @DisplayName("reemplazarSoloSiValorCorrecto() no actualiza si el valor actual no coincide")
    void reemplazar3args_noCoincide() {
        HashMap<String, String> estado = new HashMap<>();
        estado.put("semaforo", "verde");
        boolean resultado = Ejercicio12_GetOrDefaultYPutIfAbsent
                .reemplazarSoloSiValorCorrecto(estado, "semaforo", "rojo", "amarillo");
        assertThat(resultado).isFalse();
        assertThat(estado.get("semaforo")).isEqualTo("verde"); // sin cambios
    }
}
