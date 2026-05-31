# BRIEF DE ARQUITECTURA: Repositorio Core "Living Documentation" y Skills de Contexto

## 1. Visión General y Filosofía del Sistema
Actúas como un Ingeniero de Software de Sistemas y Experto en DevOps. Quiero que me ayudes a reestructurar mi espacio de trabajo actual (`~/.config/hypr`) bajo el paradigma de **Configuración Literaria (Literate Configuration)**. 

No quiero scripts de instalación rígidos que debamos recrear en cada conversación. En su lugar, el sistema se basará en un **Repositorio Core con Documentación Viva**. La "Receta Maestra" de mi sistema estará escrita en un archivo Markdown autoexplicativo (`INSTALLATION_RECIPE.md`). Cualquier acción de despliegue o script automatizado posterior debe ser un subproducto de la lectura estructurada de este archivo maestro.

### Objetivos Clave:
1.  **Cúpula de Datos Local:** Evitar el consumo de tokens en internet indexando localmente las wikis oficiales de CachyOS, Hyprland, Waybar, Rofi, Mako, Swww/Hyprpaper, Kitty/Alacritty.
2.  **Optimización de Contexto:** Crearás herramientas locales para ti (el agente de IA) que te permitan buscar rápidamente en la base de datos local y cargar únicamente el fragmento exacto que necesitas.
3.  **Skill de Auditoría y Registro (Blueprint Máquina/Humano):** Construiremos una skill individual que audite mi sistema actual (paquetes instalados, estado de los archivos, servicios activos) y genere o actualice dinámicamente nuestro archivo `INSTALLATION_RECIPE.md` detallando de forma lógica qué tengo instalado, por qué y cómo replicarlo.

---

## 2. Estructura de Directorios Propuesta para el Workspace Core
Quiero reorganizar mi repositorio Git de la siguiente forma para asegurar escalabilidad y persistencia en el tiempo:

```text
workspace-core/                 # Repositorio Git raíz
├── .config/                    # Mis archivos de configuración reales (Hyprland, Waybar, Rofi, Mako, etc.)
├── references/                 # Cúpula de Datos (Offline Wikis en Markdown/MDX)
│   ├── cachyos/                # Clon de la wiki oficial (Priorizando ruta /es/)
│   └── hyprland-ecosystem/     # Clones de wikis (Hyprland, Waybar, Rofi, Mako, Swww, etc.)
├── scripts/                    # Scripts dedicados únicamente a indexar y optimizar la búsqueda local
│   └── search_local_docs.py    # Herramienta de búsqueda offline rápida (tu Skill de búsqueda)
├── state_history/              # Historial y capturas de estado del equipo
└── INSTALLATION_RECIPE.md      # NUESTRA FUENTE DE VERDAD (Living Blueprint de mi sistema)
```

---

## 3. Requerimientos de las Skills (Herramientas para el Agente)

Para que puedas interactuar con mi sistema con la máxima eficiencia y bajo consumo de tokens, debes desarrollar y registrar las siguientes dos herramientas locales:

### Skill A: `search_local_docs(term: str, component: str = None)`
Esta herramienta te servirá para buscar sintaxis exacta y evitar alucinaciones.
*   Debe buscar el término en la carpeta `references/`.
*   Si se especifica `component` (ej: "waybar"), limitará la búsqueda a esa subcarpeta para optimizar la velocidad y ahorrar tokens de contexto.
*   Debe buscar en archivos `.md` y `.mdx`, priorizando directorios en español (`/es/`) si están disponibles.
*   Debe devolver extractos relevantes (bloques de código o párrafos específicos) en lugar de archivos completos.

### Skill B: `generate_system_blueprint(output_path: str = "INSTALLATION_RECIPE.md")`
Esta es la herramienta más importante. Cada vez que modifiquemos algo o queramos registrar un estado estable, invocarás esta skill para auditar el equipo y escribir o actualizar el archivo maestro `INSTALLATION_RECIPE.md`.
El archivo resultante debe tener una estructura similar a esta:
1.  **Metadatos del Sistema:** Versión del kernel, GPU activa, arquitectura del procesador (ej: x86-64-v3/v4).
2.  **Índice de Paquetes Instalados Manualmente:** Lista limpia extraída del sistema (diferenciando nativos de `pacman` y del repositorio `AUR/yay`), justificando brevemente su propósito en el entorno.
3.  **Configuración de Servicios Clave:** Cómo se habilitaron (SDDM, redes, notificaciones, demonios de fondo).
4.  **Mapa de Enlaces Simbólicos:** Qué carpetas de `.config/` del repositorio deben enlazarse a `~/.config/` del usuario.
5.  **Instrucciones de Despliegue Secuenciales:** Una guía en Markdown tan clara que cualquier humano, o tú mismo en una sesión futura, pueda leer y ejecutar paso a paso de arriba a abajo para dejar el escritorio idéntico.

---

## 4. Plan de Trabajo Inicial
Por favor, analiza este planteamiento y las mejores prácticas de administración declarativa de sistemas. Antes de escribir código, respóndeme con lo siguiente:
1.  **Validación de Arquitectura:** Confirma si la estructura del repositorio y el formato de la Living Documentation (`INSTALLATION_RECIPE.md`) te parecen óptimos, o si sugieres añadir algún parámetro para hacer la documentación aún más reproducible.
2.  **Diseño de la Skill B (`generate_system_blueprint`):** Explícame de qué comandos del sistema (como `pacman -Qe`, `yay -Qem`, inspección de servicios `systemctl`, etc.) te servirás para auditar mi equipo de forma limpia y generar el documento Markdown estructurado.
3.  **Primer Paso:** Cuál es la estrategia para mover mis archivos de configuración actuales a la estructura de carpetas propuesta sin perder el historial de commits de Git que ya tengo en mi carpeta local.

Quedo a la espera de tu análisis técnico para comenzar el Paso 1.