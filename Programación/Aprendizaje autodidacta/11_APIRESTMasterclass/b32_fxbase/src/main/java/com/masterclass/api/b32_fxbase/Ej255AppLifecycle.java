package com.masterclass.api.b32_fxbase;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Ejercicio 255 · Ciclo de vida de una app JavaFX y construcción del scene graph.
 *
 * <p>Teoría: {@code teoria/32_JavaFX_Base.md} (sección 1.1).
 *
 * <p>Excepción al molde del proyecto (addendum §1.6 del roadmap): esta clase <b>extiende
 * {@code Application}</b> para ser el Playground visual, por eso NO tiene constructor privado
 * (JavaFX necesita el constructor público sin argumentos para {@code launch}). La lógica
 * testeable sigue viviendo en métodos {@code static} puros, sin abrir ventana.
 */
public class Ej255AppLifecycle extends Application {

    /**
     * Construye la raíz del scene graph: un {@link VBox} con tres hijos identificados.
     *
     * @return la raíz (un {@code Parent}) con id "raiz" y 3 hijos; {@code null} sin implementar
     */
    public static Parent construirRaiz() {
        // TODO 1: crea un VBox vacío que será la raíz del árbol.
        // TODO 2: asígnale el id "raiz" con setId(...).
        // TODO 3: crea los tres hijos: Label("Título"), Button("Aceptar") y un TextField.
        // TODO 4: dale a cada hijo su id: "lblTitulo", "btnOk" y "txtNombre".
        // TODO 5: añádelos a la raíz (getChildren().addAll(...)) y devuelve la raíz.
        return null;
    }

    /**
     * Cuenta TODOS los nodos del árbol (este nodo + descendientes), de forma recursiva.
     *
     * @param nodo raíz del subárbol a contar
     * @return número total de nodos; 0 si {@code nodo} es null; -1 sin implementar
     */
    public static int contarNodos(Node nodo) {
        // TODO 6: caso base — si 'nodo' es null, devuelve 0.
        // TODO 7: arranca un contador en 1 (este nodo cuenta).
        // TODO 8: comprueba si 'nodo' es un Parent (instanceof) para poder tener hijos.
        // TODO 9: si lo es, recorre getChildrenUnmodifiable() y suma contarNodos(hijo) a cada uno.
        // TODO 10: devuelve el total acumulado.
        return -1;
    }

    /** Playground visual: monta la UI real y la muestra. El alumno pulsa Run. */
    @Override
    public void start(Stage stage) {
        Parent raiz = construirRaiz();
        if (raiz == null) {
            raiz = new VBox(new Label("Implementa construirRaiz() para ver la escena."));
        }
        stage.setTitle("Ej255 · Ciclo de vida JavaFX");
        stage.setScene(new Scene(raiz, 320, 200));
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println("Nodos en la raíz de ejemplo: " + contarNodos(construirRaiz()));
        launch(args); // arranca init -> start -> (cerrar ventana) -> stop
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Crear una etiqueta.
     * Devuelve un {@link Label} con el texto indicado.
     */
    public static Label crearEtiqueta(String texto) {
        // GUÍA: teoría 1.1 (el nodo más simple del scene graph).
        // 1. Construye y devuelve new Label(texto).
        // PISTA: el constructor de Label acepta el texto directamente.
        // OJO: el test comprueba getText(); si texto es null, Label lo admite (queda null/"").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearEtiqueta");
    }

    /**
     * Reto Extra 2: Contenedor vacío.
     * Devuelve un {@link VBox} recién creado, sin hijos.
     */
    public static Parent crearContenedorVacio() {
        // GUÍA: teoría 1.1 (un Parent puede no tener hijos todavía).
        // 1. Devuelve new VBox().
        // OJO: el test comprueba getChildrenUnmodifiable().isEmpty() == true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearContenedorVacio");
    }

    /**
     * Reto Extra 3: Apilar etiquetas.
     * Construye un {@link VBox} con una {@link Label} por cada texto, en orden.
     */
    public static Parent apilarEtiquetas(List<String> textos) {
        // GUÍA: teoría 1.1. Construye, añade hijos, devuelve.
        // 1. Crea un VBox. 2. Por cada texto, añade new Label(texto) a getChildren().
        // PISTA: textos.forEach(t -> caja.getChildren().add(new Label(t)));
        // OJO: el orden de inserción se conserva (el primer texto es el hijo 0).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para apilarEtiquetas");
    }

    /**
     * Reto Extra 4: Orden de las fases del ciclo de vida.
     * Devuelve los nombres de las fases de una app JavaFX, en el orden en que se ejecutan.
     */
    public static List<String> ordenDeFases() {
        // GUÍA: teoría 1.1 (diagrama de estados init -> start -> running -> stop).
        // 1. Devuelve la lista exacta: "init", "start", "stop".
        // OJO: init() corre ANTES de start() y en OTRO hilo (no el FX App Thread);
        //   stop() se llama al cerrar. El test compara la lista completa y en orden.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ordenDeFases");
    }

    /**
     * Reto Extra 5: Parámetros nombrados (estilo {@code getParameters().getNamed()}).
     * De argumentos tipo "--clave=valor", construye un mapa clave→valor.
     */
    public static Map<String, String> parametrosNombrados(List<String> args) {
        // GUÍA: teoría 1.1 (getParameters() expone named/unnamed/raw).
        // 1. Recorre args; quédate con los que empiezan por "--" y contienen "=".
        // 2. Parte por el PRIMER "=" en clave (sin "--") y valor.
        // PISTA: String sin = a.substring(2); int i = sin.indexOf('='); clave = sin.substring(0,i)...
        // OJO: el test mete también un posicional ("entrada.txt") que NO debe aparecer aquí.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para parametrosNombrados");
    }

    /**
     * Reto Extra 6: Parámetros posicionales (estilo {@code getParameters().getUnnamed()}).
     * Devuelve los argumentos que NO son "--clave=valor".
     */
    public static List<String> parametrosPosicionales(List<String> args) {
        // GUÍA: teoría 1.1. Es el complemento del reto 5.
        // 1. Filtra los que NO empiezan por "--".
        // PISTA: args.stream().filter(a -> !a.startsWith("--")).toList();
        // OJO: "--verbose" (flag sin "=") tampoco es posicional: empieza por "--".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para parametrosPosicionales");
    }

    /**
     * Reto Extra 7: Asignar ids con prefijo.
     * A cada nodo de la lista, ponle el id "prefijo" + su índice (0,1,2…) y devuelve la lista.
     */
    public static List<Node> asignarIds(List<Node> nodos, String prefijo) {
        // GUÍA: teoría 1.2 (cada nodo puede llevar un id para localizarlo después).
        // 1. Recorre con índice. 2. nodos.get(i).setId(prefijo + i).
        // PISTA: un bucle for clásico con índice es lo más claro aquí.
        // OJO: el test comprueba que el nodo 0 tiene id "campo0" (prefijo "campo").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para asignarIds");
    }

    /**
     * Reto Extra 8: Primer hijo directo con un id.
     * Busca SOLO entre los hijos directos de 'raiz' el primero cuyo id coincida.
     */
    public static Optional<Node> primerHijoConId(Parent raiz, String id) {
        // GUÍA: teoría 1.2 (búsqueda en un solo nivel, sin recursión todavía).
        // 1. Recorre raiz.getChildrenUnmodifiable(). 2. Devuelve el primero con id igual.
        // PISTA: ...stream().filter(n -> id.equals(n.getId())).findFirst();
        // OJO: usa id.equals(n.getId()) (no al revés) por si getId() es null.
        //   Si no hay coincidencia, Optional.empty(). En Ej256 lo harás recursivo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para primerHijoConId");
    }

    /**
     * Reto Extra 9: Recolectar los ids de los hijos directos.
     * Devuelve la lista de ids no nulos de los hijos directos, en orden.
     */
    public static List<String> recolectarIds(Parent raiz) {
        // GUÍA: teoría 1.2. Combina recorrer + filtrar nulos + mapear.
        // 1. Stream de hijos. 2. map(Node::getId). 3. filter(no nulo). 4. toList().
        // OJO: un hijo sin id aporta null y NO debe aparecer (filtra Objects::nonNull).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para recolectarIds");
    }

    /**
     * Reto Extra 10: Construir un árbol anidado de N niveles.
     * Devuelve un {@link VBox} que contiene otro {@code VBox}, que contiene otro… 'niveles' veces.
     */
    public static Parent construirArbolAnidado(int niveles) {
        // GUÍA: teoría 1.2 (un scene graph es un árbol; aquí lo construyes en cadena).
        // 1. Si niveles <= 0, devuelve un VBox vacío.
        // 2. Crea la raíz; mantén una referencia al "actual"; en cada paso crea un VBox hijo,
        //    añádelo al actual y baja: actual = hijo.
        // PISTA: VBox raiz = new VBox(); VBox actual = raiz; for(...) { VBox h = new VBox(); actual.getChildren().add(h); actual = h; }
        // OJO: contarNodos(construirArbolAnidado(3)) debe dar 3 (un VBox por nivel, anidados).
        // CULTURA: esta misma recursión/encadenado describe el DOM de una web o un árbol de ficheros.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para construirArbolAnidado");
    }
}
