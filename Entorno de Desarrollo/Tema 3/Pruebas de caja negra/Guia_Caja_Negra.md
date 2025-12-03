# Guía de Aprendizaje: Pruebas de Caja Negra

## 1. ¿Qué son las Pruebas de Caja Negra?
Las **Pruebas de Caja Negra** (Black Box Testing) son una técnica de pruebas de software en la cual se examina la funcionalidad de una aplicación **sin observar su estructura interna o código fuente**.

Imagina que el sistema es una "caja negra" cerrada:
- **Entrada**: Introduces datos.
- **Caja**: El sistema procesa los datos (no ves cómo).
- **Salida**: Observas el resultado.

**Objetivo**: Validar que el software hace lo que se supone que debe hacer según los requisitos.

---

## 2. Fases y Pasos Principales
Para realizar estas pruebas de forma efectiva, sigue estos pasos:

1.  **Análisis de Requisitos**: Entender qué debe hacer el sistema.
2.  **Selección de Técnica**: Elegir la mejor estrategia (ver sección 3).
3.  **Diseño de Casos de Prueba**: Definir las entradas y las salidas esperadas.
4.  **Ejecución**: Probar el sistema con los casos diseñados.
5.  **Comparación**: Verificar si el resultado obtenido coincide con el esperado.

---

## 3. Técnicas Principales

### A. Particiones de Equivalencia
Divide los datos de entrada en grupos (clases) que se espera que el sistema trate de la misma manera.
- **Idea**: Si funciona para un dato del grupo, debería funcionar para todos los del mismo grupo.
- **Uso**: Reduce el número de pruebas necesarias.

### B. Análisis de Valores Límite
Se centra en probar los valores en los extremos (bordes) de las clases de equivalencia.
- **Idea**: Los errores suelen ocurrir en los límites (ej. > 18, < 18).
- **Uso**: Complemento perfecto para las particiones de equivalencia.

### C. Tablas de Decisión
Se usa cuando el resultado depende de una combinación de condiciones lógicas.
- **Idea**: Mapear todas las combinaciones posibles de entradas (Verdadero/Falso) y sus acciones.

---

## 4. Ejemplo Práctico Resuelto

**Escenario**: Un sistema de validación de edad para entrar a un club.
- **Requisito**:
    - Si la edad es **menor de 18**, acceso **denegado**.
    - Si la edad está entre **18 y 65** (inclusive), acceso **permitido**.
    - Si la edad es **mayor de 65**, acceso **VIP**.
    - Edades negativas o irreales (>120) son **error**.

### Aplicando Particiones de Equivalencia

| Clase | Descripción | Tipo | Dato de Prueba (Ejemplo) | Resultado Esperado |
| :--- | :--- | :--- | :--- | :--- |
| 1 | Edad < 0 | Inválida | -5 | Error |
| 2 | 0 <= Edad < 18 | Válida | 10 | Denegado |
| 3 | 18 <= Edad <= 65 | Válida | 30 | Permitido |
| 4 | 65 < Edad <= 120 | Válida | 70 | VIP |
| 5 | Edad > 120 | Inválida | 150 | Error |

### Aplicando Valores Límite
Probamos los bordes de los rangos definidos (Límite, Límite - 1, Límite + 1).

| Límite a Probar | Dato de Prueba | Resultado Esperado |
| :--- | :--- | :--- |
| Frontera 0 | -1 | Error |
| Frontera 0 | 0 | Denegado |
| Frontera 18 | 17 | Denegado |
| Frontera 18 | 18 | Permitido |
| Frontera 65 | 65 | Permitido |
| Frontera 65 | 66 | VIP |
| Frontera 120 | 120 | VIP |
| Frontera 120 | 121 | Error |

---

## 5. Resumen para el Aprendizaje
1.  **No mires el código**: Céntrate en qué hace el programa, no en cómo.
2.  **Busca los límites**: Ahí es donde suelen esconderse los fallos.
3.  **Agrupa datos**: No pruebes todos los números posibles, prueba uno representativo de cada grupo.
4.  **Documenta**: Un caso de prueba debe tener siempre: *Entrada*, *Resultado Esperado* y *Resultado Obtenido*.
