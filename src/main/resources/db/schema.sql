create table if not exists funcionarios (
    id serial primary key,
    nome varchar(100) not null,
    cargo varchar(50) not null,
    salario decimal(10, 2) not null
);
