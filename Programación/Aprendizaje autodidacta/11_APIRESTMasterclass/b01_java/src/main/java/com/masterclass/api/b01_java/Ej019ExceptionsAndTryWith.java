package com.masterclass.api.b01_java;

/**
 * Ejercicio 019 · Excepciones y try-with-resources.
 *
 * <p>Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.5).
 */
public final class Ej019ExceptionsAndTryWith {

    private Ej019ExceptionsAndTryWith() {
    }

    /** Excepción de negocio no comprobada. */
    public static class RecursoNoEncontradoException extends RuntimeException {
        public RecursoNoEncontradoException(String msg) {
            super(msg);
        }
    }

    /** Recurso simulado que registra si fue cerrado. */
    public static class Conexion implements AutoCloseable {
        public boolean cerrada = false;
        public String leer() {
            return "datos";
        }
        @Override
        public void close() {
            this.cerrada = true;
        }
    }

    // --- Modelos y Excepciones para retos extra ---

    public static class ErrorNegocioException extends Exception {
        public ErrorNegocioException(String mensaje) {
            super(mensaje);
        }
        public ErrorNegocioException(String mensaje, Throwable causa) {
            super(mensaje, causa);
        }
    }

    public static class ErrorSistemaException extends RuntimeException {
        public ErrorSistemaException(String mensaje) {
            super(mensaje);
        }
        public ErrorSistemaException(String mensaje, Throwable causa) {
            super(mensaje, causa);
        }
    }

    public static class RecursoFragil implements AutoCloseable {
        public boolean cerrado = false;
        private final boolean fallaAlCerrar;

        public RecursoFragil(boolean fallaAlCerrar) {
            this.fallaAlCerrar = fallaAlCerrar;
        }

        public String operar(boolean lanzarError) throws ErrorNegocioException {
            if (lanzarError) {
                throw new ErrorNegocioException("Error al operar");
            }
            return "operacion-exitosa";
        }

        @Override
        public void close() throws Exception {
            this.cerrado = true;
            if (fallaAlCerrar) {
                throw new IllegalStateException("Fallo catastrófico al cerrar");
            }
        }
    }

    /**
     * Busca un recurso por id; lanza si no existe.
     *
     * @param id identificador (válido si es &gt; 0)
     * @return "recurso-{id}"
     * @throws RecursoNoEncontradoException si id &lt;= 0, con mensaje "no existe: {id}"
     */
    public static String buscar(int id) {
        // TODO 1: comprueba la precondición id > 0.
        // TODO 2: si id <= 0, construye el mensaje "no existe: " + id.
        // TODO 3: lanza RecursoNoEncontradoException con ese mensaje.
        // TODO 4: si la precondición se cumple, construye "recurso-" + id.
        // TODO 5: devuelve esa cadena.
        return "";
    }

    /**
     * Usa la conexión dentro de un try-with-resources y devuelve lo leído.
     *
     * @param c conexión a usar y cerrar automáticamente
     * @return el resultado de c.leer()
     */
    public static String usarYCerrar(Conexion c) {
        // TODO 6: abre un try-with-resources declarando el recurso (try (var r = c)).
        // TODO 7: dentro del try, invoca r.leer() y guarda el resultado.
        // TODO 8: el cierre debe ser automático al salir del try (no llames close() a mano).
        // TODO 9: garantiza que se cierra incluso si hubiera excepción (lo hace try-with-resources).
        // TODO 10: devuelve el valor leído.
        return "";
    }

    public static void main(String[] args) {
        System.out.println(buscar(5));
    }

    /**
     * Reto Extra 1: Cierre manual y seguro de recursos.
     * Cierra el recurso de manera segura. Si lanza una excepción en close(),
     * la captura e impide que se propague (silenciosamente o registrando).
     *
     * @param recurso recurso liberable
     */
    public static void cerrarRecursoSeguro(AutoCloseable recurso) {
        // TODO extra: Reto Extra 1: Cierre manual y seguro de recursos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cerrarRecursoSeguro");
    }

    /**
     * Reto Extra 2: Procesamiento clásico con try-with-resources.
     * Usa try-with-resources para garantizar el cierre del RecursoFragil y ejecuta operar().
     *
     * @param recurso         recurso a ser usado y cerrado automáticamente
     * @param lanzarAlOperar indica si operar() debe fallar
     * @return el resultado de la operación
     * @throws ErrorNegocioException si la operación falla
     */
    public static String procesarConTryWithResources(RecursoFragil recurso, boolean lanzarAlOperar) throws ErrorNegocioException {
        // TODO extra: Reto Extra 2: Procesamiento clásico con try-with-resources.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para procesarConTryWithResources");
    }

    /**
     * Reto Extra 3: Propagación de excepciones con encadenamiento (exception chaining).
     * Lanza una nueva ErrorSistemaException que encapsule y mantenga la causa original.
     *
     * @param mensaje mensaje explicativo personalizado
     * @param causa   excepción original causante
     */
    public static void lanzarConCausaOriginal(String mensaje, Throwable causa) {
        // TODO extra: Reto Extra 3: Propagación de excepciones con encadenamiento (exception chaining).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para lanzarConCausaOriginal");
    }

    /**
     * Reto Extra 4: Modos silenciosos para procesos secundarios.
     * Ejecuta una tarea Runnable. Si ésta lanza cualquier excepción, la captura y la ignora.
     *
     * @param accion tarea a ejecutar
     */
    public static void ejecutarAccionIgnorandoExcepcion(Runnable accion) {
        // TODO extra: Reto Extra 4: Modos silenciosos para procesos secundarios.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ejecutarAccionIgnorandoExcepcion");
    }

    /**
     * Reto Extra 5: Análisis de excepciones suprimidas.
     * Ejecuta operar(true) sobre un RecursoFragil configurado para fallar al cerrar dentro de un try-with-resources.
     * Captura la excepción de negocio lanzada, extrae la excepción suprimida (el fallo al cerrar) y retorna su mensaje.
     * Si no hay excepción suprimida, retorna una cadena vacía.
     *
     * @param recurso recurso frágil que falla al cerrar
     * @return mensaje de la excepción suprimida, o vacío si no hay ninguna
     */
    public static String detectarExcepcionSuprimida(RecursoFragil recurso) {
        // TODO extra: Reto Extra 5: Análisis de excepciones suprimidas.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para detectarExcepcionSuprimida");
    }

    /**
     * Reto Extra 6: Detección recursiva de excepciones de negocio.
     * Determina si la excepción provista es, o tiene en su cadena de causas, una ErrorNegocioException.
     *
     * @param t excepción a inspeccionar
     * @return true si es o contiene un error de negocio
     */
    public static boolean esExcepcionDeNegocio(Throwable t) {
        // TODO extra: Reto Extra 6: Detección recursiva de excepciones de negocio.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionDeNegocio");
    }

    /**
     * Reto Extra 7: Formateador de excepciones amigable para APIs.
     * Inspecciona la excepción y retorna el mensaje de la causa raíz formateado como "Error: [mensaje]".
     * Si no hay mensaje en la causa raíz, usa el nombre de su clase.
     *
     * @param t excepción original
     * @return mensaje formateado amigable
     */
    public static String obtenerMensajeDeErrorFormateado(Throwable t) {
        // TODO extra: Reto Extra 7: Formateador de excepciones amigable para APIs.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerMensajeDeErrorFormateado");
    }

    /**
     * Reto Extra 8: Algoritmo de tolerancia a fallos con reintentos.
     * Ejecuta una acción que devuelve un String. Si falla, realiza reintentos sucesivos hasta maxReintentos.
     * Si todos los reintentos fallan, lanza la última excepción capturada envuelta en un RuntimeException.
     *
     * @param accion        acción a ejecutar
     * @param maxReintentos número máximo de intentos adicionales
     * @return el resultado de la acción
     */
    public static String ejecutarConReintentos(java.util.concurrent.Callable<String> accion, int maxReintentos) {
        // TODO extra: Reto Extra 8: Algoritmo de tolerancia a fallos con reintentos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ejecutarConReintentos");
    }

    /**
     * Reto Extra 9: Inspección de tipo en la causa raíz.
     * Verifica si la causa raíz de una excepción es del tipo de clase especificado.
     *
     * @param tipoEx tipo de excepción esperado en la raíz
     * @param ex     excepción bajo análisis
     * @return true si la causa raíz coincide con el tipo especificado
     */
    public static boolean esCausaRaiz(Class<? extends Throwable> tipoEx, Throwable ex) {
        // TODO extra: Reto Extra 9: Inspección de tipo en la causa raíz.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCausaRaiz");
    }

    /**
     * Reto Extra 10: Try-with-resources con múltiples recursos dependientes.
     * Abre ambos recursos en una única instrucción try-with-resources y retorna la concatenación de sus operaciones exitosas.
     *
     * @param r1 primer recurso
     * @param r2 segundo recurso
     * @return r1.operar() + "+" + r2.operar()
     * @throws Exception si ocurre algún error
     */
    public static String procesarRecursosMultiples(RecursoFragil r1, RecursoFragil r2) throws Exception {
        // TODO extra: Reto Extra 10: Try-with-resources con múltiples recursos dependientes.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para procesarRecursosMultiples");
    }

}
