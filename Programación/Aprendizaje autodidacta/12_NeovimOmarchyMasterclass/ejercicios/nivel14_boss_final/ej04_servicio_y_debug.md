# EJERCICIO 14.04 — Implementar UsuarioServicio + Debug

> Verifica: cd nivel14_boss_final && mvn test -Dtest=UsuarioServicioTest

## TODOs (en archivos Java)

Edita: `src/main/java/com/bootcamp/finale/servicio/UsuarioServicio.java`.

Sigue los TODOs 1-7. Cuando hayas implementado `registrar`, pon un breakpoint dentro
del método con `<leader>db`, ejecuta el test `cincoConsec` con `<leader>tt` en modo
DEBUG (configuración "Run with Debug"). Observa cómo el id auto-incrementa en cada
iteración.

```bash
mvn test -Dtest=UsuarioServicioTest
```

## TODOs de control

**TODO 1 (LIBRE):** Cuando `mvn test -Dtest=UsuarioServicioTest` esté en VERDE, sustituye `SERVICIO_VERDE` por `OK`.

**TODO 2 (LIBRE):** Si pusiste breakpoint y observaste el id incrementarse en debug, sustituye `DEBUG_OK` por `OK`. (Si solo confiaste en los tests sin debugear, escribe `SKIP`.)

---

REGISTROS
servicio_verde: SERVICIO_VERDE
debug: DEBUG_OK
