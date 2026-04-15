package bloque5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * EJERCICIO 29 — Serializacion con Herencia
 * Teoria: teoria/05_Serializacion.md (seccion 7)
 *
 * Contexto: El restaurante tiene distintos tipos de empleados (camarero,
 * cocinero, gerente) que heredan de una clase base Empleado. Necesitas
 * serializar y deserializar objetos polimorficos.
 */
public class Ej29_SerializarHerencia {

    /** Clase base serializable. */
    public static class Empleado implements Serializable {
        private static final long serialVersionUID = 1L;
        private String nombre;
        private double salarioBase;

        public Empleado(String nombre, double salarioBase) {
            this.nombre = nombre;
            this.salarioBase = salarioBase;
        }
        public String getNombre() { return nombre; }
        public double getSalarioBase() { return salarioBase; }
        public String getTipo() { return "Empleado"; }

        @Override public String toString() {
            return String.format("%s{nombre='%s', salario=%.2f}", getTipo(), nombre, salarioBase);
        }
    }

    /** Camarero con propinas. */
    public static class Camarero extends Empleado {
        private static final long serialVersionUID = 1L;
        private double propinas;

        public Camarero(String nombre, double salarioBase, double propinas) {
            super(nombre, salarioBase);
            this.propinas = propinas;
        }
        public double getPropinas() { return propinas; }
        @Override public String getTipo() { return "Camarero"; }
    }

    /** Cocinero con especialidad. */
    public static class Cocinero extends Empleado {
        private static final long serialVersionUID = 1L;
        private String especialidad;

        public Cocinero(String nombre, double salarioBase, String especialidad) {
            super(nombre, salarioBase);
            this.especialidad = especialidad;
        }
        public String getEspecialidad() { return especialidad; }
        @Override public String getTipo() { return "Cocinero"; }
    }

    /**
     * Serializa un empleado (puede ser subclase).
     *
     * @param ruta     ruta del fichero
     * @param empleado empleado a serializar
     * @throws IOException si hay error
     */
    public static void guardarEmpleado(String ruta, Empleado empleado) throws IOException {
        // TODO 1: Crear ObjectOutputStream con try-with-resources.
        //         Escribir con writeObject(). El polimorfismo se preserva.
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Deserializa un empleado desde fichero.
     *
     * @param ruta ruta del fichero
     * @return empleado deserializado (puede ser subclase)
     * @throws IOException            si hay error
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    public static Empleado cargarEmpleado(String ruta) throws IOException, ClassNotFoundException {
        // TODO 2: Crear ObjectInputStream. Leer y hacer cast a Empleado.
        return null;
    }

    /**
     * Verifica que un Camarero serializado se recupera como Camarero.
     *
     * @param ruta ruta temporal
     * @return true si el objeto deserializado es instanceof Camarero
     * @throws IOException            si hay error
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    public static boolean polimorfismoPreservado(String ruta) throws IOException, ClassNotFoundException {
        // TODO 3: Crear un Camarero. Guardar. Cargar.
        //         Devolver true si el resultado es instanceof Camarero.
        return false;
    }

    /**
     * Serializa una lista mixta de empleados (Empleado, Camarero, Cocinero)
     * y la deserializa, verificando que los tipos se preservan.
     *
     * @param ruta ruta del fichero
     * @return array con el tipo (getTipo()) de cada empleado deserializado
     * @throws IOException            si hay error
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    public static String[] tiposDeserializados(String ruta) throws IOException, ClassNotFoundException {
        // TODO 4: Crear lista con: un Empleado, un Camarero y un Cocinero.
        //         Serializar la lista. Deserializar.
        //         Devolver array con getTipo() de cada uno.
        return new String[0];
    }

    /**
     * Calcula la nomina total de una lista de empleados serializada.
     * Para Camarero: salarioBase + propinas. Para otros: salarioBase.
     *
     * @param ruta ruta del fichero (contiene List<Empleado>)
     * @return nomina total
     * @throws IOException            si hay error
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    @SuppressWarnings("unchecked")
    public static double nominaTotal(String ruta) throws IOException, ClassNotFoundException {
        // TODO 5: Deserializar List<Empleado>. Recorrer.
        //         Si es Camarero, sumar salarioBase + propinas.
        //         Si no, sumar salarioBase.
        return 0.0;
    }

    /**
     * Cuenta cuantos empleados de cada tipo hay en un fichero.
     * Devuelve array [empleados, camareros, cocineros].
     *
     * @param ruta ruta del fichero (contiene List<Empleado>)
     * @return array de 3 contadores
     * @throws IOException            si hay error
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    @SuppressWarnings("unchecked")
    public static int[] contarPorTipo(String ruta) throws IOException, ClassNotFoundException {
        // TODO 6: Deserializar. Contar con instanceof.
        //         Cuidado: Camarero ES Empleado, asi que comprobar subclases primero.
        return new int[]{0, 0, 0};
    }

    /**
     * Indica si las subclases heredan Serializable de la clase padre.
     *
     * @return siempre true
     */
    public static boolean subclaseHeredaSerializable() {
        // TODO 7: Devolver true. Conceptual: si el padre es Serializable,
        //         las subclases tambien lo son automaticamente.
        return false;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws Exception {
        System.out.println("=== Ejercicio 29: Serializar Herencia ===\n");

        String dir = "temp/bloque5";
        new File(dir).mkdirs();

        Camarero cam = new Camarero("Ana", 1500, 200);
        guardarEmpleado(dir + "/emp.dat", cam);
        Empleado leido = cargarEmpleado(dir + "/emp.dat");
        System.out.println("Leido: " + leido + " (tipo real: " + leido.getClass().getSimpleName() + ")");

        System.out.println("Polimorfismo: " + polimorfismoPreservado(dir + "/pol.dat"));

        // Lista mixta
        List<Empleado> equipo = new ArrayList<>();
        equipo.add(new Empleado("Base", 1000));
        equipo.add(new Camarero("Ana", 1500, 200));
        equipo.add(new Cocinero("Pedro", 1800, "Paella"));
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dir + "/equipo.dat"))) {
            oos.writeObject(equipo);
        }
        System.out.println("Nomina total: " + nominaTotal(dir + "/equipo.dat"));
        System.out.println("Hereda Serializable: " + subclaseHeredaSerializable());
    }
}
