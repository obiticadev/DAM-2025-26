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
        // GUÍA: teoría 0.9 (convención 1: plural).
        // 1. null o en blanco → false.
        // 2. Solo letras minúsculas: el test rechaza "usuarios123" por los dígitos.
        //    PISTA: resource.matches("[a-záéíóúñ]+").
        // 3. Debe terminar en 's' (heurística simple de plural es/en):
        //    endsWith("s"). "usuario" → false, "usuarios" → true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esSustantivoPluralValido");
    }

    /**
     * RETO EXTRA 2: Pluralización automática de sustantivos en español.
     * En routers automáticos o generadores de código REST, es útil poder pluralizar nombres de entidad singulares.
     *
     * @param singular nombre del recurso en singular (ej. "pedido", "canal", "actividad")
     * @return nombre del recurso en plural (ej. "pedidos", "canales", "actividades")
     */
    public static String pluralizarRecurso(String singular) {
        // GUÍA: reglas del español, de la más específica a la general:
        // 1. null o en blanco → "" (o lanza excepción: decide y documenta).
        // 2. Termina en VOCAL (a,e,i,o,u) → añade "s": "pedido" → "pedidos".
        // 3. Termina en 'd' o 'z' → añade "es" (con z real sería z→ces, pero el
        //    test solo exige "actividad" → "actividades").
        // 4. Termina en otra CONSONANTE → añade "es": "canal" → "canales".
        // PISTA: char ultima = s.charAt(s.length() - 1);
        //        "aeiou".indexOf(ultima) >= 0 detecta vocal.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pluralizarRecurso");
    }

    /**
     * RETO EXTRA 3: Validación de Identificadores REST estándares.
     * Un ID de elemento seguro en REST suele ser un entero positivo o una cadena UUIDv4 válida.
     *
     * @param id identificador a validar (puede ser numérico o String)
     * @return true si el id es un número entero positivo o si es una cadena UUID sintácticamente correcta
     */
    public static boolean esIdentificadorValido(Object id) {
        // GUÍA: dos familias de id válidos — número positivo o UUID.
        // 1. null → false.
        // 2. Si es Number (instanceof): true solo si longValue() > 0
        //    (el test rechaza -5).
        // 3. Si es String: valida formato UUID. Dos opciones:
        //    a) try { UUID.fromString(s); return true; } catch (IllegalArgumentException e) { return false; }
        //    b) regex: "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}"
        // 4. Cualquier otro tipo → false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esIdentificadorValido");
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
        // GUÍA: así agrupan métricas las herramientas de observabilidad (b20):
        // mil rutas "/usuarios/123", "/usuarios/456"... → UNA plantilla.
        // 1. null → "".
        // 2. Divide por '/' y, segmento a segmento: si "parece un id",
        //    sustitúyelo por "{id}"; si no, déjalo igual.
        // 3. "Parece un id" = es numérico (matches("\\d+")) o es un UUID
        //    (reutiliza esIdentificadorValido del reto 3).
        // 4. Reconstruye con String.join("/", segmentos).
        // Test: "/usuarios/123/pedidos/da39..." → "/usuarios/{id}/pedidos/{id}".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearRutaAPlantilla");
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
        // GUÍA: teoría 0.9 (convención 2: no anidar más de 2 niveles).
        // 1. null → false.
        // 2. Cuenta cuántos segmentos son NOMBRES DE RECURSO (no-numéricos,
        //    no-id): "/usuarios/12/pedidos/34" tiene 2 recursos → válida;
        //    "/tiendas/1/estanterias/2/cajas/3/productos" tiene 4 → inválida.
        // 3. true si recursos <= 2.
        // PISTA: reutiliza la detección de ids del reto 4 para distinguir
        // recurso de identificador.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esJerarquiaDeSubrecursoValida");
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
        // GUÍA: teoría 0.10 (HATEOAS): es el "href" de los _links.
        // 1. Reutiliza item(resource, id), que ya valida y construye "/pedidos/42".
        // 2. Antepón contextPath (si es null, trátalo como "").
        // 3. Cuida la doble barra: si contextPath termina en '/', quítala antes
        //    de concatenar ("/api/v1" + "/pedidos/42" → "/api/v1/pedidos/42").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarLinkRel");
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
        // GUÍA: "dame el segmento que va DESPUÉS del recurso X".
        // 1. null en cualquiera de los dos → "".
        // 2. Divide la ruta por '/' (recuerda el primer elemento vacío,
        //    como en Ej007 reto 7).
        // 3. Busca el índice i donde el segmento equals(resource).
        // 4. Si existe Y hay un segmento i+1 → devuélvelo; si no → "".
        // Test: ("/usuarios/123/pedidos", "usuarios") → "123".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerIdDeRuta");
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
        // GUÍA: primer encuentro con varargs (String...).
        // 1. Base: item(resource, id) → "/pedidos/42".
        // 2. Si no llegan pares (array vacío o null) → devuelve solo la base.
        // 3. Recorre el array DE DOS EN DOS (for i += 2): queryKeysAndValues[i]
        //    es la clave, [i+1] el valor → "clave=valor".
        // 4. Une los pares con '&' y antepón '?' a la primera.
        // PISTA: StringJoiner sj = new StringJoiner("&"); ... base + "?" + sj.
        // DEFENSA: si llega un número impar de elementos, decide (ignorar el
        // último o lanzar IllegalArgumentException) y documéntalo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearRutaConQuery");
    }

    /**
     * RETO EXTRA 9: Detectar si una ruta representa una colección completa.
     *
     * @param path URI a inspeccionar (ej. "/pedidos" o "/pedidos/42")
     * @return true si representa la colección completa del recurso, false si hace referencia a un elemento específico
     */
    public static boolean esColeccionRuta(String path) {
        // GUÍA: teoría 0.9 (colección vs elemento).
        // 1. null o en blanco → false.
        // 2. Normaliza: quita la barra FINAL si existe ("/pedidos/" → "/pedidos";
        //    el test exige que ambas formas den true).
        // 3. Cuenta segmentos no vacíos: 1 segmento → colección (true);
        //    2 o más → elemento o subruta (false).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esColeccionRuta");
    }

    /**
     * RETO EXTRA 10: Auditoría sintáctica de inyección de acciones en URLs (Antipatrón RPC).
     * En REST, las acciones se definen mediante métodos HTTP (POST, DELETE...), nunca con verbos en la URL.
     *
     * @param path URI a auditar (ej. "/pedidos/crear" o "/eliminar-usuario")
     * @return el mensaje de error de antipatrón si se detectan palabras como "crear", "eliminar", "actualizar", "get", "post"; o cadena vacía si es correcta
     */
    public static String detectarAntipatronesRuta(String path) {
        // GUÍA: teoría 0.9 (RPC vs REST) y error común nº7.
        // 1. null → "" (nada que auditar).
        // 2. Lista de verbos prohibidos: "crear", "eliminar", "actualizar",
        //    "borrar", "get", "post", "delete", "update".
        // 3. Pasa la ruta a minúsculas; si CONTIENE alguno → devuelve un mensaje
        //    descriptivo, p.ej. "Antipatrón RPC: la ruta contiene el verbo 'crear'".
        // 4. Si está limpia → "".
        // El test solo comprueba vacío/no-vacío: el TEXTO del mensaje es tuyo,
        // pero hazlo útil — tú serás quien lo lea en un log a las 3 AM.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para detectarAntipatronesRuta");
    }

}
