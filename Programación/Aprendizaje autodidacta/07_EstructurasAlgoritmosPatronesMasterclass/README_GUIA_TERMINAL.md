# 🏗️ Masterclass: Estructuras de Datos, Algoritmos y Patrones de Diseño (GoF)

## Guía de Arranque desde Terminal (Gradle)

¡Bienvenido al proyecto más ambicioso y completo del bootcamp autodidacta!

Este ecosistema contiene **100 ejercicios** distribuidos en **8 módulos** que te llevarán desde los cimientos de las estructuras de datos hasta la maestría absoluta en los 23 Patrones de Diseño del Gang of Four, pasando por algoritmos de ordenamiento/búsqueda y análisis de complejidad algorítmica (Big O).

El proyecto se gestiona con **Gradle**, el sistema de construcción moderno de Java. Esto significa que puedes compilar y ejecutar cada uno de los 100 ejercicios de forma aislada y pura desde cualquier terminal, sin ningún IDE.

---

## 📋 Sílabo Completo (Hoja de Ruta - 100 Ejercicios)

| Módulo | Ejercicios | Temática |
|--------|-----------|----------|
| **1** | 01 – 15 | Fundamentos, Memoria y Estructuras Lineales (Arrays dinámicos, LinkedLists, Stacks, Queues) |
| **2** | 16 – 30 | Algoritmos Base, Rendimiento y Punteros (Búsqueda, Sorting, Two Pointers, Sliding Window, Big O) |
| **3** | 31 – 45 | Estructuras Avanzadas y Grafos (Árboles binarios, HashMaps, Heaps, Grafos, BFS/DFS) |
| **4** | 46 – 60 | Patrones de Diseño Creacionales (Singleton, Factory Method, Abstract Factory, Builder, Prototype) |
| **5** | 61 – 75 | Patrones de Diseño Estructurales (Adapter, Bridge, Composite, Decorator, Facade, Flyweight, Proxy) |
| **6** | 76 – 85 | Patrones de Comportamiento - Parte 1 (Chain of Responsibility, Command, Interpreter, Iterator, Mediator) |
| **7** | 86 – 99 | Patrones de Comportamiento - Parte 2 (Memento, Observer, State, Strategy, Template Method, Visitor) |
| **8** | 100 | 🏆 El Gran Boss Final — Sistema corporativo de alto rendimiento |

---

## 📁 Arquitectura del Proyecto

```
07_EstructurasAlgoritmosPatronesMasterclass/
│
├── build.gradle                    ← Configuración de Gradle (JDK 17+)
├── settings.gradle                 ← Nombre del proyecto raíz
├── README_GUIA_TERMINAL.md         ← 📖 ESTE ARCHIVO (Estás aquí)
│
├── teoria/                         ← 📚 Documentos Markdown con teoría + diagramas Mermaid
│   ├── 01_Fundamentos_Estructuras_Lineales.md
│   ├── 02_Algoritmos_Rendimiento_BigO.md
│   ├── 03_Estructuras_Avanzadas_Grafos.md
│   ├── 04_Patrones_Creacionales.md
│   ├── 05_Patrones_Estructurales.md
│   ├── 06_Patrones_Comportamiento_P1.md
│   ├── 07_Patrones_Comportamiento_P2.md
│   └── 08_Boss_Final.md
│
└── src/main/java/                  ← 💻 Código fuente Java (ejercicios)
    ├── modulo1_estructuras_lineales/
    │   ├── Ejercicio01_ArrayDinamicoManual.java
    │   ├── Ejercicio02_...java
    │   └── ...hasta Ejercicio15
    │
    ├── modulo2_algoritmos_rendimiento/
    │   ├── Ejercicio16_...java
    │   └── ...hasta Ejercicio30
    │
    ├── modulo3_estructuras_avanzadas/
    │   ├── Ejercicio31_...java
    │   └── ...hasta Ejercicio45
    │
    ├── modulo4_patrones_creacionales/
    │   ├── Ejercicio46_...java
    │   └── ...hasta Ejercicio60
    │
    ├── modulo5_patrones_estructurales/
    │   ├── Ejercicio61_...java
    │   └── ...hasta Ejercicio75
    │
    ├── modulo6_patrones_comportamiento_p1/
    │   ├── Ejercicio76_...java
    │   └── ...hasta Ejercicio85
    │
    ├── modulo7_patrones_comportamiento_p2/
    │   ├── Ejercicio86_...java
    │   └── ...hasta Ejercicio99
    │
    └── modulo8_boss_final/
        └── Ejercicio100_SistemaCorporativoIntegral.java
```

---

## ⚙️ Requisitos Previos

Antes de arrancar, verifica que tienes instalado:

### 1. Java Development Kit (JDK 17 o superior)
```bash
java -version
```
> Deberías ver algo como: `openjdk version "17.0.x"` o superior.

### 2. Gradle (Opcional si usas el Wrapper)
```bash
gradle --version
```
> Si no lo tienes instalado globalmente, no te preocupes. Este proyecto incluirá un **Gradle Wrapper** (`gradlew`) que descarga Gradle automáticamente.

---

## 🛠️ 1. Instalación y Compilación del Proyecto

### Opción A: Con Gradle instalado globalmente

Abre tu terminal (CMD, PowerShell, Git Bash) y navega a la raíz del proyecto:

```bash
cd "c:\Users\obitica\Java\DAM-2025-26\Programación\Aprendizaje autodidacta\07_EstructurasAlgoritmosPatronesMasterclass"
```

Compila todo el ecosistema de 100 ejercicios:

```bash
gradle clean build
```

> Deberías ver al final: **BUILD SUCCESSFUL**. Esto ha compilado los 100 archivos `.java` a bytecode `.class` dentro de `build/classes/`.

### Opción B: Con el Gradle Wrapper (sin instalación global)

Si no tienes Gradle instalado, genera el wrapper primero (requiere tenerlo temporalmente o descargarlo):

```bash
gradle wrapper --gradle-version 8.7
```

A partir de aquí, usa `gradlew` (o `gradlew.bat` en Windows) en lugar de `gradle`:

```bash
.\gradlew clean build
```

---

## 🔄 2. Recompilar tras editar un ejercicio

Si editas un archivo `.java`, necesitas recompilarlo. No hace falta recompilar todo:

```bash
gradle compileJava
```

O con el wrapper:

```bash
.\gradlew compileJava
```

---

## ▶️ 3. Ejecutar un Ejercicio Concreto

Este es el corazón de la guía. Cada uno de los 100 ejercicios tiene su propio `public static void main(String[] args)`, por lo que puedes ejecutarlos de forma completamente aislada.

Se ha configurado una **tarea personalizada** en el `build.gradle` llamada `runExercise` que recibe como parámetro la clase a ejecutar:

### Sintaxis General

```bash
gradle runExercise -PmainClass="<paquete>.<NombreClase>"
```

### Ejemplos Prácticos

> **Ejecutar el Ejercicio 1 (Array Dinámico Manual)**
```bash
gradle runExercise -PmainClass="modulo1_estructuras_lineales.Ejercicio01_ArrayDinamicoManual"
```

> **Ejecutar el Ejercicio 16 (Búsqueda Binaria)**
```bash
gradle runExercise -PmainClass="modulo2_algoritmos_rendimiento.Ejercicio16_BusquedaBinaria"
```

> **Ejecutar el Ejercicio 46 (Singleton Seguro)**
```bash
gradle runExercise -PmainClass="modulo4_patrones_creacionales.Ejercicio46_SingletonSeguro"
```

> **Ejecutar el Ejercicio 61 (Adapter Clásico)**
```bash
gradle runExercise -PmainClass="modulo5_patrones_estructurales.Ejercicio61_AdapterClasico"
```

> **Ejecutar el Ejercicio 100 (El Gran Boss Final)**
```bash
gradle runExercise -PmainClass="modulo8_boss_final.Ejercicio100_SistemaCorporativoIntegral"
```

### 🔑 La Clave Universal

Solo debes sustituir lo que hay entre comillas por el **nombre del paquete** + **punto** + **nombre de la clase** (sin la extensión `.java`).

Puedes localizar estos datos mirando la primera línea de cada archivo de ejercicio (`package modulo1_...;`) y el nombre del archivo.

---

## 📚 4. Dónde está la Teoría

Toda la documentación teórica con diagramas **Mermaid** (UML, Flowcharts, Sequence Diagrams...) se encuentra en la carpeta `teoria/`:

```bash
teoria/
├── 01_Fundamentos_Estructuras_Lineales.md   → Módulo 1 (Ej 01-15)
├── 02_Algoritmos_Rendimiento_BigO.md        → Módulo 2 (Ej 16-30)
├── 03_Estructuras_Avanzadas_Grafos.md       → Módulo 3 (Ej 31-45)
├── 04_Patrones_Creacionales.md              → Módulo 4 (Ej 46-60)
├── 05_Patrones_Estructurales.md             → Módulo 5 (Ej 61-75)
├── 06_Patrones_Comportamiento_P1.md         → Módulo 6 (Ej 76-85)
├── 07_Patrones_Comportamiento_P2.md         → Módulo 7 (Ej 86-99)
└── 08_Boss_Final.md                         → Módulo 8 (Ej 100)
```

> **📌 Regla de Oro**: Antes de abrir un ejercicio de código, **lee primero** el documento de teoría homónimo. El código referencia la teoría y la teoría prepara el camino del código.

---

## 🧭 5. Flujo de Trabajo Recomendado

```
1. Lee el documento de teoría del módulo actual (teoria/0X_...md)
2. Abre el esqueleto del ejercicio (.java)
3. Resuelve los bloques // TODO: siguiendo las especificaciones
4. Compila:    gradle compileJava
5. Ejecuta:    gradle runExercise -PmainClass="paquete.Clase"
6. Depura y refina hasta que el output sea correcto
7. Avanza al siguiente ejercicio
```

---

## ❓ Solución de Problemas Comunes

| Problema | Solución |
|----------|----------|
| `Could not find or load main class` | Verifica que el nombre del paquete y la clase están escritos correctamente, respetando mayúsculas/minúsculas. |
| `Unsupported class file major version` | Tu JDK es anterior al 17. Actualiza tu JDK. |
| `error: unmappable character for encoding` | Ejecuta `gradle compileJava -Dfile.encoding=UTF-8` o verifica que tu archivo está guardado en UTF-8. |
| `BUILD FAILED` en la compilación | Lee el mensaje de error del compilador. Normalmente es un error de sintaxis en el ejercicio que estás editando. |

---

## 🏆 Meta Final

Al completar los 100 ejercicios habrás dominado:

- ✅ Estructuras de datos lineales y no lineales implementadas a mano
- ✅ Algoritmos fundamentales de ordenamiento y búsqueda
- ✅ Análisis de complejidad algorítmica (Big O)
- ✅ Los 23 Patrones de Diseño del Gang of Four aplicados a Java
- ✅ Integración de todos los conceptos en un sistema corporativo de alto rendimiento

**¡No dependes de ningún IDE para ser un ingeniero de software letal! ¡Mucho éxito, arquitecto!** 🚀
