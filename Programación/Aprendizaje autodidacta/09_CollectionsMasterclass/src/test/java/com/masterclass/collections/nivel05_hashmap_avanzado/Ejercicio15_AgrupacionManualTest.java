package com.masterclass.collections.nivel05_hashmap_avanzado;

import com.masterclass.collections.modelos.Empleado;
import com.masterclass.collections.modelos.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 15 — Agrupación Manual con HashMap")
class Ejercicio15_AgrupacionManualTest {

    private ArrayList<Producto> productos;
    private ArrayList<Empleado> empleados;

    @BeforeEach
    void setUp() {
        productos = new ArrayList<>();
        productos.add(new Producto("P001", "Teclado",     89.99, "Periféricos", "Hardware"));
        productos.add(new Producto("P002", "Ratón",       45.50, "Periféricos", "Hardware"));
        productos.add(new Producto("P003", "Monitor",    320.00, "Pantallas",   "Hardware"));
        productos.add(new Producto("P004", "Auriculares", 79.99, "Audio",       "Hardware"));
        productos.add(new Producto("P005", "Antivirus",   29.99, "Software",    "Licencia"));

        empleados = new ArrayList<>();
        empleados.add(new Empleado("E01", "Ana",   "IT",     45000, "admin"));
        empleados.add(new Empleado("E02", "Luis",  "IT",     52000, "admin"));
        empleados.add(new Empleado("E03", "Marta", "RRHH",   38000, "admin"));
        empleados.add(new Empleado("E04", "Pedro", "RRHH",   40000, "admin"));
        empleados.add(new Empleado("E05", "Eva",   "IT",     61000, "admin"));
        empleados.add(new Empleado("E06", "Carlos","Ventas", 35000, "admin"));
    }

    // ── agruparPorCategoria ──────────────────────────────────────────────────

    @Test
    @DisplayName("agruparPorCategoria() crea una entrada por cada categoría distinta")
    void agruparPorCategoria_crea4Categorias() {
        HashMap<String, ArrayList<Producto>> grupos =
                Ejercicio15_AgrupacionManual.agruparPorCategoria(productos);
        assertThat(grupos).containsKeys("Periféricos", "Pantallas", "Audio", "Software");
    }

    @Test
    @DisplayName("agruparPorCategoria() pone el número correcto de productos en cada grupo")
    void agruparPorCategoria_tamanoGrupos() {
        HashMap<String, ArrayList<Producto>> grupos =
                Ejercicio15_AgrupacionManual.agruparPorCategoria(productos);
        assertThat(grupos.get("Periféricos")).hasSize(2);
        assertThat(grupos.get("Software")).hasSize(1);
    }

    @Test
    @DisplayName("agruparPorCategoria() no modifica la lista original")
    void agruparPorCategoria_noModificaOriginal() {
        int tamano = productos.size();
        Ejercicio15_AgrupacionManual.agruparPorCategoria(productos);
        assertThat(productos).hasSize(tamano);
    }

    // ── contarPorDepartamento ────────────────────────────────────────────────

    @Test
    @DisplayName("contarPorDepartamento() retorna el conteo correcto por departamento")
    void contarPorDepartamento_correcto() {
        HashMap<String, Integer> conteo =
                Ejercicio15_AgrupacionManual.contarPorDepartamento(empleados);
        assertThat(conteo.get("IT")).isEqualTo(3);
        assertThat(conteo.get("RRHH")).isEqualTo(2);
        assertThat(conteo.get("Ventas")).isEqualTo(1);
    }

    // ── agruparStringsPorLongitud ────────────────────────────────────────────

    @Test
    @DisplayName("agruparStringsPorLongitud() agrupa correctamente por longitud")
    void agruparStringsPorLongitud_correcto() {
        ArrayList<String> palabras = new ArrayList<>(
                List.of("sol", "luna", "mar", "cielo", "rio", "nube", "tierra"));
        HashMap<Integer, ArrayList<String>> grupos =
                Ejercicio15_AgrupacionManual.agruparStringsPorLongitud(palabras);

        // "sol"=3, "mar"=3, "rio"=3 → longitud 3
        assertThat(grupos.get(3)).containsExactlyInAnyOrder("sol", "mar", "rio");
        // "luna"=4, "nube"=4 → longitud 4
        assertThat(grupos.get(4)).containsExactlyInAnyOrder("luna", "nube");
        // "cielo"=5 → longitud 5
        assertThat(grupos.get(5)).containsExactlyInAnyOrder("cielo");
        // "tierra"=6 → longitud 6
        assertThat(grupos.get(6)).containsExactlyInAnyOrder("tierra");
    }

    // ── claveConMasElementos ─────────────────────────────────────────────────

    @Test
    @DisplayName("claveConMasElementos() retorna la clave de la lista más grande")
    void claveConMasElementos_correcto() {
        HashMap<String, ArrayList<?>> grupos = new HashMap<>();
        grupos.put("A", new ArrayList<>(List.of(1, 2, 3)));
        grupos.put("B", new ArrayList<>(List.of(1, 2)));
        grupos.put("C", new ArrayList<>(List.of(1)));
        assertThat(Ejercicio15_AgrupacionManual.claveConMasElementos(grupos)).isEqualTo("A");
    }

    @Test
    @DisplayName("claveConMasElementos() retorna null para mapa vacío")
    void claveConMasElementos_mapaVacio() {
        assertThat(Ejercicio15_AgrupacionManual.claveConMasElementos(new HashMap<>())).isNull();
    }

    // ── sumaSalariosPorDepartamento ──────────────────────────────────────────

    @Test
    @DisplayName("sumaSalariosPorDepartamento() calcula la suma correcta")
    void sumaSalariosPorDepartamento_correcto() {
        HashMap<String, Double> sumas =
                Ejercicio15_AgrupacionManual.sumaSalariosPorDepartamento(empleados);
        // IT: 45000+52000+61000 = 158000
        assertThat(sumas.get("IT")).isEqualTo(158000.0);
        // RRHH: 38000+40000 = 78000
        assertThat(sumas.get("RRHH")).isEqualTo(78000.0);
        // Ventas: 35000
        assertThat(sumas.get("Ventas")).isEqualTo(35000.0);
    }
}
