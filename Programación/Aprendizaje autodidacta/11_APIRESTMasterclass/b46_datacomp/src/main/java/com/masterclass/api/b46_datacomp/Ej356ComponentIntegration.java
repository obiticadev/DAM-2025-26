package com.masterclass.api.b46_datacomp;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

/**
 * Ejercicio 356 · Integración del componente en una aplicación: configurar, suscribir, usar.
 *
 * <p>Teoría: {@code teoria/46_Componentes_Datos.md} (sección 6).
 *
 * <p>Cierre del bloque: una app RECIBE un {@link ComponenteDao} (sin saber qué hay debajo), le
 * aplica su {@link Configuracion}, se suscribe a sus eventos y le pide operaciones. Aquí se ve cómo
 * se "enchufa" un componente: inyección, descubrimiento por SPI, factoría, configuración externa y
 * coordinación de varios. Es la diferencia entre tener un componente y SABER integrarlo.
 */
public final class Ej356ComponentIntegration {

    private Ej356ComponentIntegration() {
    }

    /**
     * Integra un componente en la app: valida, le aplica la configuración y registra un oyente.
     *
     * @param comp componente a integrar
     * @param cfg  configuración a aplicarle
     * @return {@code true} si se integró; {@code false} si comp o cfg son null
     */
    public static boolean integrar(ComponenteDao comp, Configuracion cfg) {
        // TODO 1: si comp es null, devuelve false.
        // TODO 2: si cfg es null, devuelve false.
        // TODO 3: aplica la configuración con comp.configurar(cfg).
        // TODO 4: crea un PropertyChangeListener de la app (puede ser uno vacío o que registre un log).
        // TODO 5: suscríbelo con comp.addPropertyChangeListener(...).
        // TODO 6: devuelve true (integración correcta).
        return false;
    }

    /**
     * Usa un componente ya integrado: ejecuta la petición y devuelve el resultado RECOGIDO POR EVENTO.
     *
     * @param comp     componente a usar
     * @param peticion petición a ejecutar
     * @return el valor del evento {@code "resultado"}, o {@code null} si comp/peticion son null
     */
    public static String usar(ComponenteDao comp, String peticion) {
        // TODO 7: si comp o peticion son null, devuelve null.
        // TODO 8: prepara un String[1] para capturar y registra un oyente que, cuando el evento sea
        //         "resultado", guarde (String) e.getNewValue() en esa casilla.
        // TODO 9: invoca comp.ejecutar(peticion) (eso disparará el evento "resultado").
        // TODO 10: devuelve lo capturado por el evento (no el valor de retorno de ejecutar): así
        //          demuestras que la app recoge el resultado por la vía de los EVENTOS.
        return null;
    }

    public static void main(String[] args) {
        ComponenteEco comp = new ComponenteEco();
        System.out.println(integrar(comp, new Configuracion("jdbc:h2:mem", "sa", 10)));
        System.out.println(usar(comp, "hola")); // eco:hola
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: crea un componente inyectándole la configuración por el CONSTRUCTOR (IoC, b03).
     */
    public static ComponenteDao inyectarPorConstructor(Configuracion cfg) {
        // GUÍA: teoría 6 (inyección de dependencias: la config entra por el constructor, no se
        //   cablea dentro). Enlaza con b03 (IoC/DI).
        // 1. devuelve new ComponenteEco(cfg).
        // PISTA: return new ComponenteEco(cfg);
        // OJO: el test castea a ComponenteEco y comprueba getConfiguracion() == cfg.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para inyectarPorConstructor");
    }

    /**
     * Reto Extra 2: factoría que crea un componente según un nombre de tipo.
     */
    public static ComponenteDao fabricarComponente(String tipo) {
        // GUÍA: teoría 6 (patrón Factory: decidir la implementación por un nombre).
        // 1. "eco" -> new ComponenteEco(); cualquier otro -> null.
        // PISTA: return "eco".equals(tipo) ? new ComponenteEco() : null;
        // OJO: el test pide fabricarComponente("eco") != null y fabricarComponente("desconocido") == null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para fabricarComponente");
    }

    /**
     * Reto Extra 3: construye una {@link Configuracion} desde un {@link Properties} externo (b04).
     */
    public static Configuracion configuracionDesdeProperties(Properties props) {
        // GUÍA: teoría 6 (la configuración viene de fuera: un .properties, variables de entorno...).
        //   Enlaza con b04 (configuración externalizada).
        // 1. lee props.getProperty("url"), "usuario" y "tamanoPagina".
        // 2. parsea el tamaño a int (si falta, usa 10 por defecto).
        // 3. devuelve new Configuracion(url, usuario, tamano).
        // PISTA: int t = Integer.parseInt(props.getProperty("tamanoPagina", "10"));
        // OJO: el test pasa un Properties con url y tamanoPagina="20" y comprueba url() y tamanoPagina()==20.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para configuracionDesdeProperties");
    }

    /**
     * Reto Extra 4: coordina VARIOS componentes mandándoles la misma petición; junta los resultados.
     */
    public static List<String> coordinarComponentes(List<ComponenteDao> comps, String peticion) {
        // GUÍA: teoría 6 (una app puede orquestar varios componentes a la vez).
        // 1. null -> List.of().
        // 2. por cada componente, ejecuta la petición y recoge su resultado en una lista.
        // PISTA: comps.stream().map(c -> c.ejecutar(peticion)).toList();
        // OJO: el test pasa dos ComponenteEco y "hola" -> ["eco:hola","eco:hola"] (tamaño 2).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para coordinarComponentes");
    }

    /**
     * Reto Extra 5: simula el ciclo de vida del componente (init -> destroy) y devuelve el log.
     */
    public static String cicloDeVida() {
        // GUÍA: teoría 6 (un componente integrado se inicia y se destruye ordenadamente).
        // 1. devuelve "init,destroy" (primero arranca, luego se libera).
        // PISTA: return "init,destroy";
        // OJO: el test compara exactamente "init,destroy".
        // CULTURA: es @PostConstruct/@PreDestroy de un bean gestionado, o init()/destroy() clásico.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cicloDeVida");
    }

    /**
     * Reto Extra 6: usa un componente SIMULADO (mock) sin instanciar ComponenteEco.
     */
    public static String usarConMock(String peticion) {
        // GUÍA: teoría 6 (en un test de integración a veces se sustituye el componente por un MOCK
        //   con respuesta fija; ComponenteDao es una interfaz, así que basta una lambda/anónima).
        // 1. crea un ComponenteDao "a medida" cuyo ejecutar devuelva "mock:"+peticion
        //    (configurar/addPropertyChangeListener pueden no hacer nada).
        // 2. invoca su ejecutar(peticion) y devuélvelo.
        // PISTA: implementa ComponenteDao con una clase anónima que devuelva "mock:" + peticion.
        // OJO: el test pasa "x" y espera "mock:x". NO uses ComponenteEco aquí (sería "eco:x").
        // CULTURA: esto es lo que hace Mockito (b19) por debajo: una implementación falsa del contrato.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para usarConMock");
    }

    /**
     * Reto Extra 7: propaga el error de un componente recogiéndolo por su evento {@code "error"}.
     */
    public static String propagarError(ComponenteDao comp, String peticion) {
        // GUÍA: teoría 6 (la app se entera de los fallos por el evento "error" del componente).
        // 1. prepara un String[1] para capturar.
        // 2. registra un oyente que, si el evento es "error", guarde (String) e.getNewValue().
        // 3. ejecuta la petición y devuelve lo capturado (null si no hubo error).
        // PISTA: con ComponenteEco, ejecutar("ERROR") dispara el evento "error" con "error:ERROR".
        // OJO: el test usa un ComponenteEco y "ERROR" -> espera "error:ERROR". Con otra petición
        //   no salta "error" y devolverías null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para propagarError");
    }

    /**
     * Reto Extra 8: descubre implementaciones de {@link ComponenteDao} por SPI ({@link ServiceLoader}).
     */
    public static int descubrirConServiceLoader() {
        // GUÍA: teoría 6 (ServiceLoader lee META-INF/services/<interfaz> y crea las implementaciones
        //   declaradas, sin que tú las nombres en el código). El recurso ya existe en el módulo.
        // 1. ServiceLoader<ComponenteDao> sl = ServiceLoader.load(ComponenteDao.class);
        // 2. cuenta cuántos proveedores hay (recorre sl o usa stream().count()).
        // PISTA: return (int) ServiceLoader.load(ComponenteDao.class).stream().count();
        // OJO: el test espera >= 1 (al menos ComponenteEco, declarado en META-INF/services).
        // CULTURA: así carga Java los drivers JDBC (b11) y los proveedores de logging: enchufar
        //   una implementación = añadir un JAR con su fichero de servicio.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para descubrirConServiceLoader");
    }

    /**
     * Reto Extra 9: registra el oyente de la app, usa el componente y cuenta los eventos recibidos.
     */
    public static int registrarYUsar(String peticion) {
        // GUÍA: teoría 6 (integración completa: suscribirse y reaccionar a los eventos al usar).
        // 1. crea un ComponenteEco y un contador.
        // 2. registra un oyente que incremente el contador en cada evento.
        // 3. ejecuta la petición UNA vez y devuelve el contador.
        // PISTA: comp.addPropertyChangeListener(e -> cont[0]++); comp.ejecutar(peticion);
        // OJO: el test usa "hola" -> espera 1 (una ejecución normal dispara un único "resultado").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para registrarYUsar");
    }

    /**
     * Reto Extra 10: integración de extremo a extremo: integra, usa y devuelve el resultado (b24).
     */
    public static String integrarTodo(Configuracion cfg, String peticion) {
        // GUÍA: teoría 6 (el "boss final" de b24: juntar config + integración + uso en un flujo).
        // 1. crea un ComponenteEco.
        // 2. intégralo con integrar(comp, cfg); si devuelve false -> "".
        // 3. úsalo con usar(comp, peticion) y devuelve el resultado.
        // PISTA: if (!integrar(comp, cfg)) return ""; return usar(comp, peticion);
        // OJO: el test pasa una cfg válida y "hola" -> espera "eco:hola". Con cfg null, integrar
        //   devuelve false y tú devuelves "".
        // CULTURA: este encadenado (configurar -> integrar -> usar -> recoger evento) es el ciclo
        //   de vida completo de un componente dentro de una app real.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para integrarTodo");
    }
}
