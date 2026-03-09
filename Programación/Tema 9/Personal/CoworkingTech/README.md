# Ejercicio: Sistema de Gestión de Préstamos "CoworkingTech"

Se necesita un sistema en Java para gestionar el alquiler de dispositivos tecnológicos en un espacio de coworking. 

El sistema debe seguir estrictamente esta estructura de paquetes: `Clases`, `DAO`, `Excepciones`, `Interfaz` y `Principal`.

## 1. Excepción Personalizada: `CodigoInvalidoException` (Paquete: `Excepciones`)
* Se lanzará si el código del dispositivo no cumple el formato correcto. 
* **Regla de formato:** Debe empezar por "DEV-" seguido de exactamente 3 números (Ejemplo válido: `DEV-012`. Ejemplo inválido: `DEV-12A` o `ABC-123`).
* Debe almacenar el **código erróneo** y la **fecha actual** (`LocalDate`) en la que se intentó registrar.

## 2. Clase Abstracta: `DispositivoAlquilado` (Paquete: `Clases`)
Representa un dispositivo prestado a un cliente.

* **Atributos `protected`:**
  * `String codigo`
  * `String marca`
  * `LocalDate fechaPrestamo`
  * `LocalDate fechaDevolucion`
  * `boolean seguroActivado`

* **Constructor:** * Recibe `codigo` y `marca`. 
  * Inicializa `fechaPrestamo` al día de hoy (`LocalDate.now()`), `fechaDevolucion` a `null`, y `seguroActivado` a `false`. 
  * Si el código es inválido, debe lanzar `CodigoInvalidoException`.

* **Métodos obligatorios:**
  * `public void finalizarPrestamo()`: Si la `fechaDevolucion` es `null`, le asigna la fecha de hoy. Si no es `null`, no hace nada.
  * `protected long calcularDiasFacturables()`: Devuelve los días transcurridos entre el préstamo y la devolución. **Regla de negocio:** Si se devuelve el mismo día (diferencia de 0), se cobra como mínimo **1 día**. *(Pista: usa `ChronoUnit.DAYS.between()`)*.
  * `public abstract double calcularPrecioTotal()`: Método abstracto para calcular el coste en las subclases.

## 3. Interfaz: `Asegurable` (Paquete: `Interfaz`)
Contiene un único método:
* `void activarSeguro();`

## 4. Subclases de Dispositivos (Paquete: `Clases`)

### a) Clase `Ordenador`
Implementa la interfaz `Asegurable`.

* **Atributos adicionales:** * `int memoriaRAM`
* **Cálculo de precio (`calcularPrecioTotal`):**
  * Precio base: **15.0 € / día**.
  * **Regla 1:** Si la `memoriaRAM` es estrictamente mayor a 16, se aplica un **recargo del 20%** sobre el precio total de los días.
  * **Regla 2:** Si el seguro está activado (`seguroActivado == true`), se suman **10.0 € fijos** al precio final total (no por día).
* **Implementación de `Asegurable`:** * `activarSeguro()` cambiará el estado del atributo `seguroActivado` a `true`.

### b) Clase `Proyector`
*(Los proyectores no se pueden asegurar, por lo que no implementan la interfaz).*

* **Atributos adicionales:** * `boolean esResolucion4K`
* **Cálculo de precio (`calcularPrecioTotal`):**
  * Precio base: **10.0 € / día**.
  * **Regla 1:** Si es 4K (`esResolucion4K == true`), al precio base se le suman **5.0 € extra por cada día** de alquiler.

## 5. Clase: `DAOAlquileres` (Paquete: `DAO`)
Gestiona la colección de dispositivos del coworking.

* **Atributo:** Un `ArrayList<DispositivoAlquilado>`.
* **Métodos:**
  * Constructor sin parámetros que inicialice la lista vacía.
  * `public void registrarDispositivo(DispositivoAlquilado d)`: Añade un dispositivo a la lista.
  * `public DispositivoAlquilado buscarPorCodigo(String codigo)`: Devuelve el dispositivo que coincida con el código, o `null` si no existe.
  * `public double calcularIngresosHistoricos()`: Suma y devuelve el precio total **solo de los dispositivos que ya han sido devueltos** (`fechaDevolucion != null`).

## 6. Clase Principal: `App` (Paquete: `Principal`)
Punto de entrada de la aplicación. Contiene el menú interactivo para probar el sistema. 
*(El código de esta clase ya está proporcionado y no debe ser modificado, el resto del programa debe adaptarse a él).*