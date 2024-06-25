create table pacientes(
    id int not null auto_increment,
    nombre varchar(100) not null,
    email varchar(100) not null,
    documento varchar(14) not null unique,
    calle varchar(100) not null,
    distrito varchar(100) not null,
    complemento varchar(100),
    numero varchar(20),
    ciudad varchar(100) not null,
    telefono varchar(20) not null,
    activo tinyint not null,
    primary key(id)
);