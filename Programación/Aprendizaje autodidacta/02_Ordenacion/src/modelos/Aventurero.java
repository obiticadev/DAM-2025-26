package modelos;

import java.util.Objects;

/**
 * CLASE MAESTRA: AVENTURERO
 * 
 * POJO pesado para nuestras demostraciones de Streams y Ordenación masiva.
 * Contiene numerosos campos de distintos tipos para practicar filtrados paramétricos lógicos
 * y ordenaciones de clase múltiple.
 */
public class Aventurero implements Comparable<Aventurero> {

    private String nombre;
    private String claseClase; // Ej: Guerrero, Mago, Healer
    private int nivel;
    private double oro;
    private boolean estadoVivo;

    public Aventurero(String nombre, String claseClase, int nivel, double oro, boolean estadoVivo) {
        this.nombre = nombre;
        this.claseClase = claseClase;
        this.nivel = nivel;
        this.oro = oro;
        this.estadoVivo = estadoVivo;
    }

    public String getNombre() { return nombre; }
    public String getClaseClase() { return claseClase; }
    public int getNivel() { return nivel; }
    public double getOro() { return oro; }
    public boolean isEstadoVivo() { return estadoVivo; }

    // Cambiadores de estado básicos
    public void matar() { this.estadoVivo = false; }
    public void cambiarOro(double modificador) { this.oro += modificador; }
    
    // Método helper para Streams complejos (Nivel 5)
    public boolean esHeroeLegendario() { return this.nivel > 50 && this.oro > 1000; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aventurero that = (Aventurero) o;
        return Objects.equals(nombre, that.nombre) && 
               Objects.equals(claseClase, that.claseClase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, claseClase);
    }

    @Override
    public String toString() {
        return nombre + " (Nv." + nivel + " | " + claseClase + " | " + 
               (estadoVivo ? "Vivo" : "D.E.P.") + " | " + oro + "g)";
    }

    // ==========================================
    // MÉTODO 'COMPARABLE' - ORDEN NATURAL
    // ==========================================
    /**
     * El cuerpo de este método dictamina cuál será el orden 'POR DEFECTO' de la clase Aventurero
     * en caso de que alguien lo lance a través de Java o la intente meter en un TreeSet.
     * 
     * Hemos decidido que el Orden Natural por excelencia de un grupo de Aventureros sea siempre
     * de Mayor Nivel a Menor Nivel (Descendente).
     * 
     * @param o2 El aventurero contra el que me enfrento a comparar.
     * @return 0 Empate (OJO CON ESTO), > 0 si this es mayor a o2, < 0 si this es menor a o2.
     * 
     * Explicación mágica del restado:
     * El compareTo de primitivas Integer es (x < y) ? -1 : ((x == y) ? 0 : 1)
     * Como queremos orden DESCENDENTE (El nivel 100 gane al 10), usamos la lógica inversa internamente
     * pasándole los parámetros al revés a Integer.compare.
     */
    @Override
    public int compareTo(Aventurero o2) {
        int r = Integer.compare(o2.getNivel(), this.getNivel());
        
        if(r == 0) { 
            // NUNCA devuelvas 0 a menos que equals sea verdadero. Si dos personas coinciden en nivel (5 vs 5)
            // TreeSet deducirá tontamente que ambos OBJETOS son EL MISMO. Y borrará uno por "duplicado".
            // Para eludir esto en el Orden Natural, usamos una muleta:
            return this.nombre.compareTo(o2.getNombre());
        }
        
        return r;
    }
}
