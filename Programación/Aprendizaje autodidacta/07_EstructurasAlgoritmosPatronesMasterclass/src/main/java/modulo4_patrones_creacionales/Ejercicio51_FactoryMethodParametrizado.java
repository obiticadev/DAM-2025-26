package modulo4_patrones_creacionales;

/**
 * ============================================================================
 * EJERCICIO 51: Factory Method — Fábrica Parametrizada con Enums
 * ============================================================================
 * 📚 Teoría: teoria/04_Patrones_Creacionales.md - Sección 4.3
 *
 * CONTEXTO:
 * Una variante elegante del Factory Method usa Enums para parametrizar
 * la creación. En lugar de if/else o switch con Strings, un Enum
 * encapsula el tipo y opcionalmente el comportamiento de creación.
 *
 * Escenario: Sistema de transporte con vehículos (Coche, Moto, Bicicleta,
 * Camion). Cada vehículo tiene velocidad máxima, consumo y capacidad.
 *
 * Arquitectura:
 * - Enum TipoVehiculo: COCHE, MOTO, BICICLETA, CAMION.
 * - Interfaz Vehiculo: acelerar(), frenar(), getInfo().
 * - VehiculoFactory: crear(TipoVehiculo tipo) usando switch sobre el enum.
 *
 * RESTRICCIONES:
 * - Usar un Enum para los tipos (no Strings).
 * - El Factory Method recibe el Enum como parámetro.
 * - Cada vehículo concreto tiene atributos propios (velocidad, consumo).
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio51_FactoryMethodParametrizado {

    // TODO 1: Crear el enum TipoVehiculo con valores:
    //         COCHE, MOTO, BICICLETA, CAMION
    //         Cada valor puede tener un campo descriptivo (opcional).

    // TODO 2: Definir la interfaz Vehiculo con:
    //         String acelerar()
    //         String frenar()
    //         String getInfo() — retorna tipo, velocidadMax, consumo, capacidad.
    //         int getVelocidadMax()
    //         double getConsumo()  (litros/100km, 0 para bicicleta)

    // TODO 3: Implementar cuatro clases concretas:
    //         Coche: velMax=180, consumo=7.5, capacidad=5
    //         Moto: velMax=200, consumo=4.0, capacidad=2
    //         Bicicleta: velMax=40, consumo=0, capacidad=1
    //         Camion: velMax=120, consumo=25.0, capacidad=3 (+ carga)
    //         Cada una implementa Vehiculo con mensajes específicos.

    // TODO 4: Crear VehiculoFactory con método estático:
    //         static Vehiculo crear(TipoVehiculo tipo)
    //         Usar switch sobre el enum para instanciar.
    //         Lanzar IllegalArgumentException para tipo desconocido.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 51: Factory Parametrizado ===\n");

        // TODO 5: Crear un array de TipoVehiculo con todos los valores.
        //         Para cada tipo:
        //           Vehiculo v = VehiculoFactory.crear(tipo);
        //           System.out.println(v.getInfo());
        //           System.out.println(v.acelerar());
        //           System.out.println(v.frenar());
        //           System.out.println("---");
        //
        //         Esto demuestra el POLIMORFISMO: el cliente trabaja con la
        //         interfaz Vehiculo sin conocer las clases concretas.

        System.out.println("(Implementa los TODOs para ver resultados)");

        System.out.println("\n=== FIN EJERCICIO 51 ===");
    }
}
