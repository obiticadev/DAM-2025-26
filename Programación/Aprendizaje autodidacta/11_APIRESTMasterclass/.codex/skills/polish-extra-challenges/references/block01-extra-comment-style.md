# Block 01 Extra Challenge Comment Style

This reference is the source of truth for applying the `b01_java` extra-challenge comment style to another block. Do not re-open or re-analyze `b01_java`; use this distilled standard.

## Style Snapshot

The block-01 style has two layers:

1. Javadoc contract: formal, precise, self-contained description of what the extra challenge asks.
2. Inline guide: a short `// GUÍA:` paragraph that explains the implementation intention without spelling out every line of code.

The style is professional, calm, and instructional. It is less like a step-by-step recipe and more like a senior teacher explaining the invariant, contract, or design decision that matters.

## Scope Rule

Apply this style only to extra challenges:

- Javadoc titles containing `RETO EXTRA`, `Reto Extra`, or close variants.
- The comment block and inline guide immediately associated with that extra challenge method.

Do not edit base exercise TODO comments. In block 01, base methods still use numbered `TODO` steps; only extra challenges shift to the polished conceptual guide style.

## Canonical Structure

Use this shape for each extra challenge:

```java
/**
 * Reto Extra N: Nombre claro del reto.
 * Una o dos frases que expliquen el objetivo desde el punto de vista del dominio,
 * el contrato o el caso de uso real.
 *
 * @param entrada descripción precisa, sin repetir el tipo si no aporta
 * @return resultado observable y casos especiales relevantes
 */
public static Tipo metodo(...) {
    // GUÍA: explica la decisión central o el modelo mental del reto.
    // Añade una o dos líneas más si hace falta para cubrir nulos, orden,
    // inmutabilidad, errores, formato exacto o contrato observable.
    throw new UnsupportedOperationException("TODO: ...");
}
```

Keep the title format consistent within the target block. Prefer `Reto Extra N:` unless the file already consistently uses uppercase and changing it would create noisy diffs. Professional consistency is more important than preserving shouting case.

## Javadoc Rules

- Start with a concise title: `Reto Extra N: ...`.
- Use a domain-focused description, not a mechanical implementation checklist.
- Keep descriptions self-contained: the student should understand the task without opening theory files.
- Mention API, REST, persistence, validation, DTO, stream, time, exception, concurrency, or domain context when it makes the task more meaningful.
- Keep `@param` and `@return` accurate, specific, and testable.
- Include edge cases in `@return` only when they are part of the contract.
- Do not include long algorithm steps in Javadoc.
- Do not reference `b01_java`, this skill, or the fact that comments are being polished.

Good Javadoc tone:

```java
/**
 * Reto Extra 4: Contrato de correspondencia entre equals y hashCode.
 * Valida la regla fundamental: si dos objetos son iguales según equals(), deben
 * producir obligatoriamente el mismo hashCode().
 *
 * @param a primer objeto
 * @param b segundo objeto
 * @return true si los objetos cumplen el contrato de correspondencia hash
 */
```

```java
/**
 * Reto Extra 8: Deserialización a partir de línea de texto plana.
 * Reconstruye un DTO desde una fila con campos obligatorios, validando el formato
 * antes de delegar las reglas finales al constructor del modelo.
 *
 * @param csv fila de entrada con los campos esperados
 * @return DTO parseado a partir del contenido recibido
 */
```

## Inline `// GUÍA:` Rules

Use `// GUÍA:` once per extra challenge method, usually as the first comment inside the method body.

The guide should:

- Explain the idea central, not enumerate every instruction.
- Usually be 2-4 lines total.
- Mention relevant safeguards: nulls, blank strings, order, immutability, exact format, exception semantics, resource closing, idempotence, or async behavior.
- Encourage reasoning about the contract.
- Stay implementation-neutral unless a specific API is the learning target.

Prefer this:

```java
// GUÍA: no uses el equals del record: ese compara todos los componentes,
// incluido el id. Aquí la equivalencia es de negocio: nombre comparable
// sin distinguir mayúsculas y mismo precio. Trata los null de forma coherente.
```

Instead of this:

```java
// GUÍA: sigue estos pasos:
// 1. Comprueba null.
// 2. Haz trim.
// 3. Divide por coma.
// 4. Devuelve el tercer token.
// PISTA: usa split(",").
```

## Vocabulary Pattern

Useful openings:

- `GUÍA: valida...`
- `GUÍA: separa el problema en...`
- `GUÍA: respeta el contrato...`
- `GUÍA: conserva...`
- `GUÍA: trata este método como...`
- `GUÍA: el objetivo es...`
- `GUÍA: piensa en...`
- `GUÍA: protege... antes de...`
- `GUÍA: primero... después...`
- `GUÍA: no confundas... con...`

Professional concepts to use when accurate:

- contrato observable
- identidad de negocio
- equivalencia semántica
- salida estable
- caso de ausencia
- normalización
- validación defensiva
- copia inmutable
- formato textual
- orden de evaluación
- recurso garantizado
- causa raíz
- composición no bloqueante
- representación canónica

## What to Remove or Replace

When polishing blocks that look like block 00, remove or rewrite these patterns in extra challenges:

- `PISTA:` labels.
- `OJO:`, `CUIDADO:`, `RECUERDA:`, `CULTURA:` labels.
- Direct references to tests such as `Tests: ...`, `el test espera...`, `lee los tests`.
- Numbered algorithm recipes with many tiny steps.
- Informal language such as `jefe final`, jokes, exclamation-heavy warnings, or classroom chatter.
- Theory cross-references as the main instruction, for example `teoría 0.8`; the comment must stand on its own.
- Overly prescriptive Java snippets unless the API itself is the lesson.

Transform those ideas into a neutral guide. If a warning is important, keep the substance without the label:

Before:

```java
// OJO con el test: "GET http://google.com/ HTTP/1.1" debe dar false aunque
// la URL contenga '/'.
// PISTA: protege el acceso tokens[1].
```

After:

```java
// GUÍA: valida la ruta como el segundo componente de la línea de petición, no
// como cualquier texto que contenga una barra. Protege el acceso a posiciones
// opcionales antes de leerlas.
```

## Handling Existing Detail

When existing comments contain useful details, keep them in polished form:

- Convert exact expected formats into contract language.
- Convert test examples into neutral examples only if they clarify the contract.
- Convert API hints into conceptual nudges unless the exercise specifically teaches that API.
- Preserve subtle edge cases, but express them as behavior, not as test trivia.

Example transformation:

Before:

```java
// GUÍA: teoría 0.11 — el examen práctico de "stateless".
// Dos condiciones simultáneas:
// 1. HAY Authorization (no null, no en blanco).
// 2. NO hay cookie de sesión.
// Test: ("JSESSIONID=123", "Bearer abc") → false.
```

After:

```java
// GUÍA: una petición sin estado debe aportar sus credenciales en cada llamada
// y no depender de una cookie de sesión mantenida por el servidor. Evalúa ambas
// condiciones de forma independiente antes de aceptar el escenario.
```

## Length and Density

Target per extra challenge:

- Javadoc title: 1 line.
- Javadoc description: 1-3 lines.
- Tags: as many as required by signature.
- Inline guide: 2-4 lines, rarely more than 5.

Avoid bloating every method. Block 01 is detailed, but not noisy: it gives enough context to think, not a full solution manual.

## Formatting Rules

- Keep Java comment indentation exactly aligned with the surrounding code.
- Use ASCII arrows only if the file already uses them; otherwise prefer words.
- Keep Spanish accents if the file already uses Spanish comments with accents.
- Keep code identifiers in plain text or `{@code ...}` inside Javadoc when useful.
- Avoid markdown bullets inside Javadoc unless the file already uses them heavily.
- Wrap long comment lines to match the local style, usually around 100-120 characters.

## Preservation Rules

Never change:

- Method signatures.
- Method names.
- Return types.
- Existing implementation logic.
- `throw new UnsupportedOperationException(...)` messages.
- Imports, unless a formatter or compiler truly requires it and the user asked for code changes.
- Tests.

If an extra challenge already contains implementation code, do not rewrite the implementation. Only update the comments.

## Block-Level Consistency

When processing a whole block:

- Make titles consistent across all extra challenges.
- Use `Reto Extra` capitalization consistently.
- Use `// GUÍA:` consistently, not a mix of `GUIA`, `Pista`, `Ayuda`, etc.
- Keep terminology consistent inside the block. For example, do not alternate between `DTO`, `objeto de transferencia`, and `registro` unless the distinction matters.
- Make comments feel like one author revised the whole block.

## Review Checklist

Before finishing, verify:

- Only requested block files changed.
- Only extra challenge comments changed.
- No base `TODO` instructions were rewritten.
- No Java behavior changed.
- No `PISTA:`, `OJO:`, `CUIDADO:`, `CULTURA:`, `RECUERDA:`, or direct test references remain inside extra challenge comments unless the user explicitly asked to keep them.
- Each extra challenge has a clear Javadoc contract and a concise `// GUÍA:` comment.
- The result can be understood without opening block 01.
