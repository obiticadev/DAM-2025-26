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
}
