# Proceso de Desarrollo de Software

### Ciclo de Vida del Software

El ciclo de vida del software es el conjunto de etapas por las que pasa el proceso de desarrollo, desde la concepción de la idea hasta que el software está listo para ser utilizado por el usuario. Aunque existen varios modelos de ciclo de vida, todos comparten una serie de etapas comunes.

### Modelo en Cascada o Tradicional

Es un modelo secuencial donde cada fase del proyecto debe completarse antes de pasar a la siguiente. Es útil para proyectos con requisitos claros y estables, pero su principal desventaja es la falta de flexibilidad, ya que no permite ajustes y los errores se detectan en las etapas finales.

**Fases del Modelo en Cascada:**

1.  **Análisis:** Se definen los requisitos del software, generalmente a través de entrevistas con el cliente. El resultado es un informe de **Especificación de Requisitos del Sistema (ERS)**, acompañado de un diagrama de clases o de entidad/relación.
2.  **Diseño:** Se determina el funcionamiento general del sistema y los recursos necesarios. El resultado se documenta en diagramas de casos de uso y de secuencia, definiendo el **cuaderno de carga**.
3.  **Codificación:** Se realiza la implementación del programa, traduciendo el cuaderno de carga al lenguaje de programación elegido. En esta fase pueden detectarse incoherencias o falta de información de las etapas anteriores.
4.  **Pruebas:** Se confirma que la codificación ha sido exitosa, que el software está libre de errores y que cumple con los requisitos definidos.
5.  **Documentación:** Se elabora tanto el manual de usuario como la documentación técnica para el equipo de desarrollo.
6.  **Explotación:** Se prepara el software para su distribución e instalación en el sistema del cliente.
7.  **Mantenimiento:** Se corrigen fallos (bugs) y se realizan ampliaciones para cubrir nuevas necesidades detectadas tras la implantación.

### Modelos Iterativos y Evolutivos

Estos modelos se basan en la idea de desarrollar el software a través de repetidos ciclos (iteraciones).

*   **Modelo de Prototipado:** Consiste en crear un prototipo temprano del sistema para obtener retroalimentación del usuario. Este feedback se utiliza para refinar el diseño de forma iterativa hasta que sea aceptable.
*   **Modelo Incremental:** El software se desarrolla en pequeñas partes funcionales o incrementos. Cada incremento añade nuevas funcionalidades al sistema, permitiendo entregas tempranas y funcionales.
*   **Modelo en Espiral:** Combina aspectos de los modelos iterativos y lineales, con un fuerte énfasis en el **análisis y control de riesgos** en cada ciclo de la espiral.

### Modelos Ágiles

Se basan en la entrega continua de software funcional en ciclos cortos (iteraciones), priorizando la colaboración con el cliente, la respuesta al cambio y el trabajo en equipo.

*   **Scrum:** Se basa en ciclos de trabajo cortos llamados **'sprints'**. Los equipos se reúnen diariamente para coordinar el progreso, identificar impedimentos y planificar el trabajo.
*   **Kanban:** Utiliza tableros visuales para gestionar el flujo de trabajo. Se enfoca en la eficiencia, la mejora continua del proceso y la limitación del trabajo en curso (WIP).
*   **Programación Extrema (XP):** Se centra en mejorar la calidad del software a través de un conjunto de buenas prácticas, como el desarrollo guiado por pruebas (TDD), la programación en parejas y la integración continua.

**Recurso adicional:**
*   **Vídeo Scrum vs Kanban:** [https://www.youtube.com/watch?v=KQ-zxvMyCnk](https://www.youtube.com/watch?v=KQ-zxvMyCnk)

### Actividad: Tabla Comparativa de Modelos de Desarrollo

| Modelo | Ventajas | Inconvenientes |
| :--- | :--- | :--- |
| **Cascada** | - Sencillo y fácil de gestionar.<br>- Fases y entregables bien definidos.<br>- Ideal para proyectos con requisitos estables. | - Inflexible y resistente a cambios.<br>- Los errores se detectan tarde y son costosos.<br>- El software funcional no se ve hasta el final. |
| **Prototipado** | - Retroalimentación temprana del usuario.<br>- Reduce el riesgo de no cumplir las expectativas.<br>- Mejora la comprensión de los requisitos. | - Puede aumentar la complejidad.<br>- El usuario puede confundir el prototipo con el producto final.<br>- Puede consumir más tiempo y recursos. |
| **Incremental** | - Entrega temprana de software funcional.<br>- Más flexible que el modelo en cascada.<br>- Facilita la detección de errores. | - Requiere una buena planificación inicial.<br>- El coste total puede ser superior al de cascada.<br>- La integración de los incrementos puede ser compleja. |
| **Espiral** | - Fuerte enfoque en el análisis de riesgos.<br>- Bueno para proyectos grandes y complejos.<br>- Permite cambios y adaptaciones. | - Es un modelo complejo y costoso de gestionar.<br>- El análisis de riesgos requiere personal experto.<br>- No es adecuado para proyectos pequeños. |
| **Scrum** | - Alta adaptabilidad a los cambios.<br>- Fomenta la colaboración y la comunicación.<br>- Transparencia total sobre el progreso del proyecto. | - Requiere un equipo experimentado y autogestionado.<br>- Riesgo de "scope creep" (aumento del alcance).<br>- Puede ser difícil de escalar a grandes proyectos. |
| **Kanban** | - Flexible y enfocado en el flujo continuo.<br>- Mejora la eficiencia y reduce el desperdicio.<br>- Visualización clara del trabajo. | - Puede ser demasiado simple para proyectos complejos.<br>- La falta de plazos fijos puede ser un problema.<br>- No es un modelo de desarrollo en sí mismo. |
| **P. Extrema (XP)** | - Produce software de alta calidad.<br>- Alta satisfacción del cliente.<br>- Mejora la colaboración y productividad del equipo. | - Requiere una gran disciplina del equipo.<br>- Puede ser difícil de implementar en grandes empresas.<br>- Fuerte dependencia de la comunicación directa. |