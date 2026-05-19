# Bloque XXV · Plantillas Dinámicas: Facturas y Albaranes (Thymeleaf)

> Devolver datos en JSON es el estándar, pero en el mundo real corporativo
> tus sistemas automatizados (y servicios externos como n8n) te pedirán que
> la API genere y devuelva documentos legales estructurados (como Facturas o
> Albaranes en PDF).

---

## 25.1 El papel de Thymeleaf en una API REST

Una API REST no devuelve HTML para que el navegador del usuario pinte una página web. Sin embargo, podemos usar el motor de plantillas de Spring (**Thymeleaf**) en modo *Standalone* (aislado) como una "impresora de textos dinámicos en memoria".

1. La API recibe una petición `GET /api/facturas/123/pdf`.
2. Extrae los datos de la Base de Datos (`FacturaDTO`, `LineasDTO`).
3. Inyecta los DTOs en el *Contexto* de Thymeleaf.
4. Thymeleaf procesa la plantilla HTML (ej. `factura.html`) incrustando los datos (variables, bucles para las líneas de la tabla).
5. El String HTML devuelto se pasa a una librería generadora de PDF (como OpenHTMLToPDF).
6. La API devuelve el binario (array de bytes) del PDF al cliente.

## 25.2 Sintaxis básica para Documentos

### Inyección de Variables Simples
Los datos pasados en el `Context` se inyectan usando `${variable}`.
```html
<div class="cabecera-factura">
    <h2>Factura nº: <span th:text="${factura.numero}">000-XYZ</span></h2>
    <p>Fecha: <span th:text="${#temporals.format(factura.fechaEmision, 'dd/MM/yyyy')}">01/01/2026</span></p>
    <p>Cliente: <strong th:text="${cliente.nombreLegal}">Nombre Genérico</strong></p>
</div>
```

### Bucles e Iteraciones (Tablas de Albarán)
Para renderizar N líneas de una factura o un albarán, usamos `th:each`.
```html
<table class="tabla-albaran">
    <thead>
        <tr>
            <th>Concepto</th>
            <th>Cantidad</th>
            <th>Precio Unitario</th>
            <th>Subtotal</th>
        </tr>
    </thead>
    <tbody>
        <!-- Se generará un <tr> por cada elemento de la lista -->
        <tr th:each="linea : ${albaran.lineas}" th:classappend="${linea.descuento > 0 ? 'fila-descuento' : ''}">
            <td th:text="${linea.descripcion}">Tornillos</td>
            <td th:text="${linea.cantidad}">50</td>
            <td th:text="${#numbers.formatDecimal(linea.precioUd, 1, 'COMMA', 2, 'POINT')}">1,50 €</td>
            <td th:text="${#numbers.formatDecimal(linea.subtotal, 1, 'COMMA', 2, 'POINT')}">75,00 €</td>
        </tr>
    </tbody>
</table>
```

## 25.3 Fragmentos: Reutilización Corporativa

Si generas Facturas, Albaranes y Presupuestos, no copies y pegues el logo de tu empresa ni el pie de página legal en todos los HTMLs. Usa **Fragmentos**.

**Archivo: `fragments/footer_legal.html`**
```html
<footer th:fragment="pieCorporativo">
    <hr/>
    <p>NIF: B-12345678 | Inscrita en el Registro Mercantil</p>
    <p>IBAN para ingresos: ES00 1234 5678 9012</p>
</footer>
```

**Archivo principal: `factura.html`**
```html
<!-- Thymeleaf inyectará aquí el contenido del fragmento de forma dinámica -->
<div th:replace="~{fragments/footer_legal :: pieCorporativo}"></div>
```

## 25.4 El paso final: De HTML a PDF

Existen múltiples librerías en el ecosistema Java (`iText`, `FlyingSaucer`, `OpenHTMLToPDF`). Una vez que Thymeleaf te ha devuelto un hermoso String en HTML con todos tus datos incrustados, se transforma a PDF y se devuelve al cliente de n8n o Front-End mediante un `ResponseEntity<byte[]>`.

```java
// Ejemplo conceptual en un Controller
@GetMapping(value = "/{id}/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
public ResponseEntity<byte[]> descargarFacturaPdf(@PathVariable UUID id) {
    String htmlRenderizado = plantillaService.generarFacturaHtml(id);
    byte[] pdfBinario = pdfGeneratorService.convertirHtmlAPdf(htmlRenderizado);
    
    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=factura_" + id + ".pdf")
            .body(pdfBinario);
}
```

---

### Qué practicarás

En este bloque extra configurarás Thymeleaf como un motor independiente (*standalone*), dominarás la sintaxis de variables y bucles, reutilizarás cabeceras con Fragmentos y ensamblarás el pipeline final para devolver archivos PDF binarios desde tu API REST.
