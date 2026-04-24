### 📋 Guía de Servicios AWS: HireFlow AI

#### 🔧 Pilar 1: Excelencia Operativa
*Enfocado en ejecutar, monitorizar y mejorar sistemas.*

| Servicio AWS | Función en HireFlow AI | Detalle Clave |
|:---|:---|:---|
| **AWS SAM / CloudFormation** | Infraestructura como Código (IaC). | Permite replicar todo el sistema en minutos sin errores manuales. |
| **Amazon CloudWatch** | Monitorización y alarmas. | Vigila la salud de las Lambdas y activa alarmas si la **Cola Roja (DLQ)** recibe mensajes. |
| **AWS X-Ray** | Trazabilidad distribuida. | Rastrea el camino de un CV para identificar exactamente dónde hay cuellos de botella. |
| **Canary Deployments** | Estrategia de despliegue seguro. | Envía solo el 10% del tráfico al código nuevo para probarlo ("el canario") antes del lanzamiento total. |

#### 🔒 Pilar 2: Seguridad
*Enfocado en proteger datos e identidades en todas las capas.*

| Servicio AWS | Función en HireFlow AI | Detalle Clave |
|:---|:---|:---|
| **Amazon Cognito** | Gestión de identidades de candidatos. | Autenticación segura (Login) sin guardar contraseñas en nuestra base de datos. |
| **AWS IAM** | Permisos de "Mínimo Privilegio". | Asegura que cada servicio (como Lambda) solo pueda hacer lo estrictamente necesario. |
| **AWS WAF** | Firewall de Aplicación Web. | Bloquea ataques de hackers, inyecciones SQL y bots maliciosos. |
| **TLS 1.2+** | Cifrado **en tránsito**. | Protege el CV mientras viaja por internet desde el PC del candidato hasta AWS. |
| **SSE-S3** | Cifrado **en reposo** (S3). | Cifra los archivos PDF dentro de los discos de AWS de forma automática. |
| **AWS KMS** | Gestión de llaves de cifrado. | Controla las llaves maestras que bloquean y desbloquean los datos en S3 y DynamoDB. |
| **AWS CloudTrail** | Auditoría de llamadas API. | Registra quién, cuándo y qué se hizo en la cuenta de AWS (historial inmutable). |
| **Amazon GuardDuty** | Detección inteligente de amenazas. | IA que busca comportamientos sospechosos o accesos desde países no autorizados. |

#### 🔄 Pilar 3: Fiabilidad
*Enfocado en que el sistema se recupere de fallos y esté siempre disponible.*

| Servicio AWS | Función en HireFlow AI | Detalle Clave |
|:---|:---|:---|
| **Amazon SQS** | Cola de mensajes (Asincronía). | Desacopla la subida del CV del procesamiento. Si hay un pico masivo, SQS aguanta la carga. |
| **Dead Letter Queue (DLQ)** | "La Cola Roja" (Sala de rescate). | Captura los CVs que fallan 3 veces para que no se pierdan y los humanos puedan revisarlos. |
| **AWS Step Functions** | Orquestador de flujos. | Gestiona los reintentos automáticos y el orden de los pasos (IA → Puntuación → Email). |
| **Amazon S3** | Almacenamiento de alta durabilidad. | Ofrece 11 nueves de durabilidad (99.999999999%) para que no se pierda ningún PDF. |
| **Amazon DynamoDB (Backups)** | Recuperación Punto en el Tiempo (PITR). | Permite restaurar la base de datos de candidatos a cualquier segundo de los últimos 35 días. |
| **Amazon SES / SNS** | Notificaciones fiables. | Garantiza que los emails a candidatos y alertas a RRHH lleguen a su destino. |

#### ⚡ Pilar 4: Eficiencia del Rendimiento
*Enfocado en usar los recursos de forma inteligente para ser rápidos.*

| Servicio AWS | Función en HireFlow AI | Detalle Clave |
|:---|:---|:---|
| **Amazon CloudFront** | Red de contenido global (CDN). | Entrega la web del candidato desde el servidor más cercano a su ciudad. |
| **Amazon API Gateway** | Puerta de entrada escalable. | Gestiona miles de peticiones simultáneas con latencia mínima. |
| **AWS Lambda** | Cómputo Serverless. | Escala instantáneamente: si llegan 10,000 CVs, lanza 10,000 funciones en paralelo. |
| **Amazon Textract** | IA de extracción (OCR). | Extrae texto de PDFs mucho más rápido de lo que podría un humano o un servidor tradicional. |
| **Amazon Comprehend** | IA de lenguaje natural (NLP). | Analiza habilidades y sentimientos en segundos usando modelos pre-entrenados. |
| **Amazon DynamoDB** | Base de datos de alta velocidad. | Respuestas en menos de 10 milisegundos para consultar perfiles de candidatos. |

#### 💰 Pilar 5: Optimización de Costos
*Enfocado en pagar solo por lo que se usa y evitar desperdicio.*

| Servicio AWS | Función en HireFlow AI | Detalle Clave |
|:---|:---|:---|
| **Modelo Serverless** | Pago por uso (Lambda, SQS, etc.). | Si no hay candidatos subiendo CVs, **el costo es $0**. |
| **S3 Intelligent-Tiering** | Gestión automática de capas. | Mueve los CVs que nadie mira a capas de almacenamiento más baratas automáticamente. |
| **S3 Glacier** | Archivo a largo plazo. | Almacena CVs de procesos terminados por fracciones de céntimo al mes. |
| **AWS Budgets** | Presupuestos y alertas. | Envía alertas si el gasto del mes proyecta superar el límite establecido por la empresa. |

---

### 💡 Resumen Estratégico Final

1.  **¿S3 o DynamoDB?** Usamos **S3** para el archivo "pesado" (el PDF físico) porque es barato y espacioso, y **DynamoDB** para el "índice" (datos del candidato + enlace al PDF en S3) porque es rápido y permite búsquedas.
2.  **Seguridad Total:** Los datos están blindados mientras viajan (**TLS**) y mientras duermen (**SSE-S3**).
3.  **Cero Pérdidas:** Si algo falla en el procesamiento, no borramos nada; lo enviamos a la **Cola Roja (DLQ)** para salvar el currículum.
4.  **Actualizaciones sin Miedo:** Antes de cambiar el sistema para todos, probamos con un pequeño grupo (**Canary**) para asegurar que la nueva versión es perfecta.