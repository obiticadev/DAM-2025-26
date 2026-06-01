# 🗺️ Ruta por bloques — Docker de noob a pro

Esta es tu **hoja de ruta maestra**. Los 40 ejercicios están organizados según los **17 bloques de teoría** (`teoria/00` … `teoria/16`). La idea es simple:

> **Para cada bloque:** lee primero su teoría, mira el resumen *"Qué verás aquí"*, y luego resuelve sus ejercicios en orden. Cuando todos estén en verde, pasas al siguiente bloque.

**Cómo leer cada ejercicio:** `NN` es su número (`.\validar.ps1 NN`). El orden de los números coincide casi siempre con el orden de lectura; donde un bloque "salta" de número, hay una 💡 nota.

| Fase | Bloques | De qué va |
|------|---------|-----------|
| **A. Fundamentos** | 00 → 04 | Qué es un contenedor, escribir Dockerfiles y buenas prácticas |
| **B. Imágenes profesionales** | 05 → 08 | Volúmenes, multi-stage, BuildKit, seguridad de la imagen |
| **C. Operar contenedores** | 09 → 11 | Redes, datos persistentes, logs y reinicios |
| **D. Orquestar con Compose** | 12 → 13 | De `docker run` a stacks declarativos |
| **E. Distribuir y escalar** | 14 → 16 | Registries, Kubernetes y el despliegue corporativo |

---

## FASE A — Fundamentos

### 📘 Bloque 00 — ¿Qué es un contenedor?
> **Teoría:** [`teoria/00_Que_Es_Un_Contenedor.md`](teoria/00_Que_Es_Un_Contenedor.md)
> **Qué verás aquí:** la diferencia entre un contenedor y una máquina virtual, el papel del kernel y por qué "en mi máquina funciona" deja de ser un problema. Tu primer contacto ejecutando algo dentro de un contenedor.

- **[01] hola_contenedor** — [enunciado](ejercicios/00_Que_Es_Un_Contenedor/01_hola_contenedor/ENUNCIADO.md) · Empaquetas un script en una imagen `alpine` y lo ejecutas. Tu primer `build` + `run`.

### 📘 Bloque 01 — Imágenes vs Contenedores
> **Teoría:** [`teoria/01_Imagenes_vs_Contenedores.md`](teoria/01_Imagenes_vs_Contenedores.md)
> **Qué verás aquí:** la imagen es la *plantilla* (inmutable, de solo lectura); el contenedor es la *instancia* en ejecución. Ciclo de vida, capas y por qué una imagen puede generar mil contenedores.
> 💡 Bloque **conceptual**: lo afianzas con el ejercicio 01 (anterior) y con todo el bloque de Anatomía que viene a continuación.

### 📘 Bloque 02 — Anatomía del Dockerfile
> **Teoría:** [`teoria/02_Anatomia_Dockerfile.md`](teoria/02_Anatomia_Dockerfile.md)
> **Qué verás aquí:** las instrucciones esenciales (`FROM`, `RUN`, `COPY`, `WORKDIR`, `ENV`, `ARG`, `EXPOSE`, `CMD`), cómo cada una crea una **capa** y cómo el orden afecta a la caché.

- **[02] primer_dockerfile** — [enunciado](ejercicios/02_Anatomia_Dockerfile/02_primer_dockerfile/ENUNCIADO.md) · Escribes tu primer Dockerfile de verdad (app Python).
- **[03] capas_y_cache** — [enunciado](ejercicios/02_Anatomia_Dockerfile/03_capas_y_cache/ENUNCIADO.md) · Entiendes las capas y cómo se invalida la caché.
- **[04] workdir_y_copy** — [enunciado](ejercicios/02_Anatomia_Dockerfile/04_workdir_y_copy/ENUNCIADO.md) · `WORKDIR` y `COPY` bien usados.
- **[05] env_y_arg** — [enunciado](ejercicios/02_Anatomia_Dockerfile/05_env_y_arg/ENUNCIADO.md) · Diferencia entre `ENV` (runtime) y `ARG` (build).
- **[06] expose_y_puertos** — [enunciado](ejercicios/02_Anatomia_Dockerfile/06_expose_y_puertos/ENUNCIADO.md) · `EXPOSE` y publicar puertos al host (nginx). *(También adelanta conceptos del bloque 09 de Redes.)*

### 📘 Bloque 03 — ENTRYPOINT vs CMD
> **Teoría:** [`teoria/03_Entrypoint_vs_Cmd.md`](teoria/03_Entrypoint_vs_Cmd.md)
> **Qué verás aquí:** las dos formas de definir "qué ejecuta" un contenedor, la diferencia entre forma *shell* y *exec*, y cómo combinarlas para hacer imágenes parametrizables.

- **[07] entrypoint_vs_cmd** — [enunciado](ejercicios/03_Entrypoint_vs_Cmd/07_entrypoint_vs_cmd/ENUNCIADO.md) · `ENTRYPOINT` fijo + `CMD` como argumento por defecto.
> 💡 A partir de aquí, **hadolint** pasa a ser puerta de calidad obligatoria en cada ejercicio.

### 📘 Bloque 04 — Buenas prácticas y seguridad
> **Teoría:** [`teoria/04_Buenas_Practicas_y_Seguridad.md`](teoria/04_Buenas_Practicas_y_Seguridad.md)
> **Qué verás aquí:** imágenes pequeñas, reproducibles y seguras: `.dockerignore`, `RUN` limpios, ejecutar como **no root**, etiquetas OCI y por qué el tamaño importa.

- **[08] dockerignore** — [enunciado](ejercicios/04_Buenas_Practicas_y_Seguridad/08_dockerignore/ENUNCIADO.md) · Excluyes basura y secretos del contexto de build.
- **[09] run_limpio** — [enunciado](ejercicios/04_Buenas_Practicas_y_Seguridad/09_run_limpio/ENUNCIADO.md) · Un solo `RUN` que instala y limpia la caché de paquetes.
- **[10] usuario_no_root** — [enunciado](ejercicios/04_Buenas_Practicas_y_Seguridad/10_usuario_no_root/ENUNCIADO.md) · Creas un usuario sin privilegios y conmutas con `USER`.
- **[11] labels_oci** — [enunciado](ejercicios/04_Buenas_Practicas_y_Seguridad/11_labels_oci/ENUNCIADO.md) · Metadatos OCI estándar.
- **[14] el_tamano_importa** — [enunciado](ejercicios/04_Buenas_Practicas_y_Seguridad/14_el_tamano_importa/ENUNCIADO.md) · Reduces la imagen eligiendo bien la base. 💡 *(salta al 14: encaja aquí por temática)*
- **[37] hadolint_audit** — [enunciado](ejercicios/04_Buenas_Practicas_y_Seguridad/37_hadolint_audit/ENUNCIADO.md) · Auditas y limpias un Dockerfile sucio hasta dejar hadolint en verde. 💡 *(reto de repaso; hazlo cuando domines el resto del bloque)*

---

## FASE B — Imágenes profesionales

### 📘 Bloque 05 — Volúmenes (fundamentos)
> **Teoría:** [`teoria/05_Volumenes_Fundamentos.md`](teoria/05_Volumenes_Fundamentos.md)
> **Qué verás aquí:** por qué un contenedor es efímero, qué es un volumen, la diferencia entre *bind mount* y *volumen nombrado*, e `VOLUME` en el Dockerfile.

- **[13] volumenes** — [enunciado](ejercicios/05_Volumenes_Fundamentos/13_volumenes/ENUNCIADO.md) · Un contador que persiste entre ejecuciones gracias a un volumen. 💡 *(salta al 13; profundiza en el bloque 10)*

### 📘 Bloque 06 — Multi-stage builds
> **Teoría:** [`teoria/06_Multistage_Builds.md`](teoria/06_Multistage_Builds.md)
> **Qué verás aquí:** compilar en una etapa y copiar solo el resultado a una imagen final mínima. Cómo tirar el compilador y dejar únicamente el binario.

- **[15] multistage_basico** — [enunciado](ejercicios/06_Multistage_Builds/15_multistage_basico/ENUNCIADO.md) · Compilas un binario Go y lo llevas a una imagen final limpia.
- **[16] adelgazar_scratch** — [enunciado](ejercicios/06_Multistage_Builds/16_adelgazar_scratch/ENUNCIADO.md) · Llevas ese binario a `scratch` (< 5 MB).

### 📘 Bloque 07 — BuildKit y caché
> **Teoría:** [`teoria/07_BuildKit_y_Cache.md`](teoria/07_BuildKit_y_Cache.md)
> **Qué verás aquí:** el motor moderno de build, los `cache mounts` (`RUN --mount=type=cache`) y cómo `ARG` antes y después de `FROM` cambia el comportamiento.

- **[17] cache_mounts** — [enunciado](ejercicios/07_BuildKit_y_Cache/17_cache_mounts/ENUNCIADO.md) · Aceleras compilaciones con caché persistente de BuildKit.
- **[18] build_args** — [enunciado](ejercicios/07_BuildKit_y_Cache/18_build_args/ENUNCIADO.md) · Parametrizas la versión de la imagen base con `ARG`.

### 📘 Bloque 08 — Escaneo y vulnerabilidades
> **Teoría:** [`teoria/08_Escaneo_y_Vulnerabilidades.md`](teoria/08_Escaneo_y_Vulnerabilidades.md)
> **Qué verás aquí:** qué es una CVE, cómo escanear imágenes (`docker scout`), las imágenes *distroless* y el endurecimiento de la superficie de ataque.

- **[19] distroless** — [enunciado](ejercicios/08_Escaneo_y_Vulnerabilidades/19_distroless/ENUNCIADO.md) · Imagen final sin shell ni gestor de paquetes (distroless).
- **[21] escaneo_cves** — [enunciado](ejercicios/08_Escaneo_y_Vulnerabilidades/21_escaneo_cves/ENUNCIADO.md) · Endureces una imagen (parches + no root) y la pasas por el escáner.

---

## FASE C — Operar contenedores

### 📘 Bloque 09 — Redes Docker
> **Teoría:** [`teoria/09_Redes_Docker.md`](teoria/09_Redes_Docker.md)
> **Qué verás aquí:** los drivers de red (`bridge`, `host`, `none`), el **DNS interno** de las redes definidas por el usuario y cómo conectar varios contenedores entre sí.

- **[22] redes_y_dns** — [enunciado](ejercicios/09_Redes_Docker/22_redes_y_dns/ENUNCIADO.md) · Dos contenedores que se ven por su **nombre** en una red propia.
- **[23] app_y_bbdd** — [enunciado](ejercicios/09_Redes_Docker/23_app_y_bbdd/ENUNCIADO.md) · Una app que se conecta a PostgreSQL por la red interna.

### 📘 Bloque 10 — Persistencia y datos
> **Teoría:** [`teoria/10_Persistencia_y_Datos.md`](teoria/10_Persistencia_y_Datos.md)
> **Qué verás aquí:** estrategias de persistencia de datos de verdad (bases de datos), volúmenes nombrados que sobreviven al contenedor y el manejo correcto de **secretos**.

- **[24] datos_persistentes** — [enunciado](ejercicios/10_Persistencia_y_Datos/24_datos_persistentes/ENUNCIADO.md) · Los datos de una BBDD sobreviven aunque destruyas el contenedor.
- **[25] secretos_env_file** — [enunciado](ejercicios/10_Persistencia_y_Datos/25_secretos_env_file/ENUNCIADO.md) · Inyectas un secreto en runtime con `--env-file`, nunca en la imagen.

### 📘 Bloque 11 — Logs y restart policies
> **Teoría:** [`teoria/11_Logs_y_Restart_Policies.md`](teoria/11_Logs_y_Restart_Policies.md)
> **Qué verás aquí:** el modelo de logging (stdout/stderr + log drivers), los `HEALTHCHECK` y las políticas de reinicio (`on-failure`, `always`, `unless-stopped`).

- **[12] healthcheck** — [enunciado](ejercicios/11_Logs_y_Restart_Policies/12_healthcheck/ENUNCIADO.md) · Declaras un `HEALTHCHECK` y ves al contenedor pasar a *healthy*. 💡 *(es el nº 12, pero su teoría vive aquí)*
- **[26] logs_y_drivers** — [enunciado](ejercicios/11_Logs_y_Restart_Policies/26_logs_y_drivers/ENUNCIADO.md) · Capturas logs con `docker logs` y configuras el log driver.
- **[27] restart_policies** — [enunciado](ejercicios/11_Logs_y_Restart_Policies/27_restart_policies/ENUNCIADO.md) · Una política de reinicio resucita un proceso que falla.

---

## FASE D — Orquestar con Compose

### 📘 Bloque 12 — Compose: fundamentos
> **Teoría:** [`teoria/12_Compose_Fundamentos.md`](teoria/12_Compose_Fundamentos.md)
> **Qué verás aquí:** pasar de `docker run` manual a describir la aplicación de forma **declarativa** en `compose.yaml`, y levantar varios servicios con un solo comando.

- **[28] primer_compose** — [enunciado](ejercicios/12_Compose_Fundamentos/28_primer_compose/ENUNCIADO.md) · Tu primer `compose.yaml` con un servicio.
- **[29] web_y_db** — [enunciado](ejercicios/12_Compose_Fundamentos/29_web_y_db/ENUNCIADO.md) · Dos servicios (web + db) cableados en el mismo Compose.

### 📘 Bloque 13 — Compose: avanzado
> **Teoría:** [`teoria/13_Compose_Avanzado.md`](teoria/13_Compose_Avanzado.md)
> **Qué verás aquí:** dependencias por salud (`service_healthy`), volúmenes y redes declarados, variables con `.env`, escalado de réplicas y `profiles`/`override`.

- **[30] depends_on_healthy** — [enunciado](ejercicios/13_Compose_Avanzado/30_depends_on_healthy/ENUNCIADO.md) · La web no arranca hasta que la db está *healthy*.
- **[31] volumenes_y_redes_compose** — [enunciado](ejercicios/13_Compose_Avanzado/31_volumenes_y_redes_compose/ENUNCIADO.md) · Volúmenes nombrados y redes propias en Compose.
- **[32] variables_env** — [enunciado](ejercicios/13_Compose_Avanzado/32_variables_env/ENUNCIADO.md) · Sacas la configuración a un `.env`.
- **[33] escalado_replicas** — [enunciado](ejercicios/13_Compose_Avanzado/33_escalado_replicas/ENUNCIADO.md) · Escalas un servicio a varias réplicas.
- **[34] override_y_profiles** — [enunciado](ejercicios/13_Compose_Avanzado/34_override_y_profiles/ENUNCIADO.md) · Servicios opcionales con `profiles`.

---

## FASE E — Distribuir y escalar

### 📘 Bloque 14 — Registries y distribución
> **Teoría:** [`teoria/14_Registries_y_Distribucion.md`](teoria/14_Registries_y_Distribucion.md)
> **Qué verás aquí:** dónde viven las imágenes (Docker Hub, registries privados), el ciclo `tag → push → pull`, el **versionado semántico** y las imágenes **multi-arquitectura** con `buildx`.

- **[20] versionado_semantico** — [enunciado](ejercicios/14_Registries_y_Distribucion/20_versionado_semantico/ENUNCIADO.md) · Un mismo ID con varios tags (`1.4.2`, `1.4`, `1`). 💡 *(es el nº 20, pero su teoría es esta)*
- **[35] registry_push_pull** — [enunciado](ejercicios/14_Registries_y_Distribucion/35_registry_push_pull/ENUNCIADO.md) · Subes y bajas una imagen de un registry local.
- **[36] buildx_multiarch** — [enunciado](ejercicios/14_Registries_y_Distribucion/36_buildx_multiarch/ENUNCIADO.md) · Publicas una imagen para `amd64` y `arm64` a la vez.

### 📘 Bloque 15 — Intro a Kubernetes
> **Teoría:** [`teoria/15_Intro_Kubernetes.md`](teoria/15_Intro_Kubernetes.md)
> **Qué verás aquí:** el salto a la orquestación: Pods, Deployments, Services, ConfigMaps, Secrets y rolling updates, practicado en un clúster local con `kind`.
> ⚙️ *Requiere `kind` y `kubectl` instalados.*

- **[38] kind_deployment_service** — [enunciado](ejercicios/15_Intro_Kubernetes/38_kind_deployment_service/ENUNCIADO.md) · Tu primer `Deployment` + `Service` en un clúster `kind`.
- **[39] k8s_config_secret_escalado** — [enunciado](ejercicios/15_Intro_Kubernetes/39_k8s_config_secret_escalado/ENUNCIADO.md) · ConfigMap, Secret, escalado y rolling update.

### 📘 Bloque 16 — El despliegue corporativo (Boss Final)
> **Teoría:** [`teoria/16_El_Despliegue_Corporativo.md`](teoria/16_El_Despliegue_Corporativo.md)
> **Qué verás aquí:** cómo encaja todo en una cadena de producción (imagen → Compose → registry → Kubernetes), las puertas de calidad y los límites honestos del entorno de prácticas.

- **[40] boss_final** — [enunciado](ejercicios/16_El_Despliegue_Corporativo/40_boss_final/ENUNCIADO.md) · 🏆 Integración total: imagen multi-stage no-root + healthcheck → stack Compose (proxy/api/cache/db) → despliegue en Kubernetes con ConfigMap y Secret. Si pasa en verde, eres **pro**.

---

## Resumen de un vistazo

| Bloque de teoría | Ejercicios |
|------------------|------------|
| 00 ¿Qué es un contenedor? | 01 |
| 01 Imágenes vs Contenedores | *(conceptual)* |
| 02 Anatomía del Dockerfile | 02 · 03 · 04 · 05 · 06 |
| 03 ENTRYPOINT vs CMD | 07 |
| 04 Buenas prácticas y seguridad | 08 · 09 · 10 · 11 · 14 · 37 |
| 05 Volúmenes (fundamentos) | 13 |
| 06 Multi-stage builds | 15 · 16 |
| 07 BuildKit y caché | 17 · 18 |
| 08 Escaneo y vulnerabilidades | 19 · 21 |
| 09 Redes Docker | 22 · 23 |
| 10 Persistencia y datos | 24 · 25 |
| 11 Logs y restart policies | 12 · 26 · 27 |
| 12 Compose: fundamentos | 28 · 29 |
| 13 Compose: avanzado | 30 · 31 · 32 · 33 · 34 |
| 14 Registries y distribución | 20 · 35 · 36 |
| 15 Intro a Kubernetes | 38 · 39 |
| 16 El despliegue corporativo | 40 |

> ¿Prefieres ir simplemente del 01 al 40 en orden? También funciona: la numeración es una ruta lineal válida. Esta guía es para quien quiere **entender el mapa** y leer cada teoría justo cuando toca.
