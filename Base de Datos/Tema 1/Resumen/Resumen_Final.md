### **Leyenda de Marcadores**

*   <mark style="background-color: #ffff00;">Amarillo</mark>: Para conceptos fundamentales, definiciones y puntos clave.
*   <mark style="background-color: #90ee90;">Verde</mark>: Para ventajas, objetivos, propósitos y características positivas.
*   <mark style="background-color: #add8e6;">Azul</mark>: Para tipos, clasificaciones, componentes, estructuras y ejemplos.
*   <mark style="background-color: #ffa07a;">Rojo/Salmón</mark>: Para problemas, inconvenientes o limitaciones.
*   <mark style="background-color: #d3d3d3;">Gris</mark>: Para tecnologías específicas, nombres propios, estándares y secciones marcadas como "Contenido Prioritario".

***

# **Esquema Detallado sobre Almacenamiento de Información y Bases de Datos**

Este documento presenta un esquema jerárquico y detallado sobre los conceptos fundamentales del almacenamiento de información, desde los sistemas basados en ficheros hasta las arquitecturas de bases de datos modernas. El contenido se ha estructurado para <mark style="background-color: #90ee90;">facilitar la comprensión, el estudio y la planificación de proyectos</mark>, con un enfoque particular en los temas clave señalados como prioritarios.

## **Parte I: Sistemas de Almacenamiento Basados en Ficheros**

Esta sección aborda los métodos tradicionales de almacenamiento de datos, que <mark style="background-color: #ffff00;">sentaron las bases para los sistemas de información posteriores</mark>, centrados en la organización y gestión mediante ficheros.

### **1. Conceptos Fundamentales de Ficheros**

*   **Introducción:** El <mark style="background-color: #ffff00;">almacenamiento y gestión de datos es una constante en la vida cotidiana</mark>. La evolución desde los archivos en papel ha sido impulsada por la <mark style="background-color: #ffff00;">necesidad de un tratamiento más eficiente de la información</mark>. La primera informatización en los años setenta se centró en <mark style="background-color: #ffff00;">trasladar los datos del papel a un formato digital para un acceso más rápido</mark>.
*   **Definición:** Un fichero es el <mark style="background-color: #ffff00;">elemento que permite el almacenamiento de datos de forma permanente</mark> en dispositivos de memoria masiva para su posterior recuperación, consulta y procesamiento.
*   **Estructura Jerárquica:**
    *   <mark style="background-color: #add8e6;">Fichero</mark>: Colección de datos relacionados.
    *   <mark style="background-color: #add8e6;">Registro</mark>: Contienen datos relativos a un mismo elemento u objeto (p. ej., datos de un usuario).
    *   <mark style="background-color: #add8e6;">Campo</mark>: Divisiones dentro de un registro que contienen información elemental (p. ej., nombre o email del usuario).
*   **Transferencia de Datos:**
    *   <mark style="background-color: #ffff00;">Bloque</mark>: Cantidad de información transferida entre el soporte de almacenamiento y la memoria principal en una <mark style="background-color: #ffff00;">única operación de lectura/escritura</mark>.
    *   <mark style="background-color: #ffff00;">Empaquetado (Blocaje)</mark>: Operación de agrupar varios registros lógicos en un bloque.
    *   <mark style="background-color: #ffff00;">Factor de Blocaje</mark>: Número de registros que caben en un bloque.

### **2. Clasificación de Ficheros por su Función**

Los ficheros se pueden clasificar <mark style="background-color: #ffff00;">según la función que desempeñan</mark> dentro de una aplicación.

| Tipo de Fichero | Subtipo | Descripción | Ejemplo |
| :--- | :--- | :--- | :--- |
| **Permanentes** | <mark style="background-color: #add8e6;">Maestros</mark> | Contienen el <mark style="background-color: #ffff00;">estado actual de los datos</mark> que pueden ser modificados por la aplicación. | Fichero de datos de usuarios de una plataforma educativa. |
| | <mark style="background-color: #add8e6;">Constantes</mark> | Incluyen <mark style="background-color: #ffff00;">datos fijos que no suelen ser modificados</mark> y se acceden para consulta. | Fichero con códigos postales. |
| | <mark style="background-color: #add8e6;">Históricos</mark> | Almacenan datos que fueron actuales en un momento anterior; se usan para reconstruir situaciones. | Fichero con los usuarios dados de baja de una plataforma. |
| **Temporales** | <mark style="background-color: #add8e6;">Intermedios</mark> | Almacenan resultados de una aplicación que serán utilizados por otra. | |
| | <mark style="background-color: #add8e6;">De maniobra</mark> | Guardan datos de una aplicación que no caben en memoria principal por falta de espacio. | |
| | <mark style="background-color: #add8e6;">De resultados</mark> | Acumulan datos que serán transferidos a un dispositivo de salida. | |

### **3. Soportes y Métodos de Acceso**

*   **Tipos de Soportes Físicos:**
    *   <mark style="background-color: #add8e6;">Soportes Secuenciales</mark>: El acceso a los datos se realiza de <mark style="background-color: #ffff00;">forma lineal, desde el principio hasta la posición deseada</mark>. Se utilizan principalmente para <mark style="background-color: #d3d3d3;">copias de seguridad</mark>. (*Ejemplo: Cintas magnéticas.*)
    *   <mark style="background-color: #add8e6;">Soportes Direccionables (de Acceso Directo)</mark>: Permiten <mark style="background-color: #ffff00;">posicionarse directamente en la ubicación del dato de interés</mark> sin recorrer la información previa. Son los <mark style="background-color: #90ee90;">más empleados en la actualidad</mark>. (*Ejemplos: Discos magnéticos, discos ópticos, SSD.*)
*   **Organizaciones de Ficheros (Métodos de Acceso):**
    *   **<mark style="background-color: #add8e6;">Ficheros Secuenciales</mark>:** Los registros <mark style="background-color: #ffff00;">se almacenan de forma contigua y se leen uno tras otro desde el inicio</mark>. Utiliza una marca de fin de fichero (<mark style="background-color: #d3d3d3;">EOF</mark>).
    *   **<mark style="background-color: #add8e6;">Ficheros de Acceso Directo (o Aleatorio)</mark>:** Permite <mark style="background-color: #ffff00;">acceder a un registro directamente</mark> indicando su posición relativa o, más comúnmente, <mark style="background-color: #ffff00;">a través de una clave</mark>. Requiere <mark style="background-color: #d3d3d3;">dispositivos de acceso directo</mark>.
    *   **<mark style="background-color: #add8e6;">Ficheros Indexados</mark>:** Se basa en el <mark style="background-color: #ffff00;">uso de índices, que son tablas que relacionan la clave de un registro con su posición física</mark> en el fichero. Consta de una <mark style="background-color: #add8e6;">zona de datos</mark> y una <mark style="background-color: #add8e6;">zona de índices</mark>.
    *   **<mark style="background-color: #add8e6;">Ficheros Secuenciales Indexados</mark>:** <mark style="background-color: #ffff00;">Combina las organizaciones secuencial e indexada</mark>. La zona de datos se divide en segmentos, y el índice apunta al inicio de cada segmento. La búsqueda es <mark style="background-color: #ffff00;">directa hasta el segmento y secuencial dentro de él</mark>.
    *   **<mark style="background-color: #add8e6;">Ficheros con Acceso Calculado (Hash)</mark>:** Utiliza una <mark style="background-color: #ffff00;">función de hashing para convertir la clave directamente en su dirección física</mark>. El problema principal son las <mark style="background-color: #ffa07a;">colisiones</mark> (sinónimos), que se resuelven con técnicas como <mark style="background-color: #add8e6;">zonas de excedentes</mark>.

### **4. Parámetros de Utilización de Ficheros**

Estos parámetros ayudan a determinar qué tipo de organización de fichero es más adecuada.

*   **Capacidad:** Espacio total que ocupa el fichero.
*   **Actividad:** Frecuencia de consultas y modificaciones. Se mide con la <mark style="background-color: #add8e6;">Tasa de Actividad</mark> y la <mark style="background-color: #add8e6;">Frecuencia de Acceso</mark>.
*   **Volatilidad:** Frecuencia de inserciones y borrados. Se mide con la <mark style="background-color: #add8e6;">Tasa de Renovación</mark> y la <mark style="background-color: #add8e6;">Frecuencia de Renovación</mark>.
*   **Crecimiento:** Variación de la capacidad del fichero, medida por la <mark style="background-color: #add8e6;">Tasa de Crecimiento</mark>.

## **Parte II: Fundamentos de Bases de Datos**

Esta sección se centra en la definición, modelos, arquitecturas y tipos de los Sistemas Gestores de Bases de Datos (SGBD), destacando los temas de mayor relevancia.

### **1. Introducción y Conceptos Clave**

*   **Definición de Base de Datos:** Una <mark style="background-color: #ffff00;">colección de datos relacionados lógicamente</mark> entre sí, con una definición y descripción comunes, estructurados de una determinada manera, almacenados con <mark style="background-color: #90ee90;">mínima redundancia</mark> y que permiten un <mark style="background-color: #90ee90;">acceso eficiente</mark>.
*   **Componentes Elementales:**
    *   **<mark style="background-color: #add8e6;">Entidad</mark>:** Objeto del mundo real o abstracto sobre el que se almacena información (ej. un cliente, un producto, un doctor).
    *   **<mark style="background-color: #add8e6;">Atributo</mark>:** Propiedad o característica de una entidad (ej. nombre, precio, raza, color).
    *   **<mark style="background-color: #add8e6;">Metadatos</mark>:** <mark style="background-color: #ffff00;">Datos que describen los datos</mark> (su estructura, tipos, relaciones). Se almacenan en el <mark style="background-color: #d3d3d3;">diccionario de datos o catálogo</mark> y permiten la <mark style="background-color: #90ee90;">independencia entre la lógica del programa y el almacenamiento físico</mark>.

### **2. Usos y Usuarios de las Bases de Datos**

*   **Tipos de Usuarios:**
    *   <mark style="background-color: #add8e6;">Administrador (DBA)</mark>: Responsable de la implementación física, seguridad y rendimiento.
    *   <mark style="background-color: #add8e6;">Diseñadores</mark>: Definen la estructura lógica y los procesos de la base de datos.
    *   <mark style="background-color: #add8e6;">Programadores de Aplicaciones</mark>: Crean los programas que interactúan con la BD.
    *   <mark style="background-color: #add8e6;">Usuarios Finales</mark>: Consumen la información a través de las aplicaciones.
*   **Ámbitos de Aplicación:** Las bases de datos son <mark style="background-color: #ffff00;">omnipresentes</mark> en sectores como la banca, líneas aéreas, telecomunicaciones, medicina, justicia, etc.

### **3. Modelos de Bases de Datos**

Un modelo de datos <mark style="background-color: #ffff00;">define la estructura lógica de una base de datos</mark>.

#### **3.1. Modelo Relacional** <mark style="background-color: #d3d3d3;">(Contenido Prioritario - Sección 6.3)</mark>

*   **Contexto:** Desarrollado por Codd en 1970, es el <mark style="background-color: #ffff00;">modelo más extendido</mark> en la actualidad.
*   **Estructura Lógica:** La información se organiza en <mark style="background-color: #ffff00;">tablas bidimensionales</mark>, también conocidas como relaciones.
*   **Terminología Clave:**
    *   **<mark style="background-color: #add8e6;">Tabla (Relación)</mark>:** Estructura principal que contiene los datos.
    *   **<mark style="background-color: #add8e6;">Registro, Entidad o Tupla</mark>:** Cada fila de la tabla.
    *   **<mark style="background-color: #add8e6;">Campo o Atributo</mark>:** Cada columna de la tabla.
    *   **<mark style="background--color: #add8e6;">Dominio</mark>:** Conjunto de valores que puede tomar un atributo.
    *   **<mark style="background-color: #add8e6;">Clave</mark>:** Atributo o conjunto de atributos que <mark style="background-color: #ffff00;">identifica de forma única a una tupla</mark>.
*   **Requisitos Fundamentales de las Tablas:**
    1.  Todos los registros son del mismo tipo.
    2.  La tabla solo puede tener un tipo de registro.
    3.  No existen campos o atributos repetidos.
    4.  No contienen tuplas duplicadas.
    5.  No existe un orden específico en el almacenamiento de los registros.
*   **Lenguaje de Consulta:** El lenguaje habitual para consultas es <mark style="background-color: #d3d3d3;">SQL (Structured Query Language)</mark>.

#### **3.2. Modelo Orientado a Objetos** <mark style="background-color: #d3d3d3;">(Contenido Prioritario - Sección 6.4)</mark>

*   **Definición:** Define una base de datos en términos de <mark style="background-color: #ffff00;">objetos, sus propiedades y sus operaciones</mark>.
*   **Conceptos Fundamentales:**
    *   **<mark style="background-color: #add8e6;">Objeto</mark>:** Unidad básica que <mark style="background-color: #ffff00;">combina estructura (datos) y comportamiento (operaciones)</mark>.
    *   **<mark style="background-color: #add8e6;">Clase</mark>:** Plantilla que define la estructura y comportamiento de un conjunto de objetos similares. Las clases se organizan en jerarquías.
    *   **<mark style="background-color: #add8e6;">Método</mark>:** Procedimiento o función asociada a una clase que define sus operaciones.
    *   **<mark style="background-color: #add8e6;">Encapsulación</mark>:** Propiedad que <mark style="background-color: #ffff00;">oculta la información interna del objeto</mark>, impidiendo accesos incorrectos y permitiendo la interacción solo a través de sus métodos.
    *   **<mark style="background-color: #add8e6;">Herencia</mark>:** Mecanismo que permite a una clase (subclase) <mark style="background-color: #ffff00;">heredar propiedades y métodos de otra (superclase)</mark>, fomentando la reutilización.
    *   **<mark style="background-color: #add8e6;">Polimorfismo</mark>:** Propiedad de una operación que le permite ser <mark style="background-color: #ffff00;">aplicada a distintos tipos de objetos</mark>.
*   **Relevancia:** Este modelo es considerado el <mark style="background-color: #90ee90;">fundamento de las bases de datos de tercera generación</mark> y cubre limitaciones del modelo relacional.

#### **3.3. Otros Modelos de Bases de Datos**

*   <mark style="background-color: #add8e6;">Modelo Jerárquico</mark>: Organiza los datos en una <mark style="background-color: #ffff00;">estructura de árbol (padre-hijo)</mark>, donde un hijo solo tiene un padre.
*   <mark style="background-color: #add8e6;">Modelo en Red</mark>: Similar al jerárquico, pero permite que <mark style="background-color: #ffff00;">un nodo hijo tenga múltiples padres</mark>.
*   <mark style="background-color: #add8e6;">Objeto-Relacional</mark>: Híbrido que combina la simplicidad del modelo relacional con las capacidades del modelo orientado a objetos.
*   <mark style="background-color: #add8e6;">Orientado a Documentos</mark>: Almacena datos en documentos semi-estructurados (p. ej., <mark style="background-color: #d3d3d3;">JSON, XML</mark>). Ejemplos: MongoDB, CouchDB.
*   <mark style="background-color: #add8e6;">Multidimensional</mark>: Estructura los datos en <mark style="background-color: #ffff00;">cubos (dimensiones) para análisis complejos (OLAP)</mark>.
*   <mark style="background-color: #add8e6;">Transaccional</mark>: Optimizado para la velocidad en el procesamiento de un gran volumen de transacciones.

### **4. Sistemas Gestores de Bases de Datos (SGBD)**

*   **Definición:** <mark style="background-color: #ffff00;">Conjunto de programas y herramientas que permite a los usuarios crear y mantener una base de datos</mark>, facilitando la definición, construcción, manipulación y compartición de la misma.

#### **4.1. Funciones del SGBD** <mark style="background-color: #d3d3d3;">(Contenido Prioritario - Sección 7.1)</mark>

El SGBD desarrolla tres funciones fundamentales a través de lenguajes específicos:

*   **<mark style="background-color: #add8e6;">Función de Descripción o Definición</mark>:**
    *   **Propósito:** <mark style="background-color: #90ee90;">Permite al diseñador crear las estructuras de la base de datos</mark> (tablas, vistas, índices) y definir las relaciones y restricciones.
    *   **Lenguaje:** Se realiza mediante el Lenguaje de Descripción de Datos (<mark style="background-color: #d3d3d3;">DDL</mark> - Data Definition Language).
*   **<mark style="background-color: #add8e6;">Función de Manipulación</mark>:**
    *   **Propósito:** <mark style="background-color: #90ee90;">Permite a los usuarios añadir, suprimir, modificar o consultar los datos</mark> de la base de datos.
    *   **Lenguaje:** Se implementa a través del Lenguaje de Manipulación de Datos (<mark style="background-color: #d3d3d3;">DML</mark> - Data Manipulation Language).
*   **<mark style="background-color: #add8e6;">Función de Control</mark>:**
    *   **Propósito:** <mark style="background-color: #90ee90;">Permite al administrador establecer mecanismos de protección (seguridad), integridad y control de acceso concurrente</mark>. Incluye la gestión de copias de seguridad, recuperación ante fallos y auditoría.
    *   **Lenguaje:** Se gestiona con el Lenguaje de Control de Datos (<mark style="background-color: #d3d3d3;">DCL</mark> - Data Control Language).
*   **<mark style="background-color: #d3d3d3;">SQL (Structured Query Language)</mark>:** Es el lenguaje estandarizado que <mark style="background-color: #ffff00;">proporciona sentencias para realizar las tres funciones (DDL, DML y DCL)</mark>.

#### **4.2. Arquitectura del SGBD** <mark style="background-color: #d3d3d3;">(Contenido Prioritario - Sección 7.3)</mark>

La arquitectura estándar, basada en el modelo <mark style="background-color: #d3d3d3;">ANSI/SPARC</mark>, propone una estructura de tres niveles para simplificar la interacción y proporcionar independencia.

*   **Arquitectura de Tres Niveles:**
    *   **<mark style="background-color: #add8e6;">Nivel Interno o Físico</mark>:** Describe la <mark style="background-color: #ffff00;">estructura física de almacenamiento</mark> de la base de datos. Es el nivel más cercano al hardware y detalla cómo se guardan los datos (ficheros, índices, etc.).
    *   **<mark style="background-color: #add8e6;">Nivel Lógico o Conceptual</mark>:** Describe la <mark style="background-color: #ffff00;">estructura completa de la base de datos para la comunidad de usuarios</mark>. Define las entidades, relaciones, operaciones y restricciones, ocultando los detalles de la implementación física.
    *   **<mark style="background-color: #add8e6;">Nivel Externo o de Visión del Usuario</mark>:** Describe las diferentes <mark style="background-color: #ffff00;">vistas que los usuarios o aplicaciones tienen de la base de datos</mark>. Cada vista muestra solo la parte de la base de datos que es relevante para un grupo de usuarios específico.
*   **Independencia de Datos:** Esta arquitectura consigue dos niveles de independencia cruciales:
    *   **<mark style="background-color: #add8e6;">Independencia Lógica</mark>:** Permite <mark style="background-color: #90ee90;">modificar el esquema conceptual sin necesidad de alterar los esquemas externos</mark> o las aplicaciones existentes.
    *   **<mark style="background-color: #add8e6;">Independencia Física</mark>:** Permite <mark style="background-color: #90ee90;">modificar el esquema interno sin que afecte al esquema conceptual</mark>.

#### **4.3. Tipos de SGBD** <mark style="background-color: #d3d3d3;">(Contenido Prioritario - Sección 7.4)</mark>

Los SGBD se pueden clasificar atendiendo a diferentes criterios:

| Criterio | Tipos | Descripción |
| :--- | :--- | :--- |
| **1. Modelo Lógico** | <mark style="background-color: #add8e6;">Relacional, Orientado a Objetos, Jerárquico, En Red.</mark> | Se basa en el modelo de datos subyacente. El relacional es el más utilizado; el orientado a objetos gana terreno. |
| **2. Número de Usuarios** | <mark style="background-color: #add8e6;">Monousuario, Multiusuario.</mark> | Define si el sistema atiende a un solo usuario a la vez o a múltiples usuarios de forma concurrente. |
| **3. Distribución** | <mark style="background-color: #add8e6;">Centralizados, Distribuidos.</mark> | **Centralizados:** Los datos se almacenan en un solo computador. **Distribuidos:** Los datos se reparten en varios sitios o nodos. |
| **4. Costo** | <mark style="background-color: #add8e6;">Comerciales, Libres/Gratuitos.</mark> | Clasificación según el modelo de licencia (p. ej., Oracle vs. PostgreSQL). |
| **5. Propósito** | <mark style="background-color: #add8e6;">Propósito General, Propósito Específico.</mark> | **General:** Utilizable para cualquier tipo de aplicación. **Específico:** Diseñado para un problema concreto. |

#### **4.4. Sistemas Comerciales vs. Sistemas Libres**

| Tipo | SGBD Ejemplos | Descripción |
| :--- | :--- | :--- |
| **Comerciales** | <mark style="background-color: #d3d3d3;">Oracle, Microsoft SQL Server, DB2, Informix</mark> | Productos con licencia que ofrecen soporte y funcionalidades avanzadas. |
| **Libres** | <mark style="background-color: #d3d3d3;">MySQL, PostgreSQL, Firebird, SQLite, Apache Derby</mark> | Sistemas de <mark style="background-color: #ffff00;">código abierto</mark>, a menudo gratuitos, con una gran comunidad de soporte. |

### **5. Arquitecturas de Almacenamiento**

*   **Tecnologías de Almacenamiento:**
    *   **Discos <mark style="background-color: #d3d3d3;">SATA</mark> y <mark style="background-color: #d3d3d3;">SCSI</mark>:** Interfaces estándar para discos duros.
    *   **<mark style="background-color: #d3d3d3;">RAID</mark> (Redundant Array of Independent Disks):** Tecnología que <mark style="background-color: #ffff00;">combina múltiples discos para mejorar el rendimiento, la seguridad o ambos</mark>.
    *   **<mark style="background-color: #d3d3d3;">NAS</mark> (Network Attached Storage):** Dispositivo de almacenamiento conectado a una red que permite el <mark style="background-color: #ffff00;">acceso centralizado</mark> a los datos.
    *   **<mark style="background-color: #d3d3d3;">SAN</mark> (Storage Area Network):** <mark style="background-color: #ffff00;">Red de alta velocidad dedicada exclusivamente a conectar servidores y dispositivos de almacenamiento</mark> masivo.
*   **<mark style="background-color: #add8e6;">Bases de Datos Centralizadas</mark>:**
    *   **Arquitectura:** La arquitectura completa del sistema (SGBD, datos) <mark style="background-color: #ffff00;">reside en un único computador</mark>. No hay múltiples elementos de procesamiento ni mecanismos de intercomunicación como en las bases de datos distribuidas.
*   **<mark style="background-color: #add8e6;">Bases de Datos Distribuidas (BDD)</mark>:**
    *   **Arquitectura:** Conjunto de múltiples bases de datos lógicamente relacionadas que se encuentran <mark style="background-color: #ffff00;">distribuidas en diferentes nodos de una red de comunicaciones</mark>.
    *   **<mark style="background-color: #add8e6;">Fragmentación</mark>:** Técnica utilizada en BDD que consiste en <mark style="background-color: #ffff00;">particionar las tablas de la base de datos y distribuir los fragmentos</mark> en diferentes nodos de la red para optimizar las consultas y el almacenamiento.