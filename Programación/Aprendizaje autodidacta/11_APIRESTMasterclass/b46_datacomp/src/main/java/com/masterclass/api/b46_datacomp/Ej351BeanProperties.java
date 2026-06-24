package com.masterclass.api.b46_datacomp;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 351 · JavaBean: propiedades, convención get/set/is e introspección.
 *
 * <p>Teoría: {@code teoria/46_Componentes_Datos.md} (sección 1).
 *
 * <p>Un componente reutilizable empieza por <em>autodescribirse</em>: exponer su estado como
 * <em>propiedades</em> que otra herramienta pueda descubrir SIN leer su código fuente. Eso es la
 * convención JavaBean: una propiedad {@code x} existe si hay un getter {@code getX()} (o
 * {@code isX()} si es booleana) y, si es de lectura/escritura, un setter {@code setX(...)}.
 * Descubrirlas por reflexión es lo que hacen Spring, Jackson o un editor visual de formularios.
 */
public final class Ej351BeanProperties {

    private Ej351BeanProperties() {
    }

    /**
     * Descubre por reflexión los nombres de las propiedades de LECTURA/ESCRITURA de una clase
     * (las que tienen getter <em>y</em> setter emparejados).
     *
     * @param clase la clase a inspeccionar
     * @return lista ordenada alfabéticamente de nombres de propiedad; {@code List.of()} si la
     *         clase es null o no tiene propiedades
     */
    public static List<String> propiedadesDe(Class<?> clase) {
        // TODO 1: si clase es null, devuelve List.of().
        // TODO 2: recorre clase.getMethods() (incluye los heredados: así salen las de la superclase).
        // TODO 3: quédate solo con métodos sin parámetros y que NO devuelvan void (candidatos a getter).
        // TODO 4: filtra los getter: nombre empieza por "get" (y no es "getClass") o por "is"
        //         (en ese caso debe devolver boolean/Boolean).
        // TODO 5: deriva el nombre de propiedad quitando el prefijo y poniendo en minúscula la
        //         primera letra (getNombre -> "nombre", isActivo -> "activo").
        // TODO 6: acepta la propiedad SOLO si existe un setter "set"+Cap con un único parámetro.
        //         Acumula los nombres en una lista, ordénala y devuélvela (return).
        return List.of();
    }

    /**
     * Lee el valor de una propiedad de un bean por su nombre, usando reflexión.
     *
     * @param bean   instancia a leer
     * @param nombre nombre de la propiedad (p.ej. {@code "nombre"} o {@code "activo"})
     * @return el valor de la propiedad, o {@code null} si el bean es null o la propiedad no existe
     */
    public static Object leerPropiedad(Object bean, String nombre) {
        // TODO 7: si bean o nombre son null, devuelve null.
        // TODO 8: construye el nombre del getter: "get" + nombre con la primera en mayúscula;
        //         si ese método no existe, prueba "is" + Cap (para las propiedades booleanas).
        // TODO 9: invoca el getter por reflexión sobre el bean y captura su valor.
        // TODO 10: si la propiedad no existe (NoSuchMethodException) o falla la invocación,
        //          devuelve null en lugar de propagar la excepción; si todo va bien, devuelve el valor.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(propiedadesDe(PersonaBean.class));          // [activo, edad, nombre]
        PersonaBean p = new PersonaBean("Ada", 36, true);
        System.out.println(leerPropiedad(p, "nombre"));                // Ada
        System.out.println(leerPropiedad(p, "activo"));                // true
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: deriva el nombre de propiedad a partir del nombre de un getter.
     */
    public static String nombreDePropiedadDesdeGetter(String nombreMetodo) {
        // GUÍA: teoría 1 (convención de nombres). "getNombre" -> "nombre", "isActivo" -> "activo".
        // 1. null/blank -> "".
        // 2. quita el prefijo "get" o "is" (cuidado: "is" son 2 letras, "get" son 3).
        // 3. pon en minúscula la primera letra de lo que queda.
        // PISTA: String resto = m.startsWith("is") ? m.substring(2) : m.substring(3);
        //        return Character.toLowerCase(resto.charAt(0)) + resto.substring(1);
        // OJO: el test pasa "getNombre" -> "nombre" e "isActivo" -> "activo". No confundas el
        //   corte de "is" (2) con el de "get" (3) o sacarás la letra equivocada.
        // CULTURA: esto es exactamente lo que hace Jackson (b02) al mapear un JSON {"nombre":...}
        //   a la propiedad de un DTO (b07).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreDePropiedadDesdeGetter");
    }

    /**
     * Reto Extra 2: construye el nombre del getter a partir del nombre de propiedad (operación inversa).
     */
    public static String nombreDeGetter(String propiedad, boolean esBooleana) {
        // GUÍA: teoría 1. "nombre",false -> "getNombre"; "activo",true -> "isActivo".
        // 1. pon en mayúscula la primera letra de la propiedad.
        // 2. antepón "is" si es booleana, "get" si no.
        // PISTA: String cap = Character.toUpperCase(p.charAt(0)) + p.substring(1);
        // OJO: el test comprueba ambos casos; el booleano usa "is", no "get".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreDeGetter");
    }

    /**
     * Reto Extra 3: indica si un nombre de método respeta la convención de getter de JavaBean.
     */
    public static boolean esGetterDeJavaBean(String nombreMetodo) {
        // GUÍA: teoría 1. Un getter válido empieza por "get" o "is" y tiene AL MENOS una letra
        //   más después del prefijo (así "get" o "is" a secas NO valen).
        // 1. null -> false.
        // 2. ("get" con longitud > 3) o ("is" con longitud > 2) -> true; resto -> false.
        // PISTA: (m.startsWith("get") && m.length() > 3) || (m.startsWith("is") && m.length() > 2)
        // OJO: el test exige que "calcular" sea false y "get" (sin nada detrás) también false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esGetterDeJavaBean");
    }

    /**
     * Reto Extra 4: indica si una propiedad es de SOLO LECTURA (tiene getter pero no setter).
     */
    public static boolean esPropiedadDeSoloLectura(Class<?> clase, String propiedad) {
        // GUÍA: teoría 1 (propiedades read-only, como 'descripcion' de PersonaBean).
        // 1. comprueba que EXISTE el getter (get+Cap o is+Cap) en la clase.
        // 2. comprueba que NO existe un setter "set"+Cap con un parámetro.
        // 3. solo-lectura = hay getter Y no hay setter.
        // PISTA: usa try/catch con clase.getMethod(...) o recorre getMethods() buscando los nombres.
        // OJO: el test pide esPropiedadDeSoloLectura(PersonaBean.class,"descripcion") -> true y
        //   sobre "nombre" -> false (nombre sí tiene setter).
        // CULTURA: una propiedad calculada de solo lectura es como un campo derivado de un record
        //   (b01) o un DTO de respuesta que no se puede modificar desde fuera (b07).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPropiedadDeSoloLectura");
    }

    /**
     * Reto Extra 5: cuenta cuántas propiedades de lectura/escritura tiene una clase.
     */
    public static int contarPropiedades(Class<?> clase) {
        // GUÍA: reutiliza propiedadesDe (composición: no repitas la reflexión).
        // 1. null -> 0.
        // 2. devuelve el tamaño de propiedadesDe(clase).
        // PISTA: return propiedadesDe(clase).size();
        // OJO: el test comprueba contarPropiedades(PersonaBean.class) == 3 (nombre, edad, activo;
        //   'descripcion' NO cuenta porque es de solo lectura).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarPropiedades");
    }

    /**
     * Reto Extra 6: devuelve el tipo declarado de una propiedad (el tipo de retorno de su getter).
     */
    public static Class<?> tipoDePropiedad(Class<?> clase, String propiedad) {
        // GUÍA: teoría 1 (cada propiedad tiene un tipo: el del getter).
        // 1. localiza el getter (get+Cap o is+Cap).
        // 2. devuelve su getReturnType(); si no existe la propiedad, devuelve null.
        // PISTA: Method g = clase.getMethod("get" + cap); return g.getReturnType();
        // OJO: el test comprueba tipoDePropiedad(PersonaBean.class,"edad") == int.class (el
        //   primitivo, no Integer.class) y sobre "activo" -> boolean.class (vía isActivo).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tipoDePropiedad");
    }

    /**
     * Reto Extra 7: copia el valor de UNA propiedad de un bean origen a un bean destino.
     */
    public static Object copiarPropiedad(Object origen, Object destino, String propiedad) {
        // GUÍA: teoría 1 (leer con getter, escribir con setter). Patrón "mapeador de beans".
        // 1. lee el valor de 'origen' con leerPropiedad (reutiliza el core).
        // 2. localiza el setter "set"+Cap en la clase de 'destino' (un único parámetro).
        // 3. invócalo con el valor leído y devuelve 'destino'.
        // PISTA: Method set = destino.getClass().getMethod("set"+cap, paramType); set.invoke(destino, valor);
        // OJO: el test copia "nombre" de una PersonaBean a otra y comprueba que el destino quedó
        //   con ese nombre. Devuelve el PROPIO destino (para poder encadenar).
        // CULTURA: copiar propiedades por nombre es lo que hace BeanUtils/ModelMapper al pasar de
        //   Entity a DTO (b07) sin escribir el mapeo a mano.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para copiarPropiedad");
    }

    /**
     * Reto Extra 8: indica si una clase incluye una propiedad concreta, contando las HEREDADAS.
     */
    public static boolean incluyePropiedadHeredada(Class<?> clase, String propiedad) {
        // GUÍA: teoría 1 (getMethods() ya trae los métodos heredados, por eso EmpleadoBean ve
        //   'nombre' de PersonaBean).
        // 1. reutiliza propiedadesDe(clase) y comprueba si contiene la propiedad.
        // PISTA: return propiedadesDe(clase).contains(propiedad);
        // OJO: el test usa incluyePropiedadHeredada(EmpleadoBean.class,"nombre") -> true
        //   (heredada) y "salario" -> true (propia). Si usaras getDeclaredMethods() perderías
        //   las heredadas: la clave es que el core use getMethods().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para incluyePropiedadHeredada");
    }

    /**
     * Reto Extra 9: valida que una clase cumpla la convención mínima de JavaBean.
     */
    public static boolean cumpleConvencionJavaBean(Class<?> clase) {
        // GUÍA: teoría 1 (requisitos de un JavaBean). Mínimo: constructor PÚBLICO sin argumentos
        //   y al menos una propiedad de lectura/escritura.
        // 1. comprueba que clase.getConstructor() (sin parámetros) existe y es público.
        // 2. comprueba que propiedadesDe(clase) no está vacía.
        // PISTA: try { clase.getConstructor(); } catch (NoSuchMethodException e) { return false; }
        // OJO: el test exige cumpleConvencionJavaBean(PersonaBean.class) -> true. getConstructor()
        //   (sin args) ya filtra por público; getDeclaredConstructor() no lo haría.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cumpleConvencionJavaBean");
    }

    /**
     * Reto Extra 10: descubre las propiedades usando la API oficial {@link java.beans.Introspector}.
     */
    public static List<String> propiedadesConIntrospector(Class<?> clase) {
        // GUÍA: teoría 1 (la forma "profesional": el JDK trae un introspector de beans).
        // 1. BeanInfo info = Introspector.getBeanInfo(clase);
        // 2. recorre info.getPropertyDescriptors() y recoge getName(), DESCARTANDO "class"
        //    (Introspector siempre inventa una pseudo-propiedad 'class' por getClass()).
        // 3. ordena y devuelve.
        // PISTA: for (PropertyDescriptor pd : info.getPropertyDescriptors()) { ... pd.getName() ... }
        // OJO: a diferencia de tu core, Introspector SÍ incluye las propiedades de solo lectura;
        //   el test comprueba que el resultado de PersonaBean CONTIENE "descripcion" (que
        //   propiedadesDe NO traía). Esa es justo la diferencia que el reto te hace ver.
        // CULTURA: Introspector + PropertyDescriptor es la maquinaria que usaban los editores
        //   visuales de Swing y, por debajo, muchos frameworks de binding.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para propiedadesConIntrospector");
    }
}
