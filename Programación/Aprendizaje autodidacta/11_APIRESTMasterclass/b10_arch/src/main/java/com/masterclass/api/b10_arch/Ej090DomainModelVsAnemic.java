package com.masterclass.api.b10_arch;

/**
 * Ejercicio 090 · Modelo rico vs anémico.
 *
 * <p>Teoría: {@code teoria/10_Arquitectura_Patrones.md} (sección 10.2).
 *
 * <p>Un modelo RICO protege sus invariantes; uno anémico es solo datos y deja
 * que cualquiera lo corrompa. Implementa el rico.
 */
public final class Ej090DomainModelVsAnemic {

    /** Carrito con invariantes protegidas (modelo rico). */
    public static final class Carrito {
        private int unidades;
        private boolean pagado;

        public Carrito() {
            // TODO 1: el carrito nace con 0 unidades y sin pagar.
            this.unidades = 0;
            this.pagado = false;
        }

        /**
         * Añade unidades.
         *
         * @param n cantidad (&gt; 0)
         * @throws IllegalArgumentException si n &lt;= 0
         * @throws IllegalStateException si el carrito ya está pagado
         */
        public void anadir(int n) {
            // TODO 2: si n <= 0 -> IllegalArgumentException.
            // TODO 3: si ya está pagado -> IllegalStateException (invariante: no mutar tras pago).
            // TODO 4: incrementa 'unidades' en n.
        }

        /**
         * Marca el carrito como pagado.
         *
         * @throws IllegalStateException si está vacío o ya pagado
         */
        public void pagar() {
            // TODO 5: si unidades == 0 -> IllegalStateException ("carrito vacío").
            // TODO 6: si ya estaba pagado -> IllegalStateException.
            // TODO 7: marca pagado = true (transición de estado controlada).
        }

        /** @return unidades actuales */
        public int unidades() {
            // TODO 8: devuelve el número de unidades.
            return -1;
        }

        /** @return si el carrito está pagado */
        public boolean pagado() {
            // TODO 9: devuelve el flag 'pagado'.
            // TODO 10: NO expongas setters: el estado solo cambia por anadir()/pagar().
            return false;
        }
    }

    private Ej090DomainModelVsAnemic() {
    }

    public static void main(String[] args) {
        var c = new Carrito();
        c.anadir(2);
        c.pagar();
        System.out.println(c.unidades() + " pagado=" + c.pagado());
    }

    /**
     * TODO extra 1: Comprueba que el estado inicial de un carrito es correcto.
     */
    public static boolean desafioVerificarEstadoInicial(Carrito c) {
        // GUÍA: teoría 10.6 — un modelo rico nace en estado VÁLIDO: 0 unidades y
        // sin pagar. El test crea un Carrito nuevo y espera true. Depende de que
        // el constructor (TODO base) inicialice bien ambos campos.
        return c.unidades() == 0 && !c.pagado();
    }

    /**
     * TODO extra 2: Valida que las unidades a añadir sean mayores que cero.
     */
    public static void desafioValidarUnidadesAIngresar(int n) {
        // GUÍA: invariante de entrada de anadir(). CUIDADO con el tipo:
        // IllegalArgumentException (argumento inválido), distinta de la
        // IllegalStateException de "carrito pagado" (estado inválido). El test:
        // n=0 lanza, n=5 pasa.
        if (n <= 0) {
            throw new IllegalArgumentException("Unidades deben ser mayores que cero");
        }
    }

    /**
     * TODO extra 3: Lanza IllegalStateException si el carrito ya ha sido pagado.
     */
    public static void desafioVerificarNoMudarTrasPago(boolean pagado) {
        // GUÍA: invariante de ESTADO — un carrito pagado es inmutable. Aquí sí es
        // IllegalStateException (el estado no permite la operación, aunque el
        // argumento sea válido). El test: pagado=true lanza, false pasa.
        if (pagado) {
            throw new IllegalStateException("Carrito ya pagado");
        }
    }

    /**
     * TODO extra 4: Calcula el incremento de unidades.
     */
    public static int desafioIncrementarUnidades(int actual, int n) {
        // GUÍA: cálculo puro del nuevo total (sin estado). El test: 2 + 3 = 5.
        // Lo usa anadir() tras pasar las dos validaciones.
        return actual + n;
    }

    /**
     * TODO extra 5: Lanza excepción si el carrito a pagar no tiene unidades.
     */
    public static void desafioValidarCarritoVacio(int unidades) {
        // GUÍA: invariante de pagar() — no se paga un carrito sin nada. Es
        // IllegalStateException. El test: unidades=0 lanza, unidades=1 pasa.
        if (unidades == 0) {
            throw new IllegalStateException("Carrito vacío");
        }
    }

    /**
     * TODO extra 6: Lanza excepción si el carrito ya está pagado en la transición de pago.
     */
    public static void desafioVerificarDoblePago(boolean pagado) {
        // GUÍA: la otra invariante de pagar() — no se paga dos veces. Misma
        // comprobación que el reto 3, pero en la transición de pago. El test:
        // pagado=true lanza, false pasa.
        if (pagado) {
            throw new IllegalStateException("Carrito ya pagado");
        }
    }

    /**
     * TODO extra 7: Modifica el estado del flag pagado a verdadero.
     */
    public static boolean desafioTransicionEstadoPago() {
        // GUÍA: representa la transición de estado "no pagado → pagado" (el nuevo
        // valor del flag tras pagar()). El test solo comprueba que devuelve true.
        return true;
    }

    /**
     * TODO extra 8: Retorna el número de unidades de forma segura.
     */
    public static int desafioObtenerUnidadesProtegidas(Carrito c) {
        // GUÍA: lectura a través del getter (única vía de acceso al estado, no hay
        // campo público). El test añade 10 unidades y espera 10. "Protegidas"
        // porque desde fuera solo se LEE, nunca se escribe directamente.
        return c.unidades();
    }

    /**
     * TODO extra 9: Retorna si el carrito está pagado.
     */
    public static boolean desafioVerificarSiPagado(Carrito c) {
        // GUÍA: getter del flag pagado. El test crea un carrito nuevo (sin pagar)
        // y espera false. Solo lectura: el estado no se toca desde fuera.
        return c.pagado();
    }

    /**
     * TODO extra 10: Verifica el invariante global de consistencia de un Carrito.
     */
    public static boolean desafioVerificarInvarianteGlobal(Carrito c) {
        // GUÍA: invariante que SIEMPRE debe cumplirse, en cualquier estado del
        // carrito: las unidades nunca son negativas. El test lo comprueba en un
        // carrito recién creado (0 >= 0 → true). Las invariantes globales son la
        // garantía que un modelo rico mantiene pase lo que pase.
        return c.unidades() >= 0;
    }

}
