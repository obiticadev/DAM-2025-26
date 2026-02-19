package DAO;

import java.util.ArrayList;

import Clases.Atleta;

public class DAOatletas {

    private ArrayList<Atleta> listaAtletas;

    public DAOatletas() {
        this.listaAtletas = new ArrayList<>();
        cargarDatos(listaAtletas);
    }

    private ArrayList<Atleta> cargarDatos(ArrayList<Atleta> listaAtletas) {

        Atleta atleta1 = new Atleta("Johansen", 128.5);
        Atleta atleta2 = new Atleta("Frenzel", 130.0);
        Atleta atleta3 = new Atleta("Lamparter", 125.0);
        Atleta atleta4 = new Atleta("Rehrl", 129.0);

        listaAtletas.add(atleta1);
        listaAtletas.add(atleta2);
        listaAtletas.add(atleta3);
        listaAtletas.add(atleta4);

        return listaAtletas;
    }

    public ArrayList<Atleta> entragarDatos() {
        return new ArrayList<>(listaAtletas);
    }

}
