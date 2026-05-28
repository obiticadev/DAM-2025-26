# 🏁 Boss Final — Gestión de Usuarios (Nivel 14)

Proyecto Maven con 3 clases (modelo, repositorio, servicio) y sus tests JUnit.
Implementa los TODOs hasta que `mvn clean test` esté todo verde.

## Cómo verificar

```bash
cd ejercicios/nivel14_boss_final
mvn clean test
```

O desde la raíz del bootcamp:
```bash
bash scripts/verificar.sh 14
```

## Workflow recomendado (totalmente desde nvim)

```
cd ejercicios/nivel14_boss_final
nvim .

# Dentro:
<leader>e             explora la estructura
<leader>ft            terminal embebida → mvn clean test (todo rojo)
<leader><space>       fuzzy → abre src/main/java/.../modelo/Usuario.java
                      → implementa TODOs (Nivel 14.02)
<leader>tt            test individual (java-test) o mvn -Dtest=UsuarioTest
... repite con Repositorio, Servicio ...
<leader>gg            lazygit → commit final
```

## Estructura

```
nivel14_boss_final/
├── pom.xml
├── README_BOSS.md   (este archivo)
└── src/
    ├── main/java/com/bootcamp/finale/
    │   ├── modelo/Usuario.java                ← TODO
    │   ├── repositorio/UsuarioRepositorio.java  ← TODO
    │   └── servicio/UsuarioServicio.java      ← TODO
    └── test/java/com/bootcamp/finale/
        ├── modelo/UsuarioTest.java
        ├── repositorio/UsuarioRepositorioTest.java
        └── servicio/UsuarioServicioTest.java
```

## Restricción

- NO uses `Optional`, streams ni mapas auxiliares más allá de los tipos básicos.
- NO modifiques los tests. Son la verdad.
- Hazlo TODO con teclado dentro de nvim. El ratón está prohibido.

## Cierre

Cuando los 3 test suites estén verdes:

1. Stagea cambios con `<leader>gg` (lazygit).
2. Commit con mensaje `boss-final: implementación completa`.
3. (Opcional) `P` para push si tienes remoto.
4. Sustituye `BOSS_FINAL_COMPLETADO` por `OK` en `ej05_refactor_y_commit.md` para certificar.

¡Mucha suerte!
