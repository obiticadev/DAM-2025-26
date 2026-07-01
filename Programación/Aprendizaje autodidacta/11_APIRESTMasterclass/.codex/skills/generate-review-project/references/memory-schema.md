# Dedicated Memory Schema

The memory file must live at `proyectos_repaso/memoria_generate_review_project.md`.

## Header

Include:

- skill name and purpose;
- Google Sheet URL;
- last synchronization date/time;
- last generated review project;
- short warning that this memory belongs only to `$generate-review-project`.

## Current Student Profile

Maintain a concise but useful synthesis:

- strongest concept families;
- fragile concept families;
- recurring note/result patterns;
- preferred project difficulty;
- content not yet allowed because it has not been completed.

## Session Index

Use one subsection per completed session.

Recommended shape:

```markdown
### Session <id> - <date or day> - <status>

- Last checked: <date>
- Sheet fields:
  - Notes: <verbatim or faithful summary>
  - Real result: <verbatim or faithful summary>
  - Score/comfort/complexity: <value if present>
- Repository evidence:
  - <block/path>
  - <exercise/test path>
- Concepts learned:
  - <concept>: <observed level>
- Strengths:
  - <specific behavior>
- Weak points:
  - <specific behavior>
- Project implications:
  - <how future generated projects should practice this>
- Change history:
  - <date>: <what changed in notes/result/status>
```

## Concept Map

Group learned material by concept family. Examples:

- Java fundamentals and control flow
- Collections and generics
- Exceptions and validation
- Testing with JUnit/AssertJ/Mockito
- DTOs and mapping
- Spring Boot structure
- Persistence and JPA
- Transactions and database behavior
- REST/API behavior
- Security
- Concurrency/processes

For each family, record:

- completed evidence;
- current level;
- comfort;
- weak subtopics;
- suitable review-project tasks.

## Review Project History

Append one entry per generated project:

```markdown
### <folder name> - <date>

- Theme:
- Main concepts included:
- Weak points emphasized:
- Generated files:
- Run/test command:
- Follow-up recommendation:
```

## Update Policy

- Do not erase past notes unless they were clearly wrong.
- Add dated changes when student notes or real result change.
- Avoid re-reading stable historical blocks when memory already contains enough evidence.
- Re-open repository files when a completed session is new, changed, ambiguous, or under-documented.
- Keep enough detail that a future call can generate a good project without loading every old session.
