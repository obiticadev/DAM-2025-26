# COMO EMPEZAR CON TESTS - Guia para Principiantes

## Que es un Test Unitario?

Imagina que eres un chef y acabas de inventar una receta nueva. Antes de servirla a los clientes, **pruebas** cada ingrediente y cada paso: "Este tomate esta bueno? La sal esta en su punto?". Un **Test Unitario** hace exactamente eso con tu codigo: prueba una **unidad** pequena (un metodo, una clase) de forma automatica.

En lugar de ejecutar tu programa, mirar la consola y comprobar a ojo si salio bien... el test lo hace **por ti**. Automaticamente. En milisegundos. Cada vez que quieras.

### Antes (tu StreamsMasterclass):
```java
// Tu validabas asi:
if (resultado.equals("esperado")) {
    System.out.println("[OK]");
} else {
    System.err.println("[ERROR] esperaba tal cosa...");
}
```

### Ahora (con Tests):
```java
// Un test hace lo mismo, pero mejor:
assertThat(resultado).isEqualTo("esperado");
// Si falla, te dice EXACTAMENTE que salio mal, que esperabas y que obtuviste.
```

---

## Que es TDD (Test-Driven Development)?

TDD es una forma de trabajar que sigue 3 pasos:

1. **ROJO** - Escribes un test que FALLA (porque el codigo aun no existe)
2. **VERDE** - Escribes el MINIMO codigo necesario para que el test pase
3. **REFACTOR** - Mejoras tu codigo sin romper el test

En este proyecto, yo ya hice el paso 1 por ti: **todos los tests estan escritos y FALLAN**. Tu trabajo es escribir el codigo para pasar del ROJO al VERDE.

```
[ROJO]  -->  Escribes tu solucion  -->  [VERDE]
  ^                                        |
  |         Si rompes algo...              |
  +----------------------------------------+
```

---

## Que es JUnit 5?

JUnit es la **herramienta** que ejecuta tus tests en Java. Es como el "motor" que:
- Busca todos los metodos marcados con `@Test`
- Los ejecuta uno a uno
- Te dice cuales pasaron (verde) y cuales fallaron (rojo)

```java
@Test  // <-- Esto le dice a JUnit: "ejecuta este metodo como test"
void debeDevolver5CuandoSumo2y3() {
    int resultado = Calculadora.sumar(2, 3);
    // ... aqui va la comprobacion
}
```

---

## Que es AssertJ?

AssertJ es la **libreria de comprobaciones**. Te permite escribir frases casi en lenguaje natural:

```java
// Comprobar igualdad
assertThat(nombre).isEqualTo("Java");

// Comprobar que no es null
assertThat(objeto).isNotNull();

// Comprobar listas
assertThat(lista).hasSize(3).contains("A", "B");

// Comprobar excepciones
assertThatThrownBy(() -> metodoQueFalla())
    .isInstanceOf(IllegalArgumentException.class)
    .hasMessageContaining("no puede ser negativo");

// Comprobar que NO lanza excepcion
assertThatCode(() -> metodoSeguro()).doesNotThrowAnyException();
```

Siempre empieza con `assertThat(...)` y luego encadena lo que quieres verificar. Es **fluido y legible**.

---

## Como abrir el proyecto en tu IDE

### Opcion A: IntelliJ IDEA (Recomendado)
1. Abre IntelliJ
2. `File > Open...`
3. Navega hasta la carpeta `InterfacesExcepcionesMasterclass`
4. Selecciona el archivo `pom.xml` y haz clic en "Open as Project"
5. IntelliJ detectara Maven automaticamente y descargara las dependencias

### Opcion B: VS Code
1. Abre VS Code
2. Instala la extension **"Extension Pack for Java"** (de Microsoft)
3. Instala la extension **"Test Runner for Java"** (de Microsoft)
4. `File > Open Folder...` > selecciona `InterfacesExcepcionesMasterclass`
5. VS Code detectara el `pom.xml` y te pedira importar el proyecto Maven. Acepta.

---

## Como instalar las dependencias con Maven

### Si tienes Maven instalado en tu sistema:
```bash
# Desde la carpeta del proyecto:
mvn clean install -DskipTests
```

### Si NO tienes Maven:
- **IntelliJ**: Lo hace automaticamente al abrir el pom.xml
- **VS Code**: La extension de Java lo maneja por ti

### Como verificar que todo esta bien:
```bash
mvn compile
# Si ves "BUILD SUCCESS" = las dependencias estan OK
```

---

## El flujo de trabajo (MUY IMPORTANTE)

### DONDE escribes tu codigo:
```
src/main/java/com/masterclass/
    nivel1_interfaces_basicas/
        Ejercicio01_PrimeraInterfaz.java    <-- AQUI ESCRIBES
        Ejercicio02_MultiplesImpl.java      <-- AQUI ESCRIBES
        ...
```

### DONDE NO debes tocar (los tests):
```
src/test/java/com/masterclass/
    nivel1_interfaces_basicas/
        Ejercicio01_PrimeraInterfazTest.java    <-- NO TOCAR
        Ejercicio02_MultiplesImplTest.java      <-- NO TOCAR
        ...
```

### Paso a paso:

1. **Abre un ejercicio** de `src/main/java/...`, por ejemplo `Ejercicio01_PrimeraInterfaz.java`
2. **Lee el Javadoc** del ejercicio (la explicacion con `/** ... */`). Ahi esta toda la teoria y las instrucciones.
3. **Lee la teoria** correspondiente en la carpeta `teoria/` si necesitas mas contexto.
4. **Busca los TODOs** dentro del codigo. Veras metodos asi:
   ```java
   public static Saludable crearSaludador() {
       throw new UnsupportedOperationException("Implementa tu solucion aqui!");
   }
   ```
5. **Borra el `throw`** y escribe tu implementacion
6. **Ejecuta el test** correspondiente para ver si pasa

---

## Como ejecutar los tests

### En IntelliJ IDEA:
1. Abre el archivo de test (ej: `Ejercicio01_PrimeraInterfazTest.java`)
2. Veras iconos verdes de "play" al lado de cada `@Test` y de la clase
3. Haz clic en el icono de play:
   - **Al lado de un metodo**: ejecuta solo ese test
   - **Al lado del nombre de la clase**: ejecuta TODOS los tests de esa clase
4. Tambien puedes: `Click derecho en la carpeta de tests > Run All Tests`

### En VS Code:
1. Abre la vista de Testing: icono del matraz (tubo de ensayo) en la barra lateral izquierda
2. Veras todos los tests organizados por clase
3. Haz clic en el boton de play al lado de cada test o clase
4. O abre el archivo de test y veras los iconos de play sobre cada `@Test`

### Desde la terminal:
```bash
# Ejecutar TODOS los tests:
mvn test

# Ejecutar solo los tests de un nivel:
mvn test -Dtest="com.masterclass.nivel1_interfaces_basicas.*"

# Ejecutar solo un test especifico:
mvn test -Dtest="Ejercicio01_PrimeraInterfazTest"
```

---

## Como interpretar un error de test (AssertJ)

Cuando un test falla, AssertJ te da un mensaje MUY claro. Ejemplo:

### Antes de implementar (UnsupportedOperationException):
```
java.lang.UnsupportedOperationException: Implementa tu solucion aqui!
    at com.masterclass.nivel1_interfaces_basicas.Ejercicio01_PrimeraInterfaz.crearSaludador(...)
```
**Significado**: "Aun no has escrito tu codigo. Ve al metodo `crearSaludador` y reemplaza el `throw`."

### Error de valor incorrecto:
```
org.opentest4j.AssertionFailedError:
Expecting:
  "Hola Mundo"
to be equal to:
  "Hola, Mundo!"
```
**Significado**: Tu metodo devolvio `"Hola Mundo"` pero el test esperaba `"Hola, Mundo!"` (con coma y exclamacion). Revisa tu implementacion.

### Error de null:
```
Expecting actual not to be null
```
**Significado**: Tu metodo devolvio `null` cuando deberia devolver algo. Probablemente olvidaste el `return`.

### Error de excepcion esperada:
```
Expecting code to raise a throwable.
```
**Significado**: El test esperaba que tu codigo lanzara una excepcion (por ejemplo, `IllegalArgumentException` si recibes un parametro invalido) pero tu codigo no la lanzo.

### Error de tipo de excepcion:
```
Expecting actual throwable to be an instance of:
  IllegalArgumentException
but was:
  NullPointerException
```
**Significado**: Tu codigo SI lanzo una excepcion, pero del tipo equivocado. Revisa tu logica de validacion.

---

## Consejos para avanzar

1. **Ve en orden**. Los niveles estan disenados para construir conocimiento progresivamente.
2. **Lee la teoria ANTES de intentar el ejercicio**. Cada nivel tiene su archivo `.md` en `teoria/`.
3. **No mires los tests** para "hacer trampa". Intenta entender QUE te pide el ejercicio y escribe tu solucion.
4. **Si un test falla**, lee el mensaje de error con calma. AssertJ te dice exactamente que esperaba.
5. **Un ejercicio a la vez**. No intentes resolver todo de golpe.
6. **Ejecuta tests frecuentemente**. Es mejor ejecutar despues de cada metodo que al final.

---

## Resumen visual

```
+------------------+       +------------------+       +------------------+
|   teoria/*.md    | --->  | src/main/java/   | --->  | src/test/java/   |
|   (Lee primero)  |       | (Escribe aqui)   |       | (No tocar)       |
+------------------+       +------------------+       +------------------+
                                    |                          |
                                    v                          v
                           Tu implementacion          mvn test / IDE play
                                    |                          |
                                    +---------> ROJO o VERDE <-+
```

Buena suerte! Cada test en verde es un concepto dominado.
