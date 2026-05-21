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

    // --- MÉTODOS Y CLASES DE RETOS EXTRA ---

    /**
     * Reto Extra 1: Servicio que inyecta múltiples repositorios de saludos.
     * Concatena la plantilla de todos ellos separados por una coma y un espacio.
     */
    public static class ServicioConMultiplesRepos {
        private final java.util.List<RepoSaludos> repos;

        public ServicioConMultiplesRepos(java.util.List<RepoSaludos> repos) {
            // TODO extra (Reto 1): Guarda la colección de repositorios.
            this.repos = repos;
        }

        public String saludarTodos() {
            // TODO extra (Reto 1): Une las plantillas de todos los repositorios.
            return "";
        }
    }

    /**
     * Reto Extra 2: Servicio con inyección de dependencia opcional.
     * Si no se inyecta ningún repo, utiliza un fallback predeterminado que retorne "Default".
     */
    public static class ServicioConDependenciaOpcional {
        private final java.util.Optional<RepoSaludos> repoOpt;

        public ServicioConDependenciaOpcional(java.util.Optional<RepoSaludos> repoOpt) {
            // TODO extra (Reto 2): Asigna el repositorio opcional.
            this.repoOpt = repoOpt;
        }

        public String obtenerPlantilla() {
            // TODO extra (Reto 2): Devuelve la plantilla del repo o la de fallback "Default".
            return "";
        }
    }

    /**
     * Reto Extra 3: Servicio que inyecta dependencias de negocio junto a configuraciones constantes.
     * Si el saludo excede el límite de longitud indicado, lo recorta y añade "...".
     */
    public static class ServicioConConfiguracion {
        private final RepoSaludos repo;
        private final int limiteLongitud;

        public ServicioConConfiguracion(RepoSaludos repo, int limiteLongitud) {
            // TODO extra (Reto 3): Inyecta el repositorio y la configuración de límite.
            this.repo = repo;
            this.limiteLongitud = limiteLongitud;
        }

        public String saludarConLimite(String nombre) {
            // TODO extra (Reto 3): Genera el saludo y recórtalo si excede el límite.
            return "";
        }
    }

    /**
     * Reto Extra 4: Detecta si existe una dependencia circular directa por constructor
     * entre dos clases simulando de forma reflectiva lo que hace Spring.
     */
    public static boolean detectarInyeccionCircular(Class<?> claseA, Class<?> claseB) {
        // TODO extra (Reto 4): Analiza reflectivamente los constructores de claseA y claseB.
        // Si claseA requiere a claseB en su constructor y viceversa, retorna true.
        return false;
    }

    /**
     * Reto Extra 5: Servicio compuesto que delega lógicas de negocio en múltiples servicios de nivel inferior.
     */
    public static class ServicioComposicion {
        private final ServicioSaludo s1;
        private final ServicioSaludo s2;

        public ServicioComposicion(ServicioSaludo s1, ServicioSaludo s2) {
            // TODO extra (Reto 5): Guarda las referencias de ambos servicios.
            this.s1 = s1;
            this.s2 = s2;
        }

        public String saludarCompuesto(String nombre) {
            // TODO extra (Reto 5): Combina la respuesta de ambos servicios en un único String.
            return "";
        }
    }

    /**
     * Reto Extra 6: Simulación pedagógica de inicialización perezosa de dependencias utilizando patrones de diseño proxy o lazy suppliers.
     */
    public static class ServicioLazyProxy {
        private final java.util.function.Supplier<RepoSaludos> lazyRepo;

        public ServicioLazyProxy(java.util.function.Supplier<RepoSaludos> lazyRepo) {
            // TODO extra (Reto 6): Guarda la referencia de inicialización diferida (Supplier).
            this.lazyRepo = lazyRepo;
        }

        public String obtenerPlantillaDiferida() {
            // TODO extra (Reto 6): Invoca el supplier solo cuando sea necesario resolver el repo.
            return "";
        }
    }

    /**
     * Reto Extra 7: Servicio decorador que añade un prefijo/sufijo a los resultados de otro RepoSaludos sin mutar su lógica interna.
     */
    public static class ServicioDecorador implements RepoSaludos {
        private final RepoSaludos original;
        private final String prefijo;
        private final String sufijo;

        public ServicioDecorador(RepoSaludos original, String prefijo, String sufijo) {
            // TODO extra (Reto 7): Recibe el decorado y los metadatos.
            this.original = original;
            this.prefijo = prefijo;
            this.sufijo = sufijo;
        }

        @Override
        public String plantilla() {
            // TODO extra (Reto 7): Retorna la plantilla original envuelta con el prefijo y el sufijo.
            return "";
        }
    }

    /**
     * Reto Extra 8: Inyecta una factoría de dependencias para delegar la creación de repositorios en caliente basados en una clave string.
     */
    public static class ServicioConFactory {
        private final java.util.Map<String, RepoSaludos> factory;

        public ServicioConFactory(java.util.Map<String, RepoSaludos> factory) {
            // TODO extra (Reto 8): Guarda el mapa-factoría de repositorios.
            this.factory = factory;
        }

        public String saludarCon(String clave, String nombre) {
            // TODO extra (Reto 8): Recupera el repo según la clave y genera el saludo.
            return "";
        }
    }

    /**
     * Reto Extra 9: Comprobación de seguridad reflectiva. Valida si la clase tiene
     * todos sus campos inyectados marcados como final (inmutabilidad).
     */
    public static boolean verificarInyeccionSegura(Class<?> clase) {
        // TODO extra (Reto 9): Inspecciona reflectivamente todos los campos declarados de la clase.
        // Si todos los campos no estáticos son final, retorna true.
        return false;
    }

    /**
     * Reto Extra 10: Inyecta un listado de repositorios y los filtra por un prefijo para devolver el saludo del repositorio coincidente.
     */
    public static class ServicioConFiltro {
        private final java.util.List<RepoSaludos> repos;

        public ServicioConFiltro(java.util.List<RepoSaludos> repos) {
            // TODO extra (Reto 10): Guarda la colección de repos.
            this.repos = repos;
        }

        public String saludarConPrefijo(String prefijoEsperado) {
            // TODO extra (Reto 10): Filtra la lista de repos y devuelve la primera plantilla que comience con el prefijo indicado o vacío si no se encuentra.
            return "";
        }
    }

}
