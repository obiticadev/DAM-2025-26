# Práctica 2 DAM

## Modelo relacional

### VIDEO-DISCO
- num_reg (PK)
- num_cop (PK)
- título

### EMPLEADOS
- dni_emp (PK)
- nom_emp
- telef_e

### SOCIOS
- num_socio (PK)
- nom_socio
- telef_s

### ALQUILER
- num_reg (PK)(FK)
- num_cop (PK)(FK)
- dni_emp (FK)
- num_socio (FK)
- fecha (PK)

### VIDEO
- num_reg (PK)(FK)
- num_cop (PK)(FK)
- protagonista

### DISCO
- num_reg (PK)(FK)
- num_cop (PK)(FK)
- autor

## Normalización

La tabla VIDEO y la tabla DISCO ya están en tercera forma normal porque cumplen con la dependencia funcional de la primera forma, la dependencia completa de la segunda forma y la transitividad entre los atributos que no son claves porque solo hay un atributo por lo que está en tercera forma

### VIDEO
- num_reg (PK)(FK)
- num_cop (PK)(FK)
- protagonista

### DISCO
- num_reg (PK)(FK)
- num_cop (PK)(FK)
- autor