package modelos;

import java.util.Objects;

/**
 * CLASE MAESTRA: MISION
 * 
 * POJO secundario para practicar la interacción y ordenación de objetos 
 * genéricos que no tienen herencia ni relación directa con el Aventurero.
 */
public class Mision {
    
    private String titulo;
    private String rango; // Ej: S, A, B, C
    private double recompensaOro;
    private int limiteNivelMinimo;
    
    public Mision(String titulo, String rango, double recompensaOro, int limiteNivelMinimo) {
        this.titulo = titulo;
        this.rango = rango;
        this.recompensaOro = recompensaOro;
        this.limiteNivelMinimo = limiteNivelMinimo;
    }

    public String getTitulo() { return titulo; }
    public String getRango() { return rango; }
    public double getRecompensaOro() { return recompensaOro; }
    public int getLimiteNivelMinimo() { return limiteNivelMinimo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mision mision = (Mision) o;
        return Double.compare(mision.recompensaOro, recompensaOro) == 0 && 
               limiteNivelMinimo == mision.limiteNivelMinimo && 
               Objects.equals(titulo, mision.titulo) && 
               Objects.equals(rango, mision.rango);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, rango, recompensaOro, limiteNivelMinimo);
    }

    @Override
    public String toString() {
        return "Misión: '" + titulo + "' [Rango " + rango + "] (Min " + limiteNivelMinimo + " Lvl) - " + recompensaOro + "g";
    }
}
