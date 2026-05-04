package com.bootcamp.nivel3_dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Ej20 — Interfaz DAO: el contrato
 * Teoría: teoria/03_Patron_DAO_Completo.md
 *
 * Objetivo: definir la interfaz que actúa como contrato del DAO.
 * La interfaz declara QUÉ puede hacer el DAO sin decir CÓMO.
 *
 * La entidad que gestionará esta interfaz es Videojuego (del Ej19).
 * Aquí se declara la interfaz IVideojuegoDAO con las 6 operaciones CRUD.
 *
 * NOTA: En este ejercicio construirás dos cosas en el mismo archivo:
 *   1. La clase Videojuego (cópiala de Ej19 — ya la tienes memorizada)
 *   2. La interfaz IVideojuegoDAO con los 6 métodos
 */
public class Ej20_InterfazDAO {

    // ── Bloque 1: Entidad ─────────────────────────────────────────────────

    // TODO 1: Crea aquí la clase pública estática "Videojuego" con:
    //         - Atributos: id (int), titulo (String), genero (String), anio (int), precio (double)
    //         - Constructor sin id: Videojuego(String titulo, String genero, int anio, double precio)
    //         - Constructor con id (para cuando leas de BD): Videojuego(int id, String titulo, String genero, int anio, double precio)
    //         - Getters para todos los campos
    //         - toString() con todos los campos

    // ── Bloque 2: Interfaz ───────────────────────────────────────────────

    // TODO 2: Crea aquí la interfaz pública estática "IVideojuegoDAO" con estas 6 firmas:
    //
    //         void crearTabla() throws SQLException;
    //
    //         boolean insertar(Videojuego v) throws SQLException;
    //
    //         List<Videojuego> obtenerTodos() throws SQLException;
    //
    //         Videojuego obtenerPorId(int id) throws SQLException;
    //
    //         boolean actualizar(Videojuego v) throws SQLException;
    //
    //         boolean eliminar(int id) throws SQLException;
    //
    //         Recuerda: en una interface los métodos son implícitamente public abstract.
    //         Solo pon el tipo de retorno, el nombre y los parámetros — sin cuerpo.

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) {
        System.out.println("Interfaz IVideojuegoDAO definida.");
        System.out.println("Comprueba que el archivo compila sin errores.");
        System.out.println("El siguiente ejercicio la implementará.");
    }
}
