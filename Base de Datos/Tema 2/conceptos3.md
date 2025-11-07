### Transformación 1:N (Diseño Visual Final)

> **Regla de Oro:** La tabla del lado "N" absorbe la clave `🔑` de la tabla "1" y todos los atributos de la relación `💎`.

### ANTES (Componentes Originales)
*Las entidades (azul) están conectadas a través de la relación (verde).*

<br>

<div style="display: flex; align-items: flex-start; justify-content: flex-start; gap: 15px; flex-wrap: wrap;">

<!-- Tabla Departamento -->
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
    <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">DEPARTAMENTO</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>id_depto</strong> (PK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">nombre_depto</td> </tr>
    <tr> <td style="padding: 8px;">ubicacion</td> </tr>
</table>

<!-- Conector con Cardinalidad -->
<div style="text-align: center; padding-top: 40px; color: #718096;">
    <span style="font-family: monospace; font-size: 0.9em;">(1,1)</span>
    <br>
    <span style="font-size: 2.5em;">→</span>
</div>

<!-- Tabla Relación -->
<table style="width: auto; border: 2px solid #285e61; border-collapse: collapse;">
    <caption style="background-color: #2c7a7b; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 2px solid #285e61; border-bottom: none;">💎 TRABAJA_EN</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">fecha_incorporacion</td> </tr>
    <tr> <td style="padding: 8px;">tipo_contrato</td> </tr>
</table>

<!-- Conector con Cardinalidad -->
<div style="text-align: center; padding-top: 40px; color: #718096;">
    <span style="font-family: monospace; font-size: 0.9em;">(1,N)</span>
    <br>
    <span style="font-size: 2.5em;">→</span>
</div>

<!-- Tabla Empleado -->
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
    <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">EMPLEADO</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>id_emp</strong> (PK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">nombre_empleado</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">apellido</td> </tr>
    <tr> <td style="padding: 8px;">salario</td> </tr>
</table>

</div>

---

### DESPUÉS (Fusión en el Modelo Relacional)
*La relación (verde) desaparece y sus atributos se integran en la tabla `EMPLEADO`.*

<br>

<div style="display: flex; align-items: flex-start; justify-content: flex-start; gap: 20px; flex-wrap: wrap;">

<!-- Tabla Departamento Final -->
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
    <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">DEPARTAMENTO</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>id_depto</strong> (PK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">nombre_depto</td> </tr>
    <tr> <td style="padding: 8px;">ubicacion</td> </tr>
</table>

<!-- Tabla Empleado Final -->
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
    <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">EMPLEADO</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>id_emp</strong> (PK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">nombre_empleado</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">apellido</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">salario</td> </tr>
    <tr style="border-bottom: 1px solid #555;">
        <td style="padding: 8px;">
            <span style="font-size:0.8em; color:#999;"><em>de DEPARTAMENTO</em> ➔</span> 
            <strong style="background-color:#4a5568; color:#e2e8f0; padding: 2px 6px; border-radius:4px;">🔗 id_depto (FK)</strong>
        </td>
    </tr>
    <tr style="border-bottom: 1px solid #555;">
        <td style="padding: 8px;">
            <span style="font-size:0.8em; color:#999;"><em>de TRABAJA_EN</em> ➔</span> 
            <strong style="background-color:#4a5568; color:#e2e8f0; padding: 2px 6px; border-radius:4px;">fecha_incorporacion</strong>
        </td>
    </tr>
    <tr>
        <td style="padding: 8px;">
            <span style="font-size:0.8em; color:#999;"><em>de TRABAJA_EN</em> ➔</span> 
            <strong style="background-color:#4a5568; color:#e2e8f0; padding: 2px 6px; border-radius:4px;">tipo_contrato</strong>
        </td>
    </tr>
</table>

</div>