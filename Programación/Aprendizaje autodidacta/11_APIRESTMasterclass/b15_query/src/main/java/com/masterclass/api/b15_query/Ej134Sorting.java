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
        // GUÍA: teoría 15.2 (ordenación segura por whitelist). Es el corazón del
        //   anti-inyección: solo dejas ordenar por columnas que TÚ apruebas.
        // 1. Una línea: return campo != null && CAMPOS_PERMITIDOS.contains(campo);
        // OJO: el test pide true para "nombre" y false para "password". El null
        //   debe dar false (Set.contains(null) no lanza, pero protege igual).
        // CULTURA: en Spring, Pageable acepta cualquier "sort"; sin esta lista
        //   blanca un atacante ordena por columnas internas o inyecta SQL.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCampoPermitido");
    }

    /**
     * Reto Extra 2: Determina la direccion de ordenacion ("asc" o "desc").
     */
    public static String determinarDireccion(boolean ascendente) {
        // GUÍA: una línea — return ascendente ? "asc" : "desc";
        // OJO: el test exige las palabras en MINÚSCULAS ("asc"/"desc"), que es lo
        //   que entiende JPQL. No confundas con formatearOrdenacion (reto 10),
        //   que usa "ASC"/"DESC" en mayúsculas para mostrar al usuario.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para determinarDireccion");
    }

    /**
     * Reto Extra 3: Genera una clausula JPQL ORDER BY de forma segura.
     */
    public static String construirOrderJpql(String campo, boolean ascendente) {
        // GUÍA: reutiliza los dos retos anteriores en vez de repetir lógica.
        // 1. (Recomendado) valida el campo con esCampoPermitido(campo) antes de
        //    concatenar; si no, lanza IllegalArgumentException.
        // 2. Construye: return "order by p." + campo + " " + determinarDireccion(ascendente);
        // OJO: el test espera EXACTAMENTE "order by p.nombre asc" para
        //   ("nombre", true). Cuida el alias "p.", el espacio antes de la
        //   dirección y la dirección en minúsculas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para construirOrderJpql");
    }

    /**
     * Reto Extra 4: Valida si la whitelist esta inicializada correctamente.
     */
    public static boolean whitelistValida() {
        // GUÍA: una línea — return CAMPOS_PERMITIDOS != null && !CAMPOS_PERMITIDOS.isEmpty();
        // El test solo comprueba que devuelve true (la lista está inicializada con
        //   3 campos). Una whitelist vacía dejaría la ordenación inservible.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para whitelistValida");
    }

    /**
     * Reto Extra 5: Devuelve una copia de los campos permitidos.
     */
    public static java.util.Set<String> obtenerCamposPermitidos() {
        // GUÍA: COPIA defensiva, no devuelvas la constante directamente.
        // 1. Una línea: return new java.util.HashSet<>(CAMPOS_PERMITIDOS);
        //    (o Set.copyOf(CAMPOS_PERMITIDOS), que además es inmutable).
        // CULTURA: si devolvieras la referencia interna, quien la reciba podría
        //   mutarla y abrir un agujero de seguridad en la whitelist. El test solo
        //   pide assertNotNull, pero la copia es la práctica correcta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerCamposPermitidos");
    }

    /**
     * Reto Extra 6: Comprueba si la direccion indicada es descendente.
     */
    public static boolean esDireccionDescendente(String dir) {
        // GUÍA: una línea — return "desc".equalsIgnoreCase(dir);
        // PISTA: pon el literal "desc" a la IZQUIERDA del equalsIgnoreCase para
        //   que un dir == null devuelva false sin NPE.
        // OJO: el test pide true para "desc" y false para "asc".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esDireccionDescendente");
    }

    /**
     * Reto Extra 7: Normaliza el nombre de un campo (trim y lowercase).
     */
    public static String normalizarCampo(String campo) {
        // GUÍA: una línea — return campo == null ? null : campo.trim().toLowerCase();
        // OJO: el test pasa "  Nombre  " y espera "nombre" (sin espacios y en
        //   minúsculas). El orden trim → toLowerCase da igual aquí, pero hazlo
        //   antes de comparar con la whitelist para tolerar entradas sucias.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarCampo");
    }

    /**
     * Reto Extra 8: Comprueba si el campo es de tipo ID.
     */
    public static boolean esCampoId(String campo) {
        // GUÍA: una línea — return "id".equalsIgnoreCase(campo);
        // (o reutiliza: "id".equals(normalizarCampo(campo)) para tolerar "  ID ").
        // El test pasa "id" y espera true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCampoId");
    }

    /**
     * Reto Extra 9: Comprueba si la whitelist contiene al menos 3 campos.
     */
    public static boolean tieneSuficientesCampos() {
        // GUÍA: una línea — return CAMPOS_PERMITIDOS.size() >= 3;
        // La whitelist tiene {id, nombre, precio} → 3 campos, así que el test
        //   espera true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneSuficientesCampos");
    }

    /**
     * Reto Extra 10: Retorna un string formateado con el criterio de ordenacion.
     */
    public static String formatearOrdenacion(String campo, boolean ascendente) {
        // GUÍA: campo + ":" + dirección en MAYÚSCULAS.
        // PISTA: return campo + ":" + (ascendente ? "ASC" : "DESC");
        // OJO: el test espera EXACTAMENTE "nombre:ASC" para ("nombre", true).
        //   Aquí la dirección va en mayúsculas, al contrario que determinarDireccion
        //   (reto 2). Es formato "de cara al usuario", no JPQL.
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
