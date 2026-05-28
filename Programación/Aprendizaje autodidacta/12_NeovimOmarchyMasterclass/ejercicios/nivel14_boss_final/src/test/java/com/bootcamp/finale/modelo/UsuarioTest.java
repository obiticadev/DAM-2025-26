package com.bootcamp.finale.modelo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Usuario — modelo")
class UsuarioTest {

    @Nested
    @DisplayName("Constructor")
    class Constructor {
        @Test
        @DisplayName("Crea un Usuario válido")
        void creaValido() {
            Usuario u = new Usuario(1L, "Ana", "ana@test.com", 30);
            assertThat(u.getId()).isEqualTo(1L);
            assertThat(u.getNombre()).isEqualTo("Ana");
            assertThat(u.getEmail()).isEqualTo("ana@test.com");
            assertThat(u.getEdad()).isEqualTo(30);
        }

        @Test
        @DisplayName("Lanza si id <= 0")
        void idNegativo() {
            assertThatThrownBy(() -> new Usuario(0, "X", "x@y.com", 20))
                .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> new Usuario(-5, "X", "x@y.com", 20))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Lanza si nombre nulo o vacío")
        void nombreInvalido() {
            assertThatThrownBy(() -> new Usuario(1, null, "x@y.com", 20))
                .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> new Usuario(1, "", "x@y.com", 20))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Lanza si email nulo o sin @")
        void emailInvalido() {
            assertThatThrownBy(() -> new Usuario(1, "X", null, 20))
                .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> new Usuario(1, "X", "sinarroba", 20))
                .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("Lanza si edad negativa")
        void edadNegativa() {
            assertThatThrownBy(() -> new Usuario(1, "X", "x@y.com", -1))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("setEmail")
    class SetEmail {
        @Test
        @DisplayName("Cambia email válido")
        void cambia() {
            Usuario u = new Usuario(1, "A", "a@b.com", 20);
            u.setEmail("nuevo@dominio.com");
            assertThat(u.getEmail()).isEqualTo("nuevo@dominio.com");
        }

        @Test
        @DisplayName("Lanza con email inválido")
        void rechazaInvalido() {
            Usuario u = new Usuario(1, "A", "a@b.com", 20);
            assertThatThrownBy(() -> u.setEmail("malo"))
                .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("equals / hashCode")
    class EqualsHash {
        @Test
        @DisplayName("Iguales si mismo id")
        void mismoId() {
            Usuario a = new Usuario(1, "A", "a@b.com", 20);
            Usuario b = new Usuario(1, "Otro", "x@y.com", 99);
            assertThat(a).isEqualTo(b);
            assertThat(a.hashCode()).isEqualTo(b.hashCode());
        }

        @Test
        @DisplayName("Distintos si distinto id")
        void distintoId() {
            Usuario a = new Usuario(1, "A", "a@b.com", 20);
            Usuario b = new Usuario(2, "A", "a@b.com", 20);
            assertThat(a).isNotEqualTo(b);
        }
    }

    @Nested
    @DisplayName("toString")
    class ToString {
        @Test
        @DisplayName("Formato exacto")
        void formato() {
            Usuario u = new Usuario(1, "Ana", "ana@x.com", 30);
            assertThat(u.toString())
                .isEqualTo("Usuario{id=1, nombre='Ana', email='ana@x.com', edad=30}");
        }
    }
}
