package com.masterclass.api.b00_http;

/**
 * Ejercicio 008 · Modelado de recursos REST.
 *
 * <p>Teoría: {@code teoria/00_Fundamentos_HTTP_Web.md} (sección 0.3).
 *
 * <p>Las rutas REST usan sustantivos en plural y jerarquía padre/hijo. Aquí
 * construyes rutas canónicas a partir de nombres de recurso e identificadores.
 */
public final class Ej008RestResourceModeler {

    private Ej008RestResourceModeler() {
    }

    /**
     * Ruta de colección: {@code /recursos}.
     *
     * @param resource nombre del recurso en plural (p.ej. "pedidos")
     * @return {@code "/pedidos"}
     * @throws IllegalArgumentException si resource es null o vacío
     */
    public static String collection(String resource) {
        // TODO 1: si resource es null, lanza IllegalArgumentException.
        // TODO 2: si resource está en blanco (vacío o solo espacios), lanza IllegalArgumentException.
        // TODO 3: recorta espacios sobrantes de 'resource'.
        // TODO 4: devuelve "/" + resource.
        return "";
    }

    /**
     * Ruta de elemento: {@code /recursos/{id}}.
     *
     * @param resource nombre del recurso en plural
     * @param id       identificador del elemento
     * @return {@code "/pedidos/42"}
     */
    public static String item(String resource, Object id) {
        // TODO 5: si id es null, lanza IllegalArgumentException (un elemento necesita id).
        // TODO 6: reutiliza collection(resource) para no duplicar validación/formato.
        // TODO 7: concatena "/" + id (usa String.valueOf para Object).
        return "";
    }

    /**
     * Ruta de subrecurso anidado: {@code /padres/{id}/hijos}.
     *
     * @param parent   recurso padre en plural
     * @param parentId id del padre
     * @param child    recurso hijo en plural
     * @return {@code "/pedidos/42/lineas"}
     */
    public static String nested(String parent, Object parentId, String child) {
        // TODO 8: valida 'child' igual que un recurso (null/blank -> IllegalArgumentException).
        // TODO 9: reutiliza item(parent, parentId) para construir el prefijo del padre.
        // TODO 10: añade "/" + child recortado y devuelve la ruta completa.
        return "";
    }

    public static void main(String[] args) {
        System.out.println(collection("pedidos"));
        System.out.println(item("pedidos", 42));
        System.out.println(nested("pedidos", 42, "lineas"));
    }

    /**
     * RETO EXTRA 1: Validación de sustantivo en plural para recursos REST.
     * Es una buena práctica en REST usar sustantivos en plural para las colecciones (ej. "productos" y no "producto").
     *
     * @param resource nombre del recurso a validar
     * @return true si cumple con el plural básico en español/inglés (ej. termina en 's' y no tiene caracteres inválidos)
     */
    public static boolean esSustantivoPluralValido(String resource) {
        // TODO extra 1: valida que el recurso no esté en blanco, que no contenga caracteres especiales o números,
        // y que termine en la letra 's' (insensible a mayúsculas).
        return false;
    }

    /**
     * RETO EXTRA 2: Pluralización automática de sustantivos en español.
     * En routers automáticos o generadores de código REST, es útil poder pluralizar nombres de entidad singulares.
     *
     * @param singular nombre del recurso en singular (ej. "pedido", "canal", "actividad")
     * @return nombre del recurso en plural (ej. "pedidos", "canales", "actividades")
     */
    public static String pluralizarRecurso(String singular) {
        // TODO extra 2: implementa reglas básicas de pluralización en español:
        // - Si termina en vocal (a, e, i, o, u), añade "s".
        // - Si termina en "d" o "l" o "r" o "n", añade "es".
        // - Si termina en "z", cámbiala por "ces".
        // De lo contrario o si es nulo/vacío, devuélvelo tal cual o vacío.
        return "";
    }

    /**
     * RETO EXTRA 3: Validación de Identificadores REST estándares.
     * Un ID de elemento seguro en REST suele ser un entero positivo o una cadena UUIDv4 válida.
     *
     * @param id identificador a validar (puede ser numérico o String)
     * @return true si el id es un número entero positivo o si es una cadena UUID sintácticamente correcta
     */
    public static boolean esIdentificadorValido(Object id) {
        // TODO extra 3: detecta si es un número entero/largo > 0, o una cadena que encaje con el patrón regex de UUIDv4.
        return false;
    }

    /**
     * RETO EXTRA 4: Conversión de Ruta Real a Ruta Plantilla (Path Template).
     * Para documentar APIs (OpenAPI/Swagger) o en routers, se necesita transformar "/usuarios/123/pedidos/abc"
     * en una plantilla "/usuarios/{id}/pedidos/{id}".
     *
     * @param urlCompleta ruta URI real (ej. "/usuarios/123/pedidos/9f8c1b2c")
     * @return la plantilla de ruta con marcadores genéricos de identificador
     */
    public static String mapearRutaAPlantilla(String urlCompleta) {
        // TODO extra 4: analiza los segmentos de la ruta; si un segmento se detecta como identificador 
        // (numérico o UUID), sustitúyelo por "{id}". Une los segmentos de vuelta.
        return "";
    }

    /**
     * RETO EXTRA 5: Validación sintáctica de jerarquía de subrecursos (Antipatrón de profundidad).
     * Evitar rutas excesivamente anidadas (profundidad > 2) como "/tiendas/1/estanterias/2/cajas/3/productos",
     * lo cual dificulta el mantenimiento y la escalabilidad.
     *
     * @param path la ruta completa propuesta
     * @return true si la ruta es una jerarquía bien diseñada (máximo un nivel de anidamiento de subrecurso)
     */
    public static boolean esJerarquiaDeSubrecursoValida(String path) {
        // TODO extra 5: cuenta el número de niveles de recursos individuales en la ruta. 
        // Si hay más de dos recursos anidados (por ejemplo, 3 colecciones separadas), devuelve false.
        return false;
    }

    /**
     * RETO EXTRA 6: Generar URI canónica para HATEOAS.
     * HATEOAS requiere devolver URIs absolutas o relativas al contexto en las respuestas.
     *
     * @param contextPath base de la API (ej. "/api/v1")
     * @param resource nombre del recurso pluralizado (ej. "pedidos")
     * @param id identificador del recurso
     * @return la URI canónica completa formada
     */
    public static String generarLinkRel(String contextPath, String resource, Object id) {
        // TODO extra 6: combina el contextPath normalizado, el recurso y el ID para generar una URI rel
        // limpia sin duplicar barras oblicuas.
        return "";
    }

    /**
     * RETO EXTRA 7: Extraer identificador de ruta por nombre de recurso.
     * A veces, en middlewares o filtros REST, necesitamos extraer el ID de un recurso específico 
     * en una ruta compleja (ej. extraer "123" de "/usuarios/123/pedidos" sabiendo que el recurso es "usuarios").
     *
     * @param url URI de entrada
     * @param resource nombre del recurso cuya clave ID queremos extraer
     * @return el ID en formato String; o vacío si no se encuentra el recurso o no tiene ID a continuación
     */
    public static String extraerIdDeRuta(String url, String resource) {
        // TODO extra 7: localiza el segmento del recurso especificado, y si hay un segmento siguiente,
        // que no sea otra palabra de recurso reservada, devuélvelo como el identificador asociado.
        return "";
    }

    /**
     * RETO EXTRA 8: Modelado de ruta con Query String de filtrado o expansión.
     * Modelar URLs con modificadores clásicos de REST (?embed=cliente o ?sort=fecha).
     *
     * @param resource nombre del recurso
     * @param id identificador
     * @param queryKeysAndValues pares clave y valor alternos para la consulta
     * @return la URL completa formateada, ej. "/pedidos/42?embed=cliente&sort=fecha"
     */
    public static String formatearRutaConQuery(String resource, Object id, String... queryKeysAndValues) {
        // TODO extra 8: construye la ruta del elemento básico y concatena de forma limpia los parámetros 
        // de consulta pasados como argumentos clave-valor alternados. Lanza excepción si la longitud no es par.
        return "";
    }

    /**
     * RETO EXTRA 9: Detectar si una ruta representa una colección completa.
     *
     * @param path URI a inspeccionar (ej. "/pedidos" o "/pedidos/42")
     * @return true si representa la colección completa del recurso, false si hace referencia a un elemento específico
     */
    public static boolean esColeccionRuta(String path) {
        // TODO extra 9: comprueba si la ruta termina con el recurso en plural sin un ID posterior.
        return false;
    }

    /**
     * RETO EXTRA 10: Auditoría sintáctica de inyección de acciones en URLs (Antipatrón RPC).
     * En REST, las acciones se definen mediante métodos HTTP (POST, DELETE...), nunca con verbos en la URL.
     *
     * @param path URI a auditar (ej. "/pedidos/crear" o "/eliminar-usuario")
     * @return el mensaje de error de antipatrón si se detectan palabras como "crear", "eliminar", "actualizar", "get", "post"; o cadena vacía si es correcta
     */
    public static String detectarAntipatronesRuta(String path) {
        // TODO extra 10: analiza si la ruta contiene verbos de acción típicos de RPC y devuelve un mensaje
        // instructivo sugiriendo corregirlo a un diseño REST estándar si los encuentra.
        return "";
    }

}
