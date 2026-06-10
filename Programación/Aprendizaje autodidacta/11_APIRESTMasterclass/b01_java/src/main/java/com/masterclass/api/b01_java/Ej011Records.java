package com.masterclass.api.b01_java;

/**
 * Ejercicio 011 · Records como DTO inmutable.
 *
 * <p>Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.1).
 *
 * <p>Los componentes del record son su <em>contrato</em> (firma), por eso vienen
 * declarados. Lo que debes implementar es la validación y la copia inmutable.
 */
public final class Ej011Records {

    /** DTO inmutable de un producto. */
    public record ProductoDto(Long id, String nombre, double precio) {

        /**
         * Constructor compacto de validación.
         *
         * @throws IllegalArgumentException si el precio es negativo o el nombre está vacío
         */
        public ProductoDto {
            // TODO 1: si precio < 0, lanza IllegalArgumentException (un precio no puede ser negativo).
            // TODO 2: si nombre es null, lanza IllegalArgumentException.
            // TODO 3: si nombre está en blanco (solo espacios), lanza IllegalArgumentException.
            // TODO 4: opcional defensivo: normaliza 'nombre' con trim antes de asignarlo.
        }

        /**
         * Devuelve una copia con el precio incrementado un porcentaje.
         *
         * @param porcentaje p.ej. 21.0 para +21 %
         * @return nuevo ProductoDto con el precio ajustado (el record es inmutable)
         */
        public ProductoDto conIva(double porcentaje) {
            // TODO 5: calcula el factor multiplicador (1 + porcentaje/100).
            // TODO 6: calcula el nuevo precio aplicando ese factor al precio actual.
            // TODO 7: NO mutes este record (son inmutables): crea uno nuevo.
            // TODO 8: conserva id y nombre originales en la copia.
            // TODO 9: devuelve la nueva instancia.
            return null;
        }

        /**
         * @return true si el producto es "caro" (precio &gt;= 100).
         */
        public boolean esCaro() {
            // TODO 10: devuelve si el precio es mayor o igual a 100.
            return false;
        }
    }

    public static void main(String[] args) {
        ProductoDto p = new ProductoDto(1L, "Teclado", 100.0);
        System.out.println(p + " -> " + p.conIva(21) + " caro=" + p.esCaro());
    }

    /**
     * RETO EXTRA 1: Validación defensiva de DTOs.
     * Comprobar la integridad de un objeto ProductoDto de forma externa a su constructor.
     *
     * @param p DTO de producto a validar
     * @return true si el producto no es nulo, tiene un ID mayor a cero, precio >= 0 y nombre no nulo ni vacío
     */
    public static boolean esValido(ProductoDto p) {
        // GUÍA: teoría 1.1.
        // Devuelve true SOLO si se cumplen las 4 condiciones a la vez:
        //   p != null  &&  p.id() != null && p.id() > 0  &&
        //   p.precio() >= 0  &&  p.nombre() != null && !p.nombre().isBlank()
        // ORDEN IMPORTA: comprueba p != null PRIMERO (el && cortocircuita y
        // evita el NPE de las siguientes). El test pasa null y un id -1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esValido");
    }

    /**
     * RETO EXTRA 2: Copia inmutable con descuento.
     * En REST, los cambios de precios a menudo se reflejan aplicando promociones.
     *
     * @param p DTO original
     * @param pct porcentaje de descuento (ej. 10.0 para un 10%)
     * @return nuevo ProductoDto con el precio reducido, conservando el resto de campos intactos
     */
    public static ProductoDto conDescuento(ProductoDto p, double pct) {
        // GUÍA: el patrón "wither" (teoría 1.1, punto 3) — gemelo de conIva pero
        // restando:
        // 1. p null → decide: null o IllegalArgumentException (documéntalo).
        // 2. nuevoPrecio = p.precio() * (1 - pct / 100).
        // 3. new ProductoDto(p.id(), p.nombre(), nuevoPrecio) — NUNCA mutes p.
        // Test: 100.0 con 10% → 90.0, mismo id.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para conDescuento");
    }

    /**
     * RETO EXTRA 3: Serialización manual simple a formato JSON.
     * Comprender cómo se transforman los campos de un Record en un cuerpo de respuesta JSON.
     *
     * @param p producto
     * @return String JSON formateado (ej. {"id":1,"nombre":"Teclado","precio":100.0})
     */
    public static String aJsonSimple(ProductoDto p) {
        // GUÍA: harás esto UNA vez a mano para entender qué hace Jackson (b02).
        // 1. p null → "{}" o "null" (decide).
        // 2. Formato exacto: {"id":1,"nombre":"Teclado","precio":100.0}
        //    - números SIN comillas, strings CON comillas
        //    - sin espacios tras los ':'
        // PISTA: String.format("{\"id\":%d,\"nombre\":\"%s\",\"precio\":%s}",
        //                      p.id(), p.nombre(), p.precio())
        // (%s para el double conserva el "100.0" que espera el test).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aJsonSimple");
    }

    /**
     * RETO EXTRA 4: Creación de DTO de servicio (Record sin ID para inserción).
     * En REST, al crear un recurso el cliente no envía ID (es generado por la DB).
     *
     * @param descripcion descripción del servicio
     * @param precioHora costo por hora
     * @return un ProductoDto con ID null que represente este servicio
     */
    public static ProductoDto crearServicioDto(String descripcion, double precioHora) {
        // GUÍA: una línea — new ProductoDto(null, descripcion, precioHora).
        // EL CONCEPTO: en un POST de creación el cliente NO manda id (lo genera
        // la BD). Por eso id es Long (objeto, admite null) y no long (primitivo).
        // Esta distinción Long/long te perseguirá en JPA (b12): apréndela ya.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearServicioDto");
    }

    /**
     * RETO EXTRA 5: Comparación de equivalencia semántica de DTOs.
     * Dos DTOs de producto se consideran semánticamente equivalentes si tienen el mismo nombre (insensible a mayúsculas) y precio.
     *
     * @param p1 primer producto
     * @param p2 segundo producto
     * @return true si son semánticamente equivalentes
     */
    public static boolean esEquivalente(ProductoDto p1, ProductoDto p2) {
        // GUÍA: el equals del record compara TODOS los campos (id incluido);
        // aquí quieres una igualdad DISTINTA: solo nombre (ignorando mayúsculas)
        // y precio. Por eso es un método aparte y no el equals.
        // 1. Ambos null → true o false (decide); uno solo null → false.
        // 2. p1.nombre().equalsIgnoreCase(p2.nombre()) && p1.precio() == p2.precio()
        // El test: ids distintos (1 vs 2) y "Teclado" vs "TECLADO" → true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esEquivalente");
    }

    /**
     * RETO EXTRA 6: Normalización de copias DTO.
     * Garantizar que un producto de origen tenga un ID autogenerado válido (si venía nulo o <= 0) en su copia.
     *
     * @param p producto original
     * @return una copia normalizada con ID corregido a 999 si no era válido (> 0)
     */
    public static ProductoDto normalizarId(ProductoDto p) {
        // GUÍA:
        // 1. Si el id ES válido (no null y > 0) → devuelve p tal cual (no copies
        //    sin necesidad).
        // 2. Si no → new ProductoDto(999L, p.nombre(), p.precio()).
        // Test: id -5 → copia con id 999.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarId");
    }

    /**
     * RETO EXTRA 7: Verificación de importes redondeados.
     * En APIs de facturación, evitamos importes con centavos sucios (ej. 10.3333333).
     *
     * @param p producto
     * @return true si el precio tiene como máximo 2 decimales
     */
    public static boolean esPrecioRedondeado(ProductoDto p) {
        // GUÍA: "¿tiene como mucho 2 decimales?" sin pelearte con el double:
        // TÉCNICA A (numérica): multiplica por 100 y comprueba si el resultado
        //   es "prácticamente entero": Math.abs(x - Math.round(x)) < 1e-9
        //   (la tolerancia absorbe el error de coma flotante de 10.25 * 100).
        // TÉCNICA B (exacta): BigDecimal.valueOf(p.precio()).scale() <= 2.
        // Tests: 10.25 → true; 10.3333 → false.
        // CULTURA: por estos líos el dinero en producción se maneja con
        // BigDecimal o céntimos enteros, nunca con double.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPrecioRedondeado");
    }

    /**
     * RETO EXTRA 8: Deserialización a partir de línea de texto plana (CSV).
     *
     * @param csv fila CSV (ej. "42,Teclado Mecanico,89.99")
     * @return ProductoDto parseado
     */
    public static ProductoDto crearDesdeValores(String csv) {
        // GUÍA:
        // 1. null o en blanco → IllegalArgumentException.
        // 2. split(",") → deben salir exactamente 3 partes; si no, excepción.
        // 3. Parsea: Long.parseLong(partes[0].trim()),
        //    partes[1].trim(), Double.parseDouble(partes[2].trim()).
        // 4. Devuelve el ProductoDto — el constructor compacto que escribiste
        //    arriba valida gratis (¡por eso se valida EN el record!).
        // OJO: el nombre puede llevar espacios internos ("Teclado Mecanico"):
        // no hagas split por espacio, solo por coma.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearDesdeValores");
    }

    /**
     * RETO EXTRA 9: Formateador de etiqueta de precio estética.
     *
     * @param p producto
     * @return String formateado como "Nombre - $Precio"
     */
    public static String formatoEtiqueta(ProductoDto p) {
        // GUÍA: una línea — p.nombre() + " - $" + p.precio()
        // (el test espera exactamente "Teclado - $100.0": la concatenación de
        // un double produce ese "100.0"). Protege p null si quieres nota alta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatoEtiqueta");
    }

    /**
     * RETO EXTRA 10: Copia DTO combinando costo logístico (Envío).
     *
     * @param p producto original
     * @param costoEnvio costo del envío
     * @return ProductoDto copia con el costo de envío sumado al precio del producto
     */
    public static ProductoDto reconstruirConEnvio(ProductoDto p, double costoEnvio) {
        // GUÍA: otro wither, esta vez sumando:
        // new ProductoDto(p.id(), p.nombre(), p.precio() + costoEnvio).
        // BONUS defensivo: ¿qué haces si costoEnvio es negativo? ¿Y si la suma
        // hiciera el precio negativo (el constructor compacto lanzará)? Piensa
        // la respuesta antes de que te la dé un stack trace.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para reconstruirConEnvio");
    }

}
