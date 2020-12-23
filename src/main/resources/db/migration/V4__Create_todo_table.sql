create table todos (
    id int unsigned primary key auto_increment,
    text varchar(100) not null,
    done Bit not null
);