package bloque5;

import java.io.*;
import java.util.Objects;

/**
 * EJERCICIO 65 — Serialización Manual (Data) vs Automática (Objetos)
 * 📋 ENTRA EN EXAMEN — Pilar 4 (Serialización) + Pilar 2 (Data I/O)
 * Teoria: teoria/05_Serializacion.md + teoria/03B_ArchivosBinarios.md
 *
 * Contexto: A veces el examen pide "serializar" pero usando DataOutputStream
 * en lugar de ObjectOutputStream. Es crucial entender la diferencia:
 *
 * 1. Serialización Automática (ObjectOutputStream): Java hace todo el trabajo.
 *    La clase DEBE implementar Serializable. Guarda metadatos de la clase.
 *
 * 2. Serialización Manual (DataOutputStream): Tú escribes campo a campo.
 *    La clase NO necesita ser Serializable. El fichero resultante es más
 *    pequeño porque no incluye metadatos, pero es más frágil (el orden importa).
 */
public class Ej65_SerializacionConData {

    /**
     * Clase interna que NO implementa Serializable.
     */
    public static class Vehiculo {
        private String matricula;
        private double precio;
        private int anio;

        public Vehiculo(String matricula, double precio, int anio) {
            this.matricula = matricula;
            this.precio = precio;
            this.anio = anio;
        }

        // Getters
        public String getMatricula() { return matricula; }
        public double getPrecio() { return precio; }
        public int getAnio() { return anio; }

        @Override
        public String toString() {
            return "Vehiculo{" + "matricula='" + matricula + '\'' + ", precio=" + precio + ", anio=" + anio + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vehiculo vehiculo = (Vehiculo) o;
            return Double.compare(vehiculo.precio, precio) == 0 &&
                    anio == vehiculo.anio &&
                    Objects.equals(matricula, vehiculo.matricula);
        }
    }

    // ═══════════════════════════════════════════════
    //  PARTE 1: Serialización Manual (DataOutputStream)
    // ═══════════════════════════════════════════════

    /**
     * Serializa "manualmente" el Vehiculo campo a campo usando DataOutputStream.
     * El orden debe ser: matricula (UTF), precio (double), anio (int).
     *
     * @param ruta     ruta del fichero
     * @param vehiculo vehículo a guardar
     * @throws IOException si hay error
     */
    public static void guardarVehiculoManual(String ruta, Vehiculo vehiculo) throws IOException {
        // TODO 1: DataOutputStream + FileOutputStream con try-with-resources.
        //         Escribir getMatricula() con writeUTF.
        //         Escribir getPrecio() con writeDouble.
        //         Escribir getAnio() con writeInt.
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Deserializa "manualmente" el Vehiculo campo a campo usando DataInputStream.
     *
     * @param ruta ruta del fichero
     * @return vehículo reconstruido
     * @throws IOException si hay error
     */
    public static Vehiculo cargarVehiculoManual(String ruta) throws IOException {
        // TODO 2: DataInputStream + FileInputStream con try-with-resources.
        //         Leer UTF, Double, Int en el MISMO orden.
        //         Devolver new Vehiculo() con los datos leídos.
        return null;
    }

    // ═══════════════════════════════════════════════
    //  PARTE 2: Comportamiento ante errores
    // ═══════════════════════════════════════════════

    /**
     * Intenta guardar el Vehiculo usando ObjectOutputStream.
     * Como Vehiculo no implementa Serializable, debe lanzar una excepción específica.
     *
     * @param ruta     ruta del fichero
     * @param vehiculo vehículo a guardar
     * @return true si lanza la excepción esperada (NotSerializableException), false en caso contrario
     */
    public static boolean verificarFalloSerializable(String ruta, Vehiculo vehiculo) {
        // TODO 3: Dentro de un try-catch, intentar ObjectOutputStream + writeObject(vehiculo).
        //         Si lanza NotSerializableException, devolver true.
        //         Si termina con éxito (no debería) o lanza otra, devolver false.
        return false;
    }

    // ═══════════════════════════════════════════════
    //  PARTE 3: Tamaño de ficheros
    // ═══════════════════════════════════════════════

    /**
     * Devuelve el tamaño en bytes del fichero.
     *
     * @param ruta ruta del fichero
     * @return tamaño en bytes
     */
    public static long obtenerTamanoFichero(String ruta) {
        // TODO 4: Devolver new File(ruta).length().
        return 0;
    }

    /**
     * Guarda el Producto (que sí es Serializable) usando ObjectOutputStream.
     *
     * @param ruta     ruta del fichero
     * @param producto producto a guardar
     * @throws IOException si hay error
     */
    public static void guardarProductoAuto(String ruta, Producto producto) throws IOException {
        // TODO 5: ObjectOutputStream + writeObject(producto).
        throw new UnsupportedOperationException("TODO 5 no implementado");
    }

    // ═══════════════════════════════════════════════
    //  ZONA DE EJECUCIÓN — Pulsa Run aquí
    // ═══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 65: Serialización Manual vs Automática ===\n");

        String dir = "temp/bloque5";
        new File(dir).mkdirs();

        Vehiculo coche = new Vehiculo("1234-ABC", 15500.0, 2020);
        String rutaData = dir + "/vehiculo_data.bin";

        // 1. Guardar manual
        guardarVehiculoManual(rutaData, coche);
        System.out.println("Guardado manual OK.");

        // 2. Cargar manual
        Vehiculo coche2 = cargarVehiculoManual(rutaData);
        System.out.println("Cargado manual: " + coche2);
        System.out.println("¿Son iguales?   " + coche.equals(coche2));

        // 3. Fallo Serializable
        String rutaObj = dir + "/vehiculo_obj.dat";
        boolean falloCorrecto = verificarFalloSerializable(rutaObj, coche);
        System.out.println("¿Falla ObjectOutputStream sin Serializable? " + falloCorrecto);

        // 4. Comparar tamaños
        Producto prod = new Producto("Teclado", 45.50, 10);
        String rutaProdObj = dir + "/producto_obj.dat";
        guardarProductoAuto(rutaProdObj, prod);

        System.out.println("\n--- COMPARATIVA DE TAMAÑOS ---");
        System.out.println("Vehiculo (DataOutputStream): " + obtenerTamanoFichero(rutaData) + " bytes (sólo datos)");
        System.out.println("Producto (ObjectOutputStream): " + obtenerTamanoFichero(rutaProdObj) + " bytes (datos + metadatos clase)");
    }
}
