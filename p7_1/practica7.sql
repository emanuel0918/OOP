/*--Crear Base de Datos--*/
drop database if exists  practica7;
create database practica7;
use practica7;
/*--Tabla perros--*/
drop table if exists perro;
create table perro (
nombre nvarchar (90),
raza nvarchar(90),
edad int not null,
genero char(1) not null,
imagen longblob);

select * from perro;
select count(*) from perro;
delete  from perro where nombre='ScoobyDoo';
