# Transformación de Relaciones E/R al Modelo Relacional

A continuación se detalla el proceso de conversión para los tipos de relaciones más comunes, utilizando plantillas visuales genéricas en HTML para máxima compatibilidad y claridad.

## 1. Relaciones Binarias

Son relaciones entre dos entidades distintas.

---

### **1.1. Relación 1:1 (Uno a Uno)**

La estrategia de transformación depende de la participación (si es obligatoria `1` u opcional `0`).

#### **Caso A: Mandatorio en ambos lados (1,1 a 1,1)**

> **Regla de Oro:** Se puede elegir cualquiera de las dos entidades y propagar la clave `🔑` y los atributos de la relación a la otra. La nueva Clave Foránea **debe tener una restricción `UNIQUE`** para garantizar que la relación siga siendo 1 a 1.
> *(Nota: Una alternativa para optimizar es fusionar ambas tablas en una, pero mantenerlas separadas también es una solución válida).*

##### ANTES (Componentes Originales)
<br>
<div style="display: flex; align-items: flex-start; justify-content: flex-start; gap: 15px; flex-wrap: wrap;">
<!-- Entidad 1 -->
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
    <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ENTIDAD 1</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>identificador1</strong> (PK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">atributo1a</td> </tr>
    <tr> <td style="padding: 8px;">atributo1b</td> </tr>
</table>
<!-- Conector -->
<div style="text-align: center; padding-top: 40px; color: #718096;"><span style="font-family: monospace; font-size: 0.9em;">(1,1)</span><br><span style="font-size: 2.5em;">→</span></div>
<!-- Relación -->
<div style="text-align: center;"><div style="font-weight: bold; color: #2c7a7b; margin-bottom: 5px; font-size: 0.9em;">RELACIÓN</div><table style="width: auto; border: 2px solid #285e61; border-collapse: collapse;"><caption style="background-color: #2c7a7b; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 2px solid #285e61; border-bottom: none;">RELACIONA</caption><tr> <td style="padding: 8px;">atributo_relacion1</td> </tr></table></div>
<!-- Conector -->
<div style="text-align: center; padding-top: 40px; color: #718096;"><span style="font-family: monospace; font-size: 0.9em;">(1,1)</span><br><span style="font-size: 2.5em;">→</span></div>
<!-- Entidad 2 -->
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ENTIDAD 2</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>identificador2</strong> (PK)</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">atributo2a</td> </tr><tr> <td style="padding: 8px;">atributo2b</td> </tr></table>
</div>

##### DESPUÉS (Propagación de Clave)
*En esta solución, se propaga la clave de `ENTIDAD 1` a `ENTIDAD 2`. La relación desaparece.*
<br>
<div style="display: flex; align-items: flex-start; justify-content: flex-start; gap: 20px; flex-wrap: wrap;">
<!-- Tabla Entidad 1 Final -->
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ENTIDAD 1</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>identificador1</strong> (PK)</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">atributo1a</td> </tr><tr> <td style="padding: 8px;">atributo1b</td> </tr></table>
<!-- Tabla Entidad 2 Final -->
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ENTIDAD 2</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>identificador2</strong> (PK)</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">atributo2a</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">atributo2b</td> </tr><tr style="border-bottom: 1px solid #555;"><td style="padding: 8px;"><span style="font-size:0.8em; color:#999;"><em>de ENTIDAD 1</em> ➔</span> <strong style="background-color:#4a5568; color:#e2e8f0; padding: 2px 6px; border-radius:4px;">🔗 identificador1 (FK, UNIQUE)</strong></td></tr><tr><td style="padding: 8px;"><span style="font-size:0.8em; color:#999;"><em>de RELACIONA</em> ➔</span> <strong style="background-color:#4a5568; color:#e2e8f0; padding: 2px 6px; border-radius:4px;">atributo_relacion1</strong></td></tr></table>
</div>

---

#### **Caso B: Opcional en uno o ambos lados (p. ej. 0,1 a 0,1)**

> **Regla de Oro:** Si un lado es opcional `(0,1)`, se propaga la clave de la entidad con participación obligatoria `(1,1)` a la opcional. Si ambos son opcionales, la solución más limpia es crear una **nueva tabla** para la relación.

##### ANTES (Componentes Originales)
<br>
<div style="display: flex; align-items: flex-start; justify-content: flex-start; gap: 15px; flex-wrap: wrap;">
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ENTIDAD 1</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>identificador1</strong> (PK)</td> </tr><tr> <td style="padding: 8px;">atributo1</td> </tr></table>
<div style="text-align: center; padding-top: 40px; color: #718096;"><span style="font-family: monospace; font-size: 0.9em;">(0,1)</span><br><span style="font-size: 2.5em;">→</span></div>
<div style="text-align: center;"> <div style="font-weight: bold; color: #2c7a7b; margin-bottom: 5px; font-size: 0.9em;">RELACIÓN</div> <table style="width: auto; border: 2px solid #285e61; border-collapse: collapse;"> <caption style="background-color: #2c7a7b; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 2px solid #285e61; border-bottom: none;">RELACIONA</caption> <tr> <td style="padding: 8px;">atributo_relacion1</td> </tr> </table></div>
<div style="text-align: center; padding-top: 40px; color: #718096;"><span style="font-family: monospace; font-size: 0.9em;">(0,1)</span><br><span style="font-size: 2.5em;">→</span></div>
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ENTIDAD 2</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>identificador2</strong> (PK)</td> </tr><tr> <td style="padding: 8px;">atributo2</td> </tr></table>
</div>

##### DESPUÉS (Creación de una Tabla Intermedia)
*La relación se convierte en su propia tabla para manejar la opcionalidad de ambos lados.*
<br>
<div style="display: flex; align-items: flex-start; justify-content: flex-start; gap: 20px; flex-wrap: wrap;">
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ENTIDAD 1</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>identificador1</strong> (PK)</td> </tr><tr> <td style="padding: 8px;">atributo1</td> </tr></table>
<table style="width: auto; border: 2px solid #285e61; border-collapse: collapse;"><caption style="background-color: #2c7a7b; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 2px solid #285e61; border-bottom: none;">RELACIONA</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑🔗 <strong>identificador1</strong> (FK, UNIQUE)</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑🔗 <strong>identificador2</strong> (FK, UNIQUE)</td> </tr><tr> <td style="padding: 8px;">atributo_relacion1</td> </tr></table>
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ENTIDAD 2</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>identificador2</strong> (PK)</td> </tr><tr> <td style="padding: 8px;">atributo2</td> </tr></table>
</div>

---

### **1.2. Relación 1:N (Uno a Muchos)**

> **Regla de Oro:** La tabla del lado "N" absorbe la clave `🔑` de la tabla "1" y todos los atributos de la relación. La nueva FK será `NOT NULL` si la participación es obligatoria `(1,N)` y permitirá `NULL` si es opcional `(0,N)`.

#### ANTES (Componentes Originales)
<br>
<div style="display: flex; align-items: flex-start; justify-content: flex-start; gap: 15px; flex-wrap: wrap;">
<!-- Entidad 1 -->
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
    <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ENTIDAD 1</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>identificador1</strong> (PK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">atributo1</td> </tr>
    <tr> <td style="padding: 8px;">atributo2</td> </tr>
</table>
<!-- Conector con Cardinalidad -->
<div style="text-align: center; padding-top: 40px; color: #718096;"><span style="font-family: monospace; font-size: 0.9em;">(1,1)</span><br><span style="font-size: 2.5em;">→</span></div>
<!-- Bloque de la Relación -->
<div style="text-align: center;">
    <div style="font-weight: bold; color: #2c7a7b; margin-bottom: 5px; font-size: 0.9em;">RELACIÓN</div>
    <table style="width: auto; border: 2px solid #285e61; border-collapse: collapse;">
        <caption style="background-color: #2c7a7b; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 2px solid #285e61; border-bottom: none;">RELACIONA</caption>
        <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">atributo_relacion1</td> </tr>
        <tr> <td style="padding: 8px;">atributo_relacion2</td> </tr>
    </table>
</div>
<!-- Conector con Cardinalidad -->
<div style="text-align: center; padding-top: 40px; color: #718096;"><span style="font-family: monospace; font-size: 0.9em;">(1,N)</span><br><span style="font-size: 2.5em;">→</span></div>
<!-- Entidad 2 -->
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
    <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ENTIDAD 2</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>identificador2</strong> (PK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">atributo3</td> </tr>
    <tr> <td style="padding: 8px;">atributo4</td> </tr>
</table>
</div>

#### DESPUÉS (Fusión en la Tabla del Lado "N")
*La relación desaparece y sus atributos se integran en `ENTIDAD 2`.*
<br>
<div style="display: flex; align-items: flex-start; justify-content: flex-start; gap: 20px; flex-wrap: wrap;">
<!-- Tabla Entidad 1 Final -->
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
    <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ENTIDAD 1</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>identificador1</strong> (PK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">atributo1</td> </tr>
    <tr> <td style="padding: 8px;">atributo2</td> </tr>
</table>
<!-- Tabla Entidad 2 Final -->
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
    <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ENTIDAD 2</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>identificador2</strong> (PK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">atributo3</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">atributo4</td> </tr>
    <tr style="border-bottom: 1px solid #555;">
        <td style="padding: 8px;">
            <span style="font-size:0.8em; color:#999;"><em>de ENTIDAD 1</em> ➔</span> 
            <strong style="background-color:#4a5568; color:#e2e8f0; padding: 2px 6px; border-radius:4px;">🔗 identificador1 (FK)</strong>
        </td>
    </tr>
    <tr style="border-bottom: 1px solid #555;">
        <td style="padding: 8px;">
            <span style="font-size:0.8em; color:#999;"><em>de RELACIONA</em> ➔</span> 
            <strong style="background-color:#4a5568; color:#e2e8f0; padding: 2px 6px; border-radius:4px;">atributo_relacion1</strong>
        </td>
    </tr>
    <tr>
        <td style="padding: 8px;">
            <span style="font-size:0.8em; color:#999;"><em>de RELACIONA</em> ➔</span> 
            <strong style="background-color:#4a5568; color:#e2e8f0; padding: 2px 6px; border-radius:4px;">atributo_relacion2</strong>
        </td>
    </tr>
</table>
</div>

---

### **1.3. Relación N:M (Muchos a Muchos)**

> **Regla de Oro:** Siempre, sin importar la participación, se crea una **nueva tabla** para la relación. Las claves `🔑` de **ambas** entidades se propagan a esta nueva tabla como Claves Foráneas (FK). Juntas, forman la Clave Primaria Compuesta de la nueva tabla.

#### ANTES (Componentes Originales)
<br>
<div style="display: flex; align-items: flex-start; justify-content: flex-start; gap: 15px; flex-wrap: wrap;">
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ENTIDAD 1</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>identificador1</strong> (PK)</td> </tr><tr> <td style="padding: 8px;">atributo1</td> </tr></table>
<div style="text-align: center; padding-top: 40px; color: #718096;"><span style="font-family: monospace; font-size: 0.9em;">(1,N)</span><br><span style="font-size: 2.5em;">→</span></div>
<div style="text-align: center;"> <div style="font-weight: bold; color: #2c7a7b; margin-bottom: 5px; font-size: 0.9em;">RELACIÓN</div> <table style="width: auto; border: 2px solid #285e61; border-collapse: collapse;"> <caption style="background-color: #2c7a7b; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 2px solid #285e61; border-bottom: none;">RELACIONA</caption> <tr> <td style="padding: 8px;">atributo_relacion1</td> </tr> </table></div>
<div style="text-align: center; padding-top: 40px; color: #718096;"><span style="font-family: monospace; font-size: 0.9em;">(1,M)</span><br><span style="font-size: 2.5em;">→</span></div>
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ENTIDAD 2</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>identificador2</strong> (PK)</td> </tr><tr> <td style="padding: 8px;">atributo2</td> </tr></table>
</div>

#### DESPUÉS (Creación de una Nueva Tabla)
*Las entidades originales no cambian. La relación se convierte en su propia tabla.*
<br>
<div style="display: flex; align-items: flex-start; justify-content: flex-start; gap: 20px; flex-wrap: wrap;">
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ENTIDAD 1</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>identificador1</strong> (PK)</td> </tr><tr> <td style="padding: 8px;">atributo1</td> </tr></table>
<table style="width: auto; border: 2px solid #285e61; border-collapse: collapse;">
    <caption style="background-color: #2c7a7b; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 2px solid #285e61; border-bottom: none;">RELACIONA</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑🔗 <strong>identificador1</strong> (PK)(FK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑🔗 <strong>identificador2</strong> (PK)(FK)</td> </tr>
    <tr> <td style="padding: 8px;">atributo_relacion1</td> </tr>
</table>
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ENTIDAD 2</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>identificador2</strong> (PK)</td> </tr><tr> <td style="padding: 8px;">atributo2</td> </tr></table>
</div>

---

## 2. Relaciones Reflexivas o Cíclicas

Son relaciones donde una entidad se relaciona consigo misma.

---

### **2.1. Relación Reflexiva 1:1**

> **Regla de Oro:** Se añade una nueva columna a la misma tabla que actuará como Clave Foránea `🔗` y apuntará a su propia Clave Primaria `🔑`. Esta nueva columna **debe tener una restricción `UNIQUE`**.

#### ANTES (Componentes Originales)
<br>
<div style="display: flex; align-items: flex-start; justify-content: flex-start; gap: 15px; flex-wrap: wrap;">
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ENTIDAD</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>identificador</strong> (PK)</td> </tr><tr> <td style="padding: 8px;">atributo1</td> </tr></table>
<div style="text-align: center; padding-top: 40px; color: #718096;"><span style="font-family: monospace; font-size: 0.9em;">(0,1) Rol1</span><br><span style="font-size: 2.5em; transform: scaleX(-1); display:inline-block;">⤷</span></div>
<div style="text-align: center;"> <div style="font-weight: bold; color: #2c7a7b; margin-bottom: 5px; font-size: 0.9em;">RELACIÓN</div> <table style="width: auto; border: 2px solid #285e61; border-collapse: collapse;"> <caption style="background-color: #2c7a7b; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 2px solid #285e61; border-bottom: none;">RELACIONA</caption> <tr> <td style="padding: 8px;">atributo_relacion1</td> </tr> </table></div>
<div style="text-align: center; padding-top: 40px; color: #718096;"><span style="font-family: monospace; font-size: 0.9em;">(0,1) Rol2</span><br><span style="font-size: 2.5em;">⤷</span></div>
</div>

#### DESPUÉS (Propagación en la misma Tabla)
<br>
<div style="display: flex; align-items: flex-start; justify-content: flex-start; gap: 20px; flex-wrap: wrap;">
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
    <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ENTIDAD</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>identificador</strong> (PK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">atributo1</td> </tr>
    <tr style="border-bottom: 1px solid #555;">
        <td style="padding: 8px;">
            <span style="font-size:0.8em; color:#999;"><em>del Rol2</em> ➔</span> 
            <strong style="background-color:#4a5568; color:#e2e8f0; padding: 2px 6px; border-radius:4px;">🔗 identificador_rol2 (FK, UNIQUE)</strong>
        </td>
    </tr>
    <tr>
        <td style="padding: 8px;">
            <span style="font-size:0.8em; color:#999;"><em>de RELACIONA</em> ➔</span> 
            <strong style="background-color:#4a5568; color:#e2e8f0; padding: 2px 6px; border-radius:4px;">atributo_relacion1</strong>
        </td>
    </tr>
</table>
</div>

---

### **2.2. Relación Reflexiva 1:N**

La solución depende de la participación del lado "N" de la relación.

#### **Caso A: Participación Obligatoria en el lado N (1,N)**

> **Regla de Oro:** Se añade una nueva columna (FK) que apunta a la PK de la misma tabla. Esta FK **NO puede ser NULA** y **NO** lleva la restricción `UNIQUE`.

##### ANTES
<br>
<div style="display: flex; align-items: flex-start; justify-content: flex-start; gap: 15px; flex-wrap: wrap;">
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ENTIDAD</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>identificador</strong> (PK)</td> </tr><tr> <td style="padding: 8px;">atributo1</td> </tr></table>
<div style="text-align: center; padding-top: 40px; color: #718096;"><span style="font-family: monospace; font-size: 0.9em;">(0,1) Rol "Jefe"</span><br><span style="font-size: 2.5em; transform: scaleX(-1); display:inline-block;">⤷</span></div>
<div style="text-align: center;"> <div style="font-weight: bold; color: #2c7a7b; margin-bottom: 5px; font-size: 0.9em;">RELACIÓN</div> <table style="width: auto; border: 2px solid #285e61; border-collapse: collapse;"> <caption style="background-color: #2c7a7b; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 2px solid #285e61; border-bottom: none;">SUPERVISA</caption> </table></div>
<div style="text-align: center; padding-top: 40px; color: #718096;"><span style="font-family: monospace; font-size: 0.9em;">(1,N) Rol "Subordinado"</span><br><span style="font-size: 2.5em;">⤷</span></div>
</div>

##### DESPUÉS (Propagación en la misma Tabla)
<br>
<div style="display: flex; align-items: flex-start; justify-content: flex-start; gap: 20px; flex-wrap: wrap;">
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ENTIDAD</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>identificador</strong> (PK)</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">atributo1</td> </tr><tr><td style="padding: 8px;"><span style="font-size:0.8em; color:#999;"><em>del Rol "Jefe"</em> ➔</span> <strong style="background-color:#4a5568; color:#e2e8f0; padding: 2px 6px; border-radius:4px;">🔗 identificador_jefe (FK)</strong></td></tr></table>
</div>

---

#### **Caso B: Participación Opcional en el lado N (0,N)**

> **Regla de Oro:** Se crea una **nueva tabla** para la relación. Su clave primaria `🔑` será la clave de la entidad en su rol del lado "N" (ej: el "subordinado"), y contendrá una FK para el rol del lado "1" (ej: el "jefe").

##### ANTES
<br>
<div style="display: flex; align-items: flex-start; justify-content: flex-start; gap: 15px; flex-wrap: wrap;">
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ENTIDAD</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>identificador</strong> (PK)</td> </tr><tr> <td style="padding: 8px;">atributo1</td> </tr></table>
<div style="text-align: center; padding-top: 40px; color: #718096;"><span style="font-family: monospace; font-size: 0.9em;">(0,1) Rol "Jefe"</span><br><span style="font-size: 2.5em; transform: scaleX(-1); display:inline-block;">⤷</span></div>
<div style="text-align: center;"> <div style="font-weight: bold; color: #2c7a7b; margin-bottom: 5px; font-size: 0.9em;">RELACIÓN</div> <table style="width: auto; border: 2px solid #285e61; border-collapse: collapse;"> <caption style="background-color: #2c7a7b; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 2px solid #285e61; border-bottom: none;">SUPERVISA</caption> </table></div>
<div style="text-align: center; padding-top: 40px; color: #718096;"><span style="font-family: monospace; font-size: 0.9em;">(0,N) Rol "Subordinado"</span><br><span style="font-size: 2.5em;">⤷</span></div>
</div>

##### DESPUÉS (Creación de una Nueva Tabla)
<br>
<div style="display: flex; align-items: flex-start; justify-content: flex-start; gap: 20px; flex-wrap: wrap;">
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ENTIDAD</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>identificador</strong> (PK)</td> </tr><tr> <td style="padding: 8px;">atributo1</td> </tr></table>
<table style="width: auto; border: 2px solid #285e61; border-collapse: collapse;"><caption style="background-color: #2c7a7b; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 2px solid #285e61; border-bottom: none;">SUPERVISA</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑🔗 <strong>id_subordinado</strong> (PK, FK)</td> </tr><tr> <td style="padding: 8px;">🔗 <strong>id_jefe</strong> (FK)</td> </tr></table>
</div>

---

### **2.3. Relación Reflexiva N:M**

> **Regla de Oro:** Se crea una **nueva tabla** para la relación. Esta tabla contendrá **dos Claves Foráneas** `🔗` que apuntan a la Clave Primaria `🔑` de la tabla original, una por cada rol. Juntas, estas dos FKs forman la Clave Primaria Compuesta de la nueva tabla.

#### ANTES (Componentes Originales)
<br>
<div style="display: flex; align-items: flex-start; justify-content: flex-start; gap: 15px; flex-wrap: wrap;">
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ENTIDAD</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>identificador</strong> (PK)</td> </tr><tr> <td style="padding: 8px;">atributo1</td> </tr></table>
<div style="text-align: center; padding-top: 40px; color: #718096;"><span style="font-family: monospace; font-size: 0.9em;">(0,N) Rol1</span><br><span style="font-size: 2.5em; transform: scaleX(-1); display:inline-block;">⤷</span></div>
<div style="text-align: center;"> <div style="font-weight: bold; color: #2c7a7b; margin-bottom: 5px; font-size: 0.9em;">RELACIÓN</div> <table style="width: auto; border: 2px solid #285e61; border-collapse: collapse;"> <caption style="background-color: #2c7a7b; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 2px solid #285e61; border-bottom: none;">COMPONE</caption><tr><td style="padding: 8px;">atributo_relacion1</td></tr></table></div>
<div style="text-align: center; padding-top: 40px; color: #718096;"><span style="font-family: monospace; font-size: 0.9em;">(0,M) Rol2</span><br><span style="font-size: 2.5em;">⤷</span></div>
</div>

#### DESPUÉS (Creación de una Nueva Tabla)
*La tabla `ENTIDAD` no cambia. La relación se convierte en su propia tabla para registrar cada par de vínculos.*
<br>
<div style="display: flex; align-items: flex-start; justify-content: flex-start; gap: 20px; flex-wrap: wrap;">
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ENTIDAD</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>identificador</strong> (PK)</td> </tr><tr> <td style="padding: 8px;">atributo1</td> </tr></table>
<table style="width: auto; border: 2px solid #285e61; border-collapse: collapse;">
    <caption style="background-color: #2c7a7b; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 2px solid #285e61; border-bottom: none;">COMPONE</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑🔗 <strong>identificador_rol1</strong> (FK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑🔗 <strong>identificador_rol2</strong> (FK)</td> </tr>
    <tr> <td style="padding: 8px;">atributo_relacion1</td> </tr>
</table>
</div>