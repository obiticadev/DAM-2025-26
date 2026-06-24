package com.masterclass.api.b46_datacomp;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Ejercicio 354 · <b>NÚCLEO DEL BLOQUE</b>: un componente DAO reutilizable, escrito como JavaBean.
 *
 * <p>Teoría: {@code teoria/46_Componentes_Datos.md} (sección 4).
 *
 * <p>Aquí se juntan los tres ejercicios anteriores en UN componente entregable: tiene
 * <b>propiedades</b> ({@code url}, {@code usuario}, {@code tamanoPagina} — como 351),
 * <b>eventos</b> ({@code "cargaCompletada"} y {@code "error"} vía {@link PropertyChangeSupport} —
 * como 352) y <b>métodos CRUD</b> ({@code buscar/guardar/borrar}) contra un <em>store</em> en
 * memoria <b>inyectable</b> (un {@code Map}), para poder probarlo SIN una base de datos real.
 *
 * <p><b>Excepción consciente a la regla "todo static / ctor privado":</b> un componente es, por
 * definición, un OBJETO con estado que alguien instancia, configura y al que se suscribe. Por eso
 * esta clase es un JavaBean instanciable (constructor público, métodos de instancia), igual que en
 * 352 los beans observables. La lógica testeable sigue siendo pura: store fake, sin red.
 */
public class Ej354DataAccessComponent {

    private String url;
    private String usuario;
    private int tamanoPagina = 10;
    private boolean conectado;

    private final Map<Integer, Cliente> store;
    private final PropertyChangeSupport eventos = new PropertyChangeSupport(this);

    /** Componente con un store vacío por defecto. */
    public Ej354DataAccessComponent() {
        this(new LinkedHashMap<>());
    }

    /** Componente con un store INYECTADO (lo que permite probarlo con datos fake, sin BD). */
    public Ej354DataAccessComponent(Map<Integer, Cliente> store) {
        this.store = store;
    }

    // ---- propiedades (estilo JavaBean) ----
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getTamanoPagina() {
        return tamanoPagina;
    }

    /** El tamaño de página debe ser positivo (si no, se ignora el valor). */
    public void setTamanoPagina(int tamanoPagina) {
        if (tamanoPagina > 0) {
            this.tamanoPagina = tamanoPagina;
        }
    }

    public boolean isConectado() {
        return conectado;
    }

    // ---- suscripción a eventos del componente ----
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        eventos.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        eventos.removePropertyChangeListener(listener);
    }

    // ================== MÉTODOS CORE (10 TODOs entre buscar/guardar/borrar) ==================

    /**
     * Devuelve una "página" de clientes (ordenados por id) y avisa con el evento
     * {@code "cargaCompletada"} (valor nuevo = nº de elementos devueltos).
     *
     * @param pagina índice de página empezando en 0
     * @return la sublista de esa página; {@code List.of()} si la página es negativa o no hay datos
     */
    public List<Cliente> buscar(int pagina) {
        // TODO 1: si pagina < 0, devuelve List.of().
        // TODO 2: vuelca store.values() a una lista y ordénala por Cliente::id (orden determinista).
        // TODO 3: calcula el "salto": skip = pagina * tamanoPagina (esto es la paginación, como ?page=&size=).
        // TODO 4: con stream().skip(skip).limit(tamanoPagina).toList() obtén la sublista de la página.
        // TODO 5: dispara eventos.firePropertyChange("cargaCompletada", null, sublista.size())
        //         y devuelve la sublista.
        return List.of();
    }

    /**
     * Da de alta o actualiza un cliente (upsert) por su id y avisa con {@code "cargaCompletada"}.
     *
     * @param c cliente a guardar
     * @return el cliente guardado, o {@code null} si {@code c} es null
     */
    public Cliente guardar(Cliente c) {
        // TODO 6: si c es null, devuelve null.
        // TODO 7: averigua si es ALTA o ACTUALIZACIÓN según store.containsKey(c.id()) (guárdalo en un boolean).
        // TODO 8: store.put(c.id(), c); dispara eventos.firePropertyChange("cargaCompletada", existia, c.id());
        //         y devuelve c.
        return null;
    }

    /**
     * Borra un cliente por id.
     *
     * @param id identificador a borrar
     * @return {@code true} si existía y se borró; {@code false} si no estaba
     */
    public boolean borrar(int id) {
        // TODO 9: comprueba si el id existe en el store (boolean existia = store.containsKey(id)).
        // TODO 10: si existía, store.remove(id); devuelve 'existia' (true solo si borró algo).
        return false;
    }

    public static void main(String[] args) {
        Map<Integer, Cliente> datos = new LinkedHashMap<>();
        datos.put(1, new Cliente(1, "Ada"));
        datos.put(2, new Cliente(2, "Linus"));
        Ej354DataAccessComponent comp = new Ej354DataAccessComponent(datos);
        comp.addPropertyChangeListener(e -> System.out.println("evento: " + e.getPropertyName() + "=" + e.getNewValue()));
        System.out.println(comp.buscar(0));
        comp.guardar(new Cliente(3, "Grace"));
        System.out.println(comp.borrar(1));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: número de clientes que tiene el componente ahora mismo.
     */
    public int contarClientes() {
        // GUÍA: teoría 4 (el componente expone su estado).
        // 1. devuelve el tamaño del store.
        // PISTA: return store.size();
        // OJO: el test inyecta 2 clientes -> espera 2.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarClientes");
    }

    /**
     * Reto Extra 2: comprobación de salud del componente (¿está listo para operar?).
     */
    public boolean healthCheck() {
        // GUÍA: teoría 4 (un componente serio sabe decir si está sano: config presente + store).
        // 1. true si url y usuario NO son null y el store existe.
        // PISTA: return url != null && usuario != null && store != null;
        // OJO: el test pone url y usuario y espera true; sin configurarlos, false.
        // CULTURA: es el /health de Spring Boot Actuator (b10/arquitectura) en miniatura.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para healthCheck");
    }

    /**
     * Reto Extra 3: DDL para crear la tabla del componente sobre JDBC (enlace b11).
     */
    public String ddlCrearTabla() {
        // GUÍA: teoría 4 (el MISMO componente puede respaldarse en JDBC real). Aquí solo devuelves
        //   la sentencia, sin ejecutarla (no arrastramos H2 al test; eso ya se practicó en b11).
        // 1. devuelve "CREATE TABLE clientes (id INT PRIMARY KEY, nombre VARCHAR(255))".
        // PISTA: cópiala tal cual.
        // OJO: el test compara la cadena EXACTA. Cuida mayúsculas, espacios y el VARCHAR(255).
        // CULTURA: este sería el componente "sobre conector" del CE c) de AD·RA6 (JDBC, b11).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ddlCrearTabla");
    }

    /**
     * Reto Extra 4: anotaciones JPA que llevaría la entidad si el componente fuera sobre ORM (enlace b12).
     */
    public List<String> anotacionesJpa() {
        // GUÍA: teoría 4 (componente "sobre ORM", CE d). Modelamos las anotaciones como cadenas.
        // 1. devuelve List.of("@Entity", "@Id", "@GeneratedValue").
        // PISTA: return List.of("@Entity", "@Id", "@GeneratedValue");
        // OJO: el test compara la lista EXACTA y EN ESE ORDEN.
        // CULTURA: el mismo DAO, respaldado por JPA/Hibernate (b12) en vez de un Map.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para anotacionesJpa");
    }

    /**
     * Reto Extra 5: representa un cliente como "documento" estilo MongoDB (enlace b17).
     */
    public Map<String, Object> documentoMongo(Cliente c) {
        // GUÍA: teoría 4 (componente "sobre documental", CE e/g). Un documento Mongo usa "_id".
        // 1. crea un LinkedHashMap y mete "_id" -> c.id() y "nombre" -> c.nombre().
        // PISTA: Map<String,Object> doc = new LinkedHashMap<>(); doc.put("_id", c.id()); ...
        // OJO: el test comprueba doc.get("_id") == id del cliente y doc.get("nombre") == su nombre.
        //   La clave es "_id" (con guion bajo), la convención de Mongo, no "id".
        // CULTURA: el mismo DAO contra Mongo (b17): cambia el almacén, no el contrato del componente.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para documentoMongo");
    }

    /**
     * Reto Extra 6: alta en lote de varios clientes; devuelve cuántos quedaron guardados.
     */
    public int guardarVarios(List<Cliente> clientes) {
        // GUÍA: teoría 4 (operación de carga masiva; reutiliza guardar()).
        // 1. null -> 0.
        // 2. recorre la lista llamando a guardar(c) para cada uno.
        // 3. devuelve cuántos se guardaron (los no nulos).
        // PISTA: apóyate en guardar(c); cuenta los que no devolvieron null.
        // OJO: el test mete 3 clientes nuevos y espera 3. Si reutilizas guardar(), también se
        //   disparan sus eventos (es correcto).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para guardarVarios");
    }

    /**
     * Reto Extra 7: busca por id; si no existe, dispara el evento {@code "error"} y devuelve null.
     */
    public Cliente buscarPorIdODisparaError(int id) {
        // GUÍA: teoría 4 (un componente avisa de los fallos por EVENTO, no solo por excepción).
        // 1. si el id está en el store, devuélvelo.
        // 2. si NO, dispara eventos.firePropertyChange("error", null, "id no encontrado: "+id) y devuelve null.
        // PISTA: Cliente c = store.get(id); if (c == null) eventos.firePropertyChange("error", null, ...);
        // OJO: el test busca un id EXISTENTE (devuelve su Cliente) y uno INEXISTENTE (devuelve null).
        //   El evento "error" lo capturará un listener en otro test.
        // CULTURA: separar el "qué falló" (mensaje del evento) del "cómo se entera quien usa" es
        //   justo el patrón de notificación de errores de un componente.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para buscarPorIdODisparaError");
    }

    /**
     * Reto Extra 8: configura el componente desde un mapa externo (config externalizada, enlace b04).
     */
    public Ej354DataAccessComponent configurarDesdeMapa(Map<String, String> cfg) {
        // GUÍA: teoría 4 (la configuración NO se cablea en el código: viene de fuera, b04).
        // 1. si cfg es null, devuelve this sin tocar nada.
        // 2. aplica setUrl(cfg.get("url")), setUsuario(cfg.get("usuario")) y, si hay "tamanoPagina",
        //    setTamanoPagina(Integer.parseInt(...)).
        // 3. devuelve this (para poder encadenar).
        // PISTA: if (cfg.containsKey("tamanoPagina")) setTamanoPagina(Integer.parseInt(cfg.get("tamanoPagina")));
        // OJO: el test pasa un mapa con url y tamanoPagina="5" y comprueba getUrl() y getTamanoPagina()==5.
        //   setTamanoPagina ignora valores <=0, cuidado con el parseo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para configurarDesdeMapa");
    }

    /**
     * Reto Extra 9: ciclo de vida de conexión; devuelve el estado tras conectar y tras desconectar.
     */
    public String cicloConexion() {
        // GUÍA: teoría 4 (un componente con recursos tiene init/destroy: conectar/desconectar).
        // 1. pon conectado=true (conectar), anota el estado.
        // 2. pon conectado=false (desconectar), anota el estado.
        // 3. devuelve "true,false" (conectado tras conectar; desconectado después).
        // PISTA: this.conectado = true; ... this.conectado = false; return "true,false";
        // OJO: el test espera exactamente "true,false". El componente NO debe quedar conectado al final.
        // CULTURA: init/destroy es el lifecycle de un bean de Spring (@PostConstruct/@PreDestroy).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cicloConexion");
    }

    /**
     * Reto Extra 10: resumen legible del componente (estilo toString de diagnóstico, enlace b10).
     */
    public String resumenComponente() {
        // GUÍA: teoría 4 (un componente debe poder describirse para logs/diagnóstico).
        // 1. devuelve "ComponenteClientesDao[url=" + url + ", clientes=" + store.size() + "]".
        // PISTA: usa concatenación directa con url y store.size().
        // OJO: el test configura url="jdbc:h2:mem" con 2 clientes y espera
        //   "ComponenteClientesDao[url=jdbc:h2:mem, clientes=2]" EXACTO (cuida la coma y el espacio).
        // CULTURA: este "autodescribirse" cierra el círculo de 351: el componente se cuenta a sí mismo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resumenComponente");
    }
}
