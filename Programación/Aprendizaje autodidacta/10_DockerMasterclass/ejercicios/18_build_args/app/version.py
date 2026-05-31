"""App YA RESUELTA. Imprime la version de Python y la version de la app."""
import os
import platform

print(f"Python: {platform.python_version()}")
print(f"APP_VERSION: {os.environ.get('APP_VERSION', '(no definido)')}")
