package com.masterclass.nivel7_sincronizacion;

/**
 * EJERCICIO 26: El Escudo de Monitores (Método Sincronizado)
 * 
 * OBJETIVO:
 * Subsanar la destrucción masiva de datos (Data Race) vista en el ejercicio anterior, 
 * valiéndose de la obtención forzosa de la "Llave Intrínseca" del objeto.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '07_Sincronizacion_Monitores.md' (Sección: "Monitor y synchronized").
 */
// TODO 1: Garantiza la integración asíncrona de la clase.
public class Ejercicio26_SynchronizedMethod {

    private int cuentaBancaria = 0;

    // TODO 2: Refina la firma de este método inyectando el modificador de acceso restrictivo 
    // de la JVM, el cual obligará a encolar las peticiones del procesador en Base de Datos.
    public void ingresarDinero() {
        cuentaBancaria++;
    }

    // TODO 3: Implementa la lógica asíncrona de la interfaz requerida en el TODO 1.
    // HINT: En su interior, abre un for gigante (15.000 iters) pero no modifiques la variable 
    // directamente, llama al método escudado 'ingresarDinero()'.

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- RESOLUCIÓN ATÓMICA DE HILOS ---");
        
        // TODO 4: Utiliza el mismo patrón del ejercicio anterior. Una instancia, 2 asíncronos. 
        // Intercepción con wait asíncrono (join).

        /*
        Ejercicio26_SynchronizedMethod cuenta = new Ejercicio26_SynchronizedMethod();
        Thread t1 = new Thread(cuenta);
        Thread t2 = new Thread(cuenta);
        
        // Manda iniciar
        // Fuerza el aguarte de hilo
        
        // TODO 5 (PRUEBA): Ejecuta el sistema decenas de veces en VSC. Verifica que ahora,
        // sin importar las condiciones de estrés, la cifra se clava SIEMPRE inmutable en 30000.
        System.out.println("Fondos Seguros Asegurados en BD: " + cuenta.cuentaBancaria);
        */
    }
}
