package com.masterclass.api.b05_web;

/**
 * Ejercicio 045 · Primer @RestController.
 *
 * <p>Teoría: {@code teoria/05_Controllers_REST.md} (secciones 5.1 y 5.2).
 *
 * <p>El test usa MockMvc en modo standalone y espera {@code GET /api/hello}
 * devolviendo 200 con el cuerpo exacto {@code "hola"}.
 */
// TODO 1: anota la clase con @org.springframework.web.bind.annotation.RestController.
// TODO 2: anota la clase con @RequestMapping("/api") para prefijar la ruta.
public class Ej045HelloController {

    /**
     * Responde al saludo.
     *
     * @return el texto "hola" con status 200
     */
    // TODO 3: anota el método con @GetMapping("/hello").
    // TODO 4: asegúrate de que la firma siga devolviendo String (cuerpo de la respuesta).
    public String hello() {
        // TODO 5: por defecto un @RestController serializa el return como cuerpo.
        // TODO 6: NO uses System.out: el valor RETORNADO es la respuesta HTTP.
        // TODO 7: el cuerpo esperado por el test es exactamente "hola" (sin comillas extra).
        // TODO 8: el Content-Type por defecto será text/plain para un String: es correcto aquí.
        // TODO 9: no añadas saltos de línea ni espacios alrededor.
        // TODO 10: devuelve "hola".
        return "";
    }

    public static void main(String[] args) {
        System.out.println(new Ej045HelloController().hello());
    }

    // --- MÉTODOS Y DTOs DE RETOS EXTRA ---

    public record MensajeJson(String mensaje, long timestamp) {}

    /**
     * Reto Extra 1: Saludo personalizado leyendo cabecera.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/saludo-custom")
    // y recibe el header "X-User-Name" con @org.springframework.web.bind.annotation.RequestHeader("X-User-Name").
    public String saludoPersonalizado(String nombre) {
        // GUÍA: teoría 5.2 (cabeceras con @RequestHeader, prima de @RequestParam).
        // 1. Renombra el parámetro a @RequestHeader("X-User-Name") String nombre:
        //    Spring inyecta el valor de esa cabecera HTTP, no de la query string.
        // 2. Una línea: return "Hola, " + nombre;
        // OJO: el test manda X-User-Name="Carlos" y compara el body EXACTO con
        //   "Hola, Carlos" (coma + espacio). Cualquier espacio de más falla.
        // CULTURA: las cabeceras transportan metadatos (auth, idioma, trazas);
        //   los datos de negocio van en path/query/body.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 2: Respuesta serializada a JSON.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/saludo-json")
    public MensajeJson saludoJson() {
        // GUÍA: teoría 5.2 — un @RestController serializa a JSON cualquier objeto
        //   que NO sea String (Jackson, bloque 2). Devolviendo un record sale JSON.
        // 1. Una línea: return new MensajeJson("hola", 1234567890L);
        // OJO: el test lee $.mensaje == "hola" y $.timestamp == 1234567890.
        //   El timestamp es un VALOR FIJO esperado, no System.currentTimeMillis()
        //   (un tiempo real haría el test indeterminista).
        // PISTA: los nombres de campo JSON salen de los componentes del record
        //   (mensaje, timestamp) sin que tengas que configurar nada.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 3: Código de estado declarativo.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping("/creado")
    // y con @org.springframework.web.bind.annotation.ResponseStatus(org.springframework.http.HttpStatus.CREATED).
    public String endpointCreado() {
        // GUÍA: teoría 5.3 — status declarativo con @ResponseStatus.
        // 1. Sobre el método (además de @PostMapping("/creado")) añade
        //    @ResponseStatus(HttpStatus.CREATED): fija el 201 sin ResponseEntity.
        // 2. Una línea: return "recurso creado";
        // OJO: el test hace POST (no GET) a /api/creado, espera status 201 y body
        //   EXACTO "recurso creado".
        // CULTURA: @ResponseStatus es la vía "fija"; ResponseEntity es la vía
        //   "dinámica" cuando el código depende de la lógica (lo ves en el reto 9).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PostMapping");
    }

    /**
     * Reto Extra 4: Múltiples rutas de alias.
     */
    // GUÍA: teoría 5.2 — una misma acción puede escuchar en VARIAS rutas.
    // 1. Anota con @GetMapping({"/alias1", "/alias2"}) (el atributo value acepta
    //    un array de rutas; las llaves {} son las de un array Java, no una ruta).
    // 2. El cuerpo ya está resuelto abajo: return "alias";
    // OJO: el test pega a /api/alias1 Y a /api/alias2 y ambos deben dar "alias".
    // TODO extra: anota con @GetMapping({"/alias1", "/alias2"}) y devuelve "alias".
    public String aliasSaludo() {
        // TODO extra: devuelve "alias".
        return null;
    }

    /**
     * Reto Extra 5: Mapeo genérico multimetodo (GET y POST).
     */
    // GUÍA: teoría 5.2 — @RequestMapping es la anotación genérica de la que
    //   @GetMapping/@PostMapping son atajos; con method={...} escucha varios verbos.
    // 1. Anota con @RequestMapping(value = "/echo-method",
    //      method = {RequestMethod.GET, RequestMethod.POST})
    //    (RequestMethod está en org.springframework.web.bind.annotation).
    // 2. El cuerpo ya está abajo: return request.getMethod();
    // OJO: el test hace GET → espera "GET" y POST → espera "POST" en la misma ruta.
    //   request.getMethod() devuelve el verbo en MAYÚSCULAS, justo lo que se compara.
    // TODO extra: anota con @RequestMapping(value="/echo-method",
    //   method={RequestMethod.GET, RequestMethod.POST}) y devuelve request.getMethod().
    public String echoMethod(jakarta.servlet.http.HttpServletRequest request) {
        // TODO extra: devuelve el método HTTP de la petición (request.getMethod()).
        return null;
    }

    /**
     * Reto Extra 6: Inspección de cabeceras de petición.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/headers")
    public java.util.Map<String, String> cabecerasPersonalizadas(
            @org.springframework.web.bind.annotation.RequestHeader("User-Agent") String userAgent,
            @org.springframework.web.bind.annotation.RequestHeader("Accept") String accept) {
        // GUÍA: teoría 5.2 — devolver un Map<String,String> se serializa a objeto JSON.
        // 1. Los dos @RequestHeader ya inyectan User-Agent y Accept (params listos).
        // 2. Construye y devuelve un Map con esas dos entradas. La forma robusta es
        //    un LinkedHashMap (Map.of NO admite null y el test no lo necesita aquí):
        //    var m = new java.util.LinkedHashMap<String,String>();
        //    m.put("User-Agent", userAgent); m.put("Accept", accept); return m;
        // OJO: el test lee $.['User-Agent']=="Mozilla" y $.Accept=="application/json":
        //   las CLAVES del JSON deben ser literalmente "User-Agent" y "Accept".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 7: Producción de JSON explícito.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping(value = "/json-only", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public String contentTypeJson() {
        // GUÍA: teoría 5.2 — produces fija el Content-Type de salida.
        // 1. La anotación (arriba) ya lleva produces = APPLICATION_JSON_VALUE: el
        //    método devuelve un String que YA ES JSON; Spring no lo reserializa.
        // 2. Devuelve el JSON a mano como texto: return "{\"status\":\"ok\"}";
        // OJO: el test exige Content-Type compatible con application/json Y
        //   $.status == "ok". Si devolvieras texto plano sin produces, jsonPath
        //   fallaría. Cuida las comillas escapadas dentro del String.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    /**
     * Reto Extra 8: Consumo de texto plano exclusivo.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping(value = "/text-only", consumes = org.springframework.http.MediaType.TEXT_PLAIN_VALUE)
    public int consumesTextPlain(@org.springframework.web.bind.annotation.RequestBody String cuerpo) {
        // GUÍA: teoría 5.2 — consumes restringe el Content-Type de ENTRADA.
        // 1. La anotación (arriba) ya lleva consumes = TEXT_PLAIN_VALUE: el cuerpo
        //    llega como String crudo en 'cuerpo' (sin pasar por Jackson).
        // 2. Una línea: return cuerpo.length();
        // OJO: el test manda content("hola mundo") como text/plain y espera body
        //   "10" (longitud de "hola mundo", con el espacio). Devuelves un int y
        //   Spring lo serializa como "10".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PostMapping");
    }

    /**
     * Reto Extra 9: Respuesta directa con ResponseEntity I_AM_A_TEAPOT.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/teapot")
    public org.springframework.http.ResponseEntity<String> teapotStatus() {
        // GUÍA: teoría 5.3 — ResponseEntity da control total de status + body.
        // 1. Una línea:
        //    return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("soy una tetera");
        //    (HttpStatus está en org.springframework.http; I_AM_A_TEAPOT == 418).
        // OJO: el test comprueba status().is(418) y body EXACTO "soy una tetera".
        // CULTURA: el 418 es el chiste de RFC 2324 (Hyper Text Coffee Pot Control
        //   Protocol); lo verás de nuevo en Ej049 como cabecera personalizada.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

    private final java.util.concurrent.atomic.AtomicInteger contador = new java.util.concurrent.atomic.AtomicInteger(0);

    /**
     * Reto Extra 10: Contador de visitas en memoria.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.GetMapping("/contador")
    public int contadorVisitas() {
        // GUÍA: teoría 5.1 + concurrencia (1.11) — un controller es un singleton
        //   que atiende muchas peticiones a la vez: el estado debe ser thread-safe.
        // 1. El campo 'contador' (arriba) es un AtomicInteger ya inicializado a 0.
        // 2. Una línea: return contador.incrementAndGet();
        //    incrementAndGet() suma 1 y DEVUELVE el nuevo valor de forma atómica.
        // OJO: el test llama dos veces y espera "1" y luego "2": por eso el campo
        //   es de instancia (persiste entre peticiones), no una variable local.
        // CUIDADO: contador++ sobre un int normal perdería incrementos bajo carga.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para GetMapping");
    }

}
