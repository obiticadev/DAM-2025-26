package com.masterclass.api.b43_erp;

import java.util.List;

/**
 * Plan de sincronización: el resultado de comparar el sistema origen con el destino (Ej336).
 *
 * <p>Antes de tocar el ERP se calcula QUÉ hay que hacer con cada registro, agrupado por acción.
 * Separar el <em>cálculo</em> del plan de su <em>ejecución</em> es lo que permite revisarlo,
 * testearlo y hacerlo idempotente: ejecutar dos veces el mismo plan deja el destino igual.
 *
 * @param altas           idExterno de los registros que existen en origen pero no en destino
 * @param modificaciones  idExterno de los que existen en ambos pero con contenido distinto
 * @param sinCambios      idExterno de los que existen en ambos e idénticos (no se tocan)
 */
public record SyncPlan(List<String> altas, List<String> modificaciones, List<String> sinCambios) {
}
