# Ruta de trabajo para ejercicios de gestión con clases, DAO y menú

## Filosofía: de abajo a arriba, de lo simple a lo complejo

La clave es **no tocar el menú ni el main hasta que todo lo de abajo funcione**. Si empiezas por el menú, acabas con métodos vacíos, lógica a medias y pierdes tiempo yendo y viniendo entre clases.

---

## Fase 0 — Leer el enunciado (3-5 min)

Antes de escribir ni una línea:

1. **Subraya las clases** que te piden (nombres exactos).
2. **Subraya los atributos** de cada clase y su tipo.
3. **Subraya los métodos**, qué reciben y qué devuelven (boolean, String, double...).
4. **Subraya las excepciones** personalizadas y sus mensajes exactos.
5. **Subraya las opciones del menú** y qué datos pide cada una.
6. **Anota al margen** cualquier detalle raro (ej: "solamente pedirá el número de reserva").

No empieces a codificar hasta que tengas claro el mapa completo. 2 minutos aquí te ahorran 15 después.

---

## Fase 1 — Excepciones personalizadas (~1 min)

Son las más triviales y no dependen de nada. Hazlas primero.

```
📁 src/Excepciones/
   └── ErrorEnCantidadAsientos.java
```

**Patrón estándar:**
```java
public class MiExcepcion extends IllegalArgumentException {
    public MiExcepcion(String mensaje) {
        super(mensaje);
    }
}
```

**Esto te quita de en medio** la creación de paquetes y la estructura base del proyecto.

---

## Fase 2 — Clase modelo más simple (~3-5 min)

La que NO depende de otras clases. En este ejercicio: `Asiento`.

### Orden dentro de la clase:

1. **Atributos** — léelos del enunciado, pon los tipos correctos.
2. **Constructor** — inicializa TODO. Si hay contador estático, increméntalo aquí.
3. **Métodos de comportamiento** — solo los que pide el enunciado.
4. **Getters** — solo los que vayas a necesitar (no generes todos automáticamente por inercia).

### Trampas comunes en esta fase:

- Si un atributo es `int` y necesitas representar "vacío", usa un valor centinela (0, -1), **nunca** `null`.
- Si hay un contador estático (`static int`), asegúrate de que se incrementa en el momento correcto.

---

## Fase 3 — Clase modelo principal (~10-12 min)

La que contiene/usa la anterior. En este ejercicio: `SesionSala`.

### Orden dentro de la clase:

1. **Atributos** — solo los del enunciado. Si no lo pide, no lo pongas.
2. **Constructor con validaciones:**
   - Escribe PRIMERO todas las validaciones (if/throw).
   - Escribe DESPUÉS la inicialización del array/lista.
   - **Arrays de objetos:** siempre bucle + `new`, nunca `Arrays.fill`.
3. **Métodos de comportamiento** — uno a uno, en el orden del enunciado:
   - `mostrarSala()` → devuelve String, usa StringBuilder.
   - `reservarAsiento()` → valida rango ANTES de acceder al array, devuelve boolean.
   - `liberarAsiento()` → recorre buscando por id, devuelve boolean.
   - `mostrarRecaudacion()` → cuenta ocupados × precio, devuelve double.

### Consejo clave: **Completa cada método antes de pasar al siguiente.**

No dejes métodos vacíos con `// TODO`. Si un método devuelve boolean, escribe al menos el `return false;` de fallo y la lógica de éxito completa.

### Checklist antes de avanzar:

- [ ] ¿El constructor lanza las excepciones con los mensajes EXACTOS del enunciado?
- [ ] ¿Cada método devuelve el tipo que pide el enunciado?
- [ ] ¿Las validaciones de rango usan `>` y no `<`? (léelas en español)
- [ ] ¿No hay posibilidad de `ArrayIndexOutOfBoundsException`?

---

## Fase 4 — DAO (~2-3 min)

El DAO es solo un contenedor. No inventes funcionalidad extra.

### Orden:

1. **Lista + constructor** con `cargarDatos()`.
2. **`cargarDatos()`** — copia los datos del enunciado TAL CUAL.
3. **Métodos de búsqueda** — solo los que necesites para el menú:
   - `buscarPorSesion(int numSesion)` → si el enunciado pide buscar por sesión.
   - `liberarAsientoGlobal(int idReserva)` → si la liberación busca en todas las salas.

### Pregúntate:

> "¿Qué necesita el menú del DAO?"

Si el menú pide "número de sesión", necesitas un método que busque por sesión. Si pide "liberar solo con id de reserva", necesitas un método que recorra todas las salas.

---

## Fase 5 — Main / App con menú (~8-10 min)

**Esta es la última fase.** Si llegas aquí con las clases terminadas, el menú es solo fontanería.

### Paso 1: Método utilitario de entrada (2 min)

Escríbelo PRIMERO porque lo vas a usar en todo el menú:

```java
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

**Reglas:**
- Si el ejercicio solo pide enteros → `preguntaInt` con `Integer.parseInt`.
- Si pide decimales (precio) → `preguntaDouble` con `Double.parseDouble`.
- **No uses `preguntaDouble` para todo y luego castees a `int`.** Es chapuza y puede dar errores con decimales.

### Paso 2: El esqueleto del menú (2 min)

```java
do {
    menu();
    String opcion = sc.nextLine().trim();
    switch (opcion) {
        case "1" -> metodo1();
        case "2" -> metodo2();
        // ...
        case "0" -> continuar = false;
        default -> System.out.println("Opción no válida.");
    }
} while (continuar);
```

**Cada case llama a UN método privado.** No metas lógica directamente en el switch.

### Paso 3: Implementa cada método del menú (5 min)

Sigue el patrón:

1. Pedir datos al usuario con `preguntaInt`.
2. Buscar en el DAO.
3. Validar que existe (null check).
4. Llamar al método de la clase modelo.
5. Mostrar resultado.

```java
private static void reservarAsiento() {
    int numSesion = preguntaInt("Sesión: ");
    SesionSala sala = dao.buscarPorSesion(numSesion);
    if (sala == null) {
        System.out.println("Sesión no encontrada.");
        return;   // ← return temprano, no anidar ifs
    }
    int fila = preguntaInt("Fila: ") - 1;
    int col = preguntaInt("Asiento: ") - 1;
    if (sala.reservarAsiento(fila, col)) {
        System.out.println("Reservado.");
    } else {
        System.out.println("No se pudo reservar.");
    }
}
```

---

## Resumen visual del orden

```
┌─────────────────────────────────────────────────┐
│  1. Leer enunciado completo (3-5 min)           │
│  2. Excepciones personalizadas (1 min)          │
│  3. Clase modelo simple — Asiento (3-5 min)     │
│  4. Clase modelo principal — SesionSala (10 min) │
│  5. DAO con datos del enunciado (2-3 min)       │
│  6. preguntaInt / preguntaDouble (2 min)         │
│  7. Menú + métodos del menú (8 min)             │
│                                                  │
│  ⏱ Total estimado: 30-35 min                    │
└─────────────────────────────────────────────────┘
```

---

## Los 5 errores que más tiempo te hacen perder

| # | Error | Consecuencia | Solución |
|---|-------|-------------|----------|
| 1 | Empezar por el menú | Métodos vacíos, ida y vuelta entre clases | Empieza por abajo |
| 2 | No leer bien el enunciado | Funcionalidad que no se pide o que falta | Subraya antes de codificar |
| 3 | Dejar métodos a medias | Te olvidas de completarlos | Termina cada método antes de pasar al siguiente |
| 4 | `Arrays.fill` con objetos | Bug silencioso que rompe toda la lógica | Siempre bucle + `new` |
| 5 | No validar entradas | El programa peta con la primera letra | `try-catch` en TODA entrada del usuario |

---

## Regla de oro

> **Si no puedes probar una clase de forma aislada (mentalmente o con un main temporal), no está terminada. No avances.**

Antes de pasar a la siguiente fase, repasa mentalmente: "¿Si creo un objeto de esta clase y llamo a sus métodos, funcionará?" Si la respuesta es sí, avanza. Si no, termínala.
