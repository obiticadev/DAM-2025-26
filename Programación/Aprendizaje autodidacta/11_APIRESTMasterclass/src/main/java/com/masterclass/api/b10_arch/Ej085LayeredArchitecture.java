package com.masterclass.api.b10_arch;

import java.util.List;
import java.util.Optional;

/**
 * Ejercicio 085 · Arquitectura por capas (Controller→Service→Repository).
 *
 * <p>Teoría: {@code teoria/10_Arquitectura_Patrones.md} (sección 10.1).
 *
 * <p>El Service contiene la regla de negocio; delega persistencia al Repository.
 */
public final class Ej085LayeredArchitecture {

    public record Cuenta(Long id, double saldo) {
    }

    /** Puerto de persistencia (capa Repository). */
    public interface CuentaRepository {
        Optional<Cuenta> findById(Long id);
        Cuenta save(Cuenta c);
        List<Cuenta> findAll();
    }

    /** Capa Service: SOLO habla con el Repository, nunca con HTTP. */
    public static class CuentaService {
        private final CuentaRepository repo;

        public CuentaService(CuentaRepository repo) {
            this.repo = repo;
        }

        /**
         * Ingresa dinero en una cuenta.
         *
         * @param id     id de la cuenta
         * @param importe cantidad a ingresar (debe ser &gt; 0)
         * @return la cuenta con el nuevo saldo
         * @throws IllegalArgumentException si importe &lt;= 0
         * @throws java.util.NoSuchElementException si la cuenta no existe
         */
        public Cuenta ingresar(Long id, double importe) {
            // TODO 1: valida importe > 0; si no, IllegalArgumentException.
            // TODO 2: recupera la cuenta vía repo.findById(id).
            // TODO 3: si no existe, lanza NoSuchElementException.
            // TODO 4: calcula el nuevo saldo (saldo + importe).
            // TODO 5: crea una Cuenta NUEVA (record inmutable) con ese saldo.
            // TODO 6: persiste con repo.save(...) y devuelve lo guardado.
            // TODO 7: el Service NO conoce HTTP ni status codes (separación de capas).
            return null;
        }

        /**
         * @return saldo total sumando todas las cuentas
         */
        public double saldoTotal() {
            // TODO 8: obtén todas las cuentas con repo.findAll().
            // TODO 9: suma sus saldos con un stream (mapToDouble + sum).
            // TODO 10: devuelve el total (0.0 si no hay cuentas).
            return -1;
        }
    }

    private Ej085LayeredArchitecture() {
    }

    public static void main(String[] args) {
        System.out.println("capas listas");
    }
}
