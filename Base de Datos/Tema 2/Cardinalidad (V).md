### **Conversión de Atributos Multivalorados**

Un atributo multivalorado es aquel que puede contener múltiples valores para una única instancia de una entidad. Por ejemplo, un empleado puede tener varios números de teléfono o conocer varios idiomas. Los atributos opcionales, por otro lado, son aquellos que pueden no tener valor (ser nulos).

> **Regla de Oro:** Se crea una **nueva tabla** para el atributo multivalorado. La clave primaria `🔑` de esta nueva tabla se compone de la clave primaria de la entidad original más el propio atributo multivalorado. Ambos forman una clave primaria compuesta. Los atributos opcionales simplemente se convierten en columnas que admiten valores `NULL`.

#### ANTES (Diagrama de Entidad con Atributos)
*La entidad `EMPLEADO` tiene un identificador (`Cod_emp`), atributos simples (`Domicilio`, `Fecha_nac`), atributos opcionales (`Cargo`, `Idioma`) y un atributo multivalorado (`Teléfono`).*
<br>
<div style="display: flex; justify-content: flex-start;">
<div style="display: inline-block; border: 1px solid #555; padding: 10px; background-color: #2d3748;">
    <div style="text-align: center; font-weight: bold; background-color: #2c5282; color: white; padding: 8px; margin: -10px -10px 10px -10px;">EMPLEADO</div>
    <table style="border-collapse: collapse; width: 100%;">
        <tr><td style="padding: 6px; border-bottom: 1px solid #4a5568;">🔑 <strong>Cod_emp</strong> (Clave Primaria)</td></tr>
        <tr><td style="padding: 6px; border-bottom: 1px solid #4a5568;">🏠 Domicilio</td></tr>
        <tr><td style="padding: 6px; border-bottom: 1px solid #4a5568;">🎂 Fecha_nac</td></tr>
        <tr><td style="padding: 6px; border-bottom: 1px solid #4a5568;"><em>(Opcional)</em> 👔 Cargo</td></tr>
        <tr><td style="padding: 6px; border-bottom: 1px solid #4a5568;"><em>(Opcional)</em> 🌐 Idioma</td></tr>
        <tr><td style="padding: 6px; background-color: #2c7a7b; color: white; border-radius: 4px;">📞 <strong>Teléfono</strong> (Atributo Multivalorado)</td></tr>
    </table>
</div>
</div>


#### DESPUÉS (División en Dos Tablas)
*La entidad `EMPLEADO` se convierte en una tabla con sus atributos simples y opcionales. El atributo multivalorado `Teléfono` genera una nueva tabla `TELEFONOS` cuya clave primaria es la combinación de `Cod_emp` y `Teléfono`.*
<br>
<div style="display: flex; align-items: flex-start; justify-content: flex-start; gap: 20px; flex-wrap: wrap;">
<!-- Tabla Entidad Principal -->
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
    <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">EMPLEADO</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>Cod_emp</strong> (PK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Domicilio</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Fecha_nac</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Cargo <em>(admite nulos)</em></td> </tr>
    <tr> <td style="padding: 8px;">Idioma <em>(admite nulos)</em></td> </tr>
</table>
<!-- Tabla Atributo Multivalorado -->
<table style="width: auto; border: 2px solid #285e61; border-collapse: collapse;">
    <caption style="background-color: #2c7a7b; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 2px solid #285e61; border-bottom: none;">TELEFONOS</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑🔗 <strong>Cod_emp</strong> (PK, FK)</td> </tr>
    <tr> <td style="padding: 8px;">🔑 <strong>Teléfono</strong> (PK)</td> </tr>
</table>
</div>