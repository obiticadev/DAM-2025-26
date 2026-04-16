package DAO;

import java.util.ArrayList;
import java.util.HashSet;

import Clases.Servidor;

public class ServidorDAO {
    private ArrayList<Servidor> listServidores;
    private HashSet<Servidor> hashServidores;

    public ServidorDAO() {
        listServidores = new ArrayList<>();
        hashServidores = new HashSet<>();
        cargarDAO();
    }

    private void cargarDAO() {
        Servidor s1 = new Servidor("Windows10", "192.168.1.200");
        Servidor s2 = new Servidor("Proxmox", "192.168.1.201");
        Servidor s3 = new Servidor("Home Assistant", "192.168.1.250");
        Servidor s4 = new Servidor("Windows10", "192.168.1.200");
        Servidor s5 = new Servidor("Jellyfin", "192.168.1.222");

        listServidores.add(s1);
        listServidores.add(s2);
        listServidores.add(s3);
        listServidores.add(s4);
        listServidores.add(s5);

        hashServidores.add(s1);
        hashServidores.add(s2);
        hashServidores.add(s3);
        hashServidores.add(s4);
        hashServidores.add(s5);
    }

    public ArrayList<Servidor> devolverArrayServidor() {
        return new ArrayList<>(listServidores);
    }

    public HashSet<Servidor> devolverHashServidor() {
        return new HashSet<>(hashServidores);
    }

}
