package Clases;

import java.util.Arrays;

public class EstanteLibros {
    private final int MAX_CAPACIDAD = 20;
    private String[] libros;
    private int contador;

    public EstanteLibros() {
        this.libros = new String[MAX_CAPACIDAD];
        Arrays.fill(this.libros, "");
        this.contador = 0;
    }

    public boolean agregarLibro(String titulo) {
        boolean aprobado;
        if (contador < MAX_CAPACIDAD) {
            this.libros[contador] = titulo;
            contador++;
            aprobado = true;
            return aprobado;
        } else {
            aprobado = false;
            return aprobado;
        }
    }

    public boolean quitarLibro(String titulo) {
        boolean aprobado;
        for (int i = 0; i < this.contador; i++) {
            if (this.libros[i].equals(titulo)) {

                for (int j = i; j < this.contador - 1; j++) {
                    this.libros[j] = this.libros[j + 1];
                }
                this.libros[this.contador - 1] = "";
                this.contador--;
                aprobado = true;
                return aprobado;
            } else {

            }
        }
        aprobado = false;
        return aprobado;
    }

    public String[] getLibros() {
        return libros;
    }

}
