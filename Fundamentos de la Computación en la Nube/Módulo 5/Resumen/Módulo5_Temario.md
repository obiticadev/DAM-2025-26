# Leyenda de Colores

*   <mark style="background-color: #ffff00;">Amarillo</mark>: Conceptos fundamentales, definiciones y puntos clave.
*   <mark style="background-color: #90ee90;">Verde</mark>: Ventajas, objetivos, propósitos y características positivas.
*   <mark style="background-color: #add8e6;">Azul</mark>: Tipos, clasificaciones, componentes, estructuras y ejemplos.
*   <mark style="background-color: #ffa07a;">Rojo/Salmón</mark>: Problemas, inconvenientes, limitaciones o advertencias.
*   <mark style="background-color: #d3d3d3;">Gris</mark>: Tecnologías específicas, nombres propios, estándares o contenido prioritario.

***

# Guía Fundamental de Redes en AWS: De VPC a la Entrega Global de Contenido

## 1.0 Fundamentos Esenciales de Redes para la Nube

### 1.1. Introducción Estratégica a los Conceptos de Red

Una <mark style="background-color: #ffff00;">comprensión sólida de los conceptos básicos de redes informáticas</mark> es el pilar fundamental para diseñar, implementar y gestionar infraestructuras seguras y eficientes en la nube de <mark style="background-color: #d3d3d3;">AWS</mark>. Este conocimiento es la base necesaria para dominar servicios de red avanzados como <mark style="background-color: #d3d3d3;">Amazon Virtual Private Cloud (Amazon VPC)</mark>, permitiendo la creación de <mark style="background-color: #90ee90;">entornos robustos y escalables</mark>.

### 1.2. Análisis de los Componentes de una Red

*   **Red Informática:** En su forma más simple, <mark style="background-color: #ffff00;">una red consiste en dos o más máquinas clientes que están conectadas para compartir recursos</mark>.
*   **Dispositivos de Red:** Componentes como <mark style="background-color: #add8e6;">enrutadores y conmutadores</mark> son esenciales para <mark style="background-color: #90ee90;">conectar a los clientes y permitir la comunicación entre ellos</mark>.
*   **Subred:** <mark style="background-color: #ffff00;">Una subred es una división lógica de una red informática más grande</mark>, utilizada para <mark style="background-color: #90ee90;">organizar y segmentar el tráfico de manera eficiente</mark>.

### 1.3. Evaluación del Direccionamiento IP

La <mark style="background-color: #ffff00;">dirección de Protocolo de Internet (IP)</mark> es un identificador numérico único que se asigna a cada máquina dentro de una red para permitir su identificación y comunicación.

*   **Dirección IPv4:** Es una etiqueta numérica de <mark style="background-color: #add8e6;">32 bits</mark> en formato decimal. Se compone de cuatro números (conocidos como octetos), cada uno con un valor que puede ir de 0 a 255, separados por puntos (ej. <mark style="background-color: #add8e6;">192.0.2.0</mark>).
*   **Dirección IPv6:** Es una alternativa de <mark style="background-color: #add8e6;">128 bits</mark> diseñada para <mark style="background-color: #90ee90;">acomodar un número mucho mayor de dispositivos</mark>. Su formato utiliza ocho grupos de cuatro caracteres hexadecimales, separados por dos puntos (ej. <mark style="background-color: #add8e6;">2600:1f18:22ba:8c00:ba86:a05e:a5ba:00FF</mark>).

### 1.4. Desglose de la Notación CIDR (Enrutamiento entre Dominios sin Clases)

<mark style="background-color: #d3d3d3;">CIDR</mark> es un método estándar para expresar un grupo de direcciones IP consecutivas. La estructura de una dirección CIDR se compone de tres partes: una dirección IP inicial, una `/` y un <mark style="background-color: #add8e6;">número de prefijo que indica cuántos bits iniciales son fijos y definen la red</mark>. Los bits restantes son flexibles y determinan el número de direcciones disponibles.

*   **Ejemplo /24:** La notación <mark style="background-color: #add8e6;">192.0.2.0/24</mark> significa que los primeros 24 bits son fijos. Esto deja 8 bits flexibles, lo que resulta en <mark style="background-color: #add8e6;">256 direcciones disponibles (2⁸)</mark>, desde 192.0.2.0 hasta 192.0.2.255.
*   **Ejemplo /16:** La notación <mark style="background-color: #add8e6;">192.0.0.0/16</mark> significa que los primeros 16 bits son fijos. Esto deja 16 bits flexibles, lo que resulta en <mark style="background-color: #add8e6;">65,536 direcciones disponibles (2¹⁶)</mark>, desde 192.0.0.0 hasta 192.0.255.255.

Existen dos casos especiales de CIDR de gran utilidad:

*   **Dirección IP Única (/32):** Una dirección como <mark style="background-color: #add8e6;">192.0.2.0/32</mark> tiene los 32 bits fijos, representando un único host. Esto es útil para <mark style="background-color: #90ee90;">crear reglas de firewall muy específicas</mark>.
*   **Internet (0.0.0.0/0):** En esta notación, todos los bits son flexibles. Representa a <mark style="background-color: #add8e6;">todo Internet</mark> y se utiliza comúnmente en tablas de enrutamiento para dirigir el tráfico hacia el exterior.

### 1.5. El Modelo OSI como Marco Conceptual

El <mark style="background-color: #d3d3d3;">modelo de Interconexión de Sistemas Abiertos (OSI)</mark> es un marco conceptual de siete capas que estandariza las funciones de un sistema de telecomunicaciones o de computación, explicando cómo viajan los datos a través de una red. En el contexto de los dispositivos de red:

*   Los <mark style="background-color: #d3d3d3;">conmutadores (switches)</mark> operan en la <mark style="background-color: #add8e6;">Capa 2 (Enlace de Datos)</mark>.
*   Los <mark style="background-color: #d3d3d3;">enrutadores (routers)</mark> operan en la <mark style="background-color: #add8e6;">Capa 3 (Red)</mark>.

### 1.6. Transición a la Nube

Con estos conceptos fundamentales establecidos, ahora podemos explorar cómo se aplican en el entorno de la nube de <mark style="background-color: #d3d3d3;">AWS</mark> para construir redes virtuales personalizadas y seguras utilizando <mark style="background-color: #d3d3d3;">Amazon VPC</mark>.

## 2.0 Construyendo su Red Privada en la Nube con Amazon VPC

### 2.1. Importancia Estratégica de Amazon VPC

<mark style="background-color: #d3d3d3;">Amazon Virtual Private Cloud (Amazon VPC)</mark> es el servicio de AWS que permite a los usuarios aprovisionar una <mark style="background-color: #ffff00;">sección de la nube lógicamente aislada</mark>, otorgándoles un control total sobre su entorno de red virtual. Este servicio replica la funcionalidad de una red tradicional, pero con la <mark style="background-color: #90ee90;">flexibilidad, escalabilidad y resiliencia</mark> inherentes a la infraestructura de la nube de AWS.

### 2.2. Análisis de los Componentes Fundamentales de una VPC

*   **Amazon VPC:** Es una red virtual dedicada a una cuenta de AWS, lógicamente aislada de otras redes virtuales. Cada VPC pertenece a una <mark style="background-color: #ffa07a;">única región de AWS</mark>, pero está diseñada para abarcar <mark style="background-color: #90ee90;">múltiples zonas de disponibilidad</mark> dentro de esa región, facilitando la alta disponibilidad. Al crearla, es necesario definir su intervalo de direcciones IP usando un bloque CIDR.
*   **Subredes:** Son divisiones de una VPC donde se pueden lanzar recursos. Cada subred debe residir en una <mark style="background-color: #ffa07a;">única zona de disponibilidad</mark> y requiere su propio bloque CIDR (que debe ser un subconjunto del CIDR de la VPC). Las subredes se clasifican en:
    *   **Subredes Públicas:** Tienen una <mark style="background-color: #add8e6;">ruta directa hacia Internet</mark>. Son ideales para recursos como servidores web.
    *   **Subredes Privadas:** <mark style="background-color: #ffa07a;">No tienen acceso directo a Internet</mark>. Se utilizan para recursos de backend, como bases de datos, que necesitan permanecer aislados.

### 2.3. Gestión del Direccionamiento IP dentro de una VPC

Dentro de cada bloque CIDR de subred, <mark style="background-color: #d3d3d3;">AWS</mark> reserva <mark style="background-color: #ffa07a;">las primeras cuatro y la última dirección IP</mark> para fines operativos internos: dirección de red, enrutador de la VPC, resolución de DNS, uso futuro y dirección de difusión. Por ejemplo, una subred con un bloque /24 (256 direcciones IP totales) tendrá solo <mark style="background-color: #add8e6;">251 direcciones disponibles</mark> para los recursos.

Las instancias lanzadas en una VPC pueden tener los siguientes tipos de direcciones IP:

*   **Dirección IP Privada:** Asignada automáticamente del rango de la subred a cada instancia. Se utiliza para la <mark style="background-color: #90ee90;">comunicación interna</mark> dentro de la VPC.
*   **Dirección IP Pública:** Una dirección IP <mark style="background-color: #90ee90;">accesible desde Internet</mark> que puede ser asignada opcionalmente a una instancia al momento de su creación.
*   **Dirección IP Elástica:** Es una dirección <mark style="background-color: #add8e6;">IPv4 pública y estática</mark> diseñada para la computación dinámica en la nube. Su principal ventaja es que puede ser <mark style="background-color: #90ee90;">reasignada rápidamente de una instancia a otra</mark> para enmascarar fallos de hardware o software. Una práctica recomendada es asociar la IP Elástica a una <mark style="background-color: #d3d3d3;">Interfaz de Red Elástica</mark> en lugar de directamente a la instancia, ya que esto permite mover todos los atributos de la interfaz (incluida la IP) a otra instancia en un solo paso. Es importante notar que el uso de Direcciones IP Elásticas puede generar <mark style="background-color: #ffa07a;">costos adicionales</mark>.

### 2.4. Evaluación de las Interfaces de Red y el Enrutamiento

*   **Interfaz de Red Elástica (ENI):** Es una <mark style="background-color: #ffff00;">interfaz de red virtual que se puede conectar y desconectar de instancias EC2</mark>. Sus atributos, como la dirección IP privada o la dirección IP Elástica, se mueven con ella, permitiendo que el tráfico de red se redirija a una nueva instancia de forma transparente. Cada instancia tiene una interfaz de red principal que <mark style="background-color: #ffa07a;">no se puede desconectar</mark>.
*   **Tabla de Enrutamiento:** Contiene un conjunto de reglas, llamadas rutas, que determinan a dónde se dirige el tráfico de red que sale de una subred. Cada ruta especifica dos componentes clave:
    1.  **Destino:** El bloque CIDR de destino del tráfico (ej. 0.0.0.0/0 para Internet).
    2.  **Objetivo:** El gateway o dispositivo a través del cual se envía el tráfico (ej. una puerta de enlace de Internet).

Cada tabla de enrutamiento contiene una **ruta local predeterminada** que permite la comunicación entre los recursos dentro de la misma VPC. Esta ruta <mark style="background-color: #ffa07a;">no se puede modificar ni eliminar</mark>. Cada subred debe estar asociada a una tabla de enrutamiento. Si no se asocia explícitamente una, se utilizará la <mark style="background-color: #d3d3d3;">tabla de enrutamiento principal</mark> de la VPC.

### 2.5. Transición a la Seguridad

Una vez que la estructura de la red está definida con VPCs y subredes, y el flujo de tráfico se controla con tablas de enrutamiento, el siguiente paso crucial es asegurar esta infraestructura mediante la implementación de firewalls virtuales.

## 3.0 Asegurando su Infraestructura de VPC

### 3.1. El Rol Crítico de la Seguridad en Capas

Una estrategia de <mark style="background-color: #ffff00;">seguridad en profundidad</mark> es vital para proteger los recursos dentro de una VPC. AWS proporciona dos mecanismos de firewall complementarios: los <mark style="background-color: #d3d3d3;">Grupos de Seguridad</mark> y las <mark style="background-color: #d3d3d3;">Listas de Control de Acceso (ACL) de red</mark>. Estos operan en diferentes niveles de la infraestructura para proporcionar un control granular y en capas sobre el tráfico entrante y saliente.

### 3.2. Análisis de los Grupos de Seguridad (Firewall a Nivel de Instancia)

Un **Grupo de Seguridad** actúa como un firewall virtual que opera a <mark style="background-color: #add8e6;">nivel de instancia de EC2</mark>, controlando el tráfico que llega y sale de ella.

*   **Comportamiento Predeterminado:**
    *   **Reglas de Entrada:** Por defecto, no tienen ninguna regla de entrada, lo que significa que <mark style="background-color: #ffa07a;">todo el tráfico entrante es denegado</mark> hasta que se agreguen reglas de permiso explícitas.
    *   **Reglas de Salida:** Incluyen una regla por defecto que <mark style="background-color: #90ee90;">permite todo el tráfico saliente</mark>.
*   **Manejo de Estado (Stateful):** Los grupos de seguridad son <mark style="background-color: #ffff00;">con estado</mark>. Esto significa que si se permite una solicitud saliente desde una instancia, <mark style="background-color: #90ee90;">el tráfico de respuesta asociado a esa solicitud se permite automáticamente</mark>, sin necesidad de una regla de entrada correspondiente.
*   **Tipo de Reglas:** Solo se pueden especificar reglas de permiso (allow). <mark style="background-color: #ffa07a;">No es posible crear reglas de denegación (deny)</mark>.

### 3.3. Análisis de las ACL de Red (Firewall a Nivel de Subred)

Una **ACL de Red** es una capa opcional de seguridad que actúa como un firewall para controlar el tráfico a <mark style="background-color: #add8e6;">nivel de subred</mark>, afectando a todas las instancias dentro de ella.

*   **Comportamiento:**
    *   La ACL de red predeterminada que se crea con una VPC <mark style="background-color: #90ee90;">permite todo el tráfico IPv4 entrante y saliente</mark>.
    *   Una ACL de red personalizada, al ser creada, <mark style="background-color: #ffa07a;">deniega todo el tráfico entrante y saliente por defecto</mark>, requiriendo la adición de reglas explícitas.
*   **Manejo de Estado (Stateless):** Las ACL de red son <mark style="background-color: #ffff00;">sin estado</mark>. Esto implica que no rastrean las conexiones. Las respuestas al tráfico permitido <mark style="background-color: #ffa07a;">deben tener reglas explícitas correspondientes tanto para la entrada como para la salida</mark>.
*   **Evaluación de Reglas:** Contienen una lista numerada de reglas que se evalúan en orden, comenzando por el número más bajo. AWS recomienda crear reglas en <mark style="background-color: #90ee90;">incrementos (ej. 100, 200, 300)</mark> para facilitar la inserción de nuevas reglas en el futuro.
*   **Tipo de Reglas:** Admiten tanto reglas de <mark style="background-color: #add8e6;">permiso como de denegación</mark>.

### 3.4. Cuadro Comparativo: Grupos de Seguridad vs. ACL de Red

| Característica | Grupo de Seguridad | ACL de Red |
| :--- | :--- | :--- |
| **Nivel de Operación** | <mark style="background-color: #add8e6;">Instancia</mark>: El Grupo de Seguridad se aplica directamente a la interfaz de red de una instancia. | <mark style="background-color: #add8e6;">Subred</mark>: La ACL de Red se aplica a todas las instancias dentro de una subred. |
| **Tipos de Reglas** | <mark style="background-color: #ffa07a;">Solo Permiso</mark>: No se pueden crear reglas de denegación. | <mark style="background-color: #90ee90;">Permiso y Denegación</mark>: Se pueden crear reglas explícitas para permitir y denegar tráfico. |
| **Manejo de Estado** | <mark style="background-color: #ffff00;">Con Estado (Stateful)</mark>: El tráfico de respuesta se permite automáticamente. | <mark style="background-color: #ffff00;">Sin Estado (Stateless)</mark>: Se deben crear reglas explícitas para el tráfico de entrada y de salida. |
| **Evaluación de Reglas** | **Todas evaluadas**: Se procesan todas las reglas antes de tomar una decisión. | **Evaluadas en orden**: Las reglas se procesan en orden numérico y se aplica la primera coincidencia. |

### 3.5. Transición al DNS

Con la red debidamente segmentada y asegurada, el siguiente desafío es hacer que los recursos sean accesibles de manera confiable, eficiente y global. Esto nos lleva al rol fundamental del Sistema de Nombres de Dominio (DNS).

## 4.0 Gestión Global del Tráfico con Amazon Route 53

### 4.1. El Valor Estratégico de un DNS Inteligente

<mark style="background-color: #d3d3d3;">Amazon Route 53</mark> es mucho más que un simple servicio de DNS; es una herramienta estratégica para la <mark style="background-color: #90ee90;">gestión del tráfico a escala global</mark>. Su función principal es traducir nombres de dominio amigables para los humanos (como www.example.com) a las direcciones IP numéricas que utilizan las máquinas. Sin embargo, su verdadero poder reside en su capacidad para enrutar a los usuarios a la mejor infraestructura de AWS (o externa) basándose en criterios avanzados como el rendimiento, el estado de salud de la aplicación y la ubicación geográfica del usuario.

### 4.2. Funcionalidades Clave de Amazon Route 53

Amazon Route 53 es un servicio web de Sistema de Nombres de Dominio (DNS) altamente disponible, escalable y compatible con IPv6. Sus capacidades principales incluyen:

*   **Resolución de Nombres:** Su función fundamental de convertir nombres de dominio en direcciones IP.
*   **Verificaciones de Estado:** <mark style="background-color: #90ee90;">Monitorea la salud de las aplicaciones</mark> y sus puntos de conexión, permitiendo dirigir el tráfico únicamente a los recursos que funcionan correctamente.
*   **Flujo de Tráfico:** Ofrece un editor visual para administrar el tráfico globalmente mediante políticas de enrutamiento avanzadas y configuraciones de conmutación por error.
*   **Registro de Dominios:** Permite a los usuarios comprar y administrar nombres de dominio directamente a través del servicio.

### 4.3. Análisis de las Políticas de Enrutamiento

Route 53 ofrece un conjunto de políticas de enrutamiento para controlar cómo responde a las consultas de DNS:

*   **Enrutamiento Simple:** Se utiliza para un <mark style="background-color: #add8e6;">único recurso</mark> que realiza una función específica, como un servidor web.
*   **Enrutamiento Ponderado:** Distribuye el tráfico entre múltiples recursos en <mark style="background-color: #add8e6;">proporciones específicas (pesos)</mark>. Es ideal para realizar <mark style="background-color: #90ee90;">pruebas A/B</mark>, enviando un pequeño porcentaje de usuarios a una nueva versión de una aplicación.
*   **Enrutamiento de Latencia:** Dirige a los usuarios a la región de AWS que les ofrece la <mark style="background-color: #90ee90;">menor latencia</mark>, proporcionando la experiencia más rápida posible.
*   **Enrutamiento de Geolocalización:** Enruta el tráfico basándose en la <mark style="background-color: #add8e6;">ubicación geográfica de los usuarios</mark>. Es útil para localizar contenido, presentar un sitio web en el idioma del usuario o restringir la distribución de contenido a ciertas áreas.
*   **Enrutamiento de Geoproximidad:** Dirige el tráfico basándose en la ubicación de sus recursos y, opcionalmente, permite aplicar un "sesgo" para desviar más o menos tráfico de una ubicación a otra, ajustando dinámicamente la distribución de la carga.
*   **Enrutamiento de Conmutación por Error (Failover):** Permite configurar una arquitectura <mark style="background-color: #add8e6;">activa-pasiva</mark>. Si el recurso principal falla una verificación de estado, Route 53 <mark style="background-color: #90ee90;">redirige automáticamente el tráfico a un recurso secundario</mark>.
*   **Direccionamiento de Respuesta con Varios Valores:** Responde a las consultas de DNS con hasta ocho registros en buen estado, seleccionados al azar. Esto mejora la disponibilidad y proporciona un <mark style="background-color: #90ee90;">balanceo de carga básico</mark> a nivel de DNS.

### 4.4. Transición a la Entrega de Contenido

Mientras que Amazon Route 53 dirige a los usuarios al punto de conexión correcto, el rendimiento de la experiencia del usuario también depende de la rapidez con la que se entrega el contenido desde ese punto. Esto introduce la necesidad de una Red de Entrega de Contenido (CDN).

## 5.0 Aceleración de la Entrega de Contenido con Amazon CloudFront

### 5.1. Introducción Estratégica a las Redes de Entrega de Contenido (CDN)

Uno de los mayores desafíos para el rendimiento en la web es la <mark style="background-color: #ffa07a;">latencia</mark>, causada por la distancia física y los múltiples saltos de red entre un usuario y el servidor de origen donde se aloja el contenido. Una <mark style="background-color: #d3d3d3;">Red de Entrega de Contenido (CDN)</mark> es la solución estratégica a este problema. Funciona como un sistema distribuido de servidores que <mark style="background-color: #90ee90;">almacena en caché (guarda copias de) el contenido cerca de los usuarios finales</mark>, acelerando drásticamente su entrega.

### 5.2. Análisis del Funcionamiento de Amazon CloudFront

<mark style="background-color: #d3d3d3;">Amazon CloudFront</mark> es el servicio de CDN de AWS, diseñado para entregar de forma segura datos, videos, aplicaciones y APIs a clientes de todo el mundo con <mark style="background-color: #90ee90;">baja latencia y altas velocidades de transferencia</mark>. Su arquitectura global se basa en dos componentes clave:

*   **Ubicaciones de Borde (Edge Locations):** <mark style="background-color: #ffff00;">Es una red mundial de centros de datos donde CloudFront almacena en caché el contenido</mark>. Cuando un usuario solicita contenido, su solicitud se dirige automáticamente a la ubicación de borde que le ofrezca la menor latencia, garantizando el mejor rendimiento posible.
*   **Cachés Perimetrales Regionales:** Actúan como una capa intermedia entre los servidores de origen y las ubicaciones de borde. Tienen una <mark style="background-color: #add8e6;">capacidad de caché más grande</mark>, lo que permite que los objetos menos populares permanezcan en caché más tiempo y más cerca de los usuarios. Esto reduce la necesidad de que CloudFront vuelva al servidor de origen y mejora el rendimiento general.

CloudFront es capaz de entregar tanto contenido estático (como imágenes, CSS y JavaScript, que se almacenan en la caché) como dinámico (que se recupera del origen aprovechando las conexiones optimizadas y seguras de la red de AWS).

### 5.3. Evaluación de los Beneficios Clave de CloudFront

*   **Rapidez y Alcance Mundial:** Aprovecha la red global de ubicaciones de borde masivamente escalada y distribuida de AWS para entregar contenido a los usuarios con una latencia mínima.
*   **Seguridad en el Borde:** Proporciona protección a nivel de red y de aplicación. Se integra de forma nativa con <mark style="background-color: #d3d3d3;">AWS Shield Standard</mark> sin costo adicional para mitigar ataques DDoS.
*   **Alta Capacidad de Programación:** Permite la personalización del comportamiento de la CDN mediante funciones <mark style="background-color: #d3d3d3;">Lambda@Edge</mark>, que ejecutan código personalizado en las ubicaciones de borde, acercando la lógica de la aplicación a los usuarios.
*   **Integración Profunda con AWS:** Está conectado directamente a la infraestructura global de AWS y se integra perfectamente con otros servicios como Amazon S3 y Elastic Load Balancing.
*   **Rentable:** Opera con un modelo de precios de pago por uso sin compromisos mínimos. Reduce los costos al disminuir la carga en los servidores de origen y, si se utilizan orígenes de AWS, <mark style="background-color: #90ee90;">no se cobra por la transferencia de datos entre esos servicios y CloudFront</mark>.

### 5.4. Desglose del Modelo de Precios

Los costos de Amazon CloudFront se basan en el uso real y se componen de cuatro áreas principales:

1.  **Transferencia de Datos:** Se cobra por el volumen de datos (en GB) transferidos desde las ubicaciones de borde de CloudFront hacia Internet o hacia sus servidores de origen.
2.  **Solicitudes HTTP(S):** Se cobra por el número total de solicitudes HTTP o HTTPS que el servicio procesa.
3.  **Solicitudes de Invalidaciones:** Se cobran por ruta de invalidación después de las primeras 1,000 rutas gratuitas por mes, permitiendo forzar la actualización del contenido en la caché.
4.  **IP Dedicada para SSL:** Se aplica una <mark style="background-color: #d3d3d3;">tarifa mensual de 600 USD</mark> (prorrateada por hora) si se utiliza un certificado SSL personalizado con la opción de IP dedicada.

## 6.0 Conclusión: Arquitectura de una Red Completa en AWS

### 6.1. Síntesis del Recorrido

Este documento ha recorrido el camino completo para construir una infraestructura de red moderna en AWS. Hemos partido de los conceptos universales de redes informáticas y direccionamiento IP, para luego aplicarlos en la creación de una red virtual privada y segura con Amazon VPC. A continuación, hemos explorado cómo gestionar el tráfico a escala global de manera inteligente con las políticas de enrutamiento de Amazon Route 53. Finalmente, hemos abordado cómo optimizar la experiencia del usuario final acelerando la entrega de contenido a nivel mundial con Amazon CloudFront. Juntos, estos servicios forman un conjunto de herramientas poderoso para construir aplicaciones seguras, escalables y de alto rendimiento en la nube.

### 6.2. Resumen de Competencias Adquiridas

Al finalizar este recorrido, usted será capaz de:

*   <mark style="background-color: #90ee90;">Reconocer los aspectos fundamentales de redes.</mark>
*   <mark style="background-color: #90ee90;">Explicar el funcionamiento de las redes virtuales en la nube con Amazon VPC.</mark>
*   <mark style="background-color: #90ee90;">Diseñar una arquitectura de VPC básica.</mark>
*   <mark style="background-color: #90ee90;">Identificar las funciones de los grupos de seguridad y las ACL de red.</mark>
*   <mark style="background-color: #90ee90;">Identificar los fundamentos de Amazon Route 53.</mark>
*   <mark style="background-color: #90ee90;">Reconocer los beneficios de Amazon CloudFront.</mark>