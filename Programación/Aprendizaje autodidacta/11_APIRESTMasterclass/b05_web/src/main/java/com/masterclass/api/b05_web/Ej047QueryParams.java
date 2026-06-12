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
        // GUÍA: teoría 5.2 — @RequestParam sin defaultValue es OBLIGATORIO; si
        //   falta, Spring responde 400 antes de entrar al método.
        // 1. Una línea: return String.valueOf(a * b);
        // OJO: el test pega a /api/mult?a=6&b=7 y espera body "42". Devuelve String
        //   (no int) para que el cuerpo sea text/plain con el número.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 2: Parámetro con valor por defecto.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/saludo")
    public String saludoPorDefecto(@org.springframework.web.bind.annotation.RequestParam(defaultValue = "es") String idioma) {
        // GUÍA: teoría 5.2 — defaultValue hace el parámetro opcional con un valor base.
        // 1. Devuelve el saludo según el idioma con un switch:
        //    "es" -> "Hola"; "en" -> "Hello"; "fr" -> "Aloha".
        //    PISTA: return switch (idioma) { case "en" -> "Hello"; ... };
        // OJO: el test cubre /api/saludo (sin param → "es" → "Hola"),
        //   ?idioma=en → "Hello" y ?idioma=fr → "Aloha". Los strings son exactos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 3: Parámetro multi-valor mapeado a lista.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/nombres")
    public String listaQueryParams(@org.springframework.web.bind.annotation.RequestParam java.util.List<String> nombres) {
        // GUÍA: teoría 5.2 — un mismo parámetro repetido (?nombres=Ana&nombres=Luis)
        //   o separado por comas se mapea a List<String>.
        // 1. Una línea: return String.join(",", nombres);
        // OJO: el test manda .param("nombres","Ana","Luis","Pedro") (tres valores) y
        //   espera "Ana,Luis,Pedro" (unidos por coma, sin espacios, en orden).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 4: Mapeo de todos los query params a un Map.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/filtro")
    public java.util.Map<String, String> mapaQueryParams(@org.springframework.web.bind.annotation.RequestParam java.util.Map<String, String> params) {
        // GUÍA: teoría 5.2 — @RequestParam Map<String,String> recoge TODOS los query
        //   params sin declararlos uno a uno (útil para filtros dinámicos).
        // 1. Una línea: return params;
        // OJO: el test manda ?criterio=activo&orden=desc y espera $.criterio=="activo"
        //   y $.orden=="desc". El Map se serializa como objeto JSON tal cual.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 5: Parámetro de consulta completamente opcional mediante Optional.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/buscar")
    public String parametroOpcional(@org.springframework.web.bind.annotation.RequestParam(required = false) java.util.Optional<String> q) {
        // GUÍA: teoría 5.2 + Optional (1.2) — Optional<String> modela "puede no venir"
        //   sin riesgo de null.
        // 1. Una línea: return q.map(v -> "buscando: " + v).orElse("sin criterio");
        // OJO: el test pega a ?q=muelle → "buscando: muelle" y a /api/buscar (sin q)
        //   → "sin criterio". Encadena map/orElse, nada de isPresent()/get().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 6: Conversión de booleano con valor predeterminado.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/transformar")
    public String booleanoParam(
            @org.springframework.web.bind.annotation.RequestParam(defaultValue = "false") Boolean invertido,
            @org.springframework.web.bind.annotation.RequestParam String texto) {
        // GUÍA: teoría 5.2 — Spring convierte "true"/"false" a Boolean; defaultValue
        //   cubre el caso en que el flag no venga.
        // 1. Si invertido es true, devuelve el texto al revés; si no, tal cual.
        //    PISTA: new StringBuilder(texto).reverse().toString() para invertir.
        //    return invertido ? new StringBuilder(texto).reverse().toString() : texto;
        // OJO: el test con ?texto=antigravity&invertido=true espera "ytivargitna" y
        //   sin invertido (default false) espera "antigravity".
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
        // GUÍA: teoría 5.2 + java.time — @DateTimeFormat(iso = ISO.DATE) parsea
        //   "2026-05-21" a LocalDate también en query params.
        // 1. Una línea: return String.valueOf(limite.getMonth());
        // OJO: el test pega a ?limite=2026-05-21 y espera "MAY" (el name() del enum
        //   Month, en inglés y mayúsculas). getMonth() devuelve Month.MAY.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 8: Conversión automática a Enum.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/horario")
    public String enumParam(@org.springframework.web.bind.annotation.RequestParam DiaSemana dia) {
        // GUÍA: teoría 5.2 — Spring convierte el texto al valor del enum por nombre
        //   ("LUNES" → DiaSemana.LUNES); si no existe, 400.
        // 1. Si el día es SABADO o DOMINGO devuelve "fin de semana"; si no
        //    "dia laborable". PISTA: dia == DiaSemana.SABADO || dia == DiaSemana.DOMINGO.
        // OJO: el test cubre ?dia=LUNES → "dia laborable" y ?dia=SABADO →
        //   "fin de semana".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 9: Validación defensiva y lanzamiento manual de IllegalArgumentException.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/edad")
    public String validarMayorDeEdad(@org.springframework.web.bind.annotation.RequestParam Integer edad) {
        // GUÍA: teoría 5.3 — validación manual lanzando una excepción de negocio.
        // 1. Si edad < 18, lanza new IllegalArgumentException(...) (mensaje libre);
        //    si no, return "acceso concedido".
        // OJO: el test con ?edad=20 espera "acceso concedido"; con ?edad=15 hace
        //   assertThrows(Exception.class, ...) — basta con que el endpoint propague
        //   una excepción (aquí no hay handler, en Ej054 sí la mapearás a HTTP).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 10: Query parameter con nombre explícito diferente.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/profile")
    public String queryConMismoNombre(@org.springframework.web.bind.annotation.RequestParam(name = "username") String user) {
        // GUÍA: teoría 5.2 — name="username" desacopla el nombre del query param del
        //   nombre de la variable Java (igual que @PathVariable("...") en Ej046 reto 1).
        // 1. Una línea: return "perfil de: " + user;
        // OJO: el test manda ?username=master (no ?user=master) y espera
        //   "perfil de: master". El binding usa el name, no el identificador 'user'.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

}
