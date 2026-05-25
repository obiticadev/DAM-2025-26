package com.masterclass.api.b17_nosql;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class Ej153AggregationPipelineTest {

    private final List<Pedido149> col = List.of(
            new Pedido149("a", "ana", 100),
            new Pedido149("b", "ana", 50),
            new Pedido149("c", "leo", 200),
            new Pedido149("d", "leo", 10));

    @Test
    void agrupaSumaYOrdenaDesc() {
        List<Map<String, Object>> r = Ej153AggregationPipeline.agregar(col, 40);
        assertEquals(2, r.size());
        assertEquals("leo", r.get(0).get("cliente"));
        assertEquals(200.0, ((Number) r.get(0).get("sumaTotal")).doubleValue());
        assertEquals("ana", r.get(1).get("cliente"));
        assertEquals(150.0, ((Number) r.get(1).get("sumaTotal")).doubleValue());
    }

    @Test
    void matchVacioDevuelveVacio() {
        assertTrue(Ej153AggregationPipeline.agregar(col, 9999).isEmpty());
    }

    @Test
    void nullFalla() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej153AggregationPipeline.agregar(null, 0));
    }

    @Test
    void testRetoExtra01_esEtapaAgregacionValida() {
        // Verifica si el string representa un operador de stage valido ($match, $group, $project).
        assertTrue(Ej153AggregationPipeline.esEtapaAgregacionValida("$match"));
    }

    @Test
    void testRetoExtra02_crearEtapaMatch() {
        // Genera el JSON de un stage de filtrado $match.
        assertEquals("{\"$match\":f}", Ej153AggregationPipeline.crearEtapaMatch("f"));
    }

    @Test
    void testRetoExtra03_crearEtapaGroup() {
        // Genera la definicion del stage $group.
        assertTrue(Ej153AggregationPipeline.crearEtapaGroup("id", "$sum", "total").contains("$group"));
    }

    @Test
    void testRetoExtra04_esPipelineVacio() {
        // Comprueba si la coleccion de etapas esta vacia.
        assertTrue(Ej153AggregationPipeline.esPipelineVacio(java.util.List.of()));
    }

    @Test
    void testRetoExtra05_extraerOperadorAcumulacion() {
        // Resuelve que funcion se aplica en la agregacion.
        assertEquals("SUM", Ej153AggregationPipeline.extraerOperadorAcumulacion("{\"$sum\":1}"));
    }

    @Test
    void testRetoExtra06_esStageLimitacion() {
        // Determina si la etapa reduce la cantidad de filas de forma directa ($limit o $skip).
        assertTrue(Ej153AggregationPipeline.esStageLimitacion("$limit"));
    }

    @Test
    void testRetoExtra07_esPipelineSeguroTamano() {
        // Valida que el pipeline no tenga mas de 20 etapas por legibilidad.
        assertTrue(Ej153AggregationPipeline.esPipelineSeguroTamano(5));
    }

    @Test
    void testRetoExtra08_esExcepcionDeAgregacion() {
        // Determina si el error proviene de una ejecucion fallida de pipeline.
        assertTrue(Ej153AggregationPipeline.esExcepcionDeAgregacion(new IllegalArgumentException("aggregation")));
    }

    @Test
    void testRetoExtra09_crearEtapaProject() {
        // Genera el JSON de la proyeccion $project.
        assertTrue(Ej153AggregationPipeline.crearEtapaProject(java.util.List.of("a")).contains("$project"));
    }

    @Test
    void testRetoExtra10_esAgregacionTemporal() {
        // Indica si la query usa ordenamientos de tiempo.
        assertTrue(Ej153AggregationPipeline.esAgregacionTemporal("date"));
    }

}