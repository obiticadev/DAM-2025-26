package modulo4_patrones_creacionales;

/**
 * ============================================================================
 * EJERCICIO 50: Factory Method — Notificaciones con Registro
 * ============================================================================
 * 📚 Teoría: teoria/04_Patrones_Creacionales.md - Sección 4.3
 *
 * CONTEXTO:
 * Un sistema de notificaciones necesita enviar mensajes por distintos
 * canales: Email, SMS, Push Notification. Usarás Factory Method para
 * desacoplar la creación del canal concreto.
 *
 * Además, implementarás un Factory Registry: un mapa estático que asocia
 * un String (tipo) con su factory correspondiente. Esto permite crear
 * notificaciones por nombre sin if/else encadenados.
 *
 * Arquitectura:
 * - Interfaz Notificacion: enviar(String destino, String mensaje).
 * - Clases: NotificacionEmail, NotificacionSMS, NotificacionPush.
 * - NotificacionFactory: factoryMethod estático con registro interno.
 *
 * RESTRICCIONES:
 * - El registro interno usa dos arrays paralelos: String[] tipos, CreadorFunc[] fabricas.
 * - Registrar cada tipo antes de usarlo.
 * - El cliente pide por String: NotificacionFactory.crear("email").
 * - Sin java.util ni java.util.function.
 * ============================================================================
 */
public class Ejercicio50_FactoryMethodNotificaciones {

    // TODO 1: Definir la interfaz Notificacion:
    //         void enviar(String destino, String mensaje);
    //         String getCanal();

    // TODO 2: Implementar tres clases concretas:
    //         NotificacionEmail: enviar() imprime "📧 Email a <destino>: <mensaje>"
    //         NotificacionSMS: enviar() imprime "📱 SMS a <destino>: <mensaje>"
    //         NotificacionPush: enviar() imprime "🔔 Push a <destino>: <mensaje>"
    //         Cada una retorna su canal en getCanal().

    // TODO 3: Crear la clase NotificacionFactory con:
    //         - Arrays estáticos: String[] tiposRegistrados, indicando el tipo.
    //         - Un método estático registrar(String tipo) que almacena el tipo.
    //         - Un método estático crear(String tipo) que según el tipo registrado
    //           instancia la clase correspondiente.
    //         - ALTERNATIVA: Si no quieres usar arrays, puedes usar un switch
    //           pero el espíritu del ejercicio es el registro dinámico.

    // TODO 4: Implementar un método estático listarCanales() que imprima
    //         todos los tipos de notificación registrados.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 50: Factory Notificaciones ===\n");

        // TODO 5: Registrar los tres tipos de notificación.
        //         Listar los canales disponibles.
        //         Crear notificaciones por nombre y enviar mensajes:
        //
        //         Notificacion n1 = NotificacionFactory.crear("email");
        //         n1.enviar("alice@mail.com", "Bienvenida al sistema");
        //
        //         Notificacion n2 = NotificacionFactory.crear("sms");
        //         n2.enviar("+34 600 123 456", "Código de verificación: 7721");
        //
        //         Notificacion n3 = NotificacionFactory.crear("push");
        //         n3.enviar("user_id_42", "Tienes 3 mensajes nuevos");
        //
        //         Probar tipo no registrado y capturar excepción.

        System.out.println("(Implementa los TODOs para ver resultados)");

        System.out.println("\n=== FIN EJERCICIO 50 ===");
    }
}
