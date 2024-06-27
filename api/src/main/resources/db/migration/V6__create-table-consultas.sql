create table consultas(

    id int not null auto_increment,
    medico_id int not null,
    paciente_id int not null,
    fecha datetime not null,
    motivo_cancelamiento varchar(100) ,

    primary key(id),

    constraint fk_consultas_medico_id foreign key(medico_id) references medicos(id),
    constraint fk_consultas_paciente_id foreign key(paciente_id) references pacientes(id)

);