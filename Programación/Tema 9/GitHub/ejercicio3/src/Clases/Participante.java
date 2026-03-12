package Clases;

public class Participante {
    private static int contador = 0;

    private int id;
    private String nombre;
    private String email;

    public Participante(String nombre, String email) {
        this.id = ++contador;
        this.nombre = nombre;
        this.email = email;
    }

    public boolean existeId(int num) {
        if (this.id == num) {
            return true;
        }
        return false;
    }

    public Participante devolverParticipante(int num) {
        if (this.id == num) {
            return this;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Participante [id=" + id + ", nombre=" + nombre + ", email=" + email + "]";
    }

    public static int getContador() {
        return contador;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

}
