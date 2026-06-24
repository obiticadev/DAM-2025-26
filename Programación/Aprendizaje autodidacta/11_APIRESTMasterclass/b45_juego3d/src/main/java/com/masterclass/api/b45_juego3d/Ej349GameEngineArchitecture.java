package com.masterclass.api.b45_juego3d;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Ejercicio 349 · Arquitectura de un motor de juego: scene graph y ECS.
 *
 * <p>Núcleo conceptual del bloque (PMDM·RA4: "componentes de un motor de juegos"). Un motor moderno
 * no guarda los objetos como una jerarquía de clases (herencia profunda), sino con el patrón
 * <strong>ECS</strong> (<em>Entity-Component-System</em>):
 * <ul>
 *   <li><strong>Entidad</strong>: un simple id (un número). No tiene datos ni lógica.</li>
 *   <li><strong>Componente</strong>: un trozo de datos (Posición, Velocidad, Salud...). Sin lógica.</li>
 *   <li><strong>Sistema</strong>: la lógica que recorre las entidades con ciertos componentes y los
 *       actualiza (el sistema de movimiento suma posición += velocidad·dt).</li>
 * </ul>
 * Es lo que usan Unity (GameObject + Component), Godot (nodos) o Unreal. Aquí se construye un
 * mini-ECS testeable: la {@code Mundo} guarda los datos, los <em>sistemas</em> son métodos estáticos.
 *
 * <p>Teoría: {@code teoria/45_Juegos3D_Motores.md} (sección 6).
 */
public final class Ej349GameEngineArchitecture {

    private Ej349GameEngineArchitecture() {
    }

    /** Marca de "trozo de datos" de una entidad. Las clases de componente la implementan. */
    public interface Componente {
    }

    /** Componente de posición 3D (inmutable: un sistema produce una nueva, no muta la vieja). */
    public record Posicion(double x, double y, double z) implements Componente {
    }

    /** Componente de velocidad 3D. */
    public record Velocidad(double x, double y, double z) implements Componente {
    }

    /** Componente de salud (ejemplo de dato no espacial). */
    public record Salud(int puntos) implements Componente {
    }

    /**
     * El "mundo" del ECS: el almacén de entidades y sus componentes. Implementación COMPLETA (no es
     * parte de los TODOs): cada entidad es un id que mapea a un mapa {@code tipo -> componente}.
     */
    public static final class Mundo {
        private final Map<Integer, Map<Class<? extends Componente>, Componente>> entidades = new LinkedHashMap<>();
        private int siguienteId = 0;

        /** Crea una entidad vacía y devuelve su id. */
        public int crearEntidad() {
            int id = siguienteId++;
            entidades.put(id, new LinkedHashMap<>());
            return id;
        }

        /** Asocia (o reemplaza) un componente a una entidad, indexado por su clase. */
        public void agregar(int id, Componente componente) {
            Map<Class<? extends Componente>, Componente> mapa = entidades.get(id);
            if (mapa != null && componente != null) {
                mapa.put(componente.getClass(), componente);
            }
        }

        /** Quita un componente de una entidad. */
        public void quitar(int id, Class<? extends Componente> tipo) {
            Map<Class<? extends Componente>, Componente> mapa = entidades.get(id);
            if (mapa != null) {
                mapa.remove(tipo);
            }
        }

        /** Devuelve el componente del tipo pedido (o {@code null} si la entidad no lo tiene). */
        public <T extends Componente> T obtener(int id, Class<T> tipo) {
            Map<Class<? extends Componente>, Componente> mapa = entidades.get(id);
            if (mapa == null) {
                return null;
            }
            return tipo.cast(mapa.get(tipo));
        }

        /** ¿Tiene la entidad un componente de ese tipo? */
        public boolean tiene(int id, Class<? extends Componente> tipo) {
            Map<Class<? extends Componente>, Componente> mapa = entidades.get(id);
            return mapa != null && mapa.containsKey(tipo);
        }

        /** Ids de todas las entidades, en orden de creación. */
        public Set<Integer> ids() {
            return entidades.keySet();
        }
    }

    /**
     * Sistema de movimiento: por cada entidad con {@code Posicion} y {@code Velocidad}, avanza la
     * posición {@code pos += vel·dt}. Es el "tick" de actualización del motor.
     *
     * @param mundo el mundo a actualizar (se modifica en sitio)
     * @param dt    delta de tiempo en segundos (un frame)
     */
    public static void tick(Mundo mundo, double dt) {
        // TODO 1: si mundo es null o dt < 0 -> return (nada que hacer).
        // TODO 2: recorre todos los ids del mundo (mundo.ids()).
        // TODO 3: para cada id, obtén su Posicion p y su Velocidad v.
        // TODO 4: si p es null O v es null -> continúa con la siguiente (esta entidad no se mueve).
        // TODO 5: calcula la nueva posición: nx = p.x + v.x*dt ; ny = p.y + v.y*dt ; nz = p.z + v.z*dt.
        // TODO 6: agrega al mundo una nueva Posicion(nx, ny, nz) (reemplaza la vieja).
        //         (Si no implementas nada, las posiciones no cambian y el test falla -> rojo.)
    }

    /**
     * Consulta: devuelve los ids de las entidades que tienen un componente del tipo dado.
     *
     * @param mundo el mundo
     * @param tipo  la clase de componente buscada
     * @return lista de ids (en orden de creación); {@code List.of()} si ninguno coincide
     */
    public static List<Integer> entidadesCon(Mundo mundo, Class<? extends Componente> tipo) {
        // TODO 7: si mundo es null o tipo es null -> List.of().
        // TODO 8: prepara una lista de resultado.
        // TODO 9: recorre los ids y añade los que cumplan mundo.tiene(id, tipo).
        // TODO 10: devuelve la lista.
        return List.of();
    }

    public static void main(String[] args) {
        Mundo m = new Mundo();
        int jugador = m.crearEntidad();
        m.agregar(jugador, new Posicion(0, 0, 0));
        m.agregar(jugador, new Velocidad(1, 2, 3));
        tick(m, 1.0);
        System.out.println("posición tras tick: " + m.obtener(jugador, Posicion.class));
        System.out.println("entidades con Velocidad: " + entidadesCon(m, Velocidad.class));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Contar cuántas entidades hay en el mundo.
     */
    public static int contarEntidades(Mundo mundo) {
        // GUÍA: teoría 6 (la entidad es solo un id; contar = tamaño del conjunto de ids).
        // 1. Si mundo es null -> 0.
        // 2. Devuelve mundo.ids().size().
        // OJO: el test crea 2 entidades -> 2; mundo vacío -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarEntidades");
    }

    /**
     * Reto Extra 2: Consulta multi-componente — entidades que tienen DOS componentes a la vez.
     */
    public static List<Integer> entidadesConDos(Mundo mundo, Class<? extends Componente> a, Class<? extends Componente> b) {
        // GUÍA: teoría 6 (un sistema real consulta "todas las entidades con Posicion Y Velocidad").
        // 1. Si mundo, a o b son null -> List.of().
        // 2. Recorre ids y añade los que cumplan tiene(id,a) && tiene(id,b).
        // OJO: el test: una entidad con Posicion+Velocidad cuenta; una con solo Posicion, no.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para entidadesConDos");
    }

    /**
     * Reto Extra 3: Clonar un componente de posición (base de un "prefab"/plantilla de entidad).
     */
    public static Posicion clonarPosicion(Posicion p) {
        // GUÍA: teoría 6 (un prefab es una plantilla que se copia para instanciar muchas entidades).
        // 1. Si p es null -> null.
        // 2. Devuelve new Posicion(p.x(), p.y(), p.z()).
        // OJO: el test comprueba que la copia es igual por valor (equals de record) pero independiente.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clonarPosicion");
    }

    /**
     * Reto Extra 4: Sistema de gravedad — devuelve una velocidad con la componente Y modificada.
     * vy_nueva = vy + g·dt.
     */
    public static Velocidad aplicarGravedad(Velocidad v, double g, double dt) {
        // GUÍA: teoría 6 (un sistema de física modifica la velocidad; otro, la posición -> separación de roles).
        // 1. Si v es null -> null.
        // 2. Devuelve new Velocidad(v.x(), v.y() + g*dt, v.z()).
        // OJO: el test: Velocidad(0,0,0), g=-10, dt=0.5 -> Velocidad(0,-5,0).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aplicarGravedad");
    }

    /**
     * Reto Extra 5: Integrar — la unidad matemática del sistema de movimiento.
     * pos_nueva = pos + vel·dt.
     */
    public static Posicion integrar(Posicion p, Velocidad v, double dt) {
        // GUÍA: teoría 6 (es justo lo que hace tick() por dentro, pero aislado y testeable a solas).
        // 1. Si p o v son null -> null.
        // 2. Devuelve new Posicion(p.x()+v.x()*dt, p.y()+v.y()*dt, p.z()+v.z()*dt).
        // OJO: el test: Posicion(0,0,0), Velocidad(1,2,3), dt=2 -> Posicion(2,4,6).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para integrar");
    }

    /**
     * Reto Extra 6: Ordenar los sistemas según un orden dado (el orden de ejecución importa).
     * Devuelve solo los sistemas presentes, en el orden indicado por 'orden'.
     */
    public static List<String> ordenarSistemas(List<String> sistemas, List<String> orden) {
        // GUÍA: teoría 6 (input antes que física, física antes que render: el orden es parte del diseño).
        // 1. Si sistemas u orden son null -> List.of().
        // 2. Recorre 'orden' y, por cada nombre que esté en 'sistemas', añádelo al resultado.
        // OJO: el test: sistemas={"render","fisica","input"}, orden={"input","fisica","render"}
        //   -> {"input","fisica","render"}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ordenarSistemas");
    }

    /**
     * Reto Extra 7: Paso fijo — cuántos pasos de simulación caben en el tiempo acumulado.
     * Igual que el fixed timestep de b41: pasos = floor(acumulado / paso).
     */
    public static int pasoFijo(double acumulado, double paso) {
        // GUÍA: teoría 6 (la física se actualiza a pasos fijos aunque los fps varíen -> determinismo).
        // 1. Si paso <= 0 -> 0.
        // 2. Devuelve (int) Math.floor(acumulado / paso).
        // OJO: el test: acumulado 0.05, paso 0.02 -> 2 (caben 2 pasos completos).
        // CULTURA: este patrón viene de b41 (game loop); aquí lo formaliza la arquitectura del motor.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoFijo");
    }

    /**
     * Reto Extra 8: Tiempo restante tras consumir los pasos fijos (se acumula para el siguiente frame).
     */
    public static double restanteTrasPasos(double acumulado, double paso) {
        // GUÍA: teoría 6 (el "sobrante" no se tira: se guarda para el próximo frame; si no, la física deriva).
        // 1. Si paso <= 0 -> acumulado.
        // 2. pasos = pasoFijo(acumulado, paso); devuelve acumulado - pasos*paso.
        // OJO: el test: acumulado 0.05, paso 0.02 -> 0.05 - 2*0.02 = 0.01 (tolerancia 1e-9).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para restanteTrasPasos");
    }

    /**
     * Reto Extra 9: Serializar el mundo a una cadena (resumen del estado, base de guardar partida).
     */
    public static String serializarMundo(Mundo mundo) {
        // GUÍA: teoría 6 (guardar partida = serializar el mundo; enlaza con b46, serialización de componentes).
        // 1. Si mundo es null -> "" (cadena vacía).
        // 2. Devuelve "entidades=" + contarEntidades(mundo).
        // OJO: el test: mundo con 2 entidades -> "entidades=2"; vacío -> "entidades=0".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarMundo");
    }

    /**
     * Reto Extra 10: Tabla de motores de juego reales — devuelve el motor según su lenguaje (RA4).
     */
    public static String motorPorLenguaje(String lenguaje) {
        // GUÍA: teoría 6 (RA4 pide CONOCER motores reales: C#->Unity, GDScript->Godot, C++->Unreal,
        //   Java->jMonkeyEngine). Es una tabla de decisión, no un if gigante mal hecho.
        // 1. Si lenguaje es null -> "Desconocido".
        // 2. Normaliza a minúsculas y mapea: "c#"->"Unity", "gdscript"->"Godot", "c++"->"Unreal",
        //    "java"->"jMonkeyEngine"; cualquier otro -> "Desconocido".
        // OJO: el test: "C#" -> "Unity"; "Java" -> "jMonkeyEngine"; "Cobol" -> "Desconocido".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para motorPorLenguaje");
    }
}
