# EJERCICIO 13.01 — Setup jdtls + DAP + java-test

> Teoría:   teoria/13_Java_JDTLS_DAP.md (sección 2)
> Verifica: bash scripts/verificar.sh 13 01

## Comandos a ejecutar

Desde terminal:
```bash
java -version          # debe ser >= 21
mvn -version
cd "01_NeovimOmarchyMasterclass"
mvn -q clean compile   # debe ser exit 0
```

Dentro de nvim:
```vim
:LazyExtras            " activa lang.java si no lo está
:Lazy sync
:MasonInstall jdtls java-debug-adapter java-test google-java-format
" abre un .java cualquiera del nivel 13 para que jdtls arranque:
" :e ejercicios/nivel13_java_jdtls_dap/ej02_navegacion_java.java
" Espera 30-60s a que jdtls indexe. Luego:
:LspInfo               " 'jdtls' debe aparecer activo
```

## TODOs

**TODO 1 (con pista):** Verifica `java -version`. Anota la versión mayor.menor en `JAVA_VER` (ej: `21`).

**TODO 2 (con pista):** Verifica `mvn -q clean compile` desde la raíz del bootcamp. Si funciona sin errores, sustituye `MVN_COMPILE` por `OK`.

**TODO 3 (con pista):** En nvim, ejecuta `:LspInfo` con un `.java` abierto. Si `jdtls` aparece y NO está marcado como "stopped", sustituye `JDTLS_ACTIVO` por `OK`.

**TODO 4 (LIBRE):** Confirma que tienes `java-debug-adapter` y `java-test` instalados en Mason. Sustituye `MASON_DAP_TEST` por `OK`.

**TODO 5 (LIBRE):** Guarda y sal.

---

REGISTROS
java_ver: JAVA_VER
mvn_compile: MVN_COMPILE
jdtls: JDTLS_ACTIVO
mason_dap_test: MASON_DAP_TEST
