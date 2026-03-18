# 📚 Manual de Collections en Java

> Autor original: **Pedro Corcuera**  
> Dpto. Matemática Aplicada y Ciencias de la Computación  
> Universidad de Cantabria  
> corcuerp@unican.es

---

## 📑 Tabla de Contenidos Estructurada

1. [1. Introducción a las Colecciones](#1-introducción-a-las-colecciones)
    - [Objetivos](#objetivos)
    - [¿Qué es una colección?](#qué-es-una-colección)
    - [¿Qué es una estructura Collection?](#qué-es-una-estructura-collection)
    - [Beneficios de la estructura Collection](#beneficios-de-la-estructura-collection)
2. [2. Arquitectura de Interfaces](#2-arquitectura-de-interfaces)
    - [Interfaces de la estructura Collection](#interfaces-de-la-estructura-collection)
    - [Núcleo de Interfaces Collection](#núcleo-de-interfaces-collection)
    - [La Interface Collection General](#la-interface-collection-general)
    - [Otras interfaces del Core Collection](#otras-interfaces-del-core-collection)
3. [3. Implementaciones](#3-implementaciones)
    - [Concepto de Implementación](#concepto-de-implementación)
    - [Implementaciones de propósito general](#implementaciones-de-propósito-general)
4. [4. Operaciones, Métodos y Recorridos](#4-operaciones-métodos-y-recorridos)
    - [Recorrido de colecciones](#recorrido-de-colecciones-dos-formas)
    - [Operaciones masivas](#operaciones-masivas)
    - [Operaciones array](#operaciones-array)
    - [Métodos específicos por Interface](#métodos-específicos-por-interface)
5. [5. Algoritmos de Collections](#5-algoritmos-de-collections)
    - [5.1 Ordenamiento (Sorting)](#51-ordenamiento-sorting)
    - [5.2 Barajado (Shuffling)](#52-barajado-shuffling)
    - [5.3 Manipulación de datos de rutina](#53-manipulación-de-datos-de-rutina)
    - [5.4 Búsqueda (Searching)](#54-búsqueda-searching)
    - [5.5 Composición y Valores Extremos](#55-composición-y-valores-extremos)
6. [6. Ejemplos Prácticos Completos y Casos de Uso](#6-ejemplos-prácticos-completos-y-casos-de-uso)

---

## 1. Introducción a las Colecciones

### Objetivos
* **Conocer y justificar** el uso de las clases Collection.
* **Aprender** a usar colecciones de forma efectiva.

### ¿Qué es una colección?
* Un objeto "collection", también llamado contenedor, es un objeto que agrupa múltiples elementos en una sola unidad.
* Las colecciones se usan para **almacenar, recuperar, manipular y comunicar** datos agregados.
  * Típicamente, representan datos que forman un grupo natural. Por ejemplo: colección de naipes, colección de cartas, lista de nombres y números (guía telefónica).

### ¿Qué es una estructura Collection?
* Una estructura collection es una arquitectura unificada para representar y manipular colecciones.
* Todas las estructuras de colecciones contienen los siguientes tres pilares fundamentales:
  1. **Interfaces**
  2. **Implementaciones**
  3. **Algoritmos**

### Beneficios de la estructura Collection

| Beneficio | Descripción |
| :--- | :--- |
| **Ahorro de Esfuerzo** | Reduce drásticamente el esfuerzo de programación. |
| **Rendimiento** | Incrementa la velocidad y calidad general de un programa. |
| **Interoperabilidad** | Permite interoperabilidad entre APIs que no están directamente relacionadas. |
| **Curva de Aprendizaje** | Reduce el esfuerzo requerido para aprender y usar nuevas APIs. |
| **Diseño API** | Reduce el esfuerzo necesario para diseñar nuevas APIs. |
| **Reutilización** | Fomenta altamente la reutilización de software. |

---

## 2. Arquitectura de Interfaces

### Interfaces de la estructura Collection
* Las interfaces Collection son tipos de datos abstractos que representan colecciones.
* Las interfaces permiten a las colecciones ser manipuladas independientemente de los detalles de representación (comportamiento polimórfico).
* En Java, y otros lenguajes orientados a objetos, las interfaces forman una jerarquía:
  * El programador selecciona la interfaz que mejor cumple las necesidades como tipo.

### Núcleo de Interfaces Collection
* El núcleo de interfaces Collection encapsula diferentes tipos de colecciones, permitiendo manipulación independiente de su representación física.

**Jerarquía del Núcleo:**
* **`Collection`**
  * `Set` → `SortedSet`
  * `List`
  * `Queue`
* **`Map`**
  * `SortedMap`

* **Generics:** Todas las colecciones son genéricas.   
  *Ejemplo de declaración:* `public interface Collection<E>...` donde `E` es el tipo de objeto contenido en la colección.
* La interface abstracta `Collection` proporciona funcionalidad común básica (métodos como `add` y `remove`).
* La interface separada `Map` crea una correspondencia explícita entre **llaves** (keys) y **valores** (values).

### La Interface Collection General
* Es la raíz de la jerarquía de colecciones principal.
* Actúa como denominador común que todas las colecciones principales implementan.
* Se usa principalmente para pasar colecciones de objetos como elementos y poder manipularlos unificadamente.

```java
public interface Collection<E> extends Iterable<E> {
    // Operaciones Básicas
    int size();
    boolean isEmpty();
    boolean contains(Object element);
    boolean add(E element); // opcional
    boolean remove(Object element); // opcional
    Iterator<E> iterator();
    
    // Operaciones Masivas (Bulk)
    boolean containsAll(Collection<?> c);
    boolean addAll(Collection<? extends E> c); // opcional
    boolean removeAll(Collection<?> c); // opcional
    boolean retainAll(Collection<?> c); // opcional
    void clear(); // opcional
    
    // Operaciones Array
    Object[] toArray();
    <T> T[] toArray(T[] a);
}
```

### Otras interfaces del Core Collection

| Interface | Propiedades y Casos de Uso |
| :--- | :--- |
| **`Set`** | Colección que **no puede contener elementos duplicados**. Modela los conjuntos matemáticos estándar. |
| **`List`** | Colección ordenada cronológicamente (secuencia) que **sí puede contener duplicados**. Ofrece control preciso sobre dónde se inserta cada elemento (por índice entero). |
| **`Queue`** | Contiene múltiples elementos previo a su proceso. Incluye operaciones adicionales de inspección. Típicamente funciona como una lista **FIFO** (First-In, First-Out). |
| **`Map`** | Proyecta llaves hacia valores. **No admite llaves duplicadas**, cada llave proyecta a un solo valor. |
| **`SortedSet`** | Versión ordenada de `Set` que mantiene automáticamente sus valores en orden ascendente (operaciones adicionales disponibles). |
| **`SortedMap`** | Versión ordenada de `Map` que ordena sus elementos ascendiendo siempre base a la Llave (Key). |

---

## 3. Implementaciones

### Concepto de Implementación
* Son las implementaciones concretas de las interfaces (objetos de datos usados para almacenar o manejar los datos).
* Existen diversos tipos de implementaciones según necesidades:
  * Implementaciones de propósito general.
  * Implementaciones de propósito especial.
  * Implementaciones concurrentes.
  * Implementaciones envolventes.
  * Implementaciones de conveniencia.
  * Implementaciones abstractas.

### Implementaciones de propósito general
El Framework Collection ofrece estas implementaciones principales para uso común:

| Interface Base | Hash table (Mejor acceso disperso) | Resizable array (Basado índices) | Tree (Mantiene Orden) | Linked list (Inserción rápida bordes) | Hash table + Linked list (Mantiene orden inserción local) |
| --- | --- | --- | --- | --- | --- |
| **`Set`** | `HashSet` (La más usada) | | `TreeSet` | | `LinkedHashSet` |
| **`List`** | | `ArrayList` (La más usada) | | `LinkedList` | |
| **`Queue`** | | | | `LinkedList` (La más usada) | |
| **`Map`** | `HashMap` (La más usada) | | `TreeMap` | | `LinkedHashMap` |

---

## 4. Operaciones, Métodos y Recorridos

### Recorrido de colecciones: dos formas

1. **Ciclo `for-each`**
   * Sintaxis similar a un `for` estándar, pero sin indexación directa del cursor. Muy sencillo.
   ```java
   for (Object o : collection) {
       System.out.println(o);
   }
   ```

2. **Uso de `Iterator`**
   * Objeto que no sólo permite recorrer, sino **eliminar** elementos de la colección de forma segura durante iteraciones.
   ```java
   public interface Iterator<E> {
       boolean hasNext();
       E next();
       void remove(); // opcional
   }
   ```
   * *Ejemplo Práctico para Filtras:* Útil cuando iteramos en múltiples colecciones o debemos purgar datos condicionalmente.
   ```java
   static void filter(Collection<?> c) {
       for (Iterator<?> it = c.iterator(); it.hasNext();) {
           if (!cond(it.next())) {
               it.remove();
           }
       }
   }
   ```

### Operaciones masivas
Son aquellas que operan mutando/inspeccionando masivamente los componentes de toda la colección interactuando a veces con otra:

| Método Masivo | Comportamiento |
| :--- | :--- |
| `containsAll(c)` | Retorna `true` si dentro existen **todos** los elementos especificados en `c`. |
| `addAll(c)` | Añade incondicionalmente a mi colección **todos** los elementos de `c`. |
| `removeAll(c)` | Purga de mi colección todo elemento que se identifique como repetido/existente en `c`. |
| `retainAll(c)` | (Intersección) Retiene localmente sólo lo que **también esté** en la otra colección `c`. Elimina el resto. |
| `clear()` | Resetea/vacía la colección iterada completamente. |

### Operaciones array
Actúan como interfaces puente legadas entre "collections" y APIs antiguas atadas al concepto estático "arrays[]":

* Volcado en arreglo de tipo crudo genérico:
  ```java
  Object[] a = c.toArray();
  ```
* Volcado a un tipo referencial concreto (ej: arreglo Strings):
  ```java
  String[] a = c.toArray(new String[0]);
  ```

### Métodos específicos por Interface

#### Operadores Interface `List`

| Firma de la acción | Resumen de Acción |
| :--- | :--- |
| `add(i, o)` | Inserta objeto `o` en una posición índice `i`. |
| `add(o)` | Añade (apila) un objeto `o` al mismísimo final. |
| `get(i)` | Accede y lee al `i-ésimo` elemento. |
| `remove(i)` | Remueve al elemento alojado dentro de `i`. |
| `remove(o)` | Busca y remueve la ocurrencia del objeto `o`. |
| `set(i, o)` | Sobrescribe el contenido dentro del índice `i` sustituyéndolo con `o`. |

#### Operadores Interface `Map`

| Firma de la acción | Resumen de Acción |
| :--- | :--- |
| `clear()` | Depura/Borra todo mapeo activo. |
| `containsKey(k)` | Analiza si la llave `k` está mapeada o activamente guardada. |
| `containsValue(v)` | Rastrea la tabla buscando la pre-existencia del valor referenciado `v`. |
| `entrySet()` | Regresa conjunto visualizado como Pares Llave-Valor. |
| `get(k)` | Recupera el respectivo "Value" atado a un Key determinado ("k"). |
| `isEmpty()` | Booleano si mapa se encuentra vacío. |
| `keySet()` | Devuelve un `Set` representativo que incluye cada Llave (solo iterativo a llaves). |
| `put(k, v)` | Inserta/asocia proactivamente el Par `<Llave, Valor>` especificado localmente. |
| `remove(k)` | Purifica / borra mapeo donde encabeza localmente `k`. |
| `size()` | Total numeral global listando de todas las duplas ingresadas. |
| `values()` | Devuelve lista Collection global sin sus llaves conteniendo solo valores en sí mismos. |

---

## 5. Algoritmos de Collections

Los **algoritmos** son métodos utilitarios `static` listados dentro de la envoltura `java.util.Collections`. Están construidos para funcionar polimórficamente sin importar de dónde provienen.

### 5.1 Ordenamiento (Sorting)
El algoritmo `sort` reordena una `List` ascendiendo acuerdo a sus directrices internas. Puede sobreescribirse base directrices explícitas.
  * Orden de "Elemento Típico Natural".
  * Directriz explícita anexando clase `Comparator`.

#### Ordenamiento Natural (Ejemplo)
```java
import java.util.*;

public class Sort {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(args);
        Collections.sort(list);
        System.out.println(list);
    }
}
```
**Ejecución:**
> `java Sort uno dos tres cuatro cinco`  
> Result: `[cinco, cuatro, dos, tres, uno]`

#### Ejemplo: Ordenamiento por `Comparator` (Aplicación)
Ordenamiento condicional basado en logíca del código (ordenado según tamaño numeral de matriz/tamaños predefinidos).

```java
// ... fragmento interior main ...
// Construye lista con grupos de permutacion por umbral de tamaño
List<List<String>> winners = new ArrayList<List<String>>();
for (List<String> l : m.values()) {
    if (l.size() >= minGroupSize)
        winners.add(l);
}
        
// Ordena grupos de permutacion de acuerdo al propio tamaño (mayor a menor) usando COMPARATOR
Collections.sort(winners, new Comparator<List<String>>() {
    public int compare(List<String> o1, List<String> o2) {
        return o2.size() - o1.size();
    }
});

for (List<String> l : winners ) {
    System.out.println(l.size() + ": " + l);
}
```

### 5.2 Barajado (Shuffling)
Su lógica y aproximación son análogas inversas frente `sort()`, destrozando trazas de persistencia u orden temporal para introducir **Permutaciones Aleatorizadas Identificables**.
Aplica algoritmos garantizando misma y estricta probabilidad para mutaciones. Su forma inicial depende del factor numérico random default, o bien requiere instanciamiento objeto derivado localmente usando `Random`.

#### Ejemplo: Shuffling (Demostración)
```java
import java.util.*;

public class Shuffle {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(args);
        Collections.shuffle(list);
        System.out.println(list);
    }
}
```
**Ejecución (Aleatoria):**
> `java Shuffle uno dos tres cuatro cinco` -> `[cuatro, uno, tres, cinco, dos]`  
> `java Shuffle uno dos tres cuatro cinco` -> `[cuatro, dos, tres, uno, cinco]`

### 5.3 Manipulación de datos de rutina
Existen métodos que ofrecen mantenimientos "estructurales triviales" en el día a día para trabajar bajo elementos derivados:

| Procedimiento | Explicación Técnica |
| :--- | :--- |
| `reverse(List)` | Transpone o invierte totalmente al listado base sin reconfigurar datos ajenos explícitamente. |
| `fill(List, valor)` | Fuerza un rellenado ininterrumpido sobre-escribiendo sub-índices integrados sin contemplación alguna con un respectivo mismo valor prefabricado. |
| `copy(List_dest, List_src)`| Transfiere a un clon subyacente la colección madre al destino explícito. |
| `swap(List, i, j)` | Permuta / Intercambia dinámicamente dos elementos alojados entre rangos predesignados por índicación `i,j`. |
| `addAll(Col, e1,e2..)` | Interfaz estática simple insertando en un Collection `Col` matriz todas las entradas predefinidas contiguamente. |

### 5.4 Búsqueda (Searching)
La estrella para búsqueda es `binarySearch`. Debe emplearse sólo si la recolección fue pre-*ordenada* anteriormente.
* Se devuelve como factor int **el Índice**.
* ¿Retorna variable con factor negativo u oscuro `-1`? Significativamente significa elemento Inexistente o Evadido. 

#### Ejemplo: Búsqueda binaria (`binarySearch`)
```java
// [...] se asume código de ArrayList creado e import java.util.Collections [...]
arrayList.add("uno"); arrayList.add("dos"); arrayList.add("tres");
Collections.sort(arrayList); // Prerequisito crítico para Búsqueda

int index = Collections.binarySearch(arrayList, "dos");
System.out.println("Elemento encontrado en posicion: " + index);
```

### 5.5 Composición y Valores Extremos
* `Collections.frequency(l, objeto)`: Interesa cuando cuantificamos apariciones recurrentes de "elemento exacto" transcurriendo iteraciones de una recolección.
* `Collections.disjoint(l1, l2)`: Análisis matemático estricto buscando solapamientos mutuos o similitudes entrelazadas mutuas. Informa vía booleano (cierto al **No Existir Cruce / No poseer vínculos de cruce mutuo**).

**Valores Extremos**
* `Collections.max(Coleccion)` o `Collections.min(Coleccion)`
Extraen iterando al más sobresaliente bajo orden Natural. Adicionalmente permiten como segundo parámetro incluir variables referenciando la interfaz `Comparator`.

---

## 6. Ejemplos Prácticos Completos y Casos de Uso

A niveles avanzados, combinamos todo dentro del API para diseñar soluciones directas como lectura masiva o manipulación tipificada por recuento numérico.

### Ejemplo Ocurrencia Básica: Cuenta "Número de Palabras Totales / Distintas"

Ideal usando `HashSet` debido a su rechazo por palabras repetidas sin importar frecuencia.

```java
import java.util.*; 
import java.io.*; 

public class CountWords {
    static public void main(String[] args) {
        HashSet words = new HashSet(); 
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
        String delim = " \t\n.,:;?!-/()[]\"\'"; 
        String line; 
        int count = 0; 
        
        try {
            while ((line = in.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, delim); 
                while (st.hasMoreTokens()) {
                    count++; // conteo real global (absolutas)
                    words.add(st.nextToken().toLowerCase()); // set discriminal (diferentes)
                }
            } 
        } catch (IOException e) { e.printStackTrace(); } 
        
        System.out.println("Numero total de palabras: " + count); 
        System.out.println("Numero de palabras diferentes (unicas): " + words.size()); 
    }
}
```

### Ejemplo Múltiple: Conteo y Ocurrencias exactas para un Vocablo (Frecuencia Absoluta con `HashMap`)

El uso de mapeos permite crear la fórmula de emparejamiento `"Clave = Palabra" => "Valor = Nº Ocurrencias"`. Se basa en una clase interior `Count` encargada del iterador `i++`.  Este modelo iterará todo el sistema `System.in` para listar sin ningún tipo de orden la ocurrencia global de todo lo leído sin descartar pero agrupando exitosamente. *La estructura subyacente de `HashMap` no imprime bajo orden predeterminado en memoria.*

### El refinamiento (Misma Frecuencia Absoluta en `TreeMap` ordenado alfabéticamente)
El proceso y arquitectura de código del ejemplo con `HashMap` solo muta estructuralmente alterando la cabecera `Map palabras` obligando desde el minuto cero a declararlo `TreeMap`. 
* *Efecto Deseado:* Las iteraciones usando su lectura vía `set.iterator()` o `.entrySet` estarán estrictamente predefinidas ascendiendo para todas sus "Llaves Letra A, B, Z..." automáticamente sin sobreesfuerzo del codificador al final del código.

### El refinamiento extra (Ordenar las frecuencias absolutas ordenadas de MAYOR a MENOR empleando List / Comparator)

Si combinamos ambos paradigmas de Collections (`HashMap` e `Interfaz de Array Lists` mutando en conversiones estáticas iterativas por `Collections.sort(List, ComparadorX)`).

```java
// [...] se procesa iterando desde lectura y guardando los Count objects al MAP (ver ejemplos pre-iteradores Map)

List list = new ArrayList(words.values()); // Extraigo todos los Count Values 
Collections.sort(list, new CountComparator()); // Modifico List reescribiendo el order vía Comparator externo manual
Iterator iter = list.iterator();

while (iter.hasNext()) {
    count = (Count)iter.next(); 
    String word = count.word;
    System.out.println(word + (word.length() < 8 ? "\t\t" : "\t") + count.i); 
}

// ... // Clase de apunte comparadora requerida interna a la clase principal:
static class CountComparator implements Comparator {
    public int compare(Object o1, Object o2) {
        if (o1 != null && o2 != null && o1 instanceof Count && o2 instanceof Count) { 
            Count c1 = (Count) o1; 
            Count c2 = (Count) o2; 
            return (c2.i - c1.i); // Invertir aquí para control Ascendente/Descendente Numérico
        } else { 
            return 0; 
        } 
    } 
}
```

---
*Este documento ha sido generado abarcando meticulosamente cada sección, punto clave, algoritmos y bloque de código presentes en la presentación original aportada al archivo, aplicando mejoras de formato a entidades estructuradas (Markdown, Tablas) para propósitos referenciales.*
