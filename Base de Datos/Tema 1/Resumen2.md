Esquema Detallado sobre Almacenamiento de Información y Bases de Datos

Parte I: Sistemas de Almacenamiento Basados en Ficheros

Esta sección aborda los métodos tradicionales de almacenamiento de datos, sentando las bases para comprender la evolución hacia los sistemas de bases de datos.

1. Conceptos Fundamentales de Ficheros

* Definición: Un fichero es el elemento que permite el almacenamiento de datos de forma permanente en dispositivos de memoria masiva.
* Estructura Jerárquica:
  * Fichero: Colección de datos relacionados.
  * Registro: Conjunto de datos relativos a un mismo elemento u objeto (p. ej., datos de un usuario).
  * Campo: Cada una de las informaciones elementales que forman un registro (p. ej., nombre de usuario).
* Transferencia de Datos:
  * Bloque: Cantidad de información transferida entre el soporte y la memoria principal en una sola operación de lectura/escritura.
  * Factor de Blocaje: Número de registros que caben en un bloque.
  * Emblocamiento: Operación de agrupar varios registros en un bloque.

2. Clasificación de Ficheros por su Función

* Ficheros Permanentes: Contienen información relevante y necesaria para la aplicación.
  * Maestros: Almacenan el estado actual de los datos que pueden ser modificados (p. ej., fichero de usuarios activos).
  * Constantes: Incluyen datos fijos que no suelen modificarse y se usan para consulta (p. ej., fichero de códigos postales).
  * Históricos: Guardan datos que fueron actuales en un momento anterior, utilizados para reconstruir situaciones pasadas (p. ej., fichero de usuarios dados de baja).
* Ficheros de Trabajo (Temporales): Almacenan información útil para una parte de la aplicación y tienen una existencia corta.
  * Intermedios: Almacenan resultados de una aplicación que serán utilizados por otra.
  * De Maniobra: Guardan datos que no pueden mantenerse en memoria principal por falta de espacio.
  * De Resultados: Contienen datos que serán transferidos a un dispositivo de salida.

3. Soportes y Métodos de Acceso

* Tipos de Soportes:
  * Soportes Secuenciales: El acceso a los datos requiere leer todo el contenido anterior. Usados principalmente para copias de seguridad (p. ej., cintas magnéticas).
  * Soportes Direccionables (de Acceso Directo): Permiten posicionarse directamente en la ubicación del dato deseado (p. ej., discos magnéticos, ópticos, SSD).
* Organizaciones de Ficheros (Métodos de Acceso):
  * Ficheros Secuenciales: Los registros se almacenan de forma contigua y se leen uno tras otro.
  * Ficheros de Acceso Directo (o Aleatorio): Se accede a un registro indicando su posición relativa o a través de una clave.
  * Ficheros Indexados: Utilizan un índice (similar al de un libro) para localizar registros de forma directa.
  * Ficheros Secuenciales Indexados: Combinan la organización secuencial con un índice que apunta a bloques de registros.
  * Ficheros con Acceso Calculado (Hash): Usan una función matemática para transformar una clave en una dirección de memoria física.

Parte II: Fundamentos de Bases de Datos

Esta sección se centra en la definición, modelos, arquitecturas y tipos de los Sistemas Gestores de Bases de Datos (SGBD), destacando los temas de mayor relevancia.

1. Introducción y Conceptos Clave

* Definición de Base de Datos: Una colección de datos relacionados lógicamente entre sí, con una definición común, estructurados de una determinada manera, almacenados con mínima redundancia y que permiten un acceso eficiente.
* Componentes Elementales:
  * Entidad: Objeto real o abstracto con características diferenciadoras (p. ej., un ejemplar, un doctor).
  * Atributo: Característica o propiedad de una entidad (p. ej., raza, color, nombre).
  * Metadatos: Descripción de los datos almacenados, guardada en el diccionario de datos o catálogo. Permiten la independencia entre la lógica y el almacenamiento físico.

2. Modelos de Bases de Datos

2.1. Modelo Relacional (Contenido Prioritario - Sección 6.3)

* Contexto: Desarrollado por Codd en 1970, es el modelo más extendido en la actualidad.
* Estructura Lógica: La información se organiza en tablas bidimensionales, también conocidas como relaciones.
* Terminología Clave:
  * Tabla (Relación): Estructura principal que contiene los datos.
  * Registro, Entidad o Tupla: Cada fila de la tabla.
  * Campo o Atributo: Cada columna de la tabla.
  * Dominio: Conjunto de valores que puede tomar un atributo.
  * Clave: Atributo o conjunto de atributos que identifica de forma única a una tupla.
* Requisitos Fundamentales de las Tablas:
  1. Todos los registros son del mismo tipo.
  2. La tabla solo puede tener un tipo de registro.
  3. No existen campos o atributos repetidos.
  4. No contienen tuplas duplicadas.
  5. No existe un orden específico en el almacenamiento de los registros.
* Lenguaje de Consulta: El lenguaje habitual para consultas es SQL (Structured Query Language).

2.2. Modelo Orientado a Objetos (Contenido Prioritario - Sección 6.4)

* Definición: Define una base de datos en términos de objetos, sus propiedades y sus operaciones.
* Conceptos Fundamentales:
  * Objeto: Unidad básica que combina estructura (datos) y comportamiento (operaciones).
  * Clase: Plantilla que define la estructura y comportamiento de un conjunto de objetos similares. Las clases se organizan en jerarquías.
  * Método: Procedimiento o función asociada a una clase que define sus operaciones.
  * Encapsulación: Propiedad que oculta la información interna del objeto, impidiendo accesos incorrectos y permitiendo la interacción solo a través de sus métodos.
  * Herencia: Mecanismo que permite a una clase (subclase) heredar propiedades y métodos de otra (superclase), fomentando la reutilización.
  * Polimorfismo: Propiedad de una operación que le permite ser aplicada a distintos tipos de objetos.
* Relevancia: Este modelo es considerado el fundamento de las bases de datos de tercera generación y cubre limitaciones del modelo relacional.

3. Sistemas Gestores de Bases de Datos (SGBD)

* Definición: Conjunto de programas y herramientas que permite a los usuarios crear y mantener una base de datos, facilitando la definición, construcción, manipulación y compartición de la misma.

3.1. Funciones del SGBD (Contenido Prioritario - Sección 7.1)

El SGBD desarrolla tres funciones fundamentales a través de lenguajes específicos:

* Función de Descripción o Definición:
  * Propósito: Permite al diseñador crear las estructuras de la base de datos (tablas, vistas, índices) y definir las relaciones y restricciones.
  * Lenguaje: Se realiza mediante el Lenguaje de Descripción de Datos (DDL - Data Definition Language).
* Función de Manipulación:
  * Propósito: Permite a los usuarios añadir, suprimir, modificar o consultar los datos de la base de datos.
  * Lenguaje: Se implementa a través del Lenguaje de Manipulación de Datos (DML - Data Manipulation Language).
* Función de Control:
  * Propósito: Permite al administrador establecer mecanismos de protección (seguridad), integridad y control de acceso concurrente. Incluye la gestión de copias de seguridad, recuperación ante fallos y auditoría.
  * Lenguaje: Se gestiona con el Lenguaje de Control de Datos (DCL - Data Control Language).
* SQL (Structured Query Language): Es el lenguaje estandarizado que proporciona sentencias para realizar las tres funciones (DDL, DML y DCL).

3.2. Arquitectura del SGBD (Contenido Prioritario - Sección 7.3)

La arquitectura estándar, basada en el modelo ANSI/SPARC, propone una estructura de tres niveles para simplificar la interacción y proporcionar independencia.

* Arquitectura de Tres Niveles:
  * Nivel Interno o Físico: Describe la estructura física de almacenamiento de la base de datos. Es el nivel más cercano al hardware y detalla cómo se guardan los datos (ficheros, índices, organización de registros, etc.).
  * Nivel Lógico o Conceptual: Describe la estructura completa de la base de datos para la comunidad de usuarios. Define las entidades, relaciones, operaciones y restricciones, ocultando los detalles de la implementación física.
  * Nivel Externo o de Visión del Usuario: Describe las diferentes vistas que los usuarios o aplicaciones tienen de la base de datos. Cada vista muestra solo la parte de la base de datos que es relevante para un grupo de usuarios específico.
* Independencia de Datos: Esta arquitectura consigue dos niveles de independencia cruciales:
  * Independencia Lógica: Permite modificar el esquema conceptual (p. ej., añadir una nueva entidad) sin necesidad de alterar los esquemas externos o las aplicaciones existentes.
  * Independencia Física: Permite modificar el esquema interno (p. ej., cambiar un fichero de almacenamiento o un índice) sin que afecte al esquema conceptual.

3.3. Tipos de SGBD (Contenido Prioritario - Sección 7.4)

Los SGBD se pueden clasificar atendiendo a diferentes criterios:

Criterio	Tipos	Descripción
1. Modelo Lógico	Relacional, Orientado a Objetos, Jerárquico, En Red.	Se basa en el modelo de datos subyacente. El relacional es el más utilizado; el orientado a objetos gana terreno.
2. Número de Usuarios	Monousuario, Multiusuario.	Define si el sistema atiende a un solo usuario a la vez o a múltiples usuarios de forma concurrente.
3. Distribución	Centralizados, Distribuidos.	Centralizados: Los datos se almacenan en un solo computador. Distribuidos: Los datos se reparten en varios sitios o nodos, pudiendo ser homogéneos (mismo SGBD) o heterogéneos (diferentes SGBD).
4. Costo	Comerciales, Libres/Gratuitos.	Clasificación según el modelo de licencia (p. ej., Oracle vs. PostgreSQL).
5. Propósito	Propósito General, Propósito Específico.	General: Utilizable para cualquier tipo de aplicación. Específico: Diseñado para un problema concreto (p. ej., sistemas de reservas de aerolíneas).

4. Arquitecturas de Almacenamiento

* Bases de Datos Centralizadas:
  * Arquitectura: Todo el sistema informático (SGBD, datos, dispositivos) reside en un único computador o sitio.
  * Características: No hay múltiples elementos de procesamiento ni mecanismos de intercomunicación como en las bases de datos distribuidas.
* Bases de Datos Distribuidas (BDD):
  * Arquitectura: Conjunto de múltiples bases de datos lógicamente relacionadas que se encuentran distribuidas en diferentes nodos de una red de comunicaciones.
  * Fragmentación: Para extraer los datos consultados, se puede realizar una fragmentación de distintas tablas. Este proceso se refiere al particionamiento de la información para distribuir cada parte a los diferentes sitios de la red.
