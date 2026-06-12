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
    // GUÍA: teoría 5.2 — cuando el parámetro Java NO se llama igual que el {token}
    //   de la ruta, hay que nombrar la variable explícitamente.
    // 1. Anota con @GetMapping("/echo-diff/{otroNombre}").
    // 2. El parámetro ya usa @PathVariable("otroNombre") (mapea {otroNombre} → val).
    // 3. El cuerpo ya está abajo: return val;
    // OJO: el test pega a /api/echo-diff/cualquierValor y espera "cualquierValor".
    //   El nombre del @PathVariable debe coincidir con el token de la ruta, NO con
    //   el del parámetro Java.
    // TODO extra: anota con @GetMapping("/echo-diff/{otroNombre}") y devuelve val.
    public String echoDiferente(@org.springframework.web.bind.annotation.PathVariable("otroNombre") String val) {
        // TODO extra: devuelve 'val'.
        return null;
    }

    /**
     * Reto Extra 2: Múltiples variables de ruta concurrentes.
     */
    // GUÍA: teoría 5.2 — una ruta puede tener VARIOS {tokens}.
    // 1. Anota con @GetMapping("/multi/{p1}/sub/{p2}"): dos variables separadas
    //    por un segmento fijo "/sub/".
    // 2. Como los parámetros se llaman igual que los tokens, @PathVariable sin
    //    nombre basta (ya están así abajo).
    // 3. El cuerpo ya está: return p1 + "-" + p2;
    // OJO: el test pega a /api/multi/hola/sub/mundo y espera "hola-mundo"
    //   (guión en medio, sin espacios).
    // TODO extra: anota con @GetMapping("/multi/{p1}/sub/{p2}").
    public String multiplesVariables(
            @org.springframework.web.bind.annotation.PathVariable String p1,
            @org.springframework.web.bind.annotation.PathVariable String p2) {
        // TODO extra: devuelve la concatenación de p1 y p2 separados por un guión (p1 + "-" + p2).
        return null;
    }

    /**
     * Reto Extra 3: Conversión automática a tipo Integer.
     */
    // GUÍA: teoría 5.2 — Spring CONVIERTE el segmento de texto al tipo del parámetro.
    // 1. Anota con @GetMapping("/buscar/{id}"). El parámetro es Integer: Spring
    //    parsea "25" → 25 sin que tú llames a Integer.parseInt.
    // 2. El cuerpo ya está: return id * 2;
    // OJO: el test pega a /api/buscar/25 y espera "50" (el doble).
    // CULTURA: si mandaras /buscar/abc, Spring lanzaría
    //   MethodArgumentTypeMismatchException → 400 (lo gestionarás en Ej054 reto 2).
    // TODO extra: anota con @GetMapping("/buscar/{id}") y devuelve id * 2.
    public int variableInteger(@org.springframework.web.bind.annotation.PathVariable Integer id) {
        // TODO extra: devuelve el doble del valor de id.
        return 0;
    }

    /**
     * Reto Extra 4: Conversión automática a tipo UUID.
     */
    // GUÍA: teoría 5.2 — la conversión de tipos cubre más que números: UUID,
    //   LocalDate, enums… cualquier tipo con un conversor registrado.
    // 1. Anota con @GetMapping("/uuid/{uuid}"). El parámetro es java.util.UUID:
    //    Spring parsea la cadena a UUID (y rechaza con 400 si no es válida).
    // 2. El cuerpo ya está: return uuid.toString();
    // OJO: el test genera un UUID aleatorio y espera recibir su toString() idéntico.
    // TODO extra: anota con @GetMapping("/uuid/{uuid}") y devuelve uuid.toString().
    public String variableUuid(@org.springframework.web.bind.annotation.PathVariable java.util.UUID uuid) {
        // TODO extra: devuelve la representación string del UUID.
        return null;
    }

    /**
     * Reto Extra 5: Validación de patrón con expresión regular.
     */
    // GUÍA: teoría 5.2 — un {token} admite una RESTRICCIÓN regex con la sintaxis
    //   {nombre:regex}. Si la URL no casa, ni siquiera entra al método → 404.
    // 1. Anota con @GetMapping("/codigo/{codigo:[A-Z]+-[0-9]+}"): mayúsculas,
    //    guión y dígitos. Así ABC-1234 entra y abc-1234 NO.
    // 2. El cuerpo ya está: return "codigo valido: " + codigo;
    // OJO: el test exige /api/codigo/ABC-1234 → "codigo valido: ABC-1234" y
    //   /api/codigo/abc-1234 (minúsculas) → 404. El 404 lo produce el regex de la
    //   ruta, no un if dentro del método.
    // TODO extra: anota con @GetMapping("/codigo/{codigo:[A-Z]+-[0-9]+}").
    public String variableRegex(@org.springframework.web.bind.annotation.PathVariable String codigo) {
        // TODO extra: devuelve "codigo valido: " seguido del codigo.
        return null;
    }

    /**
     * Reto Extra 6: Conversión a LocalDate con formato específico.
     */
    // GUÍA: teoría 5.2 + java.time (1.10) — @DateTimeFormat dice CÓMO parsear la
    //   cadena de la ruta a LocalDate.
    // 1. Anota con @GetMapping("/fecha/{fecha}"). El parámetro ya combina
    //    @PathVariable + @DateTimeFormat(iso = ISO.DATE) (formato yyyy-MM-dd).
    // 2. El cuerpo ya está: return String.valueOf(fecha.getYear());
    // OJO: el test pega a /api/fecha/2026-05-21 y espera "2026" (solo el año).
    //   Sin @DateTimeFormat, Spring no sabría interpretar "2026-05-21" como fecha.
    // TODO extra: anota con @GetMapping("/fecha/{fecha}") y devuelve el año.
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
    // GUÍA: teoría 5.2 — una variable de ruta "opcional" se logra mapeando DOS
    //   rutas (con y sin el segmento) al mismo método.
    // 1. Anota con @GetMapping({"/opcional", "/opcional/{extra}"}) (array de rutas).
    // 2. El parámetro ya es Optional<String> con required=false: estará vacío
    //    cuando se llame sin el segmento.
    // 3. El cuerpo ya está: return extra.orElse("vacio");
    // OJO: el test pega a /api/opcional/adicional → "adicional" y a /api/opcional
    //   (sin nada) → "vacio". El Optional evita el NPE del caso ausente.
    // TODO extra: anota con @GetMapping({"/opcional", "/opcional/{extra}"}).
    public String variableOpcional(@org.springframework.web.bind.annotation.PathVariable(name = "extra", required = false) java.util.Optional<String> extra) {
        // TODO extra: devuelve el valor contenido si está presente, o "vacio" si no lo está.
        return null;
    }

    /**
     * Reto Extra 8: Capturar todas las variables de ruta dinámicas en un Map.
     */
    // GUÍA: teoría 5.2 — un @PathVariable Map<String,String> captura TODAS las
    //   variables de ruta de golpe, con la clave = nombre del token.
    // 1. Anota con @GetMapping("/dinamico/{c1}/{c2}").
    // 2. El parámetro ya es Map<String,String> variables (sin nombre en @PathVariable).
    // 3. El cuerpo ya está: return variables;
    // OJO: el test pega a /api/dinamico/perro/gato y espera $.c1=="perro" y
    //   $.c2=="gato". Las claves del Map son los nombres de los {tokens}.
    // TODO extra: anota con @GetMapping("/dinamico/{c1}/{c2}").
    public java.util.Map<String, String> mapaVariables(@org.springframework.web.bind.annotation.PathVariable java.util.Map<String, String> variables) {
        // TODO extra: devuelve el Map recibido con todas las variables de ruta dinámicas capturadas.
        return null;
    }

    /**
     * Reto Extra 9: Lista de valores separados por comas.
     */
    // GUÍA: teoría 5.2 — un único segmento "10,20,30" puede mapearse a List<Long>:
    //   Spring parte por comas y convierte cada trozo al tipo del genérico.
    // 1. Anota con @GetMapping("/batch/{ids}").
    // 2. El parámetro ya es List<Long> ids.
    // 3. El cuerpo ya está: return ids.size();
    // OJO: el test pega a /api/batch/10,20,30,40 y espera "4" (el TAMAÑO de la
    //   lista, no la suma). Cada elemento se parsea a Long automáticamente.
    // TODO extra: anota con @GetMapping("/batch/{ids}") y devuelve ids.size().
    public int listaVariables(@org.springframework.web.bind.annotation.PathVariable java.util.List<Long> ids) {
        // TODO extra: devuelve el tamaño de la lista de ids recibida.
        return 0;
    }

    /**
     * Reto Extra 10: Captura de variables de matriz.
     */
    // GUÍA: teoría 5.2 (avanzado) — las "matrix variables" son pares clave=valor
    //   incrustados en un segmento con ';' (renault;color=rojo;anio=2020).
    // 1. Anota con @GetMapping("/coches/{modelo}"): el segmento entero (con los ';')
    //    es la variable 'modelo'; las @MatrixVariable extraen color y anio de él.
    // 2. Los parámetros ya están anotados con @MatrixVariable(pathVar="modelo").
    // 3. El cuerpo ya está: return "modelo:" + modelo + ", color:" + color + ", anio:" + anio;
    // OJO: el test DESACTIVA el borrado de ';' (setRemoveSemicolonContent(false))
    //   con un MockMvc propio; por defecto Spring las descarta. Espera exactamente
    //   "modelo:renault, color:rojo, anio:2020".
    // TODO extra: anota con @GetMapping("/coches/{modelo}").
    public String matrizVariables(
            @org.springframework.web.bind.annotation.PathVariable String modelo,
            @org.springframework.web.bind.annotation.MatrixVariable(name = "color", pathVar = "modelo") String color,
            @org.springframework.web.bind.annotation.MatrixVariable(name = "anio", pathVar = "modelo") String anio) {
        // TODO extra: devuelve la cadena concatenando "modelo:" + modelo + ", color:" + color + ", anio:" + anio.
        return null;
    }

}
