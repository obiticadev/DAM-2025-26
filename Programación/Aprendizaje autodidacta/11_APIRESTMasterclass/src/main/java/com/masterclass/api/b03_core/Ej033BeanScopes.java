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
}
