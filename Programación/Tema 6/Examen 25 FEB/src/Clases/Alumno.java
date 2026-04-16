package Clases;

public class Alumno {
    private String dni;
    private String nombre;
    private String email;

    // Pasamos por parámetro todos los atributos ya que son necesarios para
    // completar una matricula
    public Alumno(String dni, String nombre, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
    }

    // Implementamos el método iquals solo por su dni para que se compare únicamente
    // por ese atributo

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dni == null) ? 0 : dni.hashCode());
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
        Alumno other = (Alumno) obj;
        if (dni == null) {
            if (other.dni != null)
                return false;
        } else if (!dni.equals(other.dni))
            return false;
        return true;
    }

    // Utilizamos el toString para poder llamarlo para cuando
    // necesitemos listar alumnos igual que en la trazabilidad del examen
    @Override
    public String toString() {
        return "Alumno {dni='" + dni + "', nombre='" + nombre + "', email='" + email + "'}";
    }

    // Generamos el get de DNI que se usará en la clase Curso con el método
    // buscarAlumnoPorDni(String dni)
    public String getDni() {
        return dni;
    }

}
