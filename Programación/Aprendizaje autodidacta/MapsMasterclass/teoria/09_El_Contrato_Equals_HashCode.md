# Anatomía de la Llave: El Contrato de Hash

Hasta ahora, hemos usado `String` o `Integer` como llaves (`Key`) de nuestros Mapas. Estas clases fueron programadas por los arquitectos de Java para funcionar perfectamente. Pero, ¿qué pasa cuando usas **TU PROPIA CLASE** como Llave? (Ej: `Map<Empleado, Double> map;`)

Si no conoces las entrañas de `HashMap`, destrozarás tu aplicación perdiendo datos.

## ¿Cómo funciona un HashMap por dentro? (El sistema de Cubos / Buckets)

Cuando haces `map.put(llave, valor)`:
1.  **Fase 1 (El Cajón):** Java llama al método `llave.hashCode()`. Este método devuelve un número entero (`int`). Usando matemáticas módulares, Java decide en qué "Cajón" o "Bucket" (Índice de un array interno) va a guardar tu dato.
2.  **Fase 2 (La Coincidencia Fina):** Si ya había algo en ese cajón (Colisión de Hash), Java recorre los elementos de ese cajón y va llamando a `llaveNueva.equals(llaveVieja)`. Si da `true`, sobrescribe el valor. Si da `false`, lo añade como un nodo extra en la misma caja.

Cuando haces `map.get(llaveBuscada)`:
Se recalcula el `hashCode()` para ir RÁPIDO a la caja correcta (eso da la velocidad O(1)). Luego llama a `.equals()` contra los elementos de esa caja para encontrar el tuyo exacto.

## El Contrato Sagrado de Java
Si dos objetos son iguales (`equals() == true`), **ESTÁN OBLIGADOS** a devolver el mismo `hashCode()`.
*(Al revés no es obligatorio: Dos objetos distintos pueden tener el mismo `hashCode()`, esto se llama Colisión, es ineficiente pero legal).*

### ❌ El Error del Principiante (Romper el contrato)
Creas una clase `Usuario` y sobrescribes su `.equals()` para comparar por DNI.
PERO TE OLVIDAS de sobrescribir el `.hashCode()`.
Resultado: Creas `User A` con DNI X. Lo metes al map. Creas `User B` con el mismo DNI X. Quieres buscar el valor mapeado... ¡Y JAVA NO LO ENCUENTRA! Porque como no tienen el mismo `hashCode()`, Java fue a buscar a un "cajón" de memoria distinto en el paso 1. Has provocado un **Memory Leak** y un objeto huérfano.

### ✅ La Solución: Generar Ambos
Siempre que uses una clase propia como Key, en el IDE, selecciona: `Generate -> equals() and hashCode()` juntos. Usa campos inmutables (DNI, Email) preferiblemente.

> [!TIP]
> **PRO TIP (Java 14+):** Usa `record` en lugar de `class` para tus Llaves. Los Records autogeneran un `equals` y `hashCode` perfectos y además son inmutables por naturaleza, por lo que son la Key definitiva e incorruptible para un Map.

## El Peligro Letal: Mutación de la Llave
Si haces esto, pierdes tu objeto para siempre:
1. Creas `User(id = 5)`. 
2. Lo metes al Hashmap (se calcula su hash basado en el número 5 y cae en la caja A).
3. Modificas la llave por fuera: `user.setId(9)`.
4. Haces un `.get(user)`. Al pedirlo ahora, su Hash se recalcula basado en 9, cayendo en la caja B. Pero ¡el objeto original de Java SE QUEDÓ EN LA CAJA A! Nunca lo encontrarás. Se corrompió el mapa. **NUNCA modifiques un objeto usado como llave.**
