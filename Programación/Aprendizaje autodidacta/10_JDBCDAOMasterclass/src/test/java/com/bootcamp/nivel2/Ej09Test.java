package com.bootcamp.nivel2;

import com.bootcamp.nivel2_crud.Ej09_InsertStringParams;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej09Test {

    private static Connection mem;

    @BeforeAll
    static void setup() throws SQLException {
        mem = DriverManager.getConnection("jdbc:sqlite::memory:");
        try (Statement s = mem.createStatement()) {
            s.execute("""
                    CREATE TABLE Paises (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        nombre TEXT NOT NULL, capital TEXT, continente TEXT)""");
        }
    }

    @AfterAll
    static void teardown() throws SQLException {
        mem.close();
    }

    @Test
    @Order(1)
    @DisplayName("insertarPais() devuelve true con datos válidos")
    void insertarPaisOk() throws SQLException {
        assertTrue(Ej09_InsertStringParams.insertarPais("España", "Madrid", "Europa"));
    }

    @Test
    @Order(2)
    @DisplayName("insertarPais() incrementa el contador de filas")
    void insertarPaisIncrementaContador() throws SQLException {
        int antes = Ej09_InsertStringParams.contarPaises();
        Ej09_InsertStringParams.insertarPais("Francia", "París", "Europa");
        int despues = Ej09_InsertStringParams.contarPaises();
        assertTrue(despues > antes, "Después de insertar debe haber más filas");
    }

    @Test
    @Order(3)
    @DisplayName("insertarPaisSinContinente() devuelve true")
    void insertarSinContinenteOk() throws SQLException {
        assertTrue(Ej09_InsertStringParams.insertarPaisSinContinente("Pangea", "Sin capital"));
    }

    @Test
    @Order(4)
    @DisplayName("contarPaises() devuelve valor >= 0")
    void contarPaisesNoNegativo() throws SQLException {
        int total = Ej09_InsertStringParams.contarPaises();
        assertTrue(total >= 0);
    }

    @Test
    @Order(5)
    @DisplayName("Múltiples inserts acumulan correctamente")
    void multiplesInserts() throws SQLException {
        int antes = Ej09_InsertStringParams.contarPaises();
        Ej09_InsertStringParams.insertarPais("Alemania", "Berlín", "Europa");
        Ej09_InsertStringParams.insertarPais("Brasil", "Brasilia", "América");
        assertEquals(antes + 2, Ej09_InsertStringParams.contarPaises());
    }
}
