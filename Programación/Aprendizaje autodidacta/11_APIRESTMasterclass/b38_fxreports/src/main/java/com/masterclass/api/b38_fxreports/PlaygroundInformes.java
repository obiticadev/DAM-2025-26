package com.masterclass.api.b38_fxreports;

import java.util.List;

import com.masterclass.api.b38_fxreports.Ej299ReportDataModel.FacturaDto;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Playground visual del bloque 38 (NO es un ejercicio: es el escaparate que ejecutas con
 * {@code mvn -pl b38_fxreports javafx:run}). Monta lo que el bloque construye en datos:
 *
 * <ul>
 *   <li>una <b>TableView</b> con las facturas del informe (Ej299), con su columna "Total"
 *       calculada por {@code totalConIva};</li>
 *   <li>una etiqueta de <b>resumen</b> (gran total y factura mayor) usando los retos de Ej299;</li>
 *   <li>un botón <b>Imprimir</b> que usa {@code PrinterJob} de JavaFX (Ej303) para mandar la tabla
 *       a la impresora, escalándola para que quepa en la hoja con la geometría de {@code escalaParaCaber}.</li>
 * </ul>
 *
 * <p>Usa los métodos {@code static} de los ejercicios. Hasta que los implementes devuelven su
 * centinela, así que la tabla aparece pero la columna Total y el resumen salen "vacíos"; en cuanto
 * implementes los core, cobra vida. La generación de PDF con JasperReports vive en la teoría (38.2):
 * requiere activar la dependencia del motor en el {@code pom.xml}.
 *
 * <p>Teoría: {@code teoria/38_Informes_PDF.md}.
 */
public final class PlaygroundInformes extends Application {

    private static final List<FacturaDto> FACTURAS = List.of(
            new FacturaDto("F-001", "Ana López", 1000, 210),
            new FacturaDto("F-002", "Luis Gil", 500, 105),
            new FacturaDto("F-003", "Ana López", 250, 52.5),
            new FacturaDto("F-004", "Marta Ruiz", 1800, 378));

    @Override
    public void start(Stage stage) {
        VBox raiz = new VBox(12);
        raiz.setPadding(new Insets(16));

        TableView<FacturaDto> tabla = tablaFacturas();
        Label resumen = new Label(textoResumen());
        Button imprimir = new Button("Imprimir tabla (PrinterJob)");
        imprimir.setOnAction(e -> imprimirNodo(tabla, stage));

        raiz.getChildren().addAll(new Label("Informe de facturas (Ej299)"), tabla, resumen, imprimir);

        stage.setScene(new Scene(raiz, 560, 420));
        stage.setTitle("Bloque 38 · Informes, PDF e impresión");
        stage.show();
    }

    /** TableView del Ej299: una fila por factura, con la columna Total calculada por el core. */
    private TableView<FacturaDto> tablaFacturas() {
        TableView<FacturaDto> tabla = new TableView<>(FXCollections.observableArrayList(FACTURAS));

        TableColumn<FacturaDto, String> cNum = new TableColumn<>("Nº");
        cNum.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().numero()));
        TableColumn<FacturaDto, String> cCli = new TableColumn<>("Cliente");
        cCli.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().cliente()));
        TableColumn<FacturaDto, String> cTot = new TableColumn<>("Total (€)");
        cTot.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(
                String.format(java.util.Locale.US, "%.2f", Ej299ReportDataModel.totalConIva(c.getValue()))));

        tabla.getColumns().add(cNum);
        tabla.getColumns().add(cCli);
        tabla.getColumns().add(cTot);
        tabla.setPrefHeight(180);
        return tabla;
    }

    /** Resumen con los retos de Ej299 (gran total + factura mayor). */
    private String textoResumen() {
        double total = Ej299ReportDataModel.totalDeLista(FACTURAS);
        String mayor = Ej299ReportDataModel.facturaMayor(FACTURAS)
                .map(FacturaDto::numero).orElse("(implementa facturaMayor)");
        return String.format(java.util.Locale.US,
                "Gran total: %.2f €   ·   Factura mayor: %s", total, mayor);
    }

    /** Impresión real con PrinterJob (Ej303): escala el nodo para que quepa y lo manda a imprimir. */
    private void imprimirNodo(Node nodo, Stage owner) {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job == null) {
            new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING,
                    "No hay ninguna impresora disponible.").showAndWait();
            return;
        }
        if (job.showPrintDialog(owner)) {
            // En un caso real, aquí se aplicaría un Scale calculado con Ej303.escalaParaCaber(...)
            // sobre el nodo antes de printPage, para que la tabla quepa en el área imprimible.
            boolean ok = job.printPage(nodo);
            if (ok) {
                job.endJob();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
