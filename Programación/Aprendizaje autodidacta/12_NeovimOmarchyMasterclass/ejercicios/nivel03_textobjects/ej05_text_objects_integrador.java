// ============================================================
//  EJERCICIO 03.05 — Integrador text objects sobre código Java
//  Teoría:   teoria/03_Text_Objects_Y_Visual.md (TODA)
//  Verifica: bash scripts/verificar.sh 03 05
// ============================================================
//
// CHULETA (resumen Nivel 03):
//   ciw / caw    → cambia palabra
//   ci" / ca"    → cambia entre comillas dobles
//   ci( / ca(    → cambia entre paréntesis  (igual con i{ a{ i[ a[)
//   vi{ / va{    → selecciona en Visual el cuerpo de un bloque
//   yip / dap    → copia/borra párrafo entero
//   Ctrl-V       → Visual bloque (edición rectangular)
//   gUiw / guiw  → palabra a MAYÚSCULAS / minúsculas
//   =i{          → re-indenta el contenido de un bloque
//
// TODOS:
//
// TODO 1 (con pista): Cambia el string "Hola" en el método saludar()
//   por "Adios". Pista: navega a esa línea, ponte EN cualquier punto
//   entre las comillas y pulsa 'ci"'.
//
// TODO 2 (con pista): En el método sumar(), cambia los parámetros
//   "(int a, int b)" para que queden "(int x, int y)". Pista:
//   ponte EN cualquier punto entre los paréntesis y pulsa 'ci(',
//   escribe "int x, int y", Esc.
//
// TODO 3 (con pista): En el método cuerpoMixto(), borra TODO el cuerpo
//   entre llaves (las llaves quedan, el contenido se va).
//   Pista: ponte EN cualquier línea dentro de las llaves y pulsa 'di{'.
//
// TODO 4 (LIBRE): Convierte el nombre del método "metodoEnMinuscula"
//   a TODO MAYÚSCULAS dejando el resto de la línea intacto. Resultado:
//   "public void METODOENMINUSCULA() {". Pista: 'gUiw' sobre el nombre.
//
// TODO 5 (LIBRE): En el bloque COMENTAR_BLOQUE (las 4 líneas debajo),
//   añade al INICIO de cada línea el prefijo "// " usando Visual bloque.
//
// TODO 6 (LIBRE): Guarda y sal.

public class Ej05 {

    public String saludar() {
        return "Hola";
    }

    public int sumar(int a, int b) {
        return a + b;
    }

    public void cuerpoMixto() {
        System.out.println("borrame 1");
        System.out.println("borrame 2");
        int x = 42;
    }

    public void metodoEnMinuscula() {
        // este método mantiene su cuerpo
        System.out.println("intacto");
    }

    // COMENTAR_BLOQUE
    statement uno();
    statement dos();
    statement tres();
    statement cuatro();
}
