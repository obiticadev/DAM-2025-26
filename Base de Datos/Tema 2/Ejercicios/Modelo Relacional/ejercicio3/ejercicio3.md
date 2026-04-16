# Este ejercicio viene hecho, solo hace falta el modelo relacional

### EMPLEADO
- cod_E (PK)
- dni
- nombre
- cod_Dep - Relación pertenece
- cod_E (FK) - Relación jefe

### DEPARTAMENTO
- cod-dep (PK)
- nombre
- localidad
- cod_E(FK) - Relación responsable



### PROYECTO
- cod_P (PK)
- presupuesto

### TRABAJA
- cod_E (PK)(FK)
- cod_P (PK)(FK)
- función

