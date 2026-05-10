# 📋 SYLLABUS — Bootcamp XPath / XSLT / XQuery

> **Objetivo:** Alcanzar maestría práctica en XPath, XSLT y XQuery mediante 25 ejercicios modulares de dificultad progresiva + 1 Reto Final.
>
> **Fecha de creación:** 2026-05-10

---

## Variables del Proyecto

| Variable                     | Valor                                                          |
|------------------------------|----------------------------------------------------------------|
| Tecnología                   | Ecosistema XML (XPath 3.1, XSLT 1.0/2.0, XQuery 3.1, DOM/SAX) |
| Cantidad de ejercicios       | 25 + 1 Reto Final                                              |
| Entorno de ejecución         | Python 3 + `pytest` + `lxml` + `saxonche`                     |
| Sandbox visual               | `run_sandbox.py` (CLI)                                         |
| Tests                        | `pytest` en directorio `tests/`                                |

---

## Estructura de Carpetas

```
bootcamp-xpath-xslt-xquery/
├── README.md                     # Guía de instalación y uso
├── SYLLABUS.md                   # Este archivo — planning completo
├── run_sandbox.py                # Sandbox visual CLI
├── requirements.txt              # Dependencias Python
│
├── teoria/
│   ├── 01_XPath.md               # Resumen ultrarrápido XPath
│   ├── 02_XSLT.md                # Resumen ultrarrápido XSLT
│   ├── 03_XQuery.md              # Resumen ultrarrápido XQuery
│   └── 04_DOM_SAX.md             # Resumen ultrarrápido DOM vs SAX
│
├── src/
│   ├── xml_data/                 # XMLs fuente (bases de datos)
│   │   ├── catalogo.xml
│   │   ├── empleados.xml
│   │   ├── biblioteca.xml
│   │   ├── inventario.xml
│   │   ├── tienda.xml
│   │   ├── pedidos.xml
│   │   ├── autores.xml
│   │   └── erp_*.xml             # XMLs del Reto Final
│   │
│   └── ejercicios/
│       ├── xpath/                # Ejercicios XPath (.xpath)
│       │   ├── ej01_rutas_basicas.xpath
│       │   ├── ej02_atributos.xpath
│       │   ├── ej03_ejes.xpath
│       │   ├── ej04_predicados_posicion.xpath
│       │   ├── ej05_predicados_compuestos.xpath
│       │   ├── ej06_funciones_numericas.xpath
│       │   ├── ej07_funciones_cadena.xpath
│       │   └── ej08_funciones_avanzadas.xpath
│       │
│       ├── xslt/                 # Ejercicios XSLT (.xsl)
│       │   ├── ej09_plantilla_basica.xsl
│       │   ├── ej10_foreach_sort.xsl
│       │   ├── ej11_condicionales.xsl
│       │   ├── ej12_variables_parametros.xsl
│       │   ├── ej13_modos_named.xsl
│       │   ├── ej14_salida_csv.xsl
│       │   ├── ej15_elementos_dinamicos.xsl
│       │   ├── ej16_integrador_inventario.xsl
│       │   └── ej17_apply_templates.xsl
│       │
│       └── xquery/               # Ejercicios XQuery (.xq)
│           ├── ej18_ruta_simple.xq
│           ├── ej19_flwor_basico.xq
│           ├── ej20_flwor_let.xq
│           ├── ej21_funciones_cadena.xq
│           ├── ej22_agregacion.xq
│           ├── ej23_joins.xq
│           ├── ej24_xquf.xq
│           ├── ej25_prologo_funciones.xq
│           └── reto_final/
│               ├── reto_join.xq
│               ├── reto_update.xq
│               └── reto_dashboard.xsl
│
└── tests/
    ├── conftest.py               # Fixtures compartidos (pytest)
    ├── test_xpath.py             # Tests para ejercicios 01-08
    ├── test_xslt.py             # Tests para ejercicios 09-17
    ├── test_xquery.py           # Tests para ejercicios 18-25
    └── test_reto_final.py       # Tests para el Reto Final
```

---

## Tabla de Ejercicios por Bloque

### BLOQUE I — XPath (Ejercicios 1-8)

| #  | Archivo                          | Concepto clave                                | XML fuente         | Progreso |
|----|----------------------------------|-----------------------------------------------|--------------------|----------|
| 01 | `ej01_rutas_basicas.xpath`       | Rutas absolutas y relativas, `/` vs `//`      | `catalogo.xml`     | [ ]      |
| 02 | `ej02_atributos.xpath`           | Acceso a atributos con `@`, `@*`              | `catalogo.xml`     | [ ]      |
| 03 | `ej03_ejes.xpath`                | Ejes: `child`, `descendant`, `parent`, `following-sibling` | `tienda.xml` | [ ]      |
| 04 | `ej04_predicados_posicion.xpath` | Predicados por posición: `[1]`, `[last()]`, `position()` | `catalogo.xml` | [ ]      |
| 05 | `ej05_predicados_compuestos.xpath` | Predicados `and`/`or`/`not`, comparaciones  | `catalogo.xml`     | [ ]      |
| 06 | `ej06_funciones_numericas.xpath` | `count()`, `sum()`, `round()`, `floor()`, `ceiling()` | `catalogo.xml` | [ ]      |
| 07 | `ej07_funciones_cadena.xpath`    | `contains()`, `starts-with()`, `string-length()`, `normalize-space()`, `concat()` | `empleados.xml` | [ ]      |
| 08 | `ej08_funciones_avanzadas.xpath` | `translate()`, `substring()`, predicados con funciones combinadas | `empleados.xml` | [ ]      |

---

### BLOQUE II — XSLT (Ejercicios 9-17)

| #  | Archivo                          | Concepto clave                                | XML fuente         | Progreso |
|----|----------------------------------|-----------------------------------------------|--------------------|----------|
| 09 | `ej09_plantilla_basica.xsl`      | Estructura XSLT, `xsl:template match="/"`, `xsl:value-of` | `catalogo.xml` | [ ]      |
| 10 | `ej10_foreach_sort.xsl`          | `xsl:for-each`, `xsl:sort` (text/number, asc/desc) | `catalogo.xml` | [ ]      |
| 11 | `ej11_condicionales.xsl`         | `xsl:if`, `xsl:choose/when/otherwise`         | `empleados.xml`    | [ ]      |
| 12 | `ej12_variables_parametros.xsl`  | `xsl:variable`, `xsl:param`, `$` referencia, IVA | `catalogo.xml` | [ ]      |
| 13 | `ej13_modos_named.xsl`           | `mode="resumen"/"detalle"`, `xsl:call-template`, `name=""` | `catalogo.xml` | [ ]      |
| 14 | `ej14_salida_csv.xsl`            | `method="text"`, `xsl:text`, `&#10;`, salida CSV | `empleados.xml` | [ ]      |
| 15 | `ej15_elementos_dinamicos.xsl`   | `xsl:element`, `xsl:attribute`, `{}` AVT      | `inventario.xml`   | [ ]      |
| 16 | `ej16_integrador_inventario.xsl` | Integrador: param + sort + choose + named template + footer | `inventario.xml` | [ ]      |
| 17 | `ej17_apply_templates.xsl`       | `xsl:apply-templates` vs `for-each`, plantillas múltiples | `biblioteca.xml` | [ ]      |

---

### BLOQUE III — XQuery (Ejercicios 18-25)

| #  | Archivo                          | Concepto clave                                | XML fuente         | Progreso |
|----|----------------------------------|-----------------------------------------------|--------------------|----------|
| 18 | `ej18_ruta_simple.xq`            | `doc()`, rutas XPath en XQuery, expresiones simples | `biblioteca.xml` | [ ]      |
| 19 | `ej19_flwor_basico.xq`           | FLWOR: `for`, `where`, `order by`, `return`   | `biblioteca.xml`   | [ ]      |
| 20 | `ej20_flwor_let.xq`              | `let` vs `for`, variables locales, cálculos   | `biblioteca.xml`   | [ ]      |
| 21 | `ej21_funciones_cadena.xq`       | `contains()`, `lower-case()`, `starts-with()`, `matches()` | `biblioteca.xml` | [ ]      |
| 22 | `ej22_agregacion.xq`             | `count()`, `sum()`, `avg()`, `min()`, `max()`, estadísticas | `biblioteca.xml` | [ ]      |
| 23 | `ej23_joins.xq`                  | Join entre dos documentos, `exists()`, `empty()` | `biblioteca.xml` + `autores.xml` | [ ]      |
| 24 | `ej24_xquf.xq`                   | XQUF: `insert node`, `delete node`, `replace value of`, `rename` | `biblioteca.xml` | [ ]      |
| 25 | `ej25_prologo_funciones.xq`      | Prólogo, `declare function`, tipos XSD, `if-then-else` | `biblioteca.xml` | [ ]      |

---

### BLOQUE IV — Reto Final 🏆 "El ERP del Boss Final"

| #  | Archivo                          | Concepto clave                                | XML fuente         | Progreso |
|----|----------------------------------|-----------------------------------------------|--------------------|----------|
| 26a | `reto_join.xq`                  | Join XQuery cruzando `erp_productos.xml` + `erp_proveedores.xml` | `erp_*.xml` | [ ]      |
| 26b | `reto_update.xq`                | XQUF: Actualizar stock, insertar pedido, borrar obsoletos | `erp_*.xml` | [ ]      |
| 26c | `reto_dashboard.xsl`            | XSLT Dashboard HTML: parámetros, variables, modos, sort, condicionales | resultado XQuery | [ ]      |

---

## Teoría (resúmenes ultrarrápidos)

| Archivo              | Contenido                                                        | Progreso |
|----------------------|------------------------------------------------------------------|----------|
| `01_XPath.md`        | Árbol de nodos, rutas, ejes, predicados, funciones. Diagramas Mermaid. | [ ]      |
| `02_XSLT.md`        | Procesador, plantillas, for-each, sort, condicionales, variables, modos, output. Diagramas Mermaid. | [ ]      |
| `03_XQuery.md`       | FLWOR, funciones, joins, XQUF, prólogo. Diagramas Mermaid.      | [ ]      |
| `04_DOM_SAX.md`      | Tabla comparativa, casos de uso, analogías. Diagrama Mermaid.    | [ ]      |

---

## Plan de Generación (por bloques, según Regla 5)

| Fase | Contenido a generar                                          | Estado  |
|------|--------------------------------------------------------------|---------|
| 0    | `SYLLABUS.md` + `README.md` + `requirements.txt` + `run_sandbox.py` | ⏳ En curso |
| 1    | Bloque I — Configuración Base + XPath (8 ejercicios + tests + teoría) | [ ] Pendiente |
| 2    | Bloque II — XSLT (9 ejercicios + tests + teoría)            | [ ] Pendiente |
| 3    | Bloque III — XQuery (8 ejercicios + tests + teoría)          | [ ] Pendiente |
| 4    | Bloque IV — Reto Final (3 partes + tests)                    | [ ] Pendiente |

---

> **⚠️ REGLA 5:** Cada bloque se genera y se revisa antes de continuar con el siguiente.
> Escribe **"siguiente"** para autorizar la construcción de cada fase.
