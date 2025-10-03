package UT02;
/**
 * Ejercicio 3.
 * Crea un programa que saque por pantalla las calificaciones obtenidas
 * en 4 asignaturas, y debe indicar de alguna forma cual de ellas tiene
 * la mayor calificación.
 */
public class e3e3 {
    public static void main(String[] args){
        System.out.println("--- Ejemplo 3 - Ejercicio 3 ---");
        int mate = 5, lengua = 6, ingles = 7, musica = 9;
        boolean bMate, bLengua, bIngles, bMusica;
        bMate = (mate > lengua) && (mate > ingles) && (mate > musica);
        bLengua = (lengua > mate) && (lengua > ingles) && (lengua > musica);
        bIngles = (ingles > lengua) && (ingles > mate) && (ingles > musica);
        bMusica = (musica > lengua) && (musica > ingles) && (musica > mate);
        System.out.println("Nota de Matemáticas: " + mate + ". Es la mejor nota: " + bMate);
        System.out.println("Nota de Lengua: " + lengua + ". Es la mejor nota: " + bLengua);
        System.out.println("Nota de Inglés: " + ingles + ". Es la mejor nota: " + bIngles);
        System.out.println("Nota de Música: " + musica + ". Es la mejor nota: " + bMusica);
    }
}
