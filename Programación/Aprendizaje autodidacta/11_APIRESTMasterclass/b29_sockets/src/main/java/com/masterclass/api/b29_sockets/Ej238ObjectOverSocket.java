package com.masterclass.api.b29_sockets;

import java.io.Serializable;
import java.util.List;

/**
 * Ejercicio 238 · Enviar objetos Java por el socket con serialización.
 *
 * <p>Hasta ahora has mandado texto. Pero por un socket puedes mandar <b>objetos enteros</b>:
 * {@code ObjectOutputStream.writeObject(obj)} convierte el objeto (y todo su grafo) en bytes,
 * y {@code ObjectInputStream.readObject()} lo reconstruye al otro lado. El objeto debe
 * implementar {@code Serializable}. Es la base de la serialización nativa (enlaza con b26·Ej210)
 * y un primer paso hacia RMI / formatos binarios.
 *
 * <p>Aviso del mundo real: la serialización nativa es cómoda pero <b>insegura</b> si confías en
 * datos externos (gadget chains); en producción se prefieren JSON (b02) o formatos como Protobuf.
 * Aquí la usamos para entender los conceptos del BOE (PSP RA3.g, AD RA1).
 *
 * <p>Teoría: {@code teoria/29_Sockets_Red.md} (sección 29.6).
 */
public final class Ej238ObjectOverSocket {

    private Ej238ObjectOverSocket() {
    }

    /** Punto inmutable serializable (los records son serializables si implementan la interfaz). */
    public record Punto(int x, int y) implements Serializable {
    }

    /** Objeto con un campo {@code transient} que NO debe persistir al serializar. */
    public static final class Credencial implements Serializable {
        private static final long serialVersionUID = 1L;
        public final String usuario;
        public final transient String password; // transient => no se serializa

        public Credencial(String usuario, String password) {
            this.usuario = usuario;
            this.password = password;
        }
    }

    /** Grafo: una caja que referencia a un Punto (se serializa en cascada). */
    public record Caja(String etiqueta, Punto esquina) implements Serializable {
    }

    /** Clase que NO implementa Serializable: intentar enviarla debe fallar. */
    public static final class NoSerializable {
        public final int valor;

        public NoSerializable(int valor) {
            this.valor = valor;
        }
    }

    /**
     * Envía un Punto por el socket y devuelve el Punto reconstruido al otro lado.
     *
     * @param x coordenada x
     * @param y coordenada y
     * @return el Punto recibido de vuelta (equals al enviado)
     */
    public static Punto enviarPunto(int x, int y) {
        // TODO 1: levanta un ServerSocket(0); el hilo servidor acepta y, sobre el socket, crea
        //         ObjectInputStream in = new ObjectInputStream(s.getInputStream()) y
        //         ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream()).
        // TODO 2: CUIDADO con el orden: crear ObjectOutputStream escribe una cabecera; para evitar
        //         interbloqueos crea PRIMERO el OutputStream (y flush) y LUEGO el InputStream en cada lado.
        // TODO 3: el servidor lee el objeto con (Punto) in.readObject() y lo reescribe con out.writeObject(p); out.flush().
        // TODO 4: el cliente crea sus Object streams (mismo orden), escribe new Punto(x,y) y hace flush.
        // TODO 5: el cliente lee la respuesta con (Punto) in.readObject().
        // TODO 6: cierra todo y devuelve el Punto (maneja IOException y ClassNotFoundException).
        return null;
    }

    /**
     * Envía una lista de Strings (es Serializable) y la recupera intacta.
     *
     * @param items lista a enviar
     * @return la lista reconstruida (equals a la enviada)
     */
    public static List<String> enviarLista(List<String> items) {
        // TODO 7: reutiliza el servidor de "eco de objetos" (extrae arrancarEcoDeObjetos()).
        // TODO 8: envuelve en ArrayList serializable: out.writeObject(new java.util.ArrayList<>(items)).
        // TODO 9: el cliente lee (List<String>) in.readObject().
        // TODO 10: devuelve la lista recibida (List.of() es el centinela hasta implementarlo).
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println("enviarPunto(3,4) = " + enviarPunto(3, 4));
        System.out.println("enviarLista([a,b]) = " + enviarLista(List.of("a", "b")));
    }

    /**
     * Reto Extra 1: un campo transient NO viaja (se reconstruye como null).
     * @return true si tras enviar una Credencial, el password (transient) llega null
     */
    public static boolean transientNoSeSerializa() {
        // GUÍA: teoría 29.6 + b26·Ej210. Envía new Credencial("ana","secreto"). Al reconstruirla,
        // recibida.usuario.equals("ana") pero recibida.password == null (transient no se serializa).
        // return recibida.password == null && "ana".equals(recibida.usuario);
        // CULTURA: transient es justo para NO enviar secretos/cachés por la red.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para transientNoSeSerializa");
    }

    /**
     * Reto Extra 2: se puede serializar y recuperar un null.
     * @return true si writeObject(null) se reconstruye como null al otro lado
     */
    public static boolean objetoNuloViaja() {
        // GUÍA: out.writeObject(null); en el otro lado Object o = in.readObject(); return o == null.
        // PISTA: ObjectOutputStream codifica null como un marcador especial; no lanza excepción.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para objetoNuloViaja");
    }

    /**
     * Reto Extra 3: un grafo de objetos (Caja -&gt; Punto) viaja completo.
     * @return true si la Caja recibida conserva su Punto interno intacto
     */
    public static boolean grafoDeObjetosViaja() {
        // GUÍA: envía new Caja("c", new Punto(1,2)). writeObject serializa en cascada los objetos
        // referenciados. Comprueba recibida.esquina().equals(new Punto(1,2)).
        // CULTURA: la serialización sigue TODAS las referencias; cuidado con grafos enormes o cíclicos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para grafoDeObjetosViaja");
    }

    /**
     * Reto Extra 4: escribir dos veces el MISMO objeto preserva la identidad al leer.
     * @return true si los dos objetos leídos son la misma instancia (==)
     */
    public static boolean referenciaCompartidaSePreserva() {
        // GUÍA: Punto p = new Punto(5,5); out.writeObject(p); out.writeObject(p);
        //   Object a = in.readObject(); Object b = in.readObject(); return a == b;
        // PISTA: ObjectOutputStream cachea por referencia: la 2ª vez escribe una "back-reference",
        // no una copia. Por eso a == b (misma instancia reconstruida).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para referenciaCompartidaSePreserva");
    }

    /**
     * Reto Extra 5: reset() rompe la caché de referencias y fuerza una copia nueva.
     * @return true si, tras out.reset() entre los dos writeObject, los objetos leídos son distintos (!=)
     */
    public static boolean resetRompeLaComparticion() {
        // GUÍA: out.writeObject(p); out.reset(); out.writeObject(p);
        //   return a != b;  // reset olvida lo ya escrito → la 2ª vez se serializa entero otra vez
        // OJO/CUIDADO: contrasta con el reto 4. reset es necesario en servidores de larga vida para
        // que no acumulen referencias a objetos viejos (fuga de memoria).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resetRompeLaComparticion");
    }

    /**
     * Reto Extra 6: enviar un objeto no Serializable lanza NotSerializableException.
     * @return true si writeObject(new NoSerializable(1)) lanza NotSerializableException
     */
    public static boolean claseNoSerializableLanzaExcepcion() {
        // GUÍA: NoSerializable no implementa Serializable. try { out.writeObject(new NoSerializable(1)); return false; }
        //   catch (java.io.NotSerializableException e) { return true; }
        // OJO: NotSerializableException es subclase de IOException; captúrala específica.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para claseNoSerializableLanzaExcepcion");
    }

    /**
     * Reto Extra 7: un Integer (envuelto como Object) viaja y se recupera.
     * @return el entero recibido (== 99)
     */
    public static int enteroComoObjeto() {
        // GUÍA: out.writeObject(Integer.valueOf(99)); int r = (Integer) in.readObject(); return r.
        // PISTA: los wrappers (Integer, Long, String...) ya son Serializable.
        // OJO: el test espera 99.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para enteroComoObjeto");
    }

    /**
     * Reto Extra 8: un array de bytes viaja como objeto y se recupera intacto.
     * @return los bytes recibidos (iguales a {10,20,30})
     */
    public static byte[] arrayDeBytesComoObjeto() {
        // GUÍA: byte[] son objetos serializables. out.writeObject(new byte[]{10,20,30});
        //   byte[] r = (byte[]) in.readObject(); return r.
        // OJO: el test compara con assertArrayEquals({10,20,30}).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para arrayDeBytesComoObjeto");
    }

    /**
     * Reto Extra 9: serializar un objeto produce más de cero bytes (a memoria, sin socket).
     * @return true si serializar un Punto a un ByteArrayOutputStream genera bytes &gt; 0
     */
    public static boolean tamanoSerializadoMayorQueCero() {
        // GUÍA: no hace falta socket. ByteArrayOutputStream bos = new ByteArrayOutputStream();
        //   try (ObjectOutputStream oos = new ObjectOutputStream(bos)) { oos.writeObject(new Punto(1,1)); }
        //   return bos.toByteArray().length > 0;
        // CULTURA: el mismo writeObject sirve para socket, fichero o memoria: solo cambia el stream destino.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tamanoSerializadoMayorQueCero");
    }

    /**
     * Reto Extra 10: un record viaja por el socket y vuelve equals al original.
     * @return true si el Punto recibido equals al enviado (reutiliza enviarPunto)
     */
    public static boolean recordRoundTripIgual() {
        // GUÍA: una línea — return new Punto(7,9).equals(enviarPunto(7,9));
        // CULTURA: los records definen equals por componentes (b01·Ej011/Ej022), así que el round-trip
        // es trivial de verificar. Por eso son ideales como DTOs serializables.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para recordRoundTripIgual");
    }
}
