# Diagramas UML de Comportamiento — Material de Estudio Integral (UT07)

## Sobre este documento

Este material está pensado como fuente para el estudio de los diagramas UML de comportamiento incluidos en la Unidad de Trabajo 07 de la asignatura Entorno de Desarrollo del ciclo de Desarrollo de Aplicaciones Multiplataforma. Cubre los cinco diagramas que entran en examen: diagrama de casos de uso, diagrama de actividad, diagrama de estados, diagrama de secuencia y diagrama de comunicación. De los cinco, los cuatro primeros pueden caer tanto en preguntas teóricas como en ejercicios prácticos, mientras que el diagrama de comunicación entra únicamente como contenido teórico, no como ejercicio a dibujar. El contenido está redactado en prosa continua y didáctica para facilitar la generación de cuestionarios de evaluación y la producción de podcasts educativos. Al final del documento se incluye un banco extenso de preguntas con respuestas, organizado por diagrama, que puede emplearse directamente como base para tests de autoevaluación.

---

## Parte 1. Fundamentos de UML y diagramas de comportamiento

UML, acrónimo de Unified Modeling Language o lenguaje unificado de modelado, es el estándar industrial para describir sistemas software orientados a objetos. Su propósito es ofrecer una notación común que permita a analistas, arquitectos, programadores y otros agentes implicados en un proyecto comunicar el diseño del sistema de forma precisa y sin ambigüedades.

Los diagramas de UML se clasifican en dos grandes familias. La primera son los diagramas estructurales, también llamados estáticos, que muestran la arquitectura del sistema, es decir, qué piezas lo componen y cómo se relacionan entre sí. Pertenecen a esta familia los diagramas de clases, de objetos, de componentes, de paquetes, de despliegue, de estructura compuesta y de perfiles. La segunda familia son los diagramas de comportamiento, también llamados dinámicos, que muestran qué hace el sistema y cómo lo hace a lo largo del tiempo. Esta segunda familia es el objeto central de la Unidad de Trabajo 07.

UML 2 define siete tipos de diagramas de comportamiento. El diagrama de casos de uso describe las interacciones entre los usuarios externos del sistema, llamados actores, y las funcionalidades que el sistema ofrece. El diagrama de secuencia muestra el intercambio de mensajes entre objetos a lo largo del tiempo, ordenado verticalmente. El diagrama de comunicación, antes llamado de colaboración, modela esas mismas interacciones pero con énfasis en la estructura de la red de objetos en lugar de en el tiempo. El diagrama de actividad representa flujos de trabajo, algoritmos o procesos de negocio, de forma similar a un diagrama de flujo enriquecido con semántica orientada a objetos. El diagrama de estados, también conocido como state machine diagram o máquina de estados, modela el ciclo de vida de un objeto concreto, mostrando las situaciones por las que pasa y los eventos que provocan los cambios entre ellas. Los dos últimos, el diagrama de interacción general y el diagrama de tiempo, se mencionan únicamente para completar la clasificación, pero quedan fuera del alcance de este temario.

El campo de aplicación de los diagramas de comportamiento es amplio. En la fase de análisis de requisitos resultan imprescindibles los diagramas de casos de uso, porque permiten capturar de forma visual qué funcionalidades espera el cliente. En la fase de diseño se emplean los diagramas de secuencia y de comunicación para detallar cómo colaboran los objetos al implementar cada caso de uso. Los diagramas de actividad cubren tanto el análisis como el diseño, especialmente útiles para modelar la lógica de negocio y los procesos donde intervienen varios participantes. Por último, los diagramas de estados se reservan para el diseño detallado de aquellas clases cuyo comportamiento depende fuertemente de su estado interno, como pedidos, cuentas bancarias, documentos en revisión o conexiones de red.

Una regla mnemotécnica útil para no confundir las familias es la siguiente. Si la pregunta del examen apunta a la arquitectura, al esqueleto, a qué piezas existen y cómo se conectan estructuralmente, entonces se trata de un diagrama estructural. Si la pregunta apunta al tiempo, al flujo, al proceso, al ciclo de vida o a las interacciones, entonces se trata de un diagrama de comportamiento. El diagrama de clases, por ejemplo, es estructural y no se considera de comportamiento aunque a veces se incluya en preguntas trampa de los tests.

---

## Parte 2. El diagrama de casos de uso

El diagrama de casos de uso constituye el punto de entrada del análisis orientado a objetos. Su misión consiste en capturar los requisitos funcionales del sistema desde la perspectiva del usuario, definiendo lo que en literatura se denomina el contrato funcional. Este diagrama responde a la pregunta de qué hace el sistema y quién lo utiliza, pero deliberadamente evita responder a la pregunta de cómo se implementan las funcionalidades. Esa cuestión queda para fases posteriores del desarrollo.

### Elementos del diagrama

El primer elemento fundamental es el actor. Un actor representa cualquier entidad externa que interactúa con el sistema para conseguir un objetivo. Los actores viven fuera del sistema y se representan gráficamente mediante una figura humana esquemática, llamada en inglés stick figure, con el nombre del actor escrito debajo. Existen distintas clases de actores. Los actores humanos representan personas que utilizan el sistema, como un cliente, un cajero o un administrador. Los actores que son a su vez sistemas representan software externo con el que el sistema modelado se comunica, como una pasarela de pago, un servicio de correo electrónico o un sistema legacy heredado. Por último, los actores dispositivo representan elementos físicos como sensores, impresoras o terminales especializados. Otra clasificación distingue actores primarios, que son los que inician una interacción con el sistema, de actores secundarios, que responden cuando son invocados o reciben notificaciones del sistema.

El segundo elemento fundamental es el caso de uso. Un caso de uso representa una unidad de funcionalidad que el sistema ofrece a un actor. Se dibuja como una elipse con el nombre del caso de uso dentro. La regla de oro para el nombramiento es que el nombre debe ser un verbo en infinitivo seguido de un sustantivo, como por ejemplo "registrar pedido", "consultar saldo" o "emitir factura". Nunca debe formularse como un sustantivo en aislado del tipo "registro de pedidos", porque eso describe un artefacto y no una acción.

El tercer elemento es el límite del sistema, también llamado boundary. Se trata de un rectángulo que envuelve todos los casos de uso y que sirve para delimitar visualmente qué pertenece al sistema y qué queda fuera de él. Los casos de uso se colocan en el interior del rectángulo, mientras que los actores se sitúan en el exterior. Aunque no es obligatorio dibujarlo, se recomienda hacerlo siempre porque clarifica la estructura del diagrama.

El cuarto concepto, que no es estrictamente un elemento gráfico sino textual, es el escenario. Un escenario es una secuencia concreta de pasos que describe una instancia particular del caso de uso. Existen tres tipos de escenarios: el escenario principal, que recorre el camino feliz cuando todo funciona como se espera; los escenarios alternativos, que recogen variaciones válidas del flujo principal; y los escenarios de excepción, que describen situaciones de error o condiciones anómalas. La especificación textual completa de un caso de uso incluye también su nombre, los actores que participan, las precondiciones que deben cumplirse antes de ejecutarlo, las postcondiciones que se garantizan al terminarlo, el flujo principal, los flujos alternativos y cualquier requisito especial.

### Las cuatro relaciones del diagrama

Entre los elementos del diagrama de casos de uso existen cuatro tipos de relaciones, y dominar la diferencia entre ellas es uno de los conocimientos que más se evalúan en el examen.

La primera relación es la asociación, también llamada relación de comunicación. Se dibuja como una línea simple que conecta un actor con un caso de uso. Su significado es que el actor participa en ese caso de uso. Es la relación más sencilla y la que más aparece en cualquier diagrama.

La segunda relación es la inclusión, indicada con la palabra "include" escrita entre comillas tipográficas francesas. Se representa con una flecha discontinua que sale del caso de uso base y apunta al caso de uso incluido, llevando la etiqueta "«include»". Su significado es que el caso de uso base incluye siempre, de forma obligatoria, al caso de uso incluido. La inclusión se utiliza para factorizar comportamiento común que aparece en varios casos de uso. Una analogía útil es pensar en include como una llamada a una función auxiliar indispensable: cada vez que se ejecuta el caso de uso base, se ejecuta también el incluido sin excepción.

La tercera relación es la extensión, indicada con la palabra "extend" escrita entre comillas tipográficas francesas. Se representa con una flecha discontinua que sale del caso de uso extensor y apunta al caso de uso base, llevando la etiqueta "«extend»". Su significado es que el caso de uso extensor amplía al base solo en ciertas condiciones, marcadas mediante una guarda. La extensión se utiliza para modelar comportamiento opcional o alternativo. Una analogía útil es pensar en extend como un bloque condicional que solo se ejecuta cuando se cumple su guarda.

La diferencia entre include y extend es absolutamente crítica y conviene memorizarla con precisión. Include significa siempre, es obligatorio, y la flecha sale del caso de uso base apuntando hacia el caso de uso incluido. Extend significa a veces, es opcional, y la flecha sale del caso de uso extensor apuntando hacia el caso de uso base, en sentido inverso al de include. Esta inversión del sentido de la flecha es una de las trampas más habituales en los exámenes tipo test.

La cuarta relación es la generalización, también llamada herencia. Se representa con una flecha que tiene la punta en forma de triángulo hueco y apunta hacia el elemento padre. Se utiliza principalmente entre actores, para indicar que un actor especializado hereda el comportamiento de un actor más general. Por ejemplo, un actor "estudiante premium" puede heredar de un actor "estudiante" añadiendo capacidades adicionales. También es posible aplicar generalización entre casos de uso, aunque es mucho menos frecuente.

### Buenas prácticas en casos de uso

Existen varias normas que conviene respetar al construir un diagrama de casos de uso. La primera y más importante es nombrar los casos de uso siempre con verbo en infinitivo más sustantivo. La segunda es no incluir más de veinte casos de uso en un único diagrama, porque pierde legibilidad; si el sistema es grande, conviene dividirlo en varios diagramas por paquete o subsistema. La tercera, frecuentemente olvidada, es que el diagrama de casos de uso no debe modelar nunca interfaces gráficas, navegación entre pantallas, secuencias temporales internas ni detalles de implementación. Estas cuestiones pertenecen a otros diagramas. La cuarta consiste en distinguir cuidadosamente cuándo aplicar include y cuándo extend, basándose en si el comportamiento adicional es obligatorio o condicional.

### Cómo abordar un enunciado de casos de uso

A la hora de resolver un ejercicio práctico de casos de uso, conviene seguir un procedimiento ordenado. El primer paso consiste en leer el enunciado e identificar todos los sustantivos que designan entidades capaces de actuar sobre el sistema. Esos sustantivos son candidatos a actores. Hay que distinguir entre actores humanos, sistemas externos y dispositivos, y entre primarios y secundarios. El segundo paso consiste en identificar todos los verbos en infinitivo o las frases del tipo "el usuario puede hacer X", porque cada uno de ellos representa un caso de uso. El tercer paso consiste en trazar las asociaciones, es decir, conectar cada actor con los casos de uso que ejecuta. El cuarto paso consiste en buscar comportamiento común a varios casos de uso y factorizarlo mediante relaciones de tipo include. El quinto paso consiste en buscar comportamiento condicional, normalmente señalado en el enunciado por palabras como "opcionalmente", "si se cumple X", "puede que" o "en caso de", y modelarlo mediante relaciones de tipo extend. El sexto paso consiste en revisar si existen jerarquías entre actores, frecuentemente delatadas por frases del tipo "X es un tipo especial de Y", y representarlas mediante generalización. Por último, conviene dibujar el rectángulo del sistema, colocar dentro los casos de uso y fuera los actores.

### Ejemplo resuelto: sistema de biblioteca universitaria

Considérese el siguiente enunciado típico. Una biblioteca universitaria necesita un sistema informático para gestionar el préstamo de libros. Los socios pueden buscar el catálogo, tomar libros en préstamo y devolver los que ya han leído. Si un socio devuelve un libro con retraso, el sistema debe gestionar el pago de una multa a través de una pasarela de cobro externa. El bibliotecario es responsable de mantener actualizado el fondo de la biblioteca.

Al aplicar el procedimiento anterior, se identifican tres actores. El socio y el bibliotecario son actores humanos primarios, porque inician las interacciones con el sistema. El sistema de pago externo es un actor secundario, porque solo responde cuando se le invoca. Los casos de uso identificados son seis: buscar catálogo, tomar prestado libro, devolver libro, pagar multa, gestionar fondo y verificar socio. El socio se asocia con los cuatro primeros casos de uso. El bibliotecario se asocia con gestionar fondo. El sistema de pago se asocia con pagar multa. Existe una relación de inclusión entre tomar prestado libro y verificar socio, porque el sistema siempre verifica al socio antes de prestarle un libro. Existe una relación de extensión entre pagar multa y devolver libro, porque la multa solo se aplica si el libro se devuelve con retraso. Todos los casos de uso quedan dentro del rectángulo del sistema, etiquetado como "Sistema de Biblioteca", y los actores quedan fuera.

### Ejemplo resuelto adicional: plataforma de e-learning

Considérese ahora un segundo ejemplo. Una academia online quiere un sistema donde los estudiantes puedan matricularse en cursos, ver las lecciones y entregar ejercicios. El profesor crea cursos, sube material didáctico y corrige las entregas. Antes de permitir la matriculación, el sistema debe verificar siempre el pago del estudiante. Si la entrega de un ejercicio se realiza fuera de plazo, opcionalmente se aplica una penalización en la nota. Los administradores gestionan todos los cursos. Existe además un tipo especial de estudiante, llamado estudiante premium, que tiene acceso a tutorías personalizadas.

En este caso, los actores son el estudiante, el estudiante premium, el profesor, el administrador y la pasarela de pago. El estudiante premium hereda del estudiante mediante una relación de generalización, y añade el caso de uso recibir tutoría. Los casos de uso del sistema son matricularse en curso, ver lección, entregar ejercicio, recibir tutoría, crear curso, subir material, corregir entrega, gestionar cursos, verificar pago y aplicar penalización. La relación de inclusión vincula matricularse en curso con verificar pago, porque el sistema verifica el pago siempre antes de matricular. La relación de extensión vincula aplicar penalización con entregar ejercicio, bajo la guarda de que la entrega sea tardía.

### Preguntas frecuentes sobre casos de uso

Una pregunta habitual es cuándo conviene utilizar include y cuándo extend. La respuesta es que se utiliza include cuando el comportamiento incluido se ejecuta siempre, sin excepción, como parte obligatoria del caso de uso base. Se utiliza extend cuando el comportamiento extendido se ejecuta solo bajo ciertas condiciones definidas por una guarda. La pregunta de si un actor puede ser otro sistema tiene respuesta afirmativa: los actores no tienen por qué ser humanos, y de hecho los sistemas externos como pasarelas de pago, servicios de email, sensores o sistemas legacy se representan exactamente igual con la figura del stick figure. La pregunta de si se puede heredar entre casos de uso o solo entre actores tiene respuesta también afirmativa: la generalización es válida en ambos casos, aunque resulta mucho más frecuente y aceptada entre actores. La pregunta sobre el número máximo de casos de uso por diagrama suele responderse con la cifra orientativa de veinte, a partir de la cual la legibilidad se ve comprometida. Si dos actores comparten muchos casos de uso, lo correcto es crear un actor padre genérico del que ambos hereden mediante generalización. Sobre el boundary del sistema, conviene dibujarlo siempre aunque técnicamente no sea obligatorio, porque mejora la claridad. Por último, los flujos alternativos de cada caso de uso no se modelan en el diagrama, sino que se documentan en la ficha textual asociada al caso de uso.

---

## Parte 3. El diagrama de actividad

El diagrama de actividad es el equivalente UML del clásico diagrama de flujo, pero enriquecido con semántica orientada a objetos. Su misión consiste en modelar el flujo de trabajo de un proceso, un algoritmo o un caso de uso completo. Muestra qué acciones se realizan, qué decisiones se toman a lo largo del proceso y cómo fluye el control entre unas y otras. UML 2 añadió varias mejoras importantes a este diagrama, entre las que destacan el soporte para concurrencia mediante fork y join, las particiones llamadas swimlanes para clarificar responsabilidades, las señales para modelar eventos asíncronos y los flujos de datos para mostrar qué información circula entre las acciones.

### Elementos del diagrama de actividad

El nodo inicial es el punto de entrada del diagrama y se representa con un círculo negro sólido. Marca el inicio del flujo de control y solo puede haber uno por diagrama.

El nodo final de actividad se representa con un círculo negro encerrado dentro de otro círculo, formando una especie de diana. Su efecto es terminar toda la actividad por completo, lo cual incluye cancelar cualquier rama paralela que estuviera ejecutándose en ese momento.

El nodo final de flujo, en cambio, se representa con un círculo que contiene una equis dentro. Su efecto es muy distinto al del final de actividad: termina únicamente esa rama concreta del proceso, sin afectar al resto de ramas que puedan estar ejecutándose en paralelo. La distinción entre final de actividad y final de flujo es una de las preguntas que más caen en los exámenes tipo test.

Las acciones son las unidades atómicas del proceso. Se representan mediante un rectángulo con esquinas redondeadas que contiene en su interior un verbo o expresión verbal que describe la tarea. Las actividades son conceptualmente similares a las acciones, pero pueden descomponerse en subactividades más finas si se desea modelar el proceso a varios niveles de detalle.

El nodo de decisión se representa con un rombo y tiene una entrada y varias salidas. Cada una de las salidas debe llevar una guarda escrita entre corchetes, como por ejemplo "[Sí]", "[No]", "[OK]" o "[importe mayor que mil euros]". La decisión bifurca el flujo en función del valor de las guardas, y exactamente una de ellas debe evaluarse a verdadera.

El nodo de unión, llamado merge en inglés y representado también con un rombo, hace lo opuesto: tiene varias entradas y una sola salida, y sirve para reunificar ramas alternativas que previamente fueron bifurcadas mediante una decisión. El merge no lleva guardas en sus entradas.

El nodo de bifurcación paralela, llamado fork, se representa con una barra gruesa horizontal o vertical y tiene una entrada y varias salidas, pero a diferencia de la decisión, las ramas que salen del fork se ejecutan todas a la vez, de forma concurrente.

El nodo de unión paralela, llamado join, se representa también con una barra gruesa y tiene varias entradas y una sola salida. Su misión es sincronizar las ramas paralelas que previamente lanzó un fork: el flujo no continúa hasta que todas las ramas entrantes hayan terminado. Una regla mnemotécnica útil para recordar la relación entre fork y join es que todo fork debe tener su correspondiente join: fork sin join suspenso fijo, suelen decir los profesores.

Las particiones, también llamadas swimlanes o carriles de natación, son una de las características más útiles del diagrama de actividad. Consisten en carriles verticales u horizontales que dividen el diagrama agrupando las acciones según el responsable que las ejecuta. Cuando un proceso involucra a varios actores o sistemas, como el cliente, el almacén, el sistema de pagos y el transportista en una compra online, cada uno de ellos ocupa un swimlane y las acciones que ejecuta se dibujan dentro de su carril. Esto clarifica enormemente la responsabilidad de cada participante y muestra de forma visual cuándo el control pasa de un actor a otro.

Los nodos objeto se representan con un rectángulo simple, sin esquinas redondeadas, y representan el estado de un objeto en el flujo de datos. Las flechas entre nodos objeto modelan el flujo de información: qué dato produce una acción y qué dato consume otra.

Las señales se representan mediante pentágonos. Un pentágono con la punta hacia fuera indica una señal enviada, es decir, el sistema emite un evento. Un pentágono con la punta hacia dentro indica una señal recibida, es decir, el sistema espera la recepción de un evento externo antes de continuar.

### Cómo abordar un enunciado de actividad

Para resolver un ejercicio práctico de diagrama de actividad, conviene seguir un procedimiento sistemático. El primer paso consiste en identificar a los participantes del proceso. El enunciado suele mencionarlos explícitamente como una lista, por ejemplo "el cliente, el almacén, el sistema de pagos y el transportista". Cada uno de ellos se convierte en un swimlane. El segundo paso consiste en linealizar el proceso, es decir, escribir en orden los verbos de acción que aparecen en el enunciado, ubicando cada uno en el carril del actor que lo ejecuta. El tercer paso consiste en localizar las decisiones, normalmente delatadas por frases del tipo "si tal cosa entonces tal otra, si no tal otra distinta" o por preguntas implícitas como "comprueba si el stock está disponible". Cada decisión genera un rombo con al menos dos ramas etiquetadas con guardas. El cuarto paso consiste en localizar el paralelismo, señalado en el enunciado por palabras como "al mismo tiempo", "simultáneamente", "en paralelo" o "mientras tanto". Cada ocurrencia de paralelismo se modela con un fork antes de las ramas concurrentes y un join después para sincronizarlas. El quinto paso consiste en colocar el nodo inicial en el carril del actor que dispara el proceso. El sexto paso consiste en decidir, para cada rama del proceso, si su finalización debe terminar todo el diagrama o solo esa rama. Si una rama es de error y debe cancelar todo, se utiliza un final de actividad; si simplemente abandona ese camino sin afectar al resto, se utiliza un final de flujo.

Entre los errores más frecuentes que penalizan en el examen están olvidar las guardas en las salidas de las decisiones, dibujar un fork sin su correspondiente join o viceversa, confundir el rombo de decisión con el rombo de merge, y mezclar en un mismo swimlane acciones que corresponden a distintos responsables.

### Ejemplo resuelto: tramitación de pedido online

Considérese el siguiente enunciado. Diseña el diagrama de actividad del proceso de tramitación de un pedido online desde que el cliente pulsa el botón comprar hasta la entrega final. El diagrama debe utilizar swimlanes para los siguientes participantes: cliente, sistema de pagos, almacén y transportista. El proceso debe incluir al menos dos decisiones y al menos un fork con su join.

La solución típica consta de cuatro swimlanes, uno por participante. El nodo inicial se coloca en el carril del cliente, porque es él quien dispara el proceso al confirmar el pedido. Inmediatamente después se ejecuta la acción de procesar pago en el carril del sistema de pagos. Aparece entonces la primera decisión, etiquetada como "¿pago OK?", con dos ramas: si el pago no es correcto, la rama del "no" lleva a una acción de notificar error de pago y termina en un final de flujo; si el pago es correcto, la rama del "sí" continúa el proceso. A continuación, en el carril del almacén, se ejecuta la acción de verificar stock, y aparece la segunda decisión "¿stock OK?". Si no hay stock, la rama del "no" lleva a una acción de notificar rotura de stock y emitir reembolso, terminando en un final de flujo. Si hay stock, la rama del "sí" continúa hacia la acción de preparar pedido. Tras esta acción se llega a un fork que lanza dos ramas paralelas: una para que el almacén actualice su inventario y otra para que el transportista registre el bulto. Las dos ramas se sincronizan en un join, tras el cual se ejecuta la acción de transportar y finalmente la de entregar al cliente, llegando al nodo final de actividad que cierra todo el diagrama.

### Ejemplo resuelto adicional: proceso de selección de personal

Considérese ahora un segundo ejemplo. Una empresa quiere modelar su proceso de selección. El candidato envía su currículum. El departamento de recursos humanos lo revisa: si el candidato no cumple el perfil mínimo, se le notifica el rechazo y se termina el proceso para él. Si cumple el perfil, se le convoca a una entrevista personal con recursos humanos y, en paralelo, el departamento técnico le realiza una prueba técnica online. Una vez completadas ambas, la entrevista y la prueba, recursos humanos toma la decisión final. Si la decisión es positiva, redacta la oferta de empleo y la envía al candidato. Si la decisión es negativa, le notifica el rechazo.

Este proceso se modela con tres swimlanes: candidato, recursos humanos y técnico. El nodo inicial se sitúa en el carril del candidato y conecta con la acción de enviar currículum. Tras ella, recursos humanos revisa el currículum y aparece la primera decisión "¿cumple perfil?". La rama del "no" conduce a una notificación de rechazo y a un final de flujo. La rama del "sí" conduce a un fork que lanza dos ramas paralelas. Una rama, en el carril de recursos humanos, ejecuta la entrevista. La otra rama, en el carril del técnico, ejecuta la prueba técnica. Las dos ramas se reúnen en un join, tras el cual recursos humanos evalúa el conjunto en la decisión "¿decisión positiva?". Las dos ramas resultantes, la de redactar y enviar oferta y la de notificar rechazo, convergen ambas en un final de actividad común que cierra el diagrama.

### Preguntas frecuentes sobre actividad

Una pregunta clásica es cuándo conviene utilizar un diagrama de actividad y cuándo un diagrama de estados, dado que ambos modelan dinamismo. La respuesta es que el diagrama de actividad modela procesos, entendidos como secuencias de pasos en los que pueden intervenir varios participantes y existen decisiones y paralelismo. El diagrama de estados, en cambio, modela el ciclo de vida de un único objeto, es decir, las situaciones por las que pasa ese objeto y los eventos que provocan los cambios. Si el enunciado describe un workflow con varios actores, lo natural es un diagrama de actividad. Si el enunciado describe cómo evoluciona internamente algo concreto, como un pedido, una cuenta o un ticket, lo natural es un diagrama de estados.

Otra pregunta frecuente es qué diferencia hay entre el rombo de decisión y el rombo de merge, dado que ambos se dibujan con la misma forma geométrica. La diferencia está en el número de entradas y salidas y en la presencia de guardas. La decisión tiene una entrada y varias salidas, y cada salida lleva una guarda. El merge tiene varias entradas y una sola salida, sin guardas.

Sobre si un fork puede existir sin su correspondiente join, la respuesta correcta desde el punto de vista del estándar es que no, todo fork debe terminar en un join. Si las ramas no se sincronizan, lo correcto es terminarlas independientemente con finales de flujo.

La utilidad de los swimlanes consiste en clarificar las responsabilidades en procesos donde intervienen varios actores. Son imprescindibles cuando el enunciado describe un proceso con múltiples participantes, porque permiten ver de un vistazo quién ejecuta cada acción.

Sobre la posibilidad de anidar actividades, la respuesta es afirmativa: una actividad puede descomponerse en un subdiagrama de actividad más detallado, lo cual se indica con un pequeño icono en su esquina inferior.

La diferencia entre nodo final de actividad y nodo final de flujo es que el primero termina por completo todo el diagrama, cancelando incluso las ramas paralelas que estuvieran activas, mientras que el segundo termina únicamente esa rama, permitiendo que el resto continúe.

Por último, para modelar excepciones o errores, lo habitual es utilizar una decisión que detecte la condición de error y dirija el flujo hacia un final de flujo o de actividad, según corresponda. También pueden emplearse señales recibidas si la excepción proviene del exterior.

---

## Parte 4. El diagrama de estados

El diagrama de estados, conocido en inglés como state machine diagram o statechart, modela el comportamiento de un objeto concreto a lo largo de su ciclo de vida. Esa frase contiene dos ideas importantes. La primera es que el diagrama se centra en un único objeto, no en un proceso con varios participantes. La segunda es que describe la evolución del objeto desde que nace hasta que termina su vida, indicando todas las situaciones por las que puede pasar y todos los eventos que provocan los cambios entre ellas.

Este diagrama resulta especialmente útil para clases cuyo comportamiento depende fuertemente del estado interno del objeto. Ejemplos típicos son los pedidos, que pasan por estados como pendiente, pagado, en preparación, en tránsito y entregado; las cuentas bancarias, que pueden estar activas, bloqueadas, suspendidas o cerradas; los documentos en revisión, que pueden ser borrador, en revisión, aprobado o rechazado; o las conexiones de red, que pasan por estados de inactiva, estableciendo, activa, cerrando y cerrada.

### Elementos del diagrama de estados

El elemento central es el estado. Un estado representa una situación en la vida del objeto en la que se satisface alguna condición, se está realizando alguna actividad o se espera algún evento. Se representa mediante un rectángulo con esquinas redondeadas que contiene el nombre del estado en su interior. Los nombres de los estados se forman habitualmente con sustantivos o adjetivos descriptivos de la situación, como "activa", "pendiente", "bloqueada", "cerrado" o "en tránsito".

Dentro del rectángulo de un estado pueden escribirse acciones internas que se ejecutan en momentos específicos. La acción "entry" se ejecuta una sola vez al entrar al estado y se escribe como "entry / nombre de la acción". La acción "exit" se ejecuta una sola vez al salir del estado y se escribe como "exit / nombre de la acción". La acción "do" se ejecuta de forma continua o durativa mientras el objeto permanece en el estado, y se escribe como "do / nombre de la acción". La distinción entre las tres es importante: entry y exit son atómicas e instantáneas, mientras que do se prolonga en el tiempo y puede ser interrumpida por una transición de salida.

Las transiciones son las flechas que conectan estados y representan los cambios de un estado a otro. La etiqueta de una transición tiene una sintaxis estándar que conviene memorizar al detalle, y es la siguiente: nombre del evento, seguido opcionalmente de una guarda entre corchetes, seguido opcionalmente de una barra inclinada y una acción. La fórmula completa es "evento [guarda] / acción", aunque en la práctica algunos de los tres componentes pueden omitirse si no proceden.

El evento es el estímulo externo o interno que dispara la transición. Puede ser una llamada a un método, la llegada de un mensaje, la finalización de un temporizador o cualquier otro suceso significativo. Ejemplos de eventos son "pagar", "cancelar", "bloquear", "timeout" o "vencer".

La guarda es una expresión booleana entre corchetes que debe evaluarse a verdadera para que la transición pueda dispararse. Si el evento llega pero la guarda es falsa, la transición no se ejecuta y el objeto permanece en el estado actual. Ejemplos de guardas son "[saldo igual a cero]", "[reintentos menor que tres]" o "[usuario tiene permisos de administrador]".

La acción es la operación atómica que se ejecuta como parte de la transición, en el momento en que esta se dispara. Se escribe tras la barra inclinada. Ejemplos de acciones son "/ notificar usuario", "/ registrar en log", "/ actualizar base de datos" o "/ liberar recursos". Una transición completa de ejemplo sería "cerrar [saldo igual a cero] / liberar recursos", que se leería como: cuando llegue el evento "cerrar", si el saldo es igual a cero, ejecuta la acción de liberar recursos y transita al siguiente estado.

Existen además varios pseudoestados que cumplen funciones especiales. El estado inicial se representa con un círculo negro sólido y marca el punto de partida del ciclo de vida del objeto. No tiene transiciones entrantes y solo puede haber uno por máquina de estados. El estado final se representa con un círculo negro encerrado en otro círculo y marca el fin del ciclo de vida. Puede haber varios estados finales en un mismo diagrama si el objeto puede terminar de varias formas distintas, y también puede no haber ninguno si el objeto nunca termina, como ocurre en sistemas permanentes. El estado de decisión, representado con un rombo, sirve para bifurcar la transición en función de una condición. El estado histórico, representado con un círculo que contiene una H, se utiliza dentro de un estado compuesto para recordar el último subestado activo cuando se sale y se vuelve a entrar al compuesto. Los puntos de entrada y salida son pequeños círculos situados en el borde de un estado compuesto que permiten dirigir las transiciones hacia subestados concretos.

UML 2 introduce además los estados compuestos, que son estados que contienen una máquina de estados anidada en su interior, permitiendo modelar jerarquías de estados con varios niveles de detalle. Dentro de un estado compuesto pueden definirse regiones ortogonales, separadas por líneas discontinuas, que permiten modelar concurrencia interna: el objeto se encuentra simultáneamente en un subestado de cada región, en lugar de en uno solo.

### Cómo abordar un enunciado de estados

Para resolver un ejercicio práctico de diagrama de estados, el primer paso consiste en identificar con claridad cuál es el objeto cuyo ciclo de vida se está modelando. Es importante recordar que el diagrama de estados se refiere a un único objeto, no a un proceso con varios participantes. El segundo paso consiste en listar los estados estables por los que puede pasar el objeto. Suelen ser sustantivos o adjetivos que aparecen en el enunciado describiendo situaciones, como "pendiente", "activo", "bloqueado" o "cerrado". El tercer paso consiste en preguntarse, para cada estado identificado, qué evento provoca que el objeto abandone ese estado y a qué estado se dirige. Cada uno de estos cambios se modela con una flecha. El cuarto paso consiste en etiquetar cada transición con la sintaxis evento, guarda y acción, escribiendo solo los componentes que el enunciado mencione explícitamente. La guarda solo aparece si hay una condición, y la acción solo si se especifica qué se ejecuta al transicionar. El quinto paso consiste en añadir, dentro de los estados que lo requieran, las acciones internas entry, exit o do, según lo indique el enunciado mediante frases como "al entrar en el estado X se notifica al usuario" o "mientras está en X se acumulan intereses". El sexto paso consiste en marcar el estado inicial con un círculo negro apuntando al primer estado del ciclo de vida, y el séptimo paso consiste en marcar los estados finales con el círculo encerrado, si los hay.

Entre los errores más habituales están confundir un estado con una acción o actividad. Un estado es una situación estable, descrita con un sustantivo, en la que el objeto permanece durante cierto tiempo. Una acción es un paso del proceso, descrito con un verbo, que se ejecuta en un instante. Confundir "pagando" con "pagado" es un error típico: el primero parece un estado pero realmente describe la acción de pagar, mientras que "pagado" sí es un estado estable.

### Ejemplo resuelto: ciclo de vida de una cuenta bancaria

Considérese el siguiente enunciado. Una cuenta bancaria, tras ser creada, queda en estado activa. Puede bloquearse temporalmente por razones de seguridad o suspenderse por impago. Desde los estados bloqueada o suspendida se puede reactivar y volver al estado activa. La cuenta puede cerrarse definitivamente desde cualquier estado siempre que el saldo sea igual a cero. Mientras está activa, la cuenta acumula intereses de forma continua. Al entrar en el estado bloqueada, el sistema avisa automáticamente al titular.

El objeto modelado es la cuenta bancaria. Los estados identificados son cuatro: activa, bloqueada, suspendida y cerrada. La cuenta nace en el estado activa, lo que se modela con un pseudoestado inicial apuntando hacia activa. Dentro del rectángulo del estado activa se escriben dos acciones internas: una acción entry consistente en crear la cuenta y una acción do consistente en acumular intereses. Dentro del estado bloqueada se escribe una acción entry consistente en avisar al titular.

Las transiciones se etiquetan de la siguiente forma. De activa a bloqueada parte una transición etiquetada como "bloquear / notificar". De activa a suspendida parte una transición etiquetada como "suspender". De bloqueada a activa parte una transición etiquetada como "reactivar". De suspendida a activa parte otra transición etiquetada como "reactivar". De activa, bloqueada y suspendida parten respectivamente tres transiciones hacia el estado cerrada, todas etiquetadas como "cerrar [saldo igual a cero]", lo cual significa que solo puede cerrarse la cuenta si su saldo es cero. Finalmente, desde cerrada parte una transición sin etiqueta hacia el pseudoestado final, indicando que el ciclo de vida termina.

### Ejemplo resuelto adicional: ciclo de vida de un ticket de soporte

Considérese ahora un segundo ejemplo. Un sistema de helpdesk modela el ciclo de vida de un ticket de la siguiente manera. Al crearlo, el ticket queda en estado abierto. El técnico lo asigna y pasa a en progreso. Si el técnico necesita información adicional del usuario, el ticket se queda en espera; cuando el usuario responde, vuelve a en progreso. Cuando el técnico considera que el problema está resuelto, el ticket pasa a resuelto. El usuario tiene siete días para confirmar el cierre del ticket o para reabrirlo. Si confirma, el ticket pasa a cerrado. Si lo reabre, vuelve a en progreso. Si transcurren los siete días sin respuesta del usuario, el ticket se cierra automáticamente por timeout. En cualquier momento, si el ticket lleva más de treinta días sin actividad, se cancela automáticamente. Al entrar en el estado en espera, el sistema envía un correo electrónico al usuario para informarle.

El objeto modelado es el ticket. Los estados identificados son seis: abierto, en progreso, en espera, resuelto, cerrado y cancelado. El pseudoestado inicial apunta a abierto. El estado en espera contiene una acción entry consistente en enviar correo al usuario. Las transiciones se etiquetan como sigue. De abierto a en progreso parte una transición "asignar / registrar técnico". De en progreso a en espera parte una transición "pedir info". De en espera a en progreso parte una transición "responder". De en progreso a resuelto parte una transición "resolver". De resuelto a cerrado parte una transición "confirmar". De resuelto a en progreso parte una transición "reabrir". De resuelto a cerrado parte una segunda transición "timeout [siete días sin respuesta]". Desde abierto, en progreso y en espera parten respectivamente tres transiciones hacia cancelado, todas etiquetadas como "timeout [treinta días sin actividad]". Los estados cerrado y cancelado conducen al pseudoestado final, indicando que el ciclo de vida ha terminado por dos rutas distintas.

### Preguntas frecuentes sobre estados

La pregunta sobre la diferencia entre estado y acción o actividad ya se ha respondido más arriba: el estado es una situación estable descrita con un sustantivo, la acción es un paso instantáneo descrito con un verbo. Sobre el momento de ejecución de entry, exit y do, la respuesta es que entry se ejecuta una sola vez al entrar al estado, exit se ejecuta una sola vez al salir y do se ejecuta de forma continua o durativa mientras el objeto permanece en el estado. Sobre el propósito de la guarda, la respuesta es bloquear o permitir la transición en función de una condición: si la guarda es falsa, el evento se descarta y el objeto permanece donde estaba.

Sobre la posibilidad de que un objeto esté en dos estados simultáneamente, la respuesta es que sí, pero solo si se utilizan regiones ortogonales dentro de un estado compuesto. Cada región tiene su propia máquina de estados y el objeto se encuentra en un subestado de cada región a la vez, lo cual modela concurrencia interna.

Sobre el estado histórico, la respuesta es que se trata de un pseudoestado dentro de un estado compuesto que recuerda el último subestado activo cuando se sale y se vuelve a entrar, lo cual resulta útil cuando un proceso se interrumpe y debe retomarse en el mismo punto.

Sobre el número de estados finales, la respuesta es que pueden ser varios, si el ciclo de vida puede terminar por diferentes caminos, o ninguno, si el objeto no termina nunca.

Sobre las transiciones sin evento, la respuesta es que sí son válidas y se llaman transiciones de completado: se disparan automáticamente cuando la actividad do interna del estado termina, y si llevan guarda, esta se evalúa en ese momento.

---

## Parte 5. El diagrama de secuencia

El diagrama de secuencia es el diagrama de interacción más utilizado en UML y, según los profesores y los exámenes anteriores de esta unidad, es el que con más probabilidad caerá como ejercicio práctico en el examen final. Por esa razón conviene dedicarle una atención especial. Su misión consiste en mostrar cómo un conjunto de objetos colabora intercambiando mensajes a lo largo del tiempo para llevar a cabo una operación o implementar un caso de uso. Tiene dos dimensiones claramente diferenciadas: el eje horizontal alberga a los participantes de la interacción, dispuestos uno al lado del otro, y el eje vertical representa el tiempo, que siempre avanza hacia abajo. La fortaleza del diagrama de secuencia consiste en hacer explícito el orden temporal estricto en que ocurren los mensajes.

### Elementos del diagrama de secuencia

El primer elemento es la línea de vida, llamada en inglés lifeline. Representa la existencia de un participante a lo largo de la interacción. Gráficamente consta de dos partes: un rectángulo en la parte superior que contiene el nombre del participante y una línea vertical discontinua que desciende desde el rectángulo y simboliza el paso del tiempo durante el cual el objeto existe. El nombre del participante puede especificarse de tres formas. La primera, "nombreInstancia dos puntos NombreClase", como por ejemplo "usuario:Cliente", indica una instancia concreta de una clase. La segunda, "dos puntos NombreClase", como por ejemplo ":Servidor", indica una instancia anónima de la clase. La tercera, simplemente el nombre del actor sin clase, como "Cliente" a secas, indica un rol o un actor humano.

El segundo elemento es la caja de activación, también llamada execution occurrence. Se trata de un rectángulo estrecho y alargado superpuesto sobre la línea de vida, que representa el período durante el cual el objeto está ejecutando una operación. Comienza cuando el objeto recibe un mensaje o inicia una llamada y termina cuando devuelve el control al llamante o cuando la operación finaliza. Las cajas de activación se anidan cuando un objeto A llama a un objeto B y espera la respuesta: la caja de B aparece dentro del período de actividad de A, reflejando visualmente la pila de llamadas.

El tercer elemento son los mensajes, que vienen en varios tipos distintos. El mensaje síncrono se dibuja con una flecha sólida que termina en una cabeza rellena y maciza. Su semántica es que el emisor queda bloqueado a la espera de la respuesta, exactamente como ocurre en una llamada a método en un programa orientado a objetos. El mensaje asíncrono se dibuja con una flecha sólida que termina en una cabeza abierta, es decir, una punta de flecha que no está rellena. Su semántica es que el emisor continúa su ejecución sin esperar respuesta, como ocurre en sistemas basados en eventos, mensajería con colas o notificaciones. El mensaje de respuesta, también llamado de retorno, se dibuja con una flecha discontinua y representa la devolución del control y opcionalmente de un valor desde el objeto invocado hacia el llamante. El mensaje de creación se utiliza para instanciar un objeto durante la interacción: se dibuja con una flecha que apunta al rectángulo del nuevo objeto y se etiqueta con el estereotipo "«create»"; el rectángulo del objeto creado no aparece en la parte superior del diagrama sino a la altura del mensaje de creación. El mensaje de destrucción termina la existencia de un objeto y se etiqueta con el estereotipo "«destroy»"; visualmente la línea de vida del objeto destruido termina en una equis grande. El mensaje de autollamada, llamado en inglés self-call, es una flecha que sale de una línea de vida y vuelve a la misma línea, indicando que el objeto se llama a sí mismo, como ocurre en métodos recursivos o en delegaciones internas.

### Los fragmentos combinados

UML 2 introdujo los fragmentos combinados para permitir modelar lógica de control dentro del diagrama de secuencia. Hasta entonces, expresar condicionales o bucles en un diagrama de secuencia resultaba muy farragoso. Los fragmentos combinados son rectángulos que englobauna parte del diagrama y llevan en su esquina superior izquierda una etiqueta, llamada operador, que indica qué tipo de lógica representan. Las regiones internas de un fragmento se separan mediante líneas discontinuas horizontales, y cada región puede llevar su propia guarda entre corchetes.

El operador "alt" representa una alternativa de tipo si entonces sino, es decir, un condicional con varias ramas. Cada región del fragmento alt corresponde a una rama y se etiqueta con una guarda; solo se ejecuta la región cuya guarda evalúe a verdadera. Es el fragmento más utilizado en la práctica, porque cualquier enunciado del tipo "si las credenciales son correctas, hacer X; si no, hacer Y" se modela con un alt de dos regiones.

El operador "opt" representa un opcional, equivalente a un condicional sin rama else. La región dentro de un fragmento opt solo se ejecuta si la guarda es verdadera. Se utiliza para modelar acciones que ocurren bajo ciertas condiciones, sin alternativa explícita.

El operador "loop" representa un bucle, equivalente a una iteración while o for. La región del loop se repite mientras la guarda sea verdadera o un número determinado de veces si se especifica un contador.

El operador "par" representa paralelismo: las regiones del fragmento se ejecutan concurrentemente, no secuencialmente. Se utiliza cuando varias interacciones ocurren al mismo tiempo.

El operador "ref" representa una referencia a otra interacción definida en otro diagrama de secuencia. Permite reutilizar secuencias complejas sin tener que dibujarlas dos veces.

El operador "break" representa una salida anticipada del fragmento contenedor, equivalente a una excepción o a la sentencia break de un bucle.

### Cómo abordar un enunciado de secuencia

Para resolver un ejercicio práctico de diagrama de secuencia, el primer paso consiste en leer cuidadosamente el enunciado y subrayar todos los sustantivos que designan a participantes, ya sean actores humanos u objetos del sistema. Cada uno de ellos se convertirá en una línea de vida. Es importante decidir el orden en que se colocan las líneas de vida en el eje horizontal, normalmente de izquierda a derecha según el orden en que entran en juego en la interacción. El actor humano que dispara el caso de uso suele situarse en el extremo izquierdo.

El segundo paso consiste en subrayar todos los verbos del enunciado, porque cada uno representa un mensaje. Para cada mensaje hay que decidir su tipo. Si el emisor espera la respuesta antes de continuar, el mensaje es síncrono y se dibuja con cabeza rellena. Si el emisor continúa sin esperar, como ocurre típicamente con notificaciones, envíos de correo, logs o publicaciones en colas, el mensaje es asíncrono y se dibuja con cabeza abierta.

El tercer paso consiste en dibujar la cabecera del diagrama, colocando los rectángulos de los participantes en la parte superior con sus nombres y trazando las líneas verticales discontinuas hacia abajo.

El cuarto paso, que es el más importante y donde más se equivoca el alumnado, consiste en colocar los mensajes en orden estricto de arriba abajo, respetando el flujo temporal del enunciado. Un mensaje que aparece más abajo en el diagrama ocurre estrictamente después que cualquier mensaje situado más arriba. Esta regla del eje vertical como tiempo es la más violada en los exámenes y la que más penalización conlleva.

El quinto paso consiste en añadir las cajas de activación sobre las líneas de vida en los tramos en que cada objeto está procesando algo. Las cajas se anidan cuando hay llamadas que esperan respuesta. Conviene comprobar que la activación de A engloba la activación de B si A llama a B y espera su retorno.

El sexto paso consiste en dibujar los mensajes de respuesta con flecha discontinua cuando el enunciado los mencione explícitamente o cuando aporten claridad al diagrama. Por ejemplo, una consulta a base de datos suele acompañarse de una respuesta con el dato devuelto.

El séptimo paso consiste en englobar las partes del diagrama que tienen lógica condicional o repetitiva dentro de fragmentos combinados. Si el enunciado dice "si tal condición entonces hacer A, si no hacer B", se utiliza un fragmento alt con dos regiones. Si dice "si tal condición hacer A", sin rama else, se utiliza un fragmento opt. Si dice "se repite mientras", "por cada elemento" o "hasta que ocurra", se utiliza un fragmento loop. Si dice "al mismo tiempo" o "en paralelo", se utiliza un fragmento par.

Entre los errores más frecuentes que penalizan en el examen están confundir mensaje síncrono con asíncrono dibujando mal la cabeza de la flecha, colocar mensajes fuera del orden temporal, olvidar las cajas de activación, poner una respuesta donde el enunciado no la indica o no ponerla cuando es explícita, cruzar flechas innecesariamente por mal ordenar los participantes y olvidar las guardas de los fragmentos combinados.

### Ejemplo resuelto: inicio de sesión en una aplicación web

Considérese el siguiente enunciado. Un usuario accede a una aplicación web introduciendo su nombre de usuario y contraseña en un formulario de login. El formulario envía los datos al controlador de acceso, que los verifica contra la base de datos. Si las credenciales son correctas, se crea una sesión y se redirige al usuario a su panel personal. Si las credenciales son incorrectas, se muestra un mensaje de error.

Los participantes identificados son cuatro: el usuario, que actúa como actor humano, y tres objetos del sistema llamados FormLogin, ControlAcceso y BaseDatos. Se colocan en ese orden de izquierda a derecha. El primer mensaje es síncrono y va del usuario al FormLogin: "login(usuario, contraseña)". El segundo mensaje es también síncrono y va de FormLogin a ControlAcceso: "verificar(usuario, contraseña)". El tercer mensaje, también síncrono, va de ControlAcceso a BaseDatos: "buscarCredenciales(usuario)". La base de datos responde con un mensaje de retorno discontinuo que devuelve los datos.

En este punto entra en juego un fragmento alt con dos regiones. La primera región tiene la guarda "[credenciales OK]" y contiene una autollamada de ControlAcceso a sí mismo etiquetada como "crearSesion()", seguida de un mensaje de respuesta discontinuo de ControlAcceso a FormLogin llamado "redirigir al panel". La segunda región tiene la guarda "[credenciales NOK]" y contiene un mensaje de respuesta de ControlAcceso a FormLogin llamado "mostrar error".

Las cajas de activación reflejan la estructura siguiente: FormLogin está activo durante toda la interacción, porque está esperando la respuesta final; ControlAcceso está activo mientras procesa la verificación; BaseDatos solo está activa durante el tiempo en que consulta los datos.

### Ejemplo resuelto adicional: compra online con pasarela de pago

Considérese ahora un segundo ejemplo más complejo. Un cliente confirma su carrito de la compra en una tienda online. El controlador del carrito comprueba el stock en la base de datos. Si hay stock suficiente, el carrito envía la petición de cobro a una pasarela de pago externa y espera la respuesta. El sistema reintenta el cobro hasta un máximo de tres veces si la pasarela no responde. Si el pago se confirma, el sistema crea el pedido en la base de datos, envía de forma asíncrona una notificación al servicio de correo y devuelve la confirmación al cliente. Si no hay stock o el pago falla, devuelve un error al cliente.

Los participantes son cinco: Cliente, Carrito, Base de Datos, Pasarela y ServicioMail. El orden de izquierda a derecha facilita que las flechas no se crucen. El mensaje inicial es síncrono y va del Cliente al Carrito: "confirmarCompra()", lo cual activa al Carrito durante toda la interacción. El Carrito envía un mensaje síncrono "comprobarStock(items)" a la Base de Datos, que responde de forma discontinua con el resultado.

A continuación entra un fragmento alt exterior con dos regiones. La primera región tiene la guarda "[stock disponible]". Dentro de esta región se anida un fragmento loop con la guarda "[hasta tres reintentos]" que contiene un mensaje síncrono del Carrito a la Pasarela llamado "cobrar(total)" y su correspondiente respuesta. Tras el loop, dentro de la misma región de stock disponible, se anida un segundo fragmento alt interior con dos regiones: la primera tiene la guarda "[pago confirmado]" y contiene un mensaje síncrono del Carrito a la Base de Datos llamado "crearPedido", seguido de un mensaje asíncrono del Carrito al ServicioMail llamado "enviarConfirmacion", seguido de un mensaje de respuesta del Carrito al Cliente llamado "compraOK"; la segunda región tiene la guarda "[pago fallido]" y contiene un único mensaje de respuesta del Carrito al Cliente llamado "errorPago".

La segunda región del alt exterior tiene la guarda "[sin stock]" y contiene un único mensaje de respuesta del Carrito al Cliente llamado "errorStock".

Este patrón de combinación de fragmentos, con un alt exterior que envuelve a un loop, que a su vez envuelve a un alt interior, y la presencia de un mensaje asíncrono al final, es un patrón muy versátil que cubre prácticamente cualquier enunciado de complejidad media o alta que pueda aparecer en el examen.

### Preguntas frecuentes sobre secuencia

Sobre cuándo el mensaje es síncrono y cuándo asíncrono, la respuesta es que el mensaje síncrono se utiliza cuando el emisor bloquea su ejecución hasta recibir respuesta, lo cual ocurre típicamente en llamadas a métodos dentro del propio programa. El mensaje asíncrono se utiliza cuando el emisor continúa su ejecución sin esperar, como en eventos, mensajería con colas, publicaciones, notificaciones por correo o por mensaje corto.

Sobre si la respuesta debe dibujarse siempre, la respuesta es que no necesariamente. En llamadas síncronas, la respuesta es implícita y se devuelve automáticamente al terminar la activación del objeto llamado. Solo se dibuja explícitamente cuando aporta valor, por ejemplo cuando se devuelve un dato concreto que se va a utilizar después, o cuando el enunciado lo pide.

Sobre lo que representa la caja de activación, la respuesta es que representa el período de tiempo durante el cual el objeto está ejecutando una operación. Empieza al recibir un mensaje o al iniciar una llamada y termina al devolver el control. Las cajas se anidan cuando un objeto llama a otro y espera la respuesta, reflejando visualmente la pila de llamadas.

Sobre cómo modelar un condicional si entonces sino, la respuesta es utilizar un fragmento alt con dos regiones, cada una con su guarda. Si no hay rama else, se utiliza un fragmento opt con una sola región. Para bucles se utiliza un fragmento loop con guarda.

Sobre la creación y destrucción de objetos durante la secuencia, la respuesta es que sí es posible. La creación se modela con un mensaje etiquetado «create» que apunta al rectángulo del nuevo objeto, el cual aparece a la altura del mensaje y no en la cabecera. La destrucción se modela con un mensaje «destroy» y termina la línea de vida del objeto destruido con una equis grande.

Sobre el orden de los participantes en el eje horizontal, la respuesta es que importa visualmente. Lo recomendable es colocarlos según el flujo de interacción, normalmente de izquierda a derecha en el orden en que entran en juego, y reordenarlos si las flechas se cruzan demasiado.

Sobre la posibilidad de anidar varios fragmentos, la respuesta es que sí, los fragmentos se pueden anidar libremente. Es habitual encontrar un alt dentro de un loop, un opt dentro de un alt o estructuras aún más complejas. Conviene mantener la indentación visual clara para que la jerarquía se entienda.

---

## Parte 6. El diagrama de comunicación

El diagrama de comunicación, llamado también diagrama de colaboración en versiones anteriores de UML, comparte propósito con el diagrama de secuencia, pero adopta un enfoque visual completamente distinto. Modela las mismas interacciones que el diagrama de secuencia, pero pone el énfasis en la topología de la red de objetos que colaboran, en lugar de en el orden temporal de los mensajes. Es importante destacar que este diagrama no entra como ejercicio práctico en el examen, pero sí puede aparecer en preguntas teóricas de tipo test o de desarrollo, especialmente las que comparan secuencia y comunicación.

La equivalencia semántica entre los dos diagramas es total. Cualquier diagrama de secuencia puede transformarse en un diagrama de comunicación equivalente, y viceversa, sin perder información. La diferencia está en qué aspecto se resalta visualmente: en el de secuencia, el tiempo; en el de comunicación, la red de objetos.

### Elementos del diagrama de comunicación

Los objetos se representan como rectángulos con el nombre del objeto subrayado dentro. La notación del nombre sigue la misma convención que en secuencia: nombreInstancia dos puntos NombreClase. A diferencia de la secuencia, los objetos no se colocan en la parte superior del diagrama formando una cabecera, sino que se distribuyen libremente por el plano según convenga a la legibilidad. No existe un eje temporal explícito.

Los enlaces, llamados en inglés links, son líneas simples que conectan dos objetos entre sí. Cada enlace representa una instancia de una asociación entre las clases correspondientes en el diagrama de clases. Esta característica convierte al diagrama de comunicación en una herramienta útil para verificar la consistencia entre los diagramas estáticos y dinámicos: si un objeto necesita enviar un mensaje a otro, debe existir un enlace entre ellos, y por tanto una asociación correspondiente en el diagrama de clases. Si no la hay, se ha detectado una inconsistencia.

Los mensajes se dibujan como flechas sobre los enlaces, con una etiqueta que sigue la sintaxis número de secuencia, guarda opcional, asterisco opcional para iteración y nombre del mensaje con sus argumentos. La numeración es el elemento más distintivo de este diagrama, porque al no existir eje temporal, el orden de los mensajes solo puede deducirse de los números asignados a cada uno.

La numeración jerárquica refleja la anidación de las llamadas. El mensaje "1" es el primer mensaje de la interacción. El mensaje "1.1" es el primer submensaje que ocurre durante el procesamiento del mensaje 1. El mensaje "1.2" es el segundo submensaje dentro de la misma llamada 1. El mensaje "1.1.1" es el primer submensaje dentro del procesamiento de 1.1, y así sucesivamente. El mensaje "2" es el segundo mensaje de nivel superior, que ocurre después de haber terminado completamente todo el procesamiento de 1. Esta jerarquía refleja exactamente la pila de llamadas: cuando se procesa un mensaje, todos los submensajes que se invocan durante su procesamiento llevan un nivel adicional de numeración.

Para indicar iteración, es decir, que un mensaje se envía en un bucle, se utiliza un asterisco delante del número. La expresión "asterisco corchete condición corchete uno punto uno dos puntos procesar" significa que el mensaje 1.1 llamado "procesar" se envía repetidamente mientras se cumpla la condición. Para indicar condicional, es decir, que un mensaje solo se envía si se cumple una guarda, se utiliza la guarda entre corchetes. La expresión "corchete saldo mayor que cero corchete uno punto dos dos puntos cobrar" significa que el mensaje 1.2 llamado "cobrar" solo se envía si el saldo es mayor que cero.

### Comparativa entre secuencia y comunicación

El énfasis del diagrama de secuencia está en el orden temporal de los mensajes. El énfasis del diagrama de comunicación está en la topología de la red de objetos. El diagrama de secuencia tiene un eje temporal vertical explícito, que avanza hacia abajo. El diagrama de comunicación no tiene eje temporal, y el orden se deduce de la numeración jerárquica. En cuanto a la disposición, el diagrama de secuencia coloca los participantes en columnas en la parte superior, mientras que el diagrama de comunicación los distribuye libremente por el plano. El diagrama de secuencia es ideal para detallar paso a paso un escenario con énfasis en cuándo ocurre cada cosa, mientras que el diagrama de comunicación es ideal para visualizar la arquitectura de la colaboración, es decir, qué objetos hablan con qué otros objetos. La ventaja principal del diagrama de secuencia es la claridad del orden de ejecución; la ventaja principal del diagrama de comunicación es la claridad de la topología y el ahorro de espacio vertical en interacciones con muchos objetos. Los fragmentos combinados como alt, opt y loop tienen soporte nativo y muy expresivo en el diagrama de secuencia, mientras que en el de comunicación se modelan mediante guardas y asteriscos en la numeración, lo cual resulta menos cómodo. Ambos diagramas, no obstante, son semánticamente equivalentes: cualquier información que pueda expresarse en uno puede expresarse en el otro.

### Ejemplo conceptual: procesar un pedido

Considérese el siguiente enunciado conceptual. Un cliente realiza un pedido en una tienda online. El sistema debe comprobar el stock en el inventario, procesar el pago a través de la pasarela de cobro y enviar una confirmación al cliente cuando todo haya sido completado con éxito.

Los participantes son el Cliente, la Tienda, el Inventario, el sistema de Pago y el de Notificación. El cliente envía a la tienda el mensaje 1 etiquetado como "realizarPedido". La tienda procesa ese pedido y durante su procesamiento envía tres submensajes. El primero es el mensaje 1.1 a Inventario llamado "comprobarStock". El segundo es el mensaje 1.2 a Pago llamado "procesarPago". El tercero es el mensaje 1.3 a Notificación llamado "enviarConfirmacion". La numeración refleja que los tres ocurren dentro del procesamiento de 1, en ese orden.

Una observación interesante es que el diagrama de comunicación deja muy claro que Tienda es el objeto central, el hub, de la colaboración, ya que todos los submensajes salen de ella hacia los demás objetos. Esta característica topológica es más difícil de apreciar en un diagrama de secuencia.

### Preguntas frecuentes sobre comunicación

Sobre la diferencia con el diagrama de secuencia, la respuesta es que el énfasis es distinto. Secuencia se centra en el tiempo, comunicación en la topología. Son equivalentes semánticamente y convertibles uno en otro.

Sobre cómo se ve el orden temporal sin eje, la respuesta es mediante la numeración jerárquica de los mensajes, que indica tanto el orden global como la anidación de llamadas.

Sobre el significado de un número como uno punto uno punto dos, la respuesta es que se trata del segundo submensaje que ocurre dentro del primer submensaje del primer mensaje de nivel superior. La jerarquía refleja la pila de llamadas, igual que en una traza de ejecución de un programa.

Sobre cómo se modela un bucle, la respuesta es con un asterisco delante del número del mensaje, opcionalmente seguido de una guarda entre corchetes que indique la condición de iteración.

Sobre cómo se modela un condicional, la respuesta es con una guarda entre corchetes antes del número del mensaje. El mensaje solo se envía si la guarda evalúa a verdadera. Para un condicional con dos ramas, se utilizan dos mensajes con guardas mutuamente excluyentes.

Sobre el nombre antiguo "diagrama de colaboración", la respuesta es que así se llamaba en UML 1 y se renombró en UML 2 para reflejar mejor que muestra la comunicación entre los objetos dentro de una colaboración.

Sobre la utilidad de los enlaces, la respuesta es que cada enlace en un diagrama de comunicación corresponde a una instancia de una asociación entre clases. Esto permite validar que el diagrama de clases proporciona todas las asociaciones necesarias para soportar la colaboración modelada.

---

## Parte 7. Comparativa final entre los cinco diagramas

Una vez vistos los cinco diagramas en detalle, conviene resumir sus diferencias principales en una visión de conjunto. Cada diagrama responde a una pregunta distinta, opera en un nivel de abstracción distinto y se utiliza en una fase distinta del desarrollo.

El diagrama de casos de uso opera al nivel de abstracción más alto, el de los requisitos funcionales, y responde a la pregunta de qué hace el sistema desde la perspectiva del usuario. Se utiliza en la fase de análisis de requisitos. No tiene eje temporal. Sus participantes son los actores externos y los casos de uso. Sus relaciones principales son la asociación, la inclusión, la extensión y la generalización. La probabilidad de que caiga como ejercicio en el examen es media.

El diagrama de actividad opera a un nivel medio de abstracción y responde a la pregunta de cómo se ejecuta un proceso. Se utiliza en las fases de análisis y diseño. Tiene un eje temporal implícito reflejado en el flujo de control. Sus participantes son las acciones y las decisiones, agrupadas en swimlanes por responsable. Sus relaciones principales son las flechas de control, las decisiones con guardas y los fork y join para paralelismo. La probabilidad de que caiga como ejercicio es media.

El diagrama de estados opera al nivel detallado de una clase concreta y responde a la pregunta de cómo evoluciona un objeto a lo largo de su vida. Se utiliza en la fase de diseño detallado. No tiene eje temporal explícito, sino una sucesión de estados conectados por transiciones. Sus participantes son los estados internos del objeto. Sus relaciones principales son las transiciones etiquetadas con evento, guarda y acción. La probabilidad de que caiga como ejercicio es media.

El diagrama de secuencia opera a un nivel medio alto de abstracción y responde a la pregunta de en qué orden ocurren las interacciones entre objetos. Se utiliza en la fase de diseño. Tiene un eje temporal vertical explícito. Sus participantes son objetos representados como líneas de vida. Sus relaciones principales son los mensajes síncronos, asíncronos y de respuesta. La probabilidad de que caiga como ejercicio es alta, y de hecho es el diagrama más probable del examen.

El diagrama de comunicación opera al mismo nivel que el de secuencia y responde a la pregunta de cuál es la red de objetos que colabora. Se utiliza también en la fase de diseño. No tiene eje temporal. Sus participantes son objetos conectados por enlaces. Sus relaciones principales son los mensajes numerados jerárquicamente. La probabilidad de que caiga como ejercicio es nula, ya que solo entra como teoría.

### Flujo de trabajo profesional

En la práctica del desarrollo de software, los cinco diagramas se utilizan de forma encadenada. Se comienza por el diagrama de casos de uso, que captura los requisitos funcionales del sistema. Para los casos de uso más complejos, se elabora un diagrama de actividad que detalla el flujo del proceso, mostrando swimlanes para los distintos responsables. A continuación, para cada caso de uso relevante, se elabora un diagrama de secuencia que muestra cómo colaboran los objetos en el tiempo para implementar ese caso de uso. Opcionalmente, se convierte el diagrama de secuencia en uno de comunicación para verificar que la topología de la red de objetos es coherente y que las asociaciones del diagrama de clases son suficientes. Por último, para aquellas clases cuyo comportamiento depende fuertemente del estado interno, se elabora un diagrama de estados que detalla el ciclo de vida de cada instancia.

### Reglas mnemotécnicas anti confusión

Una primera regla útil distingue actividad de estados. El diagrama de actividad modela procesos con varios actores y los pasos son verbos. El diagrama de estados modela un único objeto y las situaciones son sustantivos. Si el enunciado describe varias responsabilidades y acciones, es actividad. Si describe la evolución de un único objeto, es estados.

Una segunda regla distingue secuencia de comunicación. Secuencia se centra en el cuándo, con tiempo vertical explícito. Comunicación se centra en el quién con quién, con numeración jerárquica.

Una tercera regla distingue include de extend. Include significa siempre, y la flecha sale del caso de uso base apuntando al incluido. Extend significa a veces, y la flecha sale del caso de uso extensor apuntando al base, en sentido inverso.

Una cuarta regla distingue final de actividad de final de flujo. El final de actividad mata todo el diagrama, incluyendo las ramas paralelas. El final de flujo solo termina esa rama, sin afectar a las demás.

---

## Parte 8. Errores típicos y consejos para el examen

A lo largo de este documento se han ido señalando los errores más frecuentes en cada diagrama. Conviene recopilarlos aquí en una sección final, porque son precisamente los que más penalización conllevan en el examen y los que con más frecuencia aparecen como preguntas trampa en los tests.

En el diagrama de casos de uso, los errores más frecuentes son nombrar los casos de uso con sustantivos en lugar de con verbos en infinitivo, confundir el sentido de la flecha de inclusión con el de la extensión, colocar actores dentro del rectángulo del sistema en lugar de fuera y modelar elementos que no corresponden a este diagrama como interfaces gráficas, pantallas o flujos internos.

En el diagrama de actividad, los errores más frecuentes son olvidar las guardas en las salidas de las decisiones, dibujar un fork sin su correspondiente join, confundir el rombo de decisión con el rombo de merge y mezclar en un mismo swimlane acciones que pertenecen a distintos responsables.

En el diagrama de estados, los errores más frecuentes son confundir un estado con una acción o actividad, olvidar las guardas en las transiciones cuando el enunciado las menciona explícitamente y poner como nombre de estado el gerundio de un verbo en lugar del nombre real del estado, como por ejemplo escribir "pagando" en lugar de la pareja estado "pendiente" más evento "pagar".

En el diagrama de secuencia, los errores más frecuentes son confundir la cabeza rellena del mensaje síncrono con la cabeza abierta del asíncrono, dibujar mensajes fuera del orden temporal estricto, olvidar las cajas de activación o anidarlas incorrectamente, poner mensajes de respuesta donde no los hay o no ponerlos cuando son explícitos, cruzar flechas innecesariamente por mal ordenar los participantes en el eje horizontal y olvidar las guardas dentro de los fragmentos combinados.

En el diagrama de comunicación, los errores más frecuentes son perderse en la numeración jerárquica al pasar de un nivel a otro y olvidar que los enlaces deben corresponder con asociaciones del diagrama de clases.

Como consejo general para el examen, conviene leer el enunciado dos veces antes de empezar a dibujar nada, subrayar las palabras clave que delaten elementos del diagrama, hacer primero un borrador en sucio organizando los elementos antes de pasar al limpio y dejar tiempo al final para revisar que todas las flechas tienen su etiqueta correcta y que las guardas, eventos y acciones están donde deben estar.

---

## Parte 9. Banco de preguntas con respuestas para cuestionarios y autoevaluación

Esta sección recopila un amplio banco de preguntas con sus respuestas para servir de base a tests, cuestionarios de evaluación o ejercicios de repaso autónomo. Las preguntas están agrupadas por diagrama y cubren tanto definiciones como diferencias conceptuales, casos prácticos y errores frecuentes.

### Preguntas generales sobre UML y diagramas de comportamiento

Pregunta uno. ¿Qué significan las siglas UML? Respuesta. UML significa Unified Modeling Language, en castellano lenguaje unificado de modelado. Es el estándar industrial para modelar sistemas software orientados a objetos.

Pregunta dos. ¿Cuáles son las dos grandes familias de diagramas en UML? Respuesta. Los diagramas estructurales o estáticos, que muestran la arquitectura del sistema, y los diagramas de comportamiento o dinámicos, que muestran qué hace el sistema y cómo lo hace a lo largo del tiempo.

Pregunta tres. ¿Cuántos tipos de diagramas de comportamiento define UML 2? Respuesta. Siete: casos de uso, secuencia, comunicación, actividad, estados, interacción general y tiempo.

Pregunta cuatro. ¿Cuál de los cinco diagramas del temario no entra como ejercicio práctico en el examen? Respuesta. El diagrama de comunicación, que entra únicamente como contenido teórico.

Pregunta cinco. ¿Es el diagrama de clases un diagrama de comportamiento? Respuesta. No, es un diagrama estructural, perteneciente a la familia estática de UML.

Pregunta seis. ¿En qué fase del desarrollo se utilizan principalmente los diagramas de casos de uso? Respuesta. En la fase de análisis de requisitos, ya que sirven para capturar las funcionalidades esperadas del sistema desde la perspectiva del usuario.

### Preguntas sobre el diagrama de casos de uso

Pregunta siete. ¿Qué representa un actor en un diagrama de casos de uso? Respuesta. Un actor representa cualquier entidad externa que interactúa con el sistema para conseguir un objetivo. Puede ser humano, otro sistema o un dispositivo.

Pregunta ocho. ¿Cómo se representa gráficamente un actor? Respuesta. Mediante una figura humana esquemática llamada en inglés stick figure, con el nombre del actor escrito debajo.

Pregunta nueve. ¿Cómo se nombra correctamente un caso de uso? Respuesta. Con un verbo en infinitivo seguido de un sustantivo, como por ejemplo "registrar pedido", "consultar saldo" o "emitir factura". Nunca con sustantivos sueltos como "registro de pedidos".

Pregunta diez. ¿Qué es el boundary o límite del sistema? Respuesta. Un rectángulo que delimita visualmente qué pertenece al sistema y qué queda fuera. Los casos de uso se colocan dentro del rectángulo y los actores fuera.

Pregunta once. ¿Cuáles son las cuatro relaciones del diagrama de casos de uso? Respuesta. Asociación, inclusión, extensión y generalización.

Pregunta doce. ¿Cuál es la diferencia entre include y extend? Respuesta. Include indica que el caso de uso incluido se ejecuta siempre como parte del caso de uso base, es decir, es obligatorio. Extend indica que el caso de uso extensor se ejecuta solo bajo ciertas condiciones, es decir, es opcional.

Pregunta trece. ¿En qué sentido va la flecha de include? Respuesta. Sale del caso de uso base y apunta al caso de uso incluido.

Pregunta catorce. ¿En qué sentido va la flecha de extend? Respuesta. Sale del caso de uso extensor y apunta al caso de uso base, en sentido inverso al de include.

Pregunta quince. ¿Qué se representa con la generalización? Respuesta. La herencia entre actores, o más raramente entre casos de uso. La flecha tiene la punta en forma de triángulo hueco y apunta al elemento padre.

Pregunta dieciséis. ¿Es obligatorio dibujar el rectángulo del sistema? Respuesta. No es estrictamente obligatorio, pero se recomienda hacerlo siempre porque mejora la claridad del diagrama.

Pregunta diecisiete. ¿Qué tipo de información no debe modelarse en un diagrama de casos de uso? Respuesta. Las interfaces gráficas, la navegación entre pantallas, los detalles de implementación interna y las secuencias temporales internas. Esas cuestiones pertenecen a otros diagramas.

Pregunta dieciocho. ¿Qué es un escenario? Respuesta. Una secuencia concreta de pasos que describe una instancia particular del caso de uso. Existen escenarios principales, alternativos y de excepción.

Pregunta diecinueve. ¿Cuál es el número máximo recomendado de casos de uso por diagrama? Respuesta. Aproximadamente veinte. Si hay más, se pierde legibilidad y conviene dividir en varios diagramas.

Pregunta veinte. ¿Puede un actor ser otro sistema software? Respuesta. Sí. Los actores pueden ser personas, otros sistemas como pasarelas de pago o servicios de correo, o dispositivos físicos como sensores.

### Preguntas sobre el diagrama de actividad

Pregunta veintiuno. ¿Qué modela el diagrama de actividad? Respuesta. Modela el flujo de trabajo de un proceso, un algoritmo o un caso de uso completo, mostrando acciones, decisiones, paralelismo y responsables.

Pregunta veintidós. ¿Cómo se representa el nodo inicial? Respuesta. Con un círculo negro sólido, y solo puede haber uno por diagrama.

Pregunta veintitrés. ¿Cuál es la diferencia entre el final de actividad y el final de flujo? Respuesta. El final de actividad, representado con un círculo dentro de otro círculo, termina todo el diagrama incluyendo las ramas paralelas. El final de flujo, representado con un círculo que contiene una equis, termina solo esa rama concreta sin afectar a las demás.

Pregunta veinticuatro. ¿Cómo se representa una acción? Respuesta. Mediante un rectángulo con esquinas redondeadas que contiene un verbo o expresión verbal describiendo la tarea.

Pregunta veinticinco. ¿Cómo se representa una decisión? Respuesta. Con un rombo que tiene una entrada y varias salidas, cada una etiquetada con una guarda entre corchetes.

Pregunta veintiséis. ¿Qué diferencia hay entre el rombo de decisión y el rombo de merge? Respuesta. La decisión tiene una entrada y varias salidas con guardas. El merge tiene varias entradas y una sola salida sin guardas, y sirve para reunificar ramas.

Pregunta veintisiete. ¿Cómo se representa un fork? Respuesta. Con una barra gruesa horizontal o vertical, que tiene una entrada y varias salidas. Las ramas salientes se ejecutan en paralelo.

Pregunta veintiocho. ¿Cómo se representa un join? Respuesta. Con una barra gruesa, que tiene varias entradas y una sola salida. Sincroniza las ramas paralelas esperando a que todas terminen.

Pregunta veintinueve. ¿Es válido tener un fork sin join? Respuesta. No, todo fork debe tener su correspondiente join para sincronizar las ramas. Si las ramas no se sincronizan, deben terminarse independientemente con finales de flujo.

Pregunta treinta. ¿Qué son las particiones o swimlanes? Respuesta. Carriles verticales u horizontales que dividen el diagrama agrupando las acciones según el responsable que las ejecuta. Imprescindibles cuando intervienen varios actores en el proceso.

Pregunta treinta y uno. ¿Cuándo conviene utilizar un diagrama de actividad y cuándo uno de estados? Respuesta. Actividad para procesos con varios participantes y flujo de control complejo. Estados para el ciclo de vida de un único objeto.

Pregunta treinta y dos. ¿Cómo se modela una excepción o un error en un diagrama de actividad? Respuesta. Con una decisión que detecte el error y dirija el flujo hacia un final de flujo o de actividad, según corresponda. También puede usarse una señal recibida si el error proviene del exterior.

### Preguntas sobre el diagrama de estados

Pregunta treinta y tres. ¿Qué modela el diagrama de estados? Respuesta. El ciclo de vida de un objeto concreto, mostrando todas las situaciones por las que pasa y los eventos que provocan los cambios entre ellas.

Pregunta treinta y cuatro. ¿Cómo se representa un estado? Respuesta. Con un rectángulo de esquinas redondeadas que contiene el nombre del estado en su interior.

Pregunta treinta y cinco. ¿Cuál es la sintaxis completa de una transición? Respuesta. Evento, seguido opcionalmente de una guarda entre corchetes, seguido opcionalmente de una barra inclinada y una acción. La fórmula completa es "evento corchete guarda corchete barra acción", aunque cualquiera de los tres componentes puede omitirse.

Pregunta treinta y seis. ¿Cuándo se ejecuta la acción entry de un estado? Respuesta. Una sola vez, en el instante en que el objeto entra al estado.

Pregunta treinta y siete. ¿Cuándo se ejecuta la acción exit? Respuesta. Una sola vez, en el instante en que el objeto sale del estado.

Pregunta treinta y ocho. ¿Cuándo se ejecuta la acción do? Respuesta. De forma continua o durativa, mientras el objeto permanece en el estado. Puede ser interrumpida por una transición de salida.

Pregunta treinta y nueve. ¿Para qué sirve una guarda en una transición? Respuesta. Para condicionar la transición. Si la guarda es falsa cuando llega el evento, la transición no se dispara y el objeto permanece en el estado actual.

Pregunta cuarenta. ¿Cómo se representa el estado inicial? Respuesta. Con un círculo negro sólido, sin transiciones entrantes. Solo puede haber uno por máquina de estados.

Pregunta cuarenta y uno. ¿Puede un objeto estar en dos estados simultáneamente? Respuesta. Sí, pero solo dentro de un estado compuesto con regiones ortogonales. Cada región tiene su propia máquina y el objeto se encuentra en un subestado de cada región a la vez.

Pregunta cuarenta y dos. ¿Qué es un estado histórico? Respuesta. Un pseudoestado dentro de un estado compuesto, representado con un círculo que contiene una H, que recuerda el último subestado activo cuando se sale y se vuelve a entrar al compuesto.

Pregunta cuarenta y tres. ¿Pueden existir varios estados finales? Respuesta. Sí, si el ciclo de vida del objeto puede terminar por diferentes caminos. También puede no haber ningún estado final si el objeto no termina nunca.

Pregunta cuarenta y cuatro. ¿Es válida una transición sin evento? Respuesta. Sí, se llama transición de completado, y se dispara automáticamente cuando termina la actividad do interna del estado.

Pregunta cuarenta y cinco. ¿Cuál es la diferencia entre un estado y una acción? Respuesta. Un estado es una situación estable descrita con un sustantivo en la que el objeto permanece durante cierto tiempo. Una acción es un paso instantáneo del proceso descrito con un verbo.

### Preguntas sobre el diagrama de secuencia

Pregunta cuarenta y seis. ¿Qué representa el eje vertical del diagrama de secuencia? Respuesta. El tiempo, que siempre avanza hacia abajo. Un mensaje situado más abajo ocurre estrictamente después que uno situado más arriba.

Pregunta cuarenta y siete. ¿Qué es una línea de vida? Respuesta. La representación gráfica de un participante a lo largo de la interacción. Consta de un rectángulo con el nombre del participante en la parte superior y una línea vertical discontinua que desciende reflejando el paso del tiempo.

Pregunta cuarenta y ocho. ¿Qué representa la caja de activación? Respuesta. El período de tiempo durante el cual el objeto está ejecutando una operación. Comienza al recibir un mensaje o iniciar una llamada y termina al devolver el control.

Pregunta cuarenta y nueve. ¿Cómo se distingue un mensaje síncrono de uno asíncrono? Respuesta. El mensaje síncrono se dibuja con una flecha de cabeza rellena y maciza, e indica que el emisor bloquea su ejecución hasta recibir respuesta. El mensaje asíncrono se dibuja con una flecha de cabeza abierta, y el emisor continúa sin esperar respuesta.

Pregunta cincuenta. ¿Cómo se dibuja un mensaje de respuesta? Respuesta. Con una flecha discontinua que va desde el objeto invocado hacia el llamante.

Pregunta cincuenta y uno. ¿Cómo se modela la creación de un objeto durante la interacción? Respuesta. Con un mensaje etiquetado "«create»" que apunta al rectángulo del objeto, el cual aparece a la altura del mensaje y no en la cabecera del diagrama.

Pregunta cincuenta y dos. ¿Cómo se modela la destrucción de un objeto? Respuesta. Con un mensaje etiquetado "«destroy»" y la línea de vida del objeto destruido termina con una equis grande.

Pregunta cincuenta y tres. ¿Qué es un fragmento combinado? Respuesta. Un rectángulo que engloba parte del diagrama y permite modelar lógica de control como condicionales, bucles o paralelismo. Lleva en su esquina superior izquierda una etiqueta con el operador correspondiente.

Pregunta cincuenta y cuatro. ¿Qué hace el operador alt? Respuesta. Representa una alternativa de tipo si entonces sino, con varias regiones, cada una con su guarda. Solo se ejecuta la región cuya guarda evalúe a verdadera.

Pregunta cincuenta y cinco. ¿Qué hace el operador opt? Respuesta. Representa un condicional opcional sin rama else. La región del fragmento se ejecuta solo si la guarda es verdadera.

Pregunta cincuenta y seis. ¿Qué hace el operador loop? Respuesta. Representa un bucle. La región se repite mientras la guarda sea verdadera, o un número fijo de veces si se especifica un contador.

Pregunta cincuenta y siete. ¿Qué hace el operador par? Respuesta. Representa paralelismo. Las regiones del fragmento se ejecutan concurrentemente.

Pregunta cincuenta y ocho. ¿Es necesario dibujar siempre el mensaje de respuesta? Respuesta. No. En llamadas síncronas la respuesta es implícita y solo se dibuja cuando aporta valor, por ejemplo cuando devuelve un dato concreto que se va a utilizar.

Pregunta cincuenta y nueve. ¿En qué orden se colocan los participantes en el eje horizontal? Respuesta. Según el flujo de interacción, normalmente de izquierda a derecha en el orden en que entran en juego. El actor humano suele ir el primero. Si las flechas se cruzan demasiado, conviene reordenar.

Pregunta sesenta. ¿Se pueden anidar fragmentos combinados? Respuesta. Sí, los fragmentos pueden anidarse libremente, encontrándose habitualmente un alt dentro de un loop, un opt dentro de un alt u otras combinaciones.

Pregunta sesenta y uno. ¿Cuál es la regla más importante a respetar al dibujar un diagrama de secuencia? Respuesta. Que el eje vertical representa el tiempo y que un mensaje situado más abajo ocurre siempre después que uno situado más arriba. Esta regla es la más violada en los exámenes.

### Preguntas sobre el diagrama de comunicación

Pregunta sesenta y dos. ¿Qué nombre recibía el diagrama de comunicación en UML 1? Respuesta. Diagrama de colaboración. Se renombró a diagrama de comunicación en UML 2.

Pregunta sesenta y tres. ¿Cuál es el énfasis principal del diagrama de comunicación? Respuesta. La topología de la red de objetos que colaboran, es decir, quién habla con quién.

Pregunta sesenta y cuatro. ¿Tiene el diagrama de comunicación un eje temporal explícito? Respuesta. No. El orden de los mensajes se deduce de la numeración jerárquica.

Pregunta sesenta y cinco. ¿Qué representa un enlace en el diagrama de comunicación? Respuesta. Una instancia de una asociación entre clases. Sirve para verificar que el diagrama de clases proporciona las asociaciones necesarias para soportar la colaboración modelada.

Pregunta sesenta y seis. ¿Qué significa el número uno punto uno punto dos en una etiqueta de mensaje? Respuesta. Es el segundo submensaje que ocurre dentro del primer submensaje del primer mensaje de nivel superior. Refleja la pila de llamadas de la ejecución.

Pregunta sesenta y siete. ¿Cómo se indica iteración en la numeración de un mensaje? Respuesta. Con un asterisco delante del número, opcionalmente seguido de una guarda entre corchetes que indique la condición de iteración.

Pregunta sesenta y ocho. ¿Cómo se indica condicional en un mensaje? Respuesta. Con una guarda entre corchetes antes del número, de modo que el mensaje solo se envía si la guarda es verdadera.

Pregunta sesenta y nueve. ¿Son los diagramas de secuencia y de comunicación semánticamente equivalentes? Respuesta. Sí, cualquier información que se exprese en uno puede expresarse en el otro. La diferencia es el enfoque visual: tiempo frente a topología.

Pregunta setenta. ¿Cuándo es mejor el diagrama de comunicación sobre el de secuencia? Respuesta. Cuando se desea visualizar la arquitectura de la colaboración, es decir, qué objetos hablan con qué otros objetos, o cuando hay muchos objetos y se quiere ahorrar espacio vertical.

### Preguntas comparativas y de síntesis

Pregunta setenta y uno. ¿Qué diagrama modela el qué hace el sistema desde la perspectiva del usuario? Respuesta. El diagrama de casos de uso.

Pregunta setenta y dos. ¿Qué diagrama modela cómo fluye un proceso? Respuesta. El diagrama de actividad.

Pregunta setenta y tres. ¿Qué diagrama modela el ciclo de vida de un objeto? Respuesta. El diagrama de estados.

Pregunta setenta y cuatro. ¿Qué diagrama modela el orden temporal de los mensajes entre objetos? Respuesta. El diagrama de secuencia.

Pregunta setenta y cinco. ¿Qué diagrama modela la topología de la red de objetos que colaboran? Respuesta. El diagrama de comunicación.

Pregunta setenta y seis. ¿Cuál es el diagrama con mayor probabilidad de caer como ejercicio práctico en el examen? Respuesta. El diagrama de secuencia.

Pregunta setenta y siete. ¿En qué orden se utilizan típicamente los cinco diagramas durante un proyecto? Respuesta. Primero casos de uso para los requisitos, luego actividad para los procesos complejos, luego secuencia para detallar las interacciones, opcionalmente comunicación para verificar la topología, y por último estados para las clases con ciclo de vida complejo.

Pregunta setenta y ocho. ¿Qué diagramas tienen eje temporal explícito? Respuesta. Solo el diagrama de secuencia, con su eje vertical descendente. El de actividad lo tiene implícito en el flujo. Los demás no tienen eje temporal explícito.

Pregunta setenta y nueve. ¿Qué dos diagramas son semánticamente equivalentes? Respuesta. El diagrama de secuencia y el diagrama de comunicación. Cualquier interacción puede modelarse en uno o en otro indistintamente.

Pregunta ochenta. ¿Cuál es la diferencia fundamental entre actividad y estados, ya que ambos modelan dinamismo? Respuesta. Actividad modela procesos con varios participantes y pasos verbales. Estados modela el ciclo de vida de un único objeto y sus situaciones sustantivas.

---

## Parte 10. Glosario de términos esenciales

Acción. Tarea atómica que se ejecuta como parte de una transición en un diagrama de estados, o como paso del flujo en un diagrama de actividad.

Actividad. Tarea que puede descomponerse en subactividades. En el diagrama de actividad se representa con un rectángulo de esquinas redondeadas, igual que una acción simple.

Actor. Entidad externa que interactúa con el sistema en un diagrama de casos de uso. Puede ser humano, sistema o dispositivo, y se representa con una figura humana esquemática.

Alt. Operador de fragmento combinado en diagramas de secuencia, equivalente al condicional si entonces sino con varias regiones y guardas.

Asíncrono. Tipo de mensaje en el que el emisor no bloquea su ejecución a la espera de la respuesta. Se dibuja con flecha de cabeza abierta.

Asociación. Relación más simple del diagrama de casos de uso, dibujada como línea simple entre actor y caso de uso.

Boundary. Rectángulo del diagrama de casos de uso que delimita el sistema, separando lo interno de lo externo.

Caja de activación. Rectángulo estrecho y alargado sobre la línea de vida en un diagrama de secuencia, que representa el período durante el cual el objeto está ejecutando una operación.

Caso de uso. Unidad de funcionalidad ofrecida por el sistema a un actor. Se dibuja como elipse y se nombra con verbo en infinitivo más sustantivo.

Decisión. Nodo de bifurcación condicional, representado con un rombo, que tiene una entrada y varias salidas etiquetadas con guardas.

Diagrama de actividad. Diagrama de comportamiento que modela flujos de trabajo, procesos o algoritmos.

Diagrama de casos de uso. Diagrama de comportamiento que captura los requisitos funcionales del sistema desde la perspectiva del usuario.

Diagrama de comunicación. Diagrama de comportamiento que muestra la topología de la red de objetos que colaboran, con mensajes numerados jerárquicamente. Antes llamado diagrama de colaboración.

Diagrama de estados. Diagrama de comportamiento que modela el ciclo de vida de un objeto, mostrando sus estados y las transiciones entre ellos.

Diagrama de secuencia. Diagrama de comportamiento que muestra el intercambio de mensajes entre objetos a lo largo del tiempo, con eje temporal vertical descendente.

Do. Acción interna de un estado que se ejecuta de forma continua mientras el objeto permanece en ese estado.

Entry. Acción interna de un estado que se ejecuta una sola vez al entrar al estado.

Escenario. Secuencia concreta de pasos que describe una instancia particular de un caso de uso. Puede ser principal, alternativo o de excepción.

Estado. Situación estable en la vida de un objeto. Se representa con rectángulo de esquinas redondeadas y se nombra con sustantivos o adjetivos.

Estado compuesto. Estado que contiene una máquina de estados anidada en su interior.

Estado final. Pseudoestado que marca el fin del ciclo de vida del objeto. Se representa con un círculo dentro de otro círculo.

Estado histórico. Pseudoestado dentro de un estado compuesto, representado con una H, que recuerda el último subestado activo.

Estado inicial. Pseudoestado que marca el comienzo del ciclo de vida. Se representa con un círculo negro sólido.

Evento. Estímulo que dispara una transición en un diagrama de estados.

Exit. Acción interna de un estado que se ejecuta una sola vez al salir del estado.

Extend. Relación del diagrama de casos de uso que indica que un caso de uso extensor amplía a otro base solo bajo ciertas condiciones. La flecha sale del extensor y apunta al base.

Final de actividad. Nodo que termina todo el diagrama de actividad, cancelando incluso las ramas paralelas. Se representa con círculo dentro de círculo.

Final de flujo. Nodo que termina solo una rama del diagrama de actividad, sin afectar a las demás. Se representa con círculo con equis.

Fork. Nodo de bifurcación paralela en un diagrama de actividad, representado con barra gruesa, que lanza varias ramas concurrentes.

Fragmento combinado. Rectángulo en un diagrama de secuencia que engloba parte del diagrama para modelar lógica de control. Lleva un operador en la esquina superior izquierda.

Generalización. Relación de herencia, representada con flecha de punta hueca, usada principalmente entre actores en el diagrama de casos de uso.

Guarda. Expresión booleana entre corchetes que condiciona una transición o una rama. Si es falsa, la transición no se dispara.

Include. Relación del diagrama de casos de uso que indica que un caso de uso base incluye siempre, de forma obligatoria, a otro. La flecha sale del base y apunta al incluido.

Join. Nodo de unión paralela en un diagrama de actividad, representado con barra gruesa, que sincroniza varias ramas concurrentes.

Lifeline. Línea de vida. Representación gráfica de un participante en un diagrama de secuencia, con rectángulo arriba y línea vertical discontinua hacia abajo.

Loop. Operador de fragmento combinado en diagramas de secuencia, equivalente a un bucle.

Mensaje. Comunicación entre dos participantes en diagramas de secuencia o de comunicación. Puede ser síncrono, asíncrono, de respuesta, de creación, de destrucción o autollamada.

Merge. Nodo de unión en un diagrama de actividad, representado con rombo, que reúne varias ramas alternativas en una sola. Tiene varias entradas y una sola salida, sin guardas.

Nodo inicial. Punto de entrada de un diagrama de actividad, representado con círculo negro sólido.

Opt. Operador de fragmento combinado en diagramas de secuencia, equivalente a un condicional sin rama else.

Par. Operador de fragmento combinado en diagramas de secuencia, que indica que las regiones se ejecutan en paralelo.

Pseudoestado. Elemento especial en un diagrama de estados que no representa una situación del objeto sino un punto de control: inicial, final, decisión, histórico, punto de entrada o de salida.

Ref. Operador de fragmento combinado en diagramas de secuencia, que referencia una interacción definida en otro diagrama.

Región ortogonal. Subdivisión interna de un estado compuesto, separada por líneas discontinuas, que modela concurrencia interna en un diagrama de estados.

Síncrono. Tipo de mensaje en el que el emisor bloquea su ejecución hasta recibir respuesta. Se dibuja con flecha de cabeza rellena.

Swimlane. Carril de natación o partición de un diagrama de actividad, que agrupa las acciones por responsable.

Transición. Flecha que conecta dos estados en un diagrama de estados, etiquetada con evento, guarda y acción opcionales.

UML. Unified Modeling Language. Lenguaje unificado de modelado, estándar de la industria para describir sistemas software orientados a objetos.

---

## Conclusión

Este documento cubre de forma exhaustiva los cinco diagramas de comportamiento de UML incluidos en la Unidad de Trabajo 07: casos de uso, actividad, estados, secuencia y comunicación. Para cada diagrama se han presentado los fundamentos teóricos, los elementos gráficos con su notación, la sintaxis y semántica de sus relaciones, dos ejemplos resueltos por diagrama incluyendo enunciado y solución detallada, y un conjunto de preguntas frecuentes. La parte final del documento añade una comparativa cruzada entre los cinco diagramas, una sección dedicada a los errores más penalizados en el examen, un banco de ochenta preguntas con sus respuestas y un glosario de términos esenciales. El conjunto está diseñado para servir tanto de fuente de estudio directa como de base para generar cuestionarios automáticos de evaluación o materiales de audio educativos. La asimilación correcta de este contenido proporciona los conocimientos necesarios tanto para superar las preguntas teóricas tipo test y desarrollo como para resolver los ejercicios prácticos de dibujo a mano que se evalúan en el examen.
