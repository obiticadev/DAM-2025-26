# Resumen Tema 9 y Tema 10


# Resumen de Direccionamiento IPv4

## 1. Conceptos Fundamentales

Una dirección IP es el "DNI" de un equipo en una red. La versión IPv4 tiene **32 bits**, divididos en 4 grupos de 8 bits (llamados octetos).

### Definiciones Clave

| Término | Definición |
| --- | --- |
| **NetID (ID de red)** | La parte de la IP que identifica a qué "barrio" o red pertenece el equipo. |
| **HostID (ID de equipo)** | La parte de la IP que identifica de forma única al equipo dentro de esa red. |
| **Máscara de Red** | Un código que dice dónde termina el NetID y dónde empieza el HostID. (1s para red, 0s para host). |
| **Puerta de Enlace (Gateway)** | La IP del router que permite a los equipos de una red local salir a internet u otras redes. |
| **NAT** | Mecanismo que permite que muchos equipos con IPs privadas salgan a internet con una sola IP pública. |

---

## 2. Direcciones Especiales (Las que no puedes usar para un PC)

Existen ciertas IPs que están reservadas para funciones específicas:

- **0.0.0.0**: Host actual (se usa cuando un equipo aún no tiene IP).
- **Dirección de Red**: Es la primera IP del rango (HostID todo a ceros). Identifica a la red entera.
- **Dirección de Broadcast**: Es la última IP del rango (HostID todo a unos). Sirve para enviar un mensaje a **todos** los equipos de la red a la vez.
- **127.0.0.1 (Loopback)**: Se usa para que el ordenador se hable a sí mismo (pruebas de conectividad local).

---

## 3. Clasificación de Redes

### IPs Públicas vs. Privadas

- **Públicas**: Visibles en todo Internet, asignadas por el proveedor (ISP).
- **Privadas**: Solo funcionan dentro de tu casa/oficina. Se pueden repetir en distintas casas del mundo.

### Rangos de IPs Privadas (Muy importante)

| Clase | Rango de IPs | Máscara por defecto |
| --- | --- | --- |
| **Clase A** | 10.0.0.0 a 10.255.255.255 | 255.0.0.0 (/8) |
| **Clase B** | 172.16.0.0 a 172.31.255.255 | 255.255.0.0 (/16) |
| **Clase C** | 192.168.0.0 a 192.168.255.255 | 255.255.255.0 (/24) |

---

# Guía de Cálculo (Paso a Paso)

Vamos a resolver un ejercicio difícil de los que tienes ahí para que veas el proceso: **172.16.5.130 /25**

### Paso 1: Entender el CIDR (el número después de la barra)

El **/25** significa que los primeros **25 bits** son de "Red" y el resto son de "Host".
Como el total son 32 bits: 32 - 25 = 7 bits para equipos (Host).

### Paso 2: Convertir a binario el octeto problemático

La IP es `172.16.5.130`. Los primeros tres números (172.16.5) suman 24 bits (8+8+8). El bit número 25 cae dentro del último número: **130**.

**¿Cómo paso 130 a binario?**
Usa esta "regla" de potencias de 2:
`128 | 64 | 32 | 16 | 8 | 4 | 2 | 1`

- ¿Cabe 128 en 130? Sí (1). Me sobran 2.
- ¿Cabe 64 en 2? No (0).
- ¿Cabe 32 en 2? No (0).
- ... (así hasta el 2)
- ¿Cabe 2 en 2? Sí (1). Me sobra 0.
- ¿Cabe 1 en 0? No (0).

Resultado: **10000010**

### Paso 3: Separar Red de Host

Dijimos que el bit 25 es de Red. Vamos a marcar el 130 binario separando el primer bit (el 25) de los otros 7:
Binario: `(1) | 0000010`*(La parte entre paréntesis es Red, lo demás es Host)*.

### Paso 4: Hallar la Dirección de Red

Para la dirección de red, ponemos todos los bits de Host a **0**.
Binario: `(1) | 0000000` -> Esto en decimal es **128**.
**Resultado Red: 172.16.5.128**

### Paso 5: Hallar la Dirección de Broadcast

Para el broadcast, ponemos todos los bits de Host a **1**.
Binario: `(1) | 1111111`*(Suma: 128 + 64 + 32 + 16 + 8 + 4 + 2 + 1 = 255)*.
**Resultado Broadcast: 172.16.5.255**

### Paso 6: Rango de Hosts (Equipos)

Es tan fácil como: (IP de Red + 1) hasta (IP de Broadcast - 1).

- Primer equipo: 172.16.5.**129**
- Último equipo: 172.16.5.**254**

### Paso 7: ¿Cuántos equipos caben?

Fórmula: 2^n - 2 (donde n es el número de bits de host).
Aquí tenemos 7 bits de host:
2^7 = 128
128 - 2 = **126 equipos posibles.**

---

## Resumen de los ejercicios de tu imagen:

1. **192.168.10.25 /24**:
    - Red: 192.168.10.0
    - Broadcast: 192.168.10.255
    - Rango: 192.168.10.1 a 192.168.10.254
2. **10.0.1.100 /24**:
    - Red: 10.0.1.0
    - Broadcast: 10.0.1.255
    - Rango: 10.0.1.1 a 10.0.1.254
3. **10.20.5.7 /12**: (Este es más complejo porque la máscara corta en el segundo octeto, pero la lógica binaria es la misma).

---

# Resumen: División en Subredes (Subnetting)

### ¿Qué es y para qué sirve?

Subnetear es como coger una pizza grande (una red) y cortarla en porciones (subredes).

- **Optimización:** No desperdicias IPs.
- **Seguridad/Organización:** Permite que equipos de diferentes departamentos (ej. Aula A y Aula B) estén aislados y no se vean entre sí si no queremos.

### Conceptos Clave

| Término | Definición  |
| --- | --- |
| **Bits Prestados** | Son bits que le "robamos" a la parte de los equipos (HostID) para dárselos a la red para crear divisiones. |
| **Salto de Red** | Es el número que nos dice de cuánto en cuánto van las subredes (ej. de 64 en 64). |
| **Máscara de Subred** | Es más larga que la original (tiene más números 1 en binario) porque ahora tiene que identificar también las divisiones. |

---

# Guía Paso a Paso: Cómo hacer Subnetting

**Dividir la red 192.168.34.0 en 4 subredes.**

### Paso 1: ¿Cuántos bits necesito "robar"?

Usamos la fórmula 2^n, donde n son los bits que pedimos prestados. El resultado tiene que ser igual o mayor al número de subredes que queremos.

- Queremos 4 subredes.
- 2^1 = 2 (No llega)
- 2^2 = 4 (**¡Bingo!** Necesitamos **2 bits**).

### Paso 2: Calcular la nueva Máscara de Red

La red original es Clase C, por lo que su máscara natural es `/24` (255.255.255.0).
Como hemos robado 2 bits, la nueva máscara es 24 + 2 = **/26**.

**¿Cómo se escribe /26 en decimal (con puntos)?**
Escribimos 26 unos en binario y rellenamos hasta 32 con ceros:
`11111111 . 11111111 . 11111111 . 11000000`
(255) . (255) . (255) . (**?**)

Para el último octeto (`11000000`):

- El primer 1 vale 128.
- El segundo 1 vale 64.
- 128 + 64 = **192**.
- **Máscara final: 255.255.255.192**

### Paso 3: Calcular el "Salto" (Tamaño de la porción)

Resta el número de la máscara al número total (256):
256 - 192 = 64.
Esto significa que las redes van a saltar de **64 en 64**.

---

# Resolución del Ejercicio de la Imagen

**Enunciado:** Dividir `192.168.34.0` en 4 subredes.

### 1. ¿Cuántos nodos (equipos) puede tener cada subred?

Usamos los bits que sobraron para hosts. Si la máscara es /26, nos quedan 32 - 26 = 6 **bits para equipos**.

- Fórmula: 2^6 - 2 = 64 - 2 = **62 nodos por subred.**
- *(Se resta 2 porque la primera IP es la red y la última es el broadcast).*

### 2. ¿Cuántas direcciones IP se pierden?

En cada subred se pierden 2 IPs (Red y Broadcast).

- Como hay 4 subredes: 4 \times 2 = **8 IPs perdidas en total.**

### 3. Tabla de las 4 subredes (Cálculo de direcciones)

Para sacar esto rápido:

1. La **Dirección de Red** empieza en 0 y sumas el "salto" (64).
2. El **Broadcast** es siempre uno menos que la siguiente red.
3. El **Primer Equipo** es Red + 1.
4. El **Último Equipo** es Broadcast - 1.

| Subred | Dir. Red | Primer Equipo | Último Equipo | Broadcast | Máscara |
| --- | --- | --- | --- | --- | --- |
| **1** | 192.168.34.**0** | 192.168.34.1 | 192.168.34.62 | 192.168.34.**63** | /26 (255.255.255.192) |
| **2** | 192.168.34.**64** | 192.168.34.65 | 192.168.34.126 | 192.168.34.**127** | /26 (255.255.255.192) |
| **3** | 192.168.34.**128** | 192.168.34.129 | 192.168.34.190 | 192.168.34.**191** | /26 (255.255.255.192) |
| **4** | 192.168.34.**192** | 192.168.34.193 | 192.168.34.254 | 192.168.34.**255** | /26 (255.255.255.192) |

# **Guía Esencial: Clasificación y Tipos de Redes Informáticas**

### 1. Según su alcance (extensión)

- **PAN (Personal):** Red para un solo usuario en un espacio muy corto.
    - *Ejemplo:* Conectar unos auriculares Bluetooth al móvil.
- **LAN (Local):** Red que cubre un edificio o lugar pequeño.
    - *Ejemplo:* El Wi-Fi de tu casa o de una oficina.
- **Campus:** Conecta varias redes LAN dentro de un recinto específico.
    - *Ejemplo:* La red que une las facultades de una Universidad.
- **MAN (Metropolitana):** Red que abarca una ciudad entera.
    - *Ejemplo:* La red de cámaras de tráfico de un ayuntamiento.
- **WAN (Área amplia):** Red que conecta países o continentes.
    - *Ejemplo:* Internet.

### 2. Según la función de sus equipos

- **Igual a igual (P2P):** Todos los ordenadores tienen el mismo mando y no hay un jefe central.
    - *Ejemplo:* Pasar un archivo de un PC a otro directamente por cable.
- **Cliente-Servidor:** Un ordenador potente (servidor) manda y da servicios a los demás (clientes).
    - *Ejemplo:* Cuando entras en una web (tú eres el cliente y la web está en un servidor).

### 3. Según el tipo de conexión

- **Cableada:** Los datos viajan por cables físicos.
    - *Ejemplo:* Conectar tu ordenador al router con un cable Ethernet.
- **Inalámbrica:** Los datos viajan por el aire mediante ondas.
    - *Ejemplo:* Conectar el móvil al Wi-Fi.

### 4. Según el grado de difusión

- **Internet:** Red mundial abierta donde todos están conectados bajo unas mismas reglas (TCP/IP).
    - *Ejemplo:* Navegar por Google.
- **Intranet:** Funciona como internet, pero es privada y solo para gente de dentro.
    - *Ejemplo:* El portal interno de una empresa para que los empleados vean sus nóminas.

### 5. Según su acceso y administración

- **Red pública:** Cualquier persona puede entrar y usarla.
    - *Ejemplo:* El Wi-Fi abierto de un aeropuerto.
- **Red privada:** Solo pueden entrar personas con permiso o contraseña.
    - *Ejemplo:* La red interna de seguridad de un banco.
- **Red híbrida:** Una mezcla donde se usa una red pública para entrar de forma segura a una privada.
    - *Ejemplo:* Una conexión VPN para trabajar desde casa.

# **2. Topologías de Red: Estructura y Funcionamiento**

### **Conceptos de Diseño**

- **Topología Física:** Es el mapa real de los cables y equipos. Sirve para mantenimiento y saber dónde está cada cable.
- **Topología Lógica:** Es el esquema de cómo viajan los datos (nombres de PC, direcciones IP).
- **Modos Inalámbricos:**
    - **Infraestructura:** Usa un Punto de Acceso (Router/AP) como centro.
    - **Ad-hoc:** Los equipos se conectan directamente entre ellos sin router.

---

### **Principales Topologías Físicas**

### **1. Topología en Bus**

- **Definición:** Un único cable central (troncal) con terminadores en los extremos donde se cuelgan todos los PCs.
- **Detalle Crítico:** Si el cable central se rompe en cualquier punto, **toda la red muere**. Era común en redes antiguas con cable coaxial.
- **Forma:**`[Term] —— PC —— PC —— PC —— [Term]`

### **2. Topología en Anillo**

- **Definición:** Cada PC se conecta al siguiente formando un círculo cerrado.
- **Detalle Crítico:** La comunicación usa un "testigo" (token). Existen versiones de **Doble Anillo** (como FDDI) que permiten que la red siga funcionando si un cable se rompe.
- **Forma:**

```
      PC —— PC
     /        \
    PC ——  ——  PC
```

### **3. Topología en Estrella**

- **Definición:** Todos los PCs se conectan a un **nodo central** (Switch, Hub o Router).
- **Detalle Crítico:** Si falla un cable de un PC, solo ese PC pierde conexión. Pero si falla el nodo central, cae toda la red. Es la más usada hoy.
- **Forma:**

```
       PC   PC
        \\  /
    PC — Nodo — PC
        /  \\
       PC   PC
```

### **4. Estrella Extendida o Árbol**

- **Definición:** Es una red jerárquica. Un nodo principal (normalmente un Router) se conecta a otros nodos centrales (Switches) que a su vez conectan a sus propios grupos de PCs.
- **Detalle Crítico:** El nodo de arriba marca el inicio de la estructura y suele dar la salida a Internet.
- **Forma:**

```
          [Router]
          /      \\
      [Switch]  [Switch]
       /    \\    /    \\
      PC    PC   PC     PC
```

### **5. Otras variantes (Malla y Totalmente Conexa)**

- **Malla:** Los equipos tienen múltiples caminos entre ellos. Si falla una ruta, hay otra (redundancia).
- **Totalmente Conexa:** Cada equipo está conectado físicamente con todos los demás equipos de la red de forma individual. Es la más cara pero la más segura.

# **3. Arquitectura de Red: Modelos OSI y TCP/IP**

### **Conceptos Iniciales**

- **Arquitectura de red:** Es el "plano" de la red. Define qué hardware (cables, routers) y qué software se usa para que todo funcione.
- **Protocolo:** Es el conjunto de reglas que permiten que dos equipos se entiendan. Si uno habla "español" y otro "inglés", el protocolo es el "traductor" que pone las normas (velocidad, idioma, formato).

---

### **El Modelo OSI (Referencia teórica)**

Es un modelo de **7 capas** creado para estandarizar la comunicación. Cada capa tiene una misión distinta.

| Capa | Nombre | Definición sencilla | Ejemplo único |
| --- | --- | --- | --- |
| **7** | **Aplicación** | Interfaz que usa el usuario. | Abrir el navegador Chrome. |
| **6** | **Presentación** | Traduce el formato de los datos (compresión/cifrado). | Ver una imagen en formato `.jpg`. |
| **5** | **Sesión** | Mantiene la conexión abierta entre los dos equipos. | Mantener abierta una llamada de voz. |
| **4** | **Transporte** | Asegura que los datos lleguen sin errores y en orden. | Dividir un archivo grande en trozos pequeños. |
| **3** | **Red** | Elige la mejor ruta para los datos (usa la IP). | Un router decidiendo por dónde enviar un paquete. |
| **2** | **Enlace** | Controla el acceso físico y los errores (usa la MAC). | La tarjeta de red enviando datos al router. |
| **1** | **Físico** | Transmisión de bits por el cable o el aire. | Los impulsos eléctricos en un cable de cobre. |

---

### **El Modelo TCP/IP (Referencia real)**

Es el modelo que realmente usa Internet. Es más sencillo y agrupa las capas de OSI en solo **4 niveles**.

1. **Nivel de Acceso a la Red:** Equivale a las capas 1 y 2 de OSI. Se encarga del hardware y las direcciones físicas (MAC).
2. **Nivel de Internet:** Equivale a la capa 3 de OSI. Se encarga de los paquetes y las direcciones lógicas (IP).
3. **Nivel de Transporte:** Equivale a la capa 4 de OSI. Usa protocolos como **TCP** (fiable, para archivos) o **UDP** (rápido, para vídeo en vivo).
4. **Nivel de Aplicación:** Equivale a las capas 5, 6 y 7 de OSI. Aquí trabajan los programas que usamos en Internet.

---

### **Tabla de Protocolos (Resolución del ejercicio)**

Esta tabla resume en qué capa del modelo **TCP/IP** trabaja cada protocolo y para qué sirve:

| Protocolo | Capa TCP/IP | Descripción sencilla |
| --- | --- | --- |
| **HTTP** | Aplicación | Protocolo para cargar páginas web en el navegador. |
| **IP** | Internet | Asigna direcciones lógicas y permite el envío de paquetes. |
| **TCP** | Transporte | Envío de datos **fiable** (confirma que han llegado). |
| **UDP** | Transporte | Envío de datos **rápido** (no confirma, ideal para streaming). |
| **ICMP** | Internet | Se usa para diagnosticar errores de red (ej: comando *Ping*). |
| **DNS** | Aplicación | Traduce nombres ([google.com](http://google.com/)) a números IP. |
| **MAC** | Acceso a Red | Dirección física única grabada en la tarjeta de red. |
| **FTP** | Aplicación | Protocolo específico para transferir archivos entre equipos. |

# **Elementos de Red y Medios de Transmisión**

### **1. Clasificación de Medios de Transmisión**

Los datos viajan a través de ondas electromagnéticas. Se dividen en dos tipos:

- **Medios Guiados:** Cables físicos (cobre o fibra).
- **Medios No Guiados:** Inalámbricos (aire o vacío).

### **Tabla Comparativa de Cables (Medios Guiados)**

| Tipo de Cable | Velocidad / Uso | Detalle Clave |
| --- | --- | --- |
| **Coaxial** | Redes de TV / Internet cable | Obsoleto para redes locales (LAN). |
| **Par Trenzado** | El estándar en LAN (Ethernet) | Usa conector **RJ-45**. Máximo **100 metros**. |
| **Fibra Óptica** | Muy alta velocidad / Larga distancia | Inmune a interferencias. Usa pulsos de luz. |

---

### **2. Categorías del Cable de Par Trenzado**

Es vital conocer las categorías para saber la velocidad de la red:

- **Categoría 5:** Hasta 100 Mbps (Fast-Ethernet).
- **Categoría 5e / 6:** Hasta 1 Gbps (Gigabit-Ethernet). **El más común hoy.**
- **Categoría 6a / 7:** Hasta 10 Gbps (10 Gigabit-Ethernet).

**Tipos de conexión RJ-45:**

- **Cable Directo:** Se usa para conectar dispositivos de **distinto nivel** (ej: PC a Switch). Se usa el estándar 568B en ambos lados.
- **Cable Cruzado:** Se usa para conectar dispositivos del **mismo nivel** (ej: PC a PC). *Nota: Los equipos modernos ya lo hacen automáticamente (Auto-MDIX).*

---

### **3. Tipos de Fibra Óptica**

| Tipo | Distancia | Funcionamiento |
| --- | --- | --- |
| **Monomodo** | Larga distancia | Un solo rayo de luz (láser). Muy rápida. |
| **Multimodo** | Corta distancia (edificios) | Varios rayos de luz (LED). Más barata. |

---

### **4. Elementos de Interconexión (Según Modelo OSI)**

Esta es la parte más técnica e importante de los apuntes:

| Nivel OSI | Dispositivo | Función Principal |
| --- | --- | --- |
| **Nivel 1 (Físico)** | **Hub (Concentrador)** | Repite la señal a todos por igual (poco eficiente). |
|  | **Repetidor** | Amplifica la señal para llegar más lejos. |
| **Nivel 2 (Enlace)** | **Switch (Conmutador)** | Envía datos solo al equipo que toca usando la **MAC**. |
|  | **Punto de Acceso (AP)** | Conecta dispositivos inalámbricos a la red cableada. |
| **Nivel 3 (Red)** | **Router (Enrutador)** | Conecta redes distintas (ej: tu casa con Internet) usando la **IP**. |
| **Niveles Superiores** | **Gateway (Pasarela)** | Conecta redes con tecnologías totalmente distintas. |

---

### **5. Conceptos Clave de Hardware**

- **Tarjeta de Red (NIC):** Dispositivo físico que conecta el PC a la red. Cada una tiene una **Dirección MAC** única de 48 bits (ej: `00:1A:2B:3C:4D:5E`).
- **Rack:** Armario donde se organiza todo el equipo de red.
- **Patch Panel:** Panel donde terminan los cables horizontales para organizarlos antes de ir al switch.
- **Servidor:** Ordenador con software especial para dar servicios a otros (clientes).

---

### **6. Cableado Estructurado (Instalación)**

Para que una red en un edificio sea profesional, se divide en subsistemas:

1. **Cableado Horizontal:** Cables que van desde el armario de planta hasta los PCs (Máximo 100m).
2. **Backbone (Vertical):** Cables que unen los armarios de diferentes plantas.
3. **Cuarto de entrada:** Por donde entran los cables del operador externo.

# **Redes en Windows: Grupos de Trabajo vs. Dominios**

### **Definiciones y Funcionamiento**

### **1. Grupo de Trabajo (Workgroup)**

Es el modelo de red por defecto en Windows. Se basa en una red **punto a punto (P2P)** donde no hay un jefe central.

- **Administración Local:** Cada ordenador es independiente. Si quieres que un usuario entre en 10 ordenadores, tienes que crear su cuenta manualmente en los 10.
- **Seguridad:** Cada usuario controla su propio equipo y qué carpetas comparte.
- **Uso ideal:** Hogares o empresas muy pequeñas (menos de 10 equipos).
- **Ejemplo único:** Una casa donde hay 3 portátiles; para que el padre vea las fotos del hijo, el hijo tiene que darle permiso y crearle un usuario en su propio portátil.

### **2. Dominio (Domain)**

Es el modelo profesional para empresas. Se basa en una red **Cliente-Servidor**.

- **Administración Centralizada:** Hay un servidor principal llamado **Controlador de Dominio (DC)** con Windows Server.
- **Cuenta Única:** Las cuentas de usuario se crean una sola vez en el servidor. Con esa cuenta, el trabajador puede sentarse en **cualquier equipo** de la empresa y entrar con su clave.
- **Seguridad:** El administrador del servidor controla los permisos de todos los equipos a la vez.
- **Uso ideal:** Empresas medianas y grandes con muchas sedes.
- **Ejemplo único:** Un banco con 50 oficinas; un empleado puede viajar a otra ciudad, sentarse en cualquier mesa y entrar a su escritorio personal solo con poner su nombre y contraseña de siempre.

---

### **Tabla Comparativa: ¿En qué se diferencian?**

| Característica | Grupo de Trabajo (Workgroup) | Dominio (Domain) |
| --- | --- | --- |
| **Administración** | Descentralizada (equipo por equipo). | Centralizada (desde un servidor). |
| **Cuentas de usuario** | Locales (una en cada PC). | Globales (una para toda la red). |
| **Configuración** | Sencilla, no requiere servidor. | Compleja, requiere Windows Server. |
| **Número de equipos** | Recomendado hasta 10-15. | Ilimitado (miles de equipos). |
| **Ubicación física** | Deben estar en la misma red local. | Pueden estar en diferentes ciudades. |
| **Seguridad** | Baja (cada usuario decide). | Alta (decide el administrador central). |

---

# **Práctica: Redes en Dominio y GPOs**

### **FASE 1: Configuración Inicial del Servidor**

Antes de que un servidor sea "jefe" (Controlador de Dominio), debe estar bien identificado.

1. **Red Interna en VirtualBox:**
    - *Qué:* Cambiar el adaptador a "Red interna".
    - *Por qué:* Para que las dos máquinas virtuales (Servidor y Cliente) se vean entre ellas en un entorno aislado, sin interferir con la conexión real de tu casa o centro y sin recibir IPs del router real.
2. **IP Estática (192.168.1.1):**
    - *Qué:* Quitar el modo automático y poner la IP a mano.
    - *Por qué:* Un "jefe" (servidor) nunca puede cambiar de dirección. Si el cliente busca al servidor y este ha cambiado de IP, la red dejará de funcionar.
3. **Cambiar nombre a DC01:**
    - *Qué:* Poner un nombre descriptivo al servidor.
    - *Por qué:* En redes grandes, identificar los equipos por nombres claros (Domain Controller 01) facilita mucho el mantenimiento.

---

### **FASE 2: Promoción a Controlador de Dominio**

Aquí es donde instalamos el "cerebro" de la red.

1. **Instalar el Rol AD DS (Active Directory Domain Services):**
    - *Qué:* Añadir la función de Servicios de Dominio.
    - *Por qué:* Es el software que permite gestionar usuarios, contraseñas y permisos de toda la red desde un solo lugar.
2. **Promover y crear Bosque (`dam.local`):**
    - *Qué:* Convertir el servidor en Controlador de Dominio y darle un nombre a la red.
    - *Por qué:* Crear un "bosque" es crear la estructura de mando. El nombre `.local` indica que es una red privada interna.

---

### **FASE 3: Unión del Cliente al Dominio**

Ahora preparamos al "empleado" (Windows 10) para que obedezca al servidor.

1. **Configurar IP y DNS (Paso Crítico):**
    - *Qué:* Poner IP del mismo rango (ej. 192.168.1.10) y **poner la IP del servidor en el apartado DNS**.
    - *Por qué:* **Este es el paso más importante.** Si el cliente no tiene la IP del servidor en su DNS, no sabrá dónde está el dominio `dam.local` y no podrá unirse nunca.
2. **Prueba de Ping:**
    - *Qué:* Comprobar conexión por IP y por nombre.
    - *Por qué:* Verificamos que los cables virtuales funcionan y que el DNS está traduciendo bien el nombre `dam.local`.
3. **Unir al dominio:**
    - *Qué:* Cambiar de "Grupo de trabajo" a "Dominio" introduciendo la clave de Administrador del servidor.
    - *Por qué:* En este momento el PC entrega las llaves de su seguridad al servidor. A partir de ahora, cualquier usuario creado en el servidor podrá entrar en este PC.

---

### **FASE 4: Directivas de Seguridad (GPOs)**

El objetivo de esta fase es demostrar quién manda en la red.

1. **Restricción Local (El "truco"):**
    - *Qué:* En el Windows 10, usamos `gpedit.msc` para prohibir el acceso al Panel de Control.
    - *Por qué:* Para simular que el PC está bloqueado localmente.
2. **Crear Unidad Organizativa (MI_UO) y Mover Usuario:**
    - *Qué:* En el servidor, crear una "carpeta" especial y meter dentro al usuario.
    - *Por qué:* Las directivas de grupo (GPOs) se aplican a Unidades Organizativas. Si no mueves al usuario ahí, la orden que des desde el servidor no le llegará.
3. **Crear y Editar GPO (Deshabilitar restricción):**
    - *Qué:* Crear una orden en el servidor que diga: "Se permite el acceso al Panel de Control".
    - *Por qué:* **Jerarquía.** Las órdenes que vienen del Servidor (Dominio) tienen más peso que las que están configuradas en el propio PC (Local).
4. **Comprobación final:**
    - *Qué:* Entrar en el Windows 10 con el usuario del dominio.
    - *Por qué:* Verás que, aunque el PC tenía prohibido el Panel de Control localmente, al entrar como usuario del dominio, la orden del servidor "gana" y te permite entrar. **Eso es la administración centralizada.**

# **Servicios en Red y Monitorización**

### **1. Arquitectura Cliente-Servidor**

Es el modelo de comunicación básico en Internet. Se basa en el intercambio de roles:

- **Cliente:** El que pide algo (hace una petición).
- **Servidor:** El que tiene la información y responde (da el servicio).
- **Ejemplo único:** Cuando usas la app de **YouTube** (cliente), esta le pide un vídeo a los ordenadores de **Google** (servidor) para que te lo envíen.

---

### **2. Puertos Virtuales**

Son "puertas" lógicas en tu ordenador que permiten separar los distintos tipos de datos que entran y salen. Hay **65.536 puertos** en total.

### **Clasificación de Puertos**

| Rango | Nombre | Uso |
| --- | --- | --- |
| **0 - 1023** | **Conocidos** | Reservados para servicios básicos de Internet (servidores). |
| **1024 - 49151** | **Registrados** | Usados por aplicaciones específicas (ej: juegos, bases de datos). |
| **49152 - 65535** | **Dinámicos** | Usados temporalmente por los clientes para sus conexiones. |

### **Tabla de Puertos más importantes (Imprescindibles)**

| Puerto | Protocolo / Servicio | Uso práctico |
| --- | --- | --- |
| **20 / 21** | **FTP** | Transferencia de archivos. |
| **22** | **SSH** | Acceso remoto seguro a otro PC. |
| **23** | **Telnet** | Acceso remoto (no seguro). |
| **25** | **SMTP** | Envío de correos electrónicos. |
| **53** | **DNS** | Traducir nombres ([web.com](http://web.com/)) a IPs. |
| **80** | **HTTP** | Navegación web normal (sin candado). |
| **443** | **HTTPS** | Navegación web segura (con candado). |
| **110** | **POP3** | Recibir correos (se descargan del servidor). |
| **143** | **IMAP** | Recibir correos (se ven online, sin descargar). |
- **Ejemplo único:** Imagina un edificio (tu IP). Los **puertos** son las oficinas de dentro: si quieres ver una web vas a la oficina 80, pero si quieres enviar un mail vas a la oficina 25.

---

### **3. Monitorización de Red**

Consiste en vigilar la red para detectar fallos, lentitud o ataques (como intrusos o virus).

### **Herramientas Principales**

1. **Wireshark (Analizador):** Es como un microscopio o una radiografía. Te permite ver cada paquete de datos que pasa por el cable.
    - *Ejemplo:* Ver si tu contraseña se está enviando por la red de forma visible o cifrada.
2. **Nmap (Escáner):** Se usa para rastrear qué "puertas" (puertos) tiene abiertas un equipo.
    - *Ejemplo:* Comprobar si un servidor tiene el puerto de juegos abierto sin permiso.
3. **Nagios (Alarma):** Sistema de vigilancia que avisa al administrador si algo se rompe.
    - *Ejemplo:* Que el administrador reciba un correo si el servidor de la empresa se apaga de repente.

---

### **¿Qué información nos da la monitorización?**

- Quién está conectado (direcciones IP).
- Si la red está saturada (ancho de banda).
- Qué programas se están usando (tipo de tráfico).
- Qué puertos están vulnerables (abiertos).

# **2. Servicios de Infraestructura de Red**

Son los servicios que "hacen que la red funcione sola" sin que tengamos que configurar cada PC a mano.

---

### **1. Servicio DHCP (Protocolo de Configuración Dinámica)**

Es el servicio encargado de "repartir" la configuración de red automáticamente a todos los dispositivos que se conectan.

- **¿Para qué sirve?** Para ahorrar tiempo y evitar errores. En lugar de poner la IP a mano en 500 ordenadores, el servidor lo hace por ti.
- **¿Qué datos entrega?** IP, Máscara de red, Puerta de enlace y DNS.

### **Métodos de Asignación de IP**

| Método | Definición sencilla | Ejemplo Único |
| --- | --- | --- |
| **Asignación Dinámica** | El servidor da una IP libre de un rango. Si el equipo se apaga, la IP queda libre para otro. | Como un hotel: te dan una habitación libre al llegar, y cuando te vas, la usa otro. |
| **Reserva por MAC** | El servidor reconoce la tarjeta de red (MAC) y le da **siempre la misma IP**. | Como una plaza de parking reservada: siempre que llegues con tu coche, aparcas en el mismo sitio. |

---

### **2. Servicio DNS (Sistema de Nombres de Dominio)**

Es el "listín telefónico" o "diccionario" de Internet. Traduce nombres fáciles de recordar a direcciones IP.

- **¿Para qué sirve?** Los humanos recordamos nombres (`google.es`), pero los ordenadores solo entienden números (`142.250.200.67`). El DNS hace el puente.
- **FQDN (Nombre de Dominio Totalmente Calificado):** Es el nombre completo de un equipo en la red (ej: `pc01.informatica.dam.local`).
- **Ejemplo único:** Es como tu agenda de contactos. No te sabes el número de tu madre, solo buscas "Mamá" y el móvil se encarga de marcar los números por ti.

---

### **3. Encaminamiento (Routing)**

Es la capacidad de un equipo (normalmente un servidor o router) para decidir por dónde deben viajar los datos para llegar a otra red distinta.

- **Puerta de Enlace (Gateway):** Es la IP de la "puerta" de salida de nuestra red local hacia el mundo exterior (Internet).
- **Ejemplo único:** Es como un GPS. Tú le dices dónde quieres ir y él decide por qué calles (redes) tienes que pasar para llegar a tu destino.

---

### **Resumen de Funciones (Tabla Final)**

| Servicio | Pregunta a la que responde | Su misión principal es... |
| --- | --- | --- |
| **DHCP** | "¿Quién soy en la red?" | Darte una IP y configuración automática. |
| **DNS** | "¿Dónde está esta web?" | Traducir nombres a números IP. |
| **Routing** | "¿Por dónde salgo a Internet?" | Conectar redes y dirigir el tráfico. |

# **3. Servicio FTP (Protocolo de Transferencia de Ficheros)**

### **¿Qué es?**

Es el sistema estándar para enviar y recibir archivos entre un ordenador (cliente) y un servidor a través de una red.

- **Diferencia con Samba/NFS:** FTP se usa sobre todo para Internet, mientras que Samba o NFS son para compartir carpetas en una red local (LAN).

---

### **Funcionamiento (La Doble Conexión)**

FTP es curioso porque no usa una sola conexión, sino **dos** a la vez:

| Conexión | Puerto | Misión | Ejemplo único |
| --- | --- | --- | --- |
| **Control** | **21** | Enviar órdenes (ej: "quiero bajar este archivo", "borrar aquello"). | Es como el camarero que toma nota de tu pedido. |
| **Datos** | **20** | Enviar el archivo real de un sitio a otro. | Es como el cocinero que saca el plato de comida a la mesa. |

---

### **Tipos de Acceso (Autenticación)**

| Tipo | ¿Cómo se entra? | Permisos | Ejemplo único |
| --- | --- | --- | --- |
| **Anónimo** | Sin usuario ni clave (o usuario "anonymous"). | Solo lectura. Entras a una carpeta pública (normalmente llamada `pub`). | Entrar en una web oficial para bajar los controladores (drivers) de una impresora. |
| **Autorizado** | Con usuario y contraseña personal. | Lectura y Escritura. Tienes tu propia carpeta privada. | Un informático subiendo los archivos nuevos para actualizar la página web de su empresa. |

---

### **Software: Servidores y Clientes**

- **Servidores (El que tiene los archivos):**
    - **Windows:** IIS (Internet Information Server).
    - **Linux/Multiplataforma:** FileZilla Server, vsftpd.
- **Clientes (El que baja/sube los archivos):**
    - **Programas visuales:** FileZilla (el más famoso), CuteFTP.
    - **Terminal:** Se puede usar el comando `ftp` seguido del nombre del servidor en la consola de comandos.

### **Nota sobre Navegadores**

Antiguamente podías entrar escribiendo `ftp://servidor.com` en Chrome o Firefox, pero hoy en día casi ningún navegador lo permite por seguridad. Ahora **es obligatorio usar un programa Cliente FTP**.

---

### **Resumen de Comandos Básicos (Terminal)**

Si entras por consola, estos son los movimientos típicos:

- `ls`: Ver qué archivos hay en el servidor.
- `get`: Bajarte un archivo del servidor a tu PC.
- `put`: Subir un archivo de tu PC al servidor.
- `bye` o `quit`: Cerrar la conexión.

# **4. Servicio Web (HTTP / HTTPS)**

### **¿Qué es?**

Es el servicio encargado de alojar y entregar páginas web. Se basa en el protocolo **HTTP** (Transferencia de Hipertexto), donde el navegador solicita una página y el servidor responde enviando el código (normalmente HTML).

---

### **Comparativa: HTTP vs HTTPS**

| Protocolo | Significado | Características | Ejemplo único |
| --- | --- | --- | --- |
| **HTTP** | Inseguro | Los datos viajan "desnudos". Alguien podría leer tu clave si la intercepta. | Leer un blog de noticias que no pide registros. |
| **HTTPS** | **Seguro** | Los datos viajan **cifrados**. Garantiza integridad y confidencialidad. | Entrar en la web de tu banco para ver tu saldo. |

---

### **Servidor Web vs Cliente Web**

- **Servidor Web (El "Almacén"):** Programa que guarda las webs y espera a que alguien las pida.
    - *Software más usado:* **Apache**, **Nginx** e **IIS** (de Microsoft).
    - *Ejemplo:* Un ordenador en las oficinas de Amazon que tiene guardada toda la lista de productos.
- **Cliente Web (El "Visualizador"):** Son los navegadores que usamos para ver el contenido.
    - *Software más usado:* Chrome, Firefox, Safari, Edge.
    - *Ejemplo:* Tu móvil usando Chrome para entrar en Amazon.

---

### **Tipos de Contenido Web**

| Tipo | Definición sencilla | Ejemplo único |
| --- | --- | --- |
| **Página Estática** | El servidor envía un archivo que ya existe (HTML, imagen). Siempre es igual. | Una página de "Quiénes somos" de una empresa. |
| **Página Dinámica** | El servidor "fabrica" la página en el momento usando scripts (PHP, ASP, JSP). | Tu muro de Facebook (es distinto para cada persona). |

---

### **Resumen de Funcionamiento**

1. **Petición:** El navegador (Cliente) pide una URL.
2. **Procesado:** El servidor busca el archivo o lo genera (si es dinámica).
3. **Respuesta:** El servidor envía el código HTML resultante al navegador.
4. **Visualización:** El navegador interpreta el código y muestra la web bonita al usuario.

# **5. Servicio de correo electrónico**

### **¿Qué es?**

Es un servicio **cliente-servidor** que permite el intercambio de mensajes y archivos (imágenes, audios, documentos) a través de Internet. Es el sustituto digital del correo postal tradicional.

---

### **Elementos del correo**

| Elemento | Definición sencilla | Ejemplo único |
| --- | --- | --- |
| **Buzón de usuario** | Espacio físico en el servidor donde se guardan tus mensajes. | Como una taquilla en la nube donde te dejan tus cartas. |
| **Cuenta de correo** | Tu identificador único (`usuario@servidor`). | Tu dirección postal digital. |
| **Cliente de correo** | Aplicación que usas para leer y escribir. | La app de Gmail en tu móvil o Outlook en tu PC. |
| **Servidor Saliente** | El encargado de "echar a andar" el correo hacia su destino. | El camión de reparto que sale de la oficina de correos. |
| **Servidor Entrante** | El encargado de recibir y guardar los correos que te envían. | El buzón de tu casa donde el cartero deja la correspondencia. |

---

### **Protocolos de correo (¿Cómo viajan los datos?)**

Existen tres protocolos fundamentales que debes conocer:

1. **SMTP (Simple Mail Transfer Protocol):** Se usa exclusivamente para **ENVIAR**. Es el "cartero" que lleva el mensaje de tu PC al servidor.
    - *Ejemplo:* Cuando pulsas el botón "Enviar" en un mail.
2. **POP3 (Post Office Protocol):** Se usa para **RECIBIR**. Descarga los mensajes en tu ordenador y, normalmente, los borra del servidor.
    - *Ejemplo:* Como cuando sacas un paquete del punto de recogida y te lo llevas a casa; ya no está en el punto de recogida.
3. **IMAP (Internet Message Access Protocol):** Se usa para **RECIBIR**. Los mensajes se quedan siempre en el servidor y se sincronizan con todos tus dispositivos.
    - *Ejemplo:* Como ver fotos en Instagram; puedes verlas desde cualquier móvil porque están guardadas en Internet, no en el teléfono.

---

### **Tabla Comparativa: POP3 vs IMAP**

| Característica | Protocolo POP3 | Protocolo IMAP |
| --- | --- | --- |
| **Ubicación mensajes** | Se descargan en tu dispositivo local. | Permanecen siempre en el servidor. |
| **Multidispositivo** | Difícil (si descargas en el PC, no lo ves en el móvil). | Perfecto (ves lo mismo en el PC, móvil y tablet). |
| **Conexión** | No necesitas Internet para leerlos (una vez bajados). | Necesitas Internet para ver los mensajes. |
| **Seguridad** | Si tu PC se rompe, pierdes los correos. | Si tu PC se rompe, los correos siguen en el servidor. |

---

### **Seguridad en el correo**

El correo tiene riesgos (identidad falsa, virus, spam). Para evitarlo se usan:

- **Filtros Antispam:** Bloquean publicidad no deseada.
- **Cifrado y Firmas digitales:** Aseguran que el mensaje no ha sido leído por otros y que el remitente es quien dice ser.
- **Protocolo SPF:** Ayuda a detectar si alguien está enviando correos falsos en tu nombre.

# **6. Protocolo SSH (Secure SHell)**

### **¿Qué es?**

Es el protocolo estándar para **conectar y controlar ordenadores de forma remota** de manera segura.

- **Diferencia clave:** A diferencia de Telnet o FTP, SSH **encripta todo** (contraseñas y datos). Nadie puede "leer" lo que haces aunque capturen tus datos.

---

### **La Criptografía de Clave Pública**

SSH usa un sistema de "pareja de claves" para que no tengas que escribir tu contraseña cada vez y para asegurar que eres tú.

| Archivo | Tipo de Clave | ¿Dónde se guarda? | Ejemplo Único |
| --- | --- | --- | --- |
| **id_rsa** | **Privada** | En **tu ordenador** (cliente). | Es tu DNI físico: nunca se lo das a nadie. |
| **id_rsa.pub** | **Pública** | En el **servidor** remoto. | Es una cerradura que dejas en la puerta del servidor. |

**¿Cómo funciona el "desafío"?**

1. El servidor te manda un mensaje bloqueado con tu "cerradura" (clave pública).
2. Tú lo abres con tu "DNI" (clave privada).
3. Le devuelves la respuesta al servidor.
4. Si la respuesta es correcta, el servidor sabe que eres tú y te deja entrar.

---

### **Archivos Importantes en `~/.ssh/`**

- **authorized_keys:** Lista de claves públicas de personas que tienen permiso para entrar en el servidor.
- **known_hosts:** Lista de servidores a los que te has conectado antes. Sirve para que nadie "suplante" al servidor y te robe la clave (ataque *Man-in-the-middle*).

---

### **Comandos de Terminal (Lo que usarás en clase)**

### **1. Conexión Remota**

Para entrar a controlar otro ordenador:

- `ssh usuario@dirección_ip`
- *Ejemplo:* `ssh ruben@192.168.1.50` (Controlarás el PC de Ruben desde el tuyo).

### **2. Copiar archivos (Comando `scp`)**

Es como el "copiar y pegar" pero entre ordenadores distintos por red.

| Acción | Comando | Ejemplo Único |
| --- | --- | --- |
| **Subir** (PC -> Servidor) | `scp archivo usuario@ip:/ruta/` | `scp foto.jpg ana@10.0.0.5:/home/ana/` |
| **Bajar** (Servidor -> PC) | `scp usuario@ip:/ruta/archivo local` | `scp ana@10.0.0.5:/home/ana/notas.txt .` |
| **Copiar carpeta** | Usa el parámetro **`-r`** | `scp -r mis_webs/ ruben@192.168.1.1:/var/www/` |

---

### **Configuración del Servidor (Windows)**

Si quieres que otros entren en tu PC Windows por SSH:

1. Instalar **Servidor OpenSSH** (en Configuración > Aplicaciones opcionales).
2. Asegurarte de que el servicio está iniciado (`services.msc`).
3. **Abrir el Puerto 22** en el Firewall (es el puerto oficial de SSH).

---

# **Guía de Comandos de Red (Terminal de Windows)**

### **1. ipconfig**

- **¿Qué hace?** Es el "espejo" de tu red. Te dice qué IP tienes, cuál es tu máscara y por dónde sales a Internet (Puerta de enlace).
- **Opciones clave:**
    - `/all`: Te da **toda** la info, incluyendo tu dirección física (MAC) y tus servidores DNS.
    - `/release`: Suelta tu IP actual (te quedas sin Internet un momento).
    - `/renew`: Le pide al router una IP nueva.
    - `/flushdns`: Limpia la memoria de nombres web de tu PC (útil si una web no carga).
- **Ejemplo:** `ipconfig /all` -> *(Úsalo para saber tu MAC o si el DHCP te ha dado IP).*

---

### **2. ping**

- **¿Qué hace?** Comprueba si otro equipo está "vivo" y cuánto tarda en responder (latencia).
- **Conceptos clave:**
    - **Tiempo (ms):** Cuanto más bajo, mejor. Si es >300ms, hay lag.
    - **TTL:** "Tiempo de vida". Es un contador que baja cada vez que el paquete pasa por un router. Evita que los paquetes den vueltas infinitas.
- **Ejemplo propio:** `ping 127.0.0.1` -> *(Ping a ti mismo. Si falla, tu tarjeta de red está rota).*
- **Ejemplo red:** `ping google.es` -> *(Si responde, tienes Internet).*

---

### **3. hostname**

- **¿Qué hace?** Te dice el nombre de tu ordenador.
- **Ejemplo:** `hostname` -> *(Te responderá algo como "DESKTOP-PC01").*

---

### **4. arp**

- **¿Qué hace?** Muestra la tabla de correspondencias entre IPs y direcciones físicas (MAC) de los equipos de tu red local.
- **Ejemplo:** `arp -a` -> *(Úsalo para ver quién hay conectado en tu mismo Wi-Fi).*

---

### **5. tracert (Traceroute)**

- **¿Qué hace?** Te enseña el camino completo (todos los routers) por los que pasa tu información hasta llegar al destino.
- **Ejemplo:** `tracert 8.8.8.8` -> *(Verás cada "salto" que da tu conexión hasta llegar a los servidores de Google).*

---

### **6. nslookup**

- **¿Qué hace?** Consulta al servidor DNS. Sirve para saber qué IP tiene una web o para saber si tu servidor DNS funciona bien.
- **Ejemplo:** `nslookup marca.com` -> *(Te dirá la IP real de esa web).*

---

### **7. netstat**

- **¿Qué hace?** Te dice quién está hablando con tu ordenador en este momento y por qué puertos.
- **Estados clave:**
    - **LISTENING:** Tu PC tiene una "puerta abierta" esperando a que alguien le llame (ej: un servidor).
    - **ESTABLISHED:** Ya hay una conversación activa (ej: estás bajando un archivo).
- **Ejemplo:** `netstat -a` -> *(Úsalo para ver si tienes programas extraños conectados a Internet sin tu permiso).*

---

# **Resolución de Actividades (Resumen)**

1. **Averiguar datos automáticos:** Usa `ipconfig /all`. En el entorno gráfico: Configuración de red > Estado > Propiedades.
2. **Diferencias de Ping:**
    - `127.0.0.1`: Casi 0ms (eres tú mismo).
    - `IP Router`: Muy bajo (está al lado, en tu casa).
    - `google.com`: Más alto (viaja por medio mundo).
    - *Utilidad:* El ping a `127.0.0.1` sirve para descartar fallos de hardware en tu PC.
3. **Tracert y asteriscos:** Si ves un , significa que ese router intermedio ha bloqueado la petición por seguridad o está saturado (tiempo de espera agotado). No siempre significa que no haya conexión.
4. **ESTABLISHED vs LISTENING:**
    - `LISTENING`: Una puerta abierta esperando (como un teléfono esperando que suene).
    - `ESTABLISHED`: Conexión funcionando (como una llamada de teléfono en curso).