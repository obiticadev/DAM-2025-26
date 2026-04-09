package DAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Clases.Burofax;
import Clases.CartaPostalCertificada;
import Clases.CartaPostalOrdinaria;
import Clases.Noticia;
import Interfaz.Formateable;
import MiExcepcion.CodigoCertificadoIncorrecto;

public class ComunicacionesDAO {
    private List<Formateable> listaElementos;

    public ComunicacionesDAO() {
        this.listaElementos = new ArrayList<>();
        try {
            cargarDatos();
        } catch (CodigoCertificadoIncorrecto e) {
            e.printStackTrace();
        }
    };

    private void cargarDatos() throws CodigoCertificadoIncorrecto {
        this.listaElementos.add(new CartaPostalCertificada("Juan Pérez", "María López",
                LocalDate.of(2026, 03, 21), "Este es el contenido de la carta certificada.", "CERT12345"));
        this.listaElementos.add(new CartaPostalOrdinaria("Ana Gómez", "Carlos Ruiz",
                LocalDate.of(2026, 03, 20), "Carta ordinaria de prueba.", 50));
        this.listaElementos.add(new Burofax("Empresa XYZ", "Pedro Martín",
                LocalDate.of(2026, 03, 19), "Burofax de aviso importante.", true));
        this.listaElementos.add(new Noticia("Noticia del día",
                "Hoy se lanza la nueva versión de la app.", "Redacción", LocalDate.of(2026, 03, 21)));
    }

    public void crearCartaCertificada(String remitente, String destinatario, LocalDate fecha, String contenido,
            String numeroCertificacion) throws CodigoCertificadoIncorrecto {
        this.listaElementos
                .add(new CartaPostalCertificada(remitente, destinatario, fecha, contenido, numeroCertificacion));
    }

    public void crearCartaPostalOrdinaria(String remitente, String destinatario, LocalDate fecha, String contenido,
            int peso) {
        this.listaElementos.add(new CartaPostalOrdinaria(remitente, destinatario, fecha, contenido, peso));
    }

    public void creaBurofax(String remitente, String destinatario, LocalDate fecha, String contenido,
            boolean conAcuseRecibo) {
        this.listaElementos.add(new Burofax(remitente, destinatario, fecha, contenido, conAcuseRecibo));
    }

    public void crearNoticia(String titulo, String contenido, String autor, LocalDate fechaPublicacion) {
        this.listaElementos.add(new Noticia(titulo, contenido, autor, fechaPublicacion));
    }

    public List<Formateable> getListaElementos() {
        return listaElementos;
    }

}
