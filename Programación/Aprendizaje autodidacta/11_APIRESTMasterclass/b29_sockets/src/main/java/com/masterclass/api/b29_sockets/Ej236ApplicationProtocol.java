package com.masterclass.api.b29_sockets;

/**
 * Ejercicio 236 · Un protocolo de aplicación propio sobre TCP: mini key-value server.
 *
 * <p>Un socket transporta bytes; el <b>protocolo</b> es el acuerdo de qué significan esos
 * bytes. Aquí defines uno textual, línea a línea, sobre un mapa en memoria:
 * <pre>
 *   PUT clave valor   -&gt; "OK"
 *   GET clave         -&gt; el valor, o "NIL" si no existe
 *   QUIT              -&gt; el servidor cierra la conexión
 *   (cualquier otra)  -&gt; "ERROR"
 * </pre>
 *
 * <p>Esto es exactamente lo que hacen Redis o memcached en pequeño, y es el puente mental
 * hacia REST: cambia el protocolo de texto por HTTP y el mapa por una base de datos, y tienes
 * un endpoint ({@code b00}, {@code b05}). El estado compartido entre clientes exige un mapa
 * concurrente (b27·Ej223).
 *
 * <p>Teoría: {@code teoria/29_Sockets_Red.md} (sección 29.4).
 */
public final class Ej236ApplicationProtocol {

    private Ej236ApplicationProtocol() {
    }

    /**
     * Conecta al mini-servidor, hace PUT de una clave y luego GET de la misma, y devuelve el valor.
     *
     * @param clave clave a almacenar
     * @param valor valor a almacenar (sin espacios para este core)
     * @return el valor recuperado con GET (== valor)
     */
    public static String putYLuegoGet(String clave, String valor) {
        // TODO 1: levanta el servidor con un Map<String,String> (HashMap basta si un solo cliente).
        // TODO 2: el bucle del servidor lee líneas; parte cada línea en partes con split(" ").
        // TODO 3: si partes[0].equals("PUT") -> mapa.put(partes[1],partes[2]) y responde "OK".
        // TODO 4: si es "GET" -> responde mapa.getOrDefault(partes[1],"NIL"); si es "QUIT" -> cierra; otro -> "ERROR".
        // TODO 5: en el cliente envía "PUT clave valor", lee "OK"; luego envía "GET clave", lee la respuesta.
        // TODO 6: devuelve la respuesta del GET (maneja IOException).
        return null;
    }

    /**
     * Hace GET de una clave que nunca se almacenó.
     *
     * @param clave clave inexistente
     * @return "NIL" (convención del protocolo para "no encontrado")
     */
    public static String getInexistenteDevuelveNil(String clave) {
        // TODO 7: reutiliza el servidor del protocolo (extrae un método privado arrancarServidorKV()).
        // TODO 8: el cliente envía directamente "GET " + clave (sin PUT previo).
        // TODO 9: lee la respuesta del servidor.
        // TODO 10: devuelve la respuesta, que debe ser exactamente "NIL".
        return null;
    }

    public static void main(String[] args) {
        System.out.println("putYLuegoGet(color,azul) = " + putYLuegoGet("color", "azul"));
        System.out.println("getInexistenteDevuelveNil(x) = " + getInexistenteDevuelveNil("x"));
    }

    /**
     * Reto Extra 1: PUT responde "OK".
     * @return la respuesta del servidor a un PUT (debe ser "OK")
     */
    public static String putRespondeOk() {
        // GUÍA: teoría 29.4. Envía "PUT k v" y lee la respuesta: el servidor confirma con "OK".
        // OJO: el test espera EXACTAMENTE "OK" (mayúsculas).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para putRespondeOk");
    }

    /**
     * Reto Extra 2: un comando no reconocido responde "ERROR".
     * @return la respuesta del servidor a un comando inválido (debe ser "ERROR")
     */
    public static String comandoDesconocidoDevuelveError() {
        // GUÍA: envía "FOO bar" (verbo inexistente). En el switch del servidor, el caso default
        // responde "ERROR". Un protocolo robusto SIEMPRE contesta algo, no se queda mudo.
        // OJO: el test espera "ERROR".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para comandoDesconocidoDevuelveError");
    }

    /**
     * Reto Extra 3: un segundo PUT sobre la misma clave la sobrescribe.
     * @return el valor final tras PUT k v1 y PUT k v2 y GET k (debe ser "v2")
     */
    public static String sobrescribirValor() {
        // GUÍA: Map.put devuelve el valor anterior y sustituye. Secuencia: PUT k v1, PUT k v2, GET k.
        // OJO: el test espera "v2".
        // PISTA: reutiliza el servidor KV; aquí cambia solo la secuencia de comandos del cliente.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sobrescribirValor");
    }

    /**
     * Reto Extra 4: claves distintas son independientes.
     * @return el valor de la clave "a" tras PUT a 1, PUT b 2 (debe ser "1")
     */
    public static String varriasClavesIndependientes() {
        // GUÍA: PUT a 1, PUT b 2, GET a → "1". El mapa guarda cada clave por separado.
        // OJO: el test espera "1" (como String, no número).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para varriasClavesIndependientes");
    }

    /**
     * Reto Extra 5: QUIT hace que el servidor cierre la conexión.
     * @return true si tras enviar "QUIT" la siguiente lectura del cliente ve fin de stream
     */
    public static boolean quitCierraConexion() {
        // GUÍA: el caso "QUIT" del servidor hace break del bucle y cierra el socket. El cliente, tras
        // enviar "QUIT", hace in.readLine() y obtiene null (EOF) → return true.
        // CULTURA: un comando de cierre limpio evita dejar conexiones colgadas. Enlaza con Ej240.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para quitCierraConexion");
    }

    /**
     * Reto Extra 6: un valor con espacios se preserva si parseas con límite.
     * @return el valor "hola mundo" tras PUT frase hola mundo y GET frase
     */
    public static String valorConEspacios() {
        // GUÍA: si haces split(" ") a secas, "PUT frase hola mundo" pierde " mundo". Usa
        // split(" ", 3): partes = ["PUT","frase","hola mundo"]. El tercer trozo conserva los espacios.
        // PISTA: el límite 3 en split corta solo las 2 primeras veces y deja el resto entero.
        // OJO: el test espera "hola mundo".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para valorConEspacios");
    }

    /**
     * Reto Extra 7: el protocolo distingue mayúsculas (los verbos van en MAYÚSCULAS).
     * @return la respuesta a "get k" en minúsculas (debe ser "ERROR" porque el verbo es "GET")
     */
    public static String comandoEsCaseSensitive() {
        // GUÍA: el servidor compara con "GET"/"PUT" exactos. "get" en minúsculas NO casa → default "ERROR".
        // OJO/CUIDADO: el test manda el verbo en minúsculas a propósito y espera "ERROR". Define bien
        // tu protocolo: o lo haces case-insensitive con toUpperCase, o documentas que es sensible.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para comandoEsCaseSensitive");
    }

    /**
     * Reto Extra 8: el estado se comparte entre clientes (un cliente PUT, otro GET lo ve).
     * @return el valor que el segundo cliente lee tras el PUT del primero (debe ser "compartido")
     */
    public static String estadoCompartidoEntreClientes() {
        // GUÍA: teoría 29.3/29.4 + b27·Ej223. El mapa debe ser ÚNICO para todo el servidor y
        // concurrente: ConcurrentHashMap, compartido por todos los handlers. Cliente1 hace
        // PUT k compartido (y QUIT/cierra); cliente2 hace GET k y recibe "compartido".
        // OJO/CUIDADO: si cada handler crea su PROPIO mapa, el cliente2 vería "NIL" (bug clásico).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estadoCompartidoEntreClientes");
    }

    /**
     * Reto Extra 9: el servidor cuenta cuántos comandos ha procesado en una sesión.
     * @return número de comandos procesados (== 3: PUT, GET, GET)
     */
    public static int contarComandosProcesados() {
        // GUÍA: incrementa un contador por cada línea-comando atendida (antes del QUIT). Envía
        // PUT a 1, GET a, GET b y luego QUIT → 3 comandos procesados (el QUIT no cuenta).
        // PISTA: devuelve el contador por un int[1]/AtomicInteger capturado por el handler.
        // OJO: el test espera 3.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarComandosProcesados");
    }

    /**
     * Reto Extra 10: GET de clave inexistente tras haber puesto OTRA clave sigue dando "NIL".
     * @return "NIL" tras PUT a 1 y GET zzz
     */
    public static String getDeOtraClaveDaNil() {
        // GUÍA: PUT a 1, luego GET zzz (clave que no existe) → "NIL". getOrDefault devuelve el default
        // solo si la clave consultada no está, aunque el mapa tenga otras.
        // OJO: el test espera "NIL".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para getDeOtraClaveDaNil");
    }
}
