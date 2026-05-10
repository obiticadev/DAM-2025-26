# 🧪 Bootcamp XPath · XSLT · XQuery

> Masterclass autodidacta — 25 ejercicios + 1 Reto Final  
> Práctica directa con validación automática por tests

---

## Requisitos previos

- **Python 3.10+** → [Descargar](https://www.python.org/downloads/)
- **pip** (viene con Python 3)

---

## Instalación paso a paso

### 1. Clonar / Descargar el proyecto

```bash
# Si usas Git:
git clone <url-del-repo>
cd bootcamp-xpath-xslt-xquery

# O simplemente abre la carpeta descargada en tu terminal.
```

### 2. Crear un entorno virtual (recomendado)

```bash
python -m venv venv

# Activar en Windows:
venv\Scripts\activate

# Activar en Linux/macOS:
source venv/bin/activate
```

### 3. Instalar dependencias

```bash
pip install -r requirements.txt
```

Esto instalará:

| Paquete     | Versión | Para qué                                   |
|-------------|---------|---------------------------------------------|
| `pytest`    | ≥7.0    | Ejecutar los tests de validación            |
| `lxml`      | ≥5.0    | Procesar XML, evaluar XPath, aplicar XSLT   |
| `saxonche`  | ≥12.0   | Procesador Saxon-HE para XQuery y XSLT 2.0  |

---

## Cómo usar el proyecto

### 🔧 Flujo de trabajo

```
1. Lee la teoría        → teoria/01_XPath.md (etc.)
2. Abre el ejercicio    → src/ejercicios/xpath/ej01_rutas_basicas.xpath
3. Completa los TODOs   → escribe tus expresiones/plantillas/consultas
4. Prueba visualmente   → python run_sandbox.py <xml> <archivo>
5. Pasa los tests       → pytest tests/test_xpath.py -v
6. Repite               → siguiente ejercicio
```

### 🎯 Sandbox visual — `run_sandbox.py`

El sandbox te permite ejecutar tu archivo XSLT o XQuery contra un XML y ver el resultado en consola **sin necesidad de pasar tests**.

```bash
# Ejecutar una transformación XSLT
python run_sandbox.py src/xml_data/catalogo.xml src/ejercicios/xslt/ej09_plantilla_basica.xsl

# Ejecutar una consulta XQuery
python run_sandbox.py src/xml_data/biblioteca.xml src/ejercicios/xquery/ej19_flwor_basico.xq

# Evaluar expresiones XPath interactivamente
python run_sandbox.py src/xml_data/catalogo.xml --xpath "//libro/titulo/text()"
```

### ✅ Ejecutar tests

```bash
# Todos los tests
pytest tests/ -v

# Solo un bloque
pytest tests/test_xpath.py -v
pytest tests/test_xslt.py -v
pytest tests/test_xquery.py -v

# Solo un ejercicio concreto
pytest tests/test_xpath.py::test_ej01_rutas_basicas -v

# Con colores y resumen corto
pytest tests/ -v --tb=short
```

---

## Estructura del proyecto

```
bootcamp-xpath-xslt-xquery/
├── README.md              ← Estás aquí
├── SYLLABUS.md            ← Planning y checkboxes de progreso
├── run_sandbox.py         ← Sandbox visual CLI
├── requirements.txt       ← Dependencias Python
├── teoria/                ← Resúmenes teóricos con diagramas Mermaid
├── src/
│   ├── xml_data/          ← Ficheros XML fuente
│   └── ejercicios/        ← Tus archivos de trabajo (.xpath, .xsl, .xq)
└── tests/                 ← Tests automáticos (NO los modifiques)
```

---

## Tips

- ⚠️ **No modifiques los archivos de `tests/` ni de `src/xml_data/`.** Solo trabaja en `src/ejercicios/`.
- 💡 Los archivos `.xpath` contienen una expresión por línea. Cada línea corresponde a un apartado (a, b, c...).
- 💡 Los archivos `.xsl` tienen esqueletos con `<!-- TODO -->`. Completa dentro de las plantillas.
- 💡 Los archivos `.xq` tienen esqueletos con `(: TODO :)`. Completa el cuerpo de la consulta.
- 🔄 Los tests XQUF copian el XML original a un temporal antes de ejecutar, así puedes repetir infinitas veces.

---

**¡Buena suerte en el examen! 💪**
