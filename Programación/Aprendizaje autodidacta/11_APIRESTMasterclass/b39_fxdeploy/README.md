# b39_fxdeploy · Documentación, ayuda y distribución (DI·RA5/RA6)

Último bloque del módulo **Desarrollo de Interfaces**. Cierra los RA5 (documentar) y RA6
(distribuir). Teoría completa en [`teoria/39_Distribucion_Instaladores.md`](../teoria/39_Distribucion_Instaladores.md).

Los cores son **lógica pura headless** (parsear manifest/module-info, construir comandos como
cadenas, comparar versiones SemVer, leer/escribir `Preferences` en un nodo temporal): se prueban con
JUnit normal, **sin abrir ventana ni ejecutar herramientas externas**.

## Lanzar el Playground visual

```bash
mvn -pl b39_fxdeploy javafx:run
```

Muestra un diálogo "Acerca de", un `Hyperlink` a la doc, un `CheckBox` de modo oscuro **persistido
con `Preferences`** y un aviso de actualización (SemVer).

## Ejercicios "guion": comandos reales de jlink / jpackage

`Ej308` y `Ej309` **no ejecutan** las herramientas (son comandos de terminal y necesitan toolchain
del SO). Sus cores construyen y validan el comando como `String`. Para empaquetar de verdad:

### 1. Compilar el jar con dependencias (ejemplo)

```bash
mvn -pl b39_fxdeploy package
```

### 2. Runtime mínimo con `jlink`

```bash
jlink --add-modules java.base,javafx.controls,javafx.fxml \
      --output target/runtime \
      --strip-debug --compress=2 --no-header-files
```

### 3. Instalador nativo con `jpackage`

```bash
# Windows (requiere WiX Toolset instalado)
jpackage --name MasterclassApp --app-version 1.0.0 --type msi \
         --input target --main-jar b39_fxdeploy-1.0.0.jar \
         --runtime-image target/runtime \
         --vendor "DAM" --icon src/main/resources/app.ico

# Linux (requiere dpkg)
jpackage --name MasterclassApp --app-version 1.0.0 --type deb \
         --input target --main-jar b39_fxdeploy-1.0.0.jar \
         --runtime-image target/runtime --icon src/main/resources/app.png

# macOS
jpackage --name MasterclassApp --app-version 1.0.0 --type dmg \
         --input target --main-jar b39_fxdeploy-1.0.0.jar \
         --runtime-image target/runtime --icon src/main/resources/app.icns
```

> `jpackage` solo genera el instalador **nativo del SO en el que corre**. Sin las herramientas del
> SO (WiX / dpkg…) solo puede producir un `app-image` (carpeta ejecutable, sin instalador):
> añade `--type app-image`.
