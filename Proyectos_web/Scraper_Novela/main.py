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

def fetch_chapter_content(url, chapter_num, just_for_link=False):
    if just_for_link:
        logging.info(f"Revisando Capítulo {chapter_num} internamente (No se guardará) solo para obtener enlace al siguiente: {url}")
    else:
        logging.info(f"Descargando Capítulo Faltante {chapter_num}: {url}")
    
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

        # Construir el texto del contenido limpio
        valid_paragraphs = []
        for p in paragraphs:
            # Extraer texto reemplazando <br> u otros saltos internos con espacios
            text = p.get_text(separator=' ', strip=True)
            if text:
                # Normalizar para eliminar cualquier salto de línea extra que se cuele dentro del párrafo
                text = " ".join(text.split())
                valid_paragraphs.append(text)
                
        # Unimos los párrafos con exactamente un doble salto de línea (un salto visual entre párrafos)
        # Esto es lo ideal para programas de creación Epub basados en TXT
        chapter_content_text = "\n\n".join(valid_paragraphs)
            
    except Exception as e:
        logging.error(f"Error extrayendo contenido: {e}")
        return

    # 3. Guardar Archivo
    save_to_file(chapter_num, title_text, chapter_content_text)

def save_to_file(chapter_num, title, content_text):
    # Asegurar que el directorio de salida existe
    base_dir = os.path.dirname(os.path.abspath(__file__))
    output_dir = os.path.join(base_dir, config.OUTPUT_FOLDER_NAME)
    
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)
        logging.info(f"Carpeta creada: {output_dir}")

    # Limpiar nombre del archivo
    safe_title = "".join([c for c in title if c.isalnum() or c in (' ', '-', '_')]).strip()
        
    filename = f"{safe_title}.txt"
    filepath = os.path.join(output_dir, filename)

    # Plantilla TXT simple y limpia
    txt_template = f"{title}\n\n{content_text}".strip()

    try:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(txt_template)
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

    import re
    
    # 1. Limpieza de archivos incorrectos (ej. novelas equivocadas en caso de error) y listar válidos
    valid_chapters_downloaded = set()
    if os.path.exists(output_dir):
        for filename in os.listdir(output_dir):
            if filename.endswith(".txt"):
                filepath = os.path.join(output_dir, filename)
                if "TBATE" not in filename.upper():  # Si el nombre no contiene TBATE, lo borramos
                    logging.info(f"Eliminando archivo incorrecto (no es TBATE): {filename}")
                    try:
                        os.remove(filepath)
                    except Exception as e:
                        logging.error(f"Error borrando {filename}: {e}")
                else:
                    # Extraer número de capítulo correcto
                    match = re.search(r"Cap[íi]tulo\s+(\d+)", filename, re.IGNORECASE)
                    if match:
                        valid_chapters_downloaded.add(int(match.group(1)))

    current_url = get_chapter_url(config.START_CHAPTER)

    for chapter in range(config.START_CHAPTER, config.END_CHAPTER + 1):
        # 2. Comprobar si ya tenemos el capítulo correcto
        if chapter in valid_chapters_downloaded:
            # Necesitamos obtener la URL para el SIGUIENTE capítulo solo si el siguiente nos falta
            if chapter < config.END_CHAPTER and (chapter + 1) not in valid_chapters_downloaded:
                logging.info(f"Capítulo {chapter} existe, accediendo a {current_url} SOLO para obtener URL del Cap {(chapter+1)}...")
                html = fetch_chapter_content(current_url, chapter, just_for_link=True)
                if html:
                    soup = BeautifulSoup(html, 'html.parser')
                    next_nav = soup.select_one('div.wp-post-navigation-next a')
                    if next_nav and 'href' in next_nav.attrs:
                        current_url = next_nav['href']
                    else:
                        logging.warning("No se encontró enlace al siguiente capítulo, usando URL por defecto.")
                        current_url = get_chapter_url(chapter + 1)
                    
                    delay = random.uniform(2.0, 5.0)  # Breve pausa por la consulta de enlace
                    time.sleep(delay)
                else:
                    current_url = get_chapter_url(chapter + 1)
            else:
                # Si el siguiente también existe, actualizamos a la URL teórica para seguir la cadena con calma
                current_url = get_chapter_url(chapter + 1)
            
            continue

        # 3. Descargar y Procesar Capítulo Faltante usando el URL real
        html = fetch_chapter_content(current_url, chapter)
        if html:
            parse_and_save_chapter(chapter, html)
            
            # 4. Extraer el enlace de "Página Siguiente"
            soup = BeautifulSoup(html, 'html.parser')
            next_nav = soup.select_one('div.wp-post-navigation-next a')
            if next_nav and 'href' in next_nav.attrs:
                current_url = next_nav['href']
            else:
                logging.warning("No se encontró enlace al siguiente capítulo, usando URL por defecto.")
                current_url = get_chapter_url(chapter + 1)
        
        # 5. Espera Aleatoria (Humanización)
        if chapter < config.END_CHAPTER:
            delay = random.uniform(config.MIN_DELAY_SECONDS, config.MAX_DELAY_SECONDS)
            logging.info(f"Esperando {delay:.1f} segundos para evitar bloqueo...")
            time.sleep(delay)
            
    print("\n===================================================")
    print("   PROCESO COMPLETADO")
    print("===================================================")

if __name__ == "__main__":
    main()
