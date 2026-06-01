# ============================================================
#  EJERCICIO 19 - Distroless: imagen final sin shell
#  (imagen: masterclass/ej19)
#
#  Una imagen "distroless" contiene SOLO lo imprescindible para correr tu app:
#  ni shell, ni gestor de paquetes, ni utilidades. Menos cosas = menos CVEs y
#  menos formas de que un atacante haga nada util dentro del contenedor.
# ============================================================

# --- Etapa 1: BUILDER ---------------------------------------
# TODO 1: Base golang:1.22-alpine como "builder"; copia app/ y compila un
#         binario ESTATICO en /server (CGO_ENABLED=0).

# --- Etapa 2: FINAL (distroless) ---------------------------
# TODO 2: Empieza la etapa final desde la imagen distroless estatica de Google
#         con TAG explicito:  gcr.io/distroless/static-debian12:nonroot

# TODO 3: Copia UNICAMENTE el binario /server desde "builder".

# TODO 4: Comando por defecto: ejecutar /server. Como NO hay shell, la forma
#         de lista (exec form) es obligatoria.
