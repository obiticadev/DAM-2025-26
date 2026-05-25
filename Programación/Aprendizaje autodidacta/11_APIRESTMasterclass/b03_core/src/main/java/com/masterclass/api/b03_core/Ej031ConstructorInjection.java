package com.masterclass.api.b03_core;

/**
 * Ejercicio 031 · Inyección por constructor.
 *
 * <p>Teoría: {@code teoria/03_Spring_Core_IoC_DI.md} (sección 3.2).
 *
 * <p>El servicio NO debe crear su repositorio con {@code new}: lo recibe ya
 * construido. Eso es DI, y es lo que hace testeable el código.
 */
public final class Ej031ConstructorInjection {

    /** Dependencia: fuente de datos simulada. */
    public interface RepoSaludos {
        String plantilla();
    }

    /** Servicio que depende de RepoSaludos por constructor. */
    public static class ServicioSaludo {
        private final RepoSaludos repo;

        /**
         * @param repo dependencia inyectada (no se instancia aquí)
         */
        public ServicioSaludo(RepoSaludos repo) {
            // TODO 1: NO hagas 'new' de ninguna implementación de RepoSaludos aquí.
            // TODO 2: valida que 'repo' no sea null (fail-fast de dependencia).
            // TODO 3: asigna la dependencia recibida al campo final 'this.repo'.
            this.repo = null;
        }

        /**
         * @param nombre nombre a saludar
         * @return la plantilla del repo con el nombre sustituido donde ponga "{}"
         */
        public String saludar(String nombre) {
            // TODO 4: obtén la plantilla llamando a repo.plantilla().
            // TODO 5: localiza el marcador "{}" dentro de la plantilla.
            // TODO 6: reemplaza ese marcador por 'nombre'.
            // TODO 7: si la plantilla no contiene "{}", devuélvela tal cual (caso límite).
            // TODO 8: si 'nombre' es null, trátalo como cadena vacía (defensa).
            // TODO 9: usa replace (no concatenación frágil) para la sustitución.
            // TODO 10: devuelve el saludo final ya construido.
            return "";
        }
    }

    private Ej031ConstructorInjection() {
    }

    public static void main(String[] args) {
        var s = new ServicioSaludo(() -> "Hola, {}!");
        System.out.println(s.saludar("Ana"));
    }

    // --- MÉTODOS Y CLASES DE RETOS EXTRA ---

    /**
     * Reto Extra 1: Servicio que inyecta múltiples repositorios de saludos.
     * Concatena la plantilla de todos ellos separados por una coma y un espacio.
     */
    public static class ServicioConMultiplesRepos {
        // TODO extra: Reto Extra 1: Servicio que inyecta múltiples repositorios de saludos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 2: Servicio con inyección de dependencia opcional.
     * Si no se inyecta ningún repo, utiliza un fallback predeterminado que retorne "Default".
     */
    public static class ServicioConDependenciaOpcional {
        // TODO extra: Reto Extra 2: Servicio con inyección de dependencia opcional.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 3: Servicio que inyecta dependencias de negocio junto a configuraciones constantes.
     * Si el saludo excede el límite de longitud indicado, lo recorta y añade "...".
     */
    public static class ServicioConConfiguracion {
        // TODO extra: Reto Extra 3: Servicio que inyecta dependencias de negocio junto a configuraciones constantes.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 4: Detecta si existe una dependencia circular directa por constructor
     * entre dos clases simulando de forma reflectiva lo que hace Spring.
     */
    public static boolean detectarInyeccionCircular(Class<?> claseA, Class<?> claseB) {
        // TODO extra: Reto Extra 4: Detecta si existe una dependencia circular directa por constructor
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para detectarInyeccionCircular");
    }

    /**
     * Reto Extra 5: Servicio compuesto que delega lógicas de negocio en múltiples servicios de nivel inferior.
     */
    public static class ServicioComposicion {
        // TODO extra: Reto Extra 5: Servicio compuesto que delega lógicas de negocio en múltiples servicios de nivel inferior.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 6: Simulación pedagógica de inicialización perezosa de dependencias utilizando patrones de diseño proxy o lazy suppliers.
     */
    public static class ServicioLazyProxy {
        // TODO extra: Reto Extra 6: Simulación pedagógica de inicialización perezosa de dependencias utilizando patrones de diseño proxy o lazy suppliers.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 7: Servicio decorador que añade un prefijo/sufijo a los resultados de otro RepoSaludos sin mutar su lógica interna.
     */
    public static class ServicioDecorador implements RepoSaludos {
        // TODO extra: Reto Extra 7: Servicio decorador que añade un prefijo/sufijo a los resultados de otro RepoSaludos sin mutar su lógica interna.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 8: Inyecta una factoría de dependencias para delegar la creación de repositorios en caliente basados en una clave string.
     */
    public static class ServicioConFactory {
        // TODO extra: Reto Extra 8: Inyecta una factoría de dependencias para delegar la creación de repositorios en caliente basados en una clave string.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 9: Comprobación de seguridad reflectiva. Valida si la clase tiene
     * todos sus campos inyectados marcados como final (inmutabilidad).
     */
    public static boolean verificarInyeccionSegura(Class<?> clase) {
        // TODO extra: Reto Extra 9: Comprobación de seguridad reflectiva. Valida si la clase tiene
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para verificarInyeccionSegura");
    }

    /**
     * Reto Extra 10: Inyecta un listado de repositorios y los filtra por un prefijo para devolver el saludo del repositorio coincidente.
     */
    public static class ServicioConFiltro {
        // TODO extra: Reto Extra 10: Inyecta un listado de repositorios y los filtra por un prefijo para devolver el saludo del repositorio coincidente.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

}
