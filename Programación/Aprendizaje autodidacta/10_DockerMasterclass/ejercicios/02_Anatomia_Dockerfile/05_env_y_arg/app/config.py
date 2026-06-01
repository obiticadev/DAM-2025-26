"""App YA RESUELTA. Lee la configuracion desde variables de entorno."""
import os

print("APP_ENV =", os.environ.get("APP_ENV", "(no definido)"))
print("APP_VERSION =", os.environ.get("APP_VERSION", "(no definido)"))
