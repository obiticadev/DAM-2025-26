package com.masterclass.api.b03_core;

/**
 * Ejercicio 031 · Inyección por constructor.
 *
 * <p>Teoría: {@code teoria/03_Spring_Core_IoC_DI.md} (sección 3.2).
 *
 * <p>El servicio NO debe crear su repositorio con {@code new}: lo recibe ya
 * construido. Eso es DI, y es lo que hace testeable el código.
 */
public final class Ej031ConstructorInjection {

    /** Dependencia: fuente de datos simulada. */
    public interface RepoSaludos {
        String plantilla();
    }

    /** Servicio que depende de RepoSaludos por constructor. */
    public static class ServicioSaludo {
        private final RepoSaludos repo;

        /**
         * @param repo dependencia inyectada (no se instancia aquí)
         */
        public ServicioSaludo(RepoSaludos repo) {
            // TODO 1: NO hagas 'new' de ninguna implementación de RepoSaludos aquí.
            // TODO 2: valida que 'repo' no sea null (fail-fast de dependencia).
            // TODO 3: asigna la dependencia recibida al campo final 'this.repo'.
            this.repo = null;
        }

        /**
         * @param nombre nombre a saludar
         * @return la plantilla del repo con el nombre sustituido donde ponga "{}"
         */
        public String saludar(String nombre) {
            // TODO 4: obtén la plantilla llamando a repo.plantilla().
            // TODO 5: localiza el marcador "{}" dentro de la plantilla.
            // TODO 6: reemplaza ese marcador por 'nombre'.
            // TODO 7: si la plantilla no contiene "{}", devuélvela tal cual (caso límite).
            // TODO 8: si 'nombre' es null, trátalo como cadena vacía (defensa).
            // TODO 9: usa replace (no concatenación frágil) para la sustitución.
            // TODO 10: devuelve el saludo final ya construido.
            return "";
        }
    }

    private Ej031ConstructorInjection() {
    }

    public static void main(String[] args) {
        var s = new ServicioSaludo(() -> "Hola, {}!");
        System.out.println(s.saludar("Ana"));
    }
}
