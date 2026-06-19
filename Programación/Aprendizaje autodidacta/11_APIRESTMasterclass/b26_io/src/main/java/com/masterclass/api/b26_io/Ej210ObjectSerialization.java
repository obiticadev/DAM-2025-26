package com.masterclass.api.b26_io;

import java.io.Serializable;
import java.util.List;

/**
 * Ejercicio 210 · Serialización nativa de objetos a fichero.
 *
 * <p>{@code ObjectOutputStream.writeObject(obj)} convierte un objeto (y todo su grafo de
 * referencias) en bytes que se guardan en un fichero; {@code ObjectInputStream.readObject()} lo
 * reconstruye. El objeto debe implementar {@code Serializable}. Es la persistencia "rápida" del
 * JDK (AD RA1.d, almacenar info) y la misma mecánica que viste enviar por socket en {@code b29·Ej238}.
 *
 * <p>Conceptos clave: {@code transient} (campos que NO se guardan), {@code serialVersionUID}
 * (versión de la clase) y el aviso de seguridad: NO deserialices datos no confiables.
 *
 * <p>Teoría: {@code teoria/26_IO_Ficheros_NIO2.md} (sección 26.4).
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
     * @return el Punto reconstruido (equals al original), o {@code null} si no se ha implementado
     */
    public static Punto serializarPunto(int x, int y) {
        // TODO 1: crea un fichero temporal.
        // TODO 2: escribe con try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tmp))) { oos.writeObject(new Punto(x,y)); }.
        // TODO 3: lee con try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tmp))) { ... }.
        // TODO 4: recupera con (Punto) ois.readObject().
        // TODO 5: borra el temporal y devuelve el Punto (maneja IOException y ClassNotFoundException).
        return null;
    }

    /**
     * Serializa una lista de Strings (es Serializable) y la recupera.
     *
     * @param items lista a serializar
     * @return la lista reconstruida (equals a la original), o lista vacía si no se ha implementado
     */
    public static List<String> serializarLista(List<String> items) {
        // TODO 6: envuelve en ArrayList serializable: new java.util.ArrayList<>(items).
        // TODO 7: escríbela con ObjectOutputStream a un temporal.
        // TODO 8: léela con ObjectInputStream y castea a (List<String>).
        // TODO 9: borra el temporal.
        // TODO 10: devuelve la lista (List.of() es el centinela hasta implementarlo).
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println("serializarPunto = " + serializarPunto(3, 4));
        System.out.println("serializarLista = " + serializarLista(List.of("a", "b")));
    }

    /**
     * Reto Extra 1: un campo transient no se serializa (vuelve null).
     * @return true si tras serializar una Credencial, el password (transient) llega null
     */
    public static boolean transientNoPersiste() {
        // GUÍA: serializa new Credencial("ana","secreto"); al recuperarla, usuario=="ana" pero
        // password==null. return recibida.password == null && "ana".equals(recibida.usuario).
        // CULTURA: transient marca lo que NO debe guardarse (secretos, cachés, datos derivados).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para transientNoPersiste");
    }

    /**
     * Reto Extra 2: se puede serializar y recuperar un null.
     * @return true si writeObject(null) se recupera como null
     */
    public static boolean objetoNuloSeSerializa() {
        // GUÍA: oos.writeObject(null); Object o = ois.readObject(); return o == null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para objetoNuloSeSerializa");
    }

    /**
     * Reto Extra 3: un grafo de objetos (Caja -> Punto) se serializa completo.
     * @return true si la Caja recuperada conserva su Punto interno
     */
    public static boolean grafoDeObjetosSeSerializa() {
        // GUÍA: serializa new Caja("c", new Punto(1,2)); comprueba recuperada.esquina().equals(new Punto(1,2)).
        // CULTURA: writeObject sigue TODAS las referencias en cascada (ojo con grafos enormes o ciclos).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para grafoDeObjetosSeSerializa");
    }

    /**
     * Reto Extra 4: serializar un objeto no Serializable lanza NotSerializableException.
     * @return true si writeObject(new NoSerializable(1)) lanza NotSerializableException
     */
    public static boolean noSerializableLanzaExcepcion() {
        // GUÍA: try { oos.writeObject(new NoSerializable(1)); return false; }
        //   catch (java.io.NotSerializableException e) { return true; }
        // OJO: es subclase de IOException; captúrala específica.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para noSerializableLanzaExcepcion");
    }

    /**
     * Reto Extra 5: serializar a memoria produce más de 0 bytes.
     * @return true si serializar un Punto a un ByteArrayOutputStream genera bytes &gt; 0
     */
    public static boolean tamanoSerializadoMayorQueCero() {
        // GUÍA: ByteArrayOutputStream bos = new ByteArrayOutputStream();
        //   try (ObjectOutputStream oos = new ObjectOutputStream(bos)) { oos.writeObject(new Punto(1,1)); }
        //   return bos.toByteArray().length > 0;
        // CULTURA: el mismo writeObject vale para fichero, memoria o socket; solo cambia el stream.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tamanoSerializadoMayorQueCero");
    }

    /**
     * Reto Extra 6: un record viaja por serialización y vuelve equals.
     * @return true si el Punto deserializado equals al original (reutiliza serializarPunto)
     */
    public static boolean recordRoundTripIgual() {
        // GUÍA: return new Punto(7,9).equals(serializarPunto(7,9));
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para recordRoundTripIgual");
    }

    /**
     * Reto Extra 7: clonar en profundidad copiando por serialización.
     * @return true si el clon (serializar+deserializar) es equals pero NO la misma instancia
     */
    public static boolean clonarPorSerializacion() {
        // GUÍA: serializa y deserializa un Punto; el resultado equals al original pero clon != original
        // (es una instancia nueva). return clon.equals(orig) && clon != orig.
        // CULTURA: truco clásico de "deep clone" sin escribir copy-constructores (con coste de E/S).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clonarPorSerializacion");
    }

    /**
     * Reto Extra 8: una HashMap es serializable y se recupera igual.
     * @return número de entradas del mapa recuperado (== 2)
     */
    public static int mapSerializableConservaEntradas() {
        // GUÍA: serializa un new HashMap<>(Map.of("a",1,"b",2)) (HashMap implementa Serializable),
        // recupéralo y return mapa.size();  // 2.
        // OJO: Map.of(...) devuelve un mapa inmutable también serializable; usa cualquiera.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapSerializableConservaEntradas");
    }

    /**
     * Reto Extra 9: un Integer (envuelto como objeto) se serializa.
     * @return el entero recuperado (== 99)
     */
    public static int enteroSerializable() {
        // GUÍA: oos.writeObject(Integer.valueOf(99)); int r = (Integer) ois.readObject(); return r.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para enteroSerializable");
    }

    /**
     * Reto Extra 10: escribir dos objetos en el mismo fichero y leerlos en orden.
     * @return la suma de x de dos Puntos escritos y leídos secuencialmente (== 1+2 = 3)
     */
    public static int dosObjetosEnElMismoFichero() {
        // GUÍA: oos.writeObject(new Punto(1,0)); oos.writeObject(new Punto(2,0));  (en el mismo stream)
        //   luego ois.readObject() dos veces, EN EL MISMO ORDEN; suma sus x.
        // OJO/CUIDADO: hay que leerlos en el mismo orden en que se escribieron.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para dosObjetosEnElMismoFichero");
    }

    /**
     * Reto Extra 11: el campo transient SÍ tiene valor antes de serializar (solo se pierde al guardar).
     * @return true si password no es null en el objeto original pero sí lo es tras el round-trip
     */
    public static boolean transientPresenteAntesAusenteDespues() {
        // GUÍA: Credencial orig = new Credencial("u","p"); comprueba orig.password != null;
        // tras serializar+deserializar, recuperada.password == null. return (orig.password != null && recuperada.password == null).
        // CULTURA: transient no borra el campo en memoria; solo lo excluye de la serialización.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para transientPresenteAntesAusenteDespues");
    }

    /**
     * Reto Extra 12: una lista grande sobrevive al round-trip con todos sus elementos.
     * @return tamaño de la lista recuperada tras serializar 1000 elementos (== 1000)
     */
    public static int listaGrandeRoundTrip() {
        // GUÍA: crea una ArrayList con 1000 Strings, serialízala, recupérala y return size().
        // CULTURA: la serialización maneja colecciones enteras; pero ojo con objetos enormes (memoria/tiempo).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para listaGrandeRoundTrip");
    }
}
