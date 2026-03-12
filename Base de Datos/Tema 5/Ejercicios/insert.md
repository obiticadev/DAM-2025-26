EJERCICIO INSERT INTO
create table antiguo
(
nombre varchar2(30) primary key,
edad number(3),
ciudad varchar2(40)
);

1.insertar en tabla antiguo sin poner los nombre de los campos
el alumno ‘keko’, de 31 años y de aranjuez
2.insertar en tabla antiguo poniendo los campos en este orden
edad,ciudad y nombre el alumno ‘michi’,de edad 25 y de alcorcon
3.insertar varios alumnos de golpe en la misma sentencia
‘michel’,45,paris
‘julian’,34,london
‘ana’,32,oviedo
4. insertar en alum aquellos alumnos de antiguo que tengan mas de 30 años
create table alum
(
nombre varchar2(30) primary key,
edad number(3),
ciudad varchar2(40)
);
