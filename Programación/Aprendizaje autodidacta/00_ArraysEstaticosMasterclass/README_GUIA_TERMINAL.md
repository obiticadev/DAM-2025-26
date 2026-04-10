# 🧪 Arrays Estáticos Masterclass — Guía Terminal

**Bootcamp Autodidacta · 50 Ejercicios · Java 25 · Maven · JUnit 5**

---

## 📋 Requisitos Previos

| Herramienta | Versión Mínima | Verificar con |
|---|---|---|
| Java JDK | 25 | `java --version` |
| Apache Maven | 3.9+ | `mvn --version` |

---

## 🏗️ Estructura del Proyecto

```
00_ArraysEstaticosMasterclass/
│
├── pom.xml                          ← Configuración Maven
├── README_GUIA_TERMINAL.md          ← Este archivo
│
├── teoria/                          ← Documentos teóricos (Markdown + Mermaid)
│   ├── 01_Fundamentos_Arrays.md
│   ├── 02_Redimensionado_Y_Copia.md
│   ├── ...
│   └── 10_Boss_Final_Gestor_Datos_En_Memoria.md
│
├── src/main/java/com/masterclass/arrays/
│   ├── nivel01/                     ← Ejercicios 1–5:  Fundamentos
│   ├── nivel02/                     ← Ejercicios 6–10: Redimensionado y Copia
│   ├── nivel03/                     ← Ejercicios 11–16: Ordenación
│   ├── nivel04/                     ← Ejercicios 17–20: Búsqueda
│   ├── nivel05/                     ← Ejercicios 21–26: Arrays 2D
│   ├── nivel06/                     ← Ejercicios 27–32: Transformaciones de Matrices
│   ├── nivel07/                     ← Ejercicios 33–36: Arrays 3D
│   ├── nivel08/                     ← Ejercicios 37–43: Simulación de Estructuras
│   ├── nivel09/                     ← Ejercicios 44–49: Patrones Avanzados
│   └── nivel10/                     ← Ejercicio 50:    BOSS FINAL
│
└── src/test/java/com/masterclass/arrays/
    ├── nivel01/                     ← Tests correspondientes
    ├── ...
    └── nivel10/
```

---

## 🔨 Compilar el Proyecto

```bash
# Compilar todo el proyecto (sin ejecutar tests)
mvn clean compile

# Compilar incluyendo los tests
mvn clean test-compile
```

---

## ▶️ Ejecutar un Ejercicio Individual

Cada archivo de ejercicio tiene su propio `public static void main(String[] args)` para que
puedas ejecutar y comprobar visualmente tu progreso.

### Desde el IDE (IntelliJ IDEA / VS Code / Eclipse)
1. Abre el archivo del ejercicio (ej. `Ej01_DeclaracionInicializacion.java`)
2. Pulsa el botón **Run ▶** junto al método `main`

### Desde la Terminal con Maven
```bash
# Ejecutar un ejercicio concreto
mvn exec:java -Dexec.mainClass="com.masterclass.arrays.nivel01.Ej01_DeclaracionInicializacion"

# Otro ejemplo (nivel 02)
mvn exec:java -Dexec.mainClass="com.masterclass.arrays.nivel02.Ej06_CopiaManual"
```

---

## 🧪 Ejecutar Tests

### Ejecutar TODOS los tests del bootcamp
```bash
mvn clean test
```

### Ejecutar tests de UN nivel completo
```bash
# Todos los tests del Nivel 01
mvn test -Dtest="com.masterclass.arrays.nivel01.*"

# Todos los tests del Nivel 02
mvn test -Dtest="com.masterclass.arrays.nivel02.*"
```

### Ejecutar UN test específico
```bash
# Test de un ejercicio concreto
mvn test -Dtest="com.masterclass.arrays.nivel01.Ej01_DeclaracionInicializacionTest"
```

### Ejecutar UN método de test específico
```bash
mvn test -Dtest="com.masterclass.arrays.nivel01.Ej01_DeclaracionInicializacionTest#deberiaCrearArrayEnterosConTamanioCorrecto"
```

---

## 🎯 Flujo de Trabajo Recomendado

```
1. Lee la TEORÍA del nivel correspondiente en teoria/
2. Abre el archivo del EJERCICIO y lee TODOS los // TODO:
3. Implementa cada TODO paso a paso
4. Pulsa RUN en el main() para comprobar visualmente
5. Ejecuta el TEST correspondiente para validar:
     mvn test -Dtest="<NombreDelTest>"
6. ✅ El ejercicio se considera COMPLETADO cuando TODOS los tests pasan en VERDE
7. Avanza al siguiente ejercicio
```

---

## ⚠️ Reglas del Bootcamp

> **PROHIBIDO** usar `java.util.ArrayList`, `java.util.HashMap`, `java.util.Collections`,
> `java.util.Arrays.sort()`, `java.util.Arrays.stream()` o cualquier clase de la API de
> Collections / Streams.
>
> Todo se construye **manualmente** con arrays primitivos y de objetos.

---

## 🔍 Diagnóstico Rápido

| Problema | Solución |
|---|---|
| `mvn: command not found` | Instalar Maven y añadir al PATH |
| `java.lang.UnsupportedClassVersionError` | Actualizar JDK a versión 25+ |
| Tests fallan con `NullPointerException` | Normal: los TODOs devuelven `null`. Implementa el código. |
| `package does not exist` | Ejecutar `mvn clean compile` primero |

---

**¡Buena suerte, guerrero de los arrays!** 🗡️
