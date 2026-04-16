package nivel9_desafios_supremos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import modelos.Empleado;
import modelos.Pedido;
import modelos.Producto;

/**
 * EJERCICIO 64 — ETL PIPELINE COMPLETO: EXTRAER, TRANSFORMAR, CARGAR
 * 
 * SIN PISTAS. Simula un pipeline de datos real:
 * 1. Extrae datos crudos de múltiples fuentes
 * 2. Transforma y limpia con streams
 * 3. Carga en estructuras de destino
 */
public class Ejercicio64_ETLPipeline {

    public static void demostracion() {
        System.out.println("=== DESAFÍO SUPREMO 64: ETL PIPELINE ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 64: PIPELINE ETL DE DATOS CORPORATIVOS ---");

        // === FUENTE 1: Empleados (algunos con datos sucios) ===
        List<Empleado> empleadosCrudos = new ArrayList<>();
        empleadosCrudos.add(new Empleado("Ana García", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empleadosCrudos.add(new Empleado("", "Backend", "Java", 3, 35000, true, "nadie@corp.com")); // nombre vacío
        empleadosCrudos.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empleadosCrudos.add(new Empleado("Marta López", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empleadosCrudos.add(new Empleado("Lucía Martín", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empleadosCrudos.add(new Empleado("Pedro Sanz", "QA", "Java", 3, -5000, false, null)); // salario negativo
        empleadosCrudos.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empleadosCrudos.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        // === FUENTE 2: Pedidos ===
        Producto laptop = new Producto("Laptop Pro", "Electrónica", 1200.0, 15, true);
        Producto raton = new Producto("Ratón Gamer", "Electrónica", 45.0, 120, true);
        Producto camiseta = new Producto("Camiseta Dev", "Ropa", 18.0, 200, true);
        Producto agotado = new Producto("Edición Limitada", "Colección", 500.0, 0, false);

        List<Pedido> pedidosCrudos = new ArrayList<>();
        pedidosCrudos.add(new Pedido("P001", "Ana García", Arrays.asList(laptop, raton), false));
        pedidosCrudos.add(new Pedido("P002", "Desconocido", Arrays.asList(camiseta), false)); // cliente no existe
        pedidosCrudos.add(new Pedido("P003", "Marta López", Arrays.asList(agotado, raton), false)); // contiene agotado
        pedidosCrudos.add(new Pedido("P004", "Elena Torres", Arrays.asList(laptop, camiseta), false));

        // === ETAPA 1: EXTRACT & CLEAN ===
        // TODO 1: Filtra empleados válidos: nombre no vacío, salario > 0, activo.
        List<Empleado> empleadosLimpios = null; // <- Escribe aquí

        // TODO 2: Filtra pedidos válidos: el cliente debe existir en empleadosLimpios
        // y todos los productos del pedido deben estar disponibles.
        List<Pedido> pedidosValidos = null; // <- Escribe aquí

        // === ETAPA 2: TRANSFORM ===
        // TODO 3: Para cada empleado limpio, genera un Map<String, String> con:
        //   "ficha" -> "NOMBRE (DEPTO) - LENGUAJE - EXP años - SALARIO€"
        //   pero con nombre en MAYÚSCULAS y email normalizado (o "N/A" si null).
        // Resultado: List<String> de fichas
        List<String> fichas = null; // <- Escribe aquí

        // TODO 4: Para cada pedido válido, calcula: cliente, número de artículos, total.
        // Genera List<String> con formato "P00X | Cliente | N arts | TOTAL€"
        List<String> resumenPedidos = null; // <- Escribe aquí

        // === ETAPA 3: LOAD (agregar) ===
        // TODO 5: Genera un resumen ejecutivo String con:
        // - Total empleados procesados / descartados
        // - Total pedidos procesados / descartados
        // - Facturación total de pedidos válidos
        // - Departamento con más empleados limpios
        String resumenEjecutivo = ""; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean v1 = empleadosLimpios != null && empleadosLimpios.size() == 6;
        boolean v2 = pedidosValidos != null && pedidosValidos.size() == 2; // P001 y P004
        boolean v3 = fichas != null && fichas.size() == 6;
        boolean v4 = resumenPedidos != null && resumenPedidos.size() == 2;
        boolean v5 = resumenEjecutivo.contains("6") && resumenEjecutivo.contains("2");

        if (v1 && v2 && v3 && v4 && v5) {
            System.out.println(">> CORRECTO: Pipeline ETL completo ejecutado.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] 6 empleados limpios, 2 pedidos válidos, 6 fichas, 2 resúmenes.");
        }
    }
}
