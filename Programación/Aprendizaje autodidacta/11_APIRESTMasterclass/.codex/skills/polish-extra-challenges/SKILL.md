---
name: polish-extra-challenges
description: Professionalize and normalize Java exercise comments for a requested masterclass block such as b08, b12, or b24, applying the established block-01 style only to "Reto Extra" sections. Use when the user invokes this skill and names a block folder, asks to upgrade/polish/standardize extra challenge comments, or wants comments brought to the professional level of b01_java without re-reading b01_java.
---

# Polish Extra Challenges

## Goal

Rewrite only the comments that belong to extra challenges in a named exercise block so they match the professional style distilled from `b01_java`: clear Javadoc contracts plus concise conceptual `// GUÍA:` comments. Do not inspect `b01_java` during normal use; load the bundled reference instead.

## Required Reference

Before editing, read [references/block01-extra-comment-style.md](references/block01-extra-comment-style.md). It contains the complete style model extracted from block 01, including structure, tone, examples, forbidden patterns, and a review checklist.

## Workflow

1. Identify the requested block folder from the user text, for example `b08`, `b08_valid`, `b12_jpa`, or `b24_boss`.
2. Locate the matching block directory in the current workspace. If the user gives a short name like `b08`, select the single directory whose name starts with that prefix.
3. Work only in `src/main/java` Java exercise files unless the user explicitly says otherwise.
4. Identify only extra challenge sections. Treat a section as in scope when its Javadoc title contains `RETO EXTRA`, `Reto Extra`, or a close variant.
5. For each in-scope extra challenge:
   - Preserve method signatures, code, thrown TODO exceptions, imports, package names, class names, and test behavior.
   - Rewrite the Javadoc title and description when needed for professional clarity.
   - Preserve accurate `@param`, `@return`, `@throws`, and generic type tags; improve wording but do not invent behavior.
   - Replace overly procedural hints with a compact conceptual `// GUÍA:` block following the reference style.
6. Do not change comments for the base exercise TODOs unless they are inside an extra challenge section.
7. Do not solve the exercises. This skill edits instructional comments only.
8. After editing, review the diff and verify that only comments/Javadoc changed in extra challenge regions.

## Editing Boundaries

Allowed:
- Javadoc text for extra challenge methods/classes.
- `// GUÍA:` comments immediately inside extra challenge methods.
- Spelling, grammar, terminology, formatting, and professionalism of comments.

Not allowed:
- Main exercise TODO comments outside extra challenges.
- Java logic, return values, exception types, method signatures, tests, POMs, generated HTML, theory files, or unrelated blocks.
- Adding references that require future agents to inspect `b01_java`.

## Output Expectations

When finished, report:
- The block processed.
- The files touched.
- Confirmation that only extra challenge comments were modified.
- Any files skipped because no extra challenges were found.
