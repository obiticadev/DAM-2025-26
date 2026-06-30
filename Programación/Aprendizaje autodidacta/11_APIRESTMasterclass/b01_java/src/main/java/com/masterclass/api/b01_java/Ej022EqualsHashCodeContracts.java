package com.masterclass.api.b01_java;

import java.util.Objects;

/**
 * Ejercicio 022 · Contratos equals/hashCode (clave para Sets y JPA).
 *
 * <p>Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.1).
 */
public final class Ej022EqualsHashCodeContracts {

    /** Entidad de negocio identificada por su 'codigo' (no por la referencia). */
    public static final class Articulo {
        private final String codigo;
        private final String nombre;

        public Articulo(String codigo, String nombre) {
            this.codigo = codigo;
            this.nombre = nombre;
        }

        public String codigo() {
            return codigo;
        }

        public String nombre() {
            return nombre;
        }

        /**
         * Dos artículos son iguales si comparten 'codigo' (la identidad de negocio).
         *
         * @param o objeto a comparar
         * @return true si es un Articulo con el mismo codigo
         */
        @Override
        public boolean equals(Object o) {
            // TODO 1: si o == this, devuelve true (reflexividad, atajo).
            // TODO 2: si o es null, devuelve false.
            // TODO 3: si la clase de o no es Articulo, devuelve false.
            // TODO 4: castea o a Articulo.
            // TODO 5: compara ÚNICAMENTE el campo 'codigo' con Objects.equals.
            // TODO 6: devuelve el resultado de esa comparación.
            return false;
        }

        /**
         * Coherente con equals: basado solo en 'codigo'.
         *
         * @return hash consistente con equals
         */
        @Override
        public int hashCode() {
            // TODO 7: el hashCode DEBE basarse en los mismos campos que equals (solo 'codigo').
            // TODO 8: usa Objects.hash(codigo) (o codigo.hashCode() controlando null).
            // TODO 9: dos objetos equals() deben tener el MISMO hashCode (contrato).
            // TODO 10: devuelve ese valor.
            return 0;
        }
    }

    private Ej022EqualsHashCodeContracts() {
    }

    public static void main(String[] args) {
        var a = new Articulo("A1", "Tornillo");
        var b = new Articulo("A1", "Tornillo (rebautizado)");
        System.out.println("equals=" + a.equals(b) + " mismoHash=" + (a.hashCode() == b.hashCode()));
        System.out.println(Objects.equals(a, b));
    }

    /**
     * Reto Extra 1: Simetría del contrato equals.
     * Comprueba si se cumple la propiedad simétrica entre dos objetos: si a es igual a b, entonces b debe ser igual a a.
     *
     * @param a primer objeto
     * @param b segundo objeto
     * @return true si se cumple la simetría (ambos se consideran iguales o ambos diferentes)
     */
    public static boolean esSimetrico(Object a, Object b) {
        // GUÍA: compara la decisión en ambos sentidos. La simetría se cumple si
        // preguntar "a contra b" y "b contra a" da una respuesta coherente, incluso
        // cuando alguno de los valores sea null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esSimetrico");
    }

    /**
     * Reto Extra 2: Transitividad del contrato equals.
     * Comprueba si se cumple la propiedad transitiva entre tres objetos: si a es igual a b, y b es igual a c, entonces a debe ser igual a c.
     *
     * @param a primer objeto
     * @param b segundo objeto
     * @param c tercer objeto
     * @return true si se cumple la transitividad (si a es igual a b y b es igual a c, entonces a es igual a c; en otro caso, true)
     */
    public static boolean esTransitivo(Object a, Object b, Object c) {
        // GUÍA: trátalo como una implicación lógica. Solo hay problema si a y b
        // son iguales, b y c también, pero a y c dejan de serlo. Si la premisa no
        // se cumple, la transitividad no queda violada.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esTransitivo");
    }

    /**
     * Reto Extra 3: Consistencia de hashCode.
     * Verifica que el valor devuelto por hashCode() sea completamente consistente y estable entre múltiples llamadas para un mismo objeto.
     *
     * @param a objeto a evaluar
     * @return true si múltiples llamadas consecutivas a hashCode() retornan exactamente el mismo valor
     */
    public static boolean esConsistenteHashCode(Object a) {
        // GUÍA: un objeto no nulo debe devolver el mismo hash mientras no cambie
        // su estado relevante. Llama varias veces y comprueba estabilidad. Este
        // contrato se rompe fácilmente si el hash depende de campos mutables.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esConsistenteHashCode");
    }

    /**
     * Reto Extra 4: Contrato de correspondencia entre equals y hashCode.
     * Valida la regla fundamental: si dos objetos son iguales según equals(), deben producir obligatoriamente el mismo hashCode().
     *
     * @param a primer objeto
     * @param b segundo objeto
     * @return true si los objetos cumplen el contrato de correspondencia hash
     */
    public static boolean verificarContratoEqualsYHashCode(Object a, Object b) {
        // GUÍA: la regla solo exige una dirección: si dos objetos son iguales,
        // sus hashCode deben coincidir. Dos objetos distintos pueden compartir hash
        // por colisión y aun así cumplir el contrato.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para verificarContratoEqualsYHashCode");
    }

    /**
     * Reto Extra 5: Seguridad frente a valores nulos en equals.
     * Comprueba que la comparación de cualquier objeto no nulo contra null devuelva siempre false de forma segura sin lanzar NullPointerException.
     *
     * @param a objeto no nulo a evaluar
     * @return true si a.equals(null) devuelve false sin lanzar excepciones
     */
    public static boolean esEqualsNuloSeguro(Object a) {
        // GUÍA: si no hay objeto, no puedes comprobar su equals. Si lo hay, llamar
        // a equals con null debe responder false y no lanzar excepción. Un equals
        // que explota ante null incumple el contrato.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esEqualsNuloSeguro");
    }

    /**
     * Reto Extra 6: Representación textual estructurada.
     * Genera una cadena formateada del objeto a partir de su nombre de clase y sus propiedades clave.
     *
     * @param nombreClase nombre de la clase del objeto
     * @param id          identificador del objeto
     * @param descripcion descripción del objeto
     * @return cadena con el formato "NombreClase{id=..., descripcion='...'}"
     */
    public static String formatearToStringElegante(String nombreClase, Object id, String descripcion) {
        // GUÍA: respeta el formato textual del contrato: nombre de clase, llaves,
        // id sin comillas y descripción entre comillas simples. El objetivo es una
        // salida estable para logs y depuración.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearToStringElegante");
    }

    /**
     * Reto Extra 7: Detección teórica de colisión de hash.
     * Evalúa si dos objetos distintos (no iguales según equals) producen fortuitamente el mismo hashCode.
     *
     * @param a primer objeto
     * @param b segundo objeto
     * @return true si existe colisión de hash (no son iguales por equals pero comparten hashCode)
     */
    public static boolean verificarColisionHash(Object a, Object b) {
        // GUÍA: una colisión ocurre cuando dos objetos no son iguales pero caen
        // en el mismo hash. Es legal y las tablas hash saben resolverlo con equals.
        // Protege los null antes de consultar hashCode.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para verificarColisionHash");
    }

    /**
     * Reto Extra 8: Reflexividad del contrato equals.
     * Valida que un objeto sea siempre igual a sí mismo (a.equals(a)).
     *
     * @param a objeto a evaluar
     * @return true si el objeto se reconoce como igual a sí mismo de forma reflexiva
     */
    public static boolean esIdentidadConsistente(Object a) {
        // GUÍA: la reflexividad dice que un objeto debe reconocerse igual a sí
        // mismo. Un null no es un objeto sobre el que probar equals. Este caso
        // justifica el atajo habitual de comparar primero contra this.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esIdentidadConsistente");
    }

    /**
     * Reto Extra 9: Cálculo de valor hash compuesto estable.
     * Calcula de forma robusta un valor hash combinado para una serie de campos, controlando nulos.
     *
     * @param campos array de objetos / campos que componen la identidad del objeto
     * @return valor hash unificado
     */
    public static int calcularHashCombinado(Object... campos) {
        // GUÍA: combina todos los campos recibidos en un único hash estable y
        // tolerante a nulls. El orden de los campos forma parte del resultado:
        // cambiarlo cambia la identidad calculada.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para calcularHashCombinado");
    }

    /**
     * Reto Extra 10: Comparación estricta de compatibilidad de tipos.
     * Comprueba si un objeto actual es compatible en tipo con una clase esperada, evitando fallos de herencia clásica.
     *
     * @param actual        objeto a evaluar
     * @param claseEsperada clase de referencia
     * @return true si el objeto no es nulo y su clase coincide exactamente con la esperada (usando getClass())
     */
    public static boolean esInstanciaCompatible(Object actual, Class<?> claseEsperada) {
        // GUÍA: este reto pide compatibilidad estricta, no "es subtipo de".
        // Compara la clase real del objeto con la clase esperada y protege valores
        // nulos. Más adelante verás por qué esto importa con proxies de JPA.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esInstanciaCompatible");
    }

}
