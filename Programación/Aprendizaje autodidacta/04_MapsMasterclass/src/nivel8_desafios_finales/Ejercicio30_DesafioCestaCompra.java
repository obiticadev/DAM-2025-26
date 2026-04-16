package nivel8_desafios_finales;

import modelos.DatosPrueba;
import modelos.Producto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * EJERCICIO 30 - DESAFÍO MASTER: MOTOR DE RECOMENDACIONES Y E-COMMERCE
 * 
 * Objetivo: Demostrar un dominio absoluto de Map y Streams combinando filtros,
 * agrupaciones y diccionarios complejos de negocio.
 */
public class Ejercicio30_DesafioCestaCompra {

    public static void demostracion() {
        System.out.println("--- ALCANZANDO EL CLÍMAX DEL MAP ---");
        System.out.println("Este es tu último desafío. Todo te ha llevado hasta aquí.");
        System.out.println("Supera este reto y podrás considerarte un Senior en el API Collections y Streams.");
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 30: AUDITORÍA DEL E-COMMERCE ---");
        List<Producto> almacen = DatosPrueba.obtenerProductos();

        // Situación del negocio: Queremos auditar el stock monetariamente.
        // Solo nos interesan los productos que REALMENTE TIENEN STOCK (enStock = true).
        // Queremos organizarlos por Categoría, pero en lugar de listas de productos,
        // queremos un mapa de <Nombre Producto, Precio Producto>.
        
        // El resultado final debe ser de tipo: Map<String, Map<String, Double>>
        // Llave 1: Categoría (String)
        // Valor 1: Otro Mapa <Nombre, Precio> (Ese es el downstream).

        // TODO 1: Implementa este pipeline monstruoso (pero elegante).
        // 1. Filtrar los que isEnStock() sea true.
        // 2. collect -> groupingBy:
        //    2.1. Clasificador: Producto::getCategoria
        //    2.2. Downstream: Collectors.toMap(Producto::getNombre, Producto::getPrecio)
        Map<String, Map<String, Double>> auditoria = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Imprime el mapa de auditoría amigablemente. (forEach anidado)
        System.out.println("Auditoría Final del Sistema:");
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Extrae el sub-mapa de la categoría "Electrónica" y dentro de ese sub-mapa
        // intenta obtener el precio del "Teclado Mecánico".
        Double precioTecladoEnAudit = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        boolean ok1 = auditoria != null && auditoria.containsKey("Electrónica") && auditoria.containsKey("Mobiliario");
        boolean ok2 = auditoria != null && auditoria.get("Mobiliario").size() == 1; // Solo la mesa tiene stock
        boolean ok3 = precioTecladoEnAudit != null && precioTecladoEnAudit == 85.00;

        if (ok1 && ok2 && ok3) {
            System.out.println("\n**************************************************************");
            System.out.println(">> ¡MÁSTER COMPLETADO! ERES UN VERDADERO EXPERTO EN JAVA MAPS Y STREAMS \033[0;32m [OK]\033[0m");
            System.out.println("**************************************************************\n");
        } else {
            System.err.println("\n>> [ERROR] El último reto se resiste. Revisa tu agrupación anidada con groupingBy() y toMap() dentro como downstream. Has olvidado el filtrado previo o algo salió mal en el map interno.");
        }
    }
}
