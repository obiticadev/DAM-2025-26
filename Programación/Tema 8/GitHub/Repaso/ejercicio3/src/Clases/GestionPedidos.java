package Clases;

import java.util.ArrayList;
import java.util.List;

public class GestionPedidos {

    private List<Pedidos> listaPedidos;

    public GestionPedidos() {
        this.listaPedidos = new ArrayList<>();
    }

    public boolean buscarPedido(Pedidos p) {
        return listaPedidos.contains(p);
    }

    public void listaPedidos() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listaPedidos.size(); i++) {
            sb.append("=== Lista de pedidos ===").append("\n")
                    .append(i + 1).append(" => ").append(listaPedidos.get(i).listarProductos());
        }
    }

}
