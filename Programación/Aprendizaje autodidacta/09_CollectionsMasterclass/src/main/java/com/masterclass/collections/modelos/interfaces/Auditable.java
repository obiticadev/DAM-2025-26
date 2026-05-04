package com.masterclass.collections.modelos.interfaces;

import java.time.LocalDateTime;

/**
 * Contrato para entidades con trazabilidad de creación y modificación.
 * Usada en ejercicios de instanceof y Boss Final.
 */
public interface Auditable {
    LocalDateTime getFechaCreacion();
    LocalDateTime getUltimaModificacion();
    String getCreadoPor();
}
