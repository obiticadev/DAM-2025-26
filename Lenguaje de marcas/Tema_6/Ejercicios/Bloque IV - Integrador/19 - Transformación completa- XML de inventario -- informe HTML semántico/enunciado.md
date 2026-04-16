# Ejercicio 19: Ejercicio Integrador (Inventario)
Diseña una hoja XSLT completa que procese el archivo `inventario.xml` cumpliendo todos estos requisitos:

**Requisitos:**
1. **Parámetro externo:** Crea un parámetro llamado `stockMinimo` con un valor por defecto de 5.
2. **Ordenación:** Los productos deben aparecer ordenados por precio de forma descendente.
3. **Plantilla nombrada:** Crea una plantilla llamada "cabecera" que genere el bloque `<header>` con un título y el número total de productos en el inventario.
4. **Formato condicional en filas:** Cada producto irá en un bloque `<article>` con una clase CSS según su stock:
   - "agotado" si el stock es 0.
   - "bajo" si el stock es mayor que 0 pero menor o igual que `$stockMinimo`.
   - "ok" en cualquier otro caso.
5. **Cálculo en el pie:** Muestra el precio total acumulado del inventario en el pie de página (`<footer>`).
6. **Salida:** Configura `method="html"` e `indent="yes"`.
