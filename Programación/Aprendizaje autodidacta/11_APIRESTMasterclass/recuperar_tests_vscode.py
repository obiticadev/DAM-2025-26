#!/usr/bin/env python3
import json
import os
import subprocess
import sys


ROOT = os.path.dirname(os.path.abspath(__file__))
SETTINGS_PATH = os.path.join(ROOT, ".vscode", "settings.json")
TEST_EXTENSION = "vscjava.vscode-java-test"
CUSTOM_EXTENSION_JS = os.path.expanduser(
    "~/.vscode-oss/extensions/oliwheel.java-maven-test-buttons-0.0.1/extension.js"
)


def run(command):
    print("$ " + " ".join(command), flush=True)
    return subprocess.run(command, cwd=ROOT, text=True)


def ensure_test_extension():
    result = subprocess.run(
        ["code", "--list-extensions"],
        cwd=ROOT,
        text=True,
        capture_output=True,
    )
    if result.returncode != 0:
        print(result.stderr.strip())
        print("AVISO: no se pudo listar extensiones con 'code'.")
        return

    installed = {line.strip() for line in result.stdout.splitlines()}
    if TEST_EXTENSION in installed:
        print(f"OK: extensión instalada: {TEST_EXTENSION}", flush=True)
        return

    install = run(["code", "--install-extension", TEST_EXTENSION])
    if install.returncode != 0:
        print("AVISO: no se pudo instalar la extensión oficial de tests Java.")


def ensure_settings():
    os.makedirs(os.path.dirname(SETTINGS_PATH), exist_ok=True)
    settings = {}
    if os.path.exists(SETTINGS_PATH):
        try:
            with open(SETTINGS_PATH, "r", encoding="utf-8") as f:
                settings = json.load(f)
        except json.JSONDecodeError:
            print(f"ERROR: {SETTINGS_PATH} no contiene JSON válido.")
            sys.exit(1)

    settings.update(
        {
            "java.test.editor.enableShortcuts": True,
            "java.referencesCodeLens.enabled": True,
            "java.implementationsCodeLens.enabled": True,
        }
    )

    with open(SETTINGS_PATH, "w", encoding="utf-8") as f:
        json.dump(settings, f, indent=4, ensure_ascii=False)
        f.write("\n")
    print(f"OK: configuración actualizada: {SETTINGS_PATH}", flush=True)


def ensure_custom_extension_log_path():
    os.makedirs("/tmp/opencode", exist_ok=True)
    if not os.path.exists(CUSTOM_EXTENSION_JS):
        print("AVISO: no existe la extensión local oliwheel.java-maven-test-buttons.")
        return

    with open(CUSTOM_EXTENSION_JS, "r", encoding="utf-8") as f:
        content = f.read()

    old = "function log(message) { fs.appendFileSync('/tmp/opencode/java-test-buttons.log', `${new Date().toISOString()} ${message}\\n`); }"
    if old not in content:
        print("OK: extensión local ya no falla por /tmp/opencode", flush=True)
    else:
        new = """const logDir = '/tmp/opencode';
const logFile = path.join(logDir, 'java-test-buttons.log');
function log(message) {
  fs.mkdirSync(logDir, { recursive: true });
  fs.appendFileSync(logFile, `${new Date().toISOString()} ${message}\\n`);
}"""
        content = content.replace(old, new)
        print("OK: extensión local reparada para crear /tmp/opencode", flush=True)

    content = content.replace(
        "vscode.workspace.findFiles('src/test/java/**/*Test.java', '**/target/**')",
        "vscode.workspace.findFiles('**/src/test/java/**/*Test.java', '**/target/**')",
    )
    with open(CUSTOM_EXTENSION_JS, "w", encoding="utf-8") as f:
        f.write(content)
    print("OK: extensión local busca tests en submódulos", flush=True)


def activate_block(block):
    bloque_py = os.path.join(ROOT, "bloque.py")
    if not os.path.exists(bloque_py):
        print("AVISO: no existe bloque.py; no se cambia la selección de módulos.")
        return

    result = run([sys.executable, bloque_py, block])
    if result.returncode != 0:
        sys.exit(result.returncode)


def main():
    block = sys.argv[1] if len(sys.argv) > 1 else "b26"
    ensure_test_extension()
    ensure_settings()
    ensure_custom_extension_log_path()
    activate_block(block)
    print()
    print("Ahora en VS Code ejecuta:")
    print("  1. Ctrl+Shift+P -> Developer: Reload Window")
    print("  2. Si no aparecen: Ctrl+Shift+P -> Java: Clean Java Language Server Workspace")
    print("  3. Después: Ctrl+Shift+P -> Java: Reload Projects")


if __name__ == "__main__":
    main()
