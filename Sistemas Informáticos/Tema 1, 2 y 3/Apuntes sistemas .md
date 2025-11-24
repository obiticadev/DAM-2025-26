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