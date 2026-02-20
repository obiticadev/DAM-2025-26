---Crear usuario con contraseña maria
create user maria identified by maria;

---Privilegio de crear sesión, de conectarse a oracle
grant create session to maria;

---Privilegio para crear tablas
grant create table to maria;

---Privilegio para insertar datos
grant insert on maria.tmaria to maria;
grant unlimited tablespace to maria;

---Revocar privilegio de crear tablas
revoke create table from maria;

---Borrar usuario en cascade, desconectando primero la conexión
drop user maria cascade;

create user oliver identified by oliver;
grant unlimited tablespace to oliver;

---Crea un rol
create role miRol;

---Agrupo todos los permisos en el rol
grant create session, create table to miRol;

---Asignar rol a un usuario
grant miRol to oliver;


create user oliver2 identified by oliver2;
grant unlimited tablespace to oliver2;
grant miRol to oliver2;

