package com.masterclass.nivel4_interfaces_funcionales;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 4 - Ejercicio 27: Consumer y Supplier")
class Ejercicio27_ConsumerYSupplierTest {
    @Test @DisplayName("27.1 - crearColector anade a lista") void colector() { List<String> dest = new ArrayList<>(); Ejercicio27_ConsumerYSupplier.crearColector(dest).accept("A"); assertThat(dest).containsExactly("A"); }
    @Test @DisplayName("27.2 - aplicarATodos ejecuta consumer en cada uno") void aplicar() { List<String> dest = new ArrayList<>(); Ejercicio27_ConsumerYSupplier.aplicarATodos(List.of("X","Y"), Ejercicio27_ConsumerYSupplier.crearColector(dest)); assertThat(dest).containsExactly("X","Y"); }
    @Test @DisplayName("27.3 - crearContador genera secuencia") void contador() { var c = Ejercicio27_ConsumerYSupplier.crearContador(1); assertThat(c.get()).isEqualTo(1); assertThat(c.get()).isEqualTo(2); assertThat(c.get()).isEqualTo(3); }
    @Test @DisplayName("27.4 - generarLista genera n elementos") void generarLista() { assertThat(Ejercicio27_ConsumerYSupplier.generarLista(Ejercicio27_ConsumerYSupplier.crearContador(10), 3)).containsExactly(10,11,12); }
}
