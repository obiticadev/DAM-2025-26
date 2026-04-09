package modulo5_patrones_estructurales;

/**
 * ============================================================================
 * EJERCICIO 65: Facade — Home Theater
 * ============================================================================
 * 📚 Teoría: teoria/05_Patrones_Estructurales.md - Sección 5.4
 *
 * CONTEXTO:
 * Un Home Theater tiene múltiples subsistemas: TV, reproductor Blu-ray,
 * sistema de sonido, luces, proyector, máquina de palomitas. Cada uno
 * tiene su propia interfaz. Sin Facade, ver una película requiere
 * 15+ llamadas a diferentes objetos.
 *
 * Facade proporciona un método simple: verPelicula(String titulo) que
 * coordina TODOS los subsistemas automáticamente.
 *
 * Implementa:
 * - Subsistemas: TV, ReproductorBluray, SistemaSonido, Luces, Palomitas.
 * - HomeTheaterFacade: verPelicula(), pausar(), detener(), modoMusica().
 * - Cada subsistema tiene 2-3 métodos propios.
 *
 * RESTRICCIONES:
 * - Cada subsistema imprime mensajes descriptivos de su acción.
 * - El Facade coordina el ORDEN correcto de operaciones.
 * - El cliente SOLO interactúa con el Facade.
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio65_FacadeHomeTheater {

    // ==== SUBSISTEMAS ====

    static class TV {
        // TODO 1: Implementar: encender(), apagar(), setCanal(int canal)
        //         Cada método imprime: "[TV] Encendiendo pantalla 55 pulgadas..."
    }

    static class ReproductorBluray {
        // TODO 2: Implementar: encender(), apagar(), reproducir(String titulo), pausar(), detener()
    }

    static class SistemaSonido {
        // TODO 3: Implementar: encender(), apagar(), setVolumen(int vol), modoSurround(), modoEstereo()
    }

    static class Luces {
        // TODO 4: Implementar: atenuar(int porcentaje), encender(), apagar()
    }

    static class MaquinaPalomitas {
        // TODO 5: Implementar: encender(), apagar(), preparar()
        //         preparar() → "[Palomitas] Preparando palomitas... ¡Listas! 🍿"
    }

    // ==== FACADE ====

    static class HomeTheaterFacade {
        private TV tv;
        private ReproductorBluray bluray;
        private SistemaSonido sonido;
        private Luces luces;
        private MaquinaPalomitas palomitas;

        public HomeTheaterFacade(TV tv, ReproductorBluray bluray,
                                 SistemaSonido sonido, Luces luces,
                                 MaquinaPalomitas palomitas) {
            this.tv = tv;
            this.bluray = bluray;
            this.sonido = sonido;
            this.luces = luces;
            this.palomitas = palomitas;
        }

        public void verPelicula(String titulo) {
            // TODO 6: Coordinar encendido en orden:
            //         System.out.println("🎬 Preparando para ver: " + titulo);
            //         palomitas.encender() → palomitas.preparar()
            //         luces.atenuar(10)
            //         tv.encender()
            //         sonido.encender() → sonido.modoSurround() → sonido.setVolumen(50)
            //         bluray.encender() → bluray.reproducir(titulo)
            //         System.out.println("🎬 ¡Disfruta la película!");
        }

        public void detener() {
            // TODO 7: Coordinar apagado en orden inverso:
            //         bluray.detener() → bluray.apagar()
            //         sonido.apagar()
            //         tv.apagar()
            //         luces.encender()
            //         palomitas.apagar()
            //         System.out.println("🎬 Home Theater apagado.");
        }
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 65: Facade Home Theater ===\n");

        // TODO 8: Crear los subsistemas y el facade:
        //         HomeTheaterFacade ht = new HomeTheaterFacade(
        //             new TV(), new ReproductorBluray(),
        //             new SistemaSonido(), new Luces(), new MaquinaPalomitas());
        //
        //         ht.verPelicula("El Señor de los Anillos");
        //         System.out.println("\n... 3 horas después ...\n");
        //         ht.detener();

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 65 ===");
    }
}
