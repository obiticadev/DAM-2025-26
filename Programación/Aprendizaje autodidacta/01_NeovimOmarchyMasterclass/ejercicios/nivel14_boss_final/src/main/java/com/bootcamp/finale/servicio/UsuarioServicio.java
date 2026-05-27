package com.bootcamp.finale.servicio;

import com.bootcamp.finale.modelo.Usuario;
import com.bootcamp.finale.repositorio.UsuarioRepositorio;
import java.util.List;
import java.util.ArrayList;

/**
 * EJERCICIO 14.04 — Servicio de Usuarios (lógica de negocio)
 *
 * Implementa los TODOs hasta que UsuarioServicioTest pase en verde.
 *
 * Especificación:
 *   - Recibe un UsuarioRepositorio por constructor (inyección).
 *   - registrar(nombre, email, edad): crea Usuario con id = max(ids)+1 (o 1 si vacío), lo guarda
 *     y lo devuelve.
 *   - actualizarEmail(id, nuevoEmail): busca el Usuario, llama a su setEmail. Si no existe, lanza
 *     IllegalArgumentException("no existe").
 *   - eliminar(id): delega en repositorio.eliminar.
 *   - mayoresDeEdad(): devuelve lista (copia) de usuarios con edad >= 18.
 *   - contar(): delega en repositorio.count.
 *
 * Pista para id auto-incremental: recorre listarTodos() para encontrar el max(id).
 */
public class UsuarioServicio {

    // TODO 1: declarar el campo final UsuarioRepositorio repo

    // TODO 2: constructor que recibe UsuarioRepositorio (no null)
    public UsuarioServicio(UsuarioRepositorio repo) {
        // implementar
    }

    // TODO 3: registrar(nombre, email, edad) → Usuario
    //   - calcula el siguiente id
    //   - crea el Usuario (aplicará sus propias validaciones)
    //   - lo guarda en el repositorio
    //   - lo devuelve
    public Usuario registrar(String nombre, String email, int edad) {
        return null;
    }

    // TODO 4: actualizarEmail(long id, String nuevoEmail) → void
    public void actualizarEmail(long id, String nuevoEmail) {
        // implementar
    }

    // TODO 5: eliminar(long id) → boolean
    public boolean eliminar(long id) {
        return false;
    }

    // TODO 6: mayoresDeEdad() → List<Usuario>
    //   - devuelve copia con los Usuarios cuya edad >= 18
    public List<Usuario> mayoresDeEdad() {
        return null;
    }

    // TODO 7: contar() → int
    public int contar() {
        return 0;
    }
}
