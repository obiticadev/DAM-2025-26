package bloque3;

/**
 * Excepcion personalizada para el Ejercicio 14.
 * Extiende IllegalArgumentException.
 */
// TODO 1: Haz que esta clase extienda IllegalArgumentException.
//         Implementa un constructor que reciba un String y lo pase a super(msg).
public class Ej14_DimensionException extends IllegalArgumentException {
    public Ej14_DimensionException(String msg) {
        super(msg);
    }
}
