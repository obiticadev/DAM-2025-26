# Feedback - Ejercicio 1: Gestión de Cine

## Bugs críticos encontrados en tu código

### 1. `Arrays.fill()` comparte la misma referencia (SesionSala.java:24-26)

```java
// ❌ Tu código - TODOS los asientos de una fila son EL MISMO OBJETO
Arrays.fill(a, new Asiento());

// ✅ Correcto - Cada asiento es una instancia independiente
for (int i = 0; i < filas; i++) {
    for (int j = 0; j < columnas; j++) {
        asientos[i][j] = new Asiento();
    }
}
```

**Por qué falla:** `Arrays.fill` pone la MISMA referencia en todas las posiciones. Si reservas el asiento [0][0], se reservan TODOS los de la fila porque apuntan al mismo objeto. Este es un error que puede pasar desapercibido en testing rápido.

**Regla:** Cuando llenas un array de objetos, siempre usa un bucle con `new` dentro.

---

### 2. `(Integer) null` causa NullPointerException (Asiento.java:19)

```java
// ❌ Tu código - NPE al hacer unboxing de null a int
this.idReserva = (Integer) null;

// ✅ Correcto - Usa un valor centinela como 0
this.idReserva = 0;
```

**Por qué falla:** Java intenta hacer unboxing de `Integer null` a `int`, lo cual lanza `NullPointerException`. Si necesitas diferenciar "sin reserva", usa `0` como valor centinela ya que los ids empiezan en 1.

---

### 3. Operador de comparación invertido (SesionSala.java:44)

```java
// ❌ Tu código
columna < asientos[0].length - 1  // Esto bloquea columnas válidas

// ✅ Correcto
columna > asientos[0].length - 1  // Esto bloquea columnas fuera de rango
```

**Consejo:** Cuando escribas validaciones de rango, lee la condición en voz alta: "si columna es MENOR que el máximo → return false" no tiene sentido.

---

## Errores de diseño / no cumplir el enunciado

### 4. El menú no corresponde al enunciado

Tu menú incluye "Agregar sala" (opción 1), que **no está en el enunciado**. El enunciado pide exactamente:

```
1. Mostrar estado de la sala
2. Reservar asiento
3. Liberar asiento
4. Ver la recaudación de la sala
0. Salir
```

**Regla:** Lee el enunciado DOS veces antes de codificar el menú. Si pide 4 opciones, haz 4 opciones.

---

### 5. El DAO no usa los datos del enunciado

```java
// ❌ Tu código - datos inventados
new SesionSala(1, 15, 7.50, 1)
new SesionSala(12, 18, 8.50, 2)
// ...

// ✅ Lo que pide el enunciado
new SesionSala(10, 10, 5.25, 1)
new SesionSala(10, 10, 6.25, 2)
new SesionSala(10, 10, 3.25, 3)
```

Esto puede parecer menor, pero en un examen te restan puntos por no seguir las instrucciones literalmente.

---

### 6. Liberar asiento: NO debe pedir la sala

El enunciado dice: *"Para liberar un asiento, **solamente** pedirá el número de reserva"*

Tu código pide la sala además del id de reserva. La liberación debe buscar el id de reserva **en todas las salas**.

```java
// ✅ En el DAO, buscar en todas las salas
public boolean liberarAsientoGlobal(int idReserva) {
    for (SesionSala sala : listaSalas) {
        if (sala.liberarAsiento(idReserva)) return true;
    }
    return false;
}
```

---

### 7. Búsqueda por `numSesion`, no por índice

El enunciado dice "pedirá el número de la sesión de la sala", no el índice del ArrayList. Tu código usa el índice de la lista directamente. Deberías buscar por `numSesion`:

```java
public SesionSala buscarPorSesion(int numSesion) {
    for (SesionSala sala : listaSalas) {
        if (sala.getNumSesion() == numSesion) return sala;
    }
    return null;
}
```

---

### 8. `mostrarRecaudacion()` no está implementado

Falta el método en `SesionSala` y la opción 5 del menú está vacía.

---

### 9. Atributo `disponibilidad` en `SesionSala` no tiene sentido

Tu `SesionSala` tiene un `char disponibilidad` que no se usa y no es parte del enunciado. La disponibilidad es un atributo de `Asiento`, no de la sala.

---

## Consejos de eficiencia para exámenes

### Orden de trabajo recomendado (de abajo a arriba)

1. **Excepciones personalizadas** (~1 min) - Son triviales, hazlas primero y quítalas de en medio.
2. **Clase modelo más simple** (`Asiento`) (~3 min) - Atributos + constructor + getters.
3. **Clase modelo principal** (`SesionSala`) (~10 min) - Constructor con validaciones, métodos de negocio.
4. **DAO** (~2 min) - Copia los datos del enunciado tal cual.
5. **App / Main con menú** (~10 min) - El menú y la lógica de interacción al final.

**Total estimado: ~25-30 min** si tienes claro lo que haces.

### Sobre `preguntaDouble` vs `preguntaInt`

Tu función `preguntaDouble` está bien conceptualmente (bucle + try-catch para validar entrada), pero:

- **Nombra bien los parámetros:** `preguna` → `pregunta` (typo).
- **Usa `preguntaInt` cuando pidas enteros.** Castear un `double` a `int` con `(int)` puede dar problemas con decimales. Si el enunciado pide filas/columnas (enteros), usa `Integer.parseInt()`.
- **No necesitas `preguntaDouble` en este ejercicio** salvo quizás para el precio de entrada, que ni siquiera se pide por teclado.

```java
// ✅ Método limpio para pedir enteros
private static int preguntaInt(String pregunta) {
    while (true) {
        try {
            System.out.print(pregunta);
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Introduce un número entero válido.");
        }
    }
}
```

### Sobre el menú

- Usa `switch` con arrow syntax (`->`) como ya haces, está bien.
- **Delega cada opción a un método privado** con nombre descriptivo (`mostrarEstadoSala()`, `reservarAsiento()`, etc.). Tu `main` queda limpio y legible.
- **No uses `throws Exception` en `main`**. Controla las excepciones dentro de cada método.

### Checklist rápido antes de entregar

- [ ] ¿El menú tiene EXACTAMENTE las opciones del enunciado?
- [ ] ¿El DAO tiene los datos EXACTOS del enunciado?
- [ ] ¿Cada método devuelve lo que pide el enunciado? (booleano, String, double...)
- [ ] ¿Las excepciones personalizadas se lanzan con los mensajes exactos?
- [ ] ¿No hay `NullPointerException` ni `ArrayIndexOutOfBoundsException` posibles?
- [ ] ¿Ninguna entrada del usuario puede romper el programa?

### Errores comunes a evitar

| Error | Cómo evitarlo |
|---|---|
| `Arrays.fill` con objetos | Siempre bucle + `new` |
| `(Integer) null` → NPE | Usar valor centinela (0, -1) |
| Comparadores invertidos (`<` vs `>`) | Leer la condición en español |
| Añadir funcionalidad no pedida | Releer el enunciado |
| No validar entradas del usuario | `try-catch` en TODA entrada |
| Usar `double` para cosas que son `int` | `Integer.parseInt` para enteros |
