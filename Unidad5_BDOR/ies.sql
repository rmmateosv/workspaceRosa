-- Creamos un tipo de datos llamado direccion
create type direccion as(
    tipoV varchar(10),
    nombreV varchar(100),
    numero int,
    cp int
);

-- Crear la tabla personas
create table persona(
    codigo serial primary key,
    nombre varchar(100) not null,
    dir direccion
);

-- Crear la tabla alumno que hereda de personas
create table alumno(
    fechaM date,
    primary key(codigo)
)inherits (persona);
--Tabla profesor
create table profesor(
	especialidad varchar(100),
	primary key (codigo)
)inherits (persona);

-- Creamos 3 alumnos y 2 profesores
insert into alumno values 
	(default, 'Pepe', ('C/','calle1 ',8, 10300),'2019-09-15'),
    (default, 'María', ('C/','calle2 ',28, 10300),'2019/09/20'),
    (default, 'Sara', ('C/','calle3 ',81, 10300),'2019/09/21');

insert into profesor values 
	(default, 'Luis', ('C/','calle1 ',8, 10300),'Web'),
    (default, 'Gema', ('C/','calle2 ',28, 10300),'Hardware');
    
-- Creamos tabla asignaturas
create table asignatura(
    nombreC varchar(10) primary key,
    nombreL varchar(100)
);
insert into asignatura values 
	('LM', 'Lenguaje de Marcas'),
    ('BD', 'Base de datos'),
    ('SI', 'Sistemas informáticos');


    