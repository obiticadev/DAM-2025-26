package Clases;
/*
Pedimos datos de las tres personas
dni
nombre
apellidos
edad
sueldo
despues mostramos el total que tiene mi empresa de sueldo
*/

public class Persona {
    private String dni;
    private String nombre;
    private String apellidos;
    private int edad;
    private double sueldo;

    
    public Persona(String dni, String nombre, String apellidos, int edad, double sueldo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.sueldo = sueldo;
    }


    public double getSueldo() {
        return sueldo;
    }

    
}