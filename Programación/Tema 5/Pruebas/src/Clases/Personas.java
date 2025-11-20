package Clases;

public class Personas {
    private String nombreApellidos;
    private String NIA;
    private String fechaMatricula;
    
    
    public Personas(String nombreApellidos, String NIA, String fechaMatricula) {
        this.nombreApellidos = nombreApellidos;
        this.NIA = NIA;
        this.fechaMatricula = fechaMatricula;
    }


    public String getNombreApellidos() {
        return nombreApellidos;
    }


    public void setNombreApellidos(String nombreApellidos) {
        this.nombreApellidos = nombreApellidos;
    }


    public String getNIA() {
        return NIA;
    }


    public void setNIA(String NIA) {
        this.NIA = NIA;
    }


    public String getFechaMatricula() {
        return fechaMatricula;
    }


    public void setFechaMatricula(String fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public String consultarDatosAlumno(){
        return "Nombre: " + nombreApellidos + " NIA: " + NIA + " Fecha Matricula: " + fechaMatricula;
    }


    

    

    

    
}
