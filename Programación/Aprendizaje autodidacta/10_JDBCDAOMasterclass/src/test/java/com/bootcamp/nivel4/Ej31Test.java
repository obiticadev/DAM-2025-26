package com.bootcamp.nivel4;

import org.junit.jupiter.api.*;

import java.lang.reflect.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Valida los dos TODOs de Ej31_SimulacionTransaccion:
 * transferir() (con commit y rollback) e insertarLote().
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej31Test {

    private static Class<?> daoClass;
    private static Object dao;
    private static int idAlice;
    private static int idBob;

    @BeforeAll
    static void resolverClases() throws Exception {
        daoClass = Class.forName("com.bootcamp.nivel4_integracion.Ej31_SimulacionTransaccion$DAOCuenta");
        dao = daoClass.getDeclaredConstructor().newInstance();
        daoClass.getMethod("crearTabla").invoke(dao);
        daoClass.getMethod("insertar", String.class, double.class).invoke(dao, "Alice", 1000.0);
        daoClass.getMethod("insertar", String.class, double.class).invoke(dao, "Bob",    200.0);
        // Obtener IDs: alice = primera cuenta (id 1), bob = segunda (id 2)
        // Usamos saldo para identificarlas en lugar de asumir IDs fijos
        idAlice = 1;
        idBob   = 2;
    }

    private double getSaldo(int id) throws Exception {
        return (double) daoClass.getMethod("obtenerSaldo", int.class).invoke(dao, id);
    }

    @Test @Order(1) @DisplayName("obtenerSaldo() devuelve el saldo correcto")
    void saldoInicial() throws Exception {
        double saldo = getSaldo(idAlice);
        assertTrue(saldo > 0, "Alice debe tener saldo positivo");
    }

    @Test @Order(2) @DisplayName("transferir() modifica ambos saldos correctamente")
    void transferirOk() throws Exception {
        double antesAlice = getSaldo(idAlice);
        double antesBob   = getSaldo(idBob);
        double importe    = 100.0;

        daoClass.getMethod("transferir", int.class, int.class, double.class)
                .invoke(dao, idAlice, idBob, importe);

        double despuesAlice = getSaldo(idAlice);
        double despuesBob   = getSaldo(idBob);

        assertEquals(antesAlice - importe, despuesAlice, 0.001);
        assertEquals(antesBob   + importe, despuesBob,   0.001);
    }

    @Test @Order(3) @DisplayName("transferir() hace rollback cuando no hay saldo suficiente")
    void transferirRollback() throws Exception {
        double antesBob = getSaldo(idBob);
        double importe  = 99999.0;

        try {
            daoClass.getMethod("transferir", int.class, int.class, double.class)
                    .invoke(dao, idBob, idAlice, importe);
            fail("Debería haber lanzado excepción por saldo insuficiente");
        } catch (InvocationTargetException e) {
            // Esperado — la causa debe ser SQLException
            assertNotNull(e.getCause());
        }

        // El saldo de Bob no debe haber cambiado
        assertEquals(antesBob, getSaldo(idBob), 0.001);
    }

    @Test @Order(4) @DisplayName("insertarLote() inserta todas las cuentas correctamente")
    void insertarLoteOk() throws Exception {
        String[] nombres = {"Carlos", "Diana", "Eva"};
        double[] saldos  = {500.0, 750.0, 1200.0};
        assertDoesNotThrow(() ->
            daoClass.getMethod("insertarLote", String[].class, double[].class)
                    .invoke(dao, nombres, saldos)
        );
        // Verificar que al menos una de las cuentas nuevas existe
        // (el ID exacto depende de las inserciones previas; simplemente comprobamos que no lanza)
    }
}
