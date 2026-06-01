# Dockerfile del servicio web (ya resuelto: tu trabajo esta en compose.yaml).
FROM python:3.12-slim
WORKDIR /app
RUN pip install --no-cache-dir "psycopg[binary]"
COPY server.py .
ENV PYTHONUNBUFFERED=1
EXPOSE 8000
CMD ["python", "server.py"]
