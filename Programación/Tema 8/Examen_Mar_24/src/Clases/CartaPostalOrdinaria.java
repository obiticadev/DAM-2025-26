package Clases;

import java.time.LocalDate;

public class CartaPostalOrdinaria extends Carta {
    private int peso;

    public CartaPostalOrdinaria(String remitente, String destinatario, LocalDate fecha, String contenido, int peso) {
        super(remitente, destinatario, fecha, contenido);
        this.peso = peso;
    }

    @Override
    public String formatear() {
        StringBuilder sb = new StringBuilder();
        sb.append("CARTA POSTAL ORDINARIA\n").append("Remitente: ").append(remitente).append("\nDestinatario: ").append(destinatario).append("\nFecha: ").append(fecha).append("\nContenido: ").append(contenido).append("\nPeso: ").append(peso);
        return sb.toString();
    }

    @Override
    public int compareTo(Carta o) {
        return this.fecha.compareTo(o.fecha);
    }

}
