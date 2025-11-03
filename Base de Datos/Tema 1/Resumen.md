Guía Estructurada sobre Almacenamiento de Información y Bases de Datos

Parte I: Fundamentos del Almacenamiento de Información Basado en Ficheros

Esta sección aborda los conceptos iniciales de almacenamiento de datos, centrados en la organización y gestión mediante ficheros, que sentaron las bases para los sistemas de información posteriores.

1. Introducción al Almacenamiento de Datos

El almacenamiento y gestión de datos es una constante en la vida cotidiana, presente en acciones como seleccionar un canal de TDT, usar la agenda del móvil, operar en un cajero automático o consultar información en Internet. La evolución desde los archivos en papel en los años setenta hasta los sistemas digitales actuales ha sido impulsada por la necesidad de un tratamiento más eficiente de la información.

* Evolución Histórica: La primera informatización en la década de los setenta se centró en la contabilidad y facturación, trasladando los datos del papel a un formato digital para un acceso más rápido. La terminología (ficheros, carpetas, formularios) se adaptó del entorno manual.
* Concepto de Fichero: Es el elemento que permite el almacenamiento permanente de datos en dispositivos de memoria masiva para su posterior recuperación, consulta y procesamiento.
* Estructura de un Fichero:
  * Registros: Contienen datos relativos a un mismo elemento u objeto (p. ej., datos de un usuario).
  * Campos: Divisiones dentro de un registro que contienen información elemental (p. ej., nombre o email del usuario).
* Conceptos de Transferencia:
  * Bloque: Cantidad de información transferida entre el soporte de almacenamiento y la memoria principal en una única operación de lectura/escritura.
  * Empaquetado (Blocaje): Operación de agrupar varios registros lógicos en un bloque.
  * Factor de Blocaje: Número de registros que caben en un bloque.

2. Clasificación de Ficheros

Los ficheros se pueden clasificar según la función que desempeñan dentro de una aplicación.

Tipo de Fichero	Subtipo	Descripción	Ejemplo
Permanentes	Maestros	Contienen el estado actual de los datos que pueden ser modificados por la aplicación.	Fichero de datos de usuarios de una plataforma educativa.
	Constantes	Incluyen datos fijos que no suelen ser modificados y se acceden para consulta.	Fichero con códigos postales.
	Históricos	Almacenan datos que fueron actuales en un momento anterior; se usan para reconstruir situaciones.	Fichero con los usuarios dados de baja de una plataforma.
Temporales	Intermedios	Almacenan resultados de una aplicación que serán utilizados por otra.	
	De maniobra	Guardan datos de una aplicación que no caben en memoria principal por falta de espacio.	
	De resultados	Acumulan datos que serán transferidos a un dispositivo de salida.	

3. Soportes Físicos de Información

La evolución del hardware ha sido clave en el desarrollo de los sistemas de almacenamiento.

* Soportes Secuenciales: El acceso a los datos se realiza de forma lineal, desde el principio hasta la posición deseada. Se utilizan principalmente para copias de seguridad.
  * Ejemplo: Cintas magnéticas.
* Soportes Direccionables (de Acceso Directo): Permiten posicionarse directamente en la ubicación del dato de interés sin recorrer la información previa. Son los más empleados en la actualidad.
  * Ejemplos: Discos magnéticos, discos ópticos, discos magneto-ópticos, unidades de estado sólido (SSD).

4. Métodos de Acceso y Organización de Ficheros

La organización de un fichero define cómo se estructuran y acceden sus registros. Los objetivos principales son proporcionar acceso rápido, economizar almacenamiento, facilitar actualizaciones y reflejar la organización real de la información.

4.1. Organización Secuencial

Los registros se almacenan de forma contigua y se leen uno tras otro desde el inicio.

* Identificador: Utiliza una marca de fin de fichero (EOF).
* Campo Clave: Un campo específico que, si se usa para ordenar el fichero, agiliza las operaciones.
* Características Principales:
  * Lectura siempre hacia adelante.
  * Monousuario (no permite acceso simultáneo).
  * Estructura rígida de campos.
  * Aprovechamiento máximo del espacio de almacenamiento.
  * No se pueden insertar registros entre los ya grabados.
  * Puede grabarse en cualquier tipo de soporte.

4.2. Organización de Acceso Directo (Aleatorio)

Permite acceder a un registro directamente indicando su posición relativa o, más comúnmente, a través de una clave.

* Requisito: Debe almacenarse en dispositivos de acceso directo.
* Acceso: Una transformación aplicada a la clave obtiene la dirección física del registro. En el modo más rápido, la clave numérica coincide con la dirección.
* Características Principales:
  * Posicionamiento inmediato.
  * Registros de longitud fija.
  * Permite apertura en modo mixto (lectura y escritura).
  * Soporta múltiples usuarios.
  * Permite la actualización de registros en tiempo real.

4.3. Organización Indexada

Se basa en el uso de índices, que son tablas que relacionan la clave de un registro con su posición física en el fichero.

* Estructura: Consta de una zona de datos (con los registros) y una zona de índices (tabla de claves y direcciones, ordenada por clave).
* Funcionamiento: Para encontrar un registro, se busca su clave en la tabla de índices (cargada en memoria) para obtener la dirección y luego se accede directamente a la zona de datos.
* Claves: Un campo debe servir como clave primaria (identificador único). Pueden existir claves alternativas.
* Modos de Acceso: Permite tanto el acceso directo (a través del índice) como el secuencial (leyendo los registros ordenados según la clave del índice).

4.4. Otras Organizaciones de Ficheros

* Secuencial Indexada (Parcialmente Indexada): Combina las organizaciones secuencial e indexada. La zona de datos se divide en segmentos (bloques), y el índice apunta al inicio de cada segmento. La búsqueda es directa hasta el segmento y secuencial dentro de él. Es muy utilizada por su flexibilidad.
* Acceso Calculado (Hashing): Utiliza una función de hashing (un algoritmo matemático) para convertir la clave de un registro directamente en su dirección física, eliminando la necesidad de una tabla de índices y agilizando el acceso.
  * Problema: Las colisiones ocurren cuando diferentes claves (llamadas sinónimos) generan la misma dirección. Se resuelven con técnicas como zonas de excedentes.

5. Parámetros de Utilización de Ficheros

Estos parámetros ayudan a determinar qué tipo de organización de fichero es más adecuada según su uso.

* Capacidad: Espacio total que ocupa el fichero.
* Actividad: Frecuencia de consultas y modificaciones. Se mide con la Tasa de Actividad (% de registros afectados) y la Frecuencia de Acceso.
* Volatilidad: Frecuencia de inserciones y borrados. Se mide con la Tasa de Renovación (% de registros renovados) y la Frecuencia de Renovación.
* Crecimiento: Variación de la capacidad del fichero, medida por la Tasa de Crecimiento.


--------------------------------------------------------------------------------


Parte II: Sistemas de Bases de Datos

Esta sección explora el concepto de base de datos como una evolución de los sistemas de ficheros, solucionando problemas como la redundancia y la inconsistencia de datos.

1. Introducción a las Bases de Datos (BD)

Una base de datos es una "colección de datos relacionados lógicamente entre sí, con una definición y descripción comunes y que están estructurados de una determinada manera". Su objetivo es almacenar datos con mínima redundancia y permitir un acceso eficiente.

* Componentes Clave:
  * Datos: La información en sí.
  * Metadatos: Datos que describen los datos (su estructura, tipos, relaciones). Se almacenan en el diccionario de datos o catálogo y permiten la independencia entre la lógica del programa y el almacenamiento físico.
* Elementos Estructurales:
  * Entidades: Objetos del mundo real o abstractos sobre los que se almacena información (ej. un cliente, un producto).
  * Atributos: Propiedades o características de una entidad (ej. nombre, precio).
  * Registros: Conjunto de atributos que describen una instancia de una entidad.

2. Ventajas de las Bases de Datos

Ventaja	Descripción
Acceso Múltiple	Varios usuarios o aplicaciones pueden acceder a los datos simultáneamente.
Utilización Múltiple	Cada usuario puede tener una visión particular de la estructura de la BD.
Flexibilidad	El acceso a la información se puede establecer de diferentes maneras.
Confidencialidad y Seguridad	El control de acceso impide que usuarios no autorizados utilicen la BD.
Protección contra Fallos	Existen mecanismos para la recuperación de la información en caso de error.
Independencia Física	Cambios en el soporte físico no afectan a la base de datos o aplicaciones.
Independencia Lógica	Cambios en la estructura lógica no afectan a las aplicaciones que la usan.
Redundancia Controlada	Los datos se almacenan una única vez, reduciendo inconsistencias.
Interfaz de Alto Nivel	Se utilizan lenguajes de alto nivel para un uso más sencillo y cómodo.
Consulta Directa	Existen herramientas para poder acceder a los datos interactivamente.

3. Usos y Usuarios de las Bases de Datos

* Tipos de Usuarios:
  * Administrador (DBA): Responsable de la implementación física, seguridad y rendimiento.
  * Diseñadores: Definen la estructura lógica y los procesos de la base de datos.
  * Programadores de Aplicaciones: Crean los programas que interactúan con la BD.
  * Usuarios Finales: Consumen la información a través de las aplicaciones.
* Ámbitos de Aplicación: Las bases de datos son omnipresentes en sectores como la banca, líneas aéreas, telecomunicaciones, medicina, justicia, hostelería, ocio y cultura.

4. Ubicación y Almacenamiento de la Información

Los sistemas de almacenamiento más utilizados para bases de datos incluyen:

* Discos SATA y SCSI: Interfaces estándar para discos duros, con SCSI orientado a entornos de alto rendimiento.
* RAID (Redundant Array of Independent Disks): Tecnología que combina múltiples discos para mejorar el rendimiento, la seguridad o ambos.
* NAS (Network Attached Storage): Dispositivo de almacenamiento conectado a una red que permite el acceso centralizado a los datos.
* SAN (Storage Area Network): Red de alta velocidad dedicada exclusivamente a conectar servidores y dispositivos de almacenamiento masivo.

5. Modelos de Bases de Datos

Un modelo de datos define la estructura lógica de una base de datos.

* Modelo Jerárquico: Organiza los datos en una estructura de árbol (padre-hijo), donde un hijo solo tiene un padre. Actualmente en desuso.
* Modelo en Red: Similar al jerárquico, pero permite que un nodo hijo tenga múltiples padres, creando una estructura de grafo.
* Modelo Relacional: Desarrollado por Codd en 1970, es el modelo más extendido. Organiza los datos en tablas bidimensionales (relaciones), compuestas por filas (tuplas) y columnas (atributos). Utiliza SQL (Structured Query Language) como lenguaje estándar.
* Modelo Orientado a Objetos: Define la base de datos en términos de objetos, clases, herencia y polimorfismo, alineándose con los paradigmas de la programación orientada a objetos (POO).
* Otros Modelos:
  * Objeto-Relacional: Híbrido que combina la simplicidad del modelo relacional con las capacidades del modelo orientado a objetos.
  * Orientado a Documentos: Almacena datos en documentos semi-estructurados (p. ej., JSON, XML). Ejemplos: MongoDB, CouchDB.
  * Multidimensional: Estructura los datos en cubos (dimensiones) para análisis complejos (OLAP).
  * Transaccional: Optimizado para la velocidad en el procesamiento de un gran volumen de transacciones.

6. Sistemas Gestores de Bases de Datos (SGBD)

Un SGBD (o DBMS en inglés) es el conjunto de software que permite a los usuarios crear, mantener y controlar el acceso a una base de datos.

* Funciones Principales:
  * Definición de Datos (DDL): Permite crear y definir las estructuras de la base de datos.
  * Manipulación de Datos (DML): Permite insertar, modificar, borrar y consultar los datos.
  * Control de Datos (DCL): Gestiona la seguridad, los permisos de usuario y la integridad.
* Componentes del SGBD: Incluye los lenguajes (DDL, DML, DCL), el diccionario de datos, el gestor de la base de datos (motor), los usuarios y herramientas de administración.
* Arquitectura de Tres Niveles (ANSI/SPARC):
  * Nivel Interno (Físico): Cómo se almacenan físicamente los datos.
  * Nivel Conceptual (Lógico): Visión unificada de toda la base de datos (entidades, relaciones).
  * Nivel Externo (De Visión): Vistas parciales de la base de datos para usuarios específicos. Esta arquitectura garantiza la independencia física y lógica de los datos.

7. Sistemas Comerciales vs. Sistemas Libres

Tipo	SGBD Ejemplos	Descripción
Comerciales	Oracle, Microsoft SQL Server, DB2, Informix	Productos con licencia que ofrecen soporte y funcionalidades avanzadas, a menudo para grandes corporaciones.
Libres	MySQL, PostgreSQL, Firebird, SQLite, Apache Derby	Sistemas de código abierto, a menudo gratuitos, con una gran comunidad de soporte. Son multiplataforma y muy populares en desarrollo web y aplicaciones de diversos tamaños.

8. Bases de Datos Centralizadas vs. Distribuidas

8.1. Bases de Datos Centralizadas

La arquitectura completa del sistema (SGBD, datos) reside en un único computador.

* Ventajas: Seguridad centralizada, mayor integridad, rendimiento optimizado para ese entorno, menor coste de mantenimiento.
* Inconvenientes: Punto único de fallo (si el sistema cae, todo se detiene), dificultad de recuperación ante desastres, menor potencia de cómputo comparada con sistemas distribuidos.

8.2. Bases de Datos Distribuidas (BDD)

Conjunto de múltiples bases de datos lógicamente relacionadas que se encuentran distribuidas en diferentes nodos de una red.

* Ventajas: Mayor tolerancia a fallos (si un nodo cae, el resto sigue funcionando), acceso más rápido a los datos locales, escalabilidad, adaptación a la estructura geográfica de una organización.
* Inconvenientes: Mayor complejidad en la gestión (control de concurrencia, recuperación), posibles brechas de seguridad entre nodos, coste inicial más elevado.
* Fragmentación: Técnica utilizada en BDD que consiste en particionar las tablas de la base de datos y distribuir los fragmentos en diferentes nodos de la red para optimizar las consultas y el almacenamiento. El objetivo es encontrar un nivel de particionamiento equilibrado.
