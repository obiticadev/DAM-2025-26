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

    /**
     * TODO extra 1: Valida que el importe sea estrictamente mayor que cero.
     *
     * @param importe cantidad a validar
     */
    public static void desafioValidarImporte(double importe) {
        // GUÍA: teoría 10.1 — la validación de negocio vive en el Service y va
        // ANTES de tocar datos. OJO: el corte es ESTRICTO (importe <= 0 falla):
        // el test exige excepción con 0.0 y con -5.0, y que 10.0 pase limpio.
        // CULTURA: en Spring esta misma comprobación la hará @Valid o el Service
        // antes de llamar al Repository (bloque 8).
        if (importe <= 0) {
            throw new IllegalArgumentException("Importe debe ser mayor que cero");
        }
    }

    /**
     * TODO extra 2: Recupera la cuenta mediante el repositorio.
     *
     * @param repo el repositorio de cuentas
     * @param id el identificador de la cuenta
     * @return un Optional con la cuenta
     */
    public static java.util.Optional<Cuenta> desafioBuscarCuenta(CuentaRepository repo, Long id) {
        // GUÍA: teoría 10.1 y 10.2 — el Service delega la búsqueda en el puerto
        // Repository y recibe un Optional (nunca null). El test espera present
        // para el id 1 (guardado) y empty para el 2 (inexistente): por eso se
        // devuelve el Optional tal cual, sin desenvolverlo aquí.
        return repo.findById(id);
    }

    /**
     * TODO extra 3: Lanza NoSuchElementException si el Optional está vacío.
     *
     * @param optionalCuenta el optional a desempaquetar
     * @return la cuenta contenida
     */
    public static Cuenta desafioObtenerCuentaOrThrow(java.util.Optional<Cuenta> optionalCuenta) {
        // GUÍA: el patrón "si no está → error" de la teoría 1.2 aplicado a una
        // capa de servicio. orElseThrow desenvuelve si hay valor o lanza la
        // excepción de dominio si está vacío. El test comprueba que con
        // Optional.of(c) devuelve esa misma cuenta y con Optional.empty() lanza
        // NoSuchElementException (que el bloque 9 convertirá en un 404 HTTP).
        return optionalCuenta.orElseThrow(() -> new java.util.NoSuchElementException("Cuenta no encontrada"));
    }

    /**
     * TODO extra 4: Calcula el nuevo saldo sumando el importe.
     *
     * @param saldoActual saldo actual
     * @param importe cantidad a sumar
     * @return el nuevo saldo
     */
    public static double desafioCalcularNuevoSaldo(double saldoActual, double importe) {
        // GUÍA: regla de negocio pura (sin estado, sin I/O), fácil de testear de
        // forma aislada. El test verifica 100 + 50 = 150. Aislar el cálculo del
        // acceso a datos es lo que hace que el Service sea testeable sin BD.
        return saldoActual + importe;
    }

    /**
     * TODO extra 5: Crea una nueva instancia de Cuenta con el mismo ID y el nuevo saldo.
     *
     * @param id id de la cuenta
     * @param nuevoSaldo nuevo saldo
     * @return la nueva cuenta
     */
    public static Cuenta desafioCrearNuevaCuenta(Long id, double nuevoSaldo) {
        // GUÍA: Cuenta es un record INMUTABLE (teoría 1.1): no hay setSaldo();
        // "cambiar" el saldo es crear una instancia nueva con el mismo id. El
        // test comprueba que id()==5 y saldo()==200. Reutiliza esto en el
        // método base ingresar() en lugar de mutar.
        return new Cuenta(id, nuevoSaldo);
    }

    /**
     * TODO extra 6: Persiste la cuenta en el repositorio y la devuelve.
     *
     * @param repo el repositorio
     * @param cuenta la cuenta a guardar
     * @return la cuenta guardada
     */
    public static Cuenta desafioGuardarCuenta(CuentaRepository repo, Cuenta cuenta) {
        // GUÍA: save persiste y DEVUELVE lo guardado (convención de Spring Data:
        // la entidad vuelve con su id ya asignado). El test exige que el
        // retorno sea igual a la cuenta y que luego repo.findById la recupere.
        return repo.save(cuenta);
    }

    /**
     * TODO extra 7: Verifica que una excepción no sea de infraestructura HTTP.
     *
     * @param e la excepcion
     * @return true si es excepcion de dominio
     */
    public static boolean desafioVerificarAislamientoHttp(Throwable e) {
        // GUÍA: teoría 10.1 — el Service NO conoce HTTP. Este método "demuestra"
        // ese aislamiento: una excepción de dominio no lleva "Http" ni
        // "ResponseStatus" en su nombre de clase. El test pasa
        // IllegalArgumentException y NoSuchElementException (ambas de negocio) y
        // espera true en las dos. Es una idea didáctica, no algo de producción.
        return !e.getClass().getName().contains("Http") && !e.getClass().getName().contains("ResponseStatus");
    }

    /**
     * TODO extra 8: Recupera todas las cuentas del repositorio.
     *
     * @param repo el repositorio
     * @return la lista de cuentas
     */
    public static java.util.List<Cuenta> desafioObtenerTodasLasCuentas(CuentaRepository repo) {
        // GUÍA: delega en el puerto findAll(). El test guarda 2 cuentas y espera
        // size()==2. Lo usarás como primer paso de saldoTotal() (método base).
        return repo.findAll();
    }

    /**
     * TODO extra 9: Suma los saldos de una lista de cuentas usando Streams.
     *
     * @param cuentas lista de cuentas
     * @return la suma de todos los saldos
     */
    public static double desafioSumarSaldosConStream(java.util.List<Cuenta> cuentas) {
        // GUÍA: teoría 1.3 — mapToDouble + sum es la reducción numérica estándar.
        // mapToDouble pasa de Stream<Cuenta> a un DoubleStream primitivo que ya
        // sabe sumar. El test verifica 100 + 200 = 300.
        return cuentas.stream().mapToDouble(Cuenta::saldo).sum();
    }

    /**
     * TODO extra 10: Calcula el saldo total y devuelve 0.0 si la lista es nula o vacía.
     *
     * @param cuentas lista de cuentas
     * @return el saldo total
     */
    public static double desafioCalcularSaldoTotalSeguro(java.util.List<Cuenta> cuentas) {
        // GUÍA: versión defensiva que reutiliza desafioSumarSaldosConStream. OJO:
        // el test pasa null Y List.of() y exige 0.0 en ambos casos; sin el guard
        // de null, cuentas.stream() lanzaría NullPointerException. Con una cuenta
        // de 100 espera 100. Patrón "null/vacío → neutro" del bloque.
        if (cuentas == null || cuentas.isEmpty()) {
            return 0.0;
        }
        return desafioSumarSaldosConStream(cuentas);
    }

}
