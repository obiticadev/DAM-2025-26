package com.masterclass.api.b03_core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej029ManualIoCContainerTest {

    @Test
    void singletonYResolucion() {
        var c = new Ej029ManualIoCContainer();
        c.register(StringBuilder.class, StringBuilder::new);
        var a = c.getBean(StringBuilder.class);
        var b = c.getBean(StringBuilder.class);
        assertSame(a, b, "debe ser singleton");
    }

    @Test
    void noRegistrado() {
        var c = new Ej029ManualIoCContainer();
        assertThrows(IllegalStateException.class, () -> c.getBean(String.class));
    }

    @Test
    void retoExtra01_prototype() {
        var c = new Ej029ManualIoCContainer();
        c.registerPrototype(StringBuilder.class, StringBuilder::new);
        var a = c.getBean(StringBuilder.class);
        var b = c.getBean(StringBuilder.class);
        assertNotSame(a, b, "Prototype debe generar instancias distintas");
    }

    @Test
    void retoExtra02_y_03_nameRegistrationAndResolution() {
        var c = new Ej029ManualIoCContainer();
        String instance = "Hola Mundo";
        c.registerSingletonInstance("saludo", instance);
        assertEquals(instance, c.getBeanByName("saludo"));
        assertThrows(IllegalStateException.class, () -> c.getBeanByName("inexistente"));
    }

    @Test
    void retoExtra04_hasBean() {
        var c = new Ej029ManualIoCContainer();
        assertFalse(c.hasBean(String.class));
        c.register(String.class, () -> "test");
        assertTrue(c.hasBean(String.class));
    }

    @Test
    void retoExtra05_clear() {
        var c = new Ej029ManualIoCContainer();
        c.register(String.class, () -> "test");
        c.clear();
        assertFalse(c.hasBean(String.class));
    }

    @Test
    void retoExtra06_getBeanCount() {
        var c = new Ej029ManualIoCContainer();
        assertEquals(0, c.getBeanCount());
        c.register(String.class, () -> "test");
        c.registerSingletonInstance("num", 42);
        assertEquals(2, c.getBeanCount());
    }

    @Test
    void retoExtra07_registerWithDependency() {
        var c = new Ej029ManualIoCContainer();
        c.register(String.class, () -> "Prefijo");
        c.registerWithDependency(StringBuilder.class, String.class, (dep) -> new StringBuilder((String) dep).append("Suffix"));
        StringBuilder sb = c.getBean(StringBuilder.class);
        assertEquals("PrefijoSuffix", sb.toString());
    }

    @Test
    void retoExtra08_getBeansOfType() {
        var c = new Ej029ManualIoCContainer();
        c.registerSingletonInstance("s1", "texto1");
        c.registerSingletonInstance("s2", "texto2");
        c.registerSingletonInstance("i1", 100);
        
        java.util.Map<String, String> strings = c.getBeansOfType(String.class);
        assertEquals(2, strings.size());
        assertTrue(strings.containsValue("texto1"));
        assertTrue(strings.containsValue("texto2"));
    }

    @Test
    void retoExtra09_alias() {
        var c = new Ej029ManualIoCContainer();
        c.registerSingletonInstance("original", "valorReal");
        c.registerAlias("original", "miAlias");
        assertEquals("valorReal", c.getBeanByName("miAlias"));
    }

    @Test
    void retoExtra10_close() throws Exception {
        class BeanCerrable implements AutoCloseable {
            boolean cerrado = false;
            @Override
            public void close() {
                cerrado = true;
            }
        }
        var c = new Ej029ManualIoCContainer();
        var b = new BeanCerrable();
        c.registerSingletonInstance("cerrable", b);
        
        // Disparar la resolución del singleton para cachearlo
        c.getBeanByName("cerrable");
        
        c.close();
        assertTrue(b.cerrado, "El close() del contenedor debe propagarse a los singletons AutoCloseable");
    }
}
