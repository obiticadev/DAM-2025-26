# 🎓 SYLLABUS — Bootcamp Práctico Autodidacta de PL/SQL

> **Objetivo:** Dominar las bases fundamentales de PL/SQL para aprobar el examen y ser completamente autónomo.
> **Entorno:** OracleDB · DBeaver conectado a una VM.
> **Tablas de trabajo:** `sedes`, `ventas`, `items`.

---

## 📋 Hoja de Ruta

| Estado | Bloque | Tema | Ejercicios | Tests |
|:------:|:------:|------|:----------:|:-----:|
| `[ ]` | **00** | Setup — Creación de tablas y datos semilla | `00_setup/init_db.sql` | — |
| `[ ]` | **01** | Fundamentos: Variables, Tipos y Condicionales | `ejercicios/B01_fundamentos.sql` | `tests/T01_fundamentos.sql` |
| `[ ]` | **02** | Estructuras de Control: Bucles (LOOP, FOR, WHILE) | `ejercicios/B02_bucles.sql` | `tests/T02_bucles.sql` |
| `[ ]` | **03** | Interacción con la BD: SELECT INTO, %TYPE, %ROWTYPE | `ejercicios/B03_select_into.sql` | `tests/T03_select_into.sql` |
| `[ ]` | **04** | Tipos Compuestos: Registros (RECORD) | `ejercicios/B04_records.sql` | `tests/T04_records.sql` |
| `[ ]` | **05** | Tipos Compuestos: Arrays (VARRAY) | `ejercicios/B05_varray.sql` | `tests/T05_varray.sql` |
| `[ ]` | **06** | Cursores (explícitos, FOR implícito, REF CURSOR) | `ejercicios/B06_cursores.sql` | `tests/T06_cursores.sql` |
| `[ ]` | **07** | Excepciones (predefinidas, de usuario, PRAGMA) | `ejercicios/B07_excepciones.sql` | `tests/T07_excepciones.sql` |
| `[ ]` | **08** | Procedimientos (anónimos y almacenados, IN/OUT) | `ejercicios/B08_procedimientos.sql` | `tests/T08_procedimientos.sql` |
| `[ ]` | **09** | Funciones (locales, almacenadas, uso en SQL) | `ejercicios/B09_funciones.sql` | `tests/T09_funciones.sql` |
| `[ ]` | **10** | Paquetes — Integrador (especificación + cuerpo) | `ejercicios/B10_paquetes.sql` | `tests/T10_paquetes.sql` |
| `[ ]` | **SIM** | 🏆 Simulacro de Examen Final | `ejercicios/B11_simulacro_examen.sql` | `tests/T11_simulacro_examen.sql` |

---

## 📂 Estructura del Proyecto

```
PLSQL_Masterclass/
├── SYLLABUS.md                      ← Estás aquí
├── 00_setup/
│   └── init_db.sql                  ← Creación de tablas y datos semilla
├── teoria/
│   ├── T01_fundamentos.md           ← Variables, tipos, IF
│   ├── T02_bucles.md                ← LOOP, FOR, WHILE
│   ├── T03_select_into.md           ← SELECT INTO, %TYPE, %ROWTYPE
│   ├── T04_records.md               ← TYPE IS RECORD
│   ├── T05_varray.md                ← VARRAY, EXTEND, métodos
│   ├── T06_cursores.md              ← Cursores explícitos, FOR, REF CURSOR
│   ├── T07_excepciones.md           ← Excepciones predefinidas y de usuario
│   ├── T08_procedimientos.md        ← Procedimientos IN/OUT
│   ├── T09_funciones.md             ← Funciones y RETURN
│   └── T10_paquetes.md              ← Paquetes: especificación y cuerpo
├── ejercicios/
│   ├── B01_fundamentos.sql          ← Esqueletos con TODO
│   ├── B02_bucles.sql
│   ├── B03_select_into.sql
│   ├── B04_records.sql
│   ├── B05_varray.sql
│   ├── B06_cursores.sql
│   ├── B07_excepciones.sql
│   ├── B08_procedimientos.sql
│   ├── B09_funciones.sql
│   ├── B10_paquetes.sql
│   └── B11_simulacro_examen.sql
└── tests/
    ├── T01_fundamentos.sql          ← Validación con RAISE_APPLICATION_ERROR
    ├── T02_bucles.sql
    ├── T03_select_into.sql
    ├── T04_records.sql
    ├── T05_varray.sql
    ├── T06_cursores.sql
    ├── T07_excepciones.sql
    ├── T08_procedimientos.sql
    ├── T09_funciones.sql
    ├── T10_paquetes.sql
    └── T11_simulacro_examen.sql
```

---

## 🧭 Flujo de Trabajo Recomendado

1. Ejecuta `00_setup/init_db.sql` en DBeaver para crear las tablas.
2. Lee la teoría del bloque correspondiente en `teoria/`.
3. Abre el archivo de `ejercicios/` y rellena los `-- TODO:`.
4. Ejecuta el test correspondiente en `tests/` para validar.
5. Si el test pasa → marca `[x]` en la tabla de arriba y pasa al siguiente bloque.
6. Si el test falla → revisa la teoría y vuelve a intentar.

---

## 📌 Cheat Sheet Rápido de Conceptos

| Concepto | Cuándo usarlo |
|----------|---------------|
| `SELECT INTO` | Esperas **exactamente 1 fila** |
| `Cursor` | Esperas **0 o más filas** |
| `%TYPE` | Declarar variable del tipo de **una columna** |
| `%ROWTYPE` | Declarar variable del tipo de **toda una fila** |
| `RECORD` | Tipo compuesto **personalizado** (tú defines campos) |
| `VARRAY` | Colección **ordenada y acotada** de elementos |
| `Procedimiento` | Lógica reutilizable que **ejecuta acciones** |
| `Función` | Lógica reutilizable que **devuelve un valor** |
| `Paquete` | **Agrupa** procedimientos, funciones, tipos y variables |
| `EXCEPTION` | **Capturar errores** para control del flujo |

---

*Última actualización: mayo 2026*
