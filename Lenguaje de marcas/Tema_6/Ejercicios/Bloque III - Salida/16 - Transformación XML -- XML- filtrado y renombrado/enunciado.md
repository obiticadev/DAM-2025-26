# Ejercicio 16: Transformación XML a XML
Genera un nuevo archivo XML basado en el catálogo original pero con cambios estructurales.

**Requisitos:**
- Selecciona solo los libros **disponibles**.
- El elemento raíz debe renombrarse a `<disponibles>`.
- Cada libro debe ser un elemento `<item>`.
- El ID del libro debe ir en un atributo llamado `ref`.
- El contenido del elemento `<item>` debe ser el título del libro.
- Usa `method="xml"` e `indent="yes"`.
