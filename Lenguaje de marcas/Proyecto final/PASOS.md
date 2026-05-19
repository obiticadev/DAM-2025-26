# Pasos a seguir — Práctica UT8 (Odoo Online)

Esta es mi guía de trabajo: lo que tengo que ir haciendo, tarea por tarea, dentro de Odoo, y
**en qué momento hacer cada captura** (las capturas las numero igual que en `MEMORIA.md`, así
solo tengo que arrastrarlas a la carpeta `capturas/`).

> 💡 Odoo Online va cambiando un poco los menús según la versión. Si un menú no se llama
> exactamente igual, busca el que más se parezca; la idea es la misma.

---

## Paso 0 — Registro previo (antes de empezar las tareas)

1. Entrar en `https://www.odoo.com/trial`.
2. Crear la instancia con el nombre **`infodistribucion-[TUS-INICIALES]`**.
3. Seleccionar país **España**.
4. Activar (instalar) las apps: **Ventas**, **Compras** e **Inventario**.
5. Apuntar la **URL de la instancia** → va en la portada de `MEMORIA.md`.

---

## Bloque — TAREA 1: Análisis comparativo de ERP

> Esta tarea es de investigación, no se toca Odoo todavía.

1. Entrar en las webs oficiales de **SAP Business One**, **Odoo** y **Dolibarr**.
2. Para cada uno, anotar: licencia y despliegue, módulos, base de datos, sectores/tamaño,
   coste y soporte.
3. Rellenar/comprobar la tabla que ya está en `MEMORIA.md` (sección 2) y ajustar lo que haya
   cambiado.
4. Verificar que cada dato tiene su fuente referenciada (`[1]`…`[5]`) y que los enlaces del
   anexo funcionan.
5. **Captura 1:** la tabla comparativa terminada (o la web oficial de cada ERP como prueba).

---

## Bloque — TAREA 2: Configuración y administración

### 2.1 Datos de la empresa
1. Ir a **Ajustes** (icono de engranaje, menú principal).
2. **Ajustes → Usuarios y empresas → Empresas** → abrir la empresa.
3. Poner: nombre legal *InfoDistribución S.L.*, NIF/CIF ficticio *B12345678*, dirección,
   teléfono y email.
4. Subir el **logotipo**.
5. En **Ajustes → Ajustes generales**, comprobar que la **divisa es EUR**.
6. **Captura 2:** pantalla de configuración general con nombre, CIF, logo y EUR.

### 2.2 Localización contable española
1. Instalar la app **Contabilidad** (o Facturación) si no está.
2. **Ajustes → Contabilidad → Localización fiscal** → elegir **España** (paquete de
   localización española) y guardar.
3. Ir a **Contabilidad → Configuración → Plan de cuentas** y comprobar que se ha cargado el
   plan contable general español.
4. **Captura 3:** el plan de cuentas español.

### 2.3 Dos almacenes
1. **Inventario → Configuración → Ajustes** → activar **"Ubicaciones de
   almacenamiento"** y **"Almacenes múltiples / Rutas multi-etapa"** y guardar.
2. **Inventario → Configuración → Almacenes**.
3. El que viene por defecto renombrarlo a **Almacén Central**.
4. Crear uno nuevo: **Almacén Auxiliar** (código distinto, p. ej. AUX).
5. **Captura 4:** lista de almacenes con los dos.

### 2.4 Unidades de medida
1. En **Inventario → Configuración → Ajustes**, activar **"Unidades de medida"** y guardar.
2. **Inventario → Configuración → Unidades de medida → Unidades de medida**.
3. Comprobar que existe **Unidad**. Crear si no están: **Hora** y **Licencia/mes**.
4. **Captura 5:** lista de unidades de medida.

### 2.5 Comprobar módulos enlazados
1. Ver que en el menú principal aparecen **Ventas, Compras e Inventario**.
2. (No hace falta captura aparte, pero comprobar que funcionan.)

---

## Bloque — TAREA 3: Control de acceso y seguridad

### 3.1 Crear los tres usuarios
1. **Ajustes → Usuarios y empresas → Usuarios → Nuevo**.
2. Crear **Administrador**: nombre + email. En permisos (Administración) marcar **Ajustes**
   (acceso total / administrador).
3. Crear **Comercial**: en permisos marcar **Ventas = Usuario** y **CRM**, y dejar todo lo
   demás sin acceso (Contabilidad e Inventario en blanco).
4. Crear **Responsable de almacén**: marcar **Inventario = Usuario** y dejar Ventas,
   tarifas y Contabilidad sin acceso.
5. Guardar cada uno.
6. **Captura 6:** lista de los tres usuarios.
7. **Captura 7:** pestaña de permisos del **Comercial**.
8. **Captura 8:** pestaña de permisos del **Responsable de almacén**.

### 3.2 Activar 2FA en el Administrador
1. Iniciar sesión con el usuario **Administrador** (o desde su ficha).
2. Menú de usuario (arriba a la derecha) → **Mi perfil / Preferencias → pestaña Seguridad de
   la cuenta**.
3. Pulsar **"Activar autenticación de dos factores"**.
4. Escanear el QR con una app (Google Authenticator / Authy) e introducir el código.
5. **Captura 9:** pantalla del proceso de activación de 2FA.

### 3.3 Verificar cada perfil
1. Cerrar sesión y entrar como **Comercial** → ver que solo aparece Ventas/CRM.
2. **Captura 10:** menú visto como Comercial.
3. Cerrar sesión y entrar como **Responsable de almacén** → ver que solo aparece Inventario.
4. **Captura 11:** menú visto como Responsable de almacén.

---

## Bloque — TAREA 4: Importación de datos

> ⚠️ **Orden obligatorio: 1º productos → 2º clientes → 3º pedidos.**
> Hacer login como **Administrador** para importar.

### 4.1 Importar productos (`productos.csv`)
1. Ir a **Inventario → Productos → Productos** (o Ventas → Productos).
2. Cambiar a vista de lista → menú **⚙ / Favoritos → Importar registros**.
3. Subir `productos.csv`.
4. Emparejar columnas con los campos de Odoo:
   - `nombre` → Nombre
   - `referencia` → Referencia interna
   - `categoria` → Categoría de producto
   - `precio_venta` → Precio de venta
   - `precio_coste` → Coste
   - `stock` → Cantidad disponible (o ajustar inventario después)
   - `unidad_medida` → Unidad de medida
   - `proveedor` → Proveedor
   - `descripcion` → Descripción
5. Pulsar **Probar** → si no hay errores, **Importar**.
6. Comprobar que hay **50 productos**.
7. **Captura 12:** resumen de importación de productos sin errores.

### 4.2 Importar clientes (`clientes.csv`)
1. Ir a **Contactos** (o Ventas → Clientes).
2. Vista de lista → **⚙ / Favoritos → Importar registros** → subir `clientes.csv`.
3. Emparejar columnas:
   - `nombre` → Nombre
   - `tipo` → (Empresa / Persona)
   - `nif` → NIF/CIF
   - `email` → Correo
   - `telefono` → Teléfono
   - `direccion` / `ciudad` / `codigo_postal` / `pais` → campos de dirección
   - `limite_credito` → Límite de crédito
   - `condiciones_pago` → Plazo de pago
4. **Probar → Importar**.
5. Comprobar que hay **20 clientes**.
6. **Captura 13:** resumen de importación de clientes sin errores.

### 4.3 Importar pedidos (`pedidos.csv`)
1. Ir a **Ventas → Pedidos → Pedidos**.
2. Vista de lista → **⚙ / Favoritos → Importar registros** → subir `pedidos.csv`.
3. Emparejar columnas (los pedidos enlazan con cliente y producto, por eso van los últimos):
   - `id_pedido` → Referencia del pedido
   - `fecha_pedido` → Fecha del pedido
   - `nombre_cliente` → Cliente (Odoo lo busca por nombre)
   - `referencia_producto` → Producto (línea de pedido, por referencia)
   - `cantidad` → Cantidad
   - `precio_unitario` → Precio unitario
   - `descuento_pct` → Descuento (%)
   - `estado` → Estado
   - `fecha_entrega_prevista` → Fecha de entrega prevista
   - `observaciones` → Notas
4. **Probar** primero. Si da error de que no encuentra cliente/producto, revisar que las
   Tareas 4.1 y 4.2 se hicieron bien y que los nombres/referencias coinciden.
5. **Importar** → comprobar que hay **30 pedidos**.
6. **Captura 14:** resumen de importación de pedidos sin errores.

---

## Bloque — TAREA 5: Informes y extracción

### 5.1 Informe 1 — Ventas por cliente
1. **Ventas → Informes** (vista tabla dinámica / pivote).
2. Agrupar por **Cliente** y medir **Total**.
3. Exportar: botón **Exportar** → CSV (`informe1-ventas-cliente.csv`).
4. Imprimir / Guardar como **PDF** (`informe1-ventas-cliente.pdf`).
5. **Captura 15:** el informe en pantalla.
   - _Comprobación: el total debe rondar **30.838,00 €**; el cliente top es ITSolves SL._

### 5.2 Informe 2 — Stock actual
1. **Inventario → Informes → Informe de inventario / Existencias**.
2. Agrupar por **Almacén** para ver las cantidades por almacén.
3. Exportar a CSV (`informe2-stock-actual.csv`) y guardar PDF (`informe2-stock-actual.pdf`).
4. **Captura 16:** el informe de stock.

### 5.3 Informe 3 — Pedidos pendientes
1. **Ventas → Pedidos → Pedidos**, vista lista.
2. Filtrar por estado **Confirmado** o **En tránsito** (deben salir **7 pedidos**, del
   PV-2024-024 al PV-2024-030).
3. Exportar a CSV (`informe3-pedidos-pendientes.csv`) y guardar PDF.
4. **Captura 17:** el informe de pedidos pendientes.
   - _Comprobación: importe pendiente ≈ **4.741,30 €**._

### 5.4 Hoja de cálculo — Resumen ejecutivo
1. Abrir una hoja de cálculo (Excel / LibreOffice / Google Sheets).
2. Crear 3 pestañas pegando cada CSV exportado.
3. Crear una pestaña **"Resumen ejecutivo"** con:
   - Total facturado y nº de pedidos.
   - Ranking de clientes (gráfico de barras).
   - Stock por almacén (gráfico).
   - Pedidos pendientes (total e importe).
4. Guardar como `resumen-ejecutivo.xlsx`.
5. **Captura 18:** la pestaña de resumen ejecutivo con sus gráficas.

---

## Checklist final antes de entregar

- [ ] URL de la instancia Odoo puesta en `MEMORIA.md`.
- [ ] Capturas 1–18 insertadas en la carpeta `capturas/`.
- [ ] Tabla comparativa con fuentes comprobadas.
- [ ] 3 informes en PDF.
- [ ] 3 CSV exportados de Odoo.
- [ ] Hoja de cálculo `resumen-ejecutivo.xlsx`.
- [ ] `MEMORIA.md` exportada a **PDF** (8–12 páginas).
- [ ] Revisar que TODAS las tareas están hechas tal cual pide el enunciado (si falta una → NO APTA).
