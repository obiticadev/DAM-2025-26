package bloque3;

/**
 * EJERCICIO 16 — Representacion visual con StringBuilder
 * Teoria: teoria/03_Clases_Con_Arrays.md (seccion 6)
 *
 * Clase Pantalla que genera distintas representaciones visuales de un array.
 * Practica intensiva de StringBuilder y formateo.
 */
public class Ej16_Visualizar {

    private int[][] datos;

    public Ej16_Visualizar(int[][] datos) {
        this.datos = datos;
    }

    // TODO 1: Implementa mostrarSimple() — valores separados por espacio, filas por newline.
    //         Sin espacio al final de fila ni newline al final.
    public String mostrarSimple() { return ""; }

    // TODO 2: Implementa mostrarConBorde() — rodea la matriz con "+---+" estilo caja.
    //         Ejemplo 2x2: "+-------+\n| 1   2 |\n| 3   4 |\n+-------+"
    //         Usa String.format("%3d", valor) para alinear.
    public String mostrarConBorde() { return ""; }

    // TODO 3: Implementa mostrarConIndices() — cabecera de columnas y numeros de fila.
    //         Columnas 1-indexed, filas 1-indexed, con formato %3d.
    public String mostrarConIndices() { return ""; }

    // TODO 4: Implementa mostrarConSumaFila() — igual que simple pero al final de cada fila
    //         anade " | suma" donde suma es la suma de esa fila.
    public String mostrarConSumaFila() { return ""; }

    // TODO 5: Implementa mostrarHeatmap() — sustituye valores por simbolos:
    //         0 = " ", 1-3 = "░", 4-6 = "▒", 7-9 = "▓", 10+ = "█"
    //         Separados por espacio, filas por newline.
    public String mostrarHeatmap() { return ""; }

    // TODO 6: Implementa mostrarTranspuesto() — muestra la transpuesta sin modificar datos.
    public String mostrarTranspuesto() { return ""; }

    // TODO 7: Implementa getDatos() que devuelve copia profunda.
    public int[][] getDatos() { return null; }

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 16: Visualizar ===\n");
        int[][] d = {{1, 5, 10}, {3, 7, 2}, {8, 0, 4}};
        Ej16_Visualizar p = new Ej16_Visualizar(d);
        System.out.println("Simple:\n" + p.mostrarSimple());
        System.out.println("\nCon borde:\n" + p.mostrarConBorde());
        System.out.println("\nCon indices:\n" + p.mostrarConIndices());
        System.out.println("\nCon suma fila:\n" + p.mostrarConSumaFila());
        System.out.println("\nHeatmap:\n" + p.mostrarHeatmap());
    }
}
