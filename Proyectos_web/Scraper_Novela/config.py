# Configuración del Scrapper de Novelas

# ==========================================
# CONFIGURACIÓN DE LA NOVELA
# ==========================================

# URL base de los capítulos.
# IMPORTANTE: Usa {} donde iría el número del capítulo.
# Ejemplo: https://novelasligera.com/novela/la-vida-despues-de-la-muerte/capitulo-{}/
BASE_URL = "https://novelasligera.com/novela/la-vida-despues-de-la-muerte/capitulo-{}/"

# Rango de capítulos a descargar
START_CHAPTER = 1    # Primer capítulo (ej. 1)
END_CHAPTER = 529    # Último capítulo (ej. 529)

# Nombre de la carpeta donde se guardarán los archivos descargados
OUTPUT_FOLDER_NAME = "La vida después de la muerte - Todos los capítulos"

# ==========================================
# SELECTORES WEB (CSS)
# ==========================================
# Aquí defines qué partes de la página quieres extraer.
# Si la página cambia de estructura, solo necesitas actualizar esto.

# Selector para el TÍTULO del capítulo (se guardará como <h1> en el archivo final)
TITLE_SELECTOR = "h1.entry-title"

# Selector para el CONTENIDO del capítulo.
# El programa buscará todos los párrafos (<p>) DENTRO de este contenedor.
CONTENT_SELECTOR = "div.entry-content"

# ==========================================
# CONFIGURACIÓN ANTI-BLOQUEO (HUMANIZACIÓN)
# ==========================================

# Tiempo de espera entre descargas (en segundos)
# El programa esperará un tiempo aleatorio entre estos dos valores para parecer humano.
MIN_DELAY_SECONDS = 5
MAX_DELAY_SECONDS = 15

# Identidad del navegador (User-Agent) para no parecer un robot.
USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
