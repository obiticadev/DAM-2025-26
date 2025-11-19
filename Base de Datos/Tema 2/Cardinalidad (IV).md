### **Conversión de Relaciones Ternarias y N-arias**

Las relaciones de grado superior (ternarias, cuaternarias, etc.) son aquellas que vinculan a más de dos entidades simultáneamente. Su transformación al modelo relacional sigue una regla específica.

---

### **1. Relación de Grado N (Caso General N:M:P...)**

> **Regla de Oro:** Se crea una **nueva tabla** para representar la relación. La Clave Primaria `🔑` de esta nueva tabla se forma por la concatenación de las claves primarias de **todas** las entidades que participan en la relación. Además, los atributos propios de la relación se añaden como columnas a esta nueva tabla.

#### ANTES (Diagrama de Relación Ternaria)
*Tres entidades (`AUTOR`, `LIBRO`, `EDITORIAL`) se vinculan a través de una única relación `PUBLICACION`. La cardinalidad es "muchos" en todas las direcciones.*
<br>
<div style="display: flex; justify-content: flex-start;">
<div style="display: inline-flex; flex-direction: column; align-items: center; gap: 10px;">
    <!-- Fila Superior -->
    <div style="display: flex; align-items: center; gap: 10px;">
        <table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">AUTOR</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>dni</strong> (PK)</td> </tr><tr> <td style="padding: 8px;">Nombre</td> </tr></table>
        <div style="text-align: center; color: #718096;"><span style="font-family: monospace; font-size: 0.9em;">(0,N)</span><br>────────</div>
        <table style="width: auto; border: 2px solid #285e61; border-collapse: collapse; text-align: center;"><caption style="background-color: #2c7a7b; color: white; caption-side: top; text-align: center; padding: 8px; font-weight: bold; border: 2px solid #285e61; border-bottom: none;">PUBLICACION</caption><tr> <td style="padding: 8px;">Fecha_Publicacion</td> </tr></table>
        <div style="text-align: center; color: #718096;"><span style="font-family: monospace; font-size: 0.9em;">(0,M)</span><br>────────</div>
        <table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">LIBRO</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>ISBN</strong> (PK)</td> </tr><tr> <td style="padding: 8px;">Titulo</td> </tr></table>
    </div>
    <!-- Conexión Vertical -->
    <div style="text-align: center; color: #718096; line-height: 1;"><span style="font-family: monospace; font-size: 0.9em;">(0,P)</span><br><span style="font-size: 2em;">|</span></div>
    <!-- Fila Inferior -->
    <table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">EDITORIAL</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>CodEditorial</strong> (PK)</td> </tr><tr> <td style="padding: 8px;">Nombre_Editorial</td> </tr></table>
</div>
</div>


#### DESPUÉS (Propagación de Claves a la Nueva Tabla)
*Se crea la tabla `PUBLICACION` que hereda las claves de las otras tres para formar su clave primaria compuesta.*
<br>
<div style="display: flex; justify-content: flex-start;">
<div style="display: inline-flex; flex-direction: column; align-items: center; gap: 10px;">
    <!-- Fila Superior -->
    <div style="display: flex; align-items: center; gap: 10px;">
        <table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">AUTOR</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>dni</strong> (PK)</td> </tr><tr> <td style="padding: 8px;">Nombre</td> </tr></table>
        <div style="text-align: center; color: #718096;">────────</div>
        <table style="width: auto; border: 2px solid #285e61; border-collapse: collapse;"><caption style="background-color: #2c7a7b; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 2px solid #285e61; border-bottom: none;">PUBLICACION</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑🔗 <strong>dni</strong> (PK, FK)</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑🔗 <strong>ISBN</strong> (PK, FK)</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑🔗 <strong>CodEditorial</strong> (PK, FK)</td> </tr><tr> <td style="padding: 8px;">Fecha_Publicacion</td> </tr></table>
        <div style="text-align: center; color: #718096;">────────</div>
        <table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">LIBRO</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>ISBN</strong> (PK)</td> </tr><tr> <td style="padding: 8px;">Titulo</td> </tr></table>
    </div>
    <!-- Conexión Vertical -->
    <div style="font-size: 2em; color: #718096;">|</div>
    <!-- Fila Inferior -->
    <table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">EDITORIAL</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>CodEditorial</strong> (PK)</td> </tr><tr> <td style="padding: 8px;">Nombre_Editorial</td> </tr></table>
</div>
</div>

---

### **2. Caso Especial: Relación 1:N:M**

> **Regla de Oro:** Se crea una nueva tabla para la relación. La clave primaria `🔑` se compone únicamente por la concatenación de las claves de las entidades con cardinalidad **N y M**. La clave de la entidad con cardinalidad **1** se añade como una Clave Foránea `🔗`, pero **no forma parte** de la clave primaria.

#### ANTES (Diagrama de Relación 1:N:M)
*Un `AUTOR` (N) y un `LIBRO` (M) se asocian a través de una única `EDITORIAL` (1).*
<br>
<div style="display: flex; justify-content: flex-start;">
<div style="display: inline-flex; flex-direction: column; align-items: center; gap: 10px;">
    <!-- Fila Superior -->
    <div style="display: flex; align-items: center; gap: 10px;">
        <table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">AUTOR</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>dni</strong> (PK)</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Nombre</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Ap1</td> </tr><tr> <td style="padding: 8px;">Nacionalidad</td> </tr></table>
        <div style="text-align: center; color: #718096;"><span style="font-family: monospace; font-size: 0.9em;">(0,N)</span><br>────────</div>
        <table style="width: auto; border: 2px solid #285e61; border-collapse: collapse; text-align: center;"><caption style="background-color: #2c7a7b; color: white; caption-side: top; text-align: center; padding: 8px; font-weight: bold; border: 2px solid #285e61; border-bottom: none;">PUBLICACION</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Fecha_Contrato</td> </tr><tr> <td style="padding: 8px;">Num_Edicion</td> </tr></table>
        <div style="text-align: center; color: #718096;"><span style="font-family: monospace; font-size: 0.9em;">(0,M)</span><br>────────</div>
        <table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">LIBRO</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>ISBN</strong> (PK)</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Titulo</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">NumEjemplares</td> </tr><tr> <td style="padding: 8px;">Genero</td> </tr></table>
    </div>
    <!-- Conexión Vertical -->
    <div style="text-align: center; color: #718096; line-height: 1;"><span style="font-family: monospace; font-size: 0.9em;">(0,1)</span><br><span style="font-size: 2em;">|</span></div>
    <!-- Fila Inferior -->
    <table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">EDITORIAL</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>CodEditorial</strong> (PK)</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Nombre</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Direccion</td> </tr><tr> <td style="padding: 8px;">Pais</td> </tr></table>
</div>
</div>

#### DESPUÉS (Diagrama Relacional del Ejemplo)
*La clave primaria de `PUBLICACION` está formada por `AUTOR_dni` y `LIBRO_ISBN`. `CodEditorial` es solo una clave foránea.*
<br>
<div style="display: flex; justify-content: flex-start;">
<div style="display: inline-flex; flex-direction: column; align-items: center; gap: 10px;">
    <!-- Fila Superior -->
    <div style="display: flex; align-items: center; gap: 10px;">
        <table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">AUTOR</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>dni</strong> (PK)</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Nombre</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Ap1</td> </tr><tr> <td style="padding: 8px;">Nacionalidad</td> </tr></table>
        <div style="text-align: center; color: #718096;">────────</div>
        <table style="width: auto; border: 2px solid #285e61; border-collapse: collapse;"><caption style="background-color: #2c7a7b; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 2px solid #285e61; border-bottom: none;">PUBLICACION</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑🔗 <strong>AUTOR_dni</strong> (PK, FK)</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑🔗 <strong>LIBRO_ISBN</strong> (PK, FK)</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔗 <strong>CodEditorial</strong> (FK)</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Fecha_Contrato</td> </tr><tr> <td style="padding: 8px;">Num_Edicion</td> </tr></table>
        <div style="text-align: center; color: #718096;">────────</div>
        <table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">LIBRO</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>ISBN</strong> (PK)</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Titulo</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">NumEjemplares</td> </tr><tr> <td style="padding: 8px;">Genero</td> </tr></table>
    </div>
    <!-- Conexión Vertical -->
    <div style="font-size: 2em; color: #718096;">|</div>
    <!-- Fila Inferior -->
    <table style="width: auto; border: 1px solid #555; border-collapse: collapse;"><caption style="background-color: #2c5282; color: white; caption-side: top; text-align: left; padding: 8px; font-weight: bold; border: 1px solid #555; border-bottom: none;">EDITORIAL</caption><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">🔑 <strong>CodEditorial</strong> (PK)</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Nombre</td> </tr><tr style="border-bottom: 1px solid #555;"> <td style="padding: 8px;">Direccion</td> </tr><tr> <td style="padding: 8px;">Pais</td> </tr></table>
</div>
</div>