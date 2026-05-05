package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import Clases.AlumnnoPresencial;
import Clases.Alumno;
import Clases.AlumnoOnline;

public class DAOalumno {
    private List<Alumno> listaAlumno;

    public DAOalumno() throws FileNotFoundException, IOException, ClassNotFoundException {
        this.listaAlumno = new ArrayList<>();
        if (existeFichero()) {
            leerFichero();
        } else {
            cargarDatos();
            guardarFichero();
        }
    }

    private void cargarDatos() {
        // Usamos addAll() en lugar de add() para insertar múltiples elementos a la vez
        this.listaAlumno.addAll(List.of(
                // Alumnos Presenciales: nombre, curso, notaMedia, numModulo, familiaNumerosa
                new AlumnnoPresencial("Oliver", "1º DAM", 8.18, 6, false),
                new AlumnnoPresencial("Elisa", "SMR", 7.5, 3, true),
                new AlumnnoPresencial("Carlos", "Bases de Datos", 6.2, 2, false),

                // Alumnos Online: nombre, curso, notaMedia, numModulo, factorParticipacion
                new AlumnoOnline("Cris", "1º DAM", 9.5, 4, 0.95),
                new AlumnoOnline("Laura", "Java Backend", 8.8, 5, 0.8),
                new AlumnoOnline("Javier", "Entornos de Desarrollo", 5.5, 2, 0.4)));
    }

    private boolean existeFichero() {
        File f = new File("fichero.ser");
        return f.exists();
    }

    @SuppressWarnings("unchecked")
    public void leerFichero() throws FileNotFoundException, IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("fichero.ser"))) {
            this.listaAlumno = (List<Alumno>) ois.readObject();
        }
    }

    public void guardarFichero() throws FileNotFoundException, IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("fichero.ser"))) {
            oos.writeObject(this.listaAlumno);
        }
    }

    public void insertarAlumno(Alumno alumno) {
        this.listaAlumno.add(alumno);

    }

    public String listarAlumnos() {
        return this.listaAlumno.stream()
                .map(Alumno::getResumen)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public Optional<Alumno> buscarAlumno(String nombre) {
        return this.listaAlumno.stream()
                .filter(a -> a.getNombre().equals(nombre))
                .findFirst();
    }

    public Alumno buscarAlumnoMasNotaMedia() {
        return this.listaAlumno.stream()
                .max(Comparator.comparingDouble(Alumno::getNotaMedia))
                .orElse(null);
    }

    public double importeTotalMatriculas() {
        return this.listaAlumno.stream()
                .mapToDouble(Alumno::getMatricula)
                .sum();
    }

}
