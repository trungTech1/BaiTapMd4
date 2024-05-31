package rikkeis.academys.service;

import rikkeis.academys.model.Author;
import rikkeis.academys.model.Book;
import rikkeis.academys.service.interfaces.IAuthorService;
import rikkeis.academys.util.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorService implements IAuthorService {
    @Override
    public List<Author> getAllAuthor() {
        List<Author> authors = new ArrayList<>();
        Connection conn = ConnectDB.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM author");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Author book = new Author(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBoolean("status")
                );
                authors.add(book);
            }
            return authors;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnectio(conn);
        }


    }

    @Override
    public Author getAuthorById(int id) {
        Author author = null;
        // mở 1 kết nối
        Connection conn = ConnectDB.getConnection();
        try {
            CallableStatement callSt = conn.prepareCall("select * from author where id =?");
            callSt.setInt(1, id);
            // thực thi sql
            ResultSet rs = callSt.executeQuery(); // thực trhi câu lệnh select
            if (rs.next()) {
                author = new Author(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBoolean("status")
                );
            }
            return author;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnectio(conn);
        }

    }

    @Override
    public void addAuthor(Author author) {

    }

    @Override
    public void save(Author author) {
        Connection conn = ConnectDB.getConnection();
        CallableStatement callSt = null;

        if (author.getId() == null) {
            try {
                callSt = conn.prepareCall("INSERT INTO author (name, status) VALUES (?,?)");
                callSt.setString(1, author.getName());

                callSt.setBoolean(2, author.isStatus());
                callSt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                ConnectDB.closeConnectio(conn);
            }
        } else {
            try {
                callSt = conn.prepareCall("UPDATE author SET name = ?, status = ? WHERE id = ?");
                callSt.setString(1, author.getName());
                callSt.setBoolean(2, author.isStatus());
                callSt.setInt(3, author.getId());
                callSt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                ConnectDB.closeConnectio(conn);
            }
        }
    }

    @Override
    public void deleteAuthor(int id) {
        Connection conn = ConnectDB.getConnection();
        try {
            // Check if there are any books in the category
            int bookCount = countBooksInCategory(id);
            if (bookCount > 0) {
                // If there are books, change the status to false (inactive)
                CallableStatement callSt = conn.prepareCall("update author set status = false where id = ?");
                callSt.setInt(1, id);
                callSt.executeUpdate();
            } else {
                // If there are no books, delete the category
                CallableStatement callSt = conn.prepareCall("delete from author where id = ?");
                callSt.setInt(1, id);
                callSt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnectio(conn);
        }

    }

    @Override
    public void changeAuthorStatus(int authorId, boolean status) {

    }
    public int countBooksInCategory(Integer authorId) {
        int count = 0;
        Connection conn = ConnectDB.getConnection();
        try {
            CallableStatement callSt = conn.prepareCall("select count(*) from book where author_id  = ?");
            callSt.setInt(1, authorId);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnectio(conn);
        }
        return count;
    }
}
