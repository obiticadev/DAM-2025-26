# 09 — Collections Masterclass
### Bootcamp autodidacta: ArrayList · LinkedList · HashMap · HashSet · instanceof con Interfaces

---

## Requisitos previos

| Herramienta | Versión mínima |
|---|---|
| JDK | 25 |
| Maven | 3.9+ |
| IDE | VS Code + Extension Pack for Java |

---

## Estructura del proyecto

```
09_CollectionsMasterclass/
├── pom.xml                         ← Configuración Maven
├── README_GUIA_TERMINAL.md         ← Este archivo
├── teoria/                         ← Documentos Markdown con teoría y diagramas
│   ├── 01_ArrayList_Fundamentos.md
│   ├── 02_LinkedList_Estructura_y_Deque.md
│   ├── 03_HashMap_Core.md
│   ├── 04_HashMap_Avanzado_LinkedHashMap_TreeMap.md
│   ├── 05_HashSet_y_Conjuntos.md
│   ├── 06_instanceof_Interfaces_Polimorfismo.md
│   ├── 07_Collections_Secundarias_Queue_Deque.md
│   └── 08_Boss_Final_Sistema_Corporativo.md
├── src/main/java/com/masterclass/collections/
│   ├── modelos/                    ← Clases e interfaces de dominio (ya implementadas)
│   ├── nivel01_arraylist_basico/
│   ├── nivel02_arraylist_intermedio/
│   ├── nivel03_linkedlist/
│   ├── nivel04_hashmap_core/
│   ├── nivel05_hashmap_avanzado/
│   ├── nivel06_linkedhashmap_treemap/
│   ├── nivel07_hashset/
│   ├── nivel08_instanceof_basico/
│   ├── nivel09_instanceof_avanzado/
│   ├── nivel10_instanceof_collections/
│   ├── nivel11_collections_secundarias/
│   ├── nivel12_integracion/
│   └── nivel13_boss_final/
└── src/test/java/com/masterclass/collections/
    └── (misma estructura de niveles)
```

---

## Comandos Maven

### Compilar el proyecto
```bash
mvn compile
```

### Ejecutar TODOS los tests
```bash
mvn test
```

### Ejecutar los tests de UN ejercicio específico
```bash
mvn test -Dtest="Ejercicio01_CRUDBasicoTest"
```

### Ejecutar tests de un nivel completo (wildcard)
```bash
mvn test -Dtest="*nivel01*"
```

### Ejecutar solo los tests del Boss Final
```bash
mvn test -Dtest="*BossFinal*"
```

### Ver output detallado de tests
```bash
mvn test -Dtest="Ejercicio01_CRUDBasicoTest" -Dsurefire.useFile=false
```

### Limpiar compilados y volver a compilar
```bash
mvn clean compile
```

### Limpiar y ejecutar todos los tests
```bash
mvn clean test
```

---

## Flujo de trabajo recomendado

```
1. Lee el documento de teoría correspondiente al nivel
         ↓
2. Abre el archivo EjercicioXX.java del nivel
         ↓
3. Lee los comentarios // TODO: de arriba a abajo
         ↓
4. Implementa los métodos indicados
         ↓
5. Pulsa ▶ Run en VS Code para ver tu salida visual en el main()
         ↓
6. Ejecuta los tests con Maven:
   mvn test -Dtest="EjercicioXX_NombreTest"
         ↓
7. Si todos los tests pasan en VERDE → ejercicio completado ✅
         ↓
8. Pasa al siguiente ejercicio
```

---

## Sílabo completo — 50 ejercicios en 13 niveles

| Bloque | Niveles | Ejercicios | Tema principal |
|---|---|---|---|
| **I** | 01–03 | 01–09 | ArrayList (básico, intermedio) + LinkedList |
| **II** | 04–05 | 10–16 | HashMap core y avanzado |
| **III** | 06 | 17–20 | LinkedHashMap y TreeMap |
| **IV** | 07 | 21–25 | HashSet y conjuntos |
| **V** | 08–10 | 26–36 | instanceof con interfaces (básico → colecciones) |
| **VI** | 11 | 37–42 | Collections secundarias (Queue, Deque, utilidades) |
| **VII** | 12 | 43–46 | Integración Collections + Interfaces |
| **VIII** | 13 | 47–50 | **BOSS FINAL** — Sistema Gestor Empresarial |

---

## Reglas del Bootcamp

- **CERO código resuelto**: los ejercicios son esqueletos con `// TODO:`.
- Un ejercicio se considera **completado** cuando sus **tests pasan en verde**.
- El `main()` es solo para comprobación visual, no sustituye a los tests.
- Lee siempre la teoría antes de atacar los ejercicios del nivel.

---

## Ejecución desde VS Code

1. Abre cualquier `EjercicioXX.java`
2. Localiza el método `main()` al final del archivo
3. Haz clic en el botón **▶ Run** que aparece encima del `main`
4. La salida aparece en el terminal integrado

---

## Configuración `.vscode/settings.json` recomendada

```json
{
    "java.configuration.updateBuildConfiguration": "automatic",
    "java.test.defaultConfig": "maven",
    "editor.formatOnSave": true
}
```
