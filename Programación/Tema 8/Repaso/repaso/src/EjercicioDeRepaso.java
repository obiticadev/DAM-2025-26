import Clases.ClaseLanzaExcepciones;
import Clases.PasswordInvalidException;

public class EjercicioDeRepaso {
    public static void main(String[] args) {

        ClaseLanzaExcepciones service = new ClaseLanzaExcepciones();
        try {
            service.validarEdad(20);
            service.dividir(10, 1);
            service.buscarUsuario("Mario");
            service.validarPassword("12345648");

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException n) {
            System.out.println(n.getMessage());
        } catch (RuntimeException r) {
            System.out.println(r.getMessage());
        } catch (PasswordInvalidException p) {
            System.out.println(p.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
