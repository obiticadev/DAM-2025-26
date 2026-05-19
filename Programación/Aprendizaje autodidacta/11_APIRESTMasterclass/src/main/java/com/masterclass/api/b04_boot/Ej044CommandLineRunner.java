package com.masterclass.api.b04_boot;

import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 044 · Bootstrap de arranque (CommandLineRunner).
 *
 * <p>Teoría: {@code teoria/04_Spring_Boot_Config.md} (sección 4.1).
 *
 * <p>Un runner ejecuta tareas justo tras arrancar el contexto (seed de datos, etc.).
 */
public class Ej044CommandLineRunner {

    private final List<String> log = new ArrayList<>();
    private boolean ejecutado = false;

    /**
     * Ejecuta el bootstrap procesando los argumentos de arranque.
     *
     * @param args argumentos de línea de comandos (formato "--clave=valor")
     */
    public void run(String... args) {
        // TODO 1: el runner debe ejecutarse UNA sola vez (idempotencia de arranque).
        // TODO 2: si 'ejecutado' ya es true, no repitas el trabajo (return temprano).
        // TODO 3: si args es null, trátalo como array vacío.
        // TODO 4: recorre cada argumento.
        // TODO 5: procesa solo los que empiecen por "--" (ignora el resto).
        // TODO 6: quita el prefijo "--" y divide por el primer '=' en clave/valor.
        // TODO 7: si no hay '=', registra la clave con valor "".
        // TODO 8: añade al 'log' una entrada "clave=valor" por argumento válido.
        // TODO 9: marca 'ejecutado = true' al terminar.
        // TODO 10: el orden del log debe respetar el orden de los argumentos.
    }

    /**
     * @return registro de lo procesado en el arranque
     */
    public List<String> log() {
        return log;
    }

    /**
     * @return true si el runner ya se ejecutó
     */
    public boolean ejecutado() {
        return ejecutado;
    }

    public static void main(String[] args) {
        var r = new Ej044CommandLineRunner();
        r.run("--seed=true", "--region=eu");
        System.out.println(r.log());
    }
}
