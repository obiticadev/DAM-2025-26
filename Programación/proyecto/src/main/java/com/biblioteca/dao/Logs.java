package com.biblioteca.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.biblioteca.Enum.Aviso;

public class Logs {

    private static final String ARCHIVO = "logs.txt";
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private String mensaje;
    private Aviso aviso;

    // CONSTRUCTOR ----------------------------

    public Logs(String mensaje, Aviso aviso) {
        this.mensaje = mensaje;
        this.aviso = aviso;
    }

    // LECTURA ----------------------------

    public static List<String> leerUltimosLogs(int cantidad) {
        List<String> todasLasLineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea = br.readLine();
            while (linea != null) {
                todasLasLineas.add(linea);
                linea = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo de logs.");
        }

        int inicio;
        if (todasLasLineas.size() <= cantidad) {
            inicio = 0;
        } else {
            inicio = todasLasLineas.size() - cantidad;
        }
        return todasLasLineas.subList(inicio, todasLasLineas.size());
    }

    // ESCRITURA ----------------------------

    public void guardarLog() {
        try (FileWriter fw = new FileWriter(ARCHIVO, true);
                PrintWriter pw = new PrintWriter(fw)) {

            String timestamp = LocalDateTime.now().format(FORMATO);
            pw.printf("[%s] [%s] %s%n", timestamp, aviso.toString(), mensaje);

        } catch (IOException e) {
            System.out.println("[LOG FALLIDO] No se ha podido escribir en el archivo de logs");
        }
    }
}