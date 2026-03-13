package DAO;

import java.util.ArrayList;

import Clases.Participante;
import MiExcepcion.MiExcepcion;

public class ParticipanteDAO {
    private ArrayList<Participante> listaRegistrados;

    public ParticipanteDAO() {
        listaRegistrados = new ArrayList<>();
        cargarDatos();
    }

    private void cargarDatos() {
        Participante p1 = new Participante("Juan Pérez", "juan.perez@email.com");
        Participante p2 = new Participante("María García", "m.garcia@outlook.com");
        Participante p3 = new Participante("Carlos Rodríguez", "carlos_rod@gmail.com");
        Participante p4 = new Participante("Ana Martínez", "ana.mtz@empresa.es");
        Participante p5 = new Participante("Luis Sánchez", "lsanchez@servidor.net");
        Participante p6 = new Participante("Elena López", "elena_lopez@universidad.edu");
        Participante p7 = new Participante("Diego Torres", "dtorres@protonmail.com");
        Participante p8 = new Participante("Lucía Fernández", "luciaf@yahoo.es");
        Participante p9 = new Participante("Javier Ruiz", "javi.ruiz88@webmail.com");
        Participante p10 = new Participante("Sofía Castro", "sofia.castro@icloud.com");

        listaRegistrados.add(p1);
        listaRegistrados.add(p2);
        listaRegistrados.add(p3);
        listaRegistrados.add(p4);
        listaRegistrados.add(p5);
        listaRegistrados.add(p6);
        listaRegistrados.add(p7);
        listaRegistrados.add(p8);
        listaRegistrados.add(p9);
        listaRegistrados.add(p10);
    }

    public ArrayList<Participante> devolverDatos() {
        return new ArrayList<>(listaRegistrados);
    }

    public boolean agregarRegistro(String nombre, String email) throws MiExcepcion {
        if (nombre.length() != 0 && email.length() != 0) {
            Participante p = new Participante(nombre, email);
            listaRegistrados.add(p);
            return true;
        }
        throw new MiExcepcion("Nombre o email debe contener al menos un caracter");
    }

}
