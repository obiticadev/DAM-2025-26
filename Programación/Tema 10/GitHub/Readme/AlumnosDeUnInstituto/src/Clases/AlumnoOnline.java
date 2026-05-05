package Clases;

public class AlumnoOnline extends Alumno {

    private final int PRECIO_BASE = 20;

    private double factorParticipacion;

    public AlumnoOnline(String nombre, String curso, double notaMedia, int numModulo, double factorParticipacion) {
        super(nombre, curso, notaMedia, numModulo);
        if (factorParticipacion > 1) {
            factorParticipacion = 1;
        } else if (factorParticipacion < 0) {
            factorParticipacion = 0;
        }
        this.factorParticipacion = factorParticipacion;
    }

    @Override
    public double getMatricula() {
        return PRECIO_BASE;
    }

    @Override
    public double getNotaMedia() {
        return notaMedia * factorParticipacion;
    }

    @Override
    public String getResumen() {
        return "AlumnoOnline [factorParticipacion=" + factorParticipacion + ", nombre=" + nombre + ", curso=" + curso
                + ", notaMedia=" + notaMedia + ", numModulo=" + numModulo + "]";
    }

}
