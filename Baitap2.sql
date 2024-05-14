1. Tạo 3 bảng và chèn dữ liệu như hình dưới đây:

INSERT INTO Student (RN, Name, Age) VALUES
(1, 'Nguyen Hong Ha', 20),
(2, 'Truong Ngoc Anh', 30),
(3, 'Tran Minh', 25),
(4, 'Dau Truong', 22);

INSERT INTO Test (TestID, Name) VALUES
(1, 'EPC'),
(2, 'DWMX'),
(3, 'SQL1'),
(4, 'SQL2');

INSERT INTO StudentTest (RN, TestID, [Date], Mark) VALUES
(1, 1, '2006-07-17', 8),
(1, 2, '2006-07-18', 5),
(1, 3, '2006-07-19', 7),
(2, 2, '2006-07-18', 4),
(2, 3, '2006-07-19', 2),
(3, 1, '2006-07-17', 10),
(3, 3, '2006-07-19', 1);

2. Sử dụng alter để sửa đổi

Thêm ràng buộc dữ liệu cho cột age với giá trị thuộc khoảng: 15-55

ALTER TABLE Student
ADD CONSTRAINT chk_age CHECK (Age BETWEEN 15 AND 55);


Thêm giá trị mặc định cho cột mark trong bảng StudentTest là 0

ALTER TABLE StudentTest
ALTER COLUMN Mark FLOAT DEFAULT 0;

Thêm khóa chính cho bảng studenttest là (RN,TestID)

ALTER TABLE StudentTest
DROP PRIMARY KEY,
ADD PRIMARY KEY (RN, TestID);

Thêm ràng buộc duy nhất (unique) cho cột name trên bảng Test

ALTER TABLE Test
ADD CONSTRAINT unique_name UNIQUE (Name);

Xóa ràng buộc duy nhất (unique) trên bảng Test

ALTER TABLE Test
DROP CONSTRAINT unique_name;

3. Hiển thị danh sách các học viên đã tham gia thi, các môn thi được thi bởi các học viên đó, điểm thi và ngày thi giống như hình sau:

SELECT s.Name AS 'Student Name', 
       t.Name AS 'Test Name', 
       st.Mark, 
       st.TestDate
FROM Student s
JOIN StudentTest st ON s.RN = st.RN
JOIN Test t ON st.TestID = t.TestID
ORDER BY s.Name, st.TestDate;

4. Hiển thị danh sách các học viên chưa tham gia thi, các môn thi và ngày thi giống như hình sau:

SELECT ROW_NUMBER() OVER(ORDER BY s.Name) AS '',
       s.Name AS 'Student Name', 
       t.Name AS 'Test Name' 
FROM Student s
JOIN Test t
LEFT JOIN StudentTest st ON s.RN = st.RN AND t.TestID = st.TestID
WHERE st.RN IS NULL

5. Hiển thị danh sách học viên phải thi lại, tên môn học phải thi lại và điểm thi(điểm phải thi lại là điểm nhỏ hơn 5):


SELECT ROW_NUMBER() OVER(ORDER BY s.Name) AS '',
       s.Name AS 'Student Name', 
       t.Name AS 'Test Name', 
       st.Mark,
       st.TestDate
FROM Student s
JOIN StudentTest st ON s.RN = st.RN
JOIN Test t ON st.TestID = t.TestID
WHERE st.Mark < 5
ORDER BY s.Name, st.TestDate

6. Hiển thị danh sách học viên và điểm trung bình(Average) của các môn đã thi. Kết quả sắp xếp theo điểm trung bình giảm dần:

SELECT ROW_NUMBER() OVER(ORDER BY AVG(st.Mark) DESC) AS '',
s.Name AS 'Student Name', 
       AVG(st.Mark) AS 'Average'
FROM Student s
JOIN StudentTest st ON s.RN = st.RN
GROUP BY s.Name
ORDER BY AVG(st.Mark) DESC

7. Hiển thị tên và điểm trung bình của học viên có điểm trung bình lớn nhất như sau:

SELECT s.Name AS 'Student Name', 
       AVG(st.Mark) AS 'Average'
FROM Student s
JOIN StudentTest st ON s.RN = st.RN
GROUP BY s.Name
ORDER BY AVG(st.Mark) DESC
LIMIT 1

8. Hiển thị điểm thi cao nhất của từng môn học. Danh sách phải được sắp xếp theo tên môn học như sau:

SELECT ROW_NUMBER() OVER(ORDER BY t.Name) AS '',
 s.Name AS 'Student Name', 
       t.Name AS 'Test Name'
FROM Student s
LEFT JOIN StudentTest st ON s.RN = st.RN
LEFT JOIN Test t ON st.TestID = t.TestID

9. Hiển thị danh sách tất cả các học viên và môn học mà các học viên đó đã thi nếu học viên chưa thi môn nào thì phần tên môn học để Null như sau:

SELECT ROW_NUMBER() OVER(ORDER BY s.Name) AS '',
       s.Name AS 'Student Name', 
       t.Name AS 'Test Name'
FROM Student s
LEFT JOIN StudentTest st ON s.RN = st.RN
LEFT JOIN Test t ON st.TestID = t.TestID
ORDER BY s.Name

10. Sửa (Update) tuổi của tất cả các học viên mỗi người lên một tuổi.

UPDATE Student
SET Age = Age + 1;

11. Thêm trường tên là Status có kiểu Varchar(10) vào bảng Student.

ALTER TABLE Student
ADD Status VARCHAR(10);

12. Cập nhật(Update) trường Status sao cho những học viên nhỏ hơn 30 tuổi sẽ nhận giá trị ‘Young’, trường hợp còn lại nhận giá trị ‘Old’ sau đó hiển thị toàn bộ nội dung bảng Student lên như sau:

UPDATE Student
SET Status = CASE
WHEN Age < 30 THEN 'Young'
ELSE 'Old'
END;
SELECT * FROM Student

13. Hiển thị danh sách học viên và điểm thi, dánh sách phải sắp xếp tăng dần theo ngày thi như sau:

SELECT ROW_NUMBER () OVER(ORDER BY st.TestDate) AS '',
 s.Name AS 'Student Name', 
       t.Name AS 'Test Name', 
       st.Mark, 
       st.TestDate
FROM Student s
JOIN StudentTest st ON s.RN = st.RN
JOIN Test t ON st.TestID = t.TestID
ORDER BY st.TestDate;

14. Hiển thị các thông tin sinh viên có tên bắt đầu bằng ký tự ‘T’ và điểm thi trung bình >4.5. Thông tin bao gồm Tên sinh viên, tuổi, điểm trung bình

SELECT s.Name AS 'Student Name', 
       s.Age, 
       AVG(st.Mark) AS 'Average'
FROM Student s
JOIN StudentTest st ON s.RN = st.RN
WHERE s.Name LIKE 'T%'
GROUP BY s.Name, s.Age
HAVING AVG(st.Mark) > 4.5
ORDER BY s.Name 

16. Sủa đổi kiểu dữ liệu cột name trong bảng student thành nvarchar(max)

ALTER TABLE Student
ALTER COLUMN Name NVARCHAR(MAX);

17.Cập nhật (sử dụng phương thức write) cột name trong bảng student với yêu cầu sau:

Nếu tuổi >20 -> thêm ‘Old’ vào trước tên (cột name)

Nếu tuổi <=20 thì thêm ‘Young’ vào trước tên (cột name)

UPDATE Student
SET Name = CASE
WHEN Age > 20 THEN 'Old ' + Name
ELSE 'Young ' + Name
END;

18. Xóa tất cả các môn học chưa có bất kỳ sinh viên nào thi

DELETE FROM Test
WHERE TestID NOT IN (SELECT TestID FROM StudentTest);

19. Xóa thông tin điểm thi của sinh viên có điểm <5.

DELETE
FROM StudentTest
WHERE Mark < 5;











