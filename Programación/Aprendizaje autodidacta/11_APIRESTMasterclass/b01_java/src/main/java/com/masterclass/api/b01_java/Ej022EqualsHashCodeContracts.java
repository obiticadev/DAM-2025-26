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
        // GUÍA: simetría = a.equals(b) y b.equals(a) dan LO MISMO.
        // Con Objects.equals esquivas los null que mete el test:
        // return Objects.equals(a, b) == Objects.equals(b, a);
        // (Objects.equals(null, x) devuelve false sin NPE; (null, null) → true.)
        // CULTURA: la simetría se rompe de verdad cuando una subclase
        // sobreescribe equals y la superclase no — el bug clásico de herencia.
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
        // GUÍA: transitividad como IMPLICACIÓN lógica:
        // "si (a==b Y b==c) entonces a==c" — y una implicación con premisa
        // falsa es VERDADERA (por eso el test con null da true: a≠null corta
        // la premisa).
        // boolean ab = Objects.equals(a, b), bc = Objects.equals(b, c);
        // return !(ab && bc) || Objects.equals(a, c);
        // (Recuerda de lógica: p → q  ≡  ¬p ∨ q.)
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
        // GUÍA:
        // 1. a null → false (el test lo exige).
        // 2. Llama a.hashCode() varias veces (2-3 bastan) y comprueba que
        //    todas devuelven el mismo valor:
        //    int h = a.hashCode();
        //    return h == a.hashCode() && h == a.hashCode();
        // POR QUÉ EXISTE: si hashCode dependiera de un campo MUTABLE y alguien
        // lo cambia con el objeto dentro de un HashSet, el objeto queda
        // "perdido" en el cajón equivocado. Por eso: hashCode sobre campos
        // inmutables (el id), nunca sobre estado que cambia.
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
        // GUÍA: LA regla del bloque (teoría 1.12), como implicación:
        // "si a.equals(b) entonces mismo hashCode" — otra vez p → q:
        // return !Objects.equals(a, b) || a.hashCode() == b.hashCode();
        // Fíjate en el 2º test: dos artículos DISTINTOS también "cumplen" el
        // contrato (premisa falsa → implicación verdadera). El contrato NO
        // exige lo inverso: hashCodes iguales con objetos distintos es una
        // colisión legal (reto 7).
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
        // GUÍA: el contrato exige que x.equals(null) sea false SIN explotar:
        // 1. a null → false (no hay objeto que probar).
        // 2. try { return !a.equals(null); }
        //    catch (RuntimeException e) { return false; }
        // El try/catch es el punto: un equals que lanza NPE al recibir null
        // está ROTO, y este método lo detecta en vez de propagarlo.
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
        // GUÍA: formato EXACTO del test:
        //   "Articulo{id=123, descripcion='Tornillo de acero'}"
        // — el id SIN comillas, la descripción CON comillas simples:
        // return nombreClase + "{id=" + id + ", descripcion='" + descripcion + "'}";
        // Es el formato del toString que generan los IDEs y Lombok: legible
        // en logs y consistente. Los records ya traen el suyo gratis.
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
        // GUÍA: colisión = NO equals pero SÍ mismo hash:
        // return !Objects.equals(a, b) && a.hashCode() == b.hashCode();
        // (protege los null antes si quieres ser fino).
        // El test usa la pareja famosa "Aa"/"BB": ambas hashean a 2112. Las
        // colisiones son LEGALES (hay infinitos strings y solo 2^32 ints);
        // un HashMap las resuelve con equals dentro del cajón. Lo ilegal es
        // lo contrario: equals true con hashes distintos.
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
        // GUÍA: una línea — return a != null && a.equals(a);
        // Reflexividad: todo objeto es igual a sí mismo. Parece imposible de
        // romper… hasta que alguien escribe un equals con campos double y NaN
        // (NaN != NaN). Por eso el "if (o == this) return true" del TODO 1
        // de arriba no es solo un atajo de rendimiento: blinda la reflexividad.
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
        // GUÍA: una línea — return Objects.hash(campos);
        // El test compara contra Objects.hash("A1", 100) directamente: tu
        // varargs Object... ya ES el array que hash espera. Objects.hash
        // tolera nulls y combina con la fórmula 31*h + x. Para UN solo campo,
        // Objects.hashCode(campo) evita crear el array (micro-detalle).
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
        // GUÍA: una línea —
        // return actual != null && actual.getClass() == claseEsperada;
        // EL DEBATE DE DISEÑO que cierra el bloque: en equals puedes comparar
        // tipos con instanceof (acepta subclases → flexible) o con getClass()
        // (clase EXACTA → estricto, lo que pide este reto). JPA/Hibernate usa
        // proxies que son SUBCLASES de tu entidad: con getClass() un proxy
        // jamás sería igual a su entidad real. Guarda esta perla para b12.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esInstanciaCompatible");
    }

}
