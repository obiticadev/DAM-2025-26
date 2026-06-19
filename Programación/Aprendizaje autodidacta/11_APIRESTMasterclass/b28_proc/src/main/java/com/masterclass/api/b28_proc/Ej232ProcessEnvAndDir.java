package com.masterclass.api.b28_proc;

/**
 * Ejercicio 232 · Entorno y directorio de trabajo del proceso hijo.
 *
 * <p>Cuando lanzas un proceso puedes controlar su <b>contexto</b>: las variables de entorno
 * ({@code ProcessBuilder.environment()}, un {@code Map} modificable) y el <b>directorio de
 * trabajo</b> ({@code directory(File)}). Por defecto el hijo HEREDA el entorno y el directorio
 * del padre. Modificar el mapa del {@code ProcessBuilder} NO toca el entorno del proceso padre.
 * Es PSP RA1: configurar la ejecución de subprocesos.
 *
 * <p>Teoría: {@code teoria/28_Multiproceso_IPC.md} (sección 28.6).
 */
public final class Ej232ProcessEnvAndDir {

    private Ej232ProcessEnvAndDir() {
    }

    /**
     * Define una variable de entorno para el hijo y devuelve el valor que el hijo lee.
     *
     * @param nombre nombre de la variable
     * @param valor  valor a asignar
     * @return el valor leído por el hijo (== valor), o {@code null} si no se ha implementado
     */
    public static String leerVariableEntorno(String nombre, String valor) {
        // TODO 1: ProcessBuilder pb = new ProcessBuilder(ProcesoHijo.comando("env", nombre)).
        // TODO 2: añade la variable: pb.environment().put(nombre, valor) (mapa modificable del hijo).
        // TODO 3: arranca el proceso.
        // TODO 4: lee su stdout (el hijo imprime el valor de la variable, o "NULL" si no existe).
        // TODO 5: espera con waitFor() y devuelve la línea leída (maneja excepciones).
        return null;
    }

    /**
     * Arranca el hijo en un directorio de trabajo concreto y comprueba que el hijo lo reporta.
     *
     * @return true si el "pwd" del hijo coincide con el directorio configurado, false si no se ha implementado
     */
    public static boolean procesoCorreEnDirectorio() {
        // TODO 6: crea un directorio temporal (Files.createTempDirectory).
        // TODO 7: ProcessBuilder pb = ...("pwd"); pb.directory(dir.toFile()).
        // TODO 8: arranca y lee el stdout (el hijo imprime System.getProperty("user.dir")).
        // TODO 9: compara la ruta leída con la del directorio de forma CANÓNICA
        //         (new File(salida).getCanonicalFile().equals(dir.toFile().getCanonicalFile())) para evitar
        //         diferencias de symlinks/mayúsculas entre SO.
        // TODO 10: borra el temporal y devuelve el resultado de la comparación.
        return false;
    }

    public static void main(String[] args) {
        System.out.println("leerVariableEntorno = " + leerVariableEntorno("MI_VAR", "valor123"));
        System.out.println("procesoCorreEnDirectorio = " + procesoCorreEnDirectorio());
    }

    /**
     * Reto Extra 1: una variable no definida se lee como "NULL" (convención del hijo).
     * @return true si el hijo reporta "NULL" para una variable que no existe
     */
    public static boolean variableNoDefinidaEsNull() {
        // GUÍA: lanza ProcesoHijo.comando("env","NO_EXISTE_XYZ") SIN definirla; el hijo imprime "NULL".
        //   return "NULL".equals(salida).
        // (el hijo imprime "NULL" cuando System.getenv devuelve null).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para variableNoDefinidaEsNull");
    }

    /**
     * Reto Extra 2: el hijo HEREDA el entorno del padre (p.ej. PATH existe).
     * @return true si el hijo ve la variable PATH del padre (no "NULL")
     */
    public static boolean heredaPathDelPadre() {
        // GUÍA: lanza ProcesoHijo.comando("env","PATH") sin tocar el entorno; el hijo hereda el del padre,
        //   así que PATH no será "NULL". return !"NULL".equals(salida).
        // CULTURA: por defecto un hijo arranca con una copia del entorno del padre.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para heredaPathDelPadre");
    }

    /**
     * Reto Extra 3: modificar el entorno del ProcessBuilder NO cambia el del proceso padre.
     * @return true si tras pb.environment().put(...) System.getenv del padre sigue sin esa variable
     */
    public static boolean modificarEntornoNoAfectaAlPadre() {
        // GUÍA: pb.environment().put("VAR_SOLO_HIJO","x"); comprueba que System.getenv("VAR_SOLO_HIJO")
        //   del PADRE sigue siendo null. return System.getenv("VAR_SOLO_HIJO") == null.
        // CULTURA: el mapa de environment() es una COPIA para el hijo; el entorno del padre es inmutable.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para modificarEntornoNoAfectaAlPadre");
    }

    /**
     * Reto Extra 4: se puede ELIMINAR una variable heredada para el hijo.
     * @return true si, tras remove("PATH") del entorno del hijo, el hijo ve "NULL" en PATH
     */
    public static boolean eliminarVariableHeredada() {
        // GUÍA: pb.environment().remove("PATH"); lanza ProcesoHijo.comando("env","PATH"); el hijo verá "NULL".
        //   return "NULL".equals(salida).
        // OJO: no afecta a encontrar java porque ProcesoHijo.comando usa la ruta ABSOLUTA del ejecutable.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para eliminarVariableHeredada");
    }

    /**
     * Reto Extra 5: sin configurar directorio, el hijo corre en el del padre.
     * @return true si el "pwd" del hijo (sin pb.directory) coincide con user.dir del padre
     */
    public static boolean directorioPorDefectoEsDelPadre() {
        // GUÍA: lanza ProcesoHijo.comando("pwd") SIN llamar a pb.directory(); compara la salida con
        //   System.getProperty("user.dir") del padre (canónicamente). return iguales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para directorioPorDefectoEsDelPadre");
    }

    /**
     * Reto Extra 6: environment() devuelve un mapa no vacío.
     * @return true si pb.environment() tiene al menos una entrada
     */
    public static boolean environmentEsMapaNoVacio() {
        // GUÍA: ProcessBuilder pb = new ProcessBuilder(); return !pb.environment().isEmpty();
        // (no hace falta arrancar nada; el mapa ya viene poblado con el entorno heredado).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para environmentEsMapaNoVacio");
    }

    /**
     * Reto Extra 7: una variable con espacios en el valor se transmite intacta.
     * @return el valor "hola mundo" leído por el hijo de una variable que lo contiene
     */
    public static String variableConEspacios() {
        // GUÍA: return leerVariableEntorno("FRASE", "hola mundo");  // el valor del env admite espacios.
        // CONTRASTE: los espacios en el VALOR de una variable no son problema (no se "trocean" como un comando).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para variableConEspacios");
    }

    /**
     * Reto Extra 8: definir dos variables y leer la segunda.
     * @return el valor de la segunda variable (== "segundo")
     */
    public static String dosVariablesIndependientes() {
        // GUÍA: pb.environment().put("A","primero"); pb.environment().put("B","segundo");
        //   lanza ProcesoHijo.comando("env","B"); return salida;  // "segundo".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para dosVariablesIndependientes");
    }

    /**
     * Reto Extra 9: directory() devuelve el directorio configurado.
     * @return true si pb.directory(dir) y luego pb.directory() devuelven el mismo directorio
     */
    public static boolean directoryDevuelveLoConfigurado() {
        // GUÍA: Path d = Files.createTempDirectory(...); ProcessBuilder pb = new ProcessBuilder();
        //   pb.directory(d.toFile()); return pb.directory().equals(d.toFile());  (borra el temporal).
        // (configuración pura; no requiere arrancar el proceso).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para directoryDevuelveLoConfigurado");
    }

    /**
     * Reto Extra 10: combinar inheritIO con un directorio de trabajo funciona.
     * @return true si un proceso con inheritIO() y directory() configurados termina con código 0
     */
    public static boolean inheritIOConDirectorioFunciona() {
        // GUÍA: Path d = Files.createTempDirectory(...); pb = new ProcessBuilder(ProcesoHijo.comando("ok"));
        //   pb.directory(d.toFile()); pb.inheritIO(); return pb.start().waitFor() == 0;  (borra el temporal).
        // CULTURA: configuras el contexto completo del hijo (E/S + cwd + entorno) antes de start().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para inheritIOConDirectorioFunciona");
    }
}
