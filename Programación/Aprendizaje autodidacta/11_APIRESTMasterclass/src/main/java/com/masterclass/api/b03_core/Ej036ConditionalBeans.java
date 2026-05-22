package com.masterclass.api.b03_core;

/**
 * Ejercicio 036 · Beans condicionales por entorno (@Profile/@Conditional).
 *
 * <p>Teoría: {@code teoria/03_Spring_Core_IoC_DI.md} (sección 3.5).
 *
 * <p>Sin Spring: replica la decisión "qué implementación según el perfil activo".
 */
public final class Ej036ConditionalBeans {

    public interface AlmacenFicheros {
        String donde();
    }

    public static class AlmacenLocal implements AlmacenFicheros {
        public String donde() {
            return "disco-local";
        }
    }

    public static class AlmacenS3 implements AlmacenFicheros {
        public String donde() {
            return "aws-s3";
        }
    }

    private Ej036ConditionalBeans() {
    }

    /**
     * Elige la implementación según el perfil.
     *
     * @param perfil "dev", "test" o "prod"
     * @return AlmacenLocal para dev/test; AlmacenS3 para prod
     * @throws IllegalArgumentException si el perfil es desconocido
     */
    public static AlmacenFicheros segunPerfil(String perfil) {
        // TODO 1: si perfil es null, lanza IllegalArgumentException.
        // TODO 2: normaliza el perfil (trim + minúsculas).
        // TODO 3: "dev" debe resolver a almacenamiento local.
        // TODO 4: "test" también a local (mismo comportamiento que dev).
        // TODO 5: agrupa dev/test en la misma rama (no dupliques el new).
        // TODO 6: "prod" debe resolver a AlmacenS3.
        // TODO 7: cualquier otro perfil -> IllegalArgumentException con el valor recibido.
        // TODO 8: devuelve una instancia nueva del almacén correspondiente.
        // TODO 9: el contrato donde() de local es "disco-local".
        // TODO 10: el contrato donde() de prod es "aws-s3".
        return null;
    }

    public static void main(String[] args) {
        System.out.println(segunPerfil("prod").donde());
    }

    // --- MÉTODOS Y CLASES DE RETOS EXTRA ---

    /**
     * Reto Extra 1: Simula la condición @ConditionalOnProperty. Registra un bean condicional si una propiedad dada está habilitada.
     */
    public static class registrarConPropiedadHabilitada {
        // TODO extra: Reto Extra 1: Simula la condición @ConditionalOnProperty. Registra un bean condicional si una propiedad dada está habilitada.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 2: Registra un bean si una propiedad está ausente o no definida, configurando un fallback (matchIfMissing = true).
     */
    public static class registrarConPropiedadAusente {
        // TODO extra: Reto Extra 2: Registra un bean si una propiedad está ausente o no definida, configurando un fallback (matchIfMissing = true).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 3: Implementación personalizada de la interfaz Condition de Spring para evaluar si el sistema operativo actual es Windows.
     */
    public static class WindowsOSCondition implements org.springframework.context.annotation.Condition {
        // TODO extra: Reto Extra 3: Implementación personalizada de la interfaz Condition de Spring para evaluar si el sistema operativo actual es Windows.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 4: DTO de servicio registrado condicionalmente basándose en la condición del sistema operativo creada en el Reto 3.
     */
    @org.springframework.context.annotation.Conditional(WindowsOSCondition.class)
    public static class ServicioSoloWindows {
        // TODO extra: Reto Extra 4: DTO de servicio registrado condicionalmente basándose en la condición del sistema operativo creada en el Reto 3.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para Conditional");
    }

    /**
     * Reto Extra 5: Carga condicionalmente un bean en el classpath si y sólo si existe una determinada clase externa.
     */
    public static class registrarSiClaseExiste {
        // TODO extra: Reto Extra 5: Carga condicionalmente un bean en el classpath si y sólo si existe una determinada clase externa.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 6: Registra un bean condicionado a que otro bean base ya esté registrado en el ApplicationContext (@ConditionalOnBean).
     */
    public static class registrarSiOtroBeanExiste {
        // TODO extra: Reto Extra 6: Registra un bean condicionado a que otro bean base ya esté registrado en el ApplicationContext (@ConditionalOnBean).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 7: Registra un bean aplicando múltiples condiciones acumulativas, donde todas deben cumplirse.
     */
    public static class CondicionalMultiple {
        // TODO extra: Reto Extra 7: Registra un bean aplicando múltiples condiciones acumulativas, donde todas deben cumplirse.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 8: Registra un bean usando una condición inversa (registro si y sólo si una determinada condición de entorno falla).
     */
    public static class CondicionNegada implements org.springframework.context.annotation.Condition {
        // TODO extra: Reto Extra 8: Registra un bean usando una condición inversa (registro si y sólo si una determinada condición de entorno falla).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 9: Condiciona el registro de un bean a la existencia física de un recurso en el classpath (ej. un fichero de propiedades).
     */
    public static class registrarCondicionalPorRecurso {
        // TODO extra: Reto Extra 9: Condiciona el registro de un bean a la existencia física de un recurso en el classpath (ej. un fichero de propiedades).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 10: Comprueba de forma programática si un determinado bean superó las condiciones de configuración y fue finalmente registrado con éxito.
     */
    public static boolean evaluarConditionDeRegistro(org.springframework.context.ApplicationContext ctx, String nombreBean) {
        // TODO extra: Reto Extra 10: Comprueba de forma programática si un determinado bean superó las condiciones de configuración y fue finalmente registrado con éxito.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para evaluarConditionDeRegistro");
    }

}
