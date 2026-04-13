<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:param name="iva" select="21"/>

  <xsl:template match="/">
    <xsl:for-each select="//producto">
      <xsl:variable name="iva" select="0.21"/>
      <xsl:variable name="base" select="precio"/>
      <xsl:variable name="total">
        <xsl:value-of select="base * (1 + iva)"/>
      </xsl:variable>
      <p><xsl:value-of select="nombre"/>: <xsl:value-of select="total"/> EUR</p>
    </xsl:for-each>
  </xsl:template>

</xsl:stylesheet>
