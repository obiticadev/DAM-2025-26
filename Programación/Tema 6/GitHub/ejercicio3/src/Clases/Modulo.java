package Clases;

import java.util.Arrays;

public class Modulo {
    private String nombre_modulo;
    private String codigo;
    private Nota[] listaNotas;
    private int contador;

    public Modulo(String nombre_modulo, String codigo) {
        this.nombre_modulo = nombre_modulo;
        this.codigo = codigo;
        this.listaNotas = new Nota[1];
        this.contador = 0;
    }

    public boolean insertarNota(TipoNota evaluacion, double nota) {

        if (nota < 0 || nota > 10) {
            return false;
        }
        if (this.contador == this.listaNotas.length) {
            redimensionar();
        }
        this.listaNotas[this.contador] = new Nota(evaluacion, nota);
        contador++;
        return true;
    }

    public void redimensionar() {
        this.listaNotas = Arrays.copyOf(this.listaNotas, listaNotas.length * 2);
    }

    public double notaMedia() {
        double suma = 0;
        double promedio;
        if (this.contador == 0) {
            return promedio = 0;
        }
        for (int i = 0; i < this.contador; i++) {
            suma += this.listaNotas[i].getNota();
        }
        promedio = suma / (this.contador);

        return promedio;

    }

    public String getNombre_modulo() {
        return nombre_modulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public Nota[] getListaNotas() {
        return listaNotas;
    }

    public int getContador() {
        return contador;
    }

}
