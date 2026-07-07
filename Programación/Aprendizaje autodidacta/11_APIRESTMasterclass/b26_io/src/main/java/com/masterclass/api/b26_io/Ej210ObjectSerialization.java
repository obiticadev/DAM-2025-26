package com.masterclass.api.b26_io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Ejercicio 210 · Serialización nativa de objetos a fichero.
 *
 * <p>
 * {@code ObjectOutputStream.writeObject(obj)} convierte un objeto (y todo su
 * grafo de
 * referencias) en bytes que se guardan en un fichero;
 * {@code ObjectInputStream.readObject()} lo
 * reconstruye. El objeto debe implementar {@code Serializable}. Es la
 * persistencia "rápida" del
 * JDK (AD RA1.d, almacenar info) y la misma mecánica que viste enviar por
 * socket en {@code b29·Ej238}.
 *
 * <p>
 * Conceptos clave: {@code transient} (campos que NO se guardan),
 * {@code serialVersionUID}
 * (versión de la clase) y el aviso de seguridad: NO deserialices datos no
 * confiables.
 *
 * <p>
 * Teoría: {@code teoria/26_IO_Ficheros_NIO2.md} (sección 26.4).
 */
public final class Ej210ObjectSerialization {

    private Ej210ObjectSerialization() {
    }

    /** Punto inmutable serializable. */
    public record Punto(int x, int y) implements Serializable {
    }

    /** Objeto con campo {@code transient} que no debe persistir. */
    public static final class Credencial implements Serializable {
        private static final long serialVersionUID = 1L;
        public final String usuario;
        public final transient String password;

        public Credencial(String usuario, String password) {
            this.usuario = usuario;
            this.password = password;
        }
    }

    /** Grafo: una caja que referencia un Punto. */
    public record Caja(String etiqueta, Punto esquina) implements Serializable {
    }

    /** Clase NO serializable. */
    public static final class NoSerializable {
        public final int v;

        public NoSerializable(int v) {
            this.v = v;
        }
    }

    /**
     * Serializa un Punto a un fichero temporal y lo deserializa de vuelta.
     *
     * @param x coordenada x
     * @param y coordenada y
     * @return el Punto reconstruido (equals al original), o {@code null} si no se
     *         ha implementado
     */
    public static Punto serializarPunto(int x, int y) {
        // TODO 1: crea un fichero temporal.
        // TODO 2: escribe con try (ObjectOutputStream oos = new ObjectOutputStream(new
        // FileOutputStream(tmp))) { oos.writeObject(new Punto(x,y)); }.
        // TODO 3: lee con try (ObjectInputStream ois = new ObjectInputStream(new
        // FileInputStream(tmp))) { ... }.
        // TODO 4: recupera con (Punto) ois.readObject().
        // TODO 5: borra el temporal y devuelve el Punto (maneja IOException y
        // ClassNotFoundException).
        File tmp = null;
        try {
            tmp = File.createTempFile("ej210", ".bin");
            tmp.deleteOnExit();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tmp))) {
                oos.writeObject(new Punto(x, y));
                oos.flush();
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tmp))) {
                    return (Punto) ois.readObject();
                }
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        }
        return null;
    }

    /**
     * Serializa una lista de Strings (es Serializable) y la recupera.
     *
     * @param items lista a serializar
     * @return la lista reconstruida (equals a la original), o lista vacía si no se
     *         ha implementado
     */
    public static List<String> serializarLista(List<String> items) {
        // TODO 6: envuelve en ArrayList serializable: new java.util.ArrayList<>(items).
        // TODO 7: escríbela con ObjectOutputStream a un temporal.
        // TODO 8: léela con ObjectInputStream y castea a (List<String>).
        // TODO 9: borra el temporal.
        // TODO 10: devuelve la lista (List.of() es el centinela hasta implementarlo).
        List<String> lista = new ArrayList<>(items);
        File tmp = null;
        try {
            tmp = File.createTempFile("ej210", ".bin");
            tmp.deleteOnExit();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tmp))) {
                oos.writeObject(lista);
            }
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tmp))) {
                Object obj = ois.readObject();
                if (obj instanceof List<?> listaLeida) {
                    List<String> resultado = new ArrayList<>();
                    for (Object element : listaLeida) {
                        if (element instanceof String s) {
                            resultado.add(s);
                        } else {
                            return List.of();
                        }
                    }
                    return resultado;
                } else {
                    return List.of();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
            return List.of();
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("serializarPunto = " + serializarPunto(3, 4));
        System.out.println("serializarLista = " + serializarLista(List.of("a", "b")));
    }

    /**
     * Reto Extra 1: un campo transient no se serializa (vuelve null).
     * Formaliza el comportamiento esperado de un campo transient no se serializa
     * (vuelve null) dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @return true si tras serializar una Credencial, el password (transient) llega
     *         null
     */
    public static boolean transientNoPersiste() {
        // GUÍA: Comprueba la frontera entre el grafo de objetos en memoria y su
        // representación serializada. El contrato
        // depende de qué objetos son serializables y qué estado se conserva al
        // reconstruirlos.
        File tmp = null;
        try {
            tmp = File.createTempFile("ej210", ".bin");
            tmp.deleteOnExit();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tmp))) {
                oos.writeObject(new Credencial("User", "1234"));
            }
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tmp))) {
                Credencial c = (Credencial) ois.readObject();
                return c.password == null;
            }
        } catch (IOException | ClassNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
    }

    /**
     * Reto Extra 2: se puede serializar y recuperar un null.
     * Formaliza el comportamiento esperado de se puede serializar y recuperar un
     * null dentro de una
     * operación de E/S pequeña y verificable.
     *
     * @return true si writeObject(null) se recupera como null
     */
    public static boolean objetoNuloSeSerializa() {
        // GUÍA: Comprueba la frontera entre el grafo de objetos en memoria y su
        // representación serializada. El contrato
        // depende de qué objetos son serializables y qué estado se conserva al
        // reconstruirlos.
        File tmp = null;
        try {
            tmp = File.createTempFile("ej210", ".bin");
            tmp.deleteOnExit();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tmp))) {
                oos.writeObject(null);
            }
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tmp))) {
                return ois.readObject() == null;
            }
        } catch (IOException | ClassNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
    }

    /**
     * Reto Extra 3: un grafo de objetos (Caja a Punto) se serializa completo.
     * Formaliza el comportamiento esperado de un grafo de objetos (Caja a Punto) se
     * serializa completo
     * dentro de una operación de E/S pequeña y verificable.
     *
     * @return true si la Caja recuperada conserva su Punto interno
     */
    public static boolean grafoDeObjetosSeSerializa() {
        // GUÍA: Comprueba la frontera entre el grafo de objetos en memoria y su
        // representación serializada. El contrato
        // depende de qué objetos son serializables y qué estado se conserva al
        // reconstruirlos.
        File tmp = null;
        try {
            tmp = File.createTempFile("ej210", ".bin");
            tmp.deleteOnExit();
            Caja caja = new Caja("Etiqueta1", new Punto(10, 20));
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tmp))) {
                oos.writeObject(caja);
            }
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tmp))) {
                Caja cajaResult = (Caja) ois.readObject();
                return cajaResult.esquina.equals(caja.esquina);
            }
        } catch (IOException | ClassNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
    }

    /**
     * Reto Extra 4: serializar un objeto no Serializable lanza
     * NotSerializableException.
     * Formaliza el comportamiento esperado de serializar un objeto no Serializable
     * lanza
     * NotSerializableException dentro de una operación de E/S pequeña y
     * verificable.
     *
     * @return true si writeObject(new NoSerializable(1)) lanza
     *         NotSerializableException
     */
    public static boolean noSerializableLanzaExcepcion() {
        // GUÍA: Comprueba la frontera entre el grafo de objetos en memoria y su
        // representación serializada. El contrato
        // depende de qué objetos son serializables y qué estado se conserva al
        // reconstruirlos.
        File tmp = null;
        try {
            tmp = File.createTempFile("ej210", ".bin");
            tmp.deleteOnExit();
            NoSerializable noSerializable = new NoSerializable(99);
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tmp))) {
                oos.writeObject(noSerializable);
            }
            return false;
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
            return true;

        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
    }

    /**
     * Reto Extra 5: serializar a memoria produce más de 0 bytes.
     * Formaliza el comportamiento esperado de serializar a memoria produce más de 0
     * bytes dentro de una
     * operación de E/S pequeña y verificable.
     *
     * @return true si serializar un Punto a un ByteArrayOutputStream genera bytes
     *         &gt; 0
     */
    public static boolean tamanoSerializadoMayorQueCero() {
        // GUÍA: Comprueba la frontera entre el grafo de objetos en memoria y su
        // representación serializada. El contrato
        // depende de qué objetos son serializables y qué estado se conserva al
        // reconstruirlos.
        Punto punto = new Punto(10, 20);
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(punto);
            oos.flush();
            byte[] bytes = baos.toByteArray();

            return bytes.length > 0;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Reto Extra 6: un record viaja por serialización y vuelve equals.
     * Formaliza el comportamiento esperado de un record viaja por serialización y
     * vuelve equals dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @return true si el Punto deserializado equals al original (reutiliza
     *         serializarPunto)
     */
    public static boolean recordRoundTripIgual() {
        // GUÍA: Comprueba la frontera entre el grafo de objetos en memoria y su
        // representación serializada. El contrato
        // depende de qué objetos son serializables y qué estado se conserva al
        // reconstruirlos.
        Punto p = new Punto(10, 20);
        File tmp = null;
        try {
            tmp = File.createTempFile("ej210", ".bin");
            tmp.deleteOnExit();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tmp))) {
                oos.writeObject(p);
            }
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tmp))) {
                return p.equals((Punto) ois.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
    }

    /**
     * Reto Extra 7: clonar en profundidad copiando por serialización.
     * Formaliza el comportamiento esperado de clonar en profundidad copiando por
     * serialización dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @return true si el clon (serializar+deserializar) es equals pero NO la misma
     *         instancia
     */
    public static boolean clonarPorSerializacion() {
        // GUÍA: Comprueba la frontera entre el grafo de objetos en memoria y su
        // representación serializada. El contrato
        // depende de qué objetos son serializables y qué estado se conserva al
        // reconstruirlos.
        Credencial cred = new Credencial("User", "Pass");

        try {
            byte[] datos;

            try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(baos)) {

                oos.writeObject(cred);
                datos = baos.toByteArray();
            }

            try (ByteArrayInputStream bais = new ByteArrayInputStream(datos);
                    ObjectInputStream ois = new ObjectInputStream(bais)) {

                Credencial clon = (Credencial) ois.readObject();
                return cred.equals(clon) && cred != clon;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Reto Extra 8: una HashMap es serializable y se recupera igual.
     * Formaliza el comportamiento esperado de una HashMap es serializable y se
     * recupera igual dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @return número de entradas del mapa recuperado (igual a 2)
     */
    public static int mapSerializableConservaEntradas() {
        // GUÍA: Comprueba la frontera entre el grafo de objetos en memoria y su
        // representación serializada. El contrato
        // depende de qué objetos son serializables y qué estado se conserva al
        // reconstruirlos.
        HashMap<Integer, String> listMap = new HashMap<>();
        listMap.put(1, "Comida");
        listMap.put(2, "Cena");

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos)) {

            oos.writeObject(listMap);
            oos.flush();

            try (ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                    ObjectInputStream ois = new ObjectInputStream(bais)) {

                HashMap<Integer, String> recuperado = (HashMap<Integer, String>) ois.readObject();

                return recuperado.size();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Reto Extra 9: un Integer (envuelto como objeto) se serializa.
     * Formaliza el comportamiento esperado de un Integer (envuelto como objeto) se
     * serializa dentro de una
     * operación de E/S pequeña y verificable.
     *
     * @return el entero recuperado (igual a 99)
     */
    public static int enteroSerializable() {
        // GUÍA: Comprueba la frontera entre el grafo de objetos en memoria y su
        // representación serializada. El contrato
        // depende de qué objetos son serializables y qué estado se conserva al
        // reconstruirlos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para enteroSerializable");
    }

    /**
     * Reto Extra 10: escribir dos objetos en el mismo fichero y leerlos en orden.
     * Formaliza el comportamiento esperado de escribir dos objetos en el mismo
     * fichero y leerlos en orden
     * dentro de una operación de E/S pequeña y verificable.
     *
     * @return la suma de x de dos Puntos escritos y leídos secuencialmente (igual a
     *         1+2 = 3)
     */
    public static int dosObjetosEnElMismoFichero() {
        // GUÍA: Comprueba la frontera entre el grafo de objetos en memoria y su
        // representación serializada. El contrato
        // depende de qué objetos son serializables y qué estado se conserva al
        // reconstruirlos.
        throw new UnsupportedOperationException(
                "TODO: Implementar la lógica del reto extra para dosObjetosEnElMismoFichero");
    }

    /**
     * Reto Extra 11: el campo transient SÍ tiene valor antes de serializar (solo se
     * pierde al guardar).
     * Formaliza el comportamiento esperado de el campo transient SÍ tiene valor
     * antes de serializar (solo
     * se pierde al guardar) dentro de una operación de E/S pequeña y verificable.
     *
     * @return true si password no es null en el objeto original pero sí lo es tras
     *         el round-trip
     */
    public static boolean transientPresenteAntesAusenteDespues() {
        // GUÍA: Comprueba la frontera entre el grafo de objetos en memoria y su
        // representación serializada. El contrato
        // depende de qué objetos son serializables y qué estado se conserva al
        // reconstruirlos.
        throw new UnsupportedOperationException(
                "TODO: Implementar la lógica del reto extra para transientPresenteAntesAusenteDespues");
    }

    /**
     * Reto Extra 12: una lista grande sobrevive al round-trip con todos sus
     * elementos.
     * Formaliza el comportamiento esperado de una lista grande sobrevive al
     * round-trip con todos sus
     * elementos dentro de una operación de E/S pequeña y verificable.
     *
     * @return tamaño de la lista recuperada tras serializar 1000 elementos (igual a
     *         1000)
     */
    public static int listaGrandeRoundTrip() {
        // GUÍA: Comprueba la frontera entre el grafo de objetos en memoria y su
        // representación serializada. El contrato
        // depende de qué objetos son serializables y qué estado se conserva al
        // reconstruirlos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para listaGrandeRoundTrip");
    }
}
