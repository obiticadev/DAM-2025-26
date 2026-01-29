package Clases;

public class PicoHierro extends Pico {

    public PicoHierro(String nombre, int durabilidad, String lucidez) {
        super(nombre, durabilidad, lucidez);
        
    }

    @Override
    public StringBuilder minar() {
        super.minar().append("hierro y no puedo minar obsidiana");
        return super.minar();
    }

    

}
