### **Conversión de Relaciones de Dependencia**

Las relaciones de dependencia vinculan una entidad "fuerte" con una "débil". Una entidad débil es aquella que no puede existir sin la entidad fuerte y, a menudo, necesita la clave de la fuerte para poder identificarse de forma única. Hay dos tipos principales de dependencia: en existencia y en identificación.

---

### **1. Relación de Dependencia en Existencia**

> **Regla de Oro:** Se transforma de manera similar a una relación 1:N estándar. La clave primaria `🔑` de la entidad fuerte (lado "1") se propaga a la tabla de la entidad débil (lado "N"), donde se convierte en una Clave Foránea `🔗`.

#### ANTES (Diagrama de Dependencia en Existencia)
*Un `FAMILIAR` (entidad débil) depende de la existencia de un `PACIENTE` (entidad fuerte). La existencia de un familiar en la base de datos no tiene sentido si no está asociado a un paciente.*
<br>
<div style="display: flex; align-items: center; justify-content: flex-start; gap: 15px; flex-wrap: wrap;">
<!-- Entidad Fuerte -->
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
    <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">PACIENTE</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>PacienteID</strong> (PK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Nombre</td> </tr>
    <tr> <td style="padding: 8px;">Tratamiento</td> </tr>
</table>
<!-- Conector -->
<div style="text-align: center; color: #718096;"><span style="font-size: 2.5em;">→</span></div>
<!-- Relación -->
<div style="text-align: center;">
    <div style="font-weight: bold; color: #2c7a7b; margin-bottom: 5px; font-size: 0.9em;">DEPENDENCIA EN EXISTENCIA</div>
    <table style="width: auto; border: 2px solid #285e61; border-collapse: collapse; margin: 0 auto;">
        <tr style="background-color: #2c7a7b; color: white; font-weight: bold;"> <td style="padding: 4px 12px;">E</td> </tr>
        <tr style="background-color: #2c7a7b; color: white;"> <td style="padding: 4px 12px;">tiene</td> </tr>
    </table>
</div>
<!-- Conector -->
<div style="text-align: center; color: #718096;"><span style="font-size: 2.5em;">→</span></div>
<!-- Entidad Débil con doble recuadro -->
<div style="border: 1px solid #888; padding: 3px; display: inline-block;">
    <table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
        <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">FAMILIAR</caption>
        <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>DNI</strong> (PK)</td> </tr>
        <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Nombre_Familiar</td> </tr>
        <tr> <td style="padding: 8px;">Parentesco</td> </tr>
    </table>
</div>
</div>

#### DESPUÉS (Propagación de Clave)
*La clave primaria de `PACIENTE` se añade como clave foránea a la tabla `FAMILIAR` para establecer el vínculo de dependencia.*
<br>
<div style="display: flex; align-items: flex-start; justify-content: flex-start; gap: 20px; flex-wrap: wrap;">
<!-- Tabla Entidad Fuerte -->
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
    <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">PACIENTE</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>PacienteID</strong> (PK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Nombre</td> </tr>
    <tr> <td style="padding: 8px;">Tratamiento</td> </tr>
</table>
<!-- Tabla Entidad Débil -->
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
    <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">FAMILIAR</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>DNI</strong> (PK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Nombre_Familiar</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Parentesco</td> </tr>
    <tr><td style="padding: 8px;"><span style="font-size:0.8em; color:#999;"><em>de PACIENTE</em> ➔</span> <strong style="background-color:#4a5568; color:#e2e8f0; padding: 2px 6px; border-radius:4px;">🔗 PacienteID (FK)</strong></td></tr>
</table>
</div>

---

### **2. Relación de Dependencia en Identificación**

> **Regla de Oro:** La clave primaria `🔑` de la entidad fuerte se propaga a la tabla de la entidad débil, donde no solo actúa como Clave Foránea `🔗`, sino que también **forma parte de la Clave Primaria** `🔑` de la entidad débil.

#### ANTES (Diagrama de Dependencia en Identificación)
*Un `EJEMPLAR` (entidad débil) no puede identificarse unívocamente solo con su número de ejemplar, ya que podría repetirse para libros distintos. Necesita el código del `LIBRO` (entidad fuerte) para tener una identidad única.*
<br>
<div style="display: flex; align-items: center; justify-content: flex-start; gap: 15px; flex-wrap: wrap;">
<!-- Entidad Fuerte -->
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
    <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">LIBRO</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>Cod_libro</strong> (PK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Titulo</td> </tr>
    <tr> <td style="padding: 8px;">Autor</td> </tr>
</table>
<!-- Conector -->
<div style="text-align: center; color: #718096;"><span style="font-family: monospace; font-size: 0.9em;">(1,1)</span><br><span style="font-size: 2.5em;">→</span></div>
<!-- Relación -->
<div style="text-align: center;">
    <div style="font-weight: bold; color: #2c7a7b; margin-bottom: 5px; font-size: 0.9em;">DEPENDENCIA EN IDENTIFICACIÓN</div>
    <table style="width: auto; border: 2px solid #285e61; border-collapse: collapse; margin: 0 auto;">
        <tr style="background-color: #2c7a7b; color: white; font-weight: bold;"> <td style="padding: 4px 12px;">ID</td> </tr>
        <tr style="background-color: #2c7a7b; color: white;"> <td style="padding: 4px 12px;">tiene</td> </tr>
    </table>
</div>
<!-- Conector -->
<div style="text-align: center; color: #718096;"><span style="font-family: monospace; font-size: 0.9em;">(0,N)</span><br><span style="font-size: 2.5em;">→</span></div>
<!-- Entidad Débil con doble recuadro -->
<div style="border: 1px solid #888; padding: 3px; display: inline-block;">
    <table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
        <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">EJEMPLAR</caption>
        <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>Num_ejemplar</strong> (PK parcial)</td> </tr>
        <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Cod_ejem</td> </tr>
        <tr> <td style="padding: 8px;">Estado</td> </tr>
    </table>
</div>
</div>

#### DESPUÉS (Clave Primaria Compuesta)
*La clave primaria de la tabla `EJEMPLAR` se compone de su clave parcial (`Num_ejemplar`) y la clave foránea de `LIBRO` (`Cod_libro`). Adicionalmente, se imponen restricciones de borrado y actualización en cascada para mantener la integridad referencial.*
<br>
<div style="display: flex; align-items: flex-start; justify-content: flex-start; gap: 20px; flex-wrap: wrap;">

<table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
    <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">LIBRO</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>Cod_libro</strong> (PK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Titulo</td> </tr>
    <tr> <td style="padding: 8px;">Autor</td> </tr>
</table>

<table style="width: auto; border: 2px solid #285e61; border-collapse: collapse;">
    <caption style="background-color: #2c7a7b; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 2px solid #285e61; border-bottom: none;">EJEMPLAR</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑🔗 <strong>Cod_libro</strong> (PK, FK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>Num_ejemplar</strong> (PK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Cod_ejem</td> </tr>
    <tr> <td style="padding: 8px;">Estado</td> </tr>
</table>
</div>
<div style="margin-top: 15px; font-family: monospace; background-color: #2d3748; color: #a0aec0; padding: 12px; border-left: 4px solid #2c7a7b; border-radius: 4px; font-size: 0.9em;">
    <strong>Nota sobre la Clave Ajena en EJEMPLAR:</strong><br>
    Se aplican las siguientes restricciones para mantener la integridad:<br>
    <span style="color: #e2e8f0;">- ON DELETE CASCADE:</span> Si se borra un libro, se borran todos sus ejemplares.<br>
    <span style="color: #e2e8f0;">- ON UPDATE CASCADE:</span> Si cambia el código de un libro, se actualiza en todos sus ejemplares.
</div>