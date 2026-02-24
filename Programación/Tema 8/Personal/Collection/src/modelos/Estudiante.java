package modelos;

/**
 * Clase Estudiante para demostrar ordenamiento natural en TreeSet y TreeMap.
 * Implementa la interfaz Comparable para dotar de un "orden natural" a estos
 * objetos.
 */
public class Estudiante implements Comparable<Estudiante> {
    private String matricula;
    private String nombre;
    private double notaMedia;

    public Estudiante(String matricula, String nombre, double notaMedia) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.notaMedia = notaMedia;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "matricula='" + matricula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", notaMedia=" + notaMedia +
                '}';
    }

    // ==========================================
    // MÉTODOS CLAVE PARA TREESET / TREEMAP
    // ==========================================

    /**
     * Define cómo se compara este estudiante (this) con otro (o).
     * Devuelve:
     * Un número negativo si "this" es MENOR que "o".
     * Cero si "this" es IGUAL a "o" a efectos de ordenamiento.
     * Un número positivo si "this" es MAYOR que "o".
     * 
     * Haremos que el orden natural sea por nota media, de MAYOR a MENOR.
     * En caso de empate, ordenamos por la matrícula alfabéticamente.
     */
    @Override
    public int compareTo(Estudiante o) {
        // Orden inverso por nota (la más alta primero)
        int comparacionNota = Double.compare(o.getNotaMedia(), this.getNotaMedia());

        if (comparacionNota == 0) {
            // Si tienen la misma nota, definimos su orden por matrícula para desempatar
            // y evitar que el Set lo considere un registro "duplicado"
            return this.matricula.compareTo(o.getMatricula());
        }

        return comparacionNota;
    }
}
