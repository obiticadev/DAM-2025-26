package com.masterclass.api.b05_web;

/**
 * Ejercicio 047 · @RequestParam con valor por defecto.
 *
 * <p>Teoría: {@code teoria/05_Controllers_REST.md} (sección 5.2).
 *
 * <p>El test espera {@code GET /api/sum?a=2&b=3} -> "5" y {@code GET /api/sum?a=2} -> "2"
 * (b por defecto 0).
 */
// TODO 1: anota la clase con @RestController.
// TODO 2: anota la clase con @RequestMapping("/api").
public class Ej047QueryParams {

    /**
     * Suma dos enteros recibidos por query string.
     *
     * @param a primer sumando (obligatorio)
     * @param b segundo sumando (opcional, por defecto 0)
     * @return la suma como texto
     */
    // TODO 3: anota el método con @GetMapping("/sum").
    // TODO 4: anota 'a' con @RequestParam (obligatorio por defecto).
    // TODO 5: anota 'b' con @RequestParam(defaultValue = "0") (opcional).
    public String sum(int a, int b) {
        // TODO 6: Spring convierte los query params a int automáticamente.
        // TODO 7: si falta 'a' Spring responde 400 (no lo gestionas tú aquí).
        // TODO 8: calcula a + b.
        // TODO 9: el cuerpo debe ser el número como String (usa String.valueOf).
        // TODO 10: devuelve esa cadena.
        return "";
    }

    public static void main(String[] args) {
        System.out.println(new Ej047QueryParams().sum(2, 3));
    }

    // --- MÉTODOS Y ENUM DE RETOS EXTRA ---

    public enum DiaSemana {
        LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
    }

    /**
     * Reto Extra 1: Multiplicación con parámetros obligatorios.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/mult")
    public String multiplicar(
            @org.springframework.web.bind.annotation.RequestParam Integer a,
            @org.springframework.web.bind.annotation.RequestParam Integer b) {
        // TODO extra: Reto Extra 1: Multiplicación con parámetros obligatorios.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 2: Parámetro con valor por defecto.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/saludo")
    public String saludoPorDefecto(@org.springframework.web.bind.annotation.RequestParam(defaultValue = "es") String idioma) {
        // TODO extra: Reto Extra 2: Parámetro con valor por defecto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 3: Parámetro multi-valor mapeado a lista.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/nombres")
    public String listaQueryParams(@org.springframework.web.bind.annotation.RequestParam java.util.List<String> nombres) {
        // TODO extra: Reto Extra 3: Parámetro multi-valor mapeado a lista.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 4: Mapeo de todos los query params a un Map.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/filtro")
    public java.util.Map<String, String> mapaQueryParams(@org.springframework.web.bind.annotation.RequestParam java.util.Map<String, String> params) {
        // TODO extra: Reto Extra 4: Mapeo de todos los query params a un Map.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 5: Parámetro de consulta completamente opcional mediante Optional.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/buscar")
    public String parametroOpcional(@org.springframework.web.bind.annotation.RequestParam(required = false) java.util.Optional<String> q) {
        // TODO extra: Reto Extra 5: Parámetro de consulta completamente opcional mediante Optional.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 6: Conversión de booleano con valor predeterminado.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/transformar")
    public String booleanoParam(
            @org.springframework.web.bind.annotation.RequestParam(defaultValue = "false") Boolean invertido,
            @org.springframework.web.bind.annotation.RequestParam String texto) {
        // TODO extra: Reto Extra 6: Conversión de booleano con valor predeterminado.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 7: Conversión a LocalDate con anotación DateTimeFormat.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/eventos")
    public String fechaParam(
            @org.springframework.web.bind.annotation.RequestParam
            @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE)
            java.time.LocalDate limite) {
        // TODO extra: Reto Extra 7: Conversión a LocalDate con anotación DateTimeFormat.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 8: Conversión automática a Enum.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/horario")
    public String enumParam(@org.springframework.web.bind.annotation.RequestParam DiaSemana dia) {
        // TODO extra: Reto Extra 8: Conversión automática a Enum.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 9: Validación defensiva y lanzamiento manual de IllegalArgumentException.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/edad")
    public String validarMayorDeEdad(@org.springframework.web.bind.annotation.RequestParam Integer edad) {
        // TODO extra: Reto Extra 9: Validación defensiva y lanzamiento manual de IllegalArgumentException.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 10: Query parameter con nombre explícito diferente.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/profile")
    public String queryConMismoNombre(@org.springframework.web.bind.annotation.RequestParam(name = "username") String user) {
        // TODO extra: Reto Extra 10: Query parameter con nombre explícito diferente.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

}
