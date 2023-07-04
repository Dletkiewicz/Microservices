CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    FIRST_NAME VARCHAR(255),
    LAST_NAME VARCHAR(255),
    GENDER VARCHAR(255),
    AGE INT
);

INSERT INTO users (FIRST_NAME, LAST_NAME, GENDER, AGE)
VALUES ('Dariusz', 'Letkiewicz', 'male', 23),
       ('Jan', 'Paweł', 'male', 60),
       ('Natalia', 'Janoszek', 'female', 33),
       ('Angelina', 'Jolie', 'female', 48),
       ('Brad', 'Pitt', 'male', 50),
       ('Jan', 'Nowak', 'male', 10),
       ('Marcin', 'Najman', 'male', 44);