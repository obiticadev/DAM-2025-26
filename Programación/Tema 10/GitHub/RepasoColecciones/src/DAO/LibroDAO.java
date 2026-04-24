package DAO;

import java.util.HashMap;
import java.util.Map;

import Clases.Libro;

public class LibroDAO {
    private Map<String, Libro> libros;

    public LibroDAO() {
        libros = new HashMap<>();
        libros.put("MAT-001", new Libro("MAT-001", "Álgebra Lineal", "Gilbert Strang"));
        libros.put("MAT-002", new Libro("MAT-002", "Cálculo", "James Stewart"));
        libros.put("INF-001", new Libro("INF-001", "Clean Code", "Robert C. Martin"));
        libros.put("INF-002", new Libro("INF-002", "El Programador Pragmático", "Andrew Hunt"));
        libros.put("FIS-001", new Libro("FIS-001", "Física Universitaria", "Sears y Zemansky"));
        libros.put("HIS-001", new Libro("HIS-001", "Sapiens", "Yuval Noah Harari"));
        libros.put("LIT-001", new Libro("LIT-001", "Don Quijote de la Mancha", "Miguel de Cervantes"));
        libros.put("INF-003", new Libro("INF-003", "Estructura de Datos en Java", "Mark Allen Weiss"));
        libros.put("QUI-001", new Libro("QUI-001", "Química General", "Petrucci"));
        libros.put("ECO-001", new Libro("ECO-001", "Principios de Economía", "N. Gregory Mankiw"));
    }

    public Map<String, Libro> getLibros() {
        return libros;
    }

    public String listarLibros() {
        int contador = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("=== CATÁLOGO DE LIBROS ===\n");
        for (Map.Entry<String, Libro> tuplaEntry : libros.entrySet()) {
            sb.append(contador++ + 1).append("-Sig:").append(tuplaEntry.getKey())
                    .append("-Título:").append(tuplaEntry.getValue().getTitulo())
                    .append("-Autor:").append(tuplaEntry.getValue().getAutor())
                    .append("\n");
        }
        return sb.toString();

    }

    public Libro buscarPorSignatura(String signatura) {
        for (Map.Entry<String, Libro> tuplaEntry : libros.entrySet()) {
            if (tuplaEntry.getKey().equals(signatura)) {
                return tuplaEntry.getValue();
            }
        }
        return null;
    }
}