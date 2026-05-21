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
        // TODO extra 1: implementa la lógica de validación externa.
        return false;
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
        // TODO extra 2: calcula el precio aplicando la reducción porcentual e instancia un nuevo record copia.
        return null;
    }

    /**
     * RETO EXTRA 3: Serialización manual simple a formato JSON.
     * Comprender cómo se transforman los campos de un Record en un cuerpo de respuesta JSON.
     *
     * @param p producto
     * @return String JSON formateado (ej. {"id":1,"nombre":"Teclado","precio":100.0})
     */
    public static String aJsonSimple(ProductoDto p) {
        // TODO extra 3: convierte de forma directa las propiedades del record a un formato JSON básico y limpio.
        return "";
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
        // TODO extra 4: valida el precio y descripción y devuelve una instancia con ID nulo.
        return null;
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
        // TODO extra 5: compara nombre y precio abstrayéndote del ID de base de datos.
        return false;
    }

    /**
     * RETO EXTRA 6: Normalización de copias DTO.
     * Garantizar que un producto de origen tenga un ID autogenerado válido (si venía nulo o <= 0) en su copia.
     *
     * @param p producto original
     * @return una copia normalizada con ID corregido a 999 si no era válido (> 0)
     */
    public static ProductoDto normalizarId(ProductoDto p) {
        // TODO extra 6: evalúa el ID y genera una copia con ID 999 si éste era nulo o negativo.
        return null;
    }

    /**
     * RETO EXTRA 7: Verificación de importes redondeados.
     * En APIs de facturación, evitamos importes con centavos sucios (ej. 10.3333333).
     *
     * @param p producto
     * @return true si el precio tiene como máximo 2 decimales
     */
    public static boolean esPrecioRedondeado(ProductoDto p) {
        // TODO extra 7: comprueba si el precio tiene como mucho 2 decimales significativos.
        return false;
    }

    /**
     * RETO EXTRA 8: Deserialización a partir de línea de texto plana (CSV).
     *
     * @param csv fila CSV (ej. "42,Teclado Mecanico,89.99")
     * @return ProductoDto parseado
     */
    public static ProductoDto crearDesdeValores(String csv) {
        // TODO extra 8: separa por comas, limpia espacios, castea los tipos apropiadamente y crea la instancia.
        return null;
    }

    /**
     * RETO EXTRA 9: Formateador de etiqueta de precio estética.
     *
     * @param p producto
     * @return String formateado como "Nombre - $Precio"
     */
    public static String formatoEtiqueta(ProductoDto p) {
        // TODO extra 9: genera una representación presentable en formato de etiqueta comercial.
        return "";
    }

    /**
     * RETO EXTRA 10: Copia DTO combinando costo logístico (Envío).
     *
     * @param p producto original
     * @param costoEnvio costo del envío
     * @return ProductoDto copia con el costo de envío sumado al precio del producto
     */
    public static ProductoDto reconstruirConEnvio(ProductoDto p, double costoEnvio) {
        // TODO extra 10: genera una copia sumando ambos importes y validando que el costo de envío no sea negativo.
        return null;
    }

}
