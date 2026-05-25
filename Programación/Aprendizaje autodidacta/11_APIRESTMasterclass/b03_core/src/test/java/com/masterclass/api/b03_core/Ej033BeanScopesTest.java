package com.masterclass.api.b03_core;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b03_core.Ej033BeanScopes.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.ObjectFactory;
import static org.junit.jupiter.api.Assertions.*;

class Ej033BeanScopesTest {

    @Test
    void singleton() {
        var s = new Ej033BeanScopes<>(Object::new, true);
        assertSame(s.get(), s.get());
    }

    @Test
    void prototype() {
        var p = new Ej033BeanScopes<>(Object::new, false);
        assertNotSame(p.get(), p.get());
    }

    @Component
    static class MiSingletonBean {}

    @Component
    @Scope("prototype")
    static class MiPrototypeBean {}

    @Test
    void retoExtra01_esMismaInstancia() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.register(MiSingletonBean.class, MiPrototypeBean.class);
            ctx.refresh();
            
            assertTrue(Ej033BeanScopes.esMismaInstancia(ctx, "ej033BeanScopesTest.MiSingletonBean"));
            assertFalse(Ej033BeanScopes.esMismaInstancia(ctx, "ej033BeanScopesTest.MiPrototypeBean"));
        }
    }

    @Test
    void retoExtra02_beanConContadorPrototype() {
        var b1 = new BeanConContadorPrototype();
        var b2 = new BeanConContadorPrototype();
        b1.incrementar();
        assertEquals(1, b1.getContador());
        assertEquals(0, b2.getContador(), "Prototype b2 no debe compartir estado con b1");
    }

    @Test
    void retoExtra03_singletonConInyeccionPrototype() {
        ObjectFactory<BeanConContadorPrototype> factory = () -> {
            var b = new BeanConContadorPrototype();
            b.incrementar(); // simula inicialización
            return b;
        };
        var s = new SingletonConInyeccionPrototype(factory);
        // Cada get debe recuperar un nuevo contador incrementado, devolviendo 2 (1 de la fábrica + 1 de obtenerValorContadorNuevo)
        assertEquals(2, s.obtenerValorContadorNuevo());
        assertEquals(2, s.obtenerValorContadorNuevo());
    }

    @Test
    void retoExtra04_registrarScopeCustom() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            var myScope = new ScopeThread();
            Ej033BeanScopes.registrarScopeCustom(ctx, "customThread", myScope);
            
            assertTrue(java.util.Arrays.asList(ctx.getBeanFactory().getRegisteredScopeNames()).contains("customThread"));
        }
    }

    @Test
    void retoExtra05_scopeThread() throws InterruptedException {
        var scope = new ScopeThread();
        ObjectFactory<Object> factory = Object::new;
        
        Object o1 = scope.get("testBean", factory);
        Object o2 = scope.get("testBean", factory);
        assertSame(o1, o2, "En el mismo hilo, debe retornar la misma instancia");

        Object[] o3 = new Object[1];
        Thread t = new Thread(() -> {
            o3[0] = scope.get("testBean", factory);
        });
        t.start();
        t.join();
        
        assertNotSame(o1, o3[0], "En hilos distintos, debe generar instancias diferentes");
    }

    @Test
    void retoExtra06_esScopePrototypeDefinido() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.register(MiSingletonBean.class, MiPrototypeBean.class);
            ctx.refresh();
            
            assertTrue(Ej033BeanScopes.esScopePrototypeDefinido(ctx, "ej033BeanScopesTest.MiPrototypeBean"));
            assertFalse(Ej033BeanScopes.esScopePrototypeDefinido(ctx, "ej033BeanScopesTest.MiSingletonBean"));
        }
    }

    @Test
    void retoExtra07_crearScopeLimitado() {
        var scope = Ej033BeanScopes.crearScopeLimitado(2);
        ObjectFactory<Object> factory = Object::new;
        
        Object o1 = scope.get("bean", factory);
        Object o2 = scope.get("bean", factory);
        Object o3 = scope.get("bean", factory);
        
        // El cupo es 2. La tercera solicitud debe reutilizar la última (o2).
        assertSame(o2, o3);
    }

    @Test
    void retoExtra08_limpiarCacheScope() {
        var scope = new ScopeThread();
        ObjectFactory<Object> factory = Object::new;
        
        Object o1 = scope.get("testBean", factory);
        Ej033BeanScopes.limpiarCachéScope(scope);
        Object o2 = scope.get("testBean", factory);
        
        assertNotSame(o1, o2, "Tras limpiar el scope, debe devolverse una nueva instancia");
    }

    @Test
    void retoExtra09_singletonConProxyPrototype() {
        var mockProxy = new BeanConContadorPrototype() {
            @Override
            public void incrementar() {}
        };
        var s = new SingletonConProxyPrototype(mockProxy);
        assertEquals(mockProxy, s.getProxyPrototype());
    }

    @Test
    void retoExtra10_evaluarCicloVidaPrototype() {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            ctx.register(MiPrototypeBean.class);
            ctx.refresh();
            ctx.getBean("ej033BeanScopesTest.MiPrototypeBean");
            
            boolean gestionado = Ej033BeanScopes.evaluarCicloVidaPrototype(ctx, "ej033BeanScopesTest.MiPrototypeBean");
            assertFalse(gestionado, "Spring no debe registrar/llamar callbacks PreDestroy para prototypes");
        }
    }
}

