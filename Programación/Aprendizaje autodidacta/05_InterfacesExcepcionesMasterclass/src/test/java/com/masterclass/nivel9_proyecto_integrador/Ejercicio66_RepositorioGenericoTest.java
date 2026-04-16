package com.masterclass.nivel9_proyecto_integrador;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 9 - Ejercicio 66: Repositorio Generico")
class Ejercicio66_RepositorioGenericoTest {

    record Usuario(String id, String nombre) implements Ejercicio66_RepositorioGenerico.Identificable {
        @Override public String getId() { return id; }
    }

    @Test @DisplayName("66.1 - guardar y buscar") void guardaBusca() {
        var repo = Ejercicio66_RepositorioGenerico.<Usuario>crearRepositorio();
        repo.guardar(new Usuario("1","Ana"));
        assertThat(repo.buscarPorId("1")).isPresent();
        assertThat(repo.contar()).isEqualTo(1);
    }
    @Test @DisplayName("66.2 - obtenerOLanzar falla") void lanza() {
        var repo = Ejercicio66_RepositorioGenerico.<Usuario>crearRepositorio();
        assertThatThrownBy(() -> repo.obtenerOLanzar("99"))
            .isInstanceOf(Ejercicio66_RepositorioGenerico.EntidadNoEncontradaException.class);
    }
    @Test @DisplayName("66.3 - buscar todos con filtro") void filtro() {
        var repo = Ejercicio66_RepositorioGenerico.<Usuario>crearRepositorio();
        repo.guardar(new Usuario("1","Ana"));
        repo.guardar(new Usuario("2","Bob"));
        assertThat(repo.buscarTodos(u -> u.nombre().startsWith("A"))).hasSize(1);
    }
    @Test @DisplayName("66.4 - eliminar") void eliminar() {
        var repo = Ejercicio66_RepositorioGenerico.<Usuario>crearRepositorio();
        repo.guardar(new Usuario("1","Ana"));
        repo.eliminar("1");
        assertThat(repo.contar()).isEqualTo(0);
    }
    @Test @DisplayName("66.5 - guardar null lanza") void nulo() {
        var repo = Ejercicio66_RepositorioGenerico.<Usuario>crearRepositorio();
        assertThatThrownBy(() -> repo.guardar(null)).isInstanceOf(IllegalArgumentException.class);
    }
}
