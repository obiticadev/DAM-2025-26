package Principal;

import java.util.Scanner;
import java.time.LocalDate;

import Clases.DispositivoAlquilado;
import Clases.Ordenador;
import Clases.Proyector;
import DAO.DAOAlquileres;
import Excepciones.CodigoInvalidoException;
import Interfaz.Asegurable;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DAOAlquileres dao = new DAOAlquileres();
        boolean salir = false;

        System.out.println("=== SISTEMA DE COWORKING TECH ===");

        while (!salir) {
            System.out.println("\n1. Prestar Ordenador");
            System.out.println("2. Prestar Proyector");
            System.out.println("3. Devolver Dispositivo");
            System.out.println("4. Contratar Seguro (Solo compatibles)");
            System.out.println("5. Ver Ingresos Históricos");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");
            
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1" -> {
                    System.out.print("Código (DEV-XXX): ");
                    String cod = sc.nextLine();
                    System.out.print("Marca: ");
                    String marca = sc.nextLine();
                    System.out.print("Memoria RAM (GB): ");
                    int ram = Integer.parseInt(sc.nextLine());
                    
                    try {
                        Ordenador o = new Ordenador(cod, marca, ram);
                        dao.registrarDispositivo(o);
                        System.out.println("Ordenador registrado con éxito.");
                    } catch (CodigoInvalidoException e) {
                        System.out.println("Error: El código " + e.getCodigoErroneo() + " introducido el " + e.getFechaIntento() + " no es válido.");
                    }
                }
                case "2" -> {
                    System.out.print("Código (DEV-XXX): ");
                    String cod = sc.nextLine();
                    System.out.print("Marca: ");
                    String marca = sc.nextLine();
                    System.out.print("¿Es 4K? (true/false): ");
                    boolean es4k = Boolean.parseBoolean(sc.nextLine());
                    
                    try {
                        Proyector p = new Proyector(cod, marca, es4k);
                        dao.registrarDispositivo(p);
                        System.out.println("Proyector registrado con éxito.");
                    } catch (CodigoInvalidoException e) {
                        System.out.println("Error: El código " + e.getCodigoErroneo() + " introducido el " + e.getFechaIntento() + " no es válido.");
                    }
                }
                case "3" -> {
                    System.out.print("Introduce el código del dispositivo a devolver: ");
                    String cod = sc.nextLine();
                    DispositivoAlquilado d = dao.buscarPorCodigo(cod);
                    
                    if (d != null && d.calcularPrecioTotal() == 0) { // Si es 0 es que no se ha devuelto aún en esta simulación base, aunque dependa de tu lógica.
                        d.finalizarPrestamo();
                        System.out.println("Devolución registrada. Total a pagar: " + d.calcularPrecioTotal() + " €");
                    } else if (d == null) {
                        System.out.println("Dispositivo no encontrado.");
                    } else {
                        System.out.println("El dispositivo ya fue devuelto.");
                    }
                }
                case "4" -> {
                    System.out.print("Introduce el código del dispositivo para asegurar: ");
                    String cod = sc.nextLine();
                    DispositivoAlquilado d = dao.buscarPorCodigo(cod);
                    
                    if (d != null) {
                        if (d instanceof Asegurable dispositivoAsegurable) {
                            dispositivoAsegurable.activarSeguro();
                            System.out.println("Seguro activado correctamente para el dispositivo: " + cod);
                        } else {
                            System.out.println("Este dispositivo no admite la contratación de un seguro.");
                        }
                    } else {
                        System.out.println("Dispositivo no encontrado.");
                    }
                }
                case "5" -> {
                    System.out.println("Ingresos totales acumulados: " + dao.calcularIngresosHistoricos() + " €");
                }
                case "6" -> salir = true;
                default -> System.out.println("Opción no válida.");
            }
        }
        sc.close();
    }
}