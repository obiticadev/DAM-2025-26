package modulo4_patrones_creacionales;

/**
 * ============================================================================
 * EJERCICIO 56: Builder con Director — Construcción de Informes
 * ============================================================================
 * 📚 Teoría: teoria/04_Patrones_Creacionales.md - Sección 4.5
 *
 * CONTEXTO:
 * El Director encapsula RECETAS de construcción predefinidas. El Builder
 * sabe CÓMO construir cada paso, el Director sabe QUÉ pasos ejecutar
 * y en qué orden para obtener un producto concreto.
 *
 * Escenario: Generador de informes que soporta distintos formatos:
 * - Informe Ejecutivo: título, resumen, gráfico, conclusión.
 * - Informe Técnico: título, resumen, datos, código, métricas, conclusión.
 * - Informe Rápido: solo título y resumen.
 *
 * Arquitectura:
 * - Clase Informe: objeto final con secciones.
 * - Interfaz InformeBuilder: métodos para cada sección.
 * - InformeTextoBuilder: genera informe en texto plano.
 * - InformeDirector: tiene métodos que invocan al builder en orden.
 *
 * RESTRICCIONES:
 * - El Director NO sabe qué tipo concreto de Builder usa.
 * - Las secciones del informe se almacenan en un array de Strings.
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio56_BuilderDirector {

    // TODO 1: Crear la clase Informe con:
    //         String titulo
    //         String[] secciones (max 20)
    //         int numSecciones
    //         void agregarSeccion(String seccion)
    //         String toString() — formatea todo el informe con líneas separadoras.

    // TODO 2: Definir la interfaz InformeBuilder:
    //         void reset()
    //         void setTitulo(String titulo)
    //         void agregarResumen(String resumen)
    //         void agregarDatos(String datos)
    //         void agregarGrafico(String grafico) — simular con arte ASCII
    //         void agregarCodigo(String codigo)
    //         void agregarMetricas(String metricas)
    //         void agregarConclusion(String conclusion)
    //         Informe getResultado()

    // TODO 3: Implementar InformeTextoBuilder:
    //         Mantiene una instancia de Informe.
    //         reset() crea un nuevo Informe vacío.
    //         Cada método "agregar..." formatea la sección como texto plano
    //         y llama a informe.agregarSeccion().

    // TODO 4: Implementar InformeDirector:
    //         Tiene un campo InformeBuilder.
    //         void setBuilder(InformeBuilder builder)
    //         void construirInformeEjecutivo() — llama: titulo, resumen, grafico, conclusion.
    //         void construirInformeTecnico() — llama: titulo, resumen, datos, codigo, metricas, conclusion.
    //         void construirInformeRapido() — llama: titulo, resumen.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 56: Builder con Director ===\n");

        // TODO 5: Crear el builder y el director.
        //         Usar el director para construir los tres tipos de informe:
        //
        //         InformeTextoBuilder builder = new InformeTextoBuilder();
        //         InformeDirector director = new InformeDirector();
        //         director.setBuilder(builder);
        //
        //         director.construirInformeEjecutivo();
        //         Informe ejecutivo = builder.getResultado();
        //         System.out.println(ejecutivo);
        //
        //         director.construirInformeTecnico();
        //         Informe tecnico = builder.getResultado();
        //         System.out.println(tecnico);
        //
        //         director.construirInformeRapido();
        //         Informe rapido = builder.getResultado();
        //         System.out.println(rapido);

        System.out.println("(Implementa los TODOs para ver resultados)");

        System.out.println("\n=== FIN EJERCICIO 56 ===");
    }
}
