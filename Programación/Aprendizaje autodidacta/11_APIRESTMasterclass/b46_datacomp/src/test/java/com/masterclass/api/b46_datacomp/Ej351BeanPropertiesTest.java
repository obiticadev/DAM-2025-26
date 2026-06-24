package com.masterclass.api.b46_datacomp;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej351BeanPropertiesTest {

    @Test
    void propiedadesDe() {
        assertEquals(List.of("activo", "edad", "nombre"),
                Ej351BeanProperties.propiedadesDe(PersonaBean.class));
        assertEquals(List.of(), Ej351BeanProperties.propiedadesDe(null)); // caso límite
    }

    @Test
    void leerPropiedad() {
        PersonaBean p = new PersonaBean("Ada", 36, true);
        assertEquals("Ada", Ej351BeanProperties.leerPropiedad(p, "nombre"));
        assertEquals(true, Ej351BeanProperties.leerPropiedad(p, "activo")); // getter "is"
        assertNull(Ej351BeanProperties.leerPropiedad(p, "noExiste"));       // caso límite
        assertNull(Ej351BeanProperties.leerPropiedad(null, "nombre"));      // caso límite
    }

    @Test
    void testRetoExtra01_nombreDePropiedadDesdeGetter() {
        assertEquals("nombre", Ej351BeanProperties.nombreDePropiedadDesdeGetter("getNombre"));
        assertEquals("activo", Ej351BeanProperties.nombreDePropiedadDesdeGetter("isActivo"));
        assertEquals("", Ej351BeanProperties.nombreDePropiedadDesdeGetter(null));
    }

    @Test
    void testRetoExtra02_nombreDeGetter() {
        assertEquals("getNombre", Ej351BeanProperties.nombreDeGetter("nombre", false));
        assertEquals("isActivo", Ej351BeanProperties.nombreDeGetter("activo", true));
    }

    @Test
    void testRetoExtra03_esGetterDeJavaBean() {
        assertTrue(Ej351BeanProperties.esGetterDeJavaBean("getNombre"));
        assertTrue(Ej351BeanProperties.esGetterDeJavaBean("isActivo"));
        assertFalse(Ej351BeanProperties.esGetterDeJavaBean("calcular"));
        assertFalse(Ej351BeanProperties.esGetterDeJavaBean("get"));
    }

    @Test
    void testRetoExtra04_esPropiedadDeSoloLectura() {
        assertTrue(Ej351BeanProperties.esPropiedadDeSoloLectura(PersonaBean.class, "descripcion"));
        assertFalse(Ej351BeanProperties.esPropiedadDeSoloLectura(PersonaBean.class, "nombre"));
    }

    @Test
    void testRetoExtra05_contarPropiedades() {
        assertEquals(3, Ej351BeanProperties.contarPropiedades(PersonaBean.class));
        assertEquals(0, Ej351BeanProperties.contarPropiedades(null));
    }

    @Test
    void testRetoExtra06_tipoDePropiedad() {
        assertEquals(int.class, Ej351BeanProperties.tipoDePropiedad(PersonaBean.class, "edad"));
        assertEquals(boolean.class, Ej351BeanProperties.tipoDePropiedad(PersonaBean.class, "activo"));
    }

    @Test
    void testRetoExtra07_copiarPropiedad() {
        PersonaBean origen = new PersonaBean("Ada", 36, true);
        PersonaBean destino = new PersonaBean();
        Ej351BeanProperties.copiarPropiedad(origen, destino, "nombre");
        assertEquals("Ada", destino.getNombre());
    }

    @Test
    void testRetoExtra08_incluyePropiedadHeredada() {
        assertTrue(Ej351BeanProperties.incluyePropiedadHeredada(EmpleadoBean.class, "nombre"));
        assertTrue(Ej351BeanProperties.incluyePropiedadHeredada(EmpleadoBean.class, "salario"));
    }

    @Test
    void testRetoExtra09_cumpleConvencionJavaBean() {
        assertTrue(Ej351BeanProperties.cumpleConvencionJavaBean(PersonaBean.class));
    }

    @Test
    void testRetoExtra10_propiedadesConIntrospector() {
        List<String> props = Ej351BeanProperties.propiedadesConIntrospector(PersonaBean.class);
        assertTrue(props.contains("nombre"));
        assertTrue(props.contains("descripcion")); // Introspector SÍ trae las de solo lectura
        assertFalse(props.contains("class"));       // ...pero NO la pseudo-propiedad 'class'
    }
}
