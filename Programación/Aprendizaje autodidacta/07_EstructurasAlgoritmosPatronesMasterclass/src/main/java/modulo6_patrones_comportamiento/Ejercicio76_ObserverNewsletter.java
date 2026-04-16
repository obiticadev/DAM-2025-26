package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 76: Observer — Sistema de Eventos / Newsletter
 * ============================================================================
 * 📚 Teoría: teoria/06_Patrones_Comportamiento.md - Sección 6.2
 *
 * CONTEXTO:
 * Una plataforma de noticias tiene canales temáticos (Tecnología, Deportes,
 * Ciencia). Los usuarios se suscriben a los canales que les interesan y
 * reciben notificaciones automáticas cuando se publica un artículo.
 *
 * Implementa:
 * - Interfaz Observer: actualizar(String canal, String mensaje).
 * - Interfaz Subject: registrar(Observer), eliminar(Observer), notificar().
 * - CanalNoticias (Subject concreto): con nombre y lista de suscriptores.
 * - Usuario (Observer concreto): recibe y muestra las notificaciones.
 *
 * RESTRICCIONES:
 * - Array de observers manual (max 20).
 * - Un usuario puede suscribirse a múltiples canales.
 * - Al publicar, solo se notifica a los suscritos a ESE canal.
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio76_ObserverNewsletter {

    interface Observer {
        // TODO 1: void actualizar(String canal, String mensaje)
        //         String getNombre()
    }

    interface Subject {
        // TODO 2: void registrar(Observer o)
        //         void eliminar(Observer o)
        //         void notificarTodos(String mensaje)
    }

    // TODO 3: Implementar CanalNoticias (Subject):
    //         - Campos: String nombre, Observer[] suscriptores, int numSubs.
    //         - registrar(): añadir al array, imprimir "[Canal] <usuario> suscrito a <canal>".
    //         - eliminar(): buscar y eliminar del array (mover elementos).
    //         - notificarTodos(): recorrer suscriptores y llamar actualizar().
    //         - publicar(String titulo): imprime "[Canal] Nueva publicación: <titulo>"
    //           y llama notificarTodos().

    // TODO 4: Implementar Usuario (Observer):
    //         - Campos: String nombre, String[] buzón (max 50), int numMensajes.
    //         - actualizar(): almacena mensaje en buzón e imprime:
    //           "  📬 <nombre> recibió de [<canal>]: <mensaje>"
    //         - verBuzon(): muestra todos los mensajes recibidos.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 76: Observer Newsletter ===\n");

        // TODO 5:
        //         CanalNoticias tech = new CanalNoticias("Tecnología");
        //         CanalNoticias deportes = new CanalNoticias("Deportes");
        //
        //         Usuario alice = new Usuario("Alice");
        //         Usuario bob = new Usuario("Bob");
        //         Usuario charlie = new Usuario("Charlie");
        //
        //         tech.registrar(alice);
        //         tech.registrar(bob);
        //         deportes.registrar(bob);
        //         deportes.registrar(charlie);
        //
        //         tech.publicar("Java 25 lanzado!");
        //         deportes.publicar("Real Madrid gana la Champions");
        //
        //         tech.eliminar(bob);
        //         tech.publicar("Nuevo framework revolucionario");
        //         // Bob NO recibe esta última de tech

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 76 ===");
    }
}
