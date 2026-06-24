package com.masterclass.api.b43_erp;

import java.util.List;
import java.util.Map;

/**
 * Ejercicio 334 · ETL: mapear, transformar y validar datos entre sistemas.
 *
 * <p>Teoría: {@code teoria/43_SGE_Integracion.md} (sección 4).
 *
 * <p>ETL = <b>E</b>xtraer (ya hecho: CSV de Ej332, API de Ej333), <b>T</b>ransformar y
 * <b>C</b>argar. La transformación es el corazón de la integración: los datos del sistema A
 * casi nunca encajan tal cual en el sistema B. Hay que limpiar (trim, normalizar mayúsculas),
 * dar valores por defecto, convertir formatos (de fecha, de teléfono) y <b>validar</b> antes de
 * cargar, porque un ERP rechaza un maestro mal formado. Todo esto es lógica pura y testeable.
 */
public final class Ej334DataMappingEtl {

    private Ej334DataMappingEtl() {
    }

    /**
     * Mapea una fila cruda (mapa columna→valor) a un {@link ClienteErp} normalizado.
     *
     * @param fila mapa con claves "idExterno", "nombre", "email", "pais" (algunas pueden faltar)
     * @return cliente con valores limpios: ids/país en mayúsculas, textos sin espacios sobrantes,
     *         país por defecto "ES" si falta
     * @throws IllegalArgumentException si falta el idExterno (sin él no se puede integrar)
     */
    public static ClienteErp mapearCliente(Map<String, String> fila) {
        // TODO 1: si fila es null -> IllegalArgumentException.
        // TODO 2: lee "idExterno"; si es null/blank -> IllegalArgumentException (clave obligatoria).
        // TODO 3: normaliza el idExterno: trim + toUpperCase.
        // TODO 4: lee "nombre" y aplícale trim (si falta, cadena vacía).
        // TODO 5: lee "email" y aplícale trim + toLowerCase (si falta, cadena vacía).
        // TODO 6: lee "pais"; si falta o está en blanco -> "ES" por defecto.
        // TODO 7: normaliza el pais a trim + toUpperCase.
        // TODO 8: construye y devuelve el ClienteErp(idExterno, nombre, email, pais).
        return null;
    }

    /**
     * Valida un maestro de cliente y devuelve la lista de errores (vacía = válido).
     *
     * @param c cliente a validar (puede ser null)
     * @return lista de mensajes de error; vacía si el cliente es válido
     */
    public static List<String> validarMaestro(ClienteErp c) {
        // TODO 1: si c es null -> devuelve List.of("cliente nulo") (un único error).
        // TODO 2: crea una lista mutable de errores.
        // TODO 3: si idExterno es null/blank -> añade "idExterno obligatorio".
        // TODO 4: si nombre es null/blank -> añade "nombre obligatorio".
        // TODO 5: si email no está vacío y no contiene '@' -> añade "email invalido".
        // TODO 6: si pais es null o su longitud != 2 -> añade "pais debe ser ISO-2".
        // TODO 7: devuelve la lista (vacía si todo correcto).
        return List.of();
    }

    public static void main(String[] args) {
        ClienteErp c = mapearCliente(Map.of("idExterno", " cli-1 ", "nombre", " Acme ", "email", "INFO@ACME.ES"));
        System.out.println(c);
        System.out.println(validarMaestro(c));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Normaliza un texto: sin espacios alrededor y sin espacios internos repetidos.
     */
    public static String normalizarTexto(String texto) {
        // GUÍA: teoría 4 (limpieza básica de strings en un ETL).
        // 1. null -> "".
        // 2. trim y colapsa cualquier secuencia de espacios en blanco a uno solo.
        // PISTA: return texto.trim().replaceAll("\\s+", " ");
        // OJO: el test pasa "  a   b " -> "a b". El \\s+ caza tabs y espacios múltiples.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarTexto");
    }

    /**
     * Reto Extra 2: Normaliza un nombre de país a su código ISO de 2 letras.
     */
    public static String normalizarCodigoPais(String pais) {
        // GUÍA: teoría 4 (homogeneizar maestros: cada sistema escribe el país a su manera).
        // 1. null/blank -> "".
        // 2. pasa a minúsculas y trim; "españa"/"spain" -> "ES"; "francia"/"france" -> "FR".
        // 3. cualquier otro -> el valor en mayúsculas (ya podría venir como "DE").
        // PISTA: switch sobre pais.trim().toLowerCase() con default pais.trim().toUpperCase().
        // OJO: el test pasa "España" -> "ES" y "de" -> "DE". Normaliza ANTES de comparar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarCodigoPais");
    }

    /**
     * Reto Extra 3: Devuelve un valor o, si está vacío, un valor por defecto.
     */
    public static String valorODefecto(String valor, String porDefecto) {
        // GUÍA: teoría 4 (los huecos del origen se rellenan con defaults en la transformación).
        // PISTA: return (valor == null || valor.isBlank()) ? porDefecto : valor.trim();
        // OJO: el test pasa ("", "ES") -> "ES" y (" X ", "ES") -> "X" (devuelve recortado).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para valorODefecto");
    }

    /**
     * Reto Extra 4: Normaliza un teléfono dejando solo el '+' inicial y los dígitos.
     */
    public static String normalizarTelefono(String telefono) {
        // GUÍA: teoría 4 (los teléfonos vienen con espacios, guiones y paréntesis dispares).
        // 1. null -> "".
        // 2. conserva un '+' SOLO si es el primer carácter; elimina todo lo que no sea dígito.
        // PISTA: boolean mas = telefono.trim().startsWith("+");
        //        String soloDigitos = telefono.replaceAll("[^0-9]", "");
        //        return mas ? "+" + soloDigitos : soloDigitos;
        // OJO: el test pasa "+34 600-123 456" -> "+34600123456". Un '+' en medio NO se conserva.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarTelefono");
    }

    /**
     * Reto Extra 5: Mapea un estado textual a su código de una letra.
     */
    public static String mapearEstado(String estado) {
        // GUÍA: teoría 4 (mapeo de valores de catálogo entre sistemas).
        // 1. "activo" -> "A"; "inactivo" -> "I"; cualquier otro (o null) -> "?".
        // PISTA: switch sobre estado normalizado a minúsculas; default "?".
        // OJO: el test pasa "Activo" (con mayúscula) -> "A". Normaliza primero.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearEstado");
    }

    /**
     * Reto Extra 6: Convierte una fecha {@code dd/MM/yyyy} a formato ISO {@code yyyy-MM-dd}.
     */
    public static String convertirFechaIso(String fecha) {
        // GUÍA: teoría 4 (reformatear fechas es el clásico de todo ETL).
        // 1. null -> "".
        // 2. parte por '/' esperando 3 trozos (día, mes, año); si no, IllegalArgumentException.
        // 3. recompón como año + "-" + mes + "-" + día.
        // PISTA: String[] p = fecha.split("/"); ... return p[2] + "-" + p[1] + "-" + p[0];
        // OJO: el test pasa "07/03/2026" -> "2026-03-07". Conserva los ceros a la izquierda
        //   (NO conviertas a int, romperías "07" -> "7"). Trabaja con las cadenas tal cual.
        // CULTURA: convertir entre formatos de fecha es lo que hiciste con java.time en b01;
        //   aquí basta el reordenado textual porque ya vienen con dos dígitos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para convertirFechaIso");
    }

    /**
     * Reto Extra 7: Trunca un texto a un máximo de caracteres (para campos con límite del ERP).
     */
    public static String truncar(String texto, int max) {
        // GUÍA: teoría 4 (muchos campos del ERP tienen longitud máxima; hay que recortar).
        // 1. null -> "".
        // 2. si la longitud <= max, devuélvelo igual; si no, los primeros 'max' caracteres.
        // PISTA: return texto.length() <= max ? texto : texto.substring(0, max);
        // OJO: el test pasa ("abcdef", 3) -> "abc" y ("ab", 5) -> "ab" (no rellena).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para truncar");
    }

    /**
     * Reto Extra 8: Indica si una fila trae todas las columnas requeridas (no nulas ni en blanco).
     */
    public static boolean esFilaCompleta(Map<String, String> fila, List<String> requeridas) {
        // GUÍA: teoría 4 (rechazar filas incompletas antes de cargar).
        // 1. fila o requeridas null -> false.
        // 2. para cada columna requerida: debe existir y su valor no estar en blanco.
        // PISTA: for (String col : requeridas) { String v = fila.get(col); if (v==null||v.isBlank()) return false; } return true;
        // OJO: el test pide que falte "email" -> false aunque el resto esté. Comprueba TODAS.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFilaCompleta");
    }

    /**
     * Reto Extra 9: Cuenta cuántos clientes de una lista son válidos.
     */
    public static int contarValidos(List<ClienteErp> clientes) {
        // GUÍA: reutiliza validarMaestro (un cliente es válido si su lista de errores está vacía).
        // 1. null -> 0.
        // 2. cuenta los que cumplen validarMaestro(c).isEmpty().
        // PISTA: (int) clientes.stream().filter(c -> validarMaestro(c).isEmpty()).count();
        // OJO: el test mezcla válidos e inválidos; apóyate en tu propio validarMaestro,
        //   no reimplantes las reglas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarValidos");
    }

    /**
     * Reto Extra 10: Ejecuta el ETL completo sobre una lista de filas, descartando las inmapeables.
     */
    public static List<ClienteErp> mapearLista(List<Map<String, String>> filas) {
        // GUÍA: teoría 4 (la tubería ETL: transformar cada fila, saltarse las irrecuperables).
        // 1. null -> List.of().
        // 2. para cada fila: intenta mapearCliente; si lanza IllegalArgumentException (sin id),
        //    sáltala (continue) en vez de abortar todo el lote.
        // PISTA: for (Map<String,String> f : filas) { try { res.add(mapearCliente(f)); } catch (IllegalArgumentException e) { } }
        // OJO: el test mete 2 filas válidas y 1 sin idExterno -> resultado de tamaño 2.
        //   Un lote NO debe caerse entero por una fila mala: ése es el "registro rechazado"
        //   que en un ETL real iría a una cola de errores (dead-letter).
        // CULTURA: tolerancia a fallos por elemento — la misma idea que la degradación
        //   controlada de b09 y los reintentos por mensaje de b27/b29.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearLista");
    }
}
