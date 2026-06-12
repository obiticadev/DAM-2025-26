package com.masterclass.api.b03_core;

import java.util.function.Supplier;

/**
 * Ejercicio 029 · Mini-contenedor IoC didáctico.
 *
 * <p>Teoría: {@code teoria/03_Spring_Core_IoC_DI.md} (sección 3.1).
 *
 * <p>Recrea, en pequeño, lo que hace Spring: registrar fábricas de beans y
 * resolverlos como singletons (la misma instancia en cada {@code getBean}).
 */
public class Ej029ManualIoCContainer implements AutoCloseable {

    /**
     * Registra una fábrica para un tipo. El bean se crea de forma perezosa
     * la primera vez que se pide y luego se cachea (singleton).
     *
     * @param tipo    clase que actúa como clave
     * @param fabrica proveedor que construye la instancia
     * @param <T>     tipo del bean
     */
    public <T> void register(Class<T> tipo, Supplier<T> fabrica) {
        // TODO 1: necesitas una estructura interna para fábricas (Map<Class,Supplier>).
        // TODO 2: y otra para instancias ya creadas (caché de singletons).
        // TODO 3: valida que 'tipo' y 'fabrica' no sean null.
        // TODO 4: guarda la fábrica indexada por 'tipo'.
        throw new UnsupportedOperationException("TODO no implementado");
    }

    /**
     * Devuelve el bean del tipo pedido, creándolo la primera vez (singleton).
     *
     * @param tipo clase pedida
     * @param <T>  tipo del bean
     * @return la instancia (siempre la misma para el mismo tipo)
     * @throws IllegalStateException si el tipo no fue registrado
     */
    public <T> T getBean(Class<T> tipo) {
        // TODO 5: si no hay fábrica registrada para 'tipo' -> IllegalStateException.
        // TODO 6: si ya existe instancia cacheada, devuélvela (mismo objeto).
        // TODO 7: si no, invoca la fábrica para crear la instancia.
        // TODO 8: guarda la instancia en la caché de singletons.
        // TODO 9: castea con tipo.cast(...) para devolver el tipo correcto sin warnings.
        // TODO 10: devuelve la instancia (futuras llamadas devolverán la misma).
        return null;
    }

    public static void main(String[] args) {
        var c = new Ej029ManualIoCContainer();
        c.register(StringBuilder.class, StringBuilder::new);
        System.out.println(c.getBean(StringBuilder.class) == c.getBean(StringBuilder.class));
    }

    // --- MÉTODOS DE RETOS EXTRA ---

    /**
     * Reto Extra 1: Registra una fábrica de alcance PROTOTYPE.
     * Cada vez que se invoque getBean(tipo), debe retornarse una nueva instancia ejecutando esta fábrica.
     */
    public <T> void registerPrototype(Class<T> tipo, Supplier<T> fabrica) {
        // GUÍA: teoría 3.5 (prototype = instancia NUEVA en cada getBean).
        // 1. Valida tipo y fabrica no null (Objects.requireNonNull).
        // 2. Necesitas un TERCER mapa, p.ej. Map<Class,Supplier> prototypes, distinto del
        //    de singletons, para marcar que este tipo NO debe cachearse.
        // 3. Guarda la fábrica ahí: prototypes.put(tipo, fabrica).
        // 4. Para que funcione, getBean (TODO 6) debe consultar PRIMERO este mapa: si el
        //    tipo es prototype -> return tipo.cast(fabrica.get()) SIN guardar en caché.
        // OJO: el test pide getBean(StringBuilder) dos veces y espera assertNotSame -> dos
        //   objetos distintos. Si lo cacheas, el test falla.
        // ⚠ CUIDADO: este reto te obliga a tocar getBean del ejercicio base para que
        //   distinga singleton de prototype; tenlo en cuenta al implementar TODO 5-10.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para registerPrototype");
    }

    /**
     * Reto Extra 2: Registra una instancia pre-construida de un bean asociada a un nombre identificativo.
     */
    public void registerSingletonInstance(String nombre, Object instancia) {
        // GUÍA: teoría 3.1 (registrar una instancia YA construida, como
        // ctx.getBeanFactory().registerSingleton de Spring).
        // 1. Valida nombre y instancia no null.
        // 2. Necesitas un mapa por NOMBRE: Map<String,Object> porNombre.
        // 3. porNombre.put(nombre, instancia).  (Esto lo consumen los retos 3, 6, 8, 9, 10.)
        // OJO: es la base de getBeanByName (reto 3) y de getBeansOfType (reto 8); piensa el
        //   nombre del campo una vez y reutilízalo en todos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para registerSingletonInstance");
    }

    /**
     * Reto Extra 3: Recupera un bean registrado a partir de su nombre identificativo.
     */
    public Object getBeanByName(String nombre) {
        // GUÍA: teoría 3.2 (resolver por nombre, como ctx.getBean("saludo")).
        // 1. Si el nombre es un alias (reto 9), tradúcelo antes al nombre original.
        // 2. Busca en el mapa porNombre (reto 2).
        // 3. Si no existe -> lanza IllegalStateException (NO devuelvas null).
        // 4. Si existe -> devuélvelo.
        // OJO: el test espera getBeanByName("saludo") == "Hola Mundo" y que
        //   getBeanByName("inexistente") lance IllegalStateException (igual que getBean).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para getBeanByName");
    }

    /**
     * Reto Extra 4: Comprueba si existe algún registro del bean en el contenedor.
     */
    public boolean hasBean(Class<?> tipo) {
        // GUÍA: una línea —
        // return fabricas.containsKey(tipo);   (o también consultar prototypes/singletons según tu diseño)
        // OJO: el test hace hasBean(String) == false ANTES de registrar y == true DESPUÉS de
        //   register(String.class, ...). Basta con mirar las fábricas registradas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para hasBean");
    }

    /**
     * Reto Extra 5: Vacía y limpia por completo todas las estructuras de registro y cachés del contenedor.
     */
    public void clear() {
        // GUÍA: vacía TODAS las estructuras internas que hayas creado.
        // 1. fabricas.clear(); singletons.clear(); porNombre.clear(); prototypes.clear(); alias.clear();
        //    (las que tengas; no olvides ninguna o quedará estado fantasma).
        // OJO: el test registra String y luego hasBean(String) debe ser false tras clear().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clear");
    }

    /**
     * Reto Extra 6: Devuelve el recuento total de tipos o nombres registrados en el contenedor.
     */
    public int getBeanCount() {
        // GUÍA: suma de registros por TIPO + registros por NOMBRE.
        // 1. return fabricas.size() + porNombre.size();  (ajusta a tus mapas).
        // OJO: el test registra 1 por tipo (String) y 1 por nombre ("num"->42) y espera 2.
        //   Empieza en 0 con el contenedor recién creado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para getBeanCount");
    }

    /**
     * Reto Extra 7: Registra un bean cuya fábrica depende dinámicamente de la resolución de otro bean registrado.
     */
    public <T> void registerWithDependency(Class<T> tipo, Class<?> dep, java.util.function.Function<Object, T> fabrica) {
        // GUÍA: teoría 3.1/3.3 (una fábrica que, al crear el bean, resuelve OTRA dependencia
        // del propio contenedor: es DI en miniatura).
        // 1. Valida los tres parámetros no null.
        // 2. Registra para 'tipo' una fábrica que, al invocarse, haga:
        //    register(tipo, () -> fabrica.apply(getBean(dep)));
        //    Es decir, en el momento de crear el bean pide getBean(dep) y se lo pasa a la función.
        // PISTA: reutiliza tu propio register(...) con un Supplier que llama a getBean(dep).
        // OJO: el test registra String "Prefijo", luego registerWithDependency(SB, String,
        //   dep -> new StringBuilder((String)dep).append("Suffix")) y espera getBean(SB) ==
        //   "PrefijoSuffix". La dependencia se resuelve cuando se crea el SB, no al registrar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para registerWithDependency");
    }

    /**
     * Reto Extra 8: Resuelve y devuelve todas las instancias singleton que hereden o implementen el tipo indicado.
     */
    public <T> java.util.Map<String, T> getBeansOfType(Class<T> tipo) {
        // GUÍA: teoría 3.4 (es el getBeansOfType de Spring: Map<nombre, bean> de los que
        // son del tipo pedido).
        // 1. Crea un Map<String,T> resultado.
        // 2. Recorre porNombre.entrySet(); para cada (nombre, instancia):
        //    si tipo.isInstance(instancia) -> resultado.put(nombre, tipo.cast(instancia)).
        // 3. return resultado.
        // PISTA: tipo.isInstance(x) y tipo.cast(x) evitan el unchecked warning.
        // OJO: el test registra 3 instancias por nombre (2 String "texto1"/"texto2" y un
        //   Integer 100) y espera que getBeansOfType(String) devuelva size 2 con esos dos
        //   valores. El Integer NO debe colarse (isInstance lo filtra).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para getBeansOfType");
    }

    /**
     * Reto Extra 9: Registra un alias alternativo que apunte al nombre de un bean ya existente.
     */
    public void registerAlias(String originalName, String alias) {
        // GUÍA: teoría 3.7 (alias = otro nombre para el MISMO bean, como @Bean(name={...})).
        // 1. Necesitas un Map<String,String> alias -> nombreOriginal.
        // 2. alias.put(alias, originalName).
        // 3. Para que funcione, getBeanByName (reto 3) debe, si el nombre pedido es un alias,
        //    sustituirlo por el original antes de buscar: nombre = aliasMap.getOrDefault(nombre, nombre).
        // OJO: el test registra "original"->"valorReal", luego alias "original"->"miAlias" y
        //   espera getBeanByName("miAlias") == "valorReal". Fíjate en el ORDEN de argumentos:
        //   originalName primero, alias después.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para registerAlias");
    }

    /**
     * Reto Extra 10: Callback de destrucción ordenada para liberar recursos de los singletons.
     */
    @Override
    public void close() throws Exception {
        // GUÍA: teoría 3.6 (@PreDestroy: al cerrar el contenedor, cierra los beans que sean
        // recursos). Aquí propagas close() a cada singleton AutoCloseable.
        // 1. Recorre TODAS tus instancias cacheadas (singletons + porNombre).
        // 2. Para cada una: if (instancia instanceof AutoCloseable ac) ac.close();
        // 3. (Buena práctica, teoría 3.6) envuelve cada close en try/catch para que un fallo
        //    no impida cerrar el resto; aquí el test usa un bean que no lanza, así que basta.
        // OJO: el test registra por nombre un BeanCerrable, lo resuelve con getBeanByName para
        //   cachearlo, llama close() y comprueba que su flag 'cerrado' quedó en true. NO dejes
        //   el throw: este método debe ejecutar la destrucción, no fallar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para close");
    }

}
