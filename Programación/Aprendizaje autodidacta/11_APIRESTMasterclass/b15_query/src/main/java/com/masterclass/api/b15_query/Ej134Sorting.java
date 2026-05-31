package com.masterclass.api.b15_query;

import jakarta.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Ejercicio 134 · Ordenación dinámica segura (whitelist de campos).
 *
 * <p>Teoría: {@code teoria/15_Consultas_Avanzadas.md} (sección 15.2).
 *
 * <p>Ordenar por un campo que viene del cliente es inyectable si no se valida.
 */
public final class Ej134Sorting {

    private static final Set<String> CAMPOS_PERMITIDOS = Set.of("id", "nombre", "precio");

    private final EntityManager em;

    public Ej134Sorting(EntityManager em) {
        this.em = em;
    }

    /**
     * Lista productos ordenados por el campo y dirección indicados.
     *
     * @param campo     campo de ordenación (debe estar en la whitelist)
     * @param ascendente true = ASC, false = DESC
     * @return lista ordenada
     * @throws IllegalArgumentException si 'campo' no está permitido
     */
    public List<Prod134> ordenar(String campo, boolean ascendente) {
        // TODO 1: si campo es null -> IllegalArgumentException.
        // TODO 2: valida que CAMPOS_PERMITIDOS.contains(campo) (anti-inyección).
        // TODO 3: si no está permitido -> IllegalArgumentException.
        // TODO 4: determina la dirección: "asc" o "desc" según 'ascendente'.
        // TODO 5: construye el JPQL "select p from Prod134 p order by p." + campo + " " + dir.
        // TODO 6: el campo es seguro porque pasó la whitelist (no concatenes nada más).
        // TODO 7: createQuery tipado.
        // TODO 8: getResultList().
        // TODO 9: NUNCA metas 'campo' en el SQL sin haberlo validado contra la lista.
        // TODO 10: devuelve la lista ordenada.
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * Reto Extra 1: Comprueba si un campo de ordenacion esta en la whitelist.
     */
    public static boolean esCampoPermitido(String campo) {
        // TODO extra: Reto Extra 1: Comprueba si un campo de ordenacion esta en la whitelist.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCampoPermitido");
    }

    /**
     * Reto Extra 2: Determina la direccion de ordenacion ("asc" o "desc").
     */
    public static String determinarDireccion(boolean ascendente) {
        // TODO extra: Reto Extra 2: Determina la direccion de ordenacion ("asc" o "desc").
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para determinarDireccion");
    }

    /**
     * Reto Extra 3: Genera una clausula JPQL ORDER BY de forma segura.
     */
    public static String construirOrderJpql(String campo, boolean ascendente) {
        // TODO extra: Reto Extra 3: Genera una clausula JPQL ORDER BY de forma segura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para construirOrderJpql");
    }

    /**
     * Reto Extra 4: Valida si la whitelist esta inicializada correctamente.
     */
    public static boolean whitelistValida() {
        // TODO extra: Reto Extra 4: Valida si la whitelist esta inicializada correctamente.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para whitelistValida");
    }

    /**
     * Reto Extra 5: Devuelve una copia de los campos permitidos.
     */
    public static java.util.Set<String> obtenerCamposPermitidos() {
        // TODO extra: Reto Extra 5: Devuelve una copia de los campos permitidos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerCamposPermitidos");
    }

    /**
     * Reto Extra 6: Comprueba si la direccion indicada es descendente.
     */
    public static boolean esDireccionDescendente(String dir) {
        // TODO extra: Reto Extra 6: Comprueba si la direccion indicada es descendente.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esDireccionDescendente");
    }

    /**
     * Reto Extra 7: Normaliza el nombre de un campo (trim y lowercase).
     */
    public static String normalizarCampo(String campo) {
        // TODO extra: Reto Extra 7: Normaliza el nombre de un campo (trim y lowercase).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarCampo");
    }

    /**
     * Reto Extra 8: Comprueba si el campo es de tipo ID.
     */
    public static boolean esCampoId(String campo) {
        // TODO extra: Reto Extra 8: Comprueba si el campo es de tipo ID.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCampoId");
    }

    /**
     * Reto Extra 9: Comprueba si la whitelist contiene al menos 3 campos.
     */
    public static boolean tieneSuficientesCampos() {
        // TODO extra: Reto Extra 9: Comprueba si la whitelist contiene al menos 3 campos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneSuficientesCampos");
    }

    /**
     * Reto Extra 10: Retorna un string formateado con el criterio de ordenacion.
     */
    public static String formatearOrdenacion(String campo, boolean ascendente) {
        // TODO extra: Reto Extra 10: Retorna un string formateado con el criterio de ordenacion.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearOrdenacion");
    }

    public static java.util.Set<String> obtainCamposPermitidosHelper() {
        return CAMPOS_PERMITIDOS;
    }



}

@Entity
class Prod134 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private double precio;

    public Prod134() {
    }

    public Prod134(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}
