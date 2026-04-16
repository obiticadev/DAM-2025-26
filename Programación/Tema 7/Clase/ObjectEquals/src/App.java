import javax.swing.plaf.basic.BasicSplitPaneUI.BasicVerticalLayoutManager;

import Clases.Ejemplo;

public class App {
    public static void main(String[] args) throws Exception {

        Ejemplo ejemplo1 = new Ejemplo();
        Ejemplo ejemplo2 = new Ejemplo();
        Ejemplo ejemplo3 = new Ejemplo("Oliver","Bitica" , "X4475430");
        
        System.out.println(ejemplo1.equals(ejemplo2));
        System.out.println(ejemplo1.equals(ejemplo3));
        
        
    }
}
