package com.masterclass.api.b03_core;

import java.util.function.Supplier;

/**
 * Ejercicio 033 · Scopes: singleton vs prototype.
 *
 * <p>Teoría: {@code teoria/03_Spring_Core_IoC_DI.md} (sección 3.4).
 */
public class Ej033BeanScopes<T> {

    private final Supplier<T> fabrica;
    private final boolean singleton;

    /**
     * @param fabrica   crea instancias del bean
     * @param singleton true = misma instancia siempre; false = una nueva por llamada
     */
    public Ej033BeanScopes(Supplier<T> fabrica, boolean singleton) {
        this.fabrica = fabrica;
        this.singleton = singleton;
    }

    /**
     * Obtiene una instancia respetando el scope configurado.
     *
     * @return la instancia (cacheada si es singleton; nueva si es prototype)
     */
    public T get() {
        // TODO 1: necesitas un campo interno para cachear la instancia singleton.
        // TODO 2: comprueba el flag 'singleton'.
        // TODO 3: si NO es singleton (prototype), devuelve siempre fabrica.get() (instancia nueva).
        // TODO 4: si es singleton, comprueba si ya hay instancia cacheada.
        // TODO 5: si la caché está vacía, créala con fabrica.get().
        // TODO 6: guárdala en el campo de caché.
        // TODO 7: en llamadas siguientes, NO vuelvas a invocar la fábrica.
        // TODO 8: devuelve siempre la MISMA instancia para singleton.
        // TODO 9: ten en cuenta el caso límite: fabrica.get() podría devolver null.
        // TODO 10: documenta mentalmente por qué prototype no cachea (estado independiente).
        return null;
    }

    public static void main(String[] args) {
        var s = new Ej033BeanScopes<>(Object::new, true);
        System.out.println(s.get() == s.get());
        var p = new Ej033BeanScopes<>(Object::new, false);
        System.out.println(p.get() == p.get());
    }

    // --- MÉTODOS Y CLASES DE RETOS EXTRA ---

    /**
     * Reto Extra 1: Comprueba si dos solicitudes de obtención de un bean devuelven la misma instancia (Singleton) o distintas (Prototype).
     */
    public static boolean esMismaInstancia(org.springframework.context.ApplicationContext ctx, String nombreBean) {
        // GUÍA: teoría 3.5 (la diferencia singleton/prototype se ve con identidad ==).
        // 1. return ctx.getBean(nombreBean) == ctx.getBean(nombreBean);
        // OJO: el test espera true para "...MiSingletonBean" (misma instancia) y false para
        //   "...MiPrototypeBean" (instancia nueva cada getBean). Usa == (identidad), no equals.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMismaInstancia");
    }

    /**
     * Reto Extra 2: Un bean Prototype que mantiene un contador incremental interno.
     * Cada nueva instancia debe inicializar su contador en 0.
     */
    public static class BeanConContadorPrototype {
        private int contador = 0;   // cada instancia (prototype) arranca su propio contador en 0

        public void incrementar() {
            // GUÍA: una línea — contador++;
            // OJO: este método se SOBRESCRIBE en el reto 9 (subclase anónima), por eso es de
            //   instancia y no final. El test reto2 crea b1 y b2; b1.incrementar() debe dejar
            //   b1 en 1 y b2 seguir en 0 (estado NO compartido entre instancias prototype).
            throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para incrementar");
        }

        public int getContador() {
            return contador;
        }
    }

    /**
     * Reto Extra 3: Soluciona el problema de inyectar un bean de alcance Prototype
     * en un Singleton utilizando ObjectFactory para obtener una nueva instancia del Prototype en cada invocación.
     */
    public static class SingletonConInyeccionPrototype {
        private final org.springframework.beans.factory.ObjectFactory<BeanConContadorPrototype> factory;

        public SingletonConInyeccionPrototype(org.springframework.beans.factory.ObjectFactory<BeanConContadorPrototype> factory) {
            this.factory = factory;
        }

        public int obtenerValorContadorNuevo() {
            // GUÍA: teoría 3.5 (la trampa del prototype en un singleton: pides una instancia
            // FRESCA en cada llamada con ObjectFactory.getObject(), no la cacheas).
            // 1. var bean = factory.getObject();   // instancia nueva cada vez
            // 2. bean.incrementar();               // suma 1 sobre la que trae la fábrica
            // 3. return bean.getContador();
            // OJO: la fábrica del test ya hace incrementar() (deja el contador en 1); tú sumas
            //   otro -> 2. Como pides una instancia nueva cada vez, dos llamadas devuelven 2 y 2
            //   (no 2 y 3). Si cachearas el bean, la segunda daría 3 y el test fallaría.
            throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerValorContadorNuevo");
        }
    }

    /**
     * Reto Extra 4: Registra programáticamente un Scope personalizado en el contexto de Spring.
     */
    public static void registrarScopeCustom(org.springframework.context.support.GenericApplicationContext ctx, String scopeName, org.springframework.beans.factory.config.Scope scope) {
        // GUÍA: una línea —
        // ctx.getBeanFactory().registerScope(scopeName, scope);
        // OJO: el test registra un ScopeThread bajo "customThread" y comprueba que aparece en
        //   ctx.getBeanFactory().getRegisteredScopeNames(). No hace falta refresh para esto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para registrarScopeCustom");
    }

    /**
     * Reto Extra 5: Implementación sencilla de ThreadScope que aísla beans por hilo (ThreadLocal).
     */
    public static class ScopeThread implements org.springframework.beans.factory.config.Scope {
        // GUÍA: teoría 3.5 (un scope a medida: una instancia por HILO con ThreadLocal).
        // El Map por hilo guarda los beans ya creados en ese hilo.
        private final ThreadLocal<java.util.Map<String, Object>> porHilo =
                ThreadLocal.withInitial(java.util.HashMap::new);

        @Override
        public Object get(String name, org.springframework.beans.factory.ObjectFactory<?> objectFactory) {
            // GUÍA:
            // 1. var mapa = porHilo.get();
            // 2. Si no contiene 'name', créalo: mapa.put(name, objectFactory.getObject()).
            // 3. return mapa.get(name);  // misma instancia para el mismo nombre EN ESTE hilo.
            // PISTA: mapa.computeIfAbsent(name, k -> objectFactory.getObject()).
            // OJO: el test pide dos veces "testBean" en el mismo hilo (assertSame) y una vez en
            //   otro hilo (assertNotSame): cada hilo tiene su propio Map por el ThreadLocal.
            throw new UnsupportedOperationException("TODO: Implementar get del ScopeThread");
        }

        @Override
        public Object remove(String name) {
            // GUÍA: una línea — return porHilo.get().remove(name);  (desaloja el bean del hilo).
            throw new UnsupportedOperationException("TODO: Implementar remove del ScopeThread");
        }

        @Override
        public void registerDestructionCallback(String name, Runnable callback) {
            // GUÍA: para un scope didáctico puedes dejarlo vacío (no gestionamos destrucción).
        }

        @Override
        public Object resolveContextualObject(String key) {
            // GUÍA: no aplica a este scope; devuelve null.
            return null;
        }

        @Override
        public String getConversationId() {
            // GUÍA: identificador del "ámbito actual"; el del hilo sirve:
            // return Thread.currentThread().getName();
            return Thread.currentThread().getName();
        }

        /** Vacía la caché del hilo actual (lo usa el reto 8). */
        public void limpiar() {
            // GUÍA: una línea — porHilo.get().clear();
            throw new UnsupportedOperationException("TODO: Implementar limpiar del ScopeThread");
        }
    }

    /**
     * Reto Extra 6: Comprueba en los metadatos si un bean está configurado explícitamente con ámbito prototype.
     */
    public static boolean esScopePrototypeDefinido(org.springframework.context.support.GenericApplicationContext ctx, String nombreBean) {
        // GUÍA: una línea —
        // return ctx.getBeanDefinition(nombreBean).isPrototype();
        // OJO: el test espera true para "...MiPrototypeBean" y false para "...MiSingletonBean".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esScopePrototypeDefinido");
    }

    /**
     * Reto Extra 7: Crea un Scope personalizado que limite a un número máximo (ej. 3) las instancias concurrentes de un bean y reutilice la última en caso de superar el cupo.
     */
    public static org.springframework.beans.factory.config.Scope crearScopeLimitado(int maxInstancias) {
        // GUÍA: teoría 3.5 (devuelves una implementación de Scope, normalmente anónima).
        // 1. Lleva un contador de cuántas instancias has creado y guarda la ÚLTIMA creada.
        // 2. En get(name, factory): si contador < maxInstancias -> crea una nueva
        //    (factory.getObject()), guárdala como "última", incrementa el contador y devuélvela.
        // 3. Si ya alcanzaste el cupo -> devuelve la "última" guardada (reutiliza, no crees).
        // 4. Los otros métodos de Scope (remove/registerDestructionCallback/...) como en ScopeThread.
        // OJO: el test crea el scope con max=2, pide get tres veces y espera assertSame(o2, o3):
        //   la 3ª (supera el cupo) reutiliza la 2ª. Las dos primeras son distintas entre sí.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearScopeLimitado");
    }

    /**
     * Reto Extra 8: Fuerza la destrucción de beans cacheados dentro de un determinado ámbito personalizado sin reiniciar el contexto de Spring.
     */
    public static void limpiarCachéScope(org.springframework.beans.factory.config.Scope scope) {
        // GUÍA: teoría 3.5 (vaciar la caché de un scope sin tocar el contexto).
        // 1. Como recibes el tipo general Scope, comprueba si es un ScopeThread:
        //    if (scope instanceof ScopeThread st) st.limpiar();
        // OJO: el test crea un ScopeThread, hace get("testBean") (o1), llama a este método y
        //   vuelve a hacer get (o2) esperando assertNotSame(o1, o2): al limpiar, la siguiente
        //   resolución crea una instancia nueva. Reutiliza el limpiar() que añadiste al ScopeThread.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para limpiarCachéScope");
    }

    /**
     * Reto Extra 9: DTO Singleton que inyecta dinámicamente un Proxy de un bean de ámbito Prototype utilizando ScopedProxyMode.
     */
    public static class SingletonConProxyPrototype {
        private final BeanConContadorPrototype proxyPrototype;

        public SingletonConProxyPrototype(BeanConContadorPrototype proxyPrototype) {
            this.proxyPrototype = proxyPrototype;
        }

        public BeanConContadorPrototype getProxyPrototype() {
            // GUÍA: una línea — return proxyPrototype;
            // (En Spring real este campo sería un PROXY que, en cada acceso, va a por una
            // instancia fresca del prototype; aquí simplemente guardas la referencia inyectada.)
            // OJO: el test inyecta un mock (subclase anónima de BeanConContadorPrototype) y espera
            //   que getProxyPrototype() devuelva ESE mismo objeto (assertEquals con ==).
            return proxyPrototype;
        }
    }

    /**
     * Reto Extra 10: Demuestra y verifica si Spring ejecuta el método anotado con @PreDestroy en beans de ámbito Prototype.
     */
    public static boolean evaluarCicloVidaPrototype(org.springframework.context.support.GenericApplicationContext ctx, String nombreBeanPrototype) {
        // GUÍA: teoría 3.5/3.6 (Spring NO gestiona la destrucción de los prototypes).
        // 1. Lee la definición: var bd = ctx.getBeanFactory().getBeanDefinition(nombreBeanPrototype).
        // 2. Devuelve si Spring gestiona su ciclo de destrucción. Para un prototype la respuesta
        //    es NO: return bd.isSingleton();  (un prototype no es singleton -> false).
        // OJO: el test registra un prototype, lo resuelve y espera que devuelvas false ("Spring
        //   no registra/llama callbacks PreDestroy para prototypes").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para evaluarCicloVidaPrototype");
    }

}
