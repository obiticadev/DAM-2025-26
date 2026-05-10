package com.biblioteca.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.biblioteca.Clases.Libro;
import com.biblioteca.Enum.Aviso;

public class Exportador {

    private static final DateTimeFormatter tiempo = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    // EXPORTAR ----------------------------

    public static void guardarLibrosCSV(List<Libro> libros, String nombreArchivo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo))) {
            pw.println("id,titulo,autor,genero,isbn,anio_publicacion,copias_totales,copias_disponibles,tipo");
            for (Libro libro : libros) {
                pw.printf("%d,%s,%s,%s,%s,%d,%d,%d,%s%n",
                        libro.getId(),
                        escaparCSV(libro.getTitulo()),
                        escaparCSV(libro.getAutor()),
                        libro.getGenero(),
                        escaparCSV(libro.getIsbn()),
                        libro.getAnioPublicacion(),
                        libro.getCopiasTotales(),
                        libro.getCopiasDisponibles(),
                        libro.getTipo());
            }
            System.out.println("Listado guardado en: " + nombreArchivo + " (" + libros.size() + " libros)");
            new Logs("Exportado listado de " + libros.size() + " libros a " + nombreArchivo, Aviso.INFO).guardarLog();
        } catch (IOException e) {
            System.out.println("Error al guardar el fichero: " + e.getMessage());
            new Logs("Error al exportar listado: " + e.getMessage(), Aviso.PELIGRO).guardarLog();
        }
    }

    // AUXILIARES ----------------------------

    private static String escaparCSV(String valor) {
        if (valor == null) {
            return "";
        }
        return "\"" + valor + "\"";
    }

    public static String nombreArchivo(String prefijo) {
        return prefijo + "_" + LocalDateTime.now().format(tiempo) + ".csv";
    }
}
