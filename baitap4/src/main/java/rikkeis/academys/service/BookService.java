package rikkeis.academys.service;

import org.springframework.stereotype.Service;
import rikkeis.academys.model.Book;
import rikkeis.academys.model.Catalog;
import rikkeis.academys.service.interfaces.IBookService;
import rikkeis.academys.util.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService implements IBookService {

    @Override
    public List<Book> getAllBook() {
        List<Book> books = new ArrayList<>();
        Connection conn = ConnectDB.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM book");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("catalog_id"),
                        rs.getInt("author_id"),
                        rs.getBoolean("status")
                );
                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnectio(conn);
        }
    }

    @Override
    public Book getBookById(int id) {
        Book book = null;
        // mở 1 kết nối
        Connection conn = ConnectDB.getConnection();
        try {
            CallableStatement callSt = conn.prepareCall("select * from book where id =?");
            callSt.setInt(1, id);
            // thực thi sql
            ResultSet rs = callSt.executeQuery(); // thực trhi câu lệnh select
            if (rs.next()) {
                book = new Book(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("catalog_id"),
                        rs.getInt("author_id"),
                        rs.getBoolean("status")
                );
            }
            return book;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnectio(conn);
        }
    }

    @Override
    public void save(Book book) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;

        if (book.getId() == null) {
            try {
                callSt = conn.prepareCall("INSERT INTO book (name, catalog_id, author_id, status) VALUES (?,?,?,?)");
                callSt.setString(1, book.getName());
                callSt.setInt(2, book.getCatalog());
                callSt.setDouble(3, book.getAuthor());
                callSt.setBoolean(4, book.isStatus());
                callSt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                ConnectDB.closeConnectio(conn);
            }
        } else {
            try {
                callSt = conn.prepareCall("UPDATE book SET name = ?, catalog_id = ?, author_id = ?, status = ? WHERE id = ?");
                callSt.setString(1, book.getName());
                callSt.setInt(2, book.getCatalog());
                callSt.setDouble(3, book.getAuthor());
                callSt.setBoolean(4, book.isStatus());
                callSt.setInt(5, book.getId());
                callSt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                ConnectDB.closeConnectio(conn);
            }
        }
    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public void deleteBook(int id) {
        Connection conn = ConnectDB.getConnection();

        try {
            CallableStatement callSt = conn.prepareCall("delete from book where id = ?");
            callSt.setInt(1, id);
            callSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnectio(conn);
        }

    }

    @Override
    public void changeBookStatus(int bookId, boolean status) {

    }




}
