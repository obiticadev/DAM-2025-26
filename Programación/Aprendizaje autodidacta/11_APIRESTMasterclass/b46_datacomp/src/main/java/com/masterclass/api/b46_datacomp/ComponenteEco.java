package com.masterclass.api.b46_datacomp;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Implementación de apoyo de {@link ComponenteDao} (fully built, sin TODOs).
 *
 * <p>Es un "eco": al ejecutar una petición devuelve {@code "eco:"+peticion} y dispara el evento
 * {@code "resultado"}. Si la petición es {@code "ERROR"}, dispara el evento {@code "error"} y
 * devuelve {@code "error:ERROR"}. Sirve para que {@code Ej356} integre y use un componente real
 * sin depender de ninguna base de datos. Tiene constructor sin argumentos para poder descubrirse
 * por {@link java.util.ServiceLoader}.
 */
public class ComponenteEco implements ComponenteDao {

    private Configuracion configuracion;
    private final PropertyChangeSupport eventos = new PropertyChangeSupport(this);

    public ComponenteEco() {
    }

    public ComponenteEco(Configuracion cfg) {
        this.configuracion = cfg;
    }

    public Configuracion getConfiguracion() {
        return configuracion;
    }

    @Override
    public void configurar(Configuracion cfg) {
        this.configuracion = cfg;
    }

    @Override
    public String ejecutar(String peticion) {
        if ("ERROR".equals(peticion)) {
            String msg = "error:" + peticion;
            eventos.firePropertyChange("error", null, msg);
            return msg;
        }
        String resultado = "eco:" + peticion;
        eventos.firePropertyChange("resultado", null, resultado);
        return resultado;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        eventos.addPropertyChangeListener(listener);
    }
}
