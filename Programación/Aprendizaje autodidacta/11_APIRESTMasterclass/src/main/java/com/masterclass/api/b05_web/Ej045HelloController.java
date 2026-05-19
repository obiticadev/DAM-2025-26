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
}
