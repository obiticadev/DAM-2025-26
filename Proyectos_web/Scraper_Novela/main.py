import os
import time
import random
import requests
from bs4 import BeautifulSoup
import config
import logging

# Configurar logging para ver qué pasa
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(levelname)s - %(message)s',
    handlers=[
        logging.FileHandler("scraper.log"),
        logging.StreamHandler()
    ]
)

def get_chapter_url(chapter_num):
    return config.BASE_URL.format(chapter_num)

def fetch_chapter_content(chapter_num):
    url = get_chapter_url(chapter_num)
    logging.info(f"Iniciando descarga del Capítulo {chapter_num}: {url}")
    
    headers = {
        'User-Agent': config.USER_AGENT,
        'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
        'Accept-Language': 'es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3',
    }
    
    try:
        response = requests.get(url, headers=headers, timeout=30)
        
        if response.status_code == 200:
            return response.text
        elif response.status_code == 404:
            logging.warning(f"Capítulo {chapter_num} no encontrado (404).")
            return None
        else:
            logging.error(f"Error {response.status_code} al acceder a {url}")
            return None
            
    except requests.RequestException as e:
        logging.error(f"Error de conexión: {e}")
        return None

def parse_and_save_chapter(chapter_num, html_content):
    if not html_content:
        return

    soup = BeautifulSoup(html_content, 'html.parser')
    
    # 1. Extraer Título
    try:
        title_tag = soup.select_one(config.TITLE_SELECTOR)
        if title_tag:
            title_text = title_tag.get_text(strip=True)
        else:
            logging.warning(f"No se encontró el título con el selector '{config.TITLE_SELECTOR}'. Usando título por defecto.")
            title_text = f"Capítulo {chapter_num}"
    except Exception as e:
        logging.error(f"Error extrayendo título: {e}")
        title_text = f"Capítulo {chapter_num}"

    # 2. Extraer Contenido
    try:
        content_div = soup.select_one(config.CONTENT_SELECTOR)
        if not content_div:
            logging.error(f"No se encontró el contenedor del contenido '{config.CONTENT_SELECTOR}' en el capítulo {chapter_num}.")
            return

        # Encontrar todos los párrafos DIRECTOS dentro del contenedor (según captura usuario)
        # Usamos recursive=False para evitar párrafos dentro de bloques de anuncios o navegación anidados
        paragraphs = content_div.find_all('p', recursive=False)
        
        if not paragraphs:
            logging.warning(f"Se encontró el contenedor pero no hay párrafos <p> dentro en el capítulo {chapter_num}.")

        # Construir el HTML del contenido limpio
        chapter_content_html = ""
        for p in paragraphs:
            # Opcional: Limpiar atributos sucios si fuera necesario, pero req usuario dice 'todos los parrafos'
            # Simplemente añadimos el texto o el html del párrafo
            chapter_content_html += str(p) + "\n"
            
    except Exception as e:
        logging.error(f"Error extrayendo contenido: {e}")
        return

    # 3. Guardar Archivo
    save_to_file(chapter_num, title_text, chapter_content_html)

def save_to_file(chapter_num, title, content_html):
    # Asegurar que el directorio de salida existe
    base_dir = os.path.dirname(os.path.abspath(__file__))
    output_dir = os.path.join(base_dir, config.OUTPUT_FOLDER_NAME)
    
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)
        logging.info(f"Carpeta creada: {output_dir}")

    # Limpiar nombre del archivo
    safe_title = "".join([c for c in title if c.isalnum() or c in (' ', '-', '_')]).strip()
    # Limitar longitud del nombre de archivo por si acaso
    if len(safe_title) > 50:
        safe_title = safe_title[:50]
        
    filename = f"Capitulo_{chapter_num:03d}_{safe_title}.html"
    filepath = os.path.join(output_dir, filename)

    # Plantilla HTML simple y limpia
    html_template = f"""<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>{title}</title>
    <style>
        body {{
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            line-height: 1.6;
            color: #333;
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #f9f9f9;
        }}
        h1 {{
            text-align: center;
            color: #2c3e50;
            border-bottom: 2px solid #eaeaea;
            padding-bottom: 10px;
        }}
        .content {{
            background: white;
            padding: 30px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            border-radius: 8px;
        }}
        p {{
            margin-bottom: 1.2em;
            text-align: justify;
        }}
    </style>
</head>
<body>
    <div class="content">
        <h1>{title}</h1>
        {content_html}
    </div>
</body>
</html>"""

    try:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(html_template)
        logging.info(f"Guardado exitosamente: {filename}")
    except Exception as e:
        logging.error(f"Error escribiendo archivo {filename}: {e}")

def main():
    print("===================================================")
    print("   INICIANDO SCRAPER DE NOVELAS - MODO HUMANO")
    print("===================================================")
    print(f"URL Base: {config.BASE_URL}")
    print(f"Capítulos: {config.START_CHAPTER} a {config.END_CHAPTER}")
    print(f"Carpeta de destino: {config.OUTPUT_FOLDER_NAME}")
    print(f"Intervalo de espera: {config.MIN_DELAY_SECONDS} - {config.MAX_DELAY_SECONDS} segundos")
    print("===================================================\n")

    # Crear carpeta si no existe antes de empezar
    base_dir = os.path.dirname(os.path.abspath(__file__))
    output_dir = os.path.join(base_dir, config.OUTPUT_FOLDER_NAME)
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    for chapter in range(config.START_CHAPTER, config.END_CHAPTER + 1):
        # 1. Descargar y Procesar
        html = fetch_chapter_content(chapter)
        if html:
            parse_and_save_chapter(chapter, html)
        
        # 2. Espera Aleatoria (Humanización)
        # No esperamos después del último capítulo
        if chapter < config.END_CHAPTER:
            delay = random.uniform(config.MIN_DELAY_SECONDS, config.MAX_DELAY_SECONDS)
            logging.info(f"Esperando {delay:.1f} segundos para evitar bloqueo...")
            time.sleep(delay)
            
    print("\n===================================================")
    print("   PROCESO COMPLETADO")
    print("===================================================")

if __name__ == "__main__":
    main()
