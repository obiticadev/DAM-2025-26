package com.masterclass.api.b35_fxdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO inmutable que representa un cliente tal como llega de la API REST (b05) en JSON. Es un
 * {@code record}: perfecto para TRANSPORTAR datos (lo que Jackson rellena al parsear), a diferencia
 * del {@link ClienteFx} (bean observable) que la tabla necesita para editar.
 *
 * <p>{@code @JsonIgnoreProperties(ignoreUnknown = true)}: si la API añade campos que aquí no
 * mapeamos, Jackson los ignora en vez de fallar. Es la práctica defensiva estándar para clientes de
 * APIs que pueden evolucionar (lo viste en b07/b02).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ClienteDto(Long id, String nombre, String email) {
}
