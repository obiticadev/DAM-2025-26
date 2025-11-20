# Guía de Encapsulación: Protegiendo los Datos

La **encapsulación** es uno de los pilares fundamentales de la Programación Orientada a Objetos (POO). Su objetivo principal es proteger los datos internos de una clase impidiendo el acceso o modificación directa desde el exterior, forzando el uso de métodos controlados para interactuar con dicha información.

---

## 1. ¿Qué es la Encapsulación y cuál es su Propósito?

Es un mecanismo que restringe el acceso directo a los atributos de una clase. En lugar de exponer las variables públicamente, se controla cómo se accede y modifica la información mediante métodos específicos (**getters** y **setters**).

### Propósitos Principales
1.  **Proteger los datos:** Evita manipulaciones incorrectas o sin control de los atributos.
2.  **Mantener la coherencia:** Permite definir reglas de negocio (como rangos numéricos permitidos) al asignar valores.
3.  **Ocultar detalles de implementación:** El usuario de la clase no necesita saber cómo se almacenan los datos internamente, solo cómo interactuar con ellos.

---

## 2. Modificadores de Acceso en Java

Determinan la visibilidad de los atributos y métodos de una clase. Para lograr la encapsulación, el más relevante es `private`.

| Modificador | Descripción |
| :--- | :--- |
| **`private`** | Solo permite acceso desde dentro de la **misma clase**. (Es la base de la encapsulación). |
| **`public`** | Permite acceso desde **cualquier parte** del programa. |
| **`protected`** | Permite acceso desde la misma clase, subclases y clases del mismo paquete. |
| **Sin modificador** | (Default) Permite acceso solo desde clases del **mismo paquete**. |
| **`static`** | Elemento compartido por todas las instancias de la clase. |
| **`abstract`** | Clase no instanciable o método sin implementación. |

---

## 3. Getters y Setters: Controlando el Acceso

Son métodos públicos diseñados para leer y escribir en atributos privados de forma controlada.

*   **Getter (`getNombreAtributo`):** Devuelve el valor actual del atributo.
*   **Setter (`setNombreAtributo`):** Asigna un nuevo valor al atributo, permitiendo incluir validaciones.

### Ventajas
1.  **Validación de datos:** Los setters pueden bloquear datos incorrectos antes de asignarlos.
2.  **Consistencia:** Asegura que el objeto siempre tenga un estado válido.
3.  **Flexibilidad:** Si la lógica interna cambia, solo se modifica el getter/setter, sin afectar al código externo que usa la clase.

### Ejemplo Básico de Implementación

```java
class Coche {
    private String color; // Atributo encapsulado (private)

    // Método getter
    public String getColor() {
        return color;
    }

    // Método setter
    public void setColor(String nuevoColor) {
        this.color = nuevoColor;
    }
}

public class Main {
    public static void main(String[] args) {
        Coche miCoche = new Coche();
        
        // miCoche.color = "Rojo"; // ESTO DARÍA ERROR (es private)
        
        miCoche.setColor("Rojo"); // Correcto: Usando el setter
        System.out.println("El color es: " + miCoche.getColor()); // Correcto: Usando el getter
    }
}
```

---

## 4. Validación de Datos: El Poder de la Encapsulación

La mayor ventaja de la encapsulación es la capacidad de validar datos antes de guardarlos. A continuación, se compara un escenario sin control frente a uno encapsulado.

### Escenario: Control de Velocidad

**Sin Encapsulación (Problemático):**
Cualquier valor es aceptado, rompiendo la lógica del programa.
```java
class Coche {
    public int velocidad; // Acceso libre
}
// En el main:
// miCoche.velocidad = -50; // Se permite una velocidad negativa (ilógico)
```

**Con Encapsulación (Correcto):**
Se validan los datos dentro del `setter`.

```java
class Coche {
    private int velocidad;

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int nuevaVelocidad) {
        // Validación: La velocidad debe ser positiva y no mayor a 200
        if (nuevaVelocidad >= 0 && nuevaVelocidad <= 200) {
            this.velocidad = nuevaVelocidad;
        } else {
            System.out.println("Error: La velocidad debe estar entre 0 y 200 km/h.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Coche miCoche = new Coche();
        
        miCoche.setVelocidad(100); // Válido
        System.out.println("Velocidad: " + miCoche.getVelocidad());
        
        miCoche.setVelocidad(300); // Inválido: Muestra mensaje de error y no cambia el valor
        miCoche.setVelocidad(-50); // Inválido
    }
}
```

---

## 5. Buenas Prácticas

1.  **Encapsular todo:** Declara `private` cualquier atributo de una clase por defecto.
2.  **Usar Getters y Setters:** Proporcionan la interfaz de acceso controlada.
3.  **Validar en los Setters:** Garantiza que los datos sean coherentes con las reglas del negocio.
4.  **Nomenclatura estándar:** Usa `getVariable` y `setVariable` para mantener la claridad.
5.  **Minimizar lógica compleja:** Los getters y setters no deben abusar de lógica excesivamente compleja, su fin principal es el acceso y validación.

---

## 6. Ejercicios Propuestos

1.  Modifica la clase `Persona` para que sus atributos `nombre` y `edad` sean privados.
2.  Añade métodos `getNombre` y `setNombre` en la clase `Persona` para acceder y modificar el atributo nombre.
3.  Crea un método `setEdad` en la clase `Persona` que valide que la edad sea mayor o igual a 0.
4.  Escribe un programa que use la clase `Persona` y valide si se puede asignar una edad negativa.
5.  ¿Qué sucede si se intenta acceder directamente a un atributo privado desde otra clase? Prueba y explica el resultado.
6.  Implementa una clase `Coche` donde el atributo `velocidad` esté encapsulado. Añade métodos para incrementar y obtener la velocidad.
7.  Crea un método en la clase `Coche` que valide que la velocidad no pueda superar 200 km/h.
8.  Implementa una clase `CuentaBancaria` con atributos `saldo` y `titular`, ambos privados. Añade métodos para depositar y retirar dinero con validaciones.
9.  ¿Qué diferencia hay entre declarar un atributo como `private` y usar un método público para acceder a él?
10. Crea una clase `Libro` donde todos sus atributos estén encapsulados. Añade getters y setters con validaciones.