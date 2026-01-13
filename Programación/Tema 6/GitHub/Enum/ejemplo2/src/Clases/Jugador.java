package Clases;

public class Jugador {
    private String nombreCompleto;
    private String fechaNacimiento;
    private double altura;
    private int dorsal;
    // enum - Base Escolta Alero Ala-Pivot Pivot
    private Posicion posicion;

    
    public Jugador(String nombreCompleto, String fechaNacimiento, double altura, int dorsal, Posicion posicion) {
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.altura = altura;
        this.dorsal = dorsal;
        this.posicion = posicion;
    }


    public String getNombreCompleto() {
        return nombreCompleto;
    }


    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }


    public String getFechaNacimiento() {
        return fechaNacimiento;
    }


    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


    public double getAltura() {
        return altura;
    }


    public void setAltura(double altura) {
        this.altura = altura;
    }


    public int getDorsal() {
        return dorsal;
    }


    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }


    public Posicion getPosicion() {
        return posicion;
    }


    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }


    @Override
    public String toString() {
        return "Jugador [nombreCompleto=" + nombreCompleto + ", fechaNacimiento=" + fechaNacimiento + ", altura="
                + altura + ", dorsal=" + dorsal + ", posicion=" + posicion + "]";
    }


    
    
    
}
