package com.masterclass.api.b46_datacomp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Ejercicio 353 · Persistencia del componente: serialización Java, {@code transient} y versionado.
 *
 * <p>Teoría: {@code teoria/46_Componentes_Datos.md} (sección 3).
 *
 * <p>Un componente debe poder <em>guardar y restaurar su estado</em>. La serialización Java
 * convierte un objeto {@link Serializable} en un {@code byte[]} ({@link ObjectOutputStream}) y lo
 * reconstruye ({@link ObjectInputStream}) — un "round-trip". Los campos {@code transient} (como la
 * contraseña o la conexión) NO viajan: tras restaurar hay que reconectarlos. El
 * {@code serialVersionUID} versiona el formato.
 */
public final class Ej353ComponentSerialization {

    private Ej353ComponentSerialization() {
    }

    /**
     * Serializa un objeto a su representación binaria.
     *
     * @param componente objeto a serializar (debe ser {@link Serializable})
     * @return los bytes del objeto, o {@code null} si es null o no es serializable
     */
    public static byte[] serializar(Object componente) {
        // TODO 1: si componente es null o NO es instancia de Serializable, devuelve null.
        // TODO 2: crea un ByteArrayOutputStream (el "buffer" donde caerán los bytes).
        // TODO 3: envuélvelo en un ObjectOutputStream dentro de try-with-resources.
        // TODO 4: escribe el objeto con writeObject(componente) y deja que el try lo cierre (flush incluido).
        // TODO 5: devuelve baos.toByteArray(); si salta IOException, captúrala y devuelve null.
        return null;
    }

    /**
     * Reconstruye un objeto a partir de sus bytes.
     *
     * @param datos bytes producidos por {@link #serializar(Object)}
     * @return el objeto reconstruido, o {@code null} si datos es null o están corruptos
     */
    public static Object deserializar(byte[] datos) {
        // TODO 6: si datos es null, devuelve null.
        // TODO 7: crea un ByteArrayInputStream sobre los datos.
        // TODO 8: envuélvelo en un ObjectInputStream dentro de try-with-resources.
        // TODO 9: lee el objeto con readObject().
        // TODO 10: devuélvelo; si salta IOException o ClassNotFoundException (versión incompatible,
        //          bytes corruptos), captúrala y devuelve null en vez de propagarla.
        return null;
    }

    public static void main(String[] args) {
        ConfiguracionComponente cfg = new ConfiguracionComponente("jdbc:h2:mem", "sa", "secreto", 30);
        byte[] bytes = serializar(cfg);
        System.out.println("bytes = " + (bytes == null ? "null" : bytes.length));
        ConfiguracionComponente vuelta = (ConfiguracionComponente) deserializar(bytes);
        System.out.println(vuelta + " password=" + (vuelta == null ? "?" : vuelta.getPassword()));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: indica si un objeto es serializable (validación previa).
     */
    public static boolean esSerializable(Object obj) {
        // GUÍA: teoría 3 (solo se puede serializar lo que implementa Serializable).
        // 1. null -> false.
        // 2. obj instanceof Serializable.
        // PISTA: return obj instanceof Serializable;
        // OJO: el test pasa una ConfiguracionComponente (true) y un Object cualquiera (false).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esSerializable");
    }

    /**
     * Reto Extra 2: devuelve el tamaño en bytes del objeto serializado.
     */
    public static int tamanoSerializado(Object obj) {
        // GUÍA: teoría 3 (medir el coste de transporte/almacenamiento del estado).
        // 1. reutiliza serializar(obj).
        // 2. si devuelve null -> 0; si no -> su length.
        // PISTA: byte[] b = serializar(obj); return b == null ? 0 : b.length;
        // OJO: el test solo comprueba que es > 0 para una config real (no fijes un número exacto:
        //   el tamaño depende del JDK).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tamanoSerializado");
    }

    /**
     * Reto Extra 3: comprueba que la contraseña (transient) NO sobrevive al round-trip.
     */
    public static boolean passwordNoSeSerializa() {
        // GUÍA: teoría 3 (los campos transient NO se serializan; vuelven a su valor por defecto).
        // 1. crea una ConfiguracionComponente con password "secreto".
        // 2. haz round-trip: deserializar(serializar(cfg)).
        // 3. devuelve true si el password de la vuelta es null (no viajó).
        // PISTA: return vuelta.getPassword() == null;
        // OJO: el test espera true. La url/usuario SÍ vuelven; solo el password (transient) se pierde.
        // CULTURA: por esto los secretos van transient o cifrados (b30): un volcado en disco no
        //   debe contener credenciales en claro.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para passwordNoSeSerializa");
    }

    /**
     * Reto Extra 4: hace un round-trip genérico y devuelve el objeto reconstruido.
     */
    public static Object roundTrip(Serializable obj) {
        // GUÍA: teoría 3 (serializar + deserializar = copia por valor).
        // 1. devuelve deserializar(serializar(obj)).
        // PISTA: return deserializar(serializar(obj));
        // OJO: el test serializa una ConfiguracionComponente y comprueba que la vuelta es EQUALS
        //   a la original (equals ignora el password transient, por eso coinciden).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para roundTrip");
    }

    /**
     * Reto Extra 5: lee el {@code serialVersionUID} declarado de una clase serializable.
     */
    public static long serialVersionUidDe(Class<?> clase) {
        // GUÍA: teoría 3 (el UID versiona el formato; ObjectStreamClass lo expone).
        // 1. usa java.io.ObjectStreamClass.lookup(clase).
        // 2. devuelve su getSerialVersionUID(); si lookup es null (no serializable) -> 0.
        // PISTA: var osc = java.io.ObjectStreamClass.lookup(clase); return osc == null ? 0 : osc.getSerialVersionUID();
        // OJO: el test comprueba serialVersionUidDe(ConfiguracionComponente.class) == 46L (el que
        //   declaramos en la clase). Si no lo declararas, el JDK calcularía uno "frágil".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serialVersionUidDe");
    }

    /**
     * Reto Extra 6: serializa Y comprime con GZIP (estado más pequeño para transporte).
     */
    public static byte[] serializarComprimido(Object obj) {
        // GUÍA: teoría 3 (comprimir el volcado ahorra red/disco).
        // 1. obtén los bytes con serializar(obj) (null -> null).
        // 2. crea un ByteArrayOutputStream y envuélvelo en un GZIPOutputStream.
        // 3. escribe los bytes, CIERRA el gzip (el cierre vuelca el footer) y devuelve toByteArray.
        // PISTA: try (GZIPOutputStream g = new GZIPOutputStream(baos)) { g.write(bytes); }
        // OJO: el test comprueba que serializarComprimido(cfg) NO es null y que su round-trip con
        //   deserializarComprimido recupera la config. Cierra el gzip ANTES de toByteArray().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarComprimido");
    }

    /**
     * Reto Extra 7: descomprime (GZIP) y deserializa (operación inversa de la 6).
     */
    public static Object deserializarComprimido(byte[] datos) {
        // GUÍA: teoría 3 (inversa del reto 6).
        // 1. null -> null.
        // 2. envuelve un ByteArrayInputStream en un GZIPInputStream y lee TODOS sus bytes.
        // 3. pasa esos bytes descomprimidos a deserializar(...).
        // PISTA: byte[] crudo = new GZIPInputStream(new ByteArrayInputStream(datos)).readAllBytes();
        // OJO: el test hace deserializarComprimido(serializarComprimido(cfg)) y espera la config
        //   EQUALS a la original. readAllBytes() te ahorra el bucle de lectura.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para deserializarComprimido");
    }

    /**
     * Reto Extra 8: round-trip a TRAVÉS de un fichero (persistencia en disco).
     */
    public static Object serializarYLeerDeFichero(Object obj, File fichero) {
        // GUÍA: teoría 3 (enlaza con b26: el mismo ObjectOutputStream sobre un FileOutputStream).
        // 1. escribe los bytes de serializar(obj) en el fichero (Files.write o FileOutputStream).
        // 2. vuelve a leer los bytes del fichero (Files.readAllBytes) y pásalos a deserializar.
        // PISTA: java.nio.file.Files.write(fichero.toPath(), serializar(obj));
        //        return deserializar(java.nio.file.Files.readAllBytes(fichero.toPath()));
        // OJO: el test usa @TempDir para darte un fichero temporal y comprueba que la vuelta es
        //   EQUALS a la original. Captura la IOException y devuelve null si falla.
        // CULTURA: esto es exactamente persistir el estado del componente en disco (b26 ficheros).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarYLeerDeFichero");
    }

    /**
     * Reto Extra 9: clona un objeto por serialización (copia PROFUNDA / deep copy).
     */
    public static Object clonarPorSerializacion(Serializable obj) {
        // GUÍA: teoría 3 (un round-trip produce un objeto NUEVO e independiente: deep copy).
        // 1. devuelve deserializar(serializar(obj)).
        // PISTA: es como roundTrip, pero aquí lo que se comprueba es la IDENTIDAD.
        // OJO: el test exige que el clon NO sea el mismo objeto (clon != original) pero SÍ equals.
        //   La serialización copia el grafo entero, no solo la referencia (eso es "profunda").
        // CULTURA: shallow copy (clone por defecto) comparte sub-objetos; deep copy los duplica.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clonarPorSerializacion");
    }

    /**
     * Reto Extra 10: restaura un campo transient "reconectando" tras deserializar.
     */
    public static ConfiguracionComponente restaurarTransientReconectando(ConfiguracionComponente cfg) {
        // GUÍA: teoría 3 (los transient vuelven null; el componente debe "reconectar" su recurso).
        // 1. haz round-trip de cfg (su password volverá null).
        // 2. sobre la vuelta, setPassword("reconectado") simulando la reconexión.
        // 3. devuelve la config reconectada.
        // PISTA: ConfiguracionComponente v = (ConfiguracionComponente) roundTrip(cfg); v.setPassword("reconectado");
        // OJO: el test comprueba que tras esto getPassword() == "reconectado" (no null). El patrón
        //   real sería sobreescribir readObject() para reconectar solo; aquí lo hacemos a mano.
        // CULTURA: una conexión JDBC (b11) es transient por naturaleza: no se serializa, se reabre.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para restaurarTransientReconectando");
    }
}
