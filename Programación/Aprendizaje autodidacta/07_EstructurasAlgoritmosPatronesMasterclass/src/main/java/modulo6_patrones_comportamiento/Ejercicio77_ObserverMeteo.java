package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 77: Observer — Estación Meteorológica con Displays
 * ============================================================================
 * 📚 Teoría: teoria/06_Patrones_Comportamiento.md - Sección 6.2
 *
 * CONTEXTO:
 * Una estación meteorológica mide temperatura, humedad y presión.
 * Cuando los datos cambian, múltiples displays se actualizan:
 * - DisplayActual: muestra los valores actuales.
 * - DisplayEstadisticas: muestra min/max/promedio de temperatura.
 * - DisplayPronostico: compara presión actual con anterior y predice.
 *
 * Implementa el clásico ejemplo de Head First Design Patterns.
 *
 * RESTRICCIONES:
 * - La estación notifica AUTOMÁTICAMENTE al cambiar mediciones.
 * - Cada display muestra datos en formato diferente.
 * - DisplayEstadisticas acumula historial (array max 100 lecturas).
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio77_ObserverMeteo {

    interface ObservadorMeteo {
        void actualizar(double temperatura, double humedad, double presion);
    }

    // TODO 1: Implementar EstacionMeteorologica (Subject):
    //         - Campos: temperatura, humedad, presion.
    //         - ObservadorMeteo[] observadores (max 10).
    //         - registrar(), eliminar(), notificar().
    //         - setMediciones(double temp, double hum, double pres):
    //           actualiza campos y llama notificar().

    // TODO 2: Implementar DisplayActual:
    //         actualizar() → imprime "🌡️ Actual: 25.3°C | 💧 65% | 🔵 1013.2 hPa"

    // TODO 3: Implementar DisplayEstadisticas:
    //         Acumula temperaturas en un array.
    //         actualizar() → calcula y muestra min, max, promedio.
    //         "📊 Stats: Min=18.5°C | Max=32.1°C | Avg=24.7°C (de 15 lecturas)"

    // TODO 4: Implementar DisplayPronostico:
    //         Compara presión actual con anterior.
    //         Si sube → "☀️ Pronostico: Mejorando!"
    //         Si baja → "🌧️ Pronostico: Empeorando..."
    //         Si igual → "🌤️ Pronostico: Estable"

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 77: Observer Meteo ===\n");

        // TODO 5:
        //         EstacionMeteorologica estacion = new EstacionMeteorologica();
        //         estacion.registrar(new DisplayActual());
        //         estacion.registrar(new DisplayEstadisticas());
        //         estacion.registrar(new DisplayPronostico());
        //
        //         estacion.setMediciones(25.3, 65, 1013.2);
        //         estacion.setMediciones(28.1, 70, 1012.8);
        //         estacion.setMediciones(22.5, 90, 1010.1);

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 77 ===");
    }
}
