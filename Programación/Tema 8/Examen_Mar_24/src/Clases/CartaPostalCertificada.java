package Clases;

import java.time.LocalDate;

import MiExcepcion.CodigoCertificadoIncorrecto;

public class CartaPostalCertificada extends Carta {

    private String numeroCertificacion;

    public CartaPostalCertificada(String remitente, String destinatario, LocalDate fecha, String contenido,
            String numeroCertificacion) throws CodigoCertificadoIncorrecto {
                super(remitente, destinatario, fecha, contenido);
                if (numeroCertificacion.length() < 8 | numeroCertificacion.length() > 10) {
                    throw new CodigoCertificadoIncorrecto("La carta certificada con remitente: " + remitente + " y destinatario " + destinatario + " con fecha " + fecha + " y contenido " + contenido + " tiene un número de certificación: " + numeroCertificacion + " que no es correcto.");
                }
        this.numeroCertificacion = numeroCertificacion;
        
    }

    @Override
    public String formatear() {
        StringBuilder sb = new StringBuilder();
        sb.append("CARTA CERTIFICADA\n").append("Remitente: ").append(remitente).append("\nDestinatario: ")
                .append(destinatario).append("\nFecha: ").append(fecha).append("\nContenido: ").append(contenido)
                .append("\nNúmero certificación: ").append(numeroCertificacion);
        return sb.toString();
    }

    @Override
    public int compareTo(Carta o) {
        return this.fecha.compareTo(o.fecha);
    }
}
