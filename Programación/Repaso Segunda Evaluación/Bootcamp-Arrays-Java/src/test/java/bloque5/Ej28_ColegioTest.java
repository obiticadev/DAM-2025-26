package bloque5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej28 - Colegio")
class Ej28_ColegioTest {

    @Test @DisplayName("Clase: matricular y dar de baja")
    void matricularBaja() {
        var c = new Ej28_Colegio.Clase("1A", 1, 3, 4);
        assertTrue(c.matricular(0, 0, "Ana"));
        assertFalse(c.matricular(0, 0, "Pedro")); // ocupado
        assertTrue(c.darDeBaja("Ana"));
        assertTrue(c.matricular(0, 0, "Pedro"));
    }

    @Test @DisplayName("Clase: alumnosMatriculados")
    void alumnos() {
        var c = new Ej28_Colegio.Clase("1A", 1, 2, 2);
        assertEquals(0, c.alumnosMatriculados());
        c.matricular(0, 0, "Ana");
        assertEquals(1, c.alumnosMatriculados());
    }

    @Test @DisplayName("Clase: validacion curso")
    void cursoInvalido() {
        assertThrows(IllegalArgumentException.class,
            () -> new Ej28_Colegio.Clase("X", 0, 2, 2));
        assertThrows(IllegalArgumentException.class,
            () -> new Ej28_Colegio.Clase("X", 7, 2, 2));
    }

    @Test @DisplayName("DAO: carga clases")
    void dao() {
        var cole = new Ej28_Colegio();
        assertTrue(cole.listar().size() >= 3);
    }

    @Test @DisplayName("DAO: claseConMasAlumnos no null")
    void masAlumnos() {
        var cole = new Ej28_Colegio();
        assertNotNull(cole.claseConMasAlumnos());
    }
}
