package com.bootcamp.nivel1;

import com.bootcamp.nivel1_conexion.Ej03_TryWithResources;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej03Test {

    @Test
    @Order(1)
    @DisplayName("ejecutarSentencia() no lanza excepción con DDL válido")
    void ejecutarSentenciaDDL() {
        assertDoesNotThrow(() ->
            Ej03_TryWithResources.ejecutarSentencia(
                "CREATE TABLE IF NOT EXISTS test_ej03 (id INTEGER PRIMARY KEY)"
            )
        );
    }

    @Test
    @Order(2)
    @DisplayName("ejecutarSentencia() limpia (DROP) sin excepción")
    void ejecutarSentenciaDrop() {
        assertDoesNotThrow(() ->
            Ej03_TryWithResources.ejecutarSentencia("DROP TABLE IF EXISTS test_ej03")
        );
    }

    @Test
    @Order(3)
    @DisplayName("ejecutarConRetorno() devuelve boolean sin excepción")
    void ejecutarConRetornoDevuelveBoolean() {
        assertDoesNotThrow(() -> {
            boolean r = Ej03_TryWithResources.ejecutarConRetorno(
                "CREATE TABLE IF NOT EXISTS test_ej03b (id INTEGER PRIMARY KEY)"
            );
            // CREATE TABLE devuelve false en JDBC — lo importante es que no explota
            assertFalse(r, "CREATE TABLE IF NOT EXISTS debe devolver false en JDBC");
            Ej03_TryWithResources.ejecutarSentencia("DROP TABLE IF EXISTS test_ej03b");
        });
    }

    @Test
    @Order(4)
    @DisplayName("verificarCierreStatement() no lanza excepción")
    void verificarCierreStatementNoLanza() {
        assertDoesNotThrow(() -> Ej03_TryWithResources.verificarCierreStatement());
    }
}
