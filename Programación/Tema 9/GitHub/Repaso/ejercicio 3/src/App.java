import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Introduce una frase a analizar: ");
        String respuesta = sc.nextLine();
        Map<String, Integer> listMap = new HashMap<>();

        String[] palabras = respuesta.trim().split("\\s+");
        Arrays.stream(palabras)
                // 1. Agrupamos por cada palabra y contamos cuántas veces sale
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                // 2. Convertimos el resultado (que es un Map) de nuevo a un Stream de entradas
                .entrySet().stream()
                // 3. Ordenamos por el conteo (valor) de mayor a menor
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                // 4. Imprimimos cada resultado
                .forEach(entry -> {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                });
    }
}
