package Clases;

public class Persona {
    private int DNI;
    private String nombre;
    private String apellido;
    private int edad;

    
    public Persona(int dni, String nom, String ape, int ed) {
        DNI = dni;
        nombre = nom;
        apellido = ape;
        edad = ed;
    }

    public String consultarDatosPersona(){
        return "Nombre: " + nombre + " " + apellido + ", Edad: " + edad + ", DNI: " + DNI;
    }
    
    
    public int getDNI() {
        return DNI;
    }
    public void setDNI(int dNI) {
        DNI = dNI;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    
    
}
