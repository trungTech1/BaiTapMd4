1.Tạo bảng database

CREATE TABLE Class (
    ClassID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    ClassName NVARCHAR(MAX) NOT NULL,
    StartDate DATETIME NOT NULL,
    Status BIT NOT NULL
);

CREATE TABLE Student (
    StudentID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    StudentName NVARCHAR(30) NOT NULL,
    Address NVARCHAR(50) NOT NULL,
    Phone VARCHAR(20),
    Status BIT NOT NULL,
    ClassID INT NOT NULL,
    FOREIGN KEY (ClassID) REFERENCES Class(ClassID)
);

CREATE TABLE Subject (
    SubID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    SubName NVARCHAR(30) NOT NULL,
    Credit TINYINT NOT NULL DEFAULT 1,
    Status BIT NOT NULL DEFAULT 1
);

CREATE TABLE Mark (
    MarkID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    SubID INT NOT NULL UNIQUE,
    StudentID INT NOT NULL,
    Mark FLOAT DEFAULT 0 CHECK (Mark BETWEEN 0 AND 100),
    ExamTimes TINYINT DEFAULT 1,
    FOREIGN KEY (SubID) REFERENCES Subject(SubID),
    FOREIGN KEY (StudentID) REFERENCES Student(StudentID)
);

3.Sử dụng câu lệnh sử đổi bảng để thêm các ràng buộc vào các bảng theo mô tả:

Thêm ràng buộc khóa ngoại trên cột ClassID của bảng Student, tham chiếu đến cột ClassID trên bảng Class.

ALTER TABLE Student
ADD CONSTRAINT FK_Student_Class
FOREIGN KEY (ClassID) REFERENCES Class(ClassID);

Thêm ràng buộc cho cột StartDate của bảng Class là ngày hiện hành.

ALTER TABLE Class
MODIFY COLUMN StartDate DATE DEFAULT (CURRENT_DATE);

Thêm ràng buộc mặc định cho cột Status của bảng Student là 1.

ALTER TABLE Student
MODIFY COLUMN Status BIT DEFAULT 1;

Thêm ràng buộc khóa ngoại cho bảng Mark trên cột:

SubID trên bảng Mark tham chiếu đến cột SubID trên bảng Subject

StudentID tren bảng Mark tham chiếu đến cột StudentID của bảng Student.

ALTER TABLE Mark
ADD CONSTRAINT FK_Mark_Subject
FOREIGN KEY (SubID) REFERENCES Subject(SubID);

ALTER TABLE Mark
ADD CONSTRAINT FK_Mark_Student
FOREIGN KEY (StudentID) REFERENCES Student(StudentID);

4.Thêm dữ liệu vào các bảng.

INSERT INTO Class (ClassName, StartDate, Status) VALUES
('A1', '2008-12-20', 1),
('A2', '2008-12-22', 1),
('B3', CURRENT_DATE(), 0);

INSERT INTO Student (StudentName, Address, Phone, Status, ClassID) VALUES
('Hung', 'Ha noi', '0912113113', 1, 1),
('Hoa', 'Hai phong', NULL, 1, 1),
('Manh', 'HCM', '0123123123', 0, 1);

INSERT INTO Subject (SubName, Credit, Status) VALUES
('CF', 5, 1),
('C', 6, 1),
('HDJ', 5, 1),
('RDBMS', 10, 1);

INSERT INTO Mark (SubID, StudentID, Mark, ExamTimes) VALUES
(1, 1, 8, 1),
(2, 2, 10, 2),
(3, 1, 12, 1);


5.Cập nhật dữ liệu.
Thay đổi mã lớp(ClassID) của sinh viên có tên ‘Hung’ là 2.

UPDATE Student
SET ClassID = 2
WHERE StudentName = 'Hung';

Cập nhật cột phone trên bảng sinh viên là ‘No phone’ cho những sinh viên chưa có số điện thoại.

UPDATE Student
SET Phone = 'No phone'
WHERE Phone IS NULL;

Nếu trạng thái của lớp (Status) là 0 thì thêm từ ‘New’ vào trước tên lớp.

UPDATE Class
SET ClassName = CONCAT('New ', ClassName)
WHERE Status = 0;

Nếu trạng thái của status trên bảng Class là 1 và tên lớp bắt đầu là ‘New’ thì thay thế ‘New’ bằng ‘old’.

UPDATE Class
SET ClassName = REPLACE(ClassName, 'New', 'Old')
WHERE Status = 1 AND ClassName LIKE 'New%';

Nếu lớp học chưa có sinh viên thì thay thế trạng thái là 0 (status=0).

UPDATE Class
SET Status = 0
WHERE ClassID NOT IN (SELECT ClassID FROM Student);

Cập nhật trạng thái của môn học (bảng subject) là 0 nếu môn học đó chưa có sinh viên dự thi.

UPDATE Subject
SET Status = 0
WHERE SubID NOT IN (SELECT SubID FROM Mark);

6.Hiện thị thông tin.

Hiển thị tất cả các sinh viên có tên bắt đầu bảng ký tự ‘h’.

SELECT *
FROM Student
WHERE StudentName LIKE 'H%';

Hiển thị các thông tin lớp học có thời gian bắt đầu vào tháng 12.

SELECT *
FROM Class
WHERE MONTH(StartDate) = 12;

Hiển thị giá trị lớn nhất của credit trong bảng subject.

SELECT MAX(Credit)
FROM Subject;

Hiển thị tất cả các thông tin môn học (bảng subject) có credit lớn nhất.

SELECT *
FROM Subject
WHERE Credit = (SELECT MAX(Credit) FROM Subject);

Hiển thị tất cả các thông tin môn học có credit trong khoảng từ 3-5.

SELECT *
FROM Subject
WHERE Credit BETWEEN 3 AND 5;

Hiển thị các thông tin bao gồm: classid, className, studentname, Address từ hai bảng Class, student

SELECT c.ClassID, c.ClassName, s.StudentName, s.Address
FROM Class c
JOIN Student s ON c.ClassID = s.ClassID;

Hiển thị các thông tin môn học chưa có sinh viên dự thi.

SELECT *
FROM Subject
WHERE SubID NOT IN (SELECT SubID FROM Mark);


Hiển thị các thông tin môn học có điểm thi lớn nhất.

SELECT *
FROM Subject
WHERE SubID IN (SELECT SubID FROM Mark WHERE Mark = (SELECT MAX(Mark) FROM Mark));

Hiển thị các thông tin sinh viên và điểm trung bình tương ứng.

SELECT s.StudentName, AVG(Mark) AS AverageMark
FROM Student s
JOIN Mark m ON s.StudentID = m.StudentID
GROUP BY s.StudentName;

Hiển thị các thông tin sinh viên và điểm trung bình của mỗi sinh viên, xếp hạng theo thứ tự điểm giảm dần (gợi ý: sử dụng hàm rank)

SELECT s.StudentName, AVG(Mark) AS AverageMark, RANK() OVER (ORDER BY AVG(Mark) DESC) AS Rank
FROM Student s
JOIN Mark m ON s.StudentID = m.StudentID
GROUP BY s.StudentName;

Hiển thị các thông tin sinh viên và điểm trung bình, chỉ đưa ra các sinh viên có điểm trung bình lớn hơn 10.

SELECT s.StudentName, AVG(Mark) AS AverageMark
FROM Student s
JOIN Mark m ON s.StudentID = m.StudentID
GROUP BY s.StudentName
HAVING AVG(Mark) > 10;

Hiển thị các thông tin: StudentName, SubName, Mark. Dữ liệu sắp xếp theo điểm thi (mark) giảm dần. nếu trùng sắp theo tên tăng dần.

SELECT s.StudentName, sb.SubName, m.Mark
FROM Student s
JOIN Mark m ON s.StudentID = m.StudentID
JOIN Subject sb ON m.SubID = sb.SubID
ORDER BY m.Mark DESC, s.StudentName ASC;

7.Xóa dữ liệu.
Xóa tất cả các lớp có trạng thái là 0.

DELETE FROM Class
WHERE Status = 0;

Xóa tất cả các môn học chưa có sinh viên dự thi.

DELETE FROM Subject
WHERE SubID NOT IN (SELECT SubID FROM Mark);

8.Thay đổi.
Xóa bỏ cột ExamTimes trên bảng Mark.

ALTER TABLE Mark
DROP COLUMN ExamTimes;

Sửa đổi cột status trên bảng class thành tên ClassStatus.

ALTER TABLE Class
CHANGE COLUMN Status ClassStatus BIT;

Đổi tên bảng Mark thành SubjectTest.

ALTER TABLE Mark
RENAME TO SubjectTest;



