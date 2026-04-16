package bloque3;

/**
 * EJERCICIO 17 — Logica de negocio: contar, sumar, filtrar
 * Teoria: teoria/03_Clases_Con_Arrays.md (seccion 5)
 *
 * Clase Almacen con estanterias (filas) y posiciones (columnas).
 * Cada celda contiene la cantidad de producto almacenado (0 = vacio).
 */
public class Ej17_Logica {

    private int[][] estanterias;
    private double precioPorUnidad;

    // TODO 1: Implementa constructor Ej17_Logica(int filas, int columnas, double precio).
    //         Valida filas y columnas > 0, precio >= 0. Si no, IllegalArgumentException.
    //         Inicializa estanterias a 0.
    public Ej17_Logica(int filas, int columnas, double precioPorUnidad) {
        throw new UnsupportedOperationException("Implementa el constructor");
    }

    // TODO 2: Implementa almacenar(int fila, int col, int cantidad).
    //         Valida rango y cantidad > 0. Suma la cantidad a lo existente. Devuelve boolean.
    public boolean almacenar(int fila, int col, int cantidad) { return false; }

    // TODO 3: Implementa retirar(int fila, int col, int cantidad).
    //         Valida rango, cantidad > 0 y que hay suficiente stock. Resta. Devuelve boolean.
    public boolean retirar(int fila, int col, int cantidad) { return false; }

    // TODO 4: Implementa stockTotal() — suma de TODAS las celdas.
    public int stockTotal() { return 0; }

    // TODO 5: Implementa valorTotal() — stockTotal() * precioPorUnidad.
    public double valorTotal() { return 0.0; }

    // TODO 6: Implementa posicionesVacias() — cuenta celdas con valor 0.
    public int posicionesVacias() { return 0; }

    // TODO 7: Implementa estanteriaConMasStock() — devuelve el indice de la fila
    //         con mayor suma. Si hay empate, la primera.
    public int estanteriaConMasStock() { return 0; }

    public String mostrar() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < estanterias.length; i++) {
            for (int j = 0; j < estanterias[i].length; j++) {
                sb.append(String.format("%4d", estanterias[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getFilas() { return estanterias.length; }
    public int getColumnas() { return estanterias[0].length; }

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 17: Logica de Negocio ===\n");
        Ej17_Logica a = new Ej17_Logica(3, 4, 2.50);
        a.almacenar(0, 0, 10);
        a.almacenar(0, 1, 5);
        a.almacenar(1, 2, 20);
        System.out.println(a.mostrar());
        System.out.println("Stock total: " + a.stockTotal());
        System.out.println("Valor total: " + a.valorTotal());
        System.out.println("Posiciones vacias: " + a.posicionesVacias());
        System.out.println("Estanteria con mas stock: " + a.estanteriaConMasStock());
    }
}
