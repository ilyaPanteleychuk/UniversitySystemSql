CREATE TABLE university.students_courses(
    student_id int NOT NULL,
    course_id int NOT NULL,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES university.students(student_id),
    FOREIGN KEY (course_id) REFERENCES university.courses(course_id)
);
