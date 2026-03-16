# 🎓 Guía Maestra de Java Collections - Tema 9

Este documento es una ruta de aprendizaje profesional para dominar el **Java Collections Framework (JCF)**. Cada bloque incluye una tabla de métodos esenciales, explicación teórica, un ejemplo práctico y un reto guiado.

---

## 🏗️ Bloque 1: Listas Dinámicas (`ArrayList` vs `LinkedList`)

### 🛠️ Tabla de Métodos Esenciales
| Método | Descripción | Devuelve |
| :--- | :--- | :--- |
| `add(E e)` | Añade un elemento al final de la lista. | `boolean` (siempre true) |
| `add(int index, E e)` | Inserta un elemento en la posición indicada, desplazando el resto. | `void` |
| `get(int index)` | Devuelve el elemento que está en esa posición. | `E` (el objeto) |
| `remove(int index)` | Elimina el elemento de esa posición. | `E` (el objeto eliminado) |
| `size()` | Indica cuántos elementos hay en la lista. | `int` |
| `addFirst(E e)` / `addLast(E e)` | (**Solo LinkedList**) Añade al inicio o al final. | `void` |
| `removeFirst()` / `removeLast()` | (**Solo LinkedList**) Elimina el primer o último nodo. | `E` |

### 📘 Teoría y Ejemplo: El Acceso vs La Inserción
*   **ArrayList**: Rápido para leer mediante índice. Lento para insertar en medio (debe mover elementos).
*   **LinkedList**: Rápido para insertar/borrar en extremos o posiciones conocidas (solo cambia punteros). Lento para buscar (debe recorrer nodos).

```java
List<String> lista = new ArrayList<>();
lista.add("A");          
lista.add(0, "B");       
String item = lista.get(1); 
```

### 🚀 Reto 1: Gestión de Logística Ferroviaria
```java
import java.util.LinkedList;

public class GestionTren {
    public static void main(String[] args) {
        LinkedList<String> vagones = new LinkedList<>();

        // TODO: 1. Añade 3 vagones de "Carga" al final.
        // TODO: 2. Engancha una "Locomotora" al principio (use .addFirst()).
        // TODO: 3. Inserta un vagón de "Pasajeros" en la posición 1.
        // TODO: 4. El último vagón se desengancha. Elimínalo con .removeLast() y muéstralo.
    }
}
```

---

## 🛑 Bloque 2: La Unicidad (`Set` - `HashSet` y `TreeSet`)

### 🛠️ Tabla de Métodos Esenciales
| Método | Descripción | Devuelve |
| :--- | :--- | :--- |
| `add(E e)` | Intenta añadir un elemento. Si ya existe, no hace nada. | `boolean` (false si ya existía) |
| `contains(Object o)` | Comprueba si el elemento está en el Set de forma ultra rápida. | `boolean` |
| `remove(Object o)` | Elimina el elemento si está presente. | `boolean` |
| `clear()` | Borra todos los elementos del conjunto. | `void` |
| `first()` / `last()` | (**Solo TreeSet**) Devuelve el menor o el mayor elemento. | `E` |

### 📘 Teoría y Ejemplo: ¿Duplicados? No, gracias.
*   **HashSet**: No mantiene orden. Basado en *Hashing*. Ideal para búsquedas rápidas.
*   **TreeSet**: Mantiene **orden natural**. Basado en un árbol rojo-negro.

```java
Set<Integer> numeros = new HashSet<>();
boolean exito = numeros.add(5); // true
boolean fallo = numeros.add(5); // false, ya existe
```

### 🚀 Reto 2: Filtro de Invitados y Ordenación Automática
```java
import java.util.*;

public class RegistroEventos {
    public static void main(String[] args) {
        String[] confirmaciones = {"Ana", "Pedro", "Luis", "Ana", "Zaira", "Pedro"};

        // TODO: 1. Crea un HashSet e introduce todos los nombres.
        // TODO: 2. Crea un TreeSet a partir del anterior para tenerlos ordenados alfabéticamente.
        // TODO: 3. Usa .contains() para verificar si "Luis" está en la lista.
    }
}
```

---

## 🗺️ Bloque 3: Mapas Clave-Valor (`HashMap` y `TreeMap`)

### 🛠️ Tabla de Métodos Esenciales
| Método | Descripción | Devuelve |
| :--- | :--- | :--- |
| `put(K key, V value)` | Guarda el par. Si la clave ya existía, sobreescribe el valor. | `V` (el valor antiguo o null) |
| `get(Object key)` | Recupera el valor asociado a esa clave. | `V` (el valor o null si no existe) |
| `containsKey(Object key)` | Verifica si esa clave existe en el mapa. | `boolean` |
| `keySet()` | Devuelve un `Set` con todas las claves del mapa. | `Set<K>` |
| `values()` | Devuelve una colección con todos los valores. | `Collection<V>` |
| `entrySet()` | Devuelve un conjunto de pares Clave-Valor (`Entry`). Ideal para bucles. | `Set<Map.Entry<K,V>>` |

### 📘 Teoría y Ejemplo: El Diccionario Moderno
Un `Map` asocia claves únicas con valores.
*   **HashMap**: Búsqueda instantánea. No ordenado.
*   **TreeMap**: Claves ordenadas automáticamente.

```java
Map<String, Double> notas = new HashMap<>();
notas.put("Pepe", 8.5);
if (notas.containsKey("Pepe")) {
    System.out.println(notas.get("Pepe"));
}
```

### 🚀 Reto 3: Sistema de Inventario de Almacén
```java
import java.util.*;

public class AlmacenTech {
    public static void main(String[] args) {
        // TODO: 1. Crea un HashMap <String (codigo), Integer (stock)>.
        // TODO: 2. Registra "P001"->50.
        // TODO: 3. Actualiza stock sumando sobre el valor actual (use .get() + .put()).
        // TODO: 4. Recorre el mapa usando entrySet() e imprime claves y valores.
    }
}
```

---

## ⚖️ Bloque 4: Colas con Inteligencia (`PriorityQueue`)

### 🛠️ Tabla de Métodos Esenciales
| Método | Descripción | Devuelve |
| :--- | :--- | :--- |
| `add(E e)` / `offer(E e)` | Añade el elemento a la cola. | `boolean` |
| `peek()` | Obtiene el primer elemento (el de más prioridad) pero **no lo quita**. | `E` |
| `poll()` | Obtiene y **elimina** el primer elemento de la cola. | `E` |

### 📘 Teoría y Ejemplo: El Triaje
Una `PriorityQueue` no entrega los elementos por orden de llegada, sino por su valor intrínseco (el menor primero por defecto).

```java
PriorityQueue<Integer> cola = new PriorityQueue<>();
cola.add(10); cola.add(1);
System.out.println(cola.poll()); // Devuelve 1
```

### 🚀 Reto 4: Torre de Control Aérea
```java
import java.util.*;

class Avion implements Comparable<Avion> {
    String id;
    int prioridad; // 1: Emergencia, 3: Normal

    @Override
    public int compareTo(Avion otro) {
        // TODO: Ordenar para que el menor número de prioridad salga antes.
        return Integer.compare(this.prioridad, otro.prioridad);
    }
}

public class TorreControl {
    public static void main(String[] args) {
        // TODO: Añade aviones y sácalos con .poll() para verificar el orden.
    }
}
```

---

## 🏆 Bloque 5: El Gran Desafío (Mapas de Listas)

### 📘 Teoría y Ejemplo: Agrupación
Estructura: `Map<String, List<Double>>`. Ideal para categorizar datos.

### 🚀 Reto Final: Analizador de Ventas
```java
import java.util.*;

public class AnalizadorVentas {
    public static void main(String[] args) {
        String[][] datos = {{"Marta", "150.5"}, {"Luis", "200.0"}, {"Marta", "50.0"}};
        // TODO: Agrupa en un Map<String, List<Double>>.
        // Pista: Si mapa.get(nombre) == null, inicializa la lista primero.
    }
}
```
