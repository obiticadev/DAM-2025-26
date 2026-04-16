package modulo4_patrones_creacionales;

/**
 * ============================================================================
 * EJERCICIO 59: Prototype — Deep Copy Manual
 * ============================================================================
 * 📚 Teoría: teoria/04_Patrones_Creacionales.md - Sección 4.6
 *
 * CONTEXTO:
 * La Deep Copy resuelve el problema de la Shallow Copy: crea copias
 * independientes de TODOS los objetos internos, incluyendo arrays y
 * objetos anidados. Modificar la copia NUNCA afecta al original.
 *
 * Escenario: Clonar una Ficha de Personaje de un juego de rol con
 * estadísticas, inventario y habilidades — todo copiado profundamente.
 *
 * Implementa:
 * - Clase Estadisticas: vida, ataque, defensa, velocidad.
 * - Clase Personaje: nombre, nivel, estadísticas, inventario[], habilidades[].
 * - El clonar() debe copiar TODOS los objetos internos recursivamente.
 *
 * RESTRICCIONES:
 * - NO usar Object.clone(), Cloneable ni serialización.
 * - Copia manual campo por campo, incluyendo crear NUEVOS arrays
 *   y NUEVOS objetos Estadisticas para la copia.
 * - Demostrar que original y copia son totalmente independientes.
 * ============================================================================
 */
public class Ejercicio59_PrototypeDeepCopy {

    interface Prototipo {
        Prototipo clonar();
    }

    static class Estadisticas implements Prototipo {
        int vida;
        int ataque;
        int defensa;
        int velocidad;

        public Estadisticas(int vida, int ataque, int defensa, int velocidad) {
            this.vida = vida;
            this.ataque = ataque;
            this.defensa = defensa;
            this.velocidad = velocidad;
        }

        @Override
        public Prototipo clonar() {
            // TODO 1: Crear una NUEVA instancia de Estadisticas con los mismos valores.
            //         return new Estadisticas(this.vida, this.ataque, this.defensa, this.velocidad);
            return null;
        }

        @Override
        public String toString() {
            return "HP:" + vida + " ATK:" + ataque + " DEF:" + defensa + " SPD:" + velocidad;
        }
    }

    static class Personaje implements Prototipo {
        String nombre;
        int nivel;
        Estadisticas stats;
        String[] inventario;
        int numItems;
        String[] habilidades;
        int numHabilidades;

        public Personaje(String nombre, int nivel, Estadisticas stats) {
            this.nombre = nombre;
            this.nivel = nivel;
            this.stats = stats;
            this.inventario = new String[20];
            this.numItems = 0;
            this.habilidades = new String[10];
            this.numHabilidades = 0;
        }

        public void agregarItem(String item) {
            if (numItems < inventario.length) inventario[numItems++] = item;
        }

        public void agregarHabilidad(String habilidad) {
            if (numHabilidades < habilidades.length) habilidades[numHabilidades++] = habilidad;
        }

        @Override
        public Prototipo clonar() {
            // TODO 2: DEEP COPY — crear copia totalmente independiente:
            //         1. Clonar Estadisticas: (Estadisticas) this.stats.clonar()
            //         2. Crear nuevo Personaje con el stats clonado.
            //         3. Crear NUEVO array de inventario y copiar elementos con bucle.
            //         4. Crear NUEVO array de habilidades y copiar elementos con bucle.
            //         5. Copiar numItems y numHabilidades.
            //         Retornar la copia.
            return null;
        }

        @Override
        public String toString() {
            // TODO 3: Formatear la ficha del personaje:
            //         "═══ PERSONAJE ═══
            //          Nombre: Guerrero | Nivel: 15
            //          Stats: HP:100 ATK:25 DEF:20 SPD:15
            //          Inventario: [Espada, Escudo, Poción]
            //          Habilidades: [Corte, Bloqueo]"
            return "Personaje vacío";
        }
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 59: Prototype Deep Copy ===\n");

        // TODO 4: Crear un personaje original completo:
        //         Estadisticas stats = new Estadisticas(100, 25, 20, 15);
        //         Personaje guerrero = new Personaje("Guerrero", 15, stats);
        //         guerrero.agregarItem("Espada de Fuego");
        //         guerrero.agregarItem("Escudo de Hierro");
        //         guerrero.agregarItem("Poción de Vida");
        //         guerrero.agregarHabilidad("Corte Cruzado");
        //         guerrero.agregarHabilidad("Defensa Total");

        // TODO 5: Clonar y modificar la copia:
        //         Personaje clon = (Personaje) guerrero.clonar();
        //         clon.nombre = "Guerrero Oscuro";
        //         clon.stats.ataque = 50;  // Modificar stats del clon
        //         clon.agregarItem("Daga Envenenada");
        //         clon.agregarHabilidad("Golpe Sombrío");

        // TODO 6: Imprimir ambos y verificar INDEPENDENCIA:
        //         System.out.println(guerrero);
        //         System.out.println(clon);
        //         System.out.println("✅ El original NO cambió → Deep Copy correcta");
        //         System.out.println("Stats originales ATK: " + guerrero.stats.ataque); // 25
        //         System.out.println("Stats copia ATK: " + clon.stats.ataque);          // 50

        System.out.println("(Implementa los TODOs para ver resultados)");

        System.out.println("\n=== FIN EJERCICIO 59 ===");
    }
}
