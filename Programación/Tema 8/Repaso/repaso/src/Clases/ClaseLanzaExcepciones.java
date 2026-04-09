package Clases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClaseLanzaExcepciones {
    private List<String> listaNombres;

    public ClaseLanzaExcepciones() {
        listaNombres = new ArrayList<>(Arrays.asList("Lucas", "Mateo", null, "admin", "Mario"));
    }

    public boolean validarEdad(int edad) throws Exception {
        if (edad < 0) {
            throw new IllegalArgumentException("Argumento ilegal");
        } else if (edad < 18) {
            throw new Exception("Menor de edad");
        } else {
            return true;
        }

    }

    public int dividir(int a, int b) {
        return a / b;
    }

    public void buscarUsuario(String nombre) {
        if (nombre.equals("admin")) {
            throw new RuntimeException("Usuario no permitido");
        } else if (!listaNombres.contains(nombre)) {
            throw new NullPointerException("La palabra " + nombre + " no está en la lista");
        } else {
            System.out.println(nombre + ", se encuentra en la lista");
        }
    }

    public void validarPassword(String pass) throws PasswordInvalidException {
        if (pass.length() < 6) {
            throw new PasswordInvalidException("La longitud de " + pass + " es inferior a 6");
        }
    }
}
