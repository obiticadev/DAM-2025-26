package com.masterclass.api.b43_erp;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Ej336IntegrationSyncTest {

    private static final ClienteErp C1 = new ClienteErp("CLI-1", "Acme", "a@b.com", "ES");
    private static final ClienteErp C1MOD = new ClienteErp("CLI-1", "Acme Corp", "a@b.com", "ES");
    private static final ClienteErp C2 = new ClienteErp("CLI-2", "Globex", "g@x.com", "US");

    @Test
    void planificarSync() {
        SyncPlan plan = Ej336IntegrationSync.planificarSync(List.of(C1, C2), List.of(C1));
        assertEquals(List.of("CLI-2"), plan.altas());
        assertEquals(List.of("CLI-1"), plan.sinCambios());
        assertTrue(plan.modificaciones().isEmpty());
    }

    @Test
    void aplicarUpsertEsIdempotente() {
        List<ClienteErp> destino = List.of(C1);
        List<ClienteErp> unaVez = Ej336IntegrationSync.aplicarUpsert(destino, C2);
        List<ClienteErp> dosVeces = Ej336IntegrationSync.aplicarUpsert(unaVez, C2);
        assertEquals(2, unaVez.size());
        assertEquals(unaVez, dosVeces); // idempotencia: aplicar dos veces == una vez
        // upsert que actualiza no crece:
        assertEquals(1, Ej336IntegrationSync.aplicarUpsert(destino, C1MOD).size());
    }

    @Test
    void testRetoExtra01_clasificarAccion() {
        assertEquals("ALTA", Ej336IntegrationSync.clasificarAccion(C1, null));
        assertEquals("SIN_CAMBIOS", Ej336IntegrationSync.clasificarAccion(C1, C1));
        assertEquals("MODIFICACION", Ej336IntegrationSync.clasificarAccion(C1MOD, C1));
    }

    @Test
    void testRetoExtra02_indexarPorId() {
        Map<String, ClienteErp> idx = Ej336IntegrationSync.indexarPorId(List.of(C1, C2));
        assertEquals("Globex", idx.get("CLI-2").nombre());
    }

    @Test
    void testRetoExtra03_hashContenido() {
        ClienteErp mismoContenidoOtroId = new ClienteErp("CLI-9", "Acme", "a@b.com", "ES");
        assertEquals(Ej336IntegrationSync.hashContenido(C1),
                Ej336IntegrationSync.hashContenido(mismoContenidoOtroId));
    }

    @Test
    void testRetoExtra04_haCambiado() {
        assertFalse(Ej336IntegrationSync.haCambiado(C1, C1));
        assertTrue(Ej336IntegrationSync.haCambiado(C1, C1MOD));
    }

    @Test
    void testRetoExtra05_idsAEliminar() {
        ClienteErp c9 = new ClienteErp("CLI-9", "Viejo", "v@x.com", "ES");
        assertEquals(List.of("CLI-9"),
                Ej336IntegrationSync.idsAEliminar(List.of(C1), List.of(C1, c9)));
    }

    @Test
    void testRetoExtra06_backoffMs() {
        assertEquals(100L, Ej336IntegrationSync.backoffMs(0));
        assertEquals(800L, Ej336IntegrationSync.backoffMs(3));
    }

    @Test
    void testRetoExtra07_particionarLotes() {
        List<List<String>> lotes = Ej336IntegrationSync.particionarLotes(
                List.of("a", "b", "c", "d", "e"), 2);
        assertEquals(3, lotes.size());
        assertEquals(List.of("e"), lotes.get(2));
    }

    @Test
    void testRetoExtra08_idsDescuadrados() {
        Map<String, Double> origen = Map.of("A", 100.0, "B", 50.0);
        Map<String, Double> destino = Map.of("A", 100.0, "B", 40.0);
        assertEquals(List.of("B"), Ej336IntegrationSync.idsDescuadrados(origen, destino));
    }

    @Test
    void testRetoExtra09_fusionar() {
        ClienteErp parche = new ClienteErp("CLI-1", "", "nuevo@b.com", "");
        ClienteErp r = Ej336IntegrationSync.fusionar(C1, parche);
        assertEquals("Acme", r.nombre());       // conservado de la base
        assertEquals("nuevo@b.com", r.email());  // pisado por el parche
        assertEquals("ES", r.pais());            // conservado de la base
    }

    @Test
    void testRetoExtra10_resumenPlan() {
        SyncPlan plan = new SyncPlan(List.of("CLI-2"), List.of(), List.of("CLI-1"));
        assertTrue(Ej336IntegrationSync.resumenPlan(plan).contains("altas=1"));
    }
}
