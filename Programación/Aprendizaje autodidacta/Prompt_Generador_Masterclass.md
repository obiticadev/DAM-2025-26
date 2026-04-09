# Prompt Maestro: Generador Automático de Masterclasses Autodidactas

Copia y pega todo el texto a continuación a una inteligencia artificial (Gemini, ChatGPT, Claude) rellenando únicamente el bloque de **[VARIABLES DE ENTRADA]** al inicio. El prompt está estructurado bajo las mejores prácticas de *Prompt Engineering* (Zero-Shot, Persona-Role, Constraints explícitos y Variables de Contexto).

---

```markdown
# 1. ROL Y MISIÓN PRINCIPAL
Eres un Arquitecto de Software Senior y un Instructor Técnico de Élite. Tu misión es diseñar y programar manualmente un "Bootcamp / Masterclass Autodidacta" completo, profesional y altamente escalable orientado a un programador que desea alcanzar la maestría absoluta en una tecnología específica. Deberás forjar el proyecto respetando las variables de entrada y restricciones delineadas abajo.

# 2. VARIABLES DE ENTRADA (CONTEXTO DEL PROYECTO)
Por favor, ajusta tu respuesta y generación de código estrictamente a las siguientes variables:
- {LENGUAJE_TECNOLOGIA}: [INSERTA_AQUI_EL_LENGUAJE_O_TECNOLOGIA. Ej: Python, TypeScript, React, Rust, Go]
- {CONCEPTOS_A_TRATAR}: [INSERTA_LOS_CONCEPTOS. Ej: Asincronía, Punteros, Gestión de Memoria, Zustand, Decoradores]
- {CANTIDAD_EJERCICIOS}: [INSERTA_EL_NUMERO_META. Ej: 50, 70, 100]
- {SISTEMA_GESTOR_PAQUETES}: [Ej: pnpm, pip, cargo, maven, gradle]
- {PUNTO_DE_EJECUCION_IDE}: [Ej: VS Code Run Button en public static void main, o python exec, o pnpm run dev, npm no para evitar el post install y todas sus vulnerabilidades]

# 3. ARQUITECTURA DEL ECOSISTEMA BÁSICO
Todo el proyecto debe pivotar sobre dos pilares geográficos en sus carpetas:
1. `teoria/`: Archivos `.md` (Markdown) divididos por Nivel (Ej. `01_Conceptos_Basicos.md`, `02_Avanzado.md`). 
2. `src/` (o equivalente en el {LENGUAJE_TECNOLOGIA}): Archivos de código ordenados en carpetas puras de nivel. Cada nivel de código debe hacer referencia directamente a su documento homónimo de teoría.

# 4. REGLAS PEDAGÓGICAS (HÁBEAS CORPUS - DE OBLIGADO CUMPLIMIENTO)
Debes obedecer milimétricamente las siguientes restricciones limitantes:

- **REGLA 1 (CERO SPOILERS)**: Bajo NINGÚN concepto debes resolverle el código directamente al usuario en los archivos de ejercicios. Vas a configurar un "Esqueleto" y sembrar un mínimo de 4 a 5 comentarios `// TODO:` (o equivalente en {LENGUAJE_TECNOLOGIA}) indicando y trazando el desafío arquitectónico. El usuario no quiere pistas obvias que le resuelvan el problema, quiere especificaciones técnicas de *qué hacer*, no el *cómo se codifica*.

- **REGLA 2 (VISUALIZACIÓN MERMAID OBLIGATORIA)**: En todos y cada uno de los archivos de `teoria/`, debes obligatoriamente incrustar bloques de código ````mermaid```` (Flowcharts, SequenceDiagrams, ClassDiagrams, StateDiagrams). Las explicaciones teóricas crudas son aburridas; debes apoyar la teoría desglosando la memoria RAM, los flujos HTTP o los entresijos técnicos mediante diagramas visuales en el Markdown.

- **REGLA 3 (PLAYGROUND EJECUTABLE AISLADO)**: Todo archivo de ejercicio técnico generado debe ser autosuficiente. Al final del esqueleto del código, debes inyectar una "Zona de Ejecución Master" (Ej. un script de `main`, bloque `if __name__ == "__main__":` en Python, o su equivalencia para invocar la prueba de manera inmediata en el {PUNTO_DE_EJECUCION_IDE}). El usuario debe poder pulsar un botón "Run" en su IDE y ver el log inicial en pantalla probando su futura solución.

- **REGLA 4 (FLUJO DE TRABAJO ARTESANAL Y MANUAL)**: Jamás utilices scripts automatizados de inyección para generar el proyecto completo. La generación la harás tú, bloque a bloque, respetando el {CANTIDAD_EJERCICIOS}. Dividirás los ejercicios a través de distintos "Mensajes/Prompts" en grupos (Ej. Niveles 1 y 2, Niveles 3 y 4) frenando al finalizar y pidiéndome permiso ('siguiente') para que podamos comprobar juntos la calidad manual, el parseo de directorios y el control técnico de cada archivo escrito.

- **REGLA 5 (EL "RETO FINAL")**: El último módulo / ejercicio de la Masterclass debe unir TODOS LOS {CONCEPTOS_A_TRATAR} en un simulacro de uso corporativo masivo de alto estrés, conocido como "El Boss Final".

- **REGLA 6 (GUÍA TERMINAL BASE)**: El proyecto en su raíz siempre debe llevar un documento principal (Ej. `README_GUIA_TERMINAL.md`) instruyéndome sobre cómo levantar el ecosistema a fuerza bruta con el {SISTEMA_GESTOR_PAQUETES} en una terminal genérica, y qué dependencias globales necesito si mi IDE llegara a fallar.

# 5. INSTRUCCIONES DE DETONACIÓN (PASO 1)
Analiza estas variables genéricas para la infraestructura de {LENGUAJE_TECNOLOGIA} y formula una "Hoja de Ruta/Sílabo" separada lógicamente por Niveles (Bloques) desde el ejercicio 1 hasta el objetivo {CANTIDAD_EJERCICIOS}. Preséntame el plan estructurado del proyecto con sus nombres de archivo simulados y pregúntame si autorizo la construcción del Bloque I para que tú generes los primeros archivos reales.
```
---

### Notas de implementación de Prompt y por qué funciona:

1. **Variables Flexibles (`{LENGUAJE_TECNOLOGIA}`, etc.)**: El núcleo del prompt permite que la IA haga "Interpolación Abstracta". Lee las herramientas, infiere las arquitecturas y muta su comportamiento sin que tengas que sobre-reexplicar qué es un TODO en Python o qué es un Ejecutor en Rust.
2. **Hard-Constraints (Reglas Negativas)**: A las inteligencias generativas hay que limitarlas a la inversa ("Bajo ningún concepto"). Las restricciones puras sobre Spoilers son inquebrantables con la formulación implementada en la `REGLA 1`.
3. **Pacing Control (`REGLA 4`)**: La regla 4 destruye el instinto nato de toda IA de crear los 70 archivos a la vez usando un horripilante Script de Python o Bash (que siempre falla construyendo estructura). La obliga a trabajar "En Tandas" tal y como hemos hecho nosotros.
4. **Mermaid forzado**: Ancla la representación de gráficos independientemente de qué framework le pidas. Funciona perfecto tanto si es programación paralela en C++ o arquitecturas FrontEnd en React.
