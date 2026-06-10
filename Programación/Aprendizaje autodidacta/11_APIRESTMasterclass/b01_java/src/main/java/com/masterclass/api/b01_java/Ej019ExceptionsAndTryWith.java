package com.masterclass.api.b01_java;

/**
 * Ejercicio 019 · Excepciones y try-with-resources.
 *
 * <p>Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.5).
 */
public final class Ej019ExceptionsAndTryWith {

    private Ej019ExceptionsAndTryWith() {
    }

    /** Excepción de negocio no comprobada. */
    public static class RecursoNoEncontradoException extends RuntimeException {
        public RecursoNoEncontradoException(String msg) {
            super(msg);
        }
    }

    /** Recurso simulado que registra si fue cerrado. */
    public static class Conexion implements AutoCloseable {
        public boolean cerrada = false;
        public String leer() {
            return "datos";
        }
        @Override
        public void close() {
            this.cerrada = true;
        }
    }

    // --- Modelos y Excepciones para retos extra ---

    public static class ErrorNegocioException extends Exception {
        public ErrorNegocioException(String mensaje) {
            super(mensaje);
        }
        public ErrorNegocioException(String mensaje, Throwable causa) {
            super(mensaje, causa);
        }
    }

    public static class ErrorSistemaException extends RuntimeException {
        public ErrorSistemaException(String mensaje) {
            super(mensaje);
        }
        public ErrorSistemaException(String mensaje, Throwable causa) {
            super(mensaje, causa);
        }
    }

    public static class RecursoFragil implements AutoCloseable {
        public boolean cerrado = false;
        private final boolean fallaAlCerrar;

        public RecursoFragil(boolean fallaAlCerrar) {
            this.fallaAlCerrar = fallaAlCerrar;
        }

        public String operar(boolean lanzarError) throws ErrorNegocioException {
            if (lanzarError) {
                throw new ErrorNegocioException("Error al operar");
            }
            return "operacion-exitosa";
        }

        @Override
        public void close() throws Exception {
            this.cerrado = true;
            if (fallaAlCerrar) {
                throw new IllegalStateException("Fallo catastrófico al cerrar");
            }
        }
    }

    /**
     * Busca un recurso por id; lanza si no existe.
     *
     * @param id identificador (válido si es &gt; 0)
     * @return "recurso-{id}"
     * @throws RecursoNoEncontradoException si id &lt;= 0, con mensaje "no existe: {id}"
     */
    public static String buscar(int id) {
        // TODO 1: comprueba la precondición id > 0.
        // TODO 2: si id <= 0, construye el mensaje "no existe: " + id.
        // TODO 3: lanza RecursoNoEncontradoException con ese mensaje.
        // TODO 4: si la precondición se cumple, construye "recurso-" + id.
        // TODO 5: devuelve esa cadena.
        return "";
    }

    /**
     * Usa la conexión dentro de un try-with-resources y devuelve lo leído.
     *
     * @param c conexión a usar y cerrar automáticamente
     * @return el resultado de c.leer()
     */
    public static String usarYCerrar(Conexion c) {
        // TODO 6: abre un try-with-resources declarando el recurso (try (var r = c)).
        // TODO 7: dentro del try, invoca r.leer() y guarda el resultado.
        // TODO 8: el cierre debe ser automático al salir del try (no llames close() a mano).
        // TODO 9: garantiza que se cierra incluso si hubiera excepción (lo hace try-with-resources).
        // TODO 10: devuelve el valor leído.
        return "";
    }

    public static void main(String[] args) {
        System.out.println(buscar(5));
    }

    /**
     * Reto Extra 1: Cierre manual y seguro de recursos.
     * Cierra el recurso de manera segura. Si lanza una excepción en close(),
     * la captura e impide que se propague (silenciosamente o registrando).
     *
     * @param recurso recurso liberable
     */
    public static void cerrarRecursoSeguro(AutoCloseable recurso) {
        // GUÍA: el "close quietly" clásico:
        // if (recurso == null) return;
        // try { recurso.close(); }
        // catch (Exception e) { /* registrado/ignorado a propósito */ }
        // El test pasa un RecursoFragil que EXPLOTA al cerrar y exige que no
        // se propague. Único sitio legítimo para un catch casi-vacío: limpieza
        // final donde ya no puedes hacer nada mejor. Deja el comentario
        // explicando que es deliberado — un catch vacío sin comentario es
        // el error común nº7.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cerrarRecursoSeguro");
    }

    /**
     * Reto Extra 2: Procesamiento clásico con try-with-resources.
     * Usa try-with-resources para garantizar el cierre del RecursoFragil y ejecuta operar().
     *
     * @param recurso         recurso a ser usado y cerrado automáticamente
     * @param lanzarAlOperar indica si operar() debe fallar
     * @return el resultado de la operación
     * @throws ErrorNegocioException si la operación falla
     */
    public static String procesarConTryWithResources(RecursoFragil recurso, boolean lanzarAlOperar) throws ErrorNegocioException {
        // GUÍA:
        // try (recurso) {                          // Java 9+: variable ya efectiva-final
        //     return recurso.operar(lanzarAlOperar);
        // } catch (Exception e) { ... }            // ojo: close() declara throws Exception
        // El truco del test: cuando operar() LANZA, el recurso DEBE quedar
        // cerrado igualmente (rFalla.cerrado == true) — eso es exactamente lo
        // que garantiza try-with-resources y lo que un try normal olvidaría.
        // Deja pasar la ErrorNegocioException (está en el throws); si close()
        // lanzara otra cosa, decide cómo tratarla.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para procesarConTryWithResources");
    }

    /**
     * Reto Extra 3: Propagación de excepciones con encadenamiento (exception chaining).
     * Lanza una nueva ErrorSistemaException que encapsule y mantenga la causa original.
     *
     * @param mensaje mensaje explicativo personalizado
     * @param causa   excepción original causante
     */
    public static void lanzarConCausaOriginal(String mensaje, Throwable causa) {
        // GUÍA: una línea — throw new ErrorSistemaException(mensaje, causa);
        // (el constructor de dos argumentos ya existe arriba).
        // ESTA ES la regla 3 de la teoría 1.9: al envolver, pasa SIEMPRE la
        // causa. El test verifica ex.getCause() == causa: sin ella, el stack
        // trace real desaparece y depurar se vuelve arqueología.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para lanzarConCausaOriginal");
    }

    /**
     * Reto Extra 4: Modos silenciosos para procesos secundarios.
     * Ejecuta una tarea Runnable. Si ésta lanza cualquier excepción, la captura y la ignora.
     *
     * @param accion tarea a ejecutar
     */
    public static void ejecutarAccionIgnorandoExcepcion(Runnable accion) {
        // GUÍA:
        // try { accion.run(); } catch (RuntimeException e) { /* deliberado */ }
        // CASO DE USO legítimo: tareas secundarias cuyo fallo no debe tumbar
        // la principal (enviar una métrica, un log remoto, un email de aviso).
        // PERO que el nombre del método lo diga a gritos, como aquí: tragarse
        // excepciones en silencio sin avisar en la firma es deuda técnica.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ejecutarAccionIgnorandoExcepcion");
    }

    /**
     * Reto Extra 5: Análisis de excepciones suprimidas.
     * Ejecuta operar(true) sobre un RecursoFragil configurado para fallar al cerrar dentro de un try-with-resources.
     * Captura la excepción de negocio lanzada, extrae la excepción suprimida (el fallo al cerrar) y retorna su mensaje.
     * Si no hay excepción suprimida, retorna una cadena vacía.
     *
     * @param recurso recurso frágil que falla al cerrar
     * @return mensaje de la excepción suprimida, o vacío si no hay ninguna
     */
    public static String detectarExcepcionSuprimida(RecursoFragil recurso) {
        // GUÍA: el rincón menos conocido de try-with-resources.
        // ESCENARIO: operar(true) lanza ErrorNegocioException Y close() lanza
        // IllegalStateException. ¿Cuál gana? La del try; la de close() no se
        // pierde: queda ADJUNTA como "suppressed".
        // try (recurso) {
        //     recurso.operar(true);                    // lanza
        // } catch (Exception e) {
        //     Throwable[] sup = e.getSuppressed();     // aquí está la de close()
        //     return sup.length > 0 ? sup[0].getMessage() : "";
        // }
        // return "";
        // El test espera "Fallo catastrófico al cerrar". Antes de Java 7 esa
        // excepción se PERDÍA de verdad — por eso existe este mecanismo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para detectarExcepcionSuprimida");
    }

    /**
     * Reto Extra 6: Detección recursiva de excepciones de negocio.
     * Determina si la excepción provista es, o tiene en su cadena de causas, una ErrorNegocioException.
     *
     * @param t excepción a inspeccionar
     * @return true si es o contiene un error de negocio
     */
    public static boolean esExcepcionDeNegocio(Throwable t) {
        // GUÍA: recorre la CADENA de causas (cada excepción puede envolver otra):
        // Versión iterativa:
        //   while (t != null) {
        //       if (t instanceof ErrorNegocioException) return true;
        //       t = t.getCause();
        //   }
        //   return false;
        // Versión recursiva: ¿es? → true; ¿causa null? → false; si no,
        // esExcepcionDeNegocio(t.getCause()). Elige una y entiende la otra.
        // En el b09, tu @ExceptionHandler hará exactamente este "desempaquetado".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionDeNegocio");
    }

    /**
     * Reto Extra 7: Formateador de excepciones amigable para APIs.
     * Inspecciona la excepción y retorna el mensaje de la causa raíz formateado como "Error: [mensaje]".
     * Si no hay mensaje en la causa raíz, usa el nombre de su clase.
     *
     * @param t excepción original
     * @return mensaje formateado amigable
     */
    public static String obtenerMensajeDeErrorFormateado(Throwable t) {
        // GUÍA: dos pasos:
        // 1. Baja hasta la CAUSA RAÍZ: while (t.getCause() != null) t = t.getCause();
        // 2. Formatea: el mensaje si lo hay, el nombre de clase si no:
        //    String msg = t.getMessage() != null ? t.getMessage()
        //                                        : t.getClass().getSimpleName();
        //    return "Error: " + msg;
        // Test: RuntimeException("Error nivel 1", NPE("Valor nulo inesperado"))
        // → "Error: Valor nulo inesperado" (la raíz, no el envoltorio).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerMensajeDeErrorFormateado");
    }

    /**
     * Reto Extra 8: Algoritmo de tolerancia a fallos con reintentos.
     * Ejecuta una acción que devuelve un String. Si falla, realiza reintentos sucesivos hasta maxReintentos.
     * Si todos los reintentos fallan, lanza la última excepción capturada envuelta en un RuntimeException.
     *
     * @param accion        acción a ejecutar
     * @param maxReintentos número máximo de intentos adicionales
     * @return el resultado de la acción
     */
    public static String ejecutarConReintentos(java.util.concurrent.Callable<String> accion, int maxReintentos) {
        // GUÍA: el bucle de retry (versión casera de lo que Resilience4j hará en b21):
        // Exception ultima = null;
        // for (int intento = 0; intento < maxReintentos; intento++) {
        //     try { return accion.call(); }
        //     catch (Exception e) { ultima = e; }
        // }
        // throw new RuntimeException("Agotados los reintentos", ultima);
        // El test: falla 2 veces, funciona a la 3ª, con maxReintentos=3 →
        // debe devolver "conectado" tras exactamente 3 invocaciones. Cuadra
        // tu condición de bucle con eso. Y la causa en el throw final: regla 3.
        // (Callable.call() declara throws Exception — por eso el catch amplio.)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ejecutarConReintentos");
    }

    /**
     * Reto Extra 9: Inspección de tipo en la causa raíz.
     * Verifica si la causa raíz de una excepción es del tipo de clase especificado.
     *
     * @param tipoEx tipo de excepción esperado en la raíz
     * @param ex     excepción bajo análisis
     * @return true si la causa raíz coincide con el tipo especificado
     */
    public static boolean esCausaRaiz(Class<? extends Throwable> tipoEx, Throwable ex) {
        // GUÍA: reutiliza la técnica del reto 7 (bajar a la raíz) y compara el tipo:
        // 1. ex null → false.
        // 2. while (ex.getCause() != null) ex = ex.getCause();
        // 3. return tipoEx.isInstance(ex);
        // tipoEx.isInstance(obj) es el "instanceof dinámico": el tipo llega
        // como PARÁMETRO (Class<? extends Throwable>) — instanceof literal no
        // sirve porque exige el tipo en tiempo de compilación.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCausaRaiz");
    }

    /**
     * Reto Extra 10: Try-with-resources con múltiples recursos dependientes.
     * Abre ambos recursos en una única instrucción try-with-resources y retorna la concatenación de sus operaciones exitosas.
     *
     * @param r1 primer recurso
     * @param r2 segundo recurso
     * @return r1.operar() + "+" + r2.operar()
     * @throws Exception si ocurre algún error
     */
    public static String procesarRecursosMultiples(RecursoFragil r1, RecursoFragil r2) throws Exception {
        // GUÍA: varios recursos en UN try, separados por ';':
        // try (r1; r2) {
        //     return r1.operar(false) + "+" + r2.operar(false);
        // }
        // DETALLE DE ORO: se cierran en orden INVERSO (r2 primero, luego r1) —
        // como una pila. Piensa por qué: si r2 dependiera de r1 (un Reader
        // sobre un Stream), cerrar r1 primero rompería el cierre de r2.
        // El throws Exception de la firma deja propagar lo que surja.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para procesarRecursosMultiples");
    }

}
