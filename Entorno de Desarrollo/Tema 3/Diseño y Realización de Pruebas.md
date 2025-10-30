# Diseño y Realización de Pruebas

### Objetivo de las Pruebas

Durante el proceso de desarrollo de software, es común incurrir en errores de especificación, diseño o codificación. Por ello, es necesario realizar un conjunto de pruebas para comprobar que el desarrollo es correcto y cumple con los requisitos del usuario.

Los objetivos principales de las pruebas son:

*   **Verificación:** Comprobar que el sistema se está construyendo correctamente, cumpliendo con las condiciones impuestas en cada fase.
*   **Validación:** Evaluar el sistema para determinar si satisface los requisitos especificados y las necesidades del usuario final.

### Características y Ventajas de las Pruebas

Un buen conjunto de pruebas debe cumplir con las siguientes características:
*   **Automatizable:** Su ejecución no debe requerir intervención manual.
*   **Completas:** Deben cubrir la mayor cantidad de código posible.
*   **Repetibles o Reutilizables:** Deben poder ejecutarse múltiples veces.
*   **Independientes:** La ejecución de una prueba no debe afectar a las demás.
*   **Profesionales:** Deben ser tratadas con la misma rigurosidad que el código (documentación, calidad, etc.).

Realizar pruebas aporta ventajas significativas:
*   **Fomentan el cambio:** Facilitan la refactorización y mejora del código al asegurar que los cambios no introducen errores.
*   **Simplifican la integración:** Aumentan la seguridad de que las distintas partes del código funcionan correctamente en conjunto.
*   **Documentan el código:** Los casos de prueba sirven como ejemplos de uso del código.
*   **Separan la interfaz de la implementación:** Permiten modificar la implementación interna sin afectar a las pruebas que validan la interfaz.

### Metodologías de Prueba

Existen tres enfoques principales para diseñar pruebas:

*   **Pruebas de Caja Negra (Funcionales):** Se centran en la interfaz externa del software, sin conocer la implementación interna. Se comprueba que para una entrada de datos específica, la salida obtenida es la correcta.
*   **Pruebas de Caja Blanca (Estructurales):** Se basan en el conocimiento del código para analizar y probar la estructura interna, buscando lógicas incorrectas, código no utilizado o rutas ineficientes.
*   **Enfoque Aleatorio:** Utiliza modelos para simular y generar automáticamente casos de prueba basados en las posibles entradas que el programa recibirá.

### Tipos de Pruebas

A lo largo del ciclo de vida, se realizan diferentes tipos de pruebas:

*   **Pruebas Unitarias:** Verifican el correcto funcionamiento de las porciones más pequeñas de código (funciones o métodos).
*   **Pruebas de Integración:** Comprueban que las diferentes porciones de código funcionan correctamente cuando se combinan.
*   **Pruebas de Validación:** Aseguran que el sistema cumple con los requisitos de software definidos. Implican activamente al cliente y utilizan casos de prueba de caja negra.
*   **Pruebas de Regresión:** Se realizan después de una modificación para asegurar que los cambios no han introducido nuevos errores en otras partes del sistema.
*   **Pruebas del Sistema:** Verifican el funcionamiento del software completo y su interacción con otros elementos del sistema.

### Pruebas de Caja Blanca

El objetivo de las pruebas de caja blanca es verificar la estructura interna del código para asegurar que todas las instrucciones se ejecutan y que todos los caminos lógicos se recorren. Se conocen comúnmente como **pruebas de cobertura de código**.

#### Criterios de Cobertura
*   **Cobertura de Sentencias:** Cada instrucción se ejecuta al menos una vez.
*   **Cobertura de Decisiones:** Cada resultado posible de una comprobación lógica (verdadero y falso) se prueba.
*   **Cobertura de Condiciones:** Cada elemento de una condición se evalúa como verdadero y falso.
*   **Cobertura de Camino:** Se prueba cada bucle en tres escenarios: sin entrar, con una sola iteración y con al menos dos.

#### Técnica de Diseño y Complejidad Ciclomática
Para garantizar la cobertura se sigue una técnica que implica:
1.  **Crear un grafo de flujo de control** que represente el código.
2.  **Calcular la complejidad ciclomática**, que determina el número de caminos independientes en el código. Se puede calcular de tres formas:
    *   `Nº de arcos - Nº de nodos + 2`
    *   `Nº de regiones cerradas del grafo + 1`
    *   `Nº de nodos de condición + 1`
3.  **Determinar los caminos de prueba**, cuyo número será igual a la complejidad ciclomática.
4.  **Definir un caso de prueba** para cada camino, especificando las entradas y los resultados esperados.
5.  **Ejecutar las pruebas** y comparar los resultados obtenidos con los esperados.

#### Ejemplo Práctico: Serie de Fibonacci
Para un programa que calcula la serie de Fibonacci, se realizó el siguiente análisis de caja blanca:
*   Se dibujó el grafo de flujo de control a partir del código.
*   Se calculó la **complejidad ciclomática**, obteniendo un valor de **6**.
*   Se identificaron **6 caminos de prueba** distintos para recorrer el grafo.
*   Se crearon **6 casos de prueba** para validar cada camino, como se muestra en la tabla:

| Caso de prueba | Valor 'salir' | Valor "cantidad" | Resultado esperado |
| :--- | :--- | :--- | :--- |
| Camino 1 | "S" | No aplica | Fin del programa |
| Camino 2 | "s" | No aplica | Fin del programa |
| Camino 3 | Distinto de "S" y "s" | 4 | No muestra resultado |
| Camino 4 | Distinto de "S" y "s" | 1 | Imprime en pantalla "0" |
| Camino 5 | Distinto de "S" y "s" | 2 | Imprime en pantalla "0 1" |
| Camino 6 | Distinto de "S" y "s" | 3 | Imprime en pantalla "0 1 1" |

### Documentación y Estándares

La fase de pruebas, como cualquier otra del ciclo de desarrollo, debe ser documentada. La documentación incluye:
*   **Plan de pruebas:** Planificación general de las pruebas.
*   **Diseño de pruebas:** Detalle de las pruebas a realizar por cada bloque.
*   **Casos de prueba:** Definición de las pruebas de caja negra y blanca.
*   **Procedimientos de prueba:** Instrucciones de ejecución y resultados esperados.
*   **Registro de pruebas:** Histórico de las pruebas ejecutadas.
*   **Informes de incidencias:** Informes detallados de los defectos encontrados.

Existen normativas y estándares para guiar el proceso de pruebas, como **Métrica v3**, **BSI**, **IEEE (829, 1008)** y la norma **ISO/IEC 29119**, que busca unificar los estándares de pruebas de software.

### Recursos Adicionales
*   [Vídeo: Diseño y realización de pruebas](https://aulavirtual3.educa.madrid.org/ies.alonsodeavellan.alcala/pluginfile.php/246691/mod_resource/content/1/3.%20Disen%CC%83o%20y%20realizacio%CC%81n%20de%20pruebas.mp4)
*   [Vídeo: Pruebas de caja blanca](https://aulavirtual3.educa.madrid.org/ies.alonsodeavellan.alcala/pluginfile.php/246692/mod_resource/content/1/3.1.%20Pruebas%20de%20caja%20blanca.mp4)