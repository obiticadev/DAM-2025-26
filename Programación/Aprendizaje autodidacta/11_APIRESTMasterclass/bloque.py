#!/usr/bin/env python3
import os
import sys
import re

def main():
    root = os.path.dirname(os.path.abspath(__file__))
    pom_path = os.path.join(root, "pom.xml")

    if not os.path.isfile(pom_path):
        print(f"\033[91mERROR: No se encontró pom.xml en {root}\033[0m")
        sys.exit(1)

    # 1. Escanear todos los módulos disponibles (carpetas bNN_* con pom.xml)
    todos = []
    try:
        for item in sorted(os.listdir(root)):
            item_path = os.path.join(root, item)
            if os.path.isdir(item_path) and re.match(r'^b\d{2}_', item):
                if os.path.isfile(os.path.join(item_path, "pom.xml")):
                    todos.append(item)
    except Exception as e:
        print(f"\033[91mERROR al listar directorios: {e}\033[0m")
        sys.exit(1)

    # Leer el pom.xml
    try:
        with open(pom_path, 'r', encoding='utf-8') as f:
            pom_content = f.read()
    except Exception as e:
        print(f"\033[91mERROR al leer pom.xml: {e}\033[0m")
        sys.exit(1)

    # Detectar el tipo de fin de línea en el pom.xml para preservarlo (CRLF o LF)
    line_ending = "\r\n" if "\r\n" in pom_content else "\n"

    # Argumentos pasados al script
    args = sys.argv[1:]

    # 2. Si no hay argumentos, mostrar el estado actual
    if not args:
        activos = re.findall(r'<module>([^<]+)</module>', pom_content)
        print(f"\033[96mBloques activos ({len(activos)}/{len(todos)}):\033[0m")
        for act in activos:
            print(f"  - {act}")
        print(f"\nUso: python bloque.py b00 [b05 ...]   |   python bloque.py todos")
        sys.exit(0)

    # 3. Determinar la selección de bloques a activar
    seleccion = []
    if "todos" in args:
        seleccion = todos
    else:
        for arg in args:
            # Buscar coincidencia exacta o por prefijo (ej. b00 matches b00_http)
            matches = [t for t in todos if t == arg or t.startswith(arg)]
            if not matches:
                print(f"\033[91mERROR: no existe ningún bloque que empiece por '{arg}'.\033[0m")
                print(f"Disponibles: {', '.join(todos)}")
                sys.exit(1)
            for m in matches:
                if m not in seleccion:
                    seleccion.append(m)

    # 4. Construir la nueva sección <modules>
    indent = "        "
    modules_list = [f"{indent}<module>{s}</module>" for s in seleccion]
    nueva_seccion = f"<modules>{line_ending}" + f"{line_ending}".join(modules_list) + f"{line_ending}    </modules>"

    # 5. Reemplazar en el contenido del pom.xml
    # (?s) es el flag DOTALL para que el punto coincida con saltos de línea
    pom_nuevo = re.sub(r'(?s)<modules>.*?</modules>', nueva_seccion, pom_content)

    # 6. Escribir de vuelta
    try:
        with open(pom_path, 'w', encoding='utf-8', newline='') as f:
            f.write(pom_nuevo)
    except Exception as e:
        print(f"\033[91mERROR al escribir pom.xml: {e}\033[0m")
        sys.exit(1)

    print(f"\033[92mpom.xml actualizado. Bloques activos ({len(seleccion)}/{len(todos)}):\033[0m")
    for s in seleccion:
        print(f"  - {s}")

    print(f"\n\033[93mAhora en VS Code: Ctrl+Shift+P -> 'Java: Reload Projects'\033[0m")
    if "todos" not in args:
        print(f"\033[93mAntes de hacer commit o 'mvn test' global: python bloque.py todos\033[0m")

if __name__ == '__main__':
    main()
