package com.masterclass.api.b32_fxbase;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.Parent;

/**
 * Ejercicio 256 · El scene graph como árbol: recorrer y buscar nodos.
 *
 * <p>Teoría: {@code teoria/32_JavaFX_Base.md} (sección 1.2).
 */
public final class Ej256SceneGraph {

    private Ej256SceneGraph() {
    }

    /**
     * Busca en TODO el árbol (recursivo, en profundidad) el primer nodo con ese id.
     *
     * @param raiz raíz del subárbol donde buscar
     * @param id   identificador buscado
     * @return el nodo encontrado, o {@code null} si no existe / sin implementar
     */
    public static Node buscarPorId(Parent raiz, String id) {
        // TODO 1: si raiz es null o id es null, devuelve null (caso límite).
        // TODO 2: recorre los hijos directos: raiz.getChildrenUnmodifiable().
        // TODO 3: si un hijo tiene ese id (id.equals(hijo.getId())), devuélvelo.
        // TODO 4: si el hijo es un Parent, baja recursivamente; si la llamada
        //         devuelve algo no nulo, propágalo hacia arriba.
        // TODO 5: si nadie coincide tras recorrer todo, devuelve null.
        return null;
    }

    /**
     * Profundidad máxima del árbol: una hoja mide 1; un padre con hijos, 1 + el hijo más hondo.
     *
     * @param nodo raíz del subárbol
     * @return profundidad (>=1 para un nodo no nulo); 0 si null; -1 sin implementar
     */
    public static int profundidadArbol(Node nodo) {
        // TODO 6: caso base — si nodo es null, devuelve 0.
        // TODO 7: si NO es un Parent (o no tiene hijos), es una hoja: devuelve 1.
        // TODO 8: recorre los hijos calculando profundidadArbol(hijo) de cada uno.
        // TODO 9: quédate con el máximo de esas profundidades de hijos.
        // TODO 10: devuelve 1 + ese máximo.
        return -1;
    }

    public static void main(String[] args) {
        javafx.scene.layout.VBox raiz = new javafx.scene.layout.VBox();
        javafx.scene.control.Label l = new javafx.scene.control.Label("hola");
        l.setId("saludo");
        raiz.getChildren().add(l);
        System.out.println("Encontrado: " + buscarPorId(raiz, "saludo"));
        System.out.println("Profundidad: " + profundidadArbol(raiz));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Contar hojas.
     * Cuenta los nodos hoja (sin hijos) del árbol.
     */
    public static int contarHojas(Node nodo) {
        // GUÍA: teoría 1.2 (recorrido recursivo, caso base = hoja).
        // 1. null -> 0. 2. Si no es Parent o no tiene hijos -> 1 (es hoja).
        // 3. Si tiene hijos, suma contarHojas de cada hijo.
        // OJO: un Parent CON hijos no cuenta como hoja; solo suman sus descendientes hoja.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarHojas");
    }

    /**
     * Reto Extra 2: Recolectar todos los ids del árbol.
     * Devuelve, en orden de recorrido en profundidad, los ids no nulos de TODO el árbol.
     */
    public static List<String> recolectarTodosLosIds(Parent raiz) {
        // GUÍA: teoría 1.2. Acumula en una lista mientras recorres en profundidad.
        // 1. Crea una List. 2. Recorre hijos; si el id no es null, añádelo;
        //    si el hijo es Parent, concatena su recolectarTodosLosIds.
        // PISTA: una función auxiliar recursiva que reciba el acumulador es lo más limpio.
        // OJO: el orden es DFS (primero el hijo y luego SUS hijos, antes del siguiente hermano).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para recolectarTodosLosIds");
    }

    /**
     * Reto Extra 3: ¿Existe un id?
     * Indica si en el árbol existe algún nodo con ese id.
     */
    public static boolean existeId(Parent raiz, String id) {
        // GUÍA: teoría 1.2. Reaprovecha buscarPorId.
        // 1. return buscarPorId(raiz, id) != null;
        // OJO: el test prueba un id que SÍ está hondo y otro que no existe (false).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para existeId");
    }

    /**
     * Reto Extra 4: Ruta de ids hasta un nodo.
     * Devuelve la lista de ids desde la raíz hasta el nodo con ese id (incluidos ambos).
     * Si no se encuentra, lista vacía.
     */
    public static List<String> rutaHastaId(Parent raiz, String id) {
        // GUÍA: teoría 1.2 (backtracking: añade al entrar, quita si la rama falla).
        // 1. Lleva una lista "camino". 2. Al visitar un nodo añade su id; si es el buscado, copia y devuelve.
        // 3. Si es Parent, baja; si la rama encuentra, propaga. 4. Si la rama falla, quita el id (backtrack).
        // PISTA: devuelve una COPIA (new ArrayList<>(camino)) al encontrarlo, no la lista viva.
        // OJO: la ruta incluye el id del propio nodo destino; si no existe, devuelve List.of().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rutaHastaId");
    }

    /**
     * Reto Extra 5: Contar nodos visibles.
     * Cuenta los nodos del árbol cuyo {@code isVisible()} es true.
     */
    public static int contarVisibles(Parent raiz) {
        // GUÍA: teoría 1.2 (propiedad 'visible': el nodo no se pinta ni recibe eventos).
        // 1. Recorre TODO el árbol (incluida la raíz). 2. Suma 1 por cada nodo con isVisible().
        // OJO: por defecto un nodo es visible (true). El test pone uno a setVisible(false) y NO debe contar.
        //   'visible' es distinto de 'managed' (reto 6): invisible pero managed sigue ocupando hueco.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarVisibles");
    }

    /**
     * Reto Extra 6: Contar nodos NO gestionados (unmanaged).
     * Cuenta los nodos del árbol cuyo {@code isManaged()} es false.
     */
    public static int contarNoGestionados(Parent raiz) {
        // GUÍA: teoría 1.2 (managed: si el layout reserva hueco para el nodo).
        // 1. Recorre el árbol. 2. Suma 1 por cada nodo con isManaged() == false.
        // OJO: por defecto managed = true, así que lo normal es 0; el test marca uno con
        //   setManaged(false). DIFERENCIA CLAVE: visible=false sigue ocupando hueco;
        //   managed=false NO ocupa hueco (el layout lo ignora) aunque siga visible.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarNoGestionados");
    }

    /**
     * Reto Extra 7: Índice z-order de un hijo directo.
     * Devuelve la posición del nodo dentro de los hijos de 'padre' (define quién pinta encima).
     */
    public static int indiceZ(Parent padre, Node hijo) {
        // GUÍA: teoría 1.3 (z-order = orden en la lista de hijos; el último pinta ENCIMA).
        // 1. return padre.getChildrenUnmodifiable().indexOf(hijo);
        // OJO: indexOf devuelve -1 si no está; el test usa eso como caso límite.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para indiceZ");
    }

    /**
     * Reto Extra 8: Traer un hijo al frente.
     * Mueve el nodo al final de la lista de hijos (queda pintado encima) y devuelve su nuevo índice.
     */
    public static int traerAlFrente(Parent padre, Node hijo) {
        // GUÍA: teoría 1.3. Quitar y volver a añadir al final cambia el z-order.
        // 1. Necesitas getChildren() (mutable) -> haz cast a Pane: ((Pane) padre).getChildren().
        // 2. remove(hijo); 3. add(hijo); 4. devuelve el último índice (size()-1).
        // PISTA: import javafx.scene.layout.Pane; (un VBox/HBox ES un Pane).
        // OJO: getChildrenUnmodifiable() NO deja modificar; por eso casteas a Pane.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para traerAlFrente");
    }

    /**
     * Reto Extra 9: Anchura del árbol (máximo de hijos en un mismo nivel).
     * Devuelve el mayor número de nodos que hay en cualquier nivel de profundidad.
     */
    public static int anchuraMaxima(Parent raiz) {
        // GUÍA: teoría 1.2 (recorrido por niveles = BFS con una cola).
        // 1. Mete la raíz en una cola (Deque/Queue). 2. En cada vuelta procesa un NIVEL completo
        //    (tamaño actual de la cola), encola los hijos de cada uno y mide ese tamaño.
        // 3. Quédate con el máximo de tamaños de nivel.
        // PISTA: int n = cola.size(); for(i<n){ saca uno; encola sus hijos } max = Math.max(max, n);
        // OJO: el nivel 0 (solo la raíz) mide 1; el test arma un árbol cuyo nivel más ancho es 3.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para anchuraMaxima");
    }

    /**
     * Reto Extra 10: Buscar el primer nodo de un tipo dado.
     * Devuelve, recorriendo en profundidad, el primer nodo asignable a 'tipo'.
     */
    public static Node buscarPorTipo(Parent raiz, Class<?> tipo) {
        // GUÍA: teoría 1.2 + genéricos (b01·Ej015/016): filtrar por Class en vez de por id.
        // 1. Recorre en profundidad. 2. Si tipo.isInstance(nodo) -> devuélvelo.
        // PISTA: tipo.isInstance(hijo) es el equivalente dinámico de "hijo instanceof Tipo".
        // OJO: devuelve el PRIMERO en orden DFS; el test busca un Button enterrado bajo varios VBox.
        // CULTURA: esto es justo lo que hace Node.lookup("#id") / lookupAll(".clase") por dentro,
        //   y se parece a querySelector del DOM. Lo formalizarás con CSS selectors en b36.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para buscarPorTipo");
    }
}
