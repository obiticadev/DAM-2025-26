package modulo5_patrones_estructurales;

/**
 * ============================================================================
 * EJERCICIO 67: Proxy — Proxy de Protección con Roles
 * ============================================================================
 * 📚 Teoría: teoria/05_Patrones_Estructurales.md - Sección 5.5
 *
 * CONTEXTO:
 * Un sistema de archivos sensible debe verificar los permisos del usuario
 * antes de permitir acceso. El Proxy intercepta las llamadas y verifica
 * que el usuario tenga el rol necesario antes de delegar al objeto real.
 *
 * Roles: ADMIN (todo), EDITOR (leer+escribir), VIEWER (solo leer).
 *
 * Implementa:
 * - Interfaz Documento: leer(), escribir(String contenido), eliminar().
 * - DocumentoReal: implementación que realmente lee/escribe/elimina.
 * - DocumentoProxy: verifica permisos antes de delegar.
 *
 * RESTRICCIONES:
 * - El Proxy recibe el rol del usuario en su constructor.
 * - VIEWER: solo puede leer. Escritura/eliminación → AccessDeniedException.
 * - EDITOR: puede leer y escribir. Eliminación → AccessDeniedException.
 * - ADMIN: puede hacer todo.
 * - El Proxy logea cada intento de acceso (permitido o denegado).
 * ============================================================================
 */
public class Ejercicio67_ProxyProteccion {

    enum Rol { ADMIN, EDITOR, VIEWER }

    // TODO 1: Definir interfaz Documento:
    //         String leer()
    //         void escribir(String contenido)
    //         void eliminar()
    //         String getNombre()

    // TODO 2: Implementar DocumentoReal:
    //         - Campos: nombre, contenido (String).
    //         - leer(): retorna el contenido.
    //         - escribir(): actualiza el contenido.
    //         - eliminar(): pone contenido a null e imprime "[REAL] Documento eliminado".

    // TODO 3: Implementar DocumentoProxy:
    //         - Implementa Documento.
    //         - Campos: DocumentoReal real, Rol rolUsuario, String nombreUsuario.
    //         - leer(): logea "[PROXY] <usuario>(VIEWER) leyendo <documento>" y delega.
    //         - escribir(): si rol es VIEWER → lanzar RuntimeException("Acceso denegado").
    //                       si rol es EDITOR o ADMIN → logear y delegar.
    //         - eliminar(): solo ADMIN puede. Otros → excepción.
    //         - Cada operación logea: "[PROXY] PERMITIDO: ..." o "[PROXY] DENEGADO: ..."

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 67: Proxy de Protección ===\n");

        // TODO 4: Crear el documento real y tres proxies con diferentes roles:
        //         DocumentoReal real = new DocumentoReal("plan_secreto.txt", "Top Secret...");
        //
        //         Documento admin = new DocumentoProxy(real, Rol.ADMIN, "Alice");
        //         Documento editor = new DocumentoProxy(real, Rol.EDITOR, "Bob");
        //         Documento viewer = new DocumentoProxy(real, Rol.VIEWER, "Charlie");
        //
        //         // Admin: todo
        //         System.out.println(admin.leer());
        //         admin.escribir("Nuevo contenido secreto");
        //
        //         // Editor: leer y escribir
        //         System.out.println(editor.leer());
        //         editor.escribir("Editado por Bob");
        //         try { editor.eliminar(); }
        //         catch (RuntimeException e) { System.out.println("❌ " + e.getMessage()); }
        //
        //         // Viewer: solo leer
        //         System.out.println(viewer.leer());
        //         try { viewer.escribir("Hack!"); }
        //         catch (RuntimeException e) { System.out.println("❌ " + e.getMessage()); }

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 67 ===");
    }
}
