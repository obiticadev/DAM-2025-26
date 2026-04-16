<xsl:template match="libro">
  <p>[A] Libro genérico</p>
</xsl:template>

<xsl:template match="catalogo/libro">
  <p>[B] Libro hijo de catálogo</p>
</xsl:template>

<xsl:template match="libro[@disponible='true']">
  <p>[C] Libro disponible</p>
</xsl:template>
