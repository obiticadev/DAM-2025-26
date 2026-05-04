Aquí tienes el documento formateado en Markdown, estructurado para mejorar la legibilidad y con espacios designados para las respuestas.

---

# EXCEPCIONES CREADAS POR USUARIO CON NOMBRE

## EJEMPLO 1

### Código PL/SQL
```sql
declare
    ncli pedidose.clientenu%TYPE;
    errorcli exception;
begin
    select clientenu into ncli
    from pedidose
    where pedidonu=1002;
    
    if (ncli <> 103) then
        raise errorcli;
    end if;
    
    dbms_output.put_line(ncli);
exception
    when errorcli then
        dbms_output.put_line('error valor '||' ERRM='|| sqlerrm || ' ECODE=' ||sqlcode);
end;
```

### Resultado de la Ejecución (Captura/Texto)
> [Inserte aquí la captura de pantalla o el texto del resultado del Ejemplo 1]

---

## EJEMPLO 2

### Código PL/SQL
```sql
declare
    ncli pedidose.clientenu%TYPE;
    errorcli exception;
    PRAGMA EXCEPTION_INIT(errorcli, -20015);
begin
    select clientenu into ncli
    from pedidose
    where pedidonu=1002;
    
    if (ncli <> 103) then
        raise errorcli;
    end if;
    
    dbms_output.put_line(ncli);
exception
    when errorcli then
        dbms_output.put_line('error valor '||' ERRM='|| sqlerrm || ' ECODE=' ||sqlcode);
end;
```

### Resultado de la Ejecución (Captura/Texto)
> [Inserte aquí la captura de pantalla o el texto del resultado del Ejemplo 2]

---

## ESPACIO PARA RESPUESTAS / OBSERVACIONES
*(Utiliza este espacio para añadir análisis sobre las diferencias entre ambos ejemplos o cualquier anotación requerida)*

**Respuesta:**
- _________________________________________________________________
- _________________________________________________________________
- _________________________________________________________________