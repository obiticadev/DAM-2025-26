¡Por supuesto! Entendido. Aquí tienes el diseño en formato horizontal, que es ideal para comparar el "antes" y el "después" uno al lado del otro.

Este formato es muy limpio y utiliza tablas de Markdown para asegurar que siempre se vea bien.

---

### Transformación 1:N (Formato Horizontal)

> **Regla de Oro:** La tabla del lado "N" absorbe la clave `🔑` de la tabla "1" y todos los atributos de la relación `💎`.

### ANTES (Diseño Conceptual)

| Entidad `DEPARTAMENTO` (Lado 1) | Relación `💎 TRABAJA_EN` | Entidad `EMPLEADO` (Lado N) |
| :--- | :--- | :--- |
| 🔑 **id_depto** (PK) | fecha_incorporacion | 🔑 **id_emp** (PK) |
| nombre_depto | tipo_contrato | nombre_empleado |
| ubicacion | | apellido |
| | | salario |

---

### DESPUÉS (Modelo Relacional Final)

| Tabla `DEPARTAMENTO` (Sin cambios) | Tabla `EMPLEADO` (Modificada) |
| :--- | :--- |
| 🔑 **id_depto** (PK) | 🔑 **id_emp** (PK) |
| nombre_depto | nombre_empleado |
| ubicacion | apellido |
| | salario |
| | **🔗 id_depto (FK)** |
| | **fecha_incorporacion** |
| | **tipo_contrato** |

**Resultado:** La tabla `EMPLEADO` ha absorbido los atributos en **negrita**, eliminando la relación y creando un vínculo directo con `DEPARTAMENTO`.