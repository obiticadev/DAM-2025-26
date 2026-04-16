<?xml version="1.0" encoding="UTF-8"?>
<stylesheet version="1.0">

  <xsl:output method="htm" encoding="UTF-8"/>

  <xsl:template match="/">
    <html>
      <body>
        <xsl:for-each select="catalogo/libro">
          <p><value-of select="titulo"/></p>
        </xsl:for-each>
      </body>
    </html>
  </xsl:template>

</stylesheet>
