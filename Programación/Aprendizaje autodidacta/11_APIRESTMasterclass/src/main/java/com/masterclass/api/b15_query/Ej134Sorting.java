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
