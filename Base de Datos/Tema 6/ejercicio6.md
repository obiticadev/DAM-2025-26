Aquí tienes el texto de la imagen formateado en Markdown, organizado de manera clara y profesional:

# Ejercicios de VARRAYS

## Ejercicio 1
Realizar un ejercicio en PL/SQL que cumpla con los siguientes requisitos:

1.  **Declaración de tipo:** Crear un tipo `VARRAY` de 4 cadenas de caracteres llamado `TACAD`.
2.  **Declaración de variable:** Declarar una variable llamada `VTACAD` basada en el tipo anterior.
3.  **Asignación de datos:** Rellenar 3 cadenas con valores aleatorios y almacenarlas en la variable `VTACAD`.
4.  **Uso de métodos:** Imprimir el resultado de llamar a los siguientes métodos de colección:
    *   `FIRST`
    *   `COUNT`
    *   `LAST`
    *   `LIMIT`
5.  **Acceso directo:** Imprimir el valor almacenado en la **posición 3**.

---

## Ejercicio 2
Realizar un ejercicio que gestione registros y colecciones:

1.  **Tipo Registro:** Crear un tipo de registro llamado `TPERSONA` con dos campos: `EDAD` y `NOMBRE`.
2.  **Variable de Registro:** Crear una variable llamada `VTPERSONA` del tipo `TPERSONA`.
3.  **Tipo Colección:** Declarar un tipo `TAPERSONA` (Varray de `TPERSONA`) y una variable llamada `VTAPERSONA` de ese tipo.
4.  **Inserción de datos:**
    *   Crear la persona 1: `(1, 'ANA')` y almacenarla en la **posición 1** de la variable `VTAPERSONA`.
    *   Crear la persona 2: `(2, 'ANDRES')` y almacenarla en la **posición 2** de la variable `VTAPERSONA`.
    *   Crear la persona 3: `(?, 'JUAN')` **pidiendo la edad por teclado** y almacenándola en la **posición 3**.
5.  **Salida de datos:** Imprimir los nombres de todas las personas almacenadas utilizando un **bucle**.