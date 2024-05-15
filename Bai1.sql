1: Tạo 4 bảng

CREATE TABLE tblPhim (
    phimId INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    Ten_phim NVARCHAR(30) NOT NULL,
    Loai_phim VARCHAR(25) NOT NULL,
    Thoi_gian INT NOT NULL,
);

CREATE TABLE tblVe(
    phimId INT NOT NULL,
    gheId INT NOT NULL,
    Ngay_chieu DATETIME NOT NULL,
    Trang_thai VARCHAR(20) NOT NULL,
    FOREIGN KEY (phimId) REFERENCES tblPhim(phimId),
    FOREIGN KEY (gheId) REFERENCES tblGhe(gheId)
)

CREATE TABLE tblGhe(
    gheId INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    phongId INT NOT NULL,
    So_ghe INT NOT NULL,
    FOREIGN KEY (phongId) REFERENCES tblPhong(phongId)
);

CREATE TABLE tblPhong(
    phongId INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    Ten_phong NVARCHAR(20) NOT NULL,
    Trang_thai TINYINT NOT NULL
);

Thêm dữ liệu cho bảng

INSERT INTO tblPhim(Ten_phim, Loai_phim, Thoi_gian) VALUES ('Em bé hà nội', 'Tâm lý', 90),
('Nhiệm vụ bất khả thi', 'Hành động', 120),
('Người nhện', 'Hành động', 120),
('Người dơi', 'Viễn tưởng', 90),
('Cuốn theo chiều gió', 'Tình cảm', 120);

INSERT INTO tblPhong(Ten_phong, Trang_thai) VALUES
('Phòng chiếu 1', 1),
('Phòng chiếu 2', 1),
('Phòng chiếu 3', 0);

INSERT INTO tblGhe(phongId, So_ghe) VALUES 
(1, 'A3'),
(1, 'B5'),
(2, 'A7'),
(2, 'D1'),
(3, 'T2');

INSERT INTO tblVe(phimId, gheId, Ngay_chieu, Trang_thai) VALUES
(1, 1, '2008-10-20 ', 'Đã bán'),
(1, 3, '2008-11-20 ', 'Đã bán'),
(1, 4, '2008-12-23 ', 'Đã bán'),
(2, 1, '2008-02-14 ', 'Đã bán'),
(3, 1, '2008-02-14 ', 'Đã bán'),
(2, 5, '2008-08-03 ', 'Chưa bán'),
(2, 3, '2008-08-03 ', 'Chưa bán');

2. Hiển thị danh sách các phim (chú ý: danh sách phải được sắp xếp theo trường Thoi_gian)

SELECT * FROM tblPhim ORDER BY Thoi_gian;

3. Hiển thị Ten_phim có thời gian chiếu dài nhất

SELECT * FROM tblPhim ORDER BY Thoi_gian DESC LIMIT 1;

4. Hiển thị Ten_Phim có thời gian chiếu ngắn nhất

SELECT * FROM tblPhim ORDER BY Thoi_gian ASC LIMIT 1;

5. Hiển thị danh sách So_Ghe mà bắt đầu bằng chữ ‘A’

SELECT * FROM tblGhe WHERE So_ghe LIKE 'A%';

6. Sửa cột Trang_thai của bảng tblPhong sang kiểu nvarchar(25)

ALTER TABLE tblPhong
MODIFY COLUMN Trang_thai NVARCHAR(25);

7. Cập nhật giá trị cột Trang_thai của bảng tblPhong theo các luật sau:

Nếu Trang_thai=0 thì gán Trang_thai=’Đang sửa’

UPDATE tblPhong
SET Trang_thai = 'Đang sửa'
WHERE Trang_thai = 0;

Nếu Trang_thai=1 thì gán Trang_thai=’Đang sử dụng’

UPDATE tblPhong
SET Trang_thai = 'Đang sử dụng'
WHERE Trang_thai = 1;

Nếu Trang_thai=null thì gán Trang_thai=’Unknow’

UPDATE tblPhong
SET Trang_thai = 'Unknow'
WHERE Trang_thai IS NULL;

Sau đó hiển thị bảng tblPhong (Yêu cầu dùng procedure để hiển thị đồng thời sau khi update)

UPDATE tblPhong CASE
    WHEN Trang_thai = 0 THEN SET Trang_thai = 'Đang sửa'
    WHEN Trang_thai = 1 THEN SET Trang_thai = 'Đang sử dụng'
    WHEN Trang_thai IS NULL THEN SET Trang_thai = 'Unknow'
END;

DELIMITER //
CREATE PROCEDURE UpdateTrangThai()
BEGIN
    UPDATE tblPhong
    SET Trang_thai = CASE
        WHEN Trang_thai = 0 THEN 'Đang sửa'
        WHEN Trang_thai = 1 THEN 'Đang sử dụng'
        WHEN Trang_thai IS NULL THEN 'Unknow'
    END;
    SELECT * FROM tblPhong;
END //
DELIMITER ;

8. Hiển thị danh sách tên phim mà có độ dài >15 và < 25 ký tự

SELECT * FROM tblPhim WHERE LENGTH(Ten_phim) > 15 AND LENGTH(Ten_phim) < 25;

9. Hiển thị Ten_Phong và Trang_Thai trong bảng tblPhong trong 1 cột với tiêu đề ‘Trạng thái phòng chiếu’

SELECT CONCAT(Ten_phong, ' - ', Trang_thai) AS 'Trạng thái phòng chiếu' FROM tblPhong;

10. Tạo view có tên tblRank với các cột sau: STT(thứ hạng sắp xếp theo Ten_Phim), TenPhim, Thoi_gian

CREATE VIEW tblRank AS
SELECT ROW_NUMBER() OVER(ORDER BY Ten_phim) AS STT, Ten_phim, Thoi_gian
FROM tblPhim;

SELECT * FROM tblRank;

11. Trong bảng tblPhim :

Thêm trường Mo_ta kiểu nvarchar(max)

ALTER TABLE tblPhim
ADD Mo_ta NVARCHAR(MAX);

Cập nhật trường Mo_ta: thêm chuỗi “Đây là bộ phim thể loại ” + nội dung trường LoaiPhim

UPDATE tblPhim
SET Mo_ta = CONCAT('Đây là bộ phim thể loại ', Loai_phim);

Hiển thị bảng tblPhim sau khi cập nhật

SELECT * FROM tblPhim;

Cập nhật trường Mo_ta: thay chuỗi “bộ phim” thành chuỗi “film” (Dùng replace)

UPDATE tblPhim
SET Mo_ta = REPLACE(Mo_ta, 'bộ phim', 'film');

Hiển thị bảng tblPhim sau khi cập nhật

SELECT * FROM tblPhim;

12. Xóa tất cả các khóa ngoại trong các bảng trên.

ALTER TABLE tblVe DROP FOREIGN KEY phimId;
ALTER TABLE tblVe DROP FOREIGN KEY gheId;
ALTER TABLE tblGhe DROP FOREIGN KEY phongId;


13. Xóa dữ liệu ở bảng tblGhe

DELETE FROM tblGhe;

14. Hiển thị ngày giờ hiện chiếu và ngày giờ chiếu cộng thêm 5000 phút trong bảng tblVe

SELECT Ngay_chieu, DATE_ADD(Ngay_chieu, INTERVAL 5000 MINUTE) AS 'Ngày chiếu sau 5000 phút' FROM tblVe;









