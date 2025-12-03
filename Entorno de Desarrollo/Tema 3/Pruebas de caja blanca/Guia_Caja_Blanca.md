# Guía de Aprendizaje: Pruebas de Caja Blanca

## 1. ¿Qué son las Pruebas de Caja Blanca?
Las **Pruebas de Caja Blanca** (White Box Testing), también conocidas como pruebas estructurales o de caja de cristal, implican probar el software **conociendo su estructura interna y código fuente**.

- **Entrada**: Datos de prueba diseñados específicamente para pasar por ciertas líneas de código.
- **Caja**: Ves el código, los bucles (for/while), las condiciones (if/else).
- **Salida**: Verificas que el flujo de ejecución fue el correcto.

**Objetivo**: Garantizar que todas las "piezas" internas del código han sido ejecutadas y funcionan correctamente.

---

## 2. Conceptos Clave

### Grafo de Flujo de Control
Es una representación visual del código donde:
- **Nodos (Círculos)**: Representan sentencias o bloques de código.
- **Aristas (Flechas)**: Representan el flujo o camino entre sentencias.

### Complejidad Ciclomática (McCabe)
Es una métrica que indica la complejidad del código y ayuda a determinar el número mínimo de casos de prueba necesarios para cubrir todos los caminos independientes.

**Fórmula**: $V(G) = A - N + 2$
*   $A$: Número de Aristas (flechas).
*   $N$: Número de Nodos.
*   O simplemente: **Número de condiciones (if, while, for, case) + 1**.

---

## 3. Criterios de Cobertura (Coverage)

Para considerar que hemos probado bien el código, usamos niveles de cobertura:

1.  **Cobertura de Sentencias (Statement Coverage)**:
    - ¿Se ha ejecutado cada línea de código al menos una vez?
2.  **Cobertura de Ramas/Decisiones (Branch/Decision Coverage)**:
    - ¿Se ha evaluado cada condición como `Verdadera` y como `Falsa` al menos una vez?
3.  **Cobertura de Caminos (Path Coverage)**:
    - ¿Se han recorrido todos los caminos posibles desde el inicio hasta el fin? (Suele ser el más difícil de lograr al 100%).

---

## 4. Ejemplo Práctico Resuelto

**Código a probar (Pseudocódigo)**:
```java
public String verificarNumero(int num) {
    String resultado = "";
    if (num > 0) {          // Decisión A
        resultado = "Positivo";
    } else {
        resultado = "No positivo";
    }
    
    if (num % 2 == 0) {     // Decisión B
        resultado += " y Par";
    }
    return resultado;
}
```

### Cálculo de Complejidad Ciclomática
- Tenemos 2 condiciones (`if`).
- Complejidad = 2 + 1 = **3**.
- Esto significa que necesitamos al menos 3 caminos independientes para cubrir todo el flujo lógico.

### Diseño de Casos de Prueba (Cobertura de Ramas)
Queremos que cada `if` sea Verdadero y Falso en algún momento.

| Caso | Entrada (num) | Camino Lógico | Resultado Esperado | ¿Qué cubre? |
| :--- | :--- | :--- | :--- | :--- |
| 1 | 4 | `num > 0` (True) -> `num % 2 == 0` (True) | "Positivo y Par" | A(True), B(True) |
| 2 | -3 | `num > 0` (False) -> `num % 2 == 0` (False) | "No positivo" | A(False), B(False) |

*Nota: Con estos dos casos, hemos cubierto A(True/False) y B(True/False). Sin embargo, para cubrir todos los caminos posibles, podríamos necesitar más combinaciones.*

### Caminos Posibles (Path Coverage)
1.  Positivo y Par (Ej: 4)
2.  Positivo e Impar (Ej: 3)
3.  No positivo y Par (Ej: -2)
4.  No positivo e Impar (Ej: -3)

---

## 5. Resumen para el Aprendizaje
1.  **Mira el código**: Dibuja mentalmente (o en papel) el diagrama de flujo.
2.  **Calcula la complejidad**: Cuenta los `if`, `while`, `for` y suma 1. Ese es tu número objetivo de caminos básicos.
3.  **Cubre las decisiones**: Asegúrate de tener pruebas donde los `if` sean verdaderos y otras donde sean falsos.
4.  **Bucles**: Prueba a no entrar en el bucle, entrar una vez, y entrar muchas veces.
