package com.bootcamp.finale.repositorio;

import com.bootcamp.finale.modelo.Usuario;
import java.util.List;
import java.util.ArrayList;

/**
 * EJERCICIO 14.03 — Repositorio de Usuarios (almacén en memoria)
 *
 * Implementa los TODOs hasta que UsuarioRepositorioTest pase en verde.
 *
 * Especificación:
 *   - Almacena Usuarios en una List<Usuario> interna (sin BD).
 *   - guardar(u): si el id ya existe, lanza IllegalArgumentException("id duplicado").
 *   - buscarPorId(id): devuelve el Usuario o NULL si no existe.
 *   - eliminar(id): elimina y devuelve true; devuelve false si no existía.
 *   - listarTodos(): devuelve UNA COPIA defensiva (no la lista interna).
 *   - count(): número de usuarios.
 *   - existe(id): boolean.
 */
public class UsuarioRepositorio {

    // TODO 1: declarar List<Usuario> usuarios = new ArrayList<>()

    // TODO 2: implementar guardar(Usuario u)
    //   - si u es null → IllegalArgumentException("usuario nulo")
    //   - si ya existe un Usuario con el mismo id → IllegalArgumentException("id duplicado")
    //   - si no, añadir a la lista
    public void guardar(Usuario u) {
        // implementar
    }

    // TODO 3: implementar buscarPorId(long id) → Usuario | null
    public Usuario buscarPorId(long id) {
        return null;
    }

    // TODO 4: implementar eliminar(long id) → boolean
    public boolean eliminar(long id) {
        return false;
    }

    // TODO 5: implementar listarTodos() → List<Usuario>
    //   - devolver una COPIA, no la lista interna
    public List<Usuario> listarTodos() {
        return null;
    }

    // TODO 6: implementar count() → int
    public int count() {
        return 0;
    }

    // TODO 7: implementar existe(long id) → boolean
    public boolean existe(long id) {
        return false;
    }
}
