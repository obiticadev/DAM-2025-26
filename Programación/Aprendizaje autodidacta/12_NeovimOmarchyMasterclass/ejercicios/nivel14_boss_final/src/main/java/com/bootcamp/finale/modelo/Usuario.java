package com.bootcamp.finale.modelo;

/**
 * EJERCICIO 14.02 — Modelo Usuario
 *
 * Implementa los TODOs hasta que UsuarioTest pase en verde.
 *
 *   <leader>tt sobre un test individual
 *   o desde terminal: mvn test -Dtest=UsuarioTest
 *
 * Especificación:
 *   - Atributos privados: id (long), nombre (String), email (String), edad (int).
 *   - Constructor con los 4 atributos.
 *   - Validar en el constructor:
 *       - id > 0 → si no, IllegalArgumentException("id debe ser positivo")
 *       - nombre no nulo ni vacío → si no, IllegalArgumentException("nombre obligatorio")
 *       - email no nulo y debe contener '@' → si no, IllegalArgumentException("email inválido")
 *       - edad >= 0 → si no, IllegalArgumentException("edad no negativa")
 *   - Getters de los 4 atributos.
 *   - Setter SOLO para email (con la misma validación).
 *   - equals() y hashCode() basados en id.
 *   - toString() con formato exacto: "Usuario{id=X, nombre='Y', email='Z', edad=N}"
 */
public class Usuario {

    // TODO 1: declarar los 4 atributos privados (id, nombre, email, edad)

    // TODO 2: implementar constructor con los 4 atributos
    //   - aplicar las validaciones descritas en el javadoc
    public Usuario(long id, String nombre, String email, int edad) {
        // implementar
    }

    // TODO 3: implementar getters: getId, getNombre, getEmail, getEdad

    // TODO 4: implementar setEmail(String) con validación

    // TODO 5: implementar equals/hashCode basados en id
    //   - ten en cuenta el contrato: dos Usuarios son iguales si y solo si tienen el mismo id

    // TODO 6: implementar toString() con formato:
    //   "Usuario{id=1, nombre='Ana', email='a@b.com', edad=30}"
}
