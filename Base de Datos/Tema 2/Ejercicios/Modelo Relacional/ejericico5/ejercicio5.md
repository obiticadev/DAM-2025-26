# Este ejercicio viene hecho, solo hace falta el modelo relacional


### DEPARTAMENTO
- departamentoID (PK)
- nombre

### EMPLEADO
- dni (PK)
- nombre
- dirección
- departamentoID (FK)

### PROYECTO
- num_proyecto (PK)
- presupuesto

### CONDUCTOR
- dni (PK)(FK)
- cat_permiso
- cant-accidentes

### CONDUCE
- dni (PK)(FK)
- matrícula (PK)(FK)
- fecha

### ADMINISTRATIVO
- dni (PK)(FK) 
- idiomas


### TÉCNICO
- dni (PK)(FK)
- especialidades

### ASIGNADO
- dni (PK)(FK)
- num_proyecto (PK)(FK)


### VEHÍCULO
- matrícula (PK)
- marca