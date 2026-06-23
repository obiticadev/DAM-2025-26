#!/usr/bin/env python3
import os
import shutil
import re

def main():
    # Obtener el directorio raíz donde se encuentra este script
    root = os.path.dirname(os.path.abspath(__file__))
    teoria_dir = os.path.join(root, 'teoria')
    
    if not os.path.isdir(teoria_dir):
        print(f"ERROR: No se encontró la carpeta de teoría en: {teoria_dir}")
        return

    # Buscar archivos de teoría
    try:
        teoria_files = os.listdir(teoria_dir)
    except Exception as e:
        print(f"ERROR al leer la carpeta de teoría: {e}")
        return

    copiados = 0
    saltados = 0

    # Expresión regular para detectar directorios de bloque (ej. b00_http, b01_java)
    block_pattern = re.compile(r'^b(\d+)_')

    # Listar directorios en la raíz
    try:
        items = os.listdir(root)
    except Exception as e:
        print(f"ERROR al leer el directorio raíz: {e}")
        return

    for item in sorted(items):
        item_path = os.path.join(root, item)
        if not os.path.isdir(item_path):
            continue

        match = block_pattern.match(item)
        if not match:
            continue

        num = match.group(1)

        # Buscar el archivo correspondiente en teoria que empiece con "NN_" y termine en ".md"
        fuente_name = None
        for f in teoria_files:
            if f.startswith(f"{num}_") and f.endswith(".md"):
                fuente_name = f
                break

        if not fuente_name:
            print(f"\033[93m[SIN TEORIA] {item}\033[0m")
            saltados += 1
            continue

        fuente_path = os.path.join(teoria_dir, fuente_name)
        destino_path = os.path.join(item_path, fuente_name)

        try:
            # Copy2 conserva metadatos y sobrescribe el destino si ya existe
            shutil.copy2(fuente_path, destino_path)
            print(f"\033[92m[OK] {item}/{fuente_name}  <-  teoria/{fuente_name}\033[0m")
            copiados += 1
        except Exception as e:
            print(f"\033[91m[ERROR] No se pudo copiar a {item}: {e}\033[0m")
            saltados += 1

    print(f"\n\033[96mSincronizados: {copiados}  |  Sin teoria: {saltados}\033[0m")

if __name__ == '__main__':
    main()
