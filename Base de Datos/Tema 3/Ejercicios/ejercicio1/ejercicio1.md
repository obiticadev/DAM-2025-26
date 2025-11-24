### Crear una tabla conductores con los campos
- NIF (cadena de 9) (PK)
- Nombre (cadena de 25 cacteres) (campo obligatorio)
- Apellidos (cadena de 50 caracteres) (campo obligatorio)
- Foto (Tipo binario obligatorio) 
- Edad (numérico entre 18 y 99) 
- Tipo de carnet (cadena con valores que pueden ser `A1`, `A2`, `A`, `B1`, `B2`, `C`, `CD` o `E`)
- Fecha de carnet (Tipo fecha la del sistema)
- Intentos (campo numérico cuyo valor debe ser menor de 10)

```SQL
Create table conductores
(
    NIF varchar2 (9) Primary Key;
    Nombre varchar2 (25) NOT NULL;
    Apellidos varchar2 (50) NOT NULL;
    Foto bfile NOT NULL;
    Edad NUMBER (2);
    Tipo_de_carnet varchar2 (25);
    Fecha_de_carnet date; 
    Intentos NUMBER(1);

    check Tipo_de_carnet in 'A1', 'A2', 'A', 'B1', 'B2', 'C', 'CD', 'E';
    check Edad between 18 99;
    
)
```