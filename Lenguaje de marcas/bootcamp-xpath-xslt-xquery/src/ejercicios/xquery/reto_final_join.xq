(: =============================================================================
   RETO FINAL — Parte 1: Join XQuery
   XML fuente: erp_productos.xml + erp_proveedores.xml
   
   OBJETIVO:
   Cruzar productos y proveedores para generar un XML consolidado.
   
   NOTA: Al igual que en el ej23, los XMLs se cargan como variables:
     - $erp_productos/erp/productos/producto
     - $erp_proveedores/erp/proveedores/proveedor
   ============================================================================= :)

(: TODO: Escribe un FLWOR que construya el inventario completo:
   
   <inventario-completo>
   {
     1. Itera sobre los productos
     2. Filtra SOLO los productos con estado="activo"
     3. Busca el proveedor correspondiente (producto/@prov_id = proveedor/@id)
     4. Asegura que el proveedor existe con where exists(...)
     5. Ordena por precio de forma descendente (usando number())
     6. Devuelve:
        <item id="{ @id }" categoria="{ categoria }" stock="{ stock }">
          <nombre>{ data del nombre }</nombre>
          <precio>{ data del precio }</precio>
          <proveedor>{ data de la empresa del proveedor }</proveedor>
          <pais>{ data del pais del proveedor }</pais>
        </item>
   }
   </inventario-completo>
:)
()
