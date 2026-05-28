package com.bootcamp.finale.repositorio;

import com.bootcamp.finale.modelo.Usuario;
import java.util.List;
import java.util.ArrayList;

public class UsuarioRepositorio {

    private final List<Usuario> usuarios = new ArrayList<>();

    public void guardar(Usuario u) {
        if (u == null) {
            throw new IllegalArgumentException("usuario nulo");
        }
        if (existe(u.getId())) {
            throw new IllegalArgumentException("id duplicado");
        }
        usuarios.add(u);
    }

    public Usuario buscarPorId(long id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) return u;
        }
        return null;
    }

    public boolean eliminar(long id) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == id) {
                usuarios.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios);
    }

    public int count() {
        return usuarios.size();
    }

    public boolean existe(long id) {
        return buscarPorId(id) != null;
    }
}
