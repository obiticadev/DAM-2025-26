package modulo4_patrones_creacionales;

/**
 * ============================================================================
 * EJERCICIO 60: Prototype — Registry (Catálogo de Prototipos)
 * ============================================================================
 * 📚 Teoría: teoria/04_Patrones_Creacionales.md - Sección 4.6
 *
 * CONTEXTO:
 * Un Prototype Registry almacena prototipos pre-configurados y permite
 * clonarlos por nombre. Es como un catálogo de "plantillas" que se pueden
 * copiar y personalizar.
 *
 * Escenario: Editor gráfico con formas predefinidas (Círculo, Rectángulo,
 * Triángulo) almacenadas en un registro. El usuario selecciona una forma
 * del catálogo, se clona, y luego la personaliza (color, posición, tamaño).
 *
 * Implementa:
 * - Interfaz Forma: clonar(), dibujar(), getInfo().
 * - Clases concretas: Circulo, Rectangulo, Triangulo.
 * - FormaRegistry: almacena prototipos por nombre y los clona bajo demanda.
 *
 * RESTRICCIONES:
 * - Registry usa arrays manuales (String[] nombres, Forma[] prototipos).
 * - MAX 20 prototipos.
 * - Deep copy en cada clonación.
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio60_PrototypeRegistry {

    // ==== INTERFAZ FORMA ====

    interface Forma {
        Forma clonar();
        void dibujar();
        String getInfo();
        void setColor(String color);
        void setPosicion(int x, int y);
    }

    // TODO 1: Implementar Circulo:
    //         Campos: radio, color, x, y.
    //         clonar(): new Circulo con mismos valores.
    //         dibujar(): arte ASCII de un círculo (o descripción textual).
    //         getInfo(): "Círculo(r=5, color=rojo, pos=[10,20])"

    // TODO 2: Implementar Rectangulo:
    //         Campos: ancho, alto, color, x, y.
    //         clonar(): new Rectangulo con mismos valores.
    //         dibujar(): arte ASCII de un rectángulo.

    // TODO 3: Implementar Triangulo:
    //         Campos: base, altura, color, x, y.
    //         clonar(): new Triangulo con mismos valores.
    //         dibujar(): arte ASCII de un triángulo.

    // ==== REGISTRY ====

    static class FormaRegistry {
        private String[] nombres;
        private Forma[] prototipos;
        private int count;

        public FormaRegistry() {
            this.nombres = new String[20];
            this.prototipos = new Forma[20];
            this.count = 0;
        }

        public void registrar(String nombre, Forma prototipo) {
            // TODO 4: Almacenar el nombre y el prototipo en los arrays.
            //         Verificar duplicados y capacidad.
        }

        public Forma crear(String nombre) {
            // TODO 5: Buscar el prototipo por nombre.
            //         Si se encuentra, retornar prototipo.clonar() (una COPIA).
            //         Si no, lanzar IllegalArgumentException.
            //         NUNCA retornar el prototipo original, siempre un clon.
            return null;
        }

        public void listar() {
            System.out.println("Prototipos registrados (" + count + "):");
            for (int i = 0; i < count; i++) {
                System.out.println("  " + nombres[i] + " → " + prototipos[i].getInfo());
            }
        }
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 60: Prototype Registry ===\n");

        // TODO 6: Crear el registry y registrar prototipos predefinidos:
        //         FormaRegistry registry = new FormaRegistry();
        //         registry.registrar("circulo-default", new Circulo(5, "rojo"));
        //         registry.registrar("rectangulo-default", new Rectangulo(10, 5, "azul"));
        //         registry.registrar("triangulo-default", new Triangulo(8, 6, "verde"));
        //         registry.registrar("circulo-grande", new Circulo(20, "negro"));
        //
        //         registry.listar();
        //
        //         // Clonar y personalizar
        //         Forma f1 = registry.crear("circulo-default");
        //         f1.setColor("amarillo");
        //         f1.setPosicion(100, 200);
        //         f1.dibujar();
        //         System.out.println(f1.getInfo());
        //
        //         Forma f2 = registry.crear("rectangulo-default");
        //         f2.setColor("morado");
        //         f2.setPosicion(50, 50);
        //         f2.dibujar();
        //
        //         // Verificar que el prototipo original NO cambió
        //         System.out.println("\nPrototipo original sin modificar:");
        //         registry.listar();

        System.out.println("(Implementa los TODOs para ver resultados)");

        System.out.println("\n=== FIN EJERCICIO 60 ===");
    }
}
