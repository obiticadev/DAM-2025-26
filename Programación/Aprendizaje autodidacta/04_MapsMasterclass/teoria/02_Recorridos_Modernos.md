# Recorridos Maestros en Maps

Iterar sobre un `Map` es la operación más frecuente y también donde muchos principiantes cometen errores de ineficiencia. Recuerda, como el Map no es un `Collection`, no puedes usar un bucle `for-each` directamente sobre el objeto `Map`. Necesitas pedirle al Map una "vista" de sus datos.

## Las 3 Vistas Clásicas

Todo Map ofrece 3 métodos para proyectar sus datos en forma de `Collection` o `Set`:

### 1. `keySet()` (Solo las Llaves)
Retorna un `Set<K>`. Útil cuando solo necesitas navegar por las llaves.

```java
for (String clave : mapa.keySet()) {
    System.out.println(clave);
    // ⚠️ ANTI-PATRÓN: Hacer mapa.get(clave) dentro de este bucle.
    // Estás haciendo doble búsqueda, es ineficiente O(N) la iteración + O(1) de get
}
```

### 2. `values()` (Solo los Valores)
Retorna una `Collection<V>`. Útil si quieres calcular totales, medias o simplemente no te importa quién es la llave de dicho valor.

```java
for (Integer valor : mapa.values()) {
    suma += valor;
}
```

### 3. `entrySet()` (Llaves y Valores juntos)
Retorna un `Set<Map.Entry<K, V>>`. **Es EL PATRÓN RECOMENDADO** si necesitas tanto la llave como el valor al mismo tiempo. Objeto `Entry` contiene ambos.

```java
for (Map.Entry<String, Integer> dupla : mapa.entrySet()) {
    System.out.println("Llave: " + dupla.getKey() + " -> Valor: " + dupla.getValue());
    
    // 🔥 Súper poder: Puedes modificar el valor del Map directamente desde el Entry
    dupla.setValue(dupla.getValue() + 10);
}
```

## El Enfoque Moderno: `forEach()` de Java 8

Con la llegada de Lambdas, Java 8 introdujo en `Map` el método `forEach(BiConsumer<K, V>)`. Es la forma más limpia y directa de hacer un recorrido.

```java
// Interfaz BiConsumer: Recibe K y V, no devuelve nada (void)
mapa.forEach((llave, valor) -> {
    System.out.println(llave + " cuesta " + valor);
});
```

**¿Cuál elegir?**
- Si solo necesitas llaves: `keySet()`.
- Si solo necesitas valores: `values()`.
- Si necesitas ambos y estás en un contexto moderno: `forEach()`.
- Si necesitas ambos y además pretendes hacer un `remove` o modificar un `Entry` complejo usando la API de iteradores clásica: `entrySet()`.
