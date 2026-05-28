package com.bootcamp.finale.repositorio;

import com.bootcamp.finale.modelo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@DisplayName("UsuarioRepositorio")
class UsuarioRepositorioTest {

    private UsuarioRepositorio repo;

    @BeforeEach
    void setUp() {
        repo = new UsuarioRepositorio();
    }

    @Nested
    @DisplayName("guardar")
    class Guardar {
        @Test
        @DisplayName("Guarda y aumenta el count")
        void guardaOk() {
            repo.guardar(new Usuario(1, "A", "a@b.com", 20));
            assertThat(repo.count()).isEqualTo(1);
        }

        @Test
        @DisplayName("Lanza si null")
        void nulo() {
            assertThatThrownBy(() -> repo.guardar(null))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Lanza si id duplicado")
        void duplicado() {
            repo.guardar(new Usuario(1, "A", "a@b.com", 20));
            assertThatThrownBy(() -> repo.guardar(new Usuario(1, "B", "b@c.com", 21)))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("buscarPorId")
    class Buscar {
        @Test
        @DisplayName("Devuelve el usuario si existe")
        void encuentra() {
            Usuario u = new Usuario(5, "X", "x@y.com", 40);
            repo.guardar(u);
            assertThat(repo.buscarPorId(5)).isEqualTo(u);
        }

        @Test
        @DisplayName("Devuelve null si no existe")
        void noEncuentra() {
            assertThat(repo.buscarPorId(999)).isNull();
        }
    }

    @Nested
    @DisplayName("eliminar")
    class Eliminar {
        @Test
        @DisplayName("Devuelve true si existía")
        void existia() {
            repo.guardar(new Usuario(1, "A", "a@b.com", 20));
            assertThat(repo.eliminar(1)).isTrue();
            assertThat(repo.count()).isEqualTo(0);
        }

        @Test
        @DisplayName("Devuelve false si no existía")
        void noExistia() {
            assertThat(repo.eliminar(99)).isFalse();
        }
    }

    @Nested
    @DisplayName("listarTodos")
    class Listar {
        @Test
        @DisplayName("Devuelve copia defensiva")
        void copiaDefensiva() {
            repo.guardar(new Usuario(1, "A", "a@b.com", 20));
            List<Usuario> lista = repo.listarTodos();
            lista.clear(); // si fuera la lista interna esto rompería el repo
            assertThat(repo.count()).isEqualTo(1);
        }
    }

    @Nested
    @DisplayName("existe")
    class Existe {
        @Test
        @DisplayName("True si guardado")
        void siExiste() {
            repo.guardar(new Usuario(1, "A", "a@b.com", 20));
            assertThat(repo.existe(1)).isTrue();
            assertThat(repo.existe(2)).isFalse();
        }
    }
}
