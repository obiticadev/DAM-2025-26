
*   <mark style="background-color: #ffff00;">Amarillo</mark>: Para conceptos fundamentales, definiciones y puntos clave.
*   <mark style="background-color: #90ee90;">Verde</mark>: Para ventajas, objetivos y características positivas.
*   <mark style="background-color: #add8e6;">Azul</mark>: Para tipos, clasificaciones, componentes y ejemplos.
*   <mark style="background-color: #ffa07a;">Rojo/Salmón</mark>: Para problemas, inconvenientes o limitaciones.
*   <mark style="background-color: #d3d3d3;">Gris</mark>: Para tecnologías específicas, nombres propios o estándares.

***

# **Guía Estructurada sobre Almacenamiento de Información y Bases de Datos**

## **Parte I: Fundamentos del Almacenamiento de Información Basado en Ficheros**

Esta sección aborda los conceptos iniciales de almacenamiento de datos, centrados en la organización y gestión mediante ficheros, que <mark style="background-color: #ffff00;">sentaron las bases para los sistemas de información posteriores</mark>.

### **1. Introducción al Almacenamiento de Datos**

El <mark style="background-color: #ffff00;">almacenamiento y gestión de datos es una constante en la vida cotidiana</mark>, presente en acciones como seleccionar un canal de TDT, usar la agenda del móvil, operar en un cajero automático o consultar información en Internet. La evolución desde los archivos en papel en los años setenta hasta los sistemas digitales actuales ha sido impulsada por la <mark style="background-color: #ffff00;">necesidad de un tratamiento más eficiente de la información</mark>.

*   **Evolución Histórica:** La primera informatización en la década de los setenta se centró en la contabilidad y facturación, <mark style="background-color: #ffff00;">trasladando los datos del papel a un formato digital para un acceso más rápido</mark>. La terminología (ficheros, carpetas, formularios) se adaptó del entorno manual.
*   **Concepto de Fichero:** Es el <mark style="background-color: #ffff00;">elemento que permite el almacenamiento permanente de datos</mark> en dispositivos de memoria masiva para su posterior recuperación, consulta y procesamiento.
*   **Estructura de un Fichero:**
    *   <mark style="background-color: #add8e6;">Registros</mark>: Contienen datos relativos a un mismo elemento u objeto (p. ej., datos de un usuario).
    *   <mark style="background-color: #add8e6;">Campos</mark>: Divisiones dentro de un registro que contienen información elemental (p. ej., nombre o email del usuario).
*   **Conceptos de Transferencia:**
    *   <mark style="background-color: #ffff00;">Bloque</mark>: Cantidad de información transferida entre el soporte de almacenamiento y la memoria principal en una <mark style="background-color: #ffff00;">única operación de lectura/escritura</mark>.
    *   <mark style="background-color: #ffff00;">Empaquetado (Blocaje)</mark>: Operación de agrupar varios registros lógicos en un bloque.
    *   <mark style="background-color: #ffff00;">Factor de Blocaje</mark>: Número de registros que caben en un bloque.

### **2. Clasificación de Ficheros**

Los ficheros se pueden clasificar <mark style="background-color: #ffff00;">según la función que desempeñan</mark> dentro de una aplicación.

| Tipo de Fichero | Subtipo | Descripción | Ejemplo |
| :--- | :--- | :--- | :--- |
| **Permanentes** | <mark style="background-color: #add8e6;">Maestros</mark> | Contienen el <mark style="background-color: #ffff00;">estado actual de los datos</mark> que pueden ser modificados por la aplicación. | Fichero de datos de usuarios de una plataforma educativa. |
| | <mark style="background-color: #add8e6;">Constantes</mark> | Incluyen <mark style="background-color: #ffff00;">datos fijos que no suelen ser modificados</mark> y se acceden para consulta. | Fichero con códigos postales. |
| | <mark style="background-color: #add8e6;">Históricos</mark> | Almacenan datos que fueron actuales en un momento anterior; se usan para reconstruir situaciones. | Fichero con los usuarios dados de baja de una plataforma. |
| **Temporales** | <mark style="background-color: #add8e6;">Intermedios</mark> | Almacenan resultados de una aplicación que serán utilizados por otra. | |
| | <mark style="background-color: #add8e6;">De maniobra</mark> | Guardan datos de una aplicación que no caben en memoria principal por falta de espacio. | |
| | <mark style="background-color: #add8e6;">De resultados</mark> | Acumulan datos que serán transferidos a un dispositivo de salida. | |

### **3. Soportes Físicos de Información**

La <mark style="background-color: #ffff00;">evolución del hardware ha sido clave</mark> en el desarrollo de los sistemas de almacenamiento.

*   <mark style="background-color: #add8e6;">Soportes Secuenciales</mark>: El acceso a los datos se realiza de <mark style="background-color: #ffff00;">forma lineal, desde el principio hasta la posición deseada</mark>. Se utilizan principalmente para <mark style="background-color: #d3d3d3;">copias de seguridad</mark>.
    *   *Ejemplo: Cintas magnéticas.*
*   <mark style="background-color: #add8e6;">Soportes Direccionables (de Acceso Directo)</mark>: Permiten <mark style="background-color: #ffff00;">posicionarse directamente en la ubicación del dato de interés</mark> sin recorrer la información previa. Son los <mark style="background-color: #90ee90;">más empleados en la actualidad</mark>.
    *   *Ejemplos: Discos magnéticos, discos ópticos, discos magneto-ópticos, unidades de estado sólido (SSD).*

### **4. Métodos de Acceso y Organización de Ficheros**

La organización de un fichero define cómo se estructuran y acceden sus registros. Los objetivos principales son <mark style="background-color: #90ee90;">proporcionar acceso rápido, economizar almacenamiento, facilitar actualizaciones y reflejar la organización real de la información</mark>.

#### **4.1. Organización Secuencial**

Los registros <mark style="background-color: #ffff00;">se almacenan de forma contigua y se leen uno tras otro desde el inicio</mark>.

*   **Identificador:** Utiliza una marca de fin de fichero (<mark style="background-color: #d3d3d3;">EOF</mark>).
*   **Campo Clave:** Un campo específico que, si se usa para ordenar el fichero, agiliza las operaciones.
*   **Características Principales:**
    *   Lectura siempre hacia adelante.
    *   <mark style="background-color: #ffa07a;">Monousuario</mark> (no permite acceso simultáneo).
    *   Estructura rígida de campos.
    *   <mark style="background-color: #90ee90;">Aprovechamiento máximo del espacio</mark> de almacenamiento.
    *   <mark style="background-color: #ffa07a;">No se pueden insertar registros</mark> entre los ya grabados.
    *   Puede grabarse en cualquier tipo de soporte.

#### **4.2. Organización de Acceso Directo (Aleatorio)**

Permite <mark style="background-color: #ffff00;">acceder a un registro directamente</mark> indicando su posición relativa o, más comúnmente, <mark style="background-color: #ffff00;">a través de una clave</mark>.

*   **Requisito:** Debe almacenarse en <mark style="background-color: #d3d3d3;">dispositivos de acceso directo</mark>.
*   **Acceso:** Una transformación aplicada a la clave obtiene la dirección física del registro. En el modo más rápido, la clave numérica coincide con la dirección.
*   **Características Principales:**
    *   <mark style="background-color: #90ee90;">Posicionamiento inmediato</mark>.
    *   Registros de longitud fija.
    *   Permite apertura en modo mixto (lectura y escritura).
    *   <mark style="background-color: #90ee90;">Soporta múltiples usuarios</mark>.
    *   Permite la <mark style="background-color: #90ee90;">actualización de registros en tiempo real</mark>.

#### **4.3. Organización Indexada**

Se basa en el <mark style="background-color: #ffff00;">uso de índices, que son tablas que relacionan la clave de un registro con su posición física</mark> en el fichero.

*   **Estructura:** Consta de una <mark style="background-color: #add8e6;">zona de datos</mark> (con los registros) y una <mark style="background-color: #add8e6;">zona de índices</mark> (tabla de claves y direcciones, ordenada por clave).
*   **Funcionamiento:** Para encontrar un registro, se busca su clave en la tabla de índices (cargada en memoria) para obtener la dirección y luego se accede directamente a la zona de datos.
*   **Claves:** Un campo debe servir como <mark style="background-color: #ffff00;">clave primaria (identificador único)</mark>. Pueden existir claves alternativas.
*   **Modos de Acceso:** Permite tanto el <mark style="background-color: #90ee90;">acceso directo</mark> (a través del índice) como el <mark style="background-color: #90ee90;">secuencial</mark> (leyendo los registros ordenados según la clave del índice).

#### **4.4. Otras Organizaciones de Ficheros**

*   **Secuencial Indexada (Parcialmente Indexada):** <mark style="background-color: #ffff00;">Combina las organizaciones secuencial e indexada</mark>. La zona de datos se divide en segmentos (bloques), y el índice apunta al inicio de cada segmento. La búsqueda es <mark style="background-color: #ffff00;">directa hasta el segmento y secuencial dentro de él</mark>. Es muy utilizada por su flexibilidad.
*   **Acceso Calculado (Hashing):** Utiliza una <mark style="background-color: #ffff00;">función de hashing para convertir la clave directamente en su dirección física</mark>, eliminando la necesidad de una tabla de índices y agilizando el acceso.
    *   **Problema:** Las <mark style="background-color: #ffa07a;">colisiones</mark> ocurren cuando diferentes claves (llamadas sinónimos) generan la misma dirección. Se resuelven con técnicas como <mark style="background-color: #add8e6;">zonas de excedentes</mark>.

### **5. Parámetros de Utilización de Ficheros**

Estos parámetros ayudan a determinar qué tipo de organización de fichero es más adecuada según su uso.

*   **Capacidad:** Espacio total que ocupa el fichero.
*   **Actividad:** Frecuencia de consultas y modificaciones. Se mide con la <mark style="background-color: #add8e6;">Tasa de Actividad</mark> (% de registros afectados) y la <mark style="background-color: #add8e6;">Frecuencia de Acceso</mark>.
*   **Volatilidad:** Frecuencia de inserciones y borrados. Se mide con la <mark style="background-color: #add8e6;">Tasa de Renovación</mark> (% de registros renovados) y la <mark style="background-color: #add8e6;">Frecuencia de Renovación</mark>.
*   **Crecimiento:** Variación de la capacidad del fichero, medida por la <mark style="background-color: #add8e6;">Tasa de Crecimiento</mark>.

***

## **Parte II: Sistemas de Bases de Datos**

Esta sección explora el concepto de base de datos como una evolución de los sistemas de ficheros, <mark style="background-color: #90ee90;">solucionando problemas como la redundancia y la inconsistencia de datos</mark>.

### **1. Introducción a las Bases de Datos (BD)**

Una base de datos es una <mark style="background-color: #ffff00;">"colección de datos relacionados lógicamente entre sí, con una definición y descripción comunes y que están estructurados de una determinada manera"</mark>. Su objetivo es almacenar datos con <mark style="background-color: #90ee90;">mínima redundancia</mark> y permitir un <mark style="background-color: #90ee90;">acceso eficiente</mark>.

*   **Componentes Clave:**
    *   <mark style="background-color: #add8e6;">Datos</mark>: La información en sí.
    *   <mark style="background-color: #add8e6;">Metadatos</mark>: <mark style="background-color: #ffff00;">Datos que describen los datos</mark> (su estructura, tipos, relaciones). Se almacenan en el <mark style="background-color: #d3d3d3;">diccionario de datos o catálogo</mark> y permiten la <mark style="background-color: #90ee90;">independencia entre la lógica del programa y el almacenamiento físico</mark>.
*   **Elementos Estructurales:**
    *   <mark style="background-color: #add8e6;">Entidades</mark>: Objetos del mundo real o abstractos sobre los que se almacena información (ej. un cliente, un producto).
    *   <mark style="background-color: #add8e6;">Atributos</mark>: Propiedades o características de una entidad (ej. nombre, precio).
    *   <mark style="background-color: #add8e6;">Registros</mark>: Conjunto de atributos que describen una instancia de una entidad.

### **2. Ventajas de las Bases de Datos**

| Ventaja | Descripción |
| :--- | :--- |
| <mark style="background-color: #90ee90;">Acceso Múltiple</mark> | Varios usuarios o aplicaciones pueden acceder a los datos simultáneamente. |
| <mark style="background-color: #90ee90;">Utilización Múltiple</mark> | Cada usuario puede tener una visión particular de la estructura de la BD. |
| <mark style="background-color: #90ee90;">Flexibilidad</mark> | El acceso a la información se puede establecer de diferentes maneras. |
| <mark style="background-color: #90ee90;">Confidencialidad y Seguridad</mark> | El control de acceso impide que usuarios no autorizados utilicen la BD. |
| <mark style="background-color: #90ee90;">Protección contra Fallos</mark> | Existen mecanismos para la recuperación de la información en caso de error. |
| <mark style="background-color: #90ee90;">Independencia Física</mark> | Cambios en el soporte físico no afectan a la base de datos o aplicaciones. |
| <mark style="background-color: #90ee90;">Independencia Lógica</mark> | Cambios en la estructura lógica no afectan a las aplicaciones que la usan. |
| <mark style="background-color: #90ee90;">Redundancia Controlada</mark> | <mark style="background-color: #ffff00;">Los datos se almacenan una única vez</mark>, reduciendo inconsistencias. |
| <mark style="background-color: #90ee90;">Interfaz de Alto Nivel</mark> | Se utilizan lenguajes de alto nivel para un uso más sencillo y cómodo. |
| <mark style="background-color: #90ee90;">Consulta Directa</mark> | Existen herramientas para poder acceder a los datos interactivamente. |

### **3. Usos y Usuarios de las Bases de Datos**

*   **Tipos de Usuarios:**
    *   <mark style="background-color: #add8e6;">Administrador (DBA)</mark>: Responsable de la implementación física, seguridad y rendimiento.
    *   <mark style="background-color: #add8e6;">Diseñadores</mark>: Definen la estructura lógica y los procesos de la base de datos.
    *   <mark style="background-color: #add8e6;">Programadores de Aplicaciones</mark>: Crean los programas que interactúan con la BD.
    *   <mark style="background-color: #add8e6;">Usuarios Finales</mark>: Consumen la información a través de las aplicaciones.
*   **Ámbitos de Aplicación:** Las bases de datos son <mark style="background-color: #ffff00;">omnipresentes</mark> en sectores como la banca, líneas aéreas, telecomunicaciones, medicina, justicia, hostelería, ocio y cultura.

### **4. Ubicación y Almacenamiento de la Información**

Los sistemas de almacenamiento más utilizados para bases de datos incluyen:

*   **Discos <mark style="background-color: #d3d3d3;">SATA</mark> y <mark style="background-color: #d3d3d3;">SCSI</mark>:** Interfaces estándar para discos duros, con SCSI orientado a entornos de alto rendimiento.
*   **<mark style="background-color: #d3d3d3;">RAID</mark> (Redundant Array of Independent Disks):** Tecnología que <mark style="background-color: #ffff00;">combina múltiples discos para mejorar el rendimiento, la seguridad o ambos</mark>.
*   **<mark style="background-color: #d3d3d3;">NAS</mark> (Network Attached Storage):** Dispositivo de almacenamiento conectado a una red que permite el <mark style="background-color: #ffff00;">acceso centralizado</mark> a los datos.
*   **<mark style="background-color: #d3d3d3;">SAN</mark> (Storage Area Network):** <mark style="background-color: #ffff00;">Red de alta velocidad dedicada exclusivamente a conectar servidores y dispositivos de almacenamiento</mark> masivo.

### **5. Modelos de Bases de Datos**

Un modelo de datos <mark style="background-color: #ffff00;">define la estructura lógica de una base de datos</mark>.

*   <mark style="background-color: #add8e6;">Modelo Jerárquico</mark>: Organiza los datos en una <mark style="background-color: #ffff00;">estructura de árbol (padre-hijo)</mark>, donde un hijo solo tiene un padre. Actualmente en desuso.
*   <mark style="background-color: #add8e6;">Modelo en Red</mark>: Similar al jerárquico, pero permite que <mark style="background-color: #ffff00;">un nodo hijo tenga múltiples padres</mark>, creando una estructura de grafo.
*   **<mark style="background-color: #add8e6;">Modelo Relacional</mark>:** Desarrollado por Codd en 1970, es el <mark style="background-color: #ffff00;">modelo más extendido</mark>. Organiza los datos en <mark style="background-color: #ffff00;">tablas bidimensionales</mark> (relaciones), compuestas por filas (tuplas) y columnas (atributos). Utiliza <mark style="background-color: #d3d3d3;">SQL (Structured Query Language)</mark> como lenguaje estándar.
*   <mark style="background-color: #add8e6;">Modelo Orientado a Objetos</mark>: Define la base de datos en términos de <mark style="background-color: #ffff00;">objetos, clases, herencia y polimorfismo</mark>, alineándose con los paradigmas de la programación orientada a objetos (POO).
*   **<mark style="background-color: #add8e6;">Otros Modelos</mark>:**
    *   **Objeto-Relacional:** Híbrido que combina la simplicidad del modelo relacional con las capacidades del modelo orientado a objetos.
    *   **Orientado a Documentos:** Almacena datos en documentos semi-estructurados (p. ej., <mark style="background-color: #d3d3d3;">JSON, XML</mark>). Ejemplos: MongoDB, CouchDB.
    *   **Multidimensional:** Estructura los datos en <mark style="background-color: #ffff00;">cubos (dimensiones) para análisis complejos (OLAP)</mark>.
    *   **Transaccional:** Optimizado para la velocidad en el procesamiento de un gran volumen de transacciones.

### **6. Sistemas Gestores de Bases de Datos (SGBD)**

Un SGBD (o DBMS en inglés) es el <mark style="background-color: #ffff00;">conjunto de software que permite a los usuarios crear, mantener y controlar el acceso a una base de datos</mark>.

*   **Funciones Principales:**
    *   <mark style="background-color: #add8e6;">Definición de Datos (DDL)</mark>: Permite crear y definir las estructuras de la base de datos.
    *   <mark style="background-color: #add8e6;">Manipulación de Datos (DML)</mark>: Permite insertar, modificar, borrar y consultar los datos.
    *   <mark style="background-color: #add8e6;">Control de Datos (DCL)</mark>: Gestiona la seguridad, los permisos de usuario y la integridad.
*   **Componentes del SGBD:** Incluye los lenguajes (<mark style="background-color: #add8e6;">DDL, DML, DCL</mark>), el <mark style="background-color: #add8e6;">diccionario de datos</mark>, el <mark style="background-color: #add8e6;">gestor de la base de datos (motor)</mark>, los <mark style="background-color: #add8e6;">usuarios</mark> y <mark style="background-color: #add8e6;">herramientas de administración</mark>.
*   **<mark style="background-color: #d3d3d3;">Arquitectura de Tres Niveles (ANSI/SPARC)</mark>:**
    *   <mark style="background-color: #add8e6;">Nivel Interno (Físico)</mark>: Cómo se almacenan físicamente los datos.
    *   <mark style="background-color: #add8e6;">Nivel Conceptual (Lógico)</mark>: Visión unificada de toda la base de datos (entidades, relaciones).
    *   <mark style="background-color: #add8e6;">Nivel Externo (De Visión)</mark>: Vistas parciales de la base de datos para usuarios específicos.
    *   *Esta arquitectura <mark style="background-color: #90ee90;">garantiza la independencia física y lógica de los datos</mark>.*

### **7. Sistemas Comerciales vs. Sistemas Libres**

| Tipo | SGBD Ejemplos | Descripción |
| :--- | :--- | :--- |
| **Comerciales** | <mark style="background-color: #d3d3d3;">Oracle, Microsoft SQL Server, DB2, Informix</mark> | Productos con licencia que ofrecen soporte y funcionalidades avanzadas, a menudo para grandes corporaciones. |
| **Libres** | <mark style="background-color: #d3d3d3;">MySQL, PostgreSQL, Firebird, SQLite, Apache Derby</mark> | Sistemas de <mark style="background-color: #ffff00;">código abierto</mark>, a menudo gratuitos, con una gran comunidad de soporte. Son multiplataforma y muy populares en desarrollo web y aplicaciones de diversos tamaños. |

### **8. Bases de Datos Centralizadas vs. Distribuidas**

#### **8.1. Bases de Datos Centralizadas**

La arquitectura completa del sistema (SGBD, datos) <mark style="background-color: #ffff00;">reside en un único computador</mark>.

*   **Ventajas:** <mark style="background-color: #90ee90;">Seguridad centralizada, mayor integridad, rendimiento optimizado para ese entorno, menor coste de mantenimiento</mark>.
*   **Inconvenientes:** <mark style="background-color: #ffa07a;">Punto único de fallo</mark> (si el sistema cae, todo se detiene), <mark style="background-color: #ffa07a;">dificultad de recuperación ante desastres</mark>, <mark style="background-color: #ffa07a;">menor potencia de cómputo</mark> comparada con sistemas distribuidos.

#### **8.2. Bases de Datos Distribuidas (BDD)**

Conjunto de múltiples bases de datos lógicamente relacionadas que se encuentran <mark style="background-color: #ffff00;">distribuidas en diferentes nodos de una red</mark>.

*   **Ventajas:** <mark style="background-color: #90ee90;">Mayor tolerancia a fallos</mark> (si un nodo cae, el resto sigue funcionando), <mark style="background-color: #90ee90;">acceso más rápido a los datos locales</mark>, <mark style="background-color: #90ee90;">escalabilidad</mark>, <mark style="background-color: #90ee90;">adaptación a la estructura geográfica</mark> de una organización.
*   **Inconvenientes:** <mark style="background-color: #ffa07a;">Mayor complejidad en la gestión</mark> (control de concurrencia, recuperación), <mark style="background-color: #ffa07a;">posibles brechas de seguridad</mark> entre nodos, <mark style="background-color: #ffa07a;">coste inicial más elevado</mark>.
*   **<mark style="background-color: #add8e6;">Fragmentación</mark>:** Técnica utilizada en BDD que consiste en <mark style="background-color: #ffff00;">particionar las tablas de la base de datos y distribuir los fragmentos</mark> en diferentes nodos de la red para optimizar las consultas y el almacenamiento. El objetivo es encontrar un nivel de particionamiento equilibrado.