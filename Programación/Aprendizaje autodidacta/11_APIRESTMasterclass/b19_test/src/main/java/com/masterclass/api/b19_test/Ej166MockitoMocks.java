package com.masterclass.api.b19_test;

/**
 * Ejercicio 166 · Dobles de prueba: stub manual (concepto de Mockito).
 *
 * <p>Teoría: {@code teoria/19_Testing_APIs.md} (sección 19.3).
 *
 * <p>Mockito haría {@code when(repo.buscar(1)).thenReturn("Ada")}. Aquí
 * modelamos ese concepto con un stub PURO: {@link RepositorioStub166}
 * devuelve respuestas preconfiguradas. La pieza bajo prueba es el
 * servicio que lo consume.
 */
public final class Ej166MockitoMocks {

    private Ej166MockitoMocks() {
    }

    /**
     * Saluda al usuario cuyo id se resuelve contra un repositorio (stub).
     *
     * @param repo repositorio (en test será un stub) no null
     * @param id   id de usuario a resolver
     * @return "Hola, &lt;nombre&gt;" o "Hola, desconocido" si no existe
     * @throws IllegalArgumentException si repo es null
     */
    public static String saludar(RepositorioStub166 repo, int id) {
        // TODO 1: si repo es null -> IllegalArgumentException.
        // TODO 2: invoca repo.buscarNombre(id) (esto es la "interacción" mockeada).
        // TODO 3: si el resultado es null -> trata como usuario no encontrado.
        // TODO 4: si el resultado es blank -> trata como no encontrado también.
        // TODO 5: para no encontrado devuelve "Hola, desconocido".
        // TODO 6: para encontrado devuelve "Hola, " + nombre.
        // TODO 7: no asumas que el repo accede a BD real: es un doble de prueba.
        // TODO 8: no captures excepciones del repo: deja que propaguen.
        // TODO 9: la lógica de saludo es pura; el stub aísla la dependencia.
        // TODO 10: devuelve la cadena de saludo.
        return null;
    }

    public static void main(String[] args) {
        RepositorioStub166 stub = unused -> "Ada";
        System.out.println(saludar(stub, 1));
    }

        /**
     * RETO EXTRA 01: Obtiene longitud del nombre.
     */
    public static int longitudNombre(RepositorioStub166 repo, int id) {
        // GUÍA: teoría 19.3 — consume el stub y deriva de su respuesta.
        // 1. Pide el nombre al doble: repo.buscarNombre(id).
        // 2. Devuelve su longitud: return repo.buscarNombre(id).length();
        // El test pasa el stub id -> "Ada" y espera 3. Igual que en saludar,
        // el método no sabe que el repo es un doble: solo usa su contrato.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para longitudNombre");
    }

    /**
     * RETO EXTRA 02: Obtiene el nombre en mayusculas.
     */
    public static String nombreEnMayusculas(RepositorioStub166 repo, int id) {
        // GUÍA: teoría 19.3 — una línea: return repo.buscarNombre(id).toUpperCase();
        // El test (id -> "Ada") espera "ADA".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreEnMayusculas");
    }

    /**
     * RETO EXTRA 03: Determina si el nombre es valido.
     */
    public static boolean nombreValido(RepositorioStub166 repo, int id) {
        // GUÍA: teoría 19.3 — "válido" = el stub devolvió algo usable.
        // 1. Lee el nombre: String n = repo.buscarNombre(id);
        // 2. Es válido si no es null y no está en blanco:
        //       return n != null && !n.isBlank();
        // El test (id -> "Ada") espera true. El orden importa: comprueba null
        // ANTES de isBlank(), o n.isBlank() lanzaría NPE si n fuera null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreValido");
    }

    /**
     * RETO EXTRA 04: Saluda al usuario con un sufijo.
     */
    public static String saludoConSufijo(RepositorioStub166 repo, int id, String suf) {
        // GUÍA: teoría 19.3 — compón el saludo con el sufijo.
        // return "Hola " + repo.buscarNombre(id) + suf;
        // ⚠ CUIDADO con el formato EXACTO: el test espera "Hola Ada!" — es
        // "Hola " (con espacio, SIN coma) + nombre + sufijo. No reutilices el
        // "Hola, " (con coma) del método saludar: aquí la coma rompería el test.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para saludoConSufijo");
    }

    /**
     * RETO EXTRA 05: Comprueba si el nombre contiene una subcadena.
     */
    public static boolean contieneSubcadena(RepositorioStub166 repo, int id, String sub) {
        // GUÍA: teoría 19.3 — una línea: return repo.buscarNombre(id).contains(sub);
        // El test ("Ada", "Ad") espera true. String.contains es sensible a
        // mayúsculas: "Ad" sí está en "Ada", "ad" no.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneSubcadena");
    }

    /**
     * RETO EXTRA 06: Determina si el usuario es un invitado.
     */
    public static boolean esInvitado(RepositorioStub166 repo, int id) {
        // GUÍA: teoría 19.3 — es invitado si el nombre devuelto es "invitado".
        // return "invitado".equalsIgnoreCase(repo.buscarNombre(id));
        // El test (id -> "invitado") espera true. PISTA: invoca equals SOBRE el
        // literal "invitado" (no sobre el resultado del repo): así, si el repo
        // devuelve null, no hay NPE — "invitado".equals(null) es false, no error.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esInvitado");
    }

    /**
     * RETO EXTRA 07: Devuelve el nombre invertido.
     */
    public static String saludoInverso(RepositorioStub166 repo, int id) {
        // GUÍA: teoría 19.3 — invierte la cadena con StringBuilder.
        // return new StringBuilder(repo.buscarNombre(id)).reverse().toString();
        // El test ("Ada") espera "adA" (mayúsculas/minúsculas se conservan, solo
        // cambia el orden). PISTA: StringBuilder.reverse() invierte in situ.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para saludoInverso");
    }

    /**
     * RETO EXTRA 08: Concatena el ID y el nombre.
     */
    public static String concatenarIdYNombre(RepositorioStub166 repo, int id) {
        // GUÍA: teoría 19.3 — formato "id:nombre".
        // return id + ":" + repo.buscarNombre(id);
        // El test (id=1, "Ada") espera "1:Ada". El separador es ":" sin espacios.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para concatenarIdYNombre");
    }

    /**
     * RETO EXTRA 09: Comprueba si el nombre es largo.
     */
    public static boolean nombreLargo(RepositorioStub166 repo, int id) {
        // GUÍA: teoría 19.3 — "largo" = más de 3 caracteres.
        // return repo.buscarNombre(id).length() > 3;
        // ⚠ CUIDADO con el umbral: el test (id -> "Ada") espera FALSE, y "Ada"
        // tiene 3 letras. Por tanto el corte es > 3 ESTRICTO (3 no es largo).
        // Si pusieras >= 3, el test fallaría.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreLargo");
    }

    /**
     * RETO EXTRA 10: Busca el nombre o lanza excepcion.
     */
    public static String buscarOError(RepositorioStub166 repo, int id) {
        // GUÍA: teoría 19.3 + patrón "si no está → error" (19.4).
        // 1. Lee el nombre: String n = repo.buscarNombre(id);
        // 2. Si es null -> lanza: if (n == null) throw new IllegalArgumentException("no existe");
        // 3. Si no, devuélvelo: return n;
        // El test pasa el stub id -> null y espera IllegalArgumentException.
        // CULTURA: este es el "findById().orElseThrow()" que verás en Spring,
        // donde la excepción se convierte luego en un 404.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para buscarOError");
    }

}

/**
 * Doble de prueba (stub) puro: sustituye un repositorio real.
 */
@FunctionalInterface
interface RepositorioStub166 {
    /** @param id id a resolver @return nombre o null si no existe */
    String buscarNombre(int id);
}
