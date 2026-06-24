package com.masterclass.api.b46_datacomp;

/**
 * Configuración inmutable que se inyecta a un {@link ComponenteDao} al integrarlo (apoyo).
 *
 * @param url          dirección del almacén de datos
 * @param usuario      usuario de conexión
 * @param tamanoPagina tamaño de página por defecto
 */
public record Configuracion(String url, String usuario, int tamanoPagina) {
}
