# Nivel 1: Interfaces Basicas

## Que es una interfaz?

Una interfaz en Java es un **contrato**. Define QUE debe hacer una clase, pero no COMO lo hace.

Piensa en un **enchufe de pared**: el enchufe define la forma (dos agujeros redondos en Europa). Cualquier aparato que tenga un conector con esa forma puede enchufarse. Al enchufe no le importa si es una tostadora, un portatil o un ventilador. Solo le importa que cumplan la forma.

```java
// La interfaz define el contrato
public interface Enchufable {
    void encender();
    void apagar();
}

// Cualquier clase puede "cumplir el contrato"
public class Tostadora implements Enchufable {
    @Override
    public void encender() { /* calentar resistencias */ }
    @Override
    public void apagar() { /* cortar corriente */ }
}
```

## Sintaxis basica

```java
public interface NombreInterfaz {
    // Metodos abstractos (sin cuerpo)
    TipoRetorno metodo(parametros);
    
    // Las interfaces NO tienen:
    // - Constructores
    // - Atributos de instancia (solo constantes)
    // - Metodos con cuerpo (excepto default/static, que veremos en Nivel 2)
}
```

## implements vs extends

- Una clase **implements** una interfaz (puede implementar MUCHAS)
- Una clase **extends** otra clase (solo puede extender UNA)
- Una interfaz **extends** otra interfaz

```java
public class MiClase implements InterfazA, InterfazB, InterfazC {
    // Debe implementar TODOS los metodos de las 3 interfaces
}
```

## Por que usar interfaces?

1. **Polimorfismo**: Puedes tratar objetos diferentes de forma uniforme
2. **Desacoplamiento**: Tu codigo depende del contrato, no de la implementacion
3. **Flexibilidad**: Puedes cambiar la implementacion sin cambiar quien la usa
4. **Testing**: Puedes crear implementaciones falsas (mocks) para tests

## Interfaces como tipo

Las interfaces se pueden usar como tipo de variable, parametro o retorno:

```java
// Como tipo de variable
Enchufable aparato = new Tostadora();

// Como parametro
public void conectar(Enchufable dispositivo) { ... }

// Como tipo de retorno
public Enchufable crearDispositivo(String tipo) { ... }
```

## Marker Interfaces (Interfaces vacias)

Algunas interfaces no tienen metodos. Sirven como "etiquetas":

```java
public interface Auditable {}  // No tiene metodos

// Sirve para marcar clases:
public class Factura implements Auditable { ... }

// Y luego comprobar:
if (objeto instanceof Auditable) { /* registrar en log */ }
```

Ejemplos reales en Java: `Serializable`, `Cloneable`, `RandomAccess`.

## Constantes en interfaces

Todos los campos en una interfaz son automaticamente `public static final`:

```java
public interface Configuracion {
    int MAX_REINTENTOS = 3;          // Es public static final
    String VERSION = "2.0";          // Es public static final
    double TIMEOUT_SEGUNDOS = 30.0;  // Es public static final
}

// Se accede asi:
int max = Configuracion.MAX_REINTENTOS;
```

## Interfaz vs Clase Abstracta

| Caracteristica          | Interfaz                    | Clase Abstracta           |
|------------------------|-----------------------------|---------------------------|
| Herencia multiple      | Si (implements muchas)      | No (extends solo una)     |
| Atributos de instancia | No (solo constantes)        | Si                        |
| Constructores          | No                          | Si                        |
| Metodos con cuerpo     | Solo default/static         | Si (tambien abstractos)   |
| Cuando usar            | "Puede hacer X" (capacidad) | "Es un tipo de" (esencia) |

**Regla practica**: 
- Usa **interfaz** cuando multiples clases no relacionadas comparten un comportamiento
- Usa **clase abstracta** cuando las clases comparten estado y comportamiento comun
