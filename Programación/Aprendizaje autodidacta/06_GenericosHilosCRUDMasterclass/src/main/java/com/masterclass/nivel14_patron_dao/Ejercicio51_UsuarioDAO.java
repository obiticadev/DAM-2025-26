package com.masterclass.nivel14_patron_dao;

import java.util.List;

/**
 * EJERCICIO 51: El Contrato Abierto (Interface DAO)
 * 
 * OBJETIVO: 
 * Redactar de forma abstracta estricta las funcionalidades estándar de persistencia
 * sobre una sub-entidad, sin dictar ni un milímetro de funcionalidad interna.
 * 
 * APOYO TEÓRICO: 
 * Revisa '14_Patron_DAO.md' (Sección: "Las Partes Ocultas")
 */
// TODO 1: Crea y forja publicamente la caja de transporte Data Transfer Object (DTO) llamada aquí 
// 'UsuarioEntity' de forma interna. Incluye propiedades como Long id, y String nombre.

// TODO 2: Observa y convierte a esta 'class' pura principal al estado de 'interface'.
public class Ejercicio51_UsuarioDAO {

    // TODO 3: Redacta los métodos (firmas puras sin body '{}') característicos de todo ciclo CRUD:
    // 1. crearUsuario(UsuarioEntity req) -> void o boolean
    // 2. buscarPorId(Long id) -> UsuarioEntity (o utilidades modernas de Java..)
    // 3. listarTodos() -> List<UsuarioEntity>
    // 4. actualizar(UsuarioEntity mutacion) -> void
    // 5. borrar(Long id) -> void

    // A partir de ahora los ingenieros que dependan de ti consumirán únicamente estas firmas.
}
