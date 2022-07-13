CREATE TABLE university.students(
    student_id serial,
    group_id int,
    first_name varchar(20),
    last_name varchar(25),
    PRIMARY KEY (student_id),
    CONSTRAINT fk_group FOREIGN KEY (group_id) REFERENCES university.groups(group_id)
);


