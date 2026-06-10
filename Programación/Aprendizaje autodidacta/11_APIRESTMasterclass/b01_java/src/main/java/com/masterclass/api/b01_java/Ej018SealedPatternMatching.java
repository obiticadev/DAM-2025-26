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
        // GUÍA: el "sealed" existe también en runtime, vía reflexión:
        // 1. null en cualquiera → false.
        // 2. Si !superClase.isSealed() → false.
        // 3. superClase.getPermittedSubclasses() devuelve Class<?>[]: recorre
        //    y comprueba si alguno equals(subClase).
        // PISTA: Arrays.asList(superClase.getPermittedSubclasses()).contains(subClase)
        // Tests: (Forma, Circulo) ✔; (Forma, String) ✘.
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
        // GUÍA: el mismo switch de patrones que aHttpStatus, sobre Forma:
        // return switch (forma) {
        //     case Circulo c    -> "CÍRCULO";
        //     case Rectangulo r -> "RECTÁNGULO";
        //     case Triangulo t  -> "TRIÁNGULO";
        // };
        // SIN default — Forma es sealed con 3 permits, el switch es exhaustivo.
        // Cuidado con las tildes: los tests las exigen exactas.
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
        // GUÍA: usa RECORD PATTERNS — destructura dentro del case (teoría 1.8):
        // return switch (forma) {
        //     case Circulo(double radio)                 -> Math.PI * radio * radio;
        //     case Rectangulo(double ancho, double alto) -> ancho * alto;
        //     case Triangulo(double base, double altura) -> base * altura / 2;
        // };
        // Los componentes saltan directos a variables, sin c.radio(). Compara
        // con la versión "case Circulo c -> ... c.radio()": ambas valen;
        // el record pattern brilla cuando hay varios campos.
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
        // GUÍA: los tres roles tienen "nombre", pero la interfaz UsuarioRol NO
        // lo declara → no puedes llamar rol.nombre() directamente. El switch
        // de patrones resuelve el acceso por subtipo:
        //     case Admin a   -> a.nombre();
        //     case Gestor g  -> g.nombre();
        //     case Cliente c -> c.nombre();
        // REFLEXIÓN DE DISEÑO: ¿habría sido mejor declarar String nombre() en
        // la interfaz sellada? Sí, probablemente — este reto te hace sentir el
        // costo de no hacerlo.
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
        // GUÍA: tu primera GUARDA (when) en un switch:
        // return switch (rol) {
        //     case Admin a when a.nivelSeguridad() >= 3 -> true;
        //     default -> false;
        // };
        // El "when" añade una condición AL PATRÓN: solo entra si es Admin Y
        // nivel >= 3. Un Admin de nivel 2 cae al default. (Aquí sí hay default
        // porque no cubres todos los subtipos con casos propios.)
        // ALTERNATIVA con instanceof: rol instanceof Admin a && a.nivelSeguridad() >= 3.
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
        // GUÍA: una línea — return clazz != null && clazz.isSealed();
        // (String.class da false: String es final, no sealed — parecidos
        // pero no iguales: final = sin subclases; sealed = subclases en
        // lista cerrada.)
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
        // GUÍA: switch de patrones; formato EXACTO de los tests
        // ("Código 200: {payload}" / "Código 400: {mensaje}"):
        //     case ExitoApi e -> "Código " + e.codigo() + ": " + e.jsonPayload();
        //     case ErrorApi err -> "Código " + err.codigo() + ": " + err.mensajeError();
        // Sin default: RespuestaApi es sealed con exactamente esos dos permits.
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
        // GUÍA: pattern matching + Optional, dos secciones en una:
        // if (res instanceof ErrorApi err) {
        //     return Optional.of(err.mensajeError());
        // }
        // return Optional.empty();
        // El instanceof con binding comprueba el tipo Y te da la variable
        // casteada en un paso (teoría 1.8). Y el retorno Optional grita en la
        // firma "puede no haber error" (teoría 1.2).
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
        // GUÍA: combina record patterns + guardas. El Javadoc ya es la spec:
        // return switch (forma) {
        //     case Circulo(double r) when r > 10.0       -> "Círculo Grande";
        //     case Circulo c                             -> "Círculo Pequeño";
        //     case Rectangulo(double w, double h) when w == h -> "Cuadrado";
        //     case Rectangulo r                          -> "Rectángulo Estándar";
        //     case Triangulo t                           -> "Triángulo";
        // };
        // EL ORDEN ES LEY: el caso CON guarda va ANTES que el caso general del
        // mismo tipo; si los inviertes, el general "se traga" todo y el
        // compilador marca el guardado como inalcanzable.
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
        // GUÍA: el broche: switch que DESTRUYE y RECONSTRUYE (records inmutables):
        // return switch (forma) {
        //     case Circulo(double r)              -> new Circulo(r * factor);
        //     case Rectangulo(double w, double h) -> new Rectangulo(w * factor, h * factor);
        //     case Triangulo(double b, double a)  -> new Triangulo(b * factor, a * factor);
        // };
        // Patrón funcional puro: entrada inmutable → nueva instancia
        // transformada. Es el "wither" de Ej011 generalizado a una jerarquía.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para reconstruirFormaEscalada");
    }

}
