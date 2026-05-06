package com.biblioteca.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.biblioteca.Enum.Aviso;

public class Logs {

    private static final String ARCHIVO = "logs.txt";
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private String mensaje;
    private Aviso aviso;

    public Logs(String mensaje, Aviso aviso) {
        this.mensaje = mensaje;
        this.aviso = aviso;
    }

    public void guardarLog() {
        try (FileWriter fw = new FileWriter(ARCHIVO, true);
                PrintWriter pw = new PrintWriter(fw)) {

            String timestamp = LocalDateTime.now().format(FORMATO);
            pw.printf("[%s] [%s] %s%n", timestamp, aviso.toString(), mensaje);

        } catch (IOException e) {
            // TODO [RECOMENDACIÓN] Si falla la escritura del log, imprimir por System.err
            // para no perder información de depuración:
            // System.err.println("[LOG FALLIDO] " + timestamp + " " + aviso + " " +
            // mensaje);
            System.out.println("[LOG FALLIDO] No se ha podido escribir en el archivo de logs");
        }
    }
}