package com.masterclass.collections.nivel06_linkedhashmap_treemap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 18 — LinkedHashMap: Caché LRU")
class Ejercicio18_LinkedHashMapLRUCacheTest {

    // ── crearCacheLRU ────────────────────────────────────────────────────────

    @Test
    @DisplayName("crearCacheLRU() retorna un LinkedHashMap no nulo y vacío")
    void crearCacheLRU_retornaMapaVacio() {
        LinkedHashMap<String, String> cache =
                Ejercicio18_LinkedHashMapLRUCache.crearCacheLRU(3);
        assertThat(cache).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("crearCacheLRU() expulsa la entrada más antigua al superar capacidad")
    void crearCacheLRU_eviccion() {
        LinkedHashMap<String, String> cache =
                Ejercicio18_LinkedHashMapLRUCache.crearCacheLRU(2);
        cache.put("A", "1");
        cache.put("B", "2");
        cache.put("C", "3"); // debe expulsar "A"
        assertThat(cache).hasSize(2);
        assertThat(cache).doesNotContainKey("A");
        assertThat(cache).containsKeys("B", "C");
    }

    @Test
    @DisplayName("crearCacheLRU() con accessOrder=true mueve la accedida al TAIL")
    void crearCacheLRU_accessOrder() {
        LinkedHashMap<String, String> cache =
                Ejercicio18_LinkedHashMapLRUCache.crearCacheLRU(3);
        cache.put("A", "1");
        cache.put("B", "2");
        cache.put("C", "3");
        cache.get("A"); // A se mueve al final
        cache.put("D", "4"); // B se expulsa (la menos reciente)
        assertThat(cache).doesNotContainKey("B");
        assertThat(cache).containsKeys("C", "A", "D");
    }

    // ── agregarACache ────────────────────────────────────────────────────────

    @Test
    @DisplayName("agregarACache() inserta la pareja correctamente")
    void agregarACache_inserta() {
        LinkedHashMap<String, String> cache =
                Ejercicio18_LinkedHashMapLRUCache.crearCacheLRU(5);
        Ejercicio18_LinkedHashMapLRUCache.agregarACache(cache, "clave", "valor");
        assertThat(cache.get("clave")).isEqualTo("valor");
    }

    @Test
    @DisplayName("agregarACache() dispara evicción al superar capacidad")
    void agregarACache_conEviccion() {
        LinkedHashMap<String, String> cache =
                Ejercicio18_LinkedHashMapLRUCache.crearCacheLRU(2);
        Ejercicio18_LinkedHashMapLRUCache.agregarACache(cache, "A", "1");
        Ejercicio18_LinkedHashMapLRUCache.agregarACache(cache, "B", "2");
        Ejercicio18_LinkedHashMapLRUCache.agregarACache(cache, "C", "3");
        assertThat(cache).hasSize(2);
        assertThat(cache).doesNotContainKey("A");
    }

    // ── consultarCache ───────────────────────────────────────────────────────

    @Test
    @DisplayName("consultarCache() retorna el valor si la clave existe")
    void consultarCache_existe() {
        LinkedHashMap<String, String> cache =
                Ejercicio18_LinkedHashMapLRUCache.crearCacheLRU(3);
        cache.put("X", "datos");
        assertThat(Ejercicio18_LinkedHashMapLRUCache.consultarCache(cache, "X"))
                .isEqualTo("datos");
    }

    @Test
    @DisplayName("consultarCache() retorna null si la clave no existe")
    void consultarCache_noExiste() {
        LinkedHashMap<String, String> cache =
                Ejercicio18_LinkedHashMapLRUCache.crearCacheLRU(3);
        assertThat(Ejercicio18_LinkedHashMapLRUCache.consultarCache(cache, "nada"))
                .isNull();
    }

    // ── obtenerOrdenActual ───────────────────────────────────────────────────

    @Test
    @DisplayName("obtenerOrdenActual() refleja el orden de acceso")
    void obtenerOrdenActual_ordenAcceso() {
        LinkedHashMap<String, String> cache =
                Ejercicio18_LinkedHashMapLRUCache.crearCacheLRU(3);
        cache.put("A", "1");
        cache.put("B", "2");
        cache.put("C", "3");
        cache.get("A"); // mueve A al final
        String[] orden = Ejercicio18_LinkedHashMapLRUCache.obtenerOrdenActual(cache);
        assertThat(orden).containsExactly("B", "C", "A");
    }

    // ── simularAccesosYVerEviccion ────────────────────────────────────────────

    @Test
    @DisplayName("simularAccesosYVerEviccion() retorna [C, A, D] tras la secuencia")
    void simularAccesosYVerEviccion_resultado() {
        String[] resultado =
                Ejercicio18_LinkedHashMapLRUCache.simularAccesosYVerEviccion();
        assertThat(resultado).containsExactly("C", "A", "D");
    }

    // ── cacheTieneEspacio ────────────────────────────────────────────────────

    @Test
    @DisplayName("cacheTieneEspacio() retorna true si hay espacio")
    void cacheTieneEspacio_hayEspacio() {
        LinkedHashMap<String, String> cache =
                Ejercicio18_LinkedHashMapLRUCache.crearCacheLRU(3);
        cache.put("A", "1");
        assertThat(Ejercicio18_LinkedHashMapLRUCache.cacheTieneEspacio(cache, 3))
                .isTrue();
    }

    @Test
    @DisplayName("cacheTieneEspacio() retorna false si está llena")
    void cacheTieneEspacio_llena() {
        LinkedHashMap<String, String> cache =
                Ejercicio18_LinkedHashMapLRUCache.crearCacheLRU(2);
        cache.put("A", "1");
        cache.put("B", "2");
        assertThat(Ejercicio18_LinkedHashMapLRUCache.cacheTieneEspacio(cache, 2))
                .isFalse();
    }
}
