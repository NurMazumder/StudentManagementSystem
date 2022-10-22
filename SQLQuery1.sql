
IF EXISTS (SELECT 1 FROM sys.tables WHERE NAME = 'Classes') DROP TABLE Classes 
IF EXISTS (SELECT 1 FROM sys.tables WHERE NAME = 'Student') DROP TABLE Student 
IF EXISTS (SELECT 1 FROM sys.tables WHERE NAME = 'Courses') DROP TABLE Courses 
IF EXISTS (SELECT 1 FROM sys.tables WHERE NAME = 'GradeAggregate') DROP TABLE GradeAggregate 
IF EXISTS (SELECT 1 FROM sys.tables WHERE NAME = 'Schedule') DROP TABLE Schedule 
GO

/* Table schedule*/
CREATE TABLE Schedule(
	courseId CHAR(12) NOT NULL UNIQUE,
	sectionNumber VARCHAR(8) NOT NULL UNIQUE,
	title VARCHAR(64),
	year INT,
	semester CHAR(6),
	instructor VARCHAR(24),
	department CHAR(16),
	program VARCHAR(48),
	PRIMARY KEY(courseId, sectionNumber));
	
BULK
INSERT Schedule
FROM 'D:\Documents\proj2\ScheduleSpring2021.txt'
WITH	(FIELDTERMINATOR = '\t',
		ROWTERMINATOR = '\n',
		FIRSTROW = 2);

/* DISPLAY TABLE*/
SELECT *
FROM Schedule;
GO

/*UPDATE TABLE SCHEDULE*/
UPDATE Schedule
SET instructor = ' dough nuh'
WHERE courseId = '10200 cc2' AND sectionNumber = '32119';

/* display Schedule*/
SELECT *
FROM Schedule;
GO

/* CREATE COURSES*/
CREATE TABLE Courses(
	courseId CHAR(12) PRIMARY KEY REFERENCES Schedule(courseId),
	title VARCHAR(64),
	department CHAR(16),
	program VARCHAR(48));

INSERT INTO Courses
SELECT courseId, title, department, program
from Schedule;

/*Display Courses*/
SELECT *
FROM Courses;
GO

/* TABLE STUDENTS*/
CREATE TABLE Student(
	emplId INT PRIMARY KEY,
	Name VARCHAR(32) NOT NULL,
	gender CHAR CHECK (gender = 'F' OR gender = 'M' OR gender = 'U'),
	dob DATE);

INSERT INTO Student VALUES (1, 'Name-1' , 'M' , NULL);
INSERT INTO Student VALUES (2, 'Name-2' , 'M' , NULL);
INSERT INTO Student VALUES (3, 'Name-3' , 'F' , NULL);
INSERT INTO Student VALUES (4, 'Name-4' , 'M' , NULL);
INSERT INTO Student VALUES (5, 'Name-5' , 'F' , NULL);
INSERT INTO Student VALUES (6, 'Name-6' , 'M' , NULL);

/*DISPLAY STUDENTS*/
SELECT *
FROM Student;
GO

/* TABLE CLASSES*/
CREATE TABLE Classes(
	studentID INT REFERENCES Student(emplId),
	courseId CHAR(12) REFERENCES Schedule(courseId),
	sectionNumber VARCHAR(8) REFERENCES Schedule(sectionNumber),
	year INT,
	semester CHAR(6),
	grade CHAR CHECK(grade = 'A' OR grade = 'B' OR grade = 'C' OR grade = 'D' OR grade = 'F' OR grade = 'W'),
	PRIMARY KEY(studentID, courseID, sectionNumber));

INSERT INTO Classes VALUES (1, '22100 F', '32131', 2021 , 'Spring', 'B');
INSERT INTO Classes VALUES (2, '22100 F', '32156', 2021 , 'Spring', 'A');
INSERT INTO Classes VALUES (3, '22100 F', '32131', 2021 , 'Spring', 'C');
INSERT INTO Classes VALUES (4, '22100 F', '32132', 2021 , 'Spring', 'B');
INSERT INTO Classes VALUES (5, '22100 F', '32150', 2021 , 'Spring', 'D');
INSERT INTO Classes VALUES (6, '22100 F', '32156', 2021 , 'Spring', 'A');

/*DISPLAY CLASSES*/
SELECT *
FROM Classes;
GO

/*Aggregate classes by student grade*/
CREATE TABLE GradeAggregate(
	grade CHAR,
	numberStudents INT);

INSERT INTO GradeAggregate
SELECT grade, count(grade)
FROM Classes
GROUP BY grade;

/*display gradeaggregate*/
SELECT *
FROM GradeAggregate;
GO



