# ============================================================
#  EJERCICIO 15 - Multi-stage basico (imagen: masterclass/ej15)
#
#  Idea: COMPILAR en una etapa "pesada" (con el toolchain de Go) y copiar SOLO
#  el binario resultante a una etapa final minima. Asi la imagen de produccion
#  NO arrastra el compilador ni el codigo fuente.
# ============================================================

# --- Etapa 1: BUILDER ---------------------------------------
# TODO 1: Usa como base golang:1.22-alpine y nombra esta etapa "builder"
#         (FROM ... AS builder).

# TODO 2: Copia el codigo fuente de app/ (go.mod y main.go) a la etapa y
#         compila un binario ESTATICO en /server
#         (sugerencia: CGO_ENABLED=0 go build -o /server .).

# --- Etapa 2: FINAL (produccion) ----------------------------
# TODO 3: Empieza una NUEVA etapa final desde una base minima alpine:3.20
#         (sin nada de Go).

# TODO 4: Copia UNICAMENTE el binario /server desde la etapa "builder"
#         (COPY --from=builder ...). No copies el codigo fuente.

# TODO 5: Comando por defecto: ejecutar /server (forma de lista).
