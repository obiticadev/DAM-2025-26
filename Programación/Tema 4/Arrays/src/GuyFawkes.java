import java.util.Scanner;

public class GuyFawkes {

    /*
     * ACTIVIDAD EN JAVA
     * 
     * Debes hacer el código para un juego que llamaremos Atrapa a Guy Fawkes Será
     * un minijuego que se ejecutará en la consola:
     * 
     * Hay varias habitaciones numeradas (este dato se preguntará antes de comenzar
     * el juego) debajo del Parlamento. Vamos a imaginar para esta primera versión,
     * que las habitaciones están en un pasillo largo, por lo que están ordenadas en
     * fila. El guardia tiene un número limitado de posibilidades de búsqueda antes
     * de que amanezca, que se calcularán como (int)númeroHabitaciones*0,55,
     * (ejemplo, si son 10 habitaciones serán 5 posibilidades)
     * 
     * En una está escondido Guy Fawkes con la pólvora.
     * 
     * El jugador es un guardia que tiene que inspeccionar las habitaciones,
     * buscando a Guy Fawkes.
     * 
     * Cada turno el jugador elige una habitación (si elige una habitación que no
     * existe el juego finaliza), según lo cerca o lejos que esté la habitación
     * elegida, el programa responde con pistas
     * ("oyes ruidos y hay un fuerte olor a póvora", “hueles pólvora cerca”,
     * “silencio total”...). Si la habitación siguiente (por la derecha o izquierda)
     * es en la que está escondido Guy, dará el primer mensaje
     * "oyes ruidos y hay un fuerte olor a póvora", si pasada una habitación (a la
     * derecha o izquierda) está escondido Guy, se mostrará el mensaje: “hueles
     * pólvora cerca”, si hay más de una habitación entre la que revisa el guardia y
     * en la que está escondido Guy mostrará: “silencio total”.
     * 
     * Si el guardia lo encuentra antes del amanecer gana, si no lo encuentra el
     * parlamento....
     */
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int numHabitaciones;
        int habitacionGanadora;
        int numIntentos;
        int selectHabitacion;
        int distanciaHabitacion;
        boolean gana = false;

        System.out.print("Dame el número de habitaciones: ");
        numHabitaciones = sc.nextInt();
        habitacionGanadora = (int) (Math.random() * numHabitaciones + 1);
        numIntentos = (int) (numHabitaciones * 0.55) + 1;

        for (int i = 0; i < numHabitaciones; i++) {
            System.out.print("----");
            System.out.print("| " + (i + 1) + " |");
            System.out.print("----");
        }
        System.out.println();

        for (int j = 0; j < numIntentos; j++) {
            try {
                System.out.print("Selecciona una habitación: ");
                selectHabitacion = sc.nextInt();
                distanciaHabitacion = habitacionGanadora - selectHabitacion;
                if (distanciaHabitacion == 0) {
                    System.out.println("Has ganado!");
                    gana = true;
                    break;
                }
                if (distanciaHabitacion == 1) {
                    System.out.println("Estas a un paso");
                } else {
                    System.out.println("Silencio absoluto");
                }

            } catch (Exception e) {
                System.out.println("Inserta una habitación válida");
                numIntentos--;
            }

        }
        

    }
}
