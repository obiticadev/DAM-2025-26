import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Clases.Persona;

public class App {
    private static List<String> lista;
    private static List<Persona> listaPersonas;
    private static Map<String, Integer> contadorMapeado;
    private static Set<Persona> listaFiltrada;

    private static Map<String, Integer> mapa;

    public static void main(String[] args) throws Exception {
        String separador = "\n-----------------------------------\n";
        System.out.println(separador);
        ejercicio1();
        System.out.println(separador);
        ejercicio2();
        System.out.println(separador);
        ejercicio3();
        System.out.println(separador);
        ejercicio4y5();
        System.out.println(separador);
        ejercicio6();
        System.out.println(separador);
        ejercicio7();
        System.out.println(separador);
        ejercicio8();
        System.out.println(separador);

    }

    // MÉTODOS PROPIOS

    private static void mostrar() {
        System.out.println("== LISTA ACTUAL ==");
        for (String nombres : lista) {
            System.out.println(nombres);
        }
    }

    // EJERCICIOS

    private static void ejercicio1() {
        System.out.println("Ejercicio 1:");
        lista = new ArrayList<>(List.of("Oliver", "Samuel", "Laura", "Alex", "Mario"));
        mostrar();
    }

    private static void ejercicio2() {
        System.out.println("Ejercicio 2:");

        String nombre = "Oliver";
        if (lista.contains(nombre)) {
            lista.remove(nombre);
            System.out.println("Eliminado el nombre: " + nombre);
        } else {
            System.out.println("No existe el nombre: " + nombre);
        }

        mostrar();
    }

    private static void ejercicio3() {
        System.out.println("Ejercicio 3:");
        mapa = new HashMap<>(Map.of("Stuart", 5, "Lopez", 78, "Lucas", 1, "Santi", 18, "Luis", 18));
        for (Map.Entry tupla : mapa.entrySet()) {
            System.out.println(tupla.getKey() + ", " + tupla.getValue());
        }

    }

    private static void ejercicio4y5() {
        System.out.println("Ejercicio 4 y 5:");
        String nombre = "Lopez";
        if (mapa.containsKey(nombre)) {
            System.out.println("La edad de " + nombre + " es " + mapa.get(nombre));
        } else {
            System.out.println(nombre + " no está en la lista");
        }
    }

    private static void ejercicio6() {
        System.out.println("Ejercicio 6:");
        int count = 0;
        Integer num = 18;
        for (Map.Entry tupla : mapa.entrySet()) {
            if (tupla.getValue().equals(num)) {
                count++;
            }
        }
        System.out.println("Existen " + count + " con " + num + " años");
    }

    private static void ejercicio7() {
        System.out.println("Ejercicio 7:");
        listaPersonas = new LinkedList<>(List.of(new Persona("Y3142406X", "Oliver", 24),
                new Persona("34567890", "Luis", 34), new Persona("1234", "Paco", 12), new Persona("1234", "Paco", 12),
                new Persona("1234", "Lucas", 12), new Persona("123", "Paco", 12), new Persona("123", "Santi", 34)));
        for (Persona persona : listaPersonas) {
            System.out.println(persona.toString());
        }
        System.out.println("--------------------");
        listaFiltrada = new HashSet<>(listaPersonas);
        for (Persona persona : listaFiltrada) {
            System.out.println(persona.toString());
        }
    }

    private static void ejercicio8() {
        System.out.println("Ejercicio 8:");
        String frase = "Esta Esta Esta es es mi mi prueba prueba prueba prueba final";
        String[] fraseArray = frase.split(" ");
        contadorMapeado = new HashMap<>();
        for (String palabra : fraseArray) {
            contadorMapeado.put(palabra, 0);
        }
        for (String palabra : fraseArray) {
            if (contadorMapeado.containsKey(palabra)) {
                int a = contadorMapeado.get(palabra);
                a++;
                contadorMapeado.put(palabra, a);
            }
        }
        for (Map.Entry tupla : contadorMapeado.entrySet()) {
            System.out.println(tupla.toString());
        }
    }
}
