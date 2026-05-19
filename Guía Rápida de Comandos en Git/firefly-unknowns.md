# Firefly III — Transacciones por identificar

**Fecha de extracción:** 2026-05-15
**Total a clarificar:** 24 patrones de comercio + 35 cargos anónimos = **59 entradas**

## Instrucciones

Lee cada entrada y, después del `= `, escribe con tus palabras **qué es** (nombre real del comercio o concepto + tipo de gasto). Lo más útil que puedes darme:

```
NOMBRE: <comercio real / persona / concepto>
QUÉ: <comida fuera | ropa | ocio | suscripción | trámite | regalo | etc.>
RECURRENTE: <sí / no>   (¿esto se repetirá en el futuro?)
NOTAS: <cualquier matiz>
```

Si una entrada **se debe ignorar** (cargo erróneo, duplicado, error de banco), pon `IGNORAR`.
Si no recuerdas qué fue, pon `?` y la dejaré en `revisar_ticket` para que la identifiques en Firefly.

Cuando termines, dímelo y leo este archivo de nuevo para regenerar el master plan con las reglas definitivas.

---

## Sección A — Comercios únicos a identificar (24)

> Son comercios que aparecen con un nombre propio pero no sé qué venden o qué tipo de gasto representan.

### A.01 — `MINI RUNNER`
- **Apariciones:** 4 transacciones
- **Importe total:** 21,00 €
- **Importe típico:** 5,25 €/tx
- **Tipo:** gasto (withdrawal)
- **Mi hipótesis:** ¿Carrera popular / inscripción deportiva infantil? ¿Tienda?
=

### A.02 — `VAOKE`
- **Apariciones:** 1
- **Importe:** 40,00 €
- **Fecha:** (única)
- **Mi hipótesis:** ¿Karaoke? ¿Marca?
= gastrobar en alcalá de henares

### A.03 — `SP ROSELIN`
- **Apariciones:** 1
- **Importe:** 43,00 €
- **Mi hipótesis:** ¿Bazar Roselin (cadena)?
= tienda de pendientes, compre unos por el día de la madre

### A.04 — `SMS BOOST* CMT5KD`
- **Apariciones:** 1
- **Importe:** 6,25 €
- **Mi hipótesis:** SMS premium / cargo dudoso (a veces son cargos no autorizados)
= este fue la compra de un mes para usar 1fincher en su version premium. el CMT5KD es un codigo autogenerado por la compra, el segundo que hice hace poco y que no está importado en firefly tiene otro código

### A.05 — `LAVAZUL`
- **Apariciones:** 2
- **Importe total:** 2,00 €
- **Mi hipótesis:** ¿Lavandería autoservicio / lavado de coche?
= lavado de coche

### A.06 — `LA MASKERA`
- **Apariciones:** 1
- **Importe:** 16,00 €
- **Mi hipótesis:** ¿Tienda de cosmética (mascarillas)? ¿Restaurante temático?
= pizzería

### A.07 — `HOMA ALCALA`
- **Apariciones:** 1
- **Importe:** 14,00 €
- **Mi hipótesis:** ¿Yoga / centro de bienestar?
= tienda de decoración y artículos para el hogar

### A.08 — `ITUREG C B`
- **Apariciones:** 1
- **Importe:** 12,00 €
- **Mi hipótesis:** ¿Iturralde Registro / gestoría / ITV?
= 

### A.09 — `ALCAL HENARES - C` *(truncado)*
- **Apariciones:** 1
- **Importe:** 35,00 €
- **Mi hipótesis:** Cargo del Ayuntamiento de Alcalá (¿tasa? ¿basuras? ¿multa?)
=

### A.10 — `DIVERSION LAND 20`
- **Apariciones:** 1
- **Importe:** 10,00 €
- **Mi hipótesis:** Parque de atracciones / juegos infantiles
=

### A.11 — `FAUSTINO PRIETO 2`
- **Apariciones:** 1
- **Importe:** 5,90 €
- **Mi hipótesis:** ¿Comercio local con nombre propio (pescadería, panadería)?
=

### A.12 — `FOTOS PANTA`
- **Apariciones:** 2
- **Importe total:** 10,05 €
- **Mi hipótesis:** Tienda de fotografía / revelado / fotomatón
=

### A.13 — `ANDYUS ALCALA`
- **Apariciones:** 2
- **Importe total:** 13,85 €
- **Mi hipótesis:** ¿Tienda de ropa / accesorios?
=

### A.14 — `MIDUDEV`
- **Apariciones:** 1
- **Importe:** 119,79 €
- **Mi hipótesis:** Curso de programación de Miguel Ángel Durán (midudev) — **Educación y Desarrollo**, tag `formacion`, `online`
=

### A.15 — `MILBBY.OASIZ.TORR`
- **Apariciones:** 1
- **Importe:** 12,98 €
- **Mi hipótesis:** Cadena MILBBY (papelería/manualidades) en el centro comercial Oasiz Torrejón — **Hogar & Bricolaje**, tag `papeleria`, `manualidades`
=

### A.16 — `OASIZ` *(genérico, sin tienda)*
- **Apariciones:** 1
- **Importe:** 59,90 €
- **Mi hipótesis:** Compra en el CC Oasiz pero el banco no registró la tienda específica. Tienes que recordar.
=

### A.17 — `PAYPAL *MINISFZDQ`
- **Apariciones:** 1
- **Importe:** 672,52 €  ← **importe alto, conviene identificar**
- **Mi hipótesis:** ¿Tienda "Mini" algo? PayPal solo da código truncado.
=

### A.18 — `PAYPAL *DORIANORI`
- **Apariciones:** 1
- **Importe:** 209,30 €
- **Mi hipótesis:** ¿Doria & Nori? ¿Pasta / restaurante? ¿Marca de ropa?
=

### A.19 — `PAYPAL *HOLA MUND`
- **Apariciones:** 2
- **Importe total:** 40,51 €
- **Mi hipótesis:** Curso "Hola Mundo" (programación) — **Educación y Desarrollo**, tag `formacion`, `online`, `paypal`
=

### A.20 — `PAYPAL *descargaf` *(probablemente "descargafilm" o similar)*
- **Apariciones:** 2
- **Importe total:** 12,00 €
- **Importe típico:** 6,00 €/tx
- **Mi hipótesis:** Servicio digital de descargas / suscripción mensual
=

### A.21 — `LLAO LLAO OASIZ M`
- **Apariciones:** 2
- **Importe total:** 10,40 €
- **Mi hipótesis:** Llao Llao (heladería/yogur helado) — **Comida Fuera de Casa**, tag `heladeria`, `capricho`
=

### A.22 — `CINESA OASIZ`
- **Apariciones:** 1
- **Importe:** 27,50 €
- **Mi hipótesis:** Cinesa cines en CC Oasiz — **Ocio & Suscripciones**, tag `cine`, `social`
=

### A.23 — `CARREF ALCALA`
- **Apariciones:** 22
- **Importe total:** 1.092,53 €
- **Mi hipótesis:** Carrefour Alcalá (cuenta como supermercado Carrefour, ya bien clasificado) — confirmar que va a regla 7.2.
=

### A.24 — `ALCAMPO S A`
- **Apariciones:** 16
- **Importe total:** 311,03 €
- **Mi hipótesis:** Alcampo (descriptor de la sociedad jurídica) — confirmar que va a regla 7.3.
=

---

## Sección B — Cargos anónimos (35)

> Son transacciones sin nombre de comercio en la descripción (importadas con descriptor genérico tipo `COMPRA CON TARJETA` o `ALCALA DE HENARES`). El banco no envió el nombre del comercio. Para identificarlas necesitas mirar el ticket / la app del banco / tu memoria.
>
> Para cada una, indica el comercio real (si lo recuerdas) y la categoría sugerida.

### B.01 — `ALCALA DE HENAR` · 2025-09-01 · 21,74 €
=

### B.02 — `ALCALA DE HENAR` · 2025-09-01 · 31,50 €
=

### B.03 — `BLINKAY ALCALA DE` · 2025-09-03 · 1,25 €
> *(Blinkay es la app de zona azul de Alcalá — sería `Parking`/`Trámites`)*
=

### B.04 — `COMPRA CON TARJETA` · 2025-09-06 · 32,64 €
=

### B.05 — `8080 CL FED GARCI` · 2025-09-07 · 2,00 €
=

### B.06 — `ALCALA 2` · 2025-09-16 · 6,65 €
=

### B.07 — `COMPRA CON TARJETA` · 2025-09-19 · 23,98 €
=

### B.08 — `241213 ALCALA DE` · 2025-09-23 · 26,00 €
=

### B.09 — `COMPRA CON TARJETA` · 2025-10-13 · 29,01 €
=

### B.10 — `ALCALA DE HENARES` · 2025-10-15 · 16,59 €
=

### B.11 — `6222 Alcala de He` · 2025-10-30 · 35,95 €
=

### B.12 — `COMPRA CON TARJETA` · 2025-11-06 · 30,69 €
=

### B.13 — `ALCALA DE HENAR` · 2025-11-23 · 55,89 €
=

### B.14 — `ALCALA DE HENAR` · 2025-11-25 · 41,98 €
=

### B.15 — `ALCALA 2` · 2025-12-01 · 3,57 €
=

### B.16 — `6222 Alcala de He` · 2025-12-02 · 5,98 €
=

### B.17 — `TRANSF. A SU FAVOR` · 2025-12-18 · 100,00 € *(INGRESO)*
> *Recurrente cada ~30 días. ¿Es una mensualidad fija que recibes? ¿Padres? ¿Alquiler de habitación?*
=

### B.18 — `COMPRA CON TARJETA` · 2025-12-26 · 25,02 €
=

### B.19 — `TRANSF. A SU FAVOR` · 2026-01-19 · 100,00 € *(INGRESO)*
=

### B.20 — `COMPRA CON TARJETA` · 2026-01-20 · 32,31 €
=

### B.21 — `ALCALA DE HENAR` · 2026-02-02 · 3,09 €
=

### B.22 — `ALCALA DE HENAR` · 2026-02-07 · 26,00 €
=

### B.23 — `TRANSF. A SU FAVOR` · 2026-02-26 · 100,00 € *(INGRESO)*
=

### B.24 — `241213 ALCALA DE` · 2026-03-08 · 1,00 €
=

### B.25 — `ALCALA DE HENAR` · 2026-03-14 · 24,00 €
=

### B.26 — `TRANSF. A SU FAVOR` · 2026-03-20 · 100,00 € *(INGRESO)*
=

### B.27 — `COMPRA CON TARJETA` · 2026-03-30 · 29,99 €
=

### B.28 — `ALCALA DE HENARES` · 2026-03-30 · 124,00 €
> *Patrón: 124,00 € recurrente los días 30/06/16/23 — parece una **cuota mensual fija**. ¿Cuál es?*
=

### B.29 — `COMPRA CON TARJETA` · 2026-04-06 · 25,52 €
=

### B.30 — `ALCALA DE HENARES` · 2026-04-06 · 124,00 €
=

### B.31 — `COMPRA CON TARJETA` · 2026-04-16 · 35,72 €
=

### B.32 — `ALCALA DE HENARES` · 2026-04-16 · 124,00 €
=

### B.33 — `TRANSF. A SU FAVOR` · 2026-04-20 · 100,00 € *(INGRESO)*
=

### B.34 — `COMPRA CON TARJETA` · 2026-04-23 · 23,07 €
=

### B.35 — `ALCALA DE HENARES` · 2026-04-23 · 124,00 €
=

---

## Patrones detectados (te ayudo a responder más rápido)

### Patrón 1: `ALCALA DE HENARES` · 124,00 € (5 instancias)
> Es claramente una **cuota mensual recurrente** en torno al día 23–30 de cada mes desde marzo-2026. Si confirmas qué es, creo **una sola regla** que las cubra todas. Posibles candidatos: cuota mensual de gimnasio premium, parking mensual, escuela, suscripción a club…
**Identificación:** =

### Patrón 2: `TRANSF. A SU FAVOR` · 100,00 € (5 instancias)
> Ingreso recurrente mensual de 100 €. Posibles: aportación familiar, devolución de un préstamo personal, alquiler de un trastero/parking, sublalquiler de habitación, etc.
**Identificación:** =

### Patrón 3: `COMPRA CON TARJETA` (10 instancias, importes variables 23–35 €)
> Cargos POS sin merchant identificado. Si los **pagas habitualmente al mismo sitio**, decime cuál: lo añado al catch-all como excepción. Si son cosas distintas cada vez (un día gasolinera de pueblo, otro día panadería sin TPV moderno…), las marcaré con `revisar_ticket` y las clasificarás manualmente cada mes.
**Identificación:** =

### Patrón 4: `ALCALA DE HENAR` (truncado) · importes variables
> Cargos en establecimientos varios de Alcalá. ¿Pueden ser todos del **mismo comercio** (p.ej. un pequeño supermercado, una panadería, un bar habitual)? Si es así, pon su nombre y los englobo.
**Identificación:** =

### Patrón 5: `241213 ALCALA DE` y `6222 Alcala de He`
> Los prefijos numéricos sugieren identificadores de TPV. Si los reconoces como el mismo comercio en distintas terminales, indícalo.
**Identificación:** =

---

Cuando rellenes todo, dímelo y vuelvo a leer este archivo para integrar las respuestas en el `firefly-master-plan.md` y proceder con la aplicación.
