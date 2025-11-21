# Apuntes sistemas

Clase: Sistemas Informáticos (https://www.notion.so/Sistemas-Inform-ticos-27f981b058b981569f34ea256ed796ed?pvs=21)
Estado: En curso
Tipo: Ampliación

- **DATOS SEGUROS DE EXAMEN:** Según las anotaciones, estos tres valores caen seguro:
    - **SATA 3.0:** Su velocidad es de **600 MB/s** (6 Gbps).
    - **PCI Express 1.0:** Su velocidad por carril es de **250 MB/s** (2 Gbps). *(En la nota pone "110", pero se refiere a la versión 1.0 x1)*.
    - **USB 3.1:** Su velocidad de transferencia es de **10 Gbps**.
    - Cuantas posiciones de memoria puede direccionar un bus de 10 bits: 2¹⁰=1024
    - Registro de instrucciones
    - Pirámide de memoria del ordenador
    - Tipos de Memoria
    - El sistema decimal,binario,octal
    - **Tamaño máximo de archivo:** **4 GB**. (No puedes guardar un archivo individual mayor a esto).
    - Las generaciones de los buses
    - Perfiles XMP
    - (Red)Direccion MAC
    - Round Robin
    - POST de la BIOS

### **Resumen General de Componentes de Hardware**

Aquí tienes una guía simplificada de los componentes clave de un ordenador, su función y cómo interactúan entre sí.

# 1. El Procesador (CPU - Unidad Central de Procesamiento)

Es el **cerebro del ordenador**. Se encarga de ejecutar las instrucciones de los programas y procesar los datos.

## **Componentes Principales:**

- **Unidad de Control (UC):** 
Dirige el flujo de datos. Lee las instrucciones, las interpreta y las ejecuta en el orden correcto.
    - **Contador de Programa (CP):** Apunta a la dirección de memoria de la próxima instrucción que se ejecutará.
    - **Decodificador de Instrucciones (DI):** Interpreta la instrucción actual para decirle al procesador qué operación realizar.
    - **Registro de Instrucciones (RI):** Almacena temporalmente la instrucción que se está ejecutando en este momento.
    - **Registro de Datos:** Guarda temporalmente los datos que se están usando en las operaciones.
    - **Registros Auxiliares:** Contienen datos temporales o de estado para ayudar en las operaciones del procesador.
    - **Registro de la Pila (Stack Pointer, SP):** Apunta a la última posición utilizada en la pila de memoria.
- **Unidad de Proceso (o Aritmético-Lógica, ALU):** Realiza todos los cálculos matemáticos y operaciones lógicas (comparaciones).
    - **La Unidad Aritmético-Lógica (ALU):** Es el circuito que realiza todas las operaciones matemáticas (suma, resta) y lógicas (Y, O, NO) dentro del procesador.
    - **El Acumulador:** Es un registro especial que almacena temporalmente los resultados intermedios de las operaciones realizadas por la ALU.
    - **El Registro de Estado (o de banderas):** Almacena información sobre el resultado de la última operación, como si fue cero, negativo o si hubo un desbordamiento.
    - **Registros de entrada:** Almacena los operandos de la operación
    - **Circuitos operacionales:** Realiza las operaciones
    - **Unidad de coma Flotante**
- **Núcleos (Cores):** Un procesador puede tener varios núcleos. Cada núcleo es como un procesador independiente, lo que permite realizar múltiples tareas a la vez (multitarea). Una "arquitectura de doble núcleo" significa que hay dos cerebros trabajando en paralelo.
    - **CISC (Complex Instruction Set Computing):** Arquitectura de procesadores con un amplio conjunto de instrucciones complejas capaces de realizar varias tareas en un solo paso.
    - **RISC (Reduced Instruction Set Computing):** Arquitectura de procesadores que utiliza un conjunto pequeño y optimizado de instrucciones simples que se ejecutan muy rápidamente.
    - **ARM (Acorn RISC Machine):** Una familia de arquitecturas basadas en RISC, conocida por su alta eficiencia y bajo consumo de energía, dominante en dispositivos móviles.
- **Memoria Caché:** Es una memoria muy pequeña y ultrarrápida integrada en el procesador. Almacena los datos e instrucciones más utilizados para que la CPU pueda acceder a ellos instantáneamente, sin tener que esperar a la memoria RAM. Se organiza en niveles:
    - **L1:** La más pequeña y rápida, integrada en cada núcleo.
    - **L2:** Más grande que la L1, a menudo una por núcleo o compartida por un par.
    - **L3:** La más grande y un poco más lenta, compartida por todos los núcleos.
- **Velocidad:** Se mide en Gigahercios (GHz). Indica el número de ciclos de operaciones que puede realizar por segundo.

# 2. La Placa Base (Motherboard)

Es la **columna vertebral del ordenador**. Es una gran placa de circuito impreso a la que se conectan todos los demás componentes para que puedan comunicarse entre sí.

## **Elementos Clave:**

- **Zócalo (Socket):** El lugar donde se instala el procesador.
- **Ranuras de memoria (Slots):** Donde se conectan los módulos de memoria RAM.
- **Slots de Expansión:** Permiten añadir tarjetas adicionales como tarjetas gráficas, de sonido o de red. Los más importantes son los **PCI Express (PCIe)**, que sustituyeron a los antiguos PCI y AGP.
- **Conectores de almacenamiento:** Para conectar discos duros y SSD (SATA, M.2).
- **Chipset:** Es el "director de tráfico" de la placa base. Es un conjunto de chips que gestiona el flujo de datos entre el procesador, la memoria y los periféricos.
    - **Arquitectura antigua:** Usaba un **Puente Norte** (Northbridge) para la comunicación rápida (CPU, RAM, gráfica) y un **Puente Sur** (Southbridge) para la comunicación más lenta (discos duros, USB).
    - **Arquitectura moderna (PCH):** Las funciones del Puente Norte se han integrado directamente en el procesador. El chipset ahora se llama **PCH (Platform Controller Hub)** y gestiona los periféricos, actuando como un Puente Sur mejorado.
- **BIOS/UEFI:** Es el primer software que se ejecuta al encender el ordenador. Realiza una comprobación del hardware (POST) y carga el sistema operativo. **UEFI** es la evolución moderna de la clásica **BIOS**, con una interfaz gráfica y funciones más avanzadas.

# 3.1 La Memoria RAM (Random Access Memory)

Es la **memoria de trabajo a corto plazo** del ordenador. Es volátil, lo que significa que pierde su contenido cuando se apaga el ordenador.

- **Función:** Almacena temporalmente los datos y programas que se están utilizando en ese momento para que el procesador pueda acceder a ellos rápidamente.
- **Tipos:** Hay cuatro tipos:
    - **DRAM** (Dinamic RAM). Aun estando alimentada, su tendencia es que los datos que almacena se pierdan, por lo que necesita refrescarse cada cierto tiempo para no perder informacion.
    - **SRAM** (Static RAM) No necesita refrescarse, por lo tanto es más rápida que la DRAM, también es más cara. Se utiliza como memoria caché.
    - **SDRAM** (Synchronous Dynamic RAM) Es una DRAM con un número reducido de ciclos de refresco. Su funcionamiento está marcado por una señal de reloj.
    - **DDR** (Double Data Rate) Es una SDRAM capaz de realizar el doble de operaciones para una misma frecuencia de reloj.
- **Características:**
    - **Capacidad:** Se mide en Gigabytes (GB). Más RAM permite tener más programas abiertos a la vez sin que el sistema se ralentice.
- **Modos de Memoria:**
    - **Single-Channel:** El procesador se comunica con la RAM a través de un solo canal de 64 bits.
    - **Dual-Channel:** Utiliza dos canales, duplicando el ancho de banda (128 bits) y mejorando el rendimiento. Requiere instalar los módulos de RAM en pares en las ranuras correctas.
- Tipos de latencias
    - CAS: indica el tiempo que tarda la memoria en colocarse sobre una columna o celda.
    - RAS: indica el tiempo que tarda la memoria en colocarse sobre una fila.
    - ACTIVE: indica el tiempo que tarda la memoria en activar un tablero.
    - PRECHARGE: indica el tiempo que tarda la memoria en desactivar un tablero.

# 3.2 La Memoria ROM (Read Only Memory)

Es la memoria de solo lectura del ordenador. **Es no volátil, lo que significa que conserva su contenido incluso cuando se apaga el ordenador.**

- **Función:** **Almacena permanentemente** las instrucciones básicas y programas necesarios para el arranque del sistema, como la **BIOS** (Basic Input Output System), para que el procesador sepa qué hacer al encenderse.
- **Tipos:** Hay tres tipos principales:
    - **PROM** (Programmable ROM). Viene vacía y **permite grabar datos una sola vez**. Una vez escrita, la información queda fija y no se puede modificar ni borrar.
    - **EPROM** (Erasable PROM) Permite **borrar y regrabar** el contenido, pero requiere exponer el chip a **luz ultravioleta** a través de una ventana en su superficie.
    - **EEPROM** (Electrically Erasable PROM) Permite **borrar y regrabar eléctricamente** sin necesidad de sacar el chip. Es la tecnología que se usa en las BIOS modernas y memorias flash.
- **Características:**
    - **Capacidad:** Se mide generalmente en **Megabytes (MB)**. Al solo guardar instrucciones de arranque, no necesita la gran capacidad de la RAM (GB).
- **Velocidad de Acceso:** (Equivalente al apartado de modos/velocidad)
    - **Lectura:** Es más lenta que la memoria RAM. Por eso, en muchos sistemas, el contenido de la ROM se copia a la RAM al arrancar (**Shadowing**) para acceder a él más rápido durante el uso.
    
    # 3 Tabla Comparativa (¡Importante para examen!)
    
    | Característica | RAM | ROM | FLASH* |
    | --- | --- | --- | --- |
    | **¿Lectura / Escritura?** | **SÍ** | **NO** | **SÍ** |
    | **¿Es Volátil?** | **SÍ** | **NO** | **NO** |
    | **¿Acceso Aleatorio?** | **SÍ** | **SÍ** | **SÍ** |
    - **Explicación de la tabla:**
        - **Lectura/Escritura:**
            - En la **RAM** y la **Flash** puedes leer y guardar datos.
            - En la **ROM**, por definición teórica (Read Only), **NO** se puede escribir durante el uso normal del ordenador (solo leer instrucciones).
        - **Volatilidad:**
            - La **RAM** es la única **volátil** de la lista (se borra al apagar).
            - La **ROM** y la **Flash** son **no volátiles** (los datos se quedan guardados sin electricidad).
        - **Acceso Aleatorio:**
            - **Todas** (RAM, ROM y Flash) permiten acceso aleatorio. Esto significa que se
            puede saltar a cualquier parte de la memoria directamente sin tener que
            leer todo lo anterior

# 4. Dispositivos de Almacenamiento Secundario

Es la **memoria a largo plazo** del ordenador. No es volátil, por lo que guarda tus archivos, programas y el sistema operativo incluso cuando el ordenador está apagado.

- **Disco Duro HDD (Hard Disk Drive):**
    - **Tecnología:** Almacena datos magnéticamente en discos giratorios (platos) que son leídos por un cabezal.
        - **Estructura Física:**
            - **Plato:** Es **cada uno de los discos** circulares que giran dentro de la unidad de disco duro.
            - **Cara:** Es **cada uno de los dos lados** (superior e inferior) de un plato.
            - **Cabezal:** Es el mecanismo encargado de leer y escribir. Existe un **cabezal por cada cara**.
        - **Organización de los Datos:**
            - **Pista:** Es una **circunferencia** concéntrica dentro de una cara.
                - Importante: **La pista cero (0) está situada en el borde exterior** del disco.
            - **Cilindro:** Es el conjunto de **varias pistas alineadas verticalmente**.
                - Imagina atravesar todos los platos con una aguja: todas las circunferencias que tocas forman un cilindro.
            - **Sector:** Es **cada una de las divisiones** (trozos de tarta) de una pista.
                - **Tamaño:** El estándar actual es de **512 bytes**, aunque la asociación IDEMA13 impulsa el nuevo estándar de **4 KiB**.
            - **Sector geométrico:** Se refiere a los sectores que son **contiguos pero pertenecen a pistas diferentes**.
            - **Clúster:** Es un **conjunto contiguo de sectores**. Es la unidad mínima de espacio que el sistema operativo utiliza para guardar archivos.
- **Unidad de Estado Sólido SSD (Solid State Drive):**
    - **Tecnología:** Utiliza chips de memoria flash (NAND), sin partes móviles.
    - **Características:** Muchísimo más rápidos, silenciosos, duraderos y eficientes energéticamente. Su precio por GB es más alto.
- **Interfaces de Conexión (Cómo se conectan a la placa base):**
    - **SATA:** La interfaz tradicional para HDD y SSD de 2.5". Su velocidad está limitada a unos 600 MB/s.
    - **M.2 y PCIe:** M.2 es un formato físico (una pequeña tarjeta). Puede usar el protocolo SATA (lento) o el protocolo **NVMe** a través de la conexión PCIe (ultrarrápido), alcanzando velocidades de miles de MB/s.

# 4.1 Unidades de entrada-salida y buses

Es el sistema encargado de la comunicación interna y externa. **La Unidad de entrada-salida permite al procesador y resto de elementos internos comunicarse con los periféricos.**

- **Concepto:**
    - **Bus:** Un bus es el **conjunto de líneas que conectan los diferentes dispositivos** del ordenador permitiendo el transporte de información entre ellos.
- **Tipos de Buses:** En un sistema distinguimos tres categorías principales:
    - **Bus de direcciones:** **Transmite información (direcciones de memoria)** desde el procesador a la memoria y periféricos. Indica *dónde* se debe leer o escribir el dato.
    - **Bus de datos:** **Transmite datos** concretos entre la UCP (Unidad Central de Proceso) y los periféricos. Es por donde viaja la información real.
    - **Bus de control:** **Organiza la transmisión de información**, gestionando las señales de control y temporización para coordinar el tráfico de datos.
- **Universal Serial Bus (USB):**

| **Estándar** | **Conector** | **Velocidad** | **Nombres Alternativos (Nomenclatura)** | **Nombre Comercial / Notas** |
| --- | --- | --- | --- | --- |
| **USB 1.1** | - | 12 Mbps*(1,5 MB/s)* | - | - |
| **USB 2.0** | - | 480 Mbps *(60 MB/s)* | - | - |
| **USB 3.0** | Tipo A, TipoB, Micro B, Tipo C | 5 Gbps *(600 MB/s)* | USB 3.1 Gen1= USB 3.2 Gen1x1 | **SuperSpeed USB** |
| **USB 3.1** | **Tipo C** | **10 Gbps** *(1,25 GB/s)* | USB 3.1 Gen2= USB 3.2 Gen2x1 | **SuperSpeed USB+ 10Gbps (EXAMEN)** |
| **USB 3.2** | Tipo C | 20 Gbps *(2,5 GB/s)* | USB 3.2 Gen2x2 Soporte Display Port y Poer Delivery | **SuperSpeed USB 20Gbps** |
| **USB 4** | Tipo C | 20-40 Gbps *(5 GB/s)* |  USB 4 Gen2xx2 USB 4 Gen3x2 | - |

# 5. Sistemas de codificación alfanumérica

Son los estándares utilizados para representar texto en los ordenadores. **Permiten traducir los caracteres humanos a código binario.**

- **ASCII** (American Standard Code for Information Interchange). Originalmente utilizaba **7 bits**, por lo que podía representar un total de **128 caracteres diferentes** ().
    - Posteriormente, añadiendo un bit más, se podían incluir otros 128 caracteres () para diferentes idiomas.
    - **Problema:** El emisor y el receptor **pueden estar usando páginas de códigos diferentes**, lo que causa errores de lectura.
- **Unicode.** Surge para **solucionar el problema de las páginas de códigos de ASCII**. Su nombre deriva de sus tres objetivos principales:
    - **Universal:** Incluye los diferentes lenguajes.
    - **Uniforme:** Se mantiene un formato fijo para su eficacia.
    - **Único:** Cada secuencia tiene una única interpretación.
- **UTF-8** Es un formato de codificación de caracteres Unicode e ISO 10646 que **utiliza símbolos de longitud variable**.
    - Es capaz de representar **cualquier carácter Unicode**.
    - Usa símbolos de longitud variable de **1 a 4 bytes por carácter Unicode**.

# 5.1 Medidas de la información

Es fundamental distinguir entre la unidad mínima y las agrupaciones para medir capacidad o velocidad. **El bit es la unidad mínima de información.**

- **Tipos de Medida:**
    - **Almacenamiento:** Para indicar capacidad se utiliza el **byte (B) y sus múltiplos**.
        - Equivalencia base: **1 byte = 8 bits**.
    - **Velocidad de transferencia:** Se utiliza **bps (bits por segundo)** y sus múltiplos de base 10.
- **Múltiplos de Bytes (Escalas):**
    - **Sistema Internacional (Decimal):** Utiliza potencias de 10 ().
        - Los prefijos son: **kilobyte (KB)**, **megabyte (MB)**, **gigabyte (GB)**...
        - Ejemplo: 1 KB = 1000 bytes.
    - **ISO/IEC 80000-13 (Binario):** Utiliza potencias de 2 (). Es la medida "real" en informática.
        - Los prefijos cambian ligeramente: **kibibyte (KiB)**, **mebibyte (MiB)**, **gibibyte (GiB)**...
        - Ejemplo: **1 KiB = 1024 bytes** ().

# **6. Análisis Detallado de las Licencias con Ejemplos**

# **6.1 Conceptos Fundamentales**

## **Dominio Público:**

- **Explicación:** Sin restricciones. Cualquiera puede hacer lo que quiera con el software.
- **Ejemplo:** **SQLite**, un pequeño motor de base de datos usado en innumerables aplicaciones (incluyendo tu teléfono), cuyo código ha sido donado al dominio público. Sus creadores han renunciado a todos sus derechos.

## **Derecho de Autor (Copyright):**

- **Explicación:** Protección total por defecto. El creador tiene todos los derechos, y nadie puede copiar, modificar o distribuir sin su permiso.

# **6.2 Modelos de Licenciamiento con Ejemplos**

- **Explicación:** El código es secreto y el uso está muy restringido por un contrato (EULA/ALUF).
- **Ejemplo:** **Adobe Photoshop**. Pagas por usarlo, pero no puedes ver su código fuente, no puedes modificarlo y el contrato te prohíbe instalarlo en más ordenadores de
los permitidos.

**Permiten gran libertad, incluso usar el código en proyectos cerrados.**

## **Licencia MIT:**

- **Explicación:** Extremadamente permisiva. Solo pide mantener el aviso de copyright.
- **Ejemplo:** **React**, la biblioteca de JavaScript de Meta (Facebook). Miles de empresas la
usan para construir sus sitios web y aplicaciones comerciales de código
cerrado. Solo tienen que incluir un pequeño archivo de texto con la
licencia MIT.

## **Licencia Apache 2.0:**

- **Explicación:** Permisiva pero más detallada, con protección contra demandas de patentes.
- **Ejemplo:** **Android** (la base, llamada AOSP). Google lo libera bajo Apache 2.0. Esto permite que fabricantes como Samsung tomen ese código base, le añadan su
software propietario (su capa de personalización One UI, su tienda de
apps) y vendan el teléfono sin tener que liberar el código de sus
añadidos.

**Obligan a que las versiones modificadas también sean libres.**

## **GNU GPL (Copyleft Fuerte):**

- **Explicación:** Si usas código GPL, tu proyecto completo también debe ser GPL.
- **Ejemplo:** El **kernel de Linux**. Si un fabricante de routers coge el kernel de Linux y lo modifica para que funcione en su nuevo dispositivo, está legalmente obligado a
publicar el código fuente de esas modificaciones. No puede mantenerlas
en secreto.

## **GNU LGPL (Copyleft Débil):**

- **Explicación:** Permite que software propietario use una "biblioteca" LGPL, pero si
modificas la biblioteca, debes compartir esas modificaciones.

## **MPL (Licencia Pública de Mozilla):**

- **Explicación:** El copyleft se aplica solo a los archivos modificados, no al proyecto entero.

# **6.4 Creative Commons (CC) con Ejemplos**

Licencias para obras creativas (fotos, música, texto).

## **CC BY (Atribución):**

- **Explicación:** Haz lo que quieras, pero menciona al autor.

## **CC BY-SA (Atribución-Compartir Igual):**

- **Explicación:** Haz lo que quieras, pero menciona al autor y tu nueva obra debe tener esta misma licencia.

## **CC BY-ND (Atribución-Sin Obras Derivadas):**

- **Explicación:** Puedes compartirla, pero no puedes cambiarla. Menciona al autor.

## **CC BY-NC (Atribución-No Comercial):**

- **Explicación:** Puedes usarla y modificarla, pero no para ganar dinero. Menciona al autor.

## **CC BY-NC-SA (Atribución-No Comercial-Compartir Igual):**

- **Explicación:** No puedes ganar dinero con ella y tus obras derivadas deben tener esta misma licencia. Menciona al autor.

## **CC BY-NC-ND (Atribución-No Comercial-Sin Obras Derivadas):**

- **Explicación:** La más restrictiva. Solo puedes compartirla, sin cambios y sin fines comerciales. Menciona al autor.

# 6.5 ¿Qué es el Software Libre? (ENTRA EN EXAMEN)

El "software libre" es aquel software que respeta la libertad de los usuarios y de la comunidad.

Según la definición de la Free Software Foundation, un programa se considera 
"software libre" si garantiza a sus usuarios cuatro libertades fundamentales:

### **Libertad 0: La libertad de ejecutar el programa como se desee, con cualquier propósito.**

- **Explicación:** No existen restricciones sobre cómo o dónde puedes usar el software.
Puedes utilizarlo para fines personales, educativos, comerciales,
gubernamentales, etc., sin necesidad de pedir permiso.

### **Libertad 1: La libertad de estudiar cómo funciona el programa y cambiarlo para que haga lo que usted quiera.**

- **Explicación:** Esta libertad te permite adaptar el programa a tus necesidades específicas. Para que esto sea posible, es una **condición indispensable tener acceso al código fuente** del programa.

### **Libertad 2: La libertad de redistribuir copias para ayudar a otros.**

- **Explicación:** Puedes compartir el programa con quien quieras. Puedes regalar, vender o simplemente pasar copias del software a amigos, colegas o al público en general.

### **Libertad 3: La libertad de distribuir copias de sus versiones modificadas a terceros.**

- **Explicación:** No solo puedes modificar el software para tu uso, sino que también
puedes compartir esas mejoras con la comunidad para que todos se
beneficien.

# 7. ESTADO DE LOS PROCESOS

**Procesos:**

Los diferentes estados de un proceso son:

- **En ejecución o activo:** Proceso activo es el que se ejecuta. En cada núcleo de un procesador, solo puede haber un proceso activo.
- **Preparado o espera:** Procesos preparados para su ejecución, pero que están a la espera de un procesador libre. (Hay otro proceso en ejecución)
    
    ![imagen.png](Apuntes%20sistemas/imagen.png)
    
- **Bloqueado o suspendido:** Las tareas que no pueden ejecutarse, p
orque necesitan un recurso que está ocupado.
    
    [](data:image/png;base64,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)
    
- **Muerto:** Un proceso está muerto cuando su ejecución ha terminado o el sistema operativo ha detectado un error fatal y lo ha transferido a dicho estado. Si se apaga el equipo por falta de alimentación eléctrica, todos los procesos pasan a muertos

# **7.1 Algoritmos FIFO, SJF y SRT**

### Algoritmo FIFO. Primero en llegar, primero en salir (First Input, First Output)

En este algoritmo, cuando un proceso pasa al estado en ejecución, se ejecuta hasta el final.

### Algoritmo SJF. Primero el trabajo más corto. (Shortest Job First)

De los procesos que están en estado preparado, se selecciona el que tiene menor tiempo de ejecución. Una vez que el trabajo se inicia, se ejecuta hasta el final.

### Algoritmo SRT. Tiempo restante más corto. (Shortest Remaining Time)

El planificador utiliza el criterio SJF, pero tiene en cuenta los nuevos procesos que puedan llegar al estado preparado. Por ejemplo, si se está ejecutando un proceso A que le quedan 3 instantes, pero llega un nuevo proceso B al estado preparado que solo necesita 1 instante, el proceso A pasa al estado preparado, mientras que B pasa a ejecución por necesitar menos tiempo. Es el primer algoritmo que utiliza la multiprogramación.

Observaciones:

- FIFO puede bloquear procesos muy cortos, por estar ejecutando uno muy largo.
- SJF y SRT puede bloquear procesos muy largos, por ejecutarse siempre los más cortos.
- FIFO y SJF dan malos resultados, pero son muy fáciles de implantar. Son los únicos algoritmos monoproceso, monotarea, no multiprogramados
- SRT se utiliza en los sistemas operativos actuales, pues da un tiempo medio de espera muy bueno.

### **Planificación Round Robin:**

El planificador asigna el procesador al primer proceso, pasado ese tiempo cuanto, se asigna el procesador al siguiente proceso preparado. Y así sucesivamente. El proceso que estaba en ejecución pasa a la cola de preparados.

Su ventaja, es que garantiza un tiempo de respuesta razonable a todos los procesos. Su desventaja, es que el tiempo que se pierde cada cuanto en cambiar de proceso, ralentiza el algoritmo.

### **Planificación por prioridades**

Cada proceso tiene asignada una prioridad y el de mayor prioridad en el estado listo es el que pasa a estar en ejecución. El valor de prioridad puede ser asignado por el usuario o el sistema. Asimismo, el usuario puede cambiar en cualquier momento la prioridad de un proceso.

Las prioridades son buenas, pero por si solas tienen el problema de que un proceso con poca prioridad no se ejecutaría nunca. Para solucionar este problema, se mejora el algoritmo utilizando prioridad por envejecimiento.

**Sincronización y comunicación de procesos**

Cuando se ejecutan varios procesos a la vez, es posible que estos compartan uno o varios recursos del sistema . El objetivo del sistema operativo es asegurar que si un proceso utiliza unos datos o recursos, estos datos o recursos se bloqueen. Este mecanismo de seguridad se denomina **exclusión mutua.**

Ejercicio1 para Round Robin 

| Proceso | Ciclo de llegada | Duración |
| --- | --- | --- |
| a | 0 | 4 |
| b | 1 | 3 |
| c | 3 | 3 |
| d | 5 | 4 |

**Quantum de 1**, lo que significa que cada proceso se ejecuta por 1 unidad de tiempo antes de ser interrumpido y movido al final de la cola de procesos listos.
Rafaga restante es “**Duración" total**

| 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13 | 14 | 15 | 16 |  |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| X | 1 |  | 2 |  |  |  | 3 |  |  |  | 4f |  |  |  |  |  | A |
|  | X | 1 |  |  | 2 |  |  |  | 3f |  |  |  |  |  |  |  | B |
|  |  |  | X | 1 |  |  |  | 2 |  |  |  | 3f |  |  |  |  | C |
|  |  |  |  |  | X | 1 |  |  |  | 2 |  |  | 3 | 4f |  |  | D |

# 8. ARRANQUE Y PARTICIONES

# 8.1 Secuencia de arranque

Es el proceso ordenado que realiza el ordenador desde que se enciende hasta que carga el sistema operativo. **Consta de las siguientes etapas generales:**

- **Suministro de energía:** Suministro y estabilización de los **voltajes de trabajo** por parte de la fuente de alimentación.
- **Inicio de la BIOS:** Localización de la dirección de inicio del programa BIOS.
- **Ejecución del POST:** Se ejecuta la primera subrutina grabada en la BIOS (ver punto siguiente).
    - En caso de detectar un error, se comunicará a través de, por ejemplo, un **código de pitidos** y se detendrá el proceso de arranque.
- **Detección de Vídeo:** Se detecta la tarjeta de vídeo y se le cede el control temporalmente. En este momento se muestra información en pantalla:
    - Tipo y versión de la **BIOS**.
    - Tipo y características de la **CPU**.
    - Cantidad de memoria **RAM** instalada tras ser testeada.
    - **Tecla de pulsación** para entrar en el setup de la BIOS (depende de la marca).
    - Comprobación de presencia de **teclado y ratón**.
    - Lectura de valores de la **CMOS** (periféricos y fecha/hora del sistema).
- **Carga del Sistema Operativo:** Se localiza un dispositivo capaz de almacenar un sistema operativo en el **orden establecido en la boot sequence** y se le cede el control final.

# 8.2 El POST (Power On Self Test)

Es la fase de autocomprobación inicial. **Se encarga de verificar el hardware conectado antes de iniciar el software.**

- **Funcionamiento:**
    - **Verificación:** Todas las BIOS comprueban las partes principales del equipo, aunque no siempre en el mismo orden.
    - **Adaptabilidad:** El POST se adapta a las prestaciones específicas de la placa base en la que está funcionando.
    - **Finalización:** Una vez hayan finalizado las comprobaciones, **si no ha fallado ningún test**, pasa el testigo al **BOOT** para que cargue el sistema operativo.
- **Variedad:**
    - La variedad de modelos y versiones de BIOS hace que haya una **gran gama de POST** diferentes (distintos pitidos, mensajes o velocidades de chequeo).

# 8.3 UEFI (Unified Extensible Firmware Interface)

Es el estándar moderno que ha sustituido a la BIOS tradicional. **Es una interfaz entre el sistema operativo y el firmware del hardware.**

- **Características Principales:**
    - **Sustituto de la BIOS:** Reemplaza la antigua interfaz del Sistema Básico de Entrada y Salida (BIOS) utilizada en los PC desde los tiempos de IBM.
    - **Estándar Abierto:** Es una especificación pública, no cerrada de una sola marca.
    - **Interfaz Gráfica:** A diferencia de la pantalla azul y gris de la BIOS, la UEFI proporciona **menús gráficos avanzados**, uso de ratón y mayor facilidad de uso.
    - **Funciones Extra:** Permite acceso remoto para solución de problemas o mantenimiento sin entrar al sistema operativo.
    - **Origen:** Es una evolución estandarizada del modelo **EFI**, desarrollada originalmente por Intel.
    
    Para un sistema moderno (UEFI), las tres particiones que deberías crear son:
    
    | Partición | Punto de Montaje | Sistema de Archivos | Tamaño Recomendado | Propósito Principal |
    | --- | --- | --- | --- | --- |
    | **EFI** | /boot/efi | FAT32 | 500 MB - 1 GB | **Obligatoria en sistemas UEFI.** Contiene el gestor de arranque para que el ordenador sepa cómo iniciar Ubuntu. |
    | **Raíz (Root)** | / | ext4 | 30 GB - 50 GB | Aquí se instala el sistema operativo, los programas y las aplicaciones. Con este tamaño tienes espacio de sobra. |
    | **Personal (Home)** | /home | ext4 | El resto del espacio del disco | **La más importante.** Aquí se guardan todos tus archivos personales: documentos, descargas, música, fotos, y la configuración de tus programas. |
    
    **La gran ventaja de esta configuración:** Si en el futuro necesitas reinstalar Ubuntu o cambiar de distribución, puedes formatear la partición raíz (/) sin tocar la partición /home. ¡Esto significa que **no perderás ninguno de tus archivos personales**!
    
    ### ¿Y la partición SWAP?
    
    Antes era obligatorio crear una partición de intercambio (SWAP), que actúa como una "memoria RAM virtual" en el disco duro.
    
    - **Actualmente, Ubuntu 22.04 ya no necesita una partición SWAP.** Por defecto, crea un **archivo de intercambio (swapfile)** dentro de la partición raíz (/). Esto es más flexible y funciona igual de bien para la mayoría de los casos.

# 8.4 Secuencia de Arranque UEFI

El proceso de encendido en UEFI es más sofisticado y seguro que en la BIOS antigua. Se divide en 5 pasos clave:

1. **Encendido del sistema:** Se suministra energía a los componentes.
2. **POST (Power-On Self Test):**
    - El firmware EFI se inicia.
    - **Diferencia Clave:** Tiene capacidad nativa para gestionar discos con sistema de **particionado GPT** (el estándar moderno que permite discos de más de 2 TB), a diferencia del antiguo MBR.
3. **Secure Boot (Arranque Seguro):**
    - Si está activo, **verifica las firmas digitales** antes de ejecutar cualquier binario de arranque (.EFI). Esto evita que virus o *malware* arranquen antes que el sistema operativo.
4. **Ejecución del Gestor de Arranque (Bootloader) desde la ESP:**
    - UEFI no busca en el "sector 0" del disco (como hacía la BIOS), sino que
    busca archivos concretos dentro de una partición especial llamada **ESP (EFI System Partition)**.
5. **Carga del Kernel:** Finalmente, se carga el núcleo del sistema operativo y el usuario toma el control.

## **8.5 MBR (Master Boot Record)**

Es el sistema de particionado más antiguo, introducido en 1983. Funciona con el sistema de arranque tradicional llamado **BIOS**.

- **Limitaciones principales:**
    - **Número de particiones:** Solo permite un máximo de 4 particiones primarias. Si necesitas más, tienes que convertir una de esas cuatro en una
    "partición extendida" para poder crear más divisiones lógicas dentro de
    ella.
    - **Tamaño del disco:** Solo puede manejar discos de hasta 2 TB (Terabytes) de tamaño.
- **Fiabilidad:**
    - La información de arranque y la tabla de particiones se guardan en un solo lugar al principio del disco, el "sector de arranque maestro".

## **8.6 GPT (GUID Partition Table)**

Es el estándar más nuevo y está asociado con el sistema de arranque moderno llamado **UEFI**, que ha reemplazado a la BIOS en la mayoría de los ordenadores actuales.

- **Ventajas sobre MBR:**
    - **Número de particiones:** Permite crear un número mucho mayor de particiones. Por ejemplo, Windows permite hasta 128 particiones primarias en un
    disco GPT, eliminando la necesidad de particiones extendidas.
    - **Tamaño del disco:** No tiene el límite de 2 TB del MBR. Puede manejar discos de tamaños enormes (teóricamente hasta 9.4)
    Zettabytes, que es un millón de veces más grande que un Terabyte).
- **Fiabilidad:**
    - GPT es mucho más robusto. Guarda una copia de seguridad de la tabla de particiones al final del disco. Si la tabla principal se corrompe, el sistema puede usar la copia para recuperar la información.
    - Utiliza un sistema de comprobación de errores (CRC32) para detectar si los
    datos de la cabecera se han dañado y, de ser así, intentar
    recuperarlos.

### **Tabla Comparativa Sencilla**

| Característica | MBR (Master Boot Record) | GPT (GUID Partition Table) |
| --- | --- | --- |
| **Edad** | Estándar antiguo (1983) | Estándar moderno |
| **Sistema de Arranque** | BIOS (Legacy) | UEFI |
| **Máximo de Particiones** | 4 particiones primarias | 128 particiones (en Windows) |
| **Tamaño Máximo del Disco** | 2 TB | Prácticamente ilimitado (9.4 ZB) |
| **Fiabilidad** | Baja (un solo punto de fallo) | Alta (copias de seguridad y comprobación de errores) |
| **Compatibilidad** | Compatible con sistemas operativos muy antiguos. | Requerido para sistemas modernos como Windows 11. |

# 9. SISTEMAS DE FICHEROS

## 9.1 Estructura Lógica: FAT32 (File Allocation Table)

Es un sistema de archivos antiguo y sencillo. Aunque ha sido reemplazado 
por NTFS en discos grandes, sigue siendo el estándar para **memorias USB y tarjetas SD** debido a su gran **compatibilidad**.

- **Estructura de una partición FAT32:**
    - **Boot Sector (Sector de Arranque):**
        - Contiene **información crítica** del sistema (tamaño del clúster, ubicación de la FAT y la tabla de particiones).
        - Si es una partición de arranque, contiene el código necesario para iniciar el sistema operativo.
    - **FAT (Tabla de Asignación de Archivos):**
        - Es el "mapa" del disco. Se divide en **dos copias** por seguridad (redundancia).
        - Cada entrada es de **32 bits** y representa el estado de un clúster (libre, ocupado o fragmentado).
        - Funciona como una **cadena**: cada clúster apunta al siguiente para formar un archivo completo.
    - **Zona de Datos (Data Region):**
        - Es donde se almacenan efectivamente los **archivos y directorios**.
        - **Región de Directorios:** Una zona específica que guarda los "metadatos": **nombre de archivo, atributos, fecha y ubicación** (puntero al primer clúster).
- **Concepto Clave:**
    - **Clúster:** Es la **unidad mínima de almacenamiento**. Es un conjunto contiguo de sectores (generalmente 512 bytes por
    sector). Un archivo siempre ocupará al menos un clúster completo, aunque sea muy pequeño.
- **Limitaciones de FAT32 (¡Datos de Examen!):**
    - **Tamaño máximo de archivo:** **4 GB**. (No puedes guardar un archivo individual mayor a esto).
    - **Tamaño máximo de partición:** Teóricamente soporta hasta **8 TB**, aunque las herramientas de formateo de Windows suelen limitarlo artificialmente a **32 GB**.

## 9.2 NTFS (New Technology File System)

Es el sistema de archivos estándar utilizado por **Windows** en discos modernos. Fue diseñado para superar las limitaciones de tamaño y seguridad de FAT32.

- **Estructura Lógica:**
    - **MFT (Master File Table):** Es el componente más importante (el corazón de NTFS). Es una tabla que contiene una **entrada para cada archivo** del disco con toda su información (nombre, tamaño, permisos, ubicación).
    - **Boot Sector:** Contiene la información crítica para arrancar y localizar la MFT.
    - **Clústeres:** Al igual que FAT32, usa clústeres (generalmente de 4 KB), pero su gestión es mucho más eficiente para reducir la fragmentación.
- **Características Avanzadas (Ventajas):**
    - **Seguridad (ACLs):** Soporta **permisos de archivo**, definiendo qué usuarios pueden acceder o modificar cada archivo.
    - **Journaling ($LogFile):** Es un sistema de **recuperación ante fallos**. Registra las transacciones antes de hacerlas; si se va la luz o el
    equipo se cuelga, el sistema puede recuperarse sin perder datos.
    - **Atributos extendidos:** Permite **compresión** y **cifrado** de archivos de forma nativa.
    - **Tamaño:** Gestiona archivos y particiones inmensas (mucho más de 4GB).

## 9.3 Diferencias Clave: FAT32 vs NTFS (Tabla de Examen)

Esta tabla resume las diferencias técnicas entre el sistema antiguo (compatible) y el moderno (robusto).

| Característica | FAT32 | NTFS |
| --- | --- | --- |
| **Estructura básica** | Boot Sector, FAT, Data Region, Directory Region | Boot Sector, **MFT**, Data Region, $LogFile, Atributos |
| **Tamaño máximo de archivo** | **4 GB** *(Limitación clave)* | Teóricamente ilimitado<br>*(Prácticamente hasta **16 TB**)* |
| **Tamaño máximo de partición** | **8 TB** (A veces limitado a 32 GB por Windows) | **8 PB** (Petabytes) |
| **Seguridad** | No soporta permisos avanzados | **Soporta permisos (ACLs)**, cifrado y control de acceso |
| **Journaling** | **No soporta** *(Si falla, se pueden corromper datos)* | **Soporta Journaling** a través de $LogFile |
| **Compatibilidad** | **Alta** (Windows, Mac, Linux, Consolas, TV) | Principalmente **Windows** *(Otros sistemas solo leen, o requieren drivers)* |
| **Fragmentación** | Más susceptible | Menos susceptible (se organiza mejor) |

# 10 Resumen de Comandos Linux

Estos comandos se introducen en el **Shell** (normalmente BASH) para interactuar con el sistema operativo. Recuerda que Linux distingue entre **mayúsculas y minúsculas**.

| Comando | Descripción Corta | Ejemplos / Opciones Clave |
| --- | --- | --- |
| **man** | Muestra el **manual** de ayuda de un comando. | man pwd (Para salir pulsa q) |
| **pwd** | Muestra la ruta del **directorio actual** de trabajo. | pwd |
| **ls** | **Lista** el contenido de los directorios. | ls -l (detallado), ls -a (ocultos), ls -1 (una columna). |
| **cd** | **Cambia** de directorio. | cd .. (atrás), cd ~ (al home/inicio), cd / (raíz). |
| **mkdir** | **Crea** nuevos directorios (carpetas). | mkdir carpeta1, mkdir /ruta/nueva. |
| **cat** | Muestra (concatena) el contenido de un fichero. También sirve para crear ficheros. | cat archivo.txt (leer), cat > nuevo.txt (crear/escribir). |
| **rm** | **Borra** ficheros o directorios. **¡Cuidado, no pide confirmación!** | rm archivo.txt, rm -r carpeta (borrado recursivo de carpeta). |
| **rmdir** | Borra directorios **solo si están vacíos**. | rmdir carpeta_vacia |
| **cp** | **Copia** ficheros y directorios. | cp origen destino, cp doc1.txt /home/copias/ |
| **mv** | **Mueve** ficheros (cambia su ubicación) o los **renombra**. | mv antiguo.txt nuevo.txt (renombrar), mv archivo.txt /ruta/ (mover). |
- **Prompt:** Es el aviso del terminal (ej: sysadmin@localhost:~$) que indica usuario, equipo y ruta actual (~ es home).
- **Formato:** comando [opciones] [argumentos] (ej: ls -l /home).

## 10.1 Estandar jerquico del sistema de archivo

En Linux, todo cuelga de una única raíz. Es vital saber dónde se guarda cada tipo de fichero.

| Directorio | Descripción y Función |
| --- | --- |
| **/** (Root) | **Jerarquía primaria (Raíz).** Es el contenedor de todo el sistema. De aquí cuelgan todos los demás directorios. |
| **/home** | Contiene los **directorios personales** de los usuarios (sus documentos, fotos, etc.). |
| **/boot** | Archivos necesarios para el **arranque del sistema** (Kernel de Linux y gestor **GRUB**). *¡Ojo! Tocar aquí puede hacer que el PC no arranque.* |
| **/etc** | Contiene los ficheros de **configuración** del sistema y utilidades de administración. |
| **/bin** | **Binarios esenciales (Comandos básicos).** Disponibles para todos los usuarios. Son vitales para el arranque (Ej: cat, ls, cp, rm, mkdir). |
| **/sbin** | **System Binaries.** Ejecutables de uso exclusivo para el **superusuario (root)**. Necesarios para arrancar y montar el sistema. |
| **/media** | Puntos de montaje para **medios extraíbles** automáticos (USB, CD-ROM, lectores). |
| **/mnt** | Sistema de archivos montados **temporalmente**. Similar a /media pero usado manualmente por el usuario o administrador para montajes puntuales. |
| **/usr** | Contiene programas y bibliotecas instalados por los usuarios. **No son estrictamente necesarios para el arranque**, pero sí para el uso normal de aplicaciones. |
| **/usr/bin** | Contiene la mayoría de comandos y ejecutables para usuarios regulares que **no son esenciales para el arranque** (Ej: python, gcc). |
| **/proc** | Sistema de archivos **virtual**. No está en el disco duro, sino en la RAM. Proporciona información en tiempo real sobre los **procesos** y el kernel. |

# 11 Virtualización: Conceptos Básicos

La virtualización consiste en la **creación lógica (por software)** de un recurso tecnológico (un ordenador completo, un servidor, etc.) dentro de otro físico.

- **Ventajas Principales:**
    - Ejecución de múltiples sistemas operativos a la vez.
    - Creación de entornos seguros de pruebas (Sandbox).
    - Reducción de costes y hardware.
    - Recuperación ante desastres y Alta Disponibilidad.
- **Terminología Clave:**
    - **Anfitrión (Host):** Es el ordenador **físico** donde instalamos el programa (VirtualBox). Pone el hardware (RAM, CPU).
    - **Invitado (Guest):** Es el sistema operativo que corre **dentro** de la máquina virtual.
    - **Máquina Virtual (VM):** Es el entorno generado que simula ser un ordenador real.

# 11.1 Formatos de Disco Duro Virtual (¡Ojo a las siglas!)

Al crear una máquina en VirtualBox, podemos elegir el tipo de archivo para el disco duro:

- **VDI:** Es el formato **nativo de VirtualBox**.
- **VMDK:** Formato nativo de **VMware**, pero compatible con VirtualBox. (Permite dividir el disco en trozos de 2GB).
- **VHD:** Formato nativo de **Microsoft Virtual PC**.
- **VHDX:** Sucesor del anterior (Windows Server 2012). **VirtualBox NO es compatible con VHDX.**

# 11.2 Configuración de RED en VirtualBox (¡MUY IMPORTANTE!)

El adaptador de red define cómo se comunica la máquina virtual con el mundo exterior y con otras máquinas. Se configura con la máquina apagada.

Aquí tienes los **5 modos** explicados al detalle:

## 1. NAT (Network Address Translation)

- **Es el modo por defecto.**
- **Funcionamiento:** La máquina virtual sale a internet "oculta" detrás de la IP del anfitrión.
- **Uso:** Ideal para **navegar por internet**, descargar archivos o leer correo.
- **Limitación:** Desde fuera no pueden "ver" a la máquina virtual (aislamiento básico).

## 2. Adaptador Puente (Bridged Adapter)

- **Funcionamiento:** Simula que la tarjeta virtual está **conectada al mismo switch físico** que el anfitrión.
- **Efecto:** La Máquina Virtual (MV) obtiene su propia IP de la red real. Se comporta como **un equipo físico más** en tu casa/clase.
- **Uso:** Si quieres que tu servidor virtual sea accesible por otros ordenadores de tu red física.

## 3. Red Interna (Internal Network)

- **Funcionamiento:** Es una red **totalmente aislada**.
- **Comunicación:** Las máquinas virtuales **solo pueden hablar entre ellas** (si están en la misma "Red interna").
- **Aislamiento:** NO tienen internet y NO pueden hablar con el anfitrión. Es un entorno cerrado ("burbuja").

## 4. Red NAT

- **Funcionamiento:** Funciona como el router de casa. Crea una subred donde **varias máquinas virtuales pueden verse entre sí** y, a la vez, salir a internet mediante NAT.
- **Diferencia con NAT normal:**
    - *NAT normal:* Aísla la máquina (es "egoísta").
    - *Red NAT:* Permite crear una pequeña red de equipos que se ven y salen juntos a internet.

## 5. Adaptador Sólo-Anfitrión (Host-Only)

- **Funcionamiento:** Crea una red privada entre el **Anfitrión (tu PC)** y las **Máquinas Virtuales**.
- **Diferencia con Red Interna:** Aquí el anfitrión SÍ participa en la red.
- **Uso:** Pruebas de red seguras donde necesitas acceder a la máquina virtual desde tu PC físico sin exponerla a internet.