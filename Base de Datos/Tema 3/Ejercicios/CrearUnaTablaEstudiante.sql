create table estudiante(
    nombre varchar2(20) primary key,
    apellido varchar2(30) not null,
    domicilio varchar2(30) unique,
    telefono varchar2(11)
);