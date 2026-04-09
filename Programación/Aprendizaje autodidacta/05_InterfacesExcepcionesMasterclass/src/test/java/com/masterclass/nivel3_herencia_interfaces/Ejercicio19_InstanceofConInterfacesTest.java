package com.masterclass.nivel3_herencia_interfaces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 3 - Ejercicio 19: Instanceof con Interfaces")
class Ejercicio19_InstanceofConInterfacesTest {

    @Test
    @DisplayName("19.1 - soloSerializable es Serializable pero no Cacheable")
    void soloSerializable() {
        var obj = Ejercicio19_InstanceofConInterfaces.crearSoloSerializable("datos");
        assertThat(obj).isInstanceOf(Ejercicio19_InstanceofConInterfaces.Serializable.class);
        assertThat(obj).isNotInstanceOf(Ejercicio19_InstanceofConInterfaces.Cacheable.class);
    }

    @Test
    @DisplayName("19.2 - serializableYCacheable es ambas")
    void serializableYCacheable() {
        var obj = Ejercicio19_InstanceofConInterfaces.crearSerializableYCacheable("datos");
        assertThat(obj).isInstanceOf(Ejercicio19_InstanceofConInterfaces.Serializable.class);
        assertThat(obj).isInstanceOf(Ejercicio19_InstanceofConInterfaces.Cacheable.class);
    }

    @Test
    @DisplayName("19.3 - completo implementa las tres interfaces")
    void completo() {
        var obj = Ejercicio19_InstanceofConInterfaces.crearCompleto("datos");
        assertThat(obj).isInstanceOf(Ejercicio19_InstanceofConInterfaces.Serializable.class);
        assertThat(obj).isInstanceOf(Ejercicio19_InstanceofConInterfaces.Cacheable.class);
        assertThat(obj).isInstanceOf(Ejercicio19_InstanceofConInterfaces.Loggeable.class);
    }

    @Test
    @DisplayName("19.4 - serializar devuelve formato correcto")
    void serializarFormato() {
        var obj = (Ejercicio19_InstanceofConInterfaces.Serializable)
                Ejercicio19_InstanceofConInterfaces.crearCompleto("test");
        assertThat(obj.serializar()).isEqualTo("JSON:test");
    }

    @Test
    @DisplayName("19.5 - getCacheKey devuelve formato correcto")
    void cacheKeyFormato() {
        var obj = (Ejercicio19_InstanceofConInterfaces.Cacheable)
                Ejercicio19_InstanceofConInterfaces.crearSerializableYCacheable("test");
        assertThat(obj.getCacheKey()).isEqualTo("CACHE_test");
    }

    @Test
    @DisplayName("19.6 - contarCacheables cuenta correctamente")
    void contarCacheables() {
        var objetos = List.<Object>of(
                Ejercicio19_InstanceofConInterfaces.crearSoloSerializable("a"),
                Ejercicio19_InstanceofConInterfaces.crearSerializableYCacheable("b"),
                Ejercicio19_InstanceofConInterfaces.crearCompleto("c")
        );
        assertThat(Ejercicio19_InstanceofConInterfaces.contarCacheables(objetos)).isEqualTo(2);
    }

    @Test
    @DisplayName("19.7 - intentarSerializar con serializable")
    void intentarSerializarOk() {
        var obj = Ejercicio19_InstanceofConInterfaces.crearSoloSerializable("ok");
        assertThat(Ejercicio19_InstanceofConInterfaces.intentarSerializar(obj)).isEqualTo("JSON:ok");
    }

    @Test
    @DisplayName("19.8 - intentarSerializar con no-serializable")
    void intentarSerializarNoOk() {
        assertThat(Ejercicio19_InstanceofConInterfaces.intentarSerializar("string normal"))
                .isEqualTo("No serializable");
    }
}
