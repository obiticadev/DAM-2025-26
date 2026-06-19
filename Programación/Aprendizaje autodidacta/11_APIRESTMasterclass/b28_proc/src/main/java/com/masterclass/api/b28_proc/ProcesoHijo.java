package com.masterclass.api.b28_proc;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de APOYO del bloque b28 (no es un ejercicio). Hace dos cosas:
 *
 * <ol>
 *   <li>Es el <b>proceso hijo</b> que lanzan los ejercicios: su {@code main} ejecuta una acción
 *       según el primer argumento (echo, exit, suma, upper, env, pwd, err, sleep...). Usar un
 *       proceso Java hijo conocido (en vez de comandos del SO como {@code dir}/{@code ls}) hace
 *       los tests DETERMINISTAS y PORTABLES entre Windows y Linux.</li>
 *   <li>Ofrece utilidades para construir el comando que arranca este mismo hijo con el
 *       <b>mismo java y classpath</b> que ejecuta los tests ({@link #comando(String...)}).</li>
 * </ol>
 *
 * <p>Teoría: {@code teoria/28_Multiproceso_IPC.md}.
 */
public final class ProcesoHijo {

    private ProcesoHijo() {
    }

    /**
     * Punto de entrada del proceso hijo. La acción se elige con el primer argumento:
     * <ul>
     *   <li>{@code echo <texto>}: imprime el texto por stdout.</li>
     *   <li>{@code exit <n>}: termina con el código de salida n.</li>
     *   <li>{@code suma <a> <b>}: imprime a+b por stdout.</li>
     *   <li>{@code upper}: lee una línea de stdin y la imprime en mayúsculas (IPC).</li>
     *   <li>{@code lineas}: lee stdin hasta EOF e imprime el número de líneas.</li>
     *   <li>{@code env <NOMBRE>}: imprime el valor de la variable de entorno (o "NULL").</li>
     *   <li>{@code pwd}: imprime el directorio de trabajo (user.dir).</li>
     *   <li>{@code err <texto>}: imprime el texto por stderr (stdout queda vacío).</li>
     *   <li>{@code outerr}: imprime "OUT" por stdout y "ERR" por stderr.</li>
     *   <li>{@code sleep <ms>}: duerme ms milisegundos y termina con 0.</li>
     *   <li>(cualquier otra cosa): imprime "HIJO-OK" y termina con 0.</li>
     * </ul>
     */
    public static void main(String[] args) throws Exception {
        String accion = args.length > 0 ? args[0] : "ok";
        switch (accion) {
            case "echo" -> System.out.println(args[1]);
            case "exit" -> System.exit(Integer.parseInt(args[1]));
            case "suma" -> System.out.println(Integer.parseInt(args[1]) + Integer.parseInt(args[2]));
            case "upper" -> {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
                String linea = br.readLine();
                System.out.println(linea == null ? "" : linea.toUpperCase());
            }
            case "lineas" -> {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
                long n = br.lines().count();
                System.out.println(n);
            }
            case "env" -> {
                String v = System.getenv(args[1]);
                System.out.println(v == null ? "NULL" : v);
            }
            case "pwd" -> System.out.println(System.getProperty("user.dir"));
            case "err" -> System.err.println(args[1]);
            case "outerr" -> {
                System.out.println("OUT");
                System.err.println("ERR");
            }
            case "sleep" -> {
                Thread.sleep(Long.parseLong(args[1]));
                System.exit(0);
            }
            default -> System.out.println("HIJO-OK");
        }
    }

    /** Ruta absoluta del ejecutable java actual (el mismo que corre los tests). */
    public static String javaBin() {
        String exe = System.getProperty("os.name").toLowerCase().contains("win") ? "java.exe" : "java";
        return Path.of(System.getProperty("java.home"), "bin", exe).toString();
    }

    /** Directorio del classpath donde vive esta clase (robusto bajo surefire, no usa java.class.path). */
    public static String classpath() {
        try {
            return new File(ProcesoHijo.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI()).getAbsolutePath();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Construye el comando que arranca este proceso hijo con los argumentos dados:
     * {@code [javaBin, -cp, classpath, com.masterclass.api.b28_proc.ProcesoHijo, args...]}.
     *
     * @param args argumentos para el {@code main} del hijo (ver arriba)
     * @return lista lista para pasar a {@code new ProcessBuilder(lista)}
     */
    public static List<String> comando(String... args) {
        List<String> cmd = new ArrayList<>(List.of(javaBin(), "-cp", classpath(), ProcesoHijo.class.getName()));
        cmd.addAll(List.of(args));
        return cmd;
    }
}
