package com.masterclass.api.b43_erp;

import java.util.List;

/**
 * Ejercicio 331 · Conceptos de ERP/CRM: módulos y modelo de datos (guion + glosario).
 *
 * <p>Teoría: {@code teoria/43_SGE_Integracion.md} (sección 1).
 *
 * <p>Antes de integrar nada hay que entender CON QUÉ se integra. Un ERP organiza la empresa
 * en <em>módulos</em> (ventas, compras, inventario, contabilidad, CRM) y cada módulo expone
 * <em>modelos</em> de datos. En Odoo los modelos se nombran con puntos: {@code res.partner}
 * (clientes/proveedores), {@code sale.order} (pedidos de venta), {@code product.template}
 * (productos), {@code account.move} (asientos/facturas), {@code purchase.order} (compras).
 *
 * <p>Este ejercicio es "guion + glosario": modela ese conocimiento como funciones puras y
 * testeables sobre cadenas, igual que los "guion" de Docker (b22) o Android (b42).
 */
public final class Ej331ErpConcepts {

    private Ej331ErpConcepts() {
    }

    /**
     * Devuelve el área de negocio a la que pertenece un modelo de Odoo conocido.
     *
     * @param modelo nombre técnico del modelo (p.ej. {@code "sale.order"})
     * @return área de negocio ("Ventas", "Compras", "Inventario", "Contabilidad", "CRM")
     *         o "Desconocido" si el modelo no está en el catálogo; nunca null
     */
    public static String areaDeModelo(String modelo) {
        // TODO 1: si modelo es null o en blanco, devuelve "Desconocido".
        // TODO 2: normaliza a minúsculas y sin espacios alrededor (trim + toLowerCase).
        // TODO 3: "sale.order" o "sale.order.line" -> "Ventas".
        // TODO 4: "purchase.order" -> "Compras".
        // TODO 5: "product.template" o "stock.move" -> "Inventario".
        // TODO 6: "account.move" -> "Contabilidad".
        // TODO 7: "res.partner" o "crm.lead" -> "CRM".
        // TODO 8: cualquier otro -> "Desconocido".
        // TODO 9: usa un switch o una cadena de if/equals (no startsWith frágil).
        // TODO 10: devuelve el área calculada.
        return "";
    }

    /**
     * Indica si un modelo representa un <em>dato maestro</em> (estable: clientes, productos)
     * frente a un dato <em>transaccional</em> (movimientos: pedidos, facturas).
     *
     * @param modelo nombre técnico del modelo
     * @return true si es maestro ({@code res.partner}, {@code product.template}),
     *         false si es transaccional o desconocido
     */
    public static boolean esDatoMaestro(String modelo) {
        // TODO 1: si modelo es null/blank -> false.
        // TODO 2: normaliza (trim + toLowerCase).
        // TODO 3: define el conjunto de maestros: "res.partner", "product.template".
        // TODO 4: "sale.order", "account.move", "purchase.order" son transaccionales -> false.
        // TODO 5: maestro = cambia poco y es referenciado por los movimientos.
        // TODO 6: devuelve si el modelo está en el conjunto de maestros.
        return false;
    }

    public static void main(String[] args) {
        System.out.println(areaDeModelo("sale.order"));      // Ventas
        System.out.println(areaDeModelo("res.partner"));     // CRM
        System.out.println(esDatoMaestro("product.template")); // true
        System.out.println(esDatoMaestro("sale.order"));     // false
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Normaliza el nombre técnico de un modelo (minúsculas, sin espacios).
     */
    public static String normalizarNombreModelo(String modelo) {
        // GUÍA: teoría 1 (los nombres de modelo de Odoo son siempre minúsculas con puntos).
        // 1. null -> "" (cadena vacía, no null).
        // 2. quita espacios alrededor y pasa a minúsculas.
        // PISTA: return modelo == null ? "" : modelo.trim().toLowerCase();
        // OJO: el test pasa "  Sale.Order " -> "sale.order". El trim va ANTES o DESPUÉS
        //   del toLowerCase, da igual, pero ambos son necesarios.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarNombreModelo");
    }

    /**
     * Reto Extra 2: Valida que un nombre de modelo tenga forma {@code palabra.palabra(.palabra)}.
     */
    public static boolean esModeloValido(String modelo) {
        // GUÍA: teoría 1 (sintaxis de los modelos). Un modelo válido es minúsculas y guion
        //   bajo separadas por al menos un punto: res.partner, sale.order.line.
        // 1. null/blank -> false.
        // 2. comprueba contra la expresión regular ^[a-z]+(\.[a-z_]+)+$.
        // PISTA: return modelo != null && modelo.matches("^[a-z]+(\\.[a-z_]+)+$");
        // OJO: el test exige que "sale" (sin punto) sea false y "sale.order" sea true.
        //   El '+' tras el grupo obliga a AL MENOS un punto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esModeloValido");
    }

    /**
     * Reto Extra 3: Devuelve la definición de un término del glosario ERP/CRM.
     */
    public static String glosario(String termino) {
        // GUÍA: teoría 1 (glosario). Mapa término -> definición corta.
        // 1. normaliza el término a mayúsculas (ERP, CRM, ETL, SKU, BI).
        // 2. "ERP" -> "Planificacion de recursos empresariales".
        //    "CRM" -> "Gestion de relaciones con clientes".
        //    "ETL" -> "Extraer, transformar y cargar".
        //    "SKU" -> "Codigo unico de articulo".
        //    "BI"  -> "Inteligencia de negocio".
        // 3. desconocido -> "" (cadena vacía).
        // PISTA: usa un switch sobre termino.trim().toUpperCase().
        // OJO: el test pide glosario("erp") (minúsculas) -> debe encontrar "ERP".
        //   Normaliza ANTES de comparar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para glosario");
    }

    /**
     * Reto Extra 4: Indica si el módulo A depende de (necesita datos de) el módulo B.
     */
    public static boolean moduloDependeDe(String moduloA, String moduloB) {
        // GUÍA: teoría 1 (dependencias entre módulos). Ventas necesita Inventario (vende
        //   productos) y CRM (vende a clientes); Contabilidad necesita Ventas (factura pedidos).
        // 1. normaliza ambos a minúsculas.
        // 2. "ventas" depende de "inventario" y de "crm".
        // 3. "contabilidad" depende de "ventas".
        // 4. en cualquier otro caso -> false.
        // PISTA: switch sobre moduloA y comprueba si moduloB está en su lista de dependencias.
        // OJO: el test usa ("ventas","inventario") -> true y ("inventario","ventas") -> false.
        //   La dependencia NO es simétrica: el orden importa.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para moduloDependeDe");
    }

    /**
     * Reto Extra 5: Prioridad de carga de un maestro (menor número = se carga antes).
     */
    public static int prioridadCarga(String modelo) {
        // GUÍA: teoría 1 (orden de migración). Al migrar datos hay que cargar primero lo
        //   referenciado: clientes y productos ANTES que los pedidos que los apuntan.
        // 1. "res.partner" -> 1; "product.template" -> 1 (maestros primero).
        // 2. "sale.order" -> 2 (necesita cliente y producto ya cargados).
        // 3. "account.move" -> 3 (factura, necesita el pedido).
        // 4. desconocido -> 99 (al final, por si acaso).
        // PISTA: switch sobre normalizarNombreModelo(modelo).
        // OJO: el test comprueba prioridadCarga("res.partner") < prioridadCarga("sale.order").
        // CULTURA: este "orden topológico" es justo el problema de las claves foráneas en
        //   b13 (relaciones JPA): no puedes insertar un hijo si el padre no existe aún.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para prioridadCarga");
    }

    /**
     * Reto Extra 6: Construye la ruta de un modelo en la API REST de Odoo.
     */
    public static String rutaApiModelo(String modelo) {
        // GUÍA: teoría 1/3 (cómo se exponen los modelos por HTTP).
        // 1. null/blank -> "".
        // 2. la ruta es "/api/" + el modelo con los puntos cambiados por barras.
        // PISTA: return "/api/" + normalizarNombreModelo(modelo).replace('.', '/');
        // OJO: el test pide rutaApiModelo("res.partner") -> "/api/res/partner".
        //   Reemplaza TODOS los puntos (replace de char los cambia todos).
        // CULTURA: esto es modelar recursos REST (b00/b05): el modelo es el sustantivo
        //   y la jerarquía va en el path.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rutaApiModelo");
    }

    /**
     * Reto Extra 7: Cuenta cuántos modelos de una lista pertenecen a un área dada.
     */
    public static int contarModelosDeArea(List<String> modelos, String area) {
        // GUÍA: reutiliza areaDeModelo (composición de funciones).
        // 1. null -> 0.
        // 2. recorre la lista y cuenta los que cumplen areaDeModelo(m).equals(area).
        // PISTA: con streams -> modelos.stream().filter(m -> areaDeModelo(m).equals(area)).count();
        //   (recuerda castear el long a int con (int)).
        // OJO: el test pasa ["sale.order","res.partner","sale.order.line"] y area "Ventas"
        //   -> 2. Apóyate en tu propio areaDeModelo, no repitas el switch.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarModelosDeArea");
    }

    /**
     * Reto Extra 8: Indica si dos modelos pertenecen al mismo módulo/área.
     */
    public static boolean mismaArea(String modeloA, String modeloB) {
        // GUÍA: reutiliza areaDeModelo para comparar.
        // 1. calcula el área de cada uno.
        // 2. son la misma área si coinciden Y no son ambas "Desconocido".
        // PISTA: String a = areaDeModelo(modeloA); ... return !a.equals("Desconocido") && a.equals(b);
        // OJO: el test exige que ("foo","bar") (ambos desconocidos) -> false, no true.
        //   Dos "Desconocido" NO cuentan como misma área.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mismaArea");
    }

    /**
     * Reto Extra 9: Describe el flujo "order-to-cash" como cadena de pasos.
     */
    public static String flujoOrderToCash() {
        // GUÍA: teoría 1 (el flujo de venta de un ERP). El ciclo clásico es:
        //   presupuesto -> pedido -> entrega -> factura -> cobro.
        // 1. devuelve esos cinco pasos unidos por " -> ".
        // PISTA: return String.join(" -> ", "presupuesto","pedido","entrega","factura","cobro");
        // OJO: el test comprueba que el resultado EMPIEZA por "presupuesto" y CONTIENE "factura".
        // CULTURA: "order-to-cash" (O2C) es el proceso estrella de un ERP; entenderlo es lo
        //   que de verdad te piden en SGE, más que la sintaxis de cada pantalla.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para flujoOrderToCash");
    }

    /**
     * Reto Extra 10: Clasifica un nombre de campo de Odoo por su tipo de relación según el sufijo.
     */
    public static String tipoRelacionCampo(String campo) {
        // GUÍA: teoría 1 (convención de nombres de Odoo). Por convención:
        //   sufijo "_id"  -> "many2one"  (apunta a UN registro: partner_id).
        //   sufijo "_ids" -> "one2many/many2many" (varios: order_line_ids).
        //   sin sufijo    -> "escalar"   (un valor simple: name, amount_total).
        // 1. null/blank -> "".
        // 2. comprueba primero "_ids" (más específico) y luego "_id".
        // PISTA: if (campo.endsWith("_ids")) return "one2many/many2many";
        //        if (campo.endsWith("_id"))  return "many2one";
        //        return "escalar";
        // OJO: el test usa "partner_id" -> "many2one" y "line_ids" -> "one2many/many2many".
        //   Si compruebas "_id" ANTES que "_ids", clasificarías mal los plurales: el orden importa.
        // CULTURA: esto son las relaciones @ManyToOne/@OneToMany de JPA (b13) con otro nombre.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tipoRelacionCampo");
    }
}
