package bloque5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej30 - BOSS FINAL: Cine Multisala")
class Ej30_BossFinalTest {

    private Ej30_BossFinal cine;

    @BeforeEach
    void setUp() {
        Ej30_BossFinal.Butaca.resetContador();
        cine = new Ej30_BossFinal();
    }

    // --- Butaca ---
    @Test @DisplayName("Butaca: estado inicial L, id 0")
    void butaca_inicial() {
        var b = new Ej30_BossFinal.Butaca();
        assertEquals('L', b.getDisponibilidad());
        assertEquals(0, b.getIdReserva());
    }

    @Test @DisplayName("Butaca: reservar cambia a O con id")
    void butaca_reservar() {
        var b = new Ej30_BossFinal.Butaca();
        b.reservar();
        assertEquals('O', b.getDisponibilidad());
        assertTrue(b.getIdReserva() > 0);
    }

    @Test @DisplayName("Butaca: liberar vuelve a L")
    void butaca_liberar() {
        var b = new Ej30_BossFinal.Butaca();
        b.reservar();
        b.liberar();
        assertEquals('L', b.getDisponibilidad());
        assertEquals(0, b.getIdReserva());
    }

    // --- SesionCine ---
    @Test @DisplayName("SesionCine: constructor valido")
    void sesion_valida() {
        var s = new Ej30_BossFinal.SesionCine(1, "Test", 5, 10, 8.50);
        assertEquals(5, s.getFilas());
        assertEquals(10, s.getColumnas());
    }

    @Test @DisplayName("SesionCine: constructor invalido lanza ErrorAsientos")
    void sesion_invalida() {
        assertThrows(Ej30_BossFinal.ErrorAsientos.class,
            () -> new Ej30_BossFinal.SesionCine(1, "X", 0, 10, 8.50));
        assertThrows(Ej30_BossFinal.ErrorAsientos.class,
            () -> new Ej30_BossFinal.SesionCine(1, "X", 5, 16, 8.50));
    }

    @Test @DisplayName("SesionCine: reservar y liberar")
    void sesion_reservarLiberar() {
        var s = new Ej30_BossFinal.SesionCine(1, "Test", 3, 4, 5.0);
        assertTrue(s.reservarButaca(0, 0));
        assertFalse(s.reservarButaca(0, 0)); // ya ocupado
        int id = Ej30_BossFinal.Butaca.getContador();
        assertTrue(s.liberarButaca(id));
        assertTrue(s.reservarButaca(0, 0)); // libre de nuevo
    }

    @Test @DisplayName("SesionCine: fuera de rango false")
    void sesion_fueraRango() {
        var s = new Ej30_BossFinal.SesionCine(1, "Test", 2, 2, 5.0);
        assertFalse(s.reservarButaca(9, 9));
    }

    @Test @DisplayName("SesionCine: recaudacion")
    void sesion_recaudacion() {
        var s = new Ej30_BossFinal.SesionCine(1, "Test", 2, 2, 10.0);
        s.reservarButaca(0, 0);
        s.reservarButaca(1, 1);
        assertEquals(20.0, s.recaudacion(), 0.01);
    }

    @Test @DisplayName("SesionCine: butacas independientes")
    void sesion_butacasIndep() {
        var s = new Ej30_BossFinal.SesionCine(1, "Test", 2, 3, 5.0);
        s.reservarButaca(0, 0);
        // [0][1] debe seguir libre
        assertTrue(s.reservarButaca(0, 1));
    }

    // --- DAO ---
    @Test @DisplayName("DAO: carga 3 sesiones")
    void dao_carga() { assertEquals(3, cine.listar().size()); }

    @Test @DisplayName("DAO: buscarSesion encuentra")
    void dao_buscar() { assertNotNull(cine.buscarSesion(1)); }

    @Test @DisplayName("DAO: buscarSesion no existe")
    void dao_buscar_null() { assertNull(cine.buscarSesion(999)); }

    @Test @DisplayName("DAO: liberarGlobal funciona")
    void dao_liberarGlobal() {
        var s = cine.buscarSesion(1);
        assertNotNull(s);
        s.reservarButaca(0, 0);
        int id = Ej30_BossFinal.Butaca.getContador();
        assertTrue(cine.liberarGlobal(id));
        assertFalse(cine.liberarGlobal(id)); // ya liberado
    }

    @Test @DisplayName("DAO: recaudacionTotal")
    void dao_recaudacion() {
        assertEquals(0.0, cine.recaudacionTotal(), 0.01); // sin reservas
        cine.buscarSesion(1).reservarButaca(0, 0);
        assertTrue(cine.recaudacionTotal() > 0);
    }

    @Test @DisplayName("SesionCine: mostrarSala contiene L")
    void sesion_mostrar() {
        var s = new Ej30_BossFinal.SesionCine(1, "Test", 2, 2, 5.0);
        assertTrue(s.mostrarSala().contains("L"));
    }

    @Test @DisplayName("ErrorAsientos es IllegalArgumentException")
    void errorHerencia() {
        var e = new Ej30_BossFinal.ErrorAsientos("test");
        assertTrue(e instanceof IllegalArgumentException);
    }
}
