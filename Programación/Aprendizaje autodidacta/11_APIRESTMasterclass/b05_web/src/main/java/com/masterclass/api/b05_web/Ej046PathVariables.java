package com.masterclass.api.b05_web;

/**
 * Ejercicio 046 · @PathVariable.
 *
 * <p>Teoría: {@code teoria/05_Controllers_REST.md} (sección 5.2).
 *
 * <p>El test espera {@code GET /api/echo/{valor}} devolviendo el valor recibido.
 */
// TODO 1: anota la clase con @RestController.
// TODO 2: anota la clase con @RequestMapping("/api").
public class Ej046PathVariables {

    /**
     * Devuelve el segmento de ruta recibido.
     *
     * @param valor segmento variable de la URL
     * @return el mismo valor como cuerpo
     */
    // TODO 3: anota el método con @GetMapping("/echo/{valor}").
    // TODO 4: anota el parámetro con @PathVariable para enlazarlo al segmento {valor}.
    public String echo(String valor) {
        // TODO 5: el nombre del @PathVariable debe coincidir con "{valor}" de la ruta.
        // TODO 6: si difieren, usa @PathVariable("valor").
        // TODO 7: no transformes el valor (eco literal).
        // TODO 8: el cuerpo de respuesta es exactamente 'valor'.
        // TODO 9: status 200 implícito al devolver normalmente.
        // TODO 10: devuelve 'valor'.
        return "";
    }

    public static void main(String[] args) {
        System.out.println(new Ej046PathVariables().echo("abc"));
    }

    // --- MÉTODOS DE RETOS EXTRA ---

    /**
     * Reto Extra 1: PathVariable con nombre explícito diferente.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/echo-diff/{
        // TODO extra: Reto Extra 1: PathVariable con nombre explícito diferente.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }")
    public String echoDiferente(@org.springframework.web.bind.annotation.PathVariable("otroNombre") String val) {
        // TODO extra: devuelve 'val'.
        return null;
    }

    /**
     * Reto Extra 2: Múltiples variables de ruta concurrentes.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/multi/{
        // TODO extra: Reto Extra 2: Múltiples variables de ruta concurrentes.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }/sub/{p2}")
    public String multiplesVariables(
            @org.springframework.web.bind.annotation.PathVariable String p1,
            @org.springframework.web.bind.annotation.PathVariable String p2) {
        // TODO extra: devuelve la concatenación de p1 y p2 separados por un guión (p1 + "-" + p2).
        return null;
    }

    /**
     * Reto Extra 3: Conversión automática a tipo Integer.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/buscar/{
        // TODO extra: Reto Extra 3: Conversión automática a tipo Integer.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }")
    public int variableInteger(@org.springframework.web.bind.annotation.PathVariable Integer id) {
        // TODO extra: devuelve el doble del valor de id.
        return 0;
    }

    /**
     * Reto Extra 4: Conversión automática a tipo UUID.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/uuid/{
        // TODO extra: Reto Extra 4: Conversión automática a tipo UUID.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }")
    public String variableUuid(@org.springframework.web.bind.annotation.PathVariable java.util.UUID uuid) {
        // TODO extra: devuelve la representación string del UUID.
        return null;
    }

    /**
     * Reto Extra 5: Validación de patrón con expresión regular.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/codigo/{
        // TODO extra: Reto Extra 5: Validación de patrón con expresión regular.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }")
    public String variableRegex(@org.springframework.web.bind.annotation.PathVariable String codigo) {
        // TODO extra: devuelve "codigo valido: " seguido del codigo.
        return null;
    }

    /**
     * Reto Extra 6: Conversión a LocalDate con formato específico.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/fecha/{
        // TODO extra: Reto Extra 6: Conversión a LocalDate con formato específico.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }")
    public String variableFecha(
            @org.springframework.web.bind.annotation.PathVariable
            @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE)
            java.time.LocalDate fecha) {
        // TODO extra: devuelve el año obtenido de la fecha en formato String.
        return null;
    }

    /**
     * Reto Extra 7: Variable de ruta opcional.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping(value = {
        // TODO extra: Reto Extra 7: Variable de ruta opcional.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    })
    public String variableOpcional(@org.springframework.web.bind.annotation.PathVariable(name = "extra", required = false) java.util.Optional<String> extra) {
        // TODO extra: devuelve el valor contenido si está presente, o "vacio" si no lo está.
        return null;
    }

    /**
     * Reto Extra 8: Capturar todas las variables de ruta dinámicas en un Map.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/dinamico/{
        // TODO extra: Reto Extra 8: Capturar todas las variables de ruta dinámicas en un Map.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }/{c2}")
    public java.util.Map<String, String> mapaVariables(@org.springframework.web.bind.annotation.PathVariable java.util.Map<String, String> variables) {
        // TODO extra: devuelve el Map recibido con todas las variables de ruta dinámicas capturadas.
        return null;
    }

    /**
     * Reto Extra 9: Lista de valores separados por comas.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/batch/{
        // TODO extra: Reto Extra 9: Lista de valores separados por comas.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }")
    public int listaVariables(@org.springframework.web.bind.annotation.PathVariable java.util.List<Long> ids) {
        // TODO extra: devuelve el tamaño de la lista de ids recibida.
        return 0;
    }

    /**
     * Reto Extra 10: Captura de variables de matriz.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/coches/{
        // TODO extra: Reto Extra 10: Captura de variables de matriz.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }")
    public String matrizVariables(
            @org.springframework.web.bind.annotation.PathVariable String modelo,
            @org.springframework.web.bind.annotation.MatrixVariable(name = "color", pathVar = "modelo") String color,
            @org.springframework.web.bind.annotation.MatrixVariable(name = "anio", pathVar = "modelo") String anio) {
        // TODO extra: devuelve la cadena concatenando "modelo:" + modelo + ", color:" + color + ", anio:" + anio.
        return null;
    }

}
