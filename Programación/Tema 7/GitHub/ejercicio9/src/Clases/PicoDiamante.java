package Clases;

public class PicoDiamante extends Pico {

    
    
    public PicoDiamante(String nombre, int durabilidad, String lucidez) {
        super(nombre, durabilidad, lucidez);
    }

    @Override
    public StringBuilder minar() {
        super.minar().append("diamante y mino cualquier bloque");
        return super.minar();
    }

    

}
