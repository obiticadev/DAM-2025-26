package modelos;

import java.util.List;

public class Estudiante {
    private String matricula;
    private String nombre;
    private int edad;
    private List<String> asignaturas;
    private double notaMedia;

    public Estudiante(String matricula, String nombre, int edad, List<String> asignaturas, double notaMedia) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.edad = edad;
        this.asignaturas = asignaturas;
        this.notaMedia = notaMedia;
    }

    public String getMatricula() { return matricula; }
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public List<String> getAsignaturas() { return asignaturas; }
    public double getNotaMedia() { return notaMedia; }

    @Override
    public String toString() {
        return String.format("Estudiante{matricula='%s', nombre='%s', media=%.2f}", matricula, nombre, notaMedia);
    }
}
