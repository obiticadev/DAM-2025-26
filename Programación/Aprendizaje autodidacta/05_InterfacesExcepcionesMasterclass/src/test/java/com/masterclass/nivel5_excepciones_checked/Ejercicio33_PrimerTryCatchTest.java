package com.masterclass.nivel5_excepciones_checked;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 5 - Ejercicio 33: Primer Try-Catch")
class Ejercicio33_PrimerTryCatchTest {
    @Test @DisplayName("33.1 - parsearSeguro con numero valido") void parseOk() { assertThat(Ejercicio33_PrimerTryCatch.parsearSeguro("42", -1)).isEqualTo(42); }
    @Test @DisplayName("33.2 - parsearSeguro con texto invalido") void parseFail() { assertThat(Ejercicio33_PrimerTryCatch.parsearSeguro("abc", -1)).isEqualTo(-1); }
    @Test @DisplayName("33.3 - dividirSeguro con divisor valido") void divOk() { assertThat(Ejercicio33_PrimerTryCatch.dividirSeguro(10, 3)).isEqualTo(3); }
    @Test @DisplayName("33.4 - dividirSeguro con divisor 0") void divCero() { assertThat(Ejercicio33_PrimerTryCatch.dividirSeguro(10, 0)).isEqualTo(0); }
    @Test @DisplayName("33.5 - accederSeguro con posicion valida") void accOk() { assertThat(Ejercicio33_PrimerTryCatch.accederSeguro(new String[]{"A","B"}, 1)).isEqualTo("B"); }
    @Test @DisplayName("33.6 - accederSeguro con posicion invalida") void accFail() { assertThat(Ejercicio33_PrimerTryCatch.accederSeguro(new String[]{"A"}, 5)).isNull(); }
}
