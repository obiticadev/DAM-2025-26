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
        // TODO extra: devuelve el producto de a por b como String (String.valueOf(a * b)).
        return null;
    }

    /**
     * Reto Extra 2: Parámetro con valor por defecto.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/saludo")
    public String saludoPorDefecto(@org.springframework.web.bind.annotation.RequestParam(defaultValue = "es") String idioma) {
        // TODO extra: si idioma es "es" devuelve "Hola", si es "en" devuelve "Hello", para cualquier otro "Aloha".
        return null;
    }

    /**
     * Reto Extra 3: Parámetro multi-valor mapeado a lista.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/nombres")
    public String listaQueryParams(@org.springframework.web.bind.annotation.RequestParam java.util.List<String> nombres) {
        // TODO extra: une los nombres recibidos usando comas como delimitador (String.join(",", nombres)).
        return null;
    }

    /**
     * Reto Extra 4: Mapeo de todos los query params a un Map.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/filtro")
    public java.util.Map<String, String> mapaQueryParams(@org.springframework.web.bind.annotation.RequestParam java.util.Map<String, String> params) {
        // TODO extra: devuelve el Map recibido con todos los parámetros de consulta.
        return null;
    }

    /**
     * Reto Extra 5: Parámetro de consulta completamente opcional mediante Optional.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/buscar")
    public String parametroOpcional(@org.springframework.web.bind.annotation.RequestParam(required = false) java.util.Optional<String> q) {
        // TODO extra: si q está presente, devuelve "buscando: " + q.get(); si no, devuelve "sin criterio".
        return null;
    }

    /**
     * Reto Extra 6: Conversión de booleano con valor predeterminado.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/transformar")
    public String booleanoParam(
            @org.springframework.web.bind.annotation.RequestParam(defaultValue = "false") Boolean invertido,
            @org.springframework.web.bind.annotation.RequestParam String texto) {
        // TODO extra: si invertido es true, devuelve el texto al revés (usando StringBuilder.reverse()); si no, el texto original.
        return null;
    }

    /**
     * Reto Extra 7: Conversión a LocalDate con anotación DateTimeFormat.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/eventos")
    public String fechaParam(
            @org.springframework.web.bind.annotation.RequestParam
            @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE)
            java.time.LocalDate limite) {
        // TODO extra: devuelve el mes de la fecha recibida en formato String (limite.getMonth().toString()).
        return null;
    }

    /**
     * Reto Extra 8: Conversión automática a Enum.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/horario")
    public String enumParam(@org.springframework.web.bind.annotation.RequestParam DiaSemana dia) {
        // TODO extra: si dia es SABADO o DOMINGO devuelve "fin de semana", si no "dia laborable".
        return null;
    }

    /**
     * Reto Extra 9: Validación defensiva y lanzamiento manual de IllegalArgumentException.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/edad")
    public String validarMayorDeEdad(@org.springframework.web.bind.annotation.RequestParam Integer edad) {
        // TODO extra: si edad es menor de 18, lanza IllegalArgumentException con mensaje "menor de edad".
        // Si es mayor o igual, devuelve "acceso concedido".
        return null;
    }

    /**
     * Reto Extra 10: Query parameter con nombre explícito diferente.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/profile")
    public String queryConMismoNombre(@org.springframework.web.bind.annotation.RequestParam(name = "username") String user) {
        // TODO extra: devuelve "perfil de: " seguido de user.
        return null;
    }

}
