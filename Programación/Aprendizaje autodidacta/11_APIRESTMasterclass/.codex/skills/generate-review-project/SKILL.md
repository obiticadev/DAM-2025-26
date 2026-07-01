---
name: generate-review-project
description: Generate an adaptive Java review project from this repository and the linked Google Sheet learning plan. Use when the user invokes $generate-review-project or asks for a recap/review project that combines completed sessions, updates the dedicated learning memory in proyectos_repaso, emphasizes weak points from student notes and real result fields, and creates a self-contained Maven project under proyectos_repaso.
---

# Generate Review Project

## Goal

Create one self-contained Java review project that combines the concepts the student has already completed, with extra emphasis on the points where the sheet notes or real result show discomfort, errors, omissions, low score, skipped work, or partial understanding.

All generated artifacts for this skill must live inside `proyectos_repaso/`. The dedicated memory is also there; do not store this skill's memory elsewhere.

## Required Inputs

- Google Sheet: `https://docs.google.com/spreadsheets/d/1FI0UpnT2YQAx89Cyi5KQXy1CTfnULCoacSbO5uYSXO8/edit?usp=sharing`
- Required sheet tab: `Sesiones`
- Repository blocks: folders such as `b01_*`, `b07_dto`, `b12_jpa`, etc.
- Dedicated memory: `proyectos_repaso/memoria_generate_review_project.md`

## Required References And Scripts

- Read [references/memory-schema.md](references/memory-schema.md) before creating or updating memory.
- Use `scripts/export-sessions.ps1` to download and snapshot the `Sesiones` tab when PowerShell/network access is available.
- Use `scripts/new-review-project.ps1` only as a scaffold helper; still customize the generated project deeply from the updated memory and the repository exercises.

## Workflow

1. Ensure `proyectos_repaso/` exists.
2. Read `proyectos_repaso/memoria_generate_review_project.md` if it exists. If not, create it using the schema reference.
3. Download or inspect the Google Sheet, always targeting `Sesiones`.
4. Identify completed sessions and the current values of the columns that represent:
   - session status/completion,
   - completed date/day,
   - module/block/exercises covered,
   - student notes,
   - real result/outcome,
   - score/comfort/complexity when present.
5. On every invocation, re-check at least student notes and real result for already-known completed sessions. These fields are volatile and must be reflected in memory even when the covered blocks did not change.
6. For new or changed completed sessions, inspect the related repository blocks and solved exercises:
   - read relevant `src/main/java`, `src/test/java`, `pom.xml`, resources, and database/migration files;
   - infer the concepts practiced, depth, tooling, expected level, and real difficulty;
   - compare the intended exercise/test behavior with the student's sheet notes and real result.
7. Update only the dedicated memory with a careful, dated, well-documented summary. Do not re-read every old block when the memory already contains a stable analysis and only notes/result changed.
8. Read the updated memory end to end.
9. Create a new review project under `proyectos_repaso/<date>_<Tema>/`.
10. Verify the project is self-contained for opening directly in VS Code:
    - include `pom.xml`;
    - include `src/main/java` and tests when useful;
    - include resources, SQL, migrations, or sample data when the learned content requires them;
    - include a short `README.md` with run/test commands and the exercise brief;
    - keep generated instructions and implementation appropriate to the content already completed.

## Review Project Rules

- Name the folder with the current day/month and a concise domain theme, for example `1_Jul_Supermercado`.
- If only terminal logic has been studied, build a terminal project. Do not add web UI, Spring MVC, databases, or APIs before the memory shows those topics are completed.
- Include all completed concept families at least lightly. Strong areas still appear in the project so the exercise feels integrated.
- Put extra tasks, validations, edge cases, tests, or design constraints around weak areas.
- Prefer realistic domains with connected requirements: supermarket, library, clinic, academy, warehouse, restaurant, bank, travel booking, task planner, etc.
- Make the project runnable and educational, not just a list of isolated katas.
- Do not copy solved exercises verbatim. Transform learned concepts into a new scenario.

## Difficulty Weighting

Treat these as weak-point signals:

- student note mentions doubt, confusion, slow progress, help needed, repeated attempts, fatigue, missing time, unclear concept, or "repasar";
- real result says partial, failed, skipped, incomplete, rough, blocked, needs correction, or similar;
- score below the usual baseline;
- completed tests passed but notes show low confidence;
- exercises with many moving parts: persistence, transactions, DTO mapping, validation, security, concurrency, process management, testing strategy, external configuration.

If nearly everything is scored highly, still create a broad project. Emphasize the few weaker concepts with deeper acceptance criteria.

## Memory Update Standard

Use the schema reference. Memory entries must be extensive enough to avoid re-reading all old sessions later, but organized enough to scan quickly. For each completed session, preserve:

- source session id/date/status;
- sheet note and real result, including last-seen date;
- blocks/exercises inspected;
- learned concepts and level;
- strengths;
- weak points and comfort signals;
- project-generation implications;
- evidence paths in the repository.

When notes/result changed, add a dated change note instead of overwriting useful history.

## Output Expectations

When finished, report:

- memory file updated or created;
- sessions newly analyzed and sessions whose notes/result changed;
- generated project folder;
- main weak points emphasized;
- how to run the project.
