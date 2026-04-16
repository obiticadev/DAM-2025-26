package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 90: Mediator — Chat Room
 * ============================================================================
 * 📚 Teoría: teoria/06_Patrones_Comportamiento.md - Sección 6.9
 *
 * CONTEXTO:
 * Un chat room donde los usuarios no se envían mensajes directamente.
 * Todos los mensajes pasan por el Mediator (la sala) que los distribuye.
 * Esto evita N*(N-1)/2 conexiones directas.
 *
 * Implementa:
 * - Interfaz ChatMediator: enviarMensaje(String msg, Usuario emisor).
 * - Clase SalaChat (Mediator): registra usuarios y distribuye mensajes.
 * - Clase Usuario: nombre, mediador, enviar(String msg), recibir(String msg).
 *
 * RESTRICCIONES:
 * - Un mensaje enviado se entrega a TODOS los usuarios EXCEPTO al emisor.
 * - La sala puede tener max 20 usuarios.
 * - Cada usuario guarda su historial (max 50 mensajes).
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio90_MediatorChat {

    interface ChatMediator {
        // TODO 1: void registrar(Usuario90 usuario)
        //         void enviarMensaje(String mensaje, Usuario90 emisor)
    }

    // TODO 2: Implementar SalaChat:
    //         - Usuario90[] usuarios (max 20), int numUsuarios.
    //         - registrar(): añadir al array.
    //         - enviarMensaje(): recorrer usuarios, si no es el emisor → recibir().

    // TODO 3: Implementar Usuario90:
    //         - String nombre, ChatMediator mediator.
    //         - String[] historial (max 50), int numMensajes.
    //         - enviar(String msg): imprime y delega al mediator.
    //         - recibir(String msg, String deQuien): almacena en historial e imprime.
    //         - verHistorial(): muestra todo el historial.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 90: Mediator Chat ===\n");

        // TODO 4:
        //         SalaChat sala = new SalaChat("Desarrollo");
        //         Usuario90 alice = new Usuario90("Alice", sala);
        //         Usuario90 bob = new Usuario90("Bob", sala);
        //         Usuario90 charlie = new Usuario90("Charlie", sala);
        //
        //         sala.registrar(alice);
        //         sala.registrar(bob);
        //         sala.registrar(charlie);
        //
        //         alice.enviar("¡Hola a todos!");
        //         bob.enviar("Hey Alice! ¿Cómo va el sprint?");
        //         charlie.enviar("El deploy está listo 🚀");

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 90 ===");
    }
}
