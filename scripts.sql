DROP SCHEMA IF NOT EXISTS challenge; 
CREATE SCHEMA IF NOT EXISTS challenge;
SHOW SCHEMAS;
USE challenge;
SHOW TABLES; 

DROP TABLE challenge.Hired_employees;
DESC challenge.Hired_employees;
CREATE TABLE challenge.Hired_employees (
    id int not null PRIMARY KEY,
    hiredName varchar(50) not null,
    hiredDate varchar(50) not null,
    department_id int,
    job_id int,
    FOREIGN KEY (department_id) REFERENCES Departments(department_id),
    FOREIGN KEY (job_id) REFERENCES Jobs(job_id)
);

DROP TABLE challenge.Departments;
DESC challenge.departments;
CREATE TABLE challenge.Departments (
    department_id int not null PRIMARY KEY,
    deparment varchar(50)
);

DROP TABLE challenge.Jobs;
DESC challenge.Jobs;
insert into challenge.Jobs (job_id, job) values ('200','Programador');
select * from challenge.Jobs;
CREATE TABLE challenge.Jobs (
    job_id int not null PRIMARY KEY,
    job varchar(50)
);

INSERT INTO `challenge`.`Jobs` (`job_id`,`job`) VALUES (1,"ejemplo");
