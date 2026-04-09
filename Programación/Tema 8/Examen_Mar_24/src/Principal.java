import java.time.LocalDate;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import Clases.Carta;
import Clases.Noticia;
import DAO.ComunicacionesDAO;
import Interfaz.Formateable;
import MiExcepcion.CodigoCertificadoIncorrecto;
import MiExcepcion.FormatoFechaIncorrecto;

// instancia instanceof cartaCertificada cartaYaCertificada
public class Principal {
    private static Scanner sc = new Scanner(System.in);
    private static ComunicacionesDAO dao = new ComunicacionesDAO();

    public static void main(String[] args) throws Exception {
        boolean continuar = true;
        String respuestaMenu;
        do {
            menu();
            respuestaMenu = sc.nextLine();
            System.out.println();
            switch (respuestaMenu) {
                case "1" -> {
                    boolean continuarSubMenu = true;
                    String respuestaSubMenu;
                    do {
                        subMenuTipoComunicacion();
                        respuestaSubMenu = sc.nextLine();
                        System.out.println();
                        switch (respuestaSubMenu) {
                            case "1" -> {
                                agregarCartaCertificada();
                            }
                            case "2" -> {
                                agregarCartaPostalOrdinaria();
                            }
                            case "3" -> {
                                agregarBurofax();
                            }
                            case "4" -> {
                                agregarNoticia();
                            }
                            case "0" -> {
                                System.out.println("Saliendo del submenú...\n");
                                continuarSubMenu = false;
                            }

                            default -> {
                                System.out.println("Selecciona una opción válida del submenú\n");
                            }
                        }
                    } while (continuarSubMenu);
                }
                case "2" -> {
                    System.out.println("--- CARTAS ORDENADAS POR FECHA ---");
                    Set<Carta> temporalCartas = new TreeSet<>();
                    for (Formateable elemento : dao.getListaElementos()) {
                        if (elemento instanceof Carta carta) {
                            temporalCartas.add(carta);
                        }
                    }
                    for (Carta c : temporalCartas) {
                        System.out.println(c.formatear());
                        System.out.println("---------------");
                    }
                }
                case "3" -> {
                    System.out.println("--- NOTICIAS ORDENADAS POR AUTOR ---");
                    Set<Noticia> temporalNoticias = new TreeSet<>();
                    for (Formateable elemento : dao.getListaElementos()) {
                        if (elemento instanceof Noticia n) {
                            temporalNoticias.add(n);
                        }
                    }
                    for (Noticia noticia : temporalNoticias) {
                        System.out.println(noticia.formatear());
                        System.out.println("---------------");
                    }
                }
                case "0" -> {
                    System.out.println("Saliendo del programa...\n");
                    continuar = false;
                }

                default -> {
                    System.out.println("Selecciona una opción válida del menú\n");
                }
            }
        } while (continuar);
    }

    private static void menu() {
        System.out.print("""
                --- MENÚ ---
                1. Añadir comunicación
                2. Mostrar todas las cartas ordenadas por fecha
                3. Mostrar todas las noticias ordenadas por autor
                0. Salir
                Elige opción:\t""");
    }

    private static void subMenuTipoComunicacion() {
        System.out.print("""
                --- SUBMENÚ ---
                1. Carta Postal Certificada
                2. Carta Postal Ordinaria
                3. Burofax
                4. Noticia
                0. Salir del SUBMENÚ
                Elige opción:\t""");
    }

    private static String preguntaString(String pregunta) {
        System.out.print(pregunta);
        return sc.nextLine();
    }

    private static int preguntaInt(String pregunta) {
        boolean continuar = true;
        int respuestaInt = -1;
        do {
            System.out.print(pregunta);
            String respuesta = sc.nextLine();
            try {
                respuestaInt = Integer.parseInt(respuesta);
                continuar = false;
            } catch (Exception e) {
                System.out.println("Introduce un número entero");
            }
        } while (continuar);
        return respuestaInt;
    }

    private static int preguntaInt(String pregunta, int min, int max) throws FormatoFechaIncorrecto {
        boolean continuar = true;
        int respuestaInt = -1;
        do {
            System.out.print(pregunta);
            String respuesta = sc.nextLine();
            try {
                respuestaInt = Integer.parseInt(respuesta);
                continuar = false;
            } catch (Exception e) {
                System.out.println("Introduce un número entero");
            }
        } while (continuar);
        if (respuestaInt < min | respuestaInt > max) {
            throw new FormatoFechaIncorrecto("Error: Formato de fecha incorrecta");
        } else {
            return respuestaInt;
        }
    }

    private static boolean preguntaBool(String pregunta) {
        System.out.println(pregunta);
        boolean continuar = true;
        Boolean respuestaBool = null;
        do {
            System.out.print(pregunta);
            String respuesta = sc.nextLine().toUpperCase().trim();
            switch (respuesta) {
                case "SI" -> {
                    respuestaBool = true;
                    continuar = false;
                }
                case "NO" -> {
                    respuestaBool = false;
                    continuar = false;
                }

                default -> {
                    System.out.println("Introduce si o no para continuar");
                }
            }
        } while (continuar);
        return respuestaBool;
    }

    // String remitente, String destinatario, LocalDate fecha, String contenido,
    // String numeroCertificacion
    private static void agregarCartaCertificada() {
        System.out.println("--CARTA CERTIFICADA--");
        String remitente = preguntaString("Remitente: ");
        String destinatario = preguntaString("Destinatario: ");
        try {
            LocalDate fecha = LocalDate.of((preguntaInt("Año: ", 1980, 2050)), preguntaInt("Mes: ", 0, 12),
                    preguntaInt("Día del mes: ", 0, 28));
            String contenido = preguntaString("Contenido: ");
            String numeroCertificacion = preguntaString("Número de certificación: ");
            try {
                dao.crearCartaCertificada(remitente, destinatario, fecha, contenido, numeroCertificacion);
            } catch (CodigoCertificadoIncorrecto e) {
                e.printStackTrace();
            }
        } catch (FormatoFechaIncorrecto e) {
            e.printStackTrace();
        }
    }

    // String remitente, String destinatario, LocalDate fecha, String contenido, int
    // peso
    private static void agregarCartaPostalOrdinaria() {
        System.out.println("--CARTA POSTAL ORDINARIA--");
        String remitente = preguntaString("Remitente: ");
        String destinatario = preguntaString("Destinatario: ");
        try {
            LocalDate fecha = LocalDate.of((preguntaInt("Año: ", 1980, 2050)), preguntaInt("Mes: ", 0, 12),
                    preguntaInt("Día del mes: ", 0, 28));
            String contenido = preguntaString("Contenido: ");
            int peso = preguntaInt("Peso: ");
            dao.crearCartaPostalOrdinaria(remitente, destinatario, fecha, contenido, peso);
        } catch (FormatoFechaIncorrecto e) {
            e.printStackTrace();
        }
    }

    // String remitente, String destinatario, LocalDate fecha, String contenido,
    // boolean conAcuseRecibo
    private static void agregarBurofax() {
        System.out.println("--BUROFAX--");
        String remitente = preguntaString("Remitente: ");
        String destinatario = preguntaString("Destinatario: ");
        try {
            LocalDate fecha = LocalDate.of((preguntaInt("Año: ", 1980, 2050)), preguntaInt("Mes: ", 0, 12),
                    preguntaInt("Día del mes: ", 0, 28));
            String contenido = preguntaString("Contenido: ");
            boolean conAcuseRecibo = preguntaBool("Con acuse de recibo (si/no): ");
            dao.creaBurofax(remitente, destinatario, fecha, contenido, conAcuseRecibo);
        } catch (FormatoFechaIncorrecto e) {
            e.printStackTrace();
        }
    }

    private static void agregarNoticia() {
        System.out.println("--NOTICIA--");
        String titulo = preguntaString("Título: ");
        String contenido = preguntaString("Contenido: ");
        String autor = preguntaString("Autor: ");
        try {
            LocalDate fecha = LocalDate.of((preguntaInt("Año: ", 1980, 2050)), preguntaInt("Mes: ", 0, 12),
                    preguntaInt("Día del mes: ", 0, 28));
            dao.crearNoticia(titulo, contenido, autor, fecha);
        } catch (FormatoFechaIncorrecto e) {
            e.printStackTrace();
        }

    }
}
