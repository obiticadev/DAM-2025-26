package Clases;

public class Videojuego {
    protected String titulo;
    protected double valoracion = 0;
    protected int numValoraciones = 0;
    protected final int MIN_PUNTUACION = 0;
    protected final int MAX_PUNTUACION = 10;

    public Videojuego(String titulo) {
        this.titulo = titulo;
    }

    public boolean valorarVidejuego(double puntuacion){
        if (puntuacion >= MIN_PUNTUACION && puntuacion <= MAX_PUNTUACION) {
            valoracion += puntuacion;
            return true;
        } else {
            return false;
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public String valoracionesMedias(){
        StringBuilder salida = new StringBuilder();
        salida.append(titulo)
              .append(": ")
              .append(media);
    }




    
}
