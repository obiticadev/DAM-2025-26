# LECTURA Y ESCRITURA DE INFORMACIÓN EN JAVA

<a id="indice"></a>
## ÍNDICE DINÁMICO
- [6. Serialización de Objetos en Java](#sec6)
  - [6.1 Introducción a la Serialización](#sec6_1)
  - [6.2 Implementación de Serialización en Java](#sec6_2)
    - [6.2.1 Ejemplo Básico de Serialización y Deserialización](#sec6_2_1)
  - [6.3 Uso de transient para Excluir Atributos](#sec6_3)
  - [6.4 Serializar una Lista de Objetos](#sec6_4)
  - [6.5 Compatibilidad de Versiones y serialVersionUID](#sec6_5)
  - [6.6 Resumen de Conceptos Clave](#sec6_6)
  - [6.7 Ejercicios Prácticos](#sec6_7)

---

<a id="sec6"></a>
# 6. Serialización de Objetos en Java

<a id="sec6_1"></a>
## 6.1 Introducción a la Serialización

La **serialización** es el proceso de convertir un objeto en un flujo de bytes para poder almacenarlo en un archivo o enviarlo a través de una red. Posteriormente, el objeto puede reconstruirse mediante la **deserialización**, convirtiendo de nuevo el flujo de bytes en una instancia del objeto original.

```mermaid
%%{init: {'theme': 'base', 'themeVariables': { 'primaryColor': '#1e293b', 'primaryTextColor': '#f8fafc', 'primaryBorderColor': '#38bdf8', 'lineColor': '#94a3b8', 'mainBkg': '#0f172a', 'nodeBorder': '#38bdf8', 'clusterBkg': '#1e293b', 'clusterBorder': '#38bdf8'}}}%%
graph LR
    A["Objeto Java\n(en memoria)"] -->|"ObjectOutputStream\nSerialización"| B["Flujo de bytes\n(archivo .ser / red)"]
    B -->|"ObjectInputStream\nDeserialización"| C["Objeto Java\n(en memoria)"]
    style A fill:#1e293b,stroke:#38bdf8
    style B fill:#0f172a,stroke:#94a3b8
    style C fill:#1e293b,stroke:#38bdf8
```

En Java, la serialización se maneja con las siguientes clases del paquete `java.io`:

- **`ObjectOutputStream`**: Permite escribir objetos en un archivo o flujo de salida.
- **`ObjectInputStream`**: Permite leer objetos desde un archivo o flujo de entrada.

> **Requisito fundamental:** Para que una clase pueda ser serializada, **debe implementar la interfaz `Serializable`**.

> 💡 **TIPS Prácticos:**
> La interfaz `Serializable` es una **interfaz marcadora** (no tiene métodos). Simplemente le indica a la JVM que los objetos de esa clase pueden convertirse a flujo de bytes. Si intentas serializar un objeto cuya clase no implementa `Serializable`, obtendrás una `NotSerializableException` en tiempo de ejecución.

[🏠 Volver al Índice](#indice)

---

<a id="sec6_2"></a>
## 6.2 Implementación de Serialización en Java

Para que un objeto sea serializable, se deben cumplir tres condiciones:

1. La clase debe **implementar `Serializable`**.
2. Todos los **atributos del objeto** deben ser serializables (tipos primitivos, `String` y colecciones de objetos serializables lo son por defecto).
3. Si hay atributos que no se pueden serializar, deben marcarse con la palabra clave **`transient`**.

<a id="sec6_2_1"></a>
### 6.2.1 Ejemplo Básico de Serialización y Deserialización

```java
import java.io.*;

// La clase Persona implementa Serializable para poder ser convertida a bytes
class Persona implements Serializable {
    private static final long serialVersionUID = 1L; // Identificador de versión
    private String nombre;
    private int    edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad   = edad;
    }

    @Override
    public String toString() {
        return "Persona{nombre='" + nombre + "', edad=" + edad + "}";
    }
}

public class SerializacionEjemplo {
    public static void main(String[] args) {
        // --- SERIALIZAR: guardar el objeto en archivo ---
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("persona.ser"))) {
            Persona p = new Persona("Carlos", 30);
            oos.writeObject(p); // Convierte el objeto a bytes y lo escribe
            System.out.println("Objeto serializado: " + p);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // --- DESERIALIZAR: recuperar el objeto desde archivo ---
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("persona.ser"))) {
            Persona p = (Persona) ois.readObject(); // Recupera y castea el objeto
            System.out.println("Objeto deserializado: " + p);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

**Explicación:**
- `writeObject(p)`: Guarda el objeto en un archivo binario (`persona.ser`).
- `readObject()`: Recupera el objeto del archivo. Nótese el *cast* a `Persona`.
- La excepción `ClassNotFoundException` es necesaria porque Java verifica en tiempo de ejecución que la clase del objeto recuperado existe en el classpath.

[🏠 Volver al Índice](#indice)

---

<a id="sec6_3"></a>
## 6.3 Uso de transient para Excluir Atributos

Si un atributo **no debe ser serializado** (contraseñas, datos sensibles, referencias no serializables como conexiones de BD), se puede marcar con la palabra clave `transient`.

**Ejemplo 2: Usando transient**

```java
import java.io.*;

class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private String   usuario;
    private transient String password; // No se serializa: campo sensible

    public Usuario(String usuario, String password) {
        this.usuario  = usuario;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{usuario='" + usuario + "', password='" + password + "'}";
    }
}

public class SerializacionTransient {
    public static void main(String[] args) {
        // Serializar
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("usuario.ser"))) {
            Usuario u = new Usuario("admin", "secreto123");
            oos.writeObject(u);
            System.out.println("Usuario serializado: " + u);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserializar
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("usuario.ser"))) {
            Usuario u = (Usuario) ois.readObject();
            System.out.println("Usuario deserializado: " + u);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

**Salida esperada:**
```
Usuario serializado: Usuario{usuario='admin', password='secreto123'}
Usuario deserializado: Usuario{usuario='admin', password='null'}
```

**Explicación:**
El campo `password` se pierde después de la deserialización porque estaba marcado como `transient`. Su valor al deserializar es el valor por defecto del tipo (`null` para `String`).

> 💡 **TIPS Prácticos:**
> `transient` es la solución correcta para proteger datos sensibles en serialización. Otros casos de uso: atributos calculados que pueden derivarse de otros datos (como una caché), o campos de tipo `Thread`, `Connection`, `Socket`, que no son serializables por naturaleza.

[🏠 Volver al Índice](#indice)

---

<a id="sec6_4"></a>
## 6.4 Serializar una Lista de Objetos

Se pueden serializar **listas de objetos** para guardar múltiples registros en un solo archivo. Toda la `ArrayList` puede tratarse como un único objeto serializable.

**Ejemplo 3: Guardar y Leer una Lista de Objetos**

```java
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializarLista {
    public static void main(String[] args) {
        List<Persona> lista = new ArrayList<>();
        lista.add(new Persona("Ana",   25));
        lista.add(new Persona("Luis",  32));
        lista.add(new Persona("Sofia", 29));

        // Serializar la lista completa como un solo objeto
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("personas.ser"))) {
            oos.writeObject(lista);
            System.out.println("Lista de personas serializada.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserializar y recuperar la lista
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("personas.ser"))) {
            @SuppressWarnings("unchecked")
            List<Persona> listaLeida = (List<Persona>) ois.readObject();
            System.out.println("Lista de personas deserializada: " + listaLeida);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

**Explicación:**
Se guarda una `List<Persona>` completa en un solo `writeObject()`. Al deserializar, se recupera toda la lista de una vez con `readObject()`.

> 🚀 **COMPLEMENTO (Fuera de temario):**
> La anotación `@SuppressWarnings("unchecked")` suprime el aviso del compilador sobre el cast sin verificar `(List<Persona>)`. En Java, los genéricos se borran en tiempo de ejecución (*type erasure*), por lo que el JVM solo ve `List`, no `List<Persona>`. Es un warning inofensivo en este contexto específico.

[🏠 Volver al Índice](#indice)

---

<a id="sec6_5"></a>
## 6.5 Compatibilidad de Versiones y serialVersionUID

El campo `serialVersionUID` se usa para **garantizar la compatibilidad de versiones** en la deserialización.

Si una clase cambia después de haber sido serializada (se añade o elimina un campo), Java lanza una `InvalidClassException` al intentar deserializar el archivo antiguo. Para controlarlo, se debe definir **manualmente** `serialVersionUID`:

```java
private static final long serialVersionUID = 1L;
```

| Escenario | Resultado |
| :--- | :--- |
| No se define `serialVersionUID` | Java lo genera automáticamente; cambia si se modifica la clase → excepción al deserializar. |
| Se define `serialVersionUID = 1L` | El mismo ID se mantiene aunque cambien los campos → deserialización más tolerante. |
| Se cambia `serialVersionUID` manualmente | Indica una ruptura de compatibilidad intencionada → excepción al deserializar versiones antiguas. |

> 💡 **TIPS Prácticos:**
> La práctica recomendada es **siempre declarar `serialVersionUID` explícitamente** en clases que implementen `Serializable`. IDEs como IntelliJ o Eclipse ofrecen la opción de generarlo automáticamente. Si añades un campo nuevo y mantienes el mismo ID, el campo tendrá su valor por defecto al deserializar objetos antiguos (en vez de lanzar excepción).

[🏠 Volver al Índice](#indice)

---

<a id="sec6_6"></a>
## 6.6 Resumen de Conceptos Clave

| Concepto | Descripción |
| :--- | :--- |
| `Serializable` | Interfaz marcadora que permite que un objeto se convierta en un flujo de bytes. |
| `ObjectOutputStream` | Escribe objetos serializables en archivos o flujos de salida. |
| `ObjectInputStream` | Lee objetos serializables desde archivos o flujos de entrada. |
| `transient` | Excluye atributos de la serialización (su valor se pierde al deserializar). |
| `serialVersionUID` | Garantiza la compatibilidad de versiones en la deserialización. |

[🏠 Volver al Índice](#indice)

---

<a id="sec6_7"></a>
## 6.7 Ejercicios Prácticos

> 💡 **TIPS Prácticos:**
> El **Ejercicio 1** (3 en raya con guardado) extiende el ejercicio de la lección anterior: ahora el estado del tablero (un objeto o un array) se serializa con `ObjectOutputStream` al pulsar `"s"`. Al iniciar el programa, comprueba si existe el archivo de guardado para cargarlo. El **Ejercicio 2** aplica el mismo concepto a un "lienzo" de texto. Para los ejercicios 3–9, practica siempre el flujo completo: crear objeto → serializar → cerrar → abrir → deserializar → verificar.

**Ejercicio 1: 3 en Raya con Guardado de Partida**
*   **Enunciado:** Añade un nuevo comando al ejercicio 1 de la lección anterior (3 en raya): el comando `"s"`. Al pulsarlo, se guardará el estado actual de la partida (serializa el objeto del tablero). Cuando el programa se inicie, deberá cargar automáticamente el estado guardado si existe un archivo. Al terminar la partida (victoria o tablas), el archivo de guardado debe eliminarse.

**Ejercicio 2: Guardar Estado del Lienzo**
*   **Enunciado:** Modifica la práctica del lienzo para que al salir se guarde el estado del lienzo (serialización) y al entrar se cargue automáticamente. Añade una nueva opción para *resetear* el lienzo (eliminar el archivo de guardado y empezar de cero).

**Ejercicio 3: Serializar y Deserializar un Objeto Empleado**
*   **Enunciado:** Crea una clase `Empleado` con atributos `nombre` (`String`) y `salario` (`double`). Serializa un objeto de esta clase en un archivo `empleado.ser` y luego deserialízalo e imprime sus datos.

**Ejercicio 4: Modificar un Atributo Después de la Deserialización**
*   **Enunciado:** Después de deserializar un `Empleado`, modifica su salario y vuelve a serializarlo en el mismo archivo.

**Ejercicio 5: Serializar y Deserializar una Lista de Objetos**
*   **Enunciado:** Serializa una lista de `Empleado` en un archivo `empleados.ser` y luego deserialízala, mostrando todos los empleados.

**Ejercicio 6: Usar transient en un Atributo**
*   **Enunciado:** Haz que el campo `password` de un objeto `Usuario` no se serialice.

> **Salida esperada:**
> ```
> Usuario serializado:   Usuario{usuario='admin', password='secreto123'}
> Usuario deserializado: Usuario{usuario='admin', password='null'}
> ```

**Ejercicio 7: Manejar serialVersionUID**
*   **Enunciado:** Agrega `serialVersionUID` explícito a una clase para garantizar compatibilidad en la deserialización. Modifica un campo de la clase y verifica que la deserialización de un archivo antiguo no lanza excepción.

**Ejercicio 8: Leer Solo Objetos de un Tipo Específico**
*   **Enunciado:** Deserializa una lista de `Empleado` desde un archivo y muestra solo aquellos que tengan un salario mayor a 3000.

**Ejercicio 9: Serializar un Objeto con un Objeto Interno**
*   **Enunciado:** Serializa un objeto `Pedido` que contenga internamente un objeto `Cliente`. Ambas clases deben implementar `Serializable`. Deserializa el pedido y muestra los datos del cliente incluido.

[🏠 Volver al Índice](#indice)
