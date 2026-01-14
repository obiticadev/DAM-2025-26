package Clases;

public class DNI {
    private String num_dni;
    private char letra_dni;

    public DNI(String num_dni, char letra_dni) {
        this.num_dni = num_dni;
        this.letra_dni = letra_dni;
    }

    public boolean esValido(String numeros, char letra) {
        boolean respuesta = numeros.length() == 8 && Character.isLetter(letra);
        return respuesta;
    }

    public String mostrarDni() {
        String salida;
        if (esValido(this.num_dni, this.letra_dni)) {
            salida = this.num_dni + String.valueOf(this.letra_dni).toUpperCase();
        } else {
            salida = "No cumples con las condiciones necesarias";
        }
        return salida;
    }

    public String getNum_dni() {
        return num_dni;
    }

    public char getLetra_dni() {
        return letra_dni;
    }

}
