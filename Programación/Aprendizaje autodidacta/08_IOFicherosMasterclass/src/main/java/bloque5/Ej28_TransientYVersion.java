package bloque5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * EJERCICIO 28 — Transient y serialVersionUID
 * Teoria: teoria/05_Serializacion.md (secciones 2, 6)
 *
 * Contexto: Al serializar usuarios del sistema del restaurante,
 * hay campos sensibles (contrasena) que no deben guardarse.
 * Exploraras el modificador transient y la importancia del versionado.
 */
public class Ej28_TransientYVersion {

    /**
     * Clase Usuario con campo transient para la contrasena.
     */
    public static class Usuario implements Serializable {
        private static final long serialVersionUID = 1L;

        private String nombre;
        private String email;
        private transient String password;
        private int intentosFallidos;

        public Usuario(String nombre, String email, String password) {
            this.nombre = nombre;
            this.email = email;
            this.password = password;
            this.intentosFallidos = 0;
        }

        public String getNombre() { return nombre; }
        public String getEmail() { return email; }
        public String getPassword() { return password; }
        public int getIntentosFallidos() { return intentosFallidos; }
        public void setIntentosFallidos(int intentosFallidos) { this.intentosFallidos = intentosFallidos; }

        @Override
        public String toString() {
            return String.format("Usuario{nombre='%s', email='%s', password='%s', intentos=%d}",
                    nombre, email, password, intentosFallidos);
        }
    }

    /**
     * Serializa un usuario a fichero.
     *
     * @param ruta    ruta del fichero
     * @param usuario usuario a serializar
     * @throws IOException si hay error
     */
    public static void guardarUsuario(String ruta, Usuario usuario) throws IOException {
        // TODO 1: Crear ObjectOutputStream con try-with-resources.
        //         Escribir el usuario con writeObject().
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Deserializa un usuario desde fichero.
     *
     * @param ruta ruta del fichero
     * @return usuario deserializado
     * @throws IOException            si hay error
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    public static Usuario cargarUsuario(String ruta) throws IOException, ClassNotFoundException {
        // TODO 2: Crear ObjectInputStream con try-with-resources.
        //         Leer con readObject() y hacer cast.
        return null;
    }

    /**
     * Verifica que la contrasena se pierde al serializar (por ser transient).
     *
     * @param ruta ruta temporal
     * @return true si password es null tras deserializar
     * @throws IOException            si hay error
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    public static boolean passwordSePierde(String ruta) throws IOException, ClassNotFoundException {
        // TODO 3: Crear usuario con password no null. Guardar. Cargar.
        //         Devolver true si getPassword() es null.
        return false;
    }

    /**
     * Verifica que los campos no transient se preservan.
     *
     * @param ruta ruta temporal
     * @return true si nombre y email se preservan
     * @throws IOException            si hay error
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    public static boolean datosSePreservan(String ruta) throws IOException, ClassNotFoundException {
        // TODO 4: Crear usuario "Ana", "ana@mail.com", "sec123".
        //         Guardar. Cargar. Verificar nombre y email.
        return false;
    }

    /**
     * Verifica que intentosFallidos (no transient) se preserva.
     *
     * @param ruta ruta temporal
     * @return true si intentosFallidos se preserva
     * @throws IOException            si hay error
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    public static boolean contadorSePreserva(String ruta) throws IOException, ClassNotFoundException {
        // TODO 5: Crear usuario, setIntentosFallidos(3). Guardar. Cargar.
        //         Devolver true si getIntentosFallidos() == 3.
        return false;
    }

    /**
     * Devuelve el valor de serialVersionUID de la clase Usuario.
     *
     * @return el serialVersionUID
     */
    public static long obtenerSerialVersionUID() {
        // TODO 6: Devolver el valor de Usuario.serialVersionUID (es 1L).
        return 0;
    }

    /**
     * Genera un informe de los campos transient de Usuario.
     * Formato: "Campos transient: password"
     * (hardcodeado ya que es un ejercicio conceptual)
     *
     * @return informe
     */
    public static String informeTransient() {
        // TODO 7: Devolver "Campos transient: password"
        return "";
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws Exception {
        System.out.println("=== Ejercicio 28: Transient y Version ===\n");

        String dir = "temp/bloque5";
        new File(dir).mkdirs();

        Usuario u = new Usuario("Carlos", "carlos@mail.com", "secreto123");
        u.setIntentosFallidos(2);
        System.out.println("Original: " + u);

        guardarUsuario(dir + "/user.dat", u);
        Usuario u2 = cargarUsuario(dir + "/user.dat");
        System.out.println("Deserializado: " + u2);

        System.out.println("Password se pierde: " + passwordSePierde(dir + "/pw.dat"));
        System.out.println("Datos se preservan: " + datosSePreservan(dir + "/dp.dat"));
        System.out.println("Contador se preserva: " + contadorSePreserva(dir + "/cnt.dat"));
        System.out.println("serialVersionUID: " + obtenerSerialVersionUID());
        System.out.println(informeTransient());
    }
}
