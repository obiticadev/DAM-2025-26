package modulo4_patrones_creacionales;

/**
 * ============================================================================
 * EJERCICIO 58: Prototype — Shallow Copy Manual
 * ============================================================================
 * 📚 Teoría: teoria/04_Patrones_Creacionales.md - Sección 4.6
 *
 * CONTEXTO:
 * El patrón Prototype permite crear nuevos objetos clonando uno existente.
 * La copia "superficial" (shallow copy) copia los campos primitivos pero
 * COMPARTE las referencias a objetos internos. Esto significa que modificar
 * un objeto interno en la copia TAMBIÉN afecta al original.
 *
 * Escenario: Un sistema de documentos donde clonar un Documento crea una
 * copia con los mismos metadatos pero que comparte el array de párrafos.
 *
 * Implementa:
 * - Interfaz Prototipo: clonar() retorna una copia del objeto.
 * - Clase Documento: título, autor, párrafos[] (array de Strings).
 * - Demostrar que la shallow copy comparte los párrafos con el original.
 *
 * RESTRICCIONES:
 * - NO usar Object.clone() ni Cloneable.
 * - Implementar la copia manualmente campo por campo.
 * - Demostrar el PROBLEMA de la shallow copy (referencia compartida).
 * ============================================================================
 */
public class Ejercicio58_PrototypeShallowCopy {

    interface Prototipo {
        Prototipo clonar();
    }

    static class Documento implements Prototipo {
        String titulo;
        String autor;
        String[] parrafos;
        int numParrafos;

        public Documento(String titulo, String autor) {
            this.titulo = titulo;
            this.autor = autor;
            this.parrafos = new String[20];
            this.numParrafos = 0;
        }

        public void agregarParrafo(String parrafo) {
            if (numParrafos < parrafos.length) {
                parrafos[numParrafos++] = parrafo;
            }
        }

        @Override
        public Prototipo clonar() {
            // TODO 1: Crear un nuevo Documento con el mismo título y autor.
            //         SHALLOW COPY: asignar el MISMO array de párrafos (no copiar).
            //         copia.parrafos = this.parrafos;  (COMPARTE la referencia)
            //         copia.numParrafos = this.numParrafos;
            //         Retornar copia.
            return null;
        }

        @Override
        public String toString() {
            // TODO 2: Formatear como:
            //         "Documento: <titulo> por <autor>
            //          Párrafos (N):
            //            1. <parrafo1>
            //            2. <parrafo2>"
            return "Documento vacío";
        }
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 58: Prototype Shallow Copy ===\n");

        // TODO 3: Crear un documento original con 2 párrafos.
        //         Documento original = new Documento("Mi Tesis", "Alice");
        //         original.agregarParrafo("Capítulo 1: Introducción...");
        //         original.agregarParrafo("Capítulo 2: Metodología...");

        // TODO 4: Clonar el documento (shallow copy).
        //         Documento copia = (Documento) original.clonar();
        //         copia.titulo = "Copia de Mi Tesis";
        //         copia.autor = "Bob";

        // TODO 5: Demostrar el PROBLEMA:
        //         Modificar un párrafo en la copia:
        //         copia.parrafos[0] = "MODIFICADO: Capítulo 1 reescrito";
        //         Imprimir AMBOS documentos.
        //         RESULTADO: el original TAMBIÉN tiene el párrafo modificado
        //         porque ambos apuntan al MISMO array.
        //         
        //         System.out.println("⚠️  PROBLEMA SHALLOW COPY:");
        //         System.out.println("La copia modificó el párrafo original porque");
        //         System.out.println("ambos COMPARTEN el mismo array de párrafos.");

        System.out.println("(Implementa los TODOs para ver resultados)");

        System.out.println("\n=== FIN EJERCICIO 58 ===");
    }
}
