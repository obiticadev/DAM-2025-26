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
}
