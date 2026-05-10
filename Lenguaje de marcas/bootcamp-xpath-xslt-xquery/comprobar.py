#!/usr/bin/env python3
"""
comprobar.py — Validador de ejercicios del Bootcamp XPath / XSLT / XQuery

Uso:
    python comprobar.py              → valida todos los ejercicios
    python comprobar.py 01           → solo el ejercicio 01
    python comprobar.py xpath        → todos los XPath  (ej01-08)
    python comprobar.py xslt         → todos los XSLT   (ej09-17)
    python comprobar.py xquery       → todos los XQuery (ej18-25)
    python comprobar.py reto         → solo el reto final
"""

import sys
import re
import subprocess
from pathlib import Path

# Forzar UTF-8 en la salida para Windows
if hasattr(sys.stdout, "reconfigure"):
    sys.stdout.reconfigure(encoding="utf-8", errors="replace")

BASE_DIR = Path(__file__).parent

EJERCICIOS = {
    "01": ("XPath",  "Rutas Básicas"),
    "02": ("XPath",  "Atributos"),
    "03": ("XPath",  "Ejes"),
    "04": ("XPath",  "Predicados de Posición"),
    "05": ("XPath",  "Predicados Compuestos"),
    "06": ("XPath",  "Funciones Numéricas"),
    "07": ("XPath",  "Funciones de Cadena"),
    "08": ("XPath",  "Funciones Avanzadas"),
    "09": ("XSLT",   "Plantilla Básica"),
    "10": ("XSLT",   "ForEach y Sort"),
    "11": ("XSLT",   "Condicionales"),
    "12": ("XSLT",   "Variables y Parámetros"),
    "13": ("XSLT",   "Modos y Named Templates"),
    "14": ("XSLT",   "Salida CSV"),
    "15": ("XSLT",   "Elementos Dinámicos"),
    "16": ("XSLT",   "Integrador Inventario"),
    "17": ("XSLT",   "Apply Templates"),
    "18": ("XQuery", "Ruta Simple"),
    "19": ("XQuery", "FLWOR Básico"),
    "20": ("XQuery", "FLWOR con let"),
    "21": ("XQuery", "Funciones de Cadena"),
    "22": ("XQuery", "Agregación"),
    "23": ("XQuery", "Joins"),
    "24": ("XQuery", "XQUF"),
    "25": ("XQuery", "Prólogo y Funciones"),
    "reto": ("Reto",  "Reto Final"),
}

TEST_FILES = {
    "xpath":  "tests/test_xpath.py",
    "xslt":   "tests/test_xslt.py",
    "xquery": "tests/test_xquery.py",
    "reto":   "tests/test_reto_final.py",
}

NUM_TO_BLOQUE = {
    **{f"{i:02d}": "xpath"  for i in range(1, 9)},
    **{f"{i:02d}": "xslt"   for i in range(9, 18)},
    **{f"{i:02d}": "xquery" for i in range(18, 26)},
}


def get_pytest_args(arg: str) -> tuple[list[str], str]:
    """Devuelve (args_pytest, descripción) para el filtro pedido."""
    arg = arg.lower().strip()

    if arg in TEST_FILES:
        return [TEST_FILES[arg]], f"bloque {arg.upper()}"

    num = arg.zfill(2) if arg.isdigit() else arg
    if num in NUM_TO_BLOQUE:
        bloque = NUM_TO_BLOQUE[num]
        tipo, nombre = EJERCICIOS[num]
        return [TEST_FILES[bloque], "-k", f"Ej{num}"], f"Ej{num} — {nombre}"

    if arg == "reto":
        return [TEST_FILES["reto"]], "Reto Final"

    print(f"\nERROR: '{arg}' no reconocido.")
    print("Opciones válidas: número 01-25, o bloque xpath / xslt / xquery / reto")
    sys.exit(1)


def friendly_test_name(raw: str) -> str:
    """Convierte 'test_a_elemento_raiz_catalogo' → 'a) elemento raiz catalogo'."""
    name = raw.removeprefix("test_")
    parts = name.split("_", 1)
    if len(parts) == 2 and len(parts[0]) <= 2:
        return f"{parts[0]}) {parts[1].replace('_', ' ')}"
    return name.replace("_", " ")


def _ej_key(class_name: str):
    """Extrae la clave del ejercicio ('01'..'25' o 'reto') del nombre de clase."""
    m = re.match(r"TestEj(\d{2})", class_name)
    if m:
        return m.group(1)
    if "reto" in class_name.lower():
        return "reto"
    return None


def parse_output(output: str) -> dict:
    """
    Parsea la salida de pytest -v --tb=short.

    Formato esperado:
      - Líneas verbose:  "tests/...::TestEjNN...::test_xxx PASSED/FAILED"
      - Sección FAILURES con cabeceras: "___ TestEjNN.test_xxx ___"
        y líneas de error con prefijo "E   mensaje"

    Devuelve:
        {ej_num: {"passed": [...], "failed": [...], "errors": {test: msg}}}
    """
    data = {}

    # Regex 1: líneas verbose de resultado por test
    result_re = re.compile(
        r"tests[\\/]test_\w+\.py::(\w+)::(test_\w+)\s+(PASSED|FAILED|ERROR)"
    )
    # Regex 2: cabecera de sección de fallo  "___ ClassName.test_xxx ___"
    section_re = re.compile(r"_{3,}\s+(\w+)\.(test_\w+)\s+_{3,}")

    # Paso 1 — recoger resultados PASSED/FAILED
    for line in output.splitlines():
        m = result_re.search(line)
        if not m:
            continue
        class_name, test_name, status = m.group(1), m.group(2), m.group(3)
        key = _ej_key(class_name)
        if not key:
            continue
        if key not in data:
            data[key] = {"passed": [], "failed": [], "errors": {}}
        if status == "PASSED":
            data[key]["passed"].append(test_name)
        else:
            data[key]["failed"].append(test_name)

    # Paso 2 — recoger mensajes de error de la sección FAILURES
    # Dentro de cada sección, la primera línea "E   ..." (sin "E    +") es el mensaje clave
    current_class = current_test = None
    for line in output.splitlines():
        m = section_re.search(line)
        if m:
            current_class, current_test = m.group(1), m.group(2)
            continue
        if current_class and line.startswith("E   ") and not line.startswith("E    "):
            msg = line[4:].strip()
            key = _ej_key(current_class)
            if key and key in data:
                data[key]["errors"][current_test] = (
                    msg[:120] + "..." if len(msg) > 120 else msg
                )
            current_class = current_test = None  # tomar solo el primer E por sección

    return data


def print_summary(data: dict, description: str):
    W = 64
    print()
    print("=" * W)
    print(f"  RESULTADOS — {description.upper()}")
    print("=" * W)

    if not data:
        print("  Sin resultados. ¿Has completado los TODOs del ejercicio?")
        print("=" * W)
        return

    total_ok = total_fail = 0
    failed_keys = []

    for key in sorted(data.keys()):
        d = data[key]
        ok = len(d["passed"])
        fail = len(d["failed"])
        total = ok + fail
        total_ok += ok
        total_fail += fail

        tipo, nombre = EJERCICIOS.get(key, ("?", "Desconocido"))

        if fail == 0:
            print(f"  [OK]    Ej{key} [{tipo:<6}] {nombre}")
        else:
            failed_keys.append(key)
            print(f"  [FALLO] Ej{key} [{tipo:<6}] {nombre}  ({ok}/{total} tests)")
            for test in d["failed"]:
                label = friendly_test_name(test)
                print(f"           x {label}")
                msg = d["errors"].get(test, "")
                if msg:
                    print(f"             {msg}")

    print()
    print("-" * W)
    total = total_ok + total_fail
    pct = int(100 * total_ok / total) if total else 0
    print(f"  Tests pasados: {total_ok}/{total}  ({pct} %)")

    if not failed_keys:
        print()
        print("  ¡Todo correcto! Puedes pasar al siguiente bloque.")
    else:
        ejercs = ", ".join(f"Ej{k}" for k in failed_keys)
        print(f"  Ejercicios con fallos: {ejercs}")
    print("=" * W)
    print()


def main():
    if len(sys.argv) == 1:
        pytest_args = list(TEST_FILES.values())
        description = "todos los ejercicios"
    else:
        pytest_args, description = get_pytest_args(sys.argv[1])

    cmd = [sys.executable, "-m", "pytest", *pytest_args,
           "-v", "--tb=short", "-p", "no:cacheprovider"]

    result = subprocess.run(
        cmd,
        capture_output=True,
        text=True,
        encoding="utf-8",
        errors="replace",
        cwd=BASE_DIR,
    )

    data = parse_output(result.stdout + result.stderr)
    print_summary(data, description)

    sys.exit(0 if result.returncode == 0 else 1)


if __name__ == "__main__":
    main()
