### **Conversión de Jerarquías de Especialización/Generalización**

Existen tres estrategias principales para convertir una jerarquía (una superentidad con varios subtipos) al modelo relacional. La elección depende de los requisitos específicos de la base de datos, como el rendimiento de las consultas, el uso del espacio y la integridad de los datos.

---

### **1. Opción 1: Crear una Única Tabla (Agrupar todo en el Supertipo)**

> **Regla de Oro:** Se crea **una única tabla** para el supertipo que aglutina todos sus atributos junto con los atributos específicos de **todos** los subtipos. A menudo se añade una columna extra (un "discriminador") para identificar a qué subtipo pertenece cada fila.

#### ANTES (Diagrama de Jerarquía)
*La entidad `USUARIO` se especializa en `INVITADO`, `REGISTRADO` y `ADMINISTRADOR`.*
<br>
<div style="display: flex; justify-content: flex-start;">
    <div style="display: inline-flex; flex-direction: column; align-items: center;">
        <!-- SuperEntidad -->
        <table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
            <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">USUARIO</caption>
            <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>Código</strong> (PK)</td> </tr>
            <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">nombre_usuario</td> </tr>
            <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">contraseña</td> </tr>
            <tr> <td style="padding: 8px;">email</td> </tr>
        </table>
        <!-- Conector de Jerarquía -->
        <div style="font-size: 2.5em; color: #718096; line-height: 0.5;">↓</div>
        <div style="border: 2px solid #2c7a7b; display: inline-block; padding: 5px 15px; border-radius: 8px; font-weight: bold; color: #2c7a7b; margin-top: -10px; background: white; font-size:0.9em;">Jerarquía</div>
        <div style="display: flex; align-items: flex-start; justify-content: center; gap: 20px; flex-wrap: wrap; margin-top: 10px;">
            <!-- Subtipo 1 -->
            <table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
                <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">INVITADO</caption>
                <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">oferta</td> </tr>
                <tr> <td style="padding: 8px;">demo</td> </tr>
            </table>
            <!-- Subtipo 2 -->
            <table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
                <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">REGISTRADO</caption>
                <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Puntos</td> </tr>
                <tr> <td style="padding: 8px;">nivel</td> </tr>
            </table>
            <!-- Subtipo 3 -->
            <table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
                <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">ADMINISTRADOR</caption>
                <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">área</td> </tr>
                <tr> <td style="padding: 8px;">Perfil</td> </tr>
            </table>
        </div>
    </div>
</div>

#### DESPUÉS (Una Sola Tabla)
*Todos los atributos se fusionan. Los atributos que no correspondan al tipo de usuario de una fila contendrán valores `NULL`.*
<br>
<div style="display: flex; align-items: flex-start; justify-content: flex-start; gap: 20px; flex-wrap: wrap;">
<table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
    <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">USUARIO</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>Código</strong> (PK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">nombre_usuario</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">contraseña</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">email</td> </tr>
    <tr style="border-bottom: 1px solid #555;"><td style="padding: 8px;"><span style="font-size:0.8em; color:#999;"><em>de INVITADO</em> ➔</span> <strong style="background-color:#4a5568; color:#e2e8f0; padding: 2px 6px; border-radius:4px;">oferta</strong></td></tr>
    <tr style="border-bottom: 1px solid #555;"><td style="padding: 8px;"><span style="font-size:0.8em; color:#999;"><em>de INVITADO</em> ➔</span> <strong style="background-color:#4a5568; color:#e2e8f0; padding: 2px 6px; border-radius:4px;">demo</strong></td></tr>
    <tr style="border-bottom: 1px solid #555;"><td style="padding: 8px;"><span style="font-size:0.8em; color:#999;"><em>de REGISTRADO</em> ➔</span> <strong style="background-color:#4a5568; color:#e2e8f0; padding: 2px 6px; border-radius:4px;">Puntos</strong></td></tr>
    <tr style="border-bottom: 1px solid #555;"><td style="padding: 8px;"><span style="font-size:0.8em; color:#999;"><em>de REGISTRADO</em> ➔</span> <strong style="background-color:#4a5568; color:#e2e8f0; padding: 2px 6px; border-radius:4px;">nivel</strong></td></tr>
    <tr style="border-bottom: 1px solid #555;"><td style="padding: 8px;"><span style="font-size:0.8em; color:#999;"><em>de ADMIN</em> ➔</span> <strong style="background-color:#4a5568; color:#e2e8f0; padding: 2px 6px; border-radius:4px;">área</strong></td></tr>
    <tr><td style="padding: 8px;"><span style="font-size:0.8em; color:#999;"><em>de ADMIN</em> ➔</span> <strong style="background-color:#4a5568; color:#e2e8f0; padding: 2px 6px; border-radius:4px;">Perfil</strong></td></tr>
</table>
</div>

---

### **2. Opción 2: Anulación del Supertipo (Agrupar todo en los Subtipos)**

> **Regla de Oro:** Se **elimina** la tabla del supertipo. Todos sus atributos comunes se replican ("se empujan hacia abajo") en cada una de las tablas creadas para los subtipos. La clave primaria `🔑` del supertipo se convierte en la clave primaria de cada tabla de subtipo.

#### ANTES (Diagrama de Jerarquía)
*La entidad `PERSONA` tiene una especialización total y exclusiva en `HOMBRE` y `MUJER`.*
<br>
<div style="display: flex; justify-content: flex-start;">
    <div style="display: inline-flex; flex-direction: column; align-items: center;">
        <table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">PERSONA</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>DNI</strong> (PK)</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">edad</td> </tr><tr> <td style="padding: 8px;">RH</td> </tr></table>
        <div style="font-size: 2.5em; color: #718096; line-height: 0.5;">↓</div>
        <div style="border: 2px solid #2c7a7b; display: inline-block; padding: 5px 15px; border-radius: 8px; font-weight: bold; color: #2c7a7b; margin-top: -10px; background: white; font-size:0.9em;">Total y Exclusiva</div>
        <div style="display: flex; align-items: flex-start; justify-content: center; gap: 40px; flex-wrap: wrap; margin-top: 10px;">
            <table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">HOMBRE</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Colesterol</td> </tr><tr> <td style="padding: 8px;">Triglicéridos</td> </tr></table>
            <table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">MUJER</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Ácido_Fólico</td> </tr><tr> <td style="padding: 8px;">Estrógenos</td> </tr></table>
        </div>
    </div>
</div>

#### DESPUÉS (Una Tabla por cada Subtipo)
*No existe la tabla `PERSONA`. Sus atributos se han replicado en las tablas `HOMBRE` y `MUJER`.*
<br>
<div style="display: flex; align-items: flex-start; justify-content: flex-start; gap: 20px; flex-wrap: wrap;">

<table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
    <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">HOMBRE</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>DNI</strong> (PK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"><td style="padding: 8px;"><span style="font-size:0.8em; color:#999;"><em>de PERSONA</em> ➔</span> <strong style="background-color:#4a5568; color:#e2e8f0; padding: 2px 6px; border-radius:4px;">edad</strong></td></tr>
    <tr style="border-bottom: 1px solid #555;"><td style="padding: 8px;"><span style="font-size:0.8em; color:#999;"><em>de PERSONA</em> ➔</span> <strong style="background-color:#4a5568; color:#e2e8f0; padding: 2px 6px; border-radius:4px;">RH</strong></td></tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Colesterol</td> </tr>
    <tr> <td style="padding: 8px;">Triglicéridos</td> </tr>
</table>

<table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
    <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">MUJER</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>DNI</strong> (PK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"><td style="padding: 8px;"><span style="font-size:0.8em; color:#999;"><em>de PERSONA</em> ➔</span> <strong style="background-color:#4a5568; color:#e2e8f0; padding: 2px 6px; border-radius:4px;">edad</strong></td></tr>
    <tr style="border-bottom: 1px solid #555;"><td style="padding: 8px;"><span style="font-size:0.8em; color:#999;"><em>de PERSONA</em> ➔</span> <strong style="background-color:#4a5568; color:#e2e8f0; padding: 2px 6px; border-radius:4px;">RH</strong></td></tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Ácido_Fólico</td> </tr>
    <tr> <td style="padding: 8px;">Estrógenos</td> </tr>
</table>
</div>

---

### **3. Opción 3: Una Tabla por cada Entidad (La más conveniente)**

> **Regla de Oro:** Se crea una tabla para el supertipo y una tabla separada para cada subtipo. La clave primaria `🔑` del supertipo se propaga a cada tabla de subtipo, donde actúa simultáneamente como **Clave Primaria y Clave Foránea** `🔑🔗`, estableciendo una relación 1 a 1.

#### ANTES (Diagrama de Jerarquía)
*Se parte del mismo diagrama del caso anterior.*
<br>
<div style="display: flex; justify-content: flex-start;">
    <div style="display: inline-flex; flex-direction: column; align-items: center;">
        <table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">PERSONA</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>DNI</strong> (PK)</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">edad</td> </tr><tr> <td style="padding: 8px;">RH</td> </tr></table>
        <div style="font-size: 2.5em; color: #718096; line-height: 0.5;">↓</div>
        <div style="border: 2px solid #2c7a7b; display: inline-block; padding: 5px 15px; border-radius: 8px; font-weight: bold; color: #2c7a7b; margin-top: -10px; background: white; font-size:0.9em;">Total y Exclusiva</div>
        <div style="display: flex; align-items: flex-start; justify-content: center; gap: 40px; flex-wrap: wrap; margin-top: 10px;">
            <table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">HOMBRE</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Colesterol</td> </tr><tr> <td style="padding: 8px;">Triglicéridos</td> </tr></table>
            <table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">MUJER</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Ácido_Fólico</td> </tr><tr> <td style="padding: 8px;">Estrógenos</td> </tr></table>
        </div>
    </div>
</div>

#### DESPUÉS (Una Tabla para cada Elemento)
*Se mantiene la estructura original, creando una tabla para el supertipo y una para cada subtipo, conectadas por la clave.*
<br>
<div style="display: flex; align-items: flex-start; justify-content: flex-start; gap: 20px; flex-wrap: wrap;">

<table style="width: auto; border: 1px solid #555; border-collapse: collapse;">
    <caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">PERSONA</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>DNI</strong> (PK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">edad</td> </tr>
    <tr> <td style="padding: 8px;">RH</td> </tr>
</table>

<table style="width: auto; border: 2px solid #285e61; border-collapse: collapse;">
    <caption style="background-color: #2c7a7b; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 2px solid #285e61; border-bottom: none;">HOMBRE</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑🔗 <strong>DNI</strong> (PK, FK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Colesterol</td> </tr>
    <tr> <td style="padding: 8px;">Triglicéridos</td> </tr>
</table>

<table style="width: auto; border: 2px solid #285e61; border-collapse: collapse;">
    <caption style="background-color: #2c7a7b; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 2px solid #285e61; border-bottom: none;">MUJER</caption>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑🔗 <strong>DNI</strong> (PK, FK)</td> </tr>
    <tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Ácido_Fólico</td> </tr>
    <tr> <td style="padding: 8px;">Estrógenos</td> </tr>
</table>
</div>