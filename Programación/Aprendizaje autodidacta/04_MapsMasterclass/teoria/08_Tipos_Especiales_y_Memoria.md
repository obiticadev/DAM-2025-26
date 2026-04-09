# Casos Extremos: Memoria y Rendimiento en Maps

Java proporciona implementaciones de `Map` diseñadas para nichos muy específicos que el 90% de los desarrolladores ignoran. Cuando conoces estos mapas, pasas de "programador" a "arquitecto".

## 1. `EnumMap<K, V>` (El Ferrari de los Mapas)

Si tus llaves (`Keys`) son un patrón `Enum`, **NUNCA** debes usar un `HashMap`. Debes usar un `EnumMap`.

*   **¿Por qué?** Porque los ENUMs están indexados numéricamente desde que compilas. Un `EnumMap` NO calcula hashes, no tiene colisiones y no tiene nodos enlazados. Por debajo, **es simplemente un maldito Array básico de Java**.
*   **Rendimiento:** Es O(1) puro y duro. Es extremadamente veloz, eficiente en memoria (no crea objetos `Node`) y los valores se mantienen ordenados automáticamente según el orden en el que declaraste el Enum.

```java
EnumMap<DiasSemana, String> menus = new EnumMap<>(DiasSemana.class);
menus.put(DiasSemana.LUNES, "Lentejas"); // O(1) instantáneo, se va al array índice 0.
```

> [!TIP]
> **PRO TIP:** En videojuegos o motores de alto rendimiento, los estados (`WALKING`, `JUMPING`, `IDLE`) siempre se mapean a acciones usando un `EnumMap`. 

## 2. `IdentityHashMap` (Destruyendo a .equals)

El contrato sagrado de los Mapas dicta que dos llaves son "la misma" si su método `.equals()` devuelve `true`.
`IdentityHashMap` dice: "¡Mentira!".

Para `IdentityHashMap`, dos llaves son idénticas SOLO si apuntan exactamente al mismo bloque de memoria RAM (es decir, `k1 == k2`).

*   **Caso de uso:** Serializadores, clonadores de objetos (Deep Copy) o recorrer grafos cíclicos donde necesitas saber si "literalmente este objeto físico ya lo he procesado", no si "se llama igual a uno que ya procesé".

```java
String a = new String("Hola");
String b = new String("Hola");

HashMap mapNormal = new HashMap();
mapNormal.put(a, 1);
mapNormal.put(b, 2); // Sobrescribe! a.equals(b) es true! (Tamaño = 1)

IdentityHashMap ideMap = new IdentityHashMap();
ideMap.put(a, 1);
ideMap.put(b, 2); // Sobreviven los dos! a == b es false. (Tamaño = 2)
```

## 3. `WeakHashMap` (El recolector de basura)

Normalmente, si un objeto está dentro de un `Map` como Clave, ese objeto es **inmortal**. El Garbage Collector (GC) de Java jamás lo borrará de la memoria porque el Mapa lo está sujetando. Si guardas millones de datos, tendrás un desbordamiento de memoria (Memory Leak).

En un `WeakHashMap`, las llaves son "Referencias Débiles". 
Si una llave ya no se usa en ninguna otra parte viva de tu programa, **el recolector de basura entrará al Mapa y destruirá esa entrada silenciosamente por atrás**.

*   **Caso de uso maestro:** Hacer "Cachés". Creas un caché vinculando Objetos Pesados a Imágenes en memoria. Si el hilo borra el Objeto Pesado, ¡puf!, el Caché se limpia a sí mismo sin que programes nada.

> [!WARNING]
> Nunca uses Strings literales fuertes (como `"Admin"`) como llaves en un `WeakHashMap` si quieres ver al GC actuar, ya que los literales están guardados en el String Pool y el GC casi nunca los mata. Debes usar objetos instanciados.
