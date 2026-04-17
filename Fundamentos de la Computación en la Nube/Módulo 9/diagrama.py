from diagrams import Cluster, Diagram, Edge
from diagrams.aws.compute import Lambda, EKS
from diagrams.aws.database import Dynamodb, Aurora
from diagrams.aws.integration import Eventbridge, SQS, SNS
from diagrams.aws.network import Route53, CloudFront
from diagrams.aws.security import WAF
from diagrams.aws.storage import S3
from diagrams.aws.ml import Rekognition, Comprehend, SageMaker

# Configuración del diagrama
graph_attr = {
    "fontsize": "20",
    "bgcolor": "white",
    "nodesep": "0.5",
    "ranksep": "1.0",
    "compound": "true",
    "dir": "TB", # Orientación Top-to-Bottom
}

with Diagram("AWS Architecture for Fly & Snap, Show & Sell, Make & Ship", show=False, filename="aws_diagram", graph_attr=graph_attr):
    # Cluster para la Capa de Red y Seguridad (Compartida/Entrada)
    with Cluster("Edge & Security Layer"):
        r53 = Route53("Route 53")
        cf = CloudFront("CloudFront")
        waf = WAF("WAF")
        r53 >> cf >> waf

    # Clusters para los Flujos Específicos (Azul, Verde, Rojo)
    
    # Flujo 1: FLY AND SNAP (Azul)
    with Cluster("FLY AND SNAP FLOW (BLUE)", graph_attr={"color": "#4285F4", "style": "dashed"}):
        # Simulación de Drone/Hub externo (icono genérico o caja)
        drone = SQS("External Drone Data") # SQS como "punto de entrada" de datos externos en este ejemplo
        # Usamos S3 y Lambda como servicios clave para este flujo
        s3_fly = S3("Fly Storage")
        lambda_fly = Lambda("Fly Processing")
        
        waf >> drone
        drone >> s3_fly >> lambda_fly
        # Ejemplo de conexión a servicios de IA desde este flujo
        ai_comprehend = Comprehend("AI Analysis (Comprehend)")
        ai_rekognition = Rekognition("AI Visión (Rekognition)")
        
        lambda_fly >> ai_comprehend
        lambda_fly >> ai_rekognition
        db_dynamo = Dynamodb("DynamoDB")
        ai_comprehend >> db_dynamo
        ai_rekognition >> db_dynamo

    # Flujo 2: SHOW AND SELL (Verde)
    with Cluster("SHOW AND SELL FLOW (GREEN)", graph_attr={"color": "#34A853", "style": "dashed"}):
        # Simulación de aplicación de cliente externa
        app_client = S3("Customer App") # Usamos S3 como icono genérico para la app estática en este ejemplo
        # Usamos CloudFront y Lambda como servicios clave para este flujo
        cf_sell = CloudFront("Showroom CDN")
        lambda_sell = Lambda("Sales Logic")
        
        waf >> cf_sell >> app_client # La app carga desde CF/S3
        app_client >> cf_sell >> lambda_sell # Interacciones de venta a Lambda
        # Ejemplo de conexión a servicios de IA y Base de Datos desde este flujo
        ai_sagemaker = SageMaker("AI Recommendations (SageMaker)")
        db_aurora = Aurora("Aurora DB")
        
        lambda_sell >> ai_sagemaker >> lambda_sell # Bidireccional para recomendaciones
        lambda_sell >> db_aurora

    # Flujo 3: MAKE AND SHIP (Rojo)
    with Cluster("MAKE AND SHIP FLOW (RED)", graph_attr={"color": "#EA4335", "style": "dashed"}):
        # Evento de Venta como desencadenador
        event_sale = Eventbridge("Sales Event")
        # Usamos EKS, Step Functions y servicios de mensajería como servicios clave para este flujo
        eks_make = EKS("Make Service")
        sqs_fulfillment = SQS("Fulfillment Queue")
        sns_ship = SNS("Ship Notifications")
        
        # Conexión indirecta o simulada para este flujo, ya que EventBridge se alimenta de otros eventos
        event_sale >> eks_make >> sqs_fulfillment >> eks_make
        # Step Functions orquestando Fulfillment
        # (diagrams no tiene icono directo de Step Functions, se usaría un Cluster o icono genérico para simplificar aquí)
        # SQS triggers fulfillment logic (simulated by EKS here)
        # Fulfillment completes, SNS triggers ship notifications
        eks_make >> sns_ship # EKS finaliza Make/Ship y notifica
        
    # Cluster para los Servicios Compartidos de Cómputo y Datos (y Mensajería/Notificaciones)
    with Cluster("Shared Compute & Data Layer"):
        shared_lambda = Lambda("Shared Functions")
        shared_eks = EKS("Shared Clusters")
        shared_s3 = S3("Shared Storage")
        shared_dynamo = Dynamodb("Shared DynamoDB")
        shared_aurora = Aurora("Shared Aurora DB")
        shared_eb = Eventbridge("Shared Event Bus")
        shared_sqs = SQS("Shared Queue")
        shared_sns = SNS("Shared SNS")
        shared_ses = SQS("Shared SES") # SES como SQS para icono genérico/simplificado

    # Definir conexiones y flujos transversales conceptuales (simplificados)
    
    # FLY AND SNAP connections to shared/other services
    lambda_fly >> shared_eb # Enviar eventos de Fly and Snap
    shared_eb >> shared_lambda >> shared_dynamo # Un evento genérico procesado y guardado
    lambda_fly >> db_dynamo # Conexión directa del flujo a su DB (ya definida)
    ai_comprehend >> db_dynamo # Conexión directa del flujo a su DB (ya definida)
    ai_rekognition >> db_dynamo # Conexión directa del flujo a su DB (ya definida)

    # SHOW AND SELL connections to shared/other services
    lambda_sell >> shared_eb # Enviar eventos de Show and Sell
    shared_eb >> shared_eks >> shared_aurora # Un evento genérico procesado y guardado
    lambda_sell >> db_aurora # Conexión directa del flujo a su DB (ya definida)
    ai_sagemaker >> db_aurora # Conexión directa del flujo a su DB (ya definida)

    # MAKE AND SHIP connections to shared/other services
    eks_make >> shared_dynamo # EKS de Make and Ship interactúa con la DB compartida
    sns_ship >> shared_sns # Usar SNS compartido para notificaciones
    # SES podría enviar correos
    sns_ship >> shared_ses

    # Conectar clusters principales para visualización conceptual
    waf >> drone
    waf >> cf_sell
    # No conectar directamente a Make and Ship ya que se basa en eventos internos/compartidos
    shared_eb >> event_sale # EventBridge compartido alimenta EventBridge específico

    # Mostrar servicios de IA y Analítica (fuera de clusters específicos si son compartidos)
    ai_comprehend
    ai_rekognition
    ai_sagemaker

print("Diagrama generado en aws_diagram.png")