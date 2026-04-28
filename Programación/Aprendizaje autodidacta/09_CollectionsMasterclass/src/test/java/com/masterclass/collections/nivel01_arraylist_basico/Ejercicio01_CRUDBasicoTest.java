package com.masterclass.collections.nivel01_arraylist_basico;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ejercicio 01 — CRUD Básico ArrayList")
class Ejercicio01_CRUDBasicoTest {

    private ArrayList<String> lista;

    @BeforeEach
    void setUp() {
        lista = Ejercicio01_CRUDBasico.crearLista("Manzana", "Banana", "Cereza");
    }

    // ── crearLista ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("crearLista() retorna una lista no nula con los elementos en orden")
    void crearLista_retornaOrdenCorrecto() {
        assertThat(lista)
                .isNotNull()
                .containsExactly("Manzana", "Banana", "Cereza");
    }

    @Test
    @DisplayName("crearLista() con varargs vacío retorna lista vacía")
    void crearLista_sinElementos_retornaListaVacia() {
        ArrayList<String> vacia = Ejercicio01_CRUDBasico.crearLista();
        assertThat(vacia).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("crearLista() retorna un ArrayList (no otra implementación de List)")
    void crearLista_esArrayList() {
        assertThat(lista).isInstanceOf(ArrayList.class);
    }

    // ── agregarAlInicio ─────────────────────────────────────────────────────

    @Test
    @DisplayName("agregarAlInicio() inserta el elemento en la posición 0")
    void agregarAlInicio_elementoEnPosicionCero() {
        Ejercicio01_CRUDBasico.agregarAlInicio(lista, "Aguacate");
        assertThat(lista.get(0)).isEqualTo("Aguacate");
    }

    @Test
    @DisplayName("agregarAlInicio() incrementa el tamaño en 1 y preserva el resto")
    void agregarAlInicio_tamanoCorrecto() {
        Ejercicio01_CRUDBasico.agregarAlInicio(lista, "Aguacate");
        assertThat(lista)
                .hasSize(4)
                .containsExactly("Aguacate", "Manzana", "Banana", "Cereza");
    }

    // ── eliminarPorIndice ───────────────────────────────────────────────────

    @Test
    @DisplayName("eliminarPorIndice() retorna el elemento eliminado")
    void eliminarPorIndice_retornaElemento() {
        String resultado = Ejercicio01_CRUDBasico.eliminarPorIndice(lista, 1);
        assertThat(resultado).isEqualTo("Banana");
    }

    @Test
    @DisplayName("eliminarPorIndice() reduce el tamaño y ajusta los índices")
    void eliminarPorIndice_ajustaLista() {
        Ejercicio01_CRUDBasico.eliminarPorIndice(lista, 0);
        assertThat(lista).containsExactly("Banana", "Cereza");
    }

    @Test
    @DisplayName("eliminarPorIndice() lanza IndexOutOfBoundsException con índice inválido")
    void eliminarPorIndice_indiceInvalido_lanzaExcepcion() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> Ejercicio01_CRUDBasico.eliminarPorIndice(lista, 99));
    }

    // ── actualizarElemento ──────────────────────────────────────────────────

    @Test
    @DisplayName("actualizarElemento() sustituye el valor en el índice dado")
    void actualizarElemento_sustituyeValor() {
        Ejercicio01_CRUDBasico.actualizarElemento(lista, 1, "BANANA_NUEVA");
        assertThat(lista.get(1)).isEqualTo("BANANA_NUEVA");
    }

    @Test
    @DisplayName("actualizarElemento() no cambia el tamaño de la lista")
    void actualizarElemento_mantieneTamano() {
        int tamanoAntes = lista.size();
        Ejercicio01_CRUDBasico.actualizarElemento(lista, 0, "Nuevo");
        assertThat(lista).hasSize(tamanoAntes);
    }

    // ── contiene ────────────────────────────────────────────────────────────

    @Test
    @DisplayName("contiene() retorna true para elementos presentes")
    void contiene_elementoPresente_retornaTrue() {
        assertThat(Ejercicio01_CRUDBasico.contiene(lista, "Banana")).isTrue();
    }

    @Test
    @DisplayName("contiene() retorna false para elementos ausentes")
    void contiene_elementoAusente_retornaFalse() {
        assertThat(Ejercicio01_CRUDBasico.contiene(lista, "Kiwi")).isFalse();
    }

    // ── tamano ──────────────────────────────────────────────────────────────

    @Test
    @DisplayName("tamano() retorna el número correcto de elementos")
    void tamano_retornaCorrecto() {
        assertThat(Ejercicio01_CRUDBasico.tamano(lista)).isEqualTo(3);
    }

    @Test
    @DisplayName("tamano() retorna 0 para lista vacía")
    void tamano_listaVacia_retornaCero() {
        assertThat(Ejercicio01_CRUDBasico.tamano(new ArrayList<>())).isEqualTo(0);
    }

    // ── vaciarLista ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("vaciarLista() deja la lista con 0 elementos")
    void vaciarLista_listaQuedarVacia() {
        Ejercicio01_CRUDBasico.vaciarLista(lista);
        assertThat(lista).isEmpty();
    }

    @Test
    @DisplayName("vaciarLista() sobre lista vacía no lanza excepción")
    void vaciarLista_listaYaVacia_sinExcepcion() {
        ArrayList<String> vacia = new ArrayList<>();
        assertThatNoException().isThrownBy(() -> Ejercicio01_CRUDBasico.vaciarLista(vacia));
        assertThat(vacia).isEmpty();
    }
}
