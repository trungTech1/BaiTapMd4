Viet truy van:

1.Hien thi danh sach tat ca cac hoc vien

SELECT * FROM Students;


2.Hien thi danh sach tat ca cac mon hoc

SELECT * FROM Subjects;


3.Tinh diem trung binh

SELECT students.StudentID, students.StudentName, AVG(marks.Mark) AS AverageMark
FROM students
JOIN marks ON students.StudentID = marks.StudentID
GROUP BY students.StudentID, students.StudentName;


4.Hien thi mon hoc nao co hoc sinh thi duoc diem cao nhat

SELECT s.subjectName, MAX(m.Mark) AS HighestMark
FROM marks m
JOIN subjects s ON m.subjectID = s.subjectID
GROUP BY s.subjectName
ORDER BY HighestMark DESC
LIMIT 1;


5.Danh so thu tu cua diem theo chieu giam

SELECT 
    SubjectName,
    Mark,
    DENSE_RANK() OVER (ORDER BY Mark DESC) AS RankDesc
FROM
    marks m
JOIN subjects s ON m.subjectID = s.subjectID
ORDER BY
    RankDesc;

6.Thay doi kieu du lieu cua cot SubjectName trong bang Subjects thanh nvarchar(max)

ALTER TABLE Subjects
MODIFY COLUMN SubjectName VARCHAR(255);


7.Cap nhat them dong chu « Day la mon hoc « vao truoc cac ban ghi tren cot SubjectName trong bang Subjects

UPDATE Subjects
SET SubjectName = CONCAT('Đây là môn học ', SubjectName);


8.Viet Check Constraint de kiem tra do tuoi nhap vao trong bang Student yeu cau Age >15 va Age < 50

ALTER TABLE Students
ADD CONSTRAINT chk_age
CHECK (Age > 15 AND Age < 50);


9.Loai bo tat ca quan he giua cac bang

ALTER TABLE marks DROP FOREIGN KEY mark_ibfk_1;
ALTER TABLE marks DROP FOREIGN KEY mark_ibfk_2;
ALTER TABLE classstudent DROP FOREIGN KEY classstudent_ibfk_1;
ALTER TABLE classstudent DROP FOREIGN KEY classstudent_ibfk_2;
ALTER TABLE marks DROP FOREIGN KEY marks_ibfk_1;
ALTER TABLE marks DROP FOREIGN KEY marks_ibfk_2;



10.Thêm Check Constraint cho cột Age trong bảng Students

ALTER TABLE Students
ADD CONSTRAINT chk_age
CHECK (Age > 15 AND Age < 50);

11. Xóa học viên có StudentID là 1
DELETE FROM Students
WHERE StudentID = 1;

12. Thêm cột Status có kiểu dữ liệu là Bit và giá trị Default là 1
ALTER TABLE Students
ADD Status BIT DEFAULT 1;

13. Cập nhật giá trị Status trong bảng Students thành 0
UPDATE Students
SET Status = 0;
