1. tạo cơ sở dữ liệu 
CREATE TABLE customer (
    cID INT PRIMARY KEY,
    Name VARCHAR(255),
    Age TINYINT
);

CREATE TABLE orders (
    oID INT PRIMARY KEY,
    cID INT,
    oDate DATETIME,
    oTotalPrice INT,
    FOREIGN KEY (cID) REFERENCES customer(cID)
);

CREATE TABLE orderdetail (
    oID INT,
    pID INT,
    qtyID INT,
    PRIMARY KEY (oID, pID),
    FOREIGN KEY (oID) REFERENCES orders(oID)
);

CREATE TABLE product (
    pID INT PRIMARY KEY,
    pName VARCHAR(25),
    pPrice INT
);


2. insert dữ liệu vào các bảng

INSERT INTO customer VALUES 
(1, 'Minh Quan', 10),
(2, 'Ngoc Oanh', 20),
(3, 'Hong Ha', 50);

INSERT INTO orders VALUES 
(1, 1, '2006-03-21', NULL),
(2, 2, '2006-03-23', NULL),
(3, 1, '2006-03-16', NULL);

INSERT INTO orderdetail VALUES 
(1, 1, 3),
(1, 3, 7),
(1, 4, 2),
(2, 1, 8);
(3, 1, 4),
(2, 5, 3),
(2, 3, 3);

INSERT INTO product VALUES 
(1, 'May Giat', 3),
(2, 'Tu Lanh', 5),
(3, 'Dieu Hoa', 7),
(4, 'Quat', 1),
(5, 'Bep Dien', 2);

2. Hiển thị các thông tin gồm oID,cID, oDate, oTotalPrice của tất cả các hóa đơn trong bảng Orders, danh sách phải sắp xếp theo thứ tự ngày tháng, hóa đơn mới hơn nằm trên như hình sau:

SELECT oID, cID, oDate, oTotalPrice
FROM orders
ORDER BY oDate DESC;

3. Hiển thị tên và giá của các sản phẩm có giá cao nhất như sau:

SELECT pName, pPrice
FROM product
WHERE pPrice = (SELECT MAX(pPrice) FROM product);

4. Hiển thị danh sách các khách hàng đã mua hàng, và danh sách sản phẩm được mua bởi các khách đó như sau:

SELECT c.Name, p.pName
FROM customer c
JOIN orders o ON c.cID = o.cID
JOIN orderdetail od ON o.oID = od.oID
JOIN product p ON od.pID = p.pID;

5. Hiển thị tên những khách hàng không mua bất kỳ một sản phẩm nào như sau:

SELECT c.Name
FROM customer c
LEFT JOIN orders o ON c.cID = o.cID
LEFT JOIN orderdetail od ON o.oID = od.oID
WHERE od.pID IS NULL;

6. Hiển thị chi tiết của từng hóa đơn như sau :

SELECT o.oID, o.oDate,  od.qtyID, p.pName, p.pPrice
FROM orders o
JOIN orderdetail od ON o.oID = od.oID
JOIN product p ON od.pID = p.pID;

7. Hiển thị mã hóa đơn, ngày bán và giá tiền của từng hóa đơn (giá một hóa đơn được tính bằng tổng giá bán của từng loại mặt hàng xuất hiện trong hóa đơn. Giá bán của từng loại được tính = odQTY*pPrice) như sau:

SELECT o.oID, o.oDate, SUM(od.qtyID*p.pPrice) AS oTotalPrice
FROM orders o
JOIN orderdetail od ON o.oID = od.oID
JOIN product p ON od.pID = p.pID
GROUP BY o.oID;

8. Tạo một view tên là Sales để hiển thị tổng doanh thu của siêu thị như sau:

CREATE VIEW Sales AS
SELECT o.oID, o.oDate, SUM(od.qtyID*p.pPrice) AS oTotalPrice
FROM orders o
JOIN orderdetail od ON o.oID = od.oID
JOIN product p ON od.pID = p.pID
GROUP BY o.oID;

SELECT * FROM Sales;

9. Xóa tất cả các ràng buộc khóa ngoại, khóa chính của tất cả các bảng. 

ALTER TABLE orderdetail DROP FOREIGN KEY orderdetail_ibfk_1;
ALTER TABLE orders DROP FOREIGN KEY orders_ibfk_1;
ALTER TABLE orderdetail DROP PRIMARY KEY;
ALTER TABLE orders DROP PRIMARY KEY;
ALTER TABLE customer DROP PRIMARY KEY;

10. Tạo một trigger tên là cusUpdate trên bảng Customer, sao cho khi sửa mã khách (cID) thì mã khách trong bảng Order cũng được sửa theo: 

DELIMITER //
CREATE TRIGGER cusUpdate
AFTER UPDATE ON customer
FOR EACH ROW
BEGIN
    UPDATE orders
    SET cID = NEW.cID
    WHERE cID = OLD.cID;
END;
 // DELIMITER ;

11. Tạo một stored procedure tên là delProduct nhận vào 1 tham số là tên của một sản phẩm, strored procedure này sẽ xóa sản phẩm có tên được truyên vào thông qua tham số, và các thông tin liên quan đến sản phẩm đó ở trong bảng OrderDetail

DELIMITER //
CREATE PROCEDURE delProduct(IN pName VARCHAR(25))
BEGIN
    DECLARE pID INT;
    SELECT pID INTO pID
    FROM product
    WHERE pName = pName;
    
    DELETE FROM orderdetail
    WHERE pID = pID;
    
    DELETE FROM product
    WHERE pID = pID;
END;
 // DELIMITER ;
    





