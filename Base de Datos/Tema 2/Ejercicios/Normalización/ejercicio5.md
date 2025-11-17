### Fase 1
- *torneo*, *numeEtapa*, *codCorredor*, nombreCiclista, tiempoMin, recorridoKm

Es importante que en esta etapa al menos alguno de los atributos dependan de alguna de las PK

### Fase 2
- *codCorredor*, nombreCiclista
- *torneo*, *numeEtapa*, *codCorredor*,  tiempoMin
- *torneo*, *numeEtapa*, recorridoKm

En esta fase se mira la dependencia funcional completa de manera que sabiendo uno de los atributos me referencie inequívocamente una PK o varias

### Fase 3
- *codCorredor*, nombreCiclista
- *torneo*, *numeEtapa*, *codCorredor*,  tiempoMin
- *torneo*, *numeEtapa*, recorridoKm

En esta fase se mira la relación de atributo a atributo que no sea PK en buscar alguna correlación