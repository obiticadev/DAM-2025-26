package Clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Interfaz.Guardable;

public class GestorTareas implements Guardable {
    private List<Tarea> listaTarea;

    public GestorTareas() {
        this.listaTarea = new ArrayList<>(List.of(
                new Tarea("Estudiar Java", 5, false),
                new Tarea("Comprar pan", 2, true),
                new Tarea("Hacer ejercicio", 4, false)));
    }

    public void agregarTarea(Tarea t) {
        this.listaTarea.add(t);
    }

    public String mostrarTareas() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.listaTarea.stream()
                .map(Tarea::toString)
                .collect(Collectors.joining("\n")));
        return sb.toString();
    }

    public String mostrarImportantes() {
        return this.listaTarea.stream()
                .filter(t -> t.getPrioridad() >= 4)
                .map(Tarea::toString)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public void guardar(String nombreFichero) throws IOException {
        File file = new File(nombreFichero);
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            pw.println("titulo;prioridad;completada");
            for (Tarea tarea : listaTarea) {
                pw.printf("%s;%d;%b%n", tarea.getTitulo(), tarea.getPrioridad(), tarea.isCompletada());
            }
        }
    }

    @Override
    public void cargar(String nombreFichero) throws FileNotFoundException, IOException {
        File file = new File(nombreFichero);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            List<String> lista = new ArrayList<>();
            String linea;
            while ((linea = br.readLine()) != null) {
                lista.add(linea);
            }
            lista.remove(0);
            for (String string : lista) {
                String[] array = string.split(";");
                this.listaTarea.add(new Tarea(array[0], Integer.parseInt(array[1]),
                        Boolean.parseBoolean(array[2])));
            }
        }
    }

}
