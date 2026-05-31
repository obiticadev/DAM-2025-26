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
        return c.unidades() == 0 && !c.pagado();
    }

    /**
     * TODO extra 2: Valida que las unidades a añadir sean mayores que cero.
     */
    public static void desafioValidarUnidadesAIngresar(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Unidades deben ser mayores que cero");
        }
    }

    /**
     * TODO extra 3: Lanza IllegalStateException si el carrito ya ha sido pagado.
     */
    public static void desafioVerificarNoMudarTrasPago(boolean pagado) {
        if (pagado) {
            throw new IllegalStateException("Carrito ya pagado");
        }
    }

    /**
     * TODO extra 4: Calcula el incremento de unidades.
     */
    public static int desafioIncrementarUnidades(int actual, int n) {
        return actual + n;
    }

    /**
     * TODO extra 5: Lanza excepción si el carrito a pagar no tiene unidades.
     */
    public static void desafioValidarCarritoVacio(int unidades) {
        if (unidades == 0) {
            throw new IllegalStateException("Carrito vacío");
        }
    }

    /**
     * TODO extra 6: Lanza excepción si el carrito ya está pagado en la transición de pago.
     */
    public static void desafioVerificarDoblePago(boolean pagado) {
        if (pagado) {
            throw new IllegalStateException("Carrito ya pagado");
        }
    }

    /**
     * TODO extra 7: Modifica el estado del flag pagado a verdadero.
     */
    public static boolean desafioTransicionEstadoPago() {
        return true;
    }

    /**
     * TODO extra 8: Retorna el número de unidades de forma segura.
     */
    public static int desafioObtenerUnidadesProtegidas(Carrito c) {
        return c.unidades();
    }

    /**
     * TODO extra 9: Retorna si el carrito está pagado.
     */
    public static boolean desafioVerificarSiPagado(Carrito c) {
        return c.pagado();
    }

    /**
     * TODO extra 10: Verifica el invariante global de consistencia de un Carrito.
     */
    public static boolean desafioVerificarInvarianteGlobal(Carrito c) {
        return c.unidades() >= 0;
    }

}
