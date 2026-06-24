package com.masterclass.api.b46_datacomp;

import java.beans.PropertyChangeListener;

/**
 * Contrato de un componente de acceso a datos integrable (apoyo de {@code Ej356}).
 *
 * <p>Una aplicación que recibe un {@code ComponenteDao} solo conoce ESTE contrato: lo
 * <b>configura</b>, se <b>suscribe</b> a sus eventos y le pide <b>ejecutar</b> operaciones, sin
 * saber qué hay debajo (un Map, JDBC, Mongo...). Esa es la gracia de programar contra una interfaz:
 * el componente concreto se puede cambiar sin tocar la app. Es también lo que permite descubrir
 * implementaciones por SPI ({@link java.util.ServiceLoader}).
 */
public interface ComponenteDao {

    /** Aplica la configuración (url, usuario, tamaño de página). */
    void configurar(Configuracion cfg);

    /** Ejecuta una petición y devuelve su resultado; además avisa por evento. */
    String ejecutar(String peticion);

    /** Suscribe a la app a los eventos del componente. */
    void addPropertyChangeListener(PropertyChangeListener listener);
}
