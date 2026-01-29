package Clases;

import java.util.ArrayList;

public class GestorHerramientas {
    protected ArrayList<Herramienta> herramienta = new ArrayList<>();

    public GestorHerramientas(ArrayList<Herramienta> herramienta) {
        this.herramienta = herramienta;
    }

    public void addHerramienta(Herramienta herramienta){
        this.herramienta.add(herramienta);
    }

    public void removeLastHerramienta(){
        this.herramienta.remove(this.herramienta.size()-1);
    }

    public ArrayList<Herramienta> getHerramienta() {
        return herramienta;
    }
    
    
    
}
