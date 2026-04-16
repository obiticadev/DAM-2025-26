package Clases;

import java.time.LocalDate;

public class Burofax extends Carta {
    private boolean conAcuseRecibo;

    public Burofax(String remitente, String destinatario, LocalDate fecha, String contenido, boolean conAcuseRecibo) {
        super(remitente, destinatario, fecha, contenido);
        this.conAcuseRecibo = conAcuseRecibo;
    }

    @Override
    public String formatear() {
        StringBuilder sb = new StringBuilder();
        sb.append("CARTA CERTIFICADA\n").append("Remitente: ").append(remitente).append("\nDestinatario: ")
                .append(destinatario).append("\nFecha: ").append(fecha).append("\nContenido: ").append(contenido)
                .append("\nCon acuse de recibo (si/no): ").append(conAcuseRecibo);
        return sb.toString();
    }

    @Override
    public int compareTo(Carta o) {
        return this.fecha.compareTo(o.fecha);
    }
}
