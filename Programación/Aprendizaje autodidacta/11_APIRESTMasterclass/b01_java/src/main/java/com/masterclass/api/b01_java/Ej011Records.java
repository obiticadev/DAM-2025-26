package com.masterclass.api.b01_java;

/**
 * Ejercicio 011 · Records como DTO inmutable.
 *
 * <p>
 * Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.1).
 *
 * <p>
 * Los componentes del record son su <em>contrato</em> (firma), por eso vienen
 * declarados. Lo que debes implementar es la validación y la copia inmutable.
 */
public final class Ej011Records {

    /** DTO inmutable de un producto. */
    public record ProductoDto(Long id, String nombre, double precio) {

        /**
         * Constructor compacto de validación.
         *
         * @throws IllegalArgumentException si el precio es negativo o el nombre está
         *                                  vacío
         */
        public ProductoDto {
            // TODO 1: si precio < 0, lanza IllegalArgumentException (un precio no puede ser
            // negativo).
            // TODO 2: si nombre es null, lanza IllegalArgumentException.
            // TODO 3: si nombre está en blanco (solo espacios), lanza
            // IllegalArgumentException.
            // TODO 4: opcional defensivo: normaliza 'nombre' con trim antes de asignarlo.
            if (precio < 0 || nombre == null || nombre.isBlank()) {
                throw new IllegalArgumentException();
            }
            nombre = nombre.trim();

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
            double factorMultiplicador = 1 + porcentaje / 100;
            double newPrice = precio * factorMultiplicador;
            return new ProductoDto(id, nombre, newPrice);
        }

        /**
         * @return true si el producto es "caro" (precio &gt;= 100).
         */
        public boolean esCaro() {
            // TODO 10: devuelve si el precio es mayor o igual a 100.
            if (precio >= 100) {
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        ProductoDto p = new ProductoDto(1L, "Teclado", 100.0);
        System.out.println(p + " -> " + p.conIva(21) + " caro=" + p.esCaro());
    }

    /**
     * RETO EXTRA 1: Validación defensiva de DTOs.
     * Comprobar la integridad de un objeto ProductoDto de forma externa a su
     * constructor.
     *
     * @param p DTO de producto a validar
     * @return true si el producto no es nulo, tiene un ID mayor a cero, precio >= 0
     *         y nombre no nulo ni vacío
     */
    public static boolean esValido(ProductoDto p) {
        // GUÍA: valida desde fuera lo mismo que esperarías de un DTO recibido por API.
        // Todas las condiciones deben cumplirse a la vez y el orden de comprobación
        // importa para no leer campos de un objeto nulo. Piensa en null, id inválido,
        // precio negativo y nombre vacío como casos independientes.
        if (p != null && p.id() != null && p.id() > 0 && p.precio() >= 0 && p.nombre() != null
                && !p.nombre().isBlank()) {
            return true;
        }
        return false;
    }

    /**
     * RETO EXTRA 2: Copia inmutable con descuento.
     * En REST, los cambios de precios a menudo se reflejan aplicando promociones.
     *
     * @param p   DTO original
     * @param pct porcentaje de descuento (ej. 10.0 para un 10%)
     * @return nuevo ProductoDto con el precio reducido, conservando el resto de
     *         campos intactos
     */
    public static ProductoDto conDescuento(ProductoDto p, double pct) {
        // GUÍA: aplica la misma idea de copia inmutable que en conIva, pero el
        // cambio de precio va en sentido contrario. Conserva identidad y nombre,
        // y decide explícitamente qué hacer si no recibes producto.
        if (p == null) {
            throw new IllegalArgumentException();
        }
        double promotion = 1 - pct / 100;
        double newPrice = p.precio() * promotion;
        return new ProductoDto(p.id(), p.nombre(), newPrice);
    }

    /**
     * RETO EXTRA 3: Serialización manual simple a formato JSON.
     * Comprender cómo se transforman los campos de un Record en un cuerpo de
     * respuesta JSON.
     *
     * @param p producto
     * @return String JSON formateado (ej.
     *         {"id":1,"nombre":"Teclado","precio":100.0})
     */
    public static String aJsonSimple(ProductoDto p) {
        // GUÍA: construye una representación JSON mínima respetando el contrato
        // textual del Javadoc. Fíjate en qué campos son numéricos y cuál es texto:
        // las comillas no se aplican igual a todos. Define también el caso null.
        if (p == null) {
            return "{}";
        }
        return String.format("{\n\t\"id\":%d,\n\t\"nombre\":\"%s\",\n\t\"precio\":%s\n}", p.id(), p.nombre(),
                p.precio());
    }

    /**
     * RETO EXTRA 4: Creación de DTO de servicio (Record sin ID para inserción).
     * En REST, al crear un recurso el cliente no envía ID (es generado por la DB).
     *
     * @param descripcion descripción del servicio
     * @param precioHora  costo por hora
     * @return un ProductoDto con ID null que represente este servicio
     */
    public static ProductoDto crearServicioDto(String descripcion, double precioHora) {
        // GUÍA: modela un DTO de creación: aún no existe identificador porque lo
        // generará el servidor o la base de datos. La descripción y el precio sí
        // forman parte de la petición y deben pasar por las reglas del record.
        return new ProductoDto(null, descripcion, precioHora);
    }

    /**
     * RETO EXTRA 5: Comparación de equivalencia semántica de DTOs.
     * Dos DTOs de producto se consideran semánticamente equivalentes si tienen el
     * mismo nombre (insensible a mayúsculas) y precio.
     *
     * @param p1 primer producto
     * @param p2 segundo producto
     * @return true si son semánticamente equivalentes
     */
    public static boolean esEquivalente(ProductoDto p1, ProductoDto p2) {
        // GUÍA: no uses el equals del record: ese compara todos los componentes,
        // incluido el id. Aquí la equivalencia es de negocio: nombre comparable
        // sin distinguir mayúsculas y mismo precio. Trata los null de forma coherente.
        if (p1 == null && p2 == null) {
            return true;
        }
        if ((p1 == null && p2 != null) || (p1 != null && p2 == null)) {
            return false;
        }
        if (p1.nombre().equalsIgnoreCase(p2.nombre()) && p1.precio() == p2.precio()) {
            return true;
        }
        return false;
    }

    /**
     * RETO EXTRA 6: Normalización de copias DTO.
     * Garantizar que un producto de origen tenga un ID autogenerado válido (si
     * venía nulo o <= 0) en su copia.
     *
     * @param p producto original
     * @return una copia normalizada con ID corregido a 999 si no era válido (> 0)
     */
    public static ProductoDto normalizarId(ProductoDto p) {
        // GUÍA: si el identificador ya es válido, no hay nada que normalizar.
        // Si falta o no sirve, crea una copia con el identificador de reserva
        // indicado por el contrato y conserva el resto de datos.
        if (p.id() != null && p.id() > 0) {
            return p;
        }
        return new ProductoDto(999L, p.nombre(), p.precio());
    }

    /**
     * RETO EXTRA 7: Verificación de importes redondeados.
     * En APIs de facturación, evitamos importes con centavos sucios (ej.
     * 10.3333333).
     *
     * @param p producto
     * @return true si el precio tiene como máximo 2 decimales
     */
    public static boolean esPrecioRedondeado(ProductoDto p) {
        // GUÍA: comprueba si el precio puede representarse con céntimos limpios.
        // Con double tendrás pequeños errores de coma flotante, así que piensa
        // en una comparación tolerante o en una representación decimal exacta.
        // En producción, dinero real suele modelarse con BigDecimal o céntimos enteros.
        return Math.abs(p.precio() * 100 - (Math.round(p.precio() * 100))) < 1e-9;
    }

    /**
     * RETO EXTRA 8: Deserialización a partir de línea de texto plana (CSV).
     *
     * @param csv fila CSV (ej. "42,Teclado Mecanico,89.99")
     * @return ProductoDto parseado
     */
    public static ProductoDto crearDesdeValores(String csv) {
        // GUÍA: trata la cadena como una fila con tres columnas obligatorias.
        // Valida que exista contenido, separa por el delimitador correcto y limpia
        // espacios externos de cada campo. El constructor del record debe seguir
        // siendo quien aplique las reglas finales de validez.
        if (csv == null || csv.isEmpty()) {
            throw new IllegalArgumentException();
        }
        String[] campos = csv.split(",");
        return new ProductoDto(Long.parseLong(campos[0].trim()), campos[1].trim(),
                Double.parseDouble(campos[2].trim()));
    }

    /**
     * RETO EXTRA 9: Formateador de etiqueta de precio estética.
     *
     * @param p producto
     * @return String formateado como "Nombre - $Precio"
     */
    public static String formatoEtiqueta(ProductoDto p) {
        // GUÍA: el resultado es una etiqueta legible para UI o logs, no JSON.
        // Respeta el separador y el símbolo monetario indicados en el contrato.
        // Decide cómo responder ante un producto null antes de acceder a sus campos.
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return String.format("%s - $%.1f", p.nombre(), p.precio());
    }

    /**
     * RETO EXTRA 10: Copia DTO combinando costo logístico (Envío).
     *
     * @param p          producto original
     * @param costoEnvio costo del envío
     * @return ProductoDto copia con el costo de envío sumado al precio del producto
     */
    public static ProductoDto reconstruirConEnvio(ProductoDto p, double costoEnvio) {
        // GUÍA: crea una copia con el coste logístico incorporado al precio.
        // No cambies id ni nombre. Antes de sumar, piensa qué significa un coste
        // de envío negativo y si debe aceptarse como dato válido.

        if (p == null || costoEnvio < 0 || p.precio() < 0) {
            throw new IllegalArgumentException();
        }
        return new ProductoDto(p.id(), p.nombre(), p.precio() + costoEnvio);
    }

}
