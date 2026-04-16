package modulo4_patrones_creacionales;

/**
 * ============================================================================
 * EJERCICIO 47: Singleton — Configuración Global del Sistema
 * ============================================================================
 * 📚 Teoría: teoria/04_Patrones_Creacionales.md - Sección 4.2
 *
 * CONTEXTO:
 * Uno de los usos más comunes del Singleton es un gestor de configuración
 * global. La aplicación lee un archivo de propiedades UNA VEZ y permite
 * acceder a la configuración desde cualquier parte del sistema.
 *
 * Implementa un ConfigManager Singleton que:
 * - Almacene pares clave-valor en un array manual (sin HashMap).
 * - Permita establecer y obtener propiedades.
 * - Cargue valores por defecto al crearse.
 * - Sea inmutable una vez inicializado (opcional: freeze).
 *
 * RESTRICCIONES:
 * - Usar el patrón Bill Pugh (Holder idiom) para la implementación Singleton.
 * - Almacenamiento interno: dos arrays paralelos String[] claves y String[] valores.
 * - Capacidad máxima de 50 propiedades.
 * - Sin usar java.util.Properties, HashMap ni ninguna colección.
 * ============================================================================
 */
public class Ejercicio47_SingletonConfiguracion {

    private String[] claves;
    private String[] valores;
    private int count;
    private boolean congelado;
    private static final int MAX_PROPS = 50;

    // TODO 1: Constructor PRIVADO que:
    //         - Inicialice los arrays claves[] y valores[] con MAX_PROPS.
    //         - count = 0, congelado = false.
    //         - Cargue propiedades por defecto:
    //           "app.name" → "MasterclassApp"
    //           "app.version" → "1.0.0"
    //           "db.host" → "localhost"
    //           "db.port" → "5432"
    //           "log.level" → "INFO"

    // TODO 2: Implementar el patrón Bill Pugh (Holder):
    //         private static class Holder {
    //             private static final Ejercicio47_SingletonConfiguracion INSTANCIA =
    //                 new Ejercicio47_SingletonConfiguracion();
    //         }
    //         public static Ejercicio47_SingletonConfiguracion getInstance() {
    //             return Holder.INSTANCIA;
    //         }
    //         Esto es lazy Y thread-safe sin synchronized.

    public void setProperty(String clave, String valor) {
        // TODO 3: Si está congelado, lanzar IllegalStateException("Configuración congelada").
        //         Buscar si la clave ya existe (recorrer claves[]): si existe, actualizar.
        //         Si no existe, añadir al final (verificar capacidad).
    }

    public String getProperty(String clave) {
        // TODO 4: Buscar la clave en el array de claves. Si la encuentra,
        //         retornar el valor correspondiente. Si no, retornar null.
        return null;
    }

    public String getProperty(String clave, String valorDefecto) {
        String val = getProperty(clave);
        return val != null ? val : valorDefecto;
    }

    public void congelar() {
        // TODO 5: Marcar congelado = true. A partir de aquí, setProperty lanza excepción.
        //         Imprimir "[CONFIG] Configuración congelada. No se permiten más cambios."
    }

    public void imprimirTodas() {
        System.out.println("=== Configuración (" + count + " propiedades) ===");
        for (int i = 0; i < count; i++) {
            System.out.println("  " + claves[i] + " = " + valores[i]);
        }
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 47: Singleton Configuración ===\n");

        // Obtener la instancia (se crean las propiedades por defecto)
        // Ejercicio47_SingletonConfiguracion config = Ejercicio47_SingletonConfiguracion.getInstance();
        // config.imprimirTodas();

        // Modificar una propiedad
        // config.setProperty("log.level", "DEBUG");
        // config.setProperty("app.env", "production");
        // config.imprimirTodas();

        // Verificar que es la misma instancia
        // Ejercicio47_SingletonConfiguracion config2 = Ejercicio47_SingletonConfiguracion.getInstance();
        // System.out.println("Misma instancia: " + (config == config2)); // true

        // Congelar y probar inmutabilidad
        // config.congelar();
        // try { config.setProperty("nuevo", "valor"); }
        // catch (IllegalStateException e) { System.out.println("✅ " + e.getMessage()); }

        System.out.println("(Implementa los TODOs y descomenta el código de prueba)");

        System.out.println("\n=== FIN EJERCICIO 47 ===");
    }
}
