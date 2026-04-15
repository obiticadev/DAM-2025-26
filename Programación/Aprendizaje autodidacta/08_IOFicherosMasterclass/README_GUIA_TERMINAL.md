# 08 — I/O y Ficheros Masterclass: Guia de Terminal

## Requisitos previos

| Herramienta | Version minima | Comprobar con |
|-------------|---------------|---------------|
| Java JDK    | 17            | `java -version` |
| Maven       | 3.8+          | `mvn -version`  |

> Asegurate de que `JAVA_HOME` apunta al JDK 17 y de que `mvn` esta en el PATH.

---

## Compilar el proyecto

```bash
mvn compile
```

Si todo va bien veras `BUILD SUCCESS`. Si hay errores de compilacion, revisa que
no hayas borrado los `return` por defecto de los esqueletos.

---

## Ejecutar tests

### Todos los tests del proyecto
```bash
mvn test
```

### Tests de un bloque concreto
```bash
mvn test -Dtest="bloque1.*"
mvn test -Dtest="bloque2.*"
mvn test -Dtest="bloque3.*"
mvn test -Dtest="bloque4.*"
mvn test -Dtest="bloque5.*"
mvn test -Dtest="bloque6.*"
mvn test -Dtest="bloque7.*"
mvn test -Dtest="bossfinal.*"
```

### Test de un ejercicio concreto
```bash
mvn test -Dtest="bloque1.Ej01_EscribirBytesTest"
mvn test -Dtest="bloque5.Ej30_MiniCrudPersistenteTest"
```

---

## Ejecutar un main concreto

```bash
mvn compile exec:java -Dexec.mainClass="bloque1.Ej01_EscribirBytes"
```

Si no tienes el plugin `exec-maven-plugin`, puedes ejecutar directamente:

```bash
# Compilar primero
mvn compile

# Ejecutar (Windows)
java -cp target/classes bloque1.Ej01_EscribirBytes

# Ejecutar (Linux/Mac)
java -cp target/classes bloque1.Ej01_EscribirBytes
```

---

## Flujo de trabajo recomendado

1. **Lee la teoria** del bloque en `teoria/0X_Tema.md`.
2. **Abre el ejercicio** `EjXX_Nombre.java` y lee el Javadoc completo.
3. **Ejecuta el main** para ver el estado inicial (valores por defecto).
4. **Implementa los TODOs** uno a uno (del 1 al 7).
5. **Ejecuta el main** tras cada TODO para ver progreso parcial.
6. **Pasa el test** del ejercicio cuando termines todos los TODOs.
7. **Avanza al siguiente ejercicio** del bloque.
8. **Repite** hasta completar el bloque, luego pasa al siguiente.

> **Consejo:** No saltes bloques. Cada bloque asume que dominas los anteriores.

---

## Ficheros temporales

Muchos ejercicios crean ficheros en una carpeta `temp/` dentro del proyecto.
Esta carpeta se crea automaticamente. Puedes borrarla cuando quieras:

```bash
# Windows
rmdir /s /q temp

# Linux/Mac
rm -rf temp
```

---

## Solucion de problemas frecuentes

| Problema | Causa probable | Solucion |
|----------|---------------|----------|
| `BUILD FAILURE` al compilar | Error de sintaxis al editar un esqueleto | Revisa que no hayas borrado los `return` por defecto ni las llaves de cierre |
| `ClassNotFoundException` al ejecutar main | No compilaste antes | Ejecuta `mvn compile` primero |
| Tests fallan con `NullPointerException` | El metodo aun devuelve `null` (no implementado) | Implementa el TODO correspondiente |
| Tests fallan con `UnsupportedOperationException` | El metodo lanza la excepcion por defecto | Implementa el TODO correspondiente |
| `FileNotFoundException` en tests | Falta el fichero temporal | Asegurate de ejecutar los ejercicios de escritura antes que los de lectura, o revisa las rutas |
| `java.io.NotSerializableException` | Tu clase no implementa `Serializable` | Anade `implements Serializable` a la clase |
| Encoding raro en consola Windows | La consola no usa UTF-8 | Ejecuta `chcp 65001` antes de correr los comandos |
| `mvn: command not found` | Maven no esta en el PATH | Instala Maven y anade su `bin/` al PATH del sistema |
| Tests de NIO.2 fallan en permisos | El directorio temporal no tiene permisos | Ejecuta como administrador o usa otra ruta |
