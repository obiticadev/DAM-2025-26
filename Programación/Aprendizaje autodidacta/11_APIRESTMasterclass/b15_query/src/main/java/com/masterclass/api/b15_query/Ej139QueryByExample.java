package com.masterclass.api.b15_query;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Ejercicio 139 · Query by Example (probe → condiciones).
 *
 * <p>Teoría: {@code teoria/15_Consultas_Avanzadas.md} (sección 15.2).
 *
 * <p>QBE: das un objeto "ejemplo"; los campos NO nulos se vuelven condiciones AND.
 */
public final class Ej139QueryByExample {

    /** Objeto-ejemplo: campos null = "no filtrar por este". */
    public record PersonaProbe(String nombre, String ciudad, Integer edad) {
    }

    private Ej139QueryByExample() {
    }

    /**
     * Construye el mapa de condiciones (campo → valor) a partir del probe.
     *
     * @param probe ejemplo con algunos campos a null
     * @return mapa con SOLO los campos no nulos, en orden nombre,ciudad,edad
     */
    public static Map<String, Object> condicionesDe(PersonaProbe probe) {
        Map<String, Object> cond = new LinkedHashMap<>();
        // TODO 1: si probe es null -> IllegalArgumentException.
        // TODO 2: si probe.nombre() != null, cond.put("nombre", probe.nombre()).
        // TODO 3: si probe.ciudad() != null, cond.put("ciudad", probe.ciudad()).
        // TODO 4: si probe.edad() != null, cond.put("edad", probe.edad()).
        // TODO 5: respeta el orden de inserción (LinkedHashMap).
        // TODO 6: un probe todo-null produce un mapa vacío (= traer todo).
        // TODO 7: devuelve el mapa.
        return cond;
    }

    /**
     * Filtra una lista en memoria aplicando las condiciones del probe (AND).
     *
     * @param datos lista de personas como Map (clave→valor)
     * @param probe ejemplo
     * @return filas que casan TODAS las condiciones activas
     */
    public static List<Map<String, Object>> filtrar(List<Map<String, Object>> datos, PersonaProbe probe) {
        // TODO 8: obtén las condiciones con condicionesDe(probe).
        // TODO 9: una fila pasa si para CADA (k,v) de condiciones, fila.get(k).equals(v).
        // TODO 10: devuelve las filas que cumplen todas (stream + filter).
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println(condicionesDe(new PersonaProbe("Ana", null, 30)));
    }

    /**
     * Reto Extra 1: Obtiene el nombre de un producto de forma segura.
     */
    public static String obtenerNombre(Prod139 p) {
        // GUÍA: teoría 15.2 (QBE). Acceso seguro a un campo del "probe".
        // 1. Una línea: return p == null ? null : p.getNombre();
        // El test crea new Prod139("Laptop", "Tech", 100) y espera "Laptop".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerNombre");
    }

    /**
     * Reto Extra 2: Obtiene la categoria de forma segura.
     */
    public static String obtenerCategoria(Prod139 p) {
        // GUÍA: una línea — return p == null ? null : p.getCategoria();
        // El test espera "Tech".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerCategoria");
    }

    /**
     * Reto Extra 3: Comprueba si el producto tiene campos asignados para Example.
     */
    public static boolean tieneCamposDeEjemplo(Prod139 p) {
        // GUÍA: en QBE un probe sirve si tiene ALGÚN campo no nulo por el que
        //   filtrar (igual que condicionesDe descarta los null).
        // 1. Una línea: return p != null && (p.getNombre() != null || p.getCategoria() != null);
        // El test pasa un producto con nombre y categoría → true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneCamposDeEjemplo");
    }

    /**
     * Reto Extra 4: Crea un nuevo producto de ejemplo.
     */
    public static Prod139 crearEjemplo(String nombre, String categoria) {
        // GUÍA: construye el probe con el constructor de Prod139 (nombre, categoria, precio).
        // 1. Una línea: return new Prod139(nombre, categoria, 0);
        //    (el precio no se usa como filtro aquí, 0 vale).
        // El test solo comprueba assertNotNull sobre el resultado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearEjemplo");
    }

    /**
     * Reto Extra 5: Comprueba si el precio de ejemplo esta asignado (mayor que 0).
     */
    public static boolean tienePrecioAsignado(Prod139 p) {
        // GUÍA: una línea — return p != null && p.getPrecio() > 0;
        // El test crea el producto con precio 100.0 → true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tienePrecioAsignado");
    }

    /**
     * Reto Extra 6: Obtiene el ID del producto de ejemplo.
     */
    public static Long obtenerId(Prod139 p) {
        // GUÍA: una línea — return p == null ? null : p.getId();
        // OJO: el test espera null. Un Prod139 recién creado no tiene id asignado
        //   (sería la BD quien lo pusiera al persistir).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerId");
    }

    /**
     * Reto Extra 7: Normaliza el texto de los campos.
     */
    public static String normalizarTexto(String s) {
        // GUÍA: una línea — return s == null ? null : s.trim().toLowerCase();
        // El test pasa "  Laptop  " y espera "laptop".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarTexto");
    }

    /**
     * Reto Extra 8: Comprueba si el ejemplo es nuevo (ID nulo).
     */
    public static boolean esNuevo(Prod139 p) {
        // GUÍA: una línea — return p != null && p.getId() == null;
        // OJO: "nuevo" = sin id (aún no persistido). El test crea el producto sin
        //   id → true. Es el mismo criterio que usa JPA para decidir persist vs merge.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNuevo");
    }

    /**
     * Reto Extra 9: Valida el producto de ejemplo.
     */
    public static boolean esValido(Prod139 p) {
        // GUÍA: válido = existe y tiene al menos nombre. Reutiliza obtenerNombre.
        // 1. Una línea: return p != null && p.getNombre() != null && !p.getNombre().isBlank();
        // El test pasa un producto con nombre "Laptop" → true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esValido");
    }

    /**
     * Reto Extra 10: Retorna formato del ejemplo.
     */
    public static String formatearEjemplo(Prod139 p) {
        // GUÍA: String.format con nombre y categoría.
        // PISTA: String.format("Example[Nombre=%s, Cat=%s]", p.getNombre(), p.getCategoria());
        // OJO: el test espera EXACTAMENTE "Example[Nombre=Laptop, Cat=Tech]".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearEjemplo");
    }



}

/** Producto-ejemplo para los retos extra (POJO, no entidad). El id queda null. */
class Prod139 {
    private Long id;
    private String nombre;
    private String categoria;
    private double precio;

    public Prod139(String nombre, String categoria, double precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }
}
