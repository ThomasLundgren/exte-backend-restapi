CREATE TABLE IF NOT EXISTS Academy (
	Id INT PRIMARY KEY AUTO_INCREMENT,
	Name varchar(255) UNIQUE NOT NULL,
	Abbreviation varchar(255) UNIQUE NOT NULL,
	CHECK(REGEXP_LIKE(Name, '[=alnum=]') AND LENGTH(Name) > 2),
	CHECK(REGEXP_LIKE(Abbreviation, '[=alnum=]') AND LENGTH(Abbreviation) < 5)
);

CREATE TABLE IF NOT EXISTS Subject (
	Id int PRIMARY KEY AUTO_INCREMENT,
    Code varchar(2) UNIQUE NOT NULL,
	Name varchar(255) UNIQUE NOT NULL,
	AcademyId int,
	FOREIGN KEY (AcademyId)
		REFERENCES Academy(Id)
		ON DELETE CASCADE,
	CHECK(REGEXP_LIKE(Code, '[=alpha=]') AND LENGTH(Code) = 2),
	CHECK(REGEXP_LIKE(Name, '[=alnum=]') AND LENGTH(Name) > 2)
);

CREATE TABLE IF NOT EXISTS Course (
	Id int PRIMARY KEY AUTO_INCREMENT,
    CourseCode varchar(7) UNIQUE NOT NULL,
	SubjectId int,
	FOREIGN KEY (SubjectId)
		REFERENCES Subject(Id)
		ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Exam (
	Id int PRIMARY KEY AUTO_INCREMENT,
    Name varchar(255) UNIQUE NOT NULL,
    Date date NOT NULL,
    PdfUrl varchar(255) UNIQUE NOT NULL,
    CourseId int,
    FOREIGN KEY (CourseId)
		REFERENCES Course(Id)
        ON DELETE CASCADE
);

INSERT INTO Academy (Name, Abbreviation) VALUES
('Akademin för teknik och miljö', 'ATM'),
('Akademin för hälsa och arbetsliv', 'AHA'),
('Akademin för utbildning och ekonomi')

