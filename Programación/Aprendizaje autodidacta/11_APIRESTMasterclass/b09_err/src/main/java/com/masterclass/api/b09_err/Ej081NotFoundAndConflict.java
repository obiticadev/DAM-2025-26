package com.masterclass.api.b09_err;

import org.springframework.http.ResponseEntity;

/**
 * Ejercicio 081 · 404 vs 409 semánticos.
 *
 * <p>Teoría: {@code teoria/09_Manejo_de_Errores.md} (sección 9.3).
 *
 * <p>El test: crear "ana" -> 201; crear "ana" otra vez -> 409; leer "zoe" -> 404.
 */
// TODO 1: anota la clase con @RestController y @RequestMapping("/api/users").
public class Ej081NotFoundAndConflict {

    private final java.util.Set<String> usuarios = java.util.concurrent.ConcurrentHashMap.newKeySet();

    /**
     * @param nombre nombre del usuario a crear
     * @return 201 si se creó; 409 si ya existía
     */
    // TODO 2: anota con @PostMapping("/{nombre}") y 'nombre' con @PathVariable.
    public ResponseEntity<String> crear(String nombre) {
        // TODO 3: intenta añadir 'nombre' al set 'usuarios'.
        // TODO 4: si add() devuelve false (ya existía) -> 409 Conflict con body "duplicado".
        // TODO 5: si se añadió -> 201 Created con body "creado".
        // TODO 6: usa ResponseEntity.status(HttpStatus.CONFLICT) / CREATED según el caso.
        return null;
    }

    /**
     * @param nombre usuario a consultar
     * @return 200 si existe; 404 si no
     */
    // TODO 7: anota con @GetMapping("/{nombre}") y 'nombre' con @PathVariable.
    public ResponseEntity<String> obtener(String nombre) {
        // TODO 8: si 'usuarios' contiene el nombre -> 200 ok con el nombre.
        // TODO 9: si no -> 404 Not Found (ResponseEntity.notFound().build()).
        // TODO 10: 404 = no existe; 409 = existe pero choca. No los confundas.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new Ej081NotFoundAndConflict().crear("ana"));
    }

        /**
     * RETO EXTRA 01: Verifica correspondencia de rutas de recursos secundarios.
     */
    public static boolean esRecursoAsociado(String path, Long parentId) {
        // GUÍA: comprueba que la ruta de un subrecurso pertenece a un padre dado.
        // 1. path o parentId null → false.
        // 2. La ruta debe contener "/<parentId>/" (el id del padre como segmento).
        //    PISTA: return path != null && path.contains("/" + parentId + "/");
        // OJO: el test pasa ("/users/1/posts/3", 1L) → true: contiene "/1/".
        // CULTURA: son las rutas anidadas tipo /users/1/posts/3 ("los posts del
        // usuario 1"), patrón REST que verás formalizado en b05/b10.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRecursoAsociado");
    }

    /**
     * RETO EXTRA 02: Determina si el error apunta a choque de claves primarias.
     */
    public static boolean esMensajeConflictoIdentificadores(String msg) {
        // GUÍA: detecta un mensaje de choque de identificadores (→ 409, 9.5).
        // 1. null → false.
        // 2. El criterio que cubre el test: el mensaje habla de un id que "ya
        //    existe".
        //    PISTA: return msg != null && msg.toLowerCase().contains("ya existe");
        // OJO: el test pasa "id ya existe" → true. Reutiliza la idea de
        // esErrorDuplicidad (Ej080 reto 10): ambos detectan el 409 por texto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMensajeConflictoIdentificadores");
    }

    /**
     * RETO EXTRA 03: Genera una representacion clave-valor canonica.
     */
    public static String crearClaveDeRecurso(String tipo, Long id) {
        // GUÍA: una línea — return tipo + ":" + id;
        // OJO: separador ":" sin espacios. El test pasa ("TIPO", 123L) y espera
        // "TIPO:123". Misma forma que crearParClaveValor (Ej080 reto 4).
        // CULTURA: esta clave "Tipo:id" es como cachés y mensajes identifican un
        // recurso único (lo verás en b17/caché).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearClaveDeRecurso");
    }

    /**
     * RETO EXTRA 04: Valida que el path comience con prefijo /api.
     */
    public static boolean esRutaControllerValida(String path) {
        // GUÍA: una línea — return path != null && path.startsWith("/api");
        // OJO: el test pasa "/api/v1/test" → true. Solo comprueba el prefijo, no
        // la ruta completa. Es el @RequestMapping("/api") del controller hecho
        // validación de String.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRutaControllerValida");
    }

    /**
     * RETO EXTRA 05: Determina el nombre del recurso a partir de la URL.
     */
    public static String extraerRecursoDeRuta(String path) {
        // GUÍA: extrae el nombre del recurso de una ruta /api/<recurso>/<id>.
        // 1. null → "" (defensa).
        // 2. Parte por "/" y coge el segmento del recurso. Para "/api/users/5",
        //    split("/") da ["", "api", "users", "5"] → el recurso es el índice 2.
        //    PISTA: String[] p = path.split("/"); return p[2];
        // OJO: el primer elemento de split es "" porque la ruta empieza por "/".
        // El test pasa "/api/users/5" y espera EXACTAMENTE "users".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerRecursoDeRuta");
    }

    /**
     * RETO EXTRA 06: Indica si la excepcion proviene del stack de hibernate direct.
     */
    public static boolean esExcepcionDePersistenciaDirecta(Throwable t) {
        // GUÍA: detecta una excepción del stack de persistencia JPA/Hibernate.
        // 1. null → false.
        // 2. La familia común es jakarta.persistence.PersistenceException
        //    (de ella cuelga EntityNotFoundException):
        //       return t instanceof jakarta.persistence.PersistenceException;
        // OJO: el test manda jakarta.persistence.EntityNotFoundException → true.
        // No uses java.sql.SQLException aquí: eso es JDBC crudo (Ej077 reto 6),
        // esto es la capa JPA (b12). EntityNotFoundException de JPA suele traducir
        // a 404; verás esa traducción en Ej083.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionDePersistenciaDirecta");
    }

    /**
     * RETO EXTRA 07: Genera un payload compacto de colision.
     */
    public static String crearJsonConflicto(String msg, String id) {
        // GUÍA: monta un JSON compacto de conflicto con el mensaje y el id.
        // PISTA: return "{\"error\":\"" + msg + "\",\"id\":\"" + id + "\"}";
        // OJO: el test solo exige que el resultado .contains("err") cuando
        // msg="err"; el formato exacto es libre. (Defensa: msg/id null → "".)
        // Este es el cuerpo de un 409 (9.5), versión a mano del ProblemDetail.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearJsonConflicto");
    }

    /**
     * RETO EXTRA 08: Determina si se detecta colision por concurrencia optimista.
     */
    public static boolean esErrorDeVersionJpa(Throwable t) {
        // GUÍA: detecta una colisión de concurrencia optimista (también → 409).
        // 1. null → false.
        // 2. El tipo JPA es jakarta.persistence.OptimisticLockException:
        //       return t instanceof jakarta.persistence.OptimisticLockException;
        // OJO: el test manda esa misma excepción → true.
        // CULTURA: ocurre cuando dos transacciones editan la MISMA fila a la vez
        // (control por columna @Version). Lo estudiarás a fondo en b14 (JPA
        // avanzado); aquí solo lo clasificas como conflicto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esErrorDeVersionJpa");
    }

    /**
     * RETO EXTRA 09: Evalua si la operacion no es valida en el estado actual.
     */
    public static boolean esConflictoLogico(String estadoActual, String operacion) {
        // GUÍA: ¿la operación choca con el estado actual del recurso? (el otro
        // tipo de 409, el "conflicto lógico" de 9.5).
        // 1. Define las transiciones prohibidas. La mínima que pide el test: no se
        //    puede EDITAR algo CERRADO.
        //    PISTA: return "CERRADO".equals(estadoActual) && "EDITAR".equals(operacion);
        // OJO: el test pasa ("CERRADO", "EDITAR") → true. Puedes ampliar con más
        // reglas (no BORRAR un PAGADO, etc.), pero esa pareja debe dar true.
        // CULTURA: "no puedes editar un pedido ya cerrado" es 409, no 404 ni 400:
        // el recurso existe y los datos son válidos, pero el estado no lo permite.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esConflictoLogico");
    }

    /**
     * RETO EXTRA 10: Crea la explicacion estandar de no hallado.
     */
    public static String construirMensajeNotFound(String recurso, String clave) {
        // GUÍA: el mensaje estándar de un 404 (el detail del ProblemDetail, 9.2).
        // PISTA: return "recurso '" + recurso + "' no hallado con clave '" + clave + "'";
        // OJO: el test compara con equals EXACTO:
        //    "recurso 'user' no hallado con clave '5'"
        // Cuida cada espacio, las comillas simples y las palabras "no hallado con
        // clave". Es el caso más estricto del bloque: cópialo carácter a carácter.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para construirMensajeNotFound");
    }

}