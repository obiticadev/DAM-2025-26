# Laboratorio AWS Academy Cloud Foundations

## Caso práctico empresarial: plataforma de streaming en entorno de instituto

| Campo                      | Valor                                                                              |
| -------------------------- | ---------------------------------------------------------------------------------- |
| **Empresa ficticia**       | StreamWave Media                                                                   |
| **Entorno**                | AWS Academy Learner Lab                                                            |
| **Trabajo principal**      | Desplegar, validar, monitorizar y plantear escalabilidad de una arquitectura cloud |
| **Restricción del centro** | Sin acceso al router del instituto y con posibles bloqueos de firewall             |

---

## 1. Empresa y necesidad

StreamWave Media es una empresa que ofrece vídeos educativos, documentales breves y material multimedia para centros de formación. Su necesidad es disponer de una infraestructura cloud inicial sencilla, escalable y económica que soporte una plataforma de streaming educativo, con posibilidad de crecer sin interrumpir el servicio.

La plataforma debe poder validarse desde un entorno con restricciones de red: sin acceso al router del instituto, con posible bloqueo de SSH y de puertos externos desde los equipos del aula. Las comprobaciones se realizan desde la consola de AWS, el navegador y, cuando sea necesario, desde AWS CloudShell.

---

## 2. Preparación del entorno

### Paso 1. Acceder al laboratorio de AWS Academy

1. Entrar en AWS Academy Learner Lab.
2. Iniciar el laboratorio desde el botón correspondiente.
3. Acceder a la consola de AWS.
4. Comprobar que el estado del laboratorio aparece como activo.

![[ CAPTURA-01 ] Consola AWS abierta dentro del Learner Lab — debe verse el estado "activo" del laboratorio y la pantalla principal de la consola AWS. Guardar como capturas/captura-01.png](./capturas/captura-01.png)

---

### Paso 2. Comprobar la región y los servicios disponibles

1. Anotar la región en la que se trabajará.
2. Buscar EC2, S3, VPC, CloudWatch y CloudShell desde el buscador de servicios.
3. Comprobar si CloudFront, Auto Scaling Groups y Load Balancers aparecen disponibles en el laboratorio.

**Región utilizada:** `us-east-1` — Estados Unidos (Norte de Virginia)

**Servicios disponibles y estado:**

| Servicio             | Disponible                                                                                                              |
| -------------------- | ----------------------------------------------------------------------------------------------------------------------- |
| Amazon EC2           | ✅ Disponible                                                                                                           |
| Amazon S3            | ✅ Disponible                                                                                                           |
| Amazon VPC           | ✅ Disponible                                                                                                           |
| Amazon CloudWatch    | ✅ Disponible                                                                                                           |
| AWS CloudShell       | ✅ Disponible                                                                                                           |
| Load Balancers (ELB) | ✅ Disponible — página accesible sin errores, permisos de lectura y creación presentes                                  |
| CloudFront           | ❌ Restringido — sin permisos IAM (`cloudfront:ListDistributions` denegado por la política del rol `voclabs`)           |
| Auto Scaling Groups  | ❌ Restringido — sin permisos IAM (`autoscaling-plans:DescribeScalingPlans` denegado por la política del rol `voclabs`) |

> **Regla de trabajo en el aula:** Si un servicio no está permitido por el laboratorio de AWS Academy, no se sustituye por acciones fuera de AWS ni se intenta cambiar la red del instituto. Se documenta la limitación y se realiza el apartado como análisis técnico.

![[ CAPTURA-02 ] Servicios AWS localizados en el buscador: EC2, S3, VPC, CloudWatch, CloudShell disponibles. Incluir también las pantallas de error de CloudFront y Auto Scaling como evidencia de restricción. Guardar como capturas/captura-02.png](./capturas/captura-02.png)

---

## 3. Despliegue del portal web en EC2

### Paso 3. Crear la instancia EC2

1. Abrir el servicio EC2.
2. Seleccionar **Launch Instance**.
3. Nombre de la instancia: `streamwave-web`.
4. AMI: **Amazon Linux**.
5. Tipo de instancia: **t2.micro** o equivalente permitido por el laboratorio.
6. Usar la VPC y subred por defecto, salvo que el profesor indique otra configuración.

### Paso 4. Configurar el Security Group

1. Crear un Security Group llamado `streamwave-sg`.
2. Permitir **HTTP puerto 80** desde `0.0.0.0/0`.
3. Permitir **HTTPS puerto 443** desde `0.0.0.0/0`, aunque la web inicial no use certificado.
4. No depender de SSH. Si se añade SSH puerto 22, será solo como observación, no como parte obligatoria de la práctica.

> **Nota:** En el instituto puede estar bloqueada la salida por SSH. La práctica está diseñada para completarse sin conectarse por SSH.

### Script de User Data

Pegar este contenido en el apartado **User Data** antes de lanzar la instancia:

```bash
#!/bin/bash
yum update -y
yum install httpd -y
systemctl start httpd
systemctl enable httpd
cat > /var/www/html/index.html <<'EOF'
<html>
<head><title>StreamWave Media</title></head>
<body>
<h1>StreamWave Media</h1>
<p>Plataforma educativa de streaming desplegada en AWS.</p>
<p>Servidor web funcionando correctamente.</p>
</body>
</html>
EOF
```

### Paso 5. Lanzar y comprobar la instancia

1. Esperar a que la instancia aparezca en estado **Running**.
2. Comprobar que los **Status Checks** estén correctos.
3. Copiar la IP pública de la instancia.
4. Intentar acceder desde el navegador con `http://IP_PUBLICA`.

**Si el firewall del instituto bloquea la prueba desde el navegador:**

1. No modificar el router ni pedir cambios en la red del centro.
2. Abrir **AWS CloudShell** desde la consola de AWS.
3. Ejecutar: `curl http://IP_PUBLICA`
4. Si aparece el HTML de StreamWave Media, la web está funcionando aunque el aula no permita verla desde el navegador.
5. Guardar captura de CloudShell como evidencia alternativa.

![[ CAPTURA-03 ] Instancia streamwave-web en estado Running con los dos Status Checks en verde. Guardar como capturas/captura-03.png](./capturas/captura-03.png)

![[ CAPTURA-04 ] Web de StreamWave Media cargada en el navegador con http://IP_PUBLICA, o resultado del comando curl en CloudShell mostrando el HTML de respuesta. Guardar como capturas/captura-04.png](./capturas/captura-04.png)

---

## 4. Almacenamiento de contenidos con Amazon S3

### Paso 6. Crear el bucket principal

1. Abrir Amazon S3.
2. Crear un bucket llamado `streamwave-media-tu-nombre`.
3. Mantener el bloqueo de acceso público activado, salvo indicación contraria del profesor.
4. No usar S3 como web pública en esta práctica si el laboratorio o el centro lo restringen.

### Paso 7. Organizar la estructura del bucket

1. Crear una carpeta **`videos/`**.
2. Crear una carpeta **`trailers/`**.
3. Crear una carpeta **`imagenes/`**.
4. Crear una carpeta **`documentos/`**.
5. Subir al menos un archivo a cada carpeta.

![[ CAPTURA-05 ] Bucket S3 con las cuatro carpetas creadas (videos/, trailers/, imagenes/, documentos/) y al menos un archivo visible en cada una. Guardar como capturas/captura-05.png](./capturas/captura-05.png)

### Paso 8. Activar versionado

1. Entrar en la pestaña **Properties** del bucket.
2. Activar **Bucket Versioning**.
3. Subir un archivo llamado `cartel.html` o `descripcion.txt`.
4. Modificar el archivo en el ordenador y volver a subirlo con el mismo nombre.
5. Comprobar que aparecen varias versiones del mismo objeto.

![[ CAPTURA-06 ] Pestaña Properties con Bucket Versioning activado + lista de versiones del mismo objeto mostrando al menos 2 versiones distintas. Guardar como capturas/captura-06.png](./capturas/captura-06.png)

### Paso 9. Crear una regla de ciclo de vida

1. Entrar en **Management** dentro del bucket.
2. Crear una regla de ciclo de vida para la carpeta `videos/` o `documentos/`.
3. Configurar una transición simulada a una clase de almacenamiento más barata si el laboratorio lo permite.

**Si el laboratorio no permite completar la regla:**

En una empresa real se configuraría una transición automática a **S3 Standard-IA** (Infrequent Access) a los 30 días, y a **S3 Glacier Instant Retrieval** a los 90 días, para los vídeos con acceso poco frecuente. Esto reduce significativamente el coste de almacenamiento sin eliminar el contenido.

![[ CAPTURA-07 ] Pestaña Management del bucket con la regla de ciclo de vida creada. Si el laboratorio no lo permite, captura del error de restricción como evidencia. Guardar como capturas/captura-07.png](./capturas/captura-07.png)

> **Aplicación empresarial:** StreamWave Media no debería guardar los vídeos dentro de la instancia EC2. Si la instancia falla o se sustituye, se perdería contenido. S3 permite separar el servidor web del almacenamiento multimedia y facilita el crecimiento de la plataforma.

---

## 5. Red y conectividad con VPC

### Paso 10. Identificar la red utilizada

1. Abrir el servicio VPC.
2. Localizar la VPC usada por la instancia EC2.
3. Identificar la subred en la que está la instancia.
4. Localizar la tabla de rutas asociada.
5. Buscar el Internet Gateway conectado a la VPC.

### Paso 11. Comprobar la salida a Internet

1. En la tabla de rutas, buscar la ruta `0.0.0.0/0`.
2. Comprobar que el destino es un Internet Gateway con identificador `igw-...`.
3. Relacionar esta ruta con el acceso HTTP a la instancia.

**Explicación:** La ruta `0.0.0.0/0` hacia el IGW es la que permite que el tráfico HTTP entrante desde cualquier IP pública llegue a la instancia EC2. Sin esta ruta, la instancia no sería accesible desde Internet aunque el Security Group lo permitiera.

![[ CAPTURA-08 ] VPC usada por la instancia + tabla de rutas con la ruta 0.0.0.0/0 apuntando al Internet Gateway (igw-...). Pueden ser dos capturas unidas o una sola con ambas secciones visibles. Guardar como capturas/captura-08.png](./capturas/captura-08.png)

### Paso 12. Prueba controlada de Security Group

1. Entrar en el Security Group de la instancia.
2. Eliminar temporalmente la regla HTTP puerto 80.
3. Intentar acceder de nuevo a la web desde navegador o CloudShell.
4. Volver a añadir la regla HTTP puerto 80.
5. Comprobar que vuelve a funcionar.

> **Nota:** Esta prueba se hace en AWS, no en el router del instituto. No modifica la red local del centro.

**Resultado observado:** Al eliminar la regla de puerto 80, la web deja de ser accesible (timeout o connection refused). Al restaurarla, el acceso se recupera de inmediato. Esto demuestra que el Security Group actúa como firewall a nivel de instancia, de forma completamente independiente de la red del instituto.

![[ CAPTURA-09 ] Security Group con la regla HTTP puerto 80 eliminada — la web no responde (timeout o connection refused). Guardar como capturas/captura-09.png](./capturas/captura-09.png)

![[ CAPTURA-09.1 ] Security Group con la regla HTTP puerto 80 restaurada — la web vuelve a responder correctamente. Guardar como capturas/captura-09.1.png](./capturas/captura-09.1.png)

---

## 6. Monitorización con CloudWatch

### Paso 13. Revisar métricas básicas de EC2

1. Abrir **CloudWatch**.
2. Acceder a **Metrics**.
3. Buscar las métricas de EC2.
4. Consultar **CPUUtilization**.
5. Consultar **NetworkIn** y **NetworkOut**.
6. Consultar **StatusCheckFailed** si aparece disponible.

![[ CAPTURA-10 ] CloudWatch Metrics con las métricas de la instancia EC2 visibles: CPUUtilization y NetworkIn/Out en la misma pantalla si es posible. Guardar como capturas/captura-10.png](./capturas/captura-10.png)

### Paso 14. Generar tráfico de forma segura

**Opción A:** Refrescar varias veces la web desde el navegador.

**Opción B — si el navegador del centro no accede a la IP pública:** Abrir CloudShell y ejecutar:

```bash
for i in {1..30}; do
  curl -s http://IP_PUBLICA > /dev/null
  echo "Peticion $i enviada"
done
```

### Paso 15. Analizar el resultado

1. Esperar unos minutos a que CloudWatch actualice las métricas.
2. Comprobar si **NetworkIn** o **NetworkOut** han cambiado.
3. Explicar si la CPU cambia o permanece estable.
4. Indicar por qué una web sencilla genera poca carga.

**Análisis:**

- **NetworkIn/NetworkOut:** Aumentan ligeramente al recibir y responder las peticiones HTTP, aunque los valores son bajos dado el reducido tamaño del HTML servido.
- **CPUUtilization:** Permanece prácticamente estable (cercana al 0%). Servir una página HTML estática consume mínimos ciclos de CPU: Apache simplemente lee un archivo del disco y lo envía por red, sin procesamiento.
- **Por qué genera poca carga:** Una web estática sin base de datos ni lógica de servidor apenas consume recursos. La carga real aparecería al introducir procesamiento dinámico, consultas a BD o transcodificación de vídeo.

![[ CAPTURA-11 ] CloudWatch con las métricas actualizadas tras generar tráfico — variación visible en NetworkIn/Out y CPUUtilization estable cerca del 0%. Guardar como capturas/captura-11.png](./capturas/captura-11.png)

### Paso 16. Crear una alarma de CPU

1. Desde CloudWatch, crear una alarma sobre **CPUUtilization**.
2. Configurar un umbral del **70%**.
3. No es obligatorio enviar notificación por correo si el laboratorio no permite SNS.
4. Guardar captura de la configuración.

![[ CAPTURA-12 ] Alarma de CPUUtilization creada en CloudWatch con umbral configurado al 70% — debe verse la pantalla de confirmación o la alarma en la lista. Guardar como capturas/captura-12.png](./capturas/captura-12.png)

---

## 7. Casos de escalabilidad aplicados a empresa

### Caso A — Lanzamiento inicial

_Pocos usuarios, web informativa sencilla, una instancia EC2 pequeña, recursos multimedia en S3, monitorización con CloudWatch._

**Solución:** Una instancia EC2 t2.micro es suficiente para servir a cientos de usuarios concurrentes con contenido estático. Los recursos multimedia se alojan en S3, separando almacenamiento de servidor. CloudWatch monitoriza la instancia para detectar anomalías. No se sobredimensiona: el coste sería innecesario y la demanda no lo justifica en esta fase inicial.

---

### Caso B — Aumento de visitas en horario lectivo (9:00–14:00)

_Tráfico creciente entre las 9:00 y las 14:00, picos predecibles._

**Solución:** Implementar **Auto Scaling** con una política basada en métricas de CloudWatch (CPUUtilization > 60% o número de peticiones elevado). Se crea una **AMI** de la instancia actual como plantilla de lanzamiento para replicar servidores idénticos. El grupo escala a 2–3 instancias durante el horario lectivo y vuelve a 1 fuera de él. Esto optimiza coste y capacidad de forma automática sin intervención manual.

---

### Caso C — Usuarios de varios países

_Acceso desde distintas ubicaciones geográficas con alta latencia._

**Solución:** Implementar **Amazon CloudFront** como CDN delante del contenido estático y multimedia. S3 actúa como origen de imágenes, trailers y documentos. CloudFront replica el contenido en edge locations cercanos a los usuarios, reduciendo drásticamente la latencia de descarga. El servidor EC2 solo recibe peticiones que no puedan cachearse. Una CDN reduce la latencia porque el usuario descarga desde un nodo cercano en lugar de desde la región de AWS, y descarga trabajo del servidor EC2 al evitar que sirva el mismo contenido repetidamente.

---

### Caso D — Fallo del servidor principal

_La instancia EC2 deja de responder._

**Solución:** Desplegar al menos **dos instancias EC2** detrás de un **Application Load Balancer (ALB)**, distribuidas en dos zonas de disponibilidad distintas (ej. us-east-1a y us-east-1b). El ALB realiza health checks periódicos; si una instancia falla, deja de enviarle tráfico automáticamente. CloudWatch detectaría el fallo mediante `StatusCheckFailed` y podría activar una alarma o acción de Auto Scaling para reemplazar la instancia caída. El servicio se mantiene activo sin intervención manual y sin que el usuario perciba la interrupción.

---

### Caso E — Crecimiento del almacenamiento multimedia

_La empresa pasa de decenas a miles de vídeos._

**Solución:** Usar S3 con **clases de almacenamiento por frecuencia de acceso** y reglas de ciclo de vida automáticas:

| Clase                        | Cuándo                        | Coste relativo |
| ---------------------------- | ----------------------------- | -------------- |
| S3 Standard                  | Vídeos recientes y frecuentes | Alto           |
| S3 Standard-IA               | Vídeos de más de 30 días      | Medio          |
| S3 Glacier Instant Retrieval | Vídeos archivados +90 días    | Bajo           |

Las reglas de ciclo de vida mueven objetos entre clases automáticamente. La documentación interna se mantiene en una carpeta separada con permisos restringidos. Este enfoque escala ilimitadamente en capacidad y reduce el coste de almacenamiento hasta un 70% respecto a mantener todo en S3 Standard.

---

### Caso F — Coste mensual demasiado alto

_La empresa detecta que el coste cloud crece demasiado._

**Solución:**

1. Revisar instancias EC2 encendidas sin uso y terminarlas.
2. Eliminar recursos de pruebas: snapshots, AMIs antiguas, buckets vacíos.
3. Usar métricas de CloudWatch para ajustar el tipo de instancia al uso real (_rightsizing_).
4. Revisar la transferencia de datos: CloudFront reduce los costes de data transfer out desde S3 y EC2.
5. Aplicar reglas de ciclo de vida en S3 para mover contenido antiguo a clases más baratas.
6. Usar **AWS Cost Explorer** para identificar los recursos que más facturan mes a mes.

---

## 8. Tareas prácticas adicionales de escalabilidad

### Paso 17. Arquitectura para 500 usuarios diarios

**Diagrama:**

```
         Internet
            │
            ▼
  [EC2 t2.micro — streamwave-web]
            │
     ┌──────┴──────┐
     ▼             ▼
[S3 bucket]  [CloudWatch]
(videos,      (métricas y
 imágenes,     alarmas)
 docs)

VPC por defecto → Subred pública → Internet Gateway
```

**Por qué no es imprescindible un Load Balancer:** Con 500 usuarios diarios y contenido estático, una sola instancia t2.micro gestiona sin problema el tráfico concurrente estimado (decenas de usuarios simultáneos). Añadir un Load Balancer introduciría un coste mensual fijo sin beneficio real en este volumen.

**Métricas a vigilar:** CPUUtilization (normal < 40%), NetworkIn/Out, StatusCheckFailed.

---

### Paso 18. Arquitectura para 10.000 usuarios diarios

**Diagrama:**

```
         Internet
            │
            ▼
[Application Load Balancer]
       │         │
       ▼         ▼
 [EC2 #1]    [EC2 #2]        [EC2 #3 — Auto Scaling]
 AZ-1a       AZ-1b           (se lanza si CPU > 60%)
       │
  [S3 bucket] ← origen multimedia
       │
  [CloudWatch + Auto Scaling Policy]
```

**Cambios respecto al caso anterior:**

- Se añade un **ALB** para distribuir el tráfico entre instancias y detectar fallos.
- Las instancias se replican en **dos zonas de disponibilidad** para alta disponibilidad.
- **Auto Scaling** ajusta el número de instancias según CPU o número de peticiones: escala hacia arriba en picos y reduce en horas bajas, optimizando coste.
- El tráfico se reparte en round-robin entre instancias sanas.

---

### Paso 19. Arquitectura para usuarios internacionales

**Diagrama:**

```
Usuarios en Europa, Asia, América
            │
            ▼
  [CloudFront — Edge Locations globales]
       │                    │
       ▼                    ▼
[S3 — origen        [ALB → EC2 x2 + Auto Scaling]
 contenido           (peticiones dinámicas)
 estático]
                     [CloudWatch]
```

**Qué sirve CloudFront y qué EC2:**

- **CloudFront:** imágenes, vídeos, trailers, CSS, JS — contenido estático que no varía por usuario y puede cachearse en los edge locations.
- **EC2:** lógica dinámica que CloudFront no puede cachear: login, registro, personalización, APIs.

**Impacto en el rendimiento percibido:** El usuario descarga el contenido multimedia desde el edge location más cercano (latencia < 50 ms) en lugar de desde la región de AWS (latencia 100–300 ms). La experiencia mejora drásticamente para usuarios en Europa, Asia y Latinoamérica sin modificar la infraestructura de origen.

---

### Paso 20. Comparación de las tres arquitecturas

| Arquitectura      | Componentes                                       | Ventajas                                                               | Inconvenientes                                            | Coste relativo |
| ----------------- | ------------------------------------------------- | ---------------------------------------------------------------------- | --------------------------------------------------------- | -------------- |
| **Mínima**        | EC2 + S3 + CloudWatch                             | Simple, económica, fácil de gestionar                                  | Punto único de fallo, no escala automáticamente           | Bajo           |
| **Escalable**     | ALB + varias EC2 + Auto Scaling + S3 + CloudWatch | Alta disponibilidad, escala automática, tolerante a fallos             | Mayor complejidad y coste fijo del LB                     | Medio          |
| **Internacional** | CloudFront + S3 + ALB + EC2 + Auto Scaling        | Latencia global reducida, CDN descarga el origen, máxima escalabilidad | Más costosa, configuración avanzada de caché y CloudFront | Alto           |

---

## 9. Costes, sostenibilidad y limpieza del laboratorio

### Paso 21. Revisar posibles costes

**Recursos que generan coste en StreamWave Media:**

- **EC2:** factura por hora de ejecución aunque no haya tráfico. Una instancia t2.micro puede suponer ~$8–10/mes.
- **S3:** factura por GB almacenado y por número de solicitudes GET/PUT. Con miles de vídeos el almacenamiento crece rápidamente.
- **CloudFront:** factura por GB transferido a los usuarios. En una plataforma multimedia, la transferencia de datos es el mayor coste potencial.
- **Transferencia de datos (data transfer out):** la salida de datos de AWS hacia Internet tiene coste por GB. CloudFront reduce este coste al cachear y servir desde edge locations.

**Por qué una plataforma multimedia aumenta costes rápidamente:** Cada reproducción de vídeo implica transferencia de datos. Con miles de usuarios reproduciendo contenido en HD, el ancho de banda puede convertirse en el coste dominante, creciendo linealmente con el número de reproducciones y el peso de cada vídeo.

### Paso 22. Proponer medidas de optimización

**Medidas concretas aplicadas a StreamWave Media:**

1. Apagar o detener instancias EC2 fuera del horario lectivo si el tráfico es predeciblemente nulo por la noche y fines de semana.
2. Aplicar reglas de ciclo de vida en S3 para mover vídeos de más de 30 días a Standard-IA y a Glacier a los 90 días.
3. Desplegar CloudFront para cachear el contenido estático y multimedia, reduciendo las solicitudes directas al origen y el data transfer out facturado.
4. Revisar mensualmente las métricas de CPU y memoria para ajustar el tipo de instancia al uso real (_rightsizing_).
5. Eliminar recursos temporales al finalizar pruebas: snapshots huérfanos, AMIs no usadas, buckets de test.

### Paso 23. Limpieza final

1. Terminar la instancia EC2.
2. Eliminar buckets S3 creados, vaciándolos previamente.
3. Eliminar alarmas de CloudWatch creadas.
4. Eliminar distribuciones CloudFront si se llegaron a crear.
5. Comprobar que no quedan recursos activos innecesarios.

> **Nota:** La limpieza forma parte de la práctica. En cloud, dejar recursos activos puede generar costes aunque no se estén usando.

![[ CAPTURA-13 ] Instancia EC2 en estado "Terminated" en la lista de instancias. Guardar como capturas/captura-13.png](./capturas/captura-13.png)

![[ CAPTURA-13.1 ] Lista de buckets S3 vacía tras eliminar el bucket del laboratorio. Guardar como capturas/captura-13.1.png](./capturas/captura-13.1.png)

![[ CAPTURA-13.2 ] CloudWatch sin alarmas activas tras eliminarlas. Guardar como capturas/captura-13.2.png](./capturas/captura-13.2.png)

---

## Checklist final

- [x] He creado una instancia EC2 con una web básica.
- [x] He probado la web desde navegador o desde CloudShell.
- [x] He creado un bucket S3 con carpetas organizadas.
- [x] He activado versionado o he explicado por qué no se pudo activar.
- [x] He identificado VPC, subred, tabla de rutas e Internet Gateway.
- [x] He revisado métricas en CloudWatch.
- [x] He planteado escenarios de escalabilidad.
- [x] He propuesto mejoras de coste y sostenibilidad.
- [x] He eliminado los recursos creados al finalizar.

---

> **Conclusión:** Una arquitectura cloud no consiste solo en crear recursos, sino en diseñar una solución que funcione en un entorno real, que pueda crecer, que sea medible, que controle costes y que se adapte a restricciones como las de una red de instituto. StreamWave Media parte de una base mínima y viable, con capacidad de evolucionar hacia una plataforma global escalable conforme crezcan sus usuarios y necesidades.
