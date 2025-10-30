# Arquitectura Von Neumann

### Concepto de Programa Informático

Un programa informático es un conjunto de instrucciones que se ejecutan secuencialmente para realizar una o varias tareas en un sistema. Es creado por un programador, compilado y ejecutado por el sistema. El procesador ejecuta el código instrucción por instrucción.

Cada instrucción se descompone en micro-instrucciones que la arquitectura del computador entiende. La arquitectura actual de computadores es la **Arquitectura de Von Neumann**, definida en 1945.

### Unidades Funcionales

La arquitectura se basa en las siguientes unidades funcionales conectadas de forma permanente:

*   **Memoria:** Dividida en celdas, almacena datos e instrucciones.
*   **CPU (Unidad Central de Proceso):** Ejecuta los programas almacenados en la memoria principal.
*   **Sistema de E/S:** Gestiona la conexión con los periféricos (monitor, teclado, etc.).
*   **Buses:** Interconectan las unidades funcionales para transmitir datos.

### CPU (Unidad Central de Proceso)

Es el cerebro del computador, encargado de ejecutar y controlar todas las operaciones.
*   Lee y escribe información de la memoria y los periféricos.
*   Ejecuta operaciones matemáticas y lógicas.
*   Decodifica las instrucciones.

Se compone de: **registros**, la **ALU** y la **unidad de control**.

#### 1. Registros
Memoria integrada en el procesador, de pequeño tamaño y rápido acceso.
*   **Uso general:** Almacenan temporalmente información (datos o direcciones). Ej: AX, BX, CX.
*   **Uso específico:** Tienen un propósito concreto, como el contador de programa, el registro de instrucción o el indicador de estado.

#### 2. ALU (Unidad Aritmético-Lógica)
Realiza las operaciones lógicas y matemáticas indicadas por la Unidad de Control. Se compone de:
*   **Registros de entrada (RE):** Almacenan los datos a utilizar.
*   **Circuito de operaciones (COP):** Realiza operaciones entre registros.
*   **Registro acumulador (RA):** Almacena el resultado de las operaciones.
*   **Registro de estado (RS):** Almacena el resultado de la última operación (cero, negativo, acarreo, desbordamiento).
*   **Registros bandera:** Registros de 16, 32 o 64 bits (ej. EFLAGS, RFLAGS).

#### 3. Unidad de Control (UC)
Controla el funcionamiento del computador. Sus funciones son:
*   Leer y decodificar instrucciones de la memoria.
*   Enviar órdenes de ejecución.
*   Almacenar el resultado de las operaciones en memoria.
*   Generar los ciclos de reloj.
*   Incrementar el contador de programa.
*   Resolver situaciones anómalas.

**Composición de la Unidad de Control:**
*   **Contador de programa (CP):** Contiene la dirección de la siguiente instrucción a ejecutar.
*   **Registro de instrucción (RI):** Almacena temporalmente la instrucción que se está ejecutando.
*   **Decodificador (D):** Interpreta la instrucción y emite las señales necesarias para su ejecución.
*   **Reloj (R):** Proporciona impulsos eléctricos a intervalos regulares para sincronizar las operaciones.
*   **Secuenciador (S):** Genera las microórdenes para ejecutar paso a paso la instrucción.

### Ejecución de un Programa

La CPU ejecuta secuencialmente las instrucciones almacenadas en memoria. El **ciclo de instrucción** es el conjunto de acciones para realizar cada instrucción y se compone de dos fases:

1.  **Fase de Búsqueda (Captación):** Consiste en transferir la instrucción desde la memoria principal a la unidad de control.
2.  **Fase de Ejecución:** Consiste en ejecutar las acciones que conlleva la instrucción. El secuenciador envía una microorden a la ALU y el resultado se almacena en el registro acumulador.

#### Pasos de la Fase de Búsqueda
1.  Se lee la dirección de memoria de la instrucción desde el contador de programa (CP).
2.  El contenido del CP se transfiere al registro de dirección de memoria (MAR).
3.  El decodificador de memoria conecta la celda de memoria indicada con el registro de datos (MBR).
4.  La instrucción a procesar aparece en el MBR.
5.  La instrucción se transfiere desde el MBR al registro de instrucción (RI) de la unidad de control.
6.  El decodificador de la UC interpreta la instrucción e informa al secuenciador.
7.  El CP se incrementa para apuntar a la siguiente instrucción.

### Buses de Comunicación
Son las líneas que conectan los elementos del computador para transmitir datos y señales de control.
*   **Bus de datos:** Transmite los datos de la CPU de forma bidireccional.
*   **Bus de direcciones:** Transmite las direcciones entre la CPU y la memoria/E/S de forma unidireccional.
*   **Bus de control:** Transmite las señales de control.

### Sistema de Memoria

#### 1. Memoria Principal
Almacena temporalmente la información y se comunica con la CPU. Organiza los datos en celdas de longitud fija (palabras) a las que se accede por una dirección. La capacidad máxima se calcula como `(n*2^m) / 8 bytes`, donde `n` es la longitud de palabra y `m` es el número de hilos del bus de direcciones.

*   **Memoria ROM (Read Only Memory):** Usada en el arranque (BIOS) para el chequeo inicial del hardware. Su borrado requiere procesos especiales.
*   **Memoria RWM (Read-Write Memory):** Es la RAM. Almacena datos temporalmente y se pierde al apagar el equipo.

#### 2. Memoria Caché
Se sitúa entre la memoria principal y la CPU para almacenar la información usada frecuentemente. Es más rápida que la memoria principal.
*   **Según niveles:** L1, L2 y L3, donde el nivel indica la cercanía a la CPU.
*   **Según uso:** Caché de disco, de pista y web.

#### 3. Memoria Virtual
Se usa cuando los programas ocupan más espacio que el disponible en memoria principal, reservando espacio en un dispositivo más lento como el disco duro.

#### 4. Jerarquía de Memoria
Se define por capacidad, velocidad y coste para obtener un almacenamiento rápido, de gran capacidad y bajo coste.
*   **Nivel 0:** Registros (menor capacidad, mayor velocidad y coste).
*   **Nivel 1:** Memoria caché.
*   **Nivel 2:** Memoria primaria.
*   **Nivel 3:** Disco duro.
*   **Nivel 4:** Memorias extraíbles (mayor capacidad, menor velocidad y coste).

### Sistema de E/S
Conecta la CPU con los periféricos, que son elementos de hardware externos para la comunicación.
*   **Periféricos de entrada:** Teclado, ratón.
*   **Periféricos de salida:** Monitor, impresora.
*   **Periféricos de entrada/salida:** Impresora multifunción.
*   **Periféricos de almacenamiento:** Disco duro externo.
*   **Periféricos de comunicaciones:** Transfieren datos entre computadores.

### Simuladores y Vídeos Explicativos

*   **Simuladores de la máquina de Von Neumann:**
    *   [https://lab.xitrus.es/VonNeumann/](https://lab.xitrus.es/VonNeumann/)
    *   [https://vonsim.github.io/](https://vonsim.github.io/)

*   **Vídeos explicativos:**
    *   [https://www.youtube.com/playlist?list=PLYuypTyWj2QcxF-WKUFGvD1C_3CMsc9bt](https://www.youtube.com/playlist?list=PLYuypTyWj2QcxF-WKUFGvD1C_3CMsc9bt)
    *   [https://www.youtube.com/watch?v=S4tisWqUqPM](https://www.youtube.com/watch?v=S4tisWqUqPM)