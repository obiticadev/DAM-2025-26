package Interfaz;

import java.io.Serializable;

public interface Alumno extends Serializable {
    String getNombre();

    String getCurso();

    double getNotaMedia();

    double getMatricula();

    String getResumen();
}
