package DAO;

import java.util.LinkedList;
import java.util.List;

import Clases.Participante;
import Clases.Persona;

public class DAOparticipantes {
    private List<Persona> listaPersonas;

    public DAOparticipantes() {
        this.listaPersonas = new LinkedList<>();
        cargarDatos();
    }

    private void cargarDatos() {
        Persona p1 = new Participante("Oliver", "Bitica", "Y344245689");
        Persona p2 = new Participante("Lucas", "García", "12345678A");
        Persona p3 = new Participante("María", "López", "87654321B");
        Persona p4 = new Participante("Ahmed", "Hassan", "X9876543C");
        Persona p5 = new Participante("Sara", "Martínez", "55443322D");
        Persona p6 = new Participante("John", "Smith", "11223344E");
        Persona p7 = new Participante("Elena", "Petrov", "Y1234567F");
        Persona p8 = new Participante("Carlos", "Ruiz", "99887766G");
        Persona p9 = new Participante("Laura", "Ferrero", "44556677H");
        Persona p10 = new Participante("Yuki", "Tanaka", "Z5566778J");
        Persona p11 = new Participante("Diego", "Costa", "33221100K");

        listaPersonas.add(p1);
        listaPersonas.add(p2);
        listaPersonas.add(p3);
        listaPersonas.add(p4);
        listaPersonas.add(p5);
        listaPersonas.add(p6);
        listaPersonas.add(p7);
        listaPersonas.add(p8);
        listaPersonas.add(p9);
        listaPersonas.add(p10);
        listaPersonas.add(p11);
    }

    public LinkedList<Persona> devolverDatos() {
        return new LinkedList<>(listaPersonas);
    }

}
