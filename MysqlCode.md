show tables

select * from java_questions

select count(question) as questionsNumber from java_questions

-- skapa view för varje programmeringsspråk (hantera lite statistik)


CREATE TABLE java_questions (
                                id INT NOT NULL AUTO_INCREMENT,
                                question VARCHAR(500),
                                correct_answers VARCHAR(1),
                                PRIMARY KEY (id)
);

CREATE TABLE javascript_questions (
                                      id INT NOT NULL AUTO_INCREMENT,
                                      question VARCHAR(500),
                                      correct_answers VARCHAR(1),
                                      PRIMARY KEY (id)
);

CREATE TABLE Cplusplus_questions (
                                      id INT NOT NULL AUTO_INCREMENT,
                                      question VARCHAR(500),
                                      correct_answers VARCHAR(1),
                                      PRIMARY KEY (id)
);



CREATE TABLE csharp_questions (
                                  id INT NOT NULL AUTO_INCREMENT,
                                  question VARCHAR(500),
                                  correct_answers VARCHAR(1),
                                  PRIMARY KEY (id)
);

CREATE TABLE python_questions (
                                  id INT NOT NULL AUTO_INCREMENT,
                                  question VARCHAR(500),
                                  correct_answers VARCHAR(1),
                                  PRIMARY KEY (id)
);

-- Skapa unik indexering av varje question kolumnen

CREATE UNIQUE INDEX javaIndex ON java_questions (question);
CREATE UNIQUE INDEX javascriptIndex ON javascript_questions (question);
CREATE UNIQUE INDEX pythonIndex ON python_questions (question);
CREATE UNIQUE INDEX charpIndex ON csharp_questions (question);
CREATE UNIQUE INDEX cplusplusIndex ON cplusplus_questions (question);

-- Skapar flera view som visar antal frågor i respektive programmerings språk

CREATE VIEW JavaQuestionsView AS
    select question from java_questions;

CREATE VIEW PythonQuestionsView AS
select question from python_questions;

CREATE VIEW JavaScriptQuestionsView AS
select question from javascript_questions

CREATE VIEW CsharpQuestionsView AS
select question from csharp_questions

CREATE VIEW CplusplusQuestionsView AS
select question from Cplusplus_questions

select * from CplusplusQuestionsView


drop view JavaQuestionsView,PythonQuestionsView,JavaScriptQuestionsView,CsharpQuestionsView,
    CplusplusQuestionsView


-- Dummy data till java tabellen

INSERT INTO java_questions(question, correct_answers)
VALUES ('Java is an object-oriented programming language. True or false? (T/F)', 'T'),
       ('In Java, "record" is a reserved keyword. True or false? (T/F)', 'F'),
       ('Array index starts with 1. True or false? (T/F)', 'F'),
       ('Interfaces can be instantiated. True or false? (T/F)', 'F'),
       ('A .class file contains bytecodes? True or false? (T/F)', 'T');

select * from java_questions

drop table java_questions

-- Dummy data till javascript tabellen

INSERT INTO javascript_questions (question, correct_answers)
VALUES ('Currying is an advanced technique of working with functions in JavaScript. True or false? (T/F)', 'T'),
       ('Javascript is a prototype-based language. True or false? (T/F)', 'T'),
       ('Javascript is a strongly typed language. True or false? (T/F)', 'F'),
       ('ES6 introduced the "val" keyword, which is used to define a new variable in JavaScript. True or false? (T/F)', 'F'),
       ('You declare a variable with the "const" keyword to make it immutable. True or false? (T/F)', 'F');

select * from javascript_questions

INSERT INTO Cplusplus_questions (question, correct_answers)
VALUES ('C++ is a dynamically-typed language: (T/F)', 'F'),
       ('C++ is platform-independent: (T/F)', 'T'),
       ('C++ is slower than interpreted languages','F'),
       ('C++ does not support object-oriented programming:','F'),
       ('C++ is an easy language to learn:', 'F'),
       ('C++ is case-sensitive:','T'),
       ('C++ does not support generic programming:','F'),
       ('C++ has a garbage collector:','F'),
       ('C++ has a rich standard library:','T');

select * from cplusplus_questions

drop table Cplusplus_questions

show tables

-- Dummy data till C#

INSERT INTO cSharp_questions (question, correct_answers)
VALUES ('A subclass is a synonym for derived class.(T/F)','T'),
       ('A class client is also known as a class user.(T/F)','T'),
       ('You begin a for statement with the keyword base followed by a set of parentheses.(T/F)','F'),
       ('The Boolean logical AND operator is written as &&.(T/F)','F'),
       ('The assignment operators cannot be overloaded.(T/F)','T');

select * from csharp_questions

INSERT INTO python_questions (question, correct_answers)
VALUES ('Python is a scripting language.(T/F)','T'),
       ('Python is a compiled language.(T/F)','F'),
       ('Python is written in C.(T/F)','T'),
       ('Expressions in Python are always evaluated from left to right.(T/F)','F'),
       ('Python is one of Googles official programming languages(T/F)','T');

select * from python_questions

