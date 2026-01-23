package Clases;

import java.time.LocalDateTime;

public class TodoList {

    protected final int LONGITUD_MAX = 10;
    protected Tarea[] tarea;
    protected int contador;

    public TodoList() {
        this.tarea = new Tarea[LONGITUD_MAX];
        this.contador = 0;
    }

    public Tarea[] getTarea() {
        return tarea;
    }

    public int getContador() {
        return contador;
    }

    public boolean añadirTarea(Tarea tarea) {
        if (this.contador != 0) {
            for (int i = 0; i < this.tarea.length; i++) {
                if (this.tarea[i] == null) {
                    this.tarea[i] = tarea;
                    this.contador++;
                    return true;
                }
            }
        } else {
            this.tarea[0] = tarea;
            this.contador++;
            return true;
        }
        return false;
    }

    public void compactarLista() {
        int destino = 0;
        for (int i = 0; i < tarea.length; i++) {
            if (tarea[i] != null) {
                tarea[destino] = tarea[i];
                if (destino != i) {
                    tarea[i] = null;
                }
                destino++;
            }

        }
        this.contador = destino;
    }

    public void ordenarLista() {
        for (int i = 0; i < tarea.length - 1; i++) {
            for (int j = 0; j < tarea.length - 1 - i; j++) {
                if (tarea[j] != null && tarea[j + 1] != null) {
                    if (tarea[j].fechaCompleta.compareTo(tarea[j + 1].fechaCompleta) > 0) {
                        Tarea aux = tarea[j];
                        tarea[j] = tarea[j + 1];
                        tarea[j + 1] = aux;
                    }
                }
            }
        }
    }

    public boolean validarFechaCompleta(LocalDateTime fechaCompletaEntrada) {
        for (Tarea t : tarea) {
            if (t != null) {
                if (t.getFechaCompleta().equals(fechaCompletaEntrada)) {
                    return false;
                }

            }
        }
        return true;
    }

    public int getLONGITUD_MAX() {
        return LONGITUD_MAX;
    }

    public StringBuilder listarTareas() {
        StringBuilder respuestaLista = new StringBuilder();
        int contadorInterno = 1;
        for (int i = 0; i < tarea.length; i++) {

            if (tarea[i] != null) {
                respuestaLista.append("Tarea ").append(contadorInterno).append(" -> {\n").append(tarea[i].toString())
                        .append("\n}\n");
                contadorInterno++;
            }

        }
        return respuestaLista;
    }

}
