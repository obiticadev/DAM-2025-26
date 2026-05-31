package com.masterclass.api.b01_java;

/**
 * Ejercicio 018 · Jerarquía sellada + pattern matching en switch.
 *
 * <p>Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.5).
 *
 * <p>Modela el resultado de una operación de API como una jerarquía cerrada.
 */
public final class Ej018SealedPatternMatching {

    /** Resultado sellado: solo puede ser Ok, NotFound o Fallo. */
    public sealed interface Resultado permits Ok, NotFound, Fallo {
    }

    public record Ok(String valor) implements Resultado {
    }

    public record NotFound(Object id) implements Resultado {
    }

    public record Fallo(String mensaje) implements Resultado {
    }

    // --- Modelos para retos extra ---

    public sealed interface Forma permits Circulo, Rectangulo, Triangulo {
    }

    public record Circulo(double radio) implements Forma {
    }

    public record Rectangulo(double ancho, double alto) implements Forma {
    }

    public record Triangulo(double base, double altura) implements Forma {
    }

    public sealed interface UsuarioRol permits Admin, Gestor, Cliente {
    }

    public record Admin(String nombre, int nivelSeguridad) implements UsuarioRol {
    }

    public record Gestor(String nombre, String departamento) implements UsuarioRol {
    }

    public record Cliente(String nombre, boolean esPremium) implements UsuarioRol {
    }

    public sealed interface RespuestaApi permits ExitoApi, ErrorApi {
    }

    public record ExitoApi(String jsonPayload, int codigo) implements RespuestaApi {
    }

    public record ErrorApi(String mensajeError, int codigo, Throwable causa) implements RespuestaApi {
    }

    private Ej018SealedPatternMatching() {
    }

    /**
     * Traduce un Resultado a un código HTTP usando switch con patrones.
     *
     * @param r resultado de dominio
     * @return 200 para Ok, 404 para NotFound, 500 para Fallo
     */
    public static int aHttpStatus(Resultado r) {
        // TODO 1: usa un switch de PATRONES sobre 'r' (switch expression).
        // TODO 2: case Ok ok -> 200.
        // TODO 3: case NotFound nf -> 404.
        // TODO 4: case Fallo f -> 500.
        // TODO 5: al ser 'sealed' el switch es exhaustivo: NO añadas 'default'.
        return -1;
    }

    /**
     * Devuelve un mensaje legible para el cliente.
     *
     * @param r resultado de dominio
     * @return texto descriptivo según el subtipo
     */
    public static String describir(Resultado r) {
        // TODO 6: switch de patrones devolviendo un String por caso.
        // TODO 7: para Ok, incluye el valor (p.ej. "OK: " + ok.valor()).
        // TODO 8: para NotFound, incluye el id buscado.
        // TODO 9: para Fallo, incluye el mensaje de error.
        // TODO 10: aprovecha el binding del patrón (ok/nf/f) para acceder a los campos.
        return "";
    }

    public static void main(String[] args) {
        System.out.println(aHttpStatus(new Ok("hola")));
        System.out.println(describir(new NotFound(7)));
    }

    /**
     * Reto Extra 1: Reflexión básica de jerarquías selladas.
     * Verifica si la superClase es sellada (isSealed()) y si permite a la subClase como subtipo directo.
     *
     * @param superClase clase o interfaz sospechosa de ser sellada
     * @param subClase   subclase a comprobar
     * @return true si es permitida directamente
     */
    public static boolean esClaseSelladaPermitida(Class<?> superClase, Class<?> subClase) {
        // TODO extra: Reto Extra 1: Reflexión básica de jerarquías selladas.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esClaseSelladaPermitida");
    }

    /**
     * Reto Extra 2: Coincidencia de patrones simple.
     * Clasifica una Forma geométrica según su subtipo devolviendo "CÍRCULO", "RECTÁNGULO" o "TRIÁNGULO".
     *
     * @param forma forma geométrica
     * @return tipo de forma formateado en mayúsculas
     */
    public static String clasificarFormaGeometrica(Forma forma) {
        // TODO extra: Reto Extra 2: Coincidencia de patrones simple.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clasificarFormaGeometrica");
    }

    /**
     * Reto Extra 3: Cálculos geométricos condicionales tipados.
     * Calcula el área de una Forma geométrica utilizando pattern matching.
     *
     * @param forma forma geométrica
     * @return área calculada
     */
    public static double calcularAreaConPatternMatching(Forma forma) {
        // TODO extra: Reto Extra 3: Cálculos geométricos condicionales tipados.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para calcularAreaConPatternMatching");
    }

    /**
     * Reto Extra 4: Pattern matching sobre records de usuarios.
     * Extrae el nombre del usuario a partir de su rol específico.
     *
     * @param rol rol del usuario
     * @return nombre del usuario
     */
    public static String obtenerNombreDeUsuarioPorRol(UsuarioRol rol) {
        // TODO extra: Reto Extra 4: Pattern matching sobre records de usuarios.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerNombreDeUsuarioPorRol");
    }

    /**
     * Reto Extra 5: Detección inteligente de privilegios con guardias.
     * Determina si el rol de usuario tiene privilegios de administrador.
     * Es admin si el rol es Admin y su nivel de seguridad es mayor o igual a 3.
     *
     * @param rol rol del usuario
     * @return true si tiene privilegios de administrador
     */
    public static boolean esRolAdministrador(UsuarioRol rol) {
        // TODO extra: Reto Extra 5: Detección inteligente de privilegios con guardias.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRolAdministrador");
    }

    /**
     * Reto Extra 6: Comprobación de exhaustividad de jerarquías.
     * Verifica si una clase o interfaz está declarada como sellada a nivel de compilador.
     *
     * @param clazz tipo a verificar
     * @return true si es sellado
     */
    public static boolean esJerarquiaSelladaCompleta(Class<?> clazz) {
        // TODO extra: Reto Extra 6: Comprobación de exhaustividad de jerarquías.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esJerarquiaSelladaCompleta");
    }

    /**
     * Reto Extra 7: Formateo semántico de respuestas de API.
     * Convierte una RespuestaApi a un formato de cadena descriptivo.
     *
     * @param res respuesta de API
     * @return representación textual descriptiva
     */
    public static String formatearRespuestaApi(RespuestaApi res) {
        // TODO extra: Reto Extra 7: Formateo semántico de respuestas de API.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearRespuestaApi");
    }

    /**
     * Reto Extra 8: Extracción segura de mensajes de error.
     * Extrae el mensaje de error de una RespuestaApi únicamente si el tipo es ErrorApi.
     *
     * @param res respuesta de API
     * @return Optional con el mensaje de error si aplica, o vacío
     */
    public static java.util.Optional<String> obtenerDetalleError(RespuestaApi res) {
        // TODO extra: Reto Extra 8: Extracción segura de mensajes de error.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerDetalleError");
    }

    /**
     * Reto Extra 9: Pattern matching avanzado con condiciones adicionales (guardias).
     * Evalúa las dimensiones de una Forma geométrica para retornar una clasificación detallada.
     * - Circulo con radio > 10.0 -> "Círculo Grande"
     * - Circulo con radio <= 10.0 -> "Círculo Pequeño"
     * - Rectangulo con ancho == alto -> "Cuadrado"
     * - Rectangulo con ancho != alto -> "Rectángulo Estándar"
     * - Triangulo -> "Triángulo"
     *
     * @param forma forma geométrica
     * @return clasificación detallada
     */
    public static String evaluarEstadoConGuardias(Forma forma) {
        // TODO extra: Reto Extra 9: Pattern matching avanzado con condiciones adicionales (guardias).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para evaluarEstadoConGuardias");
    }

    /**
     * Reto Extra 10: Generación de nuevas formas con escala.
     * Reconstruye una Forma geométrica aplicando un factor multiplicador a sus dimensiones.
     *
     * @param forma  forma original
     * @param factor factor de escala
     * @return nueva instancia de Forma escalada
     */
    public static Forma reconstruirFormaEscalada(Forma forma, double factor) {
        // TODO extra: Reto Extra 10: Generación de nuevas formas con escala.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para reconstruirFormaEscalada");
    }

}
