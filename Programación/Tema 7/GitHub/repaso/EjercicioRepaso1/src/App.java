import Clases.Producto;
import Clases.ProductoElectronico;
import Clases.Tienda;

public class App {
    public static void main(String[] args) throws Exception {
        Producto p1 = new Producto("1415", "Manzana");
        Producto p2 = new Producto("1516", "Zanahoria", 21.25);

        ProductoElectronico pe1 = new ProductoElectronico("213", "Ordenador", 4);
        ProductoElectronico pe2 = new ProductoElectronico("356", "Portatil", 23650.0, 365);

        Tienda tienda = new Tienda();

        tienda.añadirProducto(p1);
        tienda.añadirProducto(p2);
        tienda.añadirProductoElectronico(pe1);
        tienda.añadirProductoElectronico(pe2);

        System.out.println(tienda.mostrarInformacion(25.0, 120.0));
    }
}
