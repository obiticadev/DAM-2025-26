### **Leyenda de Marcadores**

*   <mark style="background-color: #ffff00;">Amarillo</mark>: Para conceptos fundamentales, definiciones y puntos clave.
*   <mark style="background-color: #90ee90;">Verde</mark>: Para ventajas, objetivos, propósitos y características positivas.
*   <mark style="background-color: #add8e6;">Azul</mark>: Para tipos, clasificaciones, componentes, estructuras y ejemplos.
*   <mark style="background-color: #d3d3d3;">Gris</mark>: Para tecnologías específicas, nombres propios, estándares y secciones marcadas como "Contenido Prioritario".

***

# **Esquema Detallado sobre Almacenamiento de Información y Bases de Datos**

Este documento presenta un esquema jerárquico y detallado sobre los conceptos fundamentales del almacenamiento de información, desde los sistemas basados en ficheros hasta las arquitecturas de bases de datos modernas. El contenido se ha estructurado para <mark style="background-color: #90ee90;">facilitar la comprensión, el estudio y la planificación de proyectos</mark>, con un enfoque particular en los temas clave señalados como prioritarios.

## **Parte I: Sistemas de Almacenamiento Basados en Ficheros**

Esta sección aborda los métodos tradicionales de almacenamiento de datos, sentando las bases para comprender la evolución hacia los sistemas de bases de datos.

### **1. Conceptos Fundamentales de Ficheros**

*   **Definición:** Un fichero es el <mark style="background-color: #ffff00;">elemento que permite el almacenamiento de datos de forma permanente</mark> en dispositivos de memoria masiva.
*   **Estructura Jerárquica:**
    *   <mark style="background-color: #add8e6;">Fichero</mark>: Colección de datos relacionados.
    *   <mark style="background-color: #add8e6;">Registro</mark>: Conjunto de datos relativos a un mismo elemento u objeto (p. ej., datos de un usuario).
    *   <mark style="background-color: #add8e6;">Campo</mark>: Cada una de las informaciones elementales que forman un registro (p. ej., nombre de usuario).
*   **Transferencia de Datos:**
    *   <mark style="background-color: #add8e6;">Bloque</mark>: Cantidad de información transferida entre el soporte y la memoria principal en <mark style="background-color: #ffff00;">una sola operación de lectura/escritura</mark>.
    *   <mark style="background-color: #add8e6;">Factor de Blocaje</mark>: Número de registros que caben en un bloque.
    *   <mark style="background-color: #add8e6;">Emblocamiento</mark>: Operación de agrupar varios registros en un bloque.

### **2. Clasificación de Ficheros por su Función**

*   **<mark style="background-color: #add8e6;">Ficheros Permanentes</mark>:** Contienen información relevante y necesaria para la aplicación.
    *   **<mark style="background-color: #add8e6;">Maestros</mark>:** Almacenan el <mark style="background-color: #ffff00;">estado actual de los datos</mark> que pueden ser modificados (p. ej., fichero de usuarios activos).
    *   **<mark style="background-color: #add8e6;">Constantes</mark>:** Incluyen <mark style="background-color: #ffff00;">datos fijos que no suelen modificarse</mark> y se usan para consulta (p. ej., fichero de códigos postales).
    *   **<mark style="background-color: #add8e6;">Históricos</mark>:** Guardan datos que fueron actuales en un momento anterior, utilizados para <mark style="background-color: #ffff00;">reconstruir situaciones pasadas</mark> (p. ej., fichero de usuarios dados de baja).
*   **<mark style="background-color: #add8e6;">Ficheros de Trabajo (Temporales)</mark>:** Almacenan información útil para una parte de la aplicación y tienen una existencia corta.
    *   **<mark style="background-color: #add8e6;">Intermedios</mark>:** Almacenan resultados de una aplicación que serán utilizados por otra.
    *   **<mark style="background-color: #add8e6;">De Maniobra</mark>:** Guardan datos que no pueden mantenerse en memoria principal por falta de espacio.
    *   **<mark style="background-color: #add8e6;">De Resultados</mark>:** Contienen datos que serán transferidos a un dispositivo de salida.

### **3. Soportes y Métodos de Acceso**

*   **Tipos de Soportes:**
    *   **<mark style="background-color: #add8e6;">Soportes Secuenciales</mark>:** El acceso a los datos <mark style="background-color: #ffff00;">requiere leer todo el contenido anterior</mark>. Usados principalmente para copias de seguridad (p. ej., cintas magnéticas).
    *   **<mark style="background-color: #add8e6;">Soportes Direccionables (de Acceso Directo)</mark>:** Permiten <mark style="background-color: #ffff00;">posicionarse directamente en la ubicación del dato deseado</mark> (p. ej., discos magnéticos, ópticos, SSD).
*   **Organizaciones de Ficheros (Métodos de Acceso):**
    *   **<mark style="background-color: #add8e6;">Ficheros Secuenciales</mark>:** Los registros se almacenan de forma contigua y se leen uno tras otro.
    *   **<mark style="background-color: #add8e6;">Ficheros de Acceso Directo (o Aleatorio)</mark>:** Se accede a un registro indicando su posición relativa o a través de una clave.
    *   **<mark style="background-color: #add8e6;">Ficheros Indexados</mark>:** Utilizan un <mark style="background-color: #ffff00;">índice (similar al de un libro) para localizar registros</mark> de forma directa.
    *   **<mark style="background-color: #add8e6;">Ficheros Secuenciales Indexados</mark>:** Combinan la organización secuencial con un índice que apunta a bloques de registros.
    *   **<mark style="background-color: #add8e6;">Ficheros con Acceso Calculado (Hash)</mark>:** Usan una <mark style="background-color: #ffff00;">función matemática para transformar una clave en una dirección</mark> de memoria física.

## **Parte II: Fundamentos de Bases de Datos**

Esta sección se centra en la definición, modelos, arquitecturas y tipos de los Sistemas Gestores de Bases de Datos (SGBD), destacando los temas de mayor relevancia.

### **1. Introducción y Conceptos Clave**

*   **Definición de Base de Datos:** Una <mark style="background-color: #ffff00;">colección de datos relacionados lógicamente</mark> entre sí, con una definición común, estructurados de una determinada manera, almacenados con <mark style="background-color: #90ee90;">mínima redundancia</mark> y que permiten un <mark style="background-color: #90ee90;">acceso eficiente</mark>.
*   **Componentes Elementales:**
    *   **<mark style="background-color: #add8e6;">Entidad</mark>:** Objeto real o abstracto con características diferenciadoras (p. ej., un ejemplar, un doctor).
    *   **<mark style="background-color: #add8e6;">Atributo</mark>:** Característica o propiedad de una entidad (p. ej., raza, color, nombre).
    *   **<mark style="background-color: #add8e6;">Metadatos</mark>:** <mark style="background-color: #ffff00;">Descripción de los datos almacenados</mark>, guardada en el diccionario de datos o catálogo. Permiten la <mark style="background-color: #90ee90;">independencia entre la lógica y el almacenamiento físico</mark>.

### **2. Modelos de Bases de Datos**

#### **2.1. Modelo Relacional** <mark style="background-color: #d3d3d3;">(Contenido Prioritario - Sección 6.3)</mark>

*   **Contexto:** Desarrollado por Codd en 1970, es el <mark style="background-color: #ffff00;">modelo más extendido</mark> en la actualidad.
*   **Estructura Lógica:** La información se organiza en <mark style="background-color: #ffff00;">tablas bidimensionales</mark>, también conocidas como relaciones.
*   **Terminología Clave:**
    *   **<mark style="background-color: #add8e6;">Tabla (Relación)</mark>:** Estructura principal que contiene los datos.
    *   **<mark style="background-color: #add8e6;">Registro, Entidad o Tupla</mark>:** Cada fila de la tabla.
    *   **<mark style="background-color: #add8e6;">Campo o Atributo</mark>:** Cada columna de la tabla.
    *   **<mark style="background-color: #add8e6;">Dominio</mark>:** Conjunto de valores que puede tomar un atributo.
    *   **<mark style="background-color: #add8e6;">Clave</mark>:** Atributo o conjunto de atributos que <mark style="background-color: #ffff00;">identifica de forma única a una tupla</mark>.
*   **Requisitos Fundamentales de las Tablas:**
    1.  Todos los registros son del mismo tipo.
    2.  La tabla solo puede tener un tipo de registro.
    3.  No existen campos o atributos repetidos.
    4.  No contienen tuplas duplicadas.
    5.  No existe un orden específico en el almacenamiento de los registros.
*   **Lenguaje de Consulta:** El lenguaje habitual para consultas es <mark style="background-color: #d3d3d3;">SQL (Structured Query Language)</mark>.

#### **2.2. Modelo Orientado a Objetos** <mark style="background-color: #d3d3d3;">(Contenido Prioritario - Sección 6.4)</mark>

*   **Definición:** Define una base de datos en términos de <mark style="background-color: #ffff00;">objetos, sus propiedades y sus operaciones</mark>.
*   **Conceptos Fundamentales:**
    *   **<mark style="background-color: #add8e6;">Objeto</mark>:** Unidad básica que <mark style="background-color: #ffff00;">combina estructura (datos) y comportamiento (operaciones)</mark>.
    *   **<mark style="background-color: #add8e6;">Clase</mark>:** Plantilla que define la estructura y comportamiento de un conjunto de objetos similares. Las clases se organizan en jerarquías.
    *   **<mark style="background-color: #add8e6;">Método</mark>:** Procedimiento o función asociada a una clase que define sus operaciones.
    *   **<mark style="background-color: #add8e6;">Encapsulación</mark>:** Propiedad que <mark style="background-color: #ffff00;">oculta la información interna del objeto</mark>, impidiendo accesos incorrectos y permitiendo la interacción solo a través de sus métodos.
    *   **<mark style="background-color: #add8e6;">Herencia</mark>:** Mecanismo que permite a una clase (subclase) <mark style="background-color: #ffff00;">heredar propiedades y métodos de otra (superclase)</mark>, fomentando la reutilización.
    *   **<mark style="background-color: #add8e6;">Polimorfismo</mark>:** Propiedad de una operación que le permite ser <mark style="background-color: #ffff00;">aplicada a distintos tipos de objetos</mark>.
*   **Relevancia:** Este modelo es considerado el <mark style="background-color: #90ee90;">fundamento de las bases de datos de tercera generación</mark> y cubre limitaciones del modelo relacional.

### **3. Sistemas Gestores de Bases de Datos (SGBD)**

*   **Definición:** <mark style="background-color: #ffff00;">Conjunto de programas y herramientas que permite a los usuarios crear y mantener una base de datos</mark>, facilitando la definición, construcción, manipulación y compartición de la misma.

#### **3.1. Funciones del SGBD** <mark style="background-color: #d3d3d3;">(Contenido Prioritario - Sección 7.1)</mark>

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

#### **3.2. Arquitectura del SGBD** <mark style="background-color: #d3d3d3;">(Contenido Prioritario - Sección 7.3)</mark>

La arquitectura estándar, basada en el modelo <mark style="background-color: #d3d3d3;">ANSI/SPARC</mark>, propone una estructura de tres niveles para simplificar la interacción y proporcionar independencia.

*   **Arquitectura de Tres Niveles:**
    *   **<mark style="background-color: #add8e6;">Nivel Interno o Físico</mark>:** Describe la <mark style="background-color: #ffff00;">estructura física de almacenamiento</mark> de la base de datos. Es el nivel más cercano al hardware y detalla cómo se guardan los datos (ficheros, índices, etc.).
    *   **<mark style="background-color: #add8e6;">Nivel Lógico o Conceptual</mark>:** Describe la <mark style="background-color: #ffff00;">estructura completa de la base de datos para la comunidad de usuarios</mark>. Define las entidades, relaciones, operaciones y restricciones, ocultando los detalles de la implementación física.
    *   **<mark style="background-color: #add8e6;">Nivel Externo o de Visión del Usuario</mark>:** Describe las diferentes <mark style="background-color: #ffff00;">vistas que los usuarios o aplicaciones tienen de la base de datos</mark>. Cada vista muestra solo la parte de la base de datos que es relevante para un grupo de usuarios específico.
*   **Independencia de Datos:** Esta arquitectura consigue dos niveles de independencia cruciales:
    *   **<mark style="background-color: #add8e6;">Independencia Lógica</mark>:** Permite <mark style="background-color: #90ee90;">modificar el esquema conceptual sin necesidad de alterar los esquemas externos</mark> o las aplicaciones existentes.
    *   **<mark style="background-color: #add8e6;">Independencia Física</mark>:** Permite <mark style="background-color: #90ee90;">modificar el esquema interno sin que afecte al esquema conceptual</mark>.

#### **3.3. Tipos de SGBD** <mark style="background-color: #d3d3d3;">(Contenido Prioritario - Sección 7.4)</mark>

Los SGBD se pueden clasificar atendiendo a diferentes criterios:

| Criterio | Tipos | Descripción |
| :--- | :--- | :--- |
| **1. Modelo Lógico** | <mark style="background-color: #add8e6;">Relacional, Orientado a Objetos, Jerárquico, En Red.</mark> | Se basa en el modelo de datos subyacente. El relacional es el más utilizado; el orientado a objetos gana terreno. |
| **2. Número de Usuarios** | <mark style="background-color: #add8e6;">Monousuario, Multiusuario.</mark> | Define si el sistema atiende a un solo usuario a la vez o a múltiples usuarios de forma concurrente. |
| **3. Distribución** | <mark style="background-color: #add8e6;">Centralizados, Distribuidos.</mark> | **Centralizados:** Los datos se almacenan en un solo computador. **Distribuidos:** Los datos se reparten en varios sitios o nodos, pudiendo ser homogéneos (mismo SGBD) o heterogéneos (diferentes SGBD). |
| **4. Costo** | <mark style="background-color: #add8e6;">Comerciales, Libres/Gratuitos.</mark> | Clasificación según el modelo de licencia (p. ej., Oracle vs. PostgreSQL). |
| **5. Propósito** | <mark style="background-color: #add8e6;">Propósito General, Propósito Específico.</mark> | **General:** Utilizable para cualquier tipo de aplicación. **Específico:** Diseñado para un problema concreto (p. ej., sistemas de reservas). |

### **4. Arquitecturas de Almacenamiento**

*   **<mark style="background-color: #add8e6;">Bases de Datos Centralizadas</mark>:**
    *   **Arquitectura:** Todo el sistema informático (SGBD, datos, dispositivos) <mark style="background-color: #ffff00;">reside en un único computador o sitio</mark>.
    *   **Características:** No hay múltiples elementos de procesamiento ni mecanismos de intercomunicación como en las bases de datos distribuidas.
*   **<mark style="background-color: #add8e6;">Bases de Datos Distribuidas (BDD)</mark>:**
    *   **Arquitectura:** Conjunto de múltiples bases de datos lógicamente relacionadas que se encuentran <mark style="background-color: #ffff00;">distribuidas en diferentes nodos de una red de comunicaciones</mark>.
    *   **<mark style="background-color: #add8e6;">Fragmentación</mark>:** Para extraer los datos consultados, se puede realizar una fragmentación de distintas tablas. Este proceso se refiere al <mark style="background-color: #ffff00;">particionamiento de la información para distribuir cada parte a los diferentes sitios</mark> de la red.