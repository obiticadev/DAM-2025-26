# 03 - Operadores

Un operador es un símbolo que le indica al compilador que realice una manipulación matemática, relacional o lógica específica sobre uno o más valores (operandos).

### Tipos de Operadores en Java

1.  **Aritméticos:** Para operaciones matemáticas (`+`, `-`, `*`, `/`, `%`, `++`, `--`).
2.  **De Asignación:** Para asignar valores a variables (`=`, `+=`, `-=`, etc.).
3.  **De Comparación (o Relacionales):** Para comparar dos valores (`==`, `!=`, `>`, `<`, etc.).
4.  **Lógicos:** Para combinar expresiones booleanas (`&&`, `||`, `!`).
5.  **Condicional (Ternario):** Para decisiones simples en una sola línea (`? :`).
6.  **De Bits:** Para manipular directamente los bits de un número (`&`, `|`, `^`, `<<`, `>>`).

---

## 1. Operadores Aritméticos

Se utilizan para realizar cálculos matemáticos.

| Operador | Descripción | Ejemplo |
| :--- | :--- | :--- |
| `+` | Suma | `a + b` |
| `-` | Resta | `a - b` |
| `*` | Multiplicación | `a * b` |
| `/` | División | `a / b` |
| `%` | Módulo (resto de la división) | `a % b` |
| `++` | Incremento (suma 1) | `a++` o `++a` |
| `--` | Decremento (resta 1) | `a--` o `--a` |

#### Operadores Básicos

```java
int a = 10;
int b = 3;

System.out.println("Suma: " + (a + b));           // Salida: 13
System.out.println("Resta: " + (a - b));          // Salida: 7
System.out.println("Multiplicación: " + (a * b)); // Salida: 30
// La división entre enteros trunca el decimal
System.out.println("División: " + (a / b));       // Salida: 3
System.out.println("Módulo: " + (a % b));         // Salida: 1
```

#### Operadores de Incremento y Decremento

*   **Pre-incremento (`++a`):** Primero incrementa el valor, luego lo usa en la expresión.
*   **Post-incremento (`a++`):** Primero usa el valor actual en la expresión, luego lo incrementa.

Lo mismo aplica para el decremento (`--a` y `a--`).

```java
int a = 5;
System.out.println("Pre-incremento (++a): " + (++a)); // Salida: 6
System.out.println("Valor actual de a: " + a);       // Salida: 6

int b = 5;
System.out.println("Post-incremento (b++): " + (b++)); // Salida: 5
System.out.println("Valor actual de b: " + b);       // Salida: 6
```

---

## 2. Operadores de Asignación

Se utilizan para asignar un valor a una variable. El operador `=` es el básico, pero existen operadores de asignación compuesta que realizan una operación y asignan el resultado en un solo paso.

| Operador | Equivalente a | Ejemplo |
| :--- | :--- | :--- |
| `a = b` | `a = b` | Asigna el valor de `b` a `a` |
| `a += b` | `a = a + b` | Suma `b` a `a` y asigna |
| `a -= b` | `a = a - b` | Resta `b` de `a` y asigna |
| `a *= b` | `a = a * b` | Multiplica `a` por `b` y asigna |
| `a /= b` | `a = a / b` | Divide `a` entre `b` y asigna |
| `a %= b` | `a = a % b` | Calcula `a % b` y asigna |

```java
int a = 10;
System.out.println("Valor inicial: " + a); // Salida: 10

a += 5; // a = 10 + 5
System.out.println("Después de a += 5: " + a); // Salida: 15

a -= 3; // a = 15 - 3
System.out.println("Después de a -= 3: " + a); // Salida: 12

a *= 2; // a = 12 * 2
System.out.println("Después de a *= 2: " + a); // Salida: 24

a /= 4; // a = 24 / 4
System.out.println("Después de a /= 4: " + a); // Salida: 6

a %= 5; // a = 6 % 5
System.out.println("Después de a %= 5: " + a); // Salida: 1
```

---

## 3. Operadores de Comparación (Relacionales)

Comparan dos valores y devuelven un resultado booleano (`true` o `false`). Son esenciales para la toma de decisiones.

| Operador | Descripción | Ejemplo |
| :--- | :--- | :--- |
| `==` | Igual a | `a == b` |
| `!=` | Distinto de | `a != b` |
| `>` | Mayor que | `a > b` |
| `<` | Menor que | `a < b` |
| `>=` | Mayor o igual que | `a >= b` |
| `<=` | Menor o igual que | `a <= b` |

```java
int a = 10;
int b = 5;

System.out.println("¿a es igual a b? " + (a == b));   // Salida: false
System.out.println("¿a es distinto de b? " + (a != b)); // Salida: true
System.out.println("¿a es mayor que b? " + (a > b));    // Salida: true
System.out.println("¿a es menor que b? " + (a < b));     // Salida: false
System.out.println("¿a es mayor o igual que b? " + (a >= b)); // Salida: true
System.out.println("¿a es menor o igual que b? " + (a <= b)); // Salida: false
```

---

## 4. Operador Condicional (Ternario)

Es una forma abreviada de una estructura `if-else`. Evalúa una condición y devuelve uno de dos valores.

**Sintaxis:** `resultado = (condicion) ? valorSiTrue : valorSiFalse;`

```java
int edad = 18;
String resultado = (edad >= 18) ? "Mayor de edad" : "Menor de edad";
System.out.println("La persona es: " + resultado); // Salida: Mayor de edad

int numero = 9;
String paridad = (numero % 2 == 0) ? "Par" : "Impar";
System.out.println("El número " + numero + " es: " + paridad); // Salida: Impar
```

---

## 5. Operadores Lógicos

Permiten combinar múltiples expresiones booleanas en una sola.

| Operador | Descripción | Ejemplo |
| :--- | :--- | :--- |
| `&&` | **AND lógico:** `true` si ambas expresiones son `true`. | `(a > b) && (a < 20)` |
| `||` | **OR lógico:** `true` si al menos una expresión es `true`. | `(a < b) || (a > 5)` |
| `!` | **NOT lógico:** Invierte el valor booleano (de `true` a `false` y viceversa). | `!(a == b)` |

```java
int a = 8;
int b = 3;

// AND lógico (&&)
boolean resultadoAND = (a > b) && (a < 10); // true && true
System.out.println("AND lógico: " + resultadoAND); // Salida: true

// OR lógico (||)
boolean resultadoOR = (a < b) || (a < 10); // false || true
System.out.println("OR lógico: " + resultadoOR);  // Salida: true

// NOT lógico (!)
boolean resultadoNOT = !(a > b); // !true
System.out.println("NOT lógico: " + resultadoNOT); // Salida: false
```

---

## 6. Operadores de Bits (Bitwise)

Realizan operaciones directamente sobre la representación binaria de los números enteros. Son útiles para manipulación de bajo nivel.

| Operador | Descripción |
| :--- | :--- |
| `&` | AND bit a bit |
| `|` | OR bit a bit |
| `^` | XOR bit a bit |
| `~` | Complemento bit a bit (invierte todos los bits) |
| `<<` | Desplazamiento a la izquierda |
| `>>` | Desplazamiento a la derecha (con signo) |
| `>>>` | Desplazamiento a la derecha (sin signo) |

```java
int a = 12; // Binario: 1100
int b = 7;  // Binario: 0111

// AND de bits (&)
// 1100
// 0111
// ----
// 0100  (Decimal: 4)
int andResultado = a & b;
System.out.println("AND de bits: " + andResultado); // Salida: 4

// OR de bits (|)
// 1100
// 0111
// ----
// 1111  (Decimal: 15)
int orResultado = a | b;
System.out.println("OR de bits: " + orResultado);  // Salida: 15

// Desplazamiento a la izquierda (<<) - Equivale a multiplicar por 2^n
int c = 5; // Binario: 0101
int desplazamientoIzq = c << 2; // 010100 (Decimal: 20)
System.out.println("Desplazamiento a la izquierda: " + desplazamientoIzq); // Salida: 20

// Desplazamiento a la derecha (>>) - Equivale a dividir entre 2^n
int d = 20; // Binario: 010100
int desplazamientoDer = d >> 2; // 000101 (Decimal: 5)
System.out.println("Desplazamiento a la derecha: " + desplazamientoDer); // Salida: 5
```

---

## 7. Precedencia de Operadores

La precedencia define el orden en que se evalúan los operadores en una expresión compleja. Los operadores con mayor precedencia se evalúan primero.

**Orden General (de mayor a menor precedencia):**

1.  **Postfijos:** `expr++`, `expr--`
2.  **Unarios:** `++expr`, `--expr`, `+expr`, `-expr`, `!`, `~`
3.  **Multiplicativos:** `*`, `/`, `%`
4.  **Aditivos:** `+`, `-`
5.  **Desplazamiento:** `<<`, `>>`, `>>>`
6.  **Relacionales:** `>`, `>=`, `<`, `<=`
7.  **Igualdad:** `==`, `!=`
8.  **AND a nivel de bits:** `&`
9.  **XOR a nivel de bits:** `^`
10. **OR a nivel de bits:** `|`
11. **AND lógico:** `&&`
12. **OR lógico:** `||`
13. **Ternario:** `? :`
14. **Asignación:** `=`, `+=`, `-=`, `*=`, `/=`, `%=`, etc.

**Consejo:** Cuando tengas dudas, usa paréntesis `()` para forzar un orden de evaluación específico y hacer tu código más claro.

```java
// Ejemplo de precedencia
int resultado = 5 + 3 * 2; // Primero se hace 3 * 2, luego se suma 5
System.out.println(resultado); // Salida: 11

int resultadoConParentesis = (5 + 3) * 2; // Primero se hace 5 + 3, luego se multiplica por 2
System.out.println(resultadoConParentesis); // Salida: 16
```
