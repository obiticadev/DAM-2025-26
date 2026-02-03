package Clases;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Publicacion {
    // ---- ATRIBUTOS PROPIOS
    private final int MAX_CONTENIDOS = 10;
    private int contadorContenido = 0;

    // ---- ATRIBUTOS EXAMEN
    private int idUsuario;
    private String[] contenidos = new String[MAX_CONTENIDOS];
    private LocalDateTime fechaHoraPublicacion;
    private LocalDateTime fechaHoraModificación;

    // ---- CONSTRUCTORES
    public Publicacion(int idUsuario, String contenidos) {
        this.idUsuario = idUsuario;
        agregarContenido(contenidos);
        this.fechaHoraPublicacion = LocalDateTime.now();
        this.fechaHoraModificación = this.fechaHoraPublicacion;
    }

    public Publicacion(int idUsuario) {
        this.idUsuario = idUsuario;
        fechaHoraPublicacion = LocalDateTime.now();
        fechaHoraModificación = fechaHoraPublicacion;
    }

    // ---- MÉTODOS PROPIOS
    public void actualizarHora() {
        this.fechaHoraModificación = LocalDateTime.now();
    }

    // ---- MÉTODOS EXAMEN
    public boolean agregarContenido(String contenido) {
        if (contenido.equals("")) {
            return false;
        }
        if (contadorContenido < contenidos.length) {
            contenidos[contadorContenido] = contenido;
            contadorContenido++;
            actualizarHora();
            return true;
        } else {
            return false;
        }
    }

    public boolean eliminarUltimoContenido() {
        if (contadorContenido != 0) {
            contenidos[contadorContenido - 1] = null;
            contadorContenido--;
            actualizarHora();
            return true;
        } else {
            return false;
        }
    }

    public String mostrarFeeds() {
        StringBuilder salida = new StringBuilder();
        salida.append("|> ID de usuario: ").append(getIdUsuario()).append("\n")
              .append("|> Fecha y hora de la modificación: ").append(getFechaHoraModificación()).append("\n");
        if (contadorContenido != 0) {
            salida.append("|> Todos los contenidos de la aplicación:").append("\n");
            for (int i = 0; i < contadorContenido; i++) {
                salida.append("\t").append(i+1).append("-> ").append(contenidos[i]).append("\n");
            }

        } else {
            salida.append("|> No hay ningún contenido en la aplicación");
        }
        return salida.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Publicacion other = (Publicacion) obj;
        if (idUsuario != other.idUsuario)
            return false;
        if (!Arrays.equals(contenidos, other.contenidos))
            return false;
        return true;
    }

    // ---- GETTERS Y SETTERS
    public int getIdUsuario() {
        return idUsuario;
    }

    public LocalDateTime getFechaHoraPublicacion() {
        return fechaHoraPublicacion;
    }

    public LocalDateTime getFechaHoraModificación() {
        return fechaHoraModificación;
    }

}
