package modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatosPrueba {

    public static List<Producto> obtenerProductos() {
        return Arrays.asList(
            new Producto("P1", "Portátil Gaming", 1200.50, "Electrónica", true),
            new Producto("P2", "Ratón Inalámbrico", 25.99, "Electrónica", true),
            new Producto("P3", "Silla Ergonómica", 150.00, "Mobiliario", false),
            new Producto("P4", "Teclado Mecánico", 85.00, "Electrónica", true),
            new Producto("P5", "Mesa de Escritorio", 200.00, "Mobiliario", true)
        );
    }

    public static List<Estudiante> obtenerEstudiantes() {
        return Arrays.asList(
            new Estudiante("MAT-001", "Ana García", 20, Arrays.asList("Matemáticas", "Física"), 8.5),
            new Estudiante("MAT-002", "Luis Pérez", 22, Arrays.asList("Historia", "Arte"), 7.2),
            new Estudiante("MAT-003", "Beatriz López", 19, Arrays.asList("Matemáticas", "Informática"), 9.1),
            new Estudiante("MAT-004", "Carlos Ruiz", 21, Arrays.asList("Informática", "Física"), 6.8),
            new Estudiante("MAT-005", "Diana Torres", 23, Arrays.asList("Arte", "Literatura"), 8.9)
        );
    }

    public static List<Pedido> obtenerPedidos() {
        return Arrays.asList(
            new Pedido("PED-101", "Cliente A", LocalDate.now().minusDays(5), 250.00, "ENTREGADO"),
            new Pedido("PED-102", "Cliente B", LocalDate.now().minusDays(2), 15.50, "ENVIADO"),
            new Pedido("PED-103", "Cliente A", LocalDate.now(), 1200.00, "PENDIENTE"),
            new Pedido("PED-104", "Cliente C", LocalDate.now().minusDays(10), 450.75, "CANCELADO"),
            new Pedido("PED-105", "Cliente B", LocalDate.now().minusDays(1), 89.99, "PENDIENTE")
        );
    }
}
