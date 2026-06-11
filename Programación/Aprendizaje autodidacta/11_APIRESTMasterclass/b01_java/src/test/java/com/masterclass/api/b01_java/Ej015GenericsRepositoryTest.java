package com.masterclass.api.b01_java;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class Ej015GenericsRepositoryTest {

    record User(Integer id, String nombre) {
    }

    @Test
    void crudCompleto() {
        var repo = new Ej015GenericsRepository<User, Integer>(User::id);
        repo.save(new User(1, "Ana"));
        repo.save(new User(2, "Leo"));
        assertEquals("Ana", repo.findById(1).orElseThrow().nombre());
        assertEquals(2, repo.findAll().size());
        assertTrue(repo.deleteById(1));
        assertTrue(repo.findById(1).isEmpty());
        assertFalse(repo.deleteById(99));
    }

    @Test
    void saveReemplaza() {
        var repo = new Ej015GenericsRepository<User, Integer>(User::id);
        repo.save(new User(1, "Ana"));
        repo.save(new User(1, "Ana2"));
        assertEquals("Ana2", repo.findById(1).orElseThrow().nombre());
        assertEquals(1, repo.findAll().size());
    }

    @Test
    void retoExtra01_obtenerPrimero() {
        assertEquals(Optional.of("a"), Ej015GenericsRepository.obtenerPrimero(new String[]{"a", "b"}));
        assertEquals(Optional.empty(), Ej015GenericsRepository.obtenerPrimero(new String[]{}));
        assertEquals(Optional.empty(), Ej015GenericsRepository.obtenerPrimero(null));
    }

    @Test
    void retoExtra02_intercambiarPosiciones() {
        Integer[] array = {10, 20, 30};
        Ej015GenericsRepository.intercambiarPosiciones(array, 0, 2);
        assertArrayEquals(new Integer[]{30, 20, 10}, array);
        
        assertThrows(IllegalArgumentException.class, () -> Ej015GenericsRepository.intercambiarPosiciones(array, -1, 1));
        assertThrows(IllegalArgumentException.class, () -> Ej015GenericsRepository.intercambiarPosiciones(null, 0, 1));
    }

    @Test
    void retoExtra03_buscarPorIdEnMapa() {
        java.util.Map<String, Integer> map = java.util.Map.of("uno", 1, "dos", 2);
        assertEquals(Optional.of(1), Ej015GenericsRepository.buscarPorIdEnMapa(map, "uno"));
        assertEquals(Optional.empty(), Ej015GenericsRepository.buscarPorIdEnMapa(map, "tres"));
    }

    @Test
    void retoExtra04_filtrarElementosGenericos() {
        var res = Ej015GenericsRepository.filtrarElementosGenericos(List.of(1, 2, 3, 4), n -> n % 2 == 0);
        assertEquals(List.of(2, 4), res);
    }

    @Test
    void retoExtra05_convertirListaAMapa() {
        var res = Ej015GenericsRepository.convertirListaAMapa(List.of("apple", "pear"), String::length);
        assertEquals("apple", res.get(5));
        assertEquals("pear", res.get(4));
    }

    @Test
    void retoExtra06_esMayorQue() {
        assertTrue(Ej015GenericsRepository.esMayorQue(10, 5));
        assertFalse(Ej015GenericsRepository.esMayorQue(5, 10));
        assertFalse(Ej015GenericsRepository.esMayorQue("apple", "banana"));
    }

    @Test
    void retoExtra07_crearTupla() {
        var t = Ej015GenericsRepository.crearTupla("id", 42);
        assertEquals("id", t.clave());
        assertEquals(42, t.valor());
    }

    @Test
    void retoExtra08_obtenerValorConDefecto() {
        assertEquals("original", Ej015GenericsRepository.obtenerValorConDefecto("original", "default"));
        assertEquals("default", Ej015GenericsRepository.obtenerValorConDefecto(null, "default"));
    }

    @Test
    void retoExtra09_contarOcurrenciasGenerico() {
        String[] array = {"a", "b", "a", "c"};
        assertEquals(2L, Ej015GenericsRepository.contarOcurrenciasGenerico(array, "a"));
        assertEquals(0L, Ej015GenericsRepository.contarOcurrenciasGenerico(array, "z"));
        assertEquals(0L, Ej015GenericsRepository.contarOcurrenciasGenerico(null, "a"));
    }

    @Test
    void retoExtra10_revertirListaGenerica() {
        var orig = List.of(1, 2, 3);
        var rev = Ej015GenericsRepository.revertirListaGenerica(orig);
        assertEquals(List.of(3, 2, 1), rev);
        assertEquals(List.of(1, 2, 3), orig, "La lista original no debe ser alterada");
    }
}

