import Clases.ProductoNoPerecedero;
import Clases.ProductoPerecedero;
import Clases.Tipo;

public class App {
    public static void main(String[] args) throws Exception {
        ProductoPerecedero alimento1 = new ProductoPerecedero("Manzana", 10, 2);
        ProductoPerecedero alimento2 = new ProductoPerecedero("Banana", 5.2, 0);

        ProductoNoPerecedero objeto1 = new ProductoNoPerecedero("Portatil", 520, 15, Tipo.ELECTRÓNICO);
        ProductoNoPerecedero objeto2 = new ProductoNoPerecedero("TV", 950, 25, Tipo.ELECTRÓNICO);

        alimento1.mostrarInformacion();
        alimento2.mostrarInformacion();
        objeto1.mostrarInformacion();
        objeto2.mostrarInformacion();
    }
}
