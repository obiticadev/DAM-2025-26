package com.bootcamp.finale.servicio;

import com.bootcamp.finale.modelo.Usuario;
import com.bootcamp.finale.repositorio.UsuarioRepositorio;
import java.util.List;
import java.util.ArrayList;

public class UsuarioServicio {

    private final UsuarioRepositorio repo;

    public UsuarioServicio(UsuarioRepositorio repo) {
        if (repo == null) {
            throw new IllegalArgumentException("repositorio nulo");
        }
        this.repo = repo;
    }

    public Usuario registrar(String nombre, String email, int edad) {
        long nextId = 1L;
        for (Usuario u : repo.listarTodos()) {
            if (u.getId() >= nextId) {
                nextId = u.getId() + 1;
            }
        }
        Usuario nuevo = new Usuario(nextId, nombre, email, edad);
        repo.guardar(nuevo);
        return nuevo;
    }

    public void actualizarEmail(long id, String nuevoEmail) {
        Usuario u = repo.buscarPorId(id);
        if (u == null) {
            throw new IllegalArgumentException("no existe");
        }
        u.setEmail(nuevoEmail);
    }

    public boolean eliminar(long id) {
        return repo.eliminar(id);
    }

    public List<Usuario> mayoresDeEdad() {
        List<Usuario> resultado = new ArrayList<>();
        for (Usuario u : repo.listarTodos()) {
            if (u.getEdad() >= 18) {
                resultado.add(u);
            }
        }
        return resultado;
    }

    public int contar() {
        return repo.count();
    }
}
