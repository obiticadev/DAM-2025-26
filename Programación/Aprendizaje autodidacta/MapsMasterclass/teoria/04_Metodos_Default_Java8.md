# Los Métodos Default de Java 8 en Map

Con la introducción de Lambdas en Java 8, la interfaz `Map` recibió una inyección brutal de "métodos default" (métodos implementados directamente en la interfaz). Esto eliminó para siempre un montón de código repetitivo de tipo `if-else` al que estábamos acostumbrados.

Estos métodos están diseñados para ser **seguros**, **elegantes** y orientados a **mutar** los datos directamente en el mapa basándose en lógicas condicionales.

## Los 6 Jinetes de la Modernidad

A continuación, una tabla rápida de lo que hacen y luego el detalle:

| Método | ¿Qué hace resumiendo? | ¿Qué devuelve? |
|--------|-----------------------|---------------|
| `putIfAbsent` | Solo inserta si la clave no existe o su valor es nulo. | El valor *existente* previo, o `null`. |
| `replace` | Solo actualiza si la clave YA existe. | El valor *anterior*, o `null`. |
| `getOrDefault`| Lee la clave, si no existe te da un valor por defecto en vez de exception o null. | El valor, o el *default* indicado. |
| `compute` | Sobreescribe aplicando una Lambda al valor actual (exista o no). | El *nuevo* valor calculado. |
| `computeIfAbsent`| Si NO existe la clave, usa la Lambda para fabricar un valor e insertarlo. | El *nuevo* valor, o el *existente*. |
| `computeIfPresent`| Si SÍ existe la clave, usa la Lambda para transformarlo. | El *nuevo* valor. |

---

### Detalles Destacados

#### 1. `V putIfAbsent(K key, V value)`
Antes, para no pisar un dato hacíamos:
```java
if (!mapa.containsKey("Pepe")) {
    mapa.put("Pepe", 100);
}
```
Ahora: `mapa.putIfAbsent("Pepe", 100);`

#### 2. `V compute(K key, BiFunction<K, V, V> remappingFunction)`
Toma la llave y el **valor actual** para mutarlo matemáticamente y guardarlo.
```java
// Incrementamos el saldo. Si no existía, el valor entra como nulo, osea que asumimos 0.
mapa.compute("Pepe", (k, v) -> (v == null) ? 100 : v + 100);
```

#### 3. `V computeIfAbsent(K key, Function<K, V> mappingFunction)`
**El rey de la pereza brillante.** Se usa muchísimo para listas dentro de mapas.
Si la llave no existe, calcula el valor y lo inserta. La magia es que te devuelve la referencia, con lo que es ideal para la idiomática `Map<K, List<V>>`.

```java
// "Si Pepe no tiene lista de notas, créale un ArrayList y luego añádele el 10."
mapa.computeIfAbsent("Pepe", k -> new ArrayList<>()).add(10);
```

#### 4. `V merge(K key, V value, BiFunction<V, V, V> remappingFunction)`
Ideal para unir o colisionar datos. Si la llave no existe, usa el valor dado. Si existe, usa la Lambda para combinar el viejo con el nuevo.
```java
// Si vendemos "Manzanas", las sumamos al stock existente.
inventario.merge("Manzanas", 5, (oldV, newV) -> oldV + newV);
```

En los próximos ejercicios vas a practicar exhaustivamente esto, ya que es el salto entre un programador Java Junior y uno Mid/Senior.
