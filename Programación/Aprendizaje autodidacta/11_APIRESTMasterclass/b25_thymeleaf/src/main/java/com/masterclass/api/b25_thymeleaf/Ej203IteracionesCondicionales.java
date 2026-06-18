package com.masterclass.api.b25_thymeleaf;

/**
 * Ejercicio 203 · Líneas de albarán con iteraciones y lógicas.
 *
 * <p>Teoría: {@code teoria/25_Plantillas_Dinamicas_Thymeleaf.md} (sección 25.3)
 *
 * <p>Uso de th:each (con variable de estado), th:if/th:unless y th:classappend
 * para construir tablas dinámicas a partir de una colección de líneas.
 */
public final class Ej203IteracionesCondicionales {

    private Ej203IteracionesCondicionales() {
    }

    public static void paso01CrearColeccionLineas() {
        // GUÍA: teoría 25.3. Modela la línea como record y crea la lista:
        //   record LineaAlbaranDTO(String concepto, int cantidad, double precioUd, double subtotal) {}
        //   var lineas = List.of(new LineaAlbaranDTO("Tornillos", 50, 1.5, 75.0), ...);
        // PISTA: List.of(...) crea la colección inmutable; mete al menos una línea
        // con cantidad 0 para probar la clase 'agotado' del paso 8.
        // OJO: el subtotal ya viene CALCULADO en el DTO (cantidad*precioUd); ver
        // paso 5: la vista no hace matemáticas.
    }

    public static void paso02BucleThEach() {
        // GUÍA: teoría 25.3. Itera la colección clonando la <tr> por elemento:
        //   <tr th:each="linea : ${albaran.lineas}"> ... </tr>
        // PISTA: th:each="elemento : ${coleccion}" — léelo como un for-each de Java.
        // OJO: el ${albaran.lineas} exige que metas un 'albaran' en el contexto
        // con un getter/accesor lineas() que devuelva la lista del paso 1.
    }

    public static void paso03UsoIterStat() {
        // GUÍA: teoría 25.3, tabla de la variable de estado. Declara un segundo
        // nombre para enumerar las filas:
        //   <tr th:each="linea, stat : ${albaran.lineas}">
        //     <td th:text="${stat.index + 1}">1</td>
        // PISTA: stat.index es base 0 (suma 1 para mostrar); stat.count es base 1.
        // CULTURA: stat.first/last/even/odd sirven para cabeceras especiales o
        // filas cebra sin contadores manuales.
    }

    public static void paso04RellenarCeldas() {
        // GUÍA: teoría 25.3. Dentro de la <tr>, un th:text por columna:
        //   <td th:text="${linea.concepto}">Tornillos</td>
        //   <td th:text="${linea.cantidad}">50</td>
        // PISTA: 'linea' es la variable del th:each; accedes a sus campos con punto.
        // Para precio/subtotal reutiliza #numbers.formatDecimal de la teoría 25.2.
    }

    public static void paso05EvitarLogicaEnVista() {
        // GUÍA: teoría 25.3, "Regla de oro: la vista no calcula" y error común nº 5.
        // El subtotal, el IVA y el total se calculan en el DTO o el Service, NO en
        // la plantilla con ${linea.cantidad * linea.precioUd}.
        // OJO/CUIDADO: aritmética en el HTML = lógica no testeable y bugs que solo
        // ves al imprimir. La plantilla solo MUESTRA datos ya calculados.
        // PISTA: si necesitas el subtotal, que el record lo exponga ya hecho
        // (campo subtotal o método subtotal() derivado, como en b1.1).
    }

    public static void paso06CondicionalThIf() {
        // GUÍA: teoría 25.3. Muestra un bloque solo si la condición es cierta:
        //   <div th:if="${albaran.tieneDescuento}">Descuento aplicado</div>
        // OJO/CUIDADO: th:if ELIMINA la etiqueta del HTML si es falso (no la oculta
        // con CSS). Si luego cuentas etiquetas en el resultado (paso 9), las no
        // renderizadas no aparecen — es justo lo que valida el paso 10.
    }

    public static void paso07CondicionalThUnless() {
        // GUÍA: teoría 25.3. th:unless es el inverso exacto de th:if, más legible
        // que th:if="${!cond}":
        //   <div th:unless="${albaran.tieneDescuento}">Sin descuento aplicado</div>
        // PISTA: usa el MISMO booleano que el paso 6; if y unless sobre la misma
        // condición son mutuamente excluyentes: siempre se ve uno y solo uno.
    }

    public static void paso08ClasesCssDinamicas() {
        // GUÍA: teoría 25.3. Añade una clase condicional SIN borrar las fijas:
        //   <tr class="fila" th:classappend="${linea.cantidad == 0 ? 'agotado' : ''}">
        // PISTA: th:classappend AÑADE a class=""; th:class la REEMPLAZA (perderías
        // 'fila'). El operador ternario de Thymeleaf es como el de Java.
        // OJO: para cantidad 0, la clase 'agotado' pinta la fila (rojo en tu CSS);
        // para el resto, cadena vacía → ninguna clase extra.
    }

    public static void paso09RenderizadoDeTabla() {
        // GUÍA: teoría 25.3. Procesa el template y cuenta las <tr> generadas:
        //   String html = engine.process("albaran_test", ctx);
        //   long filas = html.split("<tr").length - 1;   // truco simple de conteo
        // PISTA: cada elemento de la lista produce una <tr> de cuerpo; suma la fila
        // de cabecera (<thead>) si tu plantilla la tiene.
        // OJO: si usaste th:if en alguna fila (paso 6), las ocultas NO cuentan.
    }

    public static void paso10ValidacionEstructural() {
        // GUÍA: teoría 25.3. Devuelve un boolean: ¿la tabla generó exactamente las
        // filas esperadas?
        //   return filasContadas == albaran.lineas().size() (+1 si cuentas cabecera);
        // PISTA: compara el conteo del paso 9 con el tamaño de la lista del paso 1.
        // CULTURA: validar la ESTRUCTURA del HTML renderizado es la base de los
        // tests de plantillas que harías de verdad en b19.
    }

    public static boolean ejecutar() {
        // Llama a todos los pasos para comprobar la ejecución
        paso01CrearColeccionLineas();
        paso02BucleThEach();
        paso03UsoIterStat();
        paso04RellenarCeldas();
        paso05EvitarLogicaEnVista();
        paso06CondicionalThIf();
        paso07CondicionalThUnless();
        paso08ClasesCssDinamicas();
        paso09RenderizadoDeTabla();
        paso10ValidacionEstructural();
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Validando los 10 pasos: " + ejecutar());
    }
}
