



Aquí tienes la transcripción del contenido de las imágenes, formateada en Markdown (`.md`) con una estructura clara, bloques de código SQL resaltados y una presentación visualmente atractiva:

***

# 🚨 Excepciones Creadas por el Usuario en PL/SQL

A continuación se muestran dos ejemplos que ilustran cómo declarar y manejar excepciones personalizadas en Oracle Database.

---

## 📌 EJEMPLO 1: Excepción básica

En este primer caso, se declara una excepción personalizada sin asignarle un código de error específico de Oracle. Al capturarla, el sistema le asigna el código por defecto (`ECODE=1`).

```sql
declare
    ncli pedidose.clientenu%TYPE;
    errorcli exception;
begin
    select clientenu into ncli
    from pedidose
    where pedidonu = 1002;
    
    if (ncli <> 103) then
        raise errorcli;
    end if;
    
    dbms_output.put_line(ncli);
    
exception
    when errorcli then
        dbms_output.put_line('error valor ' || ' ERRM=' || sqlerrm || ' ECODE=' || sqlcode);
end;
```

**🖥️ Salida en consola:**
> `error valor  ERRM=User-Defined Exception ECODE=1`

---

## 📌 EJEMPLO 2: Uso de PRAGMA EXCEPTION_INIT

En este segundo caso, se utiliza `PRAGMA EXCEPTION_INIT` para asociar la excepción definida por el usuario a un número de error de Oracle específico (en este caso `-20015`).

```sql
declare
    ncli pedidose.clientenu%TYPE;
    errorcli exception;
    PRAGMA EXCEPTION_INIT(errorcli, -20015);
begin
    select clientenu into ncli
    from pedidose
    where pedidonu = 1002;
    
    if (ncli <> 103) then
        raise errorcli;
    end if;
    
    dbms_output.put_line(ncli);
    
exception
    when errorcli then
        dbms_output.put_line('error valor ' || ' ERRM=' || sqlerrm || ' ECODE=' || sqlcode);
end;
```

**🖥️ Salida en consola:**
> `error valor  ERRM=ORA-20015: ECODE=-20015`

*** 

*💡 **Nota de formato:** Se ha añadido indentación al código PL/SQL original para que sea mucho más legible según las buenas prácticas de programación.*