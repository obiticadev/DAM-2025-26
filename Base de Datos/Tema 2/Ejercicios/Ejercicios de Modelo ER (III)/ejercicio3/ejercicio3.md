### **Ejercicio 3**

Se desea diseñar una Base de Datos para una Universidad que contenga información sobre los Alumnos, las Asignaturas y los Profesores. Construir un Modelo Entidad-Relación y pasarlo posteriormente al modelo Relacional teniendo en cuenta las siguientes restricciones:

*   Una asignatura puede ser impartida por muchos profesores (no a la vez), ya que pueden existir grupos.
*   Un profesor puede impartir clase de muchas asignaturas.
*   Un alumno puede estar matriculado en muchas asignaturas.
*   Se necesita tener constancia de las asignaturas en las que está matriculado un alumno, la nota obtenida y el profesor que le ha calificado.
*   A su vez, se necesita tener constancia de las asignaturas que imparten todos los profesores (independientemente de si tienen algún alumno matriculado en su grupo).
*   No existen asignaturas con el mismo nombre.
*   Un alumno no puede estar matriculado en la misma asignatura con dos profesores distintos.

Expliquen todos los supuestos semánticos que consideren oportunos y que reflejen en el esquema E/R.