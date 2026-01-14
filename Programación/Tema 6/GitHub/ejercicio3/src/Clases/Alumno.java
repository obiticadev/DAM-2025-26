package Clases;

import java.util.Arrays;

public class Alumno {
    private int contador;
    private String nombre;
    private String num_matricula;
    private Modulo[] listaModulos;

    public Alumno(String nombre, String matricula) {
        this.nombre = nombre;
        this.contador = 0;
        this.num_matricula = String.valueOf(Integer.parseInt(matricula) + 1000);
        this.listaModulos = new Modulo[1];
    }

    public void insertarModulo(String nombre_modulo, String codigo) {
        if (this.contador == this.listaModulos.length) {
            redimensionar();
        }
        this.listaModulos[this.contador] = new Modulo(nombre_modulo, codigo);
        contador++;
    }

    public String mostrarInfo() {
        String respuesta = "";
        if (this.listaModulos.length != 1) {

            respuesta += "MÓDULOS:\n";
            for (int i = 0; i < this.contador; i++) {
                respuesta += listaModulos[i].getNombre_modulo() + " [" + listaModulos[i].getCodigo() + "]\n";
                respuesta += "NOTAS:\n";
                Nota listaNotasSalida[] = listaModulos[i].getListaNotas();
                for (int j = 0; j <= listaModulos[i].getContador(); j++) {
                    respuesta += "Nota " + (j + 1) + ": " + listaNotasSalida[j].getNota() + " ["
                            + listaNotasSalida[j].getTipo() + "]\n";
                }
                respuesta += "Nota media: " + listaModulos[i].notaMedia();
            }
        } else {
            return "No hay datos";
        }
        return respuesta;
    }

    public void redimensionar() {
        this.listaModulos = Arrays.copyOf(this.listaModulos, listaModulos.length * 2);
    }

    public int getContador() {
        return contador;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNum_matricula() {
        return num_matricula;
    }

    public Modulo[] getListaModulos() {
        return listaModulos;
    }
}
