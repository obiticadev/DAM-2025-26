package com.masterclass.api.b03_core;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        private final List<RepoSaludos> repos;   // colección de dependencias (teoría 3.3)

        public ServicioConMultiplesRepos(List<RepoSaludos> repos) {
            this.repos = repos;
        }

        public String saludarTodos() {
            // GUÍA: teoría 1.4 + 3.3 (inyectar una List de beans del mismo tipo y reducirla).
            // 1. Stream de 'repos', map(RepoSaludos::plantilla) para sacar cada texto.
            // 2. collect(Collectors.joining(", ")) para unirlos con coma + espacio.
            // PISTA: repos.stream().map(RepoSaludos::plantilla).collect(Collectors.joining(", "))
            // OJO: el test inyecta r1=()->"Hola", r2=()->"Mundo" y espera EXACTAMENTE
            //   "Hola, Mundo" (coma y UN espacio). CULTURA: inyectar List<T> es el patrón
            //   "estrategias" de Spring (todas las implementaciones de una interfaz).
            throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para saludarTodos");
        }
    }

    /**
     * Reto Extra 2: Servicio con inyección de dependencia opcional.
     * Si no se inyecta ningún repo, utiliza un fallback predeterminado que retorne "Default".
     */
    public static class ServicioConDependenciaOpcional {
        private final Optional<RepoSaludos> repo;

        public ServicioConDependenciaOpcional(Optional<RepoSaludos> repo) {
            this.repo = repo;
        }

        public String obtenerPlantilla() {
            // GUÍA: teoría 1.2 + 3.3 (dependencia opcional con fallback).
            // 1. Si el Optional tiene valor -> devuelve repo.get().plantilla().
            // 2. Si está vacío -> devuelve "Default".
            // PISTA, una línea: return repo.map(RepoSaludos::plantilla).orElse("Default");
            // OJO: el test prueba Optional.of(()->"Hola") -> "Hola" y Optional.empty() ->
            //   "Default" (literal exacto). NO uses isPresent()+get(): encadena map/orElse.
            throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerPlantilla");
        }
    }

    /**
     * Reto Extra 3: Servicio que inyecta dependencias de negocio junto a configuraciones constantes.
     * Si el saludo excede el límite de longitud indicado, lo recorta y añade "...".
     */
    public static class ServicioConConfiguracion {
        private final RepoSaludos repo;
        private final int limite;

        public ServicioConConfiguracion(RepoSaludos repo, int limite) {
            this.repo = repo;
            this.limite = limite;
        }

        public String saludarConLimite(String nombre) {
            // GUÍA: teoría 3.3 (dependencia de negocio + constante de configuración inyectadas).
            // 1. Construye el saludo: repo.plantilla() con "{}" reemplazado por 'nombre'.
            // 2. Si su longitud <= 'limite' -> devuélvelo tal cual.
            // 3. Si excede el límite -> recorta a 'limite' caracteres y añade "...":
            //    saludo.substring(0, limite) + "..."
            // OJO: el test usa plantilla "Saludo: {}", limite=10, nombre="Ana" -> "Saludo: Ana"
            //   (longitud 11 > 10) -> "Saludo: An" + "..." = "Saludo: An..." (13). Comprueba
            //   endsWith("...") y length <= 13 (= limite + 3). CUIDADO con el substring: el
            //   índice final es 'limite', no length.
            throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para saludarConLimite");
        }
    }

    /**
     * Reto Extra 4: Detecta si existe una dependencia circular directa por constructor
     * entre dos clases simulando de forma reflectiva lo que hace Spring.
     */
    public static boolean detectarInyeccionCircular(Class<?> claseA, Class<?> claseB) {
        // GUÍA: teoría 3.3 (ciclo por constructor: A pide B y B pide A -> irresoluble).
        // 1. Mira los parámetros del constructor de A: claseA.getDeclaredConstructors()[0]
        //    .getParameterTypes() ¿contiene claseB?
        // 2. Haz lo simétrico con B: ¿el constructor de claseB recibe claseA?
        // 3. Devuelve true SOLO si AMBOS se piden mutuamente.
        // PISTA: java.util.Arrays.asList(ctor.getParameterTypes()).contains(otraClase).
        // OJO: el test pasa CircularA(CircularB)+CircularB(CircularA) -> true, y
        //   CircularA + NoCircular(String) -> false (NoCircular no recibe CircularA).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para detectarInyeccionCircular");
    }

    /**
     * Reto Extra 5: Servicio compuesto que delega lógicas de negocio en múltiples servicios de nivel inferior.
     */
    public static class ServicioComposicion {
        private final ServicioSaludo primero;
        private final ServicioSaludo segundo;

        public ServicioComposicion(ServicioSaludo primero, ServicioSaludo segundo) {
            this.primero = primero;
            this.segundo = segundo;
        }

        public String saludarCompuesto(String nombre) {
            // GUÍA: teoría 3.3 (un servicio que ORQUESTA otros dos servicios inyectados).
            // 1. Llama a primero.saludar(nombre) y a segundo.saludar(nombre).
            // 2. Únelos con ". " (punto + espacio) en medio.
            // PISTA: return primero.saludar(nombre) + ". " + segundo.saludar(nombre);
            // OJO: con s1="Buenos días, {}" y s2="¡Que tengas un buen día, {}!" y nombre="Carlos"
            //   el test espera "Buenos días, Carlos. ¡Que tengas un buen día, Carlos!".
            //   Reutiliza ServicioSaludo.saludar (el del ejercicio base); no reimplementes el {}.
            throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para saludarCompuesto");
        }
    }

    /**
     * Reto Extra 6: Simulación pedagógica de inicialización perezosa de dependencias utilizando patrones de diseño proxy o lazy suppliers.
     */
    public static class ServicioLazyProxy {
        private final java.util.function.Supplier<RepoSaludos> proveedor;

        public ServicioLazyProxy(java.util.function.Supplier<RepoSaludos> proveedor) {
            // OJO: aquí SOLO guardas el Supplier; NO lo invoques (no llames proveedor.get()).
            this.proveedor = proveedor;
        }

        public String obtenerPlantillaDiferida() {
            // GUÍA: teoría 3.5 (inicialización perezosa: la dependencia se crea al USARLA).
            // 1. Ahora SÍ resuelves la dependencia: proveedor.get() devuelve el RepoSaludos.
            // 2. Devuelve su plantilla(): return proveedor.get().plantilla();
            // OJO: el test cuenta cuántas veces se evalúa el Supplier: 0 tras construir (no lo
            //   tocas en el constructor) y 1 tras la PRIMERA llamada a este método. Si lo
            //   resuelves en el constructor, el primer assertEquals(0, counter) falla.
            throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerPlantillaDiferida");
        }
    }

    /**
     * Reto Extra 7: Servicio decorador que añade un prefijo/sufijo a los resultados de otro RepoSaludos sin mutar su lógica interna.
     */
    public static class ServicioDecorador implements RepoSaludos {
        private final RepoSaludos delegado;
        private final String prefijo;
        private final String sufijo;

        public ServicioDecorador(RepoSaludos delegado, String prefijo, String sufijo) {
            this.delegado = delegado;
            this.prefijo = prefijo;
            this.sufijo = sufijo;
        }

        @Override
        public String plantilla() {
            // GUÍA: teoría 3.3 (patrón Decorador: implementa la MISMA interfaz, envuelve a otra
            // instancia y añade comportamiento sin tocarla).
            // 1. Pide la plantilla original: delegado.plantilla().
            // 2. Envuélvela: return prefijo + delegado.plantilla() + sufijo;
            // OJO: con delegado=()->"Hola {}", prefijo="[", sufijo="]" el test espera
            //   "[Hola {}]". El decorador NO modifica el delegado: solo rodea su resultado.
            throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para plantilla");
        }
    }

    /**
     * Reto Extra 8: Inyecta una factoría de dependencias para delegar la creación de repositorios en caliente basados en una clave string.
     */
    public static class ServicioConFactory {
        private final Map<String, RepoSaludos> factory;

        public ServicioConFactory(Map<String, RepoSaludos> factory) {
            this.factory = factory;
        }

        public String saludarCon(String clave, String nombre) {
            // GUÍA: teoría 3.3 (factoría por clave: eliges la dependencia en caliente).
            // 1. Recupera el repo: factory.get(clave) (asume que existe para el test).
            // 2. Toma su plantilla() y sustituye "{}" por 'nombre' (como hace ServicioSaludo).
            // PISTA: factory.get(clave).plantilla().replace("{}", nombre)
            // OJO: factory{"formal"->"Estimado {},", "casual"->"¡Qué pasa, {}!"}; el test espera
            //   saludarCon("formal","Juan") -> "Estimado Juan," y saludarCon("casual","Pepe")
            //   -> "¡Qué pasa, Pepe!". Conserva la coma final de "Estimado {},".
            throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para saludarCon");
        }
    }

    /**
     * Reto Extra 9: Comprobación de seguridad reflectiva. Valida si la clase tiene
     * todos sus campos inyectados marcados como final (inmutabilidad).
     */
    public static boolean verificarInyeccionSegura(Class<?> clase) {
        // GUÍA: teoría 3.3 (por qué la inyección por constructor usa campos final).
        // 1. Recorre clase.getDeclaredFields().
        // 2. Para cada campo comprueba si es final:
        //    java.lang.reflect.Modifier.isFinal(campo.getModifiers()).
        // 3. Devuelve true SOLO si TODOS son final (un solo campo no-final -> false).
        // PISTA: java.util.Arrays.stream(clase.getDeclaredFields())
        //          .allMatch(f -> Modifier.isFinal(f.getModifiers()));
        // OJO: el test pasa ClaseInmutable (campo final -> true) y ClaseMutable (campo no
        //   final -> false). allMatch sobre 0 campos devolvería true, pero ambos test tienen
        //   exactamente un campo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para verificarInyeccionSegura");
    }

    /**
     * Reto Extra 10: Inyecta un listado de repositorios y los filtra por un prefijo para devolver el saludo del repositorio coincidente.
     */
    public static class ServicioConFiltro {
        private final List<RepoSaludos> repos;

        public ServicioConFiltro(List<RepoSaludos> repos) {
            this.repos = repos;
        }

        public String saludarConPrefijo(String prefijo) {
            // GUÍA: teoría 1.3 + 3.3 (filtrar la lista inyectada y quedarte con el que casa).
            // 1. Stream de 'repos', filter por el que su plantilla() empiece por 'prefijo':
            //    .filter(r -> r.plantilla().startsWith(prefijo))
            // 2. findFirst() (devuelve Optional) y .map(RepoSaludos::plantilla).orElse(...) o
            //    .orElseThrow() si no hay coincidencia.
            // OJO: con r1=()->"ES_Hola", r2=()->"EN_Hello" el test espera
            //   saludarConPrefijo("ES_") -> "ES_Hola" y saludarConPrefijo("EN_") -> "EN_Hello".
            //   Devuelve la plantilla COMPLETA del repo que casa, no solo el prefijo.
            throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para saludarConPrefijo");
        }
    }

}
