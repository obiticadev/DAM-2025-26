# Sistemas7_8

# PARTE 1: Gestión de Cuentas, Seguridad y Clonación

## 1. Conceptos Previos: Tipos y Permisos

Antes de gestionar las cuentas, el documento define qué opciones existen:
* **Tipos de Cuentas:**
* **Cuenta Local:** Se guarda solo en el ordenador. Sirve para ese equipo en concreto.
* **Cuenta de Microsoft:** Se guarda en la nube (correo + contraseña). Permite sincronizar configuración entre dispositivos y acceder a la Microsoft Store.
* **Niveles de Acceso (Privilegios):**
* **Usuario Estándar:** Puede usar software y cambiar configuraciones simples. No puede afectar a otros usuarios.
* **Administrador:** Control total del equipo. Puede cambiar todo (a veces requiere confirmación).
* **Usuario Inicial:** Es el creado al instalar Windows; pertenece al grupo de Administradores.

## 2. Pasos para la Gestión de Cuentas

### Vía A: Desde “Configuración” (La opción moderna)

Es la interfaz principal de Windows 10/11.
1. Abre el menú de **Configuración**.
2. Selecciona la opción **Cuentas**.
3. Desde aquí puedes gestionar “Tu información”, “Correo y cuentas”, “Opciones de inicio de sesión”, etc.

### Vía B: Desde el “Panel de Control” (La opción clásica)

Interfaz tradicional de Windows.
1. Abre el **Panel de control**.
2. Busca y selecciona **Cuentas de usuario**.
3. Desde aquí puedes “Cambiar el tipo de cuenta”, “Quitar cuentas de usuario” o “Administrar otra cuenta”.

### Vía C: Ejecutando un comando directo

Para usuarios avanzados que quieren una ventana rápida de gestión.
1. Presiona la tecla **Windows + R**
2. Escribe el comando: **netplwiz**
3. Se abrirá una ventana emergente donde puedes agregar, quitar o ver propiedades de los usuarios y gestionar el inicio de sesión automático.

### Vía D: Desde “Administración de equipos”

*Nota: Esta opción suele estar disponible completamente solo en versiones Windows Pro, no en Home.*
1. Abre la herramienta **Administración de equipos**.
2. En el menú lateral izquierdo, despliega **Herramientas del sistema**.
3. Busca la carpeta **Usuarios y grupos locales**.
* **Dentro de Usuarios:** Ves la lista completa y puedes crear nuevos.
* **Dentro de Grupos:** Puedes asignar usuarios a grupos (ej. añadir a alguien al grupo Administradores).

---

## Administración de equipos.

### Paso Previo: Abrir la Herramienta de Gestión

Para realizar todas las actividades, necesitamos acceder a la consola de gestión de usuarios locales.
1. Presiona la tecla **Windows + R**.
2. Escribe el comando: **compmgmt.msc** y pulsa Enter.
* **Explicación:** Este comando abre la consola de “Administración de equipos”. Es una “caja de herramientas” que centraliza la gestión de discos, eventos y, lo que nos interesa, Usuarios y grupos locales.
3. En el panel izquierdo, despliega **Herramientas del sistema > Usuarios y grupos locales**.

### Solución ACTIVIDAD 1: Comprobación de Grupos

**Objetivo:** Verificar quién está en los grupos “Administradores” e “Invitados” y su estado.
1. En la consola, haz clic en la carpeta **Grupos**.
2. Haz doble clic en **Administradores**.
* **Lo que verás:** Aquí aparecerá el usuario “Administrador” (el integrado) y tu usuario personal con el que instalaste Windows.
3. Haz doble clic en **Invitados**.
* **Lo que verás:** Normalmente contiene al usuario “Invitado”.
4. Ahora ve a la carpeta **Usuarios** (panel izquierdo).
5. Observa los iconos de “Administrador” e “Invitado”.
* **Respuesta a la pregunta 2:** Probablemente veas una flecha negra pequeña apuntando hacia abajo en el icono de “Administrador” y en el de “Invitado”.
* **Conclusión:** NO, no están habilitadas. Por defecto, Windows deshabilita la cuenta “Administrador” (por seguridad) y la de “Invitado”.

### Solución ACTIVIDAD 2: Creación de Usuarios y Grupos

**Objetivo 1: Habilitar la cuenta Administrador**
1. En la carpeta **Usuarios**, haz clic derecho sobre **Administrador > Propiedades**.
2. Desmarca la casilla que dice **“La cuenta está deshabilitada”**.
3. Acepta. (Ahora el “super-usuario” está activo).

**Objetivo 2: Crear los Grupos (A, B y C)**
1. Ve a la carpeta **Grupos**.
2. Clic derecho en un espacio blanco > **Grupo nuevo…**
3. Nombre de grupo: **GrupoA**. Clic en **Crear**.
4. Repite para **GrupoB** y **GrupoC**.

**Objetivo 3: Crear Usuarios y asignarlos (Según la tabla)**
Para cada usuario (Antonio, Andrés, Benito, etc.), haremos lo siguiente:
1. Ve a la carpeta **Usuarios**.
2. Clic derecho > **Usuario nuevo…**
3. Escribe el nombre (ej: Antonio). Desmarca “El usuario debe cambiar la contraseña…” (para facilitar el ejercicio) y pulsa **Crear**.
4. Una vez creados los 6 usuarios, asígnalos a los grupos. La forma más rápida es ir a **Grupos**:
* Doble clic en **GrupoA > botón Agregar**.
* Escribe “Antonio; Andrés” (separados por punto y coma) > Comprobar nombres > Aceptar.
* Doble clic en **GrupoB > Agregar > “Benito; Beatriz”**.
* Doble clic en **GrupoC > Agregar > “Carlos; Carmen”**.

### Solución ACTIVIDAD 3: Permisos de Carpetas (NTFS)

*Nota: Crea tres carpetas en tu disco C: llamadas CarpetaA, CarpetaB y CarpetaC para probar esto.*
Aquí aplicaremos la teoría (Lectura, Modificar, Control Total).

**Importante:** Para cumplir la condición “El resto de usuarios no podrán entrar”, debemos **romper la herencia**. Por defecto, las carpetas heredan permisos del disco C: que permiten a “Usuarios” leer todo.

### 1. Configurar CarpetaA

**Condición:** Solo GrupoA modifica. Admins control total. Nadie más entra.
1. Clic derecho en CarpetaA > **Propiedades > pestaña Seguridad**.
2. Botón **Opciones avanzadas**.
3. Botón **Deshabilitar herencia** > Selecciona “Convertir los permisos heredados en permisos explícitos”.
4. **Limpieza:** Selecciona el grupo “Usuarios” (Users) y dale a **Quitar**. (Esto impide que entre “el resto del sistema”).
5. **Agregar GrupoA:** Botón Agregar > Seleccionar entidad de seguridad > Escribe “GrupoA”.
* En permisos básicos, marca **Modificar** (esto marca automáticamente Lectura y Escritura). Acepta.
6. Asegúrate de que “Administradores” sigue en la lista con “Control total”.

### 2. Configurar CarpetaB

**Condición:** Solo GrupoB modifica. Admins control total. Nadie más entra.
1. Repite los pasos de la CarpetaA (Deshabilitar herencia y quitar “Usuarios”).
2. Agrega **GrupoB** y marca la casilla **Modificar**.
3. Mantén a “Administradores” con Control Total.

### 3. Configurar CarpetaC

**Condición:** Todos ven (leen). GrupoC modifica. Admins control total.
1. Clic derecho CarpetaC > **Propiedades > Seguridad**.
2. Aquí **NO** quitamos al grupo “Usuarios”, porque el ejercicio dice “Todos los usuarios del sistema podrán entrar y ver”.
3. Verifica que el grupo **Usuarios** tiene marcado **Lectura y ejecución, Mostrar contenido y Lectura** (columna Permitir).
4. Botón **Editar > Agregar** > Escribe “GrupoC”.
5. Selecciona “GrupoC” en la lista y marca la casilla **Modificar** en la columna Permitir.
6. Acepta.

### Solución ACTIVIDAD 4: Denegar Acceso (Permiso Restrictivo)

**Objetivo:** Impedir que Andrés entre a la CarpetaC (aunque pertenezca a un grupo que sí tiene permiso).
1. Clic derecho en CarpetaC > **Propiedades > pestaña Seguridad**.
2. Botón **Editar**.
3. Botón **Agregar** > Escribe “Andrés” > Aceptar.
4. Selecciona al usuario **Andrés** en la lista de arriba.
5. En la lista de abajo (Permisos), busca la columna **DENEGAR**.
6. Marca la casilla **Control total** (o al menos “Lectura” y “Listar”) en la columna **Denegar**.
7. Acepta. Te saldrá una advertencia de Windows diciendo que “Denegar tiene preferencia sobre Permitir”. Dile que **Sí**.

**Explicación técnica:**
Aunque “Andrés” esté en el “GrupoA” (que podría tener acceso) o en el grupo “Usuarios” (que tiene lectura), en el sistema de archivos NTFS, un permiso **Denegar explícito** siempre gana a cualquier permiso de “Permitir”. Andrés será rebotado al intentar abrir la carpeta.

---

## Directivas de Grupo y Seguridad Local.

### 1. ¿Qué son las Directivas de Grupo?

Son el mecanismo de Windows que permite a los administradores **configurar, controlar y restringir** el comportamiento de usuarios y equipos de forma centralizada, evitando tener que configurar cada PC uno por uno.

- **Tipos:**
    - **Locales:** Afectan solo al equipo donde se configuran (útil para PC sueltos).
    - **De Dominio:** Se gestionan desde un servidor (Active Directory) y afectan a toda una red de usuarios/equipos.
- **Estados de una directiva:**
    - **No configurada:** Usa el valor por defecto de Windows.
    - **Habilitada:** Activa la regla.
    - **Deshabilitada:** Impide que la regla funcione.

### 2. Herramientas y Comandos Clave

Para gestionar estas directivas, ejecutar con Windows + R :
* **gpedit.msc:** Abre el editor de **Directivas de Grupo Local** (General).
* **SecPol.msc:** Abre directamente las **Directivas de Seguridad Local** (Contraseñas y bloqueos).
* **gpupdate:** Fuerza al sistema a **actualizar** las directivas inmediatamente sin reiniciar.

### 3. Ejemplo Práctico: Restringir el sistema (CMD)

El documento pone como ejemplo cómo prohibir el uso de la consola de comandos.
1. Ejecuta **gpedit.msc**.
2. Navega a: Configuración de usuario > Plantillas Administrativas > Sistema.
3. Busca la directiva: **“Impedir el acceso al símbolo del sistema”**.
4. Haz doble clic, selecciona **Habilitada** y Aceptar.

### 4. Directivas de Seguridad (Cuentas y Contraseñas)

Estas configuraciones son críticas para proteger el acceso. Se encuentran en SecPol.msc (o dentro de gpedit en Configuración del equipo > Configuración de Windows > Configuración de seguridad).

### A. Directivas de Contraseña

Definen cómo deben ser las claves de los usuarios:
* **Exigir historial de contraseñas:** Impide repetir las últimas “X” contraseñas usadas.
* **Requerimientos de complejidad:** Obliga a usar mayúsculas, minúsculas, números y símbolos.
* **Longitud mínima:** Cantidad mínima de caracteres (0 significa que se permite en blanco).
* **Vigencia máxima:** Días antes de que la contraseña caduque y el sistema obligue a cambiarla.
* **Vigencia mínima:** Tiempo que debe pasar antes de que el usuario pueda volver a cambiar su contraseña (evita que cambien la contraseña 10 veces seguidas para volver a usar la antigua y saltarse el historial).

### B. Directivas de Bloqueo de Cuenta

Protegen contra ataques de fuerza bruta (intentar adivinar la contraseña muchas veces):
* **Umbral de bloqueo:** Número de intentos fallidos permitidos antes de bloquear la cuenta (ej. 3 intentos).
* **Duración del bloqueo:** Cuántos minutos la cuenta permanecerá inaccesible tras bloquearse. (Si es “0”, solo un administrador puede desbloquearla).
* **Restablecer bloqueo después de:** Cuánto tiempo debe pasar sin fallos para que el contador de intentos vuelva a cero.

---

## PARTE 1: Resumen Teórico - EFS (Encrypting File System)

El documento explica cómo usar **EFS**, una herramienta nativa de Windows (versiones Pro/Enterprise) para proteger archivos mediante cifrado.

### 1. ¿Qué es EFS?

Es un sistema que cifra archivos y carpetas para que **solo el usuario que los creó** (o quien tenga el certificado digital) pueda leerlos.
* **Diferencia con NTFS:** Los permisos NTFS (Lectura/Escritura) dicen “quién puede entrar”. EFS dice “quién puede entender los datos”. Aunque un intruso tenga permiso para copiar el archivo, si no tiene el certificado, verá basura o recibirá “Acceso denegado”.

### 2. Características Clave

- **Transparente:** El dueño no pone contraseña cada vez; Windows usa su certificado invisiblemente.
- **Específico:** Se pueden cifrar archivos sueltos o carpetas enteras.
- **Requisito:** Solo funciona en discos formateados como **NTFS**.
- **Seguridad:** Si pierdes el certificado (y no tienes copia), pierdes los datos para siempre.

### 3. Cómo se activa

1. Clic derecho en el archivo/carpeta > **Propiedades**.
2. Botón **Avanzadas** (en la pestaña General).
3. Marcar la casilla: **“Cifrar contenido para proteger datos”**.

---

## PARTE 2: Solución de la ACTIVIDAD (Página 4)

### Paso 1: Preparación del entorno

1. Entra con tu usuario administrador.
2. En el disco local (C: o E:), crea una carpeta llamada **Cifrada**.
3. Clic derecho en Cifrada > **Propiedades > Seguridad > Editar**.
4. Asegúrate de que el grupo “Usuarios” tenga marcada la casilla **Modificar**. (Esto es vital para probar que, aunque tengan permiso, no podrán leer).
5. Entra en la carpeta y crea un archivo de texto (Documento.txt) y escribe algo dentro.

### Paso 2: Cifrado y Certificados

1. Clic derecho en Cifrada > **Propiedades > Avanzadas**.
2. Marca **Cifrar contenido para proteger datos** > Aceptar > Aceptar.
3. Windows preguntará: “¿Desea aplicar el cambio solo a esta carpeta o a todas las subcarpetas?”. Elige **“Aplicar cambios a esta carpeta y a todas las subcarpetas y archivos”**.
4. **Exportación:** Verás una notificación en la barra de tareas pidiendo hacer copia de seguridad. Haz clic, sigue el asistente, ponle una contraseña y guarda el archivo **.pfx** (el certificado) en un pendrive o en una carpeta fuera de la encriptada.
5. **Verificación:** Abre el comando **certmgr.msc**. Ve a **Personal > Certificados**. Ahí verás un certificado donde en “Propósitos” dice *Sistema de archivos de cifrado*.

### Paso 3: Prueba de acceso (El “Hackeo”)

1. Cierra sesión e inicia con **otro usuario** distinto.
2. Ve a la carpeta Cifrada.
3. Intenta abrir Documento.txt.
    - **Resultado esperado:** Recibirás un mensaje de “Acceso Denied” o “No tiene permisos”.
    - **Respuesta a la pregunta:** ¿Es posible acceder a la carpeta? SÍ, puedes entrar en la carpeta (porque NTFS lo permite). ¿Al contenido del fichero? NO, está cifrado.
    - **Respuesta a la pregunta:** ¿Puedes crear nuevos documentos? SÍ. Como tienes permiso NTFS de “Modificar”, puedes crear un archivo nuevo (ej: Hola.txt). Ojo: Dependiendo de la configuración, ese nuevo archivo podría cifrarse con TU certificado (y el dueño original no podría leer el tuyo) o quedarse sin cifrar.

### Paso 4: Simulación de Desastre (Borrar la llave)

1. Vuelve al usuario original (el dueño).
2. Ejecuta **certmgr.msc**.
3. Ve a **Personal > Certificados**.
4. Borra el certificado de EFS.
5. Intenta abrir tu propio Documento.txt.
    - **Resultado:** No podrás abrirlo. Aunque eres el dueño, has tirado la “llave” al río.

### Paso 5: Recuperación

1. Busca el archivo **.pfx** que exportaste en el paso 2.
2. Haz doble clic sobre él.
3. Sigue el asistente (te pedirá la contraseña que pusiste al exportar).
4. Asegúrate de marcar “Marcar esta clave como exportable” (buena práctica).
5. Finaliza. Ahora intenta abrir Documento.txt. ¡Funcionará de nuevo!

### Paso 6: Convivencia

Comprueba si puedes crear otra carpeta y que otro usuario cifre la suya.
* **Resultado:** Sí. EFS es multi-usuario. “Usuario A” cifra sus cosas con su certificado y “Usuario B” cifra las suyas con el suyo. Ninguno puede leer lo del otro, pero ambos conviven en el mismo disco duro.

---

## PARTE 3: Explicación de Comandos Nuevos

**Comando: certmgr.msc**
* **Significado:** Certificate Manager (Administrador de Certificados).
* **¿Para qué sirve?:** Es la consola que gestiona tu **identidad digital** en el equipo. Windows no guarda las claves de cifrado en archivos de texto, las guarda en un almacén seguro.
* **Uso en este ejercicio:** Aquí es donde se guarda la “llave maestra” de EFS. Si entras en la carpeta Personal > Certificados, verás quién eres para el sistema. Si borras el certificado de aquí, pierdes el acceso a todo lo que hayas cifrado (EFS, correos firmados, etc.).

---

## Cifrado de Disco Completo

### Solución de la Actividad BitLocker y Solución de la Actividad VeraCrypt.

### PARTE 1: Resumen Teórico - Herramientas de Cifrado

El documento compara dos herramientas principales para proteger particiones enteras o discos duros externos.

### 1. BitLocker (Nativo de Windows)

- **¿Qué es?:** Herramienta incluida en versiones **Pro y Enterprise** de Windows. Cifra toda la partición (incluso donde está instalado Windows).
- **Requisito de Hardware (TPM):** Por defecto, BitLocker pide un chip **TPM** (Módulo de Plataforma Segura) en la placa base. Este chip guarda las claves de cifrado físicamente.
- **BitLocker To Go:** Es la versión para cifrar pendrives (USB) y discos duros externos.
- **Recuperación:** Es vital guardar la “Clave de recuperación” (un código largo) en un lugar seguro (otro USB, la nube o imprimirlo). Si olvidas la contraseña o falla el TPM, es la única forma de entrar.

### 2. VeraCrypt (Multiplataforma)

- **Origen:** Nace cuando TrueCrypt fue abandonado misteriosamente en 2014. Es de código abierto (Open Source).
- **Ventaja principal:** Funciona en **Windows, Linux y MacOS**.
    - *Uso recomendado:* Si tienes un pendrive que usas tanto en Windows como en Linux, usa VeraCrypt. Si solo usas Windows, BitLocker es más cómodo.
- **Concepto de “Contenedor”:** VeraCrypt permite crear un archivo (ej: mis_secretos.hc) que, al abrirse con contraseña, se monta como si fuera un disco duro real (Letra Z:, por ejemplo).

---

### PARTE 2: Solución ACTIVIDAD BITLOCKER (Paso a Paso)

**Objetivo:** Cifrar la unidad C: (Sistema).
**Problema común:** Al intentarlo, Windows suele dar error diciendo: *“Este dispositivo no puede usar un Módulo de plataforma segura (TPM)”*.

**Solución al error del TPM (El “Truco” del PDF):**
Para usar BitLocker sin chip TPM, debemos autorizar el uso de contraseñas/USB mediante directivas.
1. Presiona Windows + R, escribe **gpedit.msc** y pulsa Enter.
2. Navega por la izquierda: **Configuración del equipo > Plantillas administrativas > Componentes de Windows > Cifrado de unidad BitLocker > Unidades del sistema operativo**.
3. A la derecha, busca la directiva: **“Requerir autenticación adicional al iniciar”**.
4. Doble clic sobre ella.
5. Marca **Habilitada**.
6. Asegúrate de que abajo esté marcada la casilla: **“Permitir BitLocker sin un TPM compatible”**.
7. Acepta y cierra.

**Procedimiento de Cifrado:**
1. Ve al **Panel de Control > Sistema y seguridad > Cifrado de unidad BitLocker**.
2. En la unidad C:, pulsa **Activar BitLocker**.
3. Ahora no dará error. Te pedirá cómo quieres desbloquear la unidad al encender el PC. Elige **“Escribir una contraseña”** (ya que no tenemos TPM).
4. **Clave de recuperación:** Te pedirá dónde guardarla. Elige “Guardar en un archivo” (y guárdalo en un USB distinto) o “Imprimir”.
5. Sigue el asistente (elegir “Cifrar solo el espacio usado” es más rápido).
6. Windows pedirá reiniciar para comprobar el sistema. Al reiniciar, verás una pantalla azul pidiendo la contraseña ANTES de que cargue Windows.

---

### PARTE 3: Solución ACTIVIDAD VERACRYPT (Paso a Paso)

**Objetivo:** Crear un “Contenedor seguro” (un archivo cifrado) en la partición de datos.
1. **Instalación:** Descarga e instala VeraCrypt (es gratuito).
2. Abre VeraCrypt y pulsa el botón **“Crear volumen”** (Create Volume).
3. **Tipo de volumen:** Elige **“Crear un contenedor de archivos cifrado”**. (Esto crea un fichero que funcionará como caja fuerte).
4. **Tipo:** Volumen VeraCrypt común (Standard).
5. **Ubicación:** Dale al botón “Seleccionar Archivo”. Navega a tu disco de datos y ponle un nombre, por ejemplo: **CajaFuerte.hc**.
6. **Opciones de cifrado:** Deja las opciones por defecto (AES y SHA-512 son muy seguros).
7. **Tamaño:** Decide cuánto espacio tendrá (ej. 500 MB).
8. **Contraseña:** Escribe una contraseña robusta.
9. **Formateo:** Mueve el ratón aleatoriamente dentro de la ventana (para generar entropía/aleatoriedad) hasta que la barra se llene y pulsa **Formatear**.
10. **Montar la unidad (Usarla):**
* En la pantalla principal de VeraCrypt, selecciona una letra libre (ej: Z:).
* Pulsa “Seleccionar Archivo” y busca tu fichero CajaFuerte.hc.
* Pulsa el botón **Montar** (Mount).
* Escribe la contraseña.
* **Resultado:** Ahora en “Este Equipo” verás un nuevo disco Z:. Todo lo que guardes ahí se cifrará automáticamente. Cuando termines, pulsa **Desmontar** en VeraCrypt y el disco desaparecerá, quedando todo guardado dentro del archivo.

---

## Explicación de Conceptos Nuevos

- **TPM (Trusted Platform Module):** Es un chip físico soldado en la placa base de los ordenadores modernos. Sirve para asegurar que el disco duro no ha sido manipulado y guarda las llaves de cifrado. Si alguien roba tu disco duro y lo pone en otro PC, no funcionará porque le falta el chip TPM de tu placa base original.
- **Directiva “Requerir autenticación adicional”:** Es la configuración que permite decirle a Windows: “Oye, mi PC es viejo y no tiene chip TPM, déjame usar una contraseña escrita por teclado para proteger el disco”.

---

## Imágenes del sistema y Clonación.

### 1. Conceptos Clave: Imagen vs. Clonación

El documento distingue dos técnicas fundamentales para hacer copias de seguridad (Backups) completas de un ordenador.

### A. Imágenes del Sistema (La “Foto” guardada)

- **¿Qué es?** Se trata de empaquetar todo el contenido de un disco o partición en **un solo archivo comprimido** (o varios trozos).
- **¿Para qué sirve?** Para restaurar el equipo a un estado anterior (“limpio”) si entra un virus o falla Windows.
- **Tipos:**
    - **De Partición:** Guardas solo una parte del disco (ej. solo C:). Puedes guardar el archivo resultante en otra partición del mismo disco.
    - **De Disco Completo:** Guardas TODO el disco físico. **Importante:** No puedes guardar la imagen dentro del mismo disco que estás copiando (necesitas un disco externo o red).
- **Restauración:** Cuando restauras, el proceso **sobrescribe y borra** todo lo que haya en el destino para dejarlo exactamente igual a cuando se creó la imagen.

### B. Clonación (La “Copia exacta”)

- **¿Qué es?** Es un volcado de información “bit a bit”. No se crea un archivo comprimido, sino que se copia el contenido directamente de un disco a otro.
- **¿Para qué sirve?** Para cambiar un disco duro viejo por uno nuevo (o por un SSD más rápido). Al terminar, quitas el viejo, pones el nuevo y el ordenador arranca igual.
- **Regla de oro:** El disco de destino debe ser **igual o más grande** que el de origen.

### 2. Herramientas Mencionadas

El texto destaca software para realizar estas tareas:
* **Clonezilla:**
* Es la herramienta gratuita más famosa (Open Source).
* Funciona con un **Live CD/USB**: No se instala en Windows, sino que arrancas el PC con un pendrive de Clonezilla para copiar los discos sin que el sistema operativo estorbe.
* **Herramientas Comerciales (De pago):**
* **Norton Ghost y Acronis.** Son más visuales y fáciles para usuarios novatos, pero cuestan dinero.

### 3. Análisis Técnico: El comando dd (Linux) 💻

El documento muestra un ejemplo de cómo clonar un disco usando la terminal de Linux sin necesidad de programas visuales. Este comando es muy potente y peligroso si se usa mal.

**El comando del ejemplo:**`sudo dd if=/dev/sda of=/dev/sdb bs=1M`

**Explicación paso a paso de qué hace cada parte:**
1. **sudo:** “SuperUser DO”. Da permisos de administrador. Sin esto, el sistema no te deja tocar los discos duros.
2. **dd:** Es el nombre del programa. Viene de *Data Duplicator* (aunque los informáticos bromean llamándolo *Disk Destroyer* porque si te equivocas, borras todo).
3. **if=/dev/sda:**
* **if** significan **Input File** (Archivo de Entrada / Origen).
* **/dev/sda:** En Linux, los discos duros se llaman “sd” (Storage Device). La letra “a” indica que es el **primer disco duro** conectado.
* *Traducción:* “Lee los datos del Disco 1”.
4. **of=/dev/sdb:**
* **of** significa **Output File** (Archivo de Salida / Destino).
* **/dev/sdb:** Indica el **segundo disco duro**.
* *Traducción:* “Escribe los datos en el Disco 2”.
5. **bs=1M:**
* **bs** significa **Block Size** (Tamaño del bloque).
* Le dice al ordenador: “Copia los datos en paquetes de 1 Megabyte cada vez”. Si no pones esto, copiará byte a byte y tardará una eternidad.

**Resumen de la salida del comando:**
* El texto muestra que el comando no muestra una barra de progreso visual, se queda “pensando” hasta que termina.
* Al final dice: 31 GB copiados, 2583 s. Significa que ha clonado 31 Gigas de información exacta del disco A al disco B.

---

## Guía Definitiva y Estructurada de Clonezilla

### FASE 1: Configuración Inicial (Común para todos)

Antes de elegir qué hacer, debemos configurar el idioma y el teclado.
1. **Arrancar Clonezilla (Boot):**
* **Opción:** Clonezilla Live (VGA 800x600 & To RAM)
* **¿Qué significa?:** Carga todo el programa en la memoria RAM del ordenador. Esto libera los discos duros y permite trabajar más rápido.
2. **Idioma:**
* **Opción:** es_ES.UTF-8 Spanish | Español
* **¿Qué significa?:** Traduce los menús al español.
3. **Teclado:**
* **Opción:** Don’t touch keymap (No tocar el mapa de teclado).
* **¿Qué significa?:** Mantiene la distribución estándar (EE. UU.). Como solo usaremos las flechas y la tecla Enter, no hace falta configurar la ñ o las tildes.
4. **Iniciar:**
* **Opción:** Start_Clonezilla.

### FASE 2: La Gran Decisión (¿Qué quieres hacer?)

Aquí el camino se divide en dos. El programa te preguntará qué modo usar:
* **Opción A: device-image (Imagen de disco)**
* **Significado:** Creas un **archivo de copia de seguridad** (un backup comprimido) y lo guardas en un disco externo o carpeta. Ideal para guardar el estado de un PC por si falla en el futuro.
* **Opción B: device-device (Clonación directa)**
* **Significado:** Copias el contenido de un disco duro directamente a otro disco duro conectado. Ideal cuando cambias un disco viejo por uno nuevo (SSD).

---

### CAMINO A: Crear una Imagen de Respaldo (device-image)

Sigue estos pasos si elegiste la Opción A.
1. **¿Dónde guardar la imagen?:**
* **Opción:** local_dev (Dispositivo local).
* **Significado:** Le dices que vas a guardar la copia en un disco duro USB o partición conectada al PC.
2. **Comprobación de errores:**
* **Opción:** no-fsck.
* **Significado:** Le dices que NO pierda tiempo buscando errores en el disco antes de copiar. Si tu disco funciona bien, saltar esto ahorra mucho tiempo.
3. **Selección del Repositorio:**
* El programa montará tu disco externo en **/home/partimag**. Confirma que es correcto.
4. **Tipo de copia:**
* **Opción:** savedisk (Guardar disco local como imagen).
* **Significado:** Copia TODO el disco duro (Windows, archivos, arranque…).
* *(Nota: Existe saveparts si solo quieres copiar una partición concreta, como solo los datos).*
5. **Compresión (Importante):**
* **Opción:** z9p (Compresión zstdmt).
* **Significado:** Es el método moderno. Usa todos los núcleos de tu procesador para comprimir el archivo muy rápido y que ocupe menos espacio.
6. **Finalizar:**
* El sistema creará una carpeta con archivos **.zst** (los datos comprimidos) y **.txt** (información del PC).

---

### CAMINO B: Clonar de Disco a Disco (device-device)

Sigue estos pasos si elegiste la Opción B para pasar datos a un disco nuevo.
1. **Modo de trabajo:**
* **Opción:** disk_to_local_disk.
* **Significado:** Clonar disco local a disco local.
2. **Seleccionar ORIGEN (Source):**
* **Acción:** Elige el disco que tiene tus datos actuales.
* ⚠️ **Cuidado:** ¡No te equivoques aquí! Es el disco que quieres copiar.
3. **Seleccionar DESTINO (Target):**
* **Acción:** Elige el disco vacío/nuevo.
* ⚠️ **Cuidado:** Todo lo que haya en este disco se borrará.
4. **Comprobación del sistema:**
* **Opción:** sfsck (Saltar comprobación).
* **Significado:** Igual que antes, saltamos el chequeo de errores para ir rápido.
5. **Ajuste de tamaño (Crucial para SSDs):**
Aquí tienes dos opciones clave:
* **k0 (Usar tabla de particiones del origen):** Hace una copia exacta 1:1. Si el disco nuevo es más grande, sobrará espacio sin usar al final.
* **k1 (Crear tabla proporcionalmente):** Si pasas de un disco pequeño a uno grande (ej. 500GB a 1TB), esta opción **estira** las particiones para que ocupen todo el disco nuevo automáticamente.
6. **Proceso final:**
* Verás una pantalla azul (Partclone) con una barra de progreso roja y azul.
* Al terminar, Clonezilla reinstalará el **GRUB** (cargador de arranque) para asegurar que el disco nuevo arranque Windows sin problemas.

---

### Resumen de archivos generados (Solo para Camino A)

Si hiciste una imagen, en tu carpeta tendrás:
* **clonezilla-img:** Archivo “bandera” que le dice al sistema que eso es una copia válida.
* **info-*.txt: **Texto con los detalles del hardware copiado.
*** sdb1…img.zst: ****El archivo más importante.** Contiene tus datos reales comprimidos.

# PARTE 2: Preguntas Raid

### 1. Diferencia entre un disco básico y uno dinámico

- **Respuesta:** La diferencia principal radica en la flexibilidad y la forma en que gestionan la información sobre las particiones o volúmenes.
- **Justificación:**
    - **Disco Básico:** Utiliza tablas de particiones estándar (MBR o GPT). Solo permite crear particiones simples (primarias o lógicas).
    - **Disco Dinámico:** Esto permite crear volúmenes que se extienden a través de varios discos físicos (volúmenes distribuidos, seccionados, reflejados o RAID-5), algo que un disco básico no puede hacer.

### 2. ¿Es posible asignar un tipo de particionado GPT a un disco básico?

- **Respuesta:** Sí.
- **Justificación:** GPT (GUID Partition Table) y MBR son **estilos de particionado**, mientras que “Básico” y “Dinámico” son **tipos de almacenamiento**. Un disco básico puede usar tanto MBR (antiguo, límite de 2TB) como GPT (moderno, permite más de 2TB y casi ilimitadas particiones). De hecho, hoy en día es el estándar para discos básicos en sistemas UEFI.

### 3. ¿Es posible crear un volumen simple en un disco básico? ¿Y en uno dinámico?

- **Respuesta:** Sí en ambos, aunque con distinta nomenclatura técnica.
- **Justificación:**
    - En un **disco básico**, lo que comúnmente llamamos “volumen simple” es técnicamente una **partición primaria**. Windows a menudo la etiqueta como “Volumen simple” en su asistente, y actúa como una unidad independiente.
    - En un **disco dinámico**, el volumen simple es la unidad básica de almacenamiento. Se diferencia de los otros volúmenes dinámicos porque solo utiliza espacio de un único disco físico.

### 4. ¿Es posible extender los volúmenes del punto anterior con espacio de otros discos?

- **Respuesta:** En el disco básico **no**, en el dinámico **sí**.
- **Justificación:**
    - **Disco Básico:** Las particiones están limitadas al espacio físico contiguo dentro de un mismo disco. No pueden saltar a otro disco físico.
    - **Disco Dinámico:** Una de sus mayores ventajas es que un volumen simple puede extenderse usando espacio de otros discos físicos, convirtiéndose automáticamente en un **volumen distribuido** (Spanned Volume).

### 5. ¿Cuál es la capacidad total de un RAID 0 con dos discos de 1TB cada uno?

- **Respuesta:** 2 TB.
- **Justificación:** El RAID 0 (Striping) suma las capacidades de todos los discos que lo integran. Como no hay redundancia ni paridad (no se guarda información de recuperación), el 100% del espacio se utiliza para datos. La fórmula es $C = n \times d$ (número de discos por capacidad del más pequeño).

### 6. ¿Qué volumen de Windows es equivalente a un RAID 1?

- **Respuesta:** El **Volumen Reflejado** (Mirrored Volume).
- **Justificación:** Al igual que el RAID 1 físico, el volumen reflejado de Windows escribe los mismos datos simultáneamente en dos discos físicos distintos. Si uno falla, el sistema continúa funcionando con el segundo disco sin pérdida de datos.

### 7. ¿Cuál es la capacidad total de un RAID 5 con cuatro discos de 1TB cada uno?

- **Respuesta:** 3 TB.
- **Justificación:** La fórmula de capacidad para un RAID 5 es $(n - 1) \times C$ (donde $n$ es el número de discos y $C$ la capacidad del menor). En RAID 5, el equivalente a la capacidad de un disco completo se reserva para almacenar la **paridad** (distribuida entre todos los discos), por lo que perdemos 1TB de almacenamiento a cambio de tolerancia a fallos.

### 8. La configuración RAID 1, ¿aporta alguna mejora en los ciclos de lectura/escritura?

- **Respuesta:** Mejora en la **lectura**, pero no en la escritura (e incluso puede penalizarla levemente).
- **Justificación:**
    - **Lectura:** Hay una mejora porque el controlador puede leer datos de ambos discos simultáneamente (balanceo de carga), duplicando teóricamente la velocidad de lectura.
    - **Escritura:** No hay mejora porque los datos deben escribirse en ambos discos a la vez. El sistema debe esperar a que el disco más lento termine la operación para darla por concluida, lo que genera una ligera sobrecarga de gestión.

### 9. Añade el LSB al siguiente “010010” para que se cumpla paridad par.

- **Respuesta:** 010010**0**
- **Justificación:** La paridad par exige que el número total de unos en la cadena sea un número par.
    - Cadena original: `010010` (Contamos los “1”: hay dos “1”).
    - Como el número 2 ya es un número par, el bit de paridad (LSB o bit menos significativo) que debemos añadir es un **0** para que el conteo siga siendo 2 (par).