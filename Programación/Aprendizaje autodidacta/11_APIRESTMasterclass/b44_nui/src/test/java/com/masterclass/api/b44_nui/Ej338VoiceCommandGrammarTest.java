package com.masterclass.api.b44_nui;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import com.masterclass.api.b44_nui.Ej338VoiceCommandGrammar.Intencion;

import static org.junit.jupiter.api.Assertions.*;

class Ej338VoiceCommandGrammarTest {

    @Test
    void interpretarComando() {
        assertEquals(new Intencion("abrir", Map.of("objeto", "fichero ventas")),
                Ej338VoiceCommandGrammar.interpretarComando("abre el fichero ventas"));
        assertEquals(Intencion.desconocida(),
                Ej338VoiceCommandGrammar.interpretarComando("")); // caso límite
    }

    @Test
    void accionDeVerbo() {
        assertEquals("abrir", Ej338VoiceCommandGrammar.accionDeVerbo("abre"));
        assertEquals("", Ej338VoiceCommandGrammar.accionDeVerbo("zumba")); // caso límite: fuera de gramática
    }

    @Test
    void retoExtra01_esSinonimoDeAbrir() {
        assertTrue(Ej338VoiceCommandGrammar.esSinonimoDeAbrir("muestra"));
        assertFalse(Ej338VoiceCommandGrammar.esSinonimoDeAbrir("cierra"));
    }

    @Test
    void retoExtra02_eliminarPalabrasVacias() {
        assertEquals(List.of("abre", "fichero"),
                Ej338VoiceCommandGrammar.eliminarPalabrasVacias(List.of("abre", "el", "fichero")));
        assertEquals(List.of(), Ej338VoiceCommandGrammar.eliminarPalabrasVacias(List.of("de", "la"))); // caso límite
    }

    @Test
    void retoExtra03_interpretarComandoCompuesto() {
        assertEquals(List.of("abrir", "cerrar"),
                Ej338VoiceCommandGrammar.interpretarComandoCompuesto("abre y cierra"));
        assertEquals(List.of(), Ej338VoiceCommandGrammar.interpretarComandoCompuesto("")); // caso límite
    }

    @Test
    void retoExtra04_detectarIdioma() {
        assertEquals("en", Ej338VoiceCommandGrammar.detectarIdioma("open file"));
        assertEquals("es", Ej338VoiceCommandGrammar.detectarIdioma("abre fichero"));
    }

    @Test
    void retoExtra05_corregirTypo() {
        assertEquals("abre", Ej338VoiceCommandGrammar.corregirTypo("abre", List.of("abre", "cierra")));
        assertEquals("cierra", Ej338VoiceCommandGrammar.corregirTypo("cierra", List.of("abre", "cierra")));
        assertEquals("", Ej338VoiceCommandGrammar.corregirTypo("abre", List.of())); // caso límite
    }

    @Test
    void retoExtra06_slotOpcionalConDefecto() {
        assertEquals("x", Ej338VoiceCommandGrammar.slotOpcionalConDefecto(Map.of("objeto", "x"), "objeto", "def"));
        assertEquals("def", Ej338VoiceCommandGrammar.slotOpcionalConDefecto(Map.of(), "objeto", "def")); // caso límite
    }

    @Test
    void retoExtra07_accionDesdeGramatica() {
        assertEquals("abrir", Ej338VoiceCommandGrammar.accionDesdeGramatica("abre", Map.of("abre", "abrir")));
        assertEquals("", Ej338VoiceCommandGrammar.accionDesdeGramatica("zzz", Map.of())); // caso límite
    }

    @Test
    void retoExtra08_prioridadComando() {
        assertEquals(3, Ej338VoiceCommandGrammar.prioridadComando("borrar"));
        assertEquals(0, Ej338VoiceCommandGrammar.prioridadComando("")); // caso límite
    }

    @Test
    void retoExtra09_comandoContextual() {
        assertEquals("guardar@editor", Ej338VoiceCommandGrammar.comandoContextual("guardar", "editor"));
        assertEquals("guardar", Ej338VoiceCommandGrammar.comandoContextual("guardar", "")); // caso límite
    }

    @Test
    void retoExtra10_disparaEventoUi() {
        assertEquals("onOpen", Ej338VoiceCommandGrammar.disparaEventoUi(new Intencion("abrir", Map.of())));
        assertEquals("", Ej338VoiceCommandGrammar.disparaEventoUi(Intencion.desconocida())); // caso límite
    }
}
