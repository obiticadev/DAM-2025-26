# Este ejercicio viene hecho, solo hace falta el modelo relacional

### AULA
- au_ID (P)
- nombre

### ASIGNATURA
- as_ID (P)
- nombre
- au_ID (PF)
- día
- hora
- dni (PF)

### PROFESOR
- dni (P)
- nombre

### ALUMNO
- dni (P)
- nombre

### CURSA
- dni (PF)
- as_ID (PF)
- calificación