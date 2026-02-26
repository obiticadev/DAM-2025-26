package DAO;

import java.util.ArrayList;

import Clases.Curso;

public class CursoDAO {
    private ArrayList<Curso> listarCursos;

    public CursoDAO() {
        this.listarCursos = new ArrayList<>();
    }

    public void guardar(Curso c) {
        if (!listarCursos.contains(c)) {
            this.listarCursos.add(c);
        }
    }

    public Curso buscarPorCodigo(String codigo) {
        for (Curso curso : listarCursos) {
            if (curso.getCodigo().equals(codigo)) {
                return curso;
            }
        }
        return null;
    }

    public String listar() {
        StringBuilder sb = new StringBuilder();
        sb.append("LISTA CURSOS:\n");
        if (listarCursos.size() != 0) {
            for (Curso curso : listarCursos) {
                sb.append(curso.toString()).append("\n");
            }
        }
        return sb.toString();
    }

}
