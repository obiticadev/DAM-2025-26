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
}

/**
 * Doble de prueba (stub) puro: sustituye un repositorio real.
 */
@FunctionalInterface
interface RepositorioStub166 {
    /** @param id id a resolver @return nombre o null si no existe */
    String buscarNombre(int id);
}
