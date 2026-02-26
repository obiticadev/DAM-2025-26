package Clases;

import java.util.ArrayList;

import Enum.NivelCurso;

public class Curso {
    private String codigo;
    private String nombre;
    private int horas;
    private NivelCurso nivelCurso;
    private ArrayList<Alumno> alumnosMatriculados;

    // Pasamos por parámetro solo estos atributos para cumplir con las normativas
    // estrictas del examen. El arrayList lo inicializamos vacío
    public Curso(String codigo, String nombre, int horas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.horas = horas;
        this.alumnosMatriculados = new ArrayList<>();
        this.nivelCurso = NivelCurso.BASICO;
        // Todos comienzan en Basico porque no hay más tiempo para completar el examen,
        // es una rayada, me he liado un montón con CursoDAO dentro de Curso
    }

    // Utilizamos el .contains que empleará el equals de Alumno para contrastar si
    // hay alguien con el mismo dni que el alumno pasado por parámetro
    public void matricularAlumno(Alumno a) {
        if (!alumnosMatriculados.contains(a)) {
            this.alumnosMatriculados.add(a);
        }
    }

    // En este caso utilizamos un foreach para recorrer el arrayList de alumnos
    // recogiendo el dni de cada uno y contrastándolo con el del parámetro
    public Alumno buscarAlumnoPorDni(String dni) {
        for (Alumno alumno : alumnosMatriculados) {
            if (alumno.getDni().equals(dni)) {
                return alumno;
            }
        }
        return null;
    }

    // Usamos el método equals solo por el código como hicimos en Alumno para
    // comparar solo por ese campo
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Curso other = (Curso) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

    // Utilizamos el toString para poder llamarlo para cuando
    // necesitemos listar cursos igual que en la trazabilidad del examen
    @Override
    public String toString() {
        return "Código: " + codigo + " Nombre: " + nombre + " Horas: " + horas;
    }

    // Agregamos el get de codigo para poder usarlo en el DAO en el método
    // buscarPorCodigo(String codigo)
    public String getCodigo() {
        return codigo;
    }

    // Lo necesitamos para la matriculación
    public ArrayList<Alumno> getAlumnosMatriculados() {
        return alumnosMatriculados;
    }

}
