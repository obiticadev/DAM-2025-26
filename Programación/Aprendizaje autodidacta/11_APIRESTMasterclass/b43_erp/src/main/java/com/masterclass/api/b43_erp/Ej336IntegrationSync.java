package com.masterclass.api.b43_erp;

import java.util.List;
import java.util.Map;

/**
 * Ejercicio 336 · Sincronización idempotente entre la API propia y el ERP.
 *
 * <p>Teoría: {@code teoria/43_SGE_Integracion.md} (sección 6).
 *
 * <p>El reto final de la integración: mantener dos sistemas <b>en sincronía</b> sin duplicar ni
 * corromper datos. La clave es la <b>idempotencia</b>: ejecutar la sincronización dos veces debe
 * dejar el destino exactamente igual que ejecutarla una vez (clave de negocio + <em>upsert</em>,
 * no <em>insert</em> ciego). Aquí calculas el <b>plan</b> (qué dar de alta, qué modificar, qué
 * dejar) y lo aplicas con un upsert idempotente. Conecta hashing (b30), reintentos (b27) y
 * actualización parcial (b07).
 */
public final class Ej336IntegrationSync {

    private Ej336IntegrationSync() {
    }

    /**
     * Compara origen y destino por {@code idExterno} y devuelve el plan de sincronización.
     *
     * @param origen  registros del sistema fuente (no null)
     * @param destino registros ya presentes en el ERP (no null)
     * @return plan con las listas de altas, modificaciones y sin-cambios (ids, en orden de origen)
     */
    public static SyncPlan planificarSync(List<ClienteErp> origen, List<ClienteErp> destino) {
        // TODO 1: si origen o destino son null -> IllegalArgumentException.
        // TODO 2: indexa el destino por idExterno (reutiliza indexarPorId del reto 2 o un Map).
        // TODO 3: crea tres listas mutables: altas, modificaciones, sinCambios.
        // TODO 4: recorre cada cliente del origen.
        // TODO 5: busca su par en destino por idExterno.
        // TODO 6: si no existe en destino -> su id va a 'altas'.
        // TODO 7: si existe y es record-equals (mismo contenido) -> a 'sinCambios'.
        // TODO 8: si existe pero difiere -> a 'modificaciones'.
        // TODO 9: construye y devuelve new SyncPlan(altas, modificaciones, sinCambios).
        return null;
    }

    /**
     * Aplica un upsert idempotente: si el idExterno ya existe lo reemplaza, si no lo añade.
     *
     * @param destino lista actual del ERP (no se muta; se devuelve una nueva)
     * @param nuevo   registro a insertar o actualizar
     * @return nueva lista con el upsert aplicado
     */
    public static List<ClienteErp> aplicarUpsert(List<ClienteErp> destino, ClienteErp nuevo) {
        // TODO 1: si destino o nuevo son null -> IllegalArgumentException.
        // TODO 2: crea una lista nueva (copia mutable de destino).
        // TODO 3: busca el índice del elemento con el mismo idExterno que 'nuevo'.
        // TODO 4: si existe -> set() en ese índice (REEMPLAZA, no añade): esto da idempotencia.
        // TODO 5: si no existe -> add() al final.
        // TODO 6: devuelve la nueva lista.
        // TODO 7: clave: aplicarUpsert(aplicarUpsert(d, x), x) == aplicarUpsert(d, x) (idempotente).
        return List.of();
    }

    public static void main(String[] args) {
        List<ClienteErp> origen = List.of(
                new ClienteErp("CLI-1", "Acme", "a@b.com", "ES"),
                new ClienteErp("CLI-2", "Globex", "g@x.com", "US"));
        List<ClienteErp> destino = List.of(new ClienteErp("CLI-1", "Acme", "a@b.com", "ES"));
        System.out.println(planificarSync(origen, destino));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Clasifica la acción a aplicar a un registro (ALTA/MODIFICACION/SIN_CAMBIOS).
     */
    public static String clasificarAccion(ClienteErp origen, ClienteErp destino) {
        // GUÍA: teoría 6 (la decisión por registro que toma planificarSync).
        // 1. si destino es null -> "ALTA" (no existe todavía).
        // 2. si origen.equals(destino) -> "SIN_CAMBIOS".
        // 3. en otro caso -> "MODIFICACION".
        // PISTA: if (destino == null) return "ALTA"; return origen.equals(destino) ? "SIN_CAMBIOS" : "MODIFICACION";
        // OJO: el test pasa dos clientes iguales -> "SIN_CAMBIOS". Aprovecha el equals
        //   gratis de los record (compara todos los campos).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clasificarAccion");
    }

    /**
     * Reto Extra 2: Indexa una lista de clientes por su idExterno.
     */
    public static Map<String, ClienteErp> indexarPorId(List<ClienteErp> clientes) {
        // GUÍA: teoría 6 (buscar por id en O(1) en vez de recorrer la lista cada vez).
        // 1. null -> Map.of().
        // 2. construye un mapa idExterno -> cliente.
        // PISTA: clientes.stream().collect(Collectors.toMap(ClienteErp::idExterno, c -> c));
        // OJO: el test indexa 2 clientes y recupera CLI-2 por su id. Si hubiera ids
        //   duplicados, toMap lanzaría; aquí se asume que ya vienen deduplicados (Ej332).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para indexarPorId");
    }

    /**
     * Reto Extra 3: Calcula una huella de contenido del cliente (sin contar el id).
     */
    public static int hashContenido(ClienteErp c) {
        // GUÍA: teoría 6 (detectar cambios comparando una huella, no campo a campo).
        // 1. null -> 0.
        // 2. combina nombre, email y pais (NO el idExterno) en un hash.
        // PISTA: return java.util.Objects.hash(c.nombre(), c.email(), c.pais());
        // OJO: el test exige que dos clientes con MISMO contenido y DISTINTO id den el mismo
        //   hash. Por eso NO se incluye idExterno en la huella.
        // CULTURA: es la idea del hash de integridad de b30 (MessageDigest), simplificada:
        //   misma entrada -> mismo hash; cambió algo -> cambió el hash.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para hashContenido");
    }

    /**
     * Reto Extra 4: Indica si el contenido de dos clientes cambió (comparando huellas).
     */
    public static boolean haCambiado(ClienteErp a, ClienteErp b) {
        // GUÍA: reutiliza hashContenido (reto 3).
        // 1. si ambos null -> false; si solo uno null -> true.
        // 2. compara sus hashContenido.
        // PISTA: return hashContenido(a) != hashContenido(b);
        // OJO: el test compara mismo contenido -> false. Cuidado con el caso de un null
        //   (su hash es 0; distinto de uno real -> true).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para haCambiado");
    }

    /**
     * Reto Extra 5: Calcula los ids a eliminar (están en destino pero ya no en origen).
     */
    public static List<String> idsAEliminar(List<ClienteErp> origen, List<ClienteErp> destino) {
        // GUÍA: teoría 6 (en una sincronización completa, lo que desaparece del origen se borra).
        // 1. null -> List.of().
        // 2. reúne los idExterno del origen en un Set.
        // 3. devuelve los idExterno de destino que NO estén en ese set.
        // PISTA: Set<String> ids = origen.stream().map(ClienteErp::idExterno).collect(toSet());
        //        destino.stream().map(ClienteErp::idExterno).filter(id -> !ids.contains(id)).toList();
        // OJO: el test: origen=[CLI-1], destino=[CLI-1, CLI-9] -> ["CLI-9"]. La dirección
        //   importa: son los de destino que sobran.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para idsAEliminar");
    }

    /**
     * Reto Extra 6: Calcula el retardo de reintento con backoff exponencial (en ms).
     */
    public static long backoffMs(int intento) {
        // GUÍA: teoría 6 (si el ERP falla, no reintentar a lo loco: esperar cada vez más).
        // 1. si intento < 0 -> IllegalArgumentException.
        // 2. el retardo es 100 ms * 2^intento (intento 0 -> 100, 1 -> 200, 2 -> 400...).
        // 3. limita el máximo a 10000 ms (cap).
        // PISTA: long d = 100L * (1L << intento); return Math.min(d, 10000L);
        // OJO: el test: backoffMs(0)=100, backoffMs(3)=800. Usa Math.min para que no crezca
        //   sin límite (un intento 20 daría minutos).
        // CULTURA: backoff exponencial es el patrón de resiliencia de b21 (retry) y lo que hace
        //   todo cliente serio ante un 429/503; nace de la concurrencia/tiempo de b27.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para backoffMs");
    }

    /**
     * Reto Extra 7: Parte una lista de ids en lotes de tamaño máximo (para envíos masivos).
     */
    public static List<List<String>> particionarLotes(List<String> ids, int tam) {
        // GUÍA: teoría 6 (el ERP limita cuántos registros aceptas por llamada: trocea en lotes).
        // 1. si ids null o tam <= 0 -> List.of().
        // 2. recorre de 'tam' en 'tam' creando sublistas.
        // PISTA: for (int i = 0; i < ids.size(); i += tam)
        //          lotes.add(new ArrayList<>(ids.subList(i, Math.min(i + tam, ids.size()))));
        // OJO: el test parte 5 ids en lotes de 2 -> [[a,b],[c,d],[e]] (3 lotes; el último
        //   con 1). Math.min en el extremo evita IndexOutOfBounds.
        // CULTURA: "bulk"/batch es lo que hiciste en JDBC con addBatch (b11); reduce el nº de
        //   viajes a la red, igual que el batching de JPA (b14).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para particionarLotes");
    }

    /**
     * Reto Extra 8: Detecta los ids cuyo importe descuadra entre dos sistemas (conciliación).
     */
    public static List<String> idsDescuadrados(Map<String, Double> origen, Map<String, Double> destino) {
        // GUÍA: teoría 6 (conciliar: comparar totales por clave y listar los que no cuadran).
        // 1. null -> List.of().
        // 2. para cada clave del origen, compara su valor con el de destino (0.0 si falta).
        // 3. añade la clave si difieren (usa una tolerancia pequeña para doubles).
        // PISTA: if (Math.abs(origen.get(k) - destino.getOrDefault(k, 0.0)) > 0.001) descuadres.add(k);
        // OJO: el test: origen{A=100,B=50}, destino{A=100,B=40} -> ["B"]. Compara doubles con
        //   tolerancia (Math.abs > epsilon), nunca con == directo.
        // CULTURA: conciliación es lo que hace la contabilidad al cuadrar el banco con el ERP;
        //   los doubles se comparan con epsilon (lección de coma flotante de b01).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para idsDescuadrados");
    }

    /**
     * Reto Extra 9: Fusiona un parche sobre una base (los campos no vacíos del parche ganan).
     */
    public static ClienteErp fusionar(ClienteErp base, ClienteErp parche) {
        // GUÍA: teoría 6 (actualización parcial: el parche solo trae lo que cambia).
        // 1. si base null -> devuelve parche; si parche null -> devuelve base.
        // 2. para cada campo, usa el del parche si no está en blanco; si no, el de la base.
        //    El idExterno SIEMPRE es el de la base (la clave no se cambia).
        // PISTA: String nombre = parche.nombre()!=null && !parche.nombre().isBlank() ? parche.nombre() : base.nombre();
        //        ... y construye new ClienteErp(base.idExterno(), nombre, email, pais).
        // OJO: el test parchea solo el email (resto vacío) -> conserva nombre y pais de la base.
        // CULTURA: esto es EXACTAMENTE un PATCH de HTTP (b06/b07): actualización parcial, no
        //   reemplazo total (PUT). El mismo patrón "wither" de records de b01.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para fusionar");
    }

    /**
     * Reto Extra 10: Resume un plan de sincronización en una línea legible.
     */
    public static String resumenPlan(SyncPlan plan) {
        // GUÍA: teoría 6 (el log que deja la sincronización para auditoría).
        // 1. null -> "".
        // 2. formatea "altas=X modificaciones=Y sinCambios=Z" con los tamaños de cada lista.
        // PISTA: return "altas=" + plan.altas().size() + " modificaciones=" + plan.modificaciones().size()
        //              + " sinCambios=" + plan.sinCambios().size();
        // OJO: el test comprueba que CONTIENE "altas=1" para un plan con una sola alta.
        // CULTURA: un buen proceso de integración SIEMPRE deja traza de qué hizo (observabilidad,
        //   b20): sin ese resumen, nadie sabe si la sync de anoche fue bien.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resumenPlan");
    }
}
