package com.bootcamp.finale.servicio;

import com.bootcamp.finale.modelo.Usuario;
import com.bootcamp.finale.repositorio.UsuarioRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@DisplayName("UsuarioServicio")
class UsuarioServicioTest {

    private UsuarioRepositorio repo;
    private UsuarioServicio servicio;

    @BeforeEach
    void setUp() {
        repo = new UsuarioRepositorio();
        servicio = new UsuarioServicio(repo);
    }

    @Nested
    @DisplayName("registrar")
    class Registrar {
        @Test
        @DisplayName("Primer Usuario tiene id 1")
        void primerId() {
            Usuario u = servicio.registrar("Ana", "ana@x.com", 30);
            assertThat(u.getId()).isEqualTo(1L);
            assertThat(repo.count()).isEqualTo(1);
        }

        @Test
        @DisplayName("Segundo Usuario tiene id 2")
        void segundoId() {
            servicio.registrar("Ana", "ana@x.com", 30);
            Usuario u2 = servicio.registrar("Beto", "beto@x.com", 25);
            assertThat(u2.getId()).isEqualTo(2L);
        }

        @Test
        @DisplayName("Cinco registros consecutivos")
        void cincoConsec() {
            for (int i = 0; i < 5; i++) {
                servicio.registrar("U" + i, "u" + i + "@x.com", 20 + i);
            }
            assertThat(servicio.contar()).isEqualTo(5);
        }
    }

    @Nested
    @DisplayName("actualizarEmail")
    class Actualizar {
        @Test
        @DisplayName("Cambia el email")
        void cambia() {
            Usuario u = servicio.registrar("Ana", "ana@x.com", 30);
            servicio.actualizarEmail(u.getId(), "nuevo@x.com");
            assertThat(repo.buscarPorId(u.getId()).getEmail()).isEqualTo("nuevo@x.com");
        }

        @Test
        @DisplayName("Lanza si no existe")
        void noExiste() {
            assertThatThrownBy(() -> servicio.actualizarEmail(99, "x@y.com"))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("eliminar")
    class Eliminar {
        @Test
        @DisplayName("Delega en repositorio")
        void delega() {
            Usuario u = servicio.registrar("Ana", "ana@x.com", 30);
            assertThat(servicio.eliminar(u.getId())).isTrue();
            assertThat(servicio.contar()).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("mayoresDeEdad")
    class Mayores {
        @Test
        @DisplayName("Filtra correctamente edad >= 18")
        void filtra() {
            servicio.registrar("Niño", "n@x.com", 10);
            servicio.registrar("Adulto1", "a1@x.com", 18);
            servicio.registrar("Adulto2", "a2@x.com", 65);
            servicio.registrar("Niña", "n2@x.com", 17);
            List<Usuario> may = servicio.mayoresDeEdad();
            assertThat(may).hasSize(2);
            assertThat(may).extracting(Usuario::getNombre)
                .containsExactlyInAnyOrder("Adulto1", "Adulto2");
        }

        @Test
        @DisplayName("Vacío si no hay mayores")
        void vacio() {
            servicio.registrar("Niño1", "n1@x.com", 5);
            servicio.registrar("Niño2", "n2@x.com", 10);
            assertThat(servicio.mayoresDeEdad()).isEmpty();
        }
    }
}
