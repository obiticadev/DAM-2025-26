package com.masterclass.api.b10_arch;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b10_arch.Ej085LayeredArchitecture.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class Ej085LayeredArchitectureTest {

    static class RepoMem implements CuentaRepository {
        final Map<Long, Cuenta> db = new LinkedHashMap<>();
        public Optional<Cuenta> findById(Long id) {
            return Optional.ofNullable(db.get(id));
        }
        public Cuenta save(Cuenta c) {
            db.put(c.id(), c);
            return c;
        }
        public List<Cuenta> findAll() {
            return new ArrayList<>(db.values());
        }
    }

    @Test
    void ingresarYTotal() {
        var repo = new RepoMem();
        repo.save(new Cuenta(1L, 100));
        repo.save(new Cuenta(2L, 50));
        var s = new CuentaService(repo);
        assertEquals(140.0, s.ingresar(1L, 40).saldo(), 0.0001);
        assertEquals(190.0, s.saldoTotal(), 0.0001);
    }

    @Test
    void importeInvalido() {
        var s = new CuentaService(new RepoMem());
        assertThrows(IllegalArgumentException.class, () -> s.ingresar(1L, -5));
    }

    @Test
    void cuentaInexistente() {
        var s = new CuentaService(new RepoMem());
        assertThrows(NoSuchElementException.class, () -> s.ingresar(99L, 10));
    }

@Test
    void testDesafioValidarImporte() {
        assertDoesNotThrow(() -> Ej085LayeredArchitecture.desafioValidarImporte(10.0));
        assertThrows(IllegalArgumentException.class, () -> Ej085LayeredArchitecture.desafioValidarImporte(0.0));
        assertThrows(IllegalArgumentException.class, () -> Ej085LayeredArchitecture.desafioValidarImporte(-5.0));
    }

    @Test
    void testDesafioBuscarCuenta() {
        var repo = new RepoMem();
        var c = new Cuenta(1L, 100.0);
        repo.save(c);
        assertTrue(Ej085LayeredArchitecture.desafioBuscarCuenta(repo, 1L).isPresent());
        assertFalse(Ej085LayeredArchitecture.desafioBuscarCuenta(repo, 2L).isPresent());
    }

    @Test
    void testDesafioObtenerCuentaOrThrow() {
        var c = new Cuenta(1L, 100.0);
        assertEquals(c, Ej085LayeredArchitecture.desafioObtenerCuentaOrThrow(Optional.of(c)));
        assertThrows(NoSuchElementException.class, () -> Ej085LayeredArchitecture.desafioObtenerCuentaOrThrow(Optional.empty()));
    }

    @Test
    void testDesafioCalcularNuevoSaldo() {
        assertEquals(150.0, Ej085LayeredArchitecture.desafioCalcularNuevoSaldo(100.0, 50.0), 0.0001);
    }

    @Test
    void testDesafioCrearNuevaCuenta() {
        var c = Ej085LayeredArchitecture.desafioCrearNuevaCuenta(5L, 200.0);
        assertEquals(5L, c.id());
        assertEquals(200.0, c.saldo(), 0.0001);
    }

    @Test
    void testDesafioGuardarCuenta() {
        var repo = new RepoMem();
        var c = new Cuenta(1L, 100.0);
        var saved = Ej085LayeredArchitecture.desafioGuardarCuenta(repo, c);
        assertEquals(c, saved);
        assertEquals(c, repo.findById(1L).orElse(null));
    }

    @Test
    void testDesafioVerificarAislamientoHttp() {
        assertTrue(Ej085LayeredArchitecture.desafioVerificarAislamientoHttp(new IllegalArgumentException()));
        assertTrue(Ej085LayeredArchitecture.desafioVerificarAislamientoHttp(new NoSuchElementException()));
    }

    @Test
    void testDesafioObtenerTodasLasCuentas() {
        var repo = new RepoMem();
        repo.save(new Cuenta(1L, 100.0));
        repo.save(new Cuenta(2L, 200.0));
        var list = Ej085LayeredArchitecture.desafioObtenerTodasLasCuentas(repo);
        assertEquals(2, list.size());
    }

    @Test
    void testDesafioSumarSaldosConStream() {
        var list = List.of(new Cuenta(1L, 100.0), new Cuenta(2L, 200.0));
        assertEquals(300.0, Ej085LayeredArchitecture.desafioSumarSaldosConStream(list), 0.0001);
    }

    @Test
    void testDesafioCalcularSaldoTotalSeguro() {
        assertEquals(0.0, Ej085LayeredArchitecture.desafioCalcularSaldoTotalSeguro(null), 0.0001);
        assertEquals(0.0, Ej085LayeredArchitecture.desafioCalcularSaldoTotalSeguro(List.of()), 0.0001);
        var list = List.of(new Cuenta(1L, 100.0));
        assertEquals(100.0, Ej085LayeredArchitecture.desafioCalcularSaldoTotalSeguro(list), 0.0001);
    }
}
