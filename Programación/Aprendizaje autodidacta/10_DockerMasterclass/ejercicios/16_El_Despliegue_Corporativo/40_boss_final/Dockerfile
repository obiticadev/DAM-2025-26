# ============================================================
#  BOSS FINAL (ej 40) - Imagen de la API: multi-stage + no-root + healthcheck
#  (imagen: masterclass/ej40)
#
#  Aqui juntas TODO lo aprendido en una sola imagen de produccion:
#   - build multi-stage (compilas el binario Go y tiras el compilador),
#   - imagen final minima que NO corre como root,
#   - HEALTHCHECK para que Docker/Compose sepan si la API esta sana.
#  El codigo Go esta en  app/  (no se toca). Tu construyes la imagen.
# ============================================================

# ---------- Etapa 1: compilacion ----------
# TODO 1: FROM golang:1.22-alpine AS builder

# TODO 2: WORKDIR /src

# TODO 3: copia  app/go.mod  y  app/main.go  al WORKDIR

# TODO 4: compila un binario estatico en  /api :
#         RUN CGO_ENABLED=0 go build -o /api .

# ---------- Etapa 2: imagen final ----------
# TODO 5: FROM alpine:3.20

# TODO 6: crea un usuario sin privilegios  appuser  (UID 10001)
#         RUN adduser -D -u 10001 appuser

# TODO 7: copia el binario desde la etapa builder:  COPY --from=builder /api /api

# TODO 8: conmuta a  appuser  (USER) y expon el puerto 8080 (EXPOSE)

# TODO 9: declara un HEALTHCHECK que consulte  /health  con wget:
#         HEALTHCHECK --interval=5s --timeout=3s --start-period=3s --retries=3 \
#           CMD wget -q --spider http://localhost:8080/health || exit 1

# TODO 10: comando por defecto:  ["/api"]
