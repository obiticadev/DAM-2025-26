# syntax=docker/dockerfile:1
# ============================================================
#  EJERCICIO 17 - Cache mounts con BuildKit (imagen: masterclass/ej17)
#
#  BuildKit permite montar cache PERSISTENTE entre builds para directorios
#  como el cache de compilacion de Go (/root/.cache/go-build). Esa cache NO
#  queda en la capa final (no engorda la imagen) pero acelera reconstrucciones.
#  La 1a linea "# syntax=docker/dockerfile:1" habilita esta sintaxis.
# ============================================================

# --- Etapa 1: BUILDER ---------------------------------------
# TODO 1: Base golang:1.22-alpine como etapa "builder"; copia app/ dentro.

# TODO 2: Compila /server PERO montando un cache mount de BuildKit sobre el
#         directorio de cache de compilacion de Go: /root/.cache/go-build
#         (sintaxis:  RUN --mount=type=cache,target=/root/.cache/go-build ...).
#         Compila estatico (CGO_ENABLED=0).

# --- Etapa 2: FINAL ----------------------------------------
# TODO 3: Etapa final desde alpine:3.20 con solo el binario /server.

# TODO 4: Comando por defecto: ejecutar /server (forma de lista).
